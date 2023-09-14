package ccu.pllab.tcgen.AbstractCLG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.UserDefinedType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.MethodCompleteCLG2CLP.StateNodeCLP;
import ccu.pllab.tcgen.MethodCompleteCLG2CLP.TransitionClp;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.exe.main.Main;
import tcgenplugin_2.handlers.ClassLevelHandler;

public class CLGStartNode extends CLGNode {

	private static final String flattenInputStringMapKey = "flattenInputStringMapKey";
	private static final String flattenOutputStringMapKey = "flattenOutputStringMapKey";
	private static final String checkDomainMapKey = "checkDomainMapKey";
	private String className;
	private String methodName;
	private VariableType retType;
	private boolean isConstructor;
	private LinkedHashMap<String, VariableToken> classAttributes;
	private ArrayList<String> methodParameters;
	private ArrayList<VariableType> methodParametertypes;// 我加

	public static String getFlattenInputStringMapKey() {
		return flattenInputStringMapKey;
	}
	
	public static String getFlattenOutputStringMapKey() {
		return flattenOutputStringMapKey;
	}

	public static String getCheckDomainMapKey() {
		return checkDomainMapKey;
	}

	public CLGStartNode() {
		super();
		this.retType = new VoidType();
		this.isConstructor = false;
		classAttributes = new LinkedHashMap<String, VariableToken>();
		methodParameters = new ArrayList<String>();
		methodParametertypes = new ArrayList<VariableType>();
	}

