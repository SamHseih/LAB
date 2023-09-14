package ccu.pllab.tcgen.sd2clg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class State {

	private int id;
	private String xmiID; // uml -> <subvertex xmi:id="__GHj8BYZEey387xaGPletw">
	private String name;
	private LinkedHashMap<String, Transition> transitions = new LinkedHashMap<String, Transition>();

	public State(int id, String xmlID, String name) {
		this.id = id;
		this.xmiID = xmlID;
		this.name = name;
	}

	public void addtransition(Transition trans) {
		this.transitions.put(trans.getXmiID(), trans);
	}

	public int getId() {
		return this.id;
	}

	public String getXmiID() {
		return this.xmiID;
	}

	public String getName() {
		return this.name;
	}

	public LinkedHashMap<String, Transition> getTransitions() {
		return this.transitions;
	}

}
