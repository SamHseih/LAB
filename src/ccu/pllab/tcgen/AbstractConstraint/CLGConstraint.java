package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

public abstract class CLGConstraint {
	private static int constraint_count = 0;
	private String constraint_id;
	private String clone_id;

	public CLGConstraint() {
		this.constraint_id = "c" + constraint_count++;
	}

	public String getConstraintId() {
		return this.constraint_id;
	}

	// 待刪除
	public void setCloneId(String conId) {
		this.clone_id = conId;
	}

	// 待刪除
	public String getCloneId() {
		return this.clone_id;
	}

	// 20200916 dai
	public static void reset() {
		constraint_count = 0;
	}

	public abstract String getImgInfo();

	public abstract CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey);// 參數紀錄要寫入的CLP檔，回傳則是該點需回傳給上面的值

	public abstract CLGConstraint clone();

	public abstract String getCLPValue();// return a value name of the constraint in clp(array only returns array name)

	protected abstract void renameCLPValue(int count);// rename use, set varName/constraint(CLGVariableNode) with count
														// num

	public abstract void negConstraint();

	public abstract VariableType getCLPVarType();// return constraint type in clp

	public abstract void setCLPValue(String data);

	public String getConstratinImg() {
		String result = "";
		result += (this.getConstraintId() + " " + String.format(
				"[shape=\"ecllipse\", label=\"%s\",style = \"filled\",fillcolor = \"white\",xlabel=\"[%s]\"]" + "\n",
				this.getImgInfo(), this.getConstraintId()));
		return result;
	}

	public abstract void preconditionAddPre(SymbolTable sym, String methodName);

	public abstract String getConstraintName(); // 回傳限制點顯示在圖上的名字

	public abstract void renameWithMap(HashMap<String, String> attMap); // 根據表修改變數名

	public abstract CLGConstraint addPre();

	// renameUseVar
	// because clp is ssa(static single assignment), rename the variable which is
	// defined not only one time
	// "defineVariableSet" record defined variable additionally
	protected void renameUseVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
	}

	// renameDefVar
	// Defining variable need to record to variableSet(do rename too)
	// "defineVariableSet" record defined variable additionally
	protected void renameDefVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
	}

	/*
	 * rename的進入函式
	 * variableSet 紀錄變數定義次數
	 * defineVariableSet 紀錄定義過的變數(在函式CLP不會因遇到連接結點重置)
	 * isMethodCLP是否為函式CLP(CLGObjectNode/renameUseVar需要)
	 */
	public void renameVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
		this.renameUseVar(variableSet, defineVariableSet, isMethodCLP);
		return;
	}

	//函式CLP用，取得各限制點區域變數
	public ArrayList<String> getLocalVar() {
		return new ArrayList<>();
	};

	// pathInfo output use
	//呈現限制式在規格中的字串樣子
	public String getOriginalConName() {
		return "";
	}

	public String getItNum() {
		return "";
	}

	// 在SIP刪除限制式時，將變數rename的flag回朔，以確定最後變數正確的狀態
	public void reverseVarFlag(HashMap<String, Integer> variableSet) {
	}

	protected void reverseDefVar(HashMap<String, Integer> variableSet) {
	}

	protected void reverseMethodDefVar(HashMap<String, Integer> variableSet) {
	}

}