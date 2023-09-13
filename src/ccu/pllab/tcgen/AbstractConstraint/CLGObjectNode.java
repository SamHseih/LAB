package ccu.pllab.tcgen.AbstractConstraint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;

public class CLGObjectNode extends CLGVariableNode {

	private ArrayList<CLGConstraint> qualifier;// 記陣列索引值
	private int scopeId;// -1代表此變數不影響(或另有處理, e.g. exception

	public CLGObjectNode() {
		super();
		this.qualifier = new ArrayList<>();
	}

	public CLGObjectNode(String name) {
		super(name);
		this.qualifier = new ArrayList<>();
	}

	public CLGObjectNode(String name, VariableType type) {
		super(name, type);
		this.qualifier = new ArrayList<>();
	}

	public CLGObjectNode(String name, VariableType type, int scopeId) {
		super(name, type);
		this.qualifier = new ArrayList<>();
		this.scopeId = scopeId;
	}

	public CLGObjectNode(String name, VariableType type, CLGConstraint constraint) {
		super(name, type, constraint);
		this.qualifier = new ArrayList<>();
	}

	public CLGObjectNode(CLGObjectNode node) {
		super(node.getName(), node.getType(), node.getConstraint());
		this.qualifier = new ArrayList<>();
	}

	public void setSelf(boolean isPre) {
		this.setName("Self_" + (isPre ? "pre_" : "") + this.getName());
	}

	public void setQualifier(ArrayList<CLGConstraint> list) {
		for (CLGConstraint cons : list)
			this.qualifier.add(cons.clone());
	}

	public ArrayList<CLGConstraint> getQualifier() {
		return this.qualifier;
	}

	public void addQualifier(CLGConstraint str) {
		this.qualifier.add(str);
	}

	@Override
	public CLGConstraint clone() {

		CLGConstraint newConstrain = null;
		if (this.getConstraint() != null) {
			newConstrain = this.getConstraint().clone();
		}

		CLGConstraint newObj = new CLGObjectNode(this.getName(), this.getType(), newConstrain);
		newObj.setCloneId(this.getConstraintId());
		for (CLGConstraint qual : this.qualifier)
			((CLGObjectNode) newObj).addQualifier(qual.clone());
		;
		return newObj;
	}

	@Override
	public CLPInfo getCLPInfo(HashMap<String, Integer> variableSet, HashMap<String, Boolean> containKey) {
		String retStr = "";

		String new_name = Utility.titleToUppercase(this.getName());
		if (containKey.containsKey(new_name)) {
			containKey.put(new_name, true);
		}

		ArrayList<String> methodcall = new ArrayList<String>();

		if (this.getConstraint() != null) {
			CLPInfo con_clpinfo = this.getConstraint().getCLPInfo(variableSet, containKey);

			if (this.getConstraint() instanceof CLGMethodInvocationNode) { // 可能要改QQ??
				retStr = con_clpinfo.getReturnCLP();
				methodcall.addAll(con_clpinfo.getMethodCallCLP());
			} else if (this.getName().equals("self")) {
				retStr += con_clpinfo.getReturnCLP() + "_";
			} else
				retStr += con_clpinfo.getReturnCLP() + "_";
		}

		retStr += this.getName();
		retStr = Utility.titleToUppercase(retStr);

		// 陣列索引
		if (this.qualifier.size() != 0) {

			String indexStr = "[";
			for (CLGConstraint con : this.qualifier) {

				CLPInfo qualifier_clpinfo = con.getCLPInfo(variableSet, containKey);

				String tempIndex = qualifier_clpinfo.getReturnCLP();
				methodcall.addAll(qualifier_clpinfo.getMethodCallCLP());
				if (con instanceof CLGOperatorNode) {
					String indexName = "";
					if (variableSet.containsKey("Index")) {
						indexName = "Index" + variableSet.get("Index");
						variableSet.put("Index", variableSet.get("Index") + 1);
					} else {
						indexName = "Index";
						variableSet.put("Index", 1);
					}
					methodcall.add(indexName + "#=" + tempIndex + ",\n");
					tempIndex = indexName;
				}
				indexStr += tempIndex + ",";
			}
			indexStr = Utility.delEndRedundantSymbol(indexStr, ",") + "]";
			String elementName = "";
			if (variableSet.containsKey("Element")) {
				elementName = "Element" + variableSet.get("Element");
				variableSet.put("Element", variableSet.get("Element") + 1);
			} else {
				elementName = "Element";
				variableSet.put("Element", 1);
			}
			methodcall.add("getArrayEle(" + retStr + "," + indexStr + "," + elementName + ")");
			return new CLPInfo(elementName, methodcall);
		}

		return new CLPInfo(retStr, methodcall);
	}
	
	public int getScopeId() {
		return this.scopeId;
	}

	@Override
	public ArrayList<String> getLocalVar() {
		ArrayList<String> retList = new ArrayList<>();
		if (this.getScopeId() > 0)
			retList.add(this.getCLPValue());
		return retList;
	}

	@Override
	public void renameUseVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
		// array index rename
		for (CLGConstraint cons : this.getQualifier()) {
			cons.renameUseVar(variableSet, defineVariableSet, isMethodCLP);
		}
		if (this.getQualifier().size() != 0)
			return;// array name needn't be renamed

		String variable = this.getCLPValue();

		if (variableSet.containsKey(variable)) {
			this.renameCLPValue(variableSet.get(variable));
		} else if(isMethodCLP){
			//補上變數未定義即使用時修正(函式CLP需要)
			variableSet.put(variable, 1);
		}
	}
	
	@Override
	public void renameDefVar(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, boolean isMethodCLP) {
		// array index rename(define still call getArraEle(), needn't record in the
		// variablecSet
		for (CLGConstraint cons : this.getQualifier()) {
			cons.renameUseVar(variableSet, defineVariableSet, isMethodCLP);
		}

		String variable = this.getCLPValue();

		if (!variableSet.containsKey(variable)) {
			variableSet.put(variable, 1);
			this.renameCLPValue(1);
		} else if (variableSet.containsKey(variable) && this.getQualifier().size() == 0) {
			this.renameCLPValue(variableSet.get(variable) + 1);
			variableSet.put(variable, variableSet.get(variable) + 1);// + 1);
		}
		
		defineVariableSet.add(variable);
	}
}
