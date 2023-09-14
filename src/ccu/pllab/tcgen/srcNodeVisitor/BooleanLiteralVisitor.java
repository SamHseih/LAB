package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.StringLiteral;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGLiteralNode;
import ccu.pllab.tcgen.AbstractType.BooleanType;
import ccu.pllab.tcgen.DataWriter.DataReader;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.exe.main.Main;

public class BooleanLiteralVisitor extends JAVA2CLG implements SrcNodeVisit {
	CLGNode clgNode;
	CLGConstraint constraint;

	/*************************************************/
	public boolean visit(BooleanLiteral node) {
		constraint = new CLGLiteralNode(Boolean.toString(node.booleanValue()), new BooleanType());

		return false;
	}

	public CLGConstraint getConstraint() {
		return this.constraint;
	}

	@Override
	public CLGNode getNode() {
		// TODO Auto-generated method stub
		return clgNode;
	}

	@Override
	public CLGGraph getCLGGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
