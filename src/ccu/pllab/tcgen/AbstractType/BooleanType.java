package ccu.pllab.tcgen.AbstractType;

import java.util.LinkedHashMap;

import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class BooleanType extends VariableType {

	static private String labelingBooleanCLP = "labeling_Boolean(BooleanList, " + labelingType + "):-\r\n"
			+ "	(foreach(Boolean, BooleanList),\r\n" + "	param(" + labelingType + ")\r\n" + "	do\r\n"
			+ "	Boolean:: " + " 0 .. 1,\r\n" + "	(" + labelingType + "=\"mid\"->\r\n"
			+ "	(indomain(Boolean,median));\r\n" + "	" + labelingType + "=\"labeling\"->labeling([Boolean]);\r\n"
			+ "	" + labelingType + "=\"random\"->indomain(Boolean, random);\r\n" + "	" + labelingType
			+ "=\"dcl\"->true)).\r\n";

	static private String transformBooleanCLP = "transform_Boolean([],[]).\r\n"
			+ "transform_Boolean([Boolean|BooleanList],[NewBoolean|BooleanTransformList]):-\r\n"
			+ "	( Boolean = 1 ->  NewBoolean = true ; NewBoolean = false ),\r\n"
			+ "	transform_Boolean(BooleanList,BooleanTransformList).\r\n";

	public BooleanType() {
		super.typeID = "Boolean";
		super.typeName = "Boolean";
		labelingPriority = 1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "boolean";
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		String nameCLP = Utility.titleToUppercase(obj);
		return nameCLP + ":: 0 .. 1";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "boolean";
	}

	@Override
	public BooleanType clone() {
		BooleanType cloneObj = new BooleanType();
		return cloneObj;
	}

	@Override
	public void genDclLabelingCLP(LinkedHashMap<String, String> labelingMap, LinkedHashMap<String, String> dclArr,
			LinkedHashMap<String, String> labelingArr) {
		String typeNameFix = Utility.titleToUppercase(this.getType());
		labelingMap.put(typeNameFix, labelingBooleanCLP);
		dclArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Boolean([Ele], \"dcl\");");
		labelingArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Boolean([Ele], \"random\");");
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
		return this.transformBooleanCLP;
	}

}