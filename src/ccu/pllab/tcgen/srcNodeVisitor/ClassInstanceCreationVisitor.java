package ccu.pllab.tcgen.srcNodeVisitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.Expression;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGClassNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGMethodInvocationNode;

public class ClassInstanceCreationVisitor extends JAVA2CLG implements SrcNodeVisit {
	private CLGNode clgNode;
	private CLGConstraint constraint;

	/*************************************************/
	public boolean visit(ClassInstanceCreation node) {
		// 加入參數
		ArrayList<CLGConstraint> arg = new ArrayList<>();
		for (int i = 0; i < node.arguments().size(); i++) {
			if (node.arguments().get(i) instanceof Expression)
				arg.add(BasicExpVisitor.VisitorAssign((Expression) node.arguments().get(i)));
		}

		String objClassName = node.getType().toString();
		CLGMethodInvocationNode newClassObj = new CLGMethodInvocationNode(
				new CLGClassNode(objClassName, JAVA2CLG.typeTable.get(objClassName, null)), objClassName, arg);
		newClassObj.setIsNewObj(true);

		constraint = newClassObj;
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
