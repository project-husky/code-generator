// Generated from Hl7ItsLexer.g4 by ANTLR 4.7.1

package org.ehealth_connector.codegenerator.cda.antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Hl7ItsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, CDATA=2, DTD=3, EntityRef=4, CharRef=5, SEA_WS=6, OPEN=7, XMLDeclOpen=8, 
		TEXT=9, ASSERT=10, ATTRIBUTE=11, CHOICE=12, CONFATTR=13, CONTAINSATTR=14, 
		DESC=15, ELEMENT=16, IDATTR=17, INCLUDE=18, LET=19, MANDATTR=20, MAXOCCURSATTR=21, 
		MINOCCURSATTR=22, NAMEATTR=23, OPTATTR=24, PROHIBITED=25, REFATTR=26, 
		REPORT=27, STRENGTHATTR=28, TEMPLATE=29, TYPEATTR=30, VALUEATTR=31, CLOSE=32, 
		SPECIAL_CLOSE=33, SLASH_CLOSE=34, SLASH=35, EQUALS=36, AttrValue=37, Name=38, 
		S=39, PI=40;
	public static final int
		INSIDE=1, PROC_INSTR=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "INSIDE", "PROC_INSTR"
	};

	public static final String[] ruleNames = {
		"COMMENT", "CDATA", "DTD", "EntityRef", "CharRef", "SEA_WS", "OPEN", "XMLDeclOpen", 
		"SPECIAL_OPEN", "TEXT", "ASSERT", "ATTRIBUTE", "CHOICE", "CONFATTR", "CONTAINSATTR", 
		"DESC", "ELEMENT", "IDATTR", "INCLUDE", "LET", "MANDATTR", "MAXOCCURSATTR", 
		"MINOCCURSATTR", "NAMEATTR", "OPTATTR", "PROHIBITED", "REFATTR", "REPORT", 
		"STRENGTHATTR", "TEMPLATE", "TYPEATTR", "VALUEATTR", "CLOSE", "SPECIAL_CLOSE", 
		"SLASH_CLOSE", "SLASH", "EQUALS", "AttrValue", "Name", "S", "HEXDIGIT", 
		"DIGIT", "NameChar", "NameStartChar", "PI", "IGNORE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'<'", null, null, "'assert'", 
		"'attribute'", "'choice'", "'conformance='", "'contains='", "'desc'", 
		"'element'", "'id='", "'include'", "'let'", "'isMandatory='", "'maximumMultiplicity='", 
		"'minimumMultiplicity='", "'name='", "'isOptional='", "'prohibited='", 
		"'ref='", "'report'", "'strength='", "'template'", "'datatype='", "'value='", 
		"'>'", null, "'/>'", "'/'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMENT", "CDATA", "DTD", "EntityRef", "CharRef", "SEA_WS", "OPEN", 
		"XMLDeclOpen", "TEXT", "ASSERT", "ATTRIBUTE", "CHOICE", "CONFATTR", "CONTAINSATTR", 
		"DESC", "ELEMENT", "IDATTR", "INCLUDE", "LET", "MANDATTR", "MAXOCCURSATTR", 
		"MINOCCURSATTR", "NAMEATTR", "OPTATTR", "PROHIBITED", "REFATTR", "REPORT", 
		"STRENGTHATTR", "TEMPLATE", "TYPEATTR", "VALUEATTR", "CLOSE", "SPECIAL_CLOSE", 
		"SLASH_CLOSE", "SLASH", "EQUALS", "AttrValue", "Name", "S", "PI"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public Hl7ItsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Hl7ItsLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u01e6\b\1\b\1\b"+
		"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"+
		"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"+
		"\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30"+
		"\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37"+
		"\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t"+
		"*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\2\3\2\7\2h\n\2\f\2\16"+
		"\2k\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7"+
		"\3|\n\3\f\3\16\3\177\13\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4\u0089\n"+
		"\4\f\4\16\4\u008c\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\6\6\u009a\n\6\r\6\16\6\u009b\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6\u00a5\n"+
		"\6\r\6\16\6\u00a6\3\6\3\6\5\6\u00ab\n\6\3\7\3\7\5\7\u00af\n\7\3\7\6\7"+
		"\u00b2\n\7\r\7\16\7\u00b3\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\6\13\u00cd\n\13\r\13"+
		"\16\13\u00ce\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3"+
		"$\3$\3%\3%\3&\3&\3\'\3\'\7\'\u01b6\n\'\f\'\16\'\u01b9\13\'\3\'\3\'\3\'"+
		"\7\'\u01be\n\'\f\'\16\'\u01c1\13\'\3\'\5\'\u01c4\n\'\3(\3(\7(\u01c8\n"+
		"(\f(\16(\u01cb\13(\3)\3)\3)\3)\3*\3*\3+\3+\3,\3,\3,\3,\5,\u01d9\n,\3-"+
		"\5-\u01dc\n-\3.\3.\3.\3.\3.\3/\3/\3/\3/\5i}\u008a\2\60\5\3\7\4\t\5\13"+
		"\6\r\7\17\b\21\t\23\n\25\2\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23"+
		")\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?\37A C!E\"G#I$K%"+
		"M&O\'Q(S)U\2W\2Y\2[\2]*_\2\5\2\3\4\f\4\2\13\13\"\"\4\2((>>\4\2$$>>\4\2"+
		"))>>\5\2\13\f\17\17\"\"\5\2\62;CHch\3\2\62;\4\2/\60aa\5\2\u00b9\u00b9"+
		"\u0302\u0371\u2041\u2042\n\2<<C\\c|\u2072\u2191\u2c02\u2ff1\u3003\ud801"+
		"\uf902\ufdd1\ufdf2\uffff\2\u01f0\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\3\31\3\2\2\2\3\33\3\2\2\2\3\35\3\2\2\2\3\37\3\2\2"+
		"\2\3!\3\2\2\2\3#\3\2\2\2\3%\3\2\2\2\3\'\3\2\2\2\3)\3\2\2\2\3+\3\2\2\2"+
		"\3-\3\2\2\2\3/\3\2\2\2\3\61\3\2\2\2\3\63\3\2\2\2\3\65\3\2\2\2\3\67\3\2"+
		"\2\2\39\3\2\2\2\3;\3\2\2\2\3=\3\2\2\2\3?\3\2\2\2\3A\3\2\2\2\3C\3\2\2\2"+
		"\3E\3\2\2\2\3G\3\2\2\2\3I\3\2\2\2\3K\3\2\2\2\3M\3\2\2\2\3O\3\2\2\2\3Q"+
		"\3\2\2\2\3S\3\2\2\2\4]\3\2\2\2\4_\3\2\2\2\5a\3\2\2\2\7p\3\2\2\2\t\u0084"+
		"\3\2\2\2\13\u0091\3\2\2\2\r\u00aa\3\2\2\2\17\u00b1\3\2\2\2\21\u00b5\3"+
		"\2\2\2\23\u00b9\3\2\2\2\25\u00c3\3\2\2\2\27\u00cc\3\2\2\2\31\u00d0\3\2"+
		"\2\2\33\u00d7\3\2\2\2\35\u00e1\3\2\2\2\37\u00e8\3\2\2\2!\u00f5\3\2\2\2"+
		"#\u00ff\3\2\2\2%\u0104\3\2\2\2\'\u010c\3\2\2\2)\u0110\3\2\2\2+\u0118\3"+
		"\2\2\2-\u011c\3\2\2\2/\u0129\3\2\2\2\61\u013e\3\2\2\2\63\u0153\3\2\2\2"+
		"\65\u0159\3\2\2\2\67\u0165\3\2\2\29\u0171\3\2\2\2;\u0176\3\2\2\2=\u017d"+
		"\3\2\2\2?\u0187\3\2\2\2A\u0190\3\2\2\2C\u019a\3\2\2\2E\u01a1\3\2\2\2G"+
		"\u01a5\3\2\2\2I\u01aa\3\2\2\2K\u01af\3\2\2\2M\u01b1\3\2\2\2O\u01c3\3\2"+
		"\2\2Q\u01c5\3\2\2\2S\u01cc\3\2\2\2U\u01d0\3\2\2\2W\u01d2\3\2\2\2Y\u01d8"+
		"\3\2\2\2[\u01db\3\2\2\2]\u01dd\3\2\2\2_\u01e2\3\2\2\2ab\7>\2\2bc\7#\2"+
		"\2cd\7/\2\2de\7/\2\2ei\3\2\2\2fh\13\2\2\2gf\3\2\2\2hk\3\2\2\2ij\3\2\2"+
		"\2ig\3\2\2\2jl\3\2\2\2ki\3\2\2\2lm\7/\2\2mn\7/\2\2no\7@\2\2o\6\3\2\2\2"+
		"pq\7>\2\2qr\7#\2\2rs\7]\2\2st\7E\2\2tu\7F\2\2uv\7C\2\2vw\7V\2\2wx\7C\2"+
		"\2xy\7]\2\2y}\3\2\2\2z|\13\2\2\2{z\3\2\2\2|\177\3\2\2\2}~\3\2\2\2}{\3"+
		"\2\2\2~\u0080\3\2\2\2\177}\3\2\2\2\u0080\u0081\7_\2\2\u0081\u0082\7_\2"+
		"\2\u0082\u0083\7@\2\2\u0083\b\3\2\2\2\u0084\u0085\7>\2\2\u0085\u0086\7"+
		"#\2\2\u0086\u008a\3\2\2\2\u0087\u0089\13\2\2\2\u0088\u0087\3\2\2\2\u0089"+
		"\u008c\3\2\2\2\u008a\u008b\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008d\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008d\u008e\7@\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u0090\b\4\2\2\u0090\n\3\2\2\2\u0091\u0092\7(\2\2\u0092\u0093\5Q(\2\u0093"+
		"\u0094\7=\2\2\u0094\f\3\2\2\2\u0095\u0096\7(\2\2\u0096\u0097\7%\2\2\u0097"+
		"\u0099\3\2\2\2\u0098\u009a\5W+\2\u0099\u0098\3\2\2\2\u009a\u009b\3\2\2"+
		"\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\3\2\2\2\u009d\u009e"+
		"\7=\2\2\u009e\u00ab\3\2\2\2\u009f\u00a0\7(\2\2\u00a0\u00a1\7%\2\2\u00a1"+
		"\u00a2\7z\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a5\5U*\2\u00a4\u00a3\3\2\2"+
		"\2\u00a5\u00a6\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8"+
		"\3\2\2\2\u00a8\u00a9\7=\2\2\u00a9\u00ab\3\2\2\2\u00aa\u0095\3\2\2\2\u00aa"+
		"\u009f\3\2\2\2\u00ab\16\3\2\2\2\u00ac\u00b2\t\2\2\2\u00ad\u00af\7\17\2"+
		"\2\u00ae\u00ad\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2"+
		"\7\f\2\2\u00b1\u00ac\3\2\2\2\u00b1\u00ae\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3"+
		"\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\20\3\2\2\2\u00b5\u00b6\7>\2\2"+
		"\u00b6\u00b7\3\2\2\2\u00b7\u00b8\b\b\3\2\u00b8\22\3\2\2\2\u00b9\u00ba"+
		"\7>\2\2\u00ba\u00bb\7A\2\2\u00bb\u00bc\7z\2\2\u00bc\u00bd\7o\2\2\u00bd"+
		"\u00be\7n\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\5S)\2\u00c0\u00c1\3\2\2"+
		"\2\u00c1\u00c2\b\t\3\2\u00c2\24\3\2\2\2\u00c3\u00c4\7>\2\2\u00c4\u00c5"+
		"\7A\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\5Q(\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"\u00c9\b\n\4\2\u00c9\u00ca\b\n\5\2\u00ca\26\3\2\2\2\u00cb\u00cd\n\3\2"+
		"\2\u00cc\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf"+
		"\3\2\2\2\u00cf\30\3\2\2\2\u00d0\u00d1\7c\2\2\u00d1\u00d2\7u\2\2\u00d2"+
		"\u00d3\7u\2\2\u00d3\u00d4\7g\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7v\2\2"+
		"\u00d6\32\3\2\2\2\u00d7\u00d8\7c\2\2\u00d8\u00d9\7v\2\2\u00d9\u00da\7"+
		"v\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7d\2\2\u00dd\u00de"+
		"\7w\2\2\u00de\u00df\7v\2\2\u00df\u00e0\7g\2\2\u00e0\34\3\2\2\2\u00e1\u00e2"+
		"\7e\2\2\u00e2\u00e3\7j\2\2\u00e3\u00e4\7q\2\2\u00e4\u00e5\7k\2\2\u00e5"+
		"\u00e6\7e\2\2\u00e6\u00e7\7g\2\2\u00e7\36\3\2\2\2\u00e8\u00e9\7e\2\2\u00e9"+
		"\u00ea\7q\2\2\u00ea\u00eb\7p\2\2\u00eb\u00ec\7h\2\2\u00ec\u00ed\7q\2\2"+
		"\u00ed\u00ee\7t\2\2\u00ee\u00ef\7o\2\2\u00ef\u00f0\7c\2\2\u00f0\u00f1"+
		"\7p\2\2\u00f1\u00f2\7e\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7?\2\2\u00f4"+
		" \3\2\2\2\u00f5\u00f6\7e\2\2\u00f6\u00f7\7q\2\2\u00f7\u00f8\7p\2\2\u00f8"+
		"\u00f9\7v\2\2\u00f9\u00fa\7c\2\2\u00fa\u00fb\7k\2\2\u00fb\u00fc\7p\2\2"+
		"\u00fc\u00fd\7u\2\2\u00fd\u00fe\7?\2\2\u00fe\"\3\2\2\2\u00ff\u0100\7f"+
		"\2\2\u0100\u0101\7g\2\2\u0101\u0102\7u\2\2\u0102\u0103\7e\2\2\u0103$\3"+
		"\2\2\2\u0104\u0105\7g\2\2\u0105\u0106\7n\2\2\u0106\u0107\7g\2\2\u0107"+
		"\u0108\7o\2\2\u0108\u0109\7g\2\2\u0109\u010a\7p\2\2\u010a\u010b\7v\2\2"+
		"\u010b&\3\2\2\2\u010c\u010d\7k\2\2\u010d\u010e\7f\2\2\u010e\u010f\7?\2"+
		"\2\u010f(\3\2\2\2\u0110\u0111\7k\2\2\u0111\u0112\7p\2\2\u0112\u0113\7"+
		"e\2\2\u0113\u0114\7n\2\2\u0114\u0115\7w\2\2\u0115\u0116\7f\2\2\u0116\u0117"+
		"\7g\2\2\u0117*\3\2\2\2\u0118\u0119\7n\2\2\u0119\u011a\7g\2\2\u011a\u011b"+
		"\7v\2\2\u011b,\3\2\2\2\u011c\u011d\7k\2\2\u011d\u011e\7u\2\2\u011e\u011f"+
		"\7O\2\2\u011f\u0120\7c\2\2\u0120\u0121\7p\2\2\u0121\u0122\7f\2\2\u0122"+
		"\u0123\7c\2\2\u0123\u0124\7v\2\2\u0124\u0125\7q\2\2\u0125\u0126\7t\2\2"+
		"\u0126\u0127\7{\2\2\u0127\u0128\7?\2\2\u0128.\3\2\2\2\u0129\u012a\7o\2"+
		"\2\u012a\u012b\7c\2\2\u012b\u012c\7z\2\2\u012c\u012d\7k\2\2\u012d\u012e"+
		"\7o\2\2\u012e\u012f\7w\2\2\u012f\u0130\7o\2\2\u0130\u0131\7O\2\2\u0131"+
		"\u0132\7w\2\2\u0132\u0133\7n\2\2\u0133\u0134\7v\2\2\u0134\u0135\7k\2\2"+
		"\u0135\u0136\7r\2\2\u0136\u0137\7n\2\2\u0137\u0138\7k\2\2\u0138\u0139"+
		"\7e\2\2\u0139\u013a\7k\2\2\u013a\u013b\7v\2\2\u013b\u013c\7{\2\2\u013c"+
		"\u013d\7?\2\2\u013d\60\3\2\2\2\u013e\u013f\7o\2\2\u013f\u0140\7k\2\2\u0140"+
		"\u0141\7p\2\2\u0141\u0142\7k\2\2\u0142\u0143\7o\2\2\u0143\u0144\7w\2\2"+
		"\u0144\u0145\7o\2\2\u0145\u0146\7O\2\2\u0146\u0147\7w\2\2\u0147\u0148"+
		"\7n\2\2\u0148\u0149\7v\2\2\u0149\u014a\7k\2\2\u014a\u014b\7r\2\2\u014b"+
		"\u014c\7n\2\2\u014c\u014d\7k\2\2\u014d\u014e\7e\2\2\u014e\u014f\7k\2\2"+
		"\u014f\u0150\7v\2\2\u0150\u0151\7{\2\2\u0151\u0152\7?\2\2\u0152\62\3\2"+
		"\2\2\u0153\u0154\7p\2\2\u0154\u0155\7c\2\2\u0155\u0156\7o\2\2\u0156\u0157"+
		"\7g\2\2\u0157\u0158\7?\2\2\u0158\64\3\2\2\2\u0159\u015a\7k\2\2\u015a\u015b"+
		"\7u\2\2\u015b\u015c\7Q\2\2\u015c\u015d\7r\2\2\u015d\u015e\7v\2\2\u015e"+
		"\u015f\7k\2\2\u015f\u0160\7q\2\2\u0160\u0161\7p\2\2\u0161\u0162\7c\2\2"+
		"\u0162\u0163\7n\2\2\u0163\u0164\7?\2\2\u0164\66\3\2\2\2\u0165\u0166\7"+
		"r\2\2\u0166\u0167\7t\2\2\u0167\u0168\7q\2\2\u0168\u0169\7j\2\2\u0169\u016a"+
		"\7k\2\2\u016a\u016b\7d\2\2\u016b\u016c\7k\2\2\u016c\u016d\7v\2\2\u016d"+
		"\u016e\7g\2\2\u016e\u016f\7f\2\2\u016f\u0170\7?\2\2\u01708\3\2\2\2\u0171"+
		"\u0172\7t\2\2\u0172\u0173\7g\2\2\u0173\u0174\7h\2\2\u0174\u0175\7?\2\2"+
		"\u0175:\3\2\2\2\u0176\u0177\7t\2\2\u0177\u0178\7g\2\2\u0178\u0179\7r\2"+
		"\2\u0179\u017a\7q\2\2\u017a\u017b\7t\2\2\u017b\u017c\7v\2\2\u017c<\3\2"+
		"\2\2\u017d\u017e\7u\2\2\u017e\u017f\7v\2\2\u017f\u0180\7t\2\2\u0180\u0181"+
		"\7g\2\2\u0181\u0182\7p\2\2\u0182\u0183\7i\2\2\u0183\u0184\7v\2\2\u0184"+
		"\u0185\7j\2\2\u0185\u0186\7?\2\2\u0186>\3\2\2\2\u0187\u0188\7v\2\2\u0188"+
		"\u0189\7g\2\2\u0189\u018a\7o\2\2\u018a\u018b\7r\2\2\u018b\u018c\7n\2\2"+
		"\u018c\u018d\7c\2\2\u018d\u018e\7v\2\2\u018e\u018f\7g\2\2\u018f@\3\2\2"+
		"\2\u0190\u0191\7f\2\2\u0191\u0192\7c\2\2\u0192\u0193\7v\2\2\u0193\u0194"+
		"\7c\2\2\u0194\u0195\7v\2\2\u0195\u0196\7{\2\2\u0196\u0197\7r\2\2\u0197"+
		"\u0198\7g\2\2\u0198\u0199\7?\2\2\u0199B\3\2\2\2\u019a\u019b\7x\2\2\u019b"+
		"\u019c\7c\2\2\u019c\u019d\7n\2\2\u019d\u019e\7w\2\2\u019e\u019f\7g\2\2"+
		"\u019f\u01a0\7?\2\2\u01a0D\3\2\2\2\u01a1\u01a2\7@\2\2\u01a2\u01a3\3\2"+
		"\2\2\u01a3\u01a4\b\"\6\2\u01a4F\3\2\2\2\u01a5\u01a6\7A\2\2\u01a6\u01a7"+
		"\7@\2\2\u01a7\u01a8\3\2\2\2\u01a8\u01a9\b#\6\2\u01a9H\3\2\2\2\u01aa\u01ab"+
		"\7\61\2\2\u01ab\u01ac\7@\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01ae\b$\6\2\u01ae"+
		"J\3\2\2\2\u01af\u01b0\7\61\2\2\u01b0L\3\2\2\2\u01b1\u01b2\7?\2\2\u01b2"+
		"N\3\2\2\2\u01b3\u01b7\7$\2\2\u01b4\u01b6\n\4\2\2\u01b5\u01b4\3\2\2\2\u01b6"+
		"\u01b9\3\2\2\2\u01b7\u01b5\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01ba\3\2"+
		"\2\2\u01b9\u01b7\3\2\2\2\u01ba\u01c4\7$\2\2\u01bb\u01bf\7)\2\2\u01bc\u01be"+
		"\n\5\2\2\u01bd\u01bc\3\2\2\2\u01be\u01c1\3\2\2\2\u01bf\u01bd\3\2\2\2\u01bf"+
		"\u01c0\3\2\2\2\u01c0\u01c2\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c2\u01c4\7)"+
		"\2\2\u01c3\u01b3\3\2\2\2\u01c3\u01bb\3\2\2\2\u01c4P\3\2\2\2\u01c5\u01c9"+
		"\5[-\2\u01c6\u01c8\5Y,\2\u01c7\u01c6\3\2\2\2\u01c8\u01cb\3\2\2\2\u01c9"+
		"\u01c7\3\2\2\2\u01c9\u01ca\3\2\2\2\u01caR\3\2\2\2\u01cb\u01c9\3\2\2\2"+
		"\u01cc\u01cd\t\6\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01cf\b)\2\2\u01cfT\3\2"+
		"\2\2\u01d0\u01d1\t\7\2\2\u01d1V\3\2\2\2\u01d2\u01d3\t\b\2\2\u01d3X\3\2"+
		"\2\2\u01d4\u01d9\5[-\2\u01d5\u01d9\t\t\2\2\u01d6\u01d9\5W+\2\u01d7\u01d9"+
		"\t\n\2\2\u01d8\u01d4\3\2\2\2\u01d8\u01d5\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d8"+
		"\u01d7\3\2\2\2\u01d9Z\3\2\2\2\u01da\u01dc\t\13\2\2\u01db\u01da\3\2\2\2"+
		"\u01dc\\\3\2\2\2\u01dd\u01de\7A\2\2\u01de\u01df\7@\2\2\u01df\u01e0\3\2"+
		"\2\2\u01e0\u01e1\b.\6\2\u01e1^\3\2\2\2\u01e2\u01e3\13\2\2\2\u01e3\u01e4"+
		"\3\2\2\2\u01e4\u01e5\b/\4\2\u01e5`\3\2\2\2\25\2\3\4i}\u008a\u009b\u00a6"+
		"\u00aa\u00ae\u00b1\u00b3\u00ce\u01b7\u01bf\u01c3\u01c9\u01d8\u01db\7\b"+
		"\2\2\7\3\2\5\2\2\7\4\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}