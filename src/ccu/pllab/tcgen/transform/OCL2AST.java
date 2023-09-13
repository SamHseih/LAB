//maker:王建瓏
package ccu.pllab.tcgen.transform;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.xml.sax.SAXException;

import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.AbstractType.*;
import ccu.pllab.tcgen.exe.main.Main;
import ccu.pllab.tcgen.launcher.BlackBoxLauncher;
import ccu.pllab.tcgen.oclRunner.OclLexer;
import ccu.pllab.tcgen.oclRunner.OclParser;
import ccu.pllab.tcgen.oclRunner.OclParser.PackageDeclarationCSContext;
import ccu.pllab.tcgen.PapyrusCDParser.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.io.File;

public class OCL2AST {
	private PackageExp ast;// 抽象語法樹

	public OCL2AST() throws Exception {
	}

	public AbstractSyntaxTreeNode getAbstractSyntaxTree() {
		return this.ast;
	}

	public void makeAST(File ocl) throws IOException {// OCL轉換成抽象語法樹
		InputStream is = System.in;
		if (ocl != null)
			is = new FileInputStream(ocl);// 測試的OCL檔
		ANTLRInputStream input = new ANTLRInputStream(is);
		OclLexer lexer = new OclLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		OclParser parser = new OclParser(tokens);
		this.ast = parser.packageDeclarationCS().astRoot;// 跑完編譯後，會回傳AST結構tree
		is.close();
	}

	// 20210803 已不再使用
	public void typeToAst(TypeTable typeTable) {
		int ocl_start = 0;// 代表operationContext是從第幾個開始
		if (this.ast.getTreeNode().get(0) instanceof ClassifierContext)// 因為有些OCL檔案沒有inv
		{ // 設定屬性的type
			((ClassifierContext) (this.ast.getTreeNode().get(0))).getInv().getTreeNode().addVariableType(typeTable,
					((ClassifierContext) (this.ast.getTreeNode().get(0))).getClassName());

			ocl_start = 1;// 因為有classifierContext所以operationContext必定在第二個地方出現
		}

		// ........處理operationContext.........
		for (; ocl_start < this.ast.getTreeNode().size(); ocl_start++) {// 走訪所有OperationContext
			ArrayList<StereoType> stereo = ((OperationContext) (this.ast.getTreeNode().get(ocl_start))).getStereoType();
			for (int stereo_number = 0; stereo_number < stereo.size(); stereo_number++) {
				stereo.get(stereo_number).getTreeNode().addVariableType(typeTable,
						((OperationContext) (this.ast.getTreeNode().get(ocl_start))).getClassName());
				if (stereo_number == 0 && stereo.size() > 1)
					stereo.get(stereo_number).getTreeNode().changeAssignToEqual();
				else
					stereo.get(stereo_number).getTreeNode().conditionChangeAssignToEqual();

			}
		}
	}
}
