package ccu.pllab.tcgen.TestCase;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.omg.PortableServer.ServantActivator;
import org.stringtemplate.v4.*;
import org.xml.sax.SAXException;

import com.parctechnologies.eclipse.EclipseException;
import ccu.pllab.tcgen.PapyrusCDParser.ClassInfo;
import ccu.pllab.tcgen.PapyrusCDParser.OperationInfo;
import ccu.pllab.tcgen.PapyrusCDParser.VariableInfo;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.AbstractType.ArrayType;
import ccu.pllab.tcgen.AbstractType.IntType;
import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.UserDefinedType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.ecl2data.ECLiPSeCompoundTerm;
import ccu.pllab.tcgen.ecl2data.Ecl2Data;
import ccu.pllab.tcgen.ecl2data.Ecl2DataFactory;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.launcher.BlackBoxLauncher;
import ccu.pllab.tcgen.pathCLP2data.CLP2Data;
import ccu.pllab.tcgen.pathCLP2data.CLP2DataFactory;
import ccu.pllab.tcgen.pathCLP2data.ECLiPSe_CompoundTerm;
import ccu.pllab.tcgen.sd2clg.SDXML2SD;
import ccu.pllab.tcgen.sd2clg.StateDigram;
import ccu.pllab.tcgen.sd2clp.ExecuteCLP;
import ccu.pllab.tcgen.sd2clp.SD2CLP;
import ccu.pllab.tcgen.transform.Splitter;
import ccu.pllab.tcgen.transform.UmlTransformer;
import ccu.pllab.tcgen.typeTestData.MethodTestData;
import ccu.pllab.tcgen.typeTestData.TypeTestData;
import scala.reflect.Symbol;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

public class TestScriptGenerator {
	private List<MethodTestData> testDatas;
	static int scriptNo = 1;

	public TestScriptGenerator() {
		scriptNo = 1;
	}

	public void init(List<MethodTestData> tds) {
		this.testDatas = tds;
	}

