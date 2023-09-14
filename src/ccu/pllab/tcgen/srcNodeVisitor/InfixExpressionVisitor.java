package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Expression;
import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.*;

public class InfixExpressionVisitor extends JAVA2CLG implements SrcNodeVisit {
	private CLGNode clgNode;
	private CLGConstraint constraint;
	private CLGGraph clgGraph;

	/****************************************************************/
	@Override
	public boolean visit(InfixExpression node) {
		if (!node.hasExtendedOperands()) {
			CLGConstraint leftOperandCons;
			CLGConstraint operationCons = new CLGOperatorNode(node.getOperator().toString());
			CLGConstraint rightOperandCons;
			/*
			 * LeftOp start to visit
			 */
			leftOperandCons = BasicExpVisitor.VisitorAssign(node.getLeftOperand());
			((CLGOperatorNode) operationCons).setLeftOperand(leftOperandCons);
			// LeftOp finish

			/*
			 * RightOp start to visit
			 */
			rightOperandCons = BasicExpVisitor.VisitorAssign(node.getRightOperand());
			((CLGOperatorNode) operationCons).setRightOperand(rightOperandCons);
			// RightOp finish

			constraint = operationCons;
		} else {// �p�G��extendedOperands(�Ա��i�ݩx����InfixExpression���UextendedOperands����컡��)
			// �@�}�l���Ӥ@��infix��
			String commonOp = node.getOperator().toString();// �@�Ϊ�OP
			CLGConstraint leftOperandCons;
			CLGConstraint operationCons = new CLGOperatorNode(commonOp);
			CLGConstraint rightOperandCons;

			leftOperandCons = BasicExpVisitor.VisitorAssign(node.getLeftOperand());
			((CLGOperatorNode) operationCons).setLeftOperand(leftOperandCons);
			// LeftOp finish

			/*
			 * RightOp start to visit
			 */
			rightOperandCons = BasicExpVisitor.VisitorAssign(node.getRightOperand());
			((CLGOperatorNode) operationCons).setRightOperand(rightOperandCons);
			// RightOp finish

			// �B�zextendedOp����
			for (int i = 0; i < node.extendedOperands().size(); i++) {
				leftOperandCons = operationCons;// �N�B�z�n��CLGNode���leftOp
				operationCons = new CLGOperatorNode(commonOp);// ���ؤ@�ӷs��CLGNode
				((CLGOperatorNode) operationCons).setLeftOperand(leftOperandCons);// ��B�z����쥪��
				rightOperandCons = BasicExpVisitor.VisitorAssign((Expression) node.extendedOperands().get(i));
				((CLGOperatorNode) operationCons).setRightOperand(rightOperandCons);// extendOp���k��
			}

			constraint = operationCons;
		}

		return false;
	}

	/*********************************************************/
	public CLGNode getNode() {
		return clgNode;
	}

	public CLGConstraint getConstraint() {
		return this.constraint;
	}

	public CLGConstraint negationConstraint() {
		CLGConstraint cons = this.getConstraint().clone();
		((CLGOperatorNode) (cons)).negation();
		return cons;
	}

	public CLGGraph getCLGGraph() {
		return this.clgGraph;
	}
}
/* �[�J4�� visitor */