	public CLGStartNode(String className, String methodName) {
		super();
		this.retType = new VoidType();
		this.isConstructor = false;
		this.className = className;
		this.methodName = methodName;
		classAttributes = new LinkedHashMap<String, VariableToken>();
		methodParameters = new ArrayList<String>();
		methodParametertypes = new ArrayList<VariableType>();
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setRetType(VariableType retType) {
		this.retType = retType;
	}

	public void setClassAttributes(LinkedHashMap<String, VariableToken> attributeList) {
		this.classAttributes = attributeList;
	}

	public void setClassAttributes(ArrayList<VariableToken> attributeList) {

		for (VariableToken attr : attributeList) {
			this.classAttributes.put(attr.getVariableName(), attr);
		}

	}

	public void setMethodParameters(ArrayList<String> parameters) {
		for (String arg : parameters) {
			methodParameters.add(arg);
		}
	}

	public void setMethodParameterTypes(ArrayList<VariableType> parametertypes) {
		for (VariableType arg : parametertypes) {
			methodParametertypes.add(arg);
		}
	}

	public void setIsConstructor(boolean isConstructor) {
		this.isConstructor = isConstructor;
	}

	public String getClassName() {
		return this.className;
	}

	public String getFirstUpperClassName() {
		return this.className.toUpperCase().charAt(0) + this.className.substring(1);
	}

	public String getFirstUpperMethodName() {
		return this.methodName.toUpperCase().charAt(0) + this.methodName.substring(1);
	}

	public String getMethodName() {
		return this.methodName;
	}

	public String getGraphName() {
		return this.getFirstUpperClassName() + this.getFirstUpperMethodName();
	}

	public VariableType getRetType() {
		return this.retType;
	}

	public LinkedHashMap<String, VariableToken> getClassAttributes() {
		return this.classAttributes;
	}

	public ArrayList<String> getMethodParameters() {
		return this.methodParameters;
	}

	public ArrayList<VariableType> getMethodParameterTypes() {
		return this.methodParametertypes;
	}

	@Override
	public String toGetImgInfo() {
		String result = "";
		result += String.format("digraph \"%s_%s\" {\n", this.getClassName(), this.getMethodName());
		result += (this.getId() + " "
				+ "[style=filled, fillcolor=black, shape=\"circle\", label=\"\", fixedsize=true, width=.2, height=.2, xlabel=\"[0]\"]\n");
		return result;
	}

	@Override
	public String toCLPInfo(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet) {
		// TODO Auto-generated method stub
		return null;
	}

	// 好像沒用
//	public String stateAssignEqual() {
//		String content = "";
//		for (int i = 0; i < classAttributes.size(); i++) {
//			String new_preVar = classAttributes.get(i).toUpperCase().charAt(0) + classAttributes.get(i).substring(1);
//			// content += ",\n" + new_preVar + "#=" + new_preVar + "_0";
//			content += ",\n" + new_preVar + "#=" + new_preVar + "_pre";
//		}
//		return content;
//	}

	public String toString() {
		return "(CLGStartNode)";
	}

	public boolean isConstructor() {
		return this.isConstructor;
	}

	public VariableType getReturnType() {
		return this.retType;
	}

	@Override
	public ArrayList<String> getMethodAllLocalVar(HashMap<String, Integer> variableSet) {
		ArrayList<String> retList = new ArrayList<>();
		for (CLGNode n : this.getSuccessor()) {
			retList.addAll(n.getMethodAllLocalVar(variableSet));
		}
		HashSet<String> delRe = new HashSet<>();
		for (String var : retList)
			delRe.add(var);
		retList.clear();
		for (String var : delRe)
			retList.add(var);

		return retList;
	}

	public String CLG2MethodCLP(TypeTable typeTab) {
		String CLP = "";
		ClassLevelHandler.clp = new ArrayList<TransitionClp>();
		String clp2StringForClassLevel = ""; // 20200917 dai
		ArrayList<ArrayList<String>> clp = new ArrayList<>();
		ArrayList<ArrayList<String>> localParameters = new ArrayList<>();
		ArrayList<String> clpForClassLevel = new ArrayList(); // 20200917 dai

		SymbolTable symbolTab = typeTab.get(className, null).getSymbolTable();
		ArrayList<String> argNameList = new ArrayList<>();
		// get all arguments
		if (symbolTab.getMethod().containsKey(methodName)) {
			for (String argName : symbolTab.getMethod().get(methodName).getArgument().keySet()) {
				argNameList.add(Utility.titleToUppercase(argName));
			}
		}
		HashMap<String, String> flattenAndCheckDomainCLP = new HashMap<>();
		// get flatten and check domain clp
		flattenAndCheckDomainCLP = this.getFlattenAndCheckDomainCLP(symbolTab);

		HashMap<String, Integer> variableSet = new HashMap<>();//紀錄變數_count
		HashSet<String> defineVariableSet = new HashSet<>();//紀錄有被define的變數
		this.initVariableSet(variableSet, symbolTab, methodName);//_pre變數預設有define
		for(String varName: variableSet.keySet())
			defineVariableSet.add(varName);
		
		clp = this.genMethodCLP(className, methodName, localParameters, variableSet, defineVariableSet, symbolTab, argNameList,
				flattenAndCheckDomainCLP);
//		this.genMethodCLPForClass(className, methodName, localParameters, variableSetForClass, symbolTab, argNameList,
//				flattenCLP); // 20200926

		// Sort
		Collections.sort(clp, new Comparator<ArrayList<String>>() {
			public int compare(ArrayList a1, ArrayList a2) {
				String num1 = (String) a1.get(0);
				String num2 = (String) a2.get(0);
				return num1.compareTo(num2);
			}
		});

		// change type to String
		for (int i = 0; i < clp.size(); i++) {
			for (int j = 1; j < clp.get(i).size(); j++) {

				CLP += clp.get(i).get(j);
			}
		}

		// File
//		try {
//			File dir = new File("examples/" + this.getClassName() + "CLP");
//			dir.mkdir();
//
//			FileWriter dataFile = new FileWriter("examples/" + this.getClassName() + "CLP/" + this.getClassName()
//					+ this.getMethodName().substring(0, 1).toUpperCase() + this.getMethodName().substring(1) + ".ecl");
//			BufferedWriter input = new BufferedWriter(dataFile);
//			input.write(CLP);
//			input.close();
//
//			// 20200917 dai
//			/*
//			 * File dirForClassLevel = new File("examples/" + this.getClassName() +
//			 * "CLPForClassLevel"); dirForClassLevel.mkdir();
//			 * 
//			 * FileWriter dataFileForClassLevel = new FileWriter("examples/" +
//			 * this.getClassName() + "CLPForClassLevel/" + this.getClassName() +
//			 * this.getMethodName().substring(0, 1).toUpperCase() +
//			 * this.getMethodName().substring(1) + ".ecl"); BufferedWriter
//			 * inputForClassLevel = new BufferedWriter(dataFileForClassLevel);
//			 * inputForClassLevel.write(clp2StringForClassLevel);
//			 * inputForClassLevel.close();
//			 */
//		} catch (Exception e) {
//		}
		return CLP;
	}

	@Override
	public ArrayList genMethodCLPForClass(String className, String methodName, ArrayList localParameters,
			HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, SymbolTable sym, ArrayList<String> argNameList, String flattenCLP) {
		System.out.println(className + "_" + methodName);
		ArrayList attributes_pre = new ArrayList();
		ArrayList attributes_post = new ArrayList();
		ArrayList arg_pre = new ArrayList();
		ArrayList arg_post = new ArrayList();
		ArrayList return_value = new ArrayList(Arrays.asList("Result"));
		ArrayList newLocalParameters = new ArrayList();

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

		// 先將整個CLG圖走過一遍儲存local var
		localParameters = this.getMethodAllLocalVar(new HashMap<String, Integer>());
		// 清除走訪紀錄
		CLGConnectionNode.resetVisited();
		CLGConstraintNode.resetVisited();

		for (Object localVar : localParameters) {
			newLocalParameters.add(Utility.titleToUppercase((String) localVar));
		}

//			ClassLevelHandler.clp.add(new TransitionClp( className, methodName, new CLPState("","ObjPre","ArgPre", "ObjPost", "ArgPost", "Result", "Exception",""), new CLPState("_startNode","ObjPre","ArgPre", "ObjPost", "ArgPost", "Result", "Exception","")));
//			ClassLevelHandler.clp.add(new TransitionClp( className, methodName, new CLPState("_startNode",attributes_pre.toString(),arg_pre.toString(), attributes_post.toString(), arg_post.toString(), "[]", "[]","")));

		ClassLevelHandler.clp.add(new TransitionClp(classNameFix, methodNameFix,
				new StateNodeCLP("", new ArrayList<Object>(Arrays.asList("ObjPre")),
						new ArrayList<Object>(Arrays.asList("ArgPre")), new ArrayList<Object>(Arrays.asList("ObjPost")),
						new ArrayList<Object>(Arrays.asList("ArgPost")), new ArrayList<Object>(Arrays.asList("Result")),
						new ArrayList<Object>(Arrays.asList("Exception")), newLocalParameters, true),
				new StateNodeCLP("_startNode", new ArrayList<Object>(Arrays.asList("ObjPre")),
						new ArrayList<Object>(Arrays.asList("ArgPre")), new ArrayList<Object>(Arrays.asList("ObjPost")),
						new ArrayList<Object>(Arrays.asList("ArgPost")), new ArrayList<Object>(Arrays.asList("Result")),
						new ArrayList<Object>(Arrays.asList("Exception")), null, false)));
		ClassLevelHandler.clp.add(new TransitionClp(classNameFix, methodNameFix,
				new StateNodeCLP("_startNode", attributes_pre, arg_pre, attributes_post, arg_post,
						new ArrayList<Object>(), new ArrayList<Object>(), newLocalParameters, true)));
		ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1).addConstraint(flattenCLP);
//		// clp.add(fix());
		// ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() -
		// 1).addConstraint(fixForClass());

		if (this.getSuccessor().get(0).getClass().equals(CLGConnectionNode.class)) {
//			ClassLevelHandler.clp.get(ClassLevelHandler.clp.size()-1).setNextState(new CLPState("_node_" +((CLGConnectionNode)this.getSuccessor().get(0)).getConnectionId(),attributes_pre.toString(),arg_pre.toString(),attributes_post.toString(),arg_post.toString(),"[]","[]","[]"));
			ClassLevelHandler.clp.get(ClassLevelHandler.clp.size() - 1)
					.setNextState(new StateNodeCLP(
							"_node_" + ((CLGConnectionNode) this.getSuccessor().get(0)).getConnectionId(),
							attributes_pre, arg_pre, attributes_post, arg_post, new ArrayList<Object>(),
							new ArrayList<Object>(), newLocalParameters, false));
		}
		this.getSuccessor().get(0).genMethodCLPForClass(className, methodName, localParameters, variableSet, defineVariableSet, sym,
				argNameList, flattenCLP);
		return ClassLevelHandler.clp;
	}

