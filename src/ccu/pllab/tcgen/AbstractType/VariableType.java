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
	public static String testDataStrRemain = "remain";// 剩下未處理的字串

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

	// 測試腳本宣告型態，陣列型態覆寫此函式
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

	// 物件扁平化, clpVarTypeTable紀錄個扁平化後的CLP變數型態
	// 陣列會額外實作把size + pre部分
	// isTemp: string 需要temp 傳入method，因為要從List 轉回字串

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
	 * 物件扁平化 objName為物件名稱。 isPre只有在產生objPre, argPre時才會另外加加(一般在AST轉CLG就加了，不需再處理)
	 * haveBeenFlattened紀錄已展平的使用者類別，避免因父類與子類雙向關聯(bidirectional
	 * association)照成扁平化過程無窮迴圈
	 * UserDefinedType override
	 */
	public String flattenObj(String objName, boolean isPre, boolean needGenInvCLP, HashSet<String> haveBeenFlattened,
			HashSet<String> realTypeVar) {
		return "";
	}

	/*
	 * 檢查變數值域 為解決某些情況會使變數因沒有被限制式限制，而變成空集合，在lableing時會衝突 在限制式最後先強制設定值域
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
