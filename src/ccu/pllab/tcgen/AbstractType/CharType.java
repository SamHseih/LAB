package ccu.pllab.tcgen.AbstractType;

import java.util.LinkedHashMap;

import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class CharType extends VariableType {

	static int intDomainLowerBound = 0;
	static int intDomainUpperBound = 127;

	static private String labelingCharCLP = "labeling_Char(CharList, " + labelingType + "):-\r\n"
			+ "	(foreach(Char, CharList),\r\n" + "	param(" + labelingType + ")\r\n" + "	do\r\n" + "	Char:: "
			+ intDomainLowerBound + ".." + intDomainUpperBound + ",\r\n" + "	(" + labelingType + "=\"mid\"->\r\n"
			+ "	(indomain(Char,median));\r\n" + "	" + labelingType + "=\"labeling\"->labeling([Char]);\r\n" + "	"
			+ labelingType + "=\"random\"->indomain(Char, random);\r\n" + "	" + labelingType + "=\"dcl\"->true)).\r\n";

	static private String transformCharCLP = "transform_Char([],[]).\r\n"
			+ "transform_Char([Char|CharList],[NewChar|CharTransformList]):-\r\n" + "	string_list(Char,NewChar),\r\n"
			+ "	transform_Char(CharList,CharTransformList).\r\n";

	public CharType() {
		super.typeID = "char";
		super.typeName = "char";
		labelingPriority = 1;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "char";
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		String obj_name = obj.substring(0, 1).toUpperCase() + obj.substring(1);
		return "[" + obj_name + "] :: 0 .. 127";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "char";
	}

	@Override
	public CharType clone() {
		CharType cloneObj = new CharType();
		return cloneObj;
	}

	@Override
	public void genDclLabelingCLP(LinkedHashMap<String, String> labelingMap, LinkedHashMap<String, String> dclArr,
			LinkedHashMap<String, String> labelingArr) {
		String typeNameFix = Utility.titleToUppercase(this.getType());
		labelingMap.put(typeNameFix, labelingCharCLP);
		dclArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Char([Ele], \"dcl\");");
		labelingArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Char([Ele], \"random\");");
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
		return this.transformCharCLP;
	}
}
