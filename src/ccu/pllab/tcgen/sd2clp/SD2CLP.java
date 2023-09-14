package ccu.pllab.tcgen.sd2clp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.xml.sax.SAXException;

import com.parctechnologies.eclipse.EclipseException;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.sd2clg.*;
import ccu.pllab.tcgen.ecl2data.*;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLPFinder.CLPInfo;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

public class SD2CLP {
	private String SDName = "";
	private ArrayList<String> states = new ArrayList();
	private ArrayList<String> attributes = new ArrayList();

	public void convert(StateDigram st) {

//		String source = null;
//		String transition = null;
//		String method = null;
//		ArrayList<String> methodParameters = new ArrayList<String>();
//		CLGConstraint guard = null;
//		String target = null;		

		String clp = "";
		String clpContent = "";
		try {
			this.SDName = st.getSDName();
			// add Attribute and Change first word to UpperCase

			for (Entry<String, VariableToken> attr : st.getSDAttribute().entrySet()) {
				attributes.add("Self_" + Utility.titleToUppercase(attr.getKey()));
			}

			File dir = new File(DataWriter.testMethodCLP_output_path + st.getSDName() + "CLP/");
			dir.mkdir();
			FileWriter dataFile = new FileWriter(
					DataWriter.testMethodCLP_output_path + st.getSDName() + "CLP/" + st.getSDName() + "Preamble.ecl");
			BufferedWriter input = new BufferedWriter(dataFile);

			// get state information

			// System.out.println(st.getStates());
			for (State SDState : st.getStates().values()) {
				for (Transition SDTransitions : SDState.getTransitions().values()) {
					// get source state
					String source = SDState.getName();

					// get method name
					CLGMethodInvocationNode method = ((CLGMethodInvocationNode) SDTransitions.getMethod());
					String methodName = null;
					ArrayList<String> methodParameters = new ArrayList<String>();
					if (method != null) {
						methodName = method.getMethodName();
						methodParameters.clear();
						for (CLGConstraint cons : method.getMethodArgument()) {
							String element = cons.getConstraintName();
							methodParameters.add(Utility.titleToUppercase(element));
						}
					}
					// get guard condition
					CLGConstraint guard = null;
					guard = SDTransitions.getGuard();
					// get target state
					String target = SDTransitions.getTarget().getName();

					clp = this.genCLP(source, methodName, methodParameters, guard, target);

					input.write(clp);
					clpContent += clp;
				}
			}

//			for (int i = 0; i < st.getStates().size(); i++) {
//				// for(int j = 0; j < st.getStates().get(i).getTransitions().size();j++) {
//				for (int j = st.getStates().get(i).getTransitions().size() - 1; j >= 0; j--) {
//					// get source state
//					source = st.getStates().get(i).getName();
//					// get method name
//					if (st.getStates().get(i).getTransitions().get(j).getMethod() == null)
//						method = null;
//					else {
//						methodParameters.clear();
//						// 讀取函式名稱
//						method = ((CLGMethodInvocationNode) st.getStates().get(i).getTransitions().get(j).getMethod())
//								.getMethodName();
//						// 讀取參數
//						for (CLGConstraint cons : ((CLGMethodInvocationNode) st.getStates().get(i).getTransitions()
//								.get(j).getMethod()).getMethodArgument()) {
//							String element = cons.getConstraintName();
//							methodParameters.add(element.substring(0, 1).toUpperCase() + element.substring(1));
//						}
//					}
//					// get guard condition
//					guard = st.getStates().get(i).getTransitions().get(j).getGuard();
//
//					// get target state
//					target = st.getStates().get(i).getTransitions().get(j).getTarget().getName();
//					// 判斷source target是否符合clp規定 並產生clp
////					if(source.matches("[a-z][a-zA-Z0-9_]*") && source.matches("[a-z][a-zA-Z0-9_]*")) {
////						clp = this.genCLP(source, transition, guard, target);
////						input.write(clp);
////						System.out.print(clp);
////					}
////					else
////						throw new Exception();
//
//					clp = this.genCLP(source, method, methodParameters, guard, target);
//
//					input.write(clp);
//					clpContent += clp;
//				}
//			}

			// add final state
			input.write("\n" + this.SDName.substring(0, 1).toLowerCase() + this.SDName.substring(1)
					+ "FinalState(Target, Target, [], []).\n");
			input.close();

			clpContent += "\n" + this.SDName.substring(0, 1).toLowerCase() + this.SDName.substring(1)
					+ "FinalState(Target, Target, [], []).\n";

			DataWriter.writeInfo(clpContent, st.getSDName() + "Preamble", "ecl", DataWriter.testCons_output_path);

			states.clear();
			attributes.clear();
		} catch (Exception e) {
			// System.out.println("predicate name error!!");
			System.out.println(e);
		}
	}

