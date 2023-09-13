package ccu.pllab.tcgen.launcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.internal.core.JavaProject;
import org.xml.sax.SAXException;

import com.parctechnologies.eclipse.EclipseException;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGStartNode;
import ccu.pllab.tcgen.AbstractSyntaxTree.AbstractSyntaxTreeNode;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.AbstractType.UserDefinedType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.transform.AST2CLG;
import ccu.pllab.tcgen.transform.CLG2Path;
import ccu.pllab.tcgen.transform.OCL2AST;
import ccu.pllab.tcgen.transform.Splitter;
import ccu.pllab.tcgen.transform.TestDate2TestDataTree;
import ccu.pllab.tcgen.transform.UML2Preamble;
import ccu.pllab.tcgen.transform.UML2SymbolTable;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;
import tcgenplugin_2.handlers.BlackBoxHandler;;

public class BlackBoxLauncher {
	private File ocl;
	private File classUml;
	private OCL2AST oclParser;
	private AST2CLG clgParser;
	private CLG2Path pathParser;
	private UML2SymbolTable umlParser;
	private UML2Preamble genPreamble;
	private AbstractSyntaxTreeNode oclAst;
	private SymbolTable symbolTable;
	private ArrayList<CLGGraph> clgGraph;
	private CLGGraph invCLG;
	private IProgressMonitor pmonitor;
	public static long startTime;

	public BlackBoxLauncher(File ocl, File classUml, IProgressMonitor monitor) {
		this.ocl = ocl;
		this.classUml = classUml;
		this.pmonitor = monitor;
	}

	public AbstractSyntaxTreeNode getAST() {
		return this.oclAst;
	}

	public SymbolTable getSymbolTable() {
		return this.symbolTable;
	}

	public ArrayList<CLGGraph> getCLGGraph() {
		return this.clgGraph;
	}

	public IProgressMonitor getPmonitor() {
		return pmonitor;
	}

	public CLGGraph getInvCLG() {
		return this.invCLG;
	}

