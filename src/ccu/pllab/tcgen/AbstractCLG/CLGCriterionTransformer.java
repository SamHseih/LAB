package ccu.pllab.tcgen.AbstractCLG;

import java.util.ArrayList;
import ccu.pllab.tcgen.AbstractConstraint.CLGClassNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGMethodInvocationNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.AbstractType.IntType;
import ccu.pllab.tcgen.AbstractConstraint.CLGIfNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGIterateNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGLetNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGLiteralNode;
import ccu.pllab.tcgen.clg2path.CriterionFactory;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;

// 疑似某哥學長做的
// 我修了 一下，期待有緣人改善良率和效能
// 祝未來戰士好運~~
// 已經被搞死了N次的戰士們留
public class CLGCriterionTransformer extends CLGGraph {
	// public ArrayList<CLGConstraint> constraintOfStructure; // 在 IF 、Iterate、Let
	// 中的 constraint
	// public ArrayList<CLGConstraint> constraintCollection;
	// public ArrayList<CLGConstraint> allConstraint;
	// public ArrayList<CLGConstraint> tempconstraintCollection;
	// public ArrayList<CLGConstraint> tempconstraintIterate;
	// public ArrayList<CLGConstraint> cons;
	// public ArrayList<CLGGraph> ifclg;
//	private boolean tempConstraintIf = true;
//	private boolean tempConstraintStructure = true;
	// public ArrayList<CLGConstraint> constraintIterate;
//	private boolean tempConstraintIterate = true;
	// private boolean nodemon = false;

	public CLGCriterionTransformer() {
		super();
	}

