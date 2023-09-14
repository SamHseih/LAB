package ccu.pllab.tcgen.AbstractSyntaxTree;

import ccu.pllab.tcgen.ASTGraph.ASTGraphNode;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;

public abstract class AbstractSyntaxTreeNode {
	private static int node_count = 0;
	private String id;

	public AbstractSyntaxTreeNode() {// 創建一個語法樹
		this.id = "" + node_count++;// 每一個node被給予一個特定的ID,給完Node_count+1,才會唯一
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

	// public abstract CLGConstraint AST2CLG(boolean boundaryAnalysis); ////沒用到

	// public abstract String NodeToString(); //沒用到s

	public abstract void toGraphViz();// 因為每個繼承的物件有不同寫法

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
