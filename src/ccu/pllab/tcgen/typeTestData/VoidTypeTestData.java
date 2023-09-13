package ccu.pllab.tcgen.typeTestData;

import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;

public class VoidTypeTestData  extends TypeTestData{

	@Override
	public String transformCLPData() {
		return "[]";
	}

	@Override
	public String getValueString() {
		return "";
	}

	@Override
	public VariableType getType() {
		return new VoidType();
	}

}
