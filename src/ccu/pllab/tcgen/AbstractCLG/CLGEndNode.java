package ccu.pllab.tcgen.AbstractCLG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.MethodCompleteCLG2CLP.StateNodeCLP;
import ccu.pllab.tcgen.MethodCompleteCLG2CLP.TransitionClp;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import tcgenplugin_2.handlers.ClassLevelHandler;

public class CLGEndNode extends CLGNode {
	private static ArrayList visted = new ArrayList();
	private static ArrayList vistedForClass = new ArrayList(); // 20200917 dai

	public CLGEndNode() {
		super();
	}

	public String toGetImgInfo() {
		String result = "";
		result += (this.getId() + " "
				+ ("[style=filled, fillcolor=black, shape=\"doublecircle\", label=\"\", fixedsize=true, width=.2, height=.2, xlabel=\"[-1]\"]"
						+ "\n"));
		return result;
	}

	@Override
	public String toCLPInfo(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet) {
		return ".";
	}

	public static void reset() {
		visted = new ArrayList();
		vistedForClass = new ArrayList();
	}

	@Override
	public ArrayList genMethodCLP(String className, String methodName, ArrayList localParameters,
			HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, SymbolTable sym, ArrayList<String> argNameList,
			HashMap<String, String> flattenAndCheckDomainCLP) {
		ArrayList<ArrayList<String>> clp = new ArrayList();
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

		String classNameFix = Utility.titleToLowercase(className);
		String methodNameFix = Utility.titleToUppercase(methodName);

		if (visted.contains(this.getId()) != true) {
			visted.add(this.getId());
			ArrayList a = new ArrayList();
			a.add("999_999");
			a.add(classNameFix + methodNameFix + "_endNode(" + attributes_pre + ", " + arg_pre + ", " + attributes_post
					+ ", " + arg_post + ", Result, Exception):-");
			a.add("\r\n" + flattenAndCheckDomainCLP.get(CLGStartNode.getFlattenInputStringMapKey()));
			a.add("\r\n" + flattenAndCheckDomainCLP.get(CLGStartNode.getFlattenOutputStringMapKey()));
			//無exeption或exception=[]
			a.add("\r\n( (var(Exception);Exception=[])->\n\t\tException=[]");
			//有exception
			String stateAssignStr = "";
			//參數state assign
			String tmpStr = objStateAssign(sym, defineVariableSet);
			if(!tmpStr.equals("") && !className.equals(methodName))
				stateAssignStr = "," + tmpStr;
			stateAssignStr += ";\r\n\t\ttrue)";
			tmpStr = argStateAssign(sym, methodName, defineVariableSet);
			if(tmpStr.equals(""))
				stateAssignStr += ".";
			else
				stateAssignStr += "," + tmpStr + ".";
			
			a.add(stateAssignStr + "\n");
			clp.add(a);
		}
		return clp;
	}

	private String objStateAssign(SymbolTable sym, HashSet<String> defineVariableSet) {
		String stateAssignStr = "";
		if(!defineVariableSet.contains("Self"))
			for(String att: sym.getAttribute().keySet())
				if(!defineVariableSet.contains("Self_" + att))
					stateAssignStr += "\r\n\t\tSelf_pre_" + att + "=Self_" + att + ",";
		return Utility.delEndRedundantSymbol(stateAssignStr, ",");
	}
	
	private String argStateAssign(SymbolTable sym, String methodName, HashSet<String> defineVariableSet) {
		String stateAssignStr = "";
		for(String arg: sym.getMethod().get(methodName).getArgument().keySet())
			if(!defineVariableSet.contains(arg))
				stateAssignStr += "\r\n\t" + Utility.titleToUppercase(arg) + "_pre=" + Utility.titleToUppercase(arg) + ",";
		return Utility.delEndRedundantSymbol(stateAssignStr, ",");
	}

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

//	@Override
//	public String genMethodCLP(String className, String methodName, ArrayList classAttributes, ArrayList methodParameters) {
//		String CLP = "";
//		
//		if (visted.contains(this.getId()) != true) {
//			visted.add(this.getId());
//		}
//		return CLP;
//	}

	public String toString() {
		return "(CLGEndNode)";
	}

	@Override
	public ArrayList<String> getMethodAllLocalVar(HashMap<String, Integer> variableSet) {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

}
