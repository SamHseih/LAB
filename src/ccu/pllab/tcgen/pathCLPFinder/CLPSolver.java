package ccu.pllab.tcgen.pathCLPFinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.parctechnologies.eclipse.EXDRInputStream;
import com.parctechnologies.eclipse.EclipseEngine;
import com.parctechnologies.eclipse.EclipseException;
import com.parctechnologies.eclipse.EmbeddedEclipse;
import com.parctechnologies.eclipse.FromEclipseQueue;

import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.TestCase.TestData;
import ccu.pllab.tcgen.TestCase.TestDataClassLevel;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLP2data.CLP2Data;
import ccu.pllab.tcgen.pathCLP2data.CLP2DataFactory;
import ccu.pllab.tcgen.pathCLP2data.ECLiPSe_CompoundTerm;
import scala.collection.generic.BitOperations.Int;
import tcgenplugin_2.handlers.SampleHandler;

public class CLPSolver {
	private CLP2Data clp2data;
	private String EclDirectPath = DataWriter.output_folder_path + "/ecl/";

	private List<ECLiPSe_CompoundTerm> sol;
	private TestData testData;
	private TestDataClassLevel testDataclass;

	public CLPSolver() {

	}

//	for路徑縮減
	public boolean solvingInfeasiblePath(String className, String methodName, String path, int testcaseID,
			boolean isConstructor, String objPre, String argPre, String objPost, String argPost, String retVal) {

		this.EclDirectPath = DataWriter.testMethodCLP_output_path + "/eclSIP/" + className
				+ Utility.titleToUppercase(methodName) + "SIP/";
		File eclFile = new File(EclDirectPath + className + Utility.titleToUppercase(methodName) + path + ".ecl");
		int testCaseID = 1;
		try {
//			
			this.connectCLPSolver();
			this.clp2data.compile(eclFile);
			this.sol = this.clp2data.solvingCSP_term("test" + className + Utility.titleToUppercase(methodName), objPre,
					argPre, objPost, argPost, retVal, 5);
			return true;
		} catch (Exception e) {
			try {
				FileUtils.copyFile(eclFile, new File(eclFile.getParentFile(), "fail" + eclFile.getName()));
				eclFile.delete();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// e.printStackTrace();
			return false;
		}
	}

	public boolean solving(String className, String methodName, int pathNum, int testcaseID, boolean isConstructor,
			String retType, String objPre, String argPre, String objPost, String argPost, String retVal) {
//		因應外掛修改

		String TestTypeSign = "";
		String failRename = "fail";

//		判別測試種類為黑箱或白箱等
		if (Main.TestType == "BlackBox") {
			TestTypeSign = "B";
		} else if (Main.TestType == "WhiteBox") {
			TestTypeSign = "W";
		} else {
			TestTypeSign = "C";
		}

		this.EclDirectPath = DataWriter.testCons_output_path;
		System.out.println(this.EclDirectPath);
		File eclFile = new File(EclDirectPath + className + methodName + TestTypeSign + pathNum + ".ecl");
		int testCaseID = 1;
		try {
			this.connectCLPSolver();

			this.clp2data.compile(eclFile);

			this.sol = this.clp2data.solvingCSP_term("test" + className + methodName, objPre, argPre, objPost, argPost,
					retVal, 10);
			this.testData = new TestData(className, methodName, pathNum, testCaseID, isConstructor, retType, this.sol);
			System.out.println("TD: " + testData.toString());
			return true;
		} catch (Exception e) {
			try {
				FileUtils.copyFile(eclFile, new File(eclFile.getParentFile(), failRename + eclFile.getName()));
				eclFile.delete();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// eclFile.renameTo(new File(eclFile.getParentFile(), failRename +
			// eclFile.getName()));
			// e.printStackTrace();
			return false;
		}
	}

	public boolean compiling(String fileDes, String fileName) {
		// compile method, needn't get testData
		this.EclDirectPath = fileDes;
		File eclFile = new File(EclDirectPath + fileName + ".ecl");
		try {
			this.connectCLPSolver();
			this.clp2data.compile(eclFile);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// 824
	public boolean solving(String classN, int pathNum, ArrayList<String> methodN) {
		File eclFile = null;
		int testCaseID = 1;
		try {
//			String pathecl ="src/ccu/pllab/tcgen/TCGenExample824/ECL/";
			String pathecl = ccu.pllab.tcgen.DataWriter.DataWriter.testCons_output_path;
			// this.EclDirectPath =
			// ccu.pllab.tcgen.DataWriter.DataWriter.output_folder_path+"ECL/";
			eclFile = new File(pathecl + classN + "_" + pathNum + ".ecl");
			this.connectCLPSolver();

			// 20200921 dai
//			this.clp2data.compile(new File("examples\\testmethodpath\\CLPCoffeeMachine.ecl"));
//			this.clp2data.compile(new File("examples\\testmethodpath\\CLPInsert.ecl"));
//			this.clp2data.compile(new File ("examples\\testmethodpath\\CLPWithdraw.ecl"));
//			this.clp2data.compile(new File("examples\\testmethodpath\\CLPCook.ecl"));
//			this.clp2data.compile(new File("examples\\testmethodpath\\CLPDone.ecl"));

//			this.clp2data.compile(new File("examples\\CoffeeMachineCLPForClassLevel\\CoffeeMachineCoffeeMachine.ecl"));
//			this.clp2data.compile(new File("examples\\CoffeeMachineCLPForClassLevel\\CoffeeMachineInsert.ecl"));
//			this.clp2data.compile(new File ("examples\\CoffeeMachineCLPForClassLevel\\CoffeeMachineWithdraw.ecl"));
//			this.clp2data.compile(new File("examples\\CoffeeMachineCLPForClassLevel\\CoffeeMachineCook.ecl"));
//			this.clp2data.compile(new File("examples\\CoffeeMachineCLPForClassLevel\\CoffeeMachineDone.ecl"));
			//

			this.clp2data.compile(eclFile);

			// 20200921 dai
			// String comstr = "testpath"+pathNum+"(Obj_pre, Arg_pre, Obj_post, Arg_post,
			// Ret_val).";
			String comstr = "testpath" + pathNum + "(SDObjPre, SDArgPre, SDObjPost, SDArgPost, SDResult, SDException).";
			//
			this.sol = this.clp2data.solvingCSP_new(comstr, 5);

			if (!this.sol.contains(null)) {
				this.testDataclass = new TestDataClassLevel(classN, methodN, pathNum, testCaseID, this.sol);
			} else {
				this.testDataclass = null; // pathNum-=1;
			}
//			this.testDataclass = new TestDataClassLevel(classN,methodN,pathNum,testCaseID,this.sol);
			return true;

		} catch (Exception e) {
			this.testDataclass = null;
			eclFile.renameTo(new File(this.EclDirectPath + "Fail_" + classN + methodN + "_" + pathNum + ".ecl"));
			// e.printStackTrace();
			return false;
		}
	}

	public TestData getTestData() {
		return this.testData;
	}

	public TestDataClassLevel getTestDataclass() {
		return this.testDataclass;
	}

	public void connectCLPSolver() throws EclipseException, IOException {
		if (clp2data == null)
			clp2data = CLP2DataFactory.getEcl2DataInstance();
	}

	public void disconnectCLPSolver() {
		clp2data.Destroy();
	}

}
