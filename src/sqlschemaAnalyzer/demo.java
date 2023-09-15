package sqlschemaAnalyzer;

import org.antlr.v4.runtime.*;
import org.eclipse.core.runtime.IProgressMonitor;
import org.antlr.runtime.tree.*;

import java.util.*;
import java.io.*;

import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.DataWriter.DataWriter;
import ccu.pllab.tcgen.transform.CLG2Path;
import ccu.pllab.tcgen.transform.SQL2CLG;
import ccu.pllab.tcgen.TestCase.*;
import ccu.pllab.tcgen.clg2path.CriterionFactory.Criterion;
import tcgenplugin_2.handlers.*;
import ccu.pllab.tcgen.exe.main.*;

import com.parctechnologies.eclipse.*;

public class demo {
	
    public static void main(String[] args) throws Exception {
    	

    	sqlTcgen sqltcgen = new sqlTcgen(); 
    	sqltcgen.genSQLtc("C:/Users/balloon/Desktop/mysqltest", "schemaD", "DCC");
    	
   
    }
}