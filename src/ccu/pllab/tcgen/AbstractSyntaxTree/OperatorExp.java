package ccu.pllab.tcgen.AbstractSyntaxTree;

import java.util.ArrayList;

import ccu.pllab.tcgen.ASTGraph.ASTGraphAnd;
import ccu.pllab.tcgen.ASTGraph.ASTGraphNode;
import ccu.pllab.tcgen.ASTGraph.ASTGraphOr;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.ArrayListType;
import ccu.pllab.tcgen.AbstractType.ArrayType;
import ccu.pllab.tcgen.AbstractType.BooleanType;
import ccu.pllab.tcgen.AbstractType.CollectionType;
import ccu.pllab.tcgen.AbstractType.DoubleType;
import ccu.pllab.tcgen.AbstractType.FloatType;
import ccu.pllab.tcgen.AbstractType.IntType;
import ccu.pllab.tcgen.AbstractType.RealType;
import ccu.pllab.tcgen.AbstractType.SequenceType;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.exe.main.Main;

public class OperatorExp extends AbstractSyntaxTreeNode {
	String operator;
	VariableType type = new VoidType();
	VariableType returnType = new VoidType();
	AbstractSyntaxTreeNode leftOperand;
	AbstractSyntaxTreeNode rightOperand;
	boolean isAssign = false;
	VariableType declareType = new VoidType();

	public OperatorExp(String operator) {
		super();
		this.operator = operator;
	}

	public OperatorExp(String operator, boolean isassign) {
		super();
		this.operator = operator;
		this.isAssign = isassign;
	}

	public String getOperator() {
		return this.operator;
	}

	public AbstractSyntaxTreeNode getLeftOperand() {
		return this.leftOperand;
	}

	public AbstractSyntaxTreeNode getRightOperand() {
		return this.rightOperand;
	}

