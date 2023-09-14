package ccu.pllab.tcgen.typeTestData;

import ccu.pllab.tcgen.AbstractType.IntType;
import ccu.pllab.tcgen.AbstractType.VariableType;

public class IntTypeTestData extends TypeTestData {

	private int value;

	public IntTypeTestData(String intStr) {
		this.value = Integer.valueOf(intStr);
	}

	@Override
	public VariableType getType() {
		return new IntType();
	}

	@Override
	public String transformCLPData() {
		return "" + value;
	}

	@Override
	public String getValueString() {
		return "" + value;
	}

	public int getValue() {
		return this.value;
	}

}
