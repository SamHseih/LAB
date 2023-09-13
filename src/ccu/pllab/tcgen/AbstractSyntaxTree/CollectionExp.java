package ccu.pllab.tcgen.AbstractSyntaxTree;

import ccu.pllab.tcgen.ASTGraph.ASTGraphNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.exe.main.Main;

public class CollectionExp extends AbstractSyntaxTreeNode {
	private VariableType type;
	private AbstractSyntaxTreeNode start;
	private AbstractSyntaxTreeNode end;
	private int counting = 1;
	private AbstractSyntaxTreeNode acc;
	private VariableType acc_type;
	private AbstractSyntaxTreeNode body;
	private String collectionName;
	private String methodName;

	public CollectionExp(VariableType type) {
		super();
		this.type = Main.typeTable.get(type.getType(), null);
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

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public VariableType getType() {
		return this.type;
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

	public void setAccType(VariableType type) {
		this.acc_type = type;
	}

	public VariableType getAccType() {
		return this.acc_type;
	}

	@Override
	public String childNodeInfo() {
		return "";
	}

	@Override
	public String ASTInformation() {
		String method = "";
		if (this.methodName.length() > 0)
			method = "." + this.methodName;
		return "\"" + this.getID() + ")" + this.type + method + "\"";
	}

	@Override
	public CLGConstraint AST2CLG(SymbolTable sym, String methodName, boolean isCondition) {
		CLGConstraint constraint = null;
		if (this.methodName.length() > 0) {
			if (this.methodName.equals("size()")) {
				// 為什麼要string ??
				constraint = new CLGObjectNode("size_pre", new StringType());
			}
		}

		return constraint;
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
		return "";
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
