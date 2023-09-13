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
import ccu.pllab.tcgen.srcASTVisitor.SrcVisitProcess;
import ccu.pllab.tcgen.srcNodeVisitor.JAVA2CLG;
import ccu.pllab.tcgen.transform.AST2CLG;
import ccu.pllab.tcgen.transform.CLG2Path;
import ccu.pllab.tcgen.transform.OCL2AST;
import ccu.pllab.tcgen.transform.Splitter;
import ccu.pllab.tcgen.transform.UML2SymbolTable;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;
import tcgenplugin_2.handlers.WhiteBoxHandler;;

public class WhiteBoxLauncher {
	private File ocl;
	private File java;
	private File classUml;
	private CLG2Path pathParser;
	private UML2SymbolTable umlParser;
	private SymbolTable symbolTable;
	private ArrayList<CLGGraph> clgGraph;
	private CLGGraph invCLG;
	private IProgressMonitor pmonitor;

	public WhiteBoxLauncher(File java, File ocl, File classUml, IProgressMonitor monitor) {
		this.ocl = ocl;
		this.java = java;
		this.classUml = classUml;
		this.pmonitor = monitor;
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

	public void genWhiteBoxTestScripts(boolean genTestData) {
		try {
			// UML -> symboltable
			this.umlParser = new UML2SymbolTable(classUml);
			this.umlParser.makeSymbolTable();
			this.symbolTable = this.umlParser.getSymbolTable();

			// java -> AST -> CLG
			// 加入symbolTable與typeTable
			JAVA2CLG.setSymbolTable(this.symbolTable);
			JAVA2CLG.setTypeTable(Main.typeTable);
			SrcVisitProcess srcVisitProcess = new SrcVisitProcess(java, classUml);
			this.clgGraph = srcVisitProcess.getClg();

			// 編譯預設method
			this.pathParser = new CLG2Path(symbolTable, Main.typeTable);
			this.pathParser.compileReservedMethod();
			// this.pathParser.compileLabelingMethod(Main.typeTable); //暫時拿掉

			// 白箱invCLP 使用黑箱OCL inv
			// ocl -> ast
			OCL2AST oclParser = new OCL2AST();
			AbstractSyntaxTreeNode oclAst;
			oclParser.makeAST(ocl);

			// ast -> invCLG
			AST2CLG astParser = new AST2CLG();
			astParser.genInvCLG(oclParser.getAbstractSyntaxTree(), Main.symbolTable, Main.criterion);
			this.invCLG = astParser.getInvCLG();
			pmonitor.worked(10);

			String className = this.symbolTable.getClassName();
			// add inv clg in typeTable
			((UserDefinedType) Main.typeTable.get(className, null)).addInvCLG(this.invCLG);

			pathParser.init(clgGraph);
			pmonitor.worked(10);

			// if need to generate test data
			if (genTestData) {
				for (int number = 0; number < clgGraph.size(); number++) {
					pathParser.genTestData(clgGraph, number);
					pathParser.genTestScript();
					pmonitor.worked(60 / clgGraph.size());
					if (pmonitor.isCanceled()) {
						throw new OperationCanceledException();
					}
				}

				String testScript = pathParser.getTestScript();
				testScript += "}";
				String testOutputPath = DataWriter.output_folder_path + "/test script/team/ccu/pllab/";
				switch (WhiteBoxHandler.coverageCriteria) {
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
			}
//			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
//			IProject project = root.getProject(BlackBoxHandler.CurrentProjName);
//			IFile file = project.getFile(BlackBoxHandler.CurrentEditorProjectPath+"/src/com.test");
//			IFolder f = (IFolder) file;
//			f.create(false, false, null);
//			prepare((IFolder) file.getParent());
			// IJavaProject targetProject = JavaCore.create(project);

			// System.out.println("Execution
			// Succeed"+project.hasNature(JavaCore.NATURE_ID));

			pathParser = null;

//			JOptionPane.showMessageDialog(null, "Execution Succeed", "Result", JOptionPane.INFORMATION_MESSAGE );
		} catch (Exception e1) {
//			JOptionPane.showMessageDialog(null, "Execution Failed", "Result", JOptionPane.INFORMATION_MESSAGE );
			System.out.println("Execution Failed");
			e1.printStackTrace();
		}
	}

}
