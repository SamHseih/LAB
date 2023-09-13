package ccu.pllab.tcgen.transform;

import java.io.IOException;

import ccu.pllab.tcgen.AbstractSyntaxTree.AbstractSyntaxTreeNode;
import ccu.pllab.tcgen.AbstractSyntaxTree.PackageExp;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;

public class OCL2CLP {
	AbstractSyntaxTreeNode astNode;
	SymbolTable symbolTable;
	String clp = "";

	public OCL2CLP(AbstractSyntaxTreeNode ast, SymbolTable table) throws IOException {
		this.astNode = ast;
		this.symbolTable = table;
	}

	public void AST2CLP() {
		// 此類目前無使用，內容暫時註解掉
		String attribute = "";
		/*
		 * for (VariableToken variableToken : symbolTable.getAttribute()) attribute +=
		 * "," + variableToken.getVariableName().toUpperCase().charAt(0) +
		 * variableToken.getVariableName().substring(1); if (attribute.length() > 0)
		 * attribute = attribute.substring(1); astNode.preconditionAddPre();
		 * astNode.postconditionAddPre();
		 */
		for (AbstractSyntaxTreeNode ast : ((PackageExp) astNode).getTreeNode()) {
			/*
			 * String argument = ""; if (ast instanceof OperationContext) { String method =
			 * ((OperationContext) ast).getMethodName(); for (MethodToken func :
			 * symbolTable.getMethod()) if (func.getMethodName().equals(method)) { for
			 * (VariableToken arg : func.getArgument()) argument += "," +
			 * arg.getVariableName().toUpperCase().charAt(0) +
			 * arg.getVariableName().substring(1); if (argument.length() > 0) argument =
			 * argument.substring(1); break; } clp = ((OperationContext)
			 * ast).AST2CLP(attribute, argument); }
			 */

			/*
			 * ASTGraphNode pre=null; ASTGraphNode post=null;
			 * if(((OperationContext)ast).getStereoType().size()>0) {
			 * pre=((OperationContext)ast).getStereoType().get(0).getExpression().
			 * AST2ASTGraph(); clp+=pre.getCLP(attribute, argument); //
			 * post=((OperationContext)ast).getStereoType().get(1).getExpression().
			 * AST2ASTGraph(); // clp+=post.getCLP(attribute, argument);
			 * while(!pre.getVisited()) clp+=pre.getCLP(attribute, argument); }
			 */

		}
		System.out.println("OCL2CLP:" + clp);
	}

}
