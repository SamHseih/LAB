package ccu.pllab.tcgen.pathCLPFinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Stack;

import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.SymbolTable.MethodToken;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.clgGraph2Path.CLGPath;
import ccu.pllab.tcgen.clgGraph2Path.CLGPathEnumerator;
import ccu.pllab.tcgen.exe.main.Main;

public class CLPTranslator {

	public static String SIPInsertStateBridgingFlag = "%%_CBTCG_SIP_stateBridging_flag**";// 標記刪除的節點CLP位置(for sip)
	private static final String resultKeyword = "Result";
	private static final String exceptionKeyword = "Exception";
	private static final String flattenInputStringMapKey = "flattenInputStringMapKey";
	private static final String flattenOutputStringMapKey = "flattenOutputStringMapKey";
	private static final String checkDomainMapKey = "checkDomainMapKey";
	private HashMap<String, Integer> variableSet;
	private LinkedHashSet<String> realTypeVar;
	private HashMap<String, Integer> tempSIPvariableSet;// for sip
	private HashMap<Integer, Stack<String>> nodeCLPContent = new HashMap<>();// 儲存每個節點對應的CLP(for sip)
	private int body_count = 1;
	// 扁平化後的CLP字串
	private String object_pre, object_post, result;
	private ArrayList<String> arg_pre, arg_post;
	private String flattenInputCLPStr;
	private String flattenOutputCLPStr;

	// 原本的
	private String importLibary;
	private String invCLP;
	private String headCLP;
	private String bodyCLP;
	private String wrapperCLP;
	private String endNodeCLP;
	// 預先去除不可直行路徑
	private String clpContent;
	private String temp = "";

	// array
	private String arraycontent = "";
	private int arraycount = 0;

	// type
	private SymbolTable symbolTable;
	private ArrayList<String> attribute = new ArrayList<String>();
	private UserDefinedType nowTestClass;// now testing class

	// ??
	private boolean twoD = false;

	// flag
	private boolean containException = false;
	private boolean containResult = false;

	// real type
	private boolean havaRealType = false;

	// private boolean shortestInfeai = false;
//	
//	public void setShortestInfeai(Boolean b) {
//		this.shortestInfeai = b;
//	}

	static public String getResultKeyword() {
		return resultKeyword;
	}

	static public String getExceptionKeyword() {
		return exceptionKeyword;
	}

	public static String getFlattenInputStringMapKey() {
		return flattenInputStringMapKey;
	}

	public static String getCheckDomainMapKey() {
		return checkDomainMapKey;
	}

	public HashMap<String, Integer> getVariableSet() {
		return this.variableSet;
	}

	public String getTempSIPCLP(CLGPath path, String clpContent, HashMap<Integer, Stack<String>> nodeClpContent,
			int mid, HashMap<String, Integer> variableSetAfterDel) {
		String tempCLP = "";
		// clone nodeCLPContent
		HashMap<Integer, Stack<String>> nodeClpContentClone = new HashMap<>();
		for (Integer i : nodeClpContent.keySet()) {
			Stack<String> tempSt = new Stack<>();
			for (String clpStr : nodeClpContent.get(i)) {
				tempSt.push(clpStr);
			}
			nodeClpContentClone.put(i, tempSt);
		}

		String delCLP = "";
		// 取出要刪除的CLP
		for (int i = path.getPathNodes().size() - 2; i >= mid; i--) {
			CLGNode cons = path.getPathNodes().get(i);
			if (cons instanceof CLGConstraintNode
					&& nodeClpContentClone.containsKey(((CLGConstraintNode) cons).getXLabelId())) {
				delCLP = nodeClpContentClone.get(((CLGConstraintNode) cons).getXLabelId()).pop() + delCLP;
				((CLGConstraintNode) cons).getConstraint().reverseVarFlag(variableSetAfterDel);
			}
		}
		if (delCLP != "")
			clpContent = clpContent.replace(delCLP, "\r\n" + SIPInsertStateBridgingFlag);

		nodeClpContentClone = null;
		tempCLP = clpContent;
		this.clpContent = tempCLP;

		return tempCLP;
	}

	// 待改
	public String getTempCCSCLP(CLGPath path, int i, HashMap<CLGConstraintNode, String> nodeClpContent) {
		String tempCLP = "";
//		String temp = "";
		String tempCLPContent = "";
		tempCLPContent = this.clpContent;

		CLGNode n = path.getPathNodes().get(i);
		if (n instanceof CLGConstraintNode && nodeClpContent.containsKey(n)) {
			this.temp = nodeClpContent.get(n) + ",";
		} else {
			this.temp = "";
		}

		tempCLPContent = tempCLPContent.replace(this.temp, "");

		tempCLP = tempCLPContent;

		return tempCLP;
	}

//	20200808 CCS在去除後確認無解的節點要進行刪除
	public void setTempCLPByCCS() {
		this.clpContent = this.clpContent.replace(this.temp, "");
		this.temp = "";
	}

	// ------------------------------------------

	public String getImportLibary() {
		return this.importLibary;
	}

	public String getHeadCLP() {
		return this.headCLP;
	}

	public String getBodyCLP() {
		return this.bodyCLP;
	}

	public String getWrapperCLP() {
		return this.wrapperCLP;
	}

	public String getEndNodeCLP() {
		return this.endNodeCLP;
	}

	public void setInvCLP(String invCLP) {
		this.invCLP = invCLP;
	}

	public HashMap<Integer, Stack<String>> getNodeCLPContent() {
		return nodeCLPContent;
	}

	// -----------------------------------------------

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public void setAttribute(ArrayList<String> attribute) {
		this.attribute = attribute;
	}

	private final String randomArray = "integerGen(N) :-\n" + "  random(R),\n" + "  mod(R, 32767, N).\n"
			+ "intSequenceInstances(0, []).\n" + "intSequenceInstances(Size, [N|Seq]) :-\n" + "  Size > 0,\n"
			+ "  Size1 is Size -1,\n" + "  intSequenceInstances(Size1, Seq),\n" + "  integerGen(N).\n";

	private final String permutation = "del(A,[A|B],B).\n" + "del(A,[B|C],[B|D]):-\n" + "del(A,C,D).\n" +

			"list_permutation([],[]).\n" + "list_permutation(A,[B|C]):-\n" + "del(B,A,D),\n"
			+ "list_permutation(D,C).\n";
	private final String randset = "randset(0, L,[]).\r\n" + "randset(Size,L,[N|Seq]) :-\r\n" + " Size > 0,\r\n"
			+ "  Size1 is Size -1,\r\n" + "  randset(Size1,L,Seq),\r\n" + "  random(R),\r\n" + "  mod(R, L, N).\n";
	private final String random_member = "random_member(D, A,E) :-\r\n" + "length(A, B),\r\n" + "random(N),\r\n"
			+ "mod(N, B, C),\r\n" + "C1#=C+1,\r\n" + "findelement(A,D,C1),\r\n" + "remove(D,A,E).\r\n" + "  \r\n"
			+ "remove(H, [H], []).\r\n" + "remove(X, [H], [H]).\r\n" + "remove(X, [X|T],T ).\r\n"
			+ "remove(X, [H|T], [H|S]):-\r\n" + "remove(X,T,S).\n";

