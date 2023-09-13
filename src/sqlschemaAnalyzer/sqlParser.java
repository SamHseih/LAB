package sqlschemaAnalyzer;
// Generated from sql.g4 by ANTLR 4.4
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import ccu.pllab.tcgen.AbstractCLG.*;
import ccu.pllab.tcgen.AbstractConstraint.*;
import ccu.pllab.tcgen.AbstractSyntaxTree.*;
import ccu.pllab.tcgen.AbstractType.*;
@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class sqlParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__24=1, T__23=2, T__22=3, T__21=4, T__20=5, T__19=6, T__18=7, T__17=8, 
		T__16=9, T__15=10, T__14=11, T__13=12, T__12=13, T__11=14, T__10=15, T__9=16, 
		T__8=17, T__7=18, T__6=19, T__5=20, T__4=21, T__3=22, T__2=23, T__1=24, 
		T__0=25, INTEGER=26, BOOLEAN=27, STRING=28, EQAUL=29, NOTEQUAL=30, GREATER=31, 
		GREATEREQUAL=32, LESSER=33, LESSEREQUAL=34, NUMBER=35, ID=36, WS=37;
	public static final String[] tokenNames = {
		"<INVALID>", "'/'", "'FOREIGN'", "'TABLE'", "'REFERENCES'", "'AND'", "';'", 
		"'NOT'", "'UNIQUE'", "'null'", "'KEY'", "'('", "'*'", "','", "'primary'", 
		"'PRIMARY'", "'key'", "'CHECK'", "'unique'", "'OR'", "'NULL'", "')'", 
		"'+'", "'CREATE'", "'not'", "'-'", "INTEGER", "BOOLEAN", "STRING", "EQAUL", 
		"'!='", "'>'", "GREATEREQUAL", "'<'", "LESSEREQUAL", "NUMBER", "ID", "WS"
	};
	public static final int
		RULE_schema = 0, RULE_table = 1, RULE_constraint = 2, RULE_variableConstraint = 3, 
		RULE_foreignKeyConstraint = 4, RULE_checkConstraint = 5, RULE_tableName = 6, 
		RULE_variableName = 7, RULE_variableType = 8, RULE_variableKeyword = 9, 
		RULE_keywordPrimaryKey = 10, RULE_keywordNotNull = 11, RULE_keywordUnique = 12, 
		RULE_localIndex = 13, RULE_foreignKeyTable = 14, RULE_foreignKeyIndex = 15, 
		RULE_checkEquationL = 16, RULE_checkEquationR = 17, RULE_checkEquation = 18, 
		RULE_checkEquation2 = 19, RULE_checkVariablePasser = 20, RULE_checkOperator = 21, 
		RULE_equationOperator = 22;
	public static final String[] ruleNames = {
		"schema", "table", "constraint", "variableConstraint", "foreignKeyConstraint", 
		"checkConstraint", "tableName", "variableName", "variableType", "variableKeyword", 
		"keywordPrimaryKey", "keywordNotNull", "keywordUnique", "localIndex", 
		"foreignKeyTable", "foreignKeyIndex", "checkEquationL", "checkEquationR", 
		"checkEquation", "checkEquation2", "checkVariablePasser", "checkOperator", 
		"equationOperator"
	};

	@Override
	public String getGrammarFileName() { return "sql.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		final int aset = 255;
		public int numbersOfTable = 0;
		public schemaTable[] schema = new schemaTable[255];
		public int fkNumber=0;

	public sqlParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SchemaContext extends ParserRuleContext {
		public List<TableContext> table() {
			return getRuleContexts(TableContext.class);
		}
		public TableContext table(int i) {
			return getRuleContext(TableContext.class,i);
		}
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitSchema(this);
		}
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_schema);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(46); table();
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__2 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableContext extends ParserRuleContext {
		public TableNameContext tableName;
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public List<ConstraintContext> constraint() {
			return getRuleContexts(ConstraintContext.class);
		}
		public ConstraintContext constraint(int i) {
			return getRuleContext(ConstraintContext.class,i);
		}
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitTable(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_table);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51); match(T__2);
			setState(52); match(T__22);
			setState(53); ((TableContext)_localctx).tableName = tableName();

				schema[numbersOfTable] = new schemaTable();
				schema[numbersOfTable].setTableName(((TableContext)_localctx).tableName.name);

			setState(55); match(T__14);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__8) | (1L << ID))) != 0)) {
				{
				{
				setState(56); constraint();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62); match(T__4);
			setState(63); match(T__19);

				numbersOfTable++;
				fkNumber=0;

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstraintContext extends ParserRuleContext {
		public VariableConstraintContext variableConstraint() {
			return getRuleContext(VariableConstraintContext.class,0);
		}
		public ForeignKeyConstraintContext foreignKeyConstraint() {
			return getRuleContext(ForeignKeyConstraintContext.class,0);
		}
		public CheckConstraintContext checkConstraint() {
			return getRuleContext(CheckConstraintContext.class,0);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_constraint);
		try {
			setState(78);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(66); variableConstraint();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(67); variableConstraint();
				setState(68); match(T__12);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(70); foreignKeyConstraint();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(71); foreignKeyConstraint();
				setState(72); match(T__12);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(74); checkConstraint();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(75); checkConstraint();
				setState(76); match(T__12);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableConstraintContext extends ParserRuleContext {
		public VariableNameContext variableName;
		public VariableNameContext variableName() {
			return getRuleContext(VariableNameContext.class,0);
		}
		public List<VariableKeywordContext> variableKeyword() {
			return getRuleContexts(VariableKeywordContext.class);
		}
		public VariableTypeContext variableType() {
			return getRuleContext(VariableTypeContext.class,0);
		}
		public VariableKeywordContext variableKeyword(int i) {
			return getRuleContext(VariableKeywordContext.class,i);
		}
		public VariableConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterVariableConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitVariableConstraint(this);
		}
	}

	public final VariableConstraintContext variableConstraint() throws RecognitionException {
		VariableConstraintContext _localctx = new VariableConstraintContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_variableConstraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); ((VariableConstraintContext)_localctx).variableName = variableName();

				schema[numbersOfTable].TV[schema[numbersOfTable].numbersOfVariable] = new tableVariable();
				schema[numbersOfTable].TV[schema[numbersOfTable].numbersOfVariable].setVariableName(((VariableConstraintContext)_localctx).variableName.name);

			setState(82); variableType();
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__18) | (1L << T__17) | (1L << T__11) | (1L << T__10) | (1L << T__7) | (1L << T__1))) != 0)) {
				{
				{
				setState(83); variableKeyword();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

				schema[numbersOfTable].addNumbersOfVariable();

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForeignKeyConstraintContext extends ParserRuleContext {
		public LocalIndexContext localIndex;
		public ForeignKeyTableContext foreignKeyTable;
		public ForeignKeyIndexContext foreignKeyIndex;
		public ForeignKeyIndexContext foreignKeyIndex() {
			return getRuleContext(ForeignKeyIndexContext.class,0);
		}
		public ForeignKeyTableContext foreignKeyTable() {
			return getRuleContext(ForeignKeyTableContext.class,0);
		}
		public LocalIndexContext localIndex() {
			return getRuleContext(LocalIndexContext.class,0);
		}
		public ForeignKeyConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignKeyConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterForeignKeyConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitForeignKeyConstraint(this);
		}
	}

	public final ForeignKeyConstraintContext foreignKeyConstraint() throws RecognitionException {
		ForeignKeyConstraintContext _localctx = new ForeignKeyConstraintContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_foreignKeyConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); match(T__23);
			setState(92); match(T__15);
			setState(93); match(T__14);
			setState(94); ((ForeignKeyConstraintContext)_localctx).localIndex = localIndex();
			setState(95); match(T__4);
			setState(96); match(T__21);
			setState(97); ((ForeignKeyConstraintContext)_localctx).foreignKeyTable = foreignKeyTable();
			setState(98); match(T__14);
			setState(99); ((ForeignKeyConstraintContext)_localctx).foreignKeyIndex = foreignKeyIndex();
			setState(100); match(T__4);

				System.out.println("new reference: " + ((ForeignKeyConstraintContext)_localctx).localIndex.name + " to " + ((ForeignKeyConstraintContext)_localctx).foreignKeyTable.name +"(" + ((ForeignKeyConstraintContext)_localctx).foreignKeyIndex.name + ")");
				if(schema[numbersOfTable].getVariableNo(((ForeignKeyConstraintContext)_localctx).localIndex.name)!=-1){
					fkNumber++;
					schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo(((ForeignKeyConstraintContext)_localctx).localIndex.name)].setForeignKey(((ForeignKeyConstraintContext)_localctx).foreignKeyTable.name, ((ForeignKeyConstraintContext)_localctx).foreignKeyIndex.name, fkNumber);
				}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CheckConstraintContext extends ParserRuleContext {
		public CheckEquationLContext checkEquationL;
		public CheckOperatorContext checkOperator;
		public CheckEquationRContext checkEquationR;
		public CheckOperatorContext checkOperator() {
			return getRuleContext(CheckOperatorContext.class,0);
		}
		public CheckEquationLContext checkEquationL() {
			return getRuleContext(CheckEquationLContext.class,0);
		}
		public CheckEquationRContext checkEquationR() {
			return getRuleContext(CheckEquationRContext.class,0);
		}
		public CheckConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterCheckConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitCheckConstraint(this);
		}
	}

	public final CheckConstraintContext checkConstraint() throws RecognitionException {
		CheckConstraintContext _localctx = new CheckConstraintContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_checkConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103); match(T__8);

				schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck] = new tableCheck();

			setState(105); match(T__14);
			setState(106); ((CheckConstraintContext)_localctx).checkEquationL = checkEquationL();
			setState(107); ((CheckConstraintContext)_localctx).checkOperator = checkOperator();
			setState(108); ((CheckConstraintContext)_localctx).checkEquationR = checkEquationR();
			setState(109); match(T__4);

				System.out.println("L.equation: " + ((CheckConstraintContext)_localctx).checkEquationL.equation);
				System.out.println("operator: " + ((CheckConstraintContext)_localctx).checkOperator.type);
				System.out.println("R.equation: " + ((CheckConstraintContext)_localctx).checkEquationR.equation);
				
				if(((CheckConstraintContext)_localctx).checkEquationL.ck2Exist && ((CheckConstraintContext)_localctx).checkEquationR.ck2Exist){
					System.out.println("L R EXIST 2 CK");
					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheck(((CheckConstraintContext)_localctx).checkEquationL.equation, ((CheckConstraintContext)_localctx).checkOperator.type, ((CheckConstraintContext)_localctx).checkEquationR.equation,((CheckConstraintContext)_localctx).checkEquationL.check,((CheckConstraintContext)_localctx).checkEquationR.check);
					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheck2(((CheckConstraintContext)_localctx).checkEquationL.equation, ((CheckConstraintContext)_localctx).checkOperator.type, ((CheckConstraintContext)_localctx).checkEquationR.equation,((CheckConstraintContext)_localctx).checkEquationL.check2,((CheckConstraintContext)_localctx).checkEquationR.check2);
				}else if(((CheckConstraintContext)_localctx).checkEquationL.ck2Exist && !((CheckConstraintContext)_localctx).checkEquationR.ck2Exist){
					System.out.println("L EXIST 2 CK");
					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheck(((CheckConstraintContext)_localctx).checkEquationL.equation, ((CheckConstraintContext)_localctx).checkOperator.type, ((CheckConstraintContext)_localctx).checkEquationR.equation,((CheckConstraintContext)_localctx).checkEquationL.check,((CheckConstraintContext)_localctx).checkEquationR.check);
					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheck2(((CheckConstraintContext)_localctx).checkEquationL.equation, ((CheckConstraintContext)_localctx).checkOperator.type, ((CheckConstraintContext)_localctx).checkEquationR.equation,((CheckConstraintContext)_localctx).checkEquationL.check2,((CheckConstraintContext)_localctx).checkEquationR.check);
				}else if(!((CheckConstraintContext)_localctx).checkEquationL.ck2Exist && ((CheckConstraintContext)_localctx).checkEquationR.ck2Exist){
					System.out.println("R EXIST 2 CK");
					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheck(((CheckConstraintContext)_localctx).checkEquationL.equation, ((CheckConstraintContext)_localctx).checkOperator.type, ((CheckConstraintContext)_localctx).checkEquationR.equation,((CheckConstraintContext)_localctx).checkEquationL.check,((CheckConstraintContext)_localctx).checkEquationR.check);
					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheck2(((CheckConstraintContext)_localctx).checkEquationL.equation, ((CheckConstraintContext)_localctx).checkOperator.type, ((CheckConstraintContext)_localctx).checkEquationR.equation,((CheckConstraintContext)_localctx).checkEquationL.check,((CheckConstraintContext)_localctx).checkEquationR.check2);
				}else{
					System.out.println("NO 2 CK");
					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheck(((CheckConstraintContext)_localctx).checkEquationL.equation, ((CheckConstraintContext)_localctx).checkOperator.type, ((CheckConstraintContext)_localctx).checkEquationR.equation,((CheckConstraintContext)_localctx).checkEquationL.check,((CheckConstraintContext)_localctx).checkEquationR.check);
				}
				schema[numbersOfTable].addNumbersOfCheck();

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableNameContext extends ParserRuleContext {
		public String name;
		public Token ID;
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitTableName(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_tableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112); ((TableNameContext)_localctx).ID = match(ID);
			System.out.println("new table: "+(((TableNameContext)_localctx).ID!=null?((TableNameContext)_localctx).ID.getText():null));
				((TableNameContext)_localctx).name =  (((TableNameContext)_localctx).ID!=null?((TableNameContext)_localctx).ID.getText():null);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableNameContext extends ParserRuleContext {
		public String name;
		public Token ID;
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public VariableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterVariableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitVariableName(this);
		}
	}

	public final VariableNameContext variableName() throws RecognitionException {
		VariableNameContext _localctx = new VariableNameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variableName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(115); ((VariableNameContext)_localctx).ID = match(ID);
			System.out.println("variable: "+(((VariableNameContext)_localctx).ID!=null?((VariableNameContext)_localctx).ID.getText():null));
				((VariableNameContext)_localctx).name =  (((VariableNameContext)_localctx).ID!=null?((VariableNameContext)_localctx).ID.getText():null);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableTypeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(sqlParser.INTEGER, 0); }
		public TerminalNode STRING() { return getToken(sqlParser.STRING, 0); }
		public TerminalNode BOOLEAN() { return getToken(sqlParser.BOOLEAN, 0); }
		public VariableTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterVariableType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitVariableType(this);
		}
	}

	public final VariableTypeContext variableType() throws RecognitionException {
		VariableTypeContext _localctx = new VariableTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variableType);
		try {
			setState(124);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(118); match(INTEGER);

					schema[numbersOfTable].TV[schema[numbersOfTable].numbersOfVariable].setVariableType(1);

				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 2);
				{
				setState(120); match(BOOLEAN);

					schema[numbersOfTable].TV[schema[numbersOfTable].numbersOfVariable].setVariableType(0);

				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(122); match(STRING);

					schema[numbersOfTable].TV[schema[numbersOfTable].numbersOfVariable].setVariableType(2);

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableKeywordContext extends ParserRuleContext {
		public KeywordNotNullContext keywordNotNull() {
			return getRuleContext(KeywordNotNullContext.class,0);
		}
		public KeywordPrimaryKeyContext keywordPrimaryKey() {
			return getRuleContext(KeywordPrimaryKeyContext.class,0);
		}
		public KeywordUniqueContext keywordUnique() {
			return getRuleContext(KeywordUniqueContext.class,0);
		}
		public VariableKeywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableKeyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterVariableKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitVariableKeyword(this);
		}
	}

	public final VariableKeywordContext variableKeyword() throws RecognitionException {
		VariableKeywordContext _localctx = new VariableKeywordContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_variableKeyword);
		try {
			setState(135);
			switch (_input.LA(1)) {
			case T__11:
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(126); keywordPrimaryKey();

					schema[numbersOfTable].TV[schema[numbersOfTable].numbersOfVariable].setVariableIspk();

				}
				break;
			case T__18:
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(129); keywordNotNull();

					schema[numbersOfTable].TV[schema[numbersOfTable].numbersOfVariable].setVariableIsnn();

				}
				break;
			case T__17:
			case T__7:
				enterOuterAlt(_localctx, 3);
				{
				setState(132); keywordUnique();

					schema[numbersOfTable].TV[schema[numbersOfTable].numbersOfVariable].setVariableIsun();

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordPrimaryKeyContext extends ParserRuleContext {
		public KeywordPrimaryKeyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keywordPrimaryKey; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterKeywordPrimaryKey(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitKeywordPrimaryKey(this);
		}
	}

	public final KeywordPrimaryKeyContext keywordPrimaryKey() throws RecognitionException {
		KeywordPrimaryKeyContext _localctx = new KeywordPrimaryKeyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_keywordPrimaryKey);
		try {
			setState(141);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(137); match(T__10);
				setState(138); match(T__15);
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(139); match(T__11);
				setState(140); match(T__9);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordNotNullContext extends ParserRuleContext {
		public KeywordNotNullContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keywordNotNull; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterKeywordNotNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitKeywordNotNull(this);
		}
	}

	public final KeywordNotNullContext keywordNotNull() throws RecognitionException {
		KeywordNotNullContext _localctx = new KeywordNotNullContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_keywordNotNull);
		try {
			setState(147);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(143); match(T__18);
				setState(144); match(T__5);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(145); match(T__1);
				setState(146); match(T__16);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class KeywordUniqueContext extends ParserRuleContext {
		public KeywordUniqueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_keywordUnique; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterKeywordUnique(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitKeywordUnique(this);
		}
	}

	public final KeywordUniqueContext keywordUnique() throws RecognitionException {
		KeywordUniqueContext _localctx = new KeywordUniqueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_keywordUnique);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(149);
			_la = _input.LA(1);
			if ( !(_la==T__17 || _la==T__7) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalIndexContext extends ParserRuleContext {
		public String name;
		public Token ID;
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public LocalIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterLocalIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitLocalIndex(this);
		}
	}

	public final LocalIndexContext localIndex() throws RecognitionException {
		LocalIndexContext _localctx = new LocalIndexContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_localIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(151); ((LocalIndexContext)_localctx).ID = match(ID);

				((LocalIndexContext)_localctx).name =  (((LocalIndexContext)_localctx).ID!=null?((LocalIndexContext)_localctx).ID.getText():null);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForeignKeyTableContext extends ParserRuleContext {
		public String name;
		public Token ID;
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public ForeignKeyTableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignKeyTable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterForeignKeyTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitForeignKeyTable(this);
		}
	}

	public final ForeignKeyTableContext foreignKeyTable() throws RecognitionException {
		ForeignKeyTableContext _localctx = new ForeignKeyTableContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_foreignKeyTable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154); ((ForeignKeyTableContext)_localctx).ID = match(ID);

				((ForeignKeyTableContext)_localctx).name =  (((ForeignKeyTableContext)_localctx).ID!=null?((ForeignKeyTableContext)_localctx).ID.getText():null);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForeignKeyIndexContext extends ParserRuleContext {
		public String name;
		public Token ID;
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public ForeignKeyIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreignKeyIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterForeignKeyIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitForeignKeyIndex(this);
		}
	}

	public final ForeignKeyIndexContext foreignKeyIndex() throws RecognitionException {
		ForeignKeyIndexContext _localctx = new ForeignKeyIndexContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_foreignKeyIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157); ((ForeignKeyIndexContext)_localctx).ID = match(ID);

				((ForeignKeyIndexContext)_localctx).name =  (((ForeignKeyIndexContext)_localctx).ID!=null?((ForeignKeyIndexContext)_localctx).ID.getText():null);

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CheckEquationLContext extends ParserRuleContext {
		public String equation;
		public CLGConstraint check;
		public CLGConstraint check2;
		public boolean ck2Exist;
		public CheckEquationContext checkEquation;
		public CheckEquationContext checkEquation() {
			return getRuleContext(CheckEquationContext.class,0);
		}
		public CheckEquationLContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkEquationL; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterCheckEquationL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitCheckEquationL(this);
		}
	}

	public final CheckEquationLContext checkEquationL() throws RecognitionException {
		CheckEquationLContext _localctx = new CheckEquationLContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_checkEquationL);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160); ((CheckEquationLContext)_localctx).checkEquation = checkEquation();

				((CheckEquationLContext)_localctx).equation =  ((CheckEquationLContext)_localctx).checkEquation.equation;
				((CheckEquationLContext)_localctx).check =  ((CheckEquationLContext)_localctx).checkEquation.check;
				
				if(((CheckEquationLContext)_localctx).checkEquation.ck2Exist){
					((CheckEquationLContext)_localctx).check2 =  ((CheckEquationLContext)_localctx).checkEquation.check2;
					((CheckEquationLContext)_localctx).ck2Exist =  true;
				}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CheckEquationRContext extends ParserRuleContext {
		public String equation;
		public CLGConstraint check;
		public CLGConstraint check2;
		public boolean ck2Exist;
		public CheckEquationContext checkEquation;
		public CheckEquationContext checkEquation() {
			return getRuleContext(CheckEquationContext.class,0);
		}
		public CheckEquationRContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkEquationR; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterCheckEquationR(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitCheckEquationR(this);
		}
	}

	public final CheckEquationRContext checkEquationR() throws RecognitionException {
		CheckEquationRContext _localctx = new CheckEquationRContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_checkEquationR);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163); ((CheckEquationRContext)_localctx).checkEquation = checkEquation();

				((CheckEquationRContext)_localctx).equation =  ((CheckEquationRContext)_localctx).checkEquation.equation;
				((CheckEquationRContext)_localctx).check =  ((CheckEquationRContext)_localctx).checkEquation.check;
				
				if(((CheckEquationRContext)_localctx).checkEquation.ck2Exist){
					((CheckEquationRContext)_localctx).check2 =  ((CheckEquationRContext)_localctx).checkEquation.check2;
					((CheckEquationRContext)_localctx).ck2Exist =  true;
				}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CheckEquationContext extends ParserRuleContext {
		public String equation;
		public CLGConstraint check;
		public CLGConstraint check2;
		public boolean ck2Exist;
		public Token ID;
		public Token NUMBER;
		public CheckEquation2Context checkEquation2;
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public TerminalNode NUMBER() { return getToken(sqlParser.NUMBER, 0); }
		public CheckEquation2Context checkEquation2() {
			return getRuleContext(CheckEquation2Context.class,0);
		}
		public CheckEquationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkEquation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterCheckEquation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitCheckEquation(this);
		}
	}

	public final CheckEquationContext checkEquation() throws RecognitionException {
		CheckEquationContext _localctx = new CheckEquationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_checkEquation);
		try {
			setState(173);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(166); ((CheckEquationContext)_localctx).ID = match(ID);

					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheckVariables((((CheckEquationContext)_localctx).ID!=null?((CheckEquationContext)_localctx).ID.getText():null));
					((CheckEquationContext)_localctx).equation =  (((CheckEquationContext)_localctx).ID!=null?((CheckEquationContext)_localctx).ID.getText():null);
					
					CLGVariableNode id = new CLGVariableNode((((CheckEquationContext)_localctx).ID!=null?((CheckEquationContext)_localctx).ID.getText():null), "String");
					((CheckEquationContext)_localctx).check =  id;
					
					boolean IDisUQ = false;
					if(schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquationContext)_localctx).ID!=null?((CheckEquationContext)_localctx).ID.getText():null))].getVariableIspk()||schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquationContext)_localctx).ID!=null?((CheckEquationContext)_localctx).ID.getText():null))].getVariableIsun()){
						IDisUQ = true;
					}
					if(IDisUQ){
						CLGVariableNode idX = new CLGVariableNode((((CheckEquationContext)_localctx).ID!=null?((CheckEquationContext)_localctx).ID.getText():null)+"U", "String");
						((CheckEquationContext)_localctx).check2 =  idX;
						((CheckEquationContext)_localctx).ck2Exist =  true;
					}else{
						((CheckEquationContext)_localctx).ck2Exist =  false;
					}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(168); ((CheckEquationContext)_localctx).NUMBER = match(NUMBER);

					((CheckEquationContext)_localctx).equation =  (((CheckEquationContext)_localctx).NUMBER!=null?((CheckEquationContext)_localctx).NUMBER.getText():null);
					
					CLGLiteralNode number = new CLGLiteralNode((((CheckEquationContext)_localctx).NUMBER!=null?((CheckEquationContext)_localctx).NUMBER.getText():null), "int");
					((CheckEquationContext)_localctx).check =  number;
					((CheckEquationContext)_localctx).ck2Exist =  false;

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(170); ((CheckEquationContext)_localctx).checkEquation2 = checkEquation2(0);

					((CheckEquationContext)_localctx).equation =  ((CheckEquationContext)_localctx).checkEquation2.equation;
					((CheckEquationContext)_localctx).check =  ((CheckEquationContext)_localctx).checkEquation2.check;
					if(((CheckEquationContext)_localctx).checkEquation2.ck2Exist){
						((CheckEquationContext)_localctx).check2 =  ((CheckEquationContext)_localctx).checkEquation2.check2;
						((CheckEquationContext)_localctx).ck2Exist =  true;
					}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CheckEquation2Context extends ParserRuleContext {
		public String equation;
		public CLGConstraint check;
		public CLGConstraint check2;
		public boolean ck2Exist;
		public CheckEquation2Context ce;
		public Token NUMBER;
		public EquationOperatorContext equationOperator;
		public Token ID;
		public Token ID2;
		public Token NUMBER2;
		public TerminalNode NUMBER(int i) {
			return getToken(sqlParser.NUMBER, i);
		}
		public List<TerminalNode> ID() { return getTokens(sqlParser.ID); }
		public EquationOperatorContext equationOperator() {
			return getRuleContext(EquationOperatorContext.class,0);
		}
		public TerminalNode ID(int i) {
			return getToken(sqlParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(sqlParser.NUMBER); }
		public CheckEquation2Context checkEquation2() {
			return getRuleContext(CheckEquation2Context.class,0);
		}
		public CheckEquation2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkEquation2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterCheckEquation2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitCheckEquation2(this);
		}
	}

	public final CheckEquation2Context checkEquation2() throws RecognitionException {
		return checkEquation2(0);
	}

	private CheckEquation2Context checkEquation2(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CheckEquation2Context _localctx = new CheckEquation2Context(_ctx, _parentState);
		CheckEquation2Context _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_checkEquation2, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(176); ((CheckEquation2Context)_localctx).NUMBER = match(NUMBER);
				setState(177); ((CheckEquation2Context)_localctx).equationOperator = equationOperator();
				setState(178); ((CheckEquation2Context)_localctx).ce = checkEquation2(8);

					((CheckEquation2Context)_localctx).equation =  (((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null) + " " + (((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null) + " " + ((CheckEquation2Context)_localctx).ce.equation;
					
					CLGLiteralNode L = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null), "int");
					CLGOperatorNode O = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
					O.setLeftOperand(L);
					O.setRightOperand(((CheckEquation2Context)_localctx).ce.check);
					((CheckEquation2Context)_localctx).check =  O;
					
					if(((CheckEquation2Context)_localctx).ce.ck2Exist){
						CLGLiteralNode LX = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null), "int");
						CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						OX.setLeftOperand(LX);
						OX.setRightOperand(((CheckEquation2Context)_localctx).ce.check2);
						((CheckEquation2Context)_localctx).check2 =  OX;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
						
					}
					else{
						((CheckEquation2Context)_localctx).ck2Exist =  false;
					}

				}
				break;
			case 2:
				{
				setState(181); ((CheckEquation2Context)_localctx).ID = match(ID);
				setState(182); ((CheckEquation2Context)_localctx).equationOperator = equationOperator();
				setState(183); ((CheckEquation2Context)_localctx).ce = checkEquation2(6);

					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheckVariables((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null));
					((CheckEquation2Context)_localctx).equation =  (((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null) + " " + (((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null) + " " + ((CheckEquation2Context)_localctx).ce.equation;
					
					CLGVariableNode L = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null), "String");
					CLGOperatorNode O = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
					O.setLeftOperand(L);
					O.setRightOperand(((CheckEquation2Context)_localctx).ce.check);
					((CheckEquation2Context)_localctx).check =  O;
					
					boolean IDAisUQ = false;
					if(schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIspk()||schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIsun()){
						IDAisUQ = true;
					}
					if(IDAisUQ && ((CheckEquation2Context)_localctx).ce.ck2Exist){
						CLGVariableNode LX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null)+"U", "String");
						CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						OX.setLeftOperand(LX);
						OX.setRightOperand(((CheckEquation2Context)_localctx).ce.check2);
						((CheckEquation2Context)_localctx).check2 =  OX;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
						
					}else if(IDAisUQ && !((CheckEquation2Context)_localctx).ce.ck2Exist){
						CLGVariableNode LX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null)+"U", "String");
						CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						OX.setLeftOperand(LX);
						OX.setRightOperand(((CheckEquation2Context)_localctx).ce.check);
						((CheckEquation2Context)_localctx).check2 =  OX;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
						
					}
					else if(!IDAisUQ && ((CheckEquation2Context)_localctx).ce.ck2Exist){
						CLGVariableNode LX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null), "String");
						CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						OX.setLeftOperand(LX);
						OX.setRightOperand(((CheckEquation2Context)_localctx).ce.check2);
						((CheckEquation2Context)_localctx).check2 =  OX;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
						
					}
					else{
						((CheckEquation2Context)_localctx).ck2Exist =  false;
					}

				}
				break;
			case 3:
				{
				setState(186); ((CheckEquation2Context)_localctx).ID = match(ID);
				setState(187); ((CheckEquation2Context)_localctx).equationOperator = equationOperator();
				setState(188); ((CheckEquation2Context)_localctx).NUMBER = match(NUMBER);

					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheckVariables((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null));
					((CheckEquation2Context)_localctx).equation =  (((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null) + " " + (((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null) + " " + (((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null);
					
					CLGVariableNode L = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null), "String");
					CLGLiteralNode R = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null), "int");
					CLGOperatorNode O = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
					O.setLeftOperand(L);
					O.setRightOperand(R);
					((CheckEquation2Context)_localctx).check =  O;
					
					boolean IDAisUQ = false;
					if(schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIspk()||schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIsun()){
						IDAisUQ = true;
					}
					if(IDAisUQ){
						CLGVariableNode LX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null)+"U", "String");
						CLGLiteralNode RX = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null), "int");
						CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						OX.setLeftOperand(LX);
						OX.setRightOperand(RX);
						((CheckEquation2Context)_localctx).check2 =  OX;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
						
					}else{
						((CheckEquation2Context)_localctx).ck2Exist =  false;
					}

				}
				break;
			case 4:
				{
				setState(191); ((CheckEquation2Context)_localctx).NUMBER = match(NUMBER);
				setState(192); ((CheckEquation2Context)_localctx).equationOperator = equationOperator();
				setState(193); ((CheckEquation2Context)_localctx).ID = match(ID);

					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheckVariables((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null));
					((CheckEquation2Context)_localctx).equation =  (((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null) + " " + (((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null) + " " + (((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null);
					
					CLGLiteralNode L = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null), "int");
					CLGVariableNode R = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null), "String");
					CLGOperatorNode O = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
					O.setLeftOperand(L);
					O.setRightOperand(R);
					((CheckEquation2Context)_localctx).check =  O;
					
					boolean IDBisUQ = false;
					if(schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIspk()||schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIsun()){
						IDBisUQ = true;
					}
					if(IDBisUQ){
						CLGLiteralNode LX = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null), "String");
						CLGVariableNode RX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null)+"U", "String");
						CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						OX.setLeftOperand(LX);
						OX.setRightOperand(RX);
						((CheckEquation2Context)_localctx).check2 =  OX;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
						
					}else{
						((CheckEquation2Context)_localctx).ck2Exist =  false;
					}

				}
				break;
			case 5:
				{
				setState(196); ((CheckEquation2Context)_localctx).ID = match(ID);
				setState(197); ((CheckEquation2Context)_localctx).equationOperator = equationOperator();
				setState(198); ((CheckEquation2Context)_localctx).ID2 = match(ID);

					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheckVariables((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null));
					schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheckVariables((((CheckEquation2Context)_localctx).ID2!=null?((CheckEquation2Context)_localctx).ID2.getText():null));
					((CheckEquation2Context)_localctx).equation =  (((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null) + " " + (((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null) + " " + (((CheckEquation2Context)_localctx).ID2!=null?((CheckEquation2Context)_localctx).ID2.getText():null);
					
					CLGVariableNode L = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null), "String");
					CLGVariableNode R = new CLGVariableNode((((CheckEquation2Context)_localctx).ID2!=null?((CheckEquation2Context)_localctx).ID2.getText():null), "String");
					CLGOperatorNode O = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
					O.setLeftOperand(L);
					O.setRightOperand(R);
					((CheckEquation2Context)_localctx).check =  O;
					
					boolean IDAisUQ = false;
					boolean IDBisUQ = false;
					if(schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIspk()||schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIsun()){
						IDAisUQ = true;
					}
					if(schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID2!=null?((CheckEquation2Context)_localctx).ID2.getText():null))].getVariableIspk()||schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID2!=null?((CheckEquation2Context)_localctx).ID2.getText():null))].getVariableIsun()){
						IDBisUQ = true;
					}
					if(IDAisUQ && IDBisUQ){
						CLGVariableNode LX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null)+"U", "String");
						CLGVariableNode RX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID2!=null?((CheckEquation2Context)_localctx).ID2.getText():null)+"U", "String");
						CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						OX.setLeftOperand(LX);
						OX.setRightOperand(RX);
						((CheckEquation2Context)_localctx).check2 =  OX;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
						
					}else if(IDAisUQ && !IDBisUQ){
						CLGVariableNode LX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null)+"U", "String");
						CLGVariableNode RX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID2!=null?((CheckEquation2Context)_localctx).ID2.getText():null), "String");
						CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						OX.setLeftOperand(LX);
						OX.setRightOperand(RX);
						((CheckEquation2Context)_localctx).check2 =  OX;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
						
					}else if(!IDAisUQ && IDBisUQ){
						CLGVariableNode LX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null), "String");
						CLGVariableNode RX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID2!=null?((CheckEquation2Context)_localctx).ID2.getText():null)+"U", "String");
						CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						OX.setLeftOperand(LX);
						OX.setRightOperand(RX);
						((CheckEquation2Context)_localctx).check2 =  OX;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
						
					}else{
						((CheckEquation2Context)_localctx).ck2Exist =  false;
					}

				}
				break;
			case 6:
				{
				setState(201); ((CheckEquation2Context)_localctx).NUMBER = match(NUMBER);
				setState(202); ((CheckEquation2Context)_localctx).equationOperator = equationOperator();
				setState(203); ((CheckEquation2Context)_localctx).NUMBER2 = match(NUMBER);

					((CheckEquation2Context)_localctx).equation =  (((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null) + " " + (((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null) + " " + (((CheckEquation2Context)_localctx).NUMBER2!=null?((CheckEquation2Context)_localctx).NUMBER2.getText():null);
					
					CLGLiteralNode L = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null), "int");
					CLGLiteralNode R = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER2!=null?((CheckEquation2Context)_localctx).NUMBER2.getText():null), "int");
					CLGOperatorNode O = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
					O.setLeftOperand(L);
					O.setRightOperand(R);
					((CheckEquation2Context)_localctx).check =  O;
					
					((CheckEquation2Context)_localctx).ck2Exist =  false;

				}
				break;
			case 7:
				{
				setState(206); match(T__14);
				setState(207); ((CheckEquation2Context)_localctx).ce = checkEquation2(0);
				setState(208); match(T__4);

					((CheckEquation2Context)_localctx).equation =  "(" + ((CheckEquation2Context)_localctx).ce.equation + ")";
					
					((CheckEquation2Context)_localctx).check =  ((CheckEquation2Context)_localctx).ce.check;
					
					if(((CheckEquation2Context)_localctx).ce.ck2Exist){
						((CheckEquation2Context)_localctx).check2 =  ((CheckEquation2Context)_localctx).ce.check2;
						((CheckEquation2Context)_localctx).ck2Exist =  true;
					}else{
						((CheckEquation2Context)_localctx).ck2Exist =  false;
					}

				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(225);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(223);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						_localctx = new CheckEquation2Context(_parentctx, _parentState);
						_localctx.ce = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_checkEquation2);
						setState(213);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(214); ((CheckEquation2Context)_localctx).equationOperator = equationOperator();
						setState(215); ((CheckEquation2Context)_localctx).NUMBER = match(NUMBER);

						          	((CheckEquation2Context)_localctx).equation =  ((CheckEquation2Context)_localctx).ce.equation + " " + (((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null) + " " + (((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null);
						          	
						          	CLGOperatorNode O = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						          	CLGLiteralNode R = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null), "int");
						          	O.setLeftOperand(((CheckEquation2Context)_localctx).ce.check);
						          	O.setRightOperand(R);
						          	((CheckEquation2Context)_localctx).check =  O;
						          	
						          	if(((CheckEquation2Context)_localctx).ce.ck2Exist){
						          		CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						          		CLGLiteralNode RX = new CLGLiteralNode((((CheckEquation2Context)_localctx).NUMBER!=null?((CheckEquation2Context)_localctx).NUMBER.getText():null), "int");
						          		OX.setLeftOperand(((CheckEquation2Context)_localctx).ce.check2);
						          		OX.setRightOperand(RX);
						          		((CheckEquation2Context)_localctx).check2 =  OX;
						          		((CheckEquation2Context)_localctx).ck2Exist =  true;
						          		
						          	}
						          	else{
						          		((CheckEquation2Context)_localctx).ck2Exist =  false;
						          	}
						          
						}
						break;
					case 2:
						{
						_localctx = new CheckEquation2Context(_parentctx, _parentState);
						_localctx.ce = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_checkEquation2);
						setState(218);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(219); ((CheckEquation2Context)_localctx).equationOperator = equationOperator();
						setState(220); ((CheckEquation2Context)_localctx).ID = match(ID);

						          	schema[numbersOfTable].TC[schema[numbersOfTable].numbersOfCheck].setCheckVariables((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null));
						          	((CheckEquation2Context)_localctx).equation =  ((CheckEquation2Context)_localctx).ce.equation + " " + (((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null) + " " + (((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null);
						          	
						          	CLGOperatorNode O = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						          	CLGVariableNode R = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null), "String");
						          	O.setLeftOperand(((CheckEquation2Context)_localctx).ce.check);
						          	O.setRightOperand(R);
						          	((CheckEquation2Context)_localctx).check =  O;
						          	
						          	boolean IDBisUQ = false;
						          	if(schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIspk()||schema[numbersOfTable].TV[schema[numbersOfTable].getVariableNo((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null))].getVariableIsun()){
						          		IDBisUQ = true;
						          	}
						          	if(IDBisUQ && ((CheckEquation2Context)_localctx).ce.ck2Exist){
						          		CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						          		CLGVariableNode RX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null)+"U", "String");
						          		OX.setLeftOperand(((CheckEquation2Context)_localctx).ce.check2);
						          		OX.setRightOperand(RX);
						          		((CheckEquation2Context)_localctx).check2 =  OX;
						          		((CheckEquation2Context)_localctx).ck2Exist =  true;
						          		
						          	}else if(IDBisUQ && !((CheckEquation2Context)_localctx).ce.ck2Exist){
						          		CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						          		CLGVariableNode RX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null)+"U", "String");
						          		OX.setLeftOperand(((CheckEquation2Context)_localctx).ce.check);
						          		OX.setRightOperand(RX);
						          		((CheckEquation2Context)_localctx).check2 =  OX;
						          		((CheckEquation2Context)_localctx).ck2Exist =  true;
						          		
						          	}
						          	else if(!IDBisUQ && ((CheckEquation2Context)_localctx).ce.ck2Exist){
						          		CLGOperatorNode OX = new CLGOperatorNode((((CheckEquation2Context)_localctx).equationOperator!=null?_input.getText(((CheckEquation2Context)_localctx).equationOperator.start,((CheckEquation2Context)_localctx).equationOperator.stop):null));
						          		CLGVariableNode RX = new CLGVariableNode((((CheckEquation2Context)_localctx).ID!=null?((CheckEquation2Context)_localctx).ID.getText():null), "String");
						          		OX.setLeftOperand(((CheckEquation2Context)_localctx).ce.check2);
						          		OX.setRightOperand(RX);
						          		((CheckEquation2Context)_localctx).check2 =  OX;
						          		((CheckEquation2Context)_localctx).ck2Exist =  true;
						          		
						          	}
						          	else{
						          		((CheckEquation2Context)_localctx).ck2Exist =  false;
						          	}
						          
						}
						break;
					}
					} 
				}
				setState(227);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class CheckVariablePasserContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(sqlParser.ID, 0); }
		public CheckVariablePasserContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkVariablePasser; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterCheckVariablePasser(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitCheckVariablePasser(this);
		}
	}

	public final CheckVariablePasserContext checkVariablePasser() throws RecognitionException {
		CheckVariablePasserContext _localctx = new CheckVariablePasserContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_checkVariablePasser);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(228); match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CheckOperatorContext extends ParserRuleContext {
		public String type;
		public TerminalNode EQAUL() { return getToken(sqlParser.EQAUL, 0); }
		public TerminalNode LESSER() { return getToken(sqlParser.LESSER, 0); }
		public TerminalNode GREATEREQUAL() { return getToken(sqlParser.GREATEREQUAL, 0); }
		public TerminalNode LESSEREQUAL() { return getToken(sqlParser.LESSEREQUAL, 0); }
		public TerminalNode NOTEQUAL() { return getToken(sqlParser.NOTEQUAL, 0); }
		public TerminalNode GREATER() { return getToken(sqlParser.GREATER, 0); }
		public CheckOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_checkOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterCheckOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitCheckOperator(this);
		}
	}

	public final CheckOperatorContext checkOperator() throws RecognitionException {
		CheckOperatorContext _localctx = new CheckOperatorContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_checkOperator);
		try {
			setState(242);
			switch (_input.LA(1)) {
			case EQAUL:
				enterOuterAlt(_localctx, 1);
				{
				setState(230); match(EQAUL);

					((CheckOperatorContext)_localctx).type =  "==";

				}
				break;
			case NOTEQUAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(232); match(NOTEQUAL);

					((CheckOperatorContext)_localctx).type =  "!=";

				}
				break;
			case GREATER:
				enterOuterAlt(_localctx, 3);
				{
				setState(234); match(GREATER);

					((CheckOperatorContext)_localctx).type =  ">";

				}
				break;
			case GREATEREQUAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(236); match(GREATEREQUAL);

					((CheckOperatorContext)_localctx).type =  ">=";

				}
				break;
			case LESSER:
				enterOuterAlt(_localctx, 5);
				{
				setState(238); match(LESSER);

					((CheckOperatorContext)_localctx).type =  "<";

				}
				break;
			case LESSEREQUAL:
				enterOuterAlt(_localctx, 6);
				{
				setState(240); match(LESSEREQUAL);

					((CheckOperatorContext)_localctx).type =  "<=";

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EquationOperatorContext extends ParserRuleContext {
		public EquationOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equationOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).enterEquationOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof sqlListener ) ((sqlListener)listener).exitEquationOperator(this);
		}
	}

	public final EquationOperatorContext equationOperator() throws RecognitionException {
		EquationOperatorContext _localctx = new EquationOperatorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_equationOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__20) | (1L << T__13) | (1L << T__6) | (1L << T__3) | (1L << T__0))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 19: return checkEquation2_sempred((CheckEquation2Context)_localctx, predIndex);
		}
		return true;
	}
	private boolean checkEquation2_sempred(CheckEquation2Context _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 9);
		case 1: return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\'\u00f9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\6\2\62"+
		"\n\2\r\2\16\2\63\3\3\3\3\3\3\3\3\3\3\3\3\7\3<\n\3\f\3\16\3?\13\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4Q\n\4\3"+
		"\5\3\5\3\5\3\5\7\5W\n\5\f\5\16\5Z\13\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\177\n\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13\u008a\n\13\3\f\3\f\3\f\3\f\5\f\u0090\n"+
		"\f\3\r\3\r\3\r\3\r\5\r\u0096\n\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\5\24\u00b0\n\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\5\25\u00d6\n\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25"+
		"\u00e2\n\25\f\25\16\25\u00e5\13\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00f5\n\27\3\30\3\30\3\30\2\3"+
		"(\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\2\4\4\2\n\n\24\24"+
		"\b\2\3\3\7\7\16\16\25\25\30\30\33\33\u00fe\2\61\3\2\2\2\4\65\3\2\2\2\6"+
		"P\3\2\2\2\bR\3\2\2\2\n]\3\2\2\2\fi\3\2\2\2\16r\3\2\2\2\20u\3\2\2\2\22"+
		"~\3\2\2\2\24\u0089\3\2\2\2\26\u008f\3\2\2\2\30\u0095\3\2\2\2\32\u0097"+
		"\3\2\2\2\34\u0099\3\2\2\2\36\u009c\3\2\2\2 \u009f\3\2\2\2\"\u00a2\3\2"+
		"\2\2$\u00a5\3\2\2\2&\u00af\3\2\2\2(\u00d5\3\2\2\2*\u00e6\3\2\2\2,\u00f4"+
		"\3\2\2\2.\u00f6\3\2\2\2\60\62\5\4\3\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61"+
		"\3\2\2\2\63\64\3\2\2\2\64\3\3\2\2\2\65\66\7\31\2\2\66\67\7\5\2\2\678\5"+
		"\16\b\289\b\3\1\29=\7\r\2\2:<\5\6\4\2;:\3\2\2\2<?\3\2\2\2=;\3\2\2\2=>"+
		"\3\2\2\2>@\3\2\2\2?=\3\2\2\2@A\7\27\2\2AB\7\b\2\2BC\b\3\1\2C\5\3\2\2\2"+
		"DQ\5\b\5\2EF\5\b\5\2FG\7\17\2\2GQ\3\2\2\2HQ\5\n\6\2IJ\5\n\6\2JK\7\17\2"+
		"\2KQ\3\2\2\2LQ\5\f\7\2MN\5\f\7\2NO\7\17\2\2OQ\3\2\2\2PD\3\2\2\2PE\3\2"+
		"\2\2PH\3\2\2\2PI\3\2\2\2PL\3\2\2\2PM\3\2\2\2Q\7\3\2\2\2RS\5\20\t\2ST\b"+
		"\5\1\2TX\5\22\n\2UW\5\24\13\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2"+
		"Y[\3\2\2\2ZX\3\2\2\2[\\\b\5\1\2\\\t\3\2\2\2]^\7\4\2\2^_\7\f\2\2_`\7\r"+
		"\2\2`a\5\34\17\2ab\7\27\2\2bc\7\6\2\2cd\5\36\20\2de\7\r\2\2ef\5 \21\2"+
		"fg\7\27\2\2gh\b\6\1\2h\13\3\2\2\2ij\7\23\2\2jk\b\7\1\2kl\7\r\2\2lm\5\""+
		"\22\2mn\5,\27\2no\5$\23\2op\7\27\2\2pq\b\7\1\2q\r\3\2\2\2rs\7&\2\2st\b"+
		"\b\1\2t\17\3\2\2\2uv\7&\2\2vw\b\t\1\2w\21\3\2\2\2xy\7\34\2\2y\177\b\n"+
		"\1\2z{\7\35\2\2{\177\b\n\1\2|}\7\36\2\2}\177\b\n\1\2~x\3\2\2\2~z\3\2\2"+
		"\2~|\3\2\2\2\177\23\3\2\2\2\u0080\u0081\5\26\f\2\u0081\u0082\b\13\1\2"+
		"\u0082\u008a\3\2\2\2\u0083\u0084\5\30\r\2\u0084\u0085\b\13\1\2\u0085\u008a"+
		"\3\2\2\2\u0086\u0087\5\32\16\2\u0087\u0088\b\13\1\2\u0088\u008a\3\2\2"+
		"\2\u0089\u0080\3\2\2\2\u0089\u0083\3\2\2\2\u0089\u0086\3\2\2\2\u008a\25"+
		"\3\2\2\2\u008b\u008c\7\21\2\2\u008c\u0090\7\f\2\2\u008d\u008e\7\20\2\2"+
		"\u008e\u0090\7\22\2\2\u008f\u008b\3\2\2\2\u008f\u008d\3\2\2\2\u0090\27"+
		"\3\2\2\2\u0091\u0092\7\t\2\2\u0092\u0096\7\26\2\2\u0093\u0094\7\32\2\2"+
		"\u0094\u0096\7\13\2\2\u0095\u0091\3\2\2\2\u0095\u0093\3\2\2\2\u0096\31"+
		"\3\2\2\2\u0097\u0098\t\2\2\2\u0098\33\3\2\2\2\u0099\u009a\7&\2\2\u009a"+
		"\u009b\b\17\1\2\u009b\35\3\2\2\2\u009c\u009d\7&\2\2\u009d\u009e\b\20\1"+
		"\2\u009e\37\3\2\2\2\u009f\u00a0\7&\2\2\u00a0\u00a1\b\21\1\2\u00a1!\3\2"+
		"\2\2\u00a2\u00a3\5&\24\2\u00a3\u00a4\b\22\1\2\u00a4#\3\2\2\2\u00a5\u00a6"+
		"\5&\24\2\u00a6\u00a7\b\23\1\2\u00a7%\3\2\2\2\u00a8\u00a9\7&\2\2\u00a9"+
		"\u00b0\b\24\1\2\u00aa\u00ab\7%\2\2\u00ab\u00b0\b\24\1\2\u00ac\u00ad\5"+
		"(\25\2\u00ad\u00ae\b\24\1\2\u00ae\u00b0\3\2\2\2\u00af\u00a8\3\2\2\2\u00af"+
		"\u00aa\3\2\2\2\u00af\u00ac\3\2\2\2\u00b0\'\3\2\2\2\u00b1\u00b2\b\25\1"+
		"\2\u00b2\u00b3\7%\2\2\u00b3\u00b4\5.\30\2\u00b4\u00b5\5(\25\n\u00b5\u00b6"+
		"\b\25\1\2\u00b6\u00d6\3\2\2\2\u00b7\u00b8\7&\2\2\u00b8\u00b9\5.\30\2\u00b9"+
		"\u00ba\5(\25\b\u00ba\u00bb\b\25\1\2\u00bb\u00d6\3\2\2\2\u00bc\u00bd\7"+
		"&\2\2\u00bd\u00be\5.\30\2\u00be\u00bf\7%\2\2\u00bf\u00c0\b\25\1\2\u00c0"+
		"\u00d6\3\2\2\2\u00c1\u00c2\7%\2\2\u00c2\u00c3\5.\30\2\u00c3\u00c4\7&\2"+
		"\2\u00c4\u00c5\b\25\1\2\u00c5\u00d6\3\2\2\2\u00c6\u00c7\7&\2\2\u00c7\u00c8"+
		"\5.\30\2\u00c8\u00c9\7&\2\2\u00c9\u00ca\b\25\1\2\u00ca\u00d6\3\2\2\2\u00cb"+
		"\u00cc\7%\2\2\u00cc\u00cd\5.\30\2\u00cd\u00ce\7%\2\2\u00ce\u00cf\b\25"+
		"\1\2\u00cf\u00d6\3\2\2\2\u00d0\u00d1\7\r\2\2\u00d1\u00d2\5(\25\2\u00d2"+
		"\u00d3\7\27\2\2\u00d3\u00d4\b\25\1\2\u00d4\u00d6\3\2\2\2\u00d5\u00b1\3"+
		"\2\2\2\u00d5\u00b7\3\2\2\2\u00d5\u00bc\3\2\2\2\u00d5\u00c1\3\2\2\2\u00d5"+
		"\u00c6\3\2\2\2\u00d5\u00cb\3\2\2\2\u00d5\u00d0\3\2\2\2\u00d6\u00e3\3\2"+
		"\2\2\u00d7\u00d8\f\13\2\2\u00d8\u00d9\5.\30\2\u00d9\u00da\7%\2\2\u00da"+
		"\u00db\b\25\1\2\u00db\u00e2\3\2\2\2\u00dc\u00dd\f\t\2\2\u00dd\u00de\5"+
		".\30\2\u00de\u00df\7&\2\2\u00df\u00e0\b\25\1\2\u00e0\u00e2\3\2\2\2\u00e1"+
		"\u00d7\3\2\2\2\u00e1\u00dc\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2"+
		"\2\2\u00e3\u00e4\3\2\2\2\u00e4)\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7"+
		"\7&\2\2\u00e7+\3\2\2\2\u00e8\u00e9\7\37\2\2\u00e9\u00f5\b\27\1\2\u00ea"+
		"\u00eb\7 \2\2\u00eb\u00f5\b\27\1\2\u00ec\u00ed\7!\2\2\u00ed\u00f5\b\27"+
		"\1\2\u00ee\u00ef\7\"\2\2\u00ef\u00f5\b\27\1\2\u00f0\u00f1\7#\2\2\u00f1"+
		"\u00f5\b\27\1\2\u00f2\u00f3\7$\2\2\u00f3\u00f5\b\27\1\2\u00f4\u00e8\3"+
		"\2\2\2\u00f4\u00ea\3\2\2\2\u00f4\u00ec\3\2\2\2\u00f4\u00ee\3\2\2\2\u00f4"+
		"\u00f0\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f5-\3\2\2\2\u00f6\u00f7\t\3\2\2"+
		"\u00f7/\3\2\2\2\17\63=PX~\u0089\u008f\u0095\u00af\u00d5\u00e1\u00e3\u00f4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}