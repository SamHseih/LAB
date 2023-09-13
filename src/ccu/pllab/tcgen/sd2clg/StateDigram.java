package ccu.pllab.tcgen.sd2clg;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ccu.pllab.tcgen.SymbolTable.VariableToken;

public class StateDigram {

	private LinkedHashMap<String, State> states = new LinkedHashMap<String, State>();
	private LinkedHashMap<String, Transition> transitions = new LinkedHashMap<String, Transition>();
	private String sdName = "";
	private LinkedHashMap<String, VariableToken> sdAttribute = new LinkedHashMap<String, VariableToken>();

	public StateDigram() {

	}

	public StateDigram(String sdName, LinkedHashMap<String, VariableToken> sdAttribute) {
		this.sdName = sdName;
		this.sdAttribute = sdAttribute;
	}

	public LinkedHashMap<String, State> getStates() {
		return this.states;
	}

	public void addstate(State states) {
		this.states.put(states.getXmiID(), states);
	}

	public LinkedHashMap<String, Transition> getTransitions() {
		return this.transitions;
	}

	public void addtransition(Transition trans) {
		this.transitions.put(trans.getXmiID(), trans);
	}

	public String getSDName() {
		return this.sdName;
	}

	public LinkedHashMap<String, VariableToken> getSDAttribute() {
		return this.sdAttribute;
	}
}
