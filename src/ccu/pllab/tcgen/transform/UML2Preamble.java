package ccu.pllab.tcgen.transform;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ccu.pllab.tcgen.AbstractCLG.CLGStartNode;
import ccu.pllab.tcgen.CBTCGUtility.Utility;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.pathCLPFinder.CLPSolver;
import ccu.pllab.tcgen.sd2clg.SD2CLG;
import ccu.pllab.tcgen.sd2clg.SDXML2SD;
import ccu.pllab.tcgen.sd2clg.StateDigram;
import ccu.pllab.tcgen.sd2clp.SD2CLP;

public class UML2Preamble {
	File uml;

	public UML2Preamble(File uml) {
		super();
		this.uml = uml;
	}

	public void genPreambleCLP(String fileDir, SymbolTable symbolTable) throws Exception {

		Splitter split = new Splitter(this.uml);
		File cdUml = split.split2CDuml();
		File sdUml = split.split2SDuml();

		SDXML2SD sd_parser = new SDXML2SD();
		StateDigram sd = sd_parser.convert(sdUml, cdUml, symbolTable);

		if(null!=sd) {
			SD2CLG sd2clg = new SD2CLG();
			sd2clg.convert(sd);
			
			SD2CLP s = new SD2CLP();
			s.convert(sd);
			
			cdUml.delete();
			sdUml.delete();

			String fileName = sd.getSDName() + "Preamble";

			CLPSolver methodCompiler = new CLPSolver();
			if (!methodCompiler.compiling(fileDir, fileName)) {
				System.out.println(fileName + " failed to compile.");
			}
		}
	}
}
