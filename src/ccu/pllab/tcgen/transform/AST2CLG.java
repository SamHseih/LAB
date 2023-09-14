package ccu.pllab.tcgen.transform;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import ccu.pllab.tcgen.AbstractCLG.CLGCriterionTransformer;
import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGStartNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGLiteralNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.AbstractSyntaxTree.AbstractSyntaxTreeNode;
import ccu.pllab.tcgen.AbstractSyntaxTree.ClassifierContext;
import ccu.pllab.tcgen.AbstractSyntaxTree.OperationContext;
import ccu.pllab.tcgen.AbstractSyntaxTree.PackageExp;
import ccu.pllab.tcgen.AbstractSyntaxTree.PropertyCallExp;
import ccu.pllab.tcgen.AbstractSyntaxTree.StereoType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.clg2path.CriterionFactory;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import ccu.pllab.tcgen.exe.main.Main;
import tcgenplugin_2.handlers.BlackBoxHandler;
import tcgenplugin_2.handlers.ClassLevelHandler;
import tcgenplugin_2.handlers.WhiteBoxHandler;

public class AST2CLG {
	private ArrayList<CLGGraph> clg_list = new ArrayList<CLGGraph>();
	private CLGGraph invCLG;
	private CLGConstraint invCLGConstraint;
	private String clgFolder;

	public AST2CLG() throws IOException {
	}

