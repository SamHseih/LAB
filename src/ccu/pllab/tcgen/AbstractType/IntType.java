package ccu.pllab.tcgen.AbstractType;

import java.util.LinkedHashMap;

import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class IntType extends RealType {
	static int intDomainLowerBound = -1000;
	static int intDomainUpperBound = 1100;

	static private String labelingIntCLP = "labeling_Int(IntList, " + labelingType + "):-\r\n"
			+ "	(foreach(Int, IntList),\r\n" + "	param(" + labelingType + ")\r\n" + "	do\r\n" + "	Int:: "
			+ intDomainLowerBound + ".. " + intDomainUpperBound + ",\r\n" + "	(" + labelingType + "=\"mid\"->\r\n"
			+ "	(indomain(Int,median));\r\n" + "	" + labelingType + "=\"labeling\"->labeling([Int]);\r\n" + "	"
			+ labelingType + "=\"random\"->indomain(Int, random);\r\n" + "	" + labelingType + "=\"dcl\"->true)).\r\n";

	static private String transformIntCLP = "transform_Int([],[]).\r\n"
			+ "transform_Int([Int|IntList],[NewInt|IntTransformList]):-\r\n" + "	Int #= NewInt,\r\n"
			+ "	transform_Int(IntList,IntTransformList).\r\n";

	public IntType() {
		super.typeID = "int";
		super.typeName = "int";
		labelingPriority = 1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "int";
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		String obj_name = obj.substring(0, 1).toUpperCase() + obj.substring(1);
		// C-based
		return "[" + obj_name + "] :: -32768..32767";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "int";
	}

	@Override
	public IntType clone() {
		IntType cloneObj = new IntType();
		return cloneObj;
	}

	@Override
	public void genDclLabelingCLP(LinkedHashMap<String, String> labelingMap, LinkedHashMap<String, String> dclArr,
			LinkedHashMap<String, String> labelingArr) {
		String typeNameFix = Utility.titleToUppercase(this.getType());
		labelingMap.put(typeNameFix, labelingIntCLP);
		dclArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Int([Ele], \"dcl\");");
		labelingArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Int([Ele], \"random\");");
		return;
	}

	@Override
	public void genDclTransformCLP(LinkedHashMap<String, String> transformMap,
			LinkedHashMap<String, String> transformArr) {
		String typeNameFix = Utility.titleToUppercase(this.getType());
		transformMap.put(typeNameFix, this.getTransformTypeCLP());
		transformArr.put(typeNameFix,
				"\r\n\t\tEleType=\"" + typeNameFix + "\"->transform_" + typeNameFix + "([Ele], [NewEle]);");
		return;
	}

	@Override
	public String getTransformTypeCLP() {
		// TODO Auto-generated method stub
		return this.transformIntCLP;
	}

}
