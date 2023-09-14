package ccu.pllab.tcgen.typeTestData;

import ccu.pllab.tcgen.AbstractType.BooleanType;
import ccu.pllab.tcgen.AbstractType.VariableType;

public class BooleanTypeTestData extends TypeTestData {

	private boolean value;

	public BooleanTypeTestData(String val) {
		this.value = (val.equals("true")) ? true : false;
	}

	@Override
	public VariableType getType() {
		return new BooleanType();
	}

	@Override
	public String transformCLPData() {
		return (this.value) ? "1" : "0";
	}

	public String getValueString() {
		return (this.value) ? "true" : "false";
	}

}