	// 20210810黑箱，可寫多個precondition和postcondition
	public void genCLG(AbstractSyntaxTreeNode ast, SymbolTable symbolTab, Criterion criterion) throws IOException {
		ArrayList<AbstractSyntaxTreeNode> astNode = ((PackageExp) ast).getTreeNode();
		CLGCriterionTransformer clgTF = new CLGCriterionTransformer();

		for (int clg_number = 0; clg_number < astNode.size(); clg_number++) {
			if (astNode.get(clg_number) instanceof OperationContext) {// 如果是method，分開pre和post的CLG來做

				int pre_number = ((OperationContext) astNode.get(clg_number)).getPreNum();
				int post_number = ((OperationContext) astNode.get(clg_number)).getPostNum();
				String classname = ((OperationContext) astNode.get(clg_number)).getClassName();
				String methodname = ((OperationContext) astNode.get(clg_number)).getMethodName();
				ArrayList<PropertyCallExp> parameters = ((OperationContext) astNode.get(clg_number)).getParameters();
				VariableType returntype = ((OperationContext) astNode.get(clg_number)).getReturnType();

				CLGGraph pre_clg = null;
				CLGGraph pre_not_clg = null;
				CLGGraph post_clg = null;
				ArrayList<CLGGraph> exceptionCLGList = new ArrayList<CLGGraph>();
				ArrayList<CLGConstraint> preCLGConstraintList = new ArrayList<CLGConstraint>();

				for (int stereo = 0; stereo < ((OperationContext) astNode.get(clg_number)).getStereoType()
						.size(); stereo++) {
					StereoType astConverting = ((OperationContext) astNode.get(clg_number)).getStereoType().get(stereo);
					boolean isPre = false;
					//precondition增加_pre
					if (astConverting.getStereoType().equals("precondition")) {
						isPre = true;
						astConverting.preconditionAddPre();
					}
					CLGConstraint temp = astConverting.getTreeNode().AST2CLG(symbolTab, methodname, isPre);

					if (temp == null) {
						Main.isSort = true;
						pre_number--;
						continue;
					}

					if (astConverting.getStereoType().equals("precondition")) {
						CLGGraph clg = new CLGGraph(temp.clone());// 直接從pre或post內文下手
						clg = this.CLGTransformer(clg, criterion);
						this.setCLGStartNode(classname, methodname, parameters, returntype, clg);

						// 收集 precondition CLGConstraint
						preCLGConstraintList.add(temp.clone());
						// 收集 Exception CLG
						exceptionCLGList.add(this.genExceptionCLG(astConverting.getException()));

						if (pre_clg == null) {
							pre_clg = clg;

						} else {
							pre_clg.graphAnd(clg);

						}
					} else {
						CLGGraph clg = new CLGGraph(temp.clone());// 直接從pre或post內文下手
						clg = this.CLGTransformer(clg, criterion);
						this.setCLGStartNode(classname, methodname, parameters, returntype, clg);

						if (post_clg == null) {
							post_clg = clg;
						} else {
							post_clg.graphOr(clg);
						}
					}
				}

				// pre_not_clg CLG
				pre_not_clg = this.genExceptionPathCLG(exceptionCLGList, preCLGConstraintList, criterion);

				boolean isPre = false;
				if (pre_number >= 1) {
					if (this.invCLGConstraint != null)
						post_clg.graphAnd(this.genInvCLG(this.invCLGConstraint.clone(), classname, symbolTab, isPre));
					pre_clg.graphAnd(post_clg);
					pre_clg.graphOr(pre_not_clg);

					if (!classname.equalsIgnoreCase(methodname) && this.invCLGConstraint != null) {
						isPre = true;
						CLGGraph invPreCLG = this.genInvCLG(this.invCLGConstraint.clone(), classname, symbolTab, isPre);
						invPreCLG.graphAnd(pre_clg);
						this.setCLGStartNode(classname, methodname, parameters, returntype, invPreCLG);
						clg_list.add(invPreCLG);
						this.genCLGGraph(invPreCLG, ((CLGStartNode) pre_clg.getStartNode()).getMethodName());// 建圖CLG.dot
					} else {
						clg_list.add(pre_clg);
						this.genCLGGraph(pre_clg, ((CLGStartNode) pre_clg.getStartNode()).getMethodName());// 建圖CLG.dot
					}

				} else {// 有postcondition的情況
					CLGGraph invPreCLG = null;
					if (this.invCLGConstraint != null) {
						post_clg.graphAnd(this.genInvCLG(this.invCLGConstraint.clone(), classname, symbolTab, isPre));
						isPre = true;
						invPreCLG = this.genInvCLG(this.invCLGConstraint.clone(), classname, symbolTab, isPre);
						this.setCLGStartNode(classname, methodname, parameters, returntype, invPreCLG);
					}

					if (!classname.equalsIgnoreCase(methodname) && this.invCLGConstraint != null) {
						invPreCLG.graphAnd(post_clg);
						clg_list.add(invPreCLG);
						this.genCLGGraph(invPreCLG, ((CLGStartNode) post_clg.getStartNode()).getMethodName());// 建圖CLG.dot
					} else {
						clg_list.add(post_clg);
						this.genCLGGraph(post_clg, ((CLGStartNode) post_clg.getStartNode()).getMethodName());// 建圖CLG.dot
					}
				}
			} else {// 處理inv的部分

				this.invCLGConstraint = ((ClassifierContext) astNode.get(clg_number)).getInv().getTreeNode()
						.AST2CLG(symbolTab, "", true);
				String className = ((ClassifierContext) astNode.get(clg_number)).getClassName();
				boolean isPre = false;
				this.invCLG = this.genInvCLG(this.invCLGConstraint.clone(), className, symbolTab, isPre);
				this.genCLGGraph(this.invCLG, "Inv");// 建圖CLG.dot

			}

		}
	}

