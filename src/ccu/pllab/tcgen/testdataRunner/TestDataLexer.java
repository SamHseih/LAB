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

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TestDataLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__14=1, T__13=2, T__12=3, T__11=4, T__10=5, T__9=6, T__8=7, T__7=8, T__6=9, 
		T__5=10, T__4=11, T__3=12, T__2=13, T__1=14, T__0=15, STRING=16, REAL=17, 
		BOOL=18, INTEGER=19, NUMBER=20, PUNCT=21, PUNCT2=22, CNTRL=23, LETTER=24, 
		WS=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'"
	};
	public static final String[] ruleNames = {
		"T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", 
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "STRING", "REAL", "BOOL", 
		"INTEGER", "NUMBER", "PUNCT", "PUNCT2", "CNTRL", "LETTER", "WS"
	};


	public TestDataLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "TestData.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33\u00bc\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\7\21y\n\21\f\21\16\21|\13\21\3\21\3\21\3\22\6\22"+
		"\u0081\n\22\r\22\16\22\u0082\3\22\3\22\6\22\u0087\n\22\r\22\16\22\u0088"+
		"\3\22\3\22\5\22\u008d\n\22\3\22\6\22\u0090\n\22\r\22\16\22\u0091\5\22"+
		"\u0094\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u009f\n"+
		"\23\3\24\5\24\u00a2\n\24\3\24\6\24\u00a5\n\24\r\24\16\24\u00a6\3\25\3"+
		"\25\3\26\3\26\3\27\3\27\3\27\5\27\u00b0\n\27\3\30\3\30\3\31\3\31\3\32"+
		"\6\32\u00b7\n\32\r\32\16\32\u00b8\3\32\3\32\3z\2\33\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\3\2\b\4\2--//\3\2\62;\7\2#\61<B^^`b}\u0080"+
		"\4\2\62^zz\4\2C\\c|\5\2\13\f\17\17\"\"\u00ca\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2"+
		"\2\3\65\3\2\2\2\59\3\2\2\2\7=\3\2\2\2\tB\3\2\2\2\13F\3\2\2\2\rL\3\2\2"+
		"\2\17V\3\2\2\2\21X\3\2\2\2\23_\3\2\2\2\25d\3\2\2\2\27f\3\2\2\2\31h\3\2"+
		"\2\2\33k\3\2\2\2\35n\3\2\2\2\37p\3\2\2\2!r\3\2\2\2#\u0080\3\2\2\2%\u009e"+
		"\3\2\2\2\'\u00a1\3\2\2\2)\u00a8\3\2\2\2+\u00aa\3\2\2\2-\u00af\3\2\2\2"+
		"/\u00b1\3\2\2\2\61\u00b3\3\2\2\2\63\u00b6\3\2\2\2\65\66\7Q\2\2\66\67\7"+
		"D\2\2\678\7L\2\28\4\3\2\2\29:\7C\2\2:;\7T\2\2;<\7I\2\2<\6\3\2\2\2=>\7"+
		"]\2\2>?\7]\2\2?@\7_\2\2@A\7_\2\2A\b\3\2\2\2BC\7]\2\2CD\7_\2\2DE\7*\2\2"+
		"E\n\3\2\2\2FG\7a\2\2GH\7R\2\2HI\7Q\2\2IJ\7U\2\2JK\7V\2\2K\f\3\2\2\2LM"+
		"\7G\2\2MN\7Z\2\2NO\7E\2\2OP\7G\2\2PQ\7R\2\2QR\7V\2\2RS\7K\2\2ST\7Q\2\2"+
		"TU\7P\2\2U\16\3\2\2\2VW\7]\2\2W\20\3\2\2\2XY\7T\2\2YZ\7G\2\2Z[\7V\2\2"+
		"[\\\7X\2\2\\]\7C\2\2]^\7N\2\2^\22\3\2\2\2_`\7a\2\2`a\7R\2\2ab\7T\2\2b"+
		"c\7G\2\2c\24\3\2\2\2de\7_\2\2e\26\3\2\2\2fg\7?\2\2g\30\3\2\2\2hi\7]\2"+
		"\2ij\7_\2\2j\32\3\2\2\2kl\7<\2\2lm\7\"\2\2m\34\3\2\2\2no\7+\2\2o\36\3"+
		"\2\2\2pq\7.\2\2q \3\2\2\2rz\7$\2\2sy\5-\27\2ty\5/\30\2uy\5)\25\2vy\5\61"+
		"\31\2wy\5+\26\2xs\3\2\2\2xt\3\2\2\2xu\3\2\2\2xv\3\2\2\2xw\3\2\2\2y|\3"+
		"\2\2\2z{\3\2\2\2zx\3\2\2\2{}\3\2\2\2|z\3\2\2\2}~\7$\2\2~\"\3\2\2\2\177"+
		"\u0081\5)\25\2\u0080\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0080\3\2\2"+
		"\2\u0082\u0083\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\7\60\2\2\u0085"+
		"\u0087\5)\25\2\u0086\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0088\u0089\3\2\2\2\u0089\u0093\3\2\2\2\u008a\u008c\7G\2\2\u008b"+
		"\u008d\t\2\2\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008f\3\2"+
		"\2\2\u008e\u0090\5)\25\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u008a\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094$\3\2\2\2\u0095\u0096\7v\2\2\u0096\u0097"+
		"\7t\2\2\u0097\u0098\7w\2\2\u0098\u009f\7g\2\2\u0099\u009a\7h\2\2\u009a"+
		"\u009b\7c\2\2\u009b\u009c\7n\2\2\u009c\u009d\7u\2\2\u009d\u009f\7g\2\2"+
		"\u009e\u0095\3\2\2\2\u009e\u0099\3\2\2\2\u009f&\3\2\2\2\u00a0\u00a2\7"+
		"/\2\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3"+
		"\u00a5\5)\25\2\u00a4\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2"+
		"\2\2\u00a6\u00a7\3\2\2\2\u00a7(\3\2\2\2\u00a8\u00a9\t\3\2\2\u00a9*\3\2"+
		"\2\2\u00aa\u00ab\t\4\2\2\u00ab,\3\2\2\2\u00ac\u00ad\7^\2\2\u00ad\u00b0"+
		"\7$\2\2\u00ae\u00b0\7^\2\2\u00af\u00ac\3\2\2\2\u00af\u00ae\3\2\2\2\u00b0"+
		".\3\2\2\2\u00b1\u00b2\t\5\2\2\u00b2\60\3\2\2\2\u00b3\u00b4\t\6\2\2\u00b4"+
		"\62\3\2\2\2\u00b5\u00b7\t\7\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2"+
		"\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb"+
		"\b\32\2\2\u00bb\64\3\2\2\2\17\2xz\u0082\u0088\u008c\u0091\u0093\u009e"+
		"\u00a1\u00a6\u00af\u00b8\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}