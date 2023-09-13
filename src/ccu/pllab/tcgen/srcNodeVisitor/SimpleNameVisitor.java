package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.SimpleName;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class SimpleNameVisitor extends JAVA2CLG implements SrcNodeVisit {
	private CLGNode clgNode;
	private CLGConstraint constraint;

	/*************************************************/
	public boolean visit(SimpleName node) {
		constraint = new CLGObjectNode(node.getIdentifier().toString(),
				Utility.getObjType(node.getIdentifier().toString(), JAVA2CLG.visitingMethod, JAVA2CLG.symbolTable,
						JAVA2CLG.typeTable, false));
		// if the constraint is attribute
		if (Utility.isAtt(node.getIdentifier().toString(), JAVA2CLG.symbolTable, JAVA2CLG.visitingMethod))
			((CLGObjectNode) constraint).setSelf(true);
		return false;
	}

	@Override
	public CLGNode getNode() {
		// TODO Auto-generated method stub
		return clgNode;
	}

	@Override
	public CLGConstraint getConstraint() {
		return this.constraint;
	}

	@Override
	public CLGGraph getCLGGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
