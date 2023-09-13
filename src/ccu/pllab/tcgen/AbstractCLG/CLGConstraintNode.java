package ccu.pllab.tcgen.AbstractCLG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.MethodCompleteCLG2CLP.StateNodeCLP;
import ccu.pllab.tcgen.MethodCompleteCLG2CLP.TransitionClp;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;
import tcgenplugin_2.handlers.ClassLevelHandler;

public class CLGConstraintNode extends CLGNode {
	private static ArrayList visted = new ArrayList();
	private static ArrayList vistedForClass = new ArrayList();
	private static int xlabel_count = 1;
	private int xlabel_id;
	private CLGConstraint constraint;

	public CLGConstraintNode(CLGConstraint constraint) {
		super();
		this.constraint = constraint;
		this.xlabel_id = xlabel_count++;
	}

	public CLGConstraint getConstraint() {
		return this.constraint;
	}

	private void setXlabelID(int xlabelID) {
		this.xlabel_id = xlabelID;
	}

	@Override
	public String toGetImgInfo() {
		String result = "";
		result += (this.getId() + " " + String.format(
				"[shape=\"box\", label=\"%s\",style = \"filled\",fillcolor = \"yellow\",xlabel=\"[%d]\"]" + "\n",
				this.constraint.getImgInfo(), this.xlabel_id));
		return result;
	}

	@Override
	public String toCLPInfo(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet) {
		String result = "";

		// 複製constraint再rename
		CLGConstraint cloneCons = this.constraint.clone();
		cloneCons.renameVar(variableSet, defineVariableSet, true);

		CLPInfo con_clpinfo = cloneCons.getCLPInfo(variableSet, new HashMap<>());

		for (int i = 0; i < con_clpinfo.getMethodCallCLP().size(); i++) {
			if (con_clpinfo.getMethodCallCLP().get(i) != null)
				result += "\r\n" + con_clpinfo.getMethodCallCLP().get(i) + ",";
		}

		if (con_clpinfo.getReturnCLP() != null)
			result += "\r\n" + con_clpinfo.getReturnCLP() + ",";// 未處理變數重複問題?

		return result;
	}

	public int getXLabelId() {
		return this.xlabel_id;
	}

	public String toString() {
		return "[" + this.getXLabelId() + "]";
	}

	public static void reset() {
		xlabel_count = 1;
		visted.clear();
		vistedForClass.clear();
	}

	public static void initID() {
		xlabel_count = 1;
	}

	public static void resetVisited() {
		visted.clear();
	}

	@Override
	public CLGNode clone() {
		CLGConstraintNode node = new CLGConstraintNode(this.constraint.clone());
		node.setXlabelID(this.xlabel_id);
		return node;
	}

//	@Override
//	public ArrayList genMethodCLP(String className, String methodName, ArrayList classAttributes,
//			ArrayList methodParameters, ArrayList localParameters, String result) {
//		CLGNode nextNode = this.getSuccessor().get(0);
//		ArrayList attributes_pre = new ArrayList();
//		ArrayList arg_pre = new ArrayList();
//		ArrayList<ArrayList<String>> clp = new ArrayList();
//		ArrayList newLocalParameters = new ArrayList();
//		String newClp = "";
//
//		for (int i = 0; i < classAttributes.size(); i++) {
//			// attributes_pre.add(classAttributes.get(i)+"_0");
//			attributes_pre.add(classAttributes.get(i) + "_pre");
//		}
//		for (int j = 0; j < methodParameters.size(); j++) {
//			// arg_pre.add(methodParameters.get(j)+"_0");
//			arg_pre.add(methodParameters.get(j) + "_pre");
//		}
//
//		/* 判斷有無local */
//		for (int k = 0; k < this.getConstraint().getLocalVariable().split(",").length; k++) {
//			if (this.getConstraint().getLocalVariable().split(",")[k].equals("") != true) {
//				if (localParameters.contains(this.getConstraint().getLocalVariable().split(",")[k]) != true)
//					localParameters.add(this.getConstraint().getLocalVariable().split(",")[k]);
//			}
//		}
//		// System.out.println(localParameters);
//
//		if (visted.contains(this.getId()) != true) {
//			visted.add(this.getId());
//			clp.add(new ArrayList());
//			clp.get(0).add("0");
//
//			newClp = this.toCLPInfo();
//
//			/* fix it=it+1 */
//			if (newClp.contains("It#=(It+1)")) {
//				newClp = newClp.replace("It#=(It+1)", "It_1#=(It+1)");
//				if (localParameters.contains("It_1") != true) {
//					localParameters.set(localParameters.indexOf("It"), "It_1");
//				}
//			}
//
//			/* fix it to It bug */
//			newClp = newClp.replaceAll("=it", "=It");
//
//			/* 下一個還是CLGConstraintNode用逗號隔開，表示and */
//			if (nextNode.getClass().equals(this.getClass())) {
//				clp.get(0).add("	" + newClp + ", \n");
//			}
//			/* 判斷是否要產生下一次呼叫 */
//			else if (nextNode.getClass().equals(CLGConnectionNode.class)) {
//				clp.get(0).add("	" + newClp + ", \n");
//				clp.get(0)
//						.add("	" + className + methodName + "_node_" + ((CLGConnectionNode) nextNode).getConnectionId()
//								+ "(" + attributes_pre + "," + arg_pre + "," + classAttributes + "," + methodParameters
//								+ ", " + result + ", Exception, " + localParameters + "). \n");
//			}
//			/* 遇到EndNode表示predicate結束 */
//			else {
//				clp.get(0).add("	" + newClp + ", \n");
//				clp.get(0).add("	" + className + methodName + "_endNode(" + attributes_pre + "," + arg_pre + ","
//						+ classAttributes + "," + methodParameters + ", " + result + ", Exception). \n");
//			}
//			clp.addAll(nextNode.genMethodCLP(className, methodName, classAttributes, methodParameters, localParameters,
//					result));
//		}
//		return clp;
//	}

