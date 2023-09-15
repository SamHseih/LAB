package ccu.pllab.tcgen.transform;


import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.clg.StartNode;
import ccu.pllab.tcgen.clg2path.CriterionFactory;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import ccu.pllab.tcgen.exe.main.Main;
import tcgenplugin_2.handlers.BlackBoxHandler;
import tcgenplugin_2.handlers.ClassLevelHandler;
import tcgenplugin_2.handlers.SampleHandler;
import tcgenplugin_2.handlers.WhiteBoxHandler;
import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.CLGConstraint;
import ccu.pllab.tcgen.AbstractConstraint.CLGIfNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGLiteralNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGOperatorNode;
import ccu.pllab.tcgen.AbstractConstraint.CLGVariableNode;
import ccu.pllab.tcgen.DataWriter.DataWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.AbstractType.*;

public class SQL2CLG{
	
	public void genCLGGraph(CLGGraph clg, String methodName, String outputPath) throws IOException {
		
		File clgFolder= new File(outputPath);
		if(!clgFolder.exists()) {
			clgFolder.mkdirs();
		}
		
		File clgdot;
		clgdot = new File(clgFolder.getPath()+"/"+methodName+".dot");
		
		FileWriter dataFile;	
		
		dataFile = new FileWriter(clgdot.getPath());
		BufferedWriter input = new BufferedWriter(dataFile);
		input.write(clg.graphDraw());
		input.close();
		
		new ProcessBuilder("dot", "-Tpng", outputPath + methodName + ".dot",
				"-o", outputPath + methodName + ".png").start();
	}
}

		
		
	