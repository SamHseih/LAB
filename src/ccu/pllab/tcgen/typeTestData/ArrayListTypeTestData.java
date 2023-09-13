package ccu.pllab.tcgen.typeTestData;

import java.util.ArrayList;

import ccu.pllab.tcgen.AbstractType.VariableType;

public class ArrayListTypeTestData extends TypeTestData {

	private VariableType type;
	private ArrayList<TypeTestData> element;
	
	public ArrayListTypeTestData(ArrayList<TypeTestData> ele, VariableType t) {
		this.element = ele;
		this.type = t;
	}
	
	@Override
	public VariableType getType() {
		return type;
	}
	
	@Override
	public String transformCLPData() {

		String retstr = "";
		for (TypeTestData ele : this.element) {
			retstr += ele.transformCLPData() + ", ";
		}
		retstr = retstr.endsWith(", ") ? "[" + retstr.substring(0, retstr.length() - 3) + "]" : "["+ retstr + "]";

		return retstr;
	}
	
	@Override 
	public String getValueString() {
		String retstr = "";
		for (TypeTestData ele : this.element) {
			retstr += ele.getValueString() + ", ";
		}
		retstr = retstr.endsWith(", ") ? "[" + retstr.substring(0, retstr.length() - 3) + "]" : "["+ retstr + "]";

		return retstr;
	}

}
