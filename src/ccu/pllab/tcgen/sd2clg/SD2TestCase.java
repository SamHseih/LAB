package ccu.pllab.tcgen.sd2clg;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.xml.sax.SAXException;

import ccu.pllab.tcgen.AbstractCLG.CLGGraph;
import ccu.pllab.tcgen.AbstractCLG.CLGStartNode;
import ccu.pllab.tcgen.AbstractType.TypeTable;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.TestCase.TestDataClassLevel;
import ccu.pllab.tcgen.TestCase.TestScriptGeneratorClassLevel;
import ccu.pllab.tcgen.ast.ASTUtil;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import ccu.pllab.tcgen.clgGraph2Path.CoverageCriterionManager;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.libs.DresdenOCLASTtoInternelAST;
import ccu.pllab.tcgen.libs.pivotmodel.ClassDiagInfo;
import ccu.pllab.tcgen.libs.pivotmodel.ClassDiagToJson;
import ccu.pllab.tcgen.libs.pivotmodel.Model;
import ccu.pllab.tcgen.libs.pivotmodel.type.TypeFactory;
import ccu.pllab.tcgen.transform.AST2CLG;
import ccu.pllab.tcgen.transform.OCL2AST;
import ccu.pllab.tcgen.transform.UML2SymbolTable;
import tcgenplugin_2.handlers.ClassLevelHandler;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.standalone.facade.StandaloneFacade;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

//20210518 已經不再使用了
public class SD2TestCase {

	public SD2TestCase() {
		Main.typeTable = new TypeTable();
	}

	public SD2TestCase(File SD, File CD, File OCL) throws Exception {
		SDXML2SD parsd = new SDXML2SD();
		SD2CLG cpsd = new SD2CLG();

		// QueueUnbounded , Queuebounded, Stackbounded, StackUnbounded,CoffeeMachine
		StateDigram st = new StateDigram();
		CLGGraph gtclggraph = new CLGGraph();
		AST2CLG ocl_clg = null;
		OCL2AST oclParser = new OCL2AST();
		// List<ccu.pllab.tcgen.ast.Constraint>OCLCons = new
		// ArrayList<ccu.pllab.tcgen.ast.Constraint>();

		try {
			st = parsd.convert(SD, CD);
			System.out.println(st.getStates().get(0).getName());
			System.out.println(st.getStates());
			try {
				gtclggraph = cpsd.convert(st);
			} catch (org.apache.commons.cli.ParseException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // CLG-structure coffeemachine

			oclParser.makeAST(OCL);
			UML2SymbolTable umlParser = new UML2SymbolTable(CD);
			Main.localSymbolTable.add(umlParser.getSymbolTable());
			// oclParser.typeToAst(Main.typeTable);
			oclParser.getAbstractSyntaxTree().toGraphViz();
			Main.ast = oclParser.getAbstractSyntaxTree();
			// ocl_clg=new AST2CLG(Main.ast);
			ocl_clg = new AST2CLG();
			ocl_clg.genCLG(Main.ast, umlParser.getSymbolTable(), Criterion.dc);

			// OCLCons=parseOCL(OCL, CD);
		} catch (ParserConfigurationException | SAXException | IOException | TemplateException | ModelAccessException
				| ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CoverageCriterionManager coverman = new CoverageCriterionManager();
		// coverman.init(gtclggraph, ocl_clg.getCLGGraph(), Main.criterion.toString());

		List<TestDataClassLevel> resulttd = new ArrayList<TestDataClassLevel>();
		resulttd = coverman.genClassLevelTestSuite();

		TestScriptGeneratorClassLevel testscri = new TestScriptGeneratorClassLevel();
		testscri.genTestCase(resulttd, ("ccu.pllab.tcgen.TCGenExample.example." + st.getSDName()), st.getSDName(),
				st.getSDAttribute());
	}

	public static List<ccu.pllab.tcgen.ast.Constraint> parseOCL(File ocl, File uml)
			throws TemplateException, ModelAccessException, IOException, ParseException {
		Model context;
		IModel model;
		ClassDiagInfo class_diag_info;
		ClassDiagToJson class_diag_to_json;
		List<ccu.pllab.tcgen.ast.Constraint> OCLCons;
		// File uml = new File("../Examples/test20170304/coffeemachine.uml");
		class_diag_to_json = new ClassDiagToJson(uml);
		class_diag_info = new ClassDiagInfo(class_diag_to_json);
		context = new Model(class_diag_info);
		TypeFactory.getInstance().setModel(context);
		ASTUtil ast_util = new ASTUtil();
		DresdenOCLASTtoInternelAST ast_constructor = new DresdenOCLASTtoInternelAST(ast_util, context);

		StandaloneFacade.INSTANCE.initialize(new URL("file:./log4j.properties"));
		model = StandaloneFacade.INSTANCE.loadUMLModel(uml, new File("./resources/org.eclipse.uml2.uml.resources.jar"));
		// List<tudresden.ocl20.pivot.pivotmodel.Constraint> loaduml= StandaloneFacade.
		// //test20170304/date/date.ocl"
		// INSTANCE.parseOclConstraints(model, new
		// File("../Examples/test20170304/coffeemachine.ocl"));
		List<tudresden.ocl20.pivot.pivotmodel.Constraint> loaduml = StandaloneFacade. // test20170304/date/date.ocl"
				INSTANCE.parseOclConstraints(model, ocl);
		OCLCons = ast_constructor.parseOclTreeNodeFromPivotModel(loaduml);

		return OCLCons;
	}
}
