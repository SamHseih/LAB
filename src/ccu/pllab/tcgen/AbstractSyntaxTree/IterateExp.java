package ccu.pllab.tcgen.AbstractSyntaxTree;

import ccu.pllab.tcgen.ASTGraph.ASTGraphNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGIterateNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGLiteralNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.AbstractType.BooleanType;
import ccu.pllab.tcgen.AbstractType.IntType;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.exe.main.Main;

public class IterateExp extends AbstractSyntaxTreeNode {
	private VariableType type;
	private AbstractSyntaxTreeNode start;
	private AbstractSyntaxTreeNode end;
	private int counting = 1;
	private AbstractSyntaxTreeNode acc;
	// private VariableType acc_type;
	private AbstractSyntaxTreeNode body;
	private String methodName;
	private AbstractSyntaxTreeNode element;
	// private String acc_variable;

	public IterateExp(VariableType type) {
		super();
		this.type = type;
	}

	public void setStart(AbstractSyntaxTreeNode start) {
		this.start = start;
	}

	public void setEnd(AbstractSyntaxTreeNode end) {
		this.end = end;
	}

	public void setBody(AbstractSyntaxTreeNode body) {
		this.body = body;
	}

	public AbstractSyntaxTreeNode getStart() {
		return this.start;
	}

	public AbstractSyntaxTreeNode getEnd() {
		return this.end;
	}

	public AbstractSyntaxTreeNode getBody() {
		return this.body;
	}

	public void setAcc(AbstractSyntaxTreeNode acc) {
		this.acc = acc;
	}

	// setIt(VariableRefExp it) 換過來的
	public void setElement(AbstractSyntaxTreeNode it) {
		this.element = it;
	}

//	public void setAccVariable(String acc) {
//		this.acc_variable = acc;
//	}

//	public void setAccType(VariableType type) {
//		this.acc_type = type;
//	}

//	public VariableType getAccType() {
//		return this.acc_type;
//	}

	@Override
	public String childNodeInfo() {
		return "";
	}

	@Override
	public String ASTInformation() {
		return "\"" + this.getID() + ")" + this.type + "\"";
	}

	@Override
	public CLGConstraint AST2CLG(SymbolTable sym, String methodName, boolean isCondition) {
		CLGIterateNode iterateNode = new CLGIterateNode();
		CLGOperatorNode initial = new CLGOperatorNode("=", true);
		// initial.setLeftOperand(new CLGVariableNode("it_pre","Integer"));
		initial.setLeftOperand(this.element.AST2CLG(sym, methodName, isCondition));
		initial.setRightOperand(this.start.AST2CLG(sym, methodName, isCondition));
		initial.setType(((VariableRefExp) this.element).getType());
		iterateNode.setStart(this.start.AST2CLG(sym, methodName, isCondition));

//		CLGOperatorNode acc = new CLGOperatorNode("=");
//		if (this.acc instanceof IntType) {
//			acc.setLeftOperand(new CLGObjectNode(this.acc_variable, new IntType()));
//			acc.setRightOperand(new CLGLiteralNode(this.acc, new IntType()));
//			acc.setType(new IntType());
//			iterateNode.setAccType(new IntType());
//		} else if (this.acc_type instanceof BooleanType) {
//			acc.setLeftOperand(new CLGObjectNode(this.acc_variable, new BooleanType()));
//			acc.setRightOperand(new CLGLiteralNode(this.acc, new BooleanType()));
//			acc.setType(new BooleanType());
//			iterateNode.setAccType(new BooleanType());
//		}

		CLGOperatorNode acc = (CLGOperatorNode) ((OperatorExp) this.acc).AST2CLG(sym, methodName, isCondition);
		iterateNode.setAccType(acc.getType());

		CLGOperatorNode allInit = new CLGOperatorNode("&&");
		allInit.setLeftOperand(initial);
		allInit.setRightOperand(acc);
		allInit.setType(new BooleanType());
		iterateNode.setInitial(allInit);
		CLGOperatorNode condition = null;
		// if(Main.boundary_analysis)
		/*
		 * if(Main.msort && Main.boundary_analysis) { condition=new
		 * CLGOperatorNode("<"); condition.setLeftOperand(new
		 * CLGVariableNode(this.it+"_pre","Integer")); OperatorExp add=new
		 * OperatorExp("+"); add.setOperand(this.end, new LiteralExp("Integer", "1"));
		 * condition.setRightOperand(add.AST2CLG()); } else {
		 */
		condition = new CLGOperatorNode("<=");
		condition.setLeftOperand(((VariableRefExp) this.element).AST2CLG(sym, methodName, isCondition));
		condition.setRightOperand(this.end.AST2CLG(sym, methodName, isCondition));
		condition.setType(((VariableRefExp) this.element).getType());
		condition.setFromIterateExp();
		// }
		iterateNode.setCondition(condition);
		Main.bodyExpBoundary = true;
		CLGConstraint body = this.body.AST2CLG(sym, methodName, isCondition);
		Main.bodyExpBoundary = false;
		iterateNode.setBody(body);
		CLGOperatorNode increment = new CLGOperatorNode("=", true);
		increment.setLeftOperand(((VariableRefExp) this.element).AST2CLG(sym, methodName, isCondition));
		CLGOperatorNode addone = new CLGOperatorNode("+");
		addone.setLeftOperand(((VariableRefExp) this.element).AST2CLG(sym, methodName, isCondition));
		addone.setRightOperand(new CLGLiteralNode("1", new IntType()));
		addone.setType(((VariableRefExp) this.element).getType());
		increment.setRightOperand(addone);
		increment.setType(addone.getType());
		iterateNode.setIncrement(increment);
		return iterateNode;
	}

//	@Override
//	public String NodeToString() {
//		return "";
//	}

