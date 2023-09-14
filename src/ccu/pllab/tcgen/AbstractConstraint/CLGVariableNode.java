package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import ccu.pllab.tcgen.AbstractType.ArrayListType;
import ccu.pllab.tcgen.AbstractType.ArrayType;
import ccu.pllab.tcgen.AbstractType.CollectionType;
import ccu.pllab.tcgen.AbstractType.UserDefinedType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.SymbolTable.MethodToken;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

public abstract class CLGVariableNode extends CLGConstraint {
	private VariableType type;
	private String name;
	private CLGConstraint constraint;// 由於AST 改變成左遞迴，constraint 改成紀錄左邊的

	public CLGVariableNode() {
		super();
		this.type = new VoidType();
		this.name = "";
		this.constraint = null;
	}

	public CLGVariableNode(String name) {
		super();
		this.type = new VoidType();
		this.name = name;
		this.constraint = null;
	}

	public CLGVariableNode(String name, VariableType type) {
		super();
		this.type = type;
		this.name = name;
		this.constraint = null;
	}

	public CLGVariableNode(String name, VariableType type, CLGConstraint source_constraint) {
		super();
		this.type = type;
		this.name = name;
		this.constraint = source_constraint;
	}

	public void setConstraint(CLGConstraint constraint) {
		this.constraint = constraint;
	}

	public CLGConstraint getConstraint() {
		return this.constraint;
	}

	public void setType(VariableType type) {
		this.type = type;
	}

	public VariableType getType() {
		return this.type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String getImgInfo() {
		if (this.constraint == null) {
			return this.getName();
		} else {
			// return this.name + "[" + this.constraint.getImgInfo() + "]";//我改的
			// return this.name + "." + this.constraint.getImgInfo();
			return this.constraint.getImgInfo() + "." + this.name;
		}
	}

	@Override
	public CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey) {
		String new_name = this.name;

		if (containKey.containsKey(Utility.titleToUppercase(new_name))) {
			containKey.put(Utility.titleToUppercase(new_name), true);
		}

		if (this.constraint == null) {
			return new CLPInfo(Utility.titleToUppercase(new_name), new ArrayList<String>());
		} else if (new_name.equals("")) { // 不知道照做什麼的?
			return this.constraint.getCLPInfo(variableSet, containKey);
		} else if (!(this.constraint instanceof CLGOperatorNode)) {
			CLPInfo operator_clpinfo = this.constraint.getCLPInfo(variableSet, containKey);
			String return_str = Utility.titleToUppercase(operator_clpinfo.getReturnCLP() + "_" + new_name);
			return new CLPInfo(return_str, operator_clpinfo.getMethodCallCLP());
		} else {
			return new CLPInfo(Utility.titleToUppercase(new_name), new ArrayList<String>());
		}
	}

	@Override
	public CLGConstraint clone() {
		if (this.constraint == null) {
			CLGConstraint cons = new CLGObjectNode(String.valueOf(this.name), this.type, this.constraint);
			cons.setCloneId(this.getConstraintId());
			// return new CLGVariableNode(this.name,this.type,this.constraint);
			return cons;
		} else {
			CLGConstraint newConstrain = this.constraint.clone();
			CLGConstraint cons = new CLGObjectNode(String.valueOf(this.name), this.type, newConstrain);
			cons.setCloneId(this.getConstraintId());
			return cons;
			// return new CLGVariableNode(this.name,this.type,newConstrain);
		}

	}

	@Override
	public String getCLPValue() {

		if (this.getName().contains("@")) {
			return this.getName().replaceAll("@", "_");
		}
		if (this.getName().contains("self") && this.constraint != null) {
			return this.getName().replaceAll("self", "");
		}
		return this.getName();
	}

	public void setCLPValue(String data) {
		this.setName(data);
	}

	public void renameCLPValue(int count) {
		if (count == 1)
			return;
		else if (this.constraint != null)
			this.constraint.renameCLPValue(count);
		else
			this.setName(this.name + "_" + count);
	}

	@Override
	public void negConstraint() {
	}

	@Override
	public void preconditionAddPre(SymbolTable sym, String methodName) {
		if (this.constraint == null) {
			if (!this.name.contains("_pre")) {
				if ((sym.getAttribute().get(this.name.replace("Self_", "")) != null))
					this.name = "Self_pre_" + this.name.replace("Self_", "");
				else if (sym.getMethodToken(methodName).getArgument().get(this.name) != null)
					this.name += "_pre";
			}
		} else
			this.constraint.preconditionAddPre(sym, methodName);
	}

	public String getConstraintName() {
		if (this.name.equals("null") && this.type instanceof VoidType)
			return "";
		else if (this.constraint != null)
			// return this.name + "." + this.constraint.getConstraintName();
			return this.constraint.getConstraintName() + "." + this.name; // 有可能要改 20210505，可悲
		else
			return this.name;
	}

	public CLGConstraint addPre() {
		this.setName(this.getName() + "_pre");
		return this;
	}

	@Override
	public void renameUseVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
		String variable = this.getCLPValue();

		if (variableSet.containsKey(variable)) {
			this.renameCLPValue(variableSet.get(variable));
		}
	}

	@Override
	public void renameDefVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
		String variable = this.getCLPValue();

		if (!variableSet.containsKey(variable)) {
			variableSet.put(variable, 1);
			this.renameCLPValue(1);
		} else if (variableSet.containsKey(variable)) {
			this.renameCLPValue(variableSet.get(variable) + 1);
			variableSet.put(variable, variableSet.get(variable) + 1);
		}
		defineVariableSet.add(variable);
	}

	@Override
	public VariableType getCLPVarType() {
		return this.type;
	}

	@Override
	public void renameWithMap(HashMap<String, String> attMap) {
		if (this.constraint == null) {
			if (attMap.containsKey(this.name)) {
				this.name = attMap.get(this.name);
			}
		} else {
			this.constraint.renameWithMap(attMap);
		}
	} // 根據表修改變數名

	@Override
	public String getOriginalConName() {
		return this.name + (this.constraint == null ? "" : "_" + this.constraint.getOriginalConName());
	}

	@Override
	protected void reverseDefVar(HashMap<String, Integer> variableSet) {
		String variable = this.getCLPValue();
		if (variableSet.containsKey(variable)) {
			if (variableSet.get(variable) != 1)
				variableSet.put(variable, variableSet.get(variable) - 1);
		}
	}

}
