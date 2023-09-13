package ccu.pllab.tcgen.typeTestData;

import java.util.ArrayList;

public class MethodTestData {
	
	private String classname;
	private String methodname;
	
	private ArrayList<TypeTestData> objpre;
	private ArrayList<TypeTestData> argpre;
	private ArrayList<TypeTestData> obj;
	private ArrayList<TypeTestData> arg;
	private ArrayList<TypeTestData> result;
	private ArrayList<TypeTestData> exception;
	
	private boolean isConstructor;
	private boolean invalidated;

	public MethodTestData(String classname,String methodname, ArrayList<ArrayList<TypeTestData>> dList) {
		this.classname = classname;
		this.methodname = methodname;
		
		this.objpre = dList.get(0);
		this.argpre = dList.get(1);
		this.obj = dList.get(2);
		this.arg = dList.get(3);
		this.result = dList.get(4);
		this.exception = dList.get(5);
		
		if(this.classname.equalsIgnoreCase(this.methodname)) {
			this.isConstructor = true;
		}
		
		if(this.exception.size() > 0) {
			this.invalidated = true;
		}
		
	}
	
	public String getClassName() {
		return this.classname;
	}
	
	public String getMethodName() {
		return this.methodname;
	}
	
	public boolean isConstructor() {
		return this.isConstructor;
	}
	
	public boolean isInvalidated() {
		return this.invalidated;
	}
	
	public ArrayList<TypeTestData> getObjPre(){
		return this.objpre;
	}
	
	public ArrayList<TypeTestData> getArgPre(){
		return this.argpre;
	}
	
	public ArrayList<TypeTestData> getObj(){
		return this.obj;
	}
	
	public ArrayList<TypeTestData> getArg(){
		return this.arg;
	}
	
	public ArrayList<TypeTestData> getResult(){
		return this.result;
	}
	
	public ArrayList<TypeTestData> getException(){
		return this.exception;
	}
	
}
