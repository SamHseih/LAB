package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.DataWriter.DataReader;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.exe.main.Main;

public class ThisExpressionVisitor extends JAVA2CLG implements SrcNodeVisit {
	private CLGNode clgNode;
	private CLGConstraint constraint;

	public boolean visit(ThisExpression node) {
		// 找到目前類別並設為該點型態
		String className = JAVA2CLG.symbolTable.getClassName();

		constraint = new CLGObjectNode("self", JAVA2CLG.typeTable.get(className, null));

		return false;
	}

	@Override
	public CLGNode getNode() {
		// TODO Auto-generated method stub
		return clgNode;
	}

	@Override
	public CLGConstraint getConstraint() {
		// TODO Auto-generated method stub
		return this.constraint;
	}

	@Override
	public CLGGraph getCLGGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
