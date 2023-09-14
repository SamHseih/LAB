package ccu.pllab.tcgen.AbstractSyntaxTree;

import ccu.pllab.tcgen.AbstractConstraint.CLGClassNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;

public class ClassCallExp extends VariableRefExp {

	public ClassCallExp(String variable, int scopeId) {
		super(variable, scopeId);
		// TODO Auto-generated constructor stub
	}

	public ClassCallExp(String variable, AbstractSyntaxTreeNode source, int scopeId) {
		super(variable, source, scopeId);

	}

	@Override
	public CLGConstraint AST2CLG(SymbolTable sym, String methodName, boolean isCondition) {
		String name = this.getVariable();
		CLGConstraint constraint = null;

		constraint = new CLGClassNode(name, this.getType());

		return constraint;
	}

}
