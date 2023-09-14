package ccu.pllab.tcgen.SymbolTable;

import java.util.LinkedHashMap;

import ccu.pllab.tcgen.AbstractSyntaxTree.VariableRefExp;

public class SymbolTable {
	private String className;
	// 2021-01-28 dai -> 資料只存一份
	private LinkedHashMap<String, VariableToken> attribute = new LinkedHashMap<String, VariableToken>();
	private LinkedHashMap<String, MethodToken> method = new LinkedHashMap<String, MethodToken>();
	private LinkedHashMap<String, VariableRefExp> localVariable = new LinkedHashMap<String, VariableRefExp>();

	public SymbolTable() {
		this.className = "";
	}

	public SymbolTable(String className) {
		this.className = className;

	}

	public void addAttribute(VariableToken variable) {
		attribute.put(variable.variableName, variable);
	}

	public void addLocalVariable(VariableRefExp variable) {

		localVariable.put(variable.getVariable(), variable);
	}

	public void addMethod(MethodToken method) {
		this.method.put(method.methodName, method);
	}

	public LinkedHashMap<String, VariableToken> getAttribute() {
		return this.attribute;
	}

	public String getClassName() {
		return this.className;
	}

	public LinkedHashMap<String, MethodToken> getMethod() {
		return this.method;
	}

	public LinkedHashMap<String, VariableRefExp> getLocalVariable() {
		return this.localVariable;
	}

	public MethodToken getMethodToken(String methodName) {
		if (this.method.containsKey(methodName))
			return this.method.get(methodName);
		else
			return new MethodToken("");
	}

	// cloning the method of symbolTable may lead to infinite recursive
	public SymbolTable cloneOnlyAtt() {
		SymbolTable newSym = new SymbolTable(this.className);
		for (String varName : this.getAttribute().keySet())
			newSym.addAttribute(this.getAttribute().get(varName).clone());
		return newSym;
	}

	public VariableToken getSomeOneAttrible(String name) {
		return this.attribute.get(name);
	}

	public VariableRefExp getSomeOneLocalVariable(String name) {
		return this.localVariable.get(name);
	}
}
