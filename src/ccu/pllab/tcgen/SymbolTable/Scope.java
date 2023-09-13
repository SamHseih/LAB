package ccu.pllab.tcgen.SymbolTable;

import java.util.LinkedHashMap;

public abstract class Scope {
	private static int count = 0;
	private int id;
	protected LinkedHashMap<String, VariableToken> variableMap = new LinkedHashMap<>();

	public Scope() {
		this.id = count++;
	}

	public int getId() {
		return this.id;
	}

	public LinkedHashMap<String, VariableToken> getVariableMap() {
		return variableMap;
	}

	public void addVariable(VariableToken variable) {
		this.variableMap.put(variable.getVariableName(), variable);
	}

}