	private HashMap<String, String> getFlattenAndCheckDomainCLP(SymbolTable symbolTab) {
		// 處理物件屬性、參數、變數扁平化
		String flattenInputStr = "";
		String flattenOutputStr = "";
		String checkDomainStr = "";

		boolean isPre;
		boolean needGanInvCLP = true;
		// obj
		VariableType nowTestClass = Main.typeTable.get(className, null);
		// obj pre
		if (!isConstructor) {
			isPre = true;
			flattenInputStr += nowTestClass.flattenObj("Self", isPre, needGanInvCLP, new HashSet<>(), new HashSet<>());
			checkDomainStr += nowTestClass.checkDomain("Self", isPre);
		}
		// obj post
		isPre = false;
		flattenOutputStr += nowTestClass.flattenObj("Self", isPre, needGanInvCLP, new HashSet<>(), new HashSet<>());
		checkDomainStr += nowTestClass.checkDomain("Self", isPre);
		// arg
		if (symbolTab.getMethod().containsKey(methodName)) {
			for (String argName : symbolTab.getMethod().get(methodName).getArgument().keySet()) {
				VariableToken arg = symbolTab.getMethod().get(methodName).getArgument().get(argName).clone();
				if (arg.getType() instanceof UserDefinedType) {
					// argPre
					isPre = true;
					flattenInputStr += arg.getType().flattenObj(arg.getVariableName(), isPre, needGanInvCLP,
							new HashSet<>(), new HashSet<>());
					checkDomainStr += arg.getType().checkDomain(arg.getVariableName(), isPre);
					// argPost
					isPre = false;
					flattenOutputStr += arg.getType().flattenObj(arg.getVariableName(), isPre, needGanInvCLP,
							new HashSet<>(), new HashSet<>());
					checkDomainStr += arg.getType().checkDomain(arg.getVariableName(), isPre);
				}
			}
		}
		
		
		if (flattenInputStr != "")
			flattenInputStr += "\r\n";
		if (flattenOutputStr != "")
			flattenOutputStr += "\r\n";
		if (checkDomainStr != "")
			checkDomainStr += "\r\n";
		HashMap<String, String> retMap = new HashMap<>();
		retMap.put(flattenInputStringMapKey, flattenInputStr);
		retMap.put(flattenOutputStringMapKey, flattenOutputStr);
		retMap.put(checkDomainMapKey, checkDomainStr);
		return retMap;
	}

