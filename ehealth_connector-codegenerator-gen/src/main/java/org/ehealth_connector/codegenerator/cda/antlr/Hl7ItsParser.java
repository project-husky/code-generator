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
		TEXT=9, ASSERT=10, ATTRIBUTE=11, CHOICE=12, CONFATTR=13, FLEXIBILITYATTR=14, 
		CONTAINSATTR=15, DESC=16, ELEMENT=17, IDATTR=18, INCLUDE=19, LET=20, MANDATTR=21, 
		MAXOCCURSATTR=22, MINOCCURSATTR=23, NAMEATTR=24, OPTATTR=25, PROHIBITED=26, 
		REFATTR=27, REPORT=28, STRENGTHATTR=29, TEMPLATE=30, TYPEATTR=31, VALUEATTR=32, 
		VALUESETATTR=33, VOCAB=34, CLOSE=35, SPECIAL_CLOSE=36, SLASH_CLOSE=37, 
		SLASH=38, EQUALS=39, AttrValue=40, Name=41, S=42, PI=43;
	public static final int
		RULE_prolog = 0, RULE_template = 1, RULE_desc = 2, RULE_element = 3, RULE_attribute = 4, 
		RULE_choice = 5, RULE_include = 6, RULE_vocab = 7, RULE_letter = 8, RULE_assertion = 9, 
		RULE_report = 10, RULE_content = 11, RULE_xmlelement = 12, RULE_reference = 13, 
		RULE_attr = 14, RULE_conformanceAttr = 15, RULE_flexibilityAttr = 16, 
		RULE_containsAttr = 17, RULE_dataTypeAttr = 18, RULE_idAttr = 19, RULE_isMandatoryAttr = 20, 
		RULE_isOptionalAttr = 21, RULE_maxOccursAttr = 22, RULE_minOccursAttr = 23, 
		RULE_nameAttr = 24, RULE_prohibitedAttr = 25, RULE_refAttr = 26, RULE_strengthAttr = 27, 
		RULE_valueAttr = 28, RULE_valueSetAttr = 29, RULE_chardata = 30, RULE_misc = 31;
	public static final String[] ruleNames = {
		"prolog", "template", "desc", "element", "attribute", "choice", "include", 
		"vocab", "letter", "assertion", "report", "content", "xmlelement", "reference", 
		"attr", "conformanceAttr", "flexibilityAttr", "containsAttr", "dataTypeAttr", 
		"idAttr", "isMandatoryAttr", "isOptionalAttr", "maxOccursAttr", "minOccursAttr", 
		"nameAttr", "prohibitedAttr", "refAttr", "strengthAttr", "valueAttr", 
		"valueSetAttr", "chardata", "misc"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'<'", null, null, "'assert'", 
		"'attribute'", "'choice'", "'conformance='", "'flexibility='", "'contains='", 
		"'desc'", "'element'", "'id='", "'include'", "'let'", "'isMandatory='", 
		"'maximumMultiplicity='", "'minimumMultiplicity='", "'name='", "'isOptional='", 
		"'prohibited='", "'ref='", "'report'", "'strength='", "'template'", "'datatype='", 
		"'value='", "'valueSet='", "'vocabulary'", "'>'", null, "'/>'", "'/'", 
		"'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COMMENT", "CDATA", "DTD", "EntityRef", "CharRef", "SEA_WS", "OPEN", 
		"XMLDeclOpen", "TEXT", "ASSERT", "ATTRIBUTE", "CHOICE", "CONFATTR", "FLEXIBILITYATTR", 
		"CONTAINSATTR", "DESC", "ELEMENT", "IDATTR", "INCLUDE", "LET", "MANDATTR", 
		"MAXOCCURSATTR", "MINOCCURSATTR", "NAMEATTR", "OPTATTR", "PROHIBITED", 
		"REFATTR", "REPORT", "STRENGTHATTR", "TEMPLATE", "TYPEATTR", "VALUEATTR", 
		"VALUESETATTR", "VOCAB", "CLOSE", "SPECIAL_CLOSE", "SLASH_CLOSE", "SLASH", 
		"EQUALS", "AttrValue", "Name", "S", "PI"
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
			setState(64);
			match(XMLDeclOpen);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(65);
				attr();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
			match(SPECIAL_CLOSE);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEA_WS) {
				{
				setState(72);
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
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==XMLDeclOpen) {
				{
				setState(75);
				prolog();
				}
			}

			setState(78);
			match(OPEN);
			setState(79);
			match(TEMPLATE);
			setState(83);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(80);
					attr();
					}
					} 
				}
				setState(85);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDATTR) {
				{
				setState(86);
				idAttr();
				}
			}

			setState(92);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(89);
					attr();
					}
					} 
				}
				setState(94);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAMEATTR) {
				{
				setState(95);
				nameAttr();
				}
			}

			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(98);
				attr();
				}
				}
				setState(103);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(104);
			match(CLOSE);
			setState(105);
			content();
			setState(106);
			match(OPEN);
			setState(107);
			match(SLASH);
			setState(108);
			match(TEMPLATE);
			setState(109);
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
			setState(111);
			match(OPEN);
			setState(112);
			match(DESC);
			setState(116);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(113);
				attr();
				}
				}
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(119);
			match(CLOSE);
			setState(120);
			content();
			setState(121);
			match(OPEN);
			setState(122);
			match(SLASH);
			setState(123);
			match(DESC);
			setState(124);
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
			setState(216);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(126);
				match(OPEN);
				setState(127);
				match(ELEMENT);
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(128);
					attr();
					}
					}
					setState(133);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(134);
					conformanceAttr();
					}
				}

				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONTAINSATTR) {
					{
					setState(137);
					containsAttr();
					}
				}

				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(140);
					dataTypeAttr();
					}
				}

				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(143);
					flexibilityAttr();
					}
				}

				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(146);
					idAttr();
					}
				}

				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(149);
					isMandatoryAttr();
					}
				}

				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(152);
					maxOccursAttr();
					}
				}

				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(155);
					minOccursAttr();
					}
				}

				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(158);
					nameAttr();
					}
				}

				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(161);
					valueAttr();
					}
				}

				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRENGTHATTR) {
					{
					setState(164);
					strengthAttr();
					}
				}

				setState(167);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				match(OPEN);
				setState(169);
				match(ELEMENT);
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(170);
					attr();
					}
					}
					setState(175);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(176);
					conformanceAttr();
					}
				}

				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONTAINSATTR) {
					{
					setState(179);
					containsAttr();
					}
				}

				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(182);
					dataTypeAttr();
					}
				}

				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(185);
					flexibilityAttr();
					}
				}

				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(188);
					idAttr();
					}
				}

				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(191);
					isMandatoryAttr();
					}
				}

				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(194);
					maxOccursAttr();
					}
				}

				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(197);
					minOccursAttr();
					}
				}

				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(200);
					nameAttr();
					}
				}

				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(203);
					valueAttr();
					}
				}

				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRENGTHATTR) {
					{
					setState(206);
					strengthAttr();
					}
				}

				setState(209);
				match(CLOSE);
				setState(210);
				content();
				setState(211);
				match(OPEN);
				setState(212);
				match(SLASH);
				setState(213);
				match(ELEMENT);
				setState(214);
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
			setState(302);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				match(OPEN);
				setState(219);
				match(ATTRIBUTE);
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(220);
					attr();
					}
					}
					setState(225);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(226);
					conformanceAttr();
					}
				}

				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(229);
					dataTypeAttr();
					}
				}

				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(232);
					idAttr();
					}
				}

				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(235);
					isMandatoryAttr();
					}
				}

				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(238);
					isOptionalAttr();
					}
				}

				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(241);
					maxOccursAttr();
					}
				}

				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(244);
					minOccursAttr();
					}
				}

				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(247);
					nameAttr();
					}
				}

				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(250);
					prohibitedAttr();
					}
				}

				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(253);
					valueAttr();
					}
				}

				setState(256);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(257);
				match(OPEN);
				setState(258);
				match(ATTRIBUTE);
				setState(262);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(259);
					attr();
					}
					}
					setState(264);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(265);
					conformanceAttr();
					}
				}

				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(268);
					dataTypeAttr();
					}
				}

				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(271);
					idAttr();
					}
				}

				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(274);
					isMandatoryAttr();
					}
				}

				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(277);
					isOptionalAttr();
					}
				}

				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(280);
					maxOccursAttr();
					}
				}

				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(283);
					minOccursAttr();
					}
				}

				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(286);
					nameAttr();
					}
				}

				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(289);
					prohibitedAttr();
					}
				}

				setState(293);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(292);
					valueAttr();
					}
				}

				setState(295);
				match(CLOSE);
				setState(296);
				content();
				setState(297);
				match(OPEN);
				setState(298);
				match(SLASH);
				setState(299);
				match(ATTRIBUTE);
				setState(300);
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
			setState(304);
			match(OPEN);
			setState(305);
			match(CHOICE);
			setState(309);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(306);
				attr();
				}
				}
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MAXOCCURSATTR) {
				{
				setState(312);
				maxOccursAttr();
				}
			}

			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINOCCURSATTR) {
				{
				setState(315);
				minOccursAttr();
				}
			}

			setState(318);
			match(CLOSE);
			setState(319);
			content();
			setState(320);
			match(OPEN);
			setState(321);
			match(SLASH);
			setState(322);
			match(CHOICE);
			setState(323);
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
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(325);
				match(OPEN);
				setState(326);
				match(INCLUDE);
				setState(330);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(327);
						attr();
						}
						} 
					}
					setState(332);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				}
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(333);
					conformanceAttr();
					}
				}

				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(336);
					flexibilityAttr();
					}
				}

				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(339);
					idAttr();
					}
				}

				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(342);
					isMandatoryAttr();
					}
				}

				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(345);
					maxOccursAttr();
					}
				}

				setState(349);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(348);
					minOccursAttr();
					}
				}

				setState(352);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(351);
					refAttr();
					}
				}

				setState(357);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(354);
					attr();
					}
					}
					setState(359);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(360);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(361);
				match(OPEN);
				setState(362);
				match(INCLUDE);
				setState(366);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(363);
						attr();
						}
						} 
					}
					setState(368);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
				}
				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(369);
					conformanceAttr();
					}
				}

				setState(373);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(372);
					flexibilityAttr();
					}
				}

				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(375);
					idAttr();
					}
				}

				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(378);
					isMandatoryAttr();
					}
				}

				setState(382);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(381);
					maxOccursAttr();
					}
				}

				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(384);
					minOccursAttr();
					}
				}

				setState(388);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(387);
					refAttr();
					}
				}

				setState(393);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(390);
					attr();
					}
					}
					setState(395);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(396);
				match(CLOSE);
				setState(397);
				content();
				setState(398);
				match(OPEN);
				setState(399);
				match(SLASH);
				setState(400);
				match(INCLUDE);
				setState(401);
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
			setState(453);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(405);
				match(OPEN);
				setState(406);
				match(VOCAB);
				setState(410);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(407);
						attr();
						}
						} 
					}
					setState(412);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
				}
				setState(414);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(413);
					flexibilityAttr();
					}
				}

				setState(417);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUESETATTR) {
					{
					setState(416);
					valueSetAttr();
					}
				}

				setState(422);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(419);
					attr();
					}
					}
					setState(424);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(425);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(426);
				match(OPEN);
				setState(427);
				match(VOCAB);
				setState(431);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(428);
						attr();
						}
						} 
					}
					setState(433);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
				}
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(434);
					flexibilityAttr();
					}
				}

				setState(438);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUESETATTR) {
					{
					setState(437);
					valueSetAttr();
					}
				}

				setState(443);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(440);
					attr();
					}
					}
					setState(445);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(446);
				match(CLOSE);
				setState(447);
				content();
				setState(448);
				match(OPEN);
				setState(449);
				match(SLASH);
				setState(450);
				match(VOCAB);
				setState(451);
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
			setState(479);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(455);
				match(OPEN);
				setState(456);
				match(LET);
				setState(458);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(457);
					nameAttr();
					}
				}

				setState(461);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(460);
					valueAttr();
					}
				}

				setState(463);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(464);
				match(OPEN);
				setState(465);
				match(LET);
				setState(467);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(466);
					nameAttr();
					}
				}

				setState(470);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(469);
					valueAttr();
					}
				}

				setState(472);
				match(CLOSE);
				setState(473);
				content();
				setState(474);
				match(OPEN);
				setState(475);
				match(SLASH);
				setState(476);
				match(LET);
				setState(477);
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
			setState(505);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(481);
				match(OPEN);
				setState(482);
				match(ASSERT);
				setState(486);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(483);
					attr();
					}
					}
					setState(488);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(489);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(490);
				match(OPEN);
				setState(491);
				match(ASSERT);
				setState(495);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(492);
					attr();
					}
					}
					setState(497);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(498);
				match(CLOSE);
				setState(499);
				content();
				setState(500);
				match(OPEN);
				setState(501);
				match(SLASH);
				setState(502);
				match(ASSERT);
				setState(503);
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
			setState(531);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(507);
				match(OPEN);
				setState(508);
				match(REPORT);
				setState(512);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(509);
					attr();
					}
					}
					setState(514);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(515);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(516);
				match(OPEN);
				setState(517);
				match(REPORT);
				setState(521);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(518);
					attr();
					}
					}
					setState(523);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(524);
				match(CLOSE);
				setState(525);
				content();
				setState(526);
				match(OPEN);
				setState(527);
				match(SLASH);
				setState(528);
				match(REPORT);
				setState(529);
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
			setState(534);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEA_WS || _la==TEXT) {
				{
				setState(533);
				chardata();
				}
			}

			setState(557);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(550);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
					case 1:
						{
						setState(536);
						xmlelement();
						}
						break;
					case 2:
						{
						setState(537);
						desc();
						}
						break;
					case 3:
						{
						setState(538);
						element();
						}
						break;
					case 4:
						{
						setState(539);
						include();
						}
						break;
					case 5:
						{
						setState(540);
						choice();
						}
						break;
					case 6:
						{
						setState(541);
						letter();
						}
						break;
					case 7:
						{
						setState(542);
						assertion();
						}
						break;
					case 8:
						{
						setState(543);
						report();
						}
						break;
					case 9:
						{
						setState(544);
						reference();
						}
						break;
					case 10:
						{
						setState(545);
						attribute();
						}
						break;
					case 11:
						{
						setState(546);
						vocab();
						}
						break;
					case 12:
						{
						setState(547);
						match(CDATA);
						}
						break;
					case 13:
						{
						setState(548);
						match(PI);
						}
						break;
					case 14:
						{
						setState(549);
						match(COMMENT);
						}
						break;
					}
					setState(553);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SEA_WS || _la==TEXT) {
						{
						setState(552);
						chardata();
						}
					}

					}
					} 
				}
				setState(559);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
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
			setState(596);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(560);
				match(OPEN);
				setState(561);
				match(Name);
				setState(562);
				match(CLOSE);
				setState(563);
				content();
				setState(564);
				match(OPEN);
				setState(565);
				match(SLASH);
				setState(566);
				match(Name);
				setState(567);
				match(CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(569);
				match(OPEN);
				setState(570);
				match(Name);
				setState(571);
				match(SLASH_CLOSE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(572);
				match(OPEN);
				setState(573);
				match(Name);
				setState(577);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(574);
					attr();
					}
					}
					setState(579);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(580);
				match(CLOSE);
				setState(581);
				content();
				setState(582);
				match(OPEN);
				setState(583);
				match(SLASH);
				setState(584);
				match(Name);
				setState(585);
				match(CLOSE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(587);
				match(OPEN);
				setState(588);
				match(Name);
				setState(592);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(589);
					attr();
					}
					}
					setState(594);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(595);
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
			setState(598);
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
			setState(600);
			match(Name);
			setState(601);
			match(EQUALS);
			setState(602);
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
			setState(604);
			match(CONFATTR);
			setState(605);
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
			setState(607);
			match(FLEXIBILITYATTR);
			setState(608);
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
		enterRule(_localctx, 34, RULE_containsAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(610);
			match(CONTAINSATTR);
			setState(611);
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
		enterRule(_localctx, 36, RULE_dataTypeAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(613);
			match(TYPEATTR);
			setState(614);
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
		enterRule(_localctx, 38, RULE_idAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(616);
			match(IDATTR);
			setState(617);
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
		enterRule(_localctx, 40, RULE_isMandatoryAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(619);
			match(MANDATTR);
			setState(620);
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
		enterRule(_localctx, 42, RULE_isOptionalAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(622);
			match(OPTATTR);
			setState(623);
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
		enterRule(_localctx, 44, RULE_maxOccursAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(625);
			match(MAXOCCURSATTR);
			setState(626);
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
		enterRule(_localctx, 46, RULE_minOccursAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(628);
			match(MINOCCURSATTR);
			setState(629);
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
		enterRule(_localctx, 48, RULE_nameAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(631);
			match(NAMEATTR);
			setState(632);
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
		enterRule(_localctx, 50, RULE_prohibitedAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(634);
			match(PROHIBITED);
			setState(635);
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
		enterRule(_localctx, 52, RULE_refAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(637);
			match(REFATTR);
			setState(638);
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
		enterRule(_localctx, 54, RULE_strengthAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(640);
			match(STRENGTHATTR);
			setState(641);
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
		enterRule(_localctx, 56, RULE_valueAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(643);
			match(VALUEATTR);
			setState(644);
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
		enterRule(_localctx, 58, RULE_valueSetAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(646);
			match(VALUESETATTR);
			setState(647);
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
		enterRule(_localctx, 60, RULE_chardata);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(649);
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
		enterRule(_localctx, 62, RULE_misc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(651);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u0290\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\7\2E\n\2\f\2\16\2H\13\2\3\2\3\2\5\2L\n\2\3\3\5\3O\n\3\3\3"+
		"\3\3\3\3\7\3T\n\3\f\3\16\3W\13\3\3\3\5\3Z\n\3\3\3\7\3]\n\3\f\3\16\3`\13"+
		"\3\3\3\5\3c\n\3\3\3\7\3f\n\3\f\3\16\3i\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\4\3\4\3\4\7\4u\n\4\f\4\16\4x\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\7\5\u0084\n\5\f\5\16\5\u0087\13\5\3\5\5\5\u008a\n\5\3\5\5\5\u008d"+
		"\n\5\3\5\5\5\u0090\n\5\3\5\5\5\u0093\n\5\3\5\5\5\u0096\n\5\3\5\5\5\u0099"+
		"\n\5\3\5\5\5\u009c\n\5\3\5\5\5\u009f\n\5\3\5\5\5\u00a2\n\5\3\5\5\5\u00a5"+
		"\n\5\3\5\5\5\u00a8\n\5\3\5\3\5\3\5\3\5\7\5\u00ae\n\5\f\5\16\5\u00b1\13"+
		"\5\3\5\5\5\u00b4\n\5\3\5\5\5\u00b7\n\5\3\5\5\5\u00ba\n\5\3\5\5\5\u00bd"+
		"\n\5\3\5\5\5\u00c0\n\5\3\5\5\5\u00c3\n\5\3\5\5\5\u00c6\n\5\3\5\5\5\u00c9"+
		"\n\5\3\5\5\5\u00cc\n\5\3\5\5\5\u00cf\n\5\3\5\5\5\u00d2\n\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\5\5\u00db\n\5\3\6\3\6\3\6\7\6\u00e0\n\6\f\6\16\6\u00e3"+
		"\13\6\3\6\5\6\u00e6\n\6\3\6\5\6\u00e9\n\6\3\6\5\6\u00ec\n\6\3\6\5\6\u00ef"+
		"\n\6\3\6\5\6\u00f2\n\6\3\6\5\6\u00f5\n\6\3\6\5\6\u00f8\n\6\3\6\5\6\u00fb"+
		"\n\6\3\6\5\6\u00fe\n\6\3\6\5\6\u0101\n\6\3\6\3\6\3\6\3\6\7\6\u0107\n\6"+
		"\f\6\16\6\u010a\13\6\3\6\5\6\u010d\n\6\3\6\5\6\u0110\n\6\3\6\5\6\u0113"+
		"\n\6\3\6\5\6\u0116\n\6\3\6\5\6\u0119\n\6\3\6\5\6\u011c\n\6\3\6\5\6\u011f"+
		"\n\6\3\6\5\6\u0122\n\6\3\6\5\6\u0125\n\6\3\6\5\6\u0128\n\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\5\6\u0131\n\6\3\7\3\7\3\7\7\7\u0136\n\7\f\7\16\7\u0139"+
		"\13\7\3\7\5\7\u013c\n\7\3\7\5\7\u013f\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\7\b\u014b\n\b\f\b\16\b\u014e\13\b\3\b\5\b\u0151\n\b\3\b\5"+
		"\b\u0154\n\b\3\b\5\b\u0157\n\b\3\b\5\b\u015a\n\b\3\b\5\b\u015d\n\b\3\b"+
		"\5\b\u0160\n\b\3\b\5\b\u0163\n\b\3\b\7\b\u0166\n\b\f\b\16\b\u0169\13\b"+
		"\3\b\3\b\3\b\3\b\7\b\u016f\n\b\f\b\16\b\u0172\13\b\3\b\5\b\u0175\n\b\3"+
		"\b\5\b\u0178\n\b\3\b\5\b\u017b\n\b\3\b\5\b\u017e\n\b\3\b\5\b\u0181\n\b"+
		"\3\b\5\b\u0184\n\b\3\b\5\b\u0187\n\b\3\b\7\b\u018a\n\b\f\b\16\b\u018d"+
		"\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0196\n\b\3\t\3\t\3\t\7\t\u019b"+
		"\n\t\f\t\16\t\u019e\13\t\3\t\5\t\u01a1\n\t\3\t\5\t\u01a4\n\t\3\t\7\t\u01a7"+
		"\n\t\f\t\16\t\u01aa\13\t\3\t\3\t\3\t\3\t\7\t\u01b0\n\t\f\t\16\t\u01b3"+
		"\13\t\3\t\5\t\u01b6\n\t\3\t\5\t\u01b9\n\t\3\t\7\t\u01bc\n\t\f\t\16\t\u01bf"+
		"\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u01c8\n\t\3\n\3\n\3\n\5\n\u01cd"+
		"\n\n\3\n\5\n\u01d0\n\n\3\n\3\n\3\n\3\n\5\n\u01d6\n\n\3\n\5\n\u01d9\n\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u01e2\n\n\3\13\3\13\3\13\7\13\u01e7\n"+
		"\13\f\13\16\13\u01ea\13\13\3\13\3\13\3\13\3\13\7\13\u01f0\n\13\f\13\16"+
		"\13\u01f3\13\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u01fc\n\13\3\f"+
		"\3\f\3\f\7\f\u0201\n\f\f\f\16\f\u0204\13\f\3\f\3\f\3\f\3\f\7\f\u020a\n"+
		"\f\f\f\16\f\u020d\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0216\n\f\3\r\5"+
		"\r\u0219\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5"+
		"\r\u0229\n\r\3\r\5\r\u022c\n\r\7\r\u022e\n\r\f\r\16\r\u0231\13\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\7\16\u0242\n\16\f\16\16\16\u0245\13\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\7\16\u0251\n\16\f\16\16\16\u0254\13\16\3\16\5\16"+
		"\u0257\n\16\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3 \3 \3!\3!\3"+
		"!\2\2\"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:"+
		"<>@\2\5\3\2\6\7\4\2\b\b\13\13\5\2\3\3\b\b--\2\u02e7\2B\3\2\2\2\4N\3\2"+
		"\2\2\6q\3\2\2\2\b\u00da\3\2\2\2\n\u0130\3\2\2\2\f\u0132\3\2\2\2\16\u0195"+
		"\3\2\2\2\20\u01c7\3\2\2\2\22\u01e1\3\2\2\2\24\u01fb\3\2\2\2\26\u0215\3"+
		"\2\2\2\30\u0218\3\2\2\2\32\u0256\3\2\2\2\34\u0258\3\2\2\2\36\u025a\3\2"+
		"\2\2 \u025e\3\2\2\2\"\u0261\3\2\2\2$\u0264\3\2\2\2&\u0267\3\2\2\2(\u026a"+
		"\3\2\2\2*\u026d\3\2\2\2,\u0270\3\2\2\2.\u0273\3\2\2\2\60\u0276\3\2\2\2"+
		"\62\u0279\3\2\2\2\64\u027c\3\2\2\2\66\u027f\3\2\2\28\u0282\3\2\2\2:\u0285"+
		"\3\2\2\2<\u0288\3\2\2\2>\u028b\3\2\2\2@\u028d\3\2\2\2BF\7\n\2\2CE\5\36"+
		"\20\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2\2HF\3\2\2\2IK\7"+
		"&\2\2JL\7\b\2\2KJ\3\2\2\2KL\3\2\2\2L\3\3\2\2\2MO\5\2\2\2NM\3\2\2\2NO\3"+
		"\2\2\2OP\3\2\2\2PQ\7\t\2\2QU\7 \2\2RT\5\36\20\2SR\3\2\2\2TW\3\2\2\2US"+
		"\3\2\2\2UV\3\2\2\2VY\3\2\2\2WU\3\2\2\2XZ\5(\25\2YX\3\2\2\2YZ\3\2\2\2Z"+
		"^\3\2\2\2[]\5\36\20\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_b\3\2"+
		"\2\2`^\3\2\2\2ac\5\62\32\2ba\3\2\2\2bc\3\2\2\2cg\3\2\2\2df\5\36\20\2e"+
		"d\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jk\7%\2\2k"+
		"l\5\30\r\2lm\7\t\2\2mn\7(\2\2no\7 \2\2op\7%\2\2p\5\3\2\2\2qr\7\t\2\2r"+
		"v\7\22\2\2su\5\36\20\2ts\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3\2\2\2wy\3\2\2"+
		"\2xv\3\2\2\2yz\7%\2\2z{\5\30\r\2{|\7\t\2\2|}\7(\2\2}~\7\22\2\2~\177\7"+
		"%\2\2\177\7\3\2\2\2\u0080\u0081\7\t\2\2\u0081\u0085\7\23\2\2\u0082\u0084"+
		"\5\36\20\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083\3\2\2\2"+
		"\u0085\u0086\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u008a"+
		"\5 \21\2\u0089\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b"+
		"\u008d\5$\23\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008f\3\2"+
		"\2\2\u008e\u0090\5&\24\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090"+
		"\u0092\3\2\2\2\u0091\u0093\5\"\22\2\u0092\u0091\3\2\2\2\u0092\u0093\3"+
		"\2\2\2\u0093\u0095\3\2\2\2\u0094\u0096\5(\25\2\u0095\u0094\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0099\5*\26\2\u0098\u0097\3\2"+
		"\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a\u009c\5.\30\2\u009b"+
		"\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009f\5\60"+
		"\31\2\u009e\u009d\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0"+
		"\u00a2\5\62\32\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3"+
		"\2\2\2\u00a3\u00a5\5:\36\2\u00a4\u00a3\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a7\3\2\2\2\u00a6\u00a8\58\35\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00db\7\'\2\2\u00aa\u00ab\7\t\2\2\u00ab"+
		"\u00af\7\23\2\2\u00ac\u00ae\5\36\20\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1"+
		"\3\2\2\2\u00af\u00ad\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1"+
		"\u00af\3\2\2\2\u00b2\u00b4\5 \21\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2"+
		"\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b7\5$\23\2\u00b6\u00b5\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00ba\5&\24\2\u00b9\u00b8\3\2"+
		"\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00bd\5\"\22\2\u00bc"+
		"\u00bb\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00c0\5("+
		"\25\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1"+
		"\u00c3\5*\26\2\u00c2\u00c1\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c5\3\2"+
		"\2\2\u00c4\u00c6\5.\30\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6"+
		"\u00c8\3\2\2\2\u00c7\u00c9\5\60\31\2\u00c8\u00c7\3\2\2\2\u00c8\u00c9\3"+
		"\2\2\2\u00c9\u00cb\3\2\2\2\u00ca\u00cc\5\62\32\2\u00cb\u00ca\3\2\2\2\u00cb"+
		"\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd\u00cf\5:\36\2\u00ce\u00cd\3\2"+
		"\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00d2\58\35\2\u00d1"+
		"\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d4\7%"+
		"\2\2\u00d4\u00d5\5\30\r\2\u00d5\u00d6\7\t\2\2\u00d6\u00d7\7(\2\2\u00d7"+
		"\u00d8\7\23\2\2\u00d8\u00d9\7%\2\2\u00d9\u00db\3\2\2\2\u00da\u0080\3\2"+
		"\2\2\u00da\u00aa\3\2\2\2\u00db\t\3\2\2\2\u00dc\u00dd\7\t\2\2\u00dd\u00e1"+
		"\7\r\2\2\u00de\u00e0\5\36\20\2\u00df\u00de\3\2\2\2\u00e0\u00e3\3\2\2\2"+
		"\u00e1\u00df\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1"+
		"\3\2\2\2\u00e4\u00e6\5 \21\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e8\3\2\2\2\u00e7\u00e9\5&\24\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2"+
		"\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00ec\5(\25\2\u00eb\u00ea\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00ef\5*\26\2\u00ee\u00ed\3\2"+
		"\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00f2\5,\27\2\u00f1"+
		"\u00f0\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f5\5."+
		"\30\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f7\3\2\2\2\u00f6"+
		"\u00f8\5\60\31\2\u00f7\u00f6\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3"+
		"\2\2\2\u00f9\u00fb\5\62\32\2\u00fa\u00f9\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb"+
		"\u00fd\3\2\2\2\u00fc\u00fe\5\64\33\2\u00fd\u00fc\3\2\2\2\u00fd\u00fe\3"+
		"\2\2\2\u00fe\u0100\3\2\2\2\u00ff\u0101\5:\36\2\u0100\u00ff\3\2\2\2\u0100"+
		"\u0101\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0131\7\'\2\2\u0103\u0104\7\t"+
		"\2\2\u0104\u0108\7\r\2\2\u0105\u0107\5\36\20\2\u0106\u0105\3\2\2\2\u0107"+
		"\u010a\3\2\2\2\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010c\3\2"+
		"\2\2\u010a\u0108\3\2\2\2\u010b\u010d\5 \21\2\u010c\u010b\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\u010f\3\2\2\2\u010e\u0110\5&\24\2\u010f\u010e\3\2"+
		"\2\2\u010f\u0110\3\2\2\2\u0110\u0112\3\2\2\2\u0111\u0113\5(\25\2\u0112"+
		"\u0111\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\3\2\2\2\u0114\u0116\5*"+
		"\26\2\u0115\u0114\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117"+
		"\u0119\5,\27\2\u0118\u0117\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2"+
		"\2\2\u011a\u011c\5.\30\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c"+
		"\u011e\3\2\2\2\u011d\u011f\5\60\31\2\u011e\u011d\3\2\2\2\u011e\u011f\3"+
		"\2\2\2\u011f\u0121\3\2\2\2\u0120\u0122\5\62\32\2\u0121\u0120\3\2\2\2\u0121"+
		"\u0122\3\2\2\2\u0122\u0124\3\2\2\2\u0123\u0125\5\64\33\2\u0124\u0123\3"+
		"\2\2\2\u0124\u0125\3\2\2\2\u0125\u0127\3\2\2\2\u0126\u0128\5:\36\2\u0127"+
		"\u0126\3\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\7%"+
		"\2\2\u012a\u012b\5\30\r\2\u012b\u012c\7\t\2\2\u012c\u012d\7(\2\2\u012d"+
		"\u012e\7\r\2\2\u012e\u012f\7%\2\2\u012f\u0131\3\2\2\2\u0130\u00dc\3\2"+
		"\2\2\u0130\u0103\3\2\2\2\u0131\13\3\2\2\2\u0132\u0133\7\t\2\2\u0133\u0137"+
		"\7\16\2\2\u0134\u0136\5\36\20\2\u0135\u0134\3\2\2\2\u0136\u0139\3\2\2"+
		"\2\u0137\u0135\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137"+
		"\3\2\2\2\u013a\u013c\5.\30\2\u013b\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c"+
		"\u013e\3\2\2\2\u013d\u013f\5\60\31\2\u013e\u013d\3\2\2\2\u013e\u013f\3"+
		"\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\7%\2\2\u0141\u0142\5\30\r\2\u0142"+
		"\u0143\7\t\2\2\u0143\u0144\7(\2\2\u0144\u0145\7\16\2\2\u0145\u0146\7%"+
		"\2\2\u0146\r\3\2\2\2\u0147\u0148\7\t\2\2\u0148\u014c\7\25\2\2\u0149\u014b"+
		"\5\36\20\2\u014a\u0149\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2"+
		"\u014c\u014d\3\2\2\2\u014d\u0150\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0151"+
		"\5 \21\2\u0150\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0153\3\2\2\2\u0152"+
		"\u0154\5\"\22\2\u0153\u0152\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0156\3"+
		"\2\2\2\u0155\u0157\5(\25\2\u0156\u0155\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0159\3\2\2\2\u0158\u015a\5*\26\2\u0159\u0158\3\2\2\2\u0159\u015a\3\2"+
		"\2\2\u015a\u015c\3\2\2\2\u015b\u015d\5.\30\2\u015c\u015b\3\2\2\2\u015c"+
		"\u015d\3\2\2\2\u015d\u015f\3\2\2\2\u015e\u0160\5\60\31\2\u015f\u015e\3"+
		"\2\2\2\u015f\u0160\3\2\2\2\u0160\u0162\3\2\2\2\u0161\u0163\5\66\34\2\u0162"+
		"\u0161\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0167\3\2\2\2\u0164\u0166\5\36"+
		"\20\2\u0165\u0164\3\2\2\2\u0166\u0169\3\2\2\2\u0167\u0165\3\2\2\2\u0167"+
		"\u0168\3\2\2\2\u0168\u016a\3\2\2\2\u0169\u0167\3\2\2\2\u016a\u0196\7\'"+
		"\2\2\u016b\u016c\7\t\2\2\u016c\u0170\7\25\2\2\u016d\u016f\5\36\20\2\u016e"+
		"\u016d\3\2\2\2\u016f\u0172\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2"+
		"\2\2\u0171\u0174\3\2\2\2\u0172\u0170\3\2\2\2\u0173\u0175\5 \21\2\u0174"+
		"\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0177\3\2\2\2\u0176\u0178\5\""+
		"\22\2\u0177\u0176\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u017a\3\2\2\2\u0179"+
		"\u017b\5(\25\2\u017a\u0179\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017d\3\2"+
		"\2\2\u017c\u017e\5*\26\2\u017d\u017c\3\2\2\2\u017d\u017e\3\2\2\2\u017e"+
		"\u0180\3\2\2\2\u017f\u0181\5.\30\2\u0180\u017f\3\2\2\2\u0180\u0181\3\2"+
		"\2\2\u0181\u0183\3\2\2\2\u0182\u0184\5\60\31\2\u0183\u0182\3\2\2\2\u0183"+
		"\u0184\3\2\2\2\u0184\u0186\3\2\2\2\u0185\u0187\5\66\34\2\u0186\u0185\3"+
		"\2\2\2\u0186\u0187\3\2\2\2\u0187\u018b\3\2\2\2\u0188\u018a\5\36\20\2\u0189"+
		"\u0188\3\2\2\2\u018a\u018d\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2"+
		"\2\2\u018c\u018e\3\2\2\2\u018d\u018b\3\2\2\2\u018e\u018f\7%\2\2\u018f"+
		"\u0190\5\30\r\2\u0190\u0191\7\t\2\2\u0191\u0192\7(\2\2\u0192\u0193\7\25"+
		"\2\2\u0193\u0194\7%\2\2\u0194\u0196\3\2\2\2\u0195\u0147\3\2\2\2\u0195"+
		"\u016b\3\2\2\2\u0196\17\3\2\2\2\u0197\u0198\7\t\2\2\u0198\u019c\7$\2\2"+
		"\u0199\u019b\5\36\20\2\u019a\u0199\3\2\2\2\u019b\u019e\3\2\2\2\u019c\u019a"+
		"\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u01a0\3\2\2\2\u019e\u019c\3\2\2\2\u019f"+
		"\u01a1\5\"\22\2\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1\u01a3\3"+
		"\2\2\2\u01a2\u01a4\5<\37\2\u01a3\u01a2\3\2\2\2\u01a3\u01a4\3\2\2\2\u01a4"+
		"\u01a8\3\2\2\2\u01a5\u01a7\5\36\20\2\u01a6\u01a5\3\2\2\2\u01a7\u01aa\3"+
		"\2\2\2\u01a8\u01a6\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01ab\3\2\2\2\u01aa"+
		"\u01a8\3\2\2\2\u01ab\u01c8\7\'\2\2\u01ac\u01ad\7\t\2\2\u01ad\u01b1\7$"+
		"\2\2\u01ae\u01b0\5\36\20\2\u01af\u01ae\3\2\2\2\u01b0\u01b3\3\2\2\2\u01b1"+
		"\u01af\3\2\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b1\3\2"+
		"\2\2\u01b4\u01b6\5\"\22\2\u01b5\u01b4\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6"+
		"\u01b8\3\2\2\2\u01b7\u01b9\5<\37\2\u01b8\u01b7\3\2\2\2\u01b8\u01b9\3\2"+
		"\2\2\u01b9\u01bd\3\2\2\2\u01ba\u01bc\5\36\20\2\u01bb\u01ba\3\2\2\2\u01bc"+
		"\u01bf\3\2\2\2\u01bd\u01bb\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\u01c0\3\2"+
		"\2\2\u01bf\u01bd\3\2\2\2\u01c0\u01c1\7%\2\2\u01c1\u01c2\5\30\r\2\u01c2"+
		"\u01c3\7\t\2\2\u01c3\u01c4\7(\2\2\u01c4\u01c5\7$\2\2\u01c5\u01c6\7%\2"+
		"\2\u01c6\u01c8\3\2\2\2\u01c7\u0197\3\2\2\2\u01c7\u01ac\3\2\2\2\u01c8\21"+
		"\3\2\2\2\u01c9\u01ca\7\t\2\2\u01ca\u01cc\7\26\2\2\u01cb\u01cd\5\62\32"+
		"\2\u01cc\u01cb\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01cf\3\2\2\2\u01ce\u01d0"+
		"\5:\36\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1"+
		"\u01e2\7\'\2\2\u01d2\u01d3\7\t\2\2\u01d3\u01d5\7\26\2\2\u01d4\u01d6\5"+
		"\62\32\2\u01d5\u01d4\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d8\3\2\2\2\u01d7"+
		"\u01d9\5:\36\2\u01d8\u01d7\3\2\2\2\u01d8\u01d9\3\2\2\2\u01d9\u01da\3\2"+
		"\2\2\u01da\u01db\7%\2\2\u01db\u01dc\5\30\r\2\u01dc\u01dd\7\t\2\2\u01dd"+
		"\u01de\7(\2\2\u01de\u01df\7\26\2\2\u01df\u01e0\7%\2\2\u01e0\u01e2\3\2"+
		"\2\2\u01e1\u01c9\3\2\2\2\u01e1\u01d2\3\2\2\2\u01e2\23\3\2\2\2\u01e3\u01e4"+
		"\7\t\2\2\u01e4\u01e8\7\f\2\2\u01e5\u01e7\5\36\20\2\u01e6\u01e5\3\2\2\2"+
		"\u01e7\u01ea\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01eb"+
		"\3\2\2\2\u01ea\u01e8\3\2\2\2\u01eb\u01fc\7\'\2\2\u01ec\u01ed\7\t\2\2\u01ed"+
		"\u01f1\7\f\2\2\u01ee\u01f0\5\36\20\2\u01ef\u01ee\3\2\2\2\u01f0\u01f3\3"+
		"\2\2\2\u01f1\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f4\3\2\2\2\u01f3"+
		"\u01f1\3\2\2\2\u01f4\u01f5\7%\2\2\u01f5\u01f6\5\30\r\2\u01f6\u01f7\7\t"+
		"\2\2\u01f7\u01f8\7(\2\2\u01f8\u01f9\7\f\2\2\u01f9\u01fa\7%\2\2\u01fa\u01fc"+
		"\3\2\2\2\u01fb\u01e3\3\2\2\2\u01fb\u01ec\3\2\2\2\u01fc\25\3\2\2\2\u01fd"+
		"\u01fe\7\t\2\2\u01fe\u0202\7\36\2\2\u01ff\u0201\5\36\20\2\u0200\u01ff"+
		"\3\2\2\2\u0201\u0204\3\2\2\2\u0202\u0200\3\2\2\2\u0202\u0203\3\2\2\2\u0203"+
		"\u0205\3\2\2\2\u0204\u0202\3\2\2\2\u0205\u0216\7\'\2\2\u0206\u0207\7\t"+
		"\2\2\u0207\u020b\7\36\2\2\u0208\u020a\5\36\20\2\u0209\u0208\3\2\2\2\u020a"+
		"\u020d\3\2\2\2\u020b\u0209\3\2\2\2\u020b\u020c\3\2\2\2\u020c\u020e\3\2"+
		"\2\2\u020d\u020b\3\2\2\2\u020e\u020f\7%\2\2\u020f\u0210\5\30\r\2\u0210"+
		"\u0211\7\t\2\2\u0211\u0212\7(\2\2\u0212\u0213\7\36\2\2\u0213\u0214\7%"+
		"\2\2\u0214\u0216\3\2\2\2\u0215\u01fd\3\2\2\2\u0215\u0206\3\2\2\2\u0216"+
		"\27\3\2\2\2\u0217\u0219\5> \2\u0218\u0217\3\2\2\2\u0218\u0219\3\2\2\2"+
		"\u0219\u022f\3\2\2\2\u021a\u0229\5\32\16\2\u021b\u0229\5\6\4\2\u021c\u0229"+
		"\5\b\5\2\u021d\u0229\5\16\b\2\u021e\u0229\5\f\7\2\u021f\u0229\5\22\n\2"+
		"\u0220\u0229\5\24\13\2\u0221\u0229\5\26\f\2\u0222\u0229\5\34\17\2\u0223"+
		"\u0229\5\n\6\2\u0224\u0229\5\20\t\2\u0225\u0229\7\4\2\2\u0226\u0229\7"+
		"-\2\2\u0227\u0229\7\3\2\2\u0228\u021a\3\2\2\2\u0228\u021b\3\2\2\2\u0228"+
		"\u021c\3\2\2\2\u0228\u021d\3\2\2\2\u0228\u021e\3\2\2\2\u0228\u021f\3\2"+
		"\2\2\u0228\u0220\3\2\2\2\u0228\u0221\3\2\2\2\u0228\u0222\3\2\2\2\u0228"+
		"\u0223\3\2\2\2\u0228\u0224\3\2\2\2\u0228\u0225\3\2\2\2\u0228\u0226\3\2"+
		"\2\2\u0228\u0227\3\2\2\2\u0229\u022b\3\2\2\2\u022a\u022c\5> \2\u022b\u022a"+
		"\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u022e\3\2\2\2\u022d\u0228\3\2\2\2\u022e"+
		"\u0231\3\2\2\2\u022f\u022d\3\2\2\2\u022f\u0230\3\2\2\2\u0230\31\3\2\2"+
		"\2\u0231\u022f\3\2\2\2\u0232\u0233\7\t\2\2\u0233\u0234\7+\2\2\u0234\u0235"+
		"\7%\2\2\u0235\u0236\5\30\r\2\u0236\u0237\7\t\2\2\u0237\u0238\7(\2\2\u0238"+
		"\u0239\7+\2\2\u0239\u023a\7%\2\2\u023a\u0257\3\2\2\2\u023b\u023c\7\t\2"+
		"\2\u023c\u023d\7+\2\2\u023d\u0257\7\'\2\2\u023e\u023f\7\t\2\2\u023f\u0243"+
		"\7+\2\2\u0240\u0242\5\36\20\2\u0241\u0240\3\2\2\2\u0242\u0245\3\2\2\2"+
		"\u0243\u0241\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0246\3\2\2\2\u0245\u0243"+
		"\3\2\2\2\u0246\u0247\7%\2\2\u0247\u0248\5\30\r\2\u0248\u0249\7\t\2\2\u0249"+
		"\u024a\7(\2\2\u024a\u024b\7+\2\2\u024b\u024c\7%\2\2\u024c\u0257\3\2\2"+
		"\2\u024d\u024e\7\t\2\2\u024e\u0252\7+\2\2\u024f\u0251\5\36\20\2\u0250"+
		"\u024f\3\2\2\2\u0251\u0254\3\2\2\2\u0252\u0250\3\2\2\2\u0252\u0253\3\2"+
		"\2\2\u0253\u0255\3\2\2\2\u0254\u0252\3\2\2\2\u0255\u0257\7\'\2\2\u0256"+
		"\u0232\3\2\2\2\u0256\u023b\3\2\2\2\u0256\u023e\3\2\2\2\u0256\u024d\3\2"+
		"\2\2\u0257\33\3\2\2\2\u0258\u0259\t\2\2\2\u0259\35\3\2\2\2\u025a\u025b"+
		"\7+\2\2\u025b\u025c\7)\2\2\u025c\u025d\7*\2\2\u025d\37\3\2\2\2\u025e\u025f"+
		"\7\17\2\2\u025f\u0260\7*\2\2\u0260!\3\2\2\2\u0261\u0262\7\20\2\2\u0262"+
		"\u0263\7*\2\2\u0263#\3\2\2\2\u0264\u0265\7\21\2\2\u0265\u0266\7*\2\2\u0266"+
		"%\3\2\2\2\u0267\u0268\7!\2\2\u0268\u0269\7*\2\2\u0269\'\3\2\2\2\u026a"+
		"\u026b\7\24\2\2\u026b\u026c\7*\2\2\u026c)\3\2\2\2\u026d\u026e\7\27\2\2"+
		"\u026e\u026f\7*\2\2\u026f+\3\2\2\2\u0270\u0271\7\33\2\2\u0271\u0272\7"+
		"*\2\2\u0272-\3\2\2\2\u0273\u0274\7\30\2\2\u0274\u0275\7*\2\2\u0275/\3"+
		"\2\2\2\u0276\u0277\7\31\2\2\u0277\u0278\7*\2\2\u0278\61\3\2\2\2\u0279"+
		"\u027a\7\32\2\2\u027a\u027b\7*\2\2\u027b\63\3\2\2\2\u027c\u027d\7\34\2"+
		"\2\u027d\u027e\7*\2\2\u027e\65\3\2\2\2\u027f\u0280\7\35\2\2\u0280\u0281"+
		"\7*\2\2\u0281\67\3\2\2\2\u0282\u0283\7\37\2\2\u0283\u0284\7*\2\2\u0284"+
		"9\3\2\2\2\u0285\u0286\7\"\2\2\u0286\u0287\7*\2\2\u0287;\3\2\2\2\u0288"+
		"\u0289\7#\2\2\u0289\u028a\7*\2\2\u028a=\3\2\2\2\u028b\u028c\t\3\2\2\u028c"+
		"?\3\2\2\2\u028d\u028e\t\4\2\2\u028eA\3\2\2\2lFKNUY^bgv\u0085\u0089\u008c"+
		"\u008f\u0092\u0095\u0098\u009b\u009e\u00a1\u00a4\u00a7\u00af\u00b3\u00b6"+
		"\u00b9\u00bc\u00bf\u00c2\u00c5\u00c8\u00cb\u00ce\u00d1\u00da\u00e1\u00e5"+
		"\u00e8\u00eb\u00ee\u00f1\u00f4\u00f7\u00fa\u00fd\u0100\u0108\u010c\u010f"+
		"\u0112\u0115\u0118\u011b\u011e\u0121\u0124\u0127\u0130\u0137\u013b\u013e"+
		"\u014c\u0150\u0153\u0156\u0159\u015c\u015f\u0162\u0167\u0170\u0174\u0177"+
		"\u017a\u017d\u0180\u0183\u0186\u018b\u0195\u019c\u01a0\u01a3\u01a8\u01b1"+
		"\u01b5\u01b8\u01bd\u01c7\u01cc\u01cf\u01d5\u01d8\u01e1\u01e8\u01f1\u01fb"+
		"\u0202\u020b\u0215\u0218\u0228\u022b\u022f\u0243\u0252\u0256";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}