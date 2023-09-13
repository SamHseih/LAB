package ccu.pllab.tcgen.launcher;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import ccu.pllab.tcgen.TestCase.TestDataClassLevel;
import ccu.pllab.tcgen.TestCase.TestScriptGeneratorClassLevel;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import ccu.pllab.tcgen.clgGraph2Path.CoverageCriterionManager;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.sd2clg.SD2CLG;
import ccu.pllab.tcgen.sd2clg.SDXML2SD;
import ccu.pllab.tcgen.sd2clg.StateDigram;
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
import tcgenplugin_2.handlers.ClassLevelHandler;
import tcgenplugin_2.handlers.WhiteBoxHandler;;

public class ClassLevelLauncher {
	private File ocl;
	private File uml;
	private File cdUml;
	private File sdUml;
	private CLG2Path pathParser;
	private UML2SymbolTable umlParser;
	private SymbolTable symbolTable;
	private ArrayList<CLGGraph> clgGraph;
	private CLGGraph invCLG;
	private IProgressMonitor pmonitor;

	public ClassLevelLauncher(File ocl, File uml, IProgressMonitor monitor) {
		this.ocl = ocl;

		this.uml = uml;
		Splitter splitter = new Splitter(uml);

		try {
			this.cdUml = splitter.split2CDuml();
			this.sdUml = splitter.split2CompleteSDuml();
		} catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.pmonitor = monitor;
		Main.localSymbolTable = new ArrayList<SymbolTable>();
		Main.typeTable = new TypeTable();
	}

	public SymbolTable getSymbolTable() {
		return this.symbolTable;
	}

	public ArrayList<CLGGraph> getCLGGraph() {
		return this.clgGraph;
	}

	public CLGGraph getInvCLG() {
		return this.invCLG;
	}

	public void genClassLevelTestScripts() {
		try {
			// CDUML -> symboltable
			this.umlParser = new UML2SymbolTable(cdUml);
			this.umlParser.makeSymbolTable();
			this.symbolTable = this.umlParser.getSymbolTable();
			Main.symbolTable = this.symbolTable;
			Main.localSymbolTable.add(this.symbolTable);

			// SDUml -> StateDigram
			SDXML2SD parsd = new SDXML2SD();
			StateDigram st = new StateDigram();
			st = parsd.convert(sdUml, cdUml, this.symbolTable);

			// StateDigram -> SDCLG
			SD2CLG cpsd = new SD2CLG();

			CLGGraph sdCLG = new CLGGraph();
			sdCLG = cpsd.convert(st);

			// OCL -> AST
			OCL2AST oclParser = new OCL2AST();
			oclParser.makeAST(ocl);
			oclParser.getAbstractSyntaxTree().toGraphViz();
			Main.ast = oclParser.getAbstractSyntaxTree();

			// AST -> CLG
			AST2CLG oclCLG = new AST2CLG();
			oclCLG.genCLG(Main.ast, this.symbolTable, Criterion.dc);

			// 編譯預設method
			this.pathParser = new CLG2Path(symbolTable, Main.typeTable);
			this.pathParser.compileReservedMethod();

			// 設定 className、attribute
			this.pathParser.setAttribute(symbolTable);
			this.pathParser.init(oclCLG.getCLGGraph());
			this.pathParser.genMethodCLP(oclCLG.getCLGGraph(), DataWriter.testCons_output_path, Main.typeTable);

			// 待改
			// this.pathParser.genMethodCLPClassLevel(oclCLG.getCLGGraph(),
			// DataWriter.testCons_output_path,Main.typeTable);

			// fins path and get Test data
			this.pathParser.genTestDataClassLevel(sdCLG, oclCLG.getCLGGraph(), Main.criterion.toString(),
					Main.typeTable);

			// create test script
			this.pathParser.genTestScript();

			String testScript = pathParser.getTestScript();
			String className = pathParser.getClassName();
			testScript += "}";

			String testOutputPath = DataWriter.output_folder_path + "/test script/team/ccu/pllab/";

			switch (ClassLevelHandler.coverageCriteria) {
			case "DC":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dc/");
				break;
			case "DCC":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dcc/");
				break;
			case "MCC":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "mcc/");
				break;
			case "DC_DUP":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dcdup/");
				break;
			case "DCC_DUP":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dccdup/");
				break;
			case "MCC_DUP":
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "mccdup/");
				break;
			default:
				DataWriter.writeInfo(testScript, className + "Test", "java", testOutputPath + "dc/");

			}

			pathParser = null;
			Main.typeTable = null;

		} catch (Exception e1) {
			System.out.println("Execution Failed");
			e1.printStackTrace();
		}
	}

}
