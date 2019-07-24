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
		TEXT=9, TEMPLATE=10, INCLUDE=11, CHOICE=12, LET=13, ASSERT=14, REPORT=15, 
		DESC=16, ELEMENT=17, ATTRIBUTE=18, REFATTR=19, NAMEATTR=20, TYPEATTR=21, 
		MINOCCURSATTR=22, MAXOCCURSATTR=23, CONFATTR=24, MANDATTR=25, OPTATTR=26, 
		PROHIBITED=27, IDATTR=28, VALUEATTR=29, CONTAINSATTR=30, CLOSE=31, SPECIAL_CLOSE=32, 
		SLASH_CLOSE=33, SLASH=34, EQUALS=35, AttrValue=36, Name=37, S=38, PI=39;
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
		"SPECIAL_OPEN", "TEXT", "TEMPLATE", "INCLUDE", "CHOICE", "LET", "ASSERT", 
		"REPORT", "DESC", "ELEMENT", "ATTRIBUTE", "REFATTR", "NAMEATTR", "TYPEATTR", 
		"MINOCCURSATTR", "MAXOCCURSATTR", "CONFATTR", "MANDATTR", "OPTATTR", "PROHIBITED", 
		"IDATTR", "VALUEATTR", "CONTAINSATTR", "CLOSE", "SPECIAL_CLOSE", "SLASH_CLOSE", 
		"SLASH", "EQUALS", "AttrValue", "Name", "S", "HEXDIGIT", "DIGIT", "NameChar", 
		"NameStartChar", "PI", "IGNORE"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'<'", null, null, "'template'", 
		"'include'", "'choice'", "'let'", "'assert'", "'report'", "'desc'", "'element'", 
		"'attribute'", "'ref='", "'name='", "'datatype='", "'minimumMultiplicity='", 
		"'maximumMultiplicity='", "'conformance='", "'isMandatory='", "'isOptional='", 
		"'prohibited='", "'id='", "'value='", "'contains='", "'>'", null, "'/>'", 
		"'/'", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMENT", "CDATA", "DTD", "EntityRef", "CharRef", "SEA_WS", "OPEN", 
		"XMLDeclOpen", "TEXT", "TEMPLATE", "INCLUDE", "CHOICE", "LET", "ASSERT", 
		"REPORT", "DESC", "ELEMENT", "ATTRIBUTE", "REFATTR", "NAMEATTR", "TYPEATTR", 
		"MINOCCURSATTR", "MAXOCCURSATTR", "CONFATTR", "MANDATTR", "OPTATTR", "PROHIBITED", 
		"IDATTR", "VALUEATTR", "CONTAINSATTR", "CLOSE", "SPECIAL_CLOSE", "SLASH_CLOSE", 
		"SLASH", "EQUALS", "AttrValue", "Name", "S", "PI"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2)\u01da\b\1\b\1\b"+
		"\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"+
		"\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"+
		"\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30"+
		"\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37"+
		"\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t"+
		"*\4+\t+\4,\t,\4-\t-\4.\t.\3\2\3\2\3\2\3\2\3\2\3\2\7\2f\n\2\f\2\16\2i\13"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3z\n"+
		"\3\f\3\16\3}\13\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4\u0087\n\4\f\4\16"+
		"\4\u008a\13\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\6\6\u0098"+
		"\n\6\r\6\16\6\u0099\3\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6\u00a3\n\6\r\6\16\6"+
		"\u00a4\3\6\3\6\5\6\u00a9\n\6\3\7\3\7\5\7\u00ad\n\7\3\7\6\7\u00b0\n\7\r"+
		"\7\16\7\u00b1\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\6\13\u00cb\n\13\r\13\16\13\u00cc"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3"+
		"\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3%\3%\3&\3&\7&\u01aa\n&\f&\16&\u01ad\13"+
		"&\3&\3&\3&\7&\u01b2\n&\f&\16&\u01b5\13&\3&\5&\u01b8\n&\3\'\3\'\7\'\u01bc"+
		"\n\'\f\'\16\'\u01bf\13\'\3(\3(\3(\3(\3)\3)\3*\3*\3+\3+\3+\3+\5+\u01cd"+
		"\n+\3,\5,\u01d0\n,\3-\3-\3-\3-\3-\3.\3.\3.\3.\5g{\u0088\2/\5\3\7\4\t\5"+
		"\13\6\r\7\17\b\21\t\23\n\25\2\27\13\31\f\33\r\35\16\37\17!\20#\21%\22"+
		"\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35=\36?\37A C!E\"G"+
		"#I$K%M&O\'Q(S\2U\2W\2Y\2[)]\2\5\2\3\4\f\4\2\13\13\"\"\4\2((>>\4\2$$>>"+
		"\4\2))>>\5\2\13\f\17\17\"\"\5\2\62;CHch\3\2\62;\4\2/\60aa\5\2\u00b9\u00b9"+
		"\u0302\u0371\u2041\u2042\n\2<<C\\c|\u2072\u2191\u2c02\u2ff1\u3003\ud801"+
		"\uf902\ufdd1\ufdf2\uffff\2\u01e4\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2"+
		"\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3"+
		"\2\2\2\2\27\3\2\2\2\3\31\3\2\2\2\3\33\3\2\2\2\3\35\3\2\2\2\3\37\3\2\2"+
		"\2\3!\3\2\2\2\3#\3\2\2\2\3%\3\2\2\2\3\'\3\2\2\2\3)\3\2\2\2\3+\3\2\2\2"+
		"\3-\3\2\2\2\3/\3\2\2\2\3\61\3\2\2\2\3\63\3\2\2\2\3\65\3\2\2\2\3\67\3\2"+
		"\2\2\39\3\2\2\2\3;\3\2\2\2\3=\3\2\2\2\3?\3\2\2\2\3A\3\2\2\2\3C\3\2\2\2"+
		"\3E\3\2\2\2\3G\3\2\2\2\3I\3\2\2\2\3K\3\2\2\2\3M\3\2\2\2\3O\3\2\2\2\3Q"+
		"\3\2\2\2\4[\3\2\2\2\4]\3\2\2\2\5_\3\2\2\2\7n\3\2\2\2\t\u0082\3\2\2\2\13"+
		"\u008f\3\2\2\2\r\u00a8\3\2\2\2\17\u00af\3\2\2\2\21\u00b3\3\2\2\2\23\u00b7"+
		"\3\2\2\2\25\u00c1\3\2\2\2\27\u00ca\3\2\2\2\31\u00ce\3\2\2\2\33\u00d7\3"+
		"\2\2\2\35\u00df\3\2\2\2\37\u00e6\3\2\2\2!\u00ea\3\2\2\2#\u00f1\3\2\2\2"+
		"%\u00f8\3\2\2\2\'\u00fd\3\2\2\2)\u0105\3\2\2\2+\u010f\3\2\2\2-\u0114\3"+
		"\2\2\2/\u011a\3\2\2\2\61\u0124\3\2\2\2\63\u0139\3\2\2\2\65\u014e\3\2\2"+
		"\2\67\u015b\3\2\2\29\u0168\3\2\2\2;\u0174\3\2\2\2=\u0180\3\2\2\2?\u0184"+
		"\3\2\2\2A\u018b\3\2\2\2C\u0195\3\2\2\2E\u0199\3\2\2\2G\u019e\3\2\2\2I"+
		"\u01a3\3\2\2\2K\u01a5\3\2\2\2M\u01b7\3\2\2\2O\u01b9\3\2\2\2Q\u01c0\3\2"+
		"\2\2S\u01c4\3\2\2\2U\u01c6\3\2\2\2W\u01cc\3\2\2\2Y\u01cf\3\2\2\2[\u01d1"+
		"\3\2\2\2]\u01d6\3\2\2\2_`\7>\2\2`a\7#\2\2ab\7/\2\2bc\7/\2\2cg\3\2\2\2"+
		"df\13\2\2\2ed\3\2\2\2fi\3\2\2\2gh\3\2\2\2ge\3\2\2\2hj\3\2\2\2ig\3\2\2"+
		"\2jk\7/\2\2kl\7/\2\2lm\7@\2\2m\6\3\2\2\2no\7>\2\2op\7#\2\2pq\7]\2\2qr"+
		"\7E\2\2rs\7F\2\2st\7C\2\2tu\7V\2\2uv\7C\2\2vw\7]\2\2w{\3\2\2\2xz\13\2"+
		"\2\2yx\3\2\2\2z}\3\2\2\2{|\3\2\2\2{y\3\2\2\2|~\3\2\2\2}{\3\2\2\2~\177"+
		"\7_\2\2\177\u0080\7_\2\2\u0080\u0081\7@\2\2\u0081\b\3\2\2\2\u0082\u0083"+
		"\7>\2\2\u0083\u0084\7#\2\2\u0084\u0088\3\2\2\2\u0085\u0087\13\2\2\2\u0086"+
		"\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0089\3\2\2\2\u0088\u0086\3\2"+
		"\2\2\u0089\u008b\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008c\7@\2\2\u008c"+
		"\u008d\3\2\2\2\u008d\u008e\b\4\2\2\u008e\n\3\2\2\2\u008f\u0090\7(\2\2"+
		"\u0090\u0091\5O\'\2\u0091\u0092\7=\2\2\u0092\f\3\2\2\2\u0093\u0094\7("+
		"\2\2\u0094\u0095\7%\2\2\u0095\u0097\3\2\2\2\u0096\u0098\5U*\2\u0097\u0096"+
		"\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\u009b\3\2\2\2\u009b\u009c\7=\2\2\u009c\u00a9\3\2\2\2\u009d\u009e\7(\2"+
		"\2\u009e\u009f\7%\2\2\u009f\u00a0\7z\2\2\u00a0\u00a2\3\2\2\2\u00a1\u00a3"+
		"\5S)\2\u00a2\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\7=\2\2\u00a7\u00a9\3\2"+
		"\2\2\u00a8\u0093\3\2\2\2\u00a8\u009d\3\2\2\2\u00a9\16\3\2\2\2\u00aa\u00b0"+
		"\t\2\2\2\u00ab\u00ad\7\17\2\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2"+
		"\u00ad\u00ae\3\2\2\2\u00ae\u00b0\7\f\2\2\u00af\u00aa\3\2\2\2\u00af\u00ac"+
		"\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\20\3\2\2\2\u00b3\u00b4\7>\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\b\b\3\2"+
		"\u00b6\22\3\2\2\2\u00b7\u00b8\7>\2\2\u00b8\u00b9\7A\2\2\u00b9\u00ba\7"+
		"z\2\2\u00ba\u00bb\7o\2\2\u00bb\u00bc\7n\2\2\u00bc\u00bd\3\2\2\2\u00bd"+
		"\u00be\5Q(\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\b\t\3\2\u00c0\24\3\2\2\2"+
		"\u00c1\u00c2\7>\2\2\u00c2\u00c3\7A\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5"+
		"\5O\'\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\b\n\4\2\u00c7\u00c8\b\n\5\2\u00c8"+
		"\26\3\2\2\2\u00c9\u00cb\n\3\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00cc\3\2\2"+
		"\2\u00cc\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\30\3\2\2\2\u00ce\u00cf"+
		"\7v\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7o\2\2\u00d1\u00d2\7r\2\2\u00d2"+
		"\u00d3\7n\2\2\u00d3\u00d4\7c\2\2\u00d4\u00d5\7v\2\2\u00d5\u00d6\7g\2\2"+
		"\u00d6\32\3\2\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9\7p\2\2\u00d9\u00da\7"+
		"e\2\2\u00da\u00db\7n\2\2\u00db\u00dc\7w\2\2\u00dc\u00dd\7f\2\2\u00dd\u00de"+
		"\7g\2\2\u00de\34\3\2\2\2\u00df\u00e0\7e\2\2\u00e0\u00e1\7j\2\2\u00e1\u00e2"+
		"\7q\2\2\u00e2\u00e3\7k\2\2\u00e3\u00e4\7e\2\2\u00e4\u00e5\7g\2\2\u00e5"+
		"\36\3\2\2\2\u00e6\u00e7\7n\2\2\u00e7\u00e8\7g\2\2\u00e8\u00e9\7v\2\2\u00e9"+
		" \3\2\2\2\u00ea\u00eb\7c\2\2\u00eb\u00ec\7u\2\2\u00ec\u00ed\7u\2\2\u00ed"+
		"\u00ee\7g\2\2\u00ee\u00ef\7t\2\2\u00ef\u00f0\7v\2\2\u00f0\"\3\2\2\2\u00f1"+
		"\u00f2\7t\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7r\2\2\u00f4\u00f5\7q\2\2"+
		"\u00f5\u00f6\7t\2\2\u00f6\u00f7\7v\2\2\u00f7$\3\2\2\2\u00f8\u00f9\7f\2"+
		"\2\u00f9\u00fa\7g\2\2\u00fa\u00fb\7u\2\2\u00fb\u00fc\7e\2\2\u00fc&\3\2"+
		"\2\2\u00fd\u00fe\7g\2\2\u00fe\u00ff\7n\2\2\u00ff\u0100\7g\2\2\u0100\u0101"+
		"\7o\2\2\u0101\u0102\7g\2\2\u0102\u0103\7p\2\2\u0103\u0104\7v\2\2\u0104"+
		"(\3\2\2\2\u0105\u0106\7c\2\2\u0106\u0107\7v\2\2\u0107\u0108\7v\2\2\u0108"+
		"\u0109\7t\2\2\u0109\u010a\7k\2\2\u010a\u010b\7d\2\2\u010b\u010c\7w\2\2"+
		"\u010c\u010d\7v\2\2\u010d\u010e\7g\2\2\u010e*\3\2\2\2\u010f\u0110\7t\2"+
		"\2\u0110\u0111\7g\2\2\u0111\u0112\7h\2\2\u0112\u0113\7?\2\2\u0113,\3\2"+
		"\2\2\u0114\u0115\7p\2\2\u0115\u0116\7c\2\2\u0116\u0117\7o\2\2\u0117\u0118"+
		"\7g\2\2\u0118\u0119\7?\2\2\u0119.\3\2\2\2\u011a\u011b\7f\2\2\u011b\u011c"+
		"\7c\2\2\u011c\u011d\7v\2\2\u011d\u011e\7c\2\2\u011e\u011f\7v\2\2\u011f"+
		"\u0120\7{\2\2\u0120\u0121\7r\2\2\u0121\u0122\7g\2\2\u0122\u0123\7?\2\2"+
		"\u0123\60\3\2\2\2\u0124\u0125\7o\2\2\u0125\u0126\7k\2\2\u0126\u0127\7"+
		"p\2\2\u0127\u0128\7k\2\2\u0128\u0129\7o\2\2\u0129\u012a\7w\2\2\u012a\u012b"+
		"\7o\2\2\u012b\u012c\7O\2\2\u012c\u012d\7w\2\2\u012d\u012e\7n\2\2\u012e"+
		"\u012f\7v\2\2\u012f\u0130\7k\2\2\u0130\u0131\7r\2\2\u0131\u0132\7n\2\2"+
		"\u0132\u0133\7k\2\2\u0133\u0134\7e\2\2\u0134\u0135\7k\2\2\u0135\u0136"+
		"\7v\2\2\u0136\u0137\7{\2\2\u0137\u0138\7?\2\2\u0138\62\3\2\2\2\u0139\u013a"+
		"\7o\2\2\u013a\u013b\7c\2\2\u013b\u013c\7z\2\2\u013c\u013d\7k\2\2\u013d"+
		"\u013e\7o\2\2\u013e\u013f\7w\2\2\u013f\u0140\7o\2\2\u0140\u0141\7O\2\2"+
		"\u0141\u0142\7w\2\2\u0142\u0143\7n\2\2\u0143\u0144\7v\2\2\u0144\u0145"+
		"\7k\2\2\u0145\u0146\7r\2\2\u0146\u0147\7n\2\2\u0147\u0148\7k\2\2\u0148"+
		"\u0149\7e\2\2\u0149\u014a\7k\2\2\u014a\u014b\7v\2\2\u014b\u014c\7{\2\2"+
		"\u014c\u014d\7?\2\2\u014d\64\3\2\2\2\u014e\u014f\7e\2\2\u014f\u0150\7"+
		"q\2\2\u0150\u0151\7p\2\2\u0151\u0152\7h\2\2\u0152\u0153\7q\2\2\u0153\u0154"+
		"\7t\2\2\u0154\u0155\7o\2\2\u0155\u0156\7c\2\2\u0156\u0157\7p\2\2\u0157"+
		"\u0158\7e\2\2\u0158\u0159\7g\2\2\u0159\u015a\7?\2\2\u015a\66\3\2\2\2\u015b"+
		"\u015c\7k\2\2\u015c\u015d\7u\2\2\u015d\u015e\7O\2\2\u015e\u015f\7c\2\2"+
		"\u015f\u0160\7p\2\2\u0160\u0161\7f\2\2\u0161\u0162\7c\2\2\u0162\u0163"+
		"\7v\2\2\u0163\u0164\7q\2\2\u0164\u0165\7t\2\2\u0165\u0166\7{\2\2\u0166"+
		"\u0167\7?\2\2\u01678\3\2\2\2\u0168\u0169\7k\2\2\u0169\u016a\7u\2\2\u016a"+
		"\u016b\7Q\2\2\u016b\u016c\7r\2\2\u016c\u016d\7v\2\2\u016d\u016e\7k\2\2"+
		"\u016e\u016f\7q\2\2\u016f\u0170\7p\2\2\u0170\u0171\7c\2\2\u0171\u0172"+
		"\7n\2\2\u0172\u0173\7?\2\2\u0173:\3\2\2\2\u0174\u0175\7r\2\2\u0175\u0176"+
		"\7t\2\2\u0176\u0177\7q\2\2\u0177\u0178\7j\2\2\u0178\u0179\7k\2\2\u0179"+
		"\u017a\7d\2\2\u017a\u017b\7k\2\2\u017b\u017c\7v\2\2\u017c\u017d\7g\2\2"+
		"\u017d\u017e\7f\2\2\u017e\u017f\7?\2\2\u017f<\3\2\2\2\u0180\u0181\7k\2"+
		"\2\u0181\u0182\7f\2\2\u0182\u0183\7?\2\2\u0183>\3\2\2\2\u0184\u0185\7"+
		"x\2\2\u0185\u0186\7c\2\2\u0186\u0187\7n\2\2\u0187\u0188\7w\2\2\u0188\u0189"+
		"\7g\2\2\u0189\u018a\7?\2\2\u018a@\3\2\2\2\u018b\u018c\7e\2\2\u018c\u018d"+
		"\7q\2\2\u018d\u018e\7p\2\2\u018e\u018f\7v\2\2\u018f\u0190\7c\2\2\u0190"+
		"\u0191\7k\2\2\u0191\u0192\7p\2\2\u0192\u0193\7u\2\2\u0193\u0194\7?\2\2"+
		"\u0194B\3\2\2\2\u0195\u0196\7@\2\2\u0196\u0197\3\2\2\2\u0197\u0198\b!"+
		"\6\2\u0198D\3\2\2\2\u0199\u019a\7A\2\2\u019a\u019b\7@\2\2\u019b\u019c"+
		"\3\2\2\2\u019c\u019d\b\"\6\2\u019dF\3\2\2\2\u019e\u019f\7\61\2\2\u019f"+
		"\u01a0\7@\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a2\b#\6\2\u01a2H\3\2\2\2\u01a3"+
		"\u01a4\7\61\2\2\u01a4J\3\2\2\2\u01a5\u01a6\7?\2\2\u01a6L\3\2\2\2\u01a7"+
		"\u01ab\7$\2\2\u01a8\u01aa\n\4\2\2\u01a9\u01a8\3\2\2\2\u01aa\u01ad\3\2"+
		"\2\2\u01ab\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01ae\3\2\2\2\u01ad"+
		"\u01ab\3\2\2\2\u01ae\u01b8\7$\2\2\u01af\u01b3\7)\2\2\u01b0\u01b2\n\5\2"+
		"\2\u01b1\u01b0\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b1\3\2\2\2\u01b3\u01b4"+
		"\3\2\2\2\u01b4\u01b6\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b6\u01b8\7)\2\2\u01b7"+
		"\u01a7\3\2\2\2\u01b7\u01af\3\2\2\2\u01b8N\3\2\2\2\u01b9\u01bd\5Y,\2\u01ba"+
		"\u01bc\5W+\2\u01bb\u01ba\3\2\2\2\u01bc\u01bf\3\2\2\2\u01bd\u01bb\3\2\2"+
		"\2\u01bd\u01be\3\2\2\2\u01beP\3\2\2\2\u01bf\u01bd\3\2\2\2\u01c0\u01c1"+
		"\t\6\2\2\u01c1\u01c2\3\2\2\2\u01c2\u01c3\b(\2\2\u01c3R\3\2\2\2\u01c4\u01c5"+
		"\t\7\2\2\u01c5T\3\2\2\2\u01c6\u01c7\t\b\2\2\u01c7V\3\2\2\2\u01c8\u01cd"+
		"\5Y,\2\u01c9\u01cd\t\t\2\2\u01ca\u01cd\5U*\2\u01cb\u01cd\t\n\2\2\u01cc"+
		"\u01c8\3\2\2\2\u01cc\u01c9\3\2\2\2\u01cc\u01ca\3\2\2\2\u01cc\u01cb\3\2"+
		"\2\2\u01cdX\3\2\2\2\u01ce\u01d0\t\13\2\2\u01cf\u01ce\3\2\2\2\u01d0Z\3"+
		"\2\2\2\u01d1\u01d2\7A\2\2\u01d2\u01d3\7@\2\2\u01d3\u01d4\3\2\2\2\u01d4"+
		"\u01d5\b-\6\2\u01d5\\\3\2\2\2\u01d6\u01d7\13\2\2\2\u01d7\u01d8\3\2\2\2"+
		"\u01d8\u01d9\b.\4\2\u01d9^\3\2\2\2\25\2\3\4g{\u0088\u0099\u00a4\u00a8"+
		"\u00ac\u00af\u00b1\u00cc\u01ab\u01b3\u01b7\u01bd\u01cc\u01cf\7\b\2\2\7"+
		"\3\2\5\2\2\7\4\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}