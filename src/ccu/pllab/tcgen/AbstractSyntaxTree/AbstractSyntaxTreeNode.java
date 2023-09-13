package ccu.pllab.tcgen.AbstractSyntaxTree;

import ccu.pllab.tcgen.ASTGraph.ASTGraphNode;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;

public abstract class AbstractSyntaxTreeNode {
	private static int node_count = 0;
	private String id;

	public AbstractSyntaxTreeNode() {// �Ыؤ@�ӻy�k��
		this.id = "" + node_count++;// �C�@��node�Q�����@�ӯS�w��ID,����Node_count+1,�~�|�ߤ@
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return this.id;
	}

	// 20200916 dai
	public static void reset() {
		node_count = 0;
	}

	public abstract String childNodeInfo();

	public abstract String ASTInformation();

	public abstract CLGConstraint AST2CLG(SymbolTable sym, String methodName, boolean isCondition);

	// public abstract CLGConstraint AST2CLG(boolean boundaryAnalysis); ////�S�Ψ�

	// public abstract String NodeToString(); //�S�Ψ�s

	public abstract void toGraphViz();// �]���C���~�Ӫ����󦳤��P�g�k

	public abstract void addVariableType(TypeTable typeTable, String methodName);

	public abstract void changeAssignToEqual();

	public abstract void conditionChangeAssignToEqual();

	public abstract String AST2CLP(String attribute, String argument);

	public abstract String demonganAST2CLP(String attribute, String argument);

	public abstract void demoganOperator();

	public abstract void preconditionAddPre();

	public abstract void postconditionAddPre();

	public abstract ASTGraphNode AST2ASTGraph();

	public abstract AbstractSyntaxTreeNode ASTclone();

	public void setIsQualifiedName(boolean isQualifiedName) {
	}
}