	// 20210306 dai //暫時拿掉
	/*
	 * public String ocl2clpForClassLevel(TypeTable typeTable) { String CLP = "";
	 * ClassLevelHandler.clp = new ArrayList<TransitionClp>();
	 * ArrayList<ArrayList<String>> clp = new ArrayList();
	 * ArrayList<ArrayList<String>> localParameters = new ArrayList();
	 * this.genMethodCLPForClass("", "", classAttributes, methodParameters,
	 * localParameters,new HashMap<>(), null); // 20200926
	 * 
	 * // Sort // 20200917 dai String clp2StringForClassLevel = ""; for (int i = 0;
	 * i < ClassLevelHandler.clp.size(); i++) { // clp2StringForClassLevel =
	 * clp2StringForClassLevel + // ClassLevelHandler.clp.get(i).toString();
	 * 
	 * if (i == 0) { clp2StringForClassLevel = clp2StringForClassLevel +
	 * (ClassLevelHandler.clp.get(i).toString().replace("[", "")).replace("]", "");
	 * 
	 * } else if (i == ClassLevelHandler.clp.size() - 2) { clp2StringForClassLevel =
	 * clp2StringForClassLevel +
	 * ((ClassLevelHandler.clp.get(i).toString().replace("[Result]", "Result"))
	 * .replace("[Exception]", "Exception")).replace("[Result1]", "Result1"); } else
	 * if (i == ClassLevelHandler.clp.size() - 1) { clp2StringForClassLevel =
	 * clp2StringForClassLevel + ClassLevelHandler.clp.get(i).toString()
	 * .replace("[ObjPre] ,[ArgPre] ,[ObjPost] ,[ArgPost]",
	 * "ObjPre, ArgPre, ObjPost, ArgPost"); } else { clp2StringForClassLevel =
	 * clp2StringForClassLevel + ClassLevelHandler.clp.get(i).toString(); }
	 * 
	 * }
	 * 
	 * clp2StringForClassLevel = ":- lib(ic).\r\n:- lib(timeout).\r\n\n" +
	 * clp2StringForClassLevel;
	 * 
	 * // File try {
	 * 
	 * // 20200917 dai File dirForClassLevel = new File("examples/" +
	 * this.getClassName() + "CLPForClassLevel"); dirForClassLevel.mkdir();
	 * 
	 * FileWriter dataFileForClassLevel = new FileWriter("examples/" +
	 * this.getClassName() + "CLPForClassLevel/" + this.getClassName() +
	 * this.getMethodName().substring(0, 1).toUpperCase() +
	 * this.getMethodName().substring(1) + ".ecl"); BufferedWriter
	 * inputForClassLevel = new BufferedWriter(dataFileForClassLevel);
	 * inputForClassLevel.write(clp2StringForClassLevel);
	 * inputForClassLevel.close();
	 * 
	 * } catch (Exception e) {
	 * 
	 * } return clp2StringForClassLevel; }
	 */

//	@Override
//	public ArrayList genMethodCLP(String className, String methodName, ArrayList classAttributes,
//			ArrayList methodParameters, ArrayList localParameters, String result) {
//		System.out.println(className + "_" + methodName);
//		ArrayList attributes_pre = new ArrayList();
//		ArrayList attributes_post = new ArrayList();
//		ArrayList arg_pre = new ArrayList();
//		ArrayList arg_post = new ArrayList();
//		String return_value = "Result";
//		ArrayList<ArrayList<String>> clp = new ArrayList();
//
////		System.out.println("---------------"+this.className);
////		System.out.println("---------------"+this.methodName);
//		if (this.className != null)
//			className = this.className.substring(0, 1).toLowerCase() + this.className.substring(1);
//		if (this.methodName != null)
//			methodName = this.methodName.substring(0, 1).toUpperCase() + this.methodName.substring(1);
//
//		for (int i = 0; i < this.classAttributes.size(); i++) {
//			attributes_pre.add(this.classAttributes.get(i).substring(0, 1).toUpperCase()
//					+ this.classAttributes.get(i).substring(1) + "_pre");
//			attributes_post.add(this.classAttributes.get(i).substring(0, 1).toUpperCase()
//					+ this.classAttributes.get(i).substring(1));
//		}
//		for (int j = 0; j < this.methodParameters.size(); j++) {
//			arg_pre.add(this.methodParameters.get(j).substring(0, 1).toUpperCase()
//					+ this.methodParameters.get(j).substring(1) + "_pre");
//			arg_post.add(this.methodParameters.get(j).substring(0, 1).toUpperCase()
//					+ this.methodParameters.get(j).substring(1));
//		}
//
//		/* modify non primitive type return value */
//		if (this.getReturnType() == null || this.getReturnType().equals("") || this.getReturnType().equals("String")
//				|| this.getReturnType().equals("Boolean") || this.getReturnType().equals("Integer")) {
//			return_value = "Result";
//		} else if (this.getReturnType().equals(this.className)) {
//			ArrayList returnValue = new ArrayList();
//			for (int k = 0; k < this.classAttributes.size(); k++) {
//				returnValue.add("Result_" + this.classAttributes.get(k).substring(0, 1).toUpperCase()
//						+ this.classAttributes.get(k).substring(1));
//			}
//			return_value = returnValue.toString();
//		}
//
//		clp.add(new ArrayList());
//		clp.get(0).add("0");
//		clp.get(0).add(":- lib(ic).\r\n:- lib(timeout).\r\n\n");
//		clp.get(0).add(className + methodName + "(ObjPre, ArgPre, ObjPost, ArgPost, Result, Exception):- \n");
//		clp.get(0).add("	" + className + methodName
//				+ "_startNode(ObjPre, ArgPre, ObjPost, ArgPost, Result, Exception). \n");
//		clp.get(0).add(className + methodName + "_startNode(" + attributes_pre + "," + arg_pre + ","
//				+ attributes_post + "," + arg_post + ", " + return_value + ", Exception):- \n");
//		//modify four specify method name, not sure it's effect
//		//clp.get(0).add(fix());
//		if (this.getSuccessor().get(0).getClass().equals(CLGConnectionNode.class)) {
//			clp.get(0)
//					.add("	" + className + methodName + "_node_"
//							+ ((CLGConnectionNode) this.getSuccessor().get(0)).getConnectionId() + "(" + attributes_pre
//							+ "," + arg_pre + "," + attributes_post + "," + arg_post + ", " + return_value
//							+ ", Exception, " + localParameters + "). \n");
//		}
//		clp.addAll(this.getSuccessor().get(0).genMethodCLP(className, methodName, attributes_post, arg_post,
//				localParameters, return_value));
//
//		return clp;
//	}