	private final String findnsols = "findnsols(N, Term, Goal, Solutions) :-\r\n" + "    ( N < 1 ->\r\n"
			+ "        Solutions = []\r\n" + "    ;\r\n" + "        record_create(Bag),\r\n" + "		N1#=N+1,\r\n"
			+ "        shelf_create(count(N1), Counter),\r\n" + "        ( \r\n" + "            once((\r\n"
			+ "                call(Goal),\r\n" + "                recordz(Bag, Term),\r\n"
			+ "                \\+shelf_dec(Counter, 1)   % succeed if enough\r\n" + "            )),\r\n"
			+ "            fail\r\n" + "        ;\r\n" + "            recorded_list(Bag, Solutions)\r\n"
			+ "        )\r\n" + "    ).\n";

	private final String findindex = "findIndex([A|B],Value,I,C):-\r\n"
			+ "I#=C,A#=Value;C1#=C+1,findIndex(B,Value,I,C1).\r\n" + "findIndex([],Value,-1).";
	private final String sortedArray = "intSequenceInstances2(Size, Array) :-\r\n"
			+ "intSequenceInstances(Size, Seq),\r\n" + "msort(Seq,Array).\n";

	private final String declareArray2 = "delay dcl_1dInt_array2(_, Size) if nonground([Size]).\ndcl_1dInt_array2(Array,Size) :-\r\n"
			+ "dcl_1dInt_array(Seq, Size),\r\n" + "msort(Seq,Array).\n";
	private final String intarray = "intColArray(Col,Array):-\r\n" + "Col#>1,\r\n" + "random(R),\r\n"
			+ "mod(R, 32767, N),\r\n" + "arg(Col,Array,N),\r\n" + "Col2#=Col-1,\r\n" + "intColArray(Col2,Array).\r\n"
			+ "\r\n" + "intColArray(1,Array):-\r\n" + "random(R),\r\n" + "mod(R, 32767, N),\r\n" + "arg(1,Array,N).\r\n"
			+ "\r\n" + "intArrayInstances(Row,Col,Array):-\r\n" + "Row#>1,\r\n" + "arg(Row,Array,Array2),\r\n"
			+ "intColArray(Col,Array2),\r\n" + "Row2#=Row-1,\r\n" + "intArrayInstances(Row2,Col,Array).\r\n" + "\r\n"
			+ "intArrayInstances(1,Col,Array):-\r\n" + "arg(1,Array,Array2),\r\n" + "intColArray(Col,Array2).\n";

	private final String randomIndex = "randomIndexArray(N, Array) :-\r\n"
			+ "    randomIndexArray(N, N, [], Array).\r\n" + "\r\n" + "randomIndexArray(_, 0, Array, Array) :-\r\n"
			+ "    labeling_1dInt_array(Array).\r\n" + "randomIndexArray(N, I, Curr, Array) :-\r\n" + "    I > 0,\r\n"
			+ "    I1 is I - 1,\r\n" + "    X :: 1..N,\r\n" + "    notEqualAll(X, Curr),\r\n"
			+ "    randomIndexArray(N, I1, [X|Curr], Array).\r\n" + "\r\n" + "notEqualAll(_, []).\r\n"
			+ "notEqualAll(X, [Y|L]) :-\r\n" + "    X #\\= Y,\r\n" + "    notEqualAll(X, L).\n";

	public CLPTranslator() {
		this.initial();

	}

	public void initial() {
		variableSet = new HashMap<String, Integer>();
		this.realTypeVar = new LinkedHashSet<String>();
		body_count = 1;
	}

	public int getPathNumber() {
		return this.body_count - 1;
	}

	public void setArrayContent(String content) {
		this.arraycontent = content;
	}

	public String getArrayContent() {
		return this.arraycontent;
	}

	public void setArrayCount(int count) {
		this.arraycount = count;
	}

	public int getArrayCount() {
		return this.arraycount;
	}

	public int getBodycount() {
		return body_count;
	}

	public void bodyCountFix_SIP() {
		this.body_count--;
	}

