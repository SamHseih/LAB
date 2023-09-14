package ccu.pllab.tcgen.transform;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.SAXException;

import com.parctechnologies.eclipse.EclipseException;

//import antlr.collections.List;
import ccu.pllab.tcgen.AbstractCLG.CLGConstraintNode;
import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGNode;
import ccu.pllab.tcgen.AbstractCLG.CLGStartNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGMethodInvocationNode;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.TestCase.TestCaseFactory;
import ccu.pllab.tcgen.TestCase.TestData;
import ccu.pllab.tcgen.TestCase.TestDataClassLevel;
import ccu.pllab.tcgen.TestCase.TestData_New;
import ccu.pllab.tcgen.TestCase.TestScriptGenerator;
import ccu.pllab.tcgen.clgGraph2Path.CLGPath;
import ccu.pllab.tcgen.clgGraph2Path.CLGPathEnumerator;
import ccu.pllab.tcgen.clgGraph2Path.CoverageCriterionManager;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;
import ccu.pllab.tcgen.pathCLPFinder.CLPSolver;
import ccu.pllab.tcgen.typeTestData.MethodTestData;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

public class CLG2Path {
	private LinkedList<CLGNode> path;
	private LinkedHashMap<String, VariableToken> attribute = new LinkedHashMap<String, VariableToken>();
	private String invCLP;
	private String className;
	private List<MethodTestData> testData;
	private List<TestDataClassLevel> testDataClassLevel;
	private String testScript;
	private TypeTable typeTab;
	private SymbolTable symbolTable;

	public CLG2Path(SymbolTable sym, TypeTable typeTab) throws IOException, ParserConfigurationException, SAXException,
			TemplateException, ModelAccessException, ParseException, EclipseException, TransformerException {
		this.symbolTable = sym;
		this.typeTab = typeTab;
	}

	public CLG2Path(ArrayList<CLGGraph> clg, CLGGraph invCLG, SymbolTable symbolTable, File uml) throws Exception { // 基本上，黑箱白箱都要用，單純是和張振鴻學長一樣的code
		this.setAttribute(invCLG, symbolTable);
		if (this.invCLP != null)
			Main.invCLP = this.invCLP.substring(0, this.invCLP.length() - 2);
		TestCaseFactory tcFactory = new TestCaseFactory();
		// 2019/07/01 修改
		String className = "null";
		className = ((CLGStartNode) clg.get(0).getStartNode()).getClassName();
		((CLGStartNode) clg.get(0).getStartNode()).getMethodParameterTypes();
		String testScript = "import junit.framework.TestCase;\n" + "import java.util.ArrayList;\n"
				+ "import java.util.Arrays;\n\n" + "public class " + className + "Test extends TestCase {\n";
		for (int number = 0; number < clg.size(); number++) {
			CoverageCriterionManager manager = new CoverageCriterionManager();
			CLGGraph subclg = clg.get(number);
			((CLGStartNode) subclg.getStartNode()).setClassAttributes(this.attribute);
			;
			manager.init(subclg);
			TestScriptGenerator testScriptGenerator = new TestScriptGenerator();
			testScriptGenerator.init(manager.genTestSuite(symbolTable));
			/* add preamble 2019/06/24 */
			// testScriptGenerator.genTestScript();
			String ts = testScriptGenerator.genTestScriptByPreamble();
			((CLGStartNode) clg.get(number).getStartNode()).getMethodParameterTypes();
			testScript += ts;
			tcFactory.createTestCase(((CLGStartNode) subclg.getStartNode()).getGraphName());
		}
		testScript += "}";
		DataWriter.writeInfo(testScript, className + "Test", "java", DataWriter.output_folder_path + "\\test script\\",
				className + "_" + Main.TestType + "_" + Main.criterion + "\\");
	}

	public LinkedList<CLGNode> getPath() {
		return this.path;
	}

	public LinkedHashMap<String, VariableToken> getAttribute() {
		return this.attribute;
	}

	public String getInvCLP() {
		return this.invCLP;
	}

	// 純設定 this.attribute
	// Class Level
	public void setAttribute(SymbolTable symbolTable) {
		this.attribute = symbolTable.getAttribute();
//		LinkedHashMap<String, VariableToken> attributeMap = ((SymbolTable) symbolTable).getAttribute();
//		for (String key : attributeMap.keySet()) {
//			this.attribute.add(key);
//		}
	}

