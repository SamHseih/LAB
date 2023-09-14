// Generated from Ocl.g4 by ANTLR 4.4
package ccu.pllab.tcgen.oclRunner;
//�o�����O�N�Ojava���Yimport�������A�]��.g4�ɻPOclParser.java�O�s�����Y�A�ҥH���ઽ���bOclParser.java�W�Kimport�A�@���s�ɴN�|�Q�~���A�]���p�G�nimport���ɭԡA�b@header���Y�[
	import ccu.pllab.tcgen.AbstractSyntaxTree.*;
	import ccu.pllab.tcgen.SymbolTable.*;
	import ccu.pllab.tcgen.AbstractType.*;
	import ccu.pllab.tcgen.CBTCGUtility.Utility;
	import ccu.pllab.tcgen.exe.main.*;
	import java.util.ArrayList;
	import java.util.Set;
	

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class OclParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__51=1, T__50=2, T__49=3, T__48=4, T__47=5, T__46=6, T__45=7, T__44=8, 
		T__43=9, T__42=10, T__41=11, T__40=12, T__39=13, T__38=14, T__37=15, T__36=16, 
		T__35=17, T__34=18, T__33=19, T__32=20, T__31=21, T__30=22, T__29=23, 
		T__28=24, T__27=25, T__26=26, T__25=27, T__24=28, T__23=29, T__22=30, 
		T__21=31, T__20=32, T__19=33, T__18=34, T__17=35, T__16=36, T__15=37, 
		T__14=38, T__13=39, T__12=40, T__11=41, T__10=42, T__9=43, T__8=44, T__7=45, 
		T__6=46, T__5=47, T__4=48, T__3=49, T__2=50, T__1=51, T__0=52, NAME=53, 
		INTEGER=54, STRING=55, WHITESPACE=56;
	public static final String[] tokenNames = {
		"<INVALID>", "'endif'", "'new'", "'{'", "'..'", "'IntegerRange'", "'::'", 
		"'='", "'subsequence('", "'[]'", "'implies'", "'('", "'package'", "'context'", 
		"','", "'false'", "'prepend('", "'>='", "'<'", "']'", "'pre'", "'@'", 
		"'<>'", "'let'", "'then'", "'+'", "'/'", "'ArrayList<'", "'true'", "';'", 
		"'}'", "'if'", "'<='", "'inv'", "'post'", "'*'", "'endpackage'", "'.'", 
		"'->'", "':'", "'['", "'|'", "'=='", "'>'", "'xor'", "'or'", "'else'", 
		"'in'", "'iterate('", "')'", "'and'", "'not'", "'-'", "NAME", "INTEGER", 
		"STRING", "WHITESPACE"
	};
	public static final int
		RULE_packageDeclarationCS = 0, RULE_packageName = 1, RULE_pathName = 2, 
		RULE_oclExpressions = 3, RULE_constraints = 4, RULE_constraint = 5, RULE_content = 6, 
		RULE_contextDeclaration = 7, RULE_stereotype = 8, RULE_operationContext = 9, 
		RULE_classifierContext = 10, RULE_operationName = 11, RULE_returnType = 12, 
		RULE_formalParameterList = 13, RULE_formalParameterLists = 14, RULE_typeSpecifier = 15, 
		RULE_collectionType = 16, RULE_oclExpression = 17, RULE_localVariableList = 18, 
		RULE_localVariableLists = 19, RULE_letExpression = 20, RULE_expression = 21, 
		RULE_logicalExpression = 22, RULE_relationalExpressions = 23, RULE_logicalOperator = 24, 
		RULE_relationalExpression = 25, RULE_relationalOperator = 26, RULE_additiveExpression = 27, 
		RULE_multiplicativeExpressions = 28, RULE_addOperator = 29, RULE_multiplicativeExpression = 30, 
		RULE_unaryExpressions = 31, RULE_multiplyOperator = 32, RULE_unaryExpression = 33, 
		RULE_unaryOperator = 34, RULE_postfixExpression = 35, RULE_primaryExpressions = 36, 
		RULE_primaryExpression = 37, RULE_construtorCall = 38, RULE_literal = 39, 
		RULE_booleanExp = 40, RULE_propertyCall = 41, RULE_timeExpression = 42, 
		RULE_propertyCallParameters = 43, RULE_ifExpression = 44, RULE_ifExps = 45, 
		RULE_enumLiteral = 46, RULE_simpleTypeSpecifier = 47, RULE_literalCollection = 48, 
		RULE_collectionItem = 49, RULE_collectionMethod = 50, RULE_literalIndex = 51, 
		RULE_declarator = 52, RULE_actualParameterList = 53, RULE_actualParameterLists = 54;
	public static final String[] ruleNames = {
		"packageDeclarationCS", "packageName", "pathName", "oclExpressions", "constraints", 
		"constraint", "content", "contextDeclaration", "stereotype", "operationContext", 
		"classifierContext", "operationName", "returnType", "formalParameterList", 
		"formalParameterLists", "typeSpecifier", "collectionType", "oclExpression", 
		"localVariableList", "localVariableLists", "letExpression", "expression", 
		"logicalExpression", "relationalExpressions", "logicalOperator", "relationalExpression", 
		"relationalOperator", "additiveExpression", "multiplicativeExpressions", 
		"addOperator", "multiplicativeExpression", "unaryExpressions", "multiplyOperator", 
		"unaryExpression", "unaryOperator", "postfixExpression", "primaryExpressions", 
		"primaryExpression", "construtorCall", "literal", "booleanExp", "propertyCall", 
		"timeExpression", "propertyCallParameters", "ifExpression", "ifExps", 
		"enumLiteral", "simpleTypeSpecifier", "literalCollection", "collectionItem", 
		"collectionMethod", "literalIndex", "declarator", "actualParameterList", 
		"actualParameterLists"
	};

	@Override
	public String getGrammarFileName() { return "Ocl.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		public SymbolTable symbolTab = Main.symbolTable;
		public String visitMethod;
		public LocalVarScope varScope = new LocalVarScope("", null);

	public OclParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PackageDeclarationCSContext extends ParserRuleContext {
		public PackageExp astRoot;
		public PackageNameContext paNa;
		public OclExpressionsContext oclExp;
		public OclExpressionsContext oclExpressions() {
			return getRuleContext(OclExpressionsContext.class,0);
		}
		public PackageNameContext packageName() {
			return getRuleContext(PackageNameContext.class,0);
		}
		public PackageDeclarationCSContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclarationCS; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterPackageDeclarationCS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitPackageDeclarationCS(this);
		}
	}

	public final PackageDeclarationCSContext packageDeclarationCS() throws RecognitionException {
		PackageDeclarationCSContext _localctx = new PackageDeclarationCSContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_packageDeclarationCS);
		try {
			enterOuterAlt(_localctx, 1);
			{
			Main.localSymbolTable.add(0,Main.symbolTable);
			setState(111); match(T__40);
			setState(112); ((PackageDeclarationCSContext)_localctx).paNa = packageName();
			((PackageDeclarationCSContext)_localctx).astRoot = new PackageExp(((PackageDeclarationCSContext)_localctx).paNa.name);
			setState(114); ((PackageDeclarationCSContext)_localctx).oclExp = oclExpressions();
			//�s���Ҧ�context
					for(AbstractSyntaxTreeNode node :((PackageDeclarationCSContext)_localctx).oclExp.contextNode)
					(_localctx.astRoot).addTreeChildNode(node);
				
			setState(116); match(T__16);
			Main.localSymbolTable.remove(0);
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

	public static class PackageNameContext extends ParserRuleContext {
		public String name;
		public PathNameContext phNa;
		public PathNameContext pathName() {
			return getRuleContext(PathNameContext.class,0);
		}
		public PackageNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterPackageName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitPackageName(this);
		}
	}

	public final PackageNameContext packageName() throws RecognitionException {
		PackageNameContext _localctx = new PackageNameContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_packageName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(119); ((PackageNameContext)_localctx).phNa = pathName();
			((PackageNameContext)_localctx).name = ((PackageNameContext)_localctx).phNa.name;
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

	public static class PathNameContext extends ParserRuleContext {
		public String name;
		public Token NAME;
		public TerminalNode NAME(int i) {
			return getToken(OclParser.NAME, i);
		}
		public List<TerminalNode> NAME() { return getTokens(OclParser.NAME); }
		public PathNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pathName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterPathName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitPathName(this);
		}
	}

	public final PathNameContext pathName() throws RecognitionException {
		PathNameContext _localctx = new PathNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_pathName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122); ((PathNameContext)_localctx).NAME = match(NAME);
			((PathNameContext)_localctx).name = (((PathNameContext)_localctx).NAME!=null?((PathNameContext)_localctx).NAME.getText():null);
			setState(127);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(124); match(T__46);
				setState(125); ((PathNameContext)_localctx).NAME = match(NAME);
				_localctx.name+="::"+(((PathNameContext)_localctx).NAME!=null?((PathNameContext)_localctx).NAME.getText():null);
				}
				break;
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

	public static class OclExpressionsContext extends ParserRuleContext {
		public ArrayList<AbstractSyntaxTreeNode> contextNode;
		public ConstraintsContext cons;
		public ConstraintsContext constraints() {
			return getRuleContext(ConstraintsContext.class,0);
		}
		public OclExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oclExpressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterOclExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitOclExpressions(this);
		}
	}

	public final OclExpressionsContext oclExpressions() throws RecognitionException {
		OclExpressionsContext _localctx = new OclExpressionsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_oclExpressions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(129); ((OclExpressionsContext)_localctx).cons = constraints();
			(_localctx.contextNode)=(((OclExpressionsContext)_localctx).cons.contextNode);
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

	public static class ConstraintsContext extends ParserRuleContext {
		public ArrayList<AbstractSyntaxTreeNode> contextNode;
		public ConstraintContext con;
		public ConstraintsContext cons;
		public ConstraintContext constraint() {
			return getRuleContext(ConstraintContext.class,0);
		}
		public ConstraintsContext constraints() {
			return getRuleContext(ConstraintsContext.class,0);
		}
		public ConstraintsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraints; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterConstraints(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitConstraints(this);
		}
	}

	public final ConstraintsContext constraints() throws RecognitionException {
		ConstraintsContext _localctx = new ConstraintsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constraints);
		try {
			setState(138);
			switch (_input.LA(1)) {
			case T__39:
				enterOuterAlt(_localctx, 1);
				{
				setState(132); ((ConstraintsContext)_localctx).con = constraint();

							((ConstraintsContext)_localctx).contextNode = new ArrayList<AbstractSyntaxTreeNode>();
							(_localctx.contextNode).add(((ConstraintsContext)_localctx).con.contextNode);
						
				setState(134); ((ConstraintsContext)_localctx).cons = constraints();
				(_localctx.contextNode).addAll(((ConstraintsContext)_localctx).cons.contextNode);
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
				((ConstraintsContext)_localctx).contextNode = new ArrayList<AbstractSyntaxTreeNode>();
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

	public static class ConstraintContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode contextNode;
		public ContextDeclarationContext coDe;
		public StereotypeContext sType;
		public Token NAME;
		public OclExpressionContext oclExp;
		public ContentContext co;
		public List<OclExpressionContext> oclExpression() {
			return getRuleContexts(OclExpressionContext.class);
		}
		public TerminalNode NAME(int i) {
			return getToken(OclParser.NAME, i);
		}
		public List<TerminalNode> NAME() { return getTokens(OclParser.NAME); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public StereotypeContext stereotype(int i) {
			return getRuleContext(StereotypeContext.class,i);
		}
		public ContextDeclarationContext contextDeclaration() {
			return getRuleContext(ContextDeclarationContext.class,0);
		}
		public OclExpressionContext oclExpression(int i) {
			return getRuleContext(OclExpressionContext.class,i);
		}
		public List<StereotypeContext> stereotype() {
			return getRuleContexts(StereotypeContext.class);
		}
		public ConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitConstraint(this);
		}
	}

	public final ConstraintContext constraint() throws RecognitionException {
		ConstraintContext _localctx = new ConstraintContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constraint);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			Main.localSymbolTable.add(0,new SymbolTable());
			setState(141); ((ConstraintContext)_localctx).coDe = contextDeclaration();
			setState(151); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(142); ((ConstraintContext)_localctx).sType = stereotype();
					setState(145);
					_la = _input.LA(1);
					if (_la==NAME) {
						{
						setState(143); ((ConstraintContext)_localctx).NAME = match(NAME);
						(((ConstraintContext)_localctx).sType.stereo).setException((((ConstraintContext)_localctx).NAME!=null?((ConstraintContext)_localctx).NAME.getText():null));
						}
					}

					setState(147); match(T__13);
					setState(148); ((ConstraintContext)_localctx).oclExp = oclExpression();

									(((ConstraintContext)_localctx).sType.stereo).addTreeChildNode(((ConstraintContext)_localctx).oclExp.node);
									if(((ConstraintContext)_localctx).coDe.node instanceof OperationContext)
									{
										((OperationContext)(((ConstraintContext)_localctx).coDe.node)).setStereoType(((ConstraintContext)_localctx).sType.stereo);
										//if(((ConstraintContext)_localctx).co.stereo!=null)
										//((OperationContext)(((ConstraintContext)_localctx).coDe.node)).setStereoType(((ConstraintContext)_localctx).co.stereo);
										
										
									}
									else{
										((ClassifierContext)(((ConstraintContext)_localctx).coDe.node)).setStereoType(((ConstraintContext)_localctx).sType.stereo);
									}
								
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(153); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(155); ((ConstraintContext)_localctx).co = content();

							
							if(((ConstraintContext)_localctx).coDe.node instanceof OperationContext)
							{
							
								if(((ConstraintContext)_localctx).co.stereo!=null)
								((OperationContext)(((ConstraintContext)_localctx).coDe.node)).setStereoType(((ConstraintContext)_localctx).co.stereo);
								
							}
							Main.localSymbolTable.remove(0);
							varScope = new LocalVarScope("", null);
							((ConstraintContext)_localctx).contextNode = ((ConstraintContext)_localctx).coDe.node;
						
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

	public static class ContentContext extends ParserRuleContext {
		public StereoType stereo;
		public StereotypeContext sType;
		public OclExpressionContext oclExp;
		public OclExpressionContext oclExpression() {
			return getRuleContext(OclExpressionContext.class,0);
		}
		public TerminalNode NAME() { return getToken(OclParser.NAME, 0); }
		public StereotypeContext stereotype() {
			return getRuleContext(StereotypeContext.class,0);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitContent(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_content);
		int _la;
		try {
			setState(167);
			switch (_input.LA(1)) {
			case T__32:
			case T__19:
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(158); ((ContentContext)_localctx).sType = stereotype();
				setState(160);
				_la = _input.LA(1);
				if (_la==NAME) {
					{
					setState(159); match(NAME);
					}
				}

				setState(162); match(T__13);
				setState(163); ((ContentContext)_localctx).oclExp = oclExpression();

						 	((ContentContext)_localctx).stereo = ((ContentContext)_localctx).sType.stereo;
							(_localctx.stereo).addTreeChildNode(((ContentContext)_localctx).oclExp.node);
						
				}
				break;
			case T__39:
			case T__16:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class ContextDeclarationContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public OperationContextContext opCo;
		public ClassifierContextContext clCo;
		public OperationContextContext operationContext() {
			return getRuleContext(OperationContextContext.class,0);
		}
		public ClassifierContextContext classifierContext() {
			return getRuleContext(ClassifierContextContext.class,0);
		}
		public ContextDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_contextDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterContextDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitContextDeclaration(this);
		}
	}

	public final ContextDeclarationContext contextDeclaration() throws RecognitionException {
		ContextDeclarationContext _localctx = new ContextDeclarationContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_contextDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169); match(T__39);
			setState(176);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(170); ((ContextDeclarationContext)_localctx).opCo = operationContext();
				((ContextDeclarationContext)_localctx).node = ((ContextDeclarationContext)_localctx).opCo.operation;
				}
				break;
			case 2:
				{
				setState(173); ((ContextDeclarationContext)_localctx).clCo = classifierContext();
				((ContextDeclarationContext)_localctx).node = ((ContextDeclarationContext)_localctx).clCo.classifier;
				}
				break;
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

	public static class StereotypeContext extends ParserRuleContext {
		public StereoType stereo;
		public StereotypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stereotype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterStereotype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitStereotype(this);
		}
	}

	public final StereotypeContext stereotype() throws RecognitionException {
		StereotypeContext _localctx = new StereotypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_stereotype);
		try {
			setState(184);
			switch (_input.LA(1)) {
			case T__32:
				enterOuterAlt(_localctx, 1);
				{
				setState(178); match(T__32);
				((StereotypeContext)_localctx).stereo = new StereoType("precondition");
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(180); match(T__18);
				((StereotypeContext)_localctx).stereo = new StereoType("postcondition");
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 3);
				{
				setState(182); match(T__19);
				((StereotypeContext)_localctx).stereo = new StereoType("invariant");
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

	public static class OperationContextContext extends ParserRuleContext {
		public OperationContext operation;
		public Token NAME;
		public OperationNameContext opNa;
		public FormalParameterListContext fpl;
		public ReturnTypeContext rt;
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public ReturnTypeContext returnType() {
			return getRuleContext(ReturnTypeContext.class,0);
		}
		public TerminalNode NAME() { return getToken(OclParser.NAME, 0); }
		public OperationNameContext operationName() {
			return getRuleContext(OperationNameContext.class,0);
		}
		public OperationContextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationContext; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterOperationContext(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitOperationContext(this);
		}
	}

	public final OperationContextContext operationContext() throws RecognitionException {
		OperationContextContext _localctx = new OperationContextContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_operationContext);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186); ((OperationContextContext)_localctx).NAME = match(NAME);
			setState(187); match(T__46);
			setState(188); ((OperationContextContext)_localctx).opNa = operationName();

						((OperationContextContext)_localctx).operation = new OperationContext((((OperationContextContext)_localctx).NAME!=null?((OperationContextContext)_localctx).NAME.getText():null),((OperationContextContext)_localctx).opNa.name);
						visitMethod = ((OperationContextContext)_localctx).opNa.name;					//�ثe���XmethodName
						varScope = new LocalVarScope(visitMethod, null);	//�ثe���X��scope, �]�w���X��method
						
						for(VariableRefExp locVar : Main.localSymbolTable.get(0).getLocalVariable().values()){
							varScope.addVariable( new VariableToken( locVar.getVariable(), locVar.getType()));
						}
					
			setState(190); match(T__41);
			setState(191); ((OperationContextContext)_localctx).fpl = formalParameterList(((OperationContextContext)_localctx).opNa.name);
			(_localctx.operation).setParameters(((OperationContextContext)_localctx).fpl.list);
			setState(193); match(T__3);
			setState(198);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(194); match(T__13);
				setState(195); ((OperationContextContext)_localctx).rt = returnType();
				(_localctx.operation).setReturnType(((OperationContextContext)_localctx).rt.reType);
							VariableRefExp var = new VariableRefExp("result");
							var.setType(((OperationContextContext)_localctx).rt.reType);
							varScope.addVariable( new VariableToken( var.getVariable(), var.getType()));
							Main.localSymbolTable.get(0).addLocalVariable(var);
						
				}
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

	public static class ClassifierContextContext extends ParserRuleContext {
		public ClassifierContext classifier;
		public Token NAME;
		public TerminalNode NAME(int i) {
			return getToken(OclParser.NAME, i);
		}
		public List<TerminalNode> NAME() { return getTokens(OclParser.NAME); }
		public ClassifierContextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classifierContext; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterClassifierContext(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitClassifierContext(this);
		}
	}

	public final ClassifierContextContext classifierContext() throws RecognitionException {
		ClassifierContextContext _localctx = new ClassifierContextContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_classifierContext);
		try {
			setState(205);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(200); match(NAME);
				setState(201); match(T__13);
				setState(202); match(NAME);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(203); ((ClassifierContextContext)_localctx).NAME = match(NAME);
				((ClassifierContextContext)_localctx).classifier = new ClassifierContext((((ClassifierContextContext)_localctx).NAME!=null?((ClassifierContextContext)_localctx).NAME.getText():null));
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

	public static class OperationNameContext extends ParserRuleContext {
		public String name;
		public Token NAME;
		public TerminalNode NAME() { return getToken(OclParser.NAME, 0); }
		public OperationNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operationName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterOperationName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitOperationName(this);
		}
	}

	public final OperationNameContext operationName() throws RecognitionException {
		OperationNameContext _localctx = new OperationNameContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_operationName);
		try {
			setState(224);
			switch (_input.LA(1)) {
			case NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(207); ((OperationNameContext)_localctx).NAME = match(NAME);
				((OperationNameContext)_localctx).name = (((OperationNameContext)_localctx).NAME!=null?((OperationNameContext)_localctx).NAME.getText():null);
				}
				break;
			case T__45:
				enterOuterAlt(_localctx, 2);
				{
				setState(209); match(T__45);
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 3);
				{
				setState(210); match(T__27);
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 4);
				{
				setState(211); match(T__0);
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 5);
				{
				setState(212); match(T__34);
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 6);
				{
				setState(213); match(T__20);
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 7);
				{
				setState(214); match(T__35);
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 8);
				{
				setState(215); match(T__9);
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 9);
				{
				setState(216); match(T__26);
				}
				break;
			case T__17:
				enterOuterAlt(_localctx, 10);
				{
				setState(217); match(T__17);
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 11);
				{
				setState(218); match(T__30);
				}
				break;
			case T__42:
				enterOuterAlt(_localctx, 12);
				{
				setState(219); match(T__42);
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 13);
				{
				setState(220); match(T__1);
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 14);
				{
				setState(221); match(T__7);
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 15);
				{
				setState(222); match(T__8);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 16);
				{
				setState(223); match(T__2);
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

	public static class ReturnTypeContext extends ParserRuleContext {
		public String reType;
		public TypeSpecifierContext ts;
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public ReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitReturnType(this);
		}
	}

	public final ReturnTypeContext returnType() throws RecognitionException {
		ReturnTypeContext _localctx = new ReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_returnType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226); ((ReturnTypeContext)_localctx).ts = typeSpecifier();
			((ReturnTypeContext)_localctx).reType = ((ReturnTypeContext)_localctx).ts.value;
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

	public static class FormalParameterListContext extends ParserRuleContext {
		public String opNa;
		public ArrayList<PropertyCallExp> list;
		public Token NAME;
		public TypeSpecifierContext tySpec;
		public FormalParameterListsContext fpls;
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public TerminalNode NAME() { return getToken(OclParser.NAME, 0); }
		public FormalParameterListsContext formalParameterLists() {
			return getRuleContext(FormalParameterListsContext.class,0);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FormalParameterListContext(ParserRuleContext parent, int invokingState, String opNa) {
			super(parent, invokingState);
			this.opNa = opNa;
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitFormalParameterList(this);
		}
	}

	public final FormalParameterListContext formalParameterList(String opNa) throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState(), opNa);
		enterRule(_localctx, 26, RULE_formalParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((FormalParameterListContext)_localctx).list = new ArrayList<PropertyCallExp> ();
			setState(236);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(230); ((FormalParameterListContext)_localctx).NAME = match(NAME);
				setState(231); match(T__13);
				setState(232); ((FormalParameterListContext)_localctx).tySpec = typeSpecifier();
				setState(233); ((FormalParameterListContext)_localctx).fpls = formalParameterLists(_localctx.opNa);

								VariableRefExp var=new VariableRefExp((((FormalParameterListContext)_localctx).NAME!=null?((FormalParameterListContext)_localctx).NAME.getText():null));
								var.setType(((FormalParameterListContext)_localctx).tySpec.value);
								Main.localSymbolTable.get(0).addLocalVariable(var);
								(_localctx.list).add(var);
								(_localctx.list).addAll(((FormalParameterListContext)_localctx).fpls.list);
							
				}
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

	public static class FormalParameterListsContext extends ParserRuleContext {
		public String opNa;
		public ArrayList<PropertyCallExp> list;
		public Token NAME;
		public TypeSpecifierContext tySpec;
		public FormalParameterListsContext fpls;
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public TerminalNode NAME() { return getToken(OclParser.NAME, 0); }
		public FormalParameterListsContext formalParameterLists() {
			return getRuleContext(FormalParameterListsContext.class,0);
		}
		public FormalParameterListsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FormalParameterListsContext(ParserRuleContext parent, int invokingState, String opNa) {
			super(parent, invokingState);
			this.opNa = opNa;
		}
		@Override public int getRuleIndex() { return RULE_formalParameterLists; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterFormalParameterLists(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitFormalParameterLists(this);
		}
	}

	public final FormalParameterListsContext formalParameterLists(String opNa) throws RecognitionException {
		FormalParameterListsContext _localctx = new FormalParameterListsContext(_ctx, getState(), opNa);
		enterRule(_localctx, 28, RULE_formalParameterLists);
		try {
			setState(246);
			switch (_input.LA(1)) {
			case T__38:
				enterOuterAlt(_localctx, 1);
				{
				setState(238); match(T__38);
				setState(239); ((FormalParameterListsContext)_localctx).NAME = match(NAME);
				setState(240); match(T__13);
				setState(241); ((FormalParameterListsContext)_localctx).tySpec = typeSpecifier();
				setState(242); ((FormalParameterListsContext)_localctx).fpls = formalParameterLists(_localctx.opNa);

						((FormalParameterListsContext)_localctx).list = new ArrayList<PropertyCallExp> ();
						VariableRefExp var=new VariableRefExp((((FormalParameterListsContext)_localctx).NAME!=null?((FormalParameterListsContext)_localctx).NAME.getText():null));
						var.setType(((FormalParameterListsContext)_localctx).tySpec.value);
						Main.localSymbolTable.get(0).addLocalVariable(var);
						(_localctx.list).add(var);
						(_localctx.list).addAll(((FormalParameterListsContext)_localctx).fpls.list);
					
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				((FormalParameterListsContext)_localctx).list = new ArrayList<PropertyCallExp> ();
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

	public static class TypeSpecifierContext extends ParserRuleContext {
		public String value;
		public VariableType type;
		public SimpleTypeSpecifierContext sts;
		public CollectionTypeContext col;
		public CollectionTypeContext collectionType() {
			return getRuleContext(CollectionTypeContext.class,0);
		}
		public SimpleTypeSpecifierContext simpleTypeSpecifier() {
			return getRuleContext(SimpleTypeSpecifierContext.class,0);
		}
		public TypeSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeSpecifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterTypeSpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitTypeSpecifier(this);
		}
	}

	public final TypeSpecifierContext typeSpecifier() throws RecognitionException {
		TypeSpecifierContext _localctx = new TypeSpecifierContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_typeSpecifier);
		try {
			setState(254);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248); ((TypeSpecifierContext)_localctx).sts = simpleTypeSpecifier();
				((TypeSpecifierContext)_localctx).value = ((TypeSpecifierContext)_localctx).sts.name; ((TypeSpecifierContext)_localctx).type =  ((TypeSpecifierContext)_localctx).sts.type;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(251); ((TypeSpecifierContext)_localctx).col = collectionType();
				((TypeSpecifierContext)_localctx).value = ((TypeSpecifierContext)_localctx).col.name;((TypeSpecifierContext)_localctx).type =  ((TypeSpecifierContext)_localctx).col.type;
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

	public static class CollectionTypeContext extends ParserRuleContext {
		public String name;
		public VariableType type;
		public SimpleTypeSpecifierContext sts;
		public TypeSpecifierContext simpleType;
		public SimpleTypeSpecifierContext simpleTypeSpecifier;
		public SimpleTypeSpecifierContext simpleTypeSpecifier() {
			return getRuleContext(SimpleTypeSpecifierContext.class,0);
		}
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public CollectionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_collectionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterCollectionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitCollectionType(this);
		}
	}

	public final CollectionTypeContext collectionType() throws RecognitionException {
		CollectionTypeContext _localctx = new CollectionTypeContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_collectionType);
		int _la;
		try {
			setState(282);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(256); ((CollectionTypeContext)_localctx).sts = simpleTypeSpecifier();

						((CollectionTypeContext)_localctx).name =  ((CollectionTypeContext)_localctx).sts.name;
						((CollectionTypeContext)_localctx).type =  ((CollectionTypeContext)_localctx).sts.type;
						
					
				setState(263);
				_la = _input.LA(1);
				if (_la==T__41) {
					{
					setState(258); match(T__41);
					setState(259); ((CollectionTypeContext)_localctx).simpleType = typeSpecifier();
					setState(260); match(T__3);

							_localctx.name +=  "(" + ((CollectionTypeContext)_localctx).simpleType.value + ")";
							((CollectionType)_localctx.type).setElement(((CollectionTypeContext)_localctx).simpleType.type);
							((CollectionType)_localctx.type).setTypeNameAndID(_localctx.name);
							Main.typeTable.add(_localctx.type.clone());
						
					}
				}

				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(265); ((CollectionTypeContext)_localctx).sts = simpleTypeSpecifier();
				((CollectionTypeContext)_localctx).name = ((CollectionTypeContext)_localctx).sts.name;((CollectionTypeContext)_localctx).type =  ((CollectionTypeContext)_localctx).sts.type;int i = 0;
				setState(269); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(267); match(T__43);
					 
							 i++;
							
					}
					}
					setState(271); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__43 );

						for(;i>=1;i--){
							((CollectionTypeContext)_localctx).type =  new ArrayType(_localctx.type.clone());
							Main.typeTable.add(_localctx.type.clone());
							
						}
						((CollectionTypeContext)_localctx).name =  _localctx.type.getType();
					
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(275); match(T__25);
				setState(276); ((CollectionTypeContext)_localctx).simpleTypeSpecifier = simpleTypeSpecifier();
				setState(277); match(T__9);
				((CollectionTypeContext)_localctx).name = "ArrayList"+"<"+((CollectionTypeContext)_localctx).simpleTypeSpecifier.name+">";
					 ((CollectionTypeContext)_localctx).type =  new ArrayListType(((CollectionTypeContext)_localctx).simpleTypeSpecifier.type);
					
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(280); match(T__47);
				((CollectionTypeContext)_localctx).name = "IntegerCollection";
					((CollectionTypeContext)_localctx).type =  new ArrayType(new IntType());
					Main.typeTable.add(_localctx.type);
					
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

	public static class OclExpressionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public ExpressionContext ex;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public OclExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_oclExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterOclExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitOclExpression(this);
		}
	}

	public final OclExpressionContext oclExpression() throws RecognitionException {
		OclExpressionContext _localctx = new OclExpressionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_oclExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284); ((OclExpressionContext)_localctx).ex = expression();
			((OclExpressionContext)_localctx).node = ((OclExpressionContext)_localctx).ex.node;
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

	public static class LocalVariableListContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public Token NAME;
		public TypeSpecifierContext tySpec;
		public ExpressionContext ex;
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public TerminalNode NAME() { return getToken(OclParser.NAME, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LocalVariableListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterLocalVariableList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitLocalVariableList(this);
		}
	}

	public final LocalVariableListContext localVariableList() throws RecognitionException {
		LocalVariableListContext _localctx = new LocalVariableListContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_localVariableList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287); ((LocalVariableListContext)_localctx).NAME = match(NAME);
			VariableRefExp var=new VariableRefExp((((LocalVariableListContext)_localctx).NAME!=null?((LocalVariableListContext)_localctx).NAME.getText():null),varScope.getId());
			setState(289); match(T__13);
			setState(290); ((LocalVariableListContext)_localctx).tySpec = typeSpecifier();
			 var.setType(((LocalVariableListContext)_localctx).tySpec.type);
				  Main.localSymbolTable.get(0).addLocalVariable(var);
				  varScope.addVariable(new VariableToken( (((LocalVariableListContext)_localctx).NAME!=null?((LocalVariableListContext)_localctx).NAME.getText():null), ((LocalVariableListContext)_localctx).tySpec.type));
				
			setState(292); match(T__45);
			setState(293); ((LocalVariableListContext)_localctx).ex = expression();

					OperatorExp eq=new OperatorExp("=",true);
					eq.setOperand(var,((LocalVariableListContext)_localctx).ex.node);
					((LocalVariableListContext)_localctx).node =  eq;
				
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

	public static class LocalVariableListsContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public LocalVariableListsContext localVars;
		public LocalVariableListContext localVar;
		public LocalVariableListContext localVariableList() {
			return getRuleContext(LocalVariableListContext.class,0);
		}
		public LocalVariableListsContext localVariableLists() {
			return getRuleContext(LocalVariableListsContext.class,0);
		}
		public LocalVariableListsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableLists; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterLocalVariableLists(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitLocalVariableLists(this);
		}
	}

	public final LocalVariableListsContext localVariableLists() throws RecognitionException {
		return localVariableLists(0);
	}

	private LocalVariableListsContext localVariableLists(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LocalVariableListsContext _localctx = new LocalVariableListsContext(_ctx, _parentState);
		LocalVariableListsContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_localVariableLists, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(297); ((LocalVariableListsContext)_localctx).localVar = localVariableList();

					((LocalVariableListsContext)_localctx).node =  ((LocalVariableListsContext)_localctx).localVar.node;
				
			}
			_ctx.stop = _input.LT(-1);
			setState(307);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new LocalVariableListsContext(_parentctx, _parentState);
					_localctx.localVars = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_localVariableLists);
					setState(300);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(301); match(T__38);
					setState(302); ((LocalVariableListsContext)_localctx).localVar = localVariableList();

					          	 	OperatorExp and_eq=new OperatorExp("and",true);
					          		and_eq.setOperand(((LocalVariableListsContext)_localctx).localVars.node,((LocalVariableListsContext)_localctx).localVar.node);
					          		((LocalVariableListsContext)_localctx).node =  and_eq;
					          		
					          	
					}
					} 
				}
				setState(309);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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

	public static class LetExpressionContext extends ParserRuleContext {
		public LetExp letnode;
		public LocalVariableListsContext localVars;
		public ExpressionContext ex;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LocalVariableListsContext localVariableLists() {
			return getRuleContext(LocalVariableListsContext.class,0);
		}
		public LetExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterLetExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitLetExpression(this);
		}
	}

	public final LetExpressionContext letExpression() throws RecognitionException {
		LetExpressionContext _localctx = new LetExpressionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_letExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			Main.localSymbolTable.add(0,new SymbolTable());
				 varScope = new LocalVarScope("", varScope);
				
			setState(311); match(T__29);
			setState(312); ((LetExpressionContext)_localctx).localVars = localVariableLists(0);
			setState(313); match(T__5);
			setState(314); ((LetExpressionContext)_localctx).ex = expression();

				   ((LetExpressionContext)_localctx).letnode = new LetExp( ((LetExpressionContext)_localctx).localVars.node , ((LetExpressionContext)_localctx).ex.node);
				   Main.localSymbolTable.remove(0);
				   varScope = varScope.getParent();
							
				
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

	public static class ExpressionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public LogicalExpressionContext logicExp;
		public LogicalExpressionContext logicalExpression() {
			return getRuleContext(LogicalExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317); ((ExpressionContext)_localctx).logicExp = logicalExpression();

						((ExpressionContext)_localctx).node = ((ExpressionContext)_localctx).logicExp.node;
					
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

	public static class LogicalExpressionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public RelationalExpressionsContext relExp;
		public RelationalExpressionsContext relationalExpressions() {
			return getRuleContext(RelationalExpressionsContext.class,0);
		}
		public LogicalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterLogicalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitLogicalExpression(this);
		}
	}

	public final LogicalExpressionContext logicalExpression() throws RecognitionException {
		LogicalExpressionContext _localctx = new LogicalExpressionContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_logicalExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(320); ((LogicalExpressionContext)_localctx).relExp = relationalExpressions(0);

					 	((LogicalExpressionContext)_localctx).node = ((LogicalExpressionContext)_localctx).relExp.node;
					
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

	public static class RelationalExpressionsContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public RelationalExpressionsContext reExp1;
		public RelationalExpressionContext relExp;
		public LogicalOperatorContext logicOper;
		public RelationalExpressionContext reExp2;
		public RelationalExpressionContext relationalExpression() {
			return getRuleContext(RelationalExpressionContext.class,0);
		}
		public RelationalExpressionsContext relationalExpressions() {
			return getRuleContext(RelationalExpressionsContext.class,0);
		}
		public LogicalOperatorContext logicalOperator() {
			return getRuleContext(LogicalOperatorContext.class,0);
		}
		public RelationalExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterRelationalExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitRelationalExpressions(this);
		}
	}

	public final RelationalExpressionsContext relationalExpressions() throws RecognitionException {
		return relationalExpressions(0);
	}

	private RelationalExpressionsContext relationalExpressions(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RelationalExpressionsContext _localctx = new RelationalExpressionsContext(_ctx, _parentState);
		RelationalExpressionsContext _prevctx = _localctx;
		int _startState = 46;
		enterRecursionRule(_localctx, 46, RULE_relationalExpressions, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(324); ((RelationalExpressionsContext)_localctx).relExp = relationalExpression();
			((RelationalExpressionsContext)_localctx).node = ((RelationalExpressionsContext)_localctx).relExp.node;
			}
			_ctx.stop = _input.LT(-1);
			setState(334);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new RelationalExpressionsContext(_parentctx, _parentState);
					_localctx.reExp1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_relationalExpressions);
					setState(327);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(328); ((RelationalExpressionsContext)_localctx).logicOper = logicalOperator();
					setState(329); ((RelationalExpressionsContext)_localctx).reExp2 = relationalExpression();

					          			(((RelationalExpressionsContext)_localctx).logicOper.operator).setOperand(((RelationalExpressionsContext)_localctx).reExp1.node,((RelationalExpressionsContext)_localctx).reExp2.node);
					          			((RelationalExpressionsContext)_localctx).node = ((RelationalExpressionsContext)_localctx).logicOper.operator;
					          			
					}
					} 
				}
				setState(336);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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

	public static class LogicalOperatorContext extends ParserRuleContext {
		public String logic;
		public OperatorExp operator;
		public LogicalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logicalOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterLogicalOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitLogicalOperator(this);
		}
	}

	public final LogicalOperatorContext logicalOperator() throws RecognitionException {
		LogicalOperatorContext _localctx = new LogicalOperatorContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_logicalOperator);
		try {
			setState(345);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(337); match(T__2);
				((LogicalOperatorContext)_localctx).logic = "and";((LogicalOperatorContext)_localctx).operator = new OperatorExp("and");
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(339); match(T__7);
				((LogicalOperatorContext)_localctx).logic = "or";((LogicalOperatorContext)_localctx).operator = new OperatorExp("or");
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 3);
				{
				setState(341); match(T__8);
				((LogicalOperatorContext)_localctx).logic = "xor";((LogicalOperatorContext)_localctx).operator = new OperatorExp("xor");
				}
				break;
			case T__42:
				enterOuterAlt(_localctx, 4);
				{
				setState(343); match(T__42);
				((LogicalOperatorContext)_localctx).logic = "implies";((LogicalOperatorContext)_localctx).operator = new OperatorExp("implies");
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

	public static class RelationalExpressionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public AdditiveExpressionContext addEx1;
		public RelationalOperatorContext relation;
		public AdditiveExpressionContext addEx2;
		public AdditiveExpressionContext additiveExpression(int i) {
			return getRuleContext(AdditiveExpressionContext.class,i);
		}
		public List<AdditiveExpressionContext> additiveExpression() {
			return getRuleContexts(AdditiveExpressionContext.class);
		}
		public RelationalOperatorContext relationalOperator() {
			return getRuleContext(RelationalOperatorContext.class,0);
		}
		public RelationalExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterRelationalExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitRelationalExpression(this);
		}
	}

	public final RelationalExpressionContext relationalExpression() throws RecognitionException {
		RelationalExpressionContext _localctx = new RelationalExpressionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_relationalExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(347); ((RelationalExpressionContext)_localctx).addEx1 = additiveExpression();
			((RelationalExpressionContext)_localctx).node = ((RelationalExpressionContext)_localctx).addEx1.node;
			setState(353);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(349); ((RelationalExpressionContext)_localctx).relation = relationalOperator();
				setState(350); ((RelationalExpressionContext)_localctx).addEx2 = additiveExpression();

								(((RelationalExpressionContext)_localctx).relation.operator).setOperand (((RelationalExpressionContext)_localctx).addEx1.node,((RelationalExpressionContext)_localctx).addEx2.node);
								((RelationalExpressionContext)_localctx).node = ((RelationalExpressionContext)_localctx).relation.operator;
							
				}
				break;
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

	public static class RelationalOperatorContext extends ParserRuleContext {
		public String relation;
		public OperatorExp operator;
		public RelationalOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationalOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterRelationalOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitRelationalOperator(this);
		}
	}

	public final RelationalOperatorContext relationalOperator() throws RecognitionException {
		RelationalOperatorContext _localctx = new RelationalOperatorContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_relationalOperator);
		try {
			setState(369);
			switch (_input.LA(1)) {
			case T__45:
				enterOuterAlt(_localctx, 1);
				{
				setState(355); match(T__45);
				((RelationalOperatorContext)_localctx).relation = "=";((RelationalOperatorContext)_localctx).operator = new OperatorExp ("=");
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(357); match(T__9);
				((RelationalOperatorContext)_localctx).relation = ">";((RelationalOperatorContext)_localctx).operator = new OperatorExp (">");
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 3);
				{
				setState(359); match(T__34);
				((RelationalOperatorContext)_localctx).relation = "<";((RelationalOperatorContext)_localctx).operator = new OperatorExp ("<");
				}
				break;
			case T__35:
				enterOuterAlt(_localctx, 4);
				{
				setState(361); match(T__35);
				((RelationalOperatorContext)_localctx).relation = ">=";((RelationalOperatorContext)_localctx).operator = new OperatorExp (">=");
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 5);
				{
				setState(363); match(T__20);
				((RelationalOperatorContext)_localctx).relation = "<=";((RelationalOperatorContext)_localctx).operator = new OperatorExp ("<=");
				}
				break;
			case T__30:
				enterOuterAlt(_localctx, 6);
				{
				setState(365); match(T__30);
				((RelationalOperatorContext)_localctx).relation = "<>";((RelationalOperatorContext)_localctx).operator = new OperatorExp ("<>");
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 7);
				{
				setState(367); match(T__10);
				((RelationalOperatorContext)_localctx).relation = "==";((RelationalOperatorContext)_localctx).operator = new OperatorExp ("==");
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

	public static class AdditiveExpressionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public MultiplicativeExpressionsContext mulExs;
		public MultiplicativeExpressionsContext multiplicativeExpressions() {
			return getRuleContext(MultiplicativeExpressionsContext.class,0);
		}
		public AdditiveExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterAdditiveExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitAdditiveExpression(this);
		}
	}

	public final AdditiveExpressionContext additiveExpression() throws RecognitionException {
		AdditiveExpressionContext _localctx = new AdditiveExpressionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_additiveExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371); ((AdditiveExpressionContext)_localctx).mulExs = multiplicativeExpressions(0);

					((AdditiveExpressionContext)_localctx).node =  ((AdditiveExpressionContext)_localctx).mulExs.node;
				
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

	public static class MultiplicativeExpressionsContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public MultiplicativeExpressionsContext mulEx1;
		public MultiplicativeExpressionContext mulEx;
		public AddOperatorContext addOp;
		public MultiplicativeExpressionContext mulEx2;
		public MultiplicativeExpressionContext multiplicativeExpression() {
			return getRuleContext(MultiplicativeExpressionContext.class,0);
		}
		public MultiplicativeExpressionsContext multiplicativeExpressions() {
			return getRuleContext(MultiplicativeExpressionsContext.class,0);
		}
		public AddOperatorContext addOperator() {
			return getRuleContext(AddOperatorContext.class,0);
		}
		public MultiplicativeExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterMultiplicativeExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitMultiplicativeExpressions(this);
		}
	}

	public final MultiplicativeExpressionsContext multiplicativeExpressions() throws RecognitionException {
		return multiplicativeExpressions(0);
	}

	private MultiplicativeExpressionsContext multiplicativeExpressions(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MultiplicativeExpressionsContext _localctx = new MultiplicativeExpressionsContext(_ctx, _parentState);
		MultiplicativeExpressionsContext _prevctx = _localctx;
		int _startState = 56;
		enterRecursionRule(_localctx, 56, RULE_multiplicativeExpressions, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(375); ((MultiplicativeExpressionsContext)_localctx).mulEx = multiplicativeExpression();
			((MultiplicativeExpressionsContext)_localctx).node = ((MultiplicativeExpressionsContext)_localctx).mulEx.node;
			}
			_ctx.stop = _input.LT(-1);
			setState(385);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MultiplicativeExpressionsContext(_parentctx, _parentState);
					_localctx.mulEx1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_multiplicativeExpressions);
					setState(378);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(379); ((MultiplicativeExpressionsContext)_localctx).addOp = addOperator();
					setState(380); ((MultiplicativeExpressionsContext)_localctx).mulEx2 = multiplicativeExpression();

					          		(((MultiplicativeExpressionsContext)_localctx).addOp.operator).setOperand(((MultiplicativeExpressionsContext)_localctx).mulEx1.node,((MultiplicativeExpressionsContext)_localctx).mulEx2.node);
					          		((MultiplicativeExpressionsContext)_localctx).node = ((MultiplicativeExpressionsContext)_localctx).addOp.operator;
					          	
					}
					} 
				}
				setState(387);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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

	public static class AddOperatorContext extends ParserRuleContext {
		public String type;
		public OperatorExp operator;
		public AddOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterAddOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitAddOperator(this);
		}
	}

	public final AddOperatorContext addOperator() throws RecognitionException {
		AddOperatorContext _localctx = new AddOperatorContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_addOperator);
		try {
			setState(392);
			switch (_input.LA(1)) {
			case T__27:
				enterOuterAlt(_localctx, 1);
				{
				setState(388); match(T__27);
				((AddOperatorContext)_localctx).type = "+";((AddOperatorContext)_localctx).operator = new OperatorExp ("+");
				}
				break;
			case T__0:
				enterOuterAlt(_localctx, 2);
				{
				setState(390); match(T__0);
				((AddOperatorContext)_localctx).type = "-";((AddOperatorContext)_localctx).operator = new OperatorExp ("-");
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

	public static class MultiplicativeExpressionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public UnaryExpressionsContext unEx;
		public UnaryExpressionsContext unaryExpressions() {
			return getRuleContext(UnaryExpressionsContext.class,0);
		}
		public MultiplicativeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterMultiplicativeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitMultiplicativeExpression(this);
		}
	}

	public final MultiplicativeExpressionContext multiplicativeExpression() throws RecognitionException {
		MultiplicativeExpressionContext _localctx = new MultiplicativeExpressionContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_multiplicativeExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394); ((MultiplicativeExpressionContext)_localctx).unEx = unaryExpressions(0);

					((MultiplicativeExpressionContext)_localctx).node = ((MultiplicativeExpressionContext)_localctx).unEx.node;
					
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

	public static class UnaryExpressionsContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public UnaryExpressionsContext unEx1;
		public UnaryExpressionContext unEx;
		public MultiplyOperatorContext mulOp;
		public UnaryExpressionContext unEx2;
		public UnaryExpressionContext unaryExpression() {
			return getRuleContext(UnaryExpressionContext.class,0);
		}
		public UnaryExpressionsContext unaryExpressions() {
			return getRuleContext(UnaryExpressionsContext.class,0);
		}
		public MultiplyOperatorContext multiplyOperator() {
			return getRuleContext(MultiplyOperatorContext.class,0);
		}
		public UnaryExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterUnaryExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitUnaryExpressions(this);
		}
	}

	public final UnaryExpressionsContext unaryExpressions() throws RecognitionException {
		return unaryExpressions(0);
	}

	private UnaryExpressionsContext unaryExpressions(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		UnaryExpressionsContext _localctx = new UnaryExpressionsContext(_ctx, _parentState);
		UnaryExpressionsContext _prevctx = _localctx;
		int _startState = 62;
		enterRecursionRule(_localctx, 62, RULE_unaryExpressions, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(398); ((UnaryExpressionsContext)_localctx).unEx = unaryExpression();
			((UnaryExpressionsContext)_localctx).node = ((UnaryExpressionsContext)_localctx).unEx.node;
			}
			_ctx.stop = _input.LT(-1);
			setState(408);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new UnaryExpressionsContext(_parentctx, _parentState);
					_localctx.unEx1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_unaryExpressions);
					setState(401);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(402); ((UnaryExpressionsContext)_localctx).mulOp = multiplyOperator();
					setState(403); ((UnaryExpressionsContext)_localctx).unEx2 = unaryExpression();

					          		(((UnaryExpressionsContext)_localctx).mulOp.operator).setOperand(((UnaryExpressionsContext)_localctx).unEx1.node,((UnaryExpressionsContext)_localctx).unEx2.node);
					          		((UnaryExpressionsContext)_localctx).node = ((UnaryExpressionsContext)_localctx).mulOp.operator;
					          	
					}
					} 
				}
				setState(410);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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

	public static class MultiplyOperatorContext extends ParserRuleContext {
		public String type;
		public OperatorExp operator;
		public MultiplyOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplyOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterMultiplyOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitMultiplyOperator(this);
		}
	}

	public final MultiplyOperatorContext multiplyOperator() throws RecognitionException {
		MultiplyOperatorContext _localctx = new MultiplyOperatorContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_multiplyOperator);
		try {
			setState(415);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(411); match(T__17);
				((MultiplyOperatorContext)_localctx).type = "*";((MultiplyOperatorContext)_localctx).operator = new OperatorExp ("*");
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(413); match(T__26);
				((MultiplyOperatorContext)_localctx).type = "/";((MultiplyOperatorContext)_localctx).operator = new OperatorExp ("/");
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

	public static class UnaryExpressionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public UnaryOperatorContext unOp;
		public PostfixExpressionContext poEx;
		public PostfixExpressionContext postfixExpression() {
			return getRuleContext(PostfixExpressionContext.class,0);
		}
		public UnaryOperatorContext unaryOperator() {
			return getRuleContext(UnaryOperatorContext.class,0);
		}
		public UnaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterUnaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitUnaryExpression(this);
		}
	}

	public final UnaryExpressionContext unaryExpression() throws RecognitionException {
		UnaryExpressionContext _localctx = new UnaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_unaryExpression);
		try {
			setState(424);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(417); ((UnaryExpressionContext)_localctx).unOp = unaryOperator();
				setState(418); ((UnaryExpressionContext)_localctx).poEx = postfixExpression();

						(((UnaryExpressionContext)_localctx).unOp.operator).setUnaryOperand(((UnaryExpressionContext)_localctx).poEx.node);
						((UnaryExpressionContext)_localctx).node = ((UnaryExpressionContext)_localctx).unOp.operator;
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(421); ((UnaryExpressionContext)_localctx).poEx = postfixExpression();
				((UnaryExpressionContext)_localctx).node = ((UnaryExpressionContext)_localctx).poEx.node;
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

	public static class UnaryOperatorContext extends ParserRuleContext {
		public String type;
		public OperatorExp operator;
		public UnaryOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryOperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterUnaryOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitUnaryOperator(this);
		}
	}

	public final UnaryOperatorContext unaryOperator() throws RecognitionException {
		UnaryOperatorContext _localctx = new UnaryOperatorContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_unaryOperator);
		try {
			setState(430);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(426); match(T__0);
				((UnaryOperatorContext)_localctx).type = "-";((UnaryOperatorContext)_localctx).operator = new OperatorExp("-");
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(428); match(T__1);
				((UnaryOperatorContext)_localctx).type = "not";((UnaryOperatorContext)_localctx).operator = new OperatorExp("not");
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

	public static class PostfixExpressionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public PrimaryExpressionContext priEx;
		public PrimaryExpressionsContext pes;
		public PrimaryExpressionsContext primaryExpressions() {
			return getRuleContext(PrimaryExpressionsContext.class,0);
		}
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public PostfixExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterPostfixExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitPostfixExpression(this);
		}
	}

	public final PostfixExpressionContext postfixExpression() throws RecognitionException {
		PostfixExpressionContext _localctx = new PostfixExpressionContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_postfixExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(432); ((PostfixExpressionContext)_localctx).priEx = primaryExpression();
			setState(433); ((PostfixExpressionContext)_localctx).pes = primaryExpressions(((PostfixExpressionContext)_localctx).priEx.node);
			{
			((PostfixExpressionContext)_localctx).node = ((PostfixExpressionContext)_localctx).pes.node;
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

	public static class PrimaryExpressionsContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode priEx;
		public AbstractSyntaxTreeNode node;
		public PropertyCallContext prop;
		public PrimaryExpressionsContext pes;
		public PropertyCallContext propertyCall() {
			return getRuleContext(PropertyCallContext.class,0);
		}
		public PrimaryExpressionsContext primaryExpressions() {
			return getRuleContext(PrimaryExpressionsContext.class,0);
		}
		public PrimaryExpressionsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PrimaryExpressionsContext(ParserRuleContext parent, int invokingState, AbstractSyntaxTreeNode priEx) {
			super(parent, invokingState);
			this.priEx = priEx;
		}
		@Override public int getRuleIndex() { return RULE_primaryExpressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterPrimaryExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitPrimaryExpressions(this);
		}
	}

	public final PrimaryExpressionsContext primaryExpressions(AbstractSyntaxTreeNode priEx) throws RecognitionException {
		PrimaryExpressionsContext _localctx = new PrimaryExpressionsContext(_ctx, getState(), priEx);
		enterRule(_localctx, 72, RULE_primaryExpressions);
		try {
			setState(449);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				OperatorExp op;
				setState(441);
				switch (_input.LA(1)) {
				case T__15:
					{
					setState(437); match(T__15);
					op =new OperatorExp(".");
					}
					break;
				case T__14:
					{
					setState(439); match(T__14);
					op =new OperatorExp("->");
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(443); ((PrimaryExpressionsContext)_localctx).prop = propertyCall(_localctx.priEx,false);
				op.setOperand(_localctx.priEx,((PrimaryExpressionsContext)_localctx).prop.property);
				setState(445); ((PrimaryExpressionsContext)_localctx).pes = primaryExpressions(op);

						{((PrimaryExpressionsContext)_localctx).node = ((PrimaryExpressionsContext)_localctx).pes.node;}
					
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				((PrimaryExpressionsContext)_localctx).node = _localctx.priEx;
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

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public LiteralCollectionContext literCol;
		public PropertyCallContext prop;
		public LiteralIndexContext index;
		public LiteralContext Literal;
		public ConstrutorCallContext newObject;
		public TypeSpecifierContext type;
		public LiteralIndexContext dim;
		public ExpressionContext ex;
		public IfExpressionContext ifExp;
		public LetExpressionContext letExp;
		public LiteralIndexContext literalIndex() {
			return getRuleContext(LiteralIndexContext.class,0);
		}
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public PropertyCallContext propertyCall() {
			return getRuleContext(PropertyCallContext.class,0);
		}
		public LiteralCollectionContext literalCollection() {
			return getRuleContext(LiteralCollectionContext.class,0);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public IfExpressionContext ifExpression() {
			return getRuleContext(IfExpressionContext.class,0);
		}
		public LetExpressionContext letExpression() {
			return getRuleContext(LetExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConstrutorCallContext construtorCall() {
			return getRuleContext(ConstrutorCallContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterPrimaryExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitPrimaryExpression(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_primaryExpression);
		int _la;
		try {
			setState(494);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(451); ((PrimaryExpressionContext)_localctx).literCol = literalCollection();
				((PrimaryExpressionContext)_localctx).node = ((PrimaryExpressionContext)_localctx).literCol.node;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(454); ((PrimaryExpressionContext)_localctx).prop = propertyCall(null,false);
				((PrimaryExpressionContext)_localctx).node = ((PrimaryExpressionContext)_localctx).prop.property;
				setState(456); ((PrimaryExpressionContext)_localctx).index = literalIndex();
				((PropertyCallExp)_localctx.node).setQualifier(((PrimaryExpressionContext)_localctx).index.list);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(459); ((PrimaryExpressionContext)_localctx).Literal = literal();

				  		((PrimaryExpressionContext)_localctx).node = ((PrimaryExpressionContext)_localctx).Literal.liter;
				  		
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(462); ((PrimaryExpressionContext)_localctx).newObject = construtorCall();
				setState(463); ((PrimaryExpressionContext)_localctx).type = typeSpecifier();
				setState(464); ((PrimaryExpressionContext)_localctx).dim = literalIndex();
				setState(465); match(T__41);
				setState(466); match(T__3);

						MethodCallExp arrayDim = new MethodCallExp("dim");
						 arrayDim.setParameters(((PrimaryExpressionContext)_localctx).dim.list);
						 Main.typeTable.add(new ArrayListType(new IntType()));
						 arrayDim.setType(new ArrayListType(new IntType()));
						 arrayDim.setReturnType(new ArrayListType(new IntType()));
						 
						 
						ArrayType array = new ArrayType(((PrimaryExpressionContext)_localctx).type.type);
						for(int i =  ((MethodCallExp) arrayDim).getParameters().size()-1 ; i >= 0; i-- ){
							if(i == ((MethodCallExp) arrayDim).getParameters().size()-1){
								array = new ArrayType(((PrimaryExpressionContext)_localctx).type.type);
								Main.typeTable.add(array);
							}else
								array = new ArrayType(array);
								Main.typeTable.add(array);
						}
						
						 ArrayList<AbstractSyntaxTreeNode> parameters =  new ArrayList<AbstractSyntaxTreeNode>();
						 parameters.add(arrayDim);
						 
						 MethodCallExp arrayDel = new MethodCallExp("dcl_array");
						 arrayDel.setParameters(parameters);
						 arrayDel.setType(array.clone());
						 arrayDel.setReturnType(array.clone());
						 ((PrimaryExpressionContext)_localctx).node =  arrayDel;
					
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				boolean isNew = false;
				setState(473);
				_la = _input.LA(1);
				if (_la==T__50) {
					{
					setState(470); ((PrimaryExpressionContext)_localctx).newObject = construtorCall();
					isNew = true;
					}
				}

				setState(475); ((PrimaryExpressionContext)_localctx).prop = propertyCall(null,isNew);
				((PrimaryExpressionContext)_localctx).node = ((PrimaryExpressionContext)_localctx).prop.property;
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(478); match(T__41);
				setState(479); ((PrimaryExpressionContext)_localctx).ex = expression();

							((PrimaryExpressionContext)_localctx).node = ((PrimaryExpressionContext)_localctx).ex.node;
						
				setState(481); match(T__3);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(483); match(T__41);
				setState(484); ((PrimaryExpressionContext)_localctx).ex = expression();

							((PrimaryExpressionContext)_localctx).node = ((PrimaryExpressionContext)_localctx).ex.node;
						
				setState(486); match(T__3);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(488); ((PrimaryExpressionContext)_localctx).ifExp = ifExpression();
				((PrimaryExpressionContext)_localctx).node = ((PrimaryExpressionContext)_localctx).ifExp.node;
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(491); ((PrimaryExpressionContext)_localctx).letExp = letExpression();
				((PrimaryExpressionContext)_localctx).node = ((PrimaryExpressionContext)_localctx).letExp.letnode;
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

	public static class ConstrutorCallContext extends ParserRuleContext {
		public ConstrutorCallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_construtorCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterConstrutorCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitConstrutorCall(this);
		}
	}

	public final ConstrutorCallContext construtorCall() throws RecognitionException {
		ConstrutorCallContext _localctx = new ConstrutorCallContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_construtorCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496); match(T__50);
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

	public static class LiteralContext extends ParserRuleContext {
		public LiteralExp liter;
		public Token STRING;
		public Token INTEGER;
		public BooleanExpContext boolExp;
		public BooleanExpContext booleanExp() {
			return getRuleContext(BooleanExpContext.class,0);
		}
		public List<TerminalNode> INTEGER() { return getTokens(OclParser.INTEGER); }
		public TerminalNode STRING() { return getToken(OclParser.STRING, 0); }
		public TerminalNode INTEGER(int i) {
			return getToken(OclParser.INTEGER, i);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_literal);
		int _la;
		try {
			setState(515);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(498); ((LiteralContext)_localctx).STRING = match(STRING);
				((LiteralContext)_localctx).liter = new LiteralExp(new StringType(),(((LiteralContext)_localctx).STRING!=null?((LiteralContext)_localctx).STRING.getText():null));
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				boolean minus=false;
				setState(503);
				_la = _input.LA(1);
				if (_la==T__0) {
					{
					setState(501); match(T__0);
					minus=true;
					}
				}

				setState(505); ((LiteralContext)_localctx).INTEGER = match(INTEGER);
				if(minus) ((LiteralContext)_localctx).liter = new LiteralExp(new IntType(),"-"+(((LiteralContext)_localctx).INTEGER!=null?((LiteralContext)_localctx).INTEGER.getText():null));else ((LiteralContext)_localctx).liter = new LiteralExp(new IntType(),(((LiteralContext)_localctx).INTEGER!=null?((LiteralContext)_localctx).INTEGER.getText():null));
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(507); ((LiteralContext)_localctx).INTEGER = match(INTEGER);
				String real=(((LiteralContext)_localctx).INTEGER!=null?((LiteralContext)_localctx).INTEGER.getText():null);
				setState(509); match(T__15);
				setState(510); ((LiteralContext)_localctx).INTEGER = match(INTEGER);
				real+="."+(((LiteralContext)_localctx).INTEGER!=null?((LiteralContext)_localctx).INTEGER.getText():null);((LiteralContext)_localctx).liter = new LiteralExp(new DoubleType(),real);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(512); ((LiteralContext)_localctx).boolExp = booleanExp();
				((LiteralContext)_localctx).liter = new LiteralExp( new BooleanType() ,((LiteralContext)_localctx).boolExp.value);
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

	public static class BooleanExpContext extends ParserRuleContext {
		public String value;
		public BooleanExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterBooleanExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitBooleanExp(this);
		}
	}

	public final BooleanExpContext booleanExp() throws RecognitionException {
		BooleanExpContext _localctx = new BooleanExpContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_booleanExp);
		try {
			setState(521);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(517); match(T__24);
				((BooleanExpContext)_localctx).value = "true";
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 2);
				{
				setState(519); match(T__37);
				((BooleanExpContext)_localctx).value = "false";
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

	public static class PropertyCallContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode priEx;
		public boolean isNew;
		public PropertyCallExp property;
		public PathNameContext paNa;
		public PathNameContext pathName;
		public LiteralIndexContext index;
		public PropertyCallParametersContext pcps;
		public LiteralIndexContext literalIndex() {
			return getRuleContext(LiteralIndexContext.class,0);
		}
		public PropertyCallParametersContext propertyCallParameters() {
			return getRuleContext(PropertyCallParametersContext.class,0);
		}
		public TimeExpressionContext timeExpression() {
			return getRuleContext(TimeExpressionContext.class,0);
		}
		public PathNameContext pathName() {
			return getRuleContext(PathNameContext.class,0);
		}
		public PropertyCallContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PropertyCallContext(ParserRuleContext parent, int invokingState, AbstractSyntaxTreeNode priEx, boolean isNew) {
			super(parent, invokingState);
			this.priEx = priEx;
			this.isNew = isNew;
		}
		@Override public int getRuleIndex() { return RULE_propertyCall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterPropertyCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitPropertyCall(this);
		}
	}

	public final PropertyCallContext propertyCall(AbstractSyntaxTreeNode priEx,boolean isNew) throws RecognitionException {
		PropertyCallContext _localctx = new PropertyCallContext(_ctx, getState(), priEx, isNew);
		enterRule(_localctx, 82, RULE_propertyCall);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523); ((PropertyCallContext)_localctx).paNa = ((PropertyCallContext)_localctx).pathName = pathName();

						if(_localctx.isNew){
							
							((PropertyCallContext)_localctx).property = new ClassCallExp(((PropertyCallContext)_localctx).paNa.name,_localctx.priEx, varScope.getId());
							(_localctx.property).setType(((PropertyCallContext)_localctx).pathName.name);
							(_localctx.property).setIsNewObj(true);
						}else{
							
							((PropertyCallContext)_localctx).property = new VariableRefExp(((PropertyCallContext)_localctx).paNa.name,_localctx.priEx, varScope.getScopeIdVarIn(((PropertyCallContext)_localctx).paNa.name));
						
							if(_localctx.priEx == null){
								
								(_localctx.property).findFromLocalVariableTable(Main.localSymbolTable);
								(_localctx.property).addVariableType(Main.typeTable);
							
							}else{
								
								(_localctx.property).setSourceExp(_localctx.priEx);
								(_localctx.property).addVariableType(Main.typeTable);
							}
						
						}
					
			setState(528);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(525); timeExpression();
				(_localctx.property).setTimeExpression();
				}
				break;
			}
			setState(533);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(530); ((PropertyCallContext)_localctx).index = literalIndex();
				((PropertyCallExp)_localctx.property).setQualifier(((PropertyCallContext)_localctx).index.list);
				}
				break;
			}
			setState(538);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(535); ((PropertyCallContext)_localctx).pcps = propertyCallParameters();
				((PropertyCallContext)_localctx).property =  new MethodCallExp(_localctx.property); 
							((MethodCallExp)(_localctx.property)).setParameters(((PropertyCallContext)_localctx).pcps.list);
							if(_localctx.property.getType() instanceof ArrayType){
								if (((ArrayType) _localctx.property.getType()).getDim() == ((PropertyCallExp)_localctx.property).getQualifier().size()){
									((MethodCallExp)(_localctx.property)).setReturnType( ((ArrayType) ((PropertyCallExp)_localctx.property.getSourceExp()).getType()).getUnitType().getSymbolTable().getMethod().get(_localctx.property.getVariable()).getReturnType());
								}else{
									((MethodCallExp)(_localctx.property)).setReturnType(new VoidType());
								}
							}
							else if(_localctx.property.getType() instanceof UserDefinedType)
							 	((MethodCallExp)(_localctx.property)).setReturnType(_localctx.property.getType().getSymbolTable().getMethod().get(_localctx.property.getVariable()).getReturnType());
							else {
								((MethodCallExp)(_localctx.property)).setReturnType(_localctx.property.getType(),Main.typeTable);
							}
						
				}
				break;
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

	public static class TimeExpressionContext extends ParserRuleContext {
		public TimeExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_timeExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterTimeExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitTimeExpression(this);
		}
	}

	public final TimeExpressionContext timeExpression() throws RecognitionException {
		TimeExpressionContext _localctx = new TimeExpressionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_timeExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(540); match(T__31);
			setState(541); match(T__32);
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

	public static class PropertyCallParametersContext extends ParserRuleContext {
		public ArrayList<AbstractSyntaxTreeNode> list;
		public DeclaratorContext de;
		public ActualParameterListContext apl;
		public DeclaratorContext declarator() {
			return getRuleContext(DeclaratorContext.class,0);
		}
		public ActualParameterListContext actualParameterList() {
			return getRuleContext(ActualParameterListContext.class,0);
		}
		public PropertyCallParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyCallParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterPropertyCallParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitPropertyCallParameters(this);
		}
	}

	public final PropertyCallParametersContext propertyCallParameters() throws RecognitionException {
		PropertyCallParametersContext _localctx = new PropertyCallParametersContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_propertyCallParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543); match(T__41);
			setState(545);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(544); ((PropertyCallParametersContext)_localctx).de = declarator();
				}
				break;
			}
			setState(550);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__50) | (1L << T__47) | (1L << T__41) | (1L << T__37) | (1L << T__29) | (1L << T__25) | (1L << T__24) | (1L << T__21) | (1L << T__1) | (1L << T__0) | (1L << NAME) | (1L << INTEGER) | (1L << STRING))) != 0)) {
				{
				setState(547); ((PropertyCallParametersContext)_localctx).apl = actualParameterList();

							((PropertyCallParametersContext)_localctx).list = ((PropertyCallParametersContext)_localctx).apl.list;
						
				}
			}

			setState(552); match(T__3);
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

	public static class IfExpressionContext extends ParserRuleContext {
		public IfExp node;
		public ExpressionContext condition;
		public ExpressionContext then;
		public IfExpsContext ifs;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public IfExpsContext ifExps() {
			return getRuleContext(IfExpsContext.class,0);
		}
		public IfExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterIfExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitIfExpression(this);
		}
	}

	public final IfExpressionContext ifExpression() throws RecognitionException {
		IfExpressionContext _localctx = new IfExpressionContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_ifExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(554); match(T__21);
			setState(555); ((IfExpressionContext)_localctx).condition = expression();
			setState(556); match(T__28);
			setState(557); ((IfExpressionContext)_localctx).then = expression();
			setState(558); ((IfExpressionContext)_localctx).ifs = ifExps(((IfExpressionContext)_localctx).condition.node,((IfExpressionContext)_localctx).then.node);
			((IfExpressionContext)_localctx).node = ((IfExpressionContext)_localctx).ifs.node;
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

	public static class IfExpsContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode condition;
		public AbstractSyntaxTreeNode then;
		public IfExp node;
		public ExpressionContext elseExp;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IfExpsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public IfExpsContext(ParserRuleContext parent, int invokingState, AbstractSyntaxTreeNode condition, AbstractSyntaxTreeNode then) {
			super(parent, invokingState);
			this.condition = condition;
			this.then = then;
		}
		@Override public int getRuleIndex() { return RULE_ifExps; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterIfExps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitIfExps(this);
		}
	}

	public final IfExpsContext ifExps(AbstractSyntaxTreeNode condition,AbstractSyntaxTreeNode then) throws RecognitionException {
		IfExpsContext _localctx = new IfExpsContext(_ctx, getState(), condition, then);
		enterRule(_localctx, 90, RULE_ifExps);
		try {
			setState(568);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(561); match(T__6);
				setState(562); ((IfExpsContext)_localctx).elseExp = expression();
				setState(563); match(T__51);
				((IfExpsContext)_localctx).node = new IfExp("if",_localctx.condition,_localctx.then,((IfExpsContext)_localctx).elseExp.node);
				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 2);
				{
				setState(566); match(T__51);
				((IfExpsContext)_localctx).node = new IfExp("if",_localctx.condition,_localctx.then);
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

	public static class EnumLiteralContext extends ParserRuleContext {
		public TerminalNode NAME(int i) {
			return getToken(OclParser.NAME, i);
		}
		public List<TerminalNode> NAME() { return getTokens(OclParser.NAME); }
		public EnumLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterEnumLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitEnumLiteral(this);
		}
	}

	public final EnumLiteralContext enumLiteral() throws RecognitionException {
		EnumLiteralContext _localctx = new EnumLiteralContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_enumLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(570); match(NAME);
			setState(571); match(T__46);
			setState(572); match(NAME);
			setState(577);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__46) {
				{
				{
				setState(573); match(T__46);
				setState(574); match(NAME);
				}
				}
				setState(579);
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

	public static class SimpleTypeSpecifierContext extends ParserRuleContext {
		public String name;
		public VariableType type;
		public PathNameContext pn;
		public PathNameContext pathName() {
			return getRuleContext(PathNameContext.class,0);
		}
		public SimpleTypeSpecifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleTypeSpecifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterSimpleTypeSpecifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitSimpleTypeSpecifier(this);
		}
	}

	public final SimpleTypeSpecifierContext simpleTypeSpecifier() throws RecognitionException {
		SimpleTypeSpecifierContext _localctx = new SimpleTypeSpecifierContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_simpleTypeSpecifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(580); ((SimpleTypeSpecifierContext)_localctx).pn = pathName();
			((SimpleTypeSpecifierContext)_localctx).name = ((SimpleTypeSpecifierContext)_localctx).pn.name; 
					((SimpleTypeSpecifierContext)_localctx).type =  Main.typeTable.get(((SimpleTypeSpecifierContext)_localctx).pn.name,null);
					if(_localctx.type == null){
						Main.typeTable.createUserDefinedType(((SimpleTypeSpecifierContext)_localctx).pn.name);
						((SimpleTypeSpecifierContext)_localctx).type =  Main.typeTable.get(((SimpleTypeSpecifierContext)_localctx).pn.name,null).clone();
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

	public static class LiteralCollectionContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public CollectionTypeContext colKind;
		public CollectionItemContext colItem;
		public CollectionMethodContext coMe;
		public CollectionTypeContext collectionType() {
			return getRuleContext(CollectionTypeContext.class,0);
		}
		public List<CollectionItemContext> collectionItem() {
			return getRuleContexts(CollectionItemContext.class);
		}
		public CollectionMethodContext collectionMethod() {
			return getRuleContext(CollectionMethodContext.class,0);
		}
		public CollectionItemContext collectionItem(int i) {
			return getRuleContext(CollectionItemContext.class,i);
		}
		public LiteralCollectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalCollection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterLiteralCollection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitLiteralCollection(this);
		}
	}

	public final LiteralCollectionContext literalCollection() throws RecognitionException {
		LiteralCollectionContext _localctx = new LiteralCollectionContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_literalCollection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(583); ((LiteralCollectionContext)_localctx).colKind = collectionType();
			CollectionExp node1=new CollectionExp(((LiteralCollectionContext)_localctx).colKind.type);
			setState(585); match(T__49);
			setState(594);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__50) | (1L << T__47) | (1L << T__41) | (1L << T__37) | (1L << T__29) | (1L << T__25) | (1L << T__24) | (1L << T__21) | (1L << T__1) | (1L << T__0) | (1L << NAME) | (1L << INTEGER) | (1L << STRING))) != 0)) {
				{
				setState(586); ((LiteralCollectionContext)_localctx).colItem = collectionItem(node1);
				setState(591);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__38) {
					{
					{
					setState(587); match(T__38);
					setState(588); collectionItem(node1);
					}
					}
					setState(593);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(596); match(T__22);
			setState(597); match(T__14);
			setState(598); ((LiteralCollectionContext)_localctx).coMe = collectionMethod(node1);
			if(((LiteralCollectionContext)_localctx).coMe.node2 instanceof IterateExp) {((LiteralCollectionContext)_localctx).node = ((LiteralCollectionContext)_localctx).coMe.node2;}else {((CollectionExp)_localctx.node).setMethodName(((PropertyCallExp)((LiteralCollectionContext)_localctx).coMe.node2).getVariable()); ((LiteralCollectionContext)_localctx).node = node1;}
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

	public static class CollectionItemContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public ExpressionContext ex;
		public ExpressionContext ex2;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public CollectionItemContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CollectionItemContext(ParserRuleContext parent, int invokingState, AbstractSyntaxTreeNode node) {
			super(parent, invokingState);
			this.node = node;
		}
		@Override public int getRuleIndex() { return RULE_collectionItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterCollectionItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitCollectionItem(this);
		}
	}

	public final CollectionItemContext collectionItem(AbstractSyntaxTreeNode node) throws RecognitionException {
		CollectionItemContext _localctx = new CollectionItemContext(_ctx, getState(), node);
		enterRule(_localctx, 98, RULE_collectionItem);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(601); ((CollectionItemContext)_localctx).ex = expression();
			((CollectionExp)_localctx.node).setStart(((CollectionItemContext)_localctx).ex.node);
			setState(607);
			_la = _input.LA(1);
			if (_la==T__48) {
				{
				setState(603); match(T__48);
				setState(604); ((CollectionItemContext)_localctx).ex2 = expression();
				((CollectionExp)_localctx.node).setEnd(((CollectionItemContext)_localctx).ex2.node);
				}
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

	public static class CollectionMethodContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode node;
		public AbstractSyntaxTreeNode node2;
		public Token name;
		public TypeSpecifierContext elementType;
		public LocalVariableListsContext localVars;
		public ExpressionContext ex;
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public TerminalNode NAME() { return getToken(OclParser.NAME, 0); }
		public LiteralCollectionContext literalCollection() {
			return getRuleContext(LiteralCollectionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LocalVariableListsContext localVariableLists() {
			return getRuleContext(LocalVariableListsContext.class,0);
		}
		public CollectionMethodContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public CollectionMethodContext(ParserRuleContext parent, int invokingState, AbstractSyntaxTreeNode node) {
			super(parent, invokingState);
			this.node = node;
		}
		@Override public int getRuleIndex() { return RULE_collectionMethod; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterCollectionMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitCollectionMethod(this);
		}
	}

	public final CollectionMethodContext collectionMethod(AbstractSyntaxTreeNode node) throws RecognitionException {
		CollectionMethodContext _localctx = new CollectionMethodContext(_ctx, getState(), node);
		enterRule(_localctx, 100, RULE_collectionMethod);
		try {
			setState(633);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
					
						((CollectionMethodContext)_localctx).node2 = new IterateExp(((CollectionExp)_localctx.node).getType());
						Main.localSymbolTable.add(0,new SymbolTable());
						varScope = new LocalVarScope("", varScope);
					
				setState(610); match(T__4);
				setState(611); ((CollectionMethodContext)_localctx).name = match(NAME);
				setState(612); match(T__13);
				setState(613); ((CollectionMethodContext)_localctx).elementType = typeSpecifier();
				setState(614); match(T__23);

						VariableRefExp elementVariable = new VariableRefExp((((CollectionMethodContext)_localctx).name!=null?((CollectionMethodContext)_localctx).name.getText():null),varScope.getId());
						elementVariable.setType(((CollectionMethodContext)_localctx).elementType.type);
						Main.localSymbolTable.get(0).addLocalVariable(elementVariable);
						varScope.addVariable( new VariableToken((((CollectionMethodContext)_localctx).name!=null?((CollectionMethodContext)_localctx).name.getText():null), ((CollectionMethodContext)_localctx).elementType.type));
						((IterateExp)_localctx.node2).setElement(elementVariable);
					
				setState(616); ((CollectionMethodContext)_localctx).localVars = localVariableLists(0);

					((IterateExp)_localctx.node2).setStart(((CollectionExp)_localctx.node).getStart());
				 	((IterateExp)_localctx.node2).setEnd(((CollectionExp)_localctx.node).getEnd());
				 	
				 	{
				 	 ((IterateExp)_localctx.node2).setAcc(((CollectionMethodContext)_localctx).localVars.node);
				 	 ((CollectionExp)_localctx.node).setAcc(((CollectionMethodContext)_localctx).localVars.node);
				 	}

				setState(618); match(T__11);
				setState(619); ((CollectionMethodContext)_localctx).ex = expression();
				((IterateExp)_localctx.node2).setBody(((CollectionMethodContext)_localctx).ex.node);((CollectionExp)_localctx.node).setBody(((CollectionMethodContext)_localctx).ex.node);
						Main.localSymbolTable.remove(0);
						varScope = varScope.getParent();
					
				setState(621); match(T__3);
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 2);
				{
				setState(623); match(T__36);
				setState(624); expression();
				setState(625); match(T__3);
				}
				break;
			case T__44:
				enterOuterAlt(_localctx, 3);
				{
				setState(627); match(T__44);
				setState(628); expression();
				setState(629); match(T__38);
				setState(630); literalCollection();
				setState(631); match(T__3);
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

	public static class LiteralIndexContext extends ParserRuleContext {
		public ArrayList<AbstractSyntaxTreeNode> list;
		public ExpressionContext ex;
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public LiteralIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterLiteralIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitLiteralIndex(this);
		}
	}

	public final LiteralIndexContext literalIndex() throws RecognitionException {
		LiteralIndexContext _localctx = new LiteralIndexContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_literalIndex);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			((LiteralIndexContext)_localctx).list = new ArrayList<>();
			setState(641); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(636); match(T__12);
					setState(637); ((LiteralIndexContext)_localctx).ex = expression();
					setState(638); match(T__33);
					_localctx.list.add(((LiteralIndexContext)_localctx).ex.node);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(643); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class DeclaratorContext extends ParserRuleContext {
		public SimpleTypeSpecifierContext sts;
		public TerminalNode NAME(int i) {
			return getToken(OclParser.NAME, i);
		}
		public SimpleTypeSpecifierContext simpleTypeSpecifier() {
			return getRuleContext(SimpleTypeSpecifierContext.class,0);
		}
		public TypeSpecifierContext typeSpecifier() {
			return getRuleContext(TypeSpecifierContext.class,0);
		}
		public List<TerminalNode> NAME() { return getTokens(OclParser.NAME); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitDeclarator(this);
		}
	}

	public final DeclaratorContext declarator() throws RecognitionException {
		DeclaratorContext _localctx = new DeclaratorContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_declarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(645); match(NAME);
			setState(650);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__38) {
				{
				{
				setState(646); match(T__38);
				setState(647); match(NAME);
				}
				}
				setState(652);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(655);
			_la = _input.LA(1);
			if (_la==T__13) {
				{
				setState(653); match(T__13);
				setState(654); ((DeclaratorContext)_localctx).sts = simpleTypeSpecifier();
				}
			}

			setState(664);
			_la = _input.LA(1);
			if (_la==T__23) {
				{
				setState(657); match(T__23);
				setState(658); match(NAME);
				setState(659); match(T__13);
				setState(660); typeSpecifier();
				setState(661); match(T__45);
				setState(662); expression();
				}
			}

			setState(666); match(T__11);
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

	public static class ActualParameterListContext extends ParserRuleContext {
		public ArrayList<AbstractSyntaxTreeNode> list;
		public ExpressionContext ex1;
		public ActualParameterListsContext apls;
		public ActualParameterListsContext actualParameterLists() {
			return getRuleContext(ActualParameterListsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ActualParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actualParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterActualParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitActualParameterList(this);
		}
	}

	public final ActualParameterListContext actualParameterList() throws RecognitionException {
		ActualParameterListContext _localctx = new ActualParameterListContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_actualParameterList);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668); ((ActualParameterListContext)_localctx).ex1 = expression();

					((ActualParameterListContext)_localctx).list = new ArrayList<AbstractSyntaxTreeNode>();
					(_localctx.list).add(((ActualParameterListContext)_localctx).ex1.node);
					
				
			setState(670); ((ActualParameterListContext)_localctx).apls = actualParameterLists(((ActualParameterListContext)_localctx).ex1.node);

					(_localctx.list).addAll(((ActualParameterListContext)_localctx).apls.list);
					
				
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

	public static class ActualParameterListsContext extends ParserRuleContext {
		public AbstractSyntaxTreeNode ex1;
		public ArrayList<AbstractSyntaxTreeNode> list;
		public ExpressionContext ex2;
		public ActualParameterListsContext apls;
		public ActualParameterListsContext actualParameterLists() {
			return getRuleContext(ActualParameterListsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ActualParameterListsContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ActualParameterListsContext(ParserRuleContext parent, int invokingState, AbstractSyntaxTreeNode ex1) {
			super(parent, invokingState);
			this.ex1 = ex1;
		}
		@Override public int getRuleIndex() { return RULE_actualParameterLists; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).enterActualParameterLists(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof OclListener ) ((OclListener)listener).exitActualParameterLists(this);
		}
	}

	public final ActualParameterListsContext actualParameterLists(AbstractSyntaxTreeNode ex1) throws RecognitionException {
		ActualParameterListsContext _localctx = new ActualParameterListsContext(_ctx, getState(), ex1);
		enterRule(_localctx, 108, RULE_actualParameterLists);
		try {
			setState(680);
			switch (_input.LA(1)) {
			case T__38:
				enterOuterAlt(_localctx, 1);
				{
				setState(673); match(T__38);
				setState(674); ((ActualParameterListsContext)_localctx).ex2 = expression();

						((ActualParameterListsContext)_localctx).list = new ArrayList<AbstractSyntaxTreeNode>();
						(_localctx.list).add(((ActualParameterListsContext)_localctx).ex2.node);
					
				setState(676); ((ActualParameterListsContext)_localctx).apls = actualParameterLists(((ActualParameterListsContext)_localctx).ex2.node);

						(_localctx.list).addAll(((ActualParameterListsContext)_localctx).apls.list);
					
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{

							((ActualParameterListsContext)_localctx).list = new ArrayList<AbstractSyntaxTreeNode>();
						
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 19: return localVariableLists_sempred((LocalVariableListsContext)_localctx, predIndex);
		case 23: return relationalExpressions_sempred((RelationalExpressionsContext)_localctx, predIndex);
		case 28: return multiplicativeExpressions_sempred((MultiplicativeExpressionsContext)_localctx, predIndex);
		case 31: return unaryExpressions_sempred((UnaryExpressionsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean relationalExpressions_sempred(RelationalExpressionsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean multiplicativeExpressions_sempred(MultiplicativeExpressionsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean unaryExpressions_sempred(UnaryExpressionsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3: return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean localVariableLists_sempred(LocalVariableListsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3:\u02ad\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4\u0082\n\4\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6\u008d\n\6\3\7\3\7\3\7\3\7\3\7\5\7\u0094\n\7\3\7"+
		"\3\7\3\7\3\7\6\7\u009a\n\7\r\7\16\7\u009b\3\7\3\7\3\7\3\b\3\b\5\b\u00a3"+
		"\n\b\3\b\3\b\3\b\3\b\3\b\5\b\u00aa\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t"+
		"\u00b3\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00bb\n\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00c9\n\13\3\f\3\f\3\f\3\f"+
		"\3\f\5\f\u00d0\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u00e3\n\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\5\17\u00ef\n\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20"+
		"\u00f9\n\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0101\n\21\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\5\22\u010a\n\22\3\22\3\22\3\22\3\22\6\22\u0110"+
		"\n\22\r\22\16\22\u0111\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5"+
		"\22\u011d\n\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0134\n\25\f\25"+
		"\16\25\u0137\13\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3"+
		"\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u014f"+
		"\n\31\f\31\16\31\u0152\13\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\5"+
		"\32\u015c\n\32\3\33\3\33\3\33\3\33\3\33\3\33\5\33\u0164\n\33\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0174"+
		"\n\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36"+
		"\u0182\n\36\f\36\16\36\u0185\13\36\3\37\3\37\3\37\3\37\5\37\u018b\n\37"+
		"\3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\7!\u0199\n!\f!\16!\u019c\13!\3\""+
		"\3\"\3\"\3\"\5\"\u01a2\n\"\3#\3#\3#\3#\3#\3#\3#\5#\u01ab\n#\3$\3$\3$\3"+
		"$\5$\u01b1\n$\3%\3%\3%\3%\3&\3&\3&\3&\3&\5&\u01bc\n&\3&\3&\3&\3&\3&\3"+
		"&\5&\u01c4\n&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'"+
		"\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u01dc\n\'\3\'\3\'\3\'\3\'\3\'\3\'"+
		"\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\5\'\u01f1\n\'\3("+
		"\3(\3)\3)\3)\3)\3)\5)\u01fa\n)\3)\3)\3)\3)\3)\3)\3)\3)\3)\3)\5)\u0206"+
		"\n)\3*\3*\3*\3*\5*\u020c\n*\3+\3+\3+\3+\3+\5+\u0213\n+\3+\3+\3+\5+\u0218"+
		"\n+\3+\3+\3+\5+\u021d\n+\3,\3,\3,\3-\3-\5-\u0224\n-\3-\3-\3-\5-\u0229"+
		"\n-\3-\3-\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\5/\u023b\n/\3\60\3"+
		"\60\3\60\3\60\3\60\7\60\u0242\n\60\f\60\16\60\u0245\13\60\3\61\3\61\3"+
		"\61\3\62\3\62\3\62\3\62\3\62\3\62\7\62\u0250\n\62\f\62\16\62\u0253\13"+
		"\62\5\62\u0255\n\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\5\63\u0262\n\63\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\5\64\u027c\n\64\3\65\3\65\3\65\3\65\3\65\3\65\6\65\u0284\n\65\r\65\16"+
		"\65\u0285\3\66\3\66\3\66\7\66\u028b\n\66\f\66\16\66\u028e\13\66\3\66\3"+
		"\66\5\66\u0292\n\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u029b\n\66"+
		"\3\66\3\66\3\67\3\67\3\67\3\67\3\67\38\38\38\38\38\38\38\58\u02ab\n8\3"+
		"8\2\6(\60:@9\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>@BDFHJLNPRTVXZ\\^`bdfhjln\2\2\u02ca\2p\3\2\2\2\4y\3\2\2\2\6|\3"+
		"\2\2\2\b\u0083\3\2\2\2\n\u008c\3\2\2\2\f\u008e\3\2\2\2\16\u00a9\3\2\2"+
		"\2\20\u00ab\3\2\2\2\22\u00ba\3\2\2\2\24\u00bc\3\2\2\2\26\u00cf\3\2\2\2"+
		"\30\u00e2\3\2\2\2\32\u00e4\3\2\2\2\34\u00e7\3\2\2\2\36\u00f8\3\2\2\2 "+
		"\u0100\3\2\2\2\"\u011c\3\2\2\2$\u011e\3\2\2\2&\u0121\3\2\2\2(\u012a\3"+
		"\2\2\2*\u0138\3\2\2\2,\u013f\3\2\2\2.\u0142\3\2\2\2\60\u0145\3\2\2\2\62"+
		"\u015b\3\2\2\2\64\u015d\3\2\2\2\66\u0173\3\2\2\28\u0175\3\2\2\2:\u0178"+
		"\3\2\2\2<\u018a\3\2\2\2>\u018c\3\2\2\2@\u018f\3\2\2\2B\u01a1\3\2\2\2D"+
		"\u01aa\3\2\2\2F\u01b0\3\2\2\2H\u01b2\3\2\2\2J\u01c3\3\2\2\2L\u01f0\3\2"+
		"\2\2N\u01f2\3\2\2\2P\u0205\3\2\2\2R\u020b\3\2\2\2T\u020d\3\2\2\2V\u021e"+
		"\3\2\2\2X\u0221\3\2\2\2Z\u022c\3\2\2\2\\\u023a\3\2\2\2^\u023c\3\2\2\2"+
		"`\u0246\3\2\2\2b\u0249\3\2\2\2d\u025b\3\2\2\2f\u027b\3\2\2\2h\u027d\3"+
		"\2\2\2j\u0287\3\2\2\2l\u029e\3\2\2\2n\u02aa\3\2\2\2pq\b\2\1\2qr\7\16\2"+
		"\2rs\5\4\3\2st\b\2\1\2tu\5\b\5\2uv\b\2\1\2vw\7&\2\2wx\b\2\1\2x\3\3\2\2"+
		"\2yz\5\6\4\2z{\b\3\1\2{\5\3\2\2\2|}\7\67\2\2}\u0081\b\4\1\2~\177\7\b\2"+
		"\2\177\u0080\7\67\2\2\u0080\u0082\b\4\1\2\u0081~\3\2\2\2\u0081\u0082\3"+
		"\2\2\2\u0082\7\3\2\2\2\u0083\u0084\5\n\6\2\u0084\u0085\b\5\1\2\u0085\t"+
		"\3\2\2\2\u0086\u0087\5\f\7\2\u0087\u0088\b\6\1\2\u0088\u0089\5\n\6\2\u0089"+
		"\u008a\b\6\1\2\u008a\u008d\3\2\2\2\u008b\u008d\b\6\1\2\u008c\u0086\3\2"+
		"\2\2\u008c\u008b\3\2\2\2\u008d\13\3\2\2\2\u008e\u008f\b\7\1\2\u008f\u0099"+
		"\5\20\t\2\u0090\u0093\5\22\n\2\u0091\u0092\7\67\2\2\u0092\u0094\b\7\1"+
		"\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096"+
		"\7)\2\2\u0096\u0097\5$\23\2\u0097\u0098\b\7\1\2\u0098\u009a\3\2\2\2\u0099"+
		"\u0090\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2"+
		"\2\2\u009c\u009d\3\2\2\2\u009d\u009e\5\16\b\2\u009e\u009f\b\7\1\2\u009f"+
		"\r\3\2\2\2\u00a0\u00a2\5\22\n\2\u00a1\u00a3\7\67\2\2\u00a2\u00a1\3\2\2"+
		"\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7)\2\2\u00a5\u00a6"+
		"\5$\23\2\u00a6\u00a7\b\b\1\2\u00a7\u00aa\3\2\2\2\u00a8\u00aa\3\2\2\2\u00a9"+
		"\u00a0\3\2\2\2\u00a9\u00a8\3\2\2\2\u00aa\17\3\2\2\2\u00ab\u00b2\7\17\2"+
		"\2\u00ac\u00ad\5\24\13\2\u00ad\u00ae\b\t\1\2\u00ae\u00b3\3\2\2\2\u00af"+
		"\u00b0\5\26\f\2\u00b0\u00b1\b\t\1\2\u00b1\u00b3\3\2\2\2\u00b2\u00ac\3"+
		"\2\2\2\u00b2\u00af\3\2\2\2\u00b3\21\3\2\2\2\u00b4\u00b5\7\26\2\2\u00b5"+
		"\u00bb\b\n\1\2\u00b6\u00b7\7$\2\2\u00b7\u00bb\b\n\1\2\u00b8\u00b9\7#\2"+
		"\2\u00b9\u00bb\b\n\1\2\u00ba\u00b4\3\2\2\2\u00ba\u00b6\3\2\2\2\u00ba\u00b8"+
		"\3\2\2\2\u00bb\23\3\2\2\2\u00bc\u00bd\7\67\2\2\u00bd\u00be\7\b\2\2\u00be"+
		"\u00bf\5\30\r\2\u00bf\u00c0\b\13\1\2\u00c0\u00c1\7\r\2\2\u00c1\u00c2\5"+
		"\34\17\2\u00c2\u00c3\b\13\1\2\u00c3\u00c8\7\63\2\2\u00c4\u00c5\7)\2\2"+
		"\u00c5\u00c6\5\32\16\2\u00c6\u00c7\b\13\1\2\u00c7\u00c9\3\2\2\2\u00c8"+
		"\u00c4\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\25\3\2\2\2\u00ca\u00cb\7\67\2"+
		"\2\u00cb\u00cc\7)\2\2\u00cc\u00d0\7\67\2\2\u00cd\u00ce\7\67\2\2\u00ce"+
		"\u00d0\b\f\1\2\u00cf\u00ca\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\27\3\2\2"+
		"\2\u00d1\u00d2\7\67\2\2\u00d2\u00e3\b\r\1\2\u00d3\u00e3\7\t\2\2\u00d4"+
		"\u00e3\7\33\2\2\u00d5\u00e3\7\66\2\2\u00d6\u00e3\7\24\2\2\u00d7\u00e3"+
		"\7\"\2\2\u00d8\u00e3\7\23\2\2\u00d9\u00e3\7-\2\2\u00da\u00e3\7\34\2\2"+
		"\u00db\u00e3\7%\2\2\u00dc\u00e3\7\30\2\2\u00dd\u00e3\7\f\2\2\u00de\u00e3"+
		"\7\65\2\2\u00df\u00e3\7/\2\2\u00e0\u00e3\7.\2\2\u00e1\u00e3\7\64\2\2\u00e2"+
		"\u00d1\3\2\2\2\u00e2\u00d3\3\2\2\2\u00e2\u00d4\3\2\2\2\u00e2\u00d5\3\2"+
		"\2\2\u00e2\u00d6\3\2\2\2\u00e2\u00d7\3\2\2\2\u00e2\u00d8\3\2\2\2\u00e2"+
		"\u00d9\3\2\2\2\u00e2\u00da\3\2\2\2\u00e2\u00db\3\2\2\2\u00e2\u00dc\3\2"+
		"\2\2\u00e2\u00dd\3\2\2\2\u00e2\u00de\3\2\2\2\u00e2\u00df\3\2\2\2\u00e2"+
		"\u00e0\3\2\2\2\u00e2\u00e1\3\2\2\2\u00e3\31\3\2\2\2\u00e4\u00e5\5 \21"+
		"\2\u00e5\u00e6\b\16\1\2\u00e6\33\3\2\2\2\u00e7\u00ee\b\17\1\2\u00e8\u00e9"+
		"\7\67\2\2\u00e9\u00ea\7)\2\2\u00ea\u00eb\5 \21\2\u00eb\u00ec\5\36\20\2"+
		"\u00ec\u00ed\b\17\1\2\u00ed\u00ef\3\2\2\2\u00ee\u00e8\3\2\2\2\u00ee\u00ef"+
		"\3\2\2\2\u00ef\35\3\2\2\2\u00f0\u00f1\7\20\2\2\u00f1\u00f2\7\67\2\2\u00f2"+
		"\u00f3\7)\2\2\u00f3\u00f4\5 \21\2\u00f4\u00f5\5\36\20\2\u00f5\u00f6\b"+
		"\20\1\2\u00f6\u00f9\3\2\2\2\u00f7\u00f9\b\20\1\2\u00f8\u00f0\3\2\2\2\u00f8"+
		"\u00f7\3\2\2\2\u00f9\37\3\2\2\2\u00fa\u00fb\5`\61\2\u00fb\u00fc\b\21\1"+
		"\2\u00fc\u0101\3\2\2\2\u00fd\u00fe\5\"\22\2\u00fe\u00ff\b\21\1\2\u00ff"+
		"\u0101\3\2\2\2\u0100\u00fa\3\2\2\2\u0100\u00fd\3\2\2\2\u0101!\3\2\2\2"+
		"\u0102\u0103\5`\61\2\u0103\u0109\b\22\1\2\u0104\u0105\7\r\2\2\u0105\u0106"+
		"\5 \21\2\u0106\u0107\7\63\2\2\u0107\u0108\b\22\1\2\u0108\u010a\3\2\2\2"+
		"\u0109\u0104\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u011d\3\2\2\2\u010b\u010c"+
		"\5`\61\2\u010c\u010f\b\22\1\2\u010d\u010e\7\13\2\2\u010e\u0110\b\22\1"+
		"\2\u010f\u010d\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112"+
		"\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\b\22\1\2\u0114\u011d\3\2\2\2"+
		"\u0115\u0116\7\35\2\2\u0116\u0117\5`\61\2\u0117\u0118\7-\2\2\u0118\u0119"+
		"\b\22\1\2\u0119\u011d\3\2\2\2\u011a\u011b\7\7\2\2\u011b\u011d\b\22\1\2"+
		"\u011c\u0102\3\2\2\2\u011c\u010b\3\2\2\2\u011c\u0115\3\2\2\2\u011c\u011a"+
		"\3\2\2\2\u011d#\3\2\2\2\u011e\u011f\5,\27\2\u011f\u0120\b\23\1\2\u0120"+
		"%\3\2\2\2\u0121\u0122\7\67\2\2\u0122\u0123\b\24\1\2\u0123\u0124\7)\2\2"+
		"\u0124\u0125\5 \21\2\u0125\u0126\b\24\1\2\u0126\u0127\7\t\2\2\u0127\u0128"+
		"\5,\27\2\u0128\u0129\b\24\1\2\u0129\'\3\2\2\2\u012a\u012b\b\25\1\2\u012b"+
		"\u012c\5&\24\2\u012c\u012d\b\25\1\2\u012d\u0135\3\2\2\2\u012e\u012f\f"+
		"\3\2\2\u012f\u0130\7\20\2\2\u0130\u0131\5&\24\2\u0131\u0132\b\25\1\2\u0132"+
		"\u0134\3\2\2\2\u0133\u012e\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0133\3\2"+
		"\2\2\u0135\u0136\3\2\2\2\u0136)\3\2\2\2\u0137\u0135\3\2\2\2\u0138\u0139"+
		"\b\26\1\2\u0139\u013a\7\31\2\2\u013a\u013b\5(\25\2\u013b\u013c\7\61\2"+
		"\2\u013c\u013d\5,\27\2\u013d\u013e\b\26\1\2\u013e+\3\2\2\2\u013f\u0140"+
		"\5.\30\2\u0140\u0141\b\27\1\2\u0141-\3\2\2\2\u0142\u0143\5\60\31\2\u0143"+
		"\u0144\b\30\1\2\u0144/\3\2\2\2\u0145\u0146\b\31\1\2\u0146\u0147\5\64\33"+
		"\2\u0147\u0148\b\31\1\2\u0148\u0150\3\2\2\2\u0149\u014a\f\4\2\2\u014a"+
		"\u014b\5\62\32\2\u014b\u014c\5\64\33\2\u014c\u014d\b\31\1\2\u014d\u014f"+
		"\3\2\2\2\u014e\u0149\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\61\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u0154\7\64\2"+
		"\2\u0154\u015c\b\32\1\2\u0155\u0156\7/\2\2\u0156\u015c\b\32\1\2\u0157"+
		"\u0158\7.\2\2\u0158\u015c\b\32\1\2\u0159\u015a\7\f\2\2\u015a\u015c\b\32"+
		"\1\2\u015b\u0153\3\2\2\2\u015b\u0155\3\2\2\2\u015b\u0157\3\2\2\2\u015b"+
		"\u0159\3\2\2\2\u015c\63\3\2\2\2\u015d\u015e\58\35\2\u015e\u0163\b\33\1"+
		"\2\u015f\u0160\5\66\34\2\u0160\u0161\58\35\2\u0161\u0162\b\33\1\2\u0162"+
		"\u0164\3\2\2\2\u0163\u015f\3\2\2\2\u0163\u0164\3\2\2\2\u0164\65\3\2\2"+
		"\2\u0165\u0166\7\t\2\2\u0166\u0174\b\34\1\2\u0167\u0168\7-\2\2\u0168\u0174"+
		"\b\34\1\2\u0169\u016a\7\24\2\2\u016a\u0174\b\34\1\2\u016b\u016c\7\23\2"+
		"\2\u016c\u0174\b\34\1\2\u016d\u016e\7\"\2\2\u016e\u0174\b\34\1\2\u016f"+
		"\u0170\7\30\2\2\u0170\u0174\b\34\1\2\u0171\u0172\7,\2\2\u0172\u0174\b"+
		"\34\1\2\u0173\u0165\3\2\2\2\u0173\u0167\3\2\2\2\u0173\u0169\3\2\2\2\u0173"+
		"\u016b\3\2\2\2\u0173\u016d\3\2\2\2\u0173\u016f\3\2\2\2\u0173\u0171\3\2"+
		"\2\2\u0174\67\3\2\2\2\u0175\u0176\5:\36\2\u0176\u0177\b\35\1\2\u01779"+
		"\3\2\2\2\u0178\u0179\b\36\1\2\u0179\u017a\5> \2\u017a\u017b\b\36\1\2\u017b"+
		"\u0183\3\2\2\2\u017c\u017d\f\4\2\2\u017d\u017e\5<\37\2\u017e\u017f\5>"+
		" \2\u017f\u0180\b\36\1\2\u0180\u0182\3\2\2\2\u0181\u017c\3\2\2\2\u0182"+
		"\u0185\3\2\2\2\u0183\u0181\3\2\2\2\u0183\u0184\3\2\2\2\u0184;\3\2\2\2"+
		"\u0185\u0183\3\2\2\2\u0186\u0187\7\33\2\2\u0187\u018b\b\37\1\2\u0188\u0189"+
		"\7\66\2\2\u0189\u018b\b\37\1\2\u018a\u0186\3\2\2\2\u018a\u0188\3\2\2\2"+
		"\u018b=\3\2\2\2\u018c\u018d\5@!\2\u018d\u018e\b \1\2\u018e?\3\2\2\2\u018f"+
		"\u0190\b!\1\2\u0190\u0191\5D#\2\u0191\u0192\b!\1\2\u0192\u019a\3\2\2\2"+
		"\u0193\u0194\f\4\2\2\u0194\u0195\5B\"\2\u0195\u0196\5D#\2\u0196\u0197"+
		"\b!\1\2\u0197\u0199\3\2\2\2\u0198\u0193\3\2\2\2\u0199\u019c\3\2\2\2\u019a"+
		"\u0198\3\2\2\2\u019a\u019b\3\2\2\2\u019bA\3\2\2\2\u019c\u019a\3\2\2\2"+
		"\u019d\u019e\7%\2\2\u019e\u01a2\b\"\1\2\u019f\u01a0\7\34\2\2\u01a0\u01a2"+
		"\b\"\1\2\u01a1\u019d\3\2\2\2\u01a1\u019f\3\2\2\2\u01a2C\3\2\2\2\u01a3"+
		"\u01a4\5F$\2\u01a4\u01a5\5H%\2\u01a5\u01a6\b#\1\2\u01a6\u01ab\3\2\2\2"+
		"\u01a7\u01a8\5H%\2\u01a8\u01a9\b#\1\2\u01a9\u01ab\3\2\2\2\u01aa\u01a3"+
		"\3\2\2\2\u01aa\u01a7\3\2\2\2\u01abE\3\2\2\2\u01ac\u01ad\7\66\2\2\u01ad"+
		"\u01b1\b$\1\2\u01ae\u01af\7\65\2\2\u01af\u01b1\b$\1\2\u01b0\u01ac\3\2"+
		"\2\2\u01b0\u01ae\3\2\2\2\u01b1G\3\2\2\2\u01b2\u01b3\5L\'\2\u01b3\u01b4"+
		"\5J&\2\u01b4\u01b5\b%\1\2\u01b5I\3\2\2\2\u01b6\u01bb\b&\1\2\u01b7\u01b8"+
		"\7\'\2\2\u01b8\u01bc\b&\1\2\u01b9\u01ba\7(\2\2\u01ba\u01bc\b&\1\2\u01bb"+
		"\u01b7\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bc\u01bd\3\2\2\2\u01bd\u01be\5T"+
		"+\2\u01be\u01bf\b&\1\2\u01bf\u01c0\5J&\2\u01c0\u01c1\b&\1\2\u01c1\u01c4"+
		"\3\2\2\2\u01c2\u01c4\b&\1\2\u01c3\u01b6\3\2\2\2\u01c3\u01c2\3\2\2\2\u01c4"+
		"K\3\2\2\2\u01c5\u01c6\5b\62\2\u01c6\u01c7\b\'\1\2\u01c7\u01f1\3\2\2\2"+
		"\u01c8\u01c9\5T+\2\u01c9\u01ca\b\'\1\2\u01ca\u01cb\5h\65\2\u01cb\u01cc"+
		"\b\'\1\2\u01cc\u01f1\3\2\2\2\u01cd\u01ce\5P)\2\u01ce\u01cf\b\'\1\2\u01cf"+
		"\u01f1\3\2\2\2\u01d0\u01d1\5N(\2\u01d1\u01d2\5 \21\2\u01d2\u01d3\5h\65"+
		"\2\u01d3\u01d4\7\r\2\2\u01d4\u01d5\7\63\2\2\u01d5\u01d6\b\'\1\2\u01d6"+
		"\u01f1\3\2\2\2\u01d7\u01db\b\'\1\2\u01d8\u01d9\5N(\2\u01d9\u01da\b\'\1"+
		"\2\u01da\u01dc\3\2\2\2\u01db\u01d8\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01dd"+
		"\3\2\2\2\u01dd\u01de\5T+\2\u01de\u01df\b\'\1\2\u01df\u01f1\3\2\2\2\u01e0"+
		"\u01e1\7\r\2\2\u01e1\u01e2\5,\27\2\u01e2\u01e3\b\'\1\2\u01e3\u01e4\7\63"+
		"\2\2\u01e4\u01f1\3\2\2\2\u01e5\u01e6\7\r\2\2\u01e6\u01e7\5,\27\2\u01e7"+
		"\u01e8\b\'\1\2\u01e8\u01e9\7\63\2\2\u01e9\u01f1\3\2\2\2\u01ea\u01eb\5"+
		"Z.\2\u01eb\u01ec\b\'\1\2\u01ec\u01f1\3\2\2\2\u01ed\u01ee\5*\26\2\u01ee"+
		"\u01ef\b\'\1\2\u01ef\u01f1\3\2\2\2\u01f0\u01c5\3\2\2\2\u01f0\u01c8\3\2"+
		"\2\2\u01f0\u01cd\3\2\2\2\u01f0\u01d0\3\2\2\2\u01f0\u01d7\3\2\2\2\u01f0"+
		"\u01e0\3\2\2\2\u01f0\u01e5\3\2\2\2\u01f0\u01ea\3\2\2\2\u01f0\u01ed\3\2"+
		"\2\2\u01f1M\3\2\2\2\u01f2\u01f3\7\4\2\2\u01f3O\3\2\2\2\u01f4\u01f5\79"+
		"\2\2\u01f5\u0206\b)\1\2\u01f6\u01f9\b)\1\2\u01f7\u01f8\7\66\2\2\u01f8"+
		"\u01fa\b)\1\2\u01f9\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fb\3\2"+
		"\2\2\u01fb\u01fc\78\2\2\u01fc\u0206\b)\1\2\u01fd\u01fe\78\2\2\u01fe\u01ff"+
		"\b)\1\2\u01ff\u0200\7\'\2\2\u0200\u0201\78\2\2\u0201\u0206\b)\1\2\u0202"+
		"\u0203\5R*\2\u0203\u0204\b)\1\2\u0204\u0206\3\2\2\2\u0205\u01f4\3\2\2"+
		"\2\u0205\u01f6\3\2\2\2\u0205\u01fd\3\2\2\2\u0205\u0202\3\2\2\2\u0206Q"+
		"\3\2\2\2\u0207\u0208\7\36\2\2\u0208\u020c\b*\1\2\u0209\u020a\7\21\2\2"+
		"\u020a\u020c\b*\1\2\u020b\u0207\3\2\2\2\u020b\u0209\3\2\2\2\u020cS\3\2"+
		"\2\2\u020d\u020e\5\6\4\2\u020e\u0212\b+\1\2\u020f\u0210\5V,\2\u0210\u0211"+
		"\b+\1\2\u0211\u0213\3\2\2\2\u0212\u020f\3\2\2\2\u0212\u0213\3\2\2\2\u0213"+
		"\u0217\3\2\2\2\u0214\u0215\5h\65\2\u0215\u0216\b+\1\2\u0216\u0218\3\2"+
		"\2\2\u0217\u0214\3\2\2\2\u0217\u0218\3\2\2\2\u0218\u021c\3\2\2\2\u0219"+
		"\u021a\5X-\2\u021a\u021b\b+\1\2\u021b\u021d\3\2\2\2\u021c\u0219\3\2\2"+
		"\2\u021c\u021d\3\2\2\2\u021dU\3\2\2\2\u021e\u021f\7\27\2\2\u021f\u0220"+
		"\7\26\2\2\u0220W\3\2\2\2\u0221\u0223\7\r\2\2\u0222\u0224\5j\66\2\u0223"+
		"\u0222\3\2\2\2\u0223\u0224\3\2\2\2\u0224\u0228\3\2\2\2\u0225\u0226\5l"+
		"\67\2\u0226\u0227\b-\1\2\u0227\u0229\3\2\2\2\u0228\u0225\3\2\2\2\u0228"+
		"\u0229\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022b\7\63\2\2\u022bY\3\2\2\2"+
		"\u022c\u022d\7!\2\2\u022d\u022e\5,\27\2\u022e\u022f\7\32\2\2\u022f\u0230"+
		"\5,\27\2\u0230\u0231\5\\/\2\u0231\u0232\b.\1\2\u0232[\3\2\2\2\u0233\u0234"+
		"\7\60\2\2\u0234\u0235\5,\27\2\u0235\u0236\7\3\2\2\u0236\u0237\b/\1\2\u0237"+
		"\u023b\3\2\2\2\u0238\u0239\7\3\2\2\u0239\u023b\b/\1\2\u023a\u0233\3\2"+
		"\2\2\u023a\u0238\3\2\2\2\u023b]\3\2\2\2\u023c\u023d\7\67\2\2\u023d\u023e"+
		"\7\b\2\2\u023e\u0243\7\67\2\2\u023f\u0240\7\b\2\2\u0240\u0242\7\67\2\2"+
		"\u0241\u023f\3\2\2\2\u0242\u0245\3\2\2\2\u0243\u0241\3\2\2\2\u0243\u0244"+
		"\3\2\2\2\u0244_\3\2\2\2\u0245\u0243\3\2\2\2\u0246\u0247\5\6\4\2\u0247"+
		"\u0248\b\61\1\2\u0248a\3\2\2\2\u0249\u024a\5\"\22\2\u024a\u024b\b\62\1"+
		"\2\u024b\u0254\7\5\2\2\u024c\u0251\5d\63\2\u024d\u024e\7\20\2\2\u024e"+
		"\u0250\5d\63\2\u024f\u024d\3\2\2\2\u0250\u0253\3\2\2\2\u0251\u024f\3\2"+
		"\2\2\u0251\u0252\3\2\2\2\u0252\u0255\3\2\2\2\u0253\u0251\3\2\2\2\u0254"+
		"\u024c\3\2\2\2\u0254\u0255\3\2\2\2\u0255\u0256\3\2\2\2\u0256\u0257\7 "+
		"\2\2\u0257\u0258\7(\2\2\u0258\u0259\5f\64\2\u0259\u025a\b\62\1\2\u025a"+
		"c\3\2\2\2\u025b\u025c\5,\27\2\u025c\u0261\b\63\1\2\u025d\u025e\7\6\2\2"+
		"\u025e\u025f\5,\27\2\u025f\u0260\b\63\1\2\u0260\u0262\3\2\2\2\u0261\u025d"+
		"\3\2\2\2\u0261\u0262\3\2\2\2\u0262e\3\2\2\2\u0263\u0264\b\64\1\2\u0264"+
		"\u0265\7\62\2\2\u0265\u0266\7\67\2\2\u0266\u0267\7)\2\2\u0267\u0268\5"+
		" \21\2\u0268\u0269\7\37\2\2\u0269\u026a\b\64\1\2\u026a\u026b\5(\25\2\u026b"+
		"\u026c\b\64\1\2\u026c\u026d\7+\2\2\u026d\u026e\5,\27\2\u026e\u026f\b\64"+
		"\1\2\u026f\u0270\7\63\2\2\u0270\u027c\3\2\2\2\u0271\u0272\7\22\2\2\u0272"+
		"\u0273\5,\27\2\u0273\u0274\7\63\2\2\u0274\u027c\3\2\2\2\u0275\u0276\7"+
		"\n\2\2\u0276\u0277\5,\27\2\u0277\u0278\7\20\2\2\u0278\u0279\5b\62\2\u0279"+
		"\u027a\7\63\2\2\u027a\u027c\3\2\2\2\u027b\u0263\3\2\2\2\u027b\u0271\3"+
		"\2\2\2\u027b\u0275\3\2\2\2\u027cg\3\2\2\2\u027d\u0283\b\65\1\2\u027e\u027f"+
		"\7*\2\2\u027f\u0280\5,\27\2\u0280\u0281\7\25\2\2\u0281\u0282\b\65\1\2"+
		"\u0282\u0284\3\2\2\2\u0283\u027e\3\2\2\2\u0284\u0285\3\2\2\2\u0285\u0283"+
		"\3\2\2\2\u0285\u0286\3\2\2\2\u0286i\3\2\2\2\u0287\u028c\7\67\2\2\u0288"+
		"\u0289\7\20\2\2\u0289\u028b\7\67\2\2\u028a\u0288\3\2\2\2\u028b\u028e\3"+
		"\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2\2\2\u028d\u0291\3\2\2\2\u028e"+
		"\u028c\3\2\2\2\u028f\u0290\7)\2\2\u0290\u0292\5`\61\2\u0291\u028f\3\2"+
		"\2\2\u0291\u0292\3\2\2\2\u0292\u029a\3\2\2\2\u0293\u0294\7\37\2\2\u0294"+
		"\u0295\7\67\2\2\u0295\u0296\7)\2\2\u0296\u0297\5 \21\2\u0297\u0298\7\t"+
		"\2\2\u0298\u0299\5,\27\2\u0299\u029b\3\2\2\2\u029a\u0293\3\2\2\2\u029a"+
		"\u029b\3\2\2\2\u029b\u029c\3\2\2\2\u029c\u029d\7+\2\2\u029dk\3\2\2\2\u029e"+
		"\u029f\5,\27\2\u029f\u02a0\b\67\1\2\u02a0\u02a1\5n8\2\u02a1\u02a2\b\67"+
		"\1\2\u02a2m\3\2\2\2\u02a3\u02a4\7\20\2\2\u02a4\u02a5\5,\27\2\u02a5\u02a6"+
		"\b8\1\2\u02a6\u02a7\5n8\2\u02a7\u02a8\b8\1\2\u02a8\u02ab\3\2\2\2\u02a9"+
		"\u02ab\b8\1\2\u02aa\u02a3\3\2\2\2\u02aa\u02a9\3\2\2\2\u02abo\3\2\2\2\65"+
		"\u0081\u008c\u0093\u009b\u00a2\u00a9\u00b2\u00ba\u00c8\u00cf\u00e2\u00ee"+
		"\u00f8\u0100\u0109\u0111\u011c\u0135\u0150\u015b\u0163\u0173\u0183\u018a"+
		"\u019a\u01a1\u01aa\u01b0\u01bb\u01c3\u01db\u01f0\u01f9\u0205\u020b\u0212"+
		"\u0217\u021c\u0223\u0228\u023a\u0243\u0251\u0254\u0261\u027b\u0285\u028c"+
		"\u0291\u029a\u02aa";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}