	@Override
	public ArrayList<ArrayList<String>> genMethodCLP(String className, String methodName, ArrayList localParameters,
			HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, SymbolTable sym, ArrayList<String> argNameList,
			HashMap<String, String> flattenAndCheckDomainCLP) {
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

		String return_value = "Result";
		ArrayList newLocalParameters = new ArrayList();
		ArrayList<ArrayList<String>> clp = new ArrayList<>();

		// 先將整個CLG圖走過一遍儲存local var
		localParameters = this.getMethodAllLocalVar(new HashMap<String, Integer>());
		// 清除走訪紀錄
		CLGConnectionNode.resetVisited();
		CLGConstraintNode.resetVisited();

		for (Object localVar : localParameters) {
			newLocalParameters.add(Utility.titleToUppercase((String) localVar));
		}

		String classNameFix = Utility.titleToLowercase(className);
		String methodNameFix = Utility.titleToUppercase(methodName);

		clp.add(new ArrayList<>());
		clp.get(0).add("0");
		clp.get(0).add(":- lib(ic).\r\n:- lib(timeout).\r\n\n");
		clp.get(0).add(classNameFix + methodNameFix + "(ObjPre, ArgPre, ObjPost, ArgPost, Result, Exception):- \n");
		clp.get(0).add("\t" + classNameFix + methodNameFix
				+ "_startNode(ObjPre, ArgPre, ObjPost, ArgPost, Result, Exception). \n");
		clp.get(0).add(classNameFix + methodNameFix + "_startNode(" + attributes_pre + ", " + arg_pre + ", "
				+ attributes_post + ", " + arg_post + ", " + return_value + ", Exception):- \n");
		if (this.getSuccessor().get(0).getClass().equals(CLGConnectionNode.class)) {
			clp.get(0)
					.add("\t" + classNameFix + methodNameFix + "_node_"
							+ ((CLGConnectionNode) this.getSuccessor().get(0)).getConnectionId() + "(" + attributes_pre
							+ ", " + arg_pre + ", " + attributes_post + ", " + arg_post + ", " + return_value
							+ ", Exception, " + newLocalParameters + "). \n");
		} else
			clp.get(0).add(flattenAndCheckDomainCLP.get(flattenInputStringMapKey));
		clp.addAll(this.getSuccessor().get(0).genMethodCLP(className, methodName, localParameters, variableSet, defineVariableSet, sym,
				argNameList, flattenAndCheckDomainCLP));

		return clp;
	}

