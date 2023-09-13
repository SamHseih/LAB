package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ReturnStatement;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.srcASTVisitor.SrcVisitorUnit;

public class ReturnStatementVisitor extends JAVA2CLG implements SrcNodeVisit {
	private CLGGraph clgGraph;
	private CLGConstraint constraint;
	private CLGNode clgNode;

	/******************************************************************/
	public boolean visit(ReturnStatement node) {
		CLGConstraint resultConstriant = BasicExpVisitor.VisitorAssign(node.getExpression());

		CLGConstraint returnLeftConstraint = new CLGObjectNode("Result");
		((CLGObjectNode) returnLeftConstraint).setType(new VoidType());
		CLGConstraint returnOpConstraint = new CLGOperatorNode("=");

		((CLGOperatorNode) returnOpConstraint).setLeftOperand(returnLeftConstraint);
		((CLGOperatorNode) returnOpConstraint).setRightOperand(resultConstriant);
		clgGraph = new CLGGraph(returnOpConstraint);
		return false;
	}

	/********************************************************************/
	@Override
	public CLGNode getNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CLGConstraint getConstraint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CLGGraph getCLGGraph() {
		// TODO Auto-generated method stub
		return this.clgGraph;
	}

}
