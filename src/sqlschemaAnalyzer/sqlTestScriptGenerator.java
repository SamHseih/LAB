package sqlschemaAnalyzer;

import java.util.*;
import java.lang.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import java.io.*;

import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.TestCase.*;

public class sqlTestScriptGenerator{
	/*
	public void genSQLTestScript(String outputPath, List<String> databaseConn, int numberOfTable, schemaTable[] schema, schemaTable st, List<TestData> td){
		
		//System.out.println("td:"+td.toString());
		
		try {
			File scriptFolder= new File(outputPath);
			if(!scriptFolder.exists()) {
				scriptFolder.mkdirs();
			}
			
			PrintWriter myWriter = new PrintWriter(outputPath+st.getTableName()+"TestScript.java");
			myWriter.println("import static org.junit.jupiter.api.Assertions.*;");
			myWriter.println("import java.sql.Connection;");
			myWriter.println("import java.sql.DriverManager;");
			myWriter.println("import java.sql.ResultSet;");
			myWriter.println("import java.sql.SQLException;");
			myWriter.println("import java.sql.Statement;");
			myWriter.println("import org.junit.jupiter.api.Test;");
			
			myWriter.println("");
			myWriter.println("//[WARNING] Following test include DROP & RE-CREATE tables inside the testing database, highly recommand using an empty database for check");
			myWriter.println("");
			myWriter.println("class " + st.getTableName() + "TestScript {");
			
			//���o��Ʈw��T
			//String conn = "Connection conn = DriverManager.getConnection(\"jdbc:mysql://localhost/DB1?useUnicode=true&characterEncoding=Big5&serverTimezone=UTC\",\"root\",\"balloon\");";
			String conn = "Connection conn = DriverManager.getConnection(\"jdbc:"
					+ databaseConn.get(0)
					+ "?useUnicode=true&characterEncoding=Big5&serverTimezone=UTC\",\""
					+ databaseConn.get(1)
					+ "\",\""
					+ databaseConn.get(2)
					+ "\");";
			
			//��Ʈw�^���~������~�ɷ|���X�O����database
			//���� ��Ʈw�W�ٷ|�۰���p�g
			String databaseName = databaseConn.get(3).toLowerCase();
			
			//���a�ѼƻP����Ҧ��Ѽ�
			System.out.println("args:"+st.localARG);
			System.out.println("TD size: " + td.size());
			
			//��z��UQ���
			//PK/UQ��� �x�s�X��Column Column�W�� �OPK�٬OUQ [column number, column name, is PK?]
	        
	        List<List<String>> uniqueColumn = new ArrayList<List<String>>();
	        
	        for(int i=0; i<st.getNumbersOfVariable(); i++) {
	        	if(st.TV[i].getVariableIspk()) {
	        		List<String> s = new ArrayList<String>();
	        		s.add(Integer.toString(i));
	        		s.add(st.TV[i].getVariableName());
	        		s.add("1");
	        		uniqueColumn.add(s);
	        	}
	        	if(st.TV[i].getVariableIsun()) {
	        		List<String> s = new ArrayList<String>();
	        		s.add(Integer.toString(i));
	        		s.add(st.TV[i].getVariableName());
	        		s.add("0");
	        		uniqueColumn.add(s);
	        	}
	        }
	        
	        System.out.println("Unique columns: " + uniqueColumn);
			
			//�p�G���~�����s �n�����J�~�����p���ĸ��
	        //��� ["��W��", "�����ƶq"] [0,1]
			List<List<String>> foreignColumn = new ArrayList<List<String>>();
			
			for(int i=0; i<st.getNumbersOfVariable(); i++) {
	        	if(st.TV[i].getVariableIsfk()) {
	        		System.out.println("[[[[[FK]]]]]: ");
	        		st.TV[i].printVariable();
	        		
	        		for(int j=0; j<numberOfTable; j++) {
	        			if(schema[j].getTableName().equals(st.TV[i].getForeignTable())) {
	        				List<String> s = new ArrayList<String>();
	        				s.add(st.TV[i].getForeignTable());
	        				s.add(Integer.toString(schema[j].getNumbersOfVariable()));
	        				foreignColumn.add(s);
	        			}
	        		}
	        	}
	        }
			
			System.out.println("Foreign table info: " + foreignColumn.size() + foreignColumn);
			
			//�̧�Ū��Ʊ����ͮר�
			for(int i=0; i< td.size(); i++) {
				System.out.println("TD [" + i + "]: " + td.get(i).toString());
				String ARG = td.get(i).getArgPre();
				String RET = td.get(i).getRetVal();
				System.out.println(ARG+RET);

		        //RET fix
		        RET = RET.substring(2, RET.length()-2);
		        
		        //ARG fix
		        ARG = ARG.substring(1, ARG.length()-1);
		        
		        //���ո�ƦC���� ARG
		        String[] ARGcut = ARG.split(", ");
		        List<String> localARG = Arrays.asList(ARGcut);
		        
		        List<List<String>> foreignARG = new ArrayList<List<String>>();
		        System.out.println("full ARG length: " + localARG.size());
		        System.out.println("local columns : " + st.getNumbersOfVariable());
		        
		        //td����1�Φh�ӥ~�����p�� ����
		        // [LOCAL,FK1,FK2...]
		        if(foreignColumn.size()!=0) {
		        	int previousCut = st.getNumbersOfVariable();
		        	for(int j=0; j<foreignColumn.size(); j++) {
			        	List<String> s = new ArrayList<String>();
			        	int ftv = Integer.parseInt(foreignColumn.get(j).get(1));
			        	s = localARG.subList(previousCut, previousCut+ftv);
			        	foreignARG.add(s);
			        	System.out.println("[FK testdata]: " + s);
			        	
			        	previousCut += ftv;
			        }
		        	localARG = localARG.subList(0, st.getNumbersOfVariable());
		        }
		        
		        //�T�{�ثe�O���խ��@����� �� ����
		        int constraintType=-1; //0:PrimaryKey 1:Unique 2:ForeignKey 3:NotNull 4:Check 
		        String constraintColumn = "";
		        int checkNumber = 0;
		        
		        //���ͥ�����Ʀۭ������ insert error message
		        int fkNumber = 0;
		        
		        String fkTableName = "";
		        String fkTableColumn = "";
		        String query = "";
		        String errorMessage = "";
		        
		        //��j�����רҲ��ͤ�� PKUQ/ELSE
		        //PKUQ-SUCCESS DATA
		        if(RET.contains("success")) {
		        	//�����ͤ@�մ��q���������ר�
		        	
		        	//�}�Y�s@TEST
			        myWriter.println("");
			        myWriter.println("	@Test");
			        myWriter.println("	public void test" + i + "_success() {");
			        myWriter.println("		String errorMessage = new String();");
			        myWriter.println("		boolean querySuccessed = false;");
			        myWriter.println("		//SQL QUERIES ERROR MESSAGE = EXCEPTION CATCH");
			        myWriter.println("		try{");
			        myWriter.println("			" + conn);
			        myWriter.println("			Statement stmt = conn.createStatement();");
			        
			        //�s�b�~���� �����J�~�����T
			        for(int j=0; j<foreignColumn.size(); j++) {
			        	String fquery = insertIntoGenerator(foreignColumn.get(j).get(0), foreignARG.get(j));
			        	myWriter.println("			String fquery" + j +" = \"" + fquery + "\";");
			        	myWriter.println("			stmt.executeUpdate(fquery"+j+");");
				        myWriter.println("			stmt = conn.createStatement();");
			        }
		        	
			        query = insertIntoGenerator(st.getTableName(),localARG);
			        errorMessage = "Success case, no error message should be returned.";
			        
			        //���J�R�O�y
			        myWriter.println("			String query= \"" + query + "\";");
		        	myWriter.println("			stmt.executeUpdate(query);");
			        myWriter.println("			stmt = conn.createStatement();");
			        
			        //�R�O�L���~
		    		myWriter.println("			querySuccessed = true;");
		    		myWriter.println("			errorMessage = \"Query success\";");
		    		
			        //ResultSet rs = stmt.executeQuery(query);
			        myWriter.println("		}catch(Exception e){");
			        myWriter.println("			errorMessage = e.toString();");
			        myWriter.println("		}");
			        
			        myWriter.println("		System.out.println(errorMessage);");
			        
			        myWriter.println("		//ERROR MESSAGE JUNIT CHECK");
			        myWriter.println("		try{");
			        //INSERT ASSERT EQUALS
			        myWriter.println("			if(querySuccessed){");
			        myWriter.println("				assertEquals(errorMessage, \"Query success\");");
			        myWriter.println("			} else {");
			        myWriter.println("				assertEquals(errorMessage, \"" + errorMessage + "\");");
			        myWriter.println("			}");
			        myWriter.println("		}catch(Exception e){");
			        myWriter.println("			fail(\"\");");
			        myWriter.println("		}");
			        
			        //DELETE ALL RECORDS
			        myWriter.println("		//DELETE TEST RECORDS");
			        myWriter.println("		try{");
			        myWriter.println("			" + conn);
			        myWriter.println("			String query;");
			        myWriter.println("			Statement stmt = conn.createStatement();");
			        myWriter.println("			query = \"SET FOREIGN_KEY_CHECKS=0;\";");
			        myWriter.println("			stmt.executeUpdate(query);");
		        	myWriter.println("			stmt = conn.createStatement();");
			        for(int j=0; j<numberOfTable; j++) {
			        	myWriter.println("			query= \"DELETE FROM "+ schema[j].getTableName() +"\";");
			        	myWriter.println("			stmt.executeUpdate(query);");
			        	myWriter.println("			stmt = conn.createStatement();");
			        }
			        myWriter.println("			query = \"SET FOREIGN_KEY_CHECKS=1;\";");
			        myWriter.println("			stmt.executeUpdate(query);");
		        	myWriter.println("			stmt = conn.createStatement();");
			        myWriter.println("		}catch(Exception e){");
			        myWriter.println("			fail(\"RECORD CLEAR FAILED\");");
			        myWriter.println("		}");
			        
			        myWriter.println("	}");
			        
		        	//�ھڭ������ƶq���͹������B�~���ծר� (PKUQ)
		        	for(int j=0; j<uniqueColumn.size(); j++) {
		        		String uqColumnNumber = uniqueColumn.get(j).get(0);
		        		String uqColumnName = uniqueColumn.get(j).get(1);
		        		int uqColumnIspk = Integer.valueOf(uniqueColumn.get(j).get(2));
		        		
		        		List<String> tmpARG = new ArrayList<String>(localARG);
		        		
		        		String dupeValue = "";
		        		
		        		//���ܨ�L�D��e�רҪ��W�S����(+1)
		        		for(int k=0; k<uniqueColumn.size(); k++) {
		        			//�Ҧ��D��e�ؼ�
		        			if(uqColumnNumber!=uniqueColumn.get(k).get(0)) {
		        				//�ܭ� localARG
		        				int tmp = Integer.valueOf(tmpARG.get(Integer.valueOf(uniqueColumn.get(k).get(0))));
		        				tmp += 1;
		        				tmpARG.set(Integer.valueOf(uniqueColumn.get(k).get(0)), Integer.toString(tmp));
		        			}
		        			else {
		        				//��e�ؼ� �ݭn��ƭȨӼg���~�T��
		        				dupeValue = tmpARG.get(Integer.valueOf(uniqueColumn.get(k).get(0)));
		        			}
		        		}
		        		
		        		String query1 = insertIntoGenerator(st.getTableName(),localARG);
		        		String query2 = insertIntoGenerator(st.getTableName(),tmpARG);
		        		
		        		//�}�Y�s@TEST
				        myWriter.println("");
				        myWriter.println("	@Test");
				        if(uqColumnIspk == 0) {
				        	 myWriter.println("	public void test" + i + "_" + uqColumnName + "_UQ() {");
				        	 errorMessage = "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '" + dupeValue +"' for key '" + st.getTableName() +
				        			 "." + uqColumnName + "\'";
				        }
				        if(uqColumnIspk == 1) {
				        	 myWriter.println("	public void test" + i + "_" + uqColumnName + "_PK() {");
				        	 errorMessage = "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '" + dupeValue + "' for key '" + st.getTableName() + ".PRIMARY'";
				        }
				       
				        myWriter.println("		String errorMessage = new String();");
				        myWriter.println("		boolean querySuccessed = false;");
				        myWriter.println("		//SQL QUERIES ERROR MESSAGE = EXCEPTION CATCH");
				        myWriter.println("		try{");
				        myWriter.println("			" + conn);
				        myWriter.println("			Statement stmt = conn.createStatement();");
				        
				        //�s�b�~���� �����J�~�����T
				        for(int k=0; k<foreignColumn.size(); k++) {
				        	String fquery = insertIntoGenerator(foreignColumn.get(k).get(0), foreignARG.get(k));
				        	myWriter.println("			String fquery" + k +" = \"" + fquery + "\";");
				        	myWriter.println("			stmt.executeUpdate(fquery"+k+");");
					        myWriter.println("			stmt = conn.createStatement();");
				        }
			        	
				        //���J�R�O�y
				        myWriter.println("			String query= \"" + query1 + "\";");
			        	myWriter.println("			stmt.executeUpdate(query);");
				        myWriter.println("			stmt = conn.createStatement();");
				        myWriter.println("			query= \"" + query2 + "\";");
			        	myWriter.println("			stmt.executeUpdate(query);");
				        myWriter.println("			stmt = conn.createStatement();");
				        
				        //�R�O�L���~
			    		myWriter.println("			querySuccessed = true;");
			    		myWriter.println("			errorMessage = \"Query success\";");
			    		
				        //ResultSet rs = stmt.executeQuery(query);
				        myWriter.println("		}catch(Exception e){");
				        myWriter.println("			errorMessage = e.toString();");
				        myWriter.println("		}");
				        
				        myWriter.println("		System.out.println(errorMessage);");
				        
				        myWriter.println("		//ERROR MESSAGE JUNIT CHECK");
				        myWriter.println("		try{");
				        //INSERT ASSERT EQUALS
				        myWriter.println("			if(querySuccessed){");
				        myWriter.println("				assertEquals(errorMessage, \"Query success\");");
				        myWriter.println("			} else {");
				        myWriter.println("				assertEquals(errorMessage, \"" + errorMessage + "\");");
				        myWriter.println("			}");
				        myWriter.println("		}catch(Exception e){");
				        myWriter.println("			fail(\"\");");
				        myWriter.println("		}");
				        
				        //DELETE ALL RECORDS
				        myWriter.println("		//DELETE TEST RECORDS");
				        myWriter.println("		try{");
				        myWriter.println("			" + conn);
				        myWriter.println("			String query;");
				        myWriter.println("			Statement stmt = conn.createStatement();");
				        myWriter.println("			query = \"SET FOREIGN_KEY_CHECKS=0;\";");
				        myWriter.println("			stmt.executeUpdate(query);");
			        	myWriter.println("			stmt = conn.createStatement();");
				        for(int k=0; k<numberOfTable; k++) {
				        	myWriter.println("			query= \"DELETE FROM "+ schema[k].getTableName() +"\";");
				        	myWriter.println("			stmt.executeUpdate(query);");
				        	myWriter.println("			stmt = conn.createStatement();");
				        }
				        myWriter.println("			query = \"SET FOREIGN_KEY_CHECKS=1;\";");
				        myWriter.println("			stmt.executeUpdate(query);");
			        	myWriter.println("			stmt = conn.createStatement();");
				        myWriter.println("		}catch(Exception e){");
				        myWriter.println("			fail(\"RECORD CLEAR FAILED\");");
				        myWriter.println("		}");
				        
				        myWriter.println("	}");
		        		
		        	}
		        	
		        }
		        else {
		        	//�}�Y�s@TEST
			        myWriter.println("");
			        myWriter.println("	@Test");
			        myWriter.println("	public void test" + i + "_" + RET + "() {");
			        myWriter.println("		String errorMessage = new String();");
			        myWriter.println("		boolean querySuccessed = false;");
			        myWriter.println("		//SQL QUERIES ERROR MESSAGE = EXCEPTION CATCH");
			        myWriter.println("		try{");
			        myWriter.println("			" + conn);
			        myWriter.println("			Statement stmt = conn.createStatement();");
			       
			        
			        //�s�b�~���� �����J�~�����T
			        for(int j=0; j<foreignColumn.size(); j++) {
			        	String fquery = insertIntoGenerator(foreignColumn.get(j).get(0), foreignARG.get(j));
			        	myWriter.println("			String fquery" + j +" = \"" + fquery + "\";");
			        	myWriter.println("			stmt.executeUpdate(fquery"+j+");");
				        myWriter.println("			stmt = conn.createStatement();");
			        }
			        
			        //�ھڹH�ϭ���ƪ�QUERY & ERRORMESSAGE
			        //not null
			        if(RET.contains("nn")) {
			        	String[] retCut = RET.split("_");
			        	constraintColumn = retCut[0];
			        	
			        	System.out.println("nn case gen");
			        	// find the NULL column
			        	for(int j=0; j<localARG.size(); j++) {
			        		if(localARG.get(j).equals("0")) {
			        			localARG.set(j, "NULL");
			        		}
			        	}
			        	query = insertIntoGenerator(st.getTableName(),localARG);
			        	errorMessage = "java.sql.SQLIntegrityConstraintViolationException: Column \'" + constraintColumn + "\' cannot be null";
			        }
			        //foreign key
			        if(RET.contains("fk")) {
			        	String[] retCut = RET.split("_");
			        	constraintColumn = retCut[0];
			        	fkTableName = retCut[2];
			        	fkTableColumn = retCut[3];
			        	for(int j=0; j<st.getNumbersOfVariable(); j++) {
			        		if(constraintColumn.equals(st.TV[j].getVariableName())) {
			        			fkNumber = st.TV[j].getForeignKeyNumber();
			        		}
			        	}
			        	
			        	System.out.println("fk case gen");
			        	query = insertIntoGenerator(st.getTableName(), localARG);
			        	System.out.println();
			        	errorMessage = "java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`"
			        			+ databaseName + "`.`" + st.getTableName() + "`, CONSTRAINT `" + st.getTableName() + "_ibfk_" + fkNumber + "` FOREIGN KEY (`" 
			        			+ constraintColumn + "`) REFERENCES `" + fkTableName + "` (`" + fkTableColumn + "`))";
			        }
			        //check
			        if(RET.contains("ck")) {
			        	String[] retCut = RET.split("_");
			        	checkNumber = Integer.parseInt(retCut[1]);
			        	
			        	System.out.println("ck case gen");
			        	query = insertIntoGenerator(st.getTableName(),localARG);
			        	errorMessage = "java.sql.SQLException: Check constraint \'" + st.getTableName() + "_chk_" + checkNumber + "\' is violated.";
			        }
			        
			        //���J�R�O�y
			        myWriter.println("			String query= \"" + query + "\";");
		        	myWriter.println("			stmt.executeUpdate(query);");
			        myWriter.println("			stmt = conn.createStatement();");
			        
			        //�R�O�L���~
		    		myWriter.println("			querySuccessed = true;");
		    		myWriter.println("			errorMessage = \"Query success\";");
		    		
			        //ResultSet rs = stmt.executeQuery(query);
			        myWriter.println("		}catch(Exception e){");
			        myWriter.println("			errorMessage = e.toString();");
			        myWriter.println("		}");
			        
			        myWriter.println("		System.out.println(errorMessage);");
			        
			        myWriter.println("		//ERROR MESSAGE JUNIT CHECK");
			        myWriter.println("		try{");
			        //INSERT ASSERT EQUALS
			        myWriter.println("			if(querySuccessed){");
			        myWriter.println("				assertEquals(errorMessage, \"Query success\");");
			        myWriter.println("			} else {");
			        myWriter.println("				assertEquals(errorMessage, \"" + errorMessage + "\");");
			        myWriter.println("			}");
			        myWriter.println("		}catch(Exception e){");
			        myWriter.println("			fail(\"\");");
			        myWriter.println("		}");
			        
			        //DELETE ALL RECORDS
			        myWriter.println("		//DELETE TEST RECORDS");
			        myWriter.println("		try{");
			        myWriter.println("			" + conn);
			        myWriter.println("			String query;");
			        myWriter.println("			Statement stmt = conn.createStatement();");
			        myWriter.println("			query = \"SET FOREIGN_KEY_CHECKS=0;\";");
			        myWriter.println("			stmt.executeUpdate(query);");
		        	myWriter.println("			stmt = conn.createStatement();");
			        for(int j=0; j<numberOfTable; j++) {
			        	myWriter.println("			query= \"DELETE FROM "+ schema[j].getTableName() +"\";");
			        	myWriter.println("			stmt.executeUpdate(query);");
			        	myWriter.println("			stmt = conn.createStatement();");
			        }
			        myWriter.println("			query = \"SET FOREIGN_KEY_CHECKS=1;\";");
			        myWriter.println("			stmt.executeUpdate(query);");
		        	myWriter.println("			stmt = conn.createStatement();");
			        myWriter.println("		}catch(Exception e){");
			        myWriter.println("			fail(\"RECORD CLEAR FAILED\");");
			        myWriter.println("		}");
			        
  
			        myWriter.println("	}");
		        }
		        
		        
			}
			
			myWriter.println("}");
			myWriter.close();	

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	public void genSQLTestScriptNew(String outputPath, List<String> databaseConn, int numberOfTable, schemaTable[] schemas, schemaTable localTable, List<TestData> testData) {
		try {
			
			File scriptFolder= new File(outputPath);
			if(!scriptFolder.exists()) {
				scriptFolder.mkdirs();
			}
			
			PrintWriter myWriter = new PrintWriter(outputPath+localTable.getTableName()+"TestScript.java");
			
			myWriter.println("import static org.junit.jupiter.api.Assertions.*;");
			myWriter.println("import java.sql.Connection;");
			myWriter.println("import java.sql.DriverManager;");
			myWriter.println("import java.sql.ResultSet;");
			myWriter.println("import java.sql.SQLException;");
			myWriter.println("import java.sql.Statement;");
			myWriter.println("import org.junit.jupiter.api.Test;");
			
			myWriter.println("");
			myWriter.println("//[WARNING] Following test include DROP & RE-CREATE tables inside the testing database, highly recommand using an empty database for check");
			myWriter.println("");
			myWriter.println("class " + localTable.getTableName() + "TestScript {");
			
			//���o��Ʈw��T
			
			String conn = "Connection conn = DriverManager.getConnection(\"jdbc:"
					+ databaseConn.get(0)
					+ "?useUnicode=true&characterEncoding=Big5&serverTimezone=UTC\",\""
					+ databaseConn.get(1)
					+ "\",\""
					+ databaseConn.get(2)
					+ "\");";
			
			//��Ʈw�^���~������~�ɷ|���X�O����database
			//���� ��Ʈw�W�ٷ|�۰���p�g
			String databaseName = databaseConn.get(3).toLowerCase();
			
			//�B�zFK ARG
			//���C�ӥ~�����ͨⵧ��������� FK����r�������@�w�OUQ
			
			System.out.println("testScript");
			System.out.println("localARGs: "+localTable.localARG);
			System.out.println("fkTables: "+localTable.foreignTableName);
			System.out.println("fkARGs: "+localTable.foreignARG);
			
			for(int i=0; i<testData.size(); i++) {
				
				//���o��e���
				String ARGpre = testData.get(i).getArgPre();
				String RET = testData.get(i).getRetVal();
				
				//string fix
		        ARGpre = ARGpre.substring(1, ARGpre.length()-1);
		        RET = RET.substring(2,RET.length()-2);
		        
	        	//�B�zRET
	        	int constraintType = -1;
	        	String[] retCut = RET.split("_");
	        	String constraintColumn = retCut[0];
	        	
	        	String constraintFkTable = "";
	        	String constraintFkColumn = "";
	        	int constraintFkNumber = -1;
	        	
	        	String constraintCkNumber = "";
	        	
	        	String testHeader = "ERRORCASE";
	        	
	        	if(retCut[0].equals("success")){
	        		constraintType = 0;
	        		testHeader = "Success";
	        	} else if (retCut[1].equals("nn")) {
	        		constraintType = 1;
	        		testHeader = constraintColumn + "_NotNull";
	        	} else if (retCut[1].equals("pk")) {
	        		constraintType = 2;
	        		testHeader = constraintColumn + "_PrimaryKey";
	        	} else if (retCut[1].equals("un")) {
	        		constraintType = 3;
	        		testHeader = constraintColumn + "_Unique";
	        	} else if (retCut[1].equals("fk")) {
	        		constraintType = 4;
	        		constraintFkTable = retCut[2];
	        		constraintFkColumn = retCut[3];
	        		
	        		//�M��FK�s��
	        		for(int j=0; j<localTable.getNumbersOfVariable(); j++) {
		        		if(constraintColumn.equals(localTable.TV[j].getVariableName())) {
		        			constraintFkNumber = localTable.TV[j].getForeignKeyNumber();
		        		}
		        	}
	        		testHeader = constraintColumn + "_ForeignKey_" + constraintFkNumber + "_" + constraintFkTable + "_" + constraintFkColumn;
	        		
	        	} else if (retCut[0].equals("chk")) {
	        		constraintType = 5;
	        		constraintCkNumber = retCut[1];
	        		testHeader = "_Check_" + constraintCkNumber;
	        	}
	        	
		        
		        //���ո�ƦC���� ARG
		        String[] ARGcut = ARGpre.split(", ");
		        ArrayList<String> ARG = new ArrayList<String>();
		        Collections.addAll(ARG, ARGcut);
		        
		        //�B�z�����data
		        int examinedARG = 0;
		        
		        ArrayList<String> localData1 = new ArrayList<String>();
		        ArrayList<String> localData2 = new ArrayList<String>();
		        
		        //�p���|���ʹX�ӰѼ�
		        for(int j=0; j<localTable.numbersOfVariable; j++){
		        	if(localTable.TV[j].getVariableIspk() || localTable.TV[j].getVariableIsun()) {
		        		localData1.add(ARG.get(examinedARG));
		        		examinedARG++;
		        		localData2.add(ARG.get(examinedARG));
		        		examinedARG++;
		        	}
		        	else {
		        		localData1.add(ARG.get(examinedARG));
		        		localData2.add(ARG.get(examinedARG));
			        	examinedARG++;
		        	}
		        }
				
				//�w�p�s�J���ؼ�
		        ArrayList<String> fktName = new ArrayList<String>();
		        ArrayList<ArrayList<ArrayList<String>>> fktARG = new ArrayList<ArrayList<ArrayList<String>>>();
				
				//���X�Ҧ�FK��Ƴ���
				for(int j=0; j<localTable.foreignTableName.size(); j++) {
					fktName.add(localTable.foreignTableName.get(j));
					
					//�ثe�B�z���Ӫ�
					int fktNumber=0;
					for(int k=0; k<numberOfTable; k++) {
						if(schemas[k].getTableName().equals(fktName.get(j))) {
							fktNumber = k;
						}
					}
					
					ArrayList<String> fktData1 = new ArrayList<String>();
					ArrayList<String> fktData2 = new ArrayList<String>();
					
					for(int k=0; k<schemas[fktNumber].numbersOfVariable; k++) {
						if(schemas[fktNumber].TV[k].variable_ispk || schemas[fktNumber].TV[k].variable_isun) {
							fktData1.add(ARG.get(examinedARG));
							examinedARG++;
							fktData2.add(ARG.get(examinedARG));
							examinedARG++;
						} else {
							fktData1.add(ARG.get(examinedARG));
							fktData2.add(ARG.get(examinedARG));
							examinedARG++;
						}
					}
					
					ArrayList<ArrayList<String>> fktDatas = new ArrayList<ArrayList<String>>();
					fktDatas.add(fktData1);
					fktDatas.add(fktData2);
					
					fktARG.add(fktDatas);
					
				}
				
				//��ƷǳƧ���
				System.out.println("local data 1: "+localData1);
				System.out.println("local data 2: "+localData2);
				System.out.println("fkARGs queries: "+fktARG);
				
				//�}�Y�s@TEST
		        myWriter.println("");
		        myWriter.println("	@Test");
		        myWriter.println("	public void test" + (i+1) + "_" + testHeader +  "() {");
		        myWriter.println("		String errorMessage = new String();");
		        myWriter.println("		boolean querySuccessed = false;");
		        myWriter.println("		//SQL QUERIES ERROR MESSAGE = EXCEPTION CATCH");
		        myWriter.println("		try{");
		        myWriter.println("			" + conn);
		        myWriter.println("			Statement stmt = conn.createStatement();");
		        
		        myWriter.println("			String fquery = \"\";");
				
				//��ܭ���ҪO
		        //�t�d���ͥ~����P���a���R�O�y�y�æC�L
		        //���ͦ����C�L ERRORMESSAGE 
				
		        String errorMessage = "";
		        
		        switch(constraintType) 
		        {
		        	case (0):{
		        		//success
		        		
		        		for(int j=0; j<fktARG.size(); j++) {
		        			//SUCCESS ��2���~�����
		        			String fquery = insertIntoGenerator(fktName.get(j),fktARG.get(j).get(0));
		        			myWriter.println("			fquery = \"" + fquery + "\";");
		        			myWriter.println("			stmt.executeUpdate(fquery);");
		        			myWriter.println("			stmt = conn.createStatement();");
		        			fquery = insertIntoGenerator(fktName.get(j),fktARG.get(j).get(1));
		        			myWriter.println("			fquery = \"" + fquery + "\";");
		        			myWriter.println("			stmt.executeUpdate(fquery);");
		        			myWriter.println("			stmt = conn.createStatement();");
		        		}
		        		
		        		String query1 = insertIntoGenerator(localTable.getTableName(),localData1);
		        		String query2 = insertIntoGenerator(localTable.getTableName(),localData2);
		        		errorMessage = "Query success, no errorMessage should be print.";
		        		
		        		//���J���a�� 2��
		        		myWriter.println("			String query1 = \"" + query1 + "\";");
		        		myWriter.println("			stmt.executeUpdate(query1);");
		        		myWriter.println("			stmt = conn.createStatement();");
		        		
		        		myWriter.println("			String query2 = \"" + query2 + "\";");
		        		myWriter.println("			stmt.executeUpdate(query2);");
		        		myWriter.println("			stmt = conn.createStatement();");
		        		
		        		break;
		        	}
		        	
		        	case (1):{
		        		//NN

		        		for(int j=0; j<fktARG.size(); j++) {
		        			//NN �u��1���~�����
		        			String fquery = insertIntoGenerator(fktName.get(j),fktARG.get(j).get(0));
		        			myWriter.println("			fquery = \"" + fquery + "\";");
		        			myWriter.println("			stmt.executeUpdate(fquery);");
		        			myWriter.println("			stmt = conn.createStatement();");
		        		}
		        	
		        		// find the NULL column
		        		for(int j=0; j<localData1.size(); j++) {
		        			if(localData1.get(j).equals("0")) {
		        				localData1.set(j, "NULL");
		        			}
		        		}
		        	
		        		String query = insertIntoGenerator(localTable.getTableName(),localData1);
		        		errorMessage = "java.sql.SQLIntegrityConstraintViolationException: Column \'" + constraintColumn + "\' cannot be null";
		        	
		        		//���J���a��
		        		myWriter.println("			String query = \"" + query + "\";");
		        		myWriter.println("			stmt.executeUpdate(query);");
		        		myWriter.println("			stmt = conn.createStatement();");
		        		
		        		break;
		        	}
		        	
		        	case (2):{
		        		//PK
		        		
		        		for(int j=0; j<fktARG.size(); j++) {
		        			//PK ��2���~�����
		        			String fquery = insertIntoGenerator(fktName.get(j),fktARG.get(j).get(0));
		        			myWriter.println("			fquery = \"" + fquery + "\";");
		        			myWriter.println("			stmt.executeUpdate(fquery);");
		        			myWriter.println("			stmt = conn.createStatement();");
		        			fquery = insertIntoGenerator(fktName.get(j),fktARG.get(j).get(1));
		        			myWriter.println("			fquery = \"" + fquery + "\";");
		        			myWriter.println("			stmt.executeUpdate(fquery);");
		        			myWriter.println("			stmt = conn.createStatement();");
		        		}
		        		
		        		//�M��DUPEVALUE
		        		int dupeValue = 0;
		        		
		        		for(int j=0; j<localTable.getNumbersOfVariable(); j++) {
		        			if(localTable.TV[j].getVariableName().equals(constraintColumn)) {
		        				//pk&un testcase data localdata1&2
		        				//������쪺��ƬۦP ����@�n����dupevalue
		        				//�Ω�errorMessage
		        				dupeValue = Integer.valueOf(localData1.get(j));
		        			}
		        		}
		        		
		        		String query1 = insertIntoGenerator(localTable.getTableName(),localData1);
		        		String query2 = insertIntoGenerator(localTable.getTableName(),localData2);
		        		errorMessage = "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '" + dupeValue + "' for key '" + localTable.getTableName() + ".PRIMARY'";
		        		
		        		//���J���a�� 2��
		        		myWriter.println("			String query1 = \"" + query1 + "\";");
		        		myWriter.println("			stmt.executeUpdate(query1);");
		        		myWriter.println("			stmt = conn.createStatement();");

		        		myWriter.println("			String query2 = \"" + query2 + "\";");
		        		myWriter.println("			stmt.executeUpdate(query2);");
		        		myWriter.println("			stmt = conn.createStatement();");
		        		
		        		break;
		        	}
		        	
		        	case (3):{
		        		//UN
		        		
		        		for(int j=0; j<fktARG.size(); j++) {
		        			//UN ��2���~�����
		        			String fquery = insertIntoGenerator(fktName.get(j),fktARG.get(j).get(0));
		        			myWriter.println("			fquery = \"" + fquery + "\";");
		        			myWriter.println("			stmt.executeUpdate(fquery);");
		        			myWriter.println("			stmt = conn.createStatement();");
		        			fquery = insertIntoGenerator(fktName.get(j),fktARG.get(j).get(1));
		        			myWriter.println("			fquery = \"" + fquery + "\";");
		        			myWriter.println("			stmt.executeUpdate(fquery);");
		        			myWriter.println("			stmt = conn.createStatement();");
		        		}
		        		
		        		//�M��DUPEVALUE
		        		int dupeValue = 0;
		        		
		        		for(int j=0; j<localTable.getNumbersOfVariable(); j++) {
		        			if(localTable.TV[j].getVariableName().equals(constraintColumn)) {
		        				//pk&un testcase data localdata1&2
		        				//������쪺��ƬۦP ����@�n����dupevalue
		        				//�Ω�errorMessage
		        				dupeValue = Integer.valueOf(localData1.get(j));
		        			}
		        		}
		        		
		        		String query1 = insertIntoGenerator(localTable.getTableName(),localData1);
		        		String query2 = insertIntoGenerator(localTable.getTableName(),localData2);
		        		errorMessage = "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '" + dupeValue +"' for key '" + localTable.getTableName() +
			        			 "." + constraintColumn + "\'";
		        		
		        		//���J���a�� 2��
		        		myWriter.println("			String query1 = \"" + query1 + "\";");
		        		myWriter.println("			stmt.executeUpdate(query1);");
		        		myWriter.println("			stmt = conn.createStatement();");
		        		
		        		myWriter.println("			String query2 = \"" + query2 + "\";");
		        		myWriter.println("			stmt.executeUpdate(query2);");
		        		myWriter.println("			stmt = conn.createStatement();");
		        		
		        		break;
		        	}
		        	
		        	case (4):{
		        		//FK
		        		
		        		for(int j=0; j<fktARG.size(); j++) {
		        			//FK �u��1���~�����
		        			String fquery = insertIntoGenerator(fktName.get(j),fktARG.get(j).get(0));
		        			myWriter.println("			fquery = \"" + fquery + "\";");
		        			myWriter.println("			stmt.executeUpdate(fquery);");
		        			myWriter.println("			stmt = conn.createStatement();");
		        		}
		        		
		        		String query = insertIntoGenerator(localTable.getTableName(),localData1);
		        		errorMessage = "java.sql.SQLIntegrityConstraintViolationException: Cannot add or update a child row: a foreign key constraint fails (`"
			        			+ databaseName + "`.`" + localTable.getTableName() + "`, CONSTRAINT `" + localTable.getTableName() + "_ibfk_" + constraintFkNumber + "` FOREIGN KEY (`" 
			        			+ constraintColumn + "`) REFERENCES `" + constraintFkTable + "` (`" + constraintFkColumn + "`))";
		        		
		        		//���J���a��
		        		myWriter.println("			String query = \"" + query + "\";");
		        		myWriter.println("			stmt.executeUpdate(query);");
		        		myWriter.println("			stmt = conn.createStatement();");
		        		
		        		break;
		        	}
		        	
		        	case (5):{
		        		//CHECK
		        		
		        		for(int j=0; j<fktARG.size(); j++) {
		        			//CHECK �u��1���~�����
		        			String fquery = insertIntoGenerator(fktName.get(j),fktARG.get(j).get(0));
		        			myWriter.println("			fquery = \"" + fquery + "\";");
		        			myWriter.println("			stmt.executeUpdate(fquery);");
		        			myWriter.println("			stmt = conn.createStatement();");
		        		}
		        		
		        		String query = insertIntoGenerator(localTable.getTableName(),localData1);
		        		errorMessage = "java.sql.SQLException: Check constraint \'" + localTable.getTableName() + "_chk_" + constraintCkNumber + "\' is violated.";
		        		
		        		//���J���a��
		        		myWriter.println("			String query = \"" + query + "\";");
		        		myWriter.println("			stmt.executeUpdate(query);");
		        		myWriter.println("			stmt = conn.createStatement();");
		        		
		        		break;
		        	}
		        	
		        }
		       
	    		//try passed for JUnit 
	    		if(constraintType == 0) {
		        	myWriter.println("			assertTrue(true);");
		        }
	    		else {
	    			myWriter.println("			assertFalse(true);");
	    		}
	    		
		        //ResultSet rs = stmt.executeQuery(query);
		        myWriter.println("		}catch(Exception e){");
		        myWriter.println("			errorMessage = e.toString();");
		        
		        //�Y�����������|�ר� �����ߥX�ҥ~ ��assertfalse()
		        if(constraintType == 0) {
		        	myWriter.println("			assertTrue(false);");
		        }
		        else {
		        	myWriter.println("			assertEquals(errorMessage, \"" + errorMessage + "\");");
		        }
		        
		        myWriter.println("		}");
		        
		        myWriter.println("");
		        myWriter.println("		System.out.println(\"" + errorMessage+"\");");
		        myWriter.println("");
		        
		        //DELETE ALL RECORDS
		        myWriter.println("		//RESET TEST RECORDS");
		        myWriter.println("		try{");
		        myWriter.println("			" + conn);
		        myWriter.println("			String query;");
		        myWriter.println("			Statement stmt = conn.createStatement();");
		        myWriter.println("			query = \"SET FOREIGN_KEY_CHECKS=0;\";");
		        myWriter.println("			stmt.executeUpdate(query);");
	        	myWriter.println("			stmt = conn.createStatement();");
		        for(int j=0; j<numberOfTable; j++) {
		        	myWriter.println("			query= \"DELETE FROM "+ schemas[j].getTableName() +"\";");
		        	myWriter.println("			stmt.executeUpdate(query);");
		        	myWriter.println("			stmt = conn.createStatement();");
		        }
		        myWriter.println("			query = \"SET FOREIGN_KEY_CHECKS=1;\";");
		        myWriter.println("			stmt.executeUpdate(query);");
	        	myWriter.println("			stmt = conn.createStatement();");
		        myWriter.println("		}catch(Exception e){");
		        myWriter.println("			fail(\"RECORD CLEAR FAILED\");");
		        myWriter.println("		}");
		        
		        
		        myWriter.println("	}");
			}
			
			myWriter.println("}");
			myWriter.close();	
			
		} catch(Exception e) {
			System.out.println("test script gen failed");
			e.printStackTrace();
		}
	}
	
	private String upperCaseFirst(String val) {
		char[] arr = val.toCharArray();
		arr[0] = Character.toUpperCase(arr[0]);
	    return new String(arr);
	}
	
	private String insertIntoGenerator(String tableName, List<String> data) {
		String s = "";
		s = s + "INSERT INTO " + tableName + " VALUES (";
		for(int i=0; i<data.size(); i++) {
			s = s + data.get(i);
			if(i!=data.size()-1) {
				s = s + ",";
			}
		}
		s = s + ");";
		return s;
	}
	
	
}