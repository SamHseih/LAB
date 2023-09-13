// Generated from TestData.g4 by ANTLR 4.4
package ccu.pllab.tcgen.testdataRunner;

	//�o�����O�N�Ojava���Yimport�������A�]��.g4�ɻPXXXParser.java�O�s�����Y�A
	//�ҥH���ઽ���bXXXParser.java�W�Kimport�A�@���s�ɴN�|�Q�~���A
	//�]���p�G�nimport���ɭԡA�b@header���Y�[
	import ccu.pllab.tcgen.SymbolTable.*;
	import ccu.pllab.tcgen.AbstractType.*;
	import ccu.pllab.tcgen.typeTestData.*;
	import ccu.pllab.tcgen.exe.main.*;
	import java.util.ArrayList;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TestDataParser}.
 */
public interface TestDataListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TestDataParser#testData}.
	 * @param ctx the parse tree
	 */
	void enterTestData(@NotNull TestDataParser.TestDataContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#testData}.
	 * @param ctx the parse tree
	 */
	void exitTestData(@NotNull TestDataParser.TestDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#elms}.
	 * @param ctx the parse tree
	 */
	void enterElms(@NotNull TestDataParser.ElmsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#elms}.
	 * @param ctx the parse tree
	 */
	void exitElms(@NotNull TestDataParser.ElmsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#testDataList}.
	 * @param ctx the parse tree
	 */
	void enterTestDataList(@NotNull TestDataParser.TestDataListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#testDataList}.
	 * @param ctx the parse tree
	 */
	void exitTestDataList(@NotNull TestDataParser.TestDataListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#itemState}.
	 * @param ctx the parse tree
	 */
	void enterItemState(@NotNull TestDataParser.ItemStateContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#itemState}.
	 * @param ctx the parse tree
	 */
	void exitItemState(@NotNull TestDataParser.ItemStateContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#objElmList}.
	 * @param ctx the parse tree
	 */
	void enterObjElmList(@NotNull TestDataParser.ObjElmListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#objElmList}.
	 * @param ctx the parse tree
	 */
	void exitObjElmList(@NotNull TestDataParser.ObjElmListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#exceptionState}.
	 * @param ctx the parse tree
	 */
	void enterExceptionState(@NotNull TestDataParser.ExceptionStateContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#exceptionState}.
	 * @param ctx the parse tree
	 */
	void exitExceptionState(@NotNull TestDataParser.ExceptionStateContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#typeData}.
	 * @param ctx the parse tree
	 */
	void enterTypeData(@NotNull TestDataParser.TypeDataContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#typeData}.
	 * @param ctx the parse tree
	 */
	void exitTypeData(@NotNull TestDataParser.TypeDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(@NotNull TestDataParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(@NotNull TestDataParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#objElm}.
	 * @param ctx the parse tree
	 */
	void enterObjElm(@NotNull TestDataParser.ObjElmContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#objElm}.
	 * @param ctx the parse tree
	 */
	void exitObjElm(@NotNull TestDataParser.ObjElmContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#itemName}.
	 * @param ctx the parse tree
	 */
	void enterItemName(@NotNull TestDataParser.ItemNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#itemName}.
	 * @param ctx the parse tree
	 */
	void exitItemName(@NotNull TestDataParser.ItemNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#realBoundList}.
	 * @param ctx the parse tree
	 */
	void enterRealBoundList(@NotNull TestDataParser.RealBoundListContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#realBoundList}.
	 * @param ctx the parse tree
	 */
	void exitRealBoundList(@NotNull TestDataParser.RealBoundListContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#array}.
	 * @param ctx the parse tree
	 */
	void enterArray(@NotNull TestDataParser.ArrayContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#array}.
	 * @param ctx the parse tree
	 */
	void exitArray(@NotNull TestDataParser.ArrayContext ctx);
	/**
	 * Enter a parse tree produced by {@link TestDataParser#time}.
	 * @param ctx the parse tree
	 */
	void enterTime(@NotNull TestDataParser.TimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TestDataParser#time}.
	 * @param ctx the parse tree
	 */
	void exitTime(@NotNull TestDataParser.TimeContext ctx);
}