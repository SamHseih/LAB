package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;

import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

public class CLGIterateNode extends CLGConstraint {
	CLGConstraint initial;
	CLGConstraint condition;
	CLGConstraint body;
	CLGConstraint increment;
	CLGConstraint start;
	VariableType acc_type;

	public CLGIterateNode() {
		super();
	}

	public void setInitial(CLGConstraint initial) {
		this.initial = initial;
	}

	public CLGConstraint getInitial() {
		return initial;
	}

	public void setCondition(CLGConstraint condition) {
		this.condition = condition;
	}

	public CLGConstraint getCondition() {
		return condition;
	}

	public void setBody(CLGConstraint body) {
		this.body = body;
	}

	public CLGConstraint getBody() {
		return body;
	}

	public void setIncrement(CLGConstraint increment) {
		this.increment = increment;
	}

	public CLGConstraint getIncrement() {
		return increment;
	}

	public void setStart(CLGConstraint start) {
		this.start = start;
	}

	public CLGConstraint getStart() {
		return this.start;
	}

	public void setAccType(VariableType acc_type) {
		this.acc_type = acc_type;
	}

	public VariableType getAccType() {
		return this.acc_type;
	}

	@Override
	public String getImgInfo() {
		return "iterate(" + this.initial.getImgInfo() + ";" + this.condition.getImgInfo() + ";"
				+ this.increment.getImgInfo() + "){" + this.body.getImgInfo() + "}";
	}

	@Override
	public CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey) {
		// 未處理?
		// 轉CLG時應該沒有iterateNode了
		// clpStr.append(initial.getCLPInfo(clpStr));

		CLPInfo init_clpinfo = this.initial.getCLPInfo(variableSet, containKey);
		CLPInfo condition_clpinfo = this.condition.getCLPInfo(variableSet, containKey);
		CLPInfo body_clpinfo = this.body.getCLPInfo(variableSet, containKey);
		CLPInfo increment_clpinfo = this.increment.getCLPInfo(variableSet, containKey);

		String return_str = "";
		return_str += init_clpinfo.getReturnCLP() + ",\n";
		return_str += condition_clpinfo.getReturnCLP() + ",\n";
		return_str += body_clpinfo.getReturnCLP() + ",\n";
		return_str += increment_clpinfo.getReturnCLP();

		ArrayList<String> methodcallList = new ArrayList<String>();
		methodcallList.addAll(init_clpinfo.getMethodCallCLP());
		methodcallList.addAll(condition_clpinfo.getMethodCallCLP());
		methodcallList.addAll(body_clpinfo.getMethodCallCLP());
		methodcallList.addAll(increment_clpinfo.getMethodCallCLP());

		return new CLPInfo(return_str, methodcallList);
	}

	@Override
	public CLGConstraint clone() {
		CLGIterateNode collectionNode = new CLGIterateNode();
		collectionNode.setInitial(this.initial.clone());
		collectionNode.setCondition(this.condition.clone());
		collectionNode.setBody(this.body.clone());
		collectionNode.setIncrement(this.increment.clone());
		collectionNode.setStart(this.start.clone());
		collectionNode.setAccType(this.acc_type);
		return collectionNode;
	}

	@Override
	public String getCLPValue() {
		return "";
	}

	@Override
	public void setCLPValue(String data) {

	}

	@Override
	public void negConstraint() {

	}

	@Override
	public void preconditionAddPre(SymbolTable sym, String methodname) {
		this.initial.preconditionAddPre(sym, methodname);
		this.condition.preconditionAddPre(sym, methodname);
		this.body.preconditionAddPre(sym, methodname);
	}

	@Override
	public String getConstraintName() {
		return "";
	}

	@Override
	public CLGConstraint addPre() {
		return this;
	}

	@Override
	protected void renameCLPValue(int count) {
		// TODO Auto-generated method stub

	}

	@Override
	public VariableType getCLPVarType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void renameWithMap(HashMap<String, String> attMap) {
		// TODO Auto-generated method stub

	}
}
