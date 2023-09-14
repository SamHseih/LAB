package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;

import ccu.pllab.tcgen.AbstractType.BooleanType;
import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

public class CLGLiteralNode extends CLGConstraint {
	private String value;
	private VariableType type;

	public CLGLiteralNode(String value) {
		super();
		this.value = value;
		this.type = new VoidType();
	}

	public CLGLiteralNode(String value, VariableType type) {
		super();
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		if (this.type != null && this.type instanceof StringType) {

			if (this.value.length() > 2)
				return "\"" + this.value.substring(1, this.value.length() - 1) + "\"";
			else
				return "\"\"";

//			if (this.value.contains("\"")) {
//				return this.value;
//			}
//			if (this.value.contains("\'")) {// 我加的
//				return this.value.replaceAll("\'", "\"");
//			} else {
//				return "\"" + this.value + "\"";
//			}
		} else {
			return this.value;
		}
	}

	public String genStringListCLP() {

		String returnString = "";

		returnString += "string_list(" + this.getValue() + ", S_" + this.getConstraintId() + "_List),\n";
		returnString += "length( S_" + this.getConstraintId() + "_List" + ",S_" + this.getConstraintId() + "_Size),\n";
		returnString += "Literal_S_" + this.getConstraintId() + " = [ S_" + this.getConstraintId() + "_Size" + "| S_"
				+ this.getConstraintId() + "_List]";

		return returnString;

	}

	public String genBooleanTypeCLP() {

		String returnString = "";

		returnString += "Literal_B_" + this.value + "_" + this.getConstraintId() + " #= "
				+ (this.value.equals("true") ? "1" : "0");

		return returnString;

	}

	public String genInsertMathodCallStringListCLP() {

		String returnString = "";
		returnString += "Literal_S_" + this.getConstraintId();

		return returnString;

	}

	public String genInsertMathodCallBooleanTypeCLP() {

		String returnString = "";
		returnString += "Literal_B_" + this.value + "_" + this.getConstraintId();

		return returnString;

	}

	public void setType(VariableType type) {
		this.type = type;
	}

	public VariableType getType() {
		return this.type;
	}

	@Override
	public String getImgInfo() {
		// System.out.println(this.value);
		String var = this.value;
		if (this.value.contains("\"")) {
			var = this.value.replace("\"", "\\\"");
		}
		return var;
	}

	@Override
	public CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey) {

		// Exception為flag作用，需要例外處理
		if (this.getValue().contains("\"Exception\"")) {
			containKey.put("\"Exception\"", true);
			return new CLPInfo(this.getValue(), new ArrayList<String>());
		}

		if (this.type instanceof StringType) {
			ArrayList<String> methodcall = new ArrayList<String>();
			methodcall.add(this.genStringListCLP());
			return new CLPInfo(this.genInsertMathodCallStringListCLP(), methodcall);
		} else if (this.type instanceof BooleanType) {
			ArrayList<String> methodcall = new ArrayList<String>();
			methodcall.add(this.genBooleanTypeCLP());
			return new CLPInfo(this.genInsertMathodCallBooleanTypeCLP(), methodcall);
		} else
			return new CLPInfo(this.getValue(), new ArrayList<String>());
	}

	@Override
	public CLGConstraint clone() {
		CLGConstraint cons = new CLGLiteralNode(String.valueOf(this.value), this.type);
		cons.setCloneId(this.getConstraintId());
		return cons;
		// return new CLGLiteralNode(this.value, this.type);
	}

	@Override
	public String getCLPValue() {
		return this.getValue();
	}

	@Override
	public void setCLPValue(String data) {

	}

	@Override
	public void negConstraint() {
	}

	@Override
	public void preconditionAddPre(SymbolTable sym, String methodname) {
	}

	@Override
	public String getConstraintName() {
		return this.value;
	}

	@Override
	public CLGConstraint addPre() {
		// 20210828 昱廷取消註解
		if (!Utility.isNumeric(this.value))
			this.value = this.value + "_pre";
		return this;
	}

	@Override
	public void renameCLPValue(int count) {
		this.value = this.value + (count == 1 ? "" : "_" + count);
	}

	@Override
	public VariableType getCLPVarType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public void renameWithMap(HashMap<String, String> attMap) {
		// TODO Auto-generated method stub

	}

	// 20210828 昱廷新增
	@Override
	public String getOriginalConName() {
		String retStr = "";
		if (this.value.contains("acc"))
			return "";
		if (this.type instanceof StringType)
			retStr = "\"" + this.value + "\"";
		else
			retStr = this.value;
		return retStr;
	}
}