	@Override
	public ArrayList genMethodCLP(String className, String methodName, ArrayList localParameters,
			HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, SymbolTable sym, ArrayList<String> argNameList,
			HashMap<String, String> flattenAndCheckDomainCLP) {
		CLGNode nextNode = this.getSuccessor().get(0);
		String attributes_pre = "Self_pre";
		String attributes_post = "Self";
		String arg_pre = "[";
		String arg_post = "[";
		for (String argName : argNameList) {
			arg_pre += argName + "_pre, ";
			arg_post += argName + ", ";
		}
		arg_pre = Utility.delEndRedundantSymbol(arg_pre, ", ") + "]";
		arg_post = Utility.delEndRedundantSymbol(arg_post, ", ") + "]";
		ArrayList newLocalParameters = new ArrayList();
		ArrayList<ArrayList<String>> clp = new ArrayList<>();
		String newClp = "";

		String classNameFix = Utility.titleToLowercase(className);
		String methodNameFix = Utility.titleToUppercase(methodName);

		if (visted.contains(this.getId()) != true) {
			visted.add(this.getId());
			clp.add(new ArrayList());
			clp.get(0).add("0");

			newClp = this.toCLPInfo(variableSet, defineVariableSet);
			// rename local var
			for (Object localVar : localParameters) {
				if (variableSet.containsKey(localVar)) {
					if (variableSet.get(localVar) != 1)
						newLocalParameters.add(Utility.titleToUppercase(localVar + "_" + variableSet.get(localVar)));
					else
						newLocalParameters.add(Utility.titleToUppercase((String) localVar));
				} else {
					newLocalParameters.add(Utility.titleToUppercase((String) localVar));
				}
			}

			/* 下一個還是CLGConstraintNode用逗號隔開，表示and */
			if (nextNode.getClass().equals(this.getClass())) {
				clp.get(0).add(newClp);
			}
			/* 判斷是否要產生下一次呼叫 */
			else if (nextNode.getClass().equals(CLGConnectionNode.class)) {
				clp.get(0).add(newClp);
				clp.get(0).add(flattenAndCheckDomainCLP.get(CLGStartNode.getFlattenOutputStringMapKey()));
				clp.get(0).add(flattenAndCheckDomainCLP.get(CLGStartNode.getCheckDomainMapKey()));
				clp.get(0)
						.add("\t\r\n" + classNameFix + methodNameFix + "_node_"
								+ ((CLGConnectionNode) nextNode).getConnectionId() + "(" + attributes_pre + ", "
								+ arg_pre + ", " + attributes_post + ", " + arg_post + ", Result, Exception, "
								+ newLocalParameters + "). \n");
			}
			/* 遇到EndNode表示predicate結束 */
			else {
				clp.get(0).add(newClp);
				clp.get(0).add(flattenAndCheckDomainCLP.get(CLGStartNode.getFlattenOutputStringMapKey()));
				clp.get(0).add(flattenAndCheckDomainCLP.get(CLGStartNode.getCheckDomainMapKey()));
				clp.get(0).add("\r\n	" + classNameFix + methodNameFix + "_endNode(" + attributes_pre + ", " + arg_pre
						+ ", " + attributes_post + ", " + arg_post + ", Result, Exception). \n");
			}
			clp.addAll(nextNode.genMethodCLP(className, methodName, localParameters, variableSet, defineVariableSet, sym, argNameList,
					flattenAndCheckDomainCLP));
		}
		return clp;
	}