	public String fix() {
		String fix = "";
		switch (this.methodName) {
		case "push":
			fix = "	Size#=(Size_pre+1),\r\n" + "	length(Data,Size),\n";
			break;
		case "enqueue":
			fix = "	Size#=(Size_pre+1),\r\n" + "	length(Data,Size),\n";
			break;
		case "pop":
			fix = "	Size#=(Size_pre-1),\r\n" + "	length(Data,Size),\n";
			break;
		case "dequeue":
			fix = "	Size#=(Size_pre-1),\r\n" + "	length(Data,Size),\n";
			break;
		default:
			fix = "";
		}
		return fix;
	}

	public ArrayList<String> fixForClass() {
		ArrayList<String> fix = new ArrayList<String>();
		switch (this.methodName) {
		case "push":
			fix.add("Size#=(Size_pre+1)");
			fix.add("length(Data,Size)");
			break;
		case "enqueue":
			fix.add("Size#=(Size_pre+1)");
			fix.add("length(Data,Size)");
			break;
		case "pop":
			fix.add("Size#=(Size_pre-1)");
			fix.add("length(Data,Size)");
			break;
		case "dequeue":
			fix.add("Size#=(Size_pre-1)");
			fix.add("length(Data,Size)");
			break;
		default:
			fix = null;
		}
		return fix;
	}

