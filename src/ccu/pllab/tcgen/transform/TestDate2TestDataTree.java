package ccu.pllab.tcgen.transform;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import ccu.pllab.tcgen.AbstractSyntaxTree.AbstractSyntaxTreeNode;
import ccu.pllab.tcgen.AbstractType.VariableType;
import ccu.pllab.tcgen.AbstractType.VoidType;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.oclRunner.OclLexer;
import ccu.pllab.tcgen.oclRunner.OclParser;
import ccu.pllab.tcgen.testdataRunner.TestDataLexer;
import ccu.pllab.tcgen.testdataRunner.TestDataParser;
import ccu.pllab.tcgen.typeTestData.MethodTestData;
import ccu.pllab.tcgen.typeTestData.TypeTestData;

public class TestDate2TestDataTree {

	private List<MethodTestData> testDataList;

	public TestDate2TestDataTree(){
	}
	
	public List<MethodTestData> getTestDataList() {
		return this.testDataList;
	}

	public void makeAST(VariableType type, String className,String methodName,File testDataFile) throws IOException {//轉換成抽象語法樹
		InputStream is = System.in;
		if (testDataFile != null)
			is = new FileInputStream(testDataFile);
		ANTLRInputStream input = new ANTLRInputStream(is);
		TestDataLexer lexer = new TestDataLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		TestDataParser parser = new TestDataParser(tokens);
		this.testDataList = parser.testDataList(type,className,methodName).methodTestData;
		is.close();
	}
	
	public ArrayList<TypeTestData> TransformTypeTestData(String testDataString,VariableType type, String className,String methodName,String testDataClass) throws IOException {
		InputStream is = new ByteArrayInputStream(testDataString.getBytes(StandardCharsets.UTF_8));
		ANTLRInputStream input = new ANTLRInputStream(is);
		TestDataLexer lexer = new TestDataLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		TestDataParser parser = new TestDataParser(tokens);
		
		 LinkedHashMap<String, VariableToken> argList = Main.typeTable.get(type.getType(), null).getSymbolTable().getMethodToken(methodName).getArgument();
		 
		 ArrayList<VariableType> argTypeList = new ArrayList<VariableType>();
		 for(Entry<String, VariableToken> arg : argList.entrySet()) {
			 argTypeList.add(arg.getValue().getType());
		 }
		 
		 if(argTypeList.size() < 1) {
			 argTypeList.add(new VoidType());
		 }
		 
		ArrayList<TypeTestData> testDataList =  parser.objElmList(type, methodName, testDataClass,argTypeList).testdata;
		is.close();
		return testDataList;
	}

}
