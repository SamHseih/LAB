package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ccu.pllab.tcgen.AbstractType.BooleanType;
import ccu.pllab.tcgen.AbstractType.DoubleType;
import ccu.pllab.tcgen.AbstractType.FloatType;
import ccu.pllab.tcgen.AbstractType.IntType;
import ccu.pllab.tcgen.AbstractType.RealType;
import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

public class CLGOperatorNode extends CLGConstraint {
	private String operator;
	private String name; // 描述這Operator結果的
	private VariableType type; // 此節點
	private CLGConstraint leftOperand;
	private CLGConstraint rightOperand;
	private boolean fromIterateExp = false;
	private boolean boundary = false;
	private CLGOperatorNode useclone;
	private String cloneConId;
	private boolean isAssign = false;

	public CLGOperatorNode(String operation) {
		super();
		switch (operation) {
		case "||":
		case "or":
			this.operator = "||";
			break;
		case "&&":
		case "and":
			this.operator = "&&";
			break;
		default:
			this.operator = operation;
		}
		this.type = new VoidType();
		leftOperand = null;
		rightOperand = null;
		this.name = "Result_" + this.getConstraintId();
	}

	public CLGOperatorNode(String operation, boolean isassign) {
		super();
		switch (operation) {
		case "||":
		case "or":
			this.operator = "||";
			break;
		case "&&":
		case "and":
			this.operator = "&&";
			break;
		default:
			this.operator = operation;
		}
		this.type = new VoidType();
		this.isAssign = isassign;
		leftOperand = null;
		rightOperand = null;
	}

	public CLGOperatorNode() {
		super();
		this.operator = "";
		this.type = new VoidType();
		leftOperand = null;
		rightOperand = null;
	}

