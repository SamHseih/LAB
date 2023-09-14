package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.ThisExpression;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataReader;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.exe.main.Main;

public class FieldAccessVisitor extends JAVA2CLG implements SrcNodeVisit {
	private CLGNode clgNode;
	private CLGConstraint constraint;
	private CLGGraph clgGraph;

	public boolean visit(FieldAccess node) {
		CLGConstraint expOperandCons = new CLGObjectNode(node.getExpression().toString());
		CLGConstraint nameOperandCons = new CLGObjectNode(node.getName().toString());

		if (node.getExpression() instanceof ThisExpression) {
			nameOperandCons = BasicExpVisitor.VisitorAssign(node.getName());
			if (nameOperandCons instanceof CLGObjectNode) {
				((CLGObjectNode) nameOperandCons).setSelf(true);
			}
			constraint = nameOperandCons;
		} else {
			// expOp start to visit
			expOperandCons = BasicExpVisitor.VisitorAssign(node.getExpression());
			// expOp finish

			// nameOp start to visit
			nameOperandCons = BasicExpVisitor.VisitorAssign(node.getName());
			((CLGVariableNode) expOperandCons).setConstraint(nameOperandCons);
			// NameOp finish
			constraint = expOperandCons;
		}

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
		return this.clgGraph;
	}

}
