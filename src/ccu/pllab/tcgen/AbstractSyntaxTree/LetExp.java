package ccu.pllab.tcgen.AbstractSyntaxTree;

import ccu.pllab.tcgen.ASTGraph.ASTGraphNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGLetNode;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.ast.ASTNode;

public class LetExp extends AbstractSyntaxTreeNode {

	private String letExp;
	private AbstractSyntaxTreeNode var_assign; // Let var:integer = exp
	private AbstractSyntaxTreeNode ocl_exp; // in exp

	public LetExp(AbstractSyntaxTreeNode var_assign, AbstractSyntaxTreeNode ocl_exp) {
		super();
		this.letExp = "let";
		this.var_assign = var_assign;
		this.ocl_exp = ocl_exp;
	}

	public LetExp(LetExp letAstNode) {
		super();
		this.letExp = "let";
		this.var_assign = letAstNode.var_assign;
		this.ocl_exp = letAstNode.ocl_exp;
	}

	@Override
	public String childNodeInfo() {
		return "";
	}

	@Override
	public String ASTInformation() {
		return "\"" + "(" + this.getID() + ")" + this.letExp + "\"";
	}

	@Override
	public CLGConstraint AST2CLG(SymbolTable sym, String methodName, boolean isCondition) {

		CLGConstraint var_assignConstraint = this.var_assign.AST2CLG(sym, methodName, isCondition);
		CLGConstraint ocl_expConstraint = this.ocl_exp.AST2CLG(sym, methodName, isCondition);

		return new CLGLetNode(var_assignConstraint, ocl_expConstraint);
	}

//	@Override
//	public String NodeToString() {
//		return "";
//	}

	@Override
	public void toGraphViz() {
		this.var_assign.toGraphViz();
		System.out.println(this.ASTInformation() + "->" + this.var_assign.ASTInformation());
		this.ocl_exp.toGraphViz();
		System.out.println(this.ASTInformation() + "->" + this.ocl_exp.ASTInformation());
	}

	@Override
	public void addVariableType(TypeTable typeTable, String methodName) {

		this.var_assign.addVariableType(typeTable, methodName);
		this.ocl_exp.addVariableType(typeTable, methodName);
	}

	@Override
	public void changeAssignToEqual() {

		this.var_assign.changeAssignToEqual();
		this.ocl_exp.changeAssignToEqual();
	}

	@Override
	public void conditionChangeAssignToEqual() {

		this.var_assign.conditionChangeAssignToEqual();
		this.ocl_exp.conditionChangeAssignToEqual();
	}

	@Override
	public void demoganOperator() {
		this.var_assign.demoganOperator();
		this.ocl_exp.demoganOperator();

	}

	@Override
	public void preconditionAddPre() {
		this.var_assign.preconditionAddPre();
		this.ocl_exp.preconditionAddPre();

	}

	@Override
	public void postconditionAddPre() {
		this.var_assign.postconditionAddPre();
		this.ocl_exp.postconditionAddPre();
	}

//	@Override
//	public ASTGraphNode AST2ASTGraph() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public AbstractSyntaxTreeNode ASTclone() {

		return new LetExp(this);
	}

	@Override
	public String AST2CLP(String attribute, String argument) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String demonganAST2CLP(String attribute, String argument) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public ASTGraphNode AST2ASTGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
