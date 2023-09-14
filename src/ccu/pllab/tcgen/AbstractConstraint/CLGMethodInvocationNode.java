package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import ccu.pllab.tcgen.AbstractType.ArrayType;
import ccu.pllab.tcgen.AbstractType.BooleanType;
import ccu.pllab.tcgen.AbstractType.CollectionType;
import ccu.pllab.tcgen.AbstractType.DoubleType;
import ccu.pllab.tcgen.AbstractType.FloatType;
import ccu.pllab.tcgen.AbstractType.IntType;
import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.SymbolTable.MethodToken;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;
import ccu.pllab.tcgen.pathCLPFinder.CLPSolver;

public class CLGMethodInvocationNode extends CLGVariableNode {

	public static final HashSet<String> keywordMethod = new HashSet<String>();
	// private CLGConstraint methodObject;
	// private String methodName; //-->эΘ ㄏノ CLGVariableNode  name
	private ArrayList<CLGConstraint> methodArgument;
	private boolean isNewObj = false;
	private VariableType returnType;
	// method invocation need original constraint name
	private CLGVariableNode methodObjectUnrenamed;// the methodObject wasn't renamed
	private ArrayList<CLGConstraint> methodArgumentUnrenamed;// the methodArgument wasn't renamed

	public CLGMethodInvocationNode(CLGConstraint methodObject, VariableType methodObjectType, String methodName,
			ArrayList<CLGConstraint> methodArgument, VariableType returnType) {
		super(methodName, methodObjectType, methodObject);

//		this.methodObject = methodObject;
//		this.methodName = methodName;
		this.methodArgument = new ArrayList<CLGConstraint>();
		this.methodArgument.addAll(methodArgument);
		this.returnType = returnType;
		this.methodObjectUnrenamed = (CLGVariableNode) methodObject.clone();
		this.methodArgumentUnrenamed = new ArrayList<CLGConstraint>();

		for (CLGConstraint arg : methodArgument)
			this.methodArgumentUnrenamed.add(arg.clone());
	}

	public CLGMethodInvocationNode(CLGConstraint methodObject, VariableType methodObjectType, String methodName,
			ArrayList<CLGConstraint> methodArgument) {
		super(methodName, methodObjectType, methodObject);

		this.methodArgument = new ArrayList<CLGConstraint>();
//		this.methodObject = methodObject;
//		this.methodName = methodName;
		this.methodArgument.addAll(methodArgument);
		this.methodObjectUnrenamed = (CLGVariableNode) methodObject.clone();
		this.methodArgumentUnrenamed = new ArrayList<CLGConstraint>();

		for (CLGConstraint arg : methodArgument)
			this.methodArgumentUnrenamed.add(arg.clone());
	}

	public static HashSet<String> getKeywordMethod() {
		return keywordMethod;
	}

	public void setIsNewObj(boolean b) {
		this.isNewObj = b;
	}

	public boolean isNewObj() {
		return this.isNewObj;
	}

	public void setMethodObject(CLGVariableNode methodObject) {
		this.setConstraint(methodObject);
		return;
	}

	public void setMethodObject(CLGConstraint methodObject) {
		this.setConstraint(methodObject);
		return;
	}

	public CLGConstraint getMethodObject() {
		return this.getConstraint();
	}

	public String getMethodName() {
		return this.getName();
	}

	public VariableType getReturnType() {
		return this.returnType;
	}

	public ArrayList<CLGConstraint> getMethodArgument() {
		return (ArrayList<CLGConstraint>) this.methodArgument;
	}

	public void setMethodArgument(ArrayList<CLGConstraint> methodArgument) {
		this.methodArgument.addAll(methodArgument);
	}

	// 20210828 ⊿ノ
	public void addMethodArgument(CLGConstraint arg) {
		this.methodArgument = new ArrayList<CLGConstraint>();
		this.methodArgumentUnrenamed = new ArrayList<CLGConstraint>();
		this.methodArgument.add(arg.clone());
		this.methodArgumentUnrenamed.add(arg.clone());
	}

	@Override
	public String getImgInfo() {
		return this.getConstraintName();
		// return this.getConstraintName().replaceAll("_pre", "");
	}

