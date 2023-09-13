package ccu.pllab.tcgen.pathCLP2data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.FilenameUtils;

import com.parctechnologies.eclipse.CompoundTerm;
import com.parctechnologies.eclipse.EXDRInputStream;
import com.parctechnologies.eclipse.EclipseEngine;
import com.parctechnologies.eclipse.EclipseException;
import com.parctechnologies.eclipse.EmbeddedEclipse;
import com.parctechnologies.eclipse.FromEclipseQueue;

import antlr.Token;
import ccu.pllab.tcgen.AbstractType.ArrayType;
import ccu.pllab.tcgen.AbstractType.StringType;
import ccu.pllab.tcgen.AbstractType.UserDefinedType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.ecl2data.Ecl2Data;
import ccu.pllab.tcgen.pathCLPFinder.CLPSolver;
import ccu.pllab.tcgen.transform.TestDate2TestDataTree;
import ccu.pllab.tcgen.typeTestData.StringTypeTestData;
import ccu.pllab.tcgen.typeTestData.TypeTestData;
import ccu.pllab.tcgen.typeTestData.UserDefinedTypeTestData;

public class CLP2Data {
	private EclipseEngine eclipse;
	private FromEclipseQueue eclipse2Java;
	private EXDRInputStream eclipse2JavaFormatted;

	public static String objName = "objName";
	public static String objPreamble = "preamble";
	public static String objAdditional = "additional";
	public static String objConstructor = "constructor";

	public static HashMap<String, Integer> testScriptVarCount = new HashMap<>();// 紀錄每個變數用到第幾個

	public static String newObjName(String className) {
		String objNameStr = "obj" + Utility.titleToUppercase(className);
		if (testScriptVarCount.containsKey(objNameStr)) {
			testScriptVarCount.put(objNameStr, testScriptVarCount.get(objNameStr) + 1);
		} else {
			testScriptVarCount.put(objNameStr, 1);
		}
		objNameStr += testScriptVarCount.get(objNameStr);
		return objNameStr;
	}

	private static void objNameCountBacktraction(String className) {
		String objNameStr = "obj" + Utility.titleToUppercase(className);
		if (testScriptVarCount.containsKey(objNameStr)) {
			if (testScriptVarCount.get(objNameStr).equals(1))
				testScriptVarCount.remove(objNameStr);
			else
				testScriptVarCount.put(objNameStr, testScriptVarCount.get(objNameStr) - 1);
		}
	}

