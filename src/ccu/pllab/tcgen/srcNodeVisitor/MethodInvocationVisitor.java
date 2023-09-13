package ccu.pllab.tcgen.srcNodeVisitor;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractCLG.CLGConstraintNode;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class MethodInvocationVisitor extends JAVA2CLG implements SrcNodeVisit {
	CLGNode clgNode;
	CLGConstraint constraint;
	CLGGraph clgGraph;

	/*************************************************/
	public boolean visit(MethodInvocation node) {
		String obj = node.getExpression().toString();
		String callMethodName = node.getName().toString();
		ArrayList<CLGConstraint> arg = new ArrayList<CLGConstraint>();
		for (int i = 0; i < node.arguments().size(); i++) {
			CLGConstraint argCons;
			if (node.arguments().get(i) instanceof Expression) {
				argCons = BasicExpVisitor.VisitorAssign((Expression) node.arguments().get(i));
				arg.add(argCons);
			}
		}

		boolean isClass = false;
		if (Utility.titleToUppercase(obj).equals(obj))
			isClass = true;

		// 取得目前所在method
		ASTNode temp = node.getParent();
		while (!(temp instanceof MethodDeclaration)) {
			temp = temp.getParent();
		}
		MethodDeclaration local_method = (MethodDeclaration) temp;
		VariableType objType = Utility.getObjType(obj, local_method.getName().toString(), symbolTable, typeTable,
				false);
		constraint = new CLGMethodInvocationNode(
				(isClass ? new CLGClassNode(obj, objType) : new CLGObjectNode(obj, objType)), callMethodName, arg,
				Utility.getMethodType(objType, callMethodName, typeTable));

		clgNode = new CLGConstraintNode(constraint);
		return false;
	}

	@Override
	public CLGNode getNode() {
		// TODO Auto-generated method stub
		return clgNode;
	}

	public CLGConstraint getConstraint() {
		return constraint;
	}

	public void setClassName(String className) {
	}

	@Override
	public CLGGraph getCLGGraph() {
		// TODO Auto-generated method stub
		return this.clgGraph;
	}
}