	private String renameVar(String string, HashMap<String, Integer> variableSet) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public ArrayList genMethodCLPForClass(String className, String methodName, ArrayList classAttributes,
//			ArrayList methodParameters, ArrayList localParameters, ArrayList result) {
//		CLGNode nextNode = this.getSuccessor().get(0);
//		ArrayList attributes_pre = new ArrayList();
//		ArrayList arg_pre = new ArrayList();
//		// String return_value= "Result";
//		ArrayList return_value = new ArrayList(Arrays.asList("Result"));
//		ArrayList newLocalParameters = new ArrayList();
//		String newClp = "";
//
//		for (int i = 0; i < classAttributes.size(); i++) {
//			attributes_pre.add(classAttributes.get(i) + "_pre");
//		}
//		for (int j = 0; j < methodParameters.size(); j++) {
//			arg_pre.add(methodParameters.get(j) + "_pre");
//		}
//
//		/* 判斷有無local */
//		for (int k = 0; k < this.getConstraint().getLocalVariable().split(",").length; k++) {
//			if (this.getConstraint().getLocalVariable().split(",")[k].equals("") != true) {
//				if (localParameters.contains(this.getConstraint().getLocalVariable().split(",")[k]) != true)
//					localParameters.add(this.getConstraint().getLocalVariable().split(",")[k]);
//			}
//		}
//		// System.out.println(localParameters);
//
//		if (vistedForClass.contains(this.getId()) != true) {
//			vistedForClass.add(this.getId());
//
//			newClp = this.toCLPInfo();
//
//			/* fix it=it+1 */
//			if (newClp.contains("It#=(It+1)")) {
//				newClp = newClp.replace("It#=(It+1)", "It_1#=(It+1)");
//				if (localParameters.contains("It_1") != true) {
//					localParameters.set(localParameters.indexOf("It"), "It_1");
//				}
//			}
//
//			/* fix it to It bug */
//			newClp = newClp.replaceAll("=it", "=It");
//
//			/* 下一個還是CLGConstraintNode用逗號隔開，表示and */
//			if (nextNode.getClass().equals(this.getClass())) {
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).addConstraint(newClp);
//			}
//			/* 判斷是否要產生下一次呼叫 */
//			else if (nextNode.getClass().equals(CLGConnectionNode.class)) {
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).addConstraint(newClp);
////				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).setNextState(new CLPState("_node_" +((CLGConnectionNode)this.getSuccessor().get(0)).getConnectionId(),attributes_pre.toString(),arg_pre.toString(),classAttributes.toString(),methodParameters.toString(),result.toString(),"Exception",localParameters.toString()));
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1)
//						.setNextState(new StateNodeCLP(
//								"_node_" + ((CLGConnectionNode) this.getSuccessor().get(0)).getConnectionId(),
//								attributes_pre, arg_pre, classAttributes, methodParameters, result,
//								new ArrayList(Arrays.asList("Exception")), localParameters, false));
//			}
//			/* 遇到EndNode表示predicate結束 */
//			else {
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).addConstraint(newClp);
////				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).setNextState(new CLPState("_endNode",attributes_pre.toString(),arg_pre.toString(),classAttributes.toString(),methodParameters.toString(),result.toString(),"Exception",""));
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1)
//						.setNextState(new StateNodeCLP("_endNode", attributes_pre, arg_pre, classAttributes,
//								methodParameters, result, new ArrayList(Arrays.asList("Exception")), null, false));
//				// clp.get(0).add(" "+className + methodName + "_endNode("+ attributes_pre +","+
//				// arg_pre +","+ classAttributes +","+ methodParameters +", ["+ result +"],
//				// [Exception]). \n");
//			}
//
//			if (newClp.contains("Result")) {
////				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).getThisState().setResult( "[Result|Result1]");
////				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).getNextState().setResult("Result1");
//
//				ArrayList<TransitionClp> temp = ClassLevelHandler.clp;
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).getThisState()
//						.setResult(new ArrayList(Arrays.asList("Result|Result1")));
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).getNextState()
//						.setResult(new ArrayList(Arrays.asList("Result1")));
//			}
//			if (newClp.contains("Exception")) {
////				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).getThisState().setResult("[Exception|Exception1]");
////				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).getNextState().setResult("Exception1");
//
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).getThisState()
//						.setResult(new ArrayList(Arrays.asList("Exception|Exception1")));
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).getNextState()
//						.setResult(new ArrayList(Arrays.asList("Exception1")));
//			}
//
//			// clp.addAll(nextNode.genMethodCLP(className, methodName, classAttributes,
//			// methodParameters, localParameters, result));
//			this.getSuccessor().get(0).genMethodCLPForClass(className, methodName, classAttributes, methodParameters,
//					localParameters, return_value);
//		}
//		return ClassLevelHandler.clp;
//	}

	@Override
	public ArrayList genMethodCLPForClass(String className, String methodName, ArrayList localParameters,
			HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, SymbolTable sym, ArrayList<String> argNameList, String flattenCLP) {
		CLGNode nextNode = this.getSuccessor().get(0);
		ArrayList attributes_pre = new ArrayList();
		ArrayList attributes_post = new ArrayList();
		ArrayList arg_pre = new ArrayList();
		ArrayList arg_post = new ArrayList();
		ArrayList result = new ArrayList();
		ArrayList return_value = new ArrayList(Arrays.asList("Result"));
		ArrayList newLocalParameters = new ArrayList();
		String newClp = "";

		String classNameFix = Utility.titleToLowercase(className);
		String methodNameFix = Utility.titleToUppercase(methodName);

		attributes_pre.add("Self_pre");
		attributes_post.add("Self");
		arg_pre.add("[");
		arg_post.add("[");
		String argPreStr = "";
		String argPostStr = "";
		for (String argName : argNameList) {
			argPreStr += argName + "_pre, ";
			argPostStr += argName + ", ";
		}
		arg_pre.add(Utility.delEndRedundantSymbol(argPreStr, ", ") + "]");
		arg_post.add(Utility.delEndRedundantSymbol(argPostStr, ", ") + "]");
		result.add("Result");

		if (vistedForClass.contains(this.getId()) != true) {
			vistedForClass.add(this.getId());

			newClp = this.toCLPInfo(variableSet, defineVariableSet);
			// rename local var
			for (Object localVar : localParameters) {
				if (variableSet.containsKey(localVar)) {
					if (variableSet.get(localVar) != 1)
						newLocalParameters.add(Utility.titleToUppercase(localVar + "_" + variableSet.get(localVar)));
					else
						newLocalParameters.add(Utility.titleToUppercase((String) localVar));
				} else {
					newLocalParameters.add(Utility.titleToUppercase((String) localVar));
				}
			}

			/* 下一個還是CLGConstraintNode用逗號隔開，表示and */
			if (nextNode.getClass().equals(this.getClass())) {
				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).addConstraint(newClp);
			}
			/* 判斷是否要產生下一次呼叫 */
			else if (nextNode.getClass().equals(CLGConnectionNode.class)) {
				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).addConstraint(newClp);
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).setNextState(new CLPState("_node_" +((CLGConnectionNode)this.getSuccessor().get(0)).getConnectionId(),attributes_pre.toString(),arg_pre.toString(),classAttributes.toString(),methodParameters.toString(),result.toString(),"Exception",localParameters.toString()));
				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1)
						.setNextState(new StateNodeCLP(
								"_node_" + ((CLGConnectionNode) this.getSuccessor().get(0)).getConnectionId(),
								attributes_pre, arg_pre, attributes_post, arg_post, result,
								new ArrayList(Arrays.asList("Exception")), newLocalParameters, false));
			}
			/* 遇到EndNode表示predicate結束 */
			else {
				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).addConstraint(newClp);
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).setNextState(new CLPState("_endNode",attributes_pre.toString(),arg_pre.toString(),classAttributes.toString(),methodParameters.toString(),result.toString(),"Exception",""));
				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1)
						.setNextState(new StateNodeCLP("_endNode", attributes_pre, arg_pre, attributes_post, arg_post,
								result, new ArrayList(Arrays.asList("Exception")), null, false));
				// clp.get(0).add(" "+classNameFix + methodNameFix + "_endNode("+ attributes_pre
				// +","+
				// arg_pre +","+ classAttributes +","+ methodParameters +", ["+ result +"],
				// [Exception]). \n");
			}

			if (newClp.contains("Result")) {
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).getThisState().setResult( "[Result|Result1]");
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).getNextState().setResult("Result1");

				ArrayList<TransitionClp> temp = ClassLevelHandler.clp;
				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).getThisState()
						.setResult(new ArrayList(Arrays.asList("Result|Result1")));
				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).getNextState()
						.setResult(new ArrayList(Arrays.asList("Result1")));
			}
			if (newClp.contains("Exception")) {
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).getThisState().setResult("[Exception|Exception1]");
//				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).getNextState().setResult("Exception1");

				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).getThisState()
						.setResult(new ArrayList(Arrays.asList("Exception|Exception1")));
				ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).getNextState()
						.setResult(new ArrayList(Arrays.asList("Exception1")));
			}

			// clp.addAll(nextNode.genMethodCLP(classNameFix, methodNameFix,
			// classAttributes,
			// methodParameters, localParameters, result));
			this.getSuccessor().get(0).genMethodCLPForClass(className, methodName, localParameters, variableSet, defineVariableSet, sym,
					argNameList, flattenCLP);
		}
		return ClassLevelHandler.clp;
	}

	public LinkedHashMap<String, Integer> getConsNodeLocalVar(HashMap<String, Integer> variableSet, boolean isPre) {
		// 使用linkedHashMap維持變數順序並去除重複的變數
		LinkedHashMap<String, Integer> retVar = new LinkedHashMap<>();
		if (!isPre)
			this.constraint.renameVar(variableSet, new HashSet<>(), false);
		for (String localVar : this.constraint.getLocalVar()) {
			retVar.put(localVar, 1);
		}

		CLGNode nextNode = this.getSuccessor().get(0);
		if (nextNode.getClass().equals(CLGConstraintNode.class)) {
			retVar.putAll(((CLGConstraintNode) nextNode).getConsNodeLocalVar(variableSet, isPre));
		}
		return retVar;
	}

	@Override
	public ArrayList<String> getMethodAllLocalVar(HashMap<String, Integer> variableSet) {
		ArrayList<String> retList = new ArrayList<>();

		if (visted.contains(this.getId()) != true) {
			visted.add(this.getId());

			retList.addAll(this.constraint.getLocalVar());
			for (CLGNode n : this.getSuccessor()) {
				retList.addAll(n.getMethodAllLocalVar(variableSet));
			}
		}
		return retList;
	}

	@Override
	public String getOriginalConsName() {
		return this.getConstraint().getOriginalConName();
	}

