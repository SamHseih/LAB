package ccu.pllab.tcgen.SymbolTable;

import java.util.HashMap;
import java.util.LinkedHashMap;

import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;

public class MethodToken extends Scope {
	String methodName;
	LinkedHashMap<String, VariableToken> argument = new LinkedHashMap<String, VariableToken>();
	private HashMap<Integer, LocalVarScope> scopeMap = new HashMap<>();// id, scope
	VariableType returnType;

	public MethodToken(String name) {
		super();
		this.methodName = name;
	}

	public void setReturnType(VariableType type) {
		this.returnType = type;
	}

	public void addArgument(VariableToken variable) {
		this.argument.put(variable.getVariableName(), variable);
	}

	public String getMethodName() {
		return this.methodName;
	}

	public VariableType getReturnType() {
		return this.returnType;
	}

	public LinkedHashMap<String, VariableToken> getArgument() {
		return this.argument;
	}

	public HashMap<Integer, LocalVarScope> getScopeMap() {
		return scopeMap;
	}

	public void addScope(LocalVarScope scope) {
		this.scopeMap.put(scope.getId(), scope);
	}

	public MethodToken clone() {
		MethodToken newMethod = new MethodToken(this.methodName);
		newMethod.setReturnType(this.returnType.clone());
		for (String varName : this.argument.keySet())
			newMethod.addArgument(this.argument.get(varName).clone());
		return newMethod;
	}
}
