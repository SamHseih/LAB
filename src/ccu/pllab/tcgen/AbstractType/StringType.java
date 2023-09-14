package ccu.pllab.tcgen.AbstractType;

import java.util.LinkedHashMap;

import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class StringType extends VariableType {

	CharType element;
	String size;
	static private String labelingStringCLP = "labeling_String(StringList, " + labelingType + "):-\r\n"
			+ "	(foreach(String, StringList),\r\n" + "	param(" + labelingType + ")\r\n" + "	do\r\n"
			+ "	String = [StringLen|CharList],\r\n" + "	StringLen#>= 0,\r\n" + "	(LabelingType = \"mid\" ->(\r\n"
			+ "	labeling_Int([StringLen],\"labeling\"),\r\n" + "	dcl_1dChar_array(CharList,StringLen),\r\n"
			+ "	labeling_Char(CharList,\"random\"));\r\n" + "	LabelingType = \"dcl\"-> \r\n"
			+ "	labeling_Int([StringLen],\"dcl\"))\r\n" + ").\r\n";

	static private String transformStringCLP = "transform_String([],[]).\r\n"
			+ "transform_String([String|StringList],[NewString|StringTransformList]):-\r\n"
			+ "	String = [StringLen|StringCharList],\r\n"
			+ "	(StringCharList = [] -> NewString = \"\" ;string_list(NewString, StringCharList)),\r\n"
			+ "	transform_String(StringList,StringTransformList).\r\n";

	public StringType() {
		super.typeID = "String";
		super.typeName = "String";
		labelingPriority = 2;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "String";
	}

	@Override
	public String genDomainCLP(String obj) {
		String nameCLP = Utility.titleToUppercase(obj);

		String stringSizestr = nameCLP + "Size";
		String stringListstr = nameCLP + "List";
		String stringObjectstr = nameCLP + " = [" + stringSizestr + "|" + stringListstr + "]";

		return stringSizestr + " :: 0..32767,\ndcl_1dChar_array(" + stringListstr + "," + stringSizestr + "),\n"
				+ stringObjectstr + ",\n";
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "String";
	}

//	@Override
//	// isTemp: string 需要temp 傳入method，因為要從List 轉回字串
//	public String flattenObjAddSomeString(String objName, TypeTable typeTab, boolean isPre, String str) {
//		return Utility.titleToUppercase(objName + (isPre ? "_pre" : "") + str);
//	}

	@Override
	public StringType clone() {
		StringType cloneObj = new StringType();
		return cloneObj;
	}

	@Override
	public boolean eqauls(VariableType type) {
		// TODO Auto-generated method stub
		return type.getType().equals("String");
	}

	@Override
	public void genDclLabelingCLP(LinkedHashMap<String, String> labelingMap, LinkedHashMap<String, String> dclArr,
			LinkedHashMap<String, String> labelingArr) {
		String typeNameFix = Utility.titleToUppercase(this.getType());
		labelingMap.put(typeNameFix, labelingStringCLP);
		dclArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_String([Ele], \"dcl\");");
		labelingArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_String([Ele], \"random\");");
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
		return this.transformStringCLP;
	}
}