	private String genCLP(String source, String method, ArrayList methodParameters, CLGConstraint guard,
			String target) {
		String clp = "";
		String args = "";
		String SDNameForCLP = "";
		String newSource = "";
		String newTarget = "";

		ArrayList methodParametersPost = new ArrayList();

		/* rename */
		SDNameForCLP = this.SDName.substring(0, 1).toLowerCase() + this.SDName.substring(1);
		newSource = SDNameForCLP + source.substring(0, 1).toUpperCase() + source.substring(1);
		newTarget = SDNameForCLP + target.substring(0, 1).toUpperCase() + target.substring(1);

		if (methodParameters.toString().equals("[ ]") || methodParameters.isEmpty()) {
			methodParameters.clear();
			args = "[]";
		} else {
			args = "[" + methodParameters.toString().substring(1, methodParameters.toString().length() - 1) + "]";
			for (Object element : methodParameters) {
				methodParametersPost.add(element + "_post");
			}
		}

		// add initial state clp
		if (source.equals("InitialState")) {
			clp += ":- lib(ic).\n";
			clp += ":- lib(timeout).\n\n";
			clp += SDNameForCLP + "Preamble(Target, FuncSeq, ArgSeq):-\n";
			clp += "	" + SDNameForCLP + "InitialState(Target, FuncSeq, ArgSeq).\n";
			clp += SDNameForCLP + "InitialState(" + attributes + ", [" + SDNameForCLP + "|FuncSeq], [" + args
					+ "|ArgSeq]):-\n";
			clp += "	" + SDNameForCLP + this.SDName.substring(0, 1).toUpperCase() + this.SDName.substring(1)
					+ "(Pre," + methodParameters + ", Cur, " + methodParametersPost + ", Result, Exception),\n";
			clp += "	" + newTarget + "(" + attributes + ", Cur, FuncSeq, ArgSeq).\n";
		}
		// add final state
		else if (source.equals("FinalState")) {
			// clp = clp +"\n"+ this.SDName.substring(0, 1).toLowerCase() +
			// this.SDName.substring(1)+"FinalState(Target, Target, [], []).\n";
		} else {
			// stop state clp
			if (this.states.contains(source) != true) {
				this.states.add(source);
				clp = clp + "\n" + newSource + "(Target, " + attributes + ", FuncSeq, ArgSeq):-\n";
				clp = clp + "	" + SDNameForCLP + "FinalState(Target, " + attributes + ", FuncSeq, ArgSeq).\n";
			}
			// main state clp
			if (method != null) {
				clp = clp + newSource + "(Target, " + attributes + ", [" + method + "|FuncSeq],[" + args
						+ "|ArgSeq] ):-\n";
				if (guard != null) {
					StringBuilder temp = new StringBuilder("");
					CLPInfo guard_clpinfo = guard.getCLPInfo(new HashMap<>(), new HashMap<>());

					for (String methodCallCLP : guard_clpinfo.getMethodCallCLP()) {
						temp.append(methodCallCLP + ",\n");
					}

					temp.append(guard_clpinfo.getReturnCLP() + ",\n");

					String newGuard = Utility.delEndRedundantSymbol(temp.toString(), ",\n");
					clp = clp + "	" + newGuard + ",\n";
				}
				clp = clp + "	" + SDNameForCLP + method.substring(0, 1).toUpperCase() + method.substring(1) + "("
						+ attributes + ", " + methodParameters + ", New," + methodParametersPost
						+ ", Result, Exception),\n";
				clp = clp + "	" + newTarget + "(Target, New, FuncSeq, ArgSeq).\n";
			}
		}
		return clp;
	}

	/* 測試用 */
//	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TemplateException, ModelAccessException, ParseException, org.apache.commons.cli.ParseException, JSONException, EclipseException {
//		SDXML2SD parsd = new SDXML2SD();
//		StateDigram st=parsd.convert(new File("C:/tcgen/examples/unboundedStack/unboundedStackSD.uml"),new File("C:/tcgen/examples/unboundedStack/unboundedStack(old).uml"));
//		SD2CLP s = new SD2CLP();
//		ArrayList seq = new ArrayList();
//		s.convert(st);
//		ExecuteCLP clp = new ExecuteCLP();
//		seq = clp.query("[[10,200,30,55],4]", "unboundedStack");
//		System.out.println("--------------------*-----------------"+seq);
//	}

}