	protected CLP2Data(EclipseEngine eclipse) {
		try {
			this.eclipse = eclipse;
			this.eclipse2Java = this.eclipse.getFromEclipseQueue("eclipse_to_java");
			this.eclipse2JavaFormatted = new EXDRInputStream(this.eclipse2Java);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public void compile(File eclFile) throws EclipseException, IOException {
		this.eclipse.compile(eclFile);
	}

//	public void compile(String str) throws EclipseException, IOException {
//		File clpFile = File.createTempFile("tcgen", ".ecl");
//		clpFile.deleteOnExit();
//		FileWriter writer = new FileWriter(clpFile);
//		writer.write(str);
//		writer.close();
//		this.compile(clpFile);
//	}

	public void Destroy() {
		try {
			((EmbeddedEclipse) this.eclipse).destroy();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	private ECLiPSe_CompoundTerm getECLiPSeCompoundTerm(Object eclipseOutputTerm) {
		ECLiPSe_CompoundTerm obj = new ECLiPSe_CompoundTerm(eclipseOutputTerm, 1);
		return obj;
	}

	private List<ECLiPSe_CompoundTerm> getECLiPSeCompoundTermList_NEW(List<Object> eclipseOutputTerm) {
		List<ECLiPSe_CompoundTerm> instance_list = new ArrayList<ECLiPSe_CompoundTerm>();

		if (!eclipseOutputTerm.equals(Collections.emptyList())) {
//			System.out.println(eclipseOutputTerm.toString());
			@SuppressWarnings("unchecked")
			LinkedList<Object> instance = (LinkedList<Object>) eclipseOutputTerm;

			instance_list.add(new ECLiPSe_CompoundTerm(instance));
		} else {
			instance_list.add(new ECLiPSe_CompoundTerm());
		}

		System.out.println("instance_list: " + instance_list);
		return instance_list;
	}

	private List<ECLiPSe_CompoundTerm> getECLiPSeCompoundTermList(List<Object> eclipseOutputTerm) {
		List<ECLiPSe_CompoundTerm> instance_list = new ArrayList<ECLiPSe_CompoundTerm>();
		for (int i = 0; i < eclipseOutputTerm.size(); i++) {
			if (!eclipseOutputTerm.get(i).equals(Collections.emptyList())) {
				@SuppressWarnings("unchecked")
				LinkedList<Object> instance = (LinkedList<Object>) eclipseOutputTerm.get(i);

				instance_list.add(new ECLiPSe_CompoundTerm(instance));
			} else {
				instance_list.add(new ECLiPSe_CompoundTerm());
			}
		}

		return instance_list;
	}

	public void multiplexityValidation(final String pathName) throws SolvingFailException {
		String target_predicate = String.format("%s(Instances).", pathName);
		try {
			CLP2Data.this.eclipse.rpc(target_predicate);
		} catch (Exception e) {
			throw new SolvingFailException(pathName + " multiplexityValidation");
		}
	}

	public List<List<ECLiPSe_CompoundTerm>> solvingCSP(final String pathName, int timeout)
			throws SolvingTimeOutException, SolvingFailException, IOException {
		Exception eclipseException = null;
		List<List<ECLiPSe_CompoundTerm>> return_instance = new ArrayList<List<ECLiPSe_CompoundTerm>>();
		String predicate = String.format(
				"timeout((%s(Pre, Vars, Post), write_exdr(eclipse_to_java, Pre),write_exdr(eclipse_to_java, Vars), write_exdr(eclipse_to_java, Post), flush(eclipse_to_java)), %d, write_exdr(eclipse_to_java, [[timeout]])).",
				pathName, timeout);
		try {

			System.out.println(predicate);
			// Ecl2Data.this.eclipse.rpc(target_predicate);
			CLP2Data.this.eclipse.rpc(predicate);
		} catch (Exception e) {
			// e.printStackTrace();
			eclipseException = e;
		}

		while (this.eclipse2JavaFormatted.available() > 0) {
			Object term = this.eclipse2JavaFormatted.readTerm();
			System.out.println(this.eclipse2JavaFormatted);
			// for(int i=0;i< ((List<Object>) term).size();i++)
			// System.out.println( ((List<Object>) term).get(i) );
			getECLiPSeCompoundTermList_NEW(((List<Object>) term));
			// getECLiPSeCompoundTermList(((List<Object>) term));
			// @SuppressWarnings("unchecked")
			List<ECLiPSe_CompoundTerm> obj = getECLiPSeCompoundTermList_NEW(((List<Object>) term));
			// List<ECLiPSeCompoundTerm> obj =
			// getECLiPSeCompoundTermList(((List<Object>) term));
			// System.out.println("value: "+obj.toString());
			return_instance.add(obj);
		}
		if (return_instance.size() < 3) {
			if (eclipseException != null) {
				throw new SolvingFailException(pathName);
			} else {
				if (!return_instance.isEmpty()) {
					throw new SolvingTimeOutException(pathName, timeout);
				}
			}
		}
		System.out.println("value: " + return_instance.toString());

		return return_instance;
	}

	// public List<List<ECLiPSeCompoundTerm>> solvingCSP_new(final String
	// pathName, int timeout) throws SolvingTimeOutException,
	// SolvingFailException, IOException {

	public List<List<ECLiPSe_CompoundTerm>> solvingCSP_new(final String pathName, String objPre, String argPre,
			String objPost, String argPost, String retVal, int timeout)
			throws IOException, SolvingFailException, SolvingTimeOutException {
		Exception eclipseException = null;
		List<List<ECLiPSe_CompoundTerm>> return_instance = new ArrayList<List<ECLiPSe_CompoundTerm>>();
		// String predicate = "timeout( (
		// testTriangleCategory(S_pre,Arg_pre,S,Arg,Result),write_exdr(eclipse_to_java,S_pre),write_exdr(eclipse_to_java,Arg_pre),write_exdr(eclipse_to_java,S),write_exdr(eclipse_to_java,Arg),write_exdr(eclipse_to_java,Result),flush(eclipse_to_java)),1,write_exdr(eclipse_to_java,[[timeout]])).";
		String predicate = String.format(
				"timeout( ( %s(%s,%s,%s,%s,%s),write_exdr(eclipse_to_java,%s),write_exdr(eclipse_to_java,%s),write_exdr(eclipse_to_java,%s),write_exdr(eclipse_to_java,%s),write_exdr(eclipse_to_java,%s),flush(eclipse_to_java)),1,write_exdr(eclipse_to_java,%d)).",
				pathName, objPre, argPre, objPost, argPost, retVal, objPre, argPre, objPost, argPost, retVal, timeout);

		try {
//			System.out.println(predicate);
			CLP2Data.this.eclipse.rpc(predicate);
		} catch (Exception e) {
			// e.printStackTrace();
			eclipseException = e;
		}

		while (this.eclipse2JavaFormatted.available() > 0) {
			Object term = this.eclipse2JavaFormatted.readTerm();
//			System.out.println(this.eclipse2JavaFormatted);
			// getECLiPSeCompoundTermList_NEW(((List<Object>) term));
			@SuppressWarnings("unchecked")
			List<ECLiPSe_CompoundTerm> obj = getECLiPSeCompoundTermList_NEW(((List<Object>) term));

			return_instance.add(obj);
		}
		if (return_instance.size() < 5) {
			if (eclipseException != null) {
				throw new SolvingFailException(pathName);
			} else {
				if (!return_instance.isEmpty()) {
					throw new SolvingTimeOutException(pathName, timeout);
				}
			}
		}
		System.out.println("value: " + return_instance.toString());

		return return_instance;
	}

	public List<ECLiPSe_CompoundTerm> solvingCSP_term(final String pathName, String objPre, String argPre,
			String objPost, String argPost, String retVal, int timeout)
			throws IOException, SolvingFailException, SolvingTimeOutException {
		Exception eclipseException = null;
		List<ECLiPSe_CompoundTerm> return_instance = new ArrayList<ECLiPSe_CompoundTerm>();
		String predicate = String.format(
				"timeout( ( %s(%s,%s,%s,%s,%s,%s),write_exdr(eclipse_to_java,%s),write_exdr(eclipse_to_java,%s),write_exdr(eclipse_to_java,%s),write_exdr(eclipse_to_java,%s),write_exdr(eclipse_to_java,%s),write_exdr(eclipse_to_java,%s),flush(eclipse_to_java)),1,write_exdr(eclipse_to_java,%d)).",
				pathName, objPre, argPre, objPost, argPost, retVal, "Exception", objPre, argPre, objPost, argPost,
				retVal, "Exception", timeout);

		try {
	
			CLP2Data.this.eclipse.rpc(predicate);
		} catch (Exception e) {
			// e.printStackTrace();
			eclipseException = e;
		}

		while (this.eclipse2JavaFormatted.available() > 0) {
			Object term = this.eclipse2JavaFormatted.readTerm();

			// System.out.println("term : "+( term.getClass()) );

			@SuppressWarnings("unchecked")
			ECLiPSe_CompoundTerm obj = getECLiPSeCompoundTerm((List<Object>) term);

			return_instance.add(obj);
			System.out.println("obj " + obj.toString());
		}
		if (return_instance.size() < 5) {
			if (eclipseException != null) {
				throw new SolvingFailException(pathName);
			} else {
				if (!return_instance.isEmpty()) {
					throw new SolvingTimeOutException(pathName, timeout);
				}
			}
		}

		System.out.println("term value: " + return_instance.toString() + " size: " + return_instance.size());
		return return_instance;
	}

	// --714
	public List<ECLiPSe_CompoundTerm> solvingCSP_new(String pathName, int timeout)
			throws IOException, SolvingFailException, SolvingTimeOutException {
		List<ECLiPSe_CompoundTerm> return_instance = new ArrayList<ECLiPSe_CompoundTerm>();
		String predicate = pathName;
		try {
			CompoundTerm com = CLP2Data.this.eclipse.rpc(predicate);
			System.out.println(com);
			for (int i = 1; i <= com.arity(); i++) {
				Object b = ((LinkedList) com.arg(i));
				ECLiPSe_CompoundTerm obj = new ECLiPSe_CompoundTerm(b, i);
				return_instance.add(obj);
			}
		} catch (EclipseException e) {
			return_instance.add(null);
			// System.out.println("infeasiblePath "+return_instance);
		} catch (IOException e) {
			// e.printStackTrace();

		}
		return return_instance;
	}

	public HashMap<String, String> preambleQuery(TypeTestData target, String sdName, VariableType varType) {
		HashMap<String, String> seq = new HashMap<>();
		if (varType instanceof UserDefinedType) {
			List<CompoundTerm> CallSeq = null;
			List<ECLiPSe_CompoundTerm> ArgSeq = new ArrayList<>();
			String objNameStr = "";
			if (varType instanceof UserDefinedType) {
				objNameStr = newObjName(varType.getType());
			}
			try {
				String targetCLPData = "";

				targetCLPData = target.transformCLPData();

				/* 執行query */
				String target_predicate = "timeout((" + String.format(sdName.substring(0, 1).toLowerCase()
						+ sdName.substring(1)
						+ "Preamble(%s, FuncSeq, ArgSeq), write_exdr(eclipse_to_java ,FuncSeq), write_exdr(eclipse_to_java ,ArgSeq)),1,write_exdr(eclipse_to_java,%d)).",
						targetCLPData, 10);

				System.out.println("\n" + target_predicate);
				eclipse.rpc(target_predicate);
				// get query result\
				CallSeq = (List<CompoundTerm>) this.eclipse2JavaFormatted.readTerm();
				List<CompoundTerm> ArgList = (List<CompoundTerm>) this.eclipse2JavaFormatted.readTerm();

				LinkedHashMap<String, ArrayList<TypeTestData>> callSeqTestDataList = new LinkedHashMap<String, ArrayList<TypeTestData>>();
				TestDate2TestDataTree TestDataconvert = new TestDate2TestDataTree();
				for (int i = 0; i < ArgList.size(); i++) {
					String argStr = new ECLiPSe_CompoundTerm(ArgList.get(i), 1).toString();
					if (argStr.equals("[[]]"))
						argStr = "[]";
					ArgSeq.add(new ECLiPSe_CompoundTerm(argStr, 1));
					String methodCall = CallSeq.get(i).functor();
					methodCall = methodCall.equalsIgnoreCase(varType.getType()) ? Utility.titleToUppercase(methodCall)
							: Utility.titleToLowercase(methodCall);
					callSeqTestDataList.put(methodCall, TestDataconvert.TransformTypeTestData(argStr, varType,
							varType.getType(), methodCall, "arg"));
				}

//				CallSeq = (List<CompoundTerm>) this.eclipse2JavaFormatted.readTerm();
//				for (Object compound : (List<CompoundTerm>) this.eclipse2JavaFormatted.readTerm()) {
//					ArgSeq.add(new ECLiPSe_CompoundTerm(compound, 1));
//				}

				// merge CallSeq & ArgSeq
				seq.clear();
				String objPreambleStr = "";
				String objAdditionalStr = "";
				String objConstructorStr = "";

				ArrayList<VariableToken> argumentList = null;
				for (Entry<String, ArrayList<TypeTestData>> call : callSeqTestDataList.entrySet()) {
					String preambleTemp = "";
					if (call.getKey().equalsIgnoreCase(sdName)) {
						preambleTemp += "new ";
						preambleTemp += Utility.titleToUppercase(call.getKey()) + "(";
						argumentList = new ArrayList<VariableToken>(varType.getSymbolTable().getMethod()
								.get(Utility.titleToUppercase(call.getKey())).getArgument().values());
					} else {
						preambleTemp += objNameStr + ".";
						preambleTemp += Utility.titleToLowercase(call.getKey()) + "(";
						argumentList = new ArrayList<VariableToken>(varType.getSymbolTable().getMethod()
								.get(Utility.titleToLowercase(call.getKey())).getArgument().values());
					}

					HashMap<String, String> argPreambleMap = new HashMap<>();
//					List<VariableToken> argumentList = new ArrayList<VariableToken>(
//							varType.getSymbolTable().getMethod().get(methodName).getArgument().values());

					// 待修
					for (int j = 0; j < call.getValue().size(); j++) {
						argPreambleMap.clear();

						VariableType argType = argumentList.get(j).getType();
						argPreambleMap = preambleQuery(call.getValue().get(j), argType.getType(), argType);
						preambleTemp += argPreambleMap.get(objName) + ", ";
						if (!(argPreambleMap.get(objConstructor).equals("")))
							objAdditionalStr += "\r\n" + argType.getDclType() + " " + argPreambleMap.get(objName) + "="
									+ argPreambleMap.get(objConstructor) + ";";
						if (!(argPreambleMap.get(objPreamble).equals("")))
							objAdditionalStr += "\r\n" + argPreambleMap.get(objPreamble) + ";";

						objAdditionalStr += argPreambleMap.get(objAdditional);
					}

					preambleTemp = Utility.delEndRedundantSymbol(preambleTemp, ", ") + ")";
					if (call.getKey().equalsIgnoreCase(sdName))
						objConstructorStr += preambleTemp;
					else
						objPreambleStr += preambleTemp;
				}

				if (objPreambleStr.equals("")) {
					seq.put(objName, objConstructorStr);
					seq.put(objConstructor, "");
					objNameCountBacktraction(varType.getType());// 直接使用constructor回傳，未使用變數，該變數名count回溯
				} else {
					seq.put(objName, objNameStr);
					seq.put(objConstructor, objConstructorStr);
				}
				seq.put(objPreamble, objPreambleStr);
				seq.put(objAdditional, objAdditionalStr);
			} catch (Exception e) {
				seq.put(objName, "");
				seq.put(objConstructor, "");
				seq.put(objPreamble, "");
				seq.put(objAdditional, "");
			}
		} else if (varType instanceof ArrayType) {

			HashMap<String, String> arrPreambleMap = new HashMap<>();
			arrPreambleMap = ((ArrayType) varType).genPreambleStr(target); // 待改
			seq.put(objName, arrPreambleMap.get(objName));
			seq.put(objConstructor, arrPreambleMap.get(objConstructor));
			seq.put(objPreamble, "");
			seq.put(objAdditional, arrPreambleMap.get(objAdditional));
			ArrayType.dclArrCount++;
		} else {// base type

			String targetCLPData = "";

//			for (TypeTestData targetData : target) {
//				targetCLPData += targetData.transformCLPData() + ", ";
//			}
//			targetCLPData = ((targetCLPData.endsWith(", ")) ? targetCLPData.substring(0, targetCLPData.length() - 3)
//					: targetCLPData);

			
			if (varType instanceof StringType) {
				if(target instanceof StringTypeTestData)
					seq.put(objName, "\"" + ((StringTypeTestData) target).getValue() + "\"");
				else if (target instanceof UserDefinedTypeTestData){ //  Arguments Data formal of preambleQuery are CLP Data formal 
					TypeTestData newTarget = ((UserDefinedTypeTestData) target).changeTypeTestData(varType);
					seq.put(objName, "\"" + ((StringTypeTestData) newTarget).getValue() + "\"");
				}
					
			}else {
				seq.put(objName, target.getValueString());
			}

			seq.put(objConstructor, "");
			seq.put(objPreamble, "");
			seq.put(objAdditional, "");
		}
		return seq;
	}

}
