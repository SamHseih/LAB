package ccu.pllab.tcgen.sd2clg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import com.parctechnologies.eclipse.EclipseException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ccu.pllab.tcgen.AbstractCLG.CLGConnectionNode;
//import ccu.pllab.tcgen.home_sd2clg.TestData;//////////////
import ccu.pllab.tcgen.AbstractCLG.CLGConstraintNode;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractSyntaxTree.AbstractSyntaxTreeNode;
import ccu.pllab.tcgen.AbstractSyntaxTree.ClassifierContext;
import ccu.pllab.tcgen.AbstractSyntaxTree.PackageExp;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.TestCase.TestDataClassLevel;
import ccu.pllab.tcgen.TestCase.TestScriptGeneratorClassLevel;

import ccu.pllab.tcgen.ast.ASTNode;
import ccu.pllab.tcgen.ast.ASTUtil;
import ccu.pllab.tcgen.ast.OperationCallExp;
import ccu.pllab.tcgen.ast.PropertyCallExp;

import ccu.pllab.tcgen.libs.DresdenOCLASTtoInternelAST;
import ccu.pllab.tcgen.libs.node.INode;
import ccu.pllab.tcgen.libs.pivotmodel.ClassDiagInfo;
import ccu.pllab.tcgen.libs.pivotmodel.ClassDiagToJson;
import ccu.pllab.tcgen.libs.pivotmodel.Model;
import ccu.pllab.tcgen.libs.pivotmodel.type.TypeFactory;
import ccu.pllab.tcgen.transform.AST2CLG;
import ccu.pllab.tcgen.transform.OCL2AST;
import ccu.pllab.tcgen.transform.UML2SymbolTable;
import tcgenplugin_2.handlers.ClassLevelHandler;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.standalone.facade.StandaloneFacade;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.clgGraph2Path.CoverageCriterionManager;
import ccu.pllab.tcgen.exe.main.Main;

import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import java.util.HashMap;

public class SDXML2SD {

	public SDXML2SD() {
	}

	// -----------------------**************** guard condition

