package ccu.pllab.tcgen.sd2clg;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractConstraint.*;
 

public class Transition{

	private int id;
	private CLGConstraint method;
	private State source;
	private State target;
	private CLGConstraint guard;
	private CLGGraph guardCLG;
	
	public Transition(int id, CLGConstraint method, State source, State target, CLGConstraint guard){
		this.id=id;
		this.method=method;
		this.source=source;
		this.target=target;
		this.guard=guard;
	}
	
	public Transition(int id, CLGConstraint method, State source, State target, CLGGraph guardCLG){
		this.id=id;
		this.method=method;
		this.source=source;
		this.target=target;
		this.guardCLG = guardCLG; 
	}
	
	public int getId(){
		return this.id;
	}
	public CLGConstraint getMethod(){
		return this.method;
	}
	public State getSource(){
		return this.source;
	}
	public State getTarget(){
		return this.target;
	}
	public CLGConstraint getGuard(){
		return this.guard;
	}
	
	//«Ø¶©¾Çªø AST2GLG()  20200329
		public CLGGraph getGuardCLG(){
			return this.guardCLG;
		}
}
