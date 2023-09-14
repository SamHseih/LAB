package ccu.pllab.tcgen.SymbolTable;

import ccu.pllab.tcgen.AbstractType.*;

public class VariableToken {
	String variableName;
	VariableType type;
	String lowerValue;
	String highValue;

	public VariableToken(String name, VariableType vt) {
		// TODO Auto-generated constructor stub
		this.variableName = name;
		// this.type=type;
		this.lowerValue = "1";
		this.highValue = "1";
		this.type = vt;
	}

	public String getVariableName() {
		return this.variableName;
	}
	/*
	 * public String getTypeOld() { return this.type; }
	 */

	public VariableType getType() {
		if (this.type == null)
			return new VoidType();
		return this.type;
	}

	public VariableToken clone() {
		return new VariableToken(this.variableName, this.type.clone());
	}
}