	@Override
	public void toGraphViz() // 因為每個繼承的物件有不同寫法
	{
		String astInformation = this.ASTInformation();// 減少一直呼叫函式
		if (this.start != null) {
			System.out.println(astInformation + "->" + this.start.ASTInformation());
			this.start.toGraphViz();// 遞迴
		}
		if (this.end != null) {
			System.out.println(astInformation + "->" + this.end.ASTInformation());
			this.end.toGraphViz();
		}
		if (this.body != null) {
			System.out.println(astInformation + "->" + this.body.ASTInformation());
			this.body.toGraphViz();
		}
	}

	@Override
	public void addVariableType(TypeTable typeTable, String methodName) {
		this.start.addVariableType(typeTable, methodName);
		this.end.addVariableType(typeTable, methodName);
		this.body.addVariableType(typeTable, methodName);
	}

	@Override
	public void changeAssignToEqual() {
	}

	@Override
	public void conditionChangeAssignToEqual() {
	}

	@Override
	public String AST2CLP(String attribute, String method) {
		String clp = "iterate" + this.getID() + "(Obj_pre,Arg_pre,Obj_post,Arg_post,Result):-\n";
		clp += "apply(Source,[Obj_pre,Obj_post,Abj_pre,Abj_post,Collection]),\n";
		clp += "apply(AccInitExp,[Obj_pre,Obj_post,Abj_pre,Abj_post,AccInitValue]),\n";
		clp += "iterate(Obj_pre,Arg_pre,Obj_post,Arg_post,Result,Collection,AccInitValue,BodyExp).\n";
		clp += "delay iterate(_,_,_,_,_,Collection,_,_) if var(Collection).\n";
		clp += "iterate(Obj_pre,Arg_pre,Obj_post,Arg_post,AccInitValue,Collection,AccInitValue,_):-\n";
		clp += "length(Collecion,0).\n";
		clp += "iterate(Obj_pre,Arg_pre,Obj_post,Arg_post,Result,Collection,AccInitValue,AccIterPredicate):-\n";
		clp += "(\nforeach(Elem,Collection),\n";
		clp += "fromto(AccInitValuePre,AccPre,AccPost,Result),\n";
		clp += "param(Obj_pre,Arg_pre,Obj_post,Arg_post,AccIterPredicate)\n";
		clp += "do\nappend(Arg_pre,Arg_post,[[AccPre,AccPre],[Elem,Elem]],NewArg_pre,NewArg_post),\n";
		clp += "apply(accIterPredicate,[Obj_pre,Obj_post,Arg_pre,Arg_post,AccPost])).\n";
		return clp;
	}

	@Override
	public void preconditionAddPre() {
	}

	@Override
	public void postconditionAddPre() {
	}

	@Override
	public void demoganOperator() {

	}

	@Override
	public String demonganAST2CLP(String attribute, String argument) {
		return "";
	}

	@Override
	public ASTGraphNode AST2ASTGraph() {
		return null;
	}

	@Override
	public AbstractSyntaxTreeNode ASTclone() {

		return null;
	}
}
