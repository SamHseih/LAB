package ccu.pllab.tcgen.AbstractType;

import java.util.HashSet;
import java.util.LinkedHashMap;

import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class FloatType extends RealType {

	static double intDomainLowerBound = 0.0;
	static double intDomainUpperBound = 32767.0;

	static private String labelingFloatCLP = "labeling_Float(FloatList, " + labelingType + "):-\r\n"
			+ "	(foreach(Float, FloatList),\r\n" + "	param(" + labelingType + ")\r\n" + "	do\r\n"
			+ "	Float:: 0.0 .. 2147483647.0,\r\n" + "	( LabelingType = \"mid\" ->(locate([Float],1e-7));\r\n"
			+ "		LabelingType = \"dcl\" -> true\r\n" + "	)).\r\n";

	static private String transformFloatCLP = "transform_Float([],[]).\r\n"
			+ "transform_Float([Float|FloatList],[NewFloat|FloatTransformList]):-\r\n"
			+ "	get_var_bounds(Float,Float_L, Float_H),\r\n" + "	NewFloat = [Float_L, Float_H],\r\n"
			+ "	transform_Float(FloatList,FloatTransformList).\r\n";

	public FloatType() {
		super.typeID = "float";
		super.typeName = "float";
		labelingPriority = 1;
	}

	@Override
	public String getType() {
		return "float";
	}

	@Override
	public String toString() {
		return "float";
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		String obj_name = obj.substring(0, 1).toUpperCase() + obj.substring(1);
		return "[" + obj_name + "] :: -32768.0 .. 32767.0 ";
	}

	@Override
	public FloatType clone() {
		FloatType cloneObj = new FloatType();
		return cloneObj;
	}

	@Override
	public void genDclLabelingCLP(LinkedHashMap<String, String> labelingMap, LinkedHashMap<String, String> dclArr,
			LinkedHashMap<String, String> labelingArr) {
		String typeNameFix = Utility.titleToUppercase(this.getType());
		labelingMap.put(typeNameFix, labelingFloatCLP);
		dclArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Float([Ele], \"dcl\");");
		labelingArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Float([Ele], \"random\");");
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
		return this.transformFloatCLP;
	}

}