	public void setOperator(String op) {
		this.operator = op;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setLeftOperand(CLGConstraint constraint) {
		this.leftOperand = constraint;
	}

	public CLGConstraint getLeftOperand() {
		return this.leftOperand;
	}

	public void setRightOperand(CLGConstraint constraint) {
		this.rightOperand = constraint;
	}

	public CLGConstraint getRightOperand() {
		return this.rightOperand;
	}

	public void setType(VariableType type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public VariableType getType() {
		return this.type;
	}

	public void setAssignTrue() {
		this.isAssign = true;
	}

	public boolean getAssign() {
		return this.isAssign;
	}

	public void setFromIterateExp() {
		this.fromIterateExp = true;
	}

	public boolean getFromIterateExp() {
		return this.fromIterateExp;
	}

	public void setBoundary() {
		this.boundary = true;
	}

	public boolean getBoundary() {
		return this.boundary;
	}

	private void setUseClone(CLGOperatorNode node) {
		this.useclone = node;
	}

	public CLGOperatorNode getUseClone() {
		return this.useclone;
	}

	private void setCloneCID(String id) {
		this.cloneConId = id;
	}

	public String getCloneCID() {
		return this.cloneConId;
	}

	public void negation() {
		/* criteria */
		if (this.operator.equals("==")) {
			this.operator = "<>";
		} else if (this.operator.equals("!=")) {
			this.operator = "==";
		} else if (this.operator.equals(">")) {
			this.operator = "<=";
		} else if (this.operator.equals("<")) {
			this.operator = ">=";
		} else if (this.operator.equals(">=")) {
			this.operator = "<";
		} else if (this.operator.equals("<=")) {
			this.operator = ">";
		} else if (this.operator.equals("&&") || this.operator.equals("and")) {
			this.operator = "||";
			CLGConstraint opClass = new CLGOperatorNode();
			if (this.leftOperand.getClass().equals(opClass.getClass())) {
				CLGConstraint newLeftCons = this.leftOperand.clone();
				((CLGOperatorNode) newLeftCons).negation();
				this.leftOperand = newLeftCons;
			}
			if (this.rightOperand.getClass().equals(opClass.getClass())) {
				CLGConstraint newRightCons = this.rightOperand.clone();
				((CLGOperatorNode) newRightCons).negation();
				this.rightOperand = newRightCons;
			}
		} else if (this.operator.equals("||") || this.operator.equals("or")) {
			this.operator = "&&";
			CLGConstraint opClass = new CLGOperatorNode();
			if (this.leftOperand.getClass().equals(opClass.getClass())) {
				CLGConstraint newLeftCons = this.leftOperand.clone();
				((CLGOperatorNode) newLeftCons).negation();
				this.leftOperand = newLeftCons;
			}
			if (this.rightOperand.getClass().equals(opClass.getClass())) {
				CLGConstraint newRightCons = this.rightOperand.clone();
				((CLGOperatorNode) newRightCons).negation();
				this.rightOperand = newRightCons;
			}
		}
	}

	@Override
	public String getImgInfo() {
		if (this.operator.equals("!=")) {
			this.operator = "<>";
		}
		String info = "";// 改過
		if (leftOperand != null)
			info += leftOperand.getImgInfo();
		if (rightOperand != null)
			info += this.operator + rightOperand.getImgInfo();
		return info;
		// return leftOperand.getImgInfo() + this.operator + rightOperand.getImgInfo();
	}

	@Override
	public CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey) {
		String new_op = "";

		switch (this.operator) {
		case "==":
			new_op = "=";
			break;
		case "!=":
			new_op = "\\=";

			break;
		case "<>":
			new_op = "\\=";
			break;
		case "<":
			new_op = "<";
			break;
		case ">":
			new_op = ">";
			break;
		case "<=":
			new_op = "=<";
			break;
		case ">=":
			new_op = ">=";
			break;
		case "&&":
			new_op = ",\n";
			break;
		case "||":
		case "or":
			new_op = " or ";
			break;
		case "=":
			new_op = "=";
			break;
		default:
			new_op = this.operator;
			break;
		}

		if (this.operator.equals("not")) {
			this.negConstraint();
			return this.rightOperand.getCLPInfo(variableSet, containKey);
		}

		// 因應不同type，將Operator 改成符合type 的 Operator。
		new_op = changeOperatorForType(new_op);

		if (this.type instanceof StringType) {

			CLPInfo leftinfo = this.leftOperand != null ? this.leftOperand.getCLPInfo(variableSet, containKey)
					: new CLPInfo();
			CLPInfo rightinfo = this.rightOperand != null ? this.rightOperand.getCLPInfo(variableSet, containKey)
					: new CLPInfo();

			// string compare
			if ((new_op.equals("=") || new_op.equals("\\=") || new_op.equals(">=") || new_op.equals("=<")
					|| new_op.equals(">") || new_op.equals("<"))) {
				return stringCompareCLP(new_op, leftinfo, rightinfo, variableSet);
			}

			if (this.operator.equals("+") && this.type instanceof StringType) {
				// 看成是 leftOperand.concat(rightOperand)
				ArrayList<CLGConstraint> argList = new ArrayList<>();
				argList.add(this.rightOperand);
				CLGObjectNode leftOp2Obj = null;
				leftOp2Obj = new CLGObjectNode(leftinfo.getReturnCLP());
				leftOp2Obj.setType(new StringType());
				CLGMethodInvocationNode modCLGNode = new CLGMethodInvocationNode(this.leftOperand, new StringType(),
						"concat", argList);

				return modCLGNode.getCLPInfo(variableSet, containKey);
			}
		}

		// operator %
		if (this.operator.equals("%")) {
			// 看成是 leftOperand.mod(rightOperand)
			ArrayList<CLGConstraint> argList = new ArrayList<>();
			argList.add(this.rightOperand);
			CLGObjectNode leftOp2Obj = new CLGObjectNode(this.leftOperand.getConstraintName());
			CLGMethodInvocationNode modCLGNode = new CLGMethodInvocationNode(leftOp2Obj, new IntType(), "mod", argList);
			ArrayList<String> methodCall = new ArrayList<String>();
			methodCall.addAll(this.leftOperand.getCLPInfo(variableSet, containKey).getMethodCallCLP());
			methodCall.addAll(this.rightOperand.getCLPInfo(variableSet, containKey).getMethodCallCLP());

			// modCLGNode.getCLPInfo 只能呼叫一次，不然在轉CLP時限制式節點id對不上
			CLPInfo clpinfo = modCLGNode.getCLPInfo(variableSet, containKey);
			methodCall.addAll(clpinfo.getMethodCallCLP());
			return new CLPInfo(clpinfo.getReturnCLP(), methodCall);
			// return modCLGNode.getCLPInfo(clpStr, variableSet);
		}

		if (this.operator.equals("||")) {
			if (this.rightOperand != null) {
				if (leftOperand != null) {
					// .getCLPInfo 只能呼叫一次，不然在轉CLP時限制式節點id對不上
					CLPInfo left_clpinfo = this.leftOperand.getCLPInfo(variableSet, containKey);
					CLPInfo right_clpinfo = this.rightOperand.getCLPInfo(variableSet, containKey);

					String return_str = "((" + left_clpinfo.getReturnCLP() + ");(" + right_clpinfo.getReturnCLP()
							+ "))";

					ArrayList<String> methodcallList = new ArrayList<String>();
					// 條件可能需要修改
					if (left_clpinfo.getMethodCallCLP().toString().length() > 0)
						methodcallList.addAll(left_clpinfo.getMethodCallCLP());
					if (right_clpinfo.getMethodCallCLP().toString().length() > 0)
						methodcallList.addAll(right_clpinfo.getMethodCallCLP());

					return new CLPInfo(return_str, methodcallList);

//					return "(" + this.leftOperand.getCLPInfo(clpStr, variableSet) + ";"
//							+ this.rightOperand.getCLPInfo(clpStr, variableSet) + ")";
				} else
					return this.rightOperand.getCLPInfo(variableSet, containKey);
			} else
				return this.leftOperand.getCLPInfo(variableSet, containKey);
		} else {

			CLPInfo leftinfo = new CLPInfo();
			CLPInfo rightinfo = new CLPInfo();
			if (this.leftOperand != null)
				leftinfo = this.leftOperand.getCLPInfo(variableSet, containKey);
			if (this.rightOperand != null) {

				rightinfo = this.rightOperand.getCLPInfo(variableSet, containKey);
				ArrayList<String> methodcall = new ArrayList<String>();
				methodcall.addAll(leftinfo.getMethodCallCLP());
				methodcall.addAll(rightinfo.getMethodCallCLP());

				if (new_op.equals("+") || new_op.equals("-") || new_op.equals("not")) {
					return new CLPInfo(/* "(" + */(leftinfo.getReturnCLP().length() > 0 ? leftinfo.getReturnCLP() : " ")
							+ new_op + rightinfo.getReturnCLP() /* + ")" */, methodcall);
				} else {

					return new CLPInfo(
							/* "(" + */(leftinfo.getReturnCLP().length() > 0 ? leftinfo.getReturnCLP() + new_op : " ")
									+ rightinfo.getReturnCLP() /* + ")" */,
							methodcall);
				}

			} else
				return this.leftOperand.getCLPInfo(variableSet, containKey);
		}
	}

	@Override
	public CLGConstraint clone() {
		String newOp = new String(this.operator);
		CLGConstraint cons = new CLGOperatorNode(newOp);

		if (this.leftOperand != null)
			((CLGOperatorNode) cons).setLeftOperand(this.leftOperand.clone());
		if (this.rightOperand != null)// 我加
			((CLGOperatorNode) cons).setRightOperand(this.rightOperand.clone());
		((CLGOperatorNode) cons).setType(this.type);
		cons.setCloneId(this.getConstraintId());
		if (this.getBoundary())
			((CLGOperatorNode) cons).setBoundary();
		((CLGOperatorNode) cons).setUseClone(this);
		((CLGOperatorNode) cons).setCloneCID(this.getConstraintId());

		if (this.getFromIterateExp())
			((CLGOperatorNode) cons).setFromIterateExp();

		if (this.isAssign == true)
			((CLGOperatorNode) cons).setAssignTrue();
		return cons;
	}

	public String getConstraintImg(HashMap<String, Integer> variableSet) {
		String result = "";
		result += (this.getConstraintId() + " " + String.format(
				"[shape=\"ecllipse\", label=\"%s\",style = \"filled\",fillcolor = \"white\",xlabel=\"[%s]\"]" + "\n",
				this.getOperator(), this.getConstraintId()));
		result += (this.leftOperand.getConstraintId() + " " + String.format(
				"[shape=\"ecllipse\", label=\"%s\",style = \"filled\",fillcolor = \"white\",xlabel=\"[%s]\"]" + "\n",
				this.leftOperand.getImgInfo(), this.leftOperand.getConstraintId()));
		result += (this.rightOperand.getConstraintId() + " " + String.format(
				"[shape=\"ecllipse\", label=\"%s\",style = \"filled\",fillcolor = \"white\",xlabel=\"[%s]\"]" + "\n",
				this.rightOperand.getImgInfo(), this.rightOperand.getConstraintId()));

		result += this.getConstraintId() + "->" + this.leftOperand.getConstraintId() + "\n";
		result += this.getConstraintId() + "->" + this.rightOperand.getConstraintId() + "\n";

		return result;

	}

	@Override
	public String getCLPValue() {// 好像只處理 it = ... 之類的，函式呼叫裡使用的參數好像沒處理
		if (this.leftOperand != null) {
			if (this.rightOperand != null)
				return this.leftOperand.getCLPValue() + this.operator + this.rightOperand.getCLPValue();
			else
				return this.leftOperand.getCLPValue();
		}
		return null;
	}

	@Override
	public void setCLPValue(String data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void renameCLPValue(int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public void negConstraint() {
		this.negation();
		if (this.leftOperand != null)
			this.leftOperand.negConstraint();
		if (this.rightOperand != null)
			this.rightOperand.negConstraint();
	}

	@Override
	public void preconditionAddPre(SymbolTable sym, String methodName) {
		if (this.leftOperand != null)
			this.leftOperand.preconditionAddPre(sym, methodName);
		if (this.rightOperand != null)
			this.rightOperand.preconditionAddPre(sym, methodName);
	}

	@Override
	public String getConstraintName() {

		String leftstr = "";
		String rightstr = "";
		if (this.leftOperand != null)
			leftstr += this.leftOperand.getConstraintName();
		if (this.rightOperand != null)
			rightstr += this.rightOperand.getConstraintName();

		return leftstr + this.operator + rightstr;
	}

	@Override
	public CLGConstraint addPre() {
		if (this.leftOperand != null)
			this.leftOperand = this.leftOperand.addPre();
		if (this.rightOperand != null)
			this.rightOperand = this.rightOperand.addPre();
		return this;
	}

	
	@Override
	public void renameUseVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
		if (this.getOperator().equals("=")) {
			if (!(this.rightOperand instanceof CLGMethodInvocationNode
					&& ((CLGMethodInvocationNode) this.rightOperand).getMethodName().equals("dcl_array"))) {
				if (this.getRightOperand() != null)
					this.getRightOperand().renameUseVar(variableSet, defineVariableSet, isMethodCLP);
				if (this.getLeftOperand() != null)
					this.getLeftOperand().renameDefVar(variableSet, defineVariableSet, isMethodCLP);
			}
		} else {
			if (this.getLeftOperand() != null)
				this.getLeftOperand().renameUseVar(variableSet, defineVariableSet, isMethodCLP);
			if (this.getRightOperand() != null)
				this.getRightOperand().renameUseVar(variableSet, defineVariableSet, isMethodCLP);

		}
	}

	private String changeOperatorForType(String op) {

		String returnOP = op;

		// compare or assign
		if (returnOP.equals("=") || returnOP.equals("\\=") || returnOP.equals(">=") || returnOP.equals("=<")
				|| returnOP.equals(">") || returnOP.equals("<"))

			if (this.type instanceof BooleanType) {
				returnOP = "#" + returnOP;
			} else if (this.type instanceof IntType) {
				returnOP = "#" + returnOP;
			} else if (this.type instanceof FloatType || this.type instanceof DoubleType) {
				returnOP = "$" + returnOP;
			} else if (this.type instanceof StringType) {
				returnOP = op;
			} else {
				returnOP = op;
			}

		return returnOP;
	}

	private CLPInfo stringCompareCLP(String op, CLPInfo leftclpinfo, CLPInfo rightclpinfo,
			HashMap<String, Integer> variableSet) {

		String opCLPString = "";

		switch (op) {
		case "=":
			opCLPString = "ocl_string_equals";
			break;
		case "\\=":
			opCLPString = "ocl_string_not_equals";
			break;
		case ">=":
			opCLPString = "ocl_string_greater_equals";
			break;
		case ">":
			opCLPString = "ocl_string_greater_than";
			break;
		case "=<":
			opCLPString = "ocl_string_less_equals";
			break;
		case "<":
			opCLPString = "ocl_string_less_than";
			break;

		default:
			opCLPString = op;
		}

		boolean isMethodCall = false;
		String leftstr = leftclpinfo.getReturnCLP();
		String rightstr = rightclpinfo.getReturnCLP();

		String returnstr = opCLPString + "(" + leftstr + "," + rightstr + ", 1)";

		ArrayList<String> methodcall = new ArrayList<String>();
		methodcall.addAll(leftclpinfo.getMethodCallCLP());
		methodcall.addAll(rightclpinfo.getMethodCallCLP());

		return new CLPInfo(returnstr, methodcall);
	}

	@Override
	public ArrayList<String> getLocalVar() {
		ArrayList<String> retList = new ArrayList<>();
		if (this.getLeftOperand() != null)
			retList.addAll(this.getLeftOperand().getLocalVar());
		if (this.getRightOperand() != null)
			retList.addAll(this.getRightOperand().getLocalVar());
		return retList;
	}

	@Override
	public VariableType getCLPVarType() {
		VariableType retType;
		retType = this.leftOperand.getCLPVarType();
		if (retType != null)
			return retType;
		else {
			retType = this.rightOperand.getCLPVarType();
			return retType;
		}
	}

	@Override
	public void renameWithMap(HashMap<String, String> attMap) {

		if (this.getLeftOperand() != null)
			this.getLeftOperand().renameWithMap(attMap);
		if (this.getRightOperand() != null)
			this.getRightOperand().renameWithMap(attMap);
	}

	@Override
	public String getOriginalConName() {
		if (this.leftOperand instanceof CLGLiteralNode && this.leftOperand instanceof CLGLiteralNode
				&& ((CLGLiteralNode) this.leftOperand).getValue().contains("acc")
				&& ((CLGLiteralNode) this.leftOperand).getValue().equals("true"))
			return "";
		else {
			String str = "";
			if (this.leftOperand != null)
				str += this.leftOperand.getOriginalConName();

			str += this.operator;

			if (this.rightOperand != null)
				str += this.rightOperand.getOriginalConName();

			return str;
		}
	}

	@Override
	public String getItNum() {
		if (this.leftOperand instanceof CLGObjectNode && this.operator.equals("<=")) {
			String itName = ((CLGObjectNode) this.leftOperand).getName();
			if (itName.equals("it"))
				return "1";
			if (itName.contains("it")) {
				return itName.substring(itName.indexOf("it") + 2);
			}
		}
		return "";
	}

	@Override
	public void reverseVarFlag(HashMap<String, Integer> variableSet) {
		if (this.getOperator().equals("=") && !(this.rightOperand instanceof CLGMethodInvocationNode
				&& ((CLGMethodInvocationNode) this.rightOperand).getMethodName().equals("dcl_array"))) {
			this.getLeftOperand().reverseDefVar(variableSet);
		}
		this.reverseMethodDefVar(variableSet);
	}

	@Override
	protected void reverseMethodDefVar(HashMap<String, Integer> variableSet) {
		if (this.leftOperand != null)
			this.leftOperand.reverseMethodDefVar(variableSet);
		if (this.rightOperand != null)
			this.rightOperand.reverseMethodDefVar(variableSet);
	}
}
