// Generated from Hl7ItsParser.g4 by ANTLR 4.7.1

package org.ehealth_connector.codegenerator.cda.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Hl7ItsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COMMENT=1, CDATA=2, DTD=3, EntityRef=4, CharRef=5, SEA_WS=6, OPEN=7, XMLDeclOpen=8, 
		TEXT=9, ASSERT=10, ATTRIBUTE=11, CHOICE=12, CODEATTR=13, CONFATTR=14, 
		FLEXIBILITYATTR=15, CONTAINSATTR=16, DESC=17, ELEMENT=18, IDATTR=19, INCLUDE=20, 
		LET=21, MANDATTR=22, MAXOCCURSATTR=23, MINOCCURSATTR=24, NAMEATTR=25, 
		OPTATTR=26, PROHIBITED=27, REFATTR=28, REPORT=29, STRENGTHATTR=30, TEMPLATE=31, 
		TYPEATTR=32, VALUEATTR=33, VALUESETATTR=34, VOCAB=35, CLOSE=36, SPECIAL_CLOSE=37, 
		SLASH_CLOSE=38, SLASH=39, EQUALS=40, AttrValue=41, Name=42, S=43, PI=44;
	public static final int
		RULE_prolog = 0, RULE_template = 1, RULE_desc = 2, RULE_element = 3, RULE_attribute = 4, 
		RULE_choice = 5, RULE_include = 6, RULE_vocab = 7, RULE_letter = 8, RULE_assertion = 9, 
		RULE_report = 10, RULE_content = 11, RULE_xmlelement = 12, RULE_reference = 13, 
		RULE_attr = 14, RULE_conformanceAttr = 15, RULE_flexibilityAttr = 16, 
		RULE_codeAttr = 17, RULE_containsAttr = 18, RULE_dataTypeAttr = 19, RULE_idAttr = 20, 
		RULE_isMandatoryAttr = 21, RULE_isOptionalAttr = 22, RULE_maxOccursAttr = 23, 
		RULE_minOccursAttr = 24, RULE_nameAttr = 25, RULE_prohibitedAttr = 26, 
		RULE_refAttr = 27, RULE_strengthAttr = 28, RULE_valueAttr = 29, RULE_valueSetAttr = 30, 
		RULE_chardata = 31, RULE_misc = 32;
	public static final String[] ruleNames = {
		"prolog", "template", "desc", "element", "attribute", "choice", "include", 
		"vocab", "letter", "assertion", "report", "content", "xmlelement", "reference", 
		"attr", "conformanceAttr", "flexibilityAttr", "codeAttr", "containsAttr", 
		"dataTypeAttr", "idAttr", "isMandatoryAttr", "isOptionalAttr", "maxOccursAttr", 
		"minOccursAttr", "nameAttr", "prohibitedAttr", "refAttr", "strengthAttr", 
		"valueAttr", "valueSetAttr", "chardata", "misc"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'<'", null, null, "'assert'", 
		"'attribute'", "'choice'", "'code='", "'conformance='", "'flexibility='", 
		"'contains='", "'desc'", "'element'", "'id='", "'include'", "'let'", "'isMandatory='", 
		"'maximumMultiplicity='", "'minimumMultiplicity='", "'name='", "'isOptional='", 
		"'prohibited='", "'ref='", "'report'", "'strength='", "'template'", "'datatype='", 
		"'value='", "'valueSet='", "'vocabulary'", "'>'", null, "'/>'", "'/'", 
		"'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMENT", "CDATA", "DTD", "EntityRef", "CharRef", "SEA_WS", "OPEN", 
		"XMLDeclOpen", "TEXT", "ASSERT", "ATTRIBUTE", "CHOICE", "CODEATTR", "CONFATTR", 
		"FLEXIBILITYATTR", "CONTAINSATTR", "DESC", "ELEMENT", "IDATTR", "INCLUDE", 
		"LET", "MANDATTR", "MAXOCCURSATTR", "MINOCCURSATTR", "NAMEATTR", "OPTATTR", 
		"PROHIBITED", "REFATTR", "REPORT", "STRENGTHATTR", "TEMPLATE", "TYPEATTR", 
		"VALUEATTR", "VALUESETATTR", "VOCAB", "CLOSE", "SPECIAL_CLOSE", "SLASH_CLOSE", 
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

	@Override
	public String getGrammarFileName() { return "Hl7ItsParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Hl7ItsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class PrologContext extends ParserRuleContext {
		public TerminalNode XMLDeclOpen() { return getToken(Hl7ItsParser.XMLDeclOpen, 0); }
		public TerminalNode SPECIAL_CLOSE() { return getToken(Hl7ItsParser.SPECIAL_CLOSE, 0); }
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public TerminalNode SEA_WS() { return getToken(Hl7ItsParser.SEA_WS, 0); }
		public PrologContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prolog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterProlog(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitProlog(this);
		}
	}

	public final PrologContext prolog() throws RecognitionException {
		PrologContext _localctx = new PrologContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prolog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(XMLDeclOpen);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(67);
				attr();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73);
			match(SPECIAL_CLOSE);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEA_WS) {
				{
				setState(74);
				match(SEA_WS);
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

	public static class TemplateContext extends ParserRuleContext {
		public List<TerminalNode> TEMPLATE() { return getTokens(Hl7ItsParser.TEMPLATE); }
		public TerminalNode TEMPLATE(int i) {
			return getToken(Hl7ItsParser.TEMPLATE, i);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public PrologContext prolog() {
			return getRuleContext(PrologContext.class,0);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public IdAttrContext idAttr() {
			return getRuleContext(IdAttrContext.class,0);
		}
		public NameAttrContext nameAttr() {
			return getRuleContext(NameAttrContext.class,0);
		}
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitTemplate(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_template);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==XMLDeclOpen) {
				{
				setState(77);
				prolog();
				}
			}

			setState(80);
			match(OPEN);
			setState(81);
			match(TEMPLATE);
			setState(85);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(82);
					attr();
					}
					} 
				}
				setState(87);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDATTR) {
				{
				setState(88);
				idAttr();
				}
			}

			setState(94);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(91);
					attr();
					}
					} 
				}
				setState(96);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAMEATTR) {
				{
				setState(97);
				nameAttr();
				}
			}

			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(100);
				attr();
				}
				}
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(106);
			match(CLOSE);
			setState(107);
			content();
			setState(108);
			match(OPEN);
			setState(109);
			match(SLASH);
			setState(110);
			match(TEMPLATE);
			setState(111);
			match(CLOSE);
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

	public static class DescContext extends ParserRuleContext {
		public List<TerminalNode> DESC() { return getTokens(Hl7ItsParser.DESC); }
		public TerminalNode DESC(int i) {
			return getToken(Hl7ItsParser.DESC, i);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public DescContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_desc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterDesc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitDesc(this);
		}
	}

	public final DescContext desc() throws RecognitionException {
		DescContext _localctx = new DescContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_desc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(OPEN);
			setState(114);
			match(DESC);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(115);
				attr();
				}
				}
				setState(120);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(121);
			match(CLOSE);
			setState(122);
			content();
			setState(123);
			match(OPEN);
			setState(124);
			match(SLASH);
			setState(125);
			match(DESC);
			setState(126);
			match(CLOSE);
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

	public static class ElementContext extends ParserRuleContext {
		public List<TerminalNode> ELEMENT() { return getTokens(Hl7ItsParser.ELEMENT); }
		public TerminalNode ELEMENT(int i) {
			return getToken(Hl7ItsParser.ELEMENT, i);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public ConformanceAttrContext conformanceAttr() {
			return getRuleContext(ConformanceAttrContext.class,0);
		}
		public ContainsAttrContext containsAttr() {
			return getRuleContext(ContainsAttrContext.class,0);
		}
		public DataTypeAttrContext dataTypeAttr() {
			return getRuleContext(DataTypeAttrContext.class,0);
		}
		public FlexibilityAttrContext flexibilityAttr() {
			return getRuleContext(FlexibilityAttrContext.class,0);
		}
		public IdAttrContext idAttr() {
			return getRuleContext(IdAttrContext.class,0);
		}
		public IsMandatoryAttrContext isMandatoryAttr() {
			return getRuleContext(IsMandatoryAttrContext.class,0);
		}
		public MaxOccursAttrContext maxOccursAttr() {
			return getRuleContext(MaxOccursAttrContext.class,0);
		}
		public MinOccursAttrContext minOccursAttr() {
			return getRuleContext(MinOccursAttrContext.class,0);
		}
		public NameAttrContext nameAttr() {
			return getRuleContext(NameAttrContext.class,0);
		}
		public ValueAttrContext valueAttr() {
			return getRuleContext(ValueAttrContext.class,0);
		}
		public StrengthAttrContext strengthAttr() {
			return getRuleContext(StrengthAttrContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public ElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_element; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitElement(this);
		}
	}

	public final ElementContext element() throws RecognitionException {
		ElementContext _localctx = new ElementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_element);
		int _la;
		try {
			setState(218);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				match(OPEN);
				setState(129);
				match(ELEMENT);
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(130);
					attr();
					}
					}
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(136);
					conformanceAttr();
					}
				}

				setState(140);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONTAINSATTR) {
					{
					setState(139);
					containsAttr();
					}
				}

				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(142);
					dataTypeAttr();
					}
				}

				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(145);
					flexibilityAttr();
					}
				}

				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(148);
					idAttr();
					}
				}

				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(151);
					isMandatoryAttr();
					}
				}

				setState(155);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(154);
					maxOccursAttr();
					}
				}

				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(157);
					minOccursAttr();
					}
				}

				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(160);
					nameAttr();
					}
				}

				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(163);
					valueAttr();
					}
				}

				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRENGTHATTR) {
					{
					setState(166);
					strengthAttr();
					}
				}

				setState(169);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				match(OPEN);
				setState(171);
				match(ELEMENT);
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(172);
					attr();
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(178);
					conformanceAttr();
					}
				}

				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONTAINSATTR) {
					{
					setState(181);
					containsAttr();
					}
				}

				setState(185);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(184);
					dataTypeAttr();
					}
				}

				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(187);
					flexibilityAttr();
					}
				}

				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(190);
					idAttr();
					}
				}

				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(193);
					isMandatoryAttr();
					}
				}

				setState(197);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(196);
					maxOccursAttr();
					}
				}

				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(199);
					minOccursAttr();
					}
				}

				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(202);
					nameAttr();
					}
				}

				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(205);
					valueAttr();
					}
				}

				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRENGTHATTR) {
					{
					setState(208);
					strengthAttr();
					}
				}

				setState(211);
				match(CLOSE);
				setState(212);
				content();
				setState(213);
				match(OPEN);
				setState(214);
				match(SLASH);
				setState(215);
				match(ELEMENT);
				setState(216);
				match(CLOSE);
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

	public static class AttributeContext extends ParserRuleContext {
		public List<TerminalNode> ATTRIBUTE() { return getTokens(Hl7ItsParser.ATTRIBUTE); }
		public TerminalNode ATTRIBUTE(int i) {
			return getToken(Hl7ItsParser.ATTRIBUTE, i);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public ConformanceAttrContext conformanceAttr() {
			return getRuleContext(ConformanceAttrContext.class,0);
		}
		public DataTypeAttrContext dataTypeAttr() {
			return getRuleContext(DataTypeAttrContext.class,0);
		}
		public IdAttrContext idAttr() {
			return getRuleContext(IdAttrContext.class,0);
		}
		public IsMandatoryAttrContext isMandatoryAttr() {
			return getRuleContext(IsMandatoryAttrContext.class,0);
		}
		public IsOptionalAttrContext isOptionalAttr() {
			return getRuleContext(IsOptionalAttrContext.class,0);
		}
		public MaxOccursAttrContext maxOccursAttr() {
			return getRuleContext(MaxOccursAttrContext.class,0);
		}
		public MinOccursAttrContext minOccursAttr() {
			return getRuleContext(MinOccursAttrContext.class,0);
		}
		public NameAttrContext nameAttr() {
			return getRuleContext(NameAttrContext.class,0);
		}
		public ProhibitedAttrContext prohibitedAttr() {
			return getRuleContext(ProhibitedAttrContext.class,0);
		}
		public ValueAttrContext valueAttr() {
			return getRuleContext(ValueAttrContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_attribute);
		int _la;
		try {
			setState(304);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(220);
				match(OPEN);
				setState(221);
				match(ATTRIBUTE);
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(222);
					attr();
					}
					}
					setState(227);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(229);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(228);
					conformanceAttr();
					}
				}

				setState(232);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(231);
					dataTypeAttr();
					}
				}

				setState(235);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(234);
					idAttr();
					}
				}

				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(237);
					isMandatoryAttr();
					}
				}

				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(240);
					isOptionalAttr();
					}
				}

				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(243);
					maxOccursAttr();
					}
				}

				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(246);
					minOccursAttr();
					}
				}

				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(249);
					nameAttr();
					}
				}

				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(252);
					prohibitedAttr();
					}
				}

				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(255);
					valueAttr();
					}
				}

				setState(258);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				match(OPEN);
				setState(260);
				match(ATTRIBUTE);
				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(261);
					attr();
					}
					}
					setState(266);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(267);
					conformanceAttr();
					}
				}

				setState(271);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(270);
					dataTypeAttr();
					}
				}

				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(273);
					idAttr();
					}
				}

				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(276);
					isMandatoryAttr();
					}
				}

				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(279);
					isOptionalAttr();
					}
				}

				setState(283);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(282);
					maxOccursAttr();
					}
				}

				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(285);
					minOccursAttr();
					}
				}

				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(288);
					nameAttr();
					}
				}

				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(291);
					prohibitedAttr();
					}
				}

				setState(295);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(294);
					valueAttr();
					}
				}

				setState(297);
				match(CLOSE);
				setState(298);
				content();
				setState(299);
				match(OPEN);
				setState(300);
				match(SLASH);
				setState(301);
				match(ATTRIBUTE);
				setState(302);
				match(CLOSE);
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

	public static class ChoiceContext extends ParserRuleContext {
		public List<TerminalNode> CHOICE() { return getTokens(Hl7ItsParser.CHOICE); }
		public TerminalNode CHOICE(int i) {
			return getToken(Hl7ItsParser.CHOICE, i);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public MaxOccursAttrContext maxOccursAttr() {
			return getRuleContext(MaxOccursAttrContext.class,0);
		}
		public MinOccursAttrContext minOccursAttr() {
			return getRuleContext(MinOccursAttrContext.class,0);
		}
		public ChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_choice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitChoice(this);
		}
	}

	public final ChoiceContext choice() throws RecognitionException {
		ChoiceContext _localctx = new ChoiceContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_choice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(OPEN);
			setState(307);
			match(CHOICE);
			setState(311);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(308);
				attr();
				}
				}
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MAXOCCURSATTR) {
				{
				setState(314);
				maxOccursAttr();
				}
			}

			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINOCCURSATTR) {
				{
				setState(317);
				minOccursAttr();
				}
			}

			setState(320);
			match(CLOSE);
			setState(321);
			content();
			setState(322);
			match(OPEN);
			setState(323);
			match(SLASH);
			setState(324);
			match(CHOICE);
			setState(325);
			match(CLOSE);
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

	public static class IncludeContext extends ParserRuleContext {
		public List<TerminalNode> INCLUDE() { return getTokens(Hl7ItsParser.INCLUDE); }
		public TerminalNode INCLUDE(int i) {
			return getToken(Hl7ItsParser.INCLUDE, i);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public ConformanceAttrContext conformanceAttr() {
			return getRuleContext(ConformanceAttrContext.class,0);
		}
		public FlexibilityAttrContext flexibilityAttr() {
			return getRuleContext(FlexibilityAttrContext.class,0);
		}
		public IdAttrContext idAttr() {
			return getRuleContext(IdAttrContext.class,0);
		}
		public IsMandatoryAttrContext isMandatoryAttr() {
			return getRuleContext(IsMandatoryAttrContext.class,0);
		}
		public MaxOccursAttrContext maxOccursAttr() {
			return getRuleContext(MaxOccursAttrContext.class,0);
		}
		public MinOccursAttrContext minOccursAttr() {
			return getRuleContext(MinOccursAttrContext.class,0);
		}
		public RefAttrContext refAttr() {
			return getRuleContext(RefAttrContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public IncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_include; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterInclude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitInclude(this);
		}
	}

	public final IncludeContext include() throws RecognitionException {
		IncludeContext _localctx = new IncludeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_include);
		int _la;
		try {
			int _alt;
			setState(405);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(327);
				match(OPEN);
				setState(328);
				match(INCLUDE);
				setState(332);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(329);
						attr();
						}
						} 
					}
					setState(334);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				}
				setState(336);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(335);
					conformanceAttr();
					}
				}

				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(338);
					flexibilityAttr();
					}
				}

				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(341);
					idAttr();
					}
				}

				setState(345);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(344);
					isMandatoryAttr();
					}
				}

				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(347);
					maxOccursAttr();
					}
				}

				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(350);
					minOccursAttr();
					}
				}

				setState(354);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(353);
					refAttr();
					}
				}

				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(356);
					attr();
					}
					}
					setState(361);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(362);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(363);
				match(OPEN);
				setState(364);
				match(INCLUDE);
				setState(368);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(365);
						attr();
						}
						} 
					}
					setState(370);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
				}
				setState(372);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(371);
					conformanceAttr();
					}
				}

				setState(375);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(374);
					flexibilityAttr();
					}
				}

				setState(378);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(377);
					idAttr();
					}
				}

				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(380);
					isMandatoryAttr();
					}
				}

				setState(384);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(383);
					maxOccursAttr();
					}
				}

				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(386);
					minOccursAttr();
					}
				}

				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(389);
					refAttr();
					}
				}

				setState(395);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(392);
					attr();
					}
					}
					setState(397);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(398);
				match(CLOSE);
				setState(399);
				content();
				setState(400);
				match(OPEN);
				setState(401);
				match(SLASH);
				setState(402);
				match(INCLUDE);
				setState(403);
				match(CLOSE);
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

	public static class VocabContext extends ParserRuleContext {
		public List<TerminalNode> VOCAB() { return getTokens(Hl7ItsParser.VOCAB); }
		public TerminalNode VOCAB(int i) {
			return getToken(Hl7ItsParser.VOCAB, i);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public CodeAttrContext codeAttr() {
			return getRuleContext(CodeAttrContext.class,0);
		}
		public FlexibilityAttrContext flexibilityAttr() {
			return getRuleContext(FlexibilityAttrContext.class,0);
		}
		public ValueSetAttrContext valueSetAttr() {
			return getRuleContext(ValueSetAttrContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public VocabContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vocab; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterVocab(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitVocab(this);
		}
	}

	public final VocabContext vocab() throws RecognitionException {
		VocabContext _localctx = new VocabContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_vocab);
		int _la;
		try {
			int _alt;
			setState(461);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(407);
				match(OPEN);
				setState(408);
				match(VOCAB);
				setState(412);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(409);
						attr();
						}
						} 
					}
					setState(414);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
				}
				setState(416);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CODEATTR) {
					{
					setState(415);
					codeAttr();
					}
				}

				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(418);
					flexibilityAttr();
					}
				}

				setState(422);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUESETATTR) {
					{
					setState(421);
					valueSetAttr();
					}
				}

				setState(427);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(424);
					attr();
					}
					}
					setState(429);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(430);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(431);
				match(OPEN);
				setState(432);
				match(VOCAB);
				setState(436);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(433);
						attr();
						}
						} 
					}
					setState(438);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
				}
				setState(440);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CODEATTR) {
					{
					setState(439);
					codeAttr();
					}
				}

				setState(443);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(442);
					flexibilityAttr();
					}
				}

				setState(446);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUESETATTR) {
					{
					setState(445);
					valueSetAttr();
					}
				}

				setState(451);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(448);
					attr();
					}
					}
					setState(453);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(454);
				match(CLOSE);
				setState(455);
				content();
				setState(456);
				match(OPEN);
				setState(457);
				match(SLASH);
				setState(458);
				match(VOCAB);
				setState(459);
				match(CLOSE);
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

	public static class LetterContext extends ParserRuleContext {
		public List<TerminalNode> LET() { return getTokens(Hl7ItsParser.LET); }
		public TerminalNode LET(int i) {
			return getToken(Hl7ItsParser.LET, i);
		}
		public NameAttrContext nameAttr() {
			return getRuleContext(NameAttrContext.class,0);
		}
		public ValueAttrContext valueAttr() {
			return getRuleContext(ValueAttrContext.class,0);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public LetterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterLetter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitLetter(this);
		}
	}

	public final LetterContext letter() throws RecognitionException {
		LetterContext _localctx = new LetterContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_letter);
		int _la;
		try {
			setState(487);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,94,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(463);
				match(OPEN);
				setState(464);
				match(LET);
				setState(466);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(465);
					nameAttr();
					}
				}

				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(468);
					valueAttr();
					}
				}

				setState(471);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(472);
				match(OPEN);
				setState(473);
				match(LET);
				setState(475);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(474);
					nameAttr();
					}
				}

				setState(478);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(477);
					valueAttr();
					}
				}

				setState(480);
				match(CLOSE);
				setState(481);
				content();
				setState(482);
				match(OPEN);
				setState(483);
				match(SLASH);
				setState(484);
				match(LET);
				setState(485);
				match(CLOSE);
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

	public static class AssertionContext extends ParserRuleContext {
		public List<TerminalNode> ASSERT() { return getTokens(Hl7ItsParser.ASSERT); }
		public TerminalNode ASSERT(int i) {
			return getToken(Hl7ItsParser.ASSERT, i);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public AssertionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assertion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterAssertion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitAssertion(this);
		}
	}

	public final AssertionContext assertion() throws RecognitionException {
		AssertionContext _localctx = new AssertionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assertion);
		int _la;
		try {
			setState(513);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(489);
				match(OPEN);
				setState(490);
				match(ASSERT);
				setState(494);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(491);
					attr();
					}
					}
					setState(496);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(497);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(498);
				match(OPEN);
				setState(499);
				match(ASSERT);
				setState(503);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(500);
					attr();
					}
					}
					setState(505);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(506);
				match(CLOSE);
				setState(507);
				content();
				setState(508);
				match(OPEN);
				setState(509);
				match(SLASH);
				setState(510);
				match(ASSERT);
				setState(511);
				match(CLOSE);
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

	public static class ReportContext extends ParserRuleContext {
		public List<TerminalNode> REPORT() { return getTokens(Hl7ItsParser.REPORT); }
		public TerminalNode REPORT(int i) {
			return getToken(Hl7ItsParser.REPORT, i);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public ReportContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_report; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterReport(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitReport(this);
		}
	}

	public final ReportContext report() throws RecognitionException {
		ReportContext _localctx = new ReportContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_report);
		int _la;
		try {
			setState(539);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(515);
				match(OPEN);
				setState(516);
				match(REPORT);
				setState(520);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(517);
					attr();
					}
					}
					setState(522);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(523);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(524);
				match(OPEN);
				setState(525);
				match(REPORT);
				setState(529);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(526);
					attr();
					}
					}
					setState(531);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(532);
				match(CLOSE);
				setState(533);
				content();
				setState(534);
				match(OPEN);
				setState(535);
				match(SLASH);
				setState(536);
				match(REPORT);
				setState(537);
				match(CLOSE);
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

	public static class ContentContext extends ParserRuleContext {
		public List<ChardataContext> chardata() {
			return getRuleContexts(ChardataContext.class);
		}
		public ChardataContext chardata(int i) {
			return getRuleContext(ChardataContext.class,i);
		}
		public List<XmlelementContext> xmlelement() {
			return getRuleContexts(XmlelementContext.class);
		}
		public XmlelementContext xmlelement(int i) {
			return getRuleContext(XmlelementContext.class,i);
		}
		public List<DescContext> desc() {
			return getRuleContexts(DescContext.class);
		}
		public DescContext desc(int i) {
			return getRuleContext(DescContext.class,i);
		}
		public List<ElementContext> element() {
			return getRuleContexts(ElementContext.class);
		}
		public ElementContext element(int i) {
			return getRuleContext(ElementContext.class,i);
		}
		public List<IncludeContext> include() {
			return getRuleContexts(IncludeContext.class);
		}
		public IncludeContext include(int i) {
			return getRuleContext(IncludeContext.class,i);
		}
		public List<ChoiceContext> choice() {
			return getRuleContexts(ChoiceContext.class);
		}
		public ChoiceContext choice(int i) {
			return getRuleContext(ChoiceContext.class,i);
		}
		public List<LetterContext> letter() {
			return getRuleContexts(LetterContext.class);
		}
		public LetterContext letter(int i) {
			return getRuleContext(LetterContext.class,i);
		}
		public List<AssertionContext> assertion() {
			return getRuleContexts(AssertionContext.class);
		}
		public AssertionContext assertion(int i) {
			return getRuleContext(AssertionContext.class,i);
		}
		public List<ReportContext> report() {
			return getRuleContexts(ReportContext.class);
		}
		public ReportContext report(int i) {
			return getRuleContext(ReportContext.class,i);
		}
		public List<ReferenceContext> reference() {
			return getRuleContexts(ReferenceContext.class);
		}
		public ReferenceContext reference(int i) {
			return getRuleContext(ReferenceContext.class,i);
		}
		public List<AttributeContext> attribute() {
			return getRuleContexts(AttributeContext.class);
		}
		public AttributeContext attribute(int i) {
			return getRuleContext(AttributeContext.class,i);
		}
		public List<VocabContext> vocab() {
			return getRuleContexts(VocabContext.class);
		}
		public VocabContext vocab(int i) {
			return getRuleContext(VocabContext.class,i);
		}
		public List<TerminalNode> CDATA() { return getTokens(Hl7ItsParser.CDATA); }
		public TerminalNode CDATA(int i) {
			return getToken(Hl7ItsParser.CDATA, i);
		}
		public List<TerminalNode> PI() { return getTokens(Hl7ItsParser.PI); }
		public TerminalNode PI(int i) {
			return getToken(Hl7ItsParser.PI, i);
		}
		public List<TerminalNode> COMMENT() { return getTokens(Hl7ItsParser.COMMENT); }
		public TerminalNode COMMENT(int i) {
			return getToken(Hl7ItsParser.COMMENT, i);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitContent(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_content);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEA_WS || _la==TEXT) {
				{
				setState(541);
				chardata();
				}
			}

			setState(565);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(558);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
					case 1:
						{
						setState(544);
						xmlelement();
						}
						break;
					case 2:
						{
						setState(545);
						desc();
						}
						break;
					case 3:
						{
						setState(546);
						element();
						}
						break;
					case 4:
						{
						setState(547);
						include();
						}
						break;
					case 5:
						{
						setState(548);
						choice();
						}
						break;
					case 6:
						{
						setState(549);
						letter();
						}
						break;
					case 7:
						{
						setState(550);
						assertion();
						}
						break;
					case 8:
						{
						setState(551);
						report();
						}
						break;
					case 9:
						{
						setState(552);
						reference();
						}
						break;
					case 10:
						{
						setState(553);
						attribute();
						}
						break;
					case 11:
						{
						setState(554);
						vocab();
						}
						break;
					case 12:
						{
						setState(555);
						match(CDATA);
						}
						break;
					case 13:
						{
						setState(556);
						match(PI);
						}
						break;
					case 14:
						{
						setState(557);
						match(COMMENT);
						}
						break;
					}
					setState(561);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SEA_WS || _la==TEXT) {
						{
						setState(560);
						chardata();
						}
					}

					}
					} 
				}
				setState(567);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
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

	public static class XmlelementContext extends ParserRuleContext {
		public List<TerminalNode> Name() { return getTokens(Hl7ItsParser.Name); }
		public TerminalNode Name(int i) {
			return getToken(Hl7ItsParser.Name, i);
		}
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public List<AttrContext> attr() {
			return getRuleContexts(AttrContext.class);
		}
		public AttrContext attr(int i) {
			return getRuleContext(AttrContext.class,i);
		}
		public XmlelementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xmlelement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterXmlelement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitXmlelement(this);
		}
	}

	public final XmlelementContext xmlelement() throws RecognitionException {
		XmlelementContext _localctx = new XmlelementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_xmlelement);
		int _la;
		try {
			setState(604);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,107,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(568);
				match(OPEN);
				setState(569);
				match(Name);
				setState(570);
				match(CLOSE);
				setState(571);
				content();
				setState(572);
				match(OPEN);
				setState(573);
				match(SLASH);
				setState(574);
				match(Name);
				setState(575);
				match(CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(577);
				match(OPEN);
				setState(578);
				match(Name);
				setState(579);
				match(SLASH_CLOSE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(580);
				match(OPEN);
				setState(581);
				match(Name);
				setState(585);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(582);
					attr();
					}
					}
					setState(587);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(588);
				match(CLOSE);
				setState(589);
				content();
				setState(590);
				match(OPEN);
				setState(591);
				match(SLASH);
				setState(592);
				match(Name);
				setState(593);
				match(CLOSE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(595);
				match(OPEN);
				setState(596);
				match(Name);
				setState(600);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(597);
					attr();
					}
					}
					setState(602);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(603);
				match(SLASH_CLOSE);
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

	public static class ReferenceContext extends ParserRuleContext {
		public TerminalNode EntityRef() { return getToken(Hl7ItsParser.EntityRef, 0); }
		public TerminalNode CharRef() { return getToken(Hl7ItsParser.CharRef, 0); }
		public ReferenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reference; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterReference(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitReference(this);
		}
	}

	public final ReferenceContext reference() throws RecognitionException {
		ReferenceContext _localctx = new ReferenceContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(606);
			_la = _input.LA(1);
			if ( !(_la==EntityRef || _la==CharRef) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class AttrContext extends ParserRuleContext {
		public TerminalNode Name() { return getToken(Hl7ItsParser.Name, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public AttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitAttr(this);
		}
	}

	public final AttrContext attr() throws RecognitionException {
		AttrContext _localctx = new AttrContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_attr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(608);
			match(Name);
			setState(609);
			match(EQUALS);
			setState(610);
			match(AttrValue);
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

	public static class ConformanceAttrContext extends ParserRuleContext {
		public TerminalNode CONFATTR() { return getToken(Hl7ItsParser.CONFATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public ConformanceAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conformanceAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterConformanceAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitConformanceAttr(this);
		}
	}

	public final ConformanceAttrContext conformanceAttr() throws RecognitionException {
		ConformanceAttrContext _localctx = new ConformanceAttrContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_conformanceAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(612);
			match(CONFATTR);
			setState(613);
			match(AttrValue);
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

	public static class FlexibilityAttrContext extends ParserRuleContext {
		public TerminalNode FLEXIBILITYATTR() { return getToken(Hl7ItsParser.FLEXIBILITYATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public FlexibilityAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flexibilityAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterFlexibilityAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitFlexibilityAttr(this);
		}
	}

	public final FlexibilityAttrContext flexibilityAttr() throws RecognitionException {
		FlexibilityAttrContext _localctx = new FlexibilityAttrContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_flexibilityAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(615);
			match(FLEXIBILITYATTR);
			setState(616);
			match(AttrValue);
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

	public static class CodeAttrContext extends ParserRuleContext {
		public TerminalNode CODEATTR() { return getToken(Hl7ItsParser.CODEATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public CodeAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_codeAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterCodeAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitCodeAttr(this);
		}
	}

	public final CodeAttrContext codeAttr() throws RecognitionException {
		CodeAttrContext _localctx = new CodeAttrContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_codeAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(618);
			match(CODEATTR);
			setState(619);
			match(AttrValue);
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

	public static class ContainsAttrContext extends ParserRuleContext {
		public TerminalNode CONTAINSATTR() { return getToken(Hl7ItsParser.CONTAINSATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public ContainsAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_containsAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterContainsAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitContainsAttr(this);
		}
	}

	public final ContainsAttrContext containsAttr() throws RecognitionException {
		ContainsAttrContext _localctx = new ContainsAttrContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_containsAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(621);
			match(CONTAINSATTR);
			setState(622);
			match(AttrValue);
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

	public static class DataTypeAttrContext extends ParserRuleContext {
		public TerminalNode TYPEATTR() { return getToken(Hl7ItsParser.TYPEATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public DataTypeAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataTypeAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterDataTypeAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitDataTypeAttr(this);
		}
	}

	public final DataTypeAttrContext dataTypeAttr() throws RecognitionException {
		DataTypeAttrContext _localctx = new DataTypeAttrContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_dataTypeAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(624);
			match(TYPEATTR);
			setState(625);
			match(AttrValue);
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

	public static class IdAttrContext extends ParserRuleContext {
		public TerminalNode IDATTR() { return getToken(Hl7ItsParser.IDATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public IdAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterIdAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitIdAttr(this);
		}
	}

	public final IdAttrContext idAttr() throws RecognitionException {
		IdAttrContext _localctx = new IdAttrContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_idAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(627);
			match(IDATTR);
			setState(628);
			match(AttrValue);
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

	public static class IsMandatoryAttrContext extends ParserRuleContext {
		public TerminalNode MANDATTR() { return getToken(Hl7ItsParser.MANDATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public IsMandatoryAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isMandatoryAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterIsMandatoryAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitIsMandatoryAttr(this);
		}
	}

	public final IsMandatoryAttrContext isMandatoryAttr() throws RecognitionException {
		IsMandatoryAttrContext _localctx = new IsMandatoryAttrContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_isMandatoryAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(630);
			match(MANDATTR);
			setState(631);
			match(AttrValue);
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

	public static class IsOptionalAttrContext extends ParserRuleContext {
		public TerminalNode OPTATTR() { return getToken(Hl7ItsParser.OPTATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public IsOptionalAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_isOptionalAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterIsOptionalAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitIsOptionalAttr(this);
		}
	}

	public final IsOptionalAttrContext isOptionalAttr() throws RecognitionException {
		IsOptionalAttrContext _localctx = new IsOptionalAttrContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_isOptionalAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(633);
			match(OPTATTR);
			setState(634);
			match(AttrValue);
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

	public static class MaxOccursAttrContext extends ParserRuleContext {
		public TerminalNode MAXOCCURSATTR() { return getToken(Hl7ItsParser.MAXOCCURSATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public MaxOccursAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_maxOccursAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterMaxOccursAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitMaxOccursAttr(this);
		}
	}

	public final MaxOccursAttrContext maxOccursAttr() throws RecognitionException {
		MaxOccursAttrContext _localctx = new MaxOccursAttrContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_maxOccursAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(636);
			match(MAXOCCURSATTR);
			setState(637);
			match(AttrValue);
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

	public static class MinOccursAttrContext extends ParserRuleContext {
		public TerminalNode MINOCCURSATTR() { return getToken(Hl7ItsParser.MINOCCURSATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public MinOccursAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_minOccursAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterMinOccursAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitMinOccursAttr(this);
		}
	}

	public final MinOccursAttrContext minOccursAttr() throws RecognitionException {
		MinOccursAttrContext _localctx = new MinOccursAttrContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_minOccursAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(639);
			match(MINOCCURSATTR);
			setState(640);
			match(AttrValue);
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

	public static class NameAttrContext extends ParserRuleContext {
		public TerminalNode NAMEATTR() { return getToken(Hl7ItsParser.NAMEATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public NameAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nameAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterNameAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitNameAttr(this);
		}
	}

	public final NameAttrContext nameAttr() throws RecognitionException {
		NameAttrContext _localctx = new NameAttrContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_nameAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(642);
			match(NAMEATTR);
			setState(643);
			match(AttrValue);
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

	public static class ProhibitedAttrContext extends ParserRuleContext {
		public TerminalNode PROHIBITED() { return getToken(Hl7ItsParser.PROHIBITED, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public ProhibitedAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prohibitedAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterProhibitedAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitProhibitedAttr(this);
		}
	}

	public final ProhibitedAttrContext prohibitedAttr() throws RecognitionException {
		ProhibitedAttrContext _localctx = new ProhibitedAttrContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_prohibitedAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(645);
			match(PROHIBITED);
			setState(646);
			match(AttrValue);
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

	public static class RefAttrContext extends ParserRuleContext {
		public TerminalNode REFATTR() { return getToken(Hl7ItsParser.REFATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public RefAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_refAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterRefAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitRefAttr(this);
		}
	}

	public final RefAttrContext refAttr() throws RecognitionException {
		RefAttrContext _localctx = new RefAttrContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_refAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(648);
			match(REFATTR);
			setState(649);
			match(AttrValue);
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

	public static class StrengthAttrContext extends ParserRuleContext {
		public TerminalNode STRENGTHATTR() { return getToken(Hl7ItsParser.STRENGTHATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public StrengthAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strengthAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterStrengthAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitStrengthAttr(this);
		}
	}

	public final StrengthAttrContext strengthAttr() throws RecognitionException {
		StrengthAttrContext _localctx = new StrengthAttrContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_strengthAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(651);
			match(STRENGTHATTR);
			setState(652);
			match(AttrValue);
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

	public static class ValueAttrContext extends ParserRuleContext {
		public TerminalNode VALUEATTR() { return getToken(Hl7ItsParser.VALUEATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public ValueAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterValueAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitValueAttr(this);
		}
	}

	public final ValueAttrContext valueAttr() throws RecognitionException {
		ValueAttrContext _localctx = new ValueAttrContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_valueAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(654);
			match(VALUEATTR);
			setState(655);
			match(AttrValue);
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

	public static class ValueSetAttrContext extends ParserRuleContext {
		public TerminalNode VALUESETATTR() { return getToken(Hl7ItsParser.VALUESETATTR, 0); }
		public TerminalNode AttrValue() { return getToken(Hl7ItsParser.AttrValue, 0); }
		public ValueSetAttrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueSetAttr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterValueSetAttr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitValueSetAttr(this);
		}
	}

	public final ValueSetAttrContext valueSetAttr() throws RecognitionException {
		ValueSetAttrContext _localctx = new ValueSetAttrContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_valueSetAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(657);
			match(VALUESETATTR);
			setState(658);
			match(AttrValue);
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

	public static class ChardataContext extends ParserRuleContext {
		public TerminalNode TEXT() { return getToken(Hl7ItsParser.TEXT, 0); }
		public TerminalNode SEA_WS() { return getToken(Hl7ItsParser.SEA_WS, 0); }
		public ChardataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chardata; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterChardata(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitChardata(this);
		}
	}

	public final ChardataContext chardata() throws RecognitionException {
		ChardataContext _localctx = new ChardataContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_chardata);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(660);
			_la = _input.LA(1);
			if ( !(_la==SEA_WS || _la==TEXT) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class MiscContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(Hl7ItsParser.COMMENT, 0); }
		public TerminalNode PI() { return getToken(Hl7ItsParser.PI, 0); }
		public TerminalNode SEA_WS() { return getToken(Hl7ItsParser.SEA_WS, 0); }
		public MiscContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_misc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).enterMisc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Hl7ItsParserListener ) ((Hl7ItsParserListener)listener).exitMisc(this);
		}
	}

	public final MiscContext misc() throws RecognitionException {
		MiscContext _localctx = new MiscContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_misc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(662);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMENT) | (1L << SEA_WS) | (1L << PI))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3.\u029b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\7\2G\n\2\f\2\16\2J\13\2\3\2\3\2\5\2N\n\2\3\3\5\3Q"+
		"\n\3\3\3\3\3\3\3\7\3V\n\3\f\3\16\3Y\13\3\3\3\5\3\\\n\3\3\3\7\3_\n\3\f"+
		"\3\16\3b\13\3\3\3\5\3e\n\3\3\3\7\3h\n\3\f\3\16\3k\13\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\7\4w\n\4\f\4\16\4z\13\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\7\5\u0086\n\5\f\5\16\5\u0089\13\5\3\5\5\5\u008c\n\5"+
		"\3\5\5\5\u008f\n\5\3\5\5\5\u0092\n\5\3\5\5\5\u0095\n\5\3\5\5\5\u0098\n"+
		"\5\3\5\5\5\u009b\n\5\3\5\5\5\u009e\n\5\3\5\5\5\u00a1\n\5\3\5\5\5\u00a4"+
		"\n\5\3\5\5\5\u00a7\n\5\3\5\5\5\u00aa\n\5\3\5\3\5\3\5\3\5\7\5\u00b0\n\5"+
		"\f\5\16\5\u00b3\13\5\3\5\5\5\u00b6\n\5\3\5\5\5\u00b9\n\5\3\5\5\5\u00bc"+
		"\n\5\3\5\5\5\u00bf\n\5\3\5\5\5\u00c2\n\5\3\5\5\5\u00c5\n\5\3\5\5\5\u00c8"+
		"\n\5\3\5\5\5\u00cb\n\5\3\5\5\5\u00ce\n\5\3\5\5\5\u00d1\n\5\3\5\5\5\u00d4"+
		"\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00dd\n\5\3\6\3\6\3\6\7\6\u00e2\n"+
		"\6\f\6\16\6\u00e5\13\6\3\6\5\6\u00e8\n\6\3\6\5\6\u00eb\n\6\3\6\5\6\u00ee"+
		"\n\6\3\6\5\6\u00f1\n\6\3\6\5\6\u00f4\n\6\3\6\5\6\u00f7\n\6\3\6\5\6\u00fa"+
		"\n\6\3\6\5\6\u00fd\n\6\3\6\5\6\u0100\n\6\3\6\5\6\u0103\n\6\3\6\3\6\3\6"+
		"\3\6\7\6\u0109\n\6\f\6\16\6\u010c\13\6\3\6\5\6\u010f\n\6\3\6\5\6\u0112"+
		"\n\6\3\6\5\6\u0115\n\6\3\6\5\6\u0118\n\6\3\6\5\6\u011b\n\6\3\6\5\6\u011e"+
		"\n\6\3\6\5\6\u0121\n\6\3\6\5\6\u0124\n\6\3\6\5\6\u0127\n\6\3\6\5\6\u012a"+
		"\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0133\n\6\3\7\3\7\3\7\7\7\u0138\n"+
		"\7\f\7\16\7\u013b\13\7\3\7\5\7\u013e\n\7\3\7\5\7\u0141\n\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\7\b\u014d\n\b\f\b\16\b\u0150\13\b\3\b\5\b"+
		"\u0153\n\b\3\b\5\b\u0156\n\b\3\b\5\b\u0159\n\b\3\b\5\b\u015c\n\b\3\b\5"+
		"\b\u015f\n\b\3\b\5\b\u0162\n\b\3\b\5\b\u0165\n\b\3\b\7\b\u0168\n\b\f\b"+
		"\16\b\u016b\13\b\3\b\3\b\3\b\3\b\7\b\u0171\n\b\f\b\16\b\u0174\13\b\3\b"+
		"\5\b\u0177\n\b\3\b\5\b\u017a\n\b\3\b\5\b\u017d\n\b\3\b\5\b\u0180\n\b\3"+
		"\b\5\b\u0183\n\b\3\b\5\b\u0186\n\b\3\b\5\b\u0189\n\b\3\b\7\b\u018c\n\b"+
		"\f\b\16\b\u018f\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0198\n\b\3\t\3\t"+
		"\3\t\7\t\u019d\n\t\f\t\16\t\u01a0\13\t\3\t\5\t\u01a3\n\t\3\t\5\t\u01a6"+
		"\n\t\3\t\5\t\u01a9\n\t\3\t\7\t\u01ac\n\t\f\t\16\t\u01af\13\t\3\t\3\t\3"+
		"\t\3\t\7\t\u01b5\n\t\f\t\16\t\u01b8\13\t\3\t\5\t\u01bb\n\t\3\t\5\t\u01be"+
		"\n\t\3\t\5\t\u01c1\n\t\3\t\7\t\u01c4\n\t\f\t\16\t\u01c7\13\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\5\t\u01d0\n\t\3\n\3\n\3\n\5\n\u01d5\n\n\3\n\5\n\u01d8"+
		"\n\n\3\n\3\n\3\n\3\n\5\n\u01de\n\n\3\n\5\n\u01e1\n\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\5\n\u01ea\n\n\3\13\3\13\3\13\7\13\u01ef\n\13\f\13\16\13\u01f2"+
		"\13\13\3\13\3\13\3\13\3\13\7\13\u01f8\n\13\f\13\16\13\u01fb\13\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0204\n\13\3\f\3\f\3\f\7\f\u0209\n"+
		"\f\f\f\16\f\u020c\13\f\3\f\3\f\3\f\3\f\7\f\u0212\n\f\f\f\16\f\u0215\13"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u021e\n\f\3\r\5\r\u0221\n\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0231\n\r\3\r\5\r"+
		"\u0234\n\r\7\r\u0236\n\r\f\r\16\r\u0239\13\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u024a\n\16\f\16"+
		"\16\16\u024d\13\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7"+
		"\16\u0259\n\16\f\16\16\16\u025c\13\16\3\16\5\16\u025f\n\16\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3"+
		"\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3!\3!\3\"\3\"\3\"\2\2#\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@B\2\5\3\2"+
		"\6\7\4\2\b\b\13\13\5\2\3\3\b\b..\2\u02f3\2D\3\2\2\2\4P\3\2\2\2\6s\3\2"+
		"\2\2\b\u00dc\3\2\2\2\n\u0132\3\2\2\2\f\u0134\3\2\2\2\16\u0197\3\2\2\2"+
		"\20\u01cf\3\2\2\2\22\u01e9\3\2\2\2\24\u0203\3\2\2\2\26\u021d\3\2\2\2\30"+
		"\u0220\3\2\2\2\32\u025e\3\2\2\2\34\u0260\3\2\2\2\36\u0262\3\2\2\2 \u0266"+
		"\3\2\2\2\"\u0269\3\2\2\2$\u026c\3\2\2\2&\u026f\3\2\2\2(\u0272\3\2\2\2"+
		"*\u0275\3\2\2\2,\u0278\3\2\2\2.\u027b\3\2\2\2\60\u027e\3\2\2\2\62\u0281"+
		"\3\2\2\2\64\u0284\3\2\2\2\66\u0287\3\2\2\28\u028a\3\2\2\2:\u028d\3\2\2"+
		"\2<\u0290\3\2\2\2>\u0293\3\2\2\2@\u0296\3\2\2\2B\u0298\3\2\2\2DH\7\n\2"+
		"\2EG\5\36\20\2FE\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2"+
		"\2\2KM\7\'\2\2LN\7\b\2\2ML\3\2\2\2MN\3\2\2\2N\3\3\2\2\2OQ\5\2\2\2PO\3"+
		"\2\2\2PQ\3\2\2\2QR\3\2\2\2RS\7\t\2\2SW\7!\2\2TV\5\36\20\2UT\3\2\2\2VY"+
		"\3\2\2\2WU\3\2\2\2WX\3\2\2\2X[\3\2\2\2YW\3\2\2\2Z\\\5*\26\2[Z\3\2\2\2"+
		"[\\\3\2\2\2\\`\3\2\2\2]_\5\36\20\2^]\3\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2"+
		"\2\2ad\3\2\2\2b`\3\2\2\2ce\5\64\33\2dc\3\2\2\2de\3\2\2\2ei\3\2\2\2fh\5"+
		"\36\20\2gf\3\2\2\2hk\3\2\2\2ig\3\2\2\2ij\3\2\2\2jl\3\2\2\2ki\3\2\2\2l"+
		"m\7&\2\2mn\5\30\r\2no\7\t\2\2op\7)\2\2pq\7!\2\2qr\7&\2\2r\5\3\2\2\2st"+
		"\7\t\2\2tx\7\23\2\2uw\5\36\20\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2"+
		"\2y{\3\2\2\2zx\3\2\2\2{|\7&\2\2|}\5\30\r\2}~\7\t\2\2~\177\7)\2\2\177\u0080"+
		"\7\23\2\2\u0080\u0081\7&\2\2\u0081\7\3\2\2\2\u0082\u0083\7\t\2\2\u0083"+
		"\u0087\7\24\2\2\u0084\u0086\5\36\20\2\u0085\u0084\3\2\2\2\u0086\u0089"+
		"\3\2\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u008a\u008c\5 \21\2\u008b\u008a\3\2\2\2\u008b\u008c\3\2"+
		"\2\2\u008c\u008e\3\2\2\2\u008d\u008f\5&\24\2\u008e\u008d\3\2\2\2\u008e"+
		"\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u0092\5(\25\2\u0091\u0090\3\2"+
		"\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2\2\2\u0093\u0095\5\"\22\2\u0094"+
		"\u0093\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0098\5*"+
		"\26\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3\2\2\2\u0099"+
		"\u009b\5,\27\2\u009a\u0099\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d\3\2"+
		"\2\2\u009c\u009e\5\60\31\2\u009d\u009c\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u00a0\3\2\2\2\u009f\u00a1\5\62\32\2\u00a0\u009f\3\2\2\2\u00a0\u00a1\3"+
		"\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a4\5\64\33\2\u00a3\u00a2\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u00a7\5<\37\2\u00a6\u00a5\3\2"+
		"\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a9\3\2\2\2\u00a8\u00aa\5:\36\2\u00a9"+
		"\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00dd\7("+
		"\2\2\u00ac\u00ad\7\t\2\2\u00ad\u00b1\7\24\2\2\u00ae\u00b0\5\36\20\2\u00af"+
		"\u00ae\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b6\5 \21\2\u00b5"+
		"\u00b4\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b9\5&"+
		"\24\2\u00b8\u00b7\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bb\3\2\2\2\u00ba"+
		"\u00bc\5(\25\2\u00bb\u00ba\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2"+
		"\2\2\u00bd\u00bf\5\"\22\2\u00be\u00bd\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c1\3\2\2\2\u00c0\u00c2\5*\26\2\u00c1\u00c0\3\2\2\2\u00c1\u00c2\3\2"+
		"\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c5\5,\27\2\u00c4\u00c3\3\2\2\2\u00c4"+
		"\u00c5\3\2\2\2\u00c5\u00c7\3\2\2\2\u00c6\u00c8\5\60\31\2\u00c7\u00c6\3"+
		"\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9\u00cb\5\62\32\2\u00ca"+
		"\u00c9\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00ce\5\64"+
		"\33\2\u00cd\u00cc\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf"+
		"\u00d1\5<\37\2\u00d0\u00cf\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3\3\2"+
		"\2\2\u00d2\u00d4\5:\36\2\u00d3\u00d2\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00d6\7&\2\2\u00d6\u00d7\5\30\r\2\u00d7\u00d8\7\t"+
		"\2\2\u00d8\u00d9\7)\2\2\u00d9\u00da\7\24\2\2\u00da\u00db\7&\2\2\u00db"+
		"\u00dd\3\2\2\2\u00dc\u0082\3\2\2\2\u00dc\u00ac\3\2\2\2\u00dd\t\3\2\2\2"+
		"\u00de\u00df\7\t\2\2\u00df\u00e3\7\r\2\2\u00e0\u00e2\5\36\20\2\u00e1\u00e0"+
		"\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4"+
		"\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e8\5 \21\2\u00e7\u00e6\3\2"+
		"\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00ea\3\2\2\2\u00e9\u00eb\5(\25\2\u00ea"+
		"\u00e9\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00ee\5*"+
		"\26\2\u00ed\u00ec\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef"+
		"\u00f1\5,\27\2\u00f0\u00ef\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2"+
		"\2\2\u00f2\u00f4\5.\30\2\u00f3\u00f2\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4"+
		"\u00f6\3\2\2\2\u00f5\u00f7\5\60\31\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3"+
		"\2\2\2\u00f7\u00f9\3\2\2\2\u00f8\u00fa\5\62\32\2\u00f9\u00f8\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00fd\5\64\33\2\u00fc\u00fb\3"+
		"\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00ff\3\2\2\2\u00fe\u0100\5\66\34\2\u00ff"+
		"\u00fe\3\2\2\2\u00ff\u0100\3\2\2\2\u0100\u0102\3\2\2\2\u0101\u0103\5<"+
		"\37\2\u0102\u0101\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104"+
		"\u0133\7(\2\2\u0105\u0106\7\t\2\2\u0106\u010a\7\r\2\2\u0107\u0109\5\36"+
		"\20\2\u0108\u0107\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010d\u010f\5 "+
		"\21\2\u010e\u010d\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0111\3\2\2\2\u0110"+
		"\u0112\5(\25\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0114\3\2"+
		"\2\2\u0113\u0115\5*\26\2\u0114\u0113\3\2\2\2\u0114\u0115\3\2\2\2\u0115"+
		"\u0117\3\2\2\2\u0116\u0118\5,\27\2\u0117\u0116\3\2\2\2\u0117\u0118\3\2"+
		"\2\2\u0118\u011a\3\2\2\2\u0119\u011b\5.\30\2\u011a\u0119\3\2\2\2\u011a"+
		"\u011b\3\2\2\2\u011b\u011d\3\2\2\2\u011c\u011e\5\60\31\2\u011d\u011c\3"+
		"\2\2\2\u011d\u011e\3\2\2\2\u011e\u0120\3\2\2\2\u011f\u0121\5\62\32\2\u0120"+
		"\u011f\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0123\3\2\2\2\u0122\u0124\5\64"+
		"\33\2\u0123\u0122\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126\3\2\2\2\u0125"+
		"\u0127\5\66\34\2\u0126\u0125\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u0129\3"+
		"\2\2\2\u0128\u012a\5<\37\2\u0129\u0128\3\2\2\2\u0129\u012a\3\2\2\2\u012a"+
		"\u012b\3\2\2\2\u012b\u012c\7&\2\2\u012c\u012d\5\30\r\2\u012d\u012e\7\t"+
		"\2\2\u012e\u012f\7)\2\2\u012f\u0130\7\r\2\2\u0130\u0131\7&\2\2\u0131\u0133"+
		"\3\2\2\2\u0132\u00de\3\2\2\2\u0132\u0105\3\2\2\2\u0133\13\3\2\2\2\u0134"+
		"\u0135\7\t\2\2\u0135\u0139\7\16\2\2\u0136\u0138\5\36\20\2\u0137\u0136"+
		"\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a"+
		"\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013c\u013e\5\60\31\2\u013d\u013c\3"+
		"\2\2\2\u013d\u013e\3\2\2\2\u013e\u0140\3\2\2\2\u013f\u0141\5\62\32\2\u0140"+
		"\u013f\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0143\7&"+
		"\2\2\u0143\u0144\5\30\r\2\u0144\u0145\7\t\2\2\u0145\u0146\7)\2\2\u0146"+
		"\u0147\7\16\2\2\u0147\u0148\7&\2\2\u0148\r\3\2\2\2\u0149\u014a\7\t\2\2"+
		"\u014a\u014e\7\26\2\2\u014b\u014d\5\36\20\2\u014c\u014b\3\2\2\2\u014d"+
		"\u0150\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0152\3\2"+
		"\2\2\u0150\u014e\3\2\2\2\u0151\u0153\5 \21\2\u0152\u0151\3\2\2\2\u0152"+
		"\u0153\3\2\2\2\u0153\u0155\3\2\2\2\u0154\u0156\5\"\22\2\u0155\u0154\3"+
		"\2\2\2\u0155\u0156\3\2\2\2\u0156\u0158\3\2\2\2\u0157\u0159\5*\26\2\u0158"+
		"\u0157\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015b\3\2\2\2\u015a\u015c\5,"+
		"\27\2\u015b\u015a\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015e\3\2\2\2\u015d"+
		"\u015f\5\60\31\2\u015e\u015d\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0161\3"+
		"\2\2\2\u0160\u0162\5\62\32\2\u0161\u0160\3\2\2\2\u0161\u0162\3\2\2\2\u0162"+
		"\u0164\3\2\2\2\u0163\u0165\58\35\2\u0164\u0163\3\2\2\2\u0164\u0165\3\2"+
		"\2\2\u0165\u0169\3\2\2\2\u0166\u0168\5\36\20\2\u0167\u0166\3\2\2\2\u0168"+
		"\u016b\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016c\3\2"+
		"\2\2\u016b\u0169\3\2\2\2\u016c\u0198\7(\2\2\u016d\u016e\7\t\2\2\u016e"+
		"\u0172\7\26\2\2\u016f\u0171\5\36\20\2\u0170\u016f\3\2\2\2\u0171\u0174"+
		"\3\2\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0176\3\2\2\2\u0174"+
		"\u0172\3\2\2\2\u0175\u0177\5 \21\2\u0176\u0175\3\2\2\2\u0176\u0177\3\2"+
		"\2\2\u0177\u0179\3\2\2\2\u0178\u017a\5\"\22\2\u0179\u0178\3\2\2\2\u0179"+
		"\u017a\3\2\2\2\u017a\u017c\3\2\2\2\u017b\u017d\5*\26\2\u017c\u017b\3\2"+
		"\2\2\u017c\u017d\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u0180\5,\27\2\u017f"+
		"\u017e\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0182\3\2\2\2\u0181\u0183\5\60"+
		"\31\2\u0182\u0181\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0185\3\2\2\2\u0184"+
		"\u0186\5\62\32\2\u0185\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0188\3"+
		"\2\2\2\u0187\u0189\58\35\2\u0188\u0187\3\2\2\2\u0188\u0189\3\2\2\2\u0189"+
		"\u018d\3\2\2\2\u018a\u018c\5\36\20\2\u018b\u018a\3\2\2\2\u018c\u018f\3"+
		"\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u0190\3\2\2\2\u018f"+
		"\u018d\3\2\2\2\u0190\u0191\7&\2\2\u0191\u0192\5\30\r\2\u0192\u0193\7\t"+
		"\2\2\u0193\u0194\7)\2\2\u0194\u0195\7\26\2\2\u0195\u0196\7&\2\2\u0196"+
		"\u0198\3\2\2\2\u0197\u0149\3\2\2\2\u0197\u016d\3\2\2\2\u0198\17\3\2\2"+
		"\2\u0199\u019a\7\t\2\2\u019a\u019e\7%\2\2\u019b\u019d\5\36\20\2\u019c"+
		"\u019b\3\2\2\2\u019d\u01a0\3\2\2\2\u019e\u019c\3\2\2\2\u019e\u019f\3\2"+
		"\2\2\u019f\u01a2\3\2\2\2\u01a0\u019e\3\2\2\2\u01a1\u01a3\5$\23\2\u01a2"+
		"\u01a1\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a5\3\2\2\2\u01a4\u01a6\5\""+
		"\22\2\u01a5\u01a4\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a8\3\2\2\2\u01a7"+
		"\u01a9\5> \2\u01a8\u01a7\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01ad\3\2\2"+
		"\2\u01aa\u01ac\5\36\20\2\u01ab\u01aa\3\2\2\2\u01ac\u01af\3\2\2\2\u01ad"+
		"\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01b0\3\2\2\2\u01af\u01ad\3\2"+
		"\2\2\u01b0\u01d0\7(\2\2\u01b1\u01b2\7\t\2\2\u01b2\u01b6\7%\2\2\u01b3\u01b5"+
		"\5\36\20\2\u01b4\u01b3\3\2\2\2\u01b5\u01b8\3\2\2\2\u01b6\u01b4\3\2\2\2"+
		"\u01b6\u01b7\3\2\2\2\u01b7\u01ba\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b9\u01bb"+
		"\5$\23\2\u01ba\u01b9\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bd\3\2\2\2\u01bc"+
		"\u01be\5\"\22\2\u01bd\u01bc\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\u01c0\3"+
		"\2\2\2\u01bf\u01c1\5> \2\u01c0\u01bf\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1"+
		"\u01c5\3\2\2\2\u01c2\u01c4\5\36\20\2\u01c3\u01c2\3\2\2\2\u01c4\u01c7\3"+
		"\2\2\2\u01c5\u01c3\3\2\2\2\u01c5\u01c6\3\2\2\2\u01c6\u01c8\3\2\2\2\u01c7"+
		"\u01c5\3\2\2\2\u01c8\u01c9\7&\2\2\u01c9\u01ca\5\30\r\2\u01ca\u01cb\7\t"+
		"\2\2\u01cb\u01cc\7)\2\2\u01cc\u01cd\7%\2\2\u01cd\u01ce\7&\2\2\u01ce\u01d0"+
		"\3\2\2\2\u01cf\u0199\3\2\2\2\u01cf\u01b1\3\2\2\2\u01d0\21\3\2\2\2\u01d1"+
		"\u01d2\7\t\2\2\u01d2\u01d4\7\27\2\2\u01d3\u01d5\5\64\33\2\u01d4\u01d3"+
		"\3\2\2\2\u01d4\u01d5\3\2\2\2\u01d5\u01d7\3\2\2\2\u01d6\u01d8\5<\37\2\u01d7"+
		"\u01d6\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u01ea\7("+
		"\2\2\u01da\u01db\7\t\2\2\u01db\u01dd\7\27\2\2\u01dc\u01de\5\64\33\2\u01dd"+
		"\u01dc\3\2\2\2\u01dd\u01de\3\2\2\2\u01de\u01e0\3\2\2\2\u01df\u01e1\5<"+
		"\37\2\u01e0\u01df\3\2\2\2\u01e0\u01e1\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2"+
		"\u01e3\7&\2\2\u01e3\u01e4\5\30\r\2\u01e4\u01e5\7\t\2\2\u01e5\u01e6\7)"+
		"\2\2\u01e6\u01e7\7\27\2\2\u01e7\u01e8\7&\2\2\u01e8\u01ea\3\2\2\2\u01e9"+
		"\u01d1\3\2\2\2\u01e9\u01da\3\2\2\2\u01ea\23\3\2\2\2\u01eb\u01ec\7\t\2"+
		"\2\u01ec\u01f0\7\f\2\2\u01ed\u01ef\5\36\20\2\u01ee\u01ed\3\2\2\2\u01ef"+
		"\u01f2\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f3\3\2"+
		"\2\2\u01f2\u01f0\3\2\2\2\u01f3\u0204\7(\2\2\u01f4\u01f5\7\t\2\2\u01f5"+
		"\u01f9\7\f\2\2\u01f6\u01f8\5\36\20\2\u01f7\u01f6\3\2\2\2\u01f8\u01fb\3"+
		"\2\2\2\u01f9\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fc\3\2\2\2\u01fb"+
		"\u01f9\3\2\2\2\u01fc\u01fd\7&\2\2\u01fd\u01fe\5\30\r\2\u01fe\u01ff\7\t"+
		"\2\2\u01ff\u0200\7)\2\2\u0200\u0201\7\f\2\2\u0201\u0202\7&\2\2\u0202\u0204"+
		"\3\2\2\2\u0203\u01eb\3\2\2\2\u0203\u01f4\3\2\2\2\u0204\25\3\2\2\2\u0205"+
		"\u0206\7\t\2\2\u0206\u020a\7\37\2\2\u0207\u0209\5\36\20\2\u0208\u0207"+
		"\3\2\2\2\u0209\u020c\3\2\2\2\u020a\u0208\3\2\2\2\u020a\u020b\3\2\2\2\u020b"+
		"\u020d\3\2\2\2\u020c\u020a\3\2\2\2\u020d\u021e\7(\2\2\u020e\u020f\7\t"+
		"\2\2\u020f\u0213\7\37\2\2\u0210\u0212\5\36\20\2\u0211\u0210\3\2\2\2\u0212"+
		"\u0215\3\2\2\2\u0213\u0211\3\2\2\2\u0213\u0214\3\2\2\2\u0214\u0216\3\2"+
		"\2\2\u0215\u0213\3\2\2\2\u0216\u0217\7&\2\2\u0217\u0218\5\30\r\2\u0218"+
		"\u0219\7\t\2\2\u0219\u021a\7)\2\2\u021a\u021b\7\37\2\2\u021b\u021c\7&"+
		"\2\2\u021c\u021e\3\2\2\2\u021d\u0205\3\2\2\2\u021d\u020e\3\2\2\2\u021e"+
		"\27\3\2\2\2\u021f\u0221\5@!\2\u0220\u021f\3\2\2\2\u0220\u0221\3\2\2\2"+
		"\u0221\u0237\3\2\2\2\u0222\u0231\5\32\16\2\u0223\u0231\5\6\4\2\u0224\u0231"+
		"\5\b\5\2\u0225\u0231\5\16\b\2\u0226\u0231\5\f\7\2\u0227\u0231\5\22\n\2"+
		"\u0228\u0231\5\24\13\2\u0229\u0231\5\26\f\2\u022a\u0231\5\34\17\2\u022b"+
		"\u0231\5\n\6\2\u022c\u0231\5\20\t\2\u022d\u0231\7\4\2\2\u022e\u0231\7"+
		".\2\2\u022f\u0231\7\3\2\2\u0230\u0222\3\2\2\2\u0230\u0223\3\2\2\2\u0230"+
		"\u0224\3\2\2\2\u0230\u0225\3\2\2\2\u0230\u0226\3\2\2\2\u0230\u0227\3\2"+
		"\2\2\u0230\u0228\3\2\2\2\u0230\u0229\3\2\2\2\u0230\u022a\3\2\2\2\u0230"+
		"\u022b\3\2\2\2\u0230\u022c\3\2\2\2\u0230\u022d\3\2\2\2\u0230\u022e\3\2"+
		"\2\2\u0230\u022f\3\2\2\2\u0231\u0233\3\2\2\2\u0232\u0234\5@!\2\u0233\u0232"+
		"\3\2\2\2\u0233\u0234\3\2\2\2\u0234\u0236\3\2\2\2\u0235\u0230\3\2\2\2\u0236"+
		"\u0239\3\2\2\2\u0237\u0235\3\2\2\2\u0237\u0238\3\2\2\2\u0238\31\3\2\2"+
		"\2\u0239\u0237\3\2\2\2\u023a\u023b\7\t\2\2\u023b\u023c\7,\2\2\u023c\u023d"+
		"\7&\2\2\u023d\u023e\5\30\r\2\u023e\u023f\7\t\2\2\u023f\u0240\7)\2\2\u0240"+
		"\u0241\7,\2\2\u0241\u0242\7&\2\2\u0242\u025f\3\2\2\2\u0243\u0244\7\t\2"+
		"\2\u0244\u0245\7,\2\2\u0245\u025f\7(\2\2\u0246\u0247\7\t\2\2\u0247\u024b"+
		"\7,\2\2\u0248\u024a\5\36\20\2\u0249\u0248\3\2\2\2\u024a\u024d\3\2\2\2"+
		"\u024b\u0249\3\2\2\2\u024b\u024c\3\2\2\2\u024c\u024e\3\2\2\2\u024d\u024b"+
		"\3\2\2\2\u024e\u024f\7&\2\2\u024f\u0250\5\30\r\2\u0250\u0251\7\t\2\2\u0251"+
		"\u0252\7)\2\2\u0252\u0253\7,\2\2\u0253\u0254\7&\2\2\u0254\u025f\3\2\2"+
		"\2\u0255\u0256\7\t\2\2\u0256\u025a\7,\2\2\u0257\u0259\5\36\20\2\u0258"+
		"\u0257\3\2\2\2\u0259\u025c\3\2\2\2\u025a\u0258\3\2\2\2\u025a\u025b\3\2"+
		"\2\2\u025b\u025d\3\2\2\2\u025c\u025a\3\2\2\2\u025d\u025f\7(\2\2\u025e"+
		"\u023a\3\2\2\2\u025e\u0243\3\2\2\2\u025e\u0246\3\2\2\2\u025e\u0255\3\2"+
		"\2\2\u025f\33\3\2\2\2\u0260\u0261\t\2\2\2\u0261\35\3\2\2\2\u0262\u0263"+
		"\7,\2\2\u0263\u0264\7*\2\2\u0264\u0265\7+\2\2\u0265\37\3\2\2\2\u0266\u0267"+
		"\7\20\2\2\u0267\u0268\7+\2\2\u0268!\3\2\2\2\u0269\u026a\7\21\2\2\u026a"+
		"\u026b\7+\2\2\u026b#\3\2\2\2\u026c\u026d\7\17\2\2\u026d\u026e\7+\2\2\u026e"+
		"%\3\2\2\2\u026f\u0270\7\22\2\2\u0270\u0271\7+\2\2\u0271\'\3\2\2\2\u0272"+
		"\u0273\7\"\2\2\u0273\u0274\7+\2\2\u0274)\3\2\2\2\u0275\u0276\7\25\2\2"+
		"\u0276\u0277\7+\2\2\u0277+\3\2\2\2\u0278\u0279\7\30\2\2\u0279\u027a\7"+
		"+\2\2\u027a-\3\2\2\2\u027b\u027c\7\34\2\2\u027c\u027d\7+\2\2\u027d/\3"+
		"\2\2\2\u027e\u027f\7\31\2\2\u027f\u0280\7+\2\2\u0280\61\3\2\2\2\u0281"+
		"\u0282\7\32\2\2\u0282\u0283\7+\2\2\u0283\63\3\2\2\2\u0284\u0285\7\33\2"+
		"\2\u0285\u0286\7+\2\2\u0286\65\3\2\2\2\u0287\u0288\7\35\2\2\u0288\u0289"+
		"\7+\2\2\u0289\67\3\2\2\2\u028a\u028b\7\36\2\2\u028b\u028c\7+\2\2\u028c"+
		"9\3\2\2\2\u028d\u028e\7 \2\2\u028e\u028f\7+\2\2\u028f;\3\2\2\2\u0290\u0291"+
		"\7#\2\2\u0291\u0292\7+\2\2\u0292=\3\2\2\2\u0293\u0294\7$\2\2\u0294\u0295"+
		"\7+\2\2\u0295?\3\2\2\2\u0296\u0297\t\3\2\2\u0297A\3\2\2\2\u0298\u0299"+
		"\t\4\2\2\u0299C\3\2\2\2nHMPW[`dix\u0087\u008b\u008e\u0091\u0094\u0097"+
		"\u009a\u009d\u00a0\u00a3\u00a6\u00a9\u00b1\u00b5\u00b8\u00bb\u00be\u00c1"+
		"\u00c4\u00c7\u00ca\u00cd\u00d0\u00d3\u00dc\u00e3\u00e7\u00ea\u00ed\u00f0"+
		"\u00f3\u00f6\u00f9\u00fc\u00ff\u0102\u010a\u010e\u0111\u0114\u0117\u011a"+
		"\u011d\u0120\u0123\u0126\u0129\u0132\u0139\u013d\u0140\u014e\u0152\u0155"+
		"\u0158\u015b\u015e\u0161\u0164\u0169\u0172\u0176\u0179\u017c\u017f\u0182"+
		"\u0185\u0188\u018d\u0197\u019e\u01a2\u01a5\u01a8\u01ad\u01b6\u01ba\u01bd"+
		"\u01c0\u01c5\u01cf\u01d4\u01d7\u01dd\u01e0\u01e9\u01f0\u01f9\u0203\u020a"+
		"\u0213\u021d\u0220\u0230\u0233\u0237\u024b\u025a\u025e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}