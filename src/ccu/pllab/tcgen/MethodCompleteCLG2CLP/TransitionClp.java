package ccu.pllab.tcgen.MethodCompleteCLG2CLP;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TransitionClp {
	private String className;
	private String methodName;

	private StateNodeCLP thisState;
	private StateNodeCLP nextState;

	private ArrayList<String> constraintList;
	private boolean isEndNode;
	private boolean isConstructor;

	public TransitionClp(String className, String methodName, StateNodeCLP thisState) {
		this.className = className;
		this.methodName = methodName;
		this.thisState = thisState;
		this.nextState = null;
		this.constraintList = new ArrayList<String>();
		this.isEndNode = false;
		this.isConstructor = className.toLowerCase().equals(methodName.toLowerCase());
	}

	public TransitionClp(String className, String methodName, StateNodeCLP thisState, StateNodeCLP nextState) {
		this.className = className;
		this.methodName = methodName;
		this.thisState = thisState;
		this.nextState = nextState;
		this.constraintList = new ArrayList<String>();
		this.isEndNode = false;
		this.isConstructor = className.toLowerCase().equals(methodName.toLowerCase());
	}

	public void setNextState(StateNodeCLP nextS) {
		this.nextState = nextS;
	}

	public void setIsEndNodeTrue() {
		this.isEndNode = true;
	}

	public String getClassName() {
		return this.className;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public StateNodeCLP getThisState() {
		return this.thisState;
	}

	public StateNodeCLP getNextState() {
		if (this.nextState != null)
			return this.nextState;
		else
			return new StateNodeCLP();
	}

	public ArrayList getConstraintList() {
		return this.constraintList;
	}

	public ArrayList getConstraintList2String() {
		String s = "";

		for (int i = 0; i < this.constraintList.size(); i++) {
			s += " " + this.constraintList.get(i) + ",\n";
		}

		return this.constraintList;
	}

	public void addConstraint(String Constraint) {
		if (Constraint != "" || Constraint != null)
			this.constraintList.add(Constraint);
	}

	public void addConstraint(ArrayList Constraint) {
		if (Constraint != null)
			this.constraintList.addAll(Constraint);
	}

	public String toString() {

		if (this.isEndNode) {
			return this.className.substring(0, 1).toLowerCase() + this.className.substring(1)
					+ this.methodName.substring(0, 1).toUpperCase() + this.methodName.substring(1)
					+ this.thisState.toString() + ".\n";
		}

		String s = this.className.substring(0, 1).toLowerCase() + this.className.substring(1)
				+ this.methodName.substring(0, 1).toUpperCase() + this.methodName.substring(1)
				+ this.thisState.toString() + ":-\n	";

		for (int i = 0; i < this.constraintList.size(); i++)
			s += " " + this.constraintList.get(i).toString() + ",\n";
		System.out.println(this.className.substring(0, 1).toLowerCase() + this.className.substring(1)
				+ this.methodName.substring(0, 1).toUpperCase() + this.methodName.substring(1)
				+ this.thisState.toString());

		return s + "	" + this.className.substring(0, 1).toLowerCase() + this.className.substring(1)
				+ this.methodName.substring(0, 1).toUpperCase() + this.methodName.substring(1)
				+ this.nextState.toString() + ".\n";

	}

}