	public void setOperand(AbstractSyntaxTreeNode leftOperand, AbstractSyntaxTreeNode rightOperand) {
		this.leftOperand = leftOperand;
		this.rightOperand = rightOperand;

		VariableType leftType = this.getOperandType(this.leftOperand);
		VariableType rightType = this.getOperandType(this.rightOperand);

		try {
			if (this.leftOperand instanceof ClassCallExp) { // constructor
				this.type = leftType;
				this.returnType = leftType;

			} else {
				TypeInfo typeInfo = checkTheOperatorType(leftType, rightType);
				this.type = typeInfo.type;
				this.returnType = typeInfo.retruntype;
			}

		} catch (OCLSpecTypeErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.declareType = this.type;
	}

	public void setUnaryOperand(AbstractSyntaxTreeNode rightOperand) {
		this.leftOperand = null;
		this.rightOperand = rightOperand;

		this.getOperandType(this.rightOperand);
		VariableType rightType = this.getOperandType(this.rightOperand);

		try {
			TypeInfo typeInfo = checkTheOperatorType(null, rightType);
			this.type = typeInfo.type;
			this.returnType = typeInfo.retruntype;

		} catch (OCLSpecTypeErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		this.declareType = this.type;
	}

	//
	private VariableType getOperandType(AbstractSyntaxTreeNode Operand) {

		VariableType type = new VoidType();

		if (Operand instanceof LiteralExp)
			type = ((LiteralExp) Operand).getType();
		// 可能有ArrayList、Array、Collection、Sequence等型態
		// 待改
		else if (Operand instanceof VariableRefExp) {
			type = ((VariableRefExp) Operand).getType();

			if (type instanceof ArrayType) {
				type = ((ArrayType) type).getNDimType(((VariableRefExp) Operand).getQualifier().size());
			}
			// 可能有ArrayList、Array、Collection、Sequence等型態
		} else if (Operand instanceof MethodCallExp)
			type = ((MethodCallExp) Operand).getReturnType();
		// 可能有ArrayList、Array、Collection、Sequence等型態
		else if (Operand instanceof OperatorExp)
			type = ((OperatorExp) Operand).getReturnType();
		// 可能有ArrayList、Array、Collection、Sequence等型態
		else
			type = new BooleanType();

		return type;
	}

	// 為了 checkTheOperatorType()
	private class TypeInfo {
		VariableType type;
		VariableType retruntype;

		TypeInfo(VariableType arg_type, VariableType arg_retruntype) {
			this.type = arg_type;
			this.retruntype = arg_retruntype;
		}
	}

	// type system -> 給此節點的型態資訊和此節點給其他節點型態資訊
	private TypeInfo checkTheOperatorType(VariableType left, VariableType right) throws OCLSpecTypeErrorException {

		if (this.operator.equals(".") || this.operator.equals("->")) {
			return new TypeInfo(right, right);
		} else if (this.operator.equals("not")) {
			if (right instanceof BooleanType)
				return new TypeInfo(new BooleanType(), new BooleanType());
			else
				throw new OCLSpecTypeErrorException("type error"); // type error
		} else if (this.operator.equals("-") && left == null) {
			if (right instanceof RealType)
				return new TypeInfo(right, right);
			else
				throw new OCLSpecTypeErrorException("type error"); // type error
		} else if (this.operator.equals("+") || this.operator.equals("-") || this.operator.equals("*")) {
			if (left.getClass().equals(right.getClass()))
				return new TypeInfo(left, left);
			else if (left instanceof RealType && right instanceof RealType) {
				VariableType type = typeConversionForOtherOperator(left, right);
				return new TypeInfo(type, type);
			} else
				throw new OCLSpecTypeErrorException("type error"); // type error
		} else if (this.operator.equals("/")) {
			if (left.getClass().equals(right.getClass()))
				if (left instanceof IntType)
					return new TypeInfo(left, new DoubleType());
				else
					return new TypeInfo(left, left);
			else if (left instanceof RealType && right instanceof RealType) {
				VariableType type = typeConversionForOtherOperator(left, right);
				return new TypeInfo(type, type);
			} else
				throw new OCLSpecTypeErrorException("type error"); // type error

		} else if (this.operator.equals("<>") || this.operator.equals(">") || this.operator.equals(">=")
				|| this.operator.equals("<") || this.operator.equals("<=") || this.operator.equals("==")) {// compare or
			// assignValue
			if (left.getClass().equals(right.getClass())) {
				return new TypeInfo(left, new BooleanType());
			} else if (left instanceof RealType && right instanceof RealType) {
				VariableType type = typeConversionForOtherOperator(left, right);
				return new TypeInfo(type, new BooleanType());
			} else
				throw new OCLSpecTypeErrorException("type error"); // type error
		} else if (this.operator.equals("=")) {
			if (left.getClass().equals(right.getClass())) {
				return new TypeInfo(left, new BooleanType());
			} else if (left instanceof RealType && right instanceof RealType) {
				VariableType type = typeConversionForAssign(left, right);
				return new TypeInfo(type, new BooleanType());
			} else {
				throw new OCLSpecTypeErrorException("type error"); // type error
			}

		} else { // and,or
			return new TypeInfo(new BooleanType(), new BooleanType());
		}
	}

	// type system -> typeConversion: double > float > int
	// left 和 right 皆是 realtype
	private VariableType typeConversionForAssign(VariableType left, VariableType right)
			throws OCLSpecTypeErrorException {

		if (left instanceof RealType && right instanceof RealType) {
			if (left instanceof DoubleType) { // double 值域最大
				return left;
			} else if (left instanceof FloatType) { // float 值域次大
				if (right instanceof IntType || right instanceof FloatType)
					return left;
				else { // float a = (double) b;
					throw new OCLSpecTypeErrorException("type error"); // type error
				}
			} else { // int 值域最小
				if (right instanceof IntType)
					return left;
				else // int a = (float) b; 或 int a = (double) b;
					throw new OCLSpecTypeErrorException("type error"); // type error
			}
		} else {
			throw new OCLSpecTypeErrorException("type error"); // type error
		}
	}

	// type system -> typeConversion: double > float > int
	// left 和 right 皆是 realtype
	private VariableType typeConversionForOtherOperator(VariableType left, VariableType right)
			throws OCLSpecTypeErrorException {

		if (left instanceof RealType && right instanceof RealType) {
			if (left instanceof DoubleType) { // Double 值域最大
				return left;

			} else if (left instanceof FloatType) { // Float 值域次大

				if (right instanceof DoubleType) {
					return right;
				} else {
					return left;
				}

			} else { // int 值域最小
				if (right instanceof FloatType || right instanceof DoubleType) {
					return right;
				} else
					return left;
			}
		} else {
			throw new OCLSpecTypeErrorException("type error"); // type error
		}
	}

	public VariableType getType() {
		return this.type;
	}

	public VariableType getReturnType() {
		return this.type;
	}

	public void setDeclareType(VariableType type) {
		this.declareType = type;
	}

	public VariableType getDeclareType() {
		return this.declareType;
	}

	@Override
	public void addVariableType(TypeTable typeTable, String methodName) {
		if (this.leftOperand != null)
			this.leftOperand.addVariableType(typeTable, methodName);
		if (this.rightOperand != null)
			this.rightOperand.addVariableType(typeTable, methodName);
	}

	@Override
	public void changeAssignToEqual() {
		if (this.operator.equals("="))
			this.operator = "==";
		else {
			if (this.leftOperand != null)
				this.leftOperand.changeAssignToEqual();
			if (this.rightOperand != null)
				this.rightOperand.changeAssignToEqual();
		}
	}

	@Override
	public void conditionChangeAssignToEqual() {
		if (this.leftOperand != null)
			this.leftOperand.conditionChangeAssignToEqual();
		if (this.rightOperand != null)
			this.rightOperand.conditionChangeAssignToEqual();
	}

	@Override
	public String childNodeInfo() {
		String info = "";
		if (this.leftOperand != null)
			info += this.leftOperand.childNodeInfo();
		info += this.operator;
		if (this.rightOperand != null)
			info += this.rightOperand.childNodeInfo();
		return info;
	}

	@Override
	public String ASTInformation() {
		return "\"" + "(" + this.getID() + ")" + this.operator + "\"";
	}

	@Override
	public CLGConstraint AST2CLG(SymbolTable sym, String methodName, boolean isCondition) {
		CLGConstraint constraint = null;
		if (this.operator.equals(".")) {
			constraint = this.leftOperand.AST2CLG(sym, methodName, isCondition);
			this.rightOperand.setIsQualifiedName(true);//紀錄為限制詞的接續(foo.att的屬性部份)，在variableRefExp/AST2CLG會使用
			CLGConstraint right2CLG = this.rightOperand.AST2CLG(sym, methodName, isCondition);
			// 類別.方法 或物件.方法時，另外在methodInvocationNode存入呼叫的物件與物件型態

			// 左邊為self時，將CLGObjectNode的name加入self
			if (constraint instanceof CLGObjectNode && right2CLG instanceof CLGObjectNode
					&& (((CLGObjectNode) constraint).getName().equals("this")
							|| ((CLGObjectNode) constraint).getName().equals("self"))) {
				/*
				 * e.g. self.foo@pre =>Self_foo_pre(X =>Self_pre_foo(O
				 */
				boolean rightIsPre = false;
				if (this.rightOperand instanceof PropertyCallExp) {
					rightIsPre = ((PropertyCallExp) this.rightOperand).getTimeExpression();
					((PropertyCallExp) this.rightOperand).setTimeExpression(false);
				}
				right2CLG = this.rightOperand.AST2CLG(sym, methodName, isCondition);
				((CLGObjectNode) right2CLG).setSelf(rightIsPre);
				constraint = right2CLG;
			} else if (right2CLG instanceof CLGMethodInvocationNode) {
				if (this.leftOperand instanceof PropertyCallExp) {
					// 左邊
					((CLGMethodInvocationNode) right2CLG)
							.setMethodObject((CLGVariableNode) this.leftOperand.AST2CLG(sym, methodName, isCondition));
					((CLGMethodInvocationNode) right2CLG).setIsNewObj(((PropertyCallExp) this.leftOperand).isNewObj());
				} else {
					// 左邊
					((CLGMethodInvocationNode) right2CLG)
							.setMethodObject(this.leftOperand.AST2CLG(sym, methodName, isCondition));
				}
			}
			((CLGVariableNode) right2CLG).setConstraint(constraint);

			return right2CLG;
		} else if (this.operator.equals("->")) {
			// 不知道在做什麼的QQ
//			PropertyCallExp right = (PropertyCallExp) this.rightOperand;
//			
//			//
//			if (right.getVariable().contains("msort")) {
//				return null;
//			}
//			/*
//			 * else if(right.getVariable().contains("size")) {
//			 * right.setVariable("size_pre"); }
//			 */
//			PropertyCallExp left = (PropertyCallExp) this.leftOperand;
//			String pre = "";
//			if (left.getTimeExpression())
//				pre = "pre";
//			// 為什麼要String??
//			constraint = new CLGObjectNode(left.getVariable() + pre + right.getVariable() + "_pre", new StringType());

			constraint = this.leftOperand.AST2CLG(sym, methodName, isCondition);
			CLGConstraint right2CLG = this.rightOperand.AST2CLG(sym, methodName, isCondition);
			// 類別.方法 或物件.方法時，另外在methodInvocationNode存入呼叫的物件與物件型態
			if (right2CLG instanceof CLGMethodInvocationNode) {
				if (this.leftOperand instanceof PropertyCallExp) {
					// 左邊
					((CLGMethodInvocationNode) right2CLG)
							.setMethodObject((CLGObjectNode) this.leftOperand.AST2CLG(sym, methodName, isCondition));
					((CLGMethodInvocationNode) right2CLG).setIsNewObj(((PropertyCallExp) this.leftOperand).isNewObj());
				} else {
					// 左邊
					((CLGMethodInvocationNode) right2CLG)
							.setMethodObject(this.leftOperand.AST2CLG(sym, methodName, isCondition));
				}
			}
			((CLGVariableNode) right2CLG).setConstraint(constraint);
			return right2CLG;
		} else {

			constraint = new CLGOperatorNode(this.operator, this.isAssign);
			if (this.operator.equals("=") && isCondition)
				constraint = new CLGOperatorNode("==");
			else
				constraint = new CLGOperatorNode(this.operator);
			if (Main.bodyExpBoundary)
				((CLGOperatorNode) constraint).setBoundary();
			((CLGOperatorNode) constraint).setType(this.declareType);
			if (this.leftOperand != null) {
				if (this.leftOperand.AST2CLG(sym, methodName, isCondition) == null)
					return this.rightOperand.AST2CLG(sym, methodName, isCondition);
				((CLGOperatorNode) constraint).setLeftOperand(this.leftOperand.AST2CLG(sym, methodName, isCondition));
			}

			if (this.rightOperand != null) {
				((CLGOperatorNode) constraint).setRightOperand(this.rightOperand.AST2CLG(sym, methodName, isCondition));
			}
		}
		return constraint;
	}

//	@Override
//	public String NodeToString() {
//		String name = "";
//		if (this.leftOperand != null)
//			name += this.leftOperand.NodeToString();
//		name += this.operator;
//		name += this.rightOperand.NodeToString();
//		return name;
//	}

	@Override
	public void toGraphViz() {// 一個遞迴函式，來畫AST圖
		String astInformation = this.ASTInformation();// 減少一直呼叫函式
		if (this.leftOperand != null) {
			System.out.println(astInformation + "->" + this.leftOperand.ASTInformation());
			this.leftOperand.toGraphViz();// 遞迴
		}
		if (this.rightOperand != null) {
			System.out.println(astInformation + "->" + this.rightOperand.ASTInformation());
			this.rightOperand.toGraphViz();
		}
	}

	@Override
	public String AST2CLP(String attribute, String argument) {

		String obj_pre = "", arg_pre = "";
		if (attribute.length() > 0)
			obj_pre = attribute.replaceAll(",", "_pre,") + "_pre";
		if (argument.length() > 0)
			arg_pre = argument.replaceAll(",", "_pre,") + "_pre";
		String clp = "operator_" + this.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,Result):-\n";
		switch (this.operator) {
		case "and":
		case "or":
			if (this.leftOperand instanceof OperatorExp)
				clp += "operator_";
			else if (this.leftOperand instanceof PropertyCallExp)
				clp += "variable_";
			else {
				clp += "literal_";
			}
			clp += this.leftOperand.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,[Left]),\n";
			if (this.rightOperand instanceof OperatorExp)
				clp += "operator_";
			else if (this.rightOperand instanceof PropertyCallExp)
				clp += "variable_";
			else {
				clp += "literal_";
			}
			clp += this.rightOperand.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,[Right]).\n\n";
			break;
		case "+":
		case "-":
		case "*":
		case "/":
			if (this.leftOperand instanceof OperatorExp)
				clp += "operator_";
			else if (this.leftOperand instanceof PropertyCallExp)
				clp += "variable_";
			else {
				clp += "literal_";
			}
			clp += this.leftOperand.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,[Left]),\n";
			if (this.rightOperand instanceof OperatorExp)
				clp += "operator_";
			else if (this.rightOperand instanceof PropertyCallExp)
				clp += "variable_";
			else {
				clp += "literal_";
			}
			clp += this.rightOperand.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,[Right]),\n";
			clp += "Result=Left" + this.operator + "Right.\n\n";
		case "=":
			clp += "variable_" + this.leftOperand.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,[Left]),\n";
			if (this.rightOperand instanceof OperatorExp)
				clp += "operator_";
			else if (this.rightOperand instanceof PropertyCallExp)
				clp += "variable_";
			else {
				clp += "literal_";
			}
			clp += this.rightOperand.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,[Right]),\n";
			if (((PropertyCallExp) this.leftOperand).getVariable().equals("result"))
				clp += "Left=Right,\nResult=Left.\n\n";
			else
				clp += "Left=Right.\n\n";
			break;
		case "==":
		case "<>":
		case ">":
		case ">=":
		case "<":
		case "<=":
			if (this.leftOperand instanceof OperatorExp)
				clp += "operator_";
			else if (this.leftOperand instanceof PropertyCallExp)
				clp += "variable_";
			else {
				clp += "literal_";
			}
			clp += this.leftOperand.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,[Left]),\n";
			if (this.rightOperand instanceof OperatorExp)
				clp += "operator_";
			else if (this.rightOperand instanceof PropertyCallExp)
				clp += "variable_";
			else {
				clp += "literal_";
			}
			clp += this.rightOperand.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,[Right]),\n";

			if (this.operator.equals("<="))
				clp += "Left#=<Right.\n\n";
			else if (this.operator.equals("<>"))
				clp += "Left=/=Right.\n\n";
			else
				clp += "Left#" + this.operator + "Right.\n\n";
			break;
		}
		clp += this.leftOperand.AST2CLP(attribute, argument);
		clp += this.rightOperand.AST2CLP(attribute, argument);
		return clp;
	}

	@Override
	public void preconditionAddPre() {
		if (this.leftOperand != null)
			this.leftOperand.preconditionAddPre();
		if (this.rightOperand != null)
			this.rightOperand.preconditionAddPre();
	}

	@Override
	public void postconditionAddPre() {
		if (this.operator.equals("||") || this.operator.equals("&&")) {
			// System.out.println("testOperator:"+this.operator);
			// if(this.leftOperand!=null)
			this.leftOperand.postconditionAddPre();
			// if(this.rightOperand!=null)
			this.rightOperand.postconditionAddPre();
		} else if (this.operator.equals("=")) {
			this.addpostpre(this.rightOperand);
		}
	}

	public void addpostpre(AbstractSyntaxTreeNode node) {
		if (node instanceof OperatorExp) {
			addpostpre(((OperatorExp) node).getLeftOperand());
			addpostpre(((OperatorExp) node).getRightOperand());
		} else if (node instanceof PropertyCallExp) {
			((PropertyCallExp) node).postconditionAddPre();
			;
		}
	}

	@Override
	public void demoganOperator() {
		switch (this.operator) {
		case "and":
			this.operator = "or";
			break;
		case "or":
			this.operator = "and";
			break;
		case "==":
			this.operator = "<>";
			break;
		case ">=":
			this.operator = "<";
			break;
		case "<=":
			this.operator = ">";
			break;
		case ">":
			this.operator = "=<";
			break;
		case "<":
			this.operator = ">=";
			break;
		default:
			break;
		}
		this.leftOperand.demoganOperator();
		this.rightOperand.demoganOperator();
	}

	@Override
	public String demonganAST2CLP(String attribute, String argument) {
		String obj_pre = "", arg_pre = "", clp = "";
		/*
		 * if(attribute.length()>0) obj_pre=attribute.replaceAll(",", "_pre,")+"_pre";
		 * if(argument.length()>0) arg_pre=argument.replaceAll(",", "_pre,")+"_pre";
		 * String
		 * clp="operator_"+this.getID()+"_2(["+obj_pre+"],["+arg_pre+"],["+attribute+
		 * "],["+argument+"],[Result]):-\n"; switch (this.operator) { case "and": case
		 * "or": break; case "+": case "-": case "*": case "/":
		 * 
		 * case "=":
		 * 
		 * break; case "==": case "<>": case ">": case ">=": case "<": case "<=":
		 */

		return clp;
	}

	@Override
	public ASTGraphNode AST2ASTGraph() {
		ASTGraphNode connect = null;
		if (this.operator.equals("and")) {
			connect = new ASTGraphAnd();
			if ((this.leftOperand instanceof OperatorExp) && (!((OperatorExp) this.leftOperand).equals("and")
					&& !((OperatorExp) this.leftOperand).equals("or"))) {
				connect.addOperand(this.leftOperand);
			}
			if ((this.rightOperand instanceof OperatorExp) && (!((OperatorExp) this.rightOperand).equals("and")
					&& !((OperatorExp) this.rightOperand).equals("or"))) {
				connect.addOperand(this.rightOperand);
			}
			connect.setLeft(this.leftOperand.AST2ASTGraph());
			connect.setRight(this.rightOperand.AST2ASTGraph());
		} else if (this.operator.equals("and")) {
			connect = new ASTGraphOr();
			connect.setLeft(this.leftOperand.AST2ASTGraph());
			connect.setRight(this.rightOperand.AST2ASTGraph());
		}
		return connect;
	}

	@Override
	public AbstractSyntaxTreeNode ASTclone() {
		OperatorExp oper = new OperatorExp(this.operator, this.isAssign);
		oper.setOperand(this.leftOperand.ASTclone(), this.rightOperand.ASTclone());
		return oper;
	}

	@Override
	public void setIsQualifiedName(boolean isQualifiedName) {
		this.leftOperand.setIsQualifiedName(isQualifiedName);
		this.rightOperand.setIsQualifiedName(isQualifiedName);
	}
}