	public String genTestScriptByPreamble() throws Exception {
		String testScript = "";
		/* gen test method */
		for (MethodTestData testData : this.testDatas) {
			CLP2Data.testScriptVarCount.clear();
			/* STG set up */
			URL stgFileURL;
			InputStreamReader fr = null;
			if (!testData.isInvalidated()) {
				stgFileURL = TestScriptGenerator.class.getResource("testscript.stg");
				fr = new InputStreamReader(TestScriptGenerator.class.getResourceAsStream("testscript.stg"));
				System.out.println("valid " + stgFileURL.getPath());
			} else {
				stgFileURL = TestScriptGenerator.class.getResource("testscriptfail.stg");
				System.out.println("invalid " + stgFileURL.getPath());
			}
			File stgFileDir = null;
			try {
				stgFileDir = new File(stgFileURL.getPath());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			STGroup stg = new STGroupFile(stgFileDir.getPath());

			ST template = stg.getInstanceOf("testscript");

			String sys_decl = "";
			String target_obj = "";

			/* preamble begins */
			String sys_init = "";
			HashMap<String, String> seq = new HashMap<>();
			CLP2Data clp = CLP2DataFactory.getEcl2DataInstance();

			VariableType varType = Main.typeTable.get(testData.getClassName(), null);
			SymbolTable sym = varType.getSymbolTable();
			String preambleStr = "";
			HashMap<String, String> preambleMap = new HashMap<>();
			// obj start
			if (!testData.isConstructor()) {
				preambleMap.clear();
				preambleStr = "";

				preambleMap = clp.preambleQuery(testData.getObjPre().get(0), testData.getClassName(), varType);

				preambleStr += preambleMap.get(CLP2Data.objAdditional);
				if (!(preambleMap.get(CLP2Data.objConstructor).equals(""))) {
					target_obj = preambleMap.get(CLP2Data.objName);
					preambleStr += "\r\n" + target_obj + "=" + preambleMap.get(CLP2Data.objConstructor) + ";";
				} else {
					target_obj = CLP2Data.newObjName(testData.getClassName());
					preambleStr += "\r\n" + target_obj + "=" + preambleMap.get(CLP2Data.objName) + ";";
				}

				if (!(preambleMap.get(CLP2Data.objPreamble).equals("")))
					preambleStr += "\r\n" + preambleMap.get(CLP2Data.objPreamble) + ";";

				sys_init += preambleStr;
			} else {
				target_obj = CLP2Data.newObjName(testData.getClassName());

			}
			/* sys_decl */
			sys_decl = Utility.titleToUppercase(testData.getClassName()) + " " + target_obj + " = null;\n";
			template.add("sys_decl", sys_decl);
			/* targetObj */
			template.add("target_obj", target_obj);

			// obj end

			// arg start
			String varName = "";
			preambleMap.clear();
			preambleStr = "";
			String methodName = testData.isConstructor() ? Utility.titleToUppercase(testData.getMethodName())
					: testData.getMethodName();

			for (TypeTestData arg : testData.getArgPre()) {
				preambleMap = clp.preambleQuery(arg, arg.getType().getType(), arg.getType());
				varName += preambleMap.get(CLP2Data.objName) + ", ";
				preambleStr += preambleMap.get(CLP2Data.objAdditional);
				if (!(preambleMap.get(CLP2Data.objConstructor).equals(""))) {
					preambleStr += "\r\n" + arg.getType().getDclType() + " " + preambleMap.get(CLP2Data.objName) + "="
							+ preambleMap.get(CLP2Data.objConstructor) + ";";
				}
				if (!(preambleMap.get(CLP2Data.objPreamble).equals("")))
					preambleStr += "\r\n" + preambleMap.get(CLP2Data.objPreamble) + ";";
			}
			// arg end

			/* arg_list */
			String arg_list = Utility.delEndRedundantSymbol(varName, ", ");
			template.add("arg_list", arg_list);

			// sys_init
			sys_init += preambleStr;
			if (testData.isConstructor())
				sys_init += "\r\n" + target_obj + " = new " + testData.getClassName() + "(" + arg_list + ");";

			/* preamble ends */

			/* sys_cleanup */
			/*
			 * String sys_cleanup = "obj" + testData.getClassName() + " = null;\n";
			 * sys_cleanup += "obj" + testData.getClassName() + "_Post = null;\n";
			 * template.add("sys_cleanup", sys_cleanup);
			 */

			/* isConstructor */
			template.add("is_constructor", testData.isConstructor());
			
			if (testData.getResult().size() > 0) {
				/* ret_type */
				// 沒有用到
				String ret_type = (testData.getResult().size() > 0) ? testData.getResult().get(0).getType().getType()
						: "";
				template.add("ret_type", ret_type);

				/* assert */
				String ret_val = "";
				// 因為testData裡的ret_type是字串是錯的，但改成VariableType會造成preamble
				// clp錯誤，故由symboltable判斷函式回傳型態
				if (sym.getMethod().get(methodName).getReturnType() instanceof ArrayType) {
					template.add("return_array", true);
					HashMap<String, String> retVarlMap = new HashMap<>();
					retVarlMap = clp.preambleQuery(testData.getResult().get(0), "",
							testData.getResult().get(0).getType());
					String retArrName = retVarlMap.get(CLP2Data.objName);
					if (!(retVarlMap.get(CLP2Data.objConstructor).equals("")))
						sys_init += "\r\n" + sym.getMethod().get(methodName).getReturnType().getDclType() + " "
								+ retVarlMap.get(CLP2Data.objName) + "=" + retVarlMap.get(CLP2Data.objConstructor)
								+ ";";
					if (!(retVarlMap.get(CLP2Data.objPreamble).equals("")))
						sys_init += "\r\n" + retVarlMap.get(CLP2Data.objPreamble) + ";";
					sys_init += retVarlMap.get(CLP2Data.objAdditional);
					ret_val = retArrName;
				} else if (sym.getMethod().get(methodName).getReturnType() instanceof UserDefinedType) {
					ret_val = "\"" + Utility.delHeadTailBrackets(testData.getResult().get(0).getValueString())
							.replace("[](", "[").replace(")", "]") + "\"";
					template.add("return_array", false);
					template.add("return_baseType", false);
				} else {
					ret_val = Utility.delHeadTailBrackets(testData.getResult().get(0).getValueString())
							.replace("[](", "[").replace(")", "]");
					template.add("return_array", false);
					template.add("return_baseType", true);
				}

				template.add("sys_init", sys_init);

				String assertStr = "";
				assertStr = "assertTrue(" + "result.equals(" + ret_val + "))";

				template.add("ret_val", ret_val);
			}else {
				template.add("sys_init", sys_init);
				template.add("return_void", true);
			}

			template.add("exception", "Exception");
			template.add("testCasePackage", testData.getClassName());
			template.add("classPackage", testData.getClassName());
			template.add("class_name", testData.getClassName());
			template.add("method_name", testData.getMethodName());
			template.add("new_method_name", Utility.titleToUppercase(testData.getMethodName()) + "_");
			String dataObjPost = "";
			for (TypeTestData attri : testData.getObj()) {
				dataObjPost += attri.getValueString() + ", ";
			}
			dataObjPost = Utility.delEndRedundantSymbol(dataObjPost, ", ");
			dataObjPost = dataObjPost.replace("[](", "[").replace(")", "]");
			template.add("obj_list", "\"" + dataObjPost + "\"");
			template.add("case_no", scriptNo);

			testScript += template.render();
			scriptNo++;
		}

		return testScript;
	}

	public void genTestCase(TestData data, String output_path) {
		String testCaseName = String.format("Test%s%s", data.getTestDataName(), scriptNo);
		System.out.println("testpath:" + TestScriptGenerator.class.getResource(""));

		URL stgFileURL;
		InputStreamReader fr = null;
		/*
		 * if (!data.isInvalidated()) { stgFileURL =
		 * TestScriptGenerator.class.getResource("testcase.stg"); fr= new
		 * InputStreamReader(TestScriptGenerator.class.getResourceAsStream(
		 * "testcase.stg")); System.out.println("invalid " +stgFileURL);
		 * 
		 * } else { stgFileURL =
		 * TestScriptGenerator.class.getResource("testcasefail.stg");
		 * System.out.println("valid "+stgFileURL); }
		 */

		File stgFileDir = null;
		try {
			stgFileDir = new File("./ccu/pllab/tcgen/TestCase/testcase.stg");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		STGroup stg;
		ST template;
		if (!data.isInvalidated()) {
			stg = new STGroupFile("/ccu/pllab/tcgen/TestCase/testcase.stg");
			template = stg.getInstanceOf("testcase");
		} else {
			stg = new STGroupFile("ccu/pllab/tcgen/TestCase/testcase.stg");
			template = stg.getInstanceOf("testcase");
		}
		/* sys_decl */
		String sys_decl = data.getClassName() + " obj" + data.getClassName() + " = null;\n";
		if (data.getObjPre().substring(1, data.getObjPre().length() - 1).contains("["))
			sys_decl += data.getClassName() + " obj" + data.getClassName() + "Post = null;\n";
		else
			sys_decl += data.getClassName() + " obj" + data.getClassName() + "_Post = null;\n";

		template.add("sys_decl", sys_decl);

		/* sys_init */
		String obj_pre = data.getObjPre().substring(1, data.getObjPre().length() - 1);
		String obj_post = data.getObjPost().substring(1, data.getObjPost().length() - 1);
		String sys_init = "";
		if (obj_pre.contains("[")) {
			if (!obj_pre.equals("[]")) {
				obj_pre = data.getObjPre().substring(2, data.getObjPre().indexOf("]"));
				obj_post = data.getObjPost().substring(2, data.getObjPost().length() - 2);
			} else {
				obj_pre = "";
				obj_post = "";
			}
			template.add("is_array", true);
			// obj_pre=obj_pre.replaceAll("[", "");
			// obj_pre=obj_pre.replaceAll("]", "");
			sys_init = "int[] objArray= {" + obj_pre + "};\n" + "obj" + data.getClassName() + " = new "
					+ data.getClassName() + "(objArray);\n";
			// sys_init=sys_init.replaceAll("[", "");
			// sys_init=sys_init.replaceAll("]", "");
			sys_init += "int[] objArrayPost= {" + obj_post + "};\n" + "obj" + data.getClassName() + "Post = new "
					+ data.getClassName() + "(objArrayPost);\n";
			// sys_init=sys_init.replaceAll("[", "");
			// sys_init=sys_init.replaceAll("]", "");
		} else {
			sys_init = "obj" + data.getClassName() + " = new " + data.getClassName() + "(" + obj_pre + ");\n";
			sys_init += "obj" + data.getClassName() + "_Post = new " + data.getClassName() + "(" + obj_post + ");\n";
		}

		template.add("sys_init", sys_init);

		/* sys_cleanup */
		String sys_cleanup = "obj" + data.getClassName() + " = null;\n";
		if (data.getObjPre().substring(1, data.getObjPre().length() - 1).contains("["))
			sys_cleanup += "obj" + data.getClassName() + "Post = null;\n";
		else
			sys_cleanup += "obj" + data.getClassName() + "_Post = null;\n";

		template.add("sys_cleanup", sys_cleanup);

		/* targetObj */
		String target_obj = "obj" + data.getClassName();
		template.add("target_obj", target_obj);

		/* arg_list */
		String arg_list = data.getArgPre().substring(1, data.getArgPre().length() - 1);
		template.add("arg_list", arg_list);

		/* isConstructor */
		template.add("is_constructor", data.isConstructor());
		if (data.getRetType().equals("OclVoid"))
			template.add("return_void", true);
		/* ret_type */
		String ret_type = "String";
		template.add("ret_type", ret_type);

		/* assert */
		String ret_val = data.getRetVal().substring(1, data.getRetVal().length() - 1);
		String assertStr = "";
		// if(data.getRetVal().length()-1==0)
		assertStr = "assertTrue(" + "result.equals(" + ret_val + "))";
		// else
		// assertStr = "assertTrue(" + "obj" + data.getClassName() +".equals(" + "obj" +
		// data.getClassName() + "_Post" + "))";
		template.add("assert", assertStr);

		template.add("testCasePackage", data.getClassName());
		template.add("classPackage", data.getClassName());
		template.add("class_name", data.getClassName());
		template.add("method_name", data.getMethodName());
		// template.add("case_no", data.getTestDataID());
		template.add("case_no", scriptNo);
		// System.out.println(template.render());
		String testCasePreName;
		if (data.isInvalidated()) {
			testCasePreName = "errTest";
		} else {
			testCasePreName = "Test";
		}
		if (!obj_pre.equals("[]"))
			// DataWriter.writeInfo(template.render(), testCasePreName +
			// data.getTestDataName() + "_c" + data.getTestDataID(), "java",
			// DataWriter.output_folder_path, "TestSuites");
			DataWriter.writeInfo(template.render(), testCasePreName + data.getClassName() + scriptNo, "java",
					output_path, "test");
		scriptNo++;
		System.out.println(template.toString());
	}
}
