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
			setState(210);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
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
				if (_la==IDATTR) {
					{
					setState(143);
					idAttr();
					}
				}

				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(146);
					isMandatoryAttr();
					}
				}

				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(149);
					maxOccursAttr();
					}
				}

				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(152);
					minOccursAttr();
					}
				}

				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(155);
					nameAttr();
					}
				}

				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(158);
					valueAttr();
					}
				}

				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRENGTHATTR) {
					{
					setState(161);
					strengthAttr();
					}
				}

				setState(164);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				match(OPEN);
				setState(166);
				match(ELEMENT);
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(167);
					attr();
					}
					}
					setState(172);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(173);
					conformanceAttr();
					}
				}

				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONTAINSATTR) {
					{
					setState(176);
					containsAttr();
					}
				}

				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(179);
					dataTypeAttr();
					}
				}

				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(182);
					idAttr();
					}
				}

				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(185);
					isMandatoryAttr();
					}
				}

				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(188);
					maxOccursAttr();
					}
				}

				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(191);
					minOccursAttr();
					}
				}

				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(194);
					nameAttr();
					}
				}

				setState(198);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(197);
					valueAttr();
					}
				}

				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRENGTHATTR) {
					{
					setState(200);
					strengthAttr();
					}
				}

				setState(203);
				match(CLOSE);
				setState(204);
				content();
				setState(205);
				match(OPEN);
				setState(206);
				match(SLASH);
				setState(207);
				match(ELEMENT);
				setState(208);
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
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(212);
				match(OPEN);
				setState(213);
				match(ATTRIBUTE);
				setState(217);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(214);
					attr();
					}
					}
					setState(219);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(220);
					conformanceAttr();
					}
				}

				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(223);
					dataTypeAttr();
					}
				}

				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(226);
					idAttr();
					}
				}

				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(229);
					isMandatoryAttr();
					}
				}

				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(232);
					isOptionalAttr();
					}
				}

				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(235);
					maxOccursAttr();
					}
				}

				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(238);
					minOccursAttr();
					}
				}

				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(241);
					nameAttr();
					}
				}

				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(244);
					prohibitedAttr();
					}
				}

				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(247);
					valueAttr();
					}
				}

				setState(250);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(251);
				match(OPEN);
				setState(252);
				match(ATTRIBUTE);
				setState(256);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(253);
					attr();
					}
					}
					setState(258);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(259);
					conformanceAttr();
					}
				}

				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(262);
					dataTypeAttr();
					}
				}

				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(265);
					idAttr();
					}
				}

				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(268);
					isMandatoryAttr();
					}
				}

				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(271);
					isOptionalAttr();
					}
				}

				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(274);
					maxOccursAttr();
					}
				}

				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(277);
					minOccursAttr();
					}
				}

				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(280);
					nameAttr();
					}
				}

				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(283);
					prohibitedAttr();
					}
				}

				setState(287);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(286);
					valueAttr();
					}
				}

				setState(289);
				match(CLOSE);
				setState(290);
				content();
				setState(291);
				match(OPEN);
				setState(292);
				match(SLASH);
				setState(293);
				match(ATTRIBUTE);
				setState(294);
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
			setState(298);
			match(OPEN);
			setState(299);
			match(CHOICE);
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(300);
				attr();
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(307);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MAXOCCURSATTR) {
				{
				setState(306);
				maxOccursAttr();
				}
			}

			setState(310);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINOCCURSATTR) {
				{
				setState(309);
				minOccursAttr();
				}
			}

			setState(312);
			match(CLOSE);
			setState(313);
			content();
			setState(314);
			match(OPEN);
			setState(315);
			match(SLASH);
			setState(316);
			match(CHOICE);
			setState(317);
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
			setState(397);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(319);
				match(OPEN);
				setState(320);
				match(INCLUDE);
				setState(324);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(321);
						attr();
						}
						} 
					}
					setState(326);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				}
				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(327);
					conformanceAttr();
					}
				}

				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(330);
					flexibilityAttr();
					}
				}

				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(333);
					idAttr();
					}
				}

				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(336);
					isMandatoryAttr();
					}
				}

				setState(340);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(339);
					maxOccursAttr();
					}
				}

				setState(343);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(342);
					minOccursAttr();
					}
				}

				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(345);
					refAttr();
					}
				}

				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(348);
					attr();
					}
					}
					setState(353);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(354);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(355);
				match(OPEN);
				setState(356);
				match(INCLUDE);
				setState(360);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(357);
						attr();
						}
						} 
					}
					setState(362);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
				}
				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(363);
					conformanceAttr();
					}
				}

				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(366);
					flexibilityAttr();
					}
				}

				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(369);
					idAttr();
					}
				}

				setState(373);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(372);
					isMandatoryAttr();
					}
				}

				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(375);
					maxOccursAttr();
					}
				}

				setState(379);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(378);
					minOccursAttr();
					}
				}

				setState(382);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(381);
					refAttr();
					}
				}

				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(384);
					attr();
					}
					}
					setState(389);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(390);
				match(CLOSE);
				setState(391);
				content();
				setState(392);
				match(OPEN);
				setState(393);
				match(SLASH);
				setState(394);
				match(INCLUDE);
				setState(395);
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
			setState(447);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(399);
				match(OPEN);
				setState(400);
				match(VOCAB);
				setState(404);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(401);
						attr();
						}
						} 
					}
					setState(406);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
				}
				setState(408);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(407);
					flexibilityAttr();
					}
				}

				setState(411);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUESETATTR) {
					{
					setState(410);
					valueSetAttr();
					}
				}

				setState(416);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(413);
					attr();
					}
					}
					setState(418);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(419);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(420);
				match(OPEN);
				setState(421);
				match(VOCAB);
				setState(425);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(422);
						attr();
						}
						} 
					}
					setState(427);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				}
				setState(429);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==FLEXIBILITYATTR) {
					{
					setState(428);
					flexibilityAttr();
					}
				}

				setState(432);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUESETATTR) {
					{
					setState(431);
					valueSetAttr();
					}
				}

				setState(437);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(434);
					attr();
					}
					}
					setState(439);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(440);
				match(CLOSE);
				setState(441);
				content();
				setState(442);
				match(OPEN);
				setState(443);
				match(SLASH);
				setState(444);
				match(VOCAB);
				setState(445);
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
			setState(473);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(449);
				match(OPEN);
				setState(450);
				match(LET);
				setState(452);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(451);
					nameAttr();
					}
				}

				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(454);
					valueAttr();
					}
				}

				setState(457);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(458);
				match(OPEN);
				setState(459);
				match(LET);
				setState(461);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(460);
					nameAttr();
					}
				}

				setState(464);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(463);
					valueAttr();
					}
				}

				setState(466);
				match(CLOSE);
				setState(467);
				content();
				setState(468);
				match(OPEN);
				setState(469);
				match(SLASH);
				setState(470);
				match(LET);
				setState(471);
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
			setState(499);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,93,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(475);
				match(OPEN);
				setState(476);
				match(ASSERT);
				setState(480);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(477);
					attr();
					}
					}
					setState(482);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(483);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(484);
				match(OPEN);
				setState(485);
				match(ASSERT);
				setState(489);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(486);
					attr();
					}
					}
					setState(491);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(492);
				match(CLOSE);
				setState(493);
				content();
				setState(494);
				match(OPEN);
				setState(495);
				match(SLASH);
				setState(496);
				match(ASSERT);
				setState(497);
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
			setState(525);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(501);
				match(OPEN);
				setState(502);
				match(REPORT);
				setState(506);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(503);
					attr();
					}
					}
					setState(508);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(509);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(510);
				match(OPEN);
				setState(511);
				match(REPORT);
				setState(515);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(512);
					attr();
					}
					}
					setState(517);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(518);
				match(CLOSE);
				setState(519);
				content();
				setState(520);
				match(OPEN);
				setState(521);
				match(SLASH);
				setState(522);
				match(REPORT);
				setState(523);
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
			setState(528);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEA_WS || _la==TEXT) {
				{
				setState(527);
				chardata();
				}
			}

			setState(551);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(544);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
					case 1:
						{
						setState(530);
						xmlelement();
						}
						break;
					case 2:
						{
						setState(531);
						desc();
						}
						break;
					case 3:
						{
						setState(532);
						element();
						}
						break;
					case 4:
						{
						setState(533);
						include();
						}
						break;
					case 5:
						{
						setState(534);
						choice();
						}
						break;
					case 6:
						{
						setState(535);
						letter();
						}
						break;
					case 7:
						{
						setState(536);
						assertion();
						}
						break;
					case 8:
						{
						setState(537);
						report();
						}
						break;
					case 9:
						{
						setState(538);
						reference();
						}
						break;
					case 10:
						{
						setState(539);
						attribute();
						}
						break;
					case 11:
						{
						setState(540);
						vocab();
						}
						break;
					case 12:
						{
						setState(541);
						match(CDATA);
						}
						break;
					case 13:
						{
						setState(542);
						match(PI);
						}
						break;
					case 14:
						{
						setState(543);
						match(COMMENT);
						}
						break;
					}
					setState(547);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SEA_WS || _la==TEXT) {
						{
						setState(546);
						chardata();
						}
					}

					}
					} 
				}
				setState(553);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
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
			setState(590);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(554);
				match(OPEN);
				setState(555);
				match(Name);
				setState(556);
				match(CLOSE);
				setState(557);
				content();
				setState(558);
				match(OPEN);
				setState(559);
				match(SLASH);
				setState(560);
				match(Name);
				setState(561);
				match(CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(563);
				match(OPEN);
				setState(564);
				match(Name);
				setState(565);
				match(SLASH_CLOSE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(566);
				match(OPEN);
				setState(567);
				match(Name);
				setState(571);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(568);
					attr();
					}
					}
					setState(573);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(574);
				match(CLOSE);
				setState(575);
				content();
				setState(576);
				match(OPEN);
				setState(577);
				match(SLASH);
				setState(578);
				match(Name);
				setState(579);
				match(CLOSE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(581);
				match(OPEN);
				setState(582);
				match(Name);
				setState(586);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(583);
					attr();
					}
					}
					setState(588);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(589);
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
			setState(592);
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
			setState(594);
			match(Name);
			setState(595);
			match(EQUALS);
			setState(596);
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
			setState(598);
			match(CONFATTR);
			setState(599);
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
			setState(601);
			match(FLEXIBILITYATTR);
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
			setState(604);
			match(CONTAINSATTR);
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
			setState(607);
			match(TYPEATTR);
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
			setState(610);
			match(IDATTR);
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
			setState(613);
			match(MANDATTR);
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
			setState(616);
			match(OPTATTR);
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
			setState(619);
			match(MAXOCCURSATTR);
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
			setState(622);
			match(MINOCCURSATTR);
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
			setState(625);
			match(NAMEATTR);
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
			setState(628);
			match(PROHIBITED);
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
			setState(631);
			match(REFATTR);
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
			setState(634);
			match(STRENGTHATTR);
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
			setState(637);
			match(VALUEATTR);
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
			setState(640);
			match(VALUESETATTR);
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
			setState(643);
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
			setState(645);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u028a\4\2\t\2\4"+
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
		"\n\5\3\5\3\5\3\5\3\5\7\5\u00ab\n\5\f\5\16\5\u00ae\13\5\3\5\5\5\u00b1\n"+
		"\5\3\5\5\5\u00b4\n\5\3\5\5\5\u00b7\n\5\3\5\5\5\u00ba\n\5\3\5\5\5\u00bd"+
		"\n\5\3\5\5\5\u00c0\n\5\3\5\5\5\u00c3\n\5\3\5\5\5\u00c6\n\5\3\5\5\5\u00c9"+
		"\n\5\3\5\5\5\u00cc\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00d5\n\5\3\6\3"+
		"\6\3\6\7\6\u00da\n\6\f\6\16\6\u00dd\13\6\3\6\5\6\u00e0\n\6\3\6\5\6\u00e3"+
		"\n\6\3\6\5\6\u00e6\n\6\3\6\5\6\u00e9\n\6\3\6\5\6\u00ec\n\6\3\6\5\6\u00ef"+
		"\n\6\3\6\5\6\u00f2\n\6\3\6\5\6\u00f5\n\6\3\6\5\6\u00f8\n\6\3\6\5\6\u00fb"+
		"\n\6\3\6\3\6\3\6\3\6\7\6\u0101\n\6\f\6\16\6\u0104\13\6\3\6\5\6\u0107\n"+
		"\6\3\6\5\6\u010a\n\6\3\6\5\6\u010d\n\6\3\6\5\6\u0110\n\6\3\6\5\6\u0113"+
		"\n\6\3\6\5\6\u0116\n\6\3\6\5\6\u0119\n\6\3\6\5\6\u011c\n\6\3\6\5\6\u011f"+
		"\n\6\3\6\5\6\u0122\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u012b\n\6\3\7\3"+
		"\7\3\7\7\7\u0130\n\7\f\7\16\7\u0133\13\7\3\7\5\7\u0136\n\7\3\7\5\7\u0139"+
		"\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\7\b\u0145\n\b\f\b\16\b\u0148"+
		"\13\b\3\b\5\b\u014b\n\b\3\b\5\b\u014e\n\b\3\b\5\b\u0151\n\b\3\b\5\b\u0154"+
		"\n\b\3\b\5\b\u0157\n\b\3\b\5\b\u015a\n\b\3\b\5\b\u015d\n\b\3\b\7\b\u0160"+
		"\n\b\f\b\16\b\u0163\13\b\3\b\3\b\3\b\3\b\7\b\u0169\n\b\f\b\16\b\u016c"+
		"\13\b\3\b\5\b\u016f\n\b\3\b\5\b\u0172\n\b\3\b\5\b\u0175\n\b\3\b\5\b\u0178"+
		"\n\b\3\b\5\b\u017b\n\b\3\b\5\b\u017e\n\b\3\b\5\b\u0181\n\b\3\b\7\b\u0184"+
		"\n\b\f\b\16\b\u0187\13\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0190\n\b\3\t"+
		"\3\t\3\t\7\t\u0195\n\t\f\t\16\t\u0198\13\t\3\t\5\t\u019b\n\t\3\t\5\t\u019e"+
		"\n\t\3\t\7\t\u01a1\n\t\f\t\16\t\u01a4\13\t\3\t\3\t\3\t\3\t\7\t\u01aa\n"+
		"\t\f\t\16\t\u01ad\13\t\3\t\5\t\u01b0\n\t\3\t\5\t\u01b3\n\t\3\t\7\t\u01b6"+
		"\n\t\f\t\16\t\u01b9\13\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u01c2\n\t\3\n"+
		"\3\n\3\n\5\n\u01c7\n\n\3\n\5\n\u01ca\n\n\3\n\3\n\3\n\3\n\5\n\u01d0\n\n"+
		"\3\n\5\n\u01d3\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u01dc\n\n\3\13\3\13"+
		"\3\13\7\13\u01e1\n\13\f\13\16\13\u01e4\13\13\3\13\3\13\3\13\3\13\7\13"+
		"\u01ea\n\13\f\13\16\13\u01ed\13\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\5\13\u01f6\n\13\3\f\3\f\3\f\7\f\u01fb\n\f\f\f\16\f\u01fe\13\f\3\f\3\f"+
		"\3\f\3\f\7\f\u0204\n\f\f\f\16\f\u0207\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\5\f\u0210\n\f\3\r\5\r\u0213\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\5\r\u0223\n\r\3\r\5\r\u0226\n\r\7\r\u0228\n\r\f\r\16"+
		"\r\u022b\13\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\7\16\u023c\n\16\f\16\16\16\u023f\13\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u024b\n\16\f\16\16\16\u024e"+
		"\13\16\3\16\5\16\u0251\n\16\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3"+
		"\37\3 \3 \3!\3!\3!\2\2\"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&("+
		"*,.\60\62\64\668:<>@\2\5\3\2\6\7\4\2\b\b\13\13\5\2\3\3\b\b--\2\u02df\2"+
		"B\3\2\2\2\4N\3\2\2\2\6q\3\2\2\2\b\u00d4\3\2\2\2\n\u012a\3\2\2\2\f\u012c"+
		"\3\2\2\2\16\u018f\3\2\2\2\20\u01c1\3\2\2\2\22\u01db\3\2\2\2\24\u01f5\3"+
		"\2\2\2\26\u020f\3\2\2\2\30\u0212\3\2\2\2\32\u0250\3\2\2\2\34\u0252\3\2"+
		"\2\2\36\u0254\3\2\2\2 \u0258\3\2\2\2\"\u025b\3\2\2\2$\u025e\3\2\2\2&\u0261"+
		"\3\2\2\2(\u0264\3\2\2\2*\u0267\3\2\2\2,\u026a\3\2\2\2.\u026d\3\2\2\2\60"+
		"\u0270\3\2\2\2\62\u0273\3\2\2\2\64\u0276\3\2\2\2\66\u0279\3\2\2\28\u027c"+
		"\3\2\2\2:\u027f\3\2\2\2<\u0282\3\2\2\2>\u0285\3\2\2\2@\u0287\3\2\2\2B"+
		"F\7\n\2\2CE\5\36\20\2DC\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2GI\3\2\2"+
		"\2HF\3\2\2\2IK\7&\2\2JL\7\b\2\2KJ\3\2\2\2KL\3\2\2\2L\3\3\2\2\2MO\5\2\2"+
		"\2NM\3\2\2\2NO\3\2\2\2OP\3\2\2\2PQ\7\t\2\2QU\7 \2\2RT\5\36\20\2SR\3\2"+
		"\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2VY\3\2\2\2WU\3\2\2\2XZ\5(\25\2YX\3\2"+
		"\2\2YZ\3\2\2\2Z^\3\2\2\2[]\5\36\20\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^"+
		"_\3\2\2\2_b\3\2\2\2`^\3\2\2\2ac\5\62\32\2ba\3\2\2\2bc\3\2\2\2cg\3\2\2"+
		"\2df\5\36\20\2ed\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2"+
		"\2\2jk\7%\2\2kl\5\30\r\2lm\7\t\2\2mn\7(\2\2no\7 \2\2op\7%\2\2p\5\3\2\2"+
		"\2qr\7\t\2\2rv\7\22\2\2su\5\36\20\2ts\3\2\2\2ux\3\2\2\2vt\3\2\2\2vw\3"+
		"\2\2\2wy\3\2\2\2xv\3\2\2\2yz\7%\2\2z{\5\30\r\2{|\7\t\2\2|}\7(\2\2}~\7"+
		"\22\2\2~\177\7%\2\2\177\7\3\2\2\2\u0080\u0081\7\t\2\2\u0081\u0085\7\23"+
		"\2\2\u0082\u0084\5\36\20\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0088\u008a\5 \21\2\u0089\u0088\3\2\2\2\u0089\u008a\3\2\2\2\u008a"+
		"\u008c\3\2\2\2\u008b\u008d\5$\23\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2"+
		"\2\2\u008d\u008f\3\2\2\2\u008e\u0090\5&\24\2\u008f\u008e\3\2\2\2\u008f"+
		"\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u0093\5(\25\2\u0092\u0091\3\2"+
		"\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0096\5*\26\2\u0095"+
		"\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0099\5."+
		"\30\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a"+
		"\u009c\5\60\31\2\u009b\u009a\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3"+
		"\2\2\2\u009d\u009f\5\62\32\2\u009e\u009d\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a1\3\2\2\2\u00a0\u00a2\5:\36\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2"+
		"\2\2\u00a2\u00a4\3\2\2\2\u00a3\u00a5\58\35\2\u00a4\u00a3\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00d5\7\'\2\2\u00a7\u00a8\7\t"+
		"\2\2\u00a8\u00ac\7\23\2\2\u00a9\u00ab\5\36\20\2\u00aa\u00a9\3\2\2\2\u00ab"+
		"\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00b0\3\2"+
		"\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b1\5 \21\2\u00b0\u00af\3\2\2\2\u00b0"+
		"\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b4\5$\23\2\u00b3\u00b2\3\2"+
		"\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b6\3\2\2\2\u00b5\u00b7\5&\24\2\u00b6"+
		"\u00b5\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00ba\5("+
		"\25\2\u00b9\u00b8\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb"+
		"\u00bd\5*\26\2\u00bc\u00bb\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2"+
		"\2\2\u00be\u00c0\5.\30\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c2\3\2\2\2\u00c1\u00c3\5\60\31\2\u00c2\u00c1\3\2\2\2\u00c2\u00c3\3"+
		"\2\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c6\5\62\32\2\u00c5\u00c4\3\2\2\2\u00c5"+
		"\u00c6\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00c9\5:\36\2\u00c8\u00c7\3\2"+
		"\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cb\3\2\2\2\u00ca\u00cc\58\35\2\u00cb"+
		"\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\7%"+
		"\2\2\u00ce\u00cf\5\30\r\2\u00cf\u00d0\7\t\2\2\u00d0\u00d1\7(\2\2\u00d1"+
		"\u00d2\7\23\2\2\u00d2\u00d3\7%\2\2\u00d3\u00d5\3\2\2\2\u00d4\u0080\3\2"+
		"\2\2\u00d4\u00a7\3\2\2\2\u00d5\t\3\2\2\2\u00d6\u00d7\7\t\2\2\u00d7\u00db"+
		"\7\r\2\2\u00d8\u00da\5\36\20\2\u00d9\u00d8\3\2\2\2\u00da\u00dd\3\2\2\2"+
		"\u00db\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00df\3\2\2\2\u00dd\u00db"+
		"\3\2\2\2\u00de\u00e0\5 \21\2\u00df\u00de\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0"+
		"\u00e2\3\2\2\2\u00e1\u00e3\5&\24\2\u00e2\u00e1\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e6\5(\25\2\u00e5\u00e4\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e9\5*\26\2\u00e8\u00e7\3\2"+
		"\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00eb\3\2\2\2\u00ea\u00ec\5,\27\2\u00eb"+
		"\u00ea\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00ef\5."+
		"\30\2\u00ee\u00ed\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef\u00f1\3\2\2\2\u00f0"+
		"\u00f2\5\60\31\2\u00f1\u00f0\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3"+
		"\2\2\2\u00f3\u00f5\5\62\32\2\u00f4\u00f3\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5"+
		"\u00f7\3\2\2\2\u00f6\u00f8\5\64\33\2\u00f7\u00f6\3\2\2\2\u00f7\u00f8\3"+
		"\2\2\2\u00f8\u00fa\3\2\2\2\u00f9\u00fb\5:\36\2\u00fa\u00f9\3\2\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u012b\7\'\2\2\u00fd\u00fe\7\t"+
		"\2\2\u00fe\u0102\7\r\2\2\u00ff\u0101\5\36\20\2\u0100\u00ff\3\2\2\2\u0101"+
		"\u0104\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0106\3\2"+
		"\2\2\u0104\u0102\3\2\2\2\u0105\u0107\5 \21\2\u0106\u0105\3\2\2\2\u0106"+
		"\u0107\3\2\2\2\u0107\u0109\3\2\2\2\u0108\u010a\5&\24\2\u0109\u0108\3\2"+
		"\2\2\u0109\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u010d\5(\25\2\u010c"+
		"\u010b\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010f\3\2\2\2\u010e\u0110\5*"+
		"\26\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0112\3\2\2\2\u0111"+
		"\u0113\5,\27\2\u0112\u0111\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\3\2"+
		"\2\2\u0114\u0116\5.\30\2\u0115\u0114\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"\u0118\3\2\2\2\u0117\u0119\5\60\31\2\u0118\u0117\3\2\2\2\u0118\u0119\3"+
		"\2\2\2\u0119\u011b\3\2\2\2\u011a\u011c\5\62\32\2\u011b\u011a\3\2\2\2\u011b"+
		"\u011c\3\2\2\2\u011c\u011e\3\2\2\2\u011d\u011f\5\64\33\2\u011e\u011d\3"+
		"\2\2\2\u011e\u011f\3\2\2\2\u011f\u0121\3\2\2\2\u0120\u0122\5:\36\2\u0121"+
		"\u0120\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\3\2\2\2\u0123\u0124\7%"+
		"\2\2\u0124\u0125\5\30\r\2\u0125\u0126\7\t\2\2\u0126\u0127\7(\2\2\u0127"+
		"\u0128\7\r\2\2\u0128\u0129\7%\2\2\u0129\u012b\3\2\2\2\u012a\u00d6\3\2"+
		"\2\2\u012a\u00fd\3\2\2\2\u012b\13\3\2\2\2\u012c\u012d\7\t\2\2\u012d\u0131"+
		"\7\16\2\2\u012e\u0130\5\36\20\2\u012f\u012e\3\2\2\2\u0130\u0133\3\2\2"+
		"\2\u0131\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131"+
		"\3\2\2\2\u0134\u0136\5.\30\2\u0135\u0134\3\2\2\2\u0135\u0136\3\2\2\2\u0136"+
		"\u0138\3\2\2\2\u0137\u0139\5\60\31\2\u0138\u0137\3\2\2\2\u0138\u0139\3"+
		"\2\2\2\u0139\u013a\3\2\2\2\u013a\u013b\7%\2\2\u013b\u013c\5\30\r\2\u013c"+
		"\u013d\7\t\2\2\u013d\u013e\7(\2\2\u013e\u013f\7\16\2\2\u013f\u0140\7%"+
		"\2\2\u0140\r\3\2\2\2\u0141\u0142\7\t\2\2\u0142\u0146\7\25\2\2\u0143\u0145"+
		"\5\36\20\2\u0144\u0143\3\2\2\2\u0145\u0148\3\2\2\2\u0146\u0144\3\2\2\2"+
		"\u0146\u0147\3\2\2\2\u0147\u014a\3\2\2\2\u0148\u0146\3\2\2\2\u0149\u014b"+
		"\5 \21\2\u014a\u0149\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c"+
		"\u014e\5\"\22\2\u014d\u014c\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u0150\3"+
		"\2\2\2\u014f\u0151\5(\25\2\u0150\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151"+
		"\u0153\3\2\2\2\u0152\u0154\5*\26\2\u0153\u0152\3\2\2\2\u0153\u0154\3\2"+
		"\2\2\u0154\u0156\3\2\2\2\u0155\u0157\5.\30\2\u0156\u0155\3\2\2\2\u0156"+
		"\u0157\3\2\2\2\u0157\u0159\3\2\2\2\u0158\u015a\5\60\31\2\u0159\u0158\3"+
		"\2\2\2\u0159\u015a\3\2\2\2\u015a\u015c\3\2\2\2\u015b\u015d\5\66\34\2\u015c"+
		"\u015b\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u0161\3\2\2\2\u015e\u0160\5\36"+
		"\20\2\u015f\u015e\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162\u0164\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0190\7\'"+
		"\2\2\u0165\u0166\7\t\2\2\u0166\u016a\7\25\2\2\u0167\u0169\5\36\20\2\u0168"+
		"\u0167\3\2\2\2\u0169\u016c\3\2\2\2\u016a\u0168\3\2\2\2\u016a\u016b\3\2"+
		"\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016d\u016f\5 \21\2\u016e"+
		"\u016d\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0171\3\2\2\2\u0170\u0172\5\""+
		"\22\2\u0171\u0170\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0174\3\2\2\2\u0173"+
		"\u0175\5(\25\2\u0174\u0173\3\2\2\2\u0174\u0175\3\2\2\2\u0175\u0177\3\2"+
		"\2\2\u0176\u0178\5*\26\2\u0177\u0176\3\2\2\2\u0177\u0178\3\2\2\2\u0178"+
		"\u017a\3\2\2\2\u0179\u017b\5.\30\2\u017a\u0179\3\2\2\2\u017a\u017b\3\2"+
		"\2\2\u017b\u017d\3\2\2\2\u017c\u017e\5\60\31\2\u017d\u017c\3\2\2\2\u017d"+
		"\u017e\3\2\2\2\u017e\u0180\3\2\2\2\u017f\u0181\5\66\34\2\u0180\u017f\3"+
		"\2\2\2\u0180\u0181\3\2\2\2\u0181\u0185\3\2\2\2\u0182\u0184\5\36\20\2\u0183"+
		"\u0182\3\2\2\2\u0184\u0187\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2"+
		"\2\2\u0186\u0188\3\2\2\2\u0187\u0185\3\2\2\2\u0188\u0189\7%\2\2\u0189"+
		"\u018a\5\30\r\2\u018a\u018b\7\t\2\2\u018b\u018c\7(\2\2\u018c\u018d\7\25"+
		"\2\2\u018d\u018e\7%\2\2\u018e\u0190\3\2\2\2\u018f\u0141\3\2\2\2\u018f"+
		"\u0165\3\2\2\2\u0190\17\3\2\2\2\u0191\u0192\7\t\2\2\u0192\u0196\7$\2\2"+
		"\u0193\u0195\5\36\20\2\u0194\u0193\3\2\2\2\u0195\u0198\3\2\2\2\u0196\u0194"+
		"\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u019a\3\2\2\2\u0198\u0196\3\2\2\2\u0199"+
		"\u019b\5\"\22\2\u019a\u0199\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019d\3"+
		"\2\2\2\u019c\u019e\5<\37\2\u019d\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e"+
		"\u01a2\3\2\2\2\u019f\u01a1\5\36\20\2\u01a0\u019f\3\2\2\2\u01a1\u01a4\3"+
		"\2\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3\u01a5\3\2\2\2\u01a4"+
		"\u01a2\3\2\2\2\u01a5\u01c2\7\'\2\2\u01a6\u01a7\7\t\2\2\u01a7\u01ab\7$"+
		"\2\2\u01a8\u01aa\5\36\20\2\u01a9\u01a8\3\2\2\2\u01aa\u01ad\3\2\2\2\u01ab"+
		"\u01a9\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01af\3\2\2\2\u01ad\u01ab\3\2"+
		"\2\2\u01ae\u01b0\5\"\22\2\u01af\u01ae\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0"+
		"\u01b2\3\2\2\2\u01b1\u01b3\5<\37\2\u01b2\u01b1\3\2\2\2\u01b2\u01b3\3\2"+
		"\2\2\u01b3\u01b7\3\2\2\2\u01b4\u01b6\5\36\20\2\u01b5\u01b4\3\2\2\2\u01b6"+
		"\u01b9\3\2\2\2\u01b7\u01b5\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01ba\3\2"+
		"\2\2\u01b9\u01b7\3\2\2\2\u01ba\u01bb\7%\2\2\u01bb\u01bc\5\30\r\2\u01bc"+
		"\u01bd\7\t\2\2\u01bd\u01be\7(\2\2\u01be\u01bf\7$\2\2\u01bf\u01c0\7%\2"+
		"\2\u01c0\u01c2\3\2\2\2\u01c1\u0191\3\2\2\2\u01c1\u01a6\3\2\2\2\u01c2\21"+
		"\3\2\2\2\u01c3\u01c4\7\t\2\2\u01c4\u01c6\7\26\2\2\u01c5\u01c7\5\62\32"+
		"\2\u01c6\u01c5\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u01c9\3\2\2\2\u01c8\u01ca"+
		"\5:\36\2\u01c9\u01c8\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb"+
		"\u01dc\7\'\2\2\u01cc\u01cd\7\t\2\2\u01cd\u01cf\7\26\2\2\u01ce\u01d0\5"+
		"\62\32\2\u01cf\u01ce\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0\u01d2\3\2\2\2\u01d1"+
		"\u01d3\5:\36\2\u01d2\u01d1\3\2\2\2\u01d2\u01d3\3\2\2\2\u01d3\u01d4\3\2"+
		"\2\2\u01d4\u01d5\7%\2\2\u01d5\u01d6\5\30\r\2\u01d6\u01d7\7\t\2\2\u01d7"+
		"\u01d8\7(\2\2\u01d8\u01d9\7\26\2\2\u01d9\u01da\7%\2\2\u01da\u01dc\3\2"+
		"\2\2\u01db\u01c3\3\2\2\2\u01db\u01cc\3\2\2\2\u01dc\23\3\2\2\2\u01dd\u01de"+
		"\7\t\2\2\u01de\u01e2\7\f\2\2\u01df\u01e1\5\36\20\2\u01e0\u01df\3\2\2\2"+
		"\u01e1\u01e4\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e5"+
		"\3\2\2\2\u01e4\u01e2\3\2\2\2\u01e5\u01f6\7\'\2\2\u01e6\u01e7\7\t\2\2\u01e7"+
		"\u01eb\7\f\2\2\u01e8\u01ea\5\36\20\2\u01e9\u01e8\3\2\2\2\u01ea\u01ed\3"+
		"\2\2\2\u01eb\u01e9\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ee\3\2\2\2\u01ed"+
		"\u01eb\3\2\2\2\u01ee\u01ef\7%\2\2\u01ef\u01f0\5\30\r\2\u01f0\u01f1\7\t"+
		"\2\2\u01f1\u01f2\7(\2\2\u01f2\u01f3\7\f\2\2\u01f3\u01f4\7%\2\2\u01f4\u01f6"+
		"\3\2\2\2\u01f5\u01dd\3\2\2\2\u01f5\u01e6\3\2\2\2\u01f6\25\3\2\2\2\u01f7"+
		"\u01f8\7\t\2\2\u01f8\u01fc\7\36\2\2\u01f9\u01fb\5\36\20\2\u01fa\u01f9"+
		"\3\2\2\2\u01fb\u01fe\3\2\2\2\u01fc\u01fa\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd"+
		"\u01ff\3\2\2\2\u01fe\u01fc\3\2\2\2\u01ff\u0210\7\'\2\2\u0200\u0201\7\t"+
		"\2\2\u0201\u0205\7\36\2\2\u0202\u0204\5\36\20\2\u0203\u0202\3\2\2\2\u0204"+
		"\u0207\3\2\2\2\u0205\u0203\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0208\3\2"+
		"\2\2\u0207\u0205\3\2\2\2\u0208\u0209\7%\2\2\u0209\u020a\5\30\r\2\u020a"+
		"\u020b\7\t\2\2\u020b\u020c\7(\2\2\u020c\u020d\7\36\2\2\u020d\u020e\7%"+
		"\2\2\u020e\u0210\3\2\2\2\u020f\u01f7\3\2\2\2\u020f\u0200\3\2\2\2\u0210"+
		"\27\3\2\2\2\u0211\u0213\5> \2\u0212\u0211\3\2\2\2\u0212\u0213\3\2\2\2"+
		"\u0213\u0229\3\2\2\2\u0214\u0223\5\32\16\2\u0215\u0223\5\6\4\2\u0216\u0223"+
		"\5\b\5\2\u0217\u0223\5\16\b\2\u0218\u0223\5\f\7\2\u0219\u0223\5\22\n\2"+
		"\u021a\u0223\5\24\13\2\u021b\u0223\5\26\f\2\u021c\u0223\5\34\17\2\u021d"+
		"\u0223\5\n\6\2\u021e\u0223\5\20\t\2\u021f\u0223\7\4\2\2\u0220\u0223\7"+
		"-\2\2\u0221\u0223\7\3\2\2\u0222\u0214\3\2\2\2\u0222\u0215\3\2\2\2\u0222"+
		"\u0216\3\2\2\2\u0222\u0217\3\2\2\2\u0222\u0218\3\2\2\2\u0222\u0219\3\2"+
		"\2\2\u0222\u021a\3\2\2\2\u0222\u021b\3\2\2\2\u0222\u021c\3\2\2\2\u0222"+
		"\u021d\3\2\2\2\u0222\u021e\3\2\2\2\u0222\u021f\3\2\2\2\u0222\u0220\3\2"+
		"\2\2\u0222\u0221\3\2\2\2\u0223\u0225\3\2\2\2\u0224\u0226\5> \2\u0225\u0224"+
		"\3\2\2\2\u0225\u0226\3\2\2\2\u0226\u0228\3\2\2\2\u0227\u0222\3\2\2\2\u0228"+
		"\u022b\3\2\2\2\u0229\u0227\3\2\2\2\u0229\u022a\3\2\2\2\u022a\31\3\2\2"+
		"\2\u022b\u0229\3\2\2\2\u022c\u022d\7\t\2\2\u022d\u022e\7+\2\2\u022e\u022f"+
		"\7%\2\2\u022f\u0230\5\30\r\2\u0230\u0231\7\t\2\2\u0231\u0232\7(\2\2\u0232"+
		"\u0233\7+\2\2\u0233\u0234\7%\2\2\u0234\u0251\3\2\2\2\u0235\u0236\7\t\2"+
		"\2\u0236\u0237\7+\2\2\u0237\u0251\7\'\2\2\u0238\u0239\7\t\2\2\u0239\u023d"+
		"\7+\2\2\u023a\u023c\5\36\20\2\u023b\u023a\3\2\2\2\u023c\u023f\3\2\2\2"+
		"\u023d\u023b\3\2\2\2\u023d\u023e\3\2\2\2\u023e\u0240\3\2\2\2\u023f\u023d"+
		"\3\2\2\2\u0240\u0241\7%\2\2\u0241\u0242\5\30\r\2\u0242\u0243\7\t\2\2\u0243"+
		"\u0244\7(\2\2\u0244\u0245\7+\2\2\u0245\u0246\7%\2\2\u0246\u0251\3\2\2"+
		"\2\u0247\u0248\7\t\2\2\u0248\u024c\7+\2\2\u0249\u024b\5\36\20\2\u024a"+
		"\u0249\3\2\2\2\u024b\u024e\3\2\2\2\u024c\u024a\3\2\2\2\u024c\u024d\3\2"+
		"\2\2\u024d\u024f\3\2\2\2\u024e\u024c\3\2\2\2\u024f\u0251\7\'\2\2\u0250"+
		"\u022c\3\2\2\2\u0250\u0235\3\2\2\2\u0250\u0238\3\2\2\2\u0250\u0247\3\2"+
		"\2\2\u0251\33\3\2\2\2\u0252\u0253\t\2\2\2\u0253\35\3\2\2\2\u0254\u0255"+
		"\7+\2\2\u0255\u0256\7)\2\2\u0256\u0257\7*\2\2\u0257\37\3\2\2\2\u0258\u0259"+
		"\7\17\2\2\u0259\u025a\7*\2\2\u025a!\3\2\2\2\u025b\u025c\7\20\2\2\u025c"+
		"\u025d\7*\2\2\u025d#\3\2\2\2\u025e\u025f\7\21\2\2\u025f\u0260\7*\2\2\u0260"+
		"%\3\2\2\2\u0261\u0262\7!\2\2\u0262\u0263\7*\2\2\u0263\'\3\2\2\2\u0264"+
		"\u0265\7\24\2\2\u0265\u0266\7*\2\2\u0266)\3\2\2\2\u0267\u0268\7\27\2\2"+
		"\u0268\u0269\7*\2\2\u0269+\3\2\2\2\u026a\u026b\7\33\2\2\u026b\u026c\7"+
		"*\2\2\u026c-\3\2\2\2\u026d\u026e\7\30\2\2\u026e\u026f\7*\2\2\u026f/\3"+
		"\2\2\2\u0270\u0271\7\31\2\2\u0271\u0272\7*\2\2\u0272\61\3\2\2\2\u0273"+
		"\u0274\7\32\2\2\u0274\u0275\7*\2\2\u0275\63\3\2\2\2\u0276\u0277\7\34\2"+
		"\2\u0277\u0278\7*\2\2\u0278\65\3\2\2\2\u0279\u027a\7\35\2\2\u027a\u027b"+
		"\7*\2\2\u027b\67\3\2\2\2\u027c\u027d\7\37\2\2\u027d\u027e\7*\2\2\u027e"+
		"9\3\2\2\2\u027f\u0280\7\"\2\2\u0280\u0281\7*\2\2\u0281;\3\2\2\2\u0282"+
		"\u0283\7#\2\2\u0283\u0284\7*\2\2\u0284=\3\2\2\2\u0285\u0286\t\3\2\2\u0286"+
		"?\3\2\2\2\u0287\u0288\t\4\2\2\u0288A\3\2\2\2jFKNUY^bgv\u0085\u0089\u008c"+
		"\u008f\u0092\u0095\u0098\u009b\u009e\u00a1\u00a4\u00ac\u00b0\u00b3\u00b6"+
		"\u00b9\u00bc\u00bf\u00c2\u00c5\u00c8\u00cb\u00d4\u00db\u00df\u00e2\u00e5"+
		"\u00e8\u00eb\u00ee\u00f1\u00f4\u00f7\u00fa\u0102\u0106\u0109\u010c\u010f"+
		"\u0112\u0115\u0118\u011b\u011e\u0121\u012a\u0131\u0135\u0138\u0146\u014a"+
		"\u014d\u0150\u0153\u0156\u0159\u015c\u0161\u016a\u016e\u0171\u0174\u0177"+
		"\u017a\u017d\u0180\u0185\u018f\u0196\u019a\u019d\u01a2\u01ab\u01af\u01b2"+
		"\u01b7\u01c1\u01c6\u01c9\u01cf\u01d2\u01db\u01e2\u01eb\u01f5\u01fc\u0205"+
		"\u020f\u0212\u0222\u0225\u0229\u023d\u024c\u0250";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}