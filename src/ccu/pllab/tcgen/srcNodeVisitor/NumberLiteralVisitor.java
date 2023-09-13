package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.NumberLiteral;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class NumberLiteralVisitor extends JAVA2CLG implements SrcNodeVisit {
	CLGNode clgNode;
	CLGConstraint constraint;

	/*************************************************/
	public boolean visit(NumberLiteral node) {
		VariableType nodeType = new VoidType();
		if (Utility.isNumeric(node.getToken()))
			nodeType = new IntType();
		else if (node.getToken().equals("true") || node.getToken().equals("false"))
			nodeType = new BooleanType();
		else
			nodeType = new StringType();

		constraint = new CLGLiteralNode(node.getToken(), nodeType);
		return false;
	}

	@Override
	public CLGNode getNode() {
		// TODO Auto-generated method stub
		return clgNode;
	}

	public CLGConstraint getConstraint() {
		return this.constraint;
	}

	@Override
	public CLGGraph getCLGGraph() {
		// TODO Auto-generated method stub
		return null;
	}
}
