package ccu.pllab.tcgen.MethodCompleteCLG2CLP;

import java.util.ArrayList;

public class StateNodeCLP {
	private String state;
	private ArrayList<Object> objPre;
	private ArrayList<Object> argPre;// argument
	private ArrayList<Object> objPost;
	private ArrayList<Object> argPost;
	private ArrayList<Object> result;
	private ArrayList<Object> exception;
	private ArrayList<Object> localVariable;
	private boolean isTransitionStartNode;

	public StateNodeCLP() {

		this.state = "";
		this.objPre = new ArrayList<Object>();
		this.argPre = new ArrayList<Object>();// argument
		this.objPost = new ArrayList<Object>();
		this.argPost = new ArrayList<Object>();
		this.result = new ArrayList<Object>();
		this.exception = new ArrayList<Object>();
		this.localVariable = new ArrayList<Object>();
		this.isTransitionStartNode = false;

	}

	public StateNodeCLP(String state, ArrayList<Object> objPre, ArrayList<Object> argPre, ArrayList<Object> objPost,
			ArrayList<Object> argPost, ArrayList<Object> result, ArrayList<Object> exception,
			ArrayList<Object> localVariable, boolean isTransitionStartNode) {
		this.state = state;
		this.objPre = objPre;
		this.argPre = argPre;// argument
		this.objPost = objPost;
		this.argPost = argPost;
		this.result = result;
		this.exception = exception;
		this.localVariable = localVariable;
		this.isTransitionStartNode = isTransitionStartNode;

	}

	public void setObjPre(ArrayList<Object> objPre) {
		this.objPre = objPre;
	}

	public void setArgPre(ArrayList<Object> argPre) {
		this.argPre = argPre;
	}

	public void setObjPost(ArrayList<Object> objPost) {
		this.objPost = objPost;
	}

	public void setArgPost(ArrayList<Object> argPost) {
		this.argPost = argPost;
	}

	public void setResult(ArrayList<Object> result) {
		this.result = result;
	}

	public void setException(ArrayList<Object> exception) {
		this.exception = exception;
	}

	public void setLocalVariable(ArrayList<Object> localVariable) {
		this.localVariable = localVariable;
	}

	public void setIsTranditionStartNode(boolean isTranditionStartNode) {
		this.isTransitionStartNode = isTranditionStartNode;
	}

	public ArrayList<Object> getObjPre() {
		return this.objPre;
	}

	public ArrayList<Object> getArgPre() {
		return this.argPre;
	}

	public ArrayList<Object> getObjPost() {
		return this.objPost;
	}

	public ArrayList<Object> getArgPost() {
		return this.argPost;
	}

	public ArrayList<Object> getResult() {
		return this.result;
	}

	public ArrayList<Object> getException() {
		return this.exception;
	}

	public ArrayList<Object> getLocalVariable() {
		return this.localVariable;
	}

	public boolean getIsTransitionStartNode() {
		return this.isTransitionStartNode;
	}

	public String toString() {
		String s = this.state + "( ";

		if (this.objPre != null)
			s += this.objPre.toString() + " ,";

		s += this.argPre.toString() + " ," + this.objPost.toString() + " ," + this.argPost.toString() + " ,"
				+ this.result.toString() + " ," + this.exception.toString();

		if (this.localVariable == null)
			s += ")";
		else
			s += " ," + localVariable.toString() + ")";
		return s;
	}

}
