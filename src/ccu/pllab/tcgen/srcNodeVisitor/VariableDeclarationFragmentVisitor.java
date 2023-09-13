package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGLiteralNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;

public class VariableDeclarationFragmentVisitor extends JAVA2CLG implements SrcNodeVisit {
	CLGNode clgNode;
	CLGGraph clgGraph;
	CLGConstraint constraint;

	public boolean visit(VariableDeclarationFragment node) {
		System.out.println("node.getInitializer() " + node.getInitializer());
		CLGConstraint cons = new CLGOperatorNode("=");
		CLGConstraint leftOperand = BasicExpVisitor.VisitorAssign(node.getName());
		CLGConstraint rightOperand = null;
		if (node.getInitializer() != null) {
			rightOperand = BasicExpVisitor.VisitorAssign(node.getInitializer());
		}
		((CLGOperatorNode) cons).setLeftOperand(leftOperand);
		((CLGOperatorNode) cons).setRightOperand(rightOperand);

		constraint = cons;
		return false;
	}

	/*************************************************/
	@Override
	public CLGNode getNode() {
		return null;
	}

	@Override
	public CLGConstraint getConstraint() {
		return constraint;
	}

	@Override
	public CLGGraph getCLGGraph() {
		return null;
	}

}