	/*
	 * 格式對齊: 當有新的文字內容str要加入時，會改成 "\n" + str + ","
	 * 在bodyCLP跟wrapperCLP結束時，會檢查若最後的符號為"," 或 ":-"(predicate無內容時的情況)則改為"."
	 */
	public String genPathCLP(CLGPath pathInput, String className, String methodName, Boolean isConstructor) {

		System.out.println(
				Utility.titleToLowercase(className) + Utility.titleToUppercase(methodName) + "_" + this.body_count);
		CLGPath path = null;
		try {
			path = pathInput.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Main.issort = false;
		this.object_pre = "";
		this.arg_pre = new ArrayList<String>();
		this.object_post = "";
		this.arg_post = new ArrayList<String>();
		this.result = "";
		this.containException = false;
		this.containResult = false;

		this.bodyCLP = "";
		this.flattenInputCLPStr = "";
		this.flattenOutputCLPStr = "";
		// clone type because array type need to fix size name when flattenObj
		this.nowTestClass = (UserDefinedType) Main.typeTable.get(className, null);

		ArrayList<VariableToken> classAttributes = new ArrayList<VariableToken>();
		ArrayList<VariableToken> methodParameters = new ArrayList<VariableToken>();
		VariableType returnType = new VoidType();
		for (String attName : this.symbolTable.getAttribute().keySet()) {
			classAttributes.add(this.symbolTable.getAttribute().get(attName));
		}
		for (String symMethodName : this.symbolTable.getMethod().keySet()) {
			MethodToken method = this.symbolTable.getMethod().get(symMethodName);
			if (symMethodName.equals(methodName)) {
				returnType = method.getReturnType();
				for (String argName : method.getArgument().keySet()) {
					methodParameters.add(method.getArgument().get(argName));
				}
			}
		}

		String completeCLP = "";
		completeCLP += this.importLibraryCLP();
		this.importLibary = this.importLibraryCLP();

		// 開改
		this.bodyCLP += this.genBodyName(this.body_count, path, className, methodName, isConstructor);// List<CLGNode>

		// 回傳扁平化的相關CLP與值域檢查
		// 回傳扁平化的相關CLP與值域檢查
		HashMap<String, String> tmpMap = this.genTestingVarName(isConstructor, classAttributes, methodParameters,
				returnType, 0);
		this.flattenInputCLPStr = tmpMap.get(flattenInputStringMapKey);
		this.flattenOutputCLPStr = tmpMap.get(flattenOutputStringMapKey);
		String checkDomainStr = tmpMap.get(checkDomainMapKey);

		if (flattenInputCLPStr != "")
			this.flattenInputCLPStr = "\n% Input object flattening start" + this.flattenInputCLPStr + "\n% Input object flattening end";
		
		if (flattenOutputCLPStr != "")
			this.flattenOutputCLPStr = "\n% output object flattening start" + this.flattenOutputCLPStr + "\n% output object flattening end";
		
		bodyCLP = this.flattenInputCLPStr + bodyCLP + flattenOutputCLPStr  + checkDomainStr
				+ this.stateAssignEquals(methodName, isConstructor, containException);

//		紀錄這個CLP有exception或result則head產生隨之修改
		// (因以前有特殊處理此部分故跟著做)
		if (bodyCLP.contains("Exception="))
			containException = true;
		if (!(returnType instanceof VoidType))
			containResult = true;

		String head = this.getHeadInfo(className, methodName, isConstructor);

		bodyCLP = Utility.delEndRedundantSymbol(bodyCLP, "\n");
		bodyCLP = Utility.delEndRedundantSymbol(bodyCLP, "\r");
		bodyCLP = Utility.delEndRedundantSymbol(bodyCLP, ",");
		bodyCLP = Utility.delEndRedundantSymbol(bodyCLP, ":-");
		bodyCLP += ".";

		this.headCLP = head;
		completeCLP += head;
		completeCLP += this.bodyCLP;

//		genWrapperCLP() 產生底部clp
		this.havaRealType = this.realTypeVar.size() > 0;
		completeCLP += this.genWrapperCLP(body_count, className, methodName, classAttributes, methodParameters,
				returnType, isConstructor);

		// completeCLP += this.invCLP();

		if (completeCLP.contains("findnsols"))
			completeCLP += findnsols;
		if (completeCLP.contains("findIndex"))
			completeCLP += findindex;
		if (completeCLP.contains("list_permutation"))
			completeCLP += permutation;
		if (completeCLP.contains("random_member"))
			completeCLP += random_member;
		if (completeCLP.contains("randomIndexArray"))
			completeCLP += randomIndex;
		this.body_count++;
		completeCLP = completeCLP.replaceAll("=-1", "=(-1)");

		completeCLP = Utility.delEndRedundantSymbol(completeCLP, "\n");
		completeCLP = Utility.delEndRedundantSymbol(completeCLP, ",");
		completeCLP = Utility.delEndRedundantSymbol(completeCLP, ":-");
		completeCLP += ".";
		return completeCLP;
	}

	public String getHeadInfo(String className, String methodName, boolean isConstructor) {
		String completeCLP = "\n";
		completeCLP += Utility.titleToLowercase(className);
		completeCLP += Utility.titleToUppercase(methodName);
		completeCLP += "_" + this.body_count;
		completeCLP += "(";
		completeCLP += this.object_pre + ",";
		completeCLP += this.arg_pre + ",";
		if (!isConstructor || !containException) {
			completeCLP += this.object_post + ",";
		} else
			completeCLP += "[],";

		completeCLP += this.arg_post + ",";
		completeCLP += this.containResult ? "[Result]" + "," : "[],";
		completeCLP += this.containException ? "[Exception]," : "[],";
		completeCLP = completeCLP.substring(0, completeCLP.length() - 1);
		completeCLP += "):-";

		return completeCLP;
	}

	public String addHeadInfo(ArrayList<VariableToken> list) {
		String completeCLP = "[";
		if (list.size() > 0) {
			for (VariableToken v : list) {
				String index = v.getVariableName();
				completeCLP += index + ",";
			}
			completeCLP = completeCLP.substring(0, completeCLP.length() - 1);
		}

		return completeCLP;
	}

	// *********
	private String importLibraryCLP() {
		String importLibrary = "";
		importLibrary += ":- lib(ic).";
		importLibrary += "\n:- lib(timeout).\n";
		return importLibrary;
	}

	private String genWrapperCLP(int pathNumber, String className, String methodName,
			ArrayList<VariableToken> classAttributes, ArrayList<VariableToken> methodParameters,
			VariableType returnType, Boolean isConstructor) {

		String wrapperCLP = "";
		wrapperCLP += this.genWrapperName(className, methodName);

		boolean needTransformData = false;
		if (!this.havaRealType) {
			needTransformData = true;
		}

		this.genTestingVarName(isConstructor, classAttributes, methodParameters, returnType, 1);
		wrapperCLP += this.genStateAligned(isConstructor, true, 1);
		wrapperCLP += Utility.genArrayDimVar(Main.typeTable.get(className, null).getSymbolTable(), isConstructor,
				containException, methodName, 1);
		wrapperCLP += this.genBodyCall(pathNumber, className, methodName, 1, true);
		wrapperCLP += this.analysisLabelingCLP(className, methodName, methodParameters, isConstructor, containException,
				needTransformData, 1);
		if (this.havaRealType) {
			wrapperCLP += this.realTypeVarGetMediam(this.realTypeVar, "part");
			this.genTestingVarName(isConstructor, classAttributes, methodParameters, returnType, 2);
			wrapperCLP += this.genStateAligned(isConstructor, true, 2);
			wrapperCLP += this.genBodyCall(pathNumber, className, methodName, 2, true);
			wrapperCLP += this.analysisLabelingCLP(className, methodName, methodParameters, isConstructor,
					containException, needTransformData, 2);

			wrapperCLP += this.realTypeVarGetMediam(this.realTypeVar, "all");
			this.genTestingVarName(isConstructor, classAttributes, methodParameters, returnType, 3);
			wrapperCLP += this.genStateAligned(isConstructor, true, 3);
			wrapperCLP += this.genBodyCall(pathNumber, className, methodName, 3, true);
			needTransformData = true;
			wrapperCLP += this.analysisLabelingCLP(className, methodName, methodParameters, isConstructor,
					containException, true, 3);
		}

		this.genTestingVarName(isConstructor, classAttributes, methodParameters, returnType, 0);
		wrapperCLP += this.genStateAligned(isConstructor, false, 0);
//		/********/
////		沒用好像
//		wrapperCLP += this.genInvariant();
//		/********/

//		String labelword = this.analysisLabelingCLP(className, methodName, methodParameters, isConstructor,
//				containException, true, 1);
//		String labelword = this.analysisLabelingCLP(className, methodName, methodParameters, isConstructor, "_Temp",
//				true);
//
//		wrapperCLP += labelword;/********/

		this.wrapperCLP = wrapperCLP;
		return wrapperCLP;
	}

	private String realTypeVarGetMediam(HashSet<String> realTypeVar, String Mode) {
		LinkedHashSet<String> newState = new LinkedHashSet<String>();
		LinkedHashMap<String, LinkedHashSet<String>> realTypeArgState = new LinkedHashMap<String, LinkedHashSet<String>>();

		realTypeVar.forEach(var -> {
			String[] strArr = var.split("_");
			if (strArr.length > 3) {
				if (!strArr[0].equals("Self")) {
					if (!realTypeArgState.containsKey(strArr[0] + "_" + strArr[1] + "_" + strArr[2])) {
						LinkedHashSet<String> old = new LinkedHashSet<String>();
						old.add(strArr[0] + "_" + strArr[1] + "_" + strArr[2] + "_" + strArr[3]);
						realTypeArgState.put(strArr[0] + "_" + strArr[1] + "_" + strArr[2], old);
						LinkedHashSet<String> next = new LinkedHashSet<String>();
						next.add(
								strArr[0] + "_" + (Integer.valueOf(strArr[1]) + 1) + "_" + strArr[2] + "_" + strArr[3]);
						realTypeArgState.put(strArr[0] + "_" + (Integer.valueOf(strArr[1]) + 1) + "_" + strArr[2],
								next);
					} else {
						realTypeArgState.get(strArr[0] + "_" + strArr[1] + "_" + strArr[2])
								.add(strArr[0] + "_" + strArr[1] + "_" + strArr[2] + "_" + strArr[3]);
						realTypeArgState.get(strArr[0] + "_" + (Integer.valueOf(strArr[1]) + 1) + "_" + strArr[2]).add(
								strArr[0] + "_" + (Integer.valueOf(strArr[1]) + 1) + "_" + strArr[2] + "_" + strArr[3]);
					}

				}
			}

			strArr[1] = "" + (Integer.valueOf(strArr[1]) + 1);
			newState.add(strArr[0] + "_" + strArr[1] + "_" + strArr[2] + ((strArr.length > 3) ? "_" + strArr[3] : ""));

		});

		String retStr = "";

		for (Entry<String, LinkedHashSet<String>> arg : realTypeArgState.entrySet()) {
			retStr += "\n" + arg.getKey() + " = " + arg.getValue().toString() + ",";
		}
		retStr += "\nrealTypeVargetMediam(" + realTypeVar.toString() + ", \"" + Mode + "\"," + newState.toString()
				+ "),";

		return retStr;
	}

	private String genWrapperName(String className, String methodName) {
		String clpPredicateName = "\ntest";
		String new_className = className;
		String new_methodName = methodName;
		new_className = new_className.toUpperCase().charAt(0) + new_className.substring(1);
		new_methodName = new_methodName.toUpperCase().charAt(0) + new_methodName.substring(1);
		clpPredicateName += new_className;
		clpPredicateName += new_methodName;
		clpPredicateName += "(Obj_pre,Arg_pre,Obj,Arg,Result,Exception):-\n";
		return clpPredicateName;
	}

	private String genStateAligned(Boolean isConstructor, boolean isNotTransform, int solveTime) {
		String alignedPredicate = "";
		boolean isPre = false;
		boolean needGenInvCLP = false;

		String addMethodItemString = (isNotTransform) ? "_" + solveTime : "";
		String addSelfItemString = (isNotTransform) ? "" : "New";
		ArrayList<String> transformArgPre = new ArrayList<String>();
		this.arg_pre.forEach(arg -> {
			transformArgPre.add("New" + arg);
		});
		ArrayList<String> transformArg = new ArrayList<String>();
		this.arg_post.forEach(arg -> {
			transformArg.add("New" + arg);
		});

		if (this.object_pre.length() > 0) {
			isPre = true;
			alignedPredicate += "\nObj_pre" + addMethodItemString + "="
					+ (this.object_pre.equals("[]") ? this.object_pre : addSelfItemString + this.object_pre) + ",";
			if (!isConstructor)
				alignedPredicate += nowTestClass.flattenObj(addSelfItemString + "Self" + addMethodItemString, isPre,
						needGenInvCLP, new HashSet<>(), new HashSet<String>());
		}
		if (this.arg_pre.size() > 0) {
			alignedPredicate += "\nArg_pre" + addMethodItemString + "=" + addSelfItemString + "InputArg"
					+ addMethodItemString + "_pre" + ",";
			alignedPredicate += "\n" + addSelfItemString + "InputArg" + addMethodItemString + "_pre ="
					+ ((isNotTransform) ? this.arg_pre.toString() : transformArgPre.toString()) + ",";
		} else {
			alignedPredicate += "\nArg_pre" + addMethodItemString + "= [],";
		}

		if (this.object_post.length() > 0) {
			isPre = false;
			alignedPredicate += "\nObj" + addMethodItemString + "="
					+ (this.object_post.equals("[]") ? this.object_post : addSelfItemString + this.object_post) + ",";
			alignedPredicate += nowTestClass.flattenObj(addSelfItemString + "Self" + addMethodItemString, isPre,
					needGenInvCLP, new HashSet<>(), new HashSet<String>());
		}
		if (this.arg_post.size() > 0) {
			alignedPredicate += "\nArg" + addMethodItemString + "=" + addSelfItemString + "InputArg"
					+ addMethodItemString + ",";
			alignedPredicate += "\n" + addSelfItemString + "InputArg" + addMethodItemString + " ="
					+ ((isNotTransform) ? this.arg_post.toString() : transformArg.toString()) + ",";
		} else {
			alignedPredicate += "\nArg" + addMethodItemString + "= [],";
		}

		if (this.result.length() > 0) {
			alignedPredicate += "\nResult" + addMethodItemString + "="
					+ (this.result.equals("[]")
							? "OutputResult" + addMethodItemString + ",\nOutputResult" + addMethodItemString + " = [],"
							: (addSelfItemString + "OutputResult" + addMethodItemString + "," + "\n" + addSelfItemString
									+ "OutputResult" + addMethodItemString + " =" + "[" + addSelfItemString
									+ this.result.substring(1, this.result.length() - 1) + "]" + ","));
		}
		return alignedPredicate;
	}

	private String genInvariant() {
		String invariant = "";
		return invariant;
	}

	private String genBodyCall(int num, String new_className, String new_methodName, int solveTime,
			boolean needpathnum) {
		String bodyCall = "";

		new_className = new_className.toLowerCase().charAt(0) + new_className.substring(1);
		new_methodName = new_methodName.toUpperCase().charAt(0) + new_methodName.substring(1);

		bodyCall += "\n" + new_className + new_methodName + (needpathnum ? "_" + num : "") + "(Obj_pre_" + solveTime
				+ ",Arg_pre_" + solveTime + ",Obj_" + solveTime + ",Arg_" + solveTime + ",Result_" + solveTime
				+ ",Exception" + (needpathnum ? "" : "_" + num) + "),";

		return bodyCall;
	}

	private String analysisLabelingCLP(String className, String methodName, ArrayList<VariableToken> methodParameters,
			boolean isConstructor, boolean containException, boolean needTransformData, int solveTime) {
		String labelingPredicate = "";
		String transformData = "";
		String itemName = (solveTime == 0) ? "" : "_" + solveTime;
		// pre先labeling再做post
		// array先labeling確定定義好array邊界限制
		// 非array同型態會放在一起labeling
		LinkedHashMap<String, ArrayList<String>> labelingPreMap = new LinkedHashMap<>();
		LinkedHashMap<String, ArrayList<String>> labelingMap = new LinkedHashMap<>();
		// array型態紀錄array名與array型態(size依物件名修改)
		LinkedHashMap<String, ArrayType> labelingArrayPreMap = new LinkedHashMap<>();// array name, array type
		LinkedHashMap<String, ArrayType> labelingArrayMap = new LinkedHashMap<>();

		// obj labeling
		VariableType selfType = Main.typeTable.get(className, null);
		if (selfType instanceof ArrayType) {
			if (!isConstructor)
				labelingArrayPreMap.put("Self" + itemName + "_pre", (ArrayType) selfType);

			labelingArrayMap.put("Self" + itemName, (ArrayType) selfType);
		} else {
			if (!isConstructor) {
				ArrayList<String> selfPreList = new ArrayList<>();
				selfPreList.add("Self" + itemName + "_pre");
				labelingPreMap.put(className, selfPreList);
			}
			ArrayList<String> selfList = new ArrayList<>();
			selfList.add("Self" + itemName);
			labelingMap.put(className, selfList);
		}
		// arg labeling
		LinkedHashMap<String, VariableToken> argMap = nowTestClass.getSymbolTable().getMethod().get(methodName)
				.getArgument();
		for (String argName : argMap.keySet()) {
			VariableToken arg = argMap.get(argName);
			if (arg.getType() instanceof ArrayType) {
				labelingArrayPreMap.put(argName + itemName + "_pre", (ArrayType) arg.getType());
				labelingArrayMap.put(argName + itemName, (ArrayType) arg.getType());
			} else {// not array type
				if (!labelingPreMap.containsKey(arg.getType().getType()))
					labelingPreMap.put(arg.getType().getType(), new ArrayList<>());
				if (!labelingMap.containsKey(arg.getType().getType()))
					labelingMap.put(arg.getType().getType(), new ArrayList<>());
				// arg_pre && arg
				labelingPreMap.get(arg.getType().getType()).add(argName + itemName + "_pre");
				labelingMap.get(arg.getType().getType()).add(argName + itemName);
			}
		}

		// result labeling
		VariableType relType = nowTestClass.getSymbolTable().getMethod().get(methodName).getReturnType();

		// labeling array pre
		for (String arrName : labelingArrayPreMap.keySet()) {
			ArrayList<String> tempList = new ArrayList<>();
			tempList.add(arrName);
			labelingPredicate += labelingArrayPreMap.get(arrName).getLabelingCLPMethodName(tempList);
			transformData += labelingArrayPreMap.get(arrName).getTransformCLPMethodName(tempList);
		}
		// labeling pre var
		for (String preType : labelingPreMap.keySet()) {
			labelingPredicate += Main.typeTable.get(preType, null)
					.getLabelingCLPMethodName(labelingPreMap.get(preType));
			transformData += Main.typeTable.get(preType, null).getTransformCLPMethodName(labelingPreMap.get(preType));
		}

		// labeling array
		for (String arrName : labelingArrayMap.keySet()) {
			ArrayList<String> tempList = new ArrayList<>();
			tempList.add(arrName);
			labelingPredicate += labelingArrayMap.get(arrName).getLabelingCLPMethodName(tempList);
			transformData += labelingArrayMap.get(arrName).getTransformCLPMethodName(tempList);
		}
		// labeling var
		for (String postType : labelingMap.keySet()) {
			labelingPredicate += Main.typeTable.get(postType, null).getLabelingCLPMethodName(labelingMap.get(postType));
			transformData += Main.typeTable.get(postType, null).getTransformCLPMethodName(labelingMap.get(postType));
		}
		if (!(relType instanceof VoidType)) {
			ArrayList<String> relList = new ArrayList<String>();
			relList.add("Rel" + itemName);
			labelingPredicate += relType.getLabelingCLPMethodName(relList);
			transformData += relType.getTransformCLPMethodName(relList);
		}

		labelingPredicate = (labelingPredicate.endsWith(","))
				? labelingPredicate.substring(0, labelingPredicate.length() - 1)
				: labelingPredicate;
		transformData = (transformData.endsWith(",")) ? transformData.substring(0, transformData.length())
				: transformData;

		return labelingPredicate + "," + ((needTransformData) ? transformData : "");
	}

	private String genBodyName(int num, CLGPath path, String className, String methodName, Boolean isConstructor) {

		String bodyCLP = "";

		this.variableSet.clear();
		for (Entry<String, VariableToken> att : this.symbolTable.getAttribute().entrySet())
			variableSet.put("Self_pre_" + att.getKey(), 1);
		for (Entry<String, VariableToken> arg : this.symbolTable.getMethod().get(methodName).getArgument().entrySet())
			variableSet.put(arg.getKey() + "_pre", 1);

		HashMap<String, Boolean> containKey = new HashMap<>();// judge it is result/exception or not by constraint node
		containKey.put(resultKeyword, false);
		containKey.put(exceptionKeyword, false);

//		將每一個constraint 節點中constraint一一取出寫入到bodyCLP
		boolean isMethod = false;
		for (CLGConstraintNode c : path.getConsNode()) {
			c.getConstraint().renameVar(variableSet, new HashSet<String>(), isMethod);
			CLPInfo constraintCLPInfo = c.getConstraint().getCLPInfo(variableSet, containKey);
			String tempNodeCLP = "";

			// 使用函式的結果
			if (!constraintCLPInfo.getReturnCLP().equals("")) {
				bodyCLP += "\n" + constraintCLPInfo.getReturnCLP() + ",";
				tempNodeCLP += "\n" + constraintCLPInfo.getReturnCLP() + ",";
			}

			// 產生函式CLP
			if (constraintCLPInfo.getMethodCallCLP().size() > 0)
				for (int i = 0; i < constraintCLPInfo.getMethodCallCLP().size(); i++) {
					bodyCLP += "\n" + constraintCLPInfo.getMethodCallCLP().get(i) + ",";
					tempNodeCLP += "\n" + constraintCLPInfo.getMethodCallCLP().get(i) + ",";
				}

			// for sip 刪除用，儲存限制節點對應的CLP
			if (nodeCLPContent.containsKey(c.getXLabelId()))
				nodeCLPContent.get(c.getXLabelId()).push(tempNodeCLP);
			else {
				Stack<String> tempSt = new Stack<>();
				tempSt.push(tempNodeCLP);
				nodeCLPContent.put(c.getXLabelId(), tempSt);
			}
		}

		this.containResult = containKey.get(resultKeyword);
		this.containException = containKey.get(exceptionKeyword);

		return bodyCLP;
	}

	private HashMap<String, String> genTestingVarName(Boolean isConstructor, ArrayList<VariableToken> classAttributes,
			ArrayList<VariableToken> methodParameters, VariableType returnType, int solveTime) {
		String flattenInputStr = "";// 原物件=扁平化後的變數
		String flattenOutputStr = "";// 原物件=扁平化後的變數
		String checkDomainStr = ""; // 最後變數值域確認
		ArrayList<String> argPreName = new ArrayList<>();
		ArrayList<String> argPostName = new ArrayList<>();
		this.realTypeVar.clear();
		String item = (solveTime == 0) ? "" : "_" + solveTime;

		if (classAttributes != null || methodParameters != null) {
			boolean isPre;
			boolean needGenInvCLP = false;
			// objPre
			if (!isConstructor) {
				isPre = true;
				this.object_pre = "Self" + item + "_pre";
				flattenInputStr += nowTestClass.flattenObj("Self" + item, isPre, needGenInvCLP, new HashSet<>(),
						this.realTypeVar);
				checkDomainStr += nowTestClass.checkDomain("Self" + item, isPre);
			} else
				this.object_pre = "[]";
			// objPost
			if (!containException) {
				isPre = false;
				this.object_post = "Self" + item;
				flattenOutputStr += nowTestClass.flattenObj("Self" + item, isPre, needGenInvCLP, new HashSet<>(),
						new HashSet<String>());
				checkDomainStr += nowTestClass.checkDomain("Self" + item, isPre);
			} else
				this.object_post = "[]";
			// arg
			needGenInvCLP = true;
			for (VariableToken arg : methodParameters) {
				String argName = Utility.titleToUppercase(arg.getVariableName());
				argPreName.add(argName + item + "_pre");
				argPostName.add(argName + item);

				if (arg.getType() instanceof UserDefinedType) {
					// argPre
					isPre = true;
					flattenInputStr += arg.getType().flattenObj(argName + item, isPre, needGenInvCLP, new HashSet<>(),
							this.realTypeVar);
					checkDomainStr += arg.getType().checkDomain(argName + item, isPre);
					// argPost
					isPre = false;
					flattenOutputStr += arg.getType().flattenObj(argName + item, isPre, needGenInvCLP, new HashSet<>(),
							new HashSet<String>());
					checkDomainStr += arg.getType().checkDomain(argName + item, isPre);
				} else {
					if (arg.getType() instanceof FloatType || arg.getType() instanceof DoubleType) {
						this.realTypeVar.add(argName + item + "_pre");
					}
				}
			}
			this.arg_pre = argPreName;
			this.arg_post = argPostName;

			this.result = (returnType instanceof VoidType) ? "[]" : "[Rel" + item + "]";

			if (returnType instanceof UserDefinedType) {
				isPre = false;
				flattenOutputStr += returnType.flattenObj("Result" + item, isPre, needGenInvCLP, new HashSet<>(),
						this.realTypeVar);
				checkDomainStr += returnType.checkDomain("Result" + item, isPre);
			}

		}

		HashMap<String, String> retMap = new HashMap<>();
		retMap.put(flattenInputStringMapKey, flattenInputStr);
		retMap.put(flattenOutputStringMapKey, flattenOutputStr);
		retMap.put(checkDomainMapKey, checkDomainStr);

		return retMap;
	}

//	private String genBodyCLP(ArrayList<CLGConstraint> path, int num, String className, String methodName,
//			Boolean isConstructor) {
//		String bodyCLP = "";
//		this.variableSet.clear();
//
//		if (!isConstructor && twoD)
//			bodyCLP += "\ndim(ArrayData,[Row_pre,Col_pre]),";
//
////		constraintList 中將每一個constraint 節點一一取出寫入到bodyCLP
//		for (CLGConstraint c : path) {
//			CLGConstraint cc = c.clone();
//			if (cc instanceof CLGOperatorNode) {
//				if (((CLGOperatorNode) cc).getOperator().equals("=") && ((CLGOperatorNode) cc).getAssign()) {
//					this.renameUseVar(((CLGOperatorNode) cc).getRightOperand());
//					this.renameDefVar(((CLGOperatorNode) cc).getLeftOperand());
//				} else {
//					this.renameUseVar(((CLGOperatorNode) cc).getLeftOperand());
//					this.renameUseVar(((CLGOperatorNode) cc).getRightOperand());
//				}
//			} else {
//				this.renameUseVar(cc);
//			}
//			StringBuilder consCLPStr = new StringBuilder("");
//			CLPInfo cc_clpinfo = cc.getCLPInfo(variableSet);
//			consCLPStr.append(cc_clpinfo.getReturnCLP());
//			bodyCLP += "\n" + consCLPStr.toString() + ",";
//			int methodcallToStringLength = cc_clpinfo.getMethodCallCLP().toString().length();
//			if (methodcallToStringLength > 2) {
//				bodyCLP += "\n" + cc_clpinfo.getMethodCallCLP().toString().substring(1, methodcallToStringLength - 1)
//						+ ",";
//			}
//		}
//
//		return bodyCLP;
//	}

	/*
	 * public String stateAssignEquals(String methodName) { String stateAssignEquals
	 * = "";
	 * 
	 * if (this.object_post.size() > 0 && this.object_pre.size() > 0) for
	 * (VariableToken v : this.object_post) { String object = v.getVariableName();
	 * String temp = object.toLowerCase().charAt(0) + object.substring(1); for
	 * (String attName : Main.symbolTable.getAttribute().keySet()) { VariableToken
	 * variableToken = Main.symbolTable.getAttribute().get(attName); if
	 * (variableToken.getVariableName().equals(temp)) { if
	 * (!(methodName.contains("push") || methodName.contains("pop") ||
	 * methodName.contains("enqueue") || methodName.contains("dequeue"))) {
	 * stateAssignEquals += "\n" + object + "=" + object + "_pre,"; } } } }
	 * 
	 * for (VariableToken v : this.arg_post) { String arg = v.getVariableName();
	 * stateAssignEquals += "\n" + arg + "=" + arg + "_pre,"; }
	 * 
	 * return stateAssignEquals; }
	 */

	private void renameDefVar(CLGConstraint constraint) {
		if (constraint instanceof CLGVariableNode) {
			if (constraint instanceof CLGObjectNode && ((CLGObjectNode) constraint).getQualifier().size() != 0) {
				// 是陣列
				String variable = constraint.getCLPValue();
				String arrayIndex = "[";
				for (CLGConstraint arrayIndexCons : ((CLGObjectNode) constraint).getQualifier()) {
					renameUseVar(arrayIndexCons);
					arrayIndex += arrayIndexCons.getCLPValue() + ",";
				}
				arrayIndex = Utility.delEndRedundantSymbol(arrayIndex, ",") + "]";

				// 是陣列，判斷是否重複要連index一起看
				if (!variableSet.containsKey(variable + arrayIndex)) {
					variableSet.put(variable + arrayIndex, 1);
					constraint.setCLPValue(variable);
				} else if (variableSet.containsKey(variable + arrayIndex)) {
					constraint.setCLPValue(variable + "_" + (variableSet.get(variable + arrayIndex) + 1));
					variableSet.put(variable + arrayIndex, variableSet.get(variable + arrayIndex) + 1);// + 1);
				}
			} else {
				String variable = constraint.getCLPValue();

				if (!variableSet.containsKey(variable)) {
					variableSet.put(variable, 1);
					constraint.setCLPValue(variable);
				} else if (variableSet.containsKey(variable)) {
					constraint.setCLPValue(variable + "_" + (variableSet.get(variable) + 1));
					variableSet.put(variable, variableSet.get(variable) + 1);// + 1);
				}
			}
		}
	}

	private void renameUseVar(CLGConstraint constraint) {

		if (constraint instanceof CLGOperatorNode) {

			if (((CLGOperatorNode) constraint).getOperator().equals("=")
					&& ((CLGOperatorNode) constraint).getAssign()) {
				this.renameUseVar(((CLGOperatorNode) constraint).getRightOperand());
				this.renameDefVar(((CLGOperatorNode) constraint).getLeftOperand());
			} else {
				this.renameUseVar(((CLGOperatorNode) constraint).getLeftOperand());
				this.renameUseVar(((CLGOperatorNode) constraint).getRightOperand());
			}
		} else if (constraint instanceof CLGMethodInvocationNode) {

			CLGMethodInvocationNode methodInvocationNode = (CLGMethodInvocationNode) constraint;

			this.renameUseVar(methodInvocationNode.getMethodObject());

			for (int i = 0; i < methodInvocationNode.getMethodArgument().size(); i++) {

				this.renameUseVar(methodInvocationNode.getMethodArgument().get(i));
			}

		} else if (constraint instanceof CLGVariableNode) {
			if (constraint instanceof CLGObjectNode && ((CLGObjectNode) constraint).getQualifier().size() != 0) {
				// 是陣列
				// 陣列變數部份(需連index一起看
				String variable = constraint.getCLPValue();

				// index部份改編號
				// e.g. data[it] --> data[it_2]
				String arrayIndex = "[";
				for (CLGConstraint index : ((CLGObjectNode) constraint).getQualifier()) {
					this.renameUseVar(index);
					arrayIndex += index.getCLPValue() + ",";
				}
				arrayIndex = Utility.delEndRedundantSymbol(arrayIndex, ",") + "]";

				// 是陣列元素整體是否重複要改編號要連index一起看
				// e.g. data[it] --> data_2[it]
				if (variableSet.containsKey(variable + arrayIndex)) {
					if (variableSet.get(variable + arrayIndex) != 1)
						constraint.setCLPValue(variable + "_" + variableSet.get(variable));
				}

			} else {

				// 物件
				if (((CLGVariableNode) constraint).getConstraint() != null)
					this.renameUseVar(((CLGVariableNode) constraint).getConstraint());

				String variable = constraint.getCLPValue();

				if (variableSet.containsKey(variable)) {
					if (variableSet.get(variable) == 1) {
						constraint.setCLPValue(variable);
					} else {
						constraint.setCLPValue(variable + "_" + variableSet.get(variable));
					}

				}

			}
		}
	}

	// att/arg assigned to new value after execute
	public String stateAssignEquals(String methodName, boolean isConstructor, boolean exception) {
		String stateAssignEquals = "";

		// att
		ArrayList<String> attList = new ArrayList<>();
		if (!this.containException) {
			for (String attName : this.symbolTable.getAttribute().keySet()) {
				if (!variableSet.containsKey("Self_" + attName)) {
					attList.add(attName);
				}
			}
		}
		if (attList.size() != 0 && !isConstructor) {
			stateAssignEquals += "\r\n[";
			for (String attPostName : attList)
				stateAssignEquals += "Self_" + attPostName + ", ";
			stateAssignEquals = Utility.delEndRedundantSymbol(stateAssignEquals, ", ") + "]=[";
			for (String attName : attList) {
				if (variableSet.get("Self_pre_" + attName) == 1)
					stateAssignEquals += "Self_pre_" + attName + ", ";
				else
					stateAssignEquals += "Self_pre_" + attName + "_" + variableSet.get("Self_pre_" + attName) + ", ";
			}
			stateAssignEquals = Utility.delEndRedundantSymbol(stateAssignEquals, ", ") + "],";
		}
		// arg
		ArrayList<String> argList = new ArrayList<>();
		for (String argName : this.symbolTable.getMethod().get(methodName).getArgument().keySet()) {
			if (!variableSet.containsKey(argName)) {
				argList.add(argName);
			}
		}
		if (argList.size() != 0) {
			stateAssignEquals += "\r\n[";
			for (String argPostName : argList)
				stateAssignEquals += Utility.titleToUppercase(argPostName) + ", ";
			stateAssignEquals = Utility.delEndRedundantSymbol(stateAssignEquals, ", ") + "]=[";
			for (String argName : argList) {
				if (variableSet.get(argName + "_pre") == 1)
					stateAssignEquals += Utility.titleToUppercase(argName) + "_pre" + ", ";
				else
					stateAssignEquals += Utility.titleToUppercase(argName) + "_pre" + "_"
							+ variableSet.get(argName + "_pre") + ", ";
			}
			stateAssignEquals = Utility.delEndRedundantSymbol(stateAssignEquals, ", ") + "],";
		}

		return stateAssignEquals;
	}

	public String genMethodPathCLP(CLGPath path, int pathN, String className, TypeTable typeTab) {

		String clpstr1 = ""/* , classn = "" */;
		List<CLGNode> nodeli = path.getPathNodes();
		clpstr1 += importLibraryCLP();

		clpstr1 += "testpath" + (pathN) + "(SDObjPre, SDArgPre, SDObjPost, SDArgPost, SDResult,SDException):-\n\n";

		CLGPathEnumerator clgPathEnumerator = new CLGPathEnumerator();
		nodeli = clgPathEnumerator.filterConstraintNode(nodeli);

		int nCall = 1;
		for (int i = 0; i < nodeli.size(); i++) {
			if (nodeli.get(i) instanceof CLGConstraintNode) {
				CLGConstraintNode c = (CLGConstraintNode) nodeli.get(i);

				if (c.getConstraint() instanceof CLGOperatorNode) { // graud condition
					CLGOperatorNode clgop = (CLGOperatorNode) c.getConstraint();
					if (clgop.getOperator().equals("=")) {
						this.renameUseVar(clgop.getRightOperand());
						this.renameDefVar(clgop.getLeftOperand());

						clpstr1 += "ObjPost" + clpcount + " = [P" + clpcount + "],\n";
						clpstr1 += "P" + clpcount + " #=" + clgop.getRightOperand().getImgInfo() + ",\n";
					} else {
						this.renameUseVar(clgop.getLeftOperand());
						this.renameUseVar(clgop.getRightOperand());
						if (clgop.getOperator().equals("==")) {
							clpstr1 += "ObjPost" + clpcount + " = [P" + clpcount + "],\n";
							clpstr1 += "P" + clpcount + " #= " + clgop.getRightOperand().getImgInfo() + ",\n\n";
						} else {
							clpstr1 += "ObjPost" + clpcount + " = [P" + clpcount + "],\n";
							clpstr1 += "P" + clpcount + " #" + clgop.getOperator()
									+ clgop.getRightOperand().getImgInfo() + ",\n";
						}
					}
				} else if (c.getConstraint() instanceof CLGMethodInvocationNode) {

					CLGMethodInvocationNode methodcall = (CLGMethodInvocationNode) c.getConstraint();
					clpstr1 += this.genMethodCallArg(methodcall, nCall);

					String ObjName = Utility.titleToLowercase(methodcall.getObjType().getType());
					String methodName = Utility.titleToUppercase(methodcall.getMethodName());

					clpstr1 += ObjName + methodName + "(ObjPre" + nCall + ", ArgPre" + nCall + ", ObjPost" + nCall
							+ ", ArgPre" + nCall + ", Result" + nCall + ", Exception" + nCall + "),\n";
					clpstr1 += genObjStateString(nCall, (nodeli.size() == nCall));
					nCall++;
				}
			}
		}

		for (int i = 1; i < nCall; i++) {

			if (i != 1)
				clpstr1 += "labeling(ObjPre" + i + "),\n";

			clpstr1 += "labeling(ArgPre" + i + "),\n";
			clpstr1 += "labeling(ObjPost" + i + "),\n";
			// clpstr1 += "labeling(ArgPost" + i + "),\n";

			if (i != nCall - 1)
				clpstr1 += "labeling(ArgPost" + i + "),\n\n";
			else
				clpstr1 += "labeling(ArgPost" + i + ").\n\n";

			// 之後要將Result 和 exception
		}

		this.body_count++;
		return clpstr1;
	}

	// 名稱之後再改QQ
	public String genObjStateString(int methodCount, boolean islastConstraintNode) {

		String retstr = "";

		retstr += "SDObjPre" + methodCount + " = [ObjPre" + methodCount
				+ ((islastConstraintNode) ? "" : "|SDObjPre" + (methodCount + 1)) + "],\n";

		retstr += "SDArgPre" + methodCount + " = [ArgPre" + methodCount
				+ ((islastConstraintNode) ? "" : "|SDArgPre" + (methodCount + 1)) + "],\n";

		retstr += "SDObjPost" + methodCount + " = [ObjPost" + methodCount
				+ ((islastConstraintNode) ? "" : "|SDObjPost" + (methodCount + 1)) + "],\n";

		retstr += "SDArgPost" + methodCount + " = [ArgPost" + methodCount
				+ ((islastConstraintNode) ? "" : "|SDArgPost" + (methodCount + 1)) + "],\n";

		retstr += "SDResult" + methodCount + " = " + "[Result" + methodCount
				+ ((islastConstraintNode) ? "" : "|SDResult" + (methodCount + 1)) + "],\n";

		retstr += "SDException" + methodCount + " = " + "[Exception" + methodCount
				+ ((islastConstraintNode) ? "" : "|SDException" + (methodCount + 1)) + "],\n\n";

		return retstr;
	}

	public String genMethodCallArg(CLGMethodInvocationNode methodcall, int methodCount) {

		String retstr = "";
		ArrayList<String> argmethodCallCLP = new ArrayList<String>();

		if (methodcall.getMethodArgument().size() > 0) {
			retstr += "[";
			for (CLGConstraint arg : methodcall.getMethodArgument()) {
				CLPInfo arg_clpinfo = arg.getCLPInfo(new HashMap<String, Integer>(), new HashMap<>());
				retstr += arg_clpinfo.getReturnCLP() + ", ";
				argmethodCallCLP.addAll(arg_clpinfo.getMethodCallCLP());
			}
			retstr += Utility.delEndRedundantSymbol(retstr, ", ") + "],\n";
			if (argmethodCallCLP.size() > 0)
				retstr += argmethodCallCLP.toString().substring(1, argmethodCallCLP.toString().length() - 2) + ",\n";
			retstr = "ArgPre" + methodCount + " = " + retstr;
		}

		return retstr;
	}

	// ---
//	public String genMethodPathCLP(CLGPath path, int pathN) {
//		int clpcount = 1;
//		String clpstr1 = "", classn = "";
//		List<CLGNode> nodeli = path.getPathNodes();
//		clpstr1 += importLibraryCLP();
//		clpstr1 += "testpath" + (pathN) + "(Obj_pre, Arg_pre, Obj_post, Arg_post, Ret_val):-\n\n";
//
//		CLGPathEnumerator clgPathEnumerator = new CLGPathEnumerator();
//		nodeli = clgPathEnumerator.filterConstraintNode(nodeli);
//
//		for (int i = 0; i < nodeli.size(); i++) {
//			if (nodeli.get(i) instanceof CLGConstraintNode) {
//				CLGConstraintNode c = (CLGConstraintNode) nodeli.get(i);
//
//				if (c.getConstraint() instanceof CLGOperatorNode) {
//					CLGOperatorNode clgop = (CLGOperatorNode) c.getConstraint();
//					if (clgop.getOperator().equals("=")) {
//						this.renameUseVar(clgop.getRightOperand());
//						this.renameDefVar(clgop.getLeftOperand());
//						clpstr1 += "Poststate" + (clpcount - 1) + " = [P" + (clpcount - 1) + "],\n";
//						clpstr1 += "P" + (clpcount - 1) + " #=" + clgop.getRightOperand().getImgInfo() + ",\n";
//					} else {
//						this.renameUseVar(clgop.getLeftOperand());
//						this.renameUseVar(clgop.getRightOperand());
//						if (clgop.getOperator().equals("==")) {
//							clpstr1 += "Poststate" + (clpcount - 1) + " = [P" + (clpcount - 1) + "],\n";
//							clpstr1 += "P" + (clpcount - 1) + " #= " + clgop.getRightOperand().getImgInfo() + ",\n\n";
//						} else {
//							clpstr1 += "Poststate" + (clpcount - 1) + " = [P" + (clpcount - 1) + "],\n";
//							clpstr1 += "P" + (clpcount - 1) + " #" + clgop.getOperator()
//									+ clgop.getRightOperand().getImgInfo() + ",\n";
//						}
//					}
//				} else if (c.getConstraint() instanceof CLGMethodInvocationNode) {
//					CLGMethodInvocationNode clgme = (CLGMethodInvocationNode) c.getConstraint();
//
//					if (clpcount == 1) {
//						classn = clgme.getMethodName();
//						if (nodeli.size() == 3) {
//							clpstr1 += clgme.getMethodName().toLowerCase()
//									+ clgme.getMethodName().substring(0, 1).toUpperCase()
//									+ clgme.getMethodName().substring(1, clgme.getMethodName().length()) + "(Prearg"
//									+ clpcount + ", Poststate" + clpcount + ", Postarg" + clpcount + "),\n";
//							clpstr1 += "Obj_pre = [[]],\n";
//							clpstr1 += "Arg_pre = [Prearg" + clpcount + "],\n";
//							clpstr1 += "Obj_post = [Poststate" + clpcount + "],\n";
//							clpstr1 += "Arg_post = [Postarg" + clpcount + "],\n";
//							clpstr1 += "Ret_val = [[]].\n\n";
//						} else {
//							clpstr1 += clgme.getMethodName().toLowerCase()
//									+ clgme.getMethodName().substring(0, 1).toUpperCase()
//									+ clgme.getMethodName().substring(1, clgme.getMethodName().length()) + "(Prearg"
//									+ clpcount + ", Poststate" + clpcount + ", Postarg" + clpcount + "),\n";
//							clpstr1 += "Obj_pre = [[]|Pres" + clpcount + "],\n";
//							clpstr1 += "Arg_pre = [Prearg" + clpcount + "|Prea" + clpcount + "],\n";
//							clpstr1 += "Obj_post = [Poststate" + clpcount + "|Posts" + clpcount + "],\n";
//							clpstr1 += "Arg_post = [Postarg" + clpcount + "|Posta" + clpcount + "],\n";
//							clpstr1 += "Ret_val = [[]|Re" + clpcount + "],\n\n";
//						}
//						clpcount++;
//					} else {
//						// check last op
//						if (i != nodeli.size() - 2) {
//							clpstr1 += classn.toLowerCase() + clgme.getMethodName().substring(0, 1).toUpperCase()
//									+ clgme.getMethodName().substring(1, clgme.getMethodName().length()) + "(Poststate"
//									+ (clpcount - 1) + ", Prearg" + clpcount + ", Poststate" + clpcount + ", Postarg"
//									+ clpcount + ", Return" + clpcount + "),\n";
//							clpstr1 += "Pres" + (clpcount - 1) + " = [Poststate" + (clpcount - 1) + "|Pres" + clpcount
//									+ "],\n";
//							clpstr1 += "Prea" + (clpcount - 1) + " = [Prearg" + clpcount + "|Prea" + clpcount + "],\n";
//							clpstr1 += "Posts" + (clpcount - 1) + " = [Poststate" + clpcount + "|Posts" + clpcount
//									+ "],\n";
//							clpstr1 += "Posta" + (clpcount - 1) + " = [Postarg" + clpcount + "|Posta" + clpcount
//									+ "],\n";
//							clpstr1 += "Re" + (clpcount - 1) + " = " + "[Return" + clpcount + "|Re" + clpcount
//									+ "],\n\n";
//							clpcount++;
//						} else {
//							clpstr1 += classn.toLowerCase() + clgme.getMethodName().substring(0, 1).toUpperCase()
//									+ clgme.getMethodName().substring(1, clgme.getMethodName().length()) + "(Poststate"
//									+ (clpcount - 1) + ", Prearg" + clpcount + ", Poststate" + clpcount + ", Postarg"
//									+ clpcount + ", Return" + clpcount + "),\n";
//							clpstr1 += "Pres" + (clpcount - 1) + " = [Poststate" + (clpcount - 1) + "],\n";
//							clpstr1 += "Prea" + (clpcount - 1) + " = [Prearg" + clpcount + "],\n";
//							clpstr1 += "Posts" + (clpcount - 1) + " = [Poststate" + clpcount + "],\n";
//							clpstr1 += "Posta" + (clpcount - 1) + " = [Postarg" + clpcount + "],\n";
//							clpstr1 += "Re" + (clpcount - 1) + " = " + "[Return" + clpcount + "].\n\n";
//							clpcount++;
//						}
//					} // end else
//				}
//			}
//		}
//		this.body_count++;
//		return clpstr1;
//	}

}