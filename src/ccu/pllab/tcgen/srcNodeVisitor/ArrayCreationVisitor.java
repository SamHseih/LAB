package ccu.pllab.tcgen.srcNodeVisitor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGMethodInvocationNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.transform.CLG2Path;

public class ArrayCreationVisitor extends JAVA2CLG implements SrcNodeVisit {
	CLGConstraint constraint;
	CLGNode clgNode;
	CLGGraph clgGraph;
	VariableType dclType;
	CLGMethodInvocationNode dimNode;// dimension

	// 陣列宣告只會存宣告的型態與宣告的dimesion
	public boolean visit(ArrayCreation node) {
		dclType = this.javaArrayTypeToAbstractType(node.getType().getElementType());// 陣列宣告型態
		ArrayList<CLGConstraint> dimList = new ArrayList<>();
		for (Object dim : node.dimensions()) {// 陣列維度list
			dimList.add(BasicExpVisitor.VisitorAssign((Expression) dim));
		}
//		dimNode = new CLGMethodInvocationNode(new CLGObjectNode("", new VoidType()),
//				"arrayDim", dimList);
		return false;
	}

	@Override
	public CLGNode getNode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CLGConstraint getConstraint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CLGGraph getCLGGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	public VariableType getDclType() {
		return dclType;
	}

	public CLGMethodInvocationNode getDimNode() {
		return dimNode;
	}

	private VariableType javaArrayTypeToAbstractType(Type arrayType) {
		switch (arrayType.toString()) {
		case "int":
			return new IntType();
		case "char":
			return new CharType();
		case "boolean":
			return new BooleanType();
		case "void":
			return new VoidType();
		default:
			return new UserDefinedType(arrayType.toString());
		}
	}

}
