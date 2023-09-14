package ccu.pllab.tcgen.srcNodeVisitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.Assignment;
import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.transform.CLG2Path;

public class AssignmentVisitor extends JAVA2CLG implements SrcNodeVisit {

	CLGConstraint constraint;
	CLGNode clgNode;
	CLGGraph clgGraph;

	/*************************************************/
	public boolean visit(Assignment node) {
		CLGConstraint leftVar;
		CLGConstraint operator = new CLGOperatorNode(node.getOperator().toString());
		CLGConstraint rightVar;

		/********************************************************************/
		leftVar = BasicExpVisitor.VisitorAssign(node.getLeftHandSide());

		// array create need to call "array"
		if (node.getRightHandSide() instanceof ArrayCreation) {
			if (leftVar instanceof CLGVariableNode) {
				ArrayCreationVisitor arrayCreationVisitor = new ArrayCreationVisitor();
				node.getRightHandSide().accept(arrayCreationVisitor);

				((CLGVariableNode) leftVar).setType(arrayCreationVisitor.getDclType());
				// create array(reserved method needn't methodObject
				ArrayList<CLGConstraint> arrayCreationArg = new ArrayList<>();
				arrayCreationArg.add(leftVar);// array name
				arrayCreationArg.add(arrayCreationVisitor.getDimNode());// array dimension
				arrayCreationArg.add(new CLGLiteralNode(arrayCreationVisitor.getDclType().getType(), new StringType()));// array
																														// type
//				this.constraint = new CLGMethodInvocationNode(new CLGObjectNode("", new VoidType()),
//						"arrayCreation", arrayCreationArg);
			}
		}
		// not array create
		else {
			rightVar = BasicExpVisitor.VisitorAssign(node.getRightHandSide());
			((CLGOperatorNode) operator).setLeftOperand(leftVar);
			((CLGOperatorNode) operator).setRightOperand(rightVar);
			this.constraint = operator;
		}
		clgGraph = new CLGGraph(this.constraint);
		return false;
	}

	/********************************************************************************/
	@Override
	public CLGNode getNode() {
		// TODO Auto-generated method stub
		return this.clgNode;
	}

	@Override
	public CLGConstraint getConstraint() {
		// TODO Auto-generated method stub
		return this.constraint;
	}

	public CLGGraph getCLGGraph() {
		return this.clgGraph;
	}

}
