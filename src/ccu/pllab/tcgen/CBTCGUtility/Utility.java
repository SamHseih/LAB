package ccu.pllab.tcgen.CBTCGUtility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import ccu.pllab.tcgen.AbstractCLG.CLGConnectionNode;
import ccu.pllab.tcgen.AbstractCLG.CLGConstraintNode;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractType.ArrayType;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;

public class Utility {

	/*
	 * 將常用的功能函式統一寫至此，不用到處寫 可使用"Utility.xxx"呼叫使用
	 */

	// 字首改小寫
	public static String titleToLowercase(String str) {
		if (str == null || str.isEmpty())
			return "";
		return str.toLowerCase().charAt(0) + str.substring(1);
	}

	// 字首改大寫
	public static String titleToUppercase(String str) {
		if (str == null || str.isEmpty())
			return "";
		return str.toUpperCase().charAt(0) + str.substring(1);
	}

	// 判斷是否為數字
	public static boolean isNumeric(String str) {
		if (str.isEmpty())
			return false;
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	// 去除字首的指定字串(若字首不符合則不影響
	public static String delHeadRedundantSymbol(String str, String symbol) {
		if (str.equals(symbol))
			return "";
		else if (str.length() > 1 && str.length() > symbol.length()) {
			for (int i = 0; i < symbol.length(); i++) {
				if (str.charAt(i) != symbol.charAt(i)) {
					return str;
				}
			}
			return str.substring(symbol.length(), str.length());
		}
		return str;
	}

	// 去除字尾的指定字串(若字尾不符合則不影響
	public static String delEndRedundantSymbol(String str, String symbol) {
		if (str == null || str.equals(symbol))
			return "";
		if (str.length() > 1 && str.length() > symbol.length()) {
			for (int i = 0; i < symbol.length(); i++) {
				if (str.charAt(str.length() - i - 1) != symbol.charAt(symbol.length() - i - 1)) {
					return str;
				}
			}
			return str.substring(0, str.length() - symbol.length());
		}
		return str;
	}

	/*
	 * 取得變數型態 順序: method下參數->屬性 區域變數未處理 若有this，參數isAtt設為true
	 * 白箱改到一半時寫的，白箱改掉可刪
	 */
	public static VariableType getObjType(String obj, String methodName, SymbolTable sym, TypeTable typeTab,
			boolean isAtt) {
		// 在目前method下尋找
		if (!isAtt) {
			for (String symMethodName : sym.getMethod().keySet()) {
				if (symMethodName.equals(methodName)) {
					for (String arg : sym.getMethod().get(symMethodName).getArgument().keySet()) {
						// 在method參數找到obj同名變數
						if (obj.equals(arg)) {
							return sym.getMethod().get(symMethodName).getArgument().get(arg).getType();
						}
					}
				}
			}
		}
		// 在屬性下尋找
		for (String symAtt : sym.getAttribute().keySet()) {
			if (obj.equals(symAtt)) {
				return sym.getAttribute().get(symAtt).getType();
			}
		}

		return new VoidType();
	}

	/*
	 * 取得函式回傳型態 跟據傳入的呼叫函式的物件的型去typeTable下尋找該函式的回傳型態
	 * 白箱改到一半時寫的，白箱改掉可刪
	 */
	public static VariableType getMethodType(VariableType objType, String methodName, TypeTable typeTab) {
		// 找到該物件類別的symbolTable
		SymbolTable classSym = objType.getSymbolTable();
		if (classSym != null) {
			for (String classSymMethodName : classSym.getMethod().keySet()) {
				if (methodName.equals(classSymMethodName)) {
					return classSym.getMethod().get(classSymMethodName).getReturnType();
				}
			}
		}
		return new VoidType();
	}

	// if there isn't "this"/"self" in front of the var, check it is attribute or not
	public static boolean isAtt(String varName, SymbolTable symbolTab, String methodName) {
		if (symbolTab.getAttribute().containsKey(varName)) {
			// there isn't same name argument in the method
			if (!(symbolTab.getMethod().containsKey(methodName)
					&& symbolTab.getMethod().get(methodName).getArgument().containsKey(varName))) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPre(String varName) {
		if (varName.length() <= 4)
			return false;
		String pre = "_pre";
		for (int i = 0; i < 4; i++) {
			if (varName.charAt(varName.length() - 4 + i) != pre.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	// generate array dimension clp variable
	public static String genArrayDimVar(SymbolTable sym, boolean isConstructor, boolean containException,
			String methodName, int solveTime) {
		String retStr = "";
		String arrDimCLP = "";
		// self att array dim
		for (Entry<String, VariableToken> att : sym.getAttribute().entrySet()) {
			if (att.getValue().getType() instanceof ArrayType) {
				if (!isConstructor) {
					arrDimCLP += "\r\n_Self_" + solveTime + "_pre_" + att.getValue().getVariableName() + "Dim="
							+ att.getValue().getType().getArrayDim("Self_" + solveTime + "_pre_", false) + ",";
				}
				if (!containException) {
					arrDimCLP += "\r\n_Self_" + solveTime + "_" + att.getValue().getVariableName() + "Dim="
							+ att.getValue().getType().getArrayDim("Self_" + solveTime + "_", false) + ",";
				}
			}
		}
		// arg array dim
		for (Entry<String, VariableToken> arg : sym.getMethod().get(methodName).getArgument().entrySet()) {
			if (arg.getValue().getType() instanceof ArrayType) {
				ArrayList<String> argPreDim = new ArrayList<>();
				ArrayList<String> argDim = new ArrayList<>();
				for (String dim : arg.getValue().getType().getArrayDim("", false)) {
					if (!dim.matches("[0-9]*")) {
						argPreDim.add(dim + "_" + solveTime + "_pre");
						argDim.add(dim + "_" + solveTime);
					} else {
						argPreDim.add(dim);
						argDim.add(dim);
					}
				}

				arrDimCLP += "\r\n_" + Utility.titleToUppercase(arg.getValue().getVariableName()) + "_" + solveTime
						+ "_preDim=" + argPreDim + ",";
				arrDimCLP += "\r\n_" + Utility.titleToUppercase(arg.getValue().getVariableName()) + "_" + solveTime
						+ "Dim=" + argDim + ",";
			}
		}

		VariableType returnType = sym.getMethod().get(methodName).getReturnType();
		ArrayList<String> retDim = new ArrayList<>();
		if (returnType instanceof ArrayType) {
			for (String dim : returnType.getArrayDim("", false)) {
				if (!dim.matches("[0-9]*")) {
					retDim.add(dim + "_" + solveTime);
				} else {
					retDim.add(dim);
				}
			}

		}

		if (retDim.size() > 0) {
			arrDimCLP += "\r\n_" + "Rel_" + solveTime + "Dim=" + retDim + ",";
		}
		retStr += arrDimCLP;
		return retStr;
	}

	// delete \t,\r,\n and the space at the head and tail
	public static String delSpecialChar(String str) {
		if (str == null)
			return "";
		str = str.replaceAll("\t", "");
		str = str.replaceAll("\r", "");
		str = str.replaceAll("\n", "");
		return str.trim();
	}

	public static void initCLGNodeID() {
		CLGNode.initID();
		CLGConstraintNode.initID();
		CLGConnectionNode.initID();
	}

	public static String delHeadTailBrackets(String str) {
		if (str.indexOf("[") == 0 && str.lastIndexOf("]") == str.length() - 1) {
			return str.substring(1, str.length() - 1);
		} else
			return str;
	}
}