	private void setCLGStartNode(String classname, String methodname, ArrayList<PropertyCallExp> parameters,
			VariableType returntype, CLGGraph clg) {
		// *******設定CLG的起始點的值
		((CLGStartNode) (clg.getStartNode())).setClassName(classname);
		((CLGStartNode) (clg.getStartNode())).setMethodName(methodname);
		ArrayList<String> argument = new ArrayList<String>();
		ArrayList<VariableType> argumenttype = new ArrayList<VariableType>();

		// 設定attributes
		((CLGStartNode) clg.getStartNode()).setClassAttributes(Main.localSymbolTable.get(0).getAttribute());

		for (int number = 0; number < parameters.size(); number++) {
			argument.add(parameters.get(number).getVariable());
			argumenttype.add(parameters.get(number).getType());
		}
		((CLGStartNode) (clg.getStartNode())).setMethodParameters(argument);
		((CLGStartNode) (clg.getStartNode())).setMethodParameterTypes(argumenttype);

		if (classname.equalsIgnoreCase(methodname))
			((CLGStartNode) (clg.getStartNode())).setIsConstructor(true);
		else {
			((CLGStartNode) (clg.getStartNode())).setIsConstructor(false);
			((CLGStartNode) (clg.getStartNode())).setRetType(returntype);
		}
	}

	private CLGGraph CLGTransformer(CLGGraph clg, Criterion criterion) {
		CLGCriterionTransformer clgTF = new CLGCriterionTransformer();
		if (criterion.equals(CriterionFactory.Criterion.dcc) || criterion.equals(CriterionFactory.Criterion.dccdup))
			return clgTF.CriterionTransformer(clg, CriterionFactory.Criterion.dcc);
		else if (criterion.equals(CriterionFactory.Criterion.mcc)
				|| criterion.equals(CriterionFactory.Criterion.mccdup))
			return clgTF.CriterionTransformer(clg, CriterionFactory.Criterion.mcc);
		else
			return clgTF.CriterionTransformer(clg, CriterionFactory.Criterion.dc);
	}

	private CLGGraph genExceptionCLG(String exceptionStr) {
		CLGOperatorNode exceptionExp = new CLGOperatorNode("=");
		exceptionExp.setType(new VoidType());
		CLGVariableNode result = new CLGObjectNode("exception", new VoidType());
		CLGLiteralNode liter = new CLGLiteralNode("\"Exception\"", new VoidType());
		if (!exceptionStr.equals(""))
			liter = new CLGLiteralNode("\"" + exceptionStr + "\"", new VoidType());
		exceptionExp.setLeftOperand(result);
		exceptionExp.setRightOperand(liter);
		CLGGraph expceptionCLG = new CLGGraph(exceptionExp);// 比較好做圖的連接pre和post
		expceptionCLG = this.CLGTransformer(expceptionCLG, Main.criterion);

		return expceptionCLG;
	}

	private CLGGraph genExceptionPathCLG(ArrayList<CLGGraph> exceptionCLGList,
			ArrayList<CLGConstraint> preCLGConstraintList, Criterion criterion) {

		CLGGraph returnCLGGraph = null;

		for (int exceptionIndex = 0; exceptionIndex < exceptionCLGList.size(); exceptionIndex++) {

			CLGGraph pathCLG = null;
			for (int exceptionCount = 0; exceptionCount < exceptionCLGList.size(); exceptionCount++) {

				CLGConstraint con = preCLGConstraintList.get(exceptionCount).clone();
				if (exceptionIndex == exceptionCount) {
					CLGCriterionTransformer clgTF1 = new CLGCriterionTransformer();
					con = clgTF1.Demongan((con));
				}

				CLGGraph clg = new CLGGraph(con.clone());// 直接從pre或post內文下手
				clg = this.CLGTransformer(clg, criterion);

				if (pathCLG == null) {
					pathCLG = clg;
				} else {
					pathCLG.graphAnd(clg);
				}
			}

			pathCLG.graphAnd(exceptionCLGList.get(exceptionIndex));

			if (returnCLGGraph == null) {
				returnCLGGraph = pathCLG;
			} else {
				returnCLGGraph.graphOr(pathCLG);
			}

		}

		return returnCLGGraph;
	}

	public ArrayList<CLGGraph> getCLGGraph() {

		return this.clg_list;
	}

	public CLGGraph getInvCLG() {
		return this.invCLG;
	}

	public CLGConstraint getInvCLGConstraint() {
		return this.invCLGConstraint;
	}