	public StateDigram convert(File sd, File uml, SymbolTable symbolTable) throws Exception {
		Document doc;
		File fXmlFile = sd; // new File(filename)
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();

		NodeList nList_subvertex = doc.getElementsByTagName("subvertex");
		NodeList nList_transition = doc.getElementsByTagName("transition");
		StateDigram StateD = null;
		// ------------SD Attribute-------------------
		String packagename = symbolTable.getClassName();
		StateD = new StateDigram(symbolTable.getClassName(), symbolTable.getAttribute());
		// -----------------------------

		int connectionnodecount = 0;
		// -----------------------state--------------------------------------------
		for (int temp_subvertex = 0; temp_subvertex < nList_subvertex.getLength(); temp_subvertex++) {
			Node nNode_subvertex = nList_subvertex.item(temp_subvertex);
			Element eElement_subvertex = (Element) nNode_subvertex;

			if (eElement_subvertex.getAttribute("xmi:type").equals("uml:Pseudostate")) {
				String startAttr = eElement_subvertex.getAttribute("xmi:id");
				State initialstate = new InitialState(1, startAttr, "InitialState");
				StateD.addstate(initialstate);

			} else if (eElement_subvertex.getAttribute("xmi:type").equals("uml:FinalState")) {
				String endAttr = eElement_subvertex.getAttribute("xmi:id");
				State finalstate = new FinalState(2, endAttr, "FinalState");
				StateD.addstate(finalstate);

			} else {
				connectionnodecount++;
				State st = new State(2 + connectionnodecount, eElement_subvertex.getAttribute("xmi:id"),
						eElement_subvertex.getAttribute("name"));
				StateD.addstate(st);
			}
		} // end for

		// ------------------transition-----------------------------------------

		ArrayList<String> guard_conslist = new ArrayList<String>();
		CLGConstraint method = null, guard = null;
		CLGGraph guardCLG = null; // 2019
		State source = null, target = null;
		ArrayList<INode> ocl_guard324 = new ArrayList<INode>();

		for (int temp_transition = 0; temp_transition < nList_transition.getLength(); temp_transition++) {
			Node nNode_transition = nList_transition.item(temp_transition);
			Element eElement_transition = (Element) nNode_transition;
			guard_conslist = new ArrayList<String>();

			// ----------------------------guard------------------------------------
			if (!eElement_transition.getAttribute("guard").isEmpty()) {
				NodeList nList_guard = doc.getElementsByTagName("ownedRule");
				for (int temp_guard = 0; temp_guard < nList_guard.getLength(); temp_guard++) {
					Node nNode_guard = nList_guard.item(temp_guard);
					Element eElement_guard = (Element) nNode_guard;

					if (eElement_transition.getAttribute("guard").equals(eElement_guard.getAttribute("xmi:id"))) {
						guard_conslist.add(eElement_guard.getAttribute("name"));
						// ---------parse guard -------
						String inocl, endocl, guardocl = null;
						inocl = "package tcgen\n\tcontext " + packagename + "\n\tinv:\n\t\t";
						endocl = "\nendpackage";
						guardocl = eElement_guard.getAttribute("name");

						File file1 = new File(DataWriter.testGuardOCL_path);
						if (!file1.exists()) {
							file1.mkdir();
						}
						FileWriter dataFile = new FileWriter(DataWriter.testGuardOCL_path + symbolTable.getClassName()
								+ "Guard" + temp_transition + ".ocl");
						BufferedWriter input = new BufferedWriter(dataFile);
						input.write(inocl + guardocl + endocl);
						input.close();

						OCL2AST oclParser = new OCL2AST();
						oclParser.makeAST(new File(DataWriter.testGuardOCL_path + symbolTable.getClassName() + "Guard"
								+ temp_transition + ".ocl"));
						AbstractSyntaxTreeNode guardAST = oclParser.getAbstractSyntaxTree();
						guardAST.changeAssignToEqual();
						System.out.println(guardAST.ASTInformation() + " AAAAA ");
						guardAST.toGraphViz();
						System.out.println(guardAST.childNodeInfo() + " AAAAA ");

						AST2CLG ast2clg = new AST2CLG();
						ast2clg.genInvCLG(guardAST, symbolTable, Main.criterion);
						guard = ast2clg.getInvCLGConstraint();

					}
				}

			} // end has guard

			else {
				guard = null;
			}
			// --------------------------------------------------------------------

			// ------------find source and target-------------------------

			source = StateD.getStates().get(eElement_transition.getAttribute("source"));
			target = StateD.getStates().get(eElement_transition.getAttribute("target"));

//			for (int find = 0; find < recordconnectionnode.size(); find++) {
//				if (recordconnectionnode.get(find).equals(eElement_transition.getAttribute("source"))) {
//					if (find == 0)
//						source = initialstate;
//					else if (find == 1)
//						source = finalstate;
//					else
//						source = StateD.getStates().get(find);
//				}
//				if (recordconnectionnode.get(find).equals(eElement_transition.getAttribute("target"))) {
//					if (find == 0)
//						target = initialstate;
//					else if (find == 1)
//						target = finalstate;
//					else
//						target = StateD.getStates().get(find);
//				}
//			}
			// -----------------------method-------------------------------------
			String transitionName, transitionXmiID, methodname = null, methodobj;
			ArrayList<String> methodarg = new ArrayList<String>();
			transitionName = eElement_transition.getAttribute("name");
			transitionXmiID = eElement_transition.getAttribute("xmi:id");
			if (transitionName.isEmpty()) {
				method = null;
			} else {
				int CheckMethod = transitionName.indexOf("(");
				int CheckMethod1 = transitionName.indexOf(")");
				int CheckMethodOBJ = transitionName.indexOf("."); // if no =-1
				if (CheckMethodOBJ == -1) {
					methodobj = "";
					if (CheckMethod != -1) { // is method, no obj
						methodname = transitionName.substring(0, CheckMethod);
						// no many arg
						if (transitionName.substring(CheckMethod + 1, CheckMethod1).indexOf(",") == -1) {
							// methodarg.add(parmename.substring(CheckMethod+1,CheckMethod1));
							String flag = transitionName.substring(CheckMethod + 1, CheckMethod1);

							if (!flag.equals(""))
								methodarg.add(flag);
//							else
//								methodarg.add(" "); // no arg , give " "
						} else {
							String[] a = transitionName.substring(CheckMethod + 1, CheckMethod1).split(",");
							for (int i = 0; i < a.length; i++) {
								methodarg.add(a[i]);
							}
						}
						// 20210216 盢methodInvocationNode把计эCLGConstraint,
						// 把计эArrayList<CLGConstraint>
						ArrayList<CLGConstraint> methodargList = new ArrayList<>();
						for (String argName : methodarg)
							methodargList.add(new CLGObjectNode(argName.trim()));
						method = new CLGMethodInvocationNode(
								new CLGObjectNode("self", Main.typeTable.get(packagename, null)),
								Main.typeTable.get(packagename, null), methodname, methodargList);
					} else {
						// for(int i=0;i<guard;i++){
						if (guard.equals(transitionName)) {
							// System.out.println("!!! yes get method guard~
							// "+testguard.get(i).getImgInfo());
							// method=guard;
							System.out.println(transitionName + " 2019硂㎞㎞");
							// 2019 EX: money = 0
						} else {
							methodname = transitionName;

							if (Character.isUpperCase(methodname.charAt(0))) {
								method = new CLGClassNode(methodname);
							} else {
								method = new CLGObjectNode(methodname);
							}

						}
						// }//end for
					}
				} // end no method obj
				else {
					methodobj = transitionName.substring(0, CheckMethodOBJ);
					VariableType methodobjType = Main.typeTable.get(methodobj, null);
					if (CheckMethod != -1) {
						methodname = transitionName.substring(CheckMethodOBJ + 1, CheckMethod);
						if (transitionName.substring(CheckMethod + 1, CheckMethod1).indexOf(",") == -1) {
							// methodarg.add(parmename.substring(CheckMethod+1,CheckMethod1));
							String flag = transitionName.substring(CheckMethod + 1, CheckMethod1);
							if (!flag.equals(""))
								methodarg.add(flag);
//							else
//								methodarg.add(" "); // no arg , give " "
						} else {
							String[] a = transitionName.substring(CheckMethod + 1, CheckMethod1).split(",");
							for (int i = 0; i < a.length; i++) {
								methodarg.add(a[i].trim());
							}
						}
						// 20210216 盢methodInvocationNode把计эCLGConstraint,
						// 把计эArrayList<CLGConstraint>
						ArrayList<CLGConstraint> methodargList = new ArrayList<>();
						for (String argName : methodarg)
							methodargList.add(new CLGObjectNode(argName));
						method = new CLGMethodInvocationNode(new CLGObjectNode(methodobj), methodobjType, methodname,
								methodargList);
					} else {
//						methodname = parmename.substring(CheckMethodOBJ + 1);
//						methodarg.add(" ");

						// 20210216 盢methodInvocationNode把计эCLGConstraint,
						// 把计эArrayList<CLGConstraint>
						ArrayList<CLGConstraint> methodargList = new ArrayList<>();
						for (String argName : methodarg)
							methodargList.add(new CLGObjectNode(argName));
						method = new CLGMethodInvocationNode(new CLGObjectNode(methodobj), methodobjType, methodname,
								methodargList);
					}
				}
			}
//			if (guard != null)
//				;

			Transition TaranS = new Transition(temp_transition + 1, transitionXmiID, method, source, target, guard);
			source.addtransition(TaranS);
			StateD.addtransition(TaranS);
		} // end for transition
		return StateD;
	}// end public void convert()

}
