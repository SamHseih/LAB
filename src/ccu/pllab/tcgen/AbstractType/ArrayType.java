package ccu.pllab.tcgen.AbstractType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.parctechnologies.eclipse.EclipseException;

import ccu.pllab.tcgen.AbstractSyntaxTree.AbstractSyntaxTreeNode;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.pathCLP2data.CLP2Data;
import ccu.pllab.tcgen.pathCLP2data.CLP2DataFactory;
import ccu.pllab.tcgen.typeTestData.ArrayTypeTestData;
import ccu.pllab.tcgen.typeTestData.TypeTestData;
import ccu.pllab.tcgen.typeTestData.UserDefinedTypeTestData;

public class ArrayType extends VariableType {

	public static int dclArrCount = 1;
	VariableType element;
	String size;

	// 我的
	public ArrayType(VariableType obj, String size) {
		element = obj;
		this.size = size;
		this.typeID = "Array[(" + element.typeName + ")]";
		this.typeName = "Array[(" + element.typeName + ")]";
		labelingPriority = 3;
		// dimension = dim;
	}

	// 我的
	public ArrayType(VariableType obj) {
		element = obj;
		this.typeID = "Array[(" + element.typeName + ")]";
		this.typeName = "Array[(" + element.typeName + ")]";
		labelingPriority = 3;
	}

	// 學長的
	public VariableType getElement() {
		// TODO Auto-generated method stub
		return element;
	}

	// 我的
	public String getSize() {
		// TODO Auto-generated method stub
		return this.size;
	}

	// 我自己加的
	public int getDim() {

		if (element instanceof ArrayType) {
			return ((ArrayType) element).getDim();
		} else {
			return 1;
		}
	}

	// 我自己加的
	public VariableType getUnitType() {

		if (element instanceof ArrayType) {
			return ((ArrayType) element).getUnitType();
		} else {
			return element;
		}
	}

	// 我自己加的
	public VariableType getNDimType(int n) {

		if (n >= 1) {
			if (element instanceof ArrayType) {
				n--;
				return ((ArrayType) element).getNDimType(n);
			} else {
				// type error
				return element;
			}
		} else
			return this;
	}

