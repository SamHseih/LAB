package ccu.pllab.tcgen.typeTestData;

import ccu.pllab.tcgen.AbstractType.VariableType;

public class ExceptionTestData extends TypeTestData {

	private String exceptionName;
	
	public ExceptionTestData(String ex) {
		this.exceptionName = ex;
	}
	
	@Override
	public VariableType getType() {
		return null; //л▌зя
	}
	
	@Override
	public String transformCLPData() {
		// TODO Auto-generated method stub
		return "\"" + this.exceptionName + "\"";
	}
	
	@Override
	public String getValueString() {
		return this.exceptionName;
	}

}
