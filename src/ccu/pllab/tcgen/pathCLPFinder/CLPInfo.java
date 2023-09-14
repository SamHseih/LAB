package ccu.pllab.tcgen.pathCLPFinder;

import java.util.ArrayList;

public class CLPInfo {

	String returnCLP;
	ArrayList<String> methodCallCLP;

	public CLPInfo() {
		this.returnCLP = "";
		this.methodCallCLP = new ArrayList<String>();
	}

	public CLPInfo(String returnCLP) {
		this.returnCLP = returnCLP;
		this.methodCallCLP = new ArrayList<String>();
	}

	public CLPInfo(String returnCLP, ArrayList<String> methodCall) {
		this.returnCLP = returnCLP;
		this.methodCallCLP = methodCall;
	}

	public void setReturnCLP(String returnCLP) {
		this.returnCLP = returnCLP;
	}

	public void addMethodCallCLP(ArrayList<String> methodcallclp) {
		methodCallCLP.addAll(methodcallclp);
	}

	public String getReturnCLP() {
		return returnCLP;
	}

	public ArrayList<String> getMethodCallCLP() {
		return methodCallCLP;
	}

	public String methodCallCLPtoString() {

		String returnstr = "";
		for (int i = 0; i < methodCallCLP.size(); i++) {
			returnstr = methodCallCLP.get(i) + ",";
		}

		return returnstr;
	}

}
