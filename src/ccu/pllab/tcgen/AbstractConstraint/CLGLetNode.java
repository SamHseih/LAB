package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;

import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

public class CLGLetNode extends CLGConstraint {

	private CLGConstraint locVariableAssign;
	private CLGConstraint expression;

	public CLGLetNode(CLGConstraint locVariableAssign, CLGConstraint expression) {
		super();
		this.locVariableAssign = locVariableAssign;
		this.expression = expression;
	}

	public CLGLetNode(CLGLetNode clgLetNode) {
		super();
		this.locVariableAssign = clgLetNode.getlocVariableAssign();
		this.expression = clgLetNode.getExpression();
	}

	public CLGConstraint getlocVariableAssign() {
		return this.locVariableAssign;
	}

	public CLGConstraint getExpression() {
		return this.expression;
	}

	@Override
	public String getImgInfo() {
		return "let";
	}

	@Override
	public CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey) {
		return new CLPInfo("", new ArrayList<String>());
	}

	@Override
	public CLGConstraint clone() {
		return new CLGLetNode(this);
	}

	@Override
	public String getCLPValue() {

		return "";
	}

	@Override
	public void setCLPValue(String data) {
		// TODO Auto-generated method stub

	}

	@Override
	public void negConstraint() {
		this.locVariableAssign.negConstraint();
		this.expression.negConstraint();

	}

	@Override
	public void preconditionAddPre(SymbolTable sym, String methodName) {
		this.locVariableAssign.preconditionAddPre(sym, methodName);
		this.expression.preconditionAddPre(sym, methodName);
	}

	@Override
	public String getConstraintName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public CLGConstraint addPre() {
		// TODO Auto-generated method stub
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
