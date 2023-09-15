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

public class sqlTcgen  {

	public static String CurrentEditorProjectPath;
	public static String coverageCriteria;
	private IProgressMonitor pmonitor;
	
	public void genSQLtc(String projLocation, String schemaName, String blackboxCoverage) throws Exception {
		
		long startTime = System.nanoTime();
    	
    	String schemaFile = projLocation + "/src/" + schemaName + ".sql";

    	String databaseInfoFile = projLocation + "/src/databaseInfo.txt";
    	
    	String testPath_output_path = projLocation + "/testPath/";
    	String testCons_output_path = projLocation + "/testCons/";
    	String testData_output_path = projLocation + "/testData/";
    	String testCLG_output_path = projLocation + "/testCLG/";
    	String testScript_output_path = projLocation + "/testScript/";
    	
    	//解析綱要
    	
    	InputStream is = System.in;
        is = new FileInputStream(schemaFile);
        ANTLRInputStream in = new ANTLRInputStream(is);
        sqlLexer lexer = new sqlLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        sqlParser parser = new sqlParser(tokens);
        parser.setBuildParseTree(true);
        RuleContext tree = parser.schema();
        System.out.println(tree.toStringTree(parser));
    	
        //設定資料庫連接資訊
        List<String> databaseConn = new ArrayList<String>();
        try {
            File myObj = new File(databaseInfoFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                databaseConn.add(data);
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred during reading database connection file.");
            e.printStackTrace();
        }
        
        //設定各種輸出路徑 與測試資料產生器資訊
        BlackBoxHandler.coverageCriteria = blackboxCoverage; //DCC
        Main.criterion=Criterion.dcc;
        Main.TestType = "BlackBox";
        Main.typeTable = new TypeTable();
        Main.typeTable.init();
        Main.boundary_analysis = true;
        DataWriter.testPath_output_path = testPath_output_path;
        DataWriter.testCons_output_path = testCons_output_path;
        DataWriter.testData_output_path = testData_output_path;
        DataWriter.output_folder_path = testScript_output_path;
        
        //使用到的方法實體
        SQL2CLG S2C = new SQL2CLG();
        CLG2Path pathParser = new CLG2Path();
        sqlTestScriptGenerator tsg = new sqlTestScriptGenerator();
        ArrayList<CLGGraph> sqlclg = new ArrayList<CLGGraph>();
        
        for(int i=0; i<parser.numbersOfTable; i++) {
        	
        	//產生與輸出限制邏輯圖
        	parser.schema[i].createFirstPath(parser.schema, parser.numbersOfTable);
        	parser.schema[i].printFirstPathStartNodeElements();
            //parser.schema[i].createTableCLG(parser.schema, parser.numbersOfTable);
        	parser.schema[i].createTableCLGNew(parser.schema, parser.numbersOfTable);
            
            S2C.genCLGGraph(parser.schema[i].getFirstPath(), parser.schema[i].getTableName() + "FP", testCLG_output_path);
            S2C.genCLGGraph(parser.schema[i].getTableCLG(), parser.schema[i].getTableName() + "CLG", testCLG_output_path);
            sqlclg.add(parser.schema[i].getTableCLG());

            //設定基本屬性與 SYMBOL TABLE
        	parser.schema[i].setSymbolTable();
            Main.symbolTable = parser.schema[i].getSymbolTable();
            CLGGraph inv = null;
            pathParser.setAttribute(inv, Main.symbolTable);
            pathParser.init(sqlclg);
     		
     		//產生測試資料
     		List<TestData> td = pathParser.genTestData(sqlclg, i);
     		
     		//產生測試腳本
     		//輸出路徑 資料庫連接資訊 表數量 綱要總資料 目前處理的表 測試資料集
     		tsg.genSQLTestScriptNew(testScript_output_path, databaseConn, parser.numbersOfTable, parser.schema, parser.schema[i], td);
     		
     		
        }
        
        long endTime = System.nanoTime();
        long duration;
        
        duration = (endTime - startTime)/1000000;
        System.out.println("Total time: " + duration + " milliseconds.");
		
	}
	
}