	public void setAttribute(CLGGraph clg, SymbolTable symbolTable) {
		if (clg != null) {
			String bodyCLP = "";
			ArrayList<CLGConstraint> constraintList = new ArrayList<CLGConstraint>();
			CLGPathEnumerator clgPathEnumerator = new CLGPathEnumerator();
			clgPathEnumerator.init(clg);
			CLGPath path = clgPathEnumerator.next();
			CLGNode endNode = path.getPathNodes().get(path.getPathNodes().size() - 1);
			CLGStartNode startNode = (CLGStartNode) path.getPathNodes().get(0);
			for (int i = 1; i < path.getPathNodes().size() - 1; i++) {
				if (path.getPathNodes().get(i) instanceof CLGConstraintNode) {
					((CLGConstraintNode) path.getPathNodes().get(i)).getId();
					constraintList.add(((CLGConstraintNode) path.getPathNodes().get(i)).getConstraint().clone());
					constraintList
							.add(((CLGConstraintNode) path.getPathNodes().get(i)).getConstraint().clone().addPre());
				}
			}

			StringBuilder strB = new StringBuilder();
			strB.append(bodyCLP);
			for (CLGConstraint c : constraintList) {
				// getCLPInfo只能呼叫一次，不然變數編號對不上
				CLPInfo clpinfo = c.getCLPInfo(new HashMap<>(), new HashMap<>());
				ArrayList<String> methodCallArrayList = clpinfo.getMethodCallCLP();

				strB.append(clpinfo.getReturnCLP() + ",\n");// 未處理變數重複問題?

				if (methodCallArrayList.size() > 0) {
					String methodCallString = methodCallArrayList.toString();
					methodCallString = methodCallString.substring(1, methodCallString.length() - 1);
					strB.append(methodCallString + ",\n");
				}
			}

			bodyCLP += strB.toString();
			bodyCLP = bodyCLP.replaceAll("Self_", "");
			this.invCLP = bodyCLP;
		}
		// else
		// {
		this.attribute = symbolTable.getAttribute();
//		LinkedHashMap<String, VariableToken> attributeMap = ((SymbolTable) symbolTable).getAttribute();
//		for (String key : attributeMap.keySet()) {
//			this.attribute.add(key);
//		}
		// }
	}

	public void init(ArrayList<CLGGraph> clg) {
		// 產生並編譯methodCLP
		// genMethodCLP(clg, DataWriter.testMethodCLP_output_path);
		// Main.attribute = this.attribute; // 有使用到
		if (this.invCLP != null)
			Main.invCLP = this.invCLP.substring(0, this.invCLP.length() - 2);

		this.className = ((CLGStartNode) clg.get(0).getStartNode()).getClassName();

		String packagePath = "";
		switch (Main.criterion) {
		case dc:
			packagePath = "package team.ccu.pllab.dc;";
			break;
		case dcc:
			packagePath = "package team.ccu.pllab.dcc;";
			break;
		case mcc:
			packagePath = "package team.ccu.pllab.mcc;";
			break;
		case dcdup:
			packagePath = "package team.ccu.pllab.dcdup;";
			break;
		case dccdup:
			packagePath = "package team.ccu.pllab.dccdup;";
			break;
		case mccdup:
			packagePath = "package team.ccu.pllab.mccdup;";
			break;
		default:
			packagePath = "package team.ccu.pllab.dcc;";

		}
		this.testScript = packagePath + "\n" + "import java.util.ArrayList;\n" + "import java.util.Arrays;\n"
				+ "import org.junit.Test;\n" + "import static org.junit.Assert.*;\n" + "public class " + this.className
				+ "Test{\n";
	}

	public List<MethodTestData> genTestData(ArrayList<CLGGraph> clg, int number)
			throws IOException, CloneNotSupportedException {
		CoverageCriterionManager manager = new CoverageCriterionManager();
		CLGGraph subclg = clg.get(number);
		((CLGStartNode) subclg.getStartNode()).setClassAttributes(this.attribute);
		manager.init(subclg);
		this.testData = manager.genTestSuite(this.symbolTable);

		return this.testData;
	}

	public List<MethodTestData> genTestData_VersionControl(ArrayList<CLGGraph> clg, int number)
			throws IOException, CloneNotSupportedException {
		CoverageCriterionManager manager = new CoverageCriterionManager();
		CLGGraph subclg = clg.get(number);
		((CLGStartNode) subclg.getStartNode()).setClassAttributes(this.attribute);
		manager.init(subclg);
		this.testData = manager.genTestSuite_VersionControl(version, compareVer, this.symbolTable);

		InputStream is = new ByteArrayInputStream(manager.getTD_path_dependency().getBytes());
		FileHandleToServer.storeFile(this.className+"\\"+ version +"\\dependency\\",
										 this.className+((CLGStartNode)subclg.getStartNode()).getMethodName()+"_dependency.txt"
										 , is);

		return this.testData;
	}