	public void genInvCLG(AbstractSyntaxTreeNode ast, SymbolTable symbolTab, Criterion criterion) throws IOException {
		ArrayList<AbstractSyntaxTreeNode> astNode = ((PackageExp) ast).getTreeNode();
		CLGCriterionTransformer clgTF = new CLGCriterionTransformer();

		for (int clg_number = 0; clg_number < astNode.size(); clg_number++) {
			if (!(astNode.get(clg_number) instanceof OperationContext)) {// 僅處理inv的部分
				this.invCLG = new CLGGraph(((ClassifierContext) astNode.get(clg_number)).getInv().getTreeNode()
						.AST2CLG(symbolTab, "", true));
				String classname = ((ClassifierContext) astNode.get(clg_number)).getClassName();
				((CLGStartNode) (this.invCLG.getStartNode())).setClassName(classname);
				((CLGStartNode) (this.invCLG.getStartNode())).setMethodName(classname);
				((CLGStartNode) (this.invCLG.getStartNode())).setIsConstructor(false);
				this.invCLG = clgTF.CriterionTransformer(this.invCLG, criterion);
				this.invCLG = clgTF.CriterionTransformer(this.invCLG, criterion);
				break;
			}
		}
	}

	private CLGGraph genInvCLG(CLGConstraint invCLGConstraint, String className, SymbolTable symbolTable,
			boolean isPre) {

		if (isPre)
			invCLGConstraint.preconditionAddPre(symbolTable, "");

		CLGGraph invCLG = new CLGGraph(invCLGConstraint.clone());
		ArrayList<PropertyCallExp> parameters = new ArrayList<PropertyCallExp>();
		VariableType returntype = new VoidType();
		this.setCLGStartNode(className, className, parameters, returntype, invCLG);

		CLGCriterionTransformer clgTF = new CLGCriterionTransformer();
		invCLG = clgTF.CriterionTransformer(invCLG, CriterionFactory.Criterion.dc);

		if (this.invCLG == null && !isPre) {
			this.invCLG = invCLG;
		}

		return invCLG;
	}

//	外掛新增
	public void genCLGGraph(CLGGraph clg, String methodName) throws IOException {
		FileWriter dataFile;
		File clgFolder = new File(DataWriter.Clg_output_path);
		if (!clgFolder.exists()) {
			clgFolder.mkdirs();
		}

		File clgdot;
		String imgName = ((CLGStartNode) clg.getStartNode()).getClassName() + methodName;
		if (BlackBoxHandler.CurrentEditorName != null) {
			clgdot = new File(clgFolder.getPath() + "/" + imgName + ".dot");
		} else if (WhiteBoxHandler.CurrentEditorName != null) {
			clgdot = new File(clgFolder.getPath() + "/" + imgName + ".dot");
		} else {
			clgdot = new File(clgFolder.getPath() + "/" + imgName + ".dot");
		}

		// File clgdot = new
		// File(clgFolder.getPath()+"/"+BlackBoxHandler.CurrentEditorName+methodName+".dot");
		dataFile = new FileWriter(clgdot.getPath());
		BufferedWriter input = new BufferedWriter(dataFile);
		input.write(clg.graphDraw());
		input.close();

		if (BlackBoxHandler.CurrentEditorName != null) {
			new ProcessBuilder("dot", "-Tsvg", DataWriter.Clg_output_path + imgName + ".dot", "-o",
					DataWriter.Clg_output_path + imgName + ".svg").start();
		} else if (WhiteBoxHandler.CurrentEditorName != null) {
			new ProcessBuilder("dot", "-Tpng", DataWriter.Clg_output_path + imgName + ".dot", "-o",
					DataWriter.Clg_output_path + imgName + ".png").start();
		} else {
			new ProcessBuilder("dot", "-Tpng", DataWriter.Clg_output_path + imgName + ".dot", "-o",
					DataWriter.Clg_output_path + imgName + ".png").start();
		}

	}

}