	public CLGGraph CriterionTransformer(CLGGraph clg, Criterion criterion) {
		// this.constraintOfStructure = new ArrayList<CLGConstraint>();
		String[] clgindex = clg.getConstraintCollection().keySet().toString()
				.substring(1, clg.getConstraintCollection().keySet().toString().length() - 1).split(", ");
		if (criterion.equals(CriterionFactory.Criterion.dcc) || criterion.equals(CriterionFactory.Criterion.dccdup)) {
			System.out.println("<=========dcc criterion=========>");
			CLGNode successor = null;
			CLGNode Predecessor = null;
			int size = clg.getConstraintCollection().size();
			for (int i = 0; i < size; i++) {
				int a = Integer.parseInt(clgindex[i]);
				if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGMethodInvocationNode) {
					// System.out.println("Constraint is CLGMethodInvocationNode!!");
				} else if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGIfNode) {
					CLGIfNode x = (CLGIfNode) clg.getConstraintNodeById(a).getConstraint();
					CLGConstraint condition = x.getCondition();
					CLGConstraint notcondition = this.Demongan(condition);

					CLGGraph notleft = findChildNodeIsStructure(notcondition, criterion);
					CLGGraph left = parseConstraint(condition, criterion);

					CLGConstraint then = x.getThen();
					CLGGraph middle = parseConstraint(then, criterion);
					left.graphAnd(middle);
					if (x.getElse() != null) {
						CLGConstraint elseExp = x.getElse();
						CLGGraph right = parseConstraint(elseExp, criterion);
						notleft.graphAnd(right);
						// left.graphOr(notleft);
					}
					left.graphOr(notleft);

					return left;

//					left.getEndNode().getPredecessor().get(0).removeSuccessor(left.getEndNode());
//
//					successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//					Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//					successor.removePredecessor(clg.getConstraintNodeById(a));
//					Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//					Predecessor.addSuccessor(left.getStartNode().getSuccessor().get(0));
//					successor.addPredecessor(left.getEndNode().getPredecessor().get(0));
//
//					clg.getConstraintCollection().remove(a);
//					this.AddConstraintNode(clg, left);
				}

				else if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGIterateNode) {
					CLGIterateNode x = (CLGIterateNode) clg.getConstraintNodeById(a).getConstraint();
					CLGConstraint conditionCon = x.getCondition();
					CLGGraph condition = new CLGGraph(conditionCon);
					CLGConstraint bodyCon = x.getBody();
					CLGGraph body = parseConstraint(bodyCon, criterion);
					CLGConstraint IncrementCon = x.getIncrement();
					CLGGraph increment = new CLGGraph(IncrementCon);
					condition.graphAnd(body);
					condition.graphAnd(increment);
					condition.graphClosure();
					condition.graphAnd(new CLGGraph(this.Demongan(conditionCon)));
					CLGConstraint initialCon = x.getInitial();
					CLGGraph initial = parseConstraint(initialCon, criterion);
					initial.graphAnd(condition);

					return initial;

//					
//					 initial.getEndNode().getPredecessor().get(0).removeSuccessor(initial.getEndNode());
//
//					successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//					Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//					successor.removePredecessor(clg.getConstraintNodeById(a));
//					Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//					Predecessor.addSuccessor(initial.getStartNode().getSuccessor().get(0));
//					successor.addPredecessor(initial.getEndNode().getPredecessor().get(0));
//					clg.getConstraintCollection().remove(a);
//					this.AddConstraintNode(clg, initial);

				} else if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGLetNode) {
					CLGLetNode x = (CLGLetNode) clg.getConstraintNodeById(a).getConstraint();
					CLGConstraint locVariable = x.getlocVariableAssign();
					CLGGraph locVariableCLGGraph = parseConstraint(locVariable, criterion);
					CLGConstraint inExp = x.getExpression();
					CLGGraph inExpGraph = parseConstraint(inExp, criterion);
					locVariableCLGGraph.graphAnd(inExpGraph);

					return locVariableCLGGraph;

//					successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//					Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//					successor.removePredecessor(clg.getConstraintNodeById(a));
//					Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//					Predecessor.addSuccessor(locVariableCLGGraph.getStartNode().getSuccessor().get(0));
//					successor.addPredecessor(locVariableCLGGraph.getEndNode().getPredecessor().get(0));
//					clg.getConstraintCollection().remove(a);
//					this.AddConstraintNode(clg, locVariableCLGGraph);

				} else {
					CLGOperatorNode x = (CLGOperatorNode) clg.getConstraintNodeById(a).getConstraint();

					/********************
					 * and operator 處理
					 ******************************************/
					if (x.getOperator().equals("&&") || x.getOperator().equals("and"))// and是新增的
					{

						CLGConstraint Left = x.getLeftOperand();
						CLGGraph L = parseConstraint(Left, criterion);
						CLGConstraint Right = x.getRightOperand();
						CLGGraph R = parseConstraint(Right, criterion);
						L.graphAnd(R);

						return L;

						// 連接圖片

//						L.getEndNode().getPredecessor().get(0).removeSuccessor(L.getEndNode());
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(L.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(L.getEndNode().getPredecessor().get(0));
//
//						clg.getConstraintCollection().remove(a);
//						this.AddConstraintNode(clg, L);

					}
					/****************************************************************/
					/******************** or operator 處理 ******************************************/
					else if (x.getOperator().equals("||") || x.getOperator().equals("or"))// or是新增的
					{

						CLGConstraint left = x.getLeftOperand();

						CLGConstraint notleft = this.Demongan((CLGOperatorNode) x.getLeftOperand());
						;

						CLGConstraint right = x.getRightOperand();

						CLGConstraint notright = this.Demongan(x.getRightOperand());

						CLGGraph A = parseConstraint(left, criterion);

						CLGGraph notA = parseConstraint(notleft, criterion);

						CLGGraph B = parseConstraint(right, criterion);

						CLGGraph notB = parseConstraint(notright, criterion);

						A.graphAnd(notB);

						notA.graphAnd(B);

						A.graphOr(notA);

						return A;

//						
//						A.getEndNode().getPredecessor().get(0).removeSuccessor(A.getEndNode());
//
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(A.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(A.getEndNode().getPredecessor().get(0));
//
//						clg.getConstraintCollection().remove(a);
//						this.AddConstraintNode(clg, A);

					}
					/****************************************************************/
					/********************
					 * xor operator 處理
					 ******************************************/
					else if (x.getOperator().equals("xor")) {

						CLGConstraint left = x.getLeftOperand();

						CLGConstraint notleft = this.Demongan((CLGOperatorNode) x.getLeftOperand());
						;

						CLGConstraint right = x.getRightOperand();

						CLGConstraint notright = this.Demongan((CLGOperatorNode) x.getRightOperand());

						CLGGraph A = parseConstraint(left, criterion);

						CLGGraph notA = parseConstraint(notleft, criterion);

						CLGGraph B = parseConstraint(right, criterion);

						CLGGraph notB = parseConstraint(notright, criterion);

						A.graphAnd(notB);

						B.graphAnd(notA);

						A.graphOr(B);

						return A;

//						A.getEndNode().getPredecessor().get(0).removeSuccessor(A.getEndNode());
//
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(A.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(A.getEndNode().getPredecessor().get(0));

					}
					/****************************************************************/
					/********************
					 * implies operator 處理
					 ******************************************/
					else if (x.getOperator().equals("implies")) {

						CLGConstraint left = x.getLeftOperand();

						CLGConstraint notleft = this.Demongan((CLGOperatorNode) x.getLeftOperand());
						;

						CLGConstraint right = x.getRightOperand();

						CLGConstraint notright = this.Demongan((CLGOperatorNode) x.getRightOperand());

						CLGGraph A = parseConstraint(left, criterion);

						CLGGraph notA = parseConstraint(notleft, criterion);

						CLGGraph B = parseConstraint(right, criterion);

						CLGGraph notB = parseConstraint(notright, criterion);

						notA.graphAnd(notB);

						A.graphAnd(B);

						notA.graphOr(A);

						return notA;

						// 連接圖片
//						A.getEndNode().getPredecessor().get(0).removeSuccessor(A.getEndNode());
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(A.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(A.getEndNode().getPredecessor().get(0));
					}
//					else if (x.getOperator().equals("=") && x.getRightOperand() instanceof CLGIterateNode) { // 這個有用到嗎?
//						CLGGraph closure = parseConstraint(x.getRightOperand(), criterion);
//						x.setRightOperand(new CLGObjectNode("acc_pre", new IntType()));
//						CLGGraph newClg = new CLGGraph(x);
//						closure.graphAnd(newClg);
//						closure.getEndNode().getPredecessor().get(0).removeSuccessor(closure.getEndNode());
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(closure.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(closure.getEndNode().getPredecessor().get(0));
//					}
					/****************************************************************/
				}
			}
		}
		// MCC//////////////////////////////////////////////////////////////////////////////////////////////////////
		else if (criterion.equals(CriterionFactory.Criterion.mcc)
				|| criterion.equals(CriterionFactory.Criterion.mccdup)) {
			System.out.println("<=========mcc criterion=========>");
			CLGNode successor = null;
			CLGNode Predecessor = null;

			int size = clg.getConstraintCollection().size();

			for (int i = 0; i < size; i++) {

				int a = Integer.parseInt(clgindex[i]);
				if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGMethodInvocationNode) {
					// System.out.println("Constraint is CLGMethodInvocationNode!!");
				} else if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGIterateNode) {
					CLGIterateNode x = (CLGIterateNode) clg.getConstraintNodeById(a).getConstraint();
					CLGConstraint conditionCon = x.getCondition();
					CLGGraph condition = new CLGGraph(conditionCon);
					CLGConstraint bodyCon = x.getBody();
					CLGGraph body = parseConstraint(bodyCon, criterion);
					CLGConstraint IncrementCon = x.getIncrement();
					CLGGraph increment = new CLGGraph(IncrementCon);
					condition.graphAnd(body);
					condition.graphAnd(increment);
					condition.graphClosure();
					condition.graphAnd(new CLGGraph(this.Demongan(conditionCon)));
					CLGConstraint initialCon = x.getInitial();
					CLGGraph initial = parseConstraint(initialCon, criterion);
					initial.graphAnd(condition);

					initial.getEndNode().getPredecessor().get(0).removeSuccessor(initial.getEndNode());

					return initial;

//					successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//					Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//					successor.removePredecessor(clg.getConstraintNodeById(a));
//					Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//					Predecessor.addSuccessor(initial.getStartNode().getSuccessor().get(0));
//					successor.addPredecessor(initial.getEndNode().getPredecessor().get(0));
//					clg.getConstraintCollection().remove(a);
//					this.AddConstraintNode(clg, initial);
				} else if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGIfNode) {
					CLGIfNode x = (CLGIfNode) clg.getConstraintNodeById(a).getConstraint();
					CLGConstraint condition = x.getCondition();
					CLGConstraint notcondition = this.Demongan(condition);
					CLGGraph notleft = null;
					notleft = findChildNodeIsStructure(notcondition, criterion);

					CLGGraph left = parseConstraint(condition, criterion);

					CLGConstraint then = x.getThen();
					CLGGraph middle = parseConstraint(then, criterion);
					if (x.getElse() != null) {
						CLGConstraint elseExp = x.getElse();
						CLGGraph right = parseConstraint(elseExp, criterion);
						notleft.graphAnd(right);
					}
					left.graphAnd(middle);

					left.graphOr(notleft);

					return left;
//
//					left.getEndNode().getPredecessor().get(0).removeSuccessor(left.getEndNode());
//
//					successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//					Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//					successor.removePredecessor(clg.getConstraintNodeById(a));
//					Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//					Predecessor.addSuccessor(left.getStartNode().getSuccessor().get(0));
//					successor.addPredecessor(left.getEndNode().getPredecessor().get(0));
//
//					clg.getConstraintCollection().remove(a);
//					this.AddConstraintNode(clg, left);
				} else if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGLetNode) {
					CLGLetNode x = (CLGLetNode) clg.getConstraintNodeById(a).getConstraint();
					CLGConstraint locVariable = x.getlocVariableAssign();
					CLGGraph locVariableCLGGraph = parseConstraint(locVariable, criterion);
					CLGConstraint inExp = x.getExpression();
					CLGGraph inExpGraph = parseConstraint(inExp, criterion);
					locVariableCLGGraph.graphAnd(inExpGraph);

					return locVariableCLGGraph;

//					successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//					Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//					successor.removePredecessor(clg.getConstraintNodeById(a));
//					Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//					Predecessor.addSuccessor(locVariableCLGGraph.getStartNode().getSuccessor().get(0));
//					successor.addPredecessor(locVariableCLGGraph.getEndNode().getPredecessor().get(0));
//					clg.getConstraintCollection().remove(a);
//					this.AddConstraintNode(clg, locVariableCLGGraph);

				} else {
					CLGOperatorNode x = (CLGOperatorNode) clg.getConstraintNodeById(a).getConstraint();

					/********************
					 * and operator 處理
					 ******************************************/
					if (x.getOperator().equals("&&") || x.getOperator().equals("and"))// 改!
					{

						CLGConstraint Left = x.getLeftOperand();
						CLGGraph L = parseConstraint(Left, criterion);
						CLGConstraint Right = x.getRightOperand();
						CLGGraph R = parseConstraint(Right, criterion);
						L.graphAnd(R);

						return L;

						// 連接圖片
//						L.getEndNode().getPredecessor().get(0).removeSuccessor(L.getEndNode());
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(L.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(L.getEndNode().getPredecessor().get(0));
//						clg.getConstraintCollection().remove(a);
//						this.AddConstraintNode(clg, L);

					}
					/****************************************************************/
					/******************** or operator 處理 ******************************************/
					else if (x.getOperator().equals("||") || x.getOperator().equals("or")) {
						CLGConstraint left = x.getLeftOperand();

						CLGConstraint notleft = this.Demongan(((CLGOperatorNode) x).getLeftOperand());
						;

						CLGConstraint right = x.getRightOperand();

						CLGConstraint notright = this.Demongan(((CLGOperatorNode) x).getRightOperand());

						CLGGraph A = parseConstraint(left, criterion);

						CLGGraph mid = parseConstraint(left, criterion);

						CLGGraph mid2 = parseConstraint(right, criterion);

						CLGGraph notA = parseConstraint(notleft, criterion);

						CLGGraph B = parseConstraint(right, criterion);

						CLGGraph notB = parseConstraint(notright, criterion);

						mid.graphAnd(mid2);

						A.graphAnd(notB);

						notA.graphAnd(B);

						A.graphOr(notA);

						A.graphOr(mid);

						return A;

//						// 連接圖片
//						A.getEndNode().getPredecessor().get(0).removeSuccessor(A.getEndNode());
//
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(A.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(A.getEndNode().getPredecessor().get(0));
//
//						clg.getConstraintCollection().remove(a);
//						this.AddConstraintNode(clg, A);

					}
					/****************************************************************/
					/********************
					 * xor operator 處理
					 ******************************************/
					else if (x.getOperator().equals("xor")) {
						CLGConstraint left = x.getLeftOperand();

						CLGConstraint notleft = this.Demongan((CLGOperatorNode) x.getLeftOperand());
						;

						CLGConstraint right = x.getRightOperand();

						CLGConstraint notright = this.Demongan((CLGOperatorNode) x.getRightOperand());

						CLGGraph A = parseConstraint(left, criterion);

						CLGGraph notA = parseConstraint(notleft, criterion);

						CLGGraph B = parseConstraint(right, criterion);

						CLGGraph notB = parseConstraint(notright, criterion);

						A.graphAnd(notB);

						B.graphAnd(notA);

						A.graphOr(B);

//						// 連接圖片
//						A.getEndNode().getPredecessor().get(0).removeSuccessor(A.getEndNode());
//
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(A.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(A.getEndNode().getPredecessor().get(0));

					}
					/****************************************************************/
					/********************
					 * implies operator 處理
					 ******************************************/
					else if (x.getOperator().equals("implies")) {
						CLGConstraint left = x.getLeftOperand();

						CLGConstraint notleft = this.Demongan((CLGOperatorNode) x.getLeftOperand());
						;

						CLGConstraint right = x.getRightOperand();

						CLGConstraint notright = this.Demongan((CLGOperatorNode) x.getRightOperand());

						CLGGraph A = parseConstraint(left, criterion);

						CLGGraph notA = parseConstraint(notleft, criterion);

						CLGGraph B = parseConstraint(right, criterion);

						CLGGraph notB = parseConstraint(notright, criterion);

						notA.graphAnd(notB);

						A.graphAnd(B);

						notA.graphOr(A);

						return notA;

//						// 連接圖片
//						A.getEndNode().getPredecessor().get(0).removeSuccessor(A.getEndNode());
//
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(A.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(A.getEndNode().getPredecessor().get(0));

					}
					/****************************************************************/

//					else if (x.getOperator().equals("=") && x.getRightOperand() instanceof CLGIterateNode) {
//						CLGGraph closure = parseConstraint(x.getRightOperand(), criterion);
//						x.setRightOperand(new CLGObjectNode("acc_pre", new IntType()));
//						CLGGraph newClg = new CLGGraph(x);
//						closure.graphAnd(newClg);
//						closure.getEndNode().getPredecessor().get(0).removeSuccessor(closure.getEndNode());
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(closure.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(closure.getEndNode().getPredecessor().get(0));
//					}
				}

			}

		} else {
			int size = clg.getConstraintCollection().size();
			System.out.println("<=========dc criterion=========>");
			CLGNode successor = null;
			CLGNode Predecessor = null;
			for (int i = 0; i < size; i++) {
				boolean noDeomongan = false;
				int a = Integer.parseInt(clgindex[i]);
				if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGIfNode) {
					CLGIfNode x = (CLGIfNode) clg.getConstraintNodeById(a).getConstraint();
					CLGConstraint condition = x.getCondition();
					CLGConstraint notcondition = this.Demongan(condition);

					CLGGraph left = parseConstraint(condition, criterion);

					CLGGraph notleft = null;
					notleft = findChildNodeIsStructure(notcondition, criterion);

					if (x.getElse() != null) {
						CLGConstraint elseExp = x.getElse();
						CLGGraph right = parseConstraint(elseExp, criterion);
						notleft.graphAnd(right);
					}
					CLGConstraint then = x.getThen();
					CLGGraph middle = parseConstraint(then, criterion);
					left.graphAnd(middle);
					left.graphOr(notleft);

					left.getEndNode().getPredecessor().get(0).removeSuccessor(left.getEndNode());

					return left;

//					successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//					Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//					successor.removePredecessor(clg.getConstraintNodeById(a));
//					Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//					Predecessor.addSuccessor(left.getStartNode().getSuccessor().get(0));
//					successor.addPredecessor(left.getEndNode().getPredecessor().get(0));
//
//					clg.getConstraintCollection().remove(a);
//					this.AddConstraintNode(clg, left);
				} else if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGIterateNode) {
					CLGIterateNode x = (CLGIterateNode) clg.getConstraintNodeById(a).getConstraint();
					CLGConstraint conditionCon = x.getCondition();
					CLGGraph condition = new CLGGraph(conditionCon);
					CLGConstraint bodyCon = x.getBody();
					CLGGraph body = parseConstraint(bodyCon, criterion);
					CLGConstraint IncrementCon = x.getIncrement();
					CLGGraph increment = new CLGGraph(IncrementCon);
					condition.graphAnd(body);
					condition.graphAnd(increment);
					condition.graphClosure();
					condition.graphAnd(new CLGGraph(this.Demongan(conditionCon)));
					CLGConstraint initialCon = x.getInitial();
					CLGGraph initial = parseConstraint(initialCon, CriterionFactory.Criterion.dcc);
					initial.graphAnd(condition);

					initial.getEndNode().getPredecessor().get(0).removeSuccessor(initial.getEndNode());

					return initial;

//					successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//					Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//					successor.removePredecessor(clg.getConstraintNodeById(a));
//					Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//					Predecessor.addSuccessor(initial.getStartNode().getSuccessor().get(0));
//					successor.addPredecessor(initial.getEndNode().getPredecessor().get(0));
//					clg.getConstraintCollection().remove(a);
//					this.AddConstraintNode(clg, initial);
				} else if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGMethodInvocationNode) {

				} else if (clg.getConstraintNodeById(a).getConstraint() instanceof CLGLetNode) {
					CLGLetNode x = (CLGLetNode) clg.getConstraintNodeById(a).getConstraint();
					CLGConstraint locVariable = x.getlocVariableAssign();
					CLGGraph locVariableCLGGraph = parseConstraint(locVariable, criterion);
					CLGConstraint inExp = x.getExpression();
					CLGGraph inExpGraph = parseConstraint(inExp, criterion);
					locVariableCLGGraph.graphAnd(inExpGraph);

					return locVariableCLGGraph;

//					successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//					Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//					successor.removePredecessor(clg.getConstraintNodeById(a));
//					Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//					Predecessor.addSuccessor(locVariableCLGGraph.getStartNode().getSuccessor().get(0));
//					successor.addPredecessor(locVariableCLGGraph.getEndNode().getPredecessor().get(0));
//					clg.getConstraintCollection().remove(a);
//					this.AddConstraintNode(clg, locVariableCLGGraph);

				} else {
					CLGOperatorNode x = (CLGOperatorNode) clg.getConstraintNodeById(a).getConstraint();
					CLGGraph clg_pre = clg;
					// this.constraintOfStructure = new ArrayList<CLGConstraint>();
					ArrayList<CLGGraph> subclg = new ArrayList<CLGGraph>();
					if (x.getOperator().equals("&&") || x.getOperator().equals("||")) {

						CLGGraph opNode = null;

						if (x.getRightOperand() instanceof CLGIfNode || x.getRightOperand() instanceof CLGIterateNode
								|| x.getRightOperand() instanceof CLGMethodInvocationNode
								|| x.getRightOperand() instanceof CLGLetNode) {

							CLGGraph left = findChildNodeIsStructure(x.getLeftOperand(), criterion);
							CLGGraph right = parseConstraint(x.getRightOperand(), criterion);

							if (x.getOperator().equals("&&")) {
								left.graphAnd(right);
							} else {
								left.graphOr(right);
							}

							opNode = left;

							// this.constraintOfStructure.add(x.getRightOperand());

						} else {
							opNode = findChildNodeIsStructure(x, criterion);
						}

//						

						subclg.add(opNode);

						CLGNode start = clg_pre.getStartNode();
						if (subclg.size() > 0) {
							((CLGStartNode) subclg.get(0).getStartNode())
									.setClassName(((CLGStartNode) start).getClassName());
							((CLGStartNode) subclg.get(0).getStartNode())
									.setMethodName(((CLGStartNode) start).getMethodName());
							((CLGStartNode) subclg.get(0).getStartNode())
									.setRetType(((CLGStartNode) start).getReturnType());
							((CLGStartNode) subclg.get(0).getStartNode())
									.setIsConstructor(((CLGStartNode) start).isConstructor());
							((CLGStartNode) subclg.get(0).getStartNode())
									.setClassAttributes(((CLGStartNode) start).getClassAttributes());
							((CLGStartNode) subclg.get(0).getStartNode())
									.setMethodParameters(((CLGStartNode) start).getMethodParameters());
							((CLGStartNode) subclg.get(0).getStartNode())
									.setMethodParameterTypes(((CLGStartNode) start).getMethodParameterTypes());
							clg = subclg.get(0);
						}
					}
//					else if (x.getOperator().equals("=") && x.getRightOperand() instanceof CLGIterateNode) {// 這個有用到嗎?
//						CLGGraph closure = parseConstraint(x.getRightOperand(), criterion);
//						x.setRightOperand(new CLGObjectNode("acc_pre", new IntType()));
//						CLGGraph newClg = parseConstraint(x, criterion);
//						closure.graphAnd(newClg);
//						closure.getEndNode().getPredecessor().get(0).removeSuccessor(closure.getEndNode());
//						successor = clg.getConstraintNodeById(a).getSuccessor().get(0);
//						Predecessor = clg.getConstraintNodeById(a).getPredecessor().get(0);
//						successor.removePredecessor(clg.getConstraintNodeById(a));
//						Predecessor.removeSuccessor(clg.getConstraintNodeById(a));
//						Predecessor.addSuccessor(closure.getStartNode().getSuccessor().get(0));
//						successor.addPredecessor(closure.getEndNode().getPredecessor().get(0));
//					}
				}
			}
		}
		/*****************************************
		 * 限制節點與邊數量統計***** System.out.println("constraint node " +
		 * clg.getConstraintCollection().size()); System.out.println("edge
		 * "+clg.getAllBranches().size() );
		 ***********/

		return clg;
	}

	public CLGGraph findChildNodeIsStructure(CLGConstraint node, Criterion criterion) {

		if (node instanceof CLGIfNode || node instanceof CLGLetNode || node instanceof CLGIterateNode) {
			return parseConstraint(node, criterion);
		} else if (node instanceof CLGVariableNode) {
			return parseConstraint(node, criterion);
		} else if (((CLGOperatorNode) node).getOperator().equals("&&")
				|| ((CLGOperatorNode) node).getOperator().equals("||")) {
			if (((CLGOperatorNode) node).getRightOperand() instanceof CLGIfNode
					|| ((CLGOperatorNode) node).getRightOperand() instanceof CLGLetNode
					|| ((CLGOperatorNode) node).getRightOperand() instanceof CLGIterateNode) {
				CLGGraph left = findChildNodeIsStructure(((CLGOperatorNode) node).getLeftOperand(), criterion);
				CLGGraph right = parseConstraint(((CLGOperatorNode) node).getRightOperand(), criterion);
				if (((CLGOperatorNode) node).getOperator().equals("&&")) {
					left.graphAnd(right);
					return left;
				} else {
					left.graphOr(right);
					return left;
				}

				// return;
			}
			CLGConstraint search = ((CLGOperatorNode) node).getLeftOperand();
			CLGOperatorNode temp = (CLGOperatorNode) node;
			int isIf = 0;
			String op = "";
			while (search != null)// 改過
			{
				if (search instanceof CLGIfNode || search instanceof CLGLetNode || search instanceof CLGIterateNode) {
					isIf = 1;
					CLGGraph structNode = parseConstraint(search, criterion);
					temp.setLeftOperand(null);
					CLGGraph otherNode = parseConstraint(node, criterion);

					if (temp.getOperator().equals("&&") || temp.getOperator().equals("and")) {
						structNode.graphAnd(otherNode);
					} else { // or
						structNode.graphOr(otherNode);
					}
					return structNode;
					// break;
				} else if (search != null) {
					if (search instanceof CLGOperatorNode) {
						if (((CLGOperatorNode) search).getOperator().equals("&&")
								|| ((CLGOperatorNode) search).getOperator().equals("||")
								|| ((CLGOperatorNode) search).getOperator().equals("and")
								|| ((CLGOperatorNode) search).getOperator().equals("or")) {
							if (((CLGOperatorNode) search).getRightOperand() instanceof CLGIfNode
									|| ((CLGOperatorNode) search).getRightOperand() instanceof CLGLetNode
									|| ((CLGOperatorNode) search).getRightOperand() instanceof CLGIterateNode) {
								isIf = 2;

								CLGGraph searchLeft = findChildNodeIsStructure(
										((CLGOperatorNode) search).getLeftOperand(), criterion);
								((CLGOperatorNode) search).setLeftOperand(null);

								CLGGraph structNode = parseConstraint(((CLGOperatorNode) search).getRightOperand(),
										criterion);
								temp.setLeftOperand(null);
								CLGGraph otherNode = parseConstraint(node, criterion);

								if (((CLGOperatorNode) search).getOperator().equals("&&")
										|| ((CLGOperatorNode) search).getOperator().equals("and")) {
									searchLeft.graphAnd(structNode);
								} else {
									searchLeft.graphOr(structNode);
								}

								if (((CLGOperatorNode) temp).getOperator().equals("&&")
										|| ((CLGOperatorNode) temp).getOperator().equals("and")) {
									searchLeft.graphAnd(otherNode);
								} else {
									searchLeft.graphOr(otherNode);
								}
								return searchLeft;
								// break;
							} else {
								temp = (CLGOperatorNode) search;
								search = ((CLGOperatorNode) search).getLeftOperand();
							}
						} else
							break;
					} else
						break;
				}
			}
			return new CLGGraph(node);
		} else {
			return new CLGGraph(node);
		}
	}

	private CLGGraph parseConstraint(CLGConstraint constraint, Criterion criterion) {

		if (constraint instanceof CLGIfNode) {
			CLGIfNode ifconstraint = (CLGIfNode) constraint;
			CLGConstraint condition = ifconstraint.getCondition();
			CLGGraph left = parseConstraint(condition, criterion);
			CLGConstraint then = ifconstraint.getThen();
			CLGGraph middle = parseConstraint(then, criterion);

			CLGConstraint notcondition = this.Demongan(condition);
			CLGGraph notleft = parseConstraint(notcondition, criterion);
			left.graphAnd(middle);

			if (ifconstraint.getElse() != null) {
				CLGConstraint elseExp = ifconstraint.getElse();
				CLGGraph right = parseConstraint(elseExp, criterion);
				notleft.graphAnd(right);
			}
			left.graphOr(notleft);

			return left;
		}

		else if ((constraint instanceof CLGIterateNode) && criterion.equals(CriterionFactory.Criterion.dc)) {
			CLGIterateNode collconstraint = (CLGIterateNode) constraint;
			CLGGraph initial = new CLGGraph(((CLGOperatorNode) collconstraint.getInitial()).getLeftOperand());
			initial.graphAnd(new CLGGraph(((CLGOperatorNode) collconstraint.getInitial()).getRightOperand()));

			CLGGraph condition = new CLGGraph(collconstraint.getCondition());
			if (collconstraint.getBody() instanceof CLGIterateNode || collconstraint.getBody() instanceof CLGIfNode
					|| collconstraint.getBody() instanceof CLGLetNode)
				condition.graphAnd(parseConstraint(collconstraint.getBody(), CriterionFactory.Criterion.dcc));
			else
				condition.graphAnd(parseConstraint(collconstraint.getBody(), CriterionFactory.Criterion.dc));
			condition.graphAnd(new CLGGraph(collconstraint.getIncrement()));

			condition.graphClosure();
			CLGGraph clgGragh = new CLGGraph(this.Demongan(collconstraint.getCondition()));
			condition.graphAnd(clgGragh);
			initial.graphAnd(condition);
			return initial;

		} else if (constraint instanceof CLGIterateNode) {
			CLGIterateNode collconstraint = (CLGIterateNode) constraint;
			CLGGraph condition = parseConstraint(collconstraint.getCondition(), criterion);
			CLGGraph body = parseConstraint(collconstraint.getBody(), criterion);
			CLGGraph increment = parseConstraint(collconstraint.getIncrement(), criterion);
			condition.graphAnd(body);
			condition.graphAnd(increment);
			condition.graphClosure();
			condition.graphAnd(new CLGGraph(this.Demongan(collconstraint.getCondition())));
			// CLGConstraint initialCon=collconstraint.getInitial();
			CLGGraph initial = parseConstraint(collconstraint.getInitial(), criterion);
			initial.graphAnd(condition);
			return initial;
		} else if ((constraint instanceof CLGLetNode)) {

			CLGLetNode letConstraint = (CLGLetNode) constraint;
			CLGGraph locVariableCondition = parseConstraint(letConstraint.getlocVariableAssign(), criterion);
			CLGGraph inCondition = parseConstraint(letConstraint.getExpression(), criterion);
			locVariableCondition.graphAnd(inCondition);
			return locVariableCondition;

		} else if (constraint instanceof CLGMethodInvocationNode) {
			return new CLGGraph(constraint);
		} else if (constraint instanceof CLGObjectNode || constraint instanceof CLGClassNode) {
			return new CLGGraph(constraint);
		} else {
			CLGOperatorNode opconstraint = (CLGOperatorNode) constraint;
			if ((opconstraint.getOperator().equals("&&") || opconstraint.getOperator().equals("and")
					|| opconstraint.getOperator().equals("||") || opconstraint.getOperator().equals("or"))
					&& criterion.equals(CriterionFactory.Criterion.dc)) {
				ArrayList<CLGGraph> subclg = new ArrayList<CLGGraph>();

				if (opconstraint.getRightOperand() instanceof CLGIfNode
						|| opconstraint.getRightOperand() instanceof CLGMethodInvocationNode
						|| opconstraint.getRightOperand() instanceof CLGLetNode
						|| opconstraint.getRightOperand() instanceof CLGIterateNode) {
					CLGGraph left = findChildNodeIsStructure(opconstraint.getLeftOperand(), criterion);
					CLGGraph right = parseConstraint(opconstraint.getRightOperand(), criterion);

					if (opconstraint.getOperator().equals("&&") || opconstraint.getOperator().equals("and")) {
						left.graphAnd(right);
					} else {
						left.graphOr(right);
					}
					subclg.add(left);
					// this.constraintOfStructure.add(opconstraint.getRightOperand());
				} else {
					subclg.add(findChildNodeIsStructure(opconstraint, criterion));
				}

				return subclg.get(0);
			} else if ((opconstraint.getOperator().equals("&&") || opconstraint.getOperator().equals("and"))
					&& (criterion.equals(CriterionFactory.Criterion.dcc)
							|| criterion.equals(CriterionFactory.Criterion.mcc))) {

				CLGGraph t = null;
				CLGGraph y = null;

				if (opconstraint.getLeftOperand() != null) {
					t = parseConstraint(opconstraint.getLeftOperand(), criterion);
				}

				if (opconstraint.getRightOperand() != null) {
					y = parseConstraint(opconstraint.getRightOperand(), criterion);
				}

				// return
				if (t != null && y == null) {
					return t;
				} else if (t == null && y != null) {
					return y;
				} else {
					t.graphAnd(y);
					return t;
				}
			}
			if ((opconstraint.getOperator().equals("||") || opconstraint.getOperator().equals("or"))
					&& criterion.equals(CriterionFactory.Criterion.dcc)) {
				CLGConstraint left = opconstraint.getLeftOperand();
				CLGConstraint right = opconstraint.getRightOperand();
				CLGConstraint notleft = null;
				CLGConstraint notright = null;
				CLGGraph A = null;
				CLGGraph notA = null;
				CLGGraph B = null;
				CLGGraph notB = null;

				if (left != null) {
					// if(left instanceof CLGOperatorNode)
					notleft = this.Demongan(left);
					A = parseConstraint(left, criterion);

					notA = parseConstraint(notleft, criterion);

				}

				if (right != null) {
					if (opconstraint.getRightOperand() instanceof CLGOperatorNode)
						notright = this.Demongan(((CLGOperatorNode) opconstraint).getRightOperand());
					else
						notright = opconstraint.getRightOperand();

					B = parseConstraint(right, criterion);

					notB = parseConstraint(notright, criterion);
				}

				// return
				if (A == null && B != null) {
					return B;
				} else if (A != null && B == null) {
					return A;
				} else {
					A.graphAnd(notB);
					notA.graphAnd(B);
					A.graphOr(notA);
					return A;
				}
			}
			if ((opconstraint.getOperator().equals("||") || opconstraint.getOperator().equals("or"))
					&& criterion.equals(CriterionFactory.Criterion.mcc)) {
				CLGConstraint left = opconstraint.getLeftOperand();

				CLGConstraint notleft = this.Demongan(left);

				CLGConstraint right = opconstraint.getRightOperand();
				CLGConstraint notright = null;
				if (opconstraint.getRightOperand() instanceof CLGOperatorNode)
					notright = this.Demongan((CLGOperatorNode) opconstraint.getRightOperand());
				else
					notright = opconstraint.getRightOperand();
				CLGGraph A = parseConstraint(left, criterion);

				CLGGraph mid = parseConstraint(left, criterion);

				CLGGraph notA = parseConstraint(notleft, criterion);

				if (right != null) {
					CLGGraph mid2 = parseConstraint(right, criterion);

					CLGGraph B = parseConstraint(right, criterion);

					CLGGraph notB = parseConstraint(notright, criterion);

					mid.graphAnd(mid2);

					A.graphAnd(notB);

					notA.graphAnd(B);

					A.graphOr(notA);

					A.graphOr(mid);
				}

				return A;
			}
			if (opconstraint.getOperator().equals("xor")) {
				CLGConstraint left = opconstraint.getLeftOperand();

				CLGConstraint notleft = this.Demongan((CLGOperatorNode) opconstraint.getLeftOperand());
				;

				CLGConstraint right = opconstraint.getRightOperand();

				CLGConstraint notright = this.Demongan((CLGOperatorNode) opconstraint.getRightOperand());

				CLGGraph A = parseConstraint(left, criterion);

				CLGGraph notA = parseConstraint(notleft, criterion);

				CLGGraph B = parseConstraint(right, criterion);

				CLGGraph notB = parseConstraint(notright, criterion);

				A.graphAnd(notB);

				B.graphAnd(notA);

				A.graphOr(B);
				return A;
			}
			if (opconstraint.getOperator().equals("implies")) {
				CLGConstraint left = opconstraint.getLeftOperand();

				CLGConstraint notleft = this.Demongan((CLGOperatorNode) opconstraint.getLeftOperand());
				;

				CLGConstraint right = opconstraint.getRightOperand();

				CLGConstraint notright = this.Demongan((CLGOperatorNode) opconstraint.getRightOperand());

				CLGGraph A = parseConstraint(left, criterion);

				CLGGraph notA = parseConstraint(notleft, criterion);

				CLGGraph B = parseConstraint(right, criterion);

				CLGGraph notB = parseConstraint(notright, criterion);

				notA.graphAnd(notB);

				A.graphAnd(B);

				notA.graphOr(A);
				return A;
			}
		}
		// }

		return new CLGGraph(constraint);
	}

	public CLGConstraint Demonganif(CLGIfNode Demonganconstraint) {
		CLGIfNode newif;
		CLGConstraint then;
		CLGConstraint elseExp;

		if (Demonganconstraint.getThen() instanceof CLGIfNode)
			then = Demonganif((CLGIfNode) Demonganconstraint.getThen());
		else
			then = Demongan(Demonganconstraint.getThen());
		if (Demonganconstraint.getElse() != null) {
			if (Demonganconstraint.getElse() instanceof CLGIfNode)
				elseExp = Demonganif((CLGIfNode) Demonganconstraint.getElse());
			else
				elseExp = Demongan(Demonganconstraint.getElse());
			newif = new CLGIfNode(Demonganconstraint.getCondition(), then, elseExp);
		} else
			newif = new CLGIfNode(Demonganconstraint.getCondition(), then);

		return newif.clone();
	}

	public CLGConstraint Demongan(CLGConstraint Demonganconstraint) {

		CLGConstraint finaltree = null;
		if (Demonganconstraint instanceof CLGIterateNode) {
			finaltree = new CLGIterateNode();
			((CLGIterateNode) finaltree).setInitial(((CLGIterateNode) Demonganconstraint).getInitial());
			((CLGIterateNode) finaltree).setCondition(((CLGIterateNode) Demonganconstraint).getCondition());
			((CLGIterateNode) finaltree).setIncrement(((CLGIterateNode) Demonganconstraint).getIncrement());
			((CLGIterateNode) finaltree).setStart(((CLGIterateNode) Demonganconstraint).getStart());
			((CLGIterateNode) finaltree).setStart(((CLGIterateNode) Demonganconstraint).getStart());
			((CLGIterateNode) finaltree).setAccType(((CLGIterateNode) Demonganconstraint).getAccType());
			// if(((CLGOperatorNode) ((CLGIterateNode)
			// Demonganconstraint).getBody()).getBoundary())

			((CLGIterateNode) finaltree).setBody(Demongan(((CLGIterateNode) Demonganconstraint).getBody()));
			if (((CLGIterateNode) Demonganconstraint).getBody() instanceof CLGOperatorNode) {
				if (((CLGOperatorNode) ((CLGIterateNode) Demonganconstraint).getBody()).getBoundary()) {
					((CLGOperatorNode) ((CLGIterateNode) Demonganconstraint).getBody()).setBoundary();
				}
			}
		} else if (Demonganconstraint instanceof CLGIfNode) {
			finaltree = Demonganif((CLGIfNode) Demonganconstraint);

		} else if (Demonganconstraint instanceof CLGLetNode) {
			CLGConstraint loc = Demongan(((CLGLetNode) Demonganconstraint).getlocVariableAssign());
			CLGConstraint ex = Demongan(((CLGLetNode) Demonganconstraint).getExpression());
			finaltree = new CLGLetNode(loc, ex);
		} else if (Demonganconstraint instanceof CLGVariableNode || Demonganconstraint instanceof CLGLiteralNode) {
			finaltree = Demonganconstraint.clone();
		} else {

			CLGOperatorNode theCLGOperatorNode = (CLGOperatorNode) Demonganconstraint;

			if ((theCLGOperatorNode.getOperator().equals("&&") || theCLGOperatorNode.getOperator().equals("and"))
					&& !theCLGOperatorNode.getAssign()) {
				finaltree = new CLGOperatorNode("||");
			} else if (theCLGOperatorNode.getOperator().equals("||") || theCLGOperatorNode.getOperator().equals("or")) {
				finaltree = new CLGOperatorNode("&&");
			} else if (theCLGOperatorNode.getOperator().equals("==")
					|| (theCLGOperatorNode.getOperator().equals("=") && !theCLGOperatorNode.getAssign())) {
				finaltree = new CLGOperatorNode("<>");
			} else if (theCLGOperatorNode.getOperator().equals("<>")) {
				finaltree = new CLGOperatorNode("==");
			} else if (theCLGOperatorNode.getOperator().equals("<=")) {
				finaltree = new CLGOperatorNode(">");
			} else if (theCLGOperatorNode.getOperator().equals(">")) {
				finaltree = new CLGOperatorNode("<=");
			} else if (theCLGOperatorNode.getOperator().equals(">=")) {
				finaltree = new CLGOperatorNode("<");
			} else if (theCLGOperatorNode.getOperator().equals("<")) {
				finaltree = new CLGOperatorNode(">=");
			} else {
				finaltree = new CLGOperatorNode(theCLGOperatorNode.getOperator(), theCLGOperatorNode.getAssign());
			}

			if (theCLGOperatorNode.getLeftOperand() != null)
				((CLGOperatorNode) finaltree).setLeftOperand(Demongan(theCLGOperatorNode.getLeftOperand()));

			if (theCLGOperatorNode.getRightOperand() != null)
				((CLGOperatorNode) finaltree).setRightOperand(Demongan(theCLGOperatorNode.getRightOperand()));

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
//				((CLGOperatorNode) finaltree)
//						.setLeftOperand(Demongan(((CLGOperatorNode) Demonganconstraint).getLeftOperand()));
//
//			} else if (((CLGOperatorNode) Demonganconstraint).getLeftOperand() instanceof CLGIterateNode) {
//				((CLGOperatorNode) finaltree)
//						.setLeftOperand(Demongan(((CLGOperatorNode) Demonganconstraint).getLeftOperand()));
//			} else {
//				((CLGOperatorNode) finaltree).setLeftOperand(((CLGOperatorNode) Demonganconstraint).getLeftOperand());
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
//				((CLGOperatorNode) finaltree)
//						.setRightOperand(Demongan(((CLGOperatorNode) Demonganconstraint).getRightOperand()));
//			} else {
//				((CLGOperatorNode) finaltree).setRightOperand(((CLGOperatorNode) Demonganconstraint).getRightOperand());
//			}
		}

		if (finaltree instanceof CLGOperatorNode)
			((CLGOperatorNode) finaltree).setType(((CLGOperatorNode) Demonganconstraint).getType());

		return finaltree.clone();

	}

	public void AddConstraintNode(CLGGraph clg1, CLGGraph clg2) {

		for (int j = 0; j < clg2.getConstraintCollection().size(); j++) {
			String[] index = clg2.getConstraintCollection().keySet().toString()
					.substring(1, clg2.getConstraintCollection().keySet().toString().length() - 1).split(", ");
			int b = Integer.parseInt(index[j]);
			clg1.getConstraintCollection().put(b, clg2.getConstraintNodeById(b));
		}

	}

	// 邊產生CLG邊連接，不需要Loop
	private CLGGraph parseConstraintNew(CLGConstraint constraint, Criterion criterion) {

		if (constraint instanceof CLGIfNode) {
			CLGIfNode ifconstraint = (CLGIfNode) constraint;
			CLGConstraint condition = ifconstraint.getCondition();
			CLGGraph left = parseConstraintNew(condition, criterion);
			CLGConstraint then = ifconstraint.getThen();
			CLGGraph middle = parseConstraintNew(then, criterion);

			CLGConstraint notcondition = this.Demongan(condition);
			CLGGraph notleft = parseConstraintNew(notcondition, criterion);
			left.graphAnd(middle);

			if (ifconstraint.getElse() != null) {
				CLGConstraint elseExp = ifconstraint.getElse();
				CLGGraph right = parseConstraintNew(elseExp, criterion);
				notleft.graphAnd(right);
			}
			left.graphOr(notleft);

			return left;
		}

		else if ((constraint instanceof CLGIterateNode) && criterion.equals(CriterionFactory.Criterion.dc)) {
			CLGIterateNode collconstraint = (CLGIterateNode) constraint;
			CLGGraph initial = new CLGGraph(((CLGOperatorNode) collconstraint.getInitial()).getLeftOperand());
			initial.graphAnd(new CLGGraph(((CLGOperatorNode) collconstraint.getInitial()).getRightOperand()));

			CLGGraph condition = new CLGGraph(collconstraint.getCondition());
			if (collconstraint.getBody() instanceof CLGIterateNode)
				condition.graphAnd(parseConstraintNew(collconstraint.getBody(), CriterionFactory.Criterion.dcc));
			else
				condition.graphAnd(new CLGGraph(collconstraint.getBody()));
			condition.graphAnd(new CLGGraph(collconstraint.getIncrement()));

			condition.graphClosure();
			condition.graphAnd(new CLGGraph(this.Demongan(collconstraint.getCondition())));
			initial.graphAnd(condition);
			return initial;

		} else if (constraint instanceof CLGIterateNode) {
			CLGIterateNode collconstraint = (CLGIterateNode) constraint;
			CLGGraph condition = parseConstraintNew(collconstraint.getCondition(), criterion);
			CLGGraph body = parseConstraintNew(collconstraint.getBody(), criterion);
			CLGGraph increment = parseConstraintNew(collconstraint.getIncrement(), criterion);
			condition.graphAnd(body);
			condition.graphAnd(increment);
			condition.graphClosure();
			condition.graphAnd(new CLGGraph(this.Demongan(collconstraint.getCondition())));
			// CLGConstraint initialCon=collconstraint.getInitial();
			CLGGraph initial = parseConstraintNew(collconstraint.getInitial(), criterion);
			initial.graphAnd(condition);
			return initial;
		}

		else if (constraint instanceof CLGMethodInvocationNode) {

			return new CLGGraph(constraint);
		} else if (constraint instanceof CLGObjectNode || constraint instanceof CLGClassNode) {

			return new CLGGraph(constraint);
		} else if (constraint instanceof CLGLetNode) {

			CLGGraph letclg = parseConstraintNew(((CLGLetNode) constraint).getlocVariableAssign(), criterion);
			letclg.graphAnd(parseConstraintNew(((CLGLetNode) constraint).getExpression(), criterion));
			return letclg;

		} else {
			CLGOperatorNode opconstraint = (CLGOperatorNode) constraint;
			if ((opconstraint.getOperator().equals("&&") || opconstraint.getOperator().equals("and")
					|| opconstraint.getOperator().equals("||") || opconstraint.getOperator().equals("or"))
					&& criterion.equals(CriterionFactory.Criterion.dc)) {

				CLGGraph subclg = new CLGGraph();
				if (opconstraint.getLeftOperand() != null)
					subclg = parseConstraintNew(opconstraint.getLeftOperand(), criterion);

				if (opconstraint.getRightOperand() != null) {
//					if(opconstraint.getOperator().equals("&&") || opconstraint.getOperator().equals("and"))
//						subclg.graphAnd(parseConstraintNew(opconstraint.getRightOperand(),criterion));
//					else {
					CLGGraph r = parseConstraintNew(opconstraint.getRightOperand(), criterion);
					if (subclg.getConstraintNodeCounter() < 2 && r.getConstraintNodeCounter() < 2) {
						subclg = new CLGGraph(opconstraint);
					} else {
						subclg.graphAnd(parseConstraintNew(opconstraint.getRightOperand(), criterion));
					}
//					}
				}

				return subclg;

			} else if ((opconstraint.getOperator().equals("&&") || opconstraint.getOperator().equals("and"))
					&& (criterion.equals(CriterionFactory.Criterion.dcc)
							|| criterion.equals(CriterionFactory.Criterion.mcc))) {

				CLGGraph t = parseConstraintNew(opconstraint.getLeftOperand(), criterion);

				if (opconstraint.getRightOperand() != null) {
					CLGGraph y = parseConstraintNew(opconstraint.getRightOperand(), criterion);

					t.graphAnd(y);
				}

				return t;
			}
			if ((opconstraint.getOperator().equals("||") || opconstraint.getOperator().equals("or"))
					&& criterion.equals(CriterionFactory.Criterion.dcc)) {
				CLGConstraint left = opconstraint.getLeftOperand();

				CLGConstraint notleft = null;
				// if(left instanceof CLGOperatorNode)
				notleft = this.Demongan(left);

				CLGConstraint right = opconstraint.getRightOperand();
				CLGConstraint notright = null;
				if (opconstraint.getRightOperand() instanceof CLGOperatorNode)
					notright = this.Demongan(((CLGOperatorNode) opconstraint).getRightOperand());
				else
					notright = opconstraint.getRightOperand();
				CLGGraph A = parseConstraintNew(left, criterion);

				CLGGraph notA = parseConstraintNew(notleft, criterion);

				if (right != null) {
					CLGGraph B = parseConstraintNew(right, criterion);

					CLGGraph notB = parseConstraintNew(notright, criterion);

					A.graphAnd(notB);
					notA.graphAnd(B);

					A.graphOr(notA);
				}

				return A;
			}
			if ((opconstraint.getOperator().equals("||") || opconstraint.getOperator().equals("or"))
					&& criterion.equals(CriterionFactory.Criterion.mcc)) {
				CLGConstraint left = opconstraint.getLeftOperand();

				CLGConstraint notleft = this.Demongan(left);

				CLGConstraint right = opconstraint.getRightOperand();
				CLGConstraint notright = null;
				if (opconstraint.getRightOperand() instanceof CLGOperatorNode)
					notright = this.Demongan((CLGOperatorNode) opconstraint.getRightOperand());
				else
					notright = opconstraint.getRightOperand();
				CLGGraph A = parseConstraintNew(left, criterion);

				CLGGraph mid = parseConstraintNew(left, criterion);

				CLGGraph notA = parseConstraintNew(notleft, criterion);

				if (right != null) {
					CLGGraph mid2 = parseConstraintNew(right, criterion);

					CLGGraph B = parseConstraintNew(right, criterion);

					CLGGraph notB = parseConstraintNew(notright, criterion);

					mid.graphAnd(mid2);

					A.graphAnd(notB);

					notA.graphAnd(B);

					A.graphOr(notA);

					A.graphOr(mid);
				}

				return A;
			}
			if (opconstraint.getOperator().equals("xor")) {
				CLGConstraint left = opconstraint.getLeftOperand();

				CLGConstraint notleft = this.Demongan((CLGOperatorNode) opconstraint.getLeftOperand());
				;

				CLGConstraint right = opconstraint.getRightOperand();

				CLGConstraint notright = this.Demongan((CLGOperatorNode) opconstraint.getRightOperand());

				CLGGraph A = parseConstraintNew(left, criterion);

				CLGGraph notA = parseConstraintNew(notleft, criterion);

				CLGGraph B = parseConstraintNew(right, criterion);

				CLGGraph notB = parseConstraintNew(notright, criterion);

				A.graphAnd(notB);

				B.graphAnd(notA);

				A.graphOr(B);
				return A;
			}
			if (opconstraint.getOperator().equals("implies")) {
				CLGConstraint left = opconstraint.getLeftOperand();

				CLGConstraint notleft = this.Demongan((CLGOperatorNode) opconstraint.getLeftOperand());
				;

				CLGConstraint right = opconstraint.getRightOperand();

				CLGConstraint notright = this.Demongan((CLGOperatorNode) opconstraint.getRightOperand());

				CLGGraph A = parseConstraintNew(left, criterion);

				CLGGraph notA = parseConstraintNew(notleft, criterion);

				CLGGraph B = parseConstraintNew(right, criterion);

				CLGGraph notB = parseConstraintNew(notright, criterion);

				notA.graphAnd(notB);

				A.graphAnd(B);

				notA.graphOr(A);
				return A;
			}
		}
		// }

		return new CLGGraph(constraint);
	}

	public CLGGraph CriterionTransformerOnlyParseConstraint(CLGGraph clg, Criterion criterion) {
		String[] clgindex = clg.getConstraintCollection().keySet().toString()
				.substring(1, clg.getConstraintCollection().keySet().toString().length() - 1).split(", ");
		int size = clg.getConstraintCollection().size();
		int a = Integer.parseInt(clgindex[0]);
		CLGConstraint Constraint = clg.getConstraintNodeById(a).getConstraint();
		CLGGraph resultclg = parseConstraintNew(Constraint, criterion);

		CLGStartNode clgPreStartNode = (CLGStartNode) clg.getStartNode();

		((CLGStartNode) resultclg.getStartNode()).setClassName(clgPreStartNode.getClassName());
		((CLGStartNode) resultclg.getStartNode()).setMethodName(clgPreStartNode.getMethodName());
		((CLGStartNode) resultclg.getStartNode()).setRetType(clgPreStartNode.getReturnType());
		((CLGStartNode) resultclg.getStartNode()).setIsConstructor(clgPreStartNode.isConstructor());
		((CLGStartNode) resultclg.getStartNode()).setClassAttributes(clgPreStartNode.getClassAttributes());
		((CLGStartNode) resultclg.getStartNode()).setMethodParameters(clgPreStartNode.getMethodParameters());
		((CLGStartNode) resultclg.getStartNode()).setMethodParameterTypes(clgPreStartNode.getMethodParameterTypes());

		return resultclg;
	}

}
