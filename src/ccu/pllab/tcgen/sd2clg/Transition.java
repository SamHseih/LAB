package ccu.pllab.tcgen.sd2clg;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractConstraint.*;

public class Transition {

	private int id;
	private String xmiID;
	private CLGConstraint method;
	private State source;
	private State target;
	private CLGConstraint guard;

	public Transition(int id, String xmiID, CLGConstraint method, State source, State target, CLGConstraint guard) {
		this.id = id;
		this.xmiID = xmiID;
		this.method = method;
		this.source = source;
		this.target = target;
		this.guard = guard;
	}

	public int getId() {
		return this.id;
	}

	public String getXmiID() {
		return this.xmiID;
	}

	public CLGConstraint getMethod() {
		return this.method;
	}

	public State getSource() {
		return this.source;
	}

	public State getTarget() {
		return this.target;
	}

	public CLGConstraint getGuard() {
		return this.guard;
	}

}