	public List<TestDataClassLevel> genTestDataClassLevel(CLGGraph sdCLG, ArrayList<CLGGraph> oclCLG, String criterion,
			TypeTable typeTab) throws IOException, CloneNotSupportedException {
		CoverageCriterionManager manager = new CoverageCriterionManager();
		manager.init(sdCLG, oclCLG, criterion);
		this.testDataClassLevel = manager.genClassLevelTestSuite(typeTab);
		return this.testDataClassLevel;
	}

	public void genTestScript() throws ParserConfigurationException, SAXException, IOException, TemplateException,
			ModelAccessException, ParseException, EclipseException, TransformerException {
		TestScriptGenerator testScriptGenerator = new TestScriptGenerator();
		testScriptGenerator.init(this.testData);
		String ts = "";
		try {
			ts = testScriptGenerator.genTestScriptByPreamble();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.testScript += ts;

//		if (this.className.contains("Array") || this.className.contains("Date") || this.className.contains("Time")
//				|| this.className.contains("Clock")) {
//			TestScriptGenerator testScriptGenerator = new TestScriptGenerator();
//			testScriptGenerator.init(this.testData);
//			String ts = testScriptGenerator.genTestCase();
//			this.testScript += ts;
//		} else {
//			TestScriptGenerator testScriptGenerator = new TestScriptGenerator();
//			testScriptGenerator.init(this.testData);
//			String ts = testScriptGenerator.genTestScriptByPreamble(uml);
//			this.testScript += ts;
//		}
	}

	// 20210829 從 genTestScript -> genClassLevelTestScript
	public void genClassLevelTestScript() {
		List<TestDataClassLevel> testDataClassLevel = this.testDataClassLevel;
		ArrayList<VariableToken> attribute = (ArrayList<VariableToken>) this.attribute.values();
		String testObj = Character.toLowerCase(this.className.charAt(0)) + this.className.substring(1);

		String str = "";
		for (int i = 0; i < testDataClassLevel.size(); i++) {
			ArrayList<Object> methodCallList = testDataClassLevel.get(i).getMethodCallList();
			ArrayList<TestData_New> sdPathTestData = testDataClassLevel.get(i).getMethodCallTestData();
			str += "\tpublic void test" + this.className + (i + 1) + "(){\n";
			int count = 0;
			for (int methodCallIndex = 0; methodCallIndex < methodCallList.size(); methodCallIndex++) {

				TestData_New theMethodCallTestData = sdPathTestData.get(methodCallIndex);

				if (theMethodCallTestData.isConstructor()) {
					str += "\t\t" + methodCallList.get(methodCallIndex) + " " + testObj + " = new "
							+ methodCallList.get(methodCallIndex) + "(";

					str += theMethodCallTestData.getArgPre().toString().replace("[", "]");
					str += ");\n";

					String[] objpost = theMethodCallTestData.getObjPost().toString().split(",");

					for (int a = 0; a < this.attribute.size(); a++) {
						str += "\t\tassertEquals(" + objpost[a].toString().replace("[", "").replace("]", "") + ","
								+ testObj + ".get" + Utility.titleToUppercase(attribute.get(a).getVariableName())
								+ "());\n";
					}

				} else {

					Object argPreList = theMethodCallTestData.getArgPre();
					Object result = theMethodCallTestData.getRetVal();

					if (result.toString().equals("[]")) {

						str += "\t\t" + testObj + "." + methodCallList.get(methodCallIndex) + "(";
						str += argPreList.toString().replace("[", "").replace("]", "");
						str += ");\n";

						String[] objpost = theMethodCallTestData.getObjPost().toString().split(",");

						for (int a = 0; a < this.attribute.size(); a++) {
							str += "\t\tassertEquals(" + objpost[a].toString().replace("[", "").replace("]", "") + ","
									+ testObj + ".get" + attribute.get(a) + "());\n";
						}

					} else {// has return val

						str += "\t\tassertEquals(" + result.toString().replace("[", "").replace("]", "") + ",";
						str += testObj + "." + methodCallList.get(methodCallIndex) + "("
								+ methodCallList.get(methodCallIndex);
						str += argPreList.toString().replace("[", "").replace("]", "");
						str += ");\n";

						String[] objpost = theMethodCallTestData.getObjPost().toString().split(",");

						for (int a = 0; a < this.attribute.size(); a++) {
							str += "\t\tassertEquals(" + objpost[a].toString().replace("[", "").replace("]", "") + ","
									+ testObj + ".get" + attribute.get(a) + "());\n";
						}

					}
				} // end methodname
			} // end data
			str += "\t}\n";
		}

		this.testScript = this.testScript.concat(str);

	}

	public String getClassName() {
		return this.className;
	}

	public List<MethodTestData> getTestData() {
		return this.testData;
	}

	public String getTestScript() {
		return this.testScript;
	}

	public void genTestScripts(ArrayList<CLGGraph> clg, File uml) throws Exception {
		if (this.invCLP != null)
			Main.invCLP = this.invCLP.substring(0, this.invCLP.length() - 2);

		String className = ((CLGStartNode) clg.get(0).getStartNode()).getClassName();

		String testScript = "import junit.framework.TestCase;\n" + "import java.util.ArrayList;\n"
				+ "import java.util.Arrays;\n" + "import static org.junit.Assert.*;\n\n" + "public class " + className
				+ "Test{\n";

		for (int number = 0; number < clg.size(); number++) {
			CoverageCriterionManager manager = new CoverageCriterionManager();
			CLGGraph subclg = clg.get(number);
			((CLGStartNode) subclg.getStartNode()).setClassAttributes(this.attribute);
			manager.init(subclg);
			TestScriptGenerator testScriptGenerator = new TestScriptGenerator();
			testScriptGenerator.init(manager.genTestSuite(this.symbolTable));
			String ts = testScriptGenerator.genTestScriptByPreamble();
			testScript += ts;
		}
		testScript += "}";
		DataWriter.writeInfo(testScript, className + "Test", "java", DataWriter.output_folder_path, "test");
	}
	
	//產生該類別函式CLP
	public void genMethodCLP(ArrayList<CLGGraph> clg, String fileDir, TypeTable typeTab) {
		CLPSolver methodCompiler = new CLPSolver();
		for (CLGGraph graph : clg) {
			String methodCLP = ((CLGStartNode) graph.getStartNode()).CLG2MethodCLP(typeTab);
			String fileName = ((CLGStartNode) graph.getStartNode()).getClassName().toString()
					+ Utility.titleToUppercase(((CLGStartNode) graph.getStartNode()).getMethodName().toString());
			DataWriter.writeInfo(methodCLP, fileName, "ecl", fileDir);

			if (!methodCompiler.compiling(fileDir, fileName)) {
				System.out.println(fileName + " failed to compile.");
			}
		}
	}

	// 暫時拿掉
//	public void genMethodCLPClassLevel(ArrayList<CLGGraph> clg, String fileDir, TypeTable typeTable) {
//		CLPSolver methodCompiler = new CLPSolver();
//		for (CLGGraph graph : clg) {
//			String methodCLP = ((CLGStartNode) graph.getStartNode()).ocl2clpForClassLevel(typeTable);
//			String fileName = ((CLGStartNode) graph.getStartNode()).getClassName().toString()
//					+ Utility.titleToUppercase(((CLGStartNode) graph.getStartNode()).getMethodName().toString());
//			DataWriter.writeInfo(methodCLP, fileName + "ClassLevel", "ecl", fileDir);
//
//			if (!methodCompiler.compiling(fileDir, fileName + "ClassLevel")) {
//				System.out.println(fileName + " failed to compile.");
//			}
//		}
//	}

	/*
	 * 系統預設CLP函式，會先編譯一次以供後續使用
	 */
	public void compileReservedMethod() {
		CLGMethodInvocationNode.compileSystemMethod();
		return;
	}

	// compile the labeling method of every used type in typeTable
	public void compileLabelingMethod(TypeTable typeTable) {
		String labelingType = "EleType";
		LinkedHashMap<String, String> labelingMap = new LinkedHashMap<>();
		// array clp will be arrange in one clp clause
		LinkedHashMap<String, String> dclArrCLP = new LinkedHashMap<>();
		LinkedHashMap<String, String> labelingArrCLP = new LinkedHashMap<>();
		for (String typeName : typeTable.getTypeList().keySet()) {
			typeTable.getTypeList().get(typeName).genDclLabelingCLP(labelingMap, dclArrCLP, labelingArrCLP);
		}
		String labelingCLP = ":- lib(ic).\n" + ":- lib(timeout).\n";
		for (String clp : labelingMap.keySet())
			labelingCLP += labelingMap.get(clp);
		// dcl array
		labelingCLP += "\r\ndelay dcl_array(_,DimList,_) if nonground([DimList]).\r\n" + "dcl_array(X, DimList, "
				+ labelingType + "):-\r\n" + "	dimDomain(DimList),\r\n" + "	dim(X, DimList),\r\n"
				+ "	(foreachelem(Ele, X),\r\n" + "	param(" + labelingType + ")\r\n" + "	do\r\n\t\t(";
		for (String typeName : dclArrCLP.keySet())
			labelingCLP += dclArrCLP.get(typeName);
		labelingCLP = Utility.delEndRedundantSymbol(labelingCLP, ";") + "\r\n\t\t)\r\n\t).\r\n";
		// labeling array
		labelingCLP += "\r\nlabeling_array(X, DimList, " + labelingType + "):-\r\n" + "	dimLabeling(DimList),\r\n"
				+ "	dim(X, DimList),\r\n" + "	(foreachelem(Ele, X),\r\n" + "	param(" + labelingType + ")\r\n"
				+ "	do\r\n\t\t(";
		for (String typeName : labelingArrCLP.keySet())
			labelingCLP += labelingArrCLP.get(typeName);
		labelingCLP = Utility.delEndRedundantSymbol(labelingCLP, ";") + "\r\n\t\t)\r\n\t).\r\n";

		labelingCLP += "realTypeVargetMediam([],Mode,[]).\r\n"
				+ "realTypeVargetMediam([R|Rlist],Mode,[MR|MRList]):-\r\n"
				+ "	(Rlist = [] -> ( Mode = \"all\" ->  get_median(R,MR); Mode = \"part\" -> R $= MR);\r\n"
				+ "	get_median(R,MR)),\r\n" + "	realTypeVargetMediam(Rlist,Mode,MRList).\r\n";

		DataWriter.writeInfo(labelingCLP, "labelingMehodCLP", "ecl", DataWriter.testMethodCLP_output_path);

		CLPSolver labelingMethodCompiler = new CLPSolver();

		if (!labelingMethodCompiler.compiling(DataWriter.testMethodCLP_output_path, "labelingMehodCLP")) {
			System.out.println("labeling method failed to compile.");
		}

		return;
	}

	public void compileTestDataTransformMethod(TypeTable typeTable) {
		String transformType = "EleType";
		LinkedHashMap<String, String> transformMap = new LinkedHashMap<>();
		// array clp will be arrange in one clp clause
		LinkedHashMap<String, String> dclArrCLP = new LinkedHashMap<>();
		LinkedHashMap<String, String> transformArrCLP = new LinkedHashMap<>();
		for (String typeName : typeTable.getTypeList().keySet()) {
			typeTable.getTypeList().get(typeName).genDclTransformCLP(transformMap, transformArrCLP);
		}
		String transformCLP = ":- lib(ic).\n" + ":- lib(timeout).\n";
		for (String clp : transformMap.keySet())
			transformCLP += transformMap.get(clp);

		transformCLP += "\r\ntransform_Array([],[]," + transformType + ")."
				+ "\r\ntransform_Array([Array|ArrayList], [NewArray|NewArrayLsit], " + transformType + "):-\r\n"
				+ "	dim(Array, DimList),\r\n" + "	dim(NewArray, DimList),\r\n" + "	(foreachelem(Ele, Array),\r\n"
				+ "	 foreachelem(NewEle, NewArray),\r\n" + "	param(" + transformType + ")\r\n" + "	do\r\n\t\t(";
		for (String typeName : transformArrCLP.keySet())
			transformCLP += transformArrCLP.get(typeName);
		transformCLP = Utility.delEndRedundantSymbol(transformCLP, ";") + "\r\n\t\t)\r\n\t),"
				+ "\r\n\ttransform_Array(ArrayList, NewArrayLsit, EleType).";
		DataWriter.writeInfo(transformCLP, "TypeTransformMehodCLP", "ecl", DataWriter.testMethodCLP_output_path);

		CLPSolver typeTransformMethodCompiler = new CLPSolver();

		if (!typeTransformMethodCompiler.compiling(DataWriter.testMethodCLP_output_path, "TypeTransformMehodCLP")) {
			System.out.println("type transfrom method failed to compile.");
		}

		return;
	}
}
