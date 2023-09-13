package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.Expression;

import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGObjectNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.AbstractType.VoidType;

public class BasicExpVisitor {
	// 將基本的node型別統一整理在這
	public static CLGConstraint VisitorAssign(Expression nodeExp) {
		switch (nodeExp.getClass().toString()) {
		case "class org.eclipse.jdt.core.dom.InfixExpression":
			InfixExpressionVisitor inFixVisitor = new InfixExpressionVisitor();
			nodeExp.accept(inFixVisitor);
			return inFixVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.NumberLiteral":
			NumberLiteralVisitor numberVisitor = new NumberLiteralVisitor();
			nodeExp.accept(numberVisitor);
			return numberVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.StringLiteral":
			StringLiteralVisitor stringVisitor = new StringLiteralVisitor();
			nodeExp.accept(stringVisitor);
			return stringVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.SimpleName":
			SimpleNameVisitor simpleNameVisitor = new SimpleNameVisitor();
			nodeExp.accept(simpleNameVisitor);
			return simpleNameVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.ArrayAccess":
			ArrayAccessVisitor arrayVisitor = new ArrayAccessVisitor();
			nodeExp.accept(arrayVisitor);
			return arrayVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.QualifiedName":
			QualifiedNameVisitor qualifiedNameVisitor = new QualifiedNameVisitor();
			nodeExp.accept(qualifiedNameVisitor);
			return qualifiedNameVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.ThisExpression":
			ThisExpressionVisitor thisExpressionVisitor = new ThisExpressionVisitor();
			nodeExp.accept(thisExpressionVisitor);
			return thisExpressionVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.MethodInvocation":
			MethodInvocationVisitor methodVisitor = new MethodInvocationVisitor();
			nodeExp.accept(methodVisitor);
			return methodVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.FieldAccess":
			FieldAccessVisitor fieldAccessVisitor = new FieldAccessVisitor();
			nodeExp.accept(fieldAccessVisitor);
			return fieldAccessVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.BooleanLiteral":
			BooleanLiteralVisitor booleanLiteralVisitor = new BooleanLiteralVisitor();
			nodeExp.accept(booleanLiteralVisitor);
			return booleanLiteralVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.PrefixExpression":
			PrefixExpressionVisitor prefixVisitor = new PrefixExpressionVisitor();
			nodeExp.accept(prefixVisitor);
			return prefixVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.PostfixExpression":
			PostfixExpressionVisitor postfixVisitor = new PostfixExpressionVisitor();
			nodeExp.accept(postfixVisitor);
			return postfixVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.ParenthesizedExpression":
			// 括號
			ParenthesizedExpressionVisitor ParenthesizedVisitor = new ParenthesizedExpressionVisitor();
			nodeExp.accept(ParenthesizedVisitor);
			return ParenthesizedVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.ClassInstanceCreation":
			// new class obj creation
			ClassInstanceCreationVisitor ClassInstanceCreationVisitor = new ClassInstanceCreationVisitor();
			nodeExp.accept(ClassInstanceCreationVisitor);
			return ClassInstanceCreationVisitor.getConstraint();
		case "class org.eclipse.jdt.core.dom.ArrayCreation":
			// 跑到這應該是錯的?(因由assignment那邊進入
			// 未處理
			return null;
		default:
			// 系統未處理的語法
			return new CLGObjectNode(nodeExp.toString(), new VoidType());
		}
	}
}