//	@Override
//	public String genMethodCLP(String className, String methodName, ArrayList classAttributes, ArrayList methodParameters) {
//		String CLP = "";
//		CLGNode nextNode = this.getSuccessor().get(0);
//		ArrayList attributes_pre = new ArrayList();
//		ArrayList arg_pre = new ArrayList();
//
//
//		for(int i = 0; i < classAttributes.size(); i++) {		
//			attributes_pre.add(classAttributes.get(i)+"_0");
//		}
//		for(int j = 0; j < methodParameters.size(); j++) {		
//			arg_pre.add(methodParameters.get(j)+"_0");
//		}
//		
//		if (visted.contains(this.getId()) != true) {
//			visted.add(this.getId());
//			/*下一個還是CLGConstraintNode用逗號隔開，表示and*/
//			if(nextNode.getClass().equals(this.getClass())) {
//				CLP = CLP + "	"+this.toCLPInfo() + ", \n";
//			}
//			/*判斷是否要產生下一次呼叫*/
//			else if(nextNode.getClass().equals(CLGConnectionNode.class) && nextNode.getSuccessor().get(0).getClass().equals(CLGConstraintNode.class)) {
//				CLP = CLP + "	"+this.toCLPInfo() + ", \n";
//				CLP = CLP +"	" +className + "_" + methodName + "_node_" + ((CLGConnectionNode )nextNode).getConnectionId()+"("+ attributes_pre +","+ arg_pre +","+ classAttributes +","+ methodParameters +", Result). \n";
//			}
//			/*遇到EndNode表示predicate結束*/
//			else {
//				CLP = CLP + "	"+this.toCLPInfo() + ". \n";
//			}
//			CLP = CLP + nextNode.genMethodCLP(className, methodName, classAttributes, methodParameters);
//		}
//		return CLP;
//	}
}
