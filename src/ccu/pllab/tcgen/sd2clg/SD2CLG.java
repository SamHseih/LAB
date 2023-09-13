package ccu.pllab.tcgen.sd2clg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.cli.ParseException;
import org.json.JSONException;
import org.xml.sax.SAXException;

import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import tcgenplugin_2.handlers.ClassLevelHandler;
//import ccu.pllab.tcgen.home_sd2clg.CLGRF2;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.tools.template.exception.TemplateException;

public class SD2CLG {

	public SD2CLG() {

	}

	public CLGGraph convert(StateDigram statedigram)
			throws SAXException, IOException, ParserConfigurationException, TemplateException, ModelAccessException,
			ParseException, JSONException, tudresden.ocl20.pivot.parser.ParseException {
		String clgstr = null; // .dot
		CLGGraph SD = null;
		CLGGraph SG = null; // SD=Total CLGGrapgh, SG=Sub CLGGrapgh, Guard=Sub Guard CLGGrapgh
		CLGGraph Guard = null;
		SD = new CLGGraph(statedigram.getStates().size());// 1 start, 2final

		for (Transition tr : statedigram.getTransitions().values()) {

//		for (int i = 0; i < statedigram.getTransitions().size(); i++) {
//			Transition tr = statedigram.getTransitions().get(i);

			if (tr.getGuard() != null) {

				Guard = new CLGGraph(tr.getGuard());
				if (tr.getMethod() == null)
					SG = new CLGGraph();
				else
					SG = new CLGGraph(tr.getMethod());
				Guard.graphAnd(SG);
				int source = 0;
				int target = 0;
				if (tr.getSource() instanceof InitialState)
					source = 1;
				else
					source = tr.getSource().getId();
				if (tr.getTarget() instanceof FinalState)
					target = 2;
				else
					target = tr.getTarget().getId();

				SD.graphInsert(source, Guard, target);
			} else {
				if (tr.getMethod() == null)
					SG = new CLGGraph();
				else
					SG = new CLGGraph(tr.getMethod());
				int source = 0;
				int target = 0;
				if (tr.getSource() instanceof InitialState)
					source = 1;
				else
					source = tr.getSource().getId();
				if (tr.getTarget() instanceof FinalState)
					target = 2;
				else
					target = tr.getTarget().getId();
				SD.graphInsert(source, SG, target);
			}
		}
		clgstr = SD.graphDraw();

		FileWriter dataFile;
		File clgFolder = new File(DataWriter.Clg_output_path);
		if (!clgFolder.exists()) {
			clgFolder.mkdirs();
		}
		File clgdot = new File(clgFolder.getPath() + "/" + statedigram.getSDName() + ".dot");
		dataFile = new FileWriter(clgdot.getPath());
		BufferedWriter input = new BufferedWriter(dataFile);
		input.write(clgstr);
		input.close();
		new ProcessBuilder("dot", "-Tsvg", DataWriter.Clg_output_path + statedigram.getSDName() + ".dot",
				"-o", DataWriter.Clg_output_path + statedigram.getSDName() + ".svg").start();

		((CLGStartNode) SD.getStartNode()).setClassName(statedigram.getSDName());
		((CLGStartNode) SD.getStartNode()).setMethodName(statedigram.getSDName());
		((CLGStartNode) SD.getStartNode()).setClassAttributes(statedigram.getSDAttribute());
		return SD;
	}
}