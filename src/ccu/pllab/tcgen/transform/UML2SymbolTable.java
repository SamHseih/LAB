package ccu.pllab.tcgen.transform;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import ccu.pllab.tcgen.AbstractType.UserDefinedType;
import ccu.pllab.tcgen.PapyrusCDParser.ClassInfo;
import ccu.pllab.tcgen.PapyrusCDParser.OperationInfo;
import ccu.pllab.tcgen.PapyrusCDParser.SingleCDParser;
import ccu.pllab.tcgen.PapyrusCDParser.VariableInfo;
import ccu.pllab.tcgen.SymbolTable.MethodToken;
import ccu.pllab.tcgen.SymbolTable.SymbolTable;
import ccu.pllab.tcgen.SymbolTable.VariableToken;
import ccu.pllab.tcgen.exe.main.Main;

/*
 * 由類別圖的UML檔產生SymbolTable
 */
public class UML2SymbolTable {
	File classuml;

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}

	SymbolTable symbolTable;

	public UML2SymbolTable(File classuml) {
		super();
		this.classuml = classuml;
	}

	public void makeSymbolTable() throws ParserConfigurationException, SAXException, IOException, TransformerException {
		// 類別圖分析..黎怡伶做的
		SingleCDParser classParser = new SingleCDParser();
		classParser.Parse(classParser.init(classuml));

		// 開始做symbol table
		this.symbolTable = new SymbolTable(classParser.getPkgName());
		ClassInfo c = classParser.getClassList().get(0);
		// Main.className = c.getName();

		// 測試類別資訊是否正確
		System.out.println(classParser.printParseClassInfo(c));

		for (int j = 0; c.getProperties() != null && j < c.getProperties().size(); j++) {
			VariableInfo p = c.getProperties().get(j);
			// put property into symboltalbe
			VariableToken variable = new VariableToken(p.getName(), p.getType());
			this.symbolTable.addAttribute(variable);
		}

		for (int k = 0; c.getOperations() != null && k < c.getOperations().size(); k++) {
			OperationInfo o = c.getOperations().get(k);
			MethodToken method = new MethodToken(o.getName());
			String varStr = o.getReturnType().getClass().toString().substring(0,
					o.getReturnType().getClass().toString().length() - 4);

			// 處理method的回傳值
			method.setReturnType(o.getReturnType().getType());

			// 處理參數
			for (int index = 0; o.getParameter() != null && index < o.getParameter().size(); index++) {
				VariableInfo p = o.getParameter().get(index);
				method.addArgument(new VariableToken(p.getName(), p.getType()));
			}
			// put method into symboltable
			this.symbolTable.addMethod(method);
		}
		// Main.symbolTable = this.symbolTable;

		UserDefinedType this_class_type = (UserDefinedType) Main.typeTable.get(c.getName(), c.getID());
		this_class_type.setClassInfo(c); // 暫時保留，classinfo 與 symbolTable 相似 20210204 dai
		((UserDefinedType) this_class_type).setSymbolTable(symbolTable);
		Main.typeTable.add(this_class_type);
	}
}
