package ccu.pllab.tcgen.typeTestData;

import java.util.Arrays;

import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class StringTypeTestData extends TypeTestData {

	private String value;

	public StringTypeTestData(String v) {
		super();

		if(v.equals("\"\"")) {
			this.value = "";
		}else {
			this.value = v.replace("\\\"", "\"").substring(1, v.length() - 1);
		}
	}
	
	@Override
	public VariableType getType() {
		return new StringType();
	}

	public String transformCLPData() {

		String asciicodeList = "";

		for (char v : this.value.toCharArray()) {
			asciicodeList += (int) v + ",";
		}

		asciicodeList = Utility.delEndRedundantSymbol(asciicodeList, ",");
		
		return "[" + this.value.length() + ((asciicodeList.length() == 0) ? "" : "," + asciicodeList) + "]";
	}
	
	@Override
	public String getValueString() {
		 
		return "\\\"" + value + "\\\"";
	}
	
	public String getValue() {
		return this.value.replace("/", "//"); //л▌зя
	}
}
