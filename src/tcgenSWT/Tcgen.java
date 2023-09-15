package tcgenSWT;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.*;
import java.lang.String;

import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;

import org.w3c.dom.*;
import org.xml.sax.*;

import ccu.pllab.tcgen.pathCLPFinder.CLPSolver;
import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.TestCase.TestData;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import ccu.pllab.tcgen.clgGraph2Path.*;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLP2data.CLP2Data;
import ccu.pllab.tcgen.transform.CLG2Path;
import tcgenplugin_2.handlers.BlackBoxHandler;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;
import com.parctechnologies.eclipse.*;

public class Tcgen {
	
	public Tcgen() {
	}
	
	public static void main(String[] args) throws EclipseException, IOException {
		long startTime = System.currentTimeMillis();
		/*
		try {
			
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
			
			InputStream input = null;
			
			input = new FileInputStream("C://Users//Benson//Downloads//CGUI_Spec.xml");
			
			Document doc = domBuilder.parse(input);
			
			Element root = doc.getDocumentElement();	
			
			NodeList nList = root.getChildNodes();	
			
			Node Text = nList.item(1);
			
			System.out.println(root.getAttribute("name"));
			
			System.out.println(root.getAttribute("text"));
			
			System.out.println(root.getAttribute("bounds"));
			
			System.out.println(((Element) Text).getAttribute("name"));
			
			System.out.println(((Element) Text).getAttribute("bounds"));
			
			for (int temp = 3; temp < nList.getLength(); temp = temp + 2) {
				
				Node button = nList.item(temp);
				
				System.out.println(((Element) button).getAttribute("name"));
				
				System.out.println(((Element) button).getAttribute("text"));
				
				System.out.println(((Element) button).getAttribute("bounds"));
				
				System.out.println(((Element) button).getAttribute("selectionlistener"));
				
			}
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		*/
		
		//砞﹚本把计籔郎纗竚
		BlackBoxHandler.coverageCriteria = "DCC";
		Main.criterion = Criterion.dcc;
		Main.TestType = "BlackBox";
		Main.typeTable = new TypeTable();
		Main.typeTable.init();
		Main.boundary_analysis = true;
		String test_output_path = "C:\\Users\\Benson\\Downloads\\TD\\";
		DataWriter.testPath_output_path = test_output_path;
		DataWriter.testCons_output_path = test_output_path;
		DataWriter.testData_output_path = test_output_path;
		DataWriter.output_folder_path = test_output_path;
		
		//砞竚CLGΑゅ竊翴ず甧
		CLGLiteralNode Result = new CLGLiteralNode("Result", "int");
		CLGLiteralNode Nu = new CLGLiteralNode("null", "string");
		CLGLiteralNode Suc = new CLGLiteralNode("success", "string");
		CLGLiteralNode Fa = new CLGLiteralNode("fail", "string");
		CLGLiteralNode Exception = new CLGLiteralNode("Exception", "int");
		CLGLiteralNode Ex = new CLGLiteralNode("exception", "string");
		CLGLiteralNode Shell = new CLGLiteralNode("shell", "int");
		CLGLiteralNode DragSource = new CLGLiteralNode("dragsource", "int");
		CLGLiteralNode DropTarget = new CLGLiteralNode("droptarget", "int");
		CLGLiteralNode Text = new CLGLiteralNode("text", "sting");
		CLGLiteralNode Label = new CLGLiteralNode("label", "sting");
				
		//砞竚CLGΑ笲衡竊翴ず甧Α		
		CLGOperatorNode SN = new CLGOperatorNode("=");
		SN.setLeftOperand(Shell);
		SN.setRightOperand(Nu);
		CLGOperatorNode SNN = new CLGOperatorNode("<>");
		SNN.setLeftOperand(Shell);
		SNN.setRightOperand(Nu);
		CLGOperatorNode DSE = new CLGOperatorNode("=");
		DSE.setLeftOperand(DragSource);
		DSE.setRightOperand(Text);
		CLGOperatorNode DSNE = new CLGOperatorNode("<>");
		DSNE.setLeftOperand(DragSource);
		DSNE.setRightOperand(Text);
		CLGOperatorNode DSN = new CLGOperatorNode("=");
		DSN.setLeftOperand(DragSource);
		DSN.setRightOperand(Nu);
		CLGOperatorNode DSNN = new CLGOperatorNode("<>");
		DSNN.setLeftOperand(DragSource);
		DSNN.setRightOperand(Nu);
		CLGOperatorNode DSS = new CLGOperatorNode("=");
		DSS.setLeftOperand(Result);
		DSS.setRightOperand(Suc);
		CLGOperatorNode DSF = new CLGOperatorNode("=");
		DSF.setLeftOperand(Result);
		DSF.setRightOperand(Fa);
		CLGOperatorNode DTE = new CLGOperatorNode("=");
		DTE.setLeftOperand(DropTarget);
		DTE.setRightOperand(Label);
		CLGOperatorNode DTNE = new CLGOperatorNode("<>");
		DTNE.setLeftOperand(DropTarget);
		DTNE.setRightOperand(Label);
		CLGOperatorNode DTN = new CLGOperatorNode("=");
		DTN.setLeftOperand(DropTarget);
		DTN.setRightOperand(Nu);
		CLGOperatorNode DTNN = new CLGOperatorNode("<>");
		DTNN.setLeftOperand(DropTarget);
		DTNN.setRightOperand(Nu);
		CLGOperatorNode DTS = new CLGOperatorNode("=");
		DTS.setLeftOperand(Result);
		DTS.setRightOperand(Suc);
		CLGOperatorNode DTF = new CLGOperatorNode("=");
		DTF.setLeftOperand(Result);
		DTF.setRightOperand(Fa);
		CLGOperatorNode E = new CLGOperatorNode("=");
		E.setLeftOperand(Exception);
		E.setRightOperand(Ex);
		
		
		//盢CLGΑCLG瓜讽い
		CLGGraph clg1 = new CLGGraph(DSE);
		CLGGraph clg2 = new CLGGraph(DSNE);
		CLGGraph clg3 = new CLGGraph(DSN);
		CLGGraph clg4 = new CLGGraph(DSNN);
		CLGGraph clg5 = new CLGGraph(E);
		CLGGraph clg6 = new CLGGraph(DSF);
		CLGGraph clg7 = new CLGGraph(DTE);
		CLGGraph clg8 = new CLGGraph(DTNE);
		CLGGraph clg9 = new CLGGraph(DTN);
		CLGGraph clg10 = new CLGGraph(DTNN);
		CLGGraph clg11 = new CLGGraph(DTS);
		CLGGraph clg12 = new CLGGraph(DTF);
		CLGGraph clg13 = new CLGGraph(DSF);
		CLGGraph clg14 = new CLGGraph(DTF);
		CLGGraph clg15 = new CLGGraph(SN);
		CLGGraph clg16 = new CLGGraph(SNN);
		
		clg7.graphAnd(clg11);
		clg8.graphAnd(clg12);
		clg7.graphOr(clg8);
		clg1.graphAnd(clg7);
		clg2.graphAnd(clg6);
		clg1.graphOr(clg2);
		clg10.graphAnd(clg1);
		clg9.graphAnd(clg13);
		clg10.graphOr(clg9);
		clg4.graphAnd(clg10);
		clg3.graphAnd(clg14);
		clg4.graphOr(clg3);
		clg16.graphAnd(clg4);
		clg15.graphAnd(clg5);
		clg16.graphOr(clg15);
		
		//盢CLG瓜纗秈ArrayListい
		ArrayList<CLGGraph> Gnodes = new ArrayList<CLGGraph>();
		Gnodes.add(clg16);

		//礶CLG瓜琩
		String clgdraw = clg16.graphDraw();
		System.out.println(clgdraw);
		
		//砞竚SymbolTable妮┦の嘿戈癟
		String SWT = "Swt";
		SymbolTable ST = new SymbolTable(SWT);
		MethodToken MT = new MethodToken(SWT);
		ArrayList<VariableToken> VTAL = new ArrayList<VariableToken>();
		ArrayList<String> MPAL = new ArrayList<String>();	//ㄧ计把计ArrayList
		ArrayList<String> MPTAL = new ArrayList<String>();	//ㄧ计把计篈ArrayList
		String shell = "shell";		
		String dragsorce = "dragsource";
		String droptarget = "droptarget";
		String S = "string";
		String I = "int";

		MPAL.add(shell);
		MPAL.add(dragsorce);
		MPAL.add(droptarget);
		MPTAL.add(S);
		MPTAL.add(S);
		MPTAL.add(S);
		StringType SType = new StringType();
		IntType IType = new IntType();
		VariableToken VTshell = new VariableToken(shell, SType);
		VariableToken VTdragsorce = new VariableToken(dragsorce, SType);
		VariableToken VTdroptarget = new VariableToken(droptarget, SType);
		/*
		VTAL.add(VTShellX);	
		MT.addArgument(VTShellX);		
		ST.addAttributeMap(VTShellX);
		 */
		VTAL.add(VTshell);		
		MT.addArgument(VTshell);
		VTAL.add(VTdragsorce);		
		MT.addArgument(VTdragsorce);
		VTAL.add(VTdroptarget);		
		MT.addArgument(VTdroptarget);
		
		
		ST.addArgument(VTAL);
		ST.addMethod(MT);
		
		((CLGStartNode)(clg16.getStartNode())).setClassName(SWT);
		((CLGStartNode)(clg16.getStartNode())).setMethodName(SWT);
		((CLGStartNode)(clg16.getStartNode())).setRetType("String");
		((CLGStartNode)(clg16.getStartNode())).setMethodParameters(MPAL);
		((CLGStartNode)(clg16.getStartNode())).setMethodParameterTypes(MPTAL);
		
		Main.symbolTable =  ST;

	    
		try {
			CLG2Path clgp = new CLG2Path();
			//clgp.init(Gnodes);
			clgp.genTestData(Gnodes, 0);	
		} catch (IOException | ParserConfigurationException | SAXException | TemplateException | ModelAccessException
				| ParseException | EclipseException | TransformerException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print(System.currentTimeMillis()-startTime);
	}

}