	@Override
	public CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey) {
		String retStr = "";
		ArrayList<String> methodcall = new ArrayList<String>();
		String arg = "";
		for (CLGConstraint argAstNode : this.methodArgument) {
			// 把计场var(э?
			if (argAstNode instanceof CLGVariableNode) {

				CLGVariableNode var = (CLGVariableNode) argAstNode;
				CLPInfo var_clpInfo = var.getCLPInfo(variableSet, containKey);
				methodcall.addAll(var_clpInfo.getMethodCallCLP());
				arg += var_clpInfo.getReturnCLP() + ", ";

			} else if (argAstNode instanceof CLGLiteralNode) {
				CLGLiteralNode literal = (CLGLiteralNode) argAstNode;
				CLPInfo literal_clpinfo = literal.getCLPInfo(variableSet, containKey);
				if (literal.getType() instanceof StringType) {
					methodcall.addAll(literal_clpinfo.getMethodCallCLP());
					arg += literal_clpinfo.getReturnCLP() + ", ";
				} else if (literal.getType() instanceof IntType) {
					arg += literal.getValue() + ", ";
				}
			} else if (argAstNode instanceof CLGOperatorNode) {
				CLGOperatorNode Operator = (CLGOperatorNode) argAstNode;
				CLPInfo operator_clpinfo = Operator.getCLPInfo(variableSet, containKey);
				String operator_returnstr = operator_clpinfo.getReturnCLP();
				methodcall.addAll(operator_clpinfo.getMethodCallCLP());
				arg += operator_returnstr + ", ";

			}
		}
		arg = Utility.delEndRedundantSymbol(arg, ", ");

		// 絋粄this.methodObject 
		String methodObjectName = "";
		VariableType methodObjectType = new VoidType();
		CLPInfo obj_clpInfo = new CLPInfo();

		if (this.getConstraint() instanceof CLGMethodInvocationNode) {
			obj_clpInfo = ((CLGMethodInvocationNode) this.getConstraint()).getCLPInfo(variableSet, containKey);
			methodObjectName = obj_clpInfo.getReturnCLP();
			methodcall.addAll(obj_clpInfo.getMethodCallCLP());
			methodObjectType = ((CLGMethodInvocationNode) this.getConstraint()).getReturnType();
		} else if (this.getConstraint() instanceof CLGObjectNode || this.getConstraint() instanceof CLGClassNode) {
			obj_clpInfo = ((CLGVariableNode) this.getConstraint()).getCLPInfo(variableSet, containKey);
			methodObjectName = obj_clpInfo.getReturnCLP();
			methodcall.addAll(obj_clpInfo.getMethodCallCLP());
			methodObjectType = ((CLGVariableNode) this.getConstraint()).getType();
		} else if (this.getConstraint() instanceof CLGOperatorNode) {
			obj_clpInfo = ((CLGOperatorNode) this.getConstraint()).getCLPInfo(variableSet, containKey);
			methodObjectName = obj_clpInfo.getReturnCLP();
			methodcall.addAll(obj_clpInfo.getMethodCallCLP());
			methodObjectType = ((CLGOperatorNode) this.getConstraint()).getType();
		}

		// セ╰参箇砞method
		if (keywordMethod.contains(this.getMethodName())) {
			CLPInfo clpinfo = this.getSystemMethodInvoc(arg, variableSet, containKey);
			methodcall.addAll(clpinfo.getMethodCallCLP());
			return new CLPInfo(retStr + clpinfo.getReturnCLP(), methodcall);

		} else if (methodObjectType instanceof BooleanType || methodObjectType instanceof IntType
				|| methodObjectType instanceof StringType || methodObjectType instanceof FloatType
				|| methodObjectType instanceof DoubleType || methodObjectType instanceof CollectionType) {

			retStr += "Result_" + Utility.titleToUppercase(methodObjectName) + "_"
					+ Utility.titleToUppercase(this.getMethodName()) + "_R_" + this.getConstraintId();

			MethodToken methodToken = Main.basicTypeMethodCall.findMethodCall(methodObjectType, this.getMethodName());

			String methodcall_str = "ocl_";

			if (methodObjectType instanceof FloatType || methodObjectType instanceof DoubleType) {
				methodcall_str += "real_";
			} else if (methodObjectType instanceof CollectionType) {
				methodcall_str += "collection_";
			} else {
				methodcall_str += methodObjectType.toString().toLowerCase() + "_";
			}

			methodcall_str += this.getMethodName() + "(" + Utility.titleToUppercase(methodObjectName) + ", ";

			if (methodToken.getArgument().size() > 0)
				methodcall_str += arg + ", ";

			methodcall_str += "Result_" + Utility.titleToUppercase(methodObjectName) + "_"
					+ Utility.titleToUppercase(this.getMethodName()) + "_R_" + this.getConstraintId() + ")";

			methodcall.add(methodcall_str);

		} // ㄏノ璹竡method
		else {
			/*
			 * ㄧΑ㊣CLP肂场
			 */
			String objPre = "", objPost = "";

			String nameDelPre = methodObjectName.replaceAll("_pre", "");
			if (methodObjectName.equals("null")) {
				objPre = "[]";
				objPost = "[]";
			} else {
				boolean needGenInvCLP = true;
				objPre = methodObjectType.flattenObj(nameDelPre, true, needGenInvCLP, new HashSet<String>(),
						new HashSet<String>());
				objPost = methodObjectType.flattenObj(nameDelPre, false, needGenInvCLP, new HashSet<String>(),
						new HashSet<String>());
			}
			/*
			 * 沮methodObject笆玻ネン玡跑计籔ず甧 盢肂惠㊣ㄤㄧΑCLPmethodInvocationCLP
			 */
			String objPreName, objPostName, argPreName, argPostName, resultName, exceptionName;
			String methodObjCLPName = methodObjectName.replaceAll("[\\]]", "").replaceAll("[\\[]", "_");
			objPreName = Utility.titleToUppercase(methodObjCLPName) + "_"
					+ Utility.titleToUppercase(this.getMethodName()) + "_Obj_pre";
			objPostName = Utility.titleToUppercase(methodObjCLPName) + "_"
					+ Utility.titleToUppercase(this.getMethodName()) + "_Obj";
			argPreName = Utility.titleToUppercase(methodObjCLPName) + "_"
					+ Utility.titleToUppercase(this.getMethodName()) + "_Arg_pre";
			argPostName = Utility.titleToUppercase(methodObjCLPName) + "_"
					+ Utility.titleToUppercase(this.getMethodName()) + "_Arg";
			resultName = "Result_" + Utility.titleToUppercase(methodObjCLPName) + "_" + Utility.titleToUppercase(this.getMethodName());
			if (variableSet.containsKey(resultName)) {
				int resultCount = variableSet.get(resultName);
				if(resultCount!=1)
					resultName += "_" + resultCount;
			}
			exceptionName = "[]";

			String methodcallstr = "";
			// ㄧΑン把计玡砞﹚
			methodcallstr += (isNewObj ? "" : objPreName + "=" + methodObjectName + ",\n") + argPreName + "=" + "["
					+ arg + "]" + ",";

			// ㄧΑ
			methodcallstr += "\n" + Utility.titleToLowercase(methodObjectType.getType())
					+ Utility.titleToUppercase(this.getMethodName());

			// ㄧΑ把计
			methodcallstr += "(" + objPreName + "," + argPreName + "," + objPostName + "," + argPostName + ","
					+ resultName + "," + exceptionName + ")";

			methodcall.add(methodcallstr);

			/*
			 * ン把计篈磅︽锣簿ㄧΑ秸ノ竊翴ミㄧΑ㊣ン籔把计穦玂痙
			 * CLPTransltor璶玻ネCLP玡穦盢竊翴㏑璝璶盢篈锣簿玡篈穦тぃ穓碝跑计(variableSet)
			 * 珿穦methodObjectUnrenamed, methodArgumentUnrenamed
			 * e.g.
			 * ㄧΑ㊣ン: X_pre_2
			 * methodObjectUnrenamed: X_pre
			 * methodObjectUnrenamed穓碝variableSet秈︽㏑
			 * =>ㄧΑ磅︽ン: X_pre_3
			 */
			String stateTrans = "";
			if (!(methodObjectType instanceof ArrayType)) {
				if (isNewObj) {
					retStr += objPostName + ",";
				} else {
					retStr += resultName + ",";
					if (this.getConstraint() != null) {
						CLGConstraint objClone = this.methodObjectUnrenamed.clone();
						objClone.renameDefVar(variableSet, new HashSet<>(), false);

						CLPInfo objCloneCLPInfo = objClone.getCLPInfo(variableSet, containKey);

						methodcall.addAll(objCloneCLPInfo.getMethodCallCLP());
						stateTrans += objCloneCLPInfo.getReturnCLP() + "=" + objPostName + ",";
						stateTrans = Utility.delEndRedundantSymbol(stateTrans, ",");
						methodcall.add(stateTrans);

						retStr = Utility.delEndRedundantSymbol(retStr, ",");
					}
				}
			}

			String stateTransArg = "";
			if (this.methodArgument.size() != 0) {
				stateTransArg += "[";
				for (CLGConstraint argPreState : this.methodArgumentUnrenamed) {
					CLGConstraint argClone = argPreState.clone();
					argClone.renameDefVar(variableSet, new HashSet<>(), false);

					CLPInfo argCloneCLPInfo = argClone.getCLPInfo(variableSet, containKey);

					stateTransArg += argCloneCLPInfo.getReturnCLP() + ",";
					methodcall.addAll(argCloneCLPInfo.getMethodCallCLP());
				}
				stateTransArg = Utility.delEndRedundantSymbol(stateTransArg, ",") + "]=" + argPostName;

				retStr = Utility.delEndRedundantSymbol(retStr, ",");
				retStr += ",\r\n" + Utility.delEndRedundantSymbol(stateTransArg, ",");

			}

			retStr = Utility.delEndRedundantSymbol(retStr, ",");

			return new CLPInfo(retStr, methodcall);
		}
		return new CLPInfo(retStr, methodcall);

	}

	@Override
	public CLGConstraint clone() {

		ArrayList<CLGConstraint> methodArgument_clone = new ArrayList<CLGConstraint>();
		if (this.methodArgument.size() > 0) {
			for (CLGConstraint c : this.methodArgument)
				methodArgument_clone.add(c.clone());
		}
		CLGConstraint cons = new CLGMethodInvocationNode(this.getConstraint().clone(), this.getType(),
				this.getMethodName(), methodArgument_clone, this.returnType);
		cons.setCloneId(this.getConstraintId());
		((CLGMethodInvocationNode) cons).setIsNewObj(this.isNewObj);
		return cons;
		// return new CLGMethodInvocationNode(this.methodObject, this.methodName,
		// this.methodArgument);
	}

	@Override
	public String getCLPValue() {
		return null;
	}

	@Override
	public void setCLPValue(String data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void negConstraint() {
	}

	@Override
	public void preconditionAddPre(SymbolTable sym, String methodname) {
		// 20210911 dai
		if (this.getConstraint() != null)
			this.getConstraint().preconditionAddPre(sym, methodname);

		for (CLGConstraint arg : this.getMethodArgument()) {
			arg.preconditionAddPre(sym, methodname);
		}
	}

	@Override
	public void renameWithMap(HashMap<String, String> attMap) {
		if (this.getConstraint() != null)
			this.getConstraint().renameWithMap(attMap);

		for (CLGConstraint arg : this.getMethodArgument()) {
			arg.renameWithMap(attMap);
		}

	} // 沮э跑计

	protected void setMethodName(String methodName) {
		this.setName(methodName);
		return;
	}

	@Override
	public String getConstraintName() {
		String retName = "";
		String methodObjName = this.getMethodObject().getConstraintName();
		if (methodObjName.equals("") || methodObjName.equals("null") || methodObjName==null)
			retName += this.getMethodName();
		else
			retName += methodObjName + "." + this.getMethodName();
		if (this.methodArgument.size() != 0) {
			retName += "(";
			for (CLGConstraint cons : this.methodArgument) {
				retName += cons.getConstraintName() + ",";
			}
			retName = Utility.delEndRedundantSymbol(retName, ",") + ")";
		}
		return retName;
	}

	@Override
	public CLGConstraint addPre() {

		if (this.getMethodObject() instanceof CLGOperatorNode
				&& (((CLGOperatorNode) this.getMethodObject()).getType().equals("class"))) {
			return this;
		}
		if ((this.getMethodObject() instanceof CLGObjectNode || this.getMethodObject() instanceof CLGClassNode)
				&& (((CLGVariableNode) this.getMethodObject()).getType().equals("class"))) {
			return this;
		}

		this.setConstraint(this.getConstraint().addPre());
		return this;

		// 20210716 ┾禜粂猭计跑Θオ患癹
		/*
		 * if (((CLGOperatorNode) this.getMethodObject()).getType().equals("class") ||
		 * ((CLGVariableNode) this.getMethodObject()).getType().equals("class")) { // 硂
		 * return this; } else { this.setConstraint(this.getConstraint().addPre());
		 * return this; }
		 */
	}

	public static void compileSystemMethod() {
		CLPSolver reservedMethodCompiler = new CLPSolver();
		String methodCLP = ":- lib(ic).\n" + ":- lib(timeout).\n";

		// findElement
		methodCLP += "findelement(Sequence,X,Index):-\n" + "Index#>1,\n" + "Index1#=Index-1,\n"
				+ "subsequence(Sequence,Sequence_post),\n" + "findelement(Sequence_post,X,Index1).\n"
				+ "findelement(Sequence,X,1):-\n" + "sequenceFirst(Sequence, X).\n" + "sequenceFirst([H|_], H).\n"
				+ "subsequence([_|H], H).\n";

		// isSort
		methodCLP += "isSort(Seq,0).\nisSort(Seq,1).\r\n" + "	isSort(Seq,Size):-\r\n" + "	Size#>1,\r\n"
				+ "	Size2#=Size-1,\r\n" + "	findelement(Seq,A,Size),\r\n" + "	findelement(Seq,B,Size2),\r\n"
				+ "	B#=<A,\r\n" + "	isSort(Seq,Size2).\n";

		// result assignment
		methodCLP += "resultAssignment(Result, Var):-\r\n" + "    number(Var)->\r\n" + "    Result#=Var;"
				+ "    Result=Var.\r\n";

		// dimension domain
		methodCLP += "dimDomain([]).\r\n" + "dimDomain([X|L]):-\r\n" + "	X:: 1..32767,\r\n" + "	dimDomain(L).\r\n";

		// dimension labeling
		methodCLP += "dimLabeling([]).\r\n" + "dimLabeling([X|L]):-\r\n" + "	X:: 2..32767,\r\n"
				+ "	labeling([X]),\r\n" + "	dimLabeling(L).\r\n";

		// get array element
		methodCLP += "delay getArrayEle(X,Index,_) if (var(X);nonground([Index])).\r\n"
				+ "getArrayEle(X, Index, Ele):-\r\n" + "	dimDomain(Index),\r\n" + "	arg(Index, X, Ele).\r\n";

		// delay mod
		methodCLP += "delay o_mod(M,N,_) if nonground([M,N]).\n" + "o_mod(M,N,R):-\n" + "\tmod(M,N,R).\n";

		// delay gcd
		methodCLP += "delay o_gcd(M,N,_) if nonground([M,N]).\n" + "o_gcd(M,N,R):-\n" + "\tgcd(M,N,R).\n";

		// string declaration
		methodCLP += "delay string(_, Index, _) if nonground(Index).\r\n" + "array_char(X, Index, Type):-\r\n"
				+ "	Index :: 0..32767,\r\n" + "	dim(X, Index),\r\n" + "	(foreachelem(Ele,X)\r\n" + "		do\r\n"
				+ "		Ele :: 0..128\r\n" + "	).\n";
		// get the specific element of the list
		methodCLP += "listGet([H|_], 1, H).\r\n" + "listGet([], Index, Ele):-\r\n" + "	Index #> 0,\r\n"
				+ "	false.\r\n" + "listGet([_|T], Index, Ele):-\r\n" + "	Index2 is Index - 1,\r\n"
				+ "	listGet(T, Index2, Ele).\r\n";
		
		// int type labeling_median
		methodCLP += "labeling_median(Vars) :-\r\n" + "		collection_to_list(Vars, List),\r\n"
				+ "		( foreach(Var,List) do\r\n" + "		    indomain(Var,median)\r\n" + "		).\r\n";

		// intstring collection闽
		methodCLP += Main.typeTable.getBaseTypemethodCallCLP();

		DataWriter.writeInfo(methodCLP, "reservedMehodCLP", "ecl", DataWriter.testMethodCLP_output_path);

		if (!reservedMethodCompiler.compiling(DataWriter.testMethodCLP_output_path, "reservedMehodCLP")) {
			System.out.println("reserved method failed to compile.");
		}
		// method keyword
		String[] keywordList = { "findelement", "isSort", //old version clause
				"getArrayEle", // get the specific element of the array
				"mod", // delay mod
				"array_char", "gcd", "dim", // array dimesion
				"listGet", // get the specific element of the list
				"dcl_array" };
		for (String keyword : keywordList) {
			keywordMethod.add(keyword);
		}
	}

	private CLPInfo getSystemMethodInvoc(String argCLPStr, HashMap<String, Integer> variableSet,
			HashMap<String, Boolean> containKey) {
		String retStr = "";
		ArrayList<String> methodcall = new ArrayList<String>();
		switch (this.getMethodName()) {
		case "mod": {
			// mod
			if (variableSet.containsKey("Remainder")) {
				retStr = "Remainder" + variableSet.get("Remainder");
				variableSet.put("Remainder", variableSet.get("Remainder") + 1);
			} else {
				retStr = "Remainder";
				variableSet.put("Remainder", 1);
			}
			CLPInfo clpinfo = this.getConstraint().getCLPInfo(variableSet, containKey);
			methodcall.addAll(clpinfo.getMethodCallCLP());
			methodcall.add("o_mod(" + clpinfo.getReturnCLP() + ", " + argCLPStr + ", " + retStr + ")");
			break;
		}
		case "gcd": {
			if (variableSet.containsKey("GcdFactor")) {
				retStr = "GcdFactor" + variableSet.get("GcdFactor");
				variableSet.put("GcdFactor", variableSet.get("GcdFactor") + 1);
			} else {
				retStr = "GcdFactor";
				variableSet.put("GcdFactor", 1);
			}

			CLPInfo clpinfo1 = this.getConstraint().getCLPInfo(variableSet, containKey);
			methodcall.addAll(clpinfo1.getMethodCallCLP());
			methodcall.add("o_gcd(" + argCLPStr + ", " + retStr + ")");
			break;
		}
		case "dim": {
			// call array predicate把计い丁list
			retStr = "[" + argCLPStr + "]";
			break;
		}
		case "dcl_array": {

			String variableName = "DclArray" + ((ArrayType) this.getType()).getUnitType().getType();

			if (variableSet.containsKey(variableName)) {
				retStr = variableName + variableSet.get(variableName);
				variableSet.put(variableName, variableSet.get(variableName) + 1);
			} else {
				retStr = variableName;
				variableSet.put(variableName, 1);
			}

			CLPInfo clpinfo1 = this.getConstraint().getCLPInfo(variableSet, containKey);
			methodcall.addAll(clpinfo1.getMethodCallCLP());
			methodcall.add("dcl_array(" + retStr + "," + argCLPStr + ", \""
					+ Utility.titleToUppercase(((ArrayType) this.getType()).getUnitType().getType()) + "\")");
			break;
		}
		default:
			// セ╰参ㄤウ箇砞method
			retStr = this.getMethodName() + "(" + argCLPStr + ")";
		}
		return new CLPInfo(retStr, methodcall);
	}

	@Override
	public String getOriginalConName() {
		String argConName = "";
		for (CLGConstraint c : this.methodArgument)
			argConName += c.getOriginalConName() + ", ";
		argConName = Utility.delEndRedundantSymbol(argConName, ", ");
		if (this.getConstraint() != null)
			return this.getConstraint().getOriginalConName() + "." + this.getMethodName() + "(" + argConName + ")";
		else
			return this.getMethodName() + "(" + argConName + ")";
	}

	@Override
	protected void reverseMethodDefVar(HashMap<String, Integer> variableSet) {
		if (!(super.getType() instanceof ArrayType)) {
			// 篶ン礚篈锣簿
			if (!isNewObj)
				if (super.getConstraint() != null)
					super.getConstraint().reverseDefVar(variableSet);
		}

		if (this.methodArgument.size() != 0)
			for (CLGConstraint arg : this.methodArgument)
				arg.reverseDefVar(variableSet);
	}

	@Override
	public void renameUseVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
		if(!(this.getConstraint() instanceof CLGClassNode))
			this.getConstraint().renameUseVar(variableSet, defineVariableSet, isMethodCLP);
		for (CLGConstraint arg : this.methodArgument) {
			arg.renameUseVar(variableSet, defineVariableSet, isMethodCLP);
		}
		return;
	}

}