	// need2GenTestData紀錄是否需要產生測試資料
	public void genBlackBoxTestScripts(boolean need2GenTestData) {
		try {
			/* UML -> symboltable */
			startTime = System.currentTimeMillis();
			this.umlParser = new UML2SymbolTable(classUml);
			this.umlParser.makeSymbolTable();
			this.symbolTable = this.umlParser.getSymbolTable();
			Main.className = this.symbolTable.getClassName();
			Main.symbolTable = this.symbolTable;
			Main.localSymbolTable.add(this.symbolTable);

//			OCL -> AST
			this.oclParser = new OCL2AST();
			oclParser.makeAST(ocl);

			/**/
			// oclParser.typeToAst(Main.typeTable);
			this.oclAst = oclParser.getAbstractSyntaxTree();
			oclAst.toGraphViz();
			BlackBoxHandler.logger.info("create AST Using Time:" + (System.currentTimeMillis() - startTime) + " ms");
			pmonitor.worked(10);

//			AST -> CLG
			this.clgParser = new AST2CLG();
			clgParser.genCLG(oclAst, this.symbolTable, Main.criterion);
			this.clgGraph = clgParser.getCLGGraph();
			this.invCLG = clgParser.getInvCLG();
			BlackBoxHandler.logger
					.info("create CLG model Using Time:" + (System.currentTimeMillis() - startTime) + " ms");
			pmonitor.worked(10);

			String className = this.symbolTable.getClassName();
			// add inv clg in typeTable
			((UserDefinedType) Main.typeTable.get(className, null)).addInvCLG(this.invCLG);

			this.pathParser = new CLG2Path(symbolTable, Main.typeTable);
			// 編譯預設method
			this.pathParser.compileReservedMethod();
			if (need2GenTestData) {
				this.pathParser.compileLabelingMethod(Main.typeTable);
				this.pathParser.compileTestDataTransformMethod(Main.typeTable);
			}
			BlackBoxHandler.logger.info(
					"create compileReservedMethod CLP  Using Time:" + (System.currentTimeMillis() - startTime) + " ms");

//			CLG -> JUnit
//			this.pathParser = new CLG2Path();
//			this.symbolTable = oclParser.getSymbolTable();
//			pathParser.setAttribute(invCLG, symbolTable);
//			pathParser.genTestScripts(clgGraph, classUml);
			pmonitor.worked(10);
			pathParser.setAttribute(invCLG, symbolTable);
			pathParser.init(clgGraph);
			pathParser.genMethodCLP(clgGraph, DataWriter.testMethodCLP_output_path + "/" + Main.className + "CLP" + "/",
					Main.typeTable);
			pmonitor.worked(10);

			/* SD -> Preamble */
			this.genPreamble = new UML2Preamble(classUml);
			this.genPreamble.genPreambleCLP(DataWriter.testMethodCLP_output_path + "/" + Main.className + "CLP" + "/",
					this.symbolTable);
			pmonitor.worked(10);

			if (need2GenTestData) {
				for (int number = 0; number < clgGraph.size(); number++) {
					pathParser.genTestData(clgGraph, number);
					pmonitor.worked(10);
					pathParser.genTestScript();
					pmonitor.worked(60 / clgGraph.size());
					if (pmonitor.isCanceled()) {
						throw new OperationCanceledException();
					}
				}
			}

			BlackBoxHandler.logger
					.info("create CLP and get TD Using Time:" + (System.currentTimeMillis() - startTime) + " ms");

			String testScript = pathParser.getTestScript();
			testScript += "}";
			String testOutputPath = DataWriter.output_folder_path + "/test script/team/ccu/pllab/";
			switch (BlackBoxHandler.coverageCriteria) {
			case "DC":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dc/");
				break;
			case "DCC":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dcc/");
				break;
			case "MCC":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "mcc/");
				break;
			default:
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dcc/");

			}

			BlackBoxHandler.logger.info("Total Using Time:" + (System.currentTimeMillis() - startTime) + " ms");
			BlackBoxHandler.logger.info("---------------------------------------------------------------------------");
//			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//			IProject project = root.getProject(BlackBoxHandler.CurrentProjName);
//			IFile file = project.getFile(BlackBoxHandler.CurrentEditorProjectPath+"/src/com.test");
//			IFolder f = (IFolder) file;
//			f.create(false, false, null);
//			prepare((IFolder) file.getParent());
			// IJavaProject targetProject = JavaCore.create(project);

			// System.out.println("Execution
			// Succeed"+project.hasNature(JavaCore.NATURE_ID));

			oclParser = null;
			clgParser = null;
			pathParser = null;
			// Main.typeTable = null;

//			JOptionPane.showMessageDialog(null, "Execution Succeed", "Result", JOptionPane.INFORMATION_MESSAGE );
		} catch (Exception e1) {
//			JOptionPane.showMessageDialog(null, "Execution Failed", "Result", JOptionPane.INFORMATION_MESSAGE );
			System.out.println("Execution Failed");
			e1.printStackTrace();
		}
	}

	public void genBlackBoxTestScripts_VersionControl(String ProjectName, String versionsaveFilePath,String version, String compareVer, String coverageCriteria) {
			if(coverageCriteria.equals("DC")) {
				Main.criterion=Criterion.dc;
				BlackBoxHandler.coverageCriteria = "DC";
			}
			else if(coverageCriteria.equals("DCC")) {
				Main.criterion=Criterion.dcc;
				BlackBoxHandler.coverageCriteria = "DCC";
			}
			else { //以後新增用
				Main.criterion=Criterion.dc;
				BlackBoxHandler.coverageCriteria = "DC";
			}
			BlackBoxHandler.CurrentEditorName = ProjectName;
			Main.TestType = "BlackBox";
			generate_testinfo = false;
			this.version_saveFilePath = versionsaveFilePath;
			DataWriter.output_folder_path = "D:///version_control_data//"+ProjectName+"//"; //隨便設的 不會用到 但需要設定
			DataWriter.Clg_output_path = DataWriter.output_folder_path+"test model//";//
			DataWriter.testPath_output_path = DataWriter.output_folder_path+"test paths//";//
			DataWriter.testCons_output_path = DataWriter.output_folder_path+"test//";//
			DataWriter.testData_output_path = DataWriter.output_folder_path+"test data"; //
			DataWriter.initOutputPath();
			Main.boundary_analysis=true;
			Main.msort=false;


			/* UML -> symboltable */
			startTime = System.currentTimeMillis();
			this.umlParser = new UML2SymbolTable(classUml);
			this.umlParser.makeSymbolTable();
			this.symbolTable = this.umlParser.getSymbolTable();
			Main.className = this.symbolTable.getClassName();
			Main.symbolTable = this.symbolTable;
			Main.localSymbolTable.add(this.symbolTable);

//			OCL -> AST
			this.oclParser = new OCL2AST();
			oclParser.makeAST(ocl);

			/**/
			// oclParser.typeToAst(Main.typeTable);
			this.oclAst = oclParser.getAbstractSyntaxTree();
			oclAst.toGraphViz();
			BlackBoxHandler.logger.info("create AST Using Time:" + (System.currentTimeMillis() - startTime) + " ms");

//			AST -> CLG
			this.clgParser = new AST2CLG();
			clgParser.genCLG(oclAst, this.symbolTable, Main.criterion);
			this.clgGraph = clgParser.getCLGGraph();
			this.invCLG = clgParser.getInvCLG();
			BlackBoxHandler.logger
					.info("create CLG model Using Time:" + (System.currentTimeMillis() - startTime) + " ms");

			String className = this.symbolTable.getClassName();
			// add inv clg in typeTable
			((UserDefinedType) Main.typeTable.get(className, null)).addInvCLG(this.invCLG);

			this.pathParser = new CLG2Path(symbolTable, Main.typeTable);
			// 編譯預設method
			this.pathParser.compileReservedMethod();
			if (need2GenTestData) {
				this.pathParser.compileLabelingMethod(Main.typeTable);
				this.pathParser.compileTestDataTransformMethod(Main.typeTable);
			}
			BlackBoxHandler.logger.info(
					"create compileReservedMethod CLP  Using Time:" + (System.currentTimeMillis() - startTime) + " ms");

//			CLG -> JUnit
//			this.pathParser = new CLG2Path();
//			this.symbolTable = oclParser.getSymbolTable();
//			pathParser.setAttribute(invCLG, symbolTable);
//			pathParser.genTestScripts(clgGraph, classUml);

			pathParser.setAttribute(invCLG, symbolTable);
			pathParser.init(clgGraph);
			pathParser.genMethodCLP(clgGraph, DataWriter.testMethodCLP_output_path + "/" + Main.className + "CLP" + "/",
					Main.typeTable);

			/* SD -> Preamble */
			this.genPreamble = new UML2Preamble(classUml);
			this.genPreamble.genPreambleCLP(DataWriter.testMethodCLP_output_path + "/" + Main.className + "CLP" + "/",
					this.symbolTable);

			for (int number = 0; number < clgGraph.size(); number++) {
				pathParser.genTestData_VersionControl(clgGraph,number,version,compareVer);
				pathParser.genTestScript();
			}

			BlackBoxHandler.logger
					.info("create CLP and get TD Using Time:" + (System.currentTimeMillis() - startTime) + " ms");

			String testScript = pathParser.getTestScript();
			testScript += "}";
			InputStream is = new ByteArrayInputStream(testScript.getBytes());
			FileHandleToServer.storeFile(pathParser.getClassName()+"\\"+ version +"\\",
					                     pathParser.getClassName()+"Test.txt"
											 , is);


			String testOutputPath = DataWriter.output_folder_path + "/test script/team/ccu/pllab/";
			switch (BlackBoxHandler.coverageCriteria) {
			case "DC":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dc/");
				break;
			case "DCC":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dcc/");
				break;
			case "MCC":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "mcc/");
				break;
			default:
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dcc/");

			}

			BlackBoxHandler.logger.info("Total Using Time:" + (System.currentTimeMillis() - startTime) + " ms");
			BlackBoxHandler.logger.info("---------------------------------------------------------------------------");

			oclParser = null;
			clgParser = null;
			pathParser = null;
			// Main.typeTable = null;

//			JOptionPane.showMessageDialog(null, "Execution Succeed", "Result", JOptionPane.INFORMATION_MESSAGE );
		} catch (Exception e1) {
//			JOptionPane.showMessageDialog(null, "Execution Failed", "Result", JOptionPane.INFORMATION_MESSAGE );
			System.out.println("Execution Failed");
			e1.printStackTrace();
		}	
	}

}
