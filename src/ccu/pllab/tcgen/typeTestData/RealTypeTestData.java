package ccu.pllab.tcgen.typeTestData;

import ccu.pllab.tcgen.AbstractType.DoubleType;
import ccu.pllab.tcgen.AbstractType.VariableType;

public class RealTypeTestData extends TypeTestData {
	
	private String low;
	private String high;
	
	public RealTypeTestData(String l, String h) {
		this.low = l;
		this.high = h;
	}
	
	@Override
	public VariableType getType() {
		return new DoubleType(); // л▌зя
	}

	@Override
	public String transformCLPData() {
		return this.low + "__" + this.high;
	}
	
	
	public String getValueString() {
		return this.low + "__" + this.high;
	}
}
