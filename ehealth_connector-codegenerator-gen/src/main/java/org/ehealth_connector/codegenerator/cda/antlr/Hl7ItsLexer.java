/*
 * The authorship of this project and accompanying materials is held by medshare GmbH, Switzerland.
 * All rights reserved. https://medshare.net
 *
 * Source code, documentation and other resources have been contributed by various people.
 * Project Team: https://gitlab.com/ehealth-connector/api/wikis/Team/
 * For exact developer information, please refer to the commit history of the forge.
 *
 * This code is made available under the terms of the Eclipse Public License v1.0.
 *
 * Accompanying materials are made available under the terms of the Creative Commons
 * Attribution-ShareAlike 4.0 License.
 *
 * This line is intended for UTF-8 encoding checks, do not modify/delete: äöüéè
 *
 */

package org.ehealth_connector.codegenerator.cda.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({ "all", "warnings", "unchecked", "unused", "cast" })
public class Hl7ItsLexer extends Lexer {
	static {
		RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION);
	}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache = new PredictionContextCache();
	public static final int COMMENT = 1, CDATA = 2, DTD = 3, EntityRef = 4, CharRef = 5, SEA_WS = 6,
			OPEN = 7, XMLDeclOpen = 8, TEXT = 9, ASSERT = 10, ATTRIBUTE = 11, CHOICE = 12,
			CONFATTR = 13, FLEXIBILITYATTR = 14, CONTAINSATTR = 15, DESC = 16, ELEMENT = 17,
			IDATTR = 18, INCLUDE = 19, LET = 20, MANDATTR = 21, MAXOCCURSATTR = 22,
			MINOCCURSATTR = 23, NAMEATTR = 24, OPTATTR = 25, PROHIBITED = 26, REFATTR = 27,
			REPORT = 28, STRENGTHATTR = 29, TEMPLATE = 30, TYPEATTR = 31, VALUEATTR = 32,
			VALUESETATTR = 33, VOCAB = 34, CLOSE = 35, SPECIAL_CLOSE = 36, SLASH_CLOSE = 37,
			SLASH = 38, EQUALS = 39, AttrValue = 40, Name = 41, S = 42, PI = 43;
	public static final int INSIDE = 1, PROC_INSTR = 2;
	public static String[] channelNames = { "DEFAULT_TOKEN_CHANNEL", "HIDDEN" };

	public static String[] modeNames = { "DEFAULT_MODE", "INSIDE", "PROC_INSTR" };

	public static final String[] ruleNames = { "COMMENT", "CDATA", "DTD", "EntityRef", "CharRef",
			"SEA_WS", "OPEN", "XMLDeclOpen", "SPECIAL_OPEN", "TEXT", "ASSERT", "ATTRIBUTE",
			"CHOICE", "CONFATTR", "FLEXIBILITYATTR", "CONTAINSATTR", "DESC", "ELEMENT", "IDATTR",
			"INCLUDE", "LET", "MANDATTR", "MAXOCCURSATTR", "MINOCCURSATTR", "NAMEATTR", "OPTATTR",
			"PROHIBITED", "REFATTR", "REPORT", "STRENGTHATTR", "TEMPLATE", "TYPEATTR", "VALUEATTR",
			"VALUESETATTR", "VOCAB", "CLOSE", "SPECIAL_CLOSE", "SLASH_CLOSE", "SLASH", "EQUALS",
			"AttrValue", "Name", "S", "HEXDIGIT", "DIGIT", "NameChar", "NameStartChar", "PI",
			"IGNORE" };

	private static final String[] _LITERAL_NAMES = { null, null, null, null, null, null, null,
			"'<'", null, null, "'assert'", "'attribute'", "'choice'", "'conformance='",
			"'flexibility='", "'contains='", "'desc'", "'element'", "'id='", "'include'", "'let'",
			"'isMandatory='", "'maximumMultiplicity='", "'minimumMultiplicity='", "'name='",
			"'isOptional='", "'prohibited='", "'ref='", "'report'", "'strength='", "'template'",
			"'datatype='", "'value='", "'valueSet='", "'vocabulary'", "'>'", null, "'/>'", "'/'",
			"'='" };
	private static final String[] _SYMBOLIC_NAMES = { null, "COMMENT", "CDATA", "DTD", "EntityRef",
			"CharRef", "SEA_WS", "OPEN", "XMLDeclOpen", "TEXT", "ASSERT", "ATTRIBUTE", "CHOICE",
			"CONFATTR", "FLEXIBILITYATTR", "CONTAINSATTR", "DESC", "ELEMENT", "IDATTR", "INCLUDE",
			"LET", "MANDATTR", "MAXOCCURSATTR", "MINOCCURSATTR", "NAMEATTR", "OPTATTR",
			"PROHIBITED", "REFATTR", "REPORT", "STRENGTHATTR", "TEMPLATE", "TYPEATTR", "VALUEATTR",
			"VALUESETATTR", "VOCAB", "CLOSE", "SPECIAL_CLOSE", "SLASH_CLOSE", "SLASH", "EQUALS",
			"AttrValue", "Name", "S", "PI" };
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

	public static final String _serializedATN = "\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2-\u020e\b\1\b\1\b"
			+ "\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n"
			+ "\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21"
			+ "\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30"
			+ "\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37"
			+ "\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t"
			+ "*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2"
			+ "\3\2\3\2\3\2\3\2\7\2n\n\2\f\2\16\2q\13\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3"
			+ "\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3\u0082\n\3\f\3\16\3\u0085\13\3\3\3\3"
			+ "\3\3\3\3\3\3\4\3\4\3\4\3\4\7\4\u008f\n\4\f\4\16\4\u0092\13\4\3\4\3\4\3"
			+ "\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\6\6\u00a0\n\6\r\6\16\6\u00a1\3"
			+ "\6\3\6\3\6\3\6\3\6\3\6\3\6\6\6\u00ab\n\6\r\6\16\6\u00ac\3\6\3\6\5\6\u00b1"
			+ "\n\6\3\7\3\7\5\7\u00b5\n\7\3\7\6\7\u00b8\n\7\r\7\16\7\u00b9\3\b\3\b\3"
			+ "\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n"
			+ "\3\n\3\n\3\13\6\13\u00d3\n\13\r\13\16\13\u00d4\3\f\3\f\3\f\3\f\3\f\3\f"
			+ "\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"
			+ "\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"
			+ "\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"
			+ "\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"
			+ "\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\25"
			+ "\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27"
			+ "\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"
			+ "\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"
			+ "\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"
			+ "\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32"
			+ "\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"
			+ "\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35"
			+ "\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37"
			+ "\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!"
			+ "\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3"
			+ "#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3&\3&\3&\3&\3&\3"
			+ "\'\3\'\3\'\3\'\3\'\3(\3(\3)\3)\3*\3*\7*\u01de\n*\f*\16*\u01e1\13*\3*\3"
			+ "*\3*\7*\u01e6\n*\f*\16*\u01e9\13*\3*\5*\u01ec\n*\3+\3+\7+\u01f0\n+\f+"
			+ "\16+\u01f3\13+\3,\3,\3,\3,\3-\3-\3.\3.\3/\3/\3/\3/\5/\u0201\n/\3\60\5"
			+ "\60\u0204\n\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3\62\5o\u0083\u0090"
			+ "\2\63\5\3\7\4\t\5\13\6\r\7\17\b\21\t\23\n\25\2\27\13\31\f\33\r\35\16\37"
			+ "\17!\20#\21%\22\'\23)\24+\25-\26/\27\61\30\63\31\65\32\67\339\34;\35="
			+ "\36?\37A C!E\"G#I$K%M&O\'Q(S)U*W+Y,[\2]\2_\2a\2c-e\2\5\2\3\4\f\4\2\13"
			+ "\13\"\"\4\2((>>\4\2$$>>\4\2))>>\5\2\13\f\17\17\"\"\5\2\62;CHch\3\2\62"
			+ ";\4\2/\60aa\5\2\u00b9\u00b9\u0302\u0371\u2041\u2042\n\2<<C\\c|\u2072\u2191"
			+ "\u2c02\u2ff1\u3003\ud801\uf902\ufdd1\ufdf2\uffff\2\u0218\2\5\3\2\2\2\2"
			+ "\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"
			+ "\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\3\31\3\2\2\2\3\33\3\2\2\2"
			+ "\3\35\3\2\2\2\3\37\3\2\2\2\3!\3\2\2\2\3#\3\2\2\2\3%\3\2\2\2\3\'\3\2\2"
			+ "\2\3)\3\2\2\2\3+\3\2\2\2\3-\3\2\2\2\3/\3\2\2\2\3\61\3\2\2\2\3\63\3\2\2"
			+ "\2\3\65\3\2\2\2\3\67\3\2\2\2\39\3\2\2\2\3;\3\2\2\2\3=\3\2\2\2\3?\3\2\2"
			+ "\2\3A\3\2\2\2\3C\3\2\2\2\3E\3\2\2\2\3G\3\2\2\2\3I\3\2\2\2\3K\3\2\2\2\3"
			+ "M\3\2\2\2\3O\3\2\2\2\3Q\3\2\2\2\3S\3\2\2\2\3U\3\2\2\2\3W\3\2\2\2\3Y\3"
			+ "\2\2\2\4c\3\2\2\2\4e\3\2\2\2\5g\3\2\2\2\7v\3\2\2\2\t\u008a\3\2\2\2\13"
			+ "\u0097\3\2\2\2\r\u00b0\3\2\2\2\17\u00b7\3\2\2\2\21\u00bb\3\2\2\2\23\u00bf"
			+ "\3\2\2\2\25\u00c9\3\2\2\2\27\u00d2\3\2\2\2\31\u00d6\3\2\2\2\33\u00dd\3"
			+ "\2\2\2\35\u00e7\3\2\2\2\37\u00ee\3\2\2\2!\u00fb\3\2\2\2#\u0108\3\2\2\2"
			+ "%\u0112\3\2\2\2\'\u0117\3\2\2\2)\u011f\3\2\2\2+\u0123\3\2\2\2-\u012b\3"
			+ "\2\2\2/\u012f\3\2\2\2\61\u013c\3\2\2\2\63\u0151\3\2\2\2\65\u0166\3\2\2"
			+ "\2\67\u016c\3\2\2\29\u0178\3\2\2\2;\u0184\3\2\2\2=\u0189\3\2\2\2?\u0190"
			+ "\3\2\2\2A\u019a\3\2\2\2C\u01a3\3\2\2\2E\u01ad\3\2\2\2G\u01b4\3\2\2\2I"
			+ "\u01be\3\2\2\2K\u01c9\3\2\2\2M\u01cd\3\2\2\2O\u01d2\3\2\2\2Q\u01d7\3\2"
			+ "\2\2S\u01d9\3\2\2\2U\u01eb\3\2\2\2W\u01ed\3\2\2\2Y\u01f4\3\2\2\2[\u01f8"
			+ "\3\2\2\2]\u01fa\3\2\2\2_\u0200\3\2\2\2a\u0203\3\2\2\2c\u0205\3\2\2\2e"
			+ "\u020a\3\2\2\2gh\7>\2\2hi\7#\2\2ij\7/\2\2jk\7/\2\2ko\3\2\2\2ln\13\2\2"
			+ "\2ml\3\2\2\2nq\3\2\2\2op\3\2\2\2om\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7/\2"
			+ "\2st\7/\2\2tu\7@\2\2u\6\3\2\2\2vw\7>\2\2wx\7#\2\2xy\7]\2\2yz\7E\2\2z{"
			+ "\7F\2\2{|\7C\2\2|}\7V\2\2}~\7C\2\2~\177\7]\2\2\177\u0083\3\2\2\2\u0080"
			+ "\u0082\13\2\2\2\u0081\u0080\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0084\3"
			+ "\2\2\2\u0083\u0081\3\2\2\2\u0084\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086"
			+ "\u0087\7_\2\2\u0087\u0088\7_\2\2\u0088\u0089\7@\2\2\u0089\b\3\2\2\2\u008a"
			+ "\u008b\7>\2\2\u008b\u008c\7#\2\2\u008c\u0090\3\2\2\2\u008d\u008f\13\2"
			+ "\2\2\u008e\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u0091\3\2\2\2\u0090"
			+ "\u008e\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\7@"
			+ "\2\2\u0094\u0095\3\2\2\2\u0095\u0096\b\4\2\2\u0096\n\3\2\2\2\u0097\u0098"
			+ "\7(\2\2\u0098\u0099\5W+\2\u0099\u009a\7=\2\2\u009a\f\3\2\2\2\u009b\u009c"
			+ "\7(\2\2\u009c\u009d\7%\2\2\u009d\u009f\3\2\2\2\u009e\u00a0\5].\2\u009f"
			+ "\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2"
			+ "\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\7=\2\2\u00a4\u00b1\3\2\2\2\u00a5"
			+ "\u00a6\7(\2\2\u00a6\u00a7\7%\2\2\u00a7\u00a8\7z\2\2\u00a8\u00aa\3\2\2"
			+ "\2\u00a9\u00ab\5[-\2\u00aa\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00aa"
			+ "\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\7=\2\2\u00af"
			+ "\u00b1\3\2\2\2\u00b0\u009b\3\2\2\2\u00b0\u00a5\3\2\2\2\u00b1\16\3\2\2"
			+ "\2\u00b2\u00b8\t\2\2\2\u00b3\u00b5\7\17\2\2\u00b4\u00b3\3\2\2\2\u00b4"
			+ "\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b8\7\f\2\2\u00b7\u00b2\3\2"
			+ "\2\2\u00b7\u00b4\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9"
			+ "\u00ba\3\2\2\2\u00ba\20\3\2\2\2\u00bb\u00bc\7>\2\2\u00bc\u00bd\3\2\2\2"
			+ "\u00bd\u00be\b\b\3\2\u00be\22\3\2\2\2\u00bf\u00c0\7>\2\2\u00c0\u00c1\7"
			+ "A\2\2\u00c1\u00c2\7z\2\2\u00c2\u00c3\7o\2\2\u00c3\u00c4\7n\2\2\u00c4\u00c5"
			+ "\3\2\2\2\u00c5\u00c6\5Y,\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\b\t\3\2\u00c8"
			+ "\24\3\2\2\2\u00c9\u00ca\7>\2\2\u00ca\u00cb\7A\2\2\u00cb\u00cc\3\2\2\2"
			+ "\u00cc\u00cd\5W+\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\b\n\4\2\u00cf\u00d0"
			+ "\b\n\5\2\u00d0\26\3\2\2\2\u00d1\u00d3\n\3\2\2\u00d2\u00d1\3\2\2\2\u00d3"
			+ "\u00d4\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\30\3\2\2"
			+ "\2\u00d6\u00d7\7c\2\2\u00d7\u00d8\7u\2\2\u00d8\u00d9\7u\2\2\u00d9\u00da"
			+ "\7g\2\2\u00da\u00db\7t\2\2\u00db\u00dc\7v\2\2\u00dc\32\3\2\2\2\u00dd\u00de"
			+ "\7c\2\2\u00de\u00df\7v\2\2\u00df\u00e0\7v\2\2\u00e0\u00e1\7t\2\2\u00e1"
			+ "\u00e2\7k\2\2\u00e2\u00e3\7d\2\2\u00e3\u00e4\7w\2\2\u00e4\u00e5\7v\2\2"
			+ "\u00e5\u00e6\7g\2\2\u00e6\34\3\2\2\2\u00e7\u00e8\7e\2\2\u00e8\u00e9\7"
			+ "j\2\2\u00e9\u00ea\7q\2\2\u00ea\u00eb\7k\2\2\u00eb\u00ec\7e\2\2\u00ec\u00ed"
			+ "\7g\2\2\u00ed\36\3\2\2\2\u00ee\u00ef\7e\2\2\u00ef\u00f0\7q\2\2\u00f0\u00f1"
			+ "\7p\2\2\u00f1\u00f2\7h\2\2\u00f2\u00f3\7q\2\2\u00f3\u00f4\7t\2\2\u00f4"
			+ "\u00f5\7o\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7\7p\2\2\u00f7\u00f8\7e\2\2"
			+ "\u00f8\u00f9\7g\2\2\u00f9\u00fa\7?\2\2\u00fa \3\2\2\2\u00fb\u00fc\7h\2"
			+ "\2\u00fc\u00fd\7n\2\2\u00fd\u00fe\7g\2\2\u00fe\u00ff\7z\2\2\u00ff\u0100"
			+ "\7k\2\2\u0100\u0101\7d\2\2\u0101\u0102\7k\2\2\u0102\u0103\7n\2\2\u0103"
			+ "\u0104\7k\2\2\u0104\u0105\7v\2\2\u0105\u0106\7{\2\2\u0106\u0107\7?\2\2"
			+ "\u0107\"\3\2\2\2\u0108\u0109\7e\2\2\u0109\u010a\7q\2\2\u010a\u010b\7p"
			+ "\2\2\u010b\u010c\7v\2\2\u010c\u010d\7c\2\2\u010d\u010e\7k\2\2\u010e\u010f"
			+ "\7p\2\2\u010f\u0110\7u\2\2\u0110\u0111\7?\2\2\u0111$\3\2\2\2\u0112\u0113"
			+ "\7f\2\2\u0113\u0114\7g\2\2\u0114\u0115\7u\2\2\u0115\u0116\7e\2\2\u0116"
			+ "&\3\2\2\2\u0117\u0118\7g\2\2\u0118\u0119\7n\2\2\u0119\u011a\7g\2\2\u011a"
			+ "\u011b\7o\2\2\u011b\u011c\7g\2\2\u011c\u011d\7p\2\2\u011d\u011e\7v\2\2"
			+ "\u011e(\3\2\2\2\u011f\u0120\7k\2\2\u0120\u0121\7f\2\2\u0121\u0122\7?\2"
			+ "\2\u0122*\3\2\2\2\u0123\u0124\7k\2\2\u0124\u0125\7p\2\2\u0125\u0126\7"
			+ "e\2\2\u0126\u0127\7n\2\2\u0127\u0128\7w\2\2\u0128\u0129\7f\2\2\u0129\u012a"
			+ "\7g\2\2\u012a,\3\2\2\2\u012b\u012c\7n\2\2\u012c\u012d\7g\2\2\u012d\u012e"
			+ "\7v\2\2\u012e.\3\2\2\2\u012f\u0130\7k\2\2\u0130\u0131\7u\2\2\u0131\u0132"
			+ "\7O\2\2\u0132\u0133\7c\2\2\u0133\u0134\7p\2\2\u0134\u0135\7f\2\2\u0135"
			+ "\u0136\7c\2\2\u0136\u0137\7v\2\2\u0137\u0138\7q\2\2\u0138\u0139\7t\2\2"
			+ "\u0139\u013a\7{\2\2\u013a\u013b\7?\2\2\u013b\60\3\2\2\2\u013c\u013d\7"
			+ "o\2\2\u013d\u013e\7c\2\2\u013e\u013f\7z\2\2\u013f\u0140\7k\2\2\u0140\u0141"
			+ "\7o\2\2\u0141\u0142\7w\2\2\u0142\u0143\7o\2\2\u0143\u0144\7O\2\2\u0144"
			+ "\u0145\7w\2\2\u0145\u0146\7n\2\2\u0146\u0147\7v\2\2\u0147\u0148\7k\2\2"
			+ "\u0148\u0149\7r\2\2\u0149\u014a\7n\2\2\u014a\u014b\7k\2\2\u014b\u014c"
			+ "\7e\2\2\u014c\u014d\7k\2\2\u014d\u014e\7v\2\2\u014e\u014f\7{\2\2\u014f"
			+ "\u0150\7?\2\2\u0150\62\3\2\2\2\u0151\u0152\7o\2\2\u0152\u0153\7k\2\2\u0153"
			+ "\u0154\7p\2\2\u0154\u0155\7k\2\2\u0155\u0156\7o\2\2\u0156\u0157\7w\2\2"
			+ "\u0157\u0158\7o\2\2\u0158\u0159\7O\2\2\u0159\u015a\7w\2\2\u015a\u015b"
			+ "\7n\2\2\u015b\u015c\7v\2\2\u015c\u015d\7k\2\2\u015d\u015e\7r\2\2\u015e"
			+ "\u015f\7n\2\2\u015f\u0160\7k\2\2\u0160\u0161\7e\2\2\u0161\u0162\7k\2\2"
			+ "\u0162\u0163\7v\2\2\u0163\u0164\7{\2\2\u0164\u0165\7?\2\2\u0165\64\3\2"
			+ "\2\2\u0166\u0167\7p\2\2\u0167\u0168\7c\2\2\u0168\u0169\7o\2\2\u0169\u016a"
			+ "\7g\2\2\u016a\u016b\7?\2\2\u016b\66\3\2\2\2\u016c\u016d\7k\2\2\u016d\u016e"
			+ "\7u\2\2\u016e\u016f\7Q\2\2\u016f\u0170\7r\2\2\u0170\u0171\7v\2\2\u0171"
			+ "\u0172\7k\2\2\u0172\u0173\7q\2\2\u0173\u0174\7p\2\2\u0174\u0175\7c\2\2"
			+ "\u0175\u0176\7n\2\2\u0176\u0177\7?\2\2\u01778\3\2\2\2\u0178\u0179\7r\2"
			+ "\2\u0179\u017a\7t\2\2\u017a\u017b\7q\2\2\u017b\u017c\7j\2\2\u017c\u017d"
			+ "\7k\2\2\u017d\u017e\7d\2\2\u017e\u017f\7k\2\2\u017f\u0180\7v\2\2\u0180"
			+ "\u0181\7g\2\2\u0181\u0182\7f\2\2\u0182\u0183\7?\2\2\u0183:\3\2\2\2\u0184"
			+ "\u0185\7t\2\2\u0185\u0186\7g\2\2\u0186\u0187\7h\2\2\u0187\u0188\7?\2\2"
			+ "\u0188<\3\2\2\2\u0189\u018a\7t\2\2\u018a\u018b\7g\2\2\u018b\u018c\7r\2"
			+ "\2\u018c\u018d\7q\2\2\u018d\u018e\7t\2\2\u018e\u018f\7v\2\2\u018f>\3\2"
			+ "\2\2\u0190\u0191\7u\2\2\u0191\u0192\7v\2\2\u0192\u0193\7t\2\2\u0193\u0194"
			+ "\7g\2\2\u0194\u0195\7p\2\2\u0195\u0196\7i\2\2\u0196\u0197\7v\2\2\u0197"
			+ "\u0198\7j\2\2\u0198\u0199\7?\2\2\u0199@\3\2\2\2\u019a\u019b\7v\2\2\u019b"
			+ "\u019c\7g\2\2\u019c\u019d\7o\2\2\u019d\u019e\7r\2\2\u019e\u019f\7n\2\2"
			+ "\u019f\u01a0\7c\2\2\u01a0\u01a1\7v\2\2\u01a1\u01a2\7g\2\2\u01a2B\3\2\2"
			+ "\2\u01a3\u01a4\7f\2\2\u01a4\u01a5\7c\2\2\u01a5\u01a6\7v\2\2\u01a6\u01a7"
			+ "\7c\2\2\u01a7\u01a8\7v\2\2\u01a8\u01a9\7{\2\2\u01a9\u01aa\7r\2\2\u01aa"
			+ "\u01ab\7g\2\2\u01ab\u01ac\7?\2\2\u01acD\3\2\2\2\u01ad\u01ae\7x\2\2\u01ae"
			+ "\u01af\7c\2\2\u01af\u01b0\7n\2\2\u01b0\u01b1\7w\2\2\u01b1\u01b2\7g\2\2"
			+ "\u01b2\u01b3\7?\2\2\u01b3F\3\2\2\2\u01b4\u01b5\7x\2\2\u01b5\u01b6\7c\2"
			+ "\2\u01b6\u01b7\7n\2\2\u01b7\u01b8\7w\2\2\u01b8\u01b9\7g\2\2\u01b9\u01ba"
			+ "\7U\2\2\u01ba\u01bb\7g\2\2\u01bb\u01bc\7v\2\2\u01bc\u01bd\7?\2\2\u01bd"
			+ "H\3\2\2\2\u01be\u01bf\7x\2\2\u01bf\u01c0\7q\2\2\u01c0\u01c1\7e\2\2\u01c1"
			+ "\u01c2\7c\2\2\u01c2\u01c3\7d\2\2\u01c3\u01c4\7w\2\2\u01c4\u01c5\7n\2\2"
			+ "\u01c5\u01c6\7c\2\2\u01c6\u01c7\7t\2\2\u01c7\u01c8\7{\2\2\u01c8J\3\2\2"
			+ "\2\u01c9\u01ca\7@\2\2\u01ca\u01cb\3\2\2\2\u01cb\u01cc\b%\6\2\u01ccL\3"
			+ "\2\2\2\u01cd\u01ce\7A\2\2\u01ce\u01cf\7@\2\2\u01cf\u01d0\3\2\2\2\u01d0"
			+ "\u01d1\b&\6\2\u01d1N\3\2\2\2\u01d2\u01d3\7\61\2\2\u01d3\u01d4\7@\2\2\u01d4"
			+ "\u01d5\3\2\2\2\u01d5\u01d6\b\'\6\2\u01d6P\3\2\2\2\u01d7\u01d8\7\61\2\2"
			+ "\u01d8R\3\2\2\2\u01d9\u01da\7?\2\2\u01daT\3\2\2\2\u01db\u01df\7$\2\2\u01dc"
			+ "\u01de\n\4\2\2\u01dd\u01dc\3\2\2\2\u01de\u01e1\3\2\2\2\u01df\u01dd\3\2"
			+ "\2\2\u01df\u01e0\3\2\2\2\u01e0\u01e2\3\2\2\2\u01e1\u01df\3\2\2\2\u01e2"
			+ "\u01ec\7$\2\2\u01e3\u01e7\7)\2\2\u01e4\u01e6\n\5\2\2\u01e5\u01e4\3\2\2"
			+ "\2\u01e6\u01e9\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8\u01ea"
			+ "\3\2\2\2\u01e9\u01e7\3\2\2\2\u01ea\u01ec\7)\2\2\u01eb\u01db\3\2\2\2\u01eb"
			+ "\u01e3\3\2\2\2\u01ecV\3\2\2\2\u01ed\u01f1\5a\60\2\u01ee\u01f0\5_/\2\u01ef"
			+ "\u01ee\3\2\2\2\u01f0\u01f3\3\2\2\2\u01f1\u01ef\3\2\2\2\u01f1\u01f2\3\2"
			+ "\2\2\u01f2X\3\2\2\2\u01f3\u01f1\3\2\2\2\u01f4\u01f5\t\6\2\2\u01f5\u01f6"
			+ "\3\2\2\2\u01f6\u01f7\b,\2\2\u01f7Z\3\2\2\2\u01f8\u01f9\t\7\2\2\u01f9\\"
			+ "\3\2\2\2\u01fa\u01fb\t\b\2\2\u01fb^\3\2\2\2\u01fc\u0201\5a\60\2\u01fd"
			+ "\u0201\t\t\2\2\u01fe\u0201\5].\2\u01ff\u0201\t\n\2\2\u0200\u01fc\3\2\2"
			+ "\2\u0200\u01fd\3\2\2\2\u0200\u01fe\3\2\2\2\u0200\u01ff\3\2\2\2\u0201`"
			+ "\3\2\2\2\u0202\u0204\t\13\2\2\u0203\u0202\3\2\2\2\u0204b\3\2\2\2\u0205"
			+ "\u0206\7A\2\2\u0206\u0207\7@\2\2\u0207\u0208\3\2\2\2\u0208\u0209\b\61"
			+ "\6\2\u0209d\3\2\2\2\u020a\u020b\13\2\2\2\u020b\u020c\3\2\2\2\u020c\u020d"
			+ "\b\62\4\2\u020df\3\2\2\2\25\2\3\4o\u0083\u0090\u00a1\u00ac\u00b0\u00b4"
			+ "\u00b7\u00b9\u00d4\u01df\u01e7\u01eb\u01f1\u0200\u0203\7\b\2\2\7\3\2\5"
			+ "\2\2\7\4\2\6\2\2";

	public static final ATN _ATN = new ATNDeserializer().deserialize(_serializedATN.toCharArray());

	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}

	public Hl7ItsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
	}

	@Override
	public ATN getATN() {
		return _ATN;
	}

	@Override
	public String[] getChannelNames() {
		return channelNames;
	}

	@Override
	public String getGrammarFileName() {
		return "Hl7ItsLexer.g4";
	}

	@Override
	public String[] getModeNames() {
		return modeNames;
	}

	@Override
	public String[] getRuleNames() {
		return ruleNames;
	}

	@Override
	public String getSerializedATN() {
		return _serializedATN;
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
}