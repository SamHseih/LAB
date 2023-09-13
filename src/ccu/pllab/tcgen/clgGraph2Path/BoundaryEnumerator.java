package ccu.pllab.tcgen.clgGraph2Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.print.attribute.standard.MediaName;

import ccu.pllab.tcgen.AbstractCLG.CLGConstraintNode;
import ccu.pllab.tcgen.AbstractCLG.CLGEdge;
import ccu.pllab.tcgen.AbstractCLG.CLGEndNode;
import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.exe.main.Main;

public class BoundaryEnumerator {

	public BoundaryEnumerator() {
	}

	@SuppressWarnings("unlikely-arg-type")
	public void init(CLGPath path) {
		boundaryPath = path;
		boundaryConstraints = new HashSet<>();
		op = " ";
		current_constraint = null;
		boundaryCons = new ArrayList<CLGOperatorNode>();
		for (CLGConstraint cons : boundaryPath.getCons()) {
			if (cons instanceof CLGOperatorNode) {
				flattenBoundaryConstraint((CLGOperatorNode) cons);
			}
		}
	}

	private HashSet<CLGOperatorNode> boundaryConstraints;
	private ArrayList<CLGOperatorNode> boundaryCons;
	private String op;
	private CLGOperatorNode current_constraint;
	private CLGPath boundaryPath;

	// private HashSet<CLGOperatorNode> tempboundaryConstraints ;
	private void flattenBoundaryConstraint(CLGOperatorNode constraint) {

		if (constraint.getOperator().equals(">=") || constraint.getOperator().equals("<=")) {
			boundaryCons.add(constraint);
		}

		if (constraint.getLeftOperand() instanceof CLGOperatorNode) {
			flattenBoundaryConstraint((CLGOperatorNode) constraint.getLeftOperand());
			if (constraint.getRightOperand() instanceof CLGOperatorNode) {
				flattenBoundaryConstraint((CLGOperatorNode) constraint.getRightOperand());
			}

		}

		else if (constraint.getRightOperand() instanceof CLGOperatorNode) {

			if (constraint.getLeftOperand() instanceof CLGOperatorNode) {
				flattenBoundaryConstraint((CLGOperatorNode) constraint.getLeftOperand());
			}
			flattenBoundaryConstraint((CLGOperatorNode) constraint.getRightOperand());

		}
	}

	public ArrayList<CLGOperatorNode> getBoundaryCons() {
		return boundaryCons;
	}

	public CLGPath next() {
		// bConstraints�OboundaryPath����i���I
		if (boundaryCons.isEmpty()) {
			System.out.println("There is no more boundary path !!");
			return null;
		}

		if (current_constraint == null) {
			for (CLGOperatorNode c : boundaryCons) {
				if (c.getOperator().equals("<=") && !c.getFromIterateExp())
					c.setOperator("<");
				if (c.getOperator().equals(">=") && !c.getFromIterateExp())
					c.setOperator(">");
				op = "1";
				current_constraint = c;
			}
			return boundaryPath;
		}

		if (op == "1" && boundaryCons.size() > 0) {

			for (CLGOperatorNode c : boundaryCons) {
				if (c.getOperator().equals("<") && !c.getFromIterateExp())
					c.setOperator("<=");
				if (c.getOperator().equals(">") && !c.getFromIterateExp())
					c.setOperator(">=");
			}
			op = null;
		}

		if (current_constraint != null && op != null) {
			current_constraint.setOperator(op);
			boundaryCons.remove(current_constraint);
		}

		for (CLGOperatorNode c : boundaryCons) {
			if (!c.getFromIterateExp()) {
				switch (((CLGOperatorNode) c).getOperator()) {
				case ">=":

					op = ((CLGOperatorNode) c).getOperator();

					((CLGOperatorNode) c).setOperator("==");
					current_constraint = (CLGOperatorNode) c;

					return boundaryPath;
				case "<=":

					op = ((CLGOperatorNode) c).getOperator();
					((CLGOperatorNode) c).setOperator("==");

					current_constraint = (CLGOperatorNode) c;
					return boundaryPath;
				default:
					current_constraint = (CLGOperatorNode) c;

					break;
				}
			}
		}

		return null;
	}

//	�^����ɭȤ��R���W�[���|�ơA�Ȭ�n+1��
//	for���|�Y��
	public int getNumOfBoundaryConstraints() {
		int num = this.boundaryConstraints.size();
		return num + 1;
	}
}
