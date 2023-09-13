package ccu.pllab.tcgen.srcASTVisitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.xml.sax.SAXException;

import com.parctechnologies.eclipse.EclipseException;

import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractType.ArrayListType;
import ccu.pllab.tcgen.AbstractType.ArrayType;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.TestCase.TestCaseFactory;
import ccu.pllab.tcgen.TestCase.TestScriptGenerator;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import ccu.pllab.tcgen.clgGraph2Path.CoverageCriterionManager;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLPFinder.CLPSolver;
import ccu.pllab.tcgen.transform.CLG2Path;
import ccu.pllab.tcgen.transform.OCL2AST;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

public class SrcVisitProcess {
	ArrayList<CLGGraph> clg;

	public SrcVisitProcess(File java, File classUml) throws Exception {
		// TestCaseFactory tcFactory = new TestCaseFactory();
		/******************************************************/

		this.clg = new ArrayList<>();
		CompilationUnit comp = SrcJdtAstUtil.getCompilationUnit(java.toString());

		SrcVisitorUnit visitor = new SrcVisitorUnit();
		comp.accept(visitor);

		CLGCriterionTransformer clgTF = new CLGCriterionTransformer();
		for (CLGGraph graph : visitor.getCLGGraph()) {
			if (Main.criterion.equals(Criterion.dcc) || Main.criterion.equals(Criterion.dccdup)) {
				graph = clgTF.CriterionTransformer(graph, Criterion.dcc);
			} else if (Main.criterion.equals(Criterion.mcc) || Main.criterion.equals(Criterion.mccdup)) {
				graph = clgTF.CriterionTransformer(graph, Criterion.mcc);
			} else {

			}
			this.drawGraph(graph);

			CoverageCriterionManager criterionManger = new CoverageCriterionManager();
			criterionManger.init(graph);

			clg.add(graph);
		}

	}

	public ArrayList<CLGGraph> getClg() {
		return clg;
	}

	public void drawGraph(CLGGraph graph) throws IOException {
		String content = graph.graphDraw();
		String filepath = ((CLGStartNode) graph.getStartNode()).getClassName() + "_WhiteBox_DCC";
		String fileName = ((CLGStartNode) graph.getStartNode()).getClassName() + "_"
				+ ((CLGStartNode) graph.getStartNode()).getMethodName();
		DataWriter.writeInfo(content, fileName, "dot", DataWriter.Clg_output_path);

		new ProcessBuilder("dot", "-Tpng",
				DataWriter.output_folder_path + "\\test model\\" + filepath + "\\" + fileName + ".dot", "-o",
				DataWriter.output_folder_path + "\\test model\\" + filepath + "\\" + fileName + ".png").start();
		/* need to find better approach */
	}
}
