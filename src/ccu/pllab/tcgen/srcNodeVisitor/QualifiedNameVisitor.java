package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.ThisExpression;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataReader;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.exe.main.Main;

public class QualifiedNameVisitor extends JAVA2CLG implements SrcNodeVisit {
	private CLGNode clgNode;
	private CLGConstraint constraint;

	public boolean visit(QualifiedName node) {
		CLGConstraint qualifierOperandCons, nameOperandCons;

		// qualifierOp start to visit
		qualifierOperandCons = BasicExpVisitor.VisitorAssign(node.getQualifier());
		// qualifierOp finish

		// nameOp start to visit
		nameOperandCons = BasicExpVisitor.VisitorAssign(node.getName());

		((CLGVariableNode) qualifierOperandCons).setConstraint(nameOperandCons);
		// NameOp finish

		constraint = qualifierOperandCons;

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
