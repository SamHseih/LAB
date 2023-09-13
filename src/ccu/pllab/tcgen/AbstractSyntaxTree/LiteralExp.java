package ccu.pllab.tcgen.AbstractSyntaxTree;

import ccu.pllab.tcgen.ASTGraph.ASTGraphNode;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;

public class LiteralExp extends AbstractSyntaxTreeNode {
	private VariableType type;
	private String value;

	public LiteralExp(VariableType declareType, String declareValue) {// ¦r¦ê¼Æ¦r
		super();
		this.type = declareType;
		this.value = declareValue;

	}

	public VariableType getType() {
		return this.type;
	}

	public String getValue() {
		return this.value;
	}

	/*
	 * @Override public String addOperatorType() { return this.type; }
	 * 
	 * /*@Override public void addAllNodeType(String type){}
	 * 
	 * @Override public void putAttributeToHashMap(HashMap<String,String> table){}
	 * 
	 * @Override public void takeHashMapKeyValue(HashMap<String,String> table){}
	 * 
	 * @Override public void addSelfOrResultType(String className){}
	 */

	@Override
	public void addVariableType(TypeTable typeTable, String methodName) {
	}

	@Override
	public void changeAssignToEqual() {
	}

	@Override
	public void conditionChangeAssignToEqual() {
	}

	@Override
	public String childNodeInfo() {
		return this.value;
	}

	@Override
	public String ASTInformation() {
		return "\"" + "(" + this.getID() + ")" + this.value + "\"";
	}

	@Override
	public CLGConstraint AST2CLG(SymbolTable sym, String methodName, boolean isCondition) {
		return new CLGLiteralNode(this.value, this.type);
	}

//	@Override
//	public String NodeToString() {
//		return this.value;
//	}

	@Override
	public void toGraphViz() {
	}

	@Override
	public String AST2CLP(String attribute, String argument) {
		String obj_pre = "", arg_pre = "";
		if (attribute.length() > 0)
			obj_pre = attribute.replaceAll(",", "_pre,") + "_pre";
		if (argument.length() > 0)
			arg_pre = argument.replaceAll(",", "_pre,") + "_pre";
		if (this.value.contains("'"))
			this.value = "\"" + this.value.replaceAll("'", "") + "\"";
		// return
		// "literal_"+this.getID()+"(["+obj_pre+"],["+arg_pre+"],["+attribute+"],["+argument+"],[Result]):-\n"
		// +"Result="+this.value+".\n\n";
		return "literal_" + this.getID() + "(_,_,_,_," + this.value + ").\n";

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
		LiteralExp literalExp = new LiteralExp(this.type, this.value);
		return literalExp;
	}
}
