package ccu.pllab.tcgen.srcNodeVisitor;

import org.eclipse.jdt.core.dom.ASTVisitor;

import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;

/***********
 * 
 * Extends ASTVisitor.
 * 
 * 
 * 
 * @author pllab
 *
 */
public class JAVA2CLG extends ASTVisitor {
	static SymbolTable symbolTable;
	static TypeTable typeTable;
	static String visitingMethod = "";

	public static void setSymbolTable(SymbolTable sym) {
		symbolTable = sym;
	}

	public static void setTypeTable(TypeTable typeTab) {
		typeTable = typeTab;
	}

	public static void setvisitingMethod(String methodName) {
		visitingMethod = methodName;
	}
}
