package ccu.pllab.tcgen.typeTestData;

import java.util.ArrayList;

import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.VariableType;

public class UserDefinedTypeTestData extends TypeTestData {

	private VariableType type;
	private ArrayList<TypeTestData> attribute;

	public UserDefinedTypeTestData(ArrayList<TypeTestData> attri, VariableType t) {
		this.attribute = attri;
		this.type = t;
	}

	@Override
	public VariableType getType() {
		return type;
	}

	@Override
	public String transformCLPData() {

		String retstr = "";
		for (TypeTestData attri : this.attribute) {
			retstr += attri.transformCLPData() + ", ";
		}
		retstr = retstr.endsWith(", ") ? "[" + retstr.substring(0, retstr.length() - 2) + ']' : '[' + retstr + ']';
		return retstr;
	}

	@Override
	public String getValueString() {

		String retstr = "";

		for (TypeTestData attri : this.attribute) {
			retstr += attri.getValueString() + ", ";
		}

		retstr = retstr.endsWith(", ") ? "[" + retstr.substring(0, retstr.length() - 2) + ']' : '[' + retstr + ']';
		return retstr;
	}

	public TypeTestData changeTypeTestData(VariableType type) {

		if (type instanceof StringType) {
			String str = "";
			if (this.attribute.get(0) instanceof VoidTypeTestData) {
				return new VoidTypeTestData();
			} else {
				for (int i = 1; i < this.attribute.size(); i++) {
					char c = (char) ((IntTypeTestData) this.attribute.get(i)).getValue();
					str += "\""+c+"\"";
				}
				return new StringTypeTestData(str);
			}
		} else {//¨ä¥L «Ý¸É
			return this;
		}

	}
}
