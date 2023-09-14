package ccu.pllab.tcgen.typeTestData;

import java.util.ArrayList;

import ccu.pllab.tcgen.AbstractType.VariableType;

public abstract class TypeTestData{
	
	public abstract String transformCLPData();
	public abstract String getValueString();
	public abstract VariableType getType();
}
