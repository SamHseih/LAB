package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;

import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

public class CLGIfNode extends CLGConstraint {
	private CLGConstraint condition;
	private CLGConstraint then;
	private CLGConstraint elseExp;
	// private CLGConstraint noncondition;
	// private CLGOperatorNode and;

	public CLGIfNode(CLGConstraint condition, CLGConstraint then, CLGConstraint elseExp) {
		// TODO Auto-generated constructor stub
		super();
		this.condition = condition;
		this.then = then;
		this.elseExp = elseExp;
		// this.noncondition = Demongan(condition);
	}

	public CLGIfNode(CLGConstraint condition, CLGConstraint then) {
		// TODO Auto-generated constructor stub
		super();
		this.condition = condition;
		this.then = then;
		// this.noncondition = condition;
	}

	public CLGConstraint getCondition() {
		return this.condition;
	}

	public CLGConstraint getThen() {
		return this.then;
	}

	public CLGConstraint getElse() {
		return this.elseExp;
	}

//	public CLGConstraint getNoncondition() {
//		return this.noncondition;
//	}

	@Override
	public String getImgInfo() {
		return this.getCLPInfo(new HashMap<>(), new HashMap<String, Boolean>()).getReturnCLP();
	}

	@Override
	public CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey) {
		return new CLPInfo("", new ArrayList<String>());
	}

	public CLGConstraint clone() {
		if (this.elseExp != null)
			return new CLGIfNode(this.condition.clone(), this.then.clone(), this.elseExp.clone());
		else
			return new CLGIfNode(this.condition.clone(), this.then.clone());
	}

	public String getCLPValue() {
		return "";
	}

	public String getLocalVariable() {
		return "";
	}

	public void setCLPValue(String data) {
	}

	@Override
	public void negConstraint() {
		this.condition.negConstraint();
		this.then.negConstraint();
		if (this.elseExp != null)
			this.elseExp.negConstraint();
	}// «ØÄn

	@Override
	public void preconditionAddPre(SymbolTable sym, String methodName) {
		this.condition.preconditionAddPre(sym, methodName);
		this.then.preconditionAddPre(sym, methodName);
		if (this.elseExp != null)
			this.elseExp.preconditionAddPre(sym, methodName);
	}

//	public CLGConstraint Demongan(CLGConstraint Demonganconstraint) {
//
//		CLGConstraint finaltree = null;
//		
//		if (Demonganconstraint instanceof ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode) {
//			CLGOperatorNode tree = null;
//
//			if (((CLGOperatorNode) Demonganconstraint).getOperator().equals("&&")
//					|| ((CLGOperatorNode) Demonganconstraint).getOperator().equals("and")) {
//				tree = new CLGOperatorNode("||");
//			} else if (((CLGOperatorNode) Demonganconstraint).getOperator().equals("||")
//					|| ((CLGOperatorNode) Demonganconstraint).getOperator().equals("or")) {
//				tree = new CLGOperatorNode("&&");
//			} else if (((CLGOperatorNode) Demonganconstraint).getOperator().equals("==")
//					|| ((CLGOperatorNode) Demonganconstraint).getOperator().equals("=")) {
//				tree = new CLGOperatorNode("<>");
//			} else if (((CLGOperatorNode) Demonganconstraint).getOperator().equals("<>")) {
//				tree = new CLGOperatorNode("==");
//			} else if (((CLGOperatorNode) Demonganconstraint).getOperator().equals("<=")) {
//				tree = new CLGOperatorNode(">");
//			} else if (((CLGOperatorNode) Demonganconstraint).getOperator().equals(">")) {
//				tree = new CLGOperatorNode("<=");
//			} else if (((CLGOperatorNode) Demonganconstraint).getOperator().equals(">=")) {
//				tree = new CLGOperatorNode("<");
//			} else if (((CLGOperatorNode) Demonganconstraint).getOperator().equals("<")) {
//				tree = new CLGOperatorNode(">=");
//			}
//
//			if (((CLGOperatorNode) Demonganconstraint)
//					.getLeftOperand() instanceof ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode
//					&& !((CLGOperatorNode) ((CLGOperatorNode) Demonganconstraint).getLeftOperand()).getOperator()
//							.contains("*")
//					&& !((CLGOperatorNode) ((CLGOperatorNode) Demonganconstraint).getLeftOperand()).getOperator()
//							.contains("%")
//					&& !((CLGOperatorNode) ((CLGOperatorNode) Demonganconstraint).getLeftOperand()).getOperator()
//							.contains("+")
//					&& !((CLGOperatorNode) ((CLGOperatorNode) Demonganconstraint).getLeftOperand()).getOperator()
//							.contains("-")) {
//
//				tree.setLeftOperand(Demongan(((CLGOperatorNode) Demonganconstraint).getLeftOperand()));
//
//			} else {
//				tree.setLeftOperand(((CLGOperatorNode) Demonganconstraint).getLeftOperand());
//			}
//
//			if (((CLGOperatorNode) Demonganconstraint)
//					.getRightOperand() instanceof ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode
//					&& !((CLGOperatorNode) ((CLGOperatorNode) Demonganconstraint).getRightOperand()).getOperator()
//							.contains("*")
//					&& !((CLGOperatorNode) ((CLGOperatorNode) Demonganconstraint).getRightOperand()).getOperator()
//							.contains("+")
//					&& !((CLGOperatorNode) ((CLGOperatorNode) Demonganconstraint).getRightOperand()).getOperator()
//							.contains("-")
//					&& !((CLGOperatorNode) ((CLGOperatorNode) Demonganconstraint).getRightOperand()).getOperator()
//							.contains("%")) {
//
//				tree.setRightOperand(Demongan(((CLGOperatorNode) Demonganconstraint).getRightOperand()));
//			} else {
//				tree.setRightOperand(((CLGOperatorNode) Demonganconstraint).getRightOperand());
//			}
//
//			((CLGOperatorNode) tree).setType(((CLGOperatorNode) Demonganconstraint).getType());
//
//			finaltree = tree;
//
//		} else if (Demonganconstraint instanceof ccu.pllab.tcgen.AbstractConstraint.CLGLetNode) {
//
//			CLGLetNode DemonganLetconstraint = (CLGLetNode) Demonganconstraint;
//
//			finaltree = new CLGLetNode(Demongan(DemonganLetconstraint.getlocVariableAssign()),
//					Demongan(DemonganLetconstraint.getExpression()));
//		}
//
//		 return finaltree;
//
//	}

	@Override
	public String getConstraintName() {
		return "";
	}

	@Override
	public CLGConstraint addPre() {
		return this;
	}

	@Override
	protected void renameCLPValue(int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public VariableType getCLPVarType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void renameWithMap(HashMap<String, String> attMap) {
		// TODO Auto-generated method stub

	}
}