	// 我的
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Array[(" + element.typeName + ")]";
	}

	// 我的
	@Override
	public String genDomainCLP(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	// 我的
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return "Array[(" + element.getType() + ")]";
	}

	// 測試腳本宣告型態
	@Override
	public String getDclType() {
		return this.element.getDclType() + "[]";
	}

	// fix the size name of arrayType in every dimension
	public void fixArraySizeName(String objName) {
		if (!Utility.isNumeric(this.size))
			this.size = Utility.titleToUppercase(objName + "_" + this.size);
		if (this.element instanceof ArrayType)
			((ArrayType) this.element).fixArraySizeName(objName);
		return;
	}

	@Override
	public void genDclLabelingCLP(LinkedHashMap<String, String> labelingMap, LinkedHashMap<String, String> dclArr,
			LinkedHashMap<String, String> labelingArr) {
		this.element.genDclLabelingCLP(labelingMap, dclArr, labelingArr);
		return;
	}

	@Override
	public void genDclTransformCLP(LinkedHashMap<String, String> transformMap,
			LinkedHashMap<String, String> transformArr) {
		this.element.genDclTransformCLP(transformMap, transformArr);
		return;
	}

	// 學長的
	@Override
	public String getLabelingCLPMethodName(ArrayList<String> objList) {
		String objName = Utility.titleToUppercase(objList.get(0));//依序取出每個傳入的array進行labeling

		VariableType eleType = this;
		while (eleType instanceof ArrayType) {
			eleType = ((ArrayType) eleType).getElement();
		}
		return "\r\nlabeling_array(" + objName + ", _" + objName + "Dim, \""
				+ Utility.titleToUppercase(eleType.getType()) + "\"),";
	}

	// 仿學長的，做TestData轉換
	@Override
	public String getTransformCLPMethodName(ArrayList<String> objList) {
		String objName = Utility.titleToUppercase(objList.get(0));// array processes labeling only one variable every
																	// time
		ArrayList<String> objFixList = new ArrayList<>();
		ArrayList<String> newObjFixList = new ArrayList<>();
		for (String s : objList) {
			String[] sArr = s.split("_");
			String newObjname = sArr[0];
			sArr[1].matches("d*");
			if (sArr.length >= 3)
				newObjname += "_" + sArr[2];
			objFixList.add(Utility.titleToUppercase(s));
			newObjFixList.add("New" + Utility.titleToUppercase(newObjname));
		}

		VariableType eleType = this;
		while (eleType instanceof ArrayType) {
			eleType = ((ArrayType) eleType).getElement();
		}
		return "\r\ntransform_Array(" + objFixList + ", " + newObjFixList + ", \""
				+ Utility.titleToUppercase(eleType.getType()) + "\"),";
	}

	// self的pre加在fixName
	@Override
	public ArrayList<String> getArrayDim(String fixName, boolean isPre) {
		ArrayList<String> retList = new ArrayList<>();
		if (!Utility.isNumeric(this.size))
			retList.add(Utility.titleToUppercase(fixName + this.size + (isPre ? "_pre" : "")));
		else
			retList.add(this.size);
		retList.addAll(this.element.getArrayDim(fixName, isPre));
		return retList;
	}

	@Override
	public ArrayType clone() {
		ArrayType cloneObj = new ArrayType(this.element.clone(), this.size);
		return cloneObj;
	}

	public HashMap<String, String> genPreambleStr(TypeTestData target) {
		HashMap<String, String> retMap = new HashMap<>();
		String objConstructorStr = "";
		String objAdditionalStr = "";
		CLP2Data clp;
		try {
			clp = CLP2DataFactory.getEcl2DataInstance();

			objConstructorStr += "{";
			for (TypeTestData ele : ((ArrayTypeTestData) target).getElement()) {
				HashMap<String, String> elePreambleStrMap = new HashMap<>();
				if (ele instanceof ArrayTypeTestData) {
					elePreambleStrMap = ((ArrayType) element).genPreambleStr(ele);
					// target = elePreambleStrMap.get(testDataStrRemain); // 看不懂
					// if element is array too
					if (elePreambleStrMap.containsKey(CLP2Data.objConstructor))
						objConstructorStr += elePreambleStrMap.get(CLP2Data.objConstructor) + ", ";
				} else {
					// user defined type
					if (ele instanceof UserDefinedTypeTestData) {

						elePreambleStrMap = clp.preambleQuery(ele, ele.getType().getType(), ele.getType());
//							target = target.substring(target.indexOf("]") + 1).trim();
						objConstructorStr += elePreambleStrMap.get(CLP2Data.objName) + ", ";

						if (!(elePreambleStrMap.get(CLP2Data.objConstructor).equals("")))
							objAdditionalStr += "\r\n" + Utility.titleToUppercase(element.getType()) + ", "
									+ elePreambleStrMap.get(CLP2Data.objName) + "="
									+ elePreambleStrMap.get(CLP2Data.objConstructor) + ";";

						if (!(elePreambleStrMap.get(CLP2Data.objPreamble).equals("")))
							objAdditionalStr += "\r\n" + elePreambleStrMap.get(CLP2Data.objPreamble) + ";";

//							if (target.indexOf(",") == 0) {
//								target = target.substring(1).trim();
//								objConstructorStr += ", ";
//							}
					} else {// base type

//							int elementEnd = 0;
//							if (target.indexOf(")") != -1 && target.indexOf(",") != -1)
//								elementEnd = (target.indexOf(")") < target.indexOf(",") ? target.indexOf(")")
//										: target.indexOf(","));
//							else {
//								if (target.indexOf(")") != -1)
//									elementEnd = target.indexOf(")");
//								else if ((target.indexOf(",") != -1))
//									elementEnd = target.indexOf(",");
//							}
//							
//							objConstructorStr += target.substring(0, elementEnd);
//							target = target.substring(elementEnd).trim();

						objConstructorStr += ele.getValueString() + ", ";

//							if (target.indexOf(",") == 0) {
//								target = target.substring(1).trim();
//								objConstructorStr += ", ";
//							}

						elePreambleStrMap.put(CLP2Data.objAdditional, "");
					}
				}
//				objConstructorStr += "}";
				objAdditionalStr += elePreambleStrMap.get(CLP2Data.objAdditional);
//				target = target.trim();

//				if (target.indexOf(")") == 0) { //陣列結束
//					target = target.substring(1);
//					objConstructorStr += "}";
//				}
//				if (target.indexOf(",") == 0) {
//					target = target.substring(1).trim();
//					objConstructorStr += ", ";
//				}

				objAdditionalStr += elePreambleStrMap.get(CLP2Data.objAdditional);

				retMap.put(CLP2Data.objName, "arr" + dclArrCount);
//				retMap.put(testDataStrRemain, target);
				retMap.put(CLP2Data.objConstructor, objConstructorStr);
				retMap.put(CLP2Data.objPreamble, "");
				retMap.put(CLP2Data.objAdditional, objAdditionalStr);
			}

			objConstructorStr = Utility.delEndRedundantSymbol(objConstructorStr, ", ");
			objConstructorStr += "}";
			retMap.put(CLP2Data.objName, "arr" + dclArrCount);
//			retMap.put(testDataStrRemain, target);
			retMap.put(CLP2Data.objConstructor, objConstructorStr);
			retMap.put(CLP2Data.objPreamble, "");
			retMap.put(CLP2Data.objAdditional, objAdditionalStr);

//			else 
//			{
//				retMap.put(CLP2Data.objName, "");
//				retMap.put(testDataStrRemain, "");
//				retMap.put(CLP2Data.objConstructor, "");
//				retMap.put(CLP2Data.objPreamble, "");
//				retMap.put(CLP2Data.objAdditional, "");
//			}
		} catch (EclipseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retMap;
	}

	private ArrayTypeTestData ele(ArrayTypeTestData target) {
		// TODO Auto-generated method stub
		return null;
	}

	// 確定變數值域
	@Override
	public String checkDomain(String objName, boolean isPre) {
		String objNameFix = Utility.titleToUppercase(objName + (isPre ? "_pre" : ""));
		return "\r\nlabeling_array([" + objNameFix + "], " + this.getArrayDim("", isPre) + ", "
				+ Utility.titleToUppercase(this.getUnitType().getType()) + "),";// declare variable(setting domain)
	}

	@Override
	public String getTransformTypeCLP() {
		// 待改
		return "";
	}

}
