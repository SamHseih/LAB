package ccu.pllab.tcgen.SymbolTable;

import java.util.LinkedHashMap;

public class LocalVarScope extends Scope {
	private String methodName;
	private LocalVarScope parent;

	public LocalVarScope(String methodName, LocalVarScope parent) {
		super();
		this.methodName = methodName;
		this.variableMap = new LinkedHashMap<>();
		this.parent = parent;
	}

	public void setParent(LocalVarScope p) {
		this.parent = p;
	}

	public String getMethodName() {
		return methodName;
	}

	public LocalVarScope getParent() {
		return parent;
	}

	/*
	 * return the id of the scope which the var belong if not local var, return -1
	 * ocl grammar use
	 **/
	public int getScopeIdVarIn(String varName) {
		if (super.getVariableMap().containsKey(varName) && this.parent != null)
			return this.getId();
		else if (this.parent != null)
			return this.parent.getScopeIdVarIn(varName);
		else
			return -1;
	}
}
