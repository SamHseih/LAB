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

	// �ݧR��
	public void setCloneId(String conId) {
		this.clone_id = conId;
	}

	// �ݧR��
	public String getCloneId() {
		return this.clone_id;
	}

	// 20200916 dai
	public static void reset() {
		constraint_count = 0;
	}

	public abstract String getImgInfo();

	public abstract CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey);// �ѼƬ����n�g�J��CLP�ɡA�^�ǫh�O���I�ݦ^�ǵ��W������

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

	public abstract String getConstraintName(); // �^�ǭ����I��ܦb�ϤW���W�r

	public abstract void renameWithMap(HashMap<String, String> attMap); // �ھڪ�ק��ܼƦW

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
	 * rename���i�J�禡
	 * variableSet �����ܼƩw�q����
	 * defineVariableSet �����w�q�L���ܼ�(�b�禡CLP���|�]�J��s�����I���m)
	 * isMethodCLP�O�_���禡CLP(CLGObjectNode/renameUseVar�ݭn)
	 */
	public void renameVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
		this.renameUseVar(variableSet, defineVariableSet, isMethodCLP);
		return;
	}

	//�禡CLP�ΡA���o�U�����I�ϰ��ܼ�
	public ArrayList<String> getLocalVar() {
		return new ArrayList<>();
	};

	// pathInfo output use
	//�e�{����b�W�椤���r��ˤl
	public String getOriginalConName() {
		return "";
	}

	public String getItNum() {
		return "";
	}

	// �bSIP�R������ɡA�N�ܼ�rename��flag�^�ҡA�H�T�w�̫��ܼƥ��T�����A
	public void reverseVarFlag(HashMap<String, Integer> variableSet) {
	}

	protected void reverseDefVar(HashMap<String, Integer> variableSet) {
	}

	protected void reverseMethodDefVar(HashMap<String, Integer> variableSet) {
	}

}