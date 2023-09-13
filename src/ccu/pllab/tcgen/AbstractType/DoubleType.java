package ccu.pllab.tcgen.AbstractType;

import java.util.HashSet;
import java.util.LinkedHashMap;

import ccu.pllab.tcgen.CBTCGUtility.Utility;

public class DoubleType extends RealType {

	static double intDomainLowerBound = 0.0;
	static double intDomainUpperBound = 2147483647.0;

	static private String labelingDoubleCLP = "labeling_Double(DoubleList, " + labelingType + "):-\r\n"
			+ "	(foreach(Double, DoubleList),\r\n" + "	param(" + labelingType + ")\r\n" + "	do\r\n"
			+ "	Double:: 0.0 .. 2147483647.0,\r\n" + "	(LabelingType = \"mid\" -> (locate([Double],1e-15));\r\n"
			+ "	LabelingType = \"dcl\" -> true\r\n" + "	)).\r\n";

	static private String transformDoubleCLP = "transform_Double([],[]).\r\n"
			+ "transform_Double([Double|DoubleList],[NewDouble|DoubleTransformList]):-\r\n"
			+ "	get_var_bounds(Double,Double_L, Double_H),\r\n" + "	NewDouble = [Double_L, Double_H],\r\n"
			+ "	transform_Double(DoubleList,DoubleTransformList).\r\n";

	public DoubleType() {
		super.typeID = "double";
		super.typeName = "double";
		labelingPriority = 1;
	}

	@Override
	public String getType() {
		return "double";
	}

	@Override
	public String toString() {
		return "double";
	}

	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		String obj_name = obj.substring(0, 1).toUpperCase() + obj.substring(1);
		return "[" + obj_name + "] :: 0.0 .. 2147483647.0 "; // 龟计Τ璽计办Τ拜肈既タ计
		// return "[" + obj_name + "] :: 1.0 .. 128.0 ";
	}

	@Override
	public DoubleType clone() {
		DoubleType cloneObj = new DoubleType();
		return cloneObj;
	}

	@Override
	public void genDclLabelingCLP(LinkedHashMap<String, String> labelingMap, LinkedHashMap<String, String> dclArr,
			LinkedHashMap<String, String> labelingArr) {
		String typeNameFix = Utility.titleToUppercase(this.getType());
		labelingMap.put(typeNameFix, labelingDoubleCLP);
		dclArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Double([Ele], \"dcl\");");
		labelingArr.put(typeNameFix, "\r\n\t\tEleType=\"" + typeNameFix + "\"->labeling_Double([Ele], \"random\");");
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
		return this.transformDoubleCLP;
	}

}
