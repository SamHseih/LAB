package ccu.pllab.tcgen.TestCase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.parctechnologies.eclipse.*;

import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLP2data.ECLiPSe_CompoundTerm;
import scala.collection.generic.BitOperations.Int;

public class TestData {
	private List<ECLiPSe_CompoundTerm> solution;

	private String className;
	private String methodName;
	private String obj_pre;
	private String arg_pre;
	private String obj_post;
	private String arg_post;
	private String ret_val;
	private String exception;
	private int pathId;
	private int testDataID;
	private int arrayID = 0;
	private boolean isConstructor;
	private boolean invalidated;
	private String retType;

	public TestData(String className, String methodName, int pathid, int testDataID, boolean isConstructor,
			String retType, List<ECLiPSe_CompoundTerm> solution) {
		this.className = className;
		this.methodName = methodName.toLowerCase().charAt(0) + methodName.substring(1);
		this.pathId = pathid;
		this.testDataID = testDataID;
		this.solution = solution;

		this.obj_pre = testDataToString(solution.get(0));// .substring(1, solution.get(0).toString().length()-1);
		this.arg_pre = testDataToString(solution.get(1));// .substring(1, solution.get(1).toString().length()-1);
		this.obj_post = testDataToString(solution.get(2));// .substring(1, solution.get(2).toString().length()-1);
		this.arg_post = testDataToString(solution.get(3));// .substring(1, solution.get(3).toString().length()-1);
		this.ret_val = testDataToString(solution.get(4));// .substring(1, solution.get(4).toString().length()-1);
		this.exception = testDataToString(solution.get(5));

		this.isConstructor = isConstructor;
		this.invalidated = false;
		if (this.exception.contains("Exception")) {
			this.invalidated = true;
		}
		this.retType = retType;
	}

	public TestData(String className, String methodName, int pathid, int testDataID, int arrayID, boolean isConstructor,
			String retType, String objpre, String argpre, String objpost, String argpost, String retval) {
		this.className = className;
		this.methodName = methodName.toLowerCase().charAt(0) + methodName.substring(1);
		this.pathId = pathid;
		this.testDataID = testDataID;
		this.arrayID = arrayID;
		this.obj_pre = objpre;// .substring(1, solution.get(0).toString().length()-1);
		this.arg_pre = argpre;// .substring(1, solution.get(1).toString().length()-1);
		this.obj_post = objpost;// .substring(1, solution.get(2).toString().length()-1);
		this.arg_post = argpost;// .substring(1, solution.get(3).toString().length()-1);
		this.ret_val = retval;// .substring(1, solution.get(4).toString().length()-1);
		/*
		 * if(ret_val.contains("[[")) ret_val=ret_val.replaceAll("[[", "[");
		 * if(ret_val.contains("]]")) ret_val=ret_val.replaceAll("]]", "]");
		 */
		this.isConstructor = isConstructor;
		this.invalidated = false;
		if (this.ret_val.contains("Exception")) {
			this.invalidated = true;
		}
		this.retType = retType;
	}

	// classLevel
	public TestData(String className, Object methodName, Object objpre, Object argpre, Object objpost, Object argpost,
			Object retval, Object exception) {
		this.className = className;
		this.methodName = methodName.toString().toLowerCase().charAt(0) + methodName.toString().substring(1);
		this.pathId = -1;
		this.testDataID = -1;
		this.arrayID = -1;
		this.obj_pre = objpre.toString();
		this.arg_pre = argpre.toString();
		this.obj_post = objpost.toString();
		this.arg_post = argpost.toString();
		this.ret_val = retval.toString();
		this.exception = exception.toString();

		this.isConstructor = className.equalsIgnoreCase(this.methodName);
		this.invalidated = false;

		if (this.ret_val.contains("Exception")) {
			this.invalidated = true;
		}
		this.retType = retType;
	}

	private String testDataToString(ECLiPSe_CompoundTerm solution) {
		String retStr = "[";
		for (Object o : solution.getTerm()) {
			if (o instanceof CompoundTerm) {
				switch (((CompoundTerm) o).functor()) {
				case "[]":// ����
					retStr += "[";
					for (int i = 0; i < ((CompoundTerm) o).arity(); i++) {
						retStr += ((CompoundTerm) o).arg(i + 1) + ", ";
					}
					retStr = Utility.delEndRedundantSymbol(retStr, ", ") + "], ";
					break;
				case "true":// boolean
				case "false":
					retStr += ((CompoundTerm) o).functor().toString();
					break;
				default:
					retStr += ((CompoundTerm) o).toString();
				}
			} else {
				retStr += o.toString() + ", ";
			}
		}
		retStr = Utility.delEndRedundantSymbol(retStr, ", ") + "]";
		return retStr;
	}

	public String getTestDataName() {
		return this.className + "_" + this.methodName;
	}

	public String getClassName() {
		return this.className;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public String getTestDataID() {
		// if(this.arrayID==0)
		// {
		return this.pathId + "_" + this.testDataID;
		// }
		// else
		// {
		// return this.pathId + "_" + this.testDataID+"_"+(this.arrayID+1);
		// }
	}

	public String toString() {
		return "[" + this.obj_pre + ", " + this.arg_pre + ", " + this.obj_post + ", " + this.arg_post + ", "
				+ this.ret_val + "," + this.exception + "]";
	}
	
	public List<ECLiPSe_CompoundTerm> getSolution(){
		return this.solution;
	}

	public String getObjPre() {
		return this.obj_pre;
	}

	public void setObjPre(String objpre) {
		this.obj_pre = objpre;
	}

	public void setRetVal(String retval) {
		this.ret_val = retval;
	}

	public void setArgPre(String arg_pre) {
		this.arg_pre = arg_pre;
	}

	public String getArgPre() {
		return this.arg_pre;
	}

	public String getException() {
		String exe = "[]";
		if (this.exception != null && this.exception.length() > 0)
			exe = this.exception;
		return exe;
	}

	public String getObjPost() {
		return this.obj_post;
	}

	public void setObjPost(String objpost) {
		this.obj_post = objpost;
	}

	public void setArgPost(String arg_post) {
		this.arg_post = arg_post;
	}

	public String getArgPost() {
		return this.arg_post;
	}

	public String getRetVal() {
		return this.ret_val;
	}

	public int getPathId() {
		return this.pathId;
	}

	public int getTestDataId() {
		return this.testDataID;
	}

	public int getArrayId() {
		return this.arrayID;
	}

	public boolean isConstructor() {
		return this.isConstructor;
	}

	public boolean isInvalidated() {
		return this.invalidated;
	}

	public String getRetType() {
		return this.retType;
	}

	public void setIsInvalid(boolean state) {
		this.invalidated = state;
	}
}
