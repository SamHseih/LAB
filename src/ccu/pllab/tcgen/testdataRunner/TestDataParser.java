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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TestDataParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__14=1, T__13=2, T__12=3, T__11=4, T__10=5, T__9=6, T__8=7, T__7=8, T__6=9, 
		T__5=10, T__4=11, T__3=12, T__2=13, T__1=14, T__0=15, STRING=16, REAL=17, 
		BOOL=18, INTEGER=19, NUMBER=20, PUNCT=21, PUNCT2=22, CNTRL=23, LETTER=24, 
		WS=25;
	public static final String[] tokenNames = {
		"<INVALID>", "'OBJ'", "'ARG'", "'[[]]'", "'[]('", "'_POST'", "'EXCEPTION'", 
		"'['", "'RETVAL'", "'_PRE'", "']'", "'='", "'[]'", "': '", "')'", "','", 
		"STRING", "REAL", "BOOL", "INTEGER", "NUMBER", "PUNCT", "PUNCT2", "CNTRL", 
		"LETTER", "WS"
	};
	public static final int
		RULE_testDataList = 0, RULE_testData = 1, RULE_exceptionState = 2, RULE_itemState = 3, 
		RULE_itemName = 4, RULE_time = 5, RULE_elms = 6, RULE_objElmList = 7, 
		RULE_objElm = 8, RULE_typeData = 9, RULE_list = 10, RULE_array = 11, RULE_realBoundList = 12;
	public static final String[] ruleNames = {
		"testDataList", "testData", "exceptionState", "itemState", "itemName", 
		"time", "elms", "objElmList", "objElm", "typeData", "list", "array", "realBoundList"
	};

	@Override
	public String getGrammarFileName() { return "TestData.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TestDataParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TestDataListContext extends ParserRuleContext {
		public VariableType type;
		public String className;
		public String methodName;
		public List<MethodTestData> methodTestData;
		public TestDataContext data;
		public List<TerminalNode> INTEGER() { return getTokens(TestDataParser.INTEGER); }
		public TestDataContext testData(int i) {
			return getRuleContext(TestDataContext.class,i);
		}
		public TerminalNode INTEGER(int i) {
			return getToken(TestDataParser.INTEGER, i);
		}
		public List<TestDataContext> testData() {
			return getRuleContexts(TestDataContext.class);
		}
		public TestDataListContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TestDataListContext(ParserRuleContext parent, int invokingState, VariableType type, String className, String methodName) {
			super(parent, invokingState);
			this.type = type;
			this.className = className;
			this.methodName = methodName;
		}
		@Override public int getRuleIndex() { return RULE_testDataList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterTestDataList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitTestDataList(this);
		}
	}

	public final TestDataListContext testDataList(VariableType type,String className,String methodName) throws RecognitionException {
		TestDataListContext _localctx = new TestDataListContext(_ctx, getState(), type, className, methodName);
		enterRule(_localctx, 0, RULE_testDataList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((TestDataListContext)_localctx).methodTestData =  new ArrayList<MethodTestData>();
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==INTEGER) {
				{
				{
				setState(27); match(INTEGER);
				setState(28); match(T__2);
				setState(29); ((TestDataListContext)_localctx).data = testData( type, className, methodName);

				 			(_localctx.methodTestData).add(((TestDataListContext)_localctx).data.data);
				 			
				 		
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class TestDataContext extends ParserRuleContext {
		public VariableType type;
		public String className;
		public String methodName;
		public MethodTestData data;
		public ItemStateContext item;
		public ExceptionStateContext exitem;
		public List<ItemStateContext> itemState() {
			return getRuleContexts(ItemStateContext.class);
		}
		public ItemStateContext itemState(int i) {
			return getRuleContext(ItemStateContext.class,i);
		}
		public ExceptionStateContext exceptionState() {
			return getRuleContext(ExceptionStateContext.class,0);
		}
		public TestDataContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TestDataContext(ParserRuleContext parent, int invokingState, VariableType type, String className, String methodName) {
			super(parent, invokingState);
			this.type = type;
			this.className = className;
			this.methodName = methodName;
		}
		@Override public int getRuleIndex() { return RULE_testData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterTestData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitTestData(this);
		}
	}

	public final TestDataContext testData(VariableType type,String className,String methodName) throws RecognitionException {
		TestDataContext _localctx = new TestDataContext(_ctx, getState(), type, className, methodName);
		enterRule(_localctx, 2, RULE_testData);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			ArrayList<ArrayList<TypeTestData>> dataList = new ArrayList<ArrayList<TypeTestData>>(); 
			setState(42); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38); ((TestDataContext)_localctx).item = itemState(type,methodName);

				 			(dataList).add(((TestDataContext)_localctx).item.item);
				 		
				setState(40); match(T__0);
				}
				}
				setState(44); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__13) | (1L << T__7))) != 0) );
			setState(46); ((TestDataContext)_localctx).exitem = exceptionState();

			 	 	dataList.add(((TestDataContext)_localctx).exitem.item);
			 	 	((TestDataContext)_localctx).data =  new MethodTestData(className,methodName,dataList);
			 	 
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

	public static class ExceptionStateContext extends ParserRuleContext {
		public ArrayList<TypeTestData> item;
		public Token exStr;
		public TerminalNode STRING() { return getToken(TestDataParser.STRING, 0); }
		public ExceptionStateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exceptionState; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterExceptionState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitExceptionState(this);
		}
	}

	public final ExceptionStateContext exceptionState() throws RecognitionException {
		ExceptionStateContext _localctx = new ExceptionStateContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_exceptionState);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49); match(T__9);
			setState(50); match(T__4);
			setState(56);
			switch (_input.LA(1)) {
			case T__3:
				{
				setState(51); match(T__3);
				}
				break;
			case T__12:
				{
				setState(52); match(T__12);
				}
				break;
			case T__8:
				{
				setState(53); match(T__8);
				setState(54); ((ExceptionStateContext)_localctx).exStr = match(STRING);
				setState(55); match(T__5);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}

			 		ExceptionTestData exdata = null;
			 		((ExceptionStateContext)_localctx).item =  new ArrayList<TypeTestData>();
			 		if(((ExceptionStateContext)_localctx).exStr != null){
			 			exdata = new ExceptionTestData((((ExceptionStateContext)_localctx).exStr!=null?((ExceptionStateContext)_localctx).exStr.getText():null));
			 			(_localctx.item).add(exdata);
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

	public static class ItemStateContext extends ParserRuleContext {
		public VariableType type;
		public String method;
		public ArrayList<TypeTestData> item;
		public ItemNameContext itemclass;
		public ElmsContext ele;
		public ElmsContext elms() {
			return getRuleContext(ElmsContext.class,0);
		}
		public ItemNameContext itemName() {
			return getRuleContext(ItemNameContext.class,0);
		}
		public ItemStateContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ItemStateContext(ParserRuleContext parent, int invokingState, VariableType type, String method) {
			super(parent, invokingState);
			this.type = type;
			this.method = method;
		}
		@Override public int getRuleIndex() { return RULE_itemState; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterItemState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitItemState(this);
		}
	}

	public final ItemStateContext itemState(VariableType type,String method) throws RecognitionException {
		ItemStateContext _localctx = new ItemStateContext(_ctx, getState(), type, method);
		enterRule(_localctx, 6, RULE_itemState);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60); ((ItemStateContext)_localctx).itemclass = itemName();
			setState(61); match(T__4);
			setState(62); ((ItemStateContext)_localctx).ele = elms( type, method, ((ItemStateContext)_localctx).itemclass.itemClass);

			 		if(((ItemStateContext)_localctx).itemclass.itemClass.equals("obj")){
			 			TypeTestData userTestData =  new UserDefinedTypeTestData(((ItemStateContext)_localctx).ele.eleDataList, type);
			 			((ItemStateContext)_localctx).item =  new ArrayList<TypeTestData>();
			 			_localctx.item.add(userTestData);
			 		}else{
			 			((ItemStateContext)_localctx).item =  ((ItemStateContext)_localctx).ele.eleDataList;
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

	public static class ItemNameContext extends ParserRuleContext {
		public String itemClass;
		public Token objStr;
		public Token argStr;
		public TimeContext time() {
			return getRuleContext(TimeContext.class,0);
		}
		public ItemNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_itemName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterItemName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitItemName(this);
		}
	}

	public final ItemNameContext itemName() throws RecognitionException {
		ItemNameContext _localctx = new ItemNameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_itemName);
		try {
			setState(74);
			switch (_input.LA(1)) {
			case T__14:
			case T__13:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				switch (_input.LA(1)) {
				case T__14:
					{
					setState(65); ((ItemNameContext)_localctx).objStr = match(T__14);
					((ItemNameContext)_localctx).itemClass =  "obj";
					}
					break;
				case T__13:
					{
					setState(67); ((ItemNameContext)_localctx).argStr = match(T__13);
					((ItemNameContext)_localctx).itemClass =  "arg";
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(71); time();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(72); match(T__7);
				 ((ItemNameContext)_localctx).itemClass =  "ret";
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

	public static class TimeContext extends ParserRuleContext {
		public TimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitTime(this);
		}
	}

	public final TimeContext time() throws RecognitionException {
		TimeContext _localctx = new TimeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_time);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_la = _input.LA(1);
			if ( !(_la==T__10 || _la==T__6) ) {
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

	public static class ElmsContext extends ParserRuleContext {
		public VariableType type;
		public String method;
		public String inputType;
		public ArrayList<TypeTestData> eleDataList;
		public ObjElmListContext ele;
		public ObjElmListContext objElmList() {
			return getRuleContext(ObjElmListContext.class,0);
		}
		public ElmsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ElmsContext(ParserRuleContext parent, int invokingState, VariableType type, String method, String inputType) {
			super(parent, invokingState);
			this.type = type;
			this.method = method;
			this.inputType = inputType;
		}
		@Override public int getRuleIndex() { return RULE_elms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterElms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitElms(this);
		}
	}

	public final ElmsContext elms(VariableType type,String method,String inputType) throws RecognitionException {
		ElmsContext _localctx = new ElmsContext(_ctx, getState(), type, method, inputType);
		enterRule(_localctx, 12, RULE_elms);
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ElmsContext)_localctx).eleDataList =  new ArrayList<TypeTestData>();
				 ArrayList<VariableType> elemTypeList = new ArrayList<VariableType>(); 
					if(type instanceof UserDefinedType){
						if(inputType.equals("obj")){
							for( VariableToken var : type.getSymbolTable().getAttribute().values()){
								elemTypeList.add(var.getType());
							}
						}else if(inputType.equals("arg")){
							for( VariableToken var : type.getSymbolTable().getMethod().get(method).getArgument().values()){
								elemTypeList.add(var.getType());
							}
						}else if(inputType.equals("ret")){
							elemTypeList.add( type.getSymbolTable().getMethod().get(method).getReturnType());
						}
					}else{
						elemTypeList.add(type);
					}
					
				
			setState(85);
			switch (_input.LA(1)) {
			case T__3:
				{
				setState(79); match(T__3);
				}
				break;
			case T__8:
				{
				setState(80); match(T__8);
				setState(81); ((ElmsContext)_localctx).ele = objElmList(type,method,inputType,elemTypeList);
				setState(82); match(T__5);

				 		((ElmsContext)_localctx).eleDataList =  ((ElmsContext)_localctx).ele.testdata;
				 	
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ObjElmListContext extends ParserRuleContext {
		public VariableType type;
		public String method;
		public String inputType;
		public ArrayList<VariableType> elemTypeList;
		public ArrayList<TypeTestData> testdata;
		public ObjElmContext ele;
		public ObjElmContext ele1;
		public List<ObjElmContext> objElm() {
			return getRuleContexts(ObjElmContext.class);
		}
		public ObjElmContext objElm(int i) {
			return getRuleContext(ObjElmContext.class,i);
		}
		public ObjElmListContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ObjElmListContext(ParserRuleContext parent, int invokingState, VariableType type, String method, String inputType, ArrayList<VariableType> elemTypeList) {
			super(parent, invokingState);
			this.type = type;
			this.method = method;
			this.inputType = inputType;
			this.elemTypeList = elemTypeList;
		}
		@Override public int getRuleIndex() { return RULE_objElmList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterObjElmList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitObjElmList(this);
		}
	}

	public final ObjElmListContext objElmList(VariableType type,String method,String inputType,ArrayList<VariableType> elemTypeList) throws RecognitionException {
		ObjElmListContext _localctx = new ObjElmListContext(_ctx, getState(), type, method, inputType, elemTypeList);
		enterRule(_localctx, 14, RULE_objElmList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			int eleCount = 0; 
				((ObjElmListContext)_localctx).testdata =  new ArrayList<TypeTestData>();
				
			setState(88); ((ObjElmListContext)_localctx).ele = objElm(elemTypeList.get(eleCount));

			 		(_localctx.testdata).add(((ObjElmListContext)_localctx).ele.objElmTestdata);
			 		if(elemTypeList.size() > eleCount + 1)
			 			eleCount++;
			 	
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(90); match(T__0);
				setState(91); ((ObjElmListContext)_localctx).ele1 = objElm(elemTypeList.get(eleCount));

				 			(_localctx.testdata).add(((ObjElmListContext)_localctx).ele1.objElmTestdata);
				 			if(elemTypeList.size() > eleCount + 1)
				 				eleCount++;
				 		
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class ObjElmContext extends ParserRuleContext {
		public VariableType type;
		public TypeTestData objElmTestdata;
		public TypeDataContext typedata;
		public TypeDataContext typeData() {
			return getRuleContext(TypeDataContext.class,0);
		}
		public ObjElmContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ObjElmContext(ParserRuleContext parent, int invokingState, VariableType type) {
			super(parent, invokingState);
			this.type = type;
		}
		@Override public int getRuleIndex() { return RULE_objElm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterObjElm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitObjElm(this);
		}
	}

	public final ObjElmContext objElm(VariableType type) throws RecognitionException {
		ObjElmContext _localctx = new ObjElmContext(_ctx, getState(), type);
		enterRule(_localctx, 16, RULE_objElm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99); ((ObjElmContext)_localctx).typedata = typeData(type);
			((ObjElmContext)_localctx).objElmTestdata =  ((ObjElmContext)_localctx).typedata.data;
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

	public static class TypeDataContext extends ParserRuleContext {
		public VariableType type;
		public TypeTestData data;
		public Token bool;
		public Token intstr;
		public RealBoundListContext realBound;
		public Token str;
		public ArrayContext arrayData;
		public ListContext listTestData;
		public TerminalNode BOOL() { return getToken(TestDataParser.BOOL, 0); }
		public TerminalNode INTEGER() { return getToken(TestDataParser.INTEGER, 0); }
		public RealBoundListContext realBoundList() {
			return getRuleContext(RealBoundListContext.class,0);
		}
		public TerminalNode STRING() { return getToken(TestDataParser.STRING, 0); }
		public ListContext list() {
			return getRuleContext(ListContext.class,0);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public TypeDataContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TypeDataContext(ParserRuleContext parent, int invokingState, VariableType type) {
			super(parent, invokingState);
			this.type = type;
		}
		@Override public int getRuleIndex() { return RULE_typeData; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterTypeData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitTypeData(this);
		}
	}

	public final TypeDataContext typeData(VariableType type) throws RecognitionException {
		TypeDataContext _localctx = new TypeDataContext(_ctx, getState(), type);
		enterRule(_localctx, 18, RULE_typeData);
		try {
			setState(120);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102); match(T__3);
				((TypeDataContext)_localctx).data =  new VoidTypeTestData();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(104); ((TypeDataContext)_localctx).bool = match(BOOL);
				 ((TypeDataContext)_localctx).data =  new BooleanTypeTestData((((TypeDataContext)_localctx).bool!=null?((TypeDataContext)_localctx).bool.getText():null));
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(106); ((TypeDataContext)_localctx).intstr = match(INTEGER);
				((TypeDataContext)_localctx).data =  new IntTypeTestData((((TypeDataContext)_localctx).intstr!=null?((TypeDataContext)_localctx).intstr.getText():null));
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(108); ((TypeDataContext)_localctx).realBound = realBoundList();
				((TypeDataContext)_localctx).data =  new RealTypeTestData((((TypeDataContext)_localctx).realBound.realBound).get(0),(((TypeDataContext)_localctx).realBound.realBound).get(1));
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(111); ((TypeDataContext)_localctx).str = match(STRING);
				 ((TypeDataContext)_localctx).data =  new StringTypeTestData((((TypeDataContext)_localctx).str!=null?((TypeDataContext)_localctx).str.getText():null));
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(113); ((TypeDataContext)_localctx).arrayData = array(type);
				((TypeDataContext)_localctx).data =  new ArrayTypeTestData(((TypeDataContext)_localctx).arrayData.arrayTestdata,type);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(116); ((TypeDataContext)_localctx).listTestData = list(type);

				 		if(type instanceof ArrayListType){
				 			((TypeDataContext)_localctx).data =  new ArrayListTypeTestData(((TypeDataContext)_localctx).listTestData.listdata,type);
				 		}else{
				 			((TypeDataContext)_localctx).data =  new UserDefinedTypeTestData(((TypeDataContext)_localctx).listTestData.listdata,type);
				 		}
				 	
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				((TypeDataContext)_localctx).data =  new VoidTypeTestData();
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

	public static class ListContext extends ParserRuleContext {
		public VariableType type;
		public ArrayList<TypeTestData> listdata;
		public ObjElmListContext objlist;
		public ObjElmListContext objElmList() {
			return getRuleContext(ObjElmListContext.class,0);
		}
		public ListContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ListContext(ParserRuleContext parent, int invokingState, VariableType type) {
			super(parent, invokingState);
			this.type = type;
		}
		@Override public int getRuleIndex() { return RULE_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitList(this);
		}
	}

	public final ListContext list(VariableType type) throws RecognitionException {
		ListContext _localctx = new ListContext(_ctx, getState(), type);
		enterRule(_localctx, 20, RULE_list);
		try {
			enterOuterAlt(_localctx, 1);
			{

				VariableType elemType = (type instanceof ArrayListType)? ((ArrayListType) type).getElement(): type ;
				 ArrayList<VariableType> elemTypeList = new ArrayList<VariableType>(); 
					if(elemType instanceof UserDefinedType){
						
							for( VariableToken var : elemType.getSymbolTable().getAttribute().values()){
								elemTypeList.add(var.getType());
							}
					}else{
						elemTypeList.add(elemType);
					}
					
				
			setState(123); match(T__8);
			setState(124); ((ListContext)_localctx).objlist = objElmList(elemType, "", "obj", elemTypeList);
			setState(125); match(T__5);
			((ListContext)_localctx).listdata =  ((ListContext)_localctx).objlist.testdata;
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

	public static class ArrayContext extends ParserRuleContext {
		public VariableType type;
		public ArrayList<TypeTestData> arrayTestdata;
		public ObjElmListContext elmList;
		public ObjElmListContext objElmList() {
			return getRuleContext(ObjElmListContext.class,0);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ArrayContext(ParserRuleContext parent, int invokingState, VariableType type) {
			super(parent, invokingState);
			this.type = type;
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array(VariableType type) throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState(), type);
		enterRule(_localctx, 22, RULE_array);
		try {
			enterOuterAlt(_localctx, 1);
			{

				VariableType elemType = (type instanceof ArrayType)? ((ArrayType) type).getElement(): type ;
				 ArrayList<VariableType> elemTypeList = new ArrayList<VariableType>(); 
					if(elemType instanceof UserDefinedType){
						
							for( VariableToken var : elemType.getSymbolTable().getAttribute().values()){
								elemTypeList.add(var.getType());
							}
					}else{
						elemTypeList.add(elemType);
					}
					
				
			setState(129); match(T__11);
			setState(130); ((ArrayContext)_localctx).elmList = objElmList(elemType, "", "obj",elemTypeList);
			setState(131); match(T__1);
			 ((ArrayContext)_localctx).arrayTestdata =  ((ArrayContext)_localctx).elmList.testdata;
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

	public static class RealBoundListContext extends ParserRuleContext {
		public ArrayList<String> realBound;
		public Token low;
		public Token high;
		public List<TerminalNode> REAL() { return getTokens(TestDataParser.REAL); }
		public TerminalNode REAL(int i) {
			return getToken(TestDataParser.REAL, i);
		}
		public RealBoundListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_realBoundList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).enterRealBoundList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TestDataListener ) ((TestDataListener)listener).exitRealBoundList(this);
		}
	}

	public final RealBoundListContext realBoundList() throws RecognitionException {
		RealBoundListContext _localctx = new RealBoundListContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_realBoundList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134); match(T__8);
			setState(135); ((RealBoundListContext)_localctx).low = match(REAL);
			setState(136); match(T__0);
			setState(137); ((RealBoundListContext)_localctx).high = match(REAL);
			setState(138); match(T__5);

					((RealBoundListContext)_localctx).realBound =  new ArrayList<String>();
					(_localctx.realBound).add((((RealBoundListContext)_localctx).low!=null?((RealBoundListContext)_localctx).low.getText():null));
					(_localctx.realBound).add((((RealBoundListContext)_localctx).high!=null?((RealBoundListContext)_localctx).high.getText():null));
				
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\33\u0090\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\3\2\3\2\3\2\7\2#\n\2\f\2\16"+
		"\2&\13\2\3\3\3\3\3\3\3\3\3\3\6\3-\n\3\r\3\16\3.\3\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\5\4;\n\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3"+
		"\6\5\6H\n\6\3\6\3\6\3\6\5\6M\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5"+
		"\bX\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\ta\n\t\f\t\16\td\13\t\3\n\3\n\3"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\5\13{\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\2\2\17\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\2\3\4\2\7\7\13\13\u0091\2\34\3\2\2\2\4\'\3\2\2"+
		"\2\6\63\3\2\2\2\b>\3\2\2\2\nL\3\2\2\2\fN\3\2\2\2\16P\3\2\2\2\20Y\3\2\2"+
		"\2\22e\3\2\2\2\24z\3\2\2\2\26|\3\2\2\2\30\u0082\3\2\2\2\32\u0088\3\2\2"+
		"\2\34$\b\2\1\2\35\36\7\25\2\2\36\37\7\17\2\2\37 \5\4\3\2 !\b\2\1\2!#\3"+
		"\2\2\2\"\35\3\2\2\2#&\3\2\2\2$\"\3\2\2\2$%\3\2\2\2%\3\3\2\2\2&$\3\2\2"+
		"\2\',\b\3\1\2()\5\b\5\2)*\b\3\1\2*+\7\21\2\2+-\3\2\2\2,(\3\2\2\2-.\3\2"+
		"\2\2.,\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\5\6\4\2\61\62\b\3\1\2\62\5"+
		"\3\2\2\2\63\64\7\b\2\2\64:\7\r\2\2\65;\7\16\2\2\66;\7\5\2\2\678\7\t\2"+
		"\289\7\22\2\29;\7\f\2\2:\65\3\2\2\2:\66\3\2\2\2:\67\3\2\2\2;<\3\2\2\2"+
		"<=\b\4\1\2=\7\3\2\2\2>?\5\n\6\2?@\7\r\2\2@A\5\16\b\2AB\b\5\1\2B\t\3\2"+
		"\2\2CD\7\3\2\2DH\b\6\1\2EF\7\4\2\2FH\b\6\1\2GC\3\2\2\2GE\3\2\2\2HI\3\2"+
		"\2\2IM\5\f\7\2JK\7\n\2\2KM\b\6\1\2LG\3\2\2\2LJ\3\2\2\2M\13\3\2\2\2NO\t"+
		"\2\2\2O\r\3\2\2\2PW\b\b\1\2QX\7\16\2\2RS\7\t\2\2ST\5\20\t\2TU\7\f\2\2"+
		"UV\b\b\1\2VX\3\2\2\2WQ\3\2\2\2WR\3\2\2\2X\17\3\2\2\2YZ\b\t\1\2Z[\5\22"+
		"\n\2[b\b\t\1\2\\]\7\21\2\2]^\5\22\n\2^_\b\t\1\2_a\3\2\2\2`\\\3\2\2\2a"+
		"d\3\2\2\2b`\3\2\2\2bc\3\2\2\2c\21\3\2\2\2db\3\2\2\2ef\5\24\13\2fg\b\n"+
		"\1\2g\23\3\2\2\2hi\7\16\2\2i{\b\13\1\2jk\7\24\2\2k{\b\13\1\2lm\7\25\2"+
		"\2m{\b\13\1\2no\5\32\16\2op\b\13\1\2p{\3\2\2\2qr\7\22\2\2r{\b\13\1\2s"+
		"t\5\30\r\2tu\b\13\1\2u{\3\2\2\2vw\5\26\f\2wx\b\13\1\2x{\3\2\2\2y{\b\13"+
		"\1\2zh\3\2\2\2zj\3\2\2\2zl\3\2\2\2zn\3\2\2\2zq\3\2\2\2zs\3\2\2\2zv\3\2"+
		"\2\2zy\3\2\2\2{\25\3\2\2\2|}\b\f\1\2}~\7\t\2\2~\177\5\20\t\2\177\u0080"+
		"\7\f\2\2\u0080\u0081\b\f\1\2\u0081\27\3\2\2\2\u0082\u0083\b\r\1\2\u0083"+
		"\u0084\7\6\2\2\u0084\u0085\5\20\t\2\u0085\u0086\7\20\2\2\u0086\u0087\b"+
		"\r\1\2\u0087\31\3\2\2\2\u0088\u0089\7\t\2\2\u0089\u008a\7\23\2\2\u008a"+
		"\u008b\7\21\2\2\u008b\u008c\7\23\2\2\u008c\u008d\7\f\2\2\u008d\u008e\b"+
		"\16\1\2\u008e\33\3\2\2\2\n$.:GLWbz";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}