package ccu.pllab.tcgen.AbstractCLG;

import static ccu.pllab.tcgen.AbstractCLG.CLGNode.checkDomainMapKey;
import static ccu.pllab.tcgen.AbstractCLG.CLGNode.flattenStringMapKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;

public abstract class CLGNode implements Cloneable {
	private static int node_count = 0;
	protected static final String flattenStringMapKey = "flattenStringMapKey";
	protected static final String checkDomainMapKey = "checkDomainMapKey";
	private Set<CLGNode> successor;
	private Set<CLGNode> predecessor;
	private CLGNode endNode;
	private int id;
	private boolean visited = false;

	public static String getFlattenStringMapKey() {
		return flattenStringMapKey;
	}

	public static String getCheckDomainMapKey() {
		return checkDomainMapKey;
	}
	
	public CLGNode() {
		successor = new HashSet<CLGNode>();
		predecessor = new HashSet<CLGNode>();
		id = node_count++;
		endNode = this;
	}

	public static void initID() { // 跟reset 相同
		node_count = 0;
	}

	@Override
	public CLGNode clone() throws CloneNotSupportedException {
		CLGNode n = (CLGNode) super.clone();
		return n;
	}

	public void addPredecessor(CLGNode node) {
		this.predecessor.add(node);
		if (!node.getSuccessor().contains(this)) {
			node.addSuccessor(this);
		}
	}

	public void addSuccessor(CLGNode node) {
		this.successor.add(node);
		if (!node.getPredecessor().contains(this)) {
			node.addPredecessor(this);
		}
	}

	public void removePredecessor(CLGNode node) {
		this.predecessor.remove(node);
		if (node.getSuccessor().contains(this)) {
			node.getSuccessor().remove(this);
		}
	}

	public void removeSuccessor(CLGNode node) {
		this.successor.remove(node);
		if (node.getPredecessor().contains(this)) {
			node.getPredecessor().remove(this);
		}
	}

	public List<CLGNode> getPredecessor() {
		return new ArrayList<CLGNode>(this.predecessor);
	}

	public List<CLGNode> getSuccessor() {
		return new ArrayList<CLGNode>(this.successor);
	}

	public void clearPredecessors() {
		this.predecessor.clear();
	}

	public void clearSuccessors() {
		this.successor.clear();
	}

	public final int getId() {
		return this.id;
	}

	public void setEndNode(CLGNode node) {
		this.endNode = node;
	}

	public CLGNode getEndNode() {
		if (endNode == null) {
			return this;
		} else {
			return this.endNode;
		}
	}

	private void setVisitedTrue() {
		this.visited = true;
	}

	public String toGenImg() {
		String content = "";
		content += this.toGetImgInfo();
		if (!this.visited) {
			for (CLGNode node : this.getSuccessor()) {
				this.setVisitedTrue();
				content += this.getId() + "->" + node.getId() + "\n" + node.toGenImg();
			}
		}
		this.setVisitedTrue();
		return content;
	}

	public void setVisitFalse() {
		if (this.visited == true) {
			for (CLGNode node : this.getSuccessor()) {
				this.visited = false;
				node.setVisitFalse();
			}
		}
	}

	@Override
	public String toString() {
		return this.toString();

	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof CLGNode) {
			CLGNode n = (CLGNode) obj;
			return this.endNode.toString().equals(n.endNode.toString());
		}
		return false;
	}

	public abstract String toGetImgInfo();

	/*
	 * variableSet: 定義變數紀錄(在函式CLP會在每個連接結點重置
	 * defineVariableSet: 變數曾定義紀錄(在CLGEndNode確認是否要將 物件/參數 執行前代入執行後
	 */
	public abstract String toCLPInfo(HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet);

	/*localParameters回儲存所有路徑上的區域變數(待優化
	 * variableSet儲存變數定義次數(for ssa rename)，在每個連接節點會重置
	 * defineVariableSet儲存變數定義次數(最後執行前後物件參數狀態移轉，在CLGEndNode)
	 * argNameList儲存所有參數並已處理好命名(物件本身使用self表示即可，不需另外紀錄)
	 * flattenAndCheckDomainCLP紀錄扁平化字串與最後declare(labeling(XXX, "dcl"))的CLP
	 * (使用靜態變數flattenStringMapKey, checkDomainMapKey在flattenAndCheckDomainCLP的hashMap中取出該key的value)
	*/
	public abstract ArrayList<ArrayList<String>> genMethodCLP(String className, String methodName,
			ArrayList localParameters, HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet,
			SymbolTable sym, ArrayList<String> argNameList, HashMap<String, String> flattenAndCheckDomainCLP);

//	public abstract ArrayList genMethodCLP(String className, String methodName, ArrayList classAttributes,
//			ArrayList methodParameters, ArrayList localParameters, String result);

	// add att,arg to variableSet
	protected void initVariableSet(HashMap<String, Integer> variableSet, SymbolTable sym, String methodName) {
		for (Entry<String, VariableToken> att : sym.getAttribute().entrySet()) {
			variableSet.put("Self_pre_" + att.getKey(), 1);
		}
		for (Entry<String, VariableToken> arg : sym.getMethod().get(methodName).getArgument().entrySet()) {
			variableSet.put(arg.getKey() + "_pre", 1);
		}
	}

	public String getOriginalConsName() {
		return "";
	}

	// 20200916 dai
	public static void reset() {
		node_count = 0;
	}

	public abstract ArrayList<String> getMethodAllLocalVar(HashMap<String, Integer> variableSet);

	public abstract ArrayList genMethodCLPForClass(String className, String methodName, ArrayList localParameters,
			HashMap<String, Integer> variableSet, HashSet<String> defineVariableSet, SymbolTable sym, ArrayList<String> argNameList, String flattenCLP);

//	public  CLGNode clone(){
//		return this;
//	};
}
