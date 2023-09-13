package ccu.pllab.tcgen.AbstractType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.CBTCGUtility.Utility;

public abstract class VariableType {
	String typeName;
	String typeID;
	protected SymbolTable symbolTable;
	protected int labelingPriority = 0;
	static String labelingType = "LabelingType";
	public static String splitVar = "split";
	public static String testDataStrRemain = "remain";// �ѤU���B�z���r��

	public VariableType() {
	}

	public void setSymbolTable(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	public String getTypeID() {
		return this.typeID;
	}

	public SymbolTable getSymbolTable() {
		return this.symbolTable;
	}

	public int getLabelingPriority() {
		return labelingPriority;
	}

	public abstract String getType();

	// ���ո}���ŧi���A�A�}�C���A�мg���禡
	public String getDclType() {
		return getType();
	}

	public abstract String toString();

	public abstract String genDomainCLP(String obj);

	// symbolTable only clone its attribute
	public abstract VariableType clone();

	public boolean eqauls(VariableType type) {
		// TODO Auto-generated method stub
		return type.getType().equals(this.typeName) || type.getTypeID().equals(this.typeID);
	}

	// ����󥭤�, clpVarTypeTable�����ӫ󥭤ƫ᪺CLP�ܼƫ��A
	// �}�C�|�B�~��@��size + pre����
	// isTemp: string �ݭntemp �ǤJmethod�A�]���n�qList ��^�r��

	/*
	 * public String flattenObj(String objName, TypeTable typeTab,
	 * LinkedHashMap<String, VariableType> clpVarInfoTable, boolean isPre) {
	 * clpVarInfoTable.put(Utility.titleToUppercase(objName) + (isPre ? "_pre" :
	 * ""), this); return Utility.titleToUppercase(objName + (isPre ? "_pre" : ""));
	 * }
	 */

	// basic type labeling clp defined in static attribute
	public void genDclLabelingCLP(LinkedHashMap<String, String> labelingMap, LinkedHashMap<String, String> dclArr,
			LinkedHashMap<String, String> labelingArr) {
		return;
	}

	public abstract String getTransformTypeCLP();

	public void genDclTransformCLP(LinkedHashMap<String, String> transformMap,
			LinkedHashMap<String, String> transformArr) {
		return;
	}

	// clp labeling method name
	public String getLabelingCLPMethodName(ArrayList<String> objList) {
		ArrayList<String> objFixList = new ArrayList<>();
		for (String s : objList) {
			objFixList.add(Utility.titleToUppercase(s));
		}
		return "\r\nlabeling_" + Utility.titleToUppercase(this.getType()) + "(" + objFixList + ", \"mid\"),";
	}

	public String getTransformCLPMethodName(ArrayList<String> objList) {
		ArrayList<String> objFixList = new ArrayList<>();
		ArrayList<String> newObjFixList = new ArrayList<>();
		for (String s : objList) {
			String[] sArr = s.split("_");
			String newObjname = sArr[0];
//			sArr[1].matches("[0-9]*");
			if (sArr.length >= 3)
				newObjname += "_" + sArr[2];
			objFixList.add(Utility.titleToUppercase(s));
			newObjFixList.add("New" + Utility.titleToUppercase(newObjname));
		}
		return "\r\n transform_" + Utility.titleToUppercase(this.getType()) + "(" + objFixList + ", " + newObjFixList
				+ "),";
	}

	public String getDclArrayCLPMethodName(String objName) {
		VariableType eleType = this;
		ArrayList<String> dimList = new ArrayList<>();
		while (eleType instanceof ArrayType) {
			dimList.add(((ArrayType) eleType).getSize());
			eleType = ((ArrayType) eleType).getElement();
		}
		String retStr = "";
		retStr += "\r\ndcl_array(" + objName + ", " + dimList + ", \"" + Utility.titleToUppercase(eleType.getType())
				+ "\"),";
		return retStr;
	}

	// arrayType override
	public ArrayList<String> getArrayDim(String fixName, boolean isPre) {
		return new ArrayList<>();
	}

	public String genInvCLP(String objName, boolean isPre) {
		return "";
	}

	/*
	 * ����󥭤� objName������W�١C isPre�u���b����objPre, argPre�ɤ~�|�t�~�[�[(�@��bAST��CLG�N�[�F�A���ݦA�B�z)
	 * haveBeenFlattened�����w�i�����ϥΪ����O�A�קK�]�����P�l�����V���p(bidirectional
	 * association)�Ӧ��󥭤ƹL�{�L�a�j��
	 * UserDefinedType override
	 */
	public String flattenObj(String objName, boolean isPre, boolean needGenInvCLP, HashSet<String> haveBeenFlattened,
			HashSet<String> realTypeVar) {
		return "";
	}

	/*
	 * �ˬd�ܼƭȰ� ���ѨM�Y�Ǳ��p�|���ܼƦ]�S���Q�������A���ܦ��Ŷ��X�A�blableing�ɷ|�Ĭ� �b����̫���j��]�w�Ȱ�
	 */
	// arrayType override
	public String checkDomain(String objName, boolean isPre) {
		String objNameFix = Utility.titleToUppercase(objName + (isPre ? "_pre" : ""));
		return "\r\nlabeling_" + Utility.titleToUppercase(this.getType()) + "([" + objNameFix + "], " + "\"dcl\"),";// declare
																													// variable(setting
																													// domain)
	}

	public HashMap<String, String> splitTestData(String testDatas) {
		HashMap<String, String> retMap = new HashMap<>();
		int[] symbolIndex = { testDatas.indexOf("]"), testDatas.indexOf(","), testDatas.indexOf(")") };
		Arrays.sort(symbolIndex);
		boolean haveEnd = false;
		for (int i = 0; i < symbolIndex.length && !haveEnd; i++) {
			if (symbolIndex[i] != -1) {
				retMap.put(splitVar, testDatas.substring(0, symbolIndex[i]));
				retMap.put(testDataStrRemain, testDatas.substring(symbolIndex[i]));
				haveEnd = true;
			}
		}
		if (!haveEnd) {
			retMap.put(splitVar, testDatas);
			retMap.put(testDataStrRemain, "");
		}
		return retMap;
	}

}