	// add att,arg to variableSet
	protected void initVariableSet(HashMap<String, Integer> variableSet, SymbolTable sym, String methodName) {
		for (Entry<String, VariableToken> att : sym.getAttribute().entrySet()) {
			variableSet.put("Self_pre_" + att.getKey(), 1);
		}
		for (Entry<String, VariableToken> arg : sym.getMethod().get(methodName).getArgument().entrySet()) {
			variableSet.put(arg.getKey() + "_pre", 1);
		}
	}

	public String getOriginalConsName() {
		return "";
	}
}

//	@Override
//	public String genMethodCLP(String className, String methodName, ArrayList classAttributes, ArrayList methodParameters) {
//		String CLP = "";
//		ArrayList attributes_pre = new ArrayList();
//		ArrayList attributes_post = new ArrayList();
//		ArrayList arg_pre = new ArrayList();
//		ArrayList arg_post = new ArrayList();
//		if(this.className!= null)
//			className = this.className.toLowerCase();
//		if(this.methodName != null)
//			methodName = this.methodName.toLowerCase();
//
//		for(int i = 0; i < this.classAttributes.size(); i++) {		
//			attributes_pre.add(this.classAttributes.get(i).substring(0, 1).toUpperCase() + this.classAttributes.get(i).substring(1)+"_0");
//			attributes_post.add(this.classAttributes.get(i).substring(0, 1).toUpperCase() + this.classAttributes.get(i).substring(1)) ;
//		}
//		for(int j = 0; j < this.methodParameters.size(); j++) {		
//			arg_pre.add(this.methodParameters.get(j).substring(0, 1).toUpperCase() + this.methodParameters.get(j).substring(1)+"_0");
//			arg_post.add(this.methodParameters.get(j).substring(0, 1).toUpperCase() + this.methodParameters.get(j).substring(1)) ;
//		}
//		
//		CLP = CLP + className + "_" + methodName +"("+ attributes_pre +","+ arg_pre +","+ attributes_post +","+ arg_post +", Result):- \n";
//		if(this.getSuccessor().get(0).getClass().equals(CLGConnectionNode.class)) {
//			CLP = CLP + "	"+ className + "_" + methodName + "_node_" +((CLGConnectionNode)this.getSuccessor().get(0)).getConnectionId()+"("+attributes_pre +","+ arg_pre +","+ attributes_post +","+ arg_post+", Result). \n\n";
//		}
//		CLP = CLP + this.getSuccessor().get(0).genMethodCLP(className, methodName, attributes_post, arg_post);
//		return CLP;
//	}
//}
