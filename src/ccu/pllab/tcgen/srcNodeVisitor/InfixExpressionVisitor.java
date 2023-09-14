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
		} else {// 如果有extendedOperands(詳情可看官方文件InfixExpression底下extendedOperands的欄位說明)
			// 一開始先照一般infix做
			String commonOp = node.getOperator().toString();// 共用的OP
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

			// 處理extendedOp部份
			for (int i = 0; i < node.extendedOperands().size(); i++) {
				leftOperandCons = operationCons;// 將處理好的CLGNode丟到leftOp
				operationCons = new CLGOperatorNode(commonOp);// 重建一個新的CLGNode
				((CLGOperatorNode) operationCons).setLeftOperand(leftOperandCons);// 剛處理的丟到左邊
				rightOperandCons = BasicExpVisitor.VisitorAssign((Expression) node.extendedOperands().get(i));
				((CLGOperatorNode) operationCons).setRightOperand(rightOperandCons);// extendOp丟到右邊
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
/* 加入4種 visitor */