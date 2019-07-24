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
		TEXT=9, TEMPLATE=10, INCLUDE=11, CHOICE=12, LET=13, ASSERT=14, REPORT=15, 
		DESC=16, ELEMENT=17, ATTRIBUTE=18, REFATTR=19, NAMEATTR=20, TYPEATTR=21, 
		MINOCCURSATTR=22, MAXOCCURSATTR=23, CONFATTR=24, MANDATTR=25, OPTATTR=26, 
		PROHIBITED=27, IDATTR=28, VALUEATTR=29, CONTAINSATTR=30, CLOSE=31, SPECIAL_CLOSE=32, 
		SLASH_CLOSE=33, SLASH=34, EQUALS=35, AttrValue=36, Name=37, S=38, PI=39;
	public static final int
		RULE_prolog = 0, RULE_template = 1, RULE_desc = 2, RULE_element = 3, RULE_attribute = 4, 
		RULE_choice = 5, RULE_include = 6, RULE_letter = 7, RULE_assertion = 8, 
		RULE_report = 9, RULE_content = 10, RULE_xmlelement = 11, RULE_reference = 12, 
		RULE_attr = 13, RULE_nameAttr = 14, RULE_dataTypeAttr = 15, RULE_minOccursAttr = 16, 
		RULE_maxOccursAttr = 17, RULE_conformanceAttr = 18, RULE_isMandatoryAttr = 19, 
		RULE_isOptionalAttr = 20, RULE_prohibitedAttr = 21, RULE_idAttr = 22, 
		RULE_valueAttr = 23, RULE_containsAttr = 24, RULE_refAttr = 25, RULE_chardata = 26, 
		RULE_misc = 27;
	public static final String[] ruleNames = {
		"prolog", "template", "desc", "element", "attribute", "choice", "include", 
		"letter", "assertion", "report", "content", "xmlelement", "reference", 
		"attr", "nameAttr", "dataTypeAttr", "minOccursAttr", "maxOccursAttr", 
		"conformanceAttr", "isMandatoryAttr", "isOptionalAttr", "prohibitedAttr", 
		"idAttr", "valueAttr", "containsAttr", "refAttr", "chardata", "misc"
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
			setState(56);
			match(XMLDeclOpen);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(57);
				attr();
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63);
			match(SPECIAL_CLOSE);
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEA_WS) {
				{
				setState(64);
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
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==XMLDeclOpen) {
				{
				setState(67);
				prolog();
				}
			}

			setState(70);
			match(OPEN);
			setState(71);
			match(TEMPLATE);
			setState(75);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(72);
					attr();
					}
					} 
				}
				setState(77);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDATTR) {
				{
				setState(78);
				idAttr();
				}
			}

			setState(84);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(81);
					attr();
					}
					} 
				}
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAMEATTR) {
				{
				setState(87);
				nameAttr();
				}
			}

			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(90);
				attr();
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
			match(CLOSE);
			setState(97);
			content();
			setState(98);
			match(OPEN);
			setState(99);
			match(SLASH);
			setState(100);
			match(TEMPLATE);
			setState(101);
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
			setState(103);
			match(OPEN);
			setState(104);
			match(DESC);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(105);
				attr();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
			match(CLOSE);
			setState(112);
			content();
			setState(113);
			match(OPEN);
			setState(114);
			match(SLASH);
			setState(115);
			match(DESC);
			setState(116);
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
			setState(196);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(118);
				match(OPEN);
				setState(119);
				match(ELEMENT);
				setState(123);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(120);
					attr();
					}
					}
					setState(125);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(127);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(126);
					conformanceAttr();
					}
				}

				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONTAINSATTR) {
					{
					setState(129);
					containsAttr();
					}
				}

				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(132);
					dataTypeAttr();
					}
				}

				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(135);
					idAttr();
					}
				}

				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(138);
					isMandatoryAttr();
					}
				}

				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(141);
					maxOccursAttr();
					}
				}

				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(144);
					minOccursAttr();
					}
				}

				setState(148);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(147);
					nameAttr();
					}
				}

				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(150);
					valueAttr();
					}
				}

				setState(153);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				match(OPEN);
				setState(155);
				match(ELEMENT);
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(156);
					attr();
					}
					}
					setState(161);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(163);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(162);
					conformanceAttr();
					}
				}

				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONTAINSATTR) {
					{
					setState(165);
					containsAttr();
					}
				}

				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(168);
					dataTypeAttr();
					}
				}

				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(171);
					idAttr();
					}
				}

				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(174);
					isMandatoryAttr();
					}
				}

				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(177);
					maxOccursAttr();
					}
				}

				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(180);
					minOccursAttr();
					}
				}

				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(183);
					nameAttr();
					}
				}

				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(186);
					valueAttr();
					}
				}

				setState(189);
				match(CLOSE);
				setState(190);
				content();
				setState(191);
				match(OPEN);
				setState(192);
				match(SLASH);
				setState(193);
				match(ELEMENT);
				setState(194);
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
			setState(282);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				match(OPEN);
				setState(199);
				match(ATTRIBUTE);
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(200);
					attr();
					}
					}
					setState(205);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(207);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(206);
					conformanceAttr();
					}
				}

				setState(210);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(209);
					dataTypeAttr();
					}
				}

				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(212);
					idAttr();
					}
				}

				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(215);
					isMandatoryAttr();
					}
				}

				setState(219);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(218);
					isOptionalAttr();
					}
				}

				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(221);
					maxOccursAttr();
					}
				}

				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(224);
					minOccursAttr();
					}
				}

				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(227);
					nameAttr();
					}
				}

				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(230);
					prohibitedAttr();
					}
				}

				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(233);
					valueAttr();
					}
				}

				setState(236);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(237);
				match(OPEN);
				setState(238);
				match(ATTRIBUTE);
				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(239);
					attr();
					}
					}
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(245);
					conformanceAttr();
					}
				}

				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(248);
					dataTypeAttr();
					}
				}

				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(251);
					idAttr();
					}
				}

				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(254);
					isMandatoryAttr();
					}
				}

				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(257);
					isOptionalAttr();
					}
				}

				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(260);
					maxOccursAttr();
					}
				}

				setState(264);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(263);
					minOccursAttr();
					}
				}

				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(266);
					nameAttr();
					}
				}

				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(269);
					prohibitedAttr();
					}
				}

				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(272);
					valueAttr();
					}
				}

				setState(275);
				match(CLOSE);
				setState(276);
				content();
				setState(277);
				match(OPEN);
				setState(278);
				match(SLASH);
				setState(279);
				match(ATTRIBUTE);
				setState(280);
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
			setState(284);
			match(OPEN);
			setState(285);
			match(CHOICE);
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(286);
				attr();
				}
				}
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(293);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MAXOCCURSATTR) {
				{
				setState(292);
				maxOccursAttr();
				}
			}

			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINOCCURSATTR) {
				{
				setState(295);
				minOccursAttr();
				}
			}

			setState(298);
			match(CLOSE);
			setState(299);
			content();
			setState(300);
			match(OPEN);
			setState(301);
			match(SLASH);
			setState(302);
			match(CHOICE);
			setState(303);
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
			setState(377);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(305);
				match(OPEN);
				setState(306);
				match(INCLUDE);
				setState(310);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(307);
						attr();
						}
						} 
					}
					setState(312);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
				}
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(313);
					conformanceAttr();
					}
				}

				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(316);
					idAttr();
					}
				}

				setState(320);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(319);
					isMandatoryAttr();
					}
				}

				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(322);
					maxOccursAttr();
					}
				}

				setState(326);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(325);
					minOccursAttr();
					}
				}

				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(328);
					refAttr();
					}
				}

				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(331);
					attr();
					}
					}
					setState(336);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(337);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(338);
				match(OPEN);
				setState(339);
				match(INCLUDE);
				setState(343);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(340);
						attr();
						}
						} 
					}
					setState(345);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
				}
				setState(347);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(346);
					conformanceAttr();
					}
				}

				setState(350);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(349);
					idAttr();
					}
				}

				setState(353);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(352);
					isMandatoryAttr();
					}
				}

				setState(356);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(355);
					maxOccursAttr();
					}
				}

				setState(359);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(358);
					minOccursAttr();
					}
				}

				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(361);
					refAttr();
					}
				}

				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(364);
					attr();
					}
					}
					setState(369);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(370);
				match(CLOSE);
				setState(371);
				content();
				setState(372);
				match(OPEN);
				setState(373);
				match(SLASH);
				setState(374);
				match(INCLUDE);
				setState(375);
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
		enterRule(_localctx, 14, RULE_letter);
		int _la;
		try {
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(379);
				match(OPEN);
				setState(380);
				match(LET);
				setState(382);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(381);
					nameAttr();
					}
				}

				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(384);
					valueAttr();
					}
				}

				setState(387);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(388);
				match(OPEN);
				setState(389);
				match(LET);
				setState(391);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(390);
					nameAttr();
					}
				}

				setState(394);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(393);
					valueAttr();
					}
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
				match(LET);
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
		enterRule(_localctx, 16, RULE_assertion);
		int _la;
		try {
			setState(429);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(405);
				match(OPEN);
				setState(406);
				match(ASSERT);
				setState(410);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(407);
					attr();
					}
					}
					setState(412);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(413);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(414);
				match(OPEN);
				setState(415);
				match(ASSERT);
				setState(419);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(416);
					attr();
					}
					}
					setState(421);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(422);
				match(CLOSE);
				setState(423);
				content();
				setState(424);
				match(OPEN);
				setState(425);
				match(SLASH);
				setState(426);
				match(ASSERT);
				setState(427);
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
		enterRule(_localctx, 18, RULE_report);
		int _la;
		try {
			setState(455);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(431);
				match(OPEN);
				setState(432);
				match(REPORT);
				setState(436);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(433);
					attr();
					}
					}
					setState(438);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(439);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(440);
				match(OPEN);
				setState(441);
				match(REPORT);
				setState(445);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(442);
					attr();
					}
					}
					setState(447);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(448);
				match(CLOSE);
				setState(449);
				content();
				setState(450);
				match(OPEN);
				setState(451);
				match(SLASH);
				setState(452);
				match(REPORT);
				setState(453);
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
		enterRule(_localctx, 20, RULE_content);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEA_WS || _la==TEXT) {
				{
				setState(457);
				chardata();
				}
			}

			setState(480);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(473);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
					case 1:
						{
						setState(460);
						xmlelement();
						}
						break;
					case 2:
						{
						setState(461);
						desc();
						}
						break;
					case 3:
						{
						setState(462);
						element();
						}
						break;
					case 4:
						{
						setState(463);
						include();
						}
						break;
					case 5:
						{
						setState(464);
						choice();
						}
						break;
					case 6:
						{
						setState(465);
						letter();
						}
						break;
					case 7:
						{
						setState(466);
						assertion();
						}
						break;
					case 8:
						{
						setState(467);
						report();
						}
						break;
					case 9:
						{
						setState(468);
						reference();
						}
						break;
					case 10:
						{
						setState(469);
						attribute();
						}
						break;
					case 11:
						{
						setState(470);
						match(CDATA);
						}
						break;
					case 12:
						{
						setState(471);
						match(PI);
						}
						break;
					case 13:
						{
						setState(472);
						match(COMMENT);
						}
						break;
					}
					setState(476);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SEA_WS || _la==TEXT) {
						{
						setState(475);
						chardata();
						}
					}

					}
					} 
				}
				setState(482);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
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
		enterRule(_localctx, 22, RULE_xmlelement);
		int _la;
		try {
			setState(519);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,90,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(483);
				match(OPEN);
				setState(484);
				match(Name);
				setState(485);
				match(CLOSE);
				setState(486);
				content();
				setState(487);
				match(OPEN);
				setState(488);
				match(SLASH);
				setState(489);
				match(Name);
				setState(490);
				match(CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(492);
				match(OPEN);
				setState(493);
				match(Name);
				setState(494);
				match(SLASH_CLOSE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(495);
				match(OPEN);
				setState(496);
				match(Name);
				setState(500);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(497);
					attr();
					}
					}
					setState(502);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(503);
				match(CLOSE);
				setState(504);
				content();
				setState(505);
				match(OPEN);
				setState(506);
				match(SLASH);
				setState(507);
				match(Name);
				setState(508);
				match(CLOSE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(510);
				match(OPEN);
				setState(511);
				match(Name);
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
		enterRule(_localctx, 24, RULE_reference);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(521);
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
		enterRule(_localctx, 26, RULE_attr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			match(Name);
			setState(524);
			match(EQUALS);
			setState(525);
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
		enterRule(_localctx, 28, RULE_nameAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(527);
			match(NAMEATTR);
			setState(528);
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
		enterRule(_localctx, 30, RULE_dataTypeAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(530);
			match(TYPEATTR);
			setState(531);
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
		enterRule(_localctx, 32, RULE_minOccursAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(533);
			match(MINOCCURSATTR);
			setState(534);
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
		enterRule(_localctx, 34, RULE_maxOccursAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(536);
			match(MAXOCCURSATTR);
			setState(537);
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
		enterRule(_localctx, 36, RULE_conformanceAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(539);
			match(CONFATTR);
			setState(540);
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
		enterRule(_localctx, 38, RULE_isMandatoryAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			match(MANDATTR);
			setState(543);
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
		enterRule(_localctx, 40, RULE_isOptionalAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(545);
			match(OPTATTR);
			setState(546);
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
		enterRule(_localctx, 42, RULE_prohibitedAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			match(PROHIBITED);
			setState(549);
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
		enterRule(_localctx, 44, RULE_idAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(551);
			match(IDATTR);
			setState(552);
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
		enterRule(_localctx, 46, RULE_valueAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(554);
			match(VALUEATTR);
			setState(555);
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
		enterRule(_localctx, 48, RULE_containsAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(557);
			match(CONTAINSATTR);
			setState(558);
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
		enterRule(_localctx, 50, RULE_refAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			match(REFATTR);
			setState(561);
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
		enterRule(_localctx, 52, RULE_chardata);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(563);
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
		enterRule(_localctx, 54, RULE_misc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u023a\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\7\2=\n\2\f\2\16\2@\13"+
		"\2\3\2\3\2\5\2D\n\2\3\3\5\3G\n\3\3\3\3\3\3\3\7\3L\n\3\f\3\16\3O\13\3\3"+
		"\3\5\3R\n\3\3\3\7\3U\n\3\f\3\16\3X\13\3\3\3\5\3[\n\3\3\3\7\3^\n\3\f\3"+
		"\16\3a\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4m\n\4\f\4\16\4"+
		"p\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5|\n\5\f\5\16\5\177\13"+
		"\5\3\5\5\5\u0082\n\5\3\5\5\5\u0085\n\5\3\5\5\5\u0088\n\5\3\5\5\5\u008b"+
		"\n\5\3\5\5\5\u008e\n\5\3\5\5\5\u0091\n\5\3\5\5\5\u0094\n\5\3\5\5\5\u0097"+
		"\n\5\3\5\5\5\u009a\n\5\3\5\3\5\3\5\3\5\7\5\u00a0\n\5\f\5\16\5\u00a3\13"+
		"\5\3\5\5\5\u00a6\n\5\3\5\5\5\u00a9\n\5\3\5\5\5\u00ac\n\5\3\5\5\5\u00af"+
		"\n\5\3\5\5\5\u00b2\n\5\3\5\5\5\u00b5\n\5\3\5\5\5\u00b8\n\5\3\5\5\5\u00bb"+
		"\n\5\3\5\5\5\u00be\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00c7\n\5\3\6\3"+
		"\6\3\6\7\6\u00cc\n\6\f\6\16\6\u00cf\13\6\3\6\5\6\u00d2\n\6\3\6\5\6\u00d5"+
		"\n\6\3\6\5\6\u00d8\n\6\3\6\5\6\u00db\n\6\3\6\5\6\u00de\n\6\3\6\5\6\u00e1"+
		"\n\6\3\6\5\6\u00e4\n\6\3\6\5\6\u00e7\n\6\3\6\5\6\u00ea\n\6\3\6\5\6\u00ed"+
		"\n\6\3\6\3\6\3\6\3\6\7\6\u00f3\n\6\f\6\16\6\u00f6\13\6\3\6\5\6\u00f9\n"+
		"\6\3\6\5\6\u00fc\n\6\3\6\5\6\u00ff\n\6\3\6\5\6\u0102\n\6\3\6\5\6\u0105"+
		"\n\6\3\6\5\6\u0108\n\6\3\6\5\6\u010b\n\6\3\6\5\6\u010e\n\6\3\6\5\6\u0111"+
		"\n\6\3\6\5\6\u0114\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u011d\n\6\3\7\3"+
		"\7\3\7\7\7\u0122\n\7\f\7\16\7\u0125\13\7\3\7\5\7\u0128\n\7\3\7\5\7\u012b"+
		"\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\7\b\u0137\n\b\f\b\16\b\u013a"+
		"\13\b\3\b\5\b\u013d\n\b\3\b\5\b\u0140\n\b\3\b\5\b\u0143\n\b\3\b\5\b\u0146"+
		"\n\b\3\b\5\b\u0149\n\b\3\b\5\b\u014c\n\b\3\b\7\b\u014f\n\b\f\b\16\b\u0152"+
		"\13\b\3\b\3\b\3\b\3\b\7\b\u0158\n\b\f\b\16\b\u015b\13\b\3\b\5\b\u015e"+
		"\n\b\3\b\5\b\u0161\n\b\3\b\5\b\u0164\n\b\3\b\5\b\u0167\n\b\3\b\5\b\u016a"+
		"\n\b\3\b\5\b\u016d\n\b\3\b\7\b\u0170\n\b\f\b\16\b\u0173\13\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\5\b\u017c\n\b\3\t\3\t\3\t\5\t\u0181\n\t\3\t\5\t\u0184"+
		"\n\t\3\t\3\t\3\t\3\t\5\t\u018a\n\t\3\t\5\t\u018d\n\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\5\t\u0196\n\t\3\n\3\n\3\n\7\n\u019b\n\n\f\n\16\n\u019e\13\n"+
		"\3\n\3\n\3\n\3\n\7\n\u01a4\n\n\f\n\16\n\u01a7\13\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\5\n\u01b0\n\n\3\13\3\13\3\13\7\13\u01b5\n\13\f\13\16\13\u01b8"+
		"\13\13\3\13\3\13\3\13\3\13\7\13\u01be\n\13\f\13\16\13\u01c1\13\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u01ca\n\13\3\f\5\f\u01cd\n\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u01dc\n\f\3\f\5\f\u01df"+
		"\n\f\7\f\u01e1\n\f\f\f\16\f\u01e4\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u01f5\n\r\f\r\16\r\u01f8\13\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0204\n\r\f\r\16\r\u0207\13\r\3\r"+
		"\5\r\u020a\n\r\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\34\3\34\3\35\3\35\3\35\2\2\36\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668\2\5\3\2\6\7\4\2\b\b\13\13\5\2\3"+
		"\3\b\b))\2\u0285\2:\3\2\2\2\4F\3\2\2\2\6i\3\2\2\2\b\u00c6\3\2\2\2\n\u011c"+
		"\3\2\2\2\f\u011e\3\2\2\2\16\u017b\3\2\2\2\20\u0195\3\2\2\2\22\u01af\3"+
		"\2\2\2\24\u01c9\3\2\2\2\26\u01cc\3\2\2\2\30\u0209\3\2\2\2\32\u020b\3\2"+
		"\2\2\34\u020d\3\2\2\2\36\u0211\3\2\2\2 \u0214\3\2\2\2\"\u0217\3\2\2\2"+
		"$\u021a\3\2\2\2&\u021d\3\2\2\2(\u0220\3\2\2\2*\u0223\3\2\2\2,\u0226\3"+
		"\2\2\2.\u0229\3\2\2\2\60\u022c\3\2\2\2\62\u022f\3\2\2\2\64\u0232\3\2\2"+
		"\2\66\u0235\3\2\2\28\u0237\3\2\2\2:>\7\n\2\2;=\5\34\17\2<;\3\2\2\2=@\3"+
		"\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AC\7\"\2\2BD\7\b\2\2CB\3"+
		"\2\2\2CD\3\2\2\2D\3\3\2\2\2EG\5\2\2\2FE\3\2\2\2FG\3\2\2\2GH\3\2\2\2HI"+
		"\7\t\2\2IM\7\f\2\2JL\5\34\17\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2"+
		"NQ\3\2\2\2OM\3\2\2\2PR\5.\30\2QP\3\2\2\2QR\3\2\2\2RV\3\2\2\2SU\5\34\17"+
		"\2TS\3\2\2\2UX\3\2\2\2VT\3\2\2\2VW\3\2\2\2WZ\3\2\2\2XV\3\2\2\2Y[\5\36"+
		"\20\2ZY\3\2\2\2Z[\3\2\2\2[_\3\2\2\2\\^\5\34\17\2]\\\3\2\2\2^a\3\2\2\2"+
		"_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3\2\2\2bc\7!\2\2cd\5\26\f\2de\7\t\2\2"+
		"ef\7$\2\2fg\7\f\2\2gh\7!\2\2h\5\3\2\2\2ij\7\t\2\2jn\7\22\2\2km\5\34\17"+
		"\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2pn\3\2\2\2qr\7!\2"+
		"\2rs\5\26\f\2st\7\t\2\2tu\7$\2\2uv\7\22\2\2vw\7!\2\2w\7\3\2\2\2xy\7\t"+
		"\2\2y}\7\23\2\2z|\5\34\17\2{z\3\2\2\2|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2"+
		"~\u0081\3\2\2\2\177}\3\2\2\2\u0080\u0082\5&\24\2\u0081\u0080\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0085\5\62\32\2\u0084\u0083\3"+
		"\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086\u0088\5 \21\2\u0087"+
		"\u0086\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u008b\5."+
		"\30\2\u008a\u0089\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c"+
		"\u008e\5(\25\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u0090\3\2"+
		"\2\2\u008f\u0091\5$\23\2\u0090\u008f\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0093\3\2\2\2\u0092\u0094\5\"\22\2\u0093\u0092\3\2\2\2\u0093\u0094\3"+
		"\2\2\2\u0094\u0096\3\2\2\2\u0095\u0097\5\36\20\2\u0096\u0095\3\2\2\2\u0096"+
		"\u0097\3\2\2\2\u0097\u0099\3\2\2\2\u0098\u009a\5\60\31\2\u0099\u0098\3"+
		"\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u00c7\7#\2\2\u009c"+
		"\u009d\7\t\2\2\u009d\u00a1\7\23\2\2\u009e\u00a0\5\34\17\2\u009f\u009e"+
		"\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2"+
		"\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a4\u00a6\5&\24\2\u00a5\u00a4\3\2"+
		"\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a9\5\62\32\2\u00a8"+
		"\u00a7\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00ac\5 "+
		"\21\2\u00ab\u00aa\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad"+
		"\u00af\5.\30\2\u00ae\u00ad\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\3\2"+
		"\2\2\u00b0\u00b2\5(\25\2\u00b1\u00b0\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2"+
		"\u00b4\3\2\2\2\u00b3\u00b5\5$\23\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b8\5\"\22\2\u00b7\u00b6\3\2\2\2\u00b7"+
		"\u00b8\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00bb\5\36\20\2\u00ba\u00b9\3"+
		"\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00be\5\60\31\2\u00bd"+
		"\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\7!"+
		"\2\2\u00c0\u00c1\5\26\f\2\u00c1\u00c2\7\t\2\2\u00c2\u00c3\7$\2\2\u00c3"+
		"\u00c4\7\23\2\2\u00c4\u00c5\7!\2\2\u00c5\u00c7\3\2\2\2\u00c6x\3\2\2\2"+
		"\u00c6\u009c\3\2\2\2\u00c7\t\3\2\2\2\u00c8\u00c9\7\t\2\2\u00c9\u00cd\7"+
		"\24\2\2\u00ca\u00cc\5\34\17\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf\3\2\2\2"+
		"\u00cd\u00cb\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d1\3\2\2\2\u00cf\u00cd"+
		"\3\2\2\2\u00d0\u00d2\5&\24\2\u00d1\u00d0\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2"+
		"\u00d4\3\2\2\2\u00d3\u00d5\5 \21\2\u00d4\u00d3\3\2\2\2\u00d4\u00d5\3\2"+
		"\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d8\5.\30\2\u00d7\u00d6\3\2\2\2\u00d7"+
		"\u00d8\3\2\2\2\u00d8\u00da\3\2\2\2\u00d9\u00db\5(\25\2\u00da\u00d9\3\2"+
		"\2\2\u00da\u00db\3\2\2\2\u00db\u00dd\3\2\2\2\u00dc\u00de\5*\26\2\u00dd"+
		"\u00dc\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df\u00e1\5$"+
		"\23\2\u00e0\u00df\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2"+
		"\u00e4\5\"\22\2\u00e3\u00e2\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e6\3"+
		"\2\2\2\u00e5\u00e7\5\36\20\2\u00e6\u00e5\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e9\3\2\2\2\u00e8\u00ea\5,\27\2\u00e9\u00e8\3\2\2\2\u00e9\u00ea\3\2"+
		"\2\2\u00ea\u00ec\3\2\2\2\u00eb\u00ed\5\60\31\2\u00ec\u00eb\3\2\2\2\u00ec"+
		"\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u011d\7#\2\2\u00ef\u00f0\7\t"+
		"\2\2\u00f0\u00f4\7\24\2\2\u00f1\u00f3\5\34\17\2\u00f2\u00f1\3\2\2\2\u00f3"+
		"\u00f6\3\2\2\2\u00f4\u00f2\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f8\3\2"+
		"\2\2\u00f6\u00f4\3\2\2\2\u00f7\u00f9\5&\24\2\u00f8\u00f7\3\2\2\2\u00f8"+
		"\u00f9\3\2\2\2\u00f9\u00fb\3\2\2\2\u00fa\u00fc\5 \21\2\u00fb\u00fa\3\2"+
		"\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd\u00ff\5.\30\2\u00fe"+
		"\u00fd\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\3\2\2\2\u0100\u0102\5("+
		"\25\2\u0101\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0104\3\2\2\2\u0103"+
		"\u0105\5*\26\2\u0104\u0103\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0107\3\2"+
		"\2\2\u0106\u0108\5$\23\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108"+
		"\u010a\3\2\2\2\u0109\u010b\5\"\22\2\u010a\u0109\3\2\2\2\u010a\u010b\3"+
		"\2\2\2\u010b\u010d\3\2\2\2\u010c\u010e\5\36\20\2\u010d\u010c\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\u0110\3\2\2\2\u010f\u0111\5,\27\2\u0110\u010f\3\2"+
		"\2\2\u0110\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0114\5\60\31\2\u0113"+
		"\u0112\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\7!"+
		"\2\2\u0116\u0117\5\26\f\2\u0117\u0118\7\t\2\2\u0118\u0119\7$\2\2\u0119"+
		"\u011a\7\24\2\2\u011a\u011b\7!\2\2\u011b\u011d\3\2\2\2\u011c\u00c8\3\2"+
		"\2\2\u011c\u00ef\3\2\2\2\u011d\13\3\2\2\2\u011e\u011f\7\t\2\2\u011f\u0123"+
		"\7\16\2\2\u0120\u0122\5\34\17\2\u0121\u0120\3\2\2\2\u0122\u0125\3\2\2"+
		"\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0127\3\2\2\2\u0125\u0123"+
		"\3\2\2\2\u0126\u0128\5$\23\2\u0127\u0126\3\2\2\2\u0127\u0128\3\2\2\2\u0128"+
		"\u012a\3\2\2\2\u0129\u012b\5\"\22\2\u012a\u0129\3\2\2\2\u012a\u012b\3"+
		"\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\7!\2\2\u012d\u012e\5\26\f\2\u012e"+
		"\u012f\7\t\2\2\u012f\u0130\7$\2\2\u0130\u0131\7\16\2\2\u0131\u0132\7!"+
		"\2\2\u0132\r\3\2\2\2\u0133\u0134\7\t\2\2\u0134\u0138\7\r\2\2\u0135\u0137"+
		"\5\34\17\2\u0136\u0135\3\2\2\2\u0137\u013a\3\2\2\2\u0138\u0136\3\2\2\2"+
		"\u0138\u0139\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013d"+
		"\5&\24\2\u013c\u013b\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013f\3\2\2\2\u013e"+
		"\u0140\5.\30\2\u013f\u013e\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0142\3\2"+
		"\2\2\u0141\u0143\5(\25\2\u0142\u0141\3\2\2\2\u0142\u0143\3\2\2\2\u0143"+
		"\u0145\3\2\2\2\u0144\u0146\5$\23\2\u0145\u0144\3\2\2\2\u0145\u0146\3\2"+
		"\2\2\u0146\u0148\3\2\2\2\u0147\u0149\5\"\22\2\u0148\u0147\3\2\2\2\u0148"+
		"\u0149\3\2\2\2\u0149\u014b\3\2\2\2\u014a\u014c\5\64\33\2\u014b\u014a\3"+
		"\2\2\2\u014b\u014c\3\2\2\2\u014c\u0150\3\2\2\2\u014d\u014f\5\34\17\2\u014e"+
		"\u014d\3\2\2\2\u014f\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2"+
		"\2\2\u0151\u0153\3\2\2\2\u0152\u0150\3\2\2\2\u0153\u017c\7#\2\2\u0154"+
		"\u0155\7\t\2\2\u0155\u0159\7\r\2\2\u0156\u0158\5\34\17\2\u0157\u0156\3"+
		"\2\2\2\u0158\u015b\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a"+
		"\u015d\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u015e\5&\24\2\u015d\u015c\3\2"+
		"\2\2\u015d\u015e\3\2\2\2\u015e\u0160\3\2\2\2\u015f\u0161\5.\30\2\u0160"+
		"\u015f\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0163\3\2\2\2\u0162\u0164\5("+
		"\25\2\u0163\u0162\3\2\2\2\u0163\u0164\3\2\2\2\u0164\u0166\3\2\2\2\u0165"+
		"\u0167\5$\23\2\u0166\u0165\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0169\3\2"+
		"\2\2\u0168\u016a\5\"\22\2\u0169\u0168\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"\u016c\3\2\2\2\u016b\u016d\5\64\33\2\u016c\u016b\3\2\2\2\u016c\u016d\3"+
		"\2\2\2\u016d\u0171\3\2\2\2\u016e\u0170\5\34\17\2\u016f\u016e\3\2\2\2\u0170"+
		"\u0173\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0174\3\2"+
		"\2\2\u0173\u0171\3\2\2\2\u0174\u0175\7!\2\2\u0175\u0176\5\26\f\2\u0176"+
		"\u0177\7\t\2\2\u0177\u0178\7$\2\2\u0178\u0179\7\r\2\2\u0179\u017a\7!\2"+
		"\2\u017a\u017c\3\2\2\2\u017b\u0133\3\2\2\2\u017b\u0154\3\2\2\2\u017c\17"+
		"\3\2\2\2\u017d\u017e\7\t\2\2\u017e\u0180\7\17\2\2\u017f\u0181\5\36\20"+
		"\2\u0180\u017f\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0183\3\2\2\2\u0182\u0184"+
		"\5\60\31\2\u0183\u0182\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0185\3\2\2\2"+
		"\u0185\u0196\7#\2\2\u0186\u0187\7\t\2\2\u0187\u0189\7\17\2\2\u0188\u018a"+
		"\5\36\20\2\u0189\u0188\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018c\3\2\2\2"+
		"\u018b\u018d\5\60\31\2\u018c\u018b\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u018e"+
		"\3\2\2\2\u018e\u018f\7!\2\2\u018f\u0190\5\26\f\2\u0190\u0191\7\t\2\2\u0191"+
		"\u0192\7$\2\2\u0192\u0193\7\17\2\2\u0193\u0194\7!\2\2\u0194\u0196\3\2"+
		"\2\2\u0195\u017d\3\2\2\2\u0195\u0186\3\2\2\2\u0196\21\3\2\2\2\u0197\u0198"+
		"\7\t\2\2\u0198\u019c\7\20\2\2\u0199\u019b\5\34\17\2\u019a\u0199\3\2\2"+
		"\2\u019b\u019e\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019f"+
		"\3\2\2\2\u019e\u019c\3\2\2\2\u019f\u01b0\7#\2\2\u01a0\u01a1\7\t\2\2\u01a1"+
		"\u01a5\7\20\2\2\u01a2\u01a4\5\34\17\2\u01a3\u01a2\3\2\2\2\u01a4\u01a7"+
		"\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a8\3\2\2\2\u01a7"+
		"\u01a5\3\2\2\2\u01a8\u01a9\7!\2\2\u01a9\u01aa\5\26\f\2\u01aa\u01ab\7\t"+
		"\2\2\u01ab\u01ac\7$\2\2\u01ac\u01ad\7\20\2\2\u01ad\u01ae\7!\2\2\u01ae"+
		"\u01b0\3\2\2\2\u01af\u0197\3\2\2\2\u01af\u01a0\3\2\2\2\u01b0\23\3\2\2"+
		"\2\u01b1\u01b2\7\t\2\2\u01b2\u01b6\7\21\2\2\u01b3\u01b5\5\34\17\2\u01b4"+
		"\u01b3\3\2\2\2\u01b5\u01b8\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b6\u01b7\3\2"+
		"\2\2\u01b7\u01b9\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b9\u01ca\7#\2\2\u01ba"+
		"\u01bb\7\t\2\2\u01bb\u01bf\7\21\2\2\u01bc\u01be\5\34\17\2\u01bd\u01bc"+
		"\3\2\2\2\u01be\u01c1\3\2\2\2\u01bf\u01bd\3\2\2\2\u01bf\u01c0\3\2\2\2\u01c0"+
		"\u01c2\3\2\2\2\u01c1\u01bf\3\2\2\2\u01c2\u01c3\7!\2\2\u01c3\u01c4\5\26"+
		"\f\2\u01c4\u01c5\7\t\2\2\u01c5\u01c6\7$\2\2\u01c6\u01c7\7\21\2\2\u01c7"+
		"\u01c8\7!\2\2\u01c8\u01ca\3\2\2\2\u01c9\u01b1\3\2\2\2\u01c9\u01ba\3\2"+
		"\2\2\u01ca\25\3\2\2\2\u01cb\u01cd\5\66\34\2\u01cc\u01cb\3\2\2\2\u01cc"+
		"\u01cd\3\2\2\2\u01cd\u01e2\3\2\2\2\u01ce\u01dc\5\30\r\2\u01cf\u01dc\5"+
		"\6\4\2\u01d0\u01dc\5\b\5\2\u01d1\u01dc\5\16\b\2\u01d2\u01dc\5\f\7\2\u01d3"+
		"\u01dc\5\20\t\2\u01d4\u01dc\5\22\n\2\u01d5\u01dc\5\24\13\2\u01d6\u01dc"+
		"\5\32\16\2\u01d7\u01dc\5\n\6\2\u01d8\u01dc\7\4\2\2\u01d9\u01dc\7)\2\2"+
		"\u01da\u01dc\7\3\2\2\u01db\u01ce\3\2\2\2\u01db\u01cf\3\2\2\2\u01db\u01d0"+
		"\3\2\2\2\u01db\u01d1\3\2\2\2\u01db\u01d2\3\2\2\2\u01db\u01d3\3\2\2\2\u01db"+
		"\u01d4\3\2\2\2\u01db\u01d5\3\2\2\2\u01db\u01d6\3\2\2\2\u01db\u01d7\3\2"+
		"\2\2\u01db\u01d8\3\2\2\2\u01db\u01d9\3\2\2\2\u01db\u01da\3\2\2\2\u01dc"+
		"\u01de\3\2\2\2\u01dd\u01df\5\66\34\2\u01de\u01dd\3\2\2\2\u01de\u01df\3"+
		"\2\2\2\u01df\u01e1\3\2\2\2\u01e0\u01db\3\2\2\2\u01e1\u01e4\3\2\2\2\u01e2"+
		"\u01e0\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\27\3\2\2\2\u01e4\u01e2\3\2\2"+
		"\2\u01e5\u01e6\7\t\2\2\u01e6\u01e7\7\'\2\2\u01e7\u01e8\7!\2\2\u01e8\u01e9"+
		"\5\26\f\2\u01e9\u01ea\7\t\2\2\u01ea\u01eb\7$\2\2\u01eb\u01ec\7\'\2\2\u01ec"+
		"\u01ed\7!\2\2\u01ed\u020a\3\2\2\2\u01ee\u01ef\7\t\2\2\u01ef\u01f0\7\'"+
		"\2\2\u01f0\u020a\7#\2\2\u01f1\u01f2\7\t\2\2\u01f2\u01f6\7\'\2\2\u01f3"+
		"\u01f5\5\34\17\2\u01f4\u01f3\3\2\2\2\u01f5\u01f8\3\2\2\2\u01f6\u01f4\3"+
		"\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f9\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f9"+
		"\u01fa\7!\2\2\u01fa\u01fb\5\26\f\2\u01fb\u01fc\7\t\2\2\u01fc\u01fd\7$"+
		"\2\2\u01fd\u01fe\7\'\2\2\u01fe\u01ff\7!\2\2\u01ff\u020a\3\2\2\2\u0200"+
		"\u0201\7\t\2\2\u0201\u0205\7\'\2\2\u0202\u0204\5\34\17\2\u0203\u0202\3"+
		"\2\2\2\u0204\u0207\3\2\2\2\u0205\u0203\3\2\2\2\u0205\u0206\3\2\2\2\u0206"+
		"\u0208\3\2\2\2\u0207\u0205\3\2\2\2\u0208\u020a\7#\2\2\u0209\u01e5\3\2"+
		"\2\2\u0209\u01ee\3\2\2\2\u0209\u01f1\3\2\2\2\u0209\u0200\3\2\2\2\u020a"+
		"\31\3\2\2\2\u020b\u020c\t\2\2\2\u020c\33\3\2\2\2\u020d\u020e\7\'\2\2\u020e"+
		"\u020f\7%\2\2\u020f\u0210\7&\2\2\u0210\35\3\2\2\2\u0211\u0212\7\26\2\2"+
		"\u0212\u0213\7&\2\2\u0213\37\3\2\2\2\u0214\u0215\7\27\2\2\u0215\u0216"+
		"\7&\2\2\u0216!\3\2\2\2\u0217\u0218\7\30\2\2\u0218\u0219\7&\2\2\u0219#"+
		"\3\2\2\2\u021a\u021b\7\31\2\2\u021b\u021c\7&\2\2\u021c%\3\2\2\2\u021d"+
		"\u021e\7\32\2\2\u021e\u021f\7&\2\2\u021f\'\3\2\2\2\u0220\u0221\7\33\2"+
		"\2\u0221\u0222\7&\2\2\u0222)\3\2\2\2\u0223\u0224\7\34\2\2\u0224\u0225"+
		"\7&\2\2\u0225+\3\2\2\2\u0226\u0227\7\35\2\2\u0227\u0228\7&\2\2\u0228-"+
		"\3\2\2\2\u0229\u022a\7\36\2\2\u022a\u022b\7&\2\2\u022b/\3\2\2\2\u022c"+
		"\u022d\7\37\2\2\u022d\u022e\7&\2\2\u022e\61\3\2\2\2\u022f\u0230\7 \2\2"+
		"\u0230\u0231\7&\2\2\u0231\63\3\2\2\2\u0232\u0233\7\25\2\2\u0233\u0234"+
		"\7&\2\2\u0234\65\3\2\2\2\u0235\u0236\t\3\2\2\u0236\67\3\2\2\2\u0237\u0238"+
		"\t\4\2\2\u02389\3\2\2\2]>CFMQVZ_n}\u0081\u0084\u0087\u008a\u008d\u0090"+
		"\u0093\u0096\u0099\u00a1\u00a5\u00a8\u00ab\u00ae\u00b1\u00b4\u00b7\u00ba"+
		"\u00bd\u00c6\u00cd\u00d1\u00d4\u00d7\u00da\u00dd\u00e0\u00e3\u00e6\u00e9"+
		"\u00ec\u00f4\u00f8\u00fb\u00fe\u0101\u0104\u0107\u010a\u010d\u0110\u0113"+
		"\u011c\u0123\u0127\u012a\u0138\u013c\u013f\u0142\u0145\u0148\u014b\u0150"+
		"\u0159\u015d\u0160\u0163\u0166\u0169\u016c\u0171\u017b\u0180\u0183\u0189"+
		"\u018c\u0195\u019c\u01a5\u01af\u01b6\u01bf\u01c9\u01cc\u01db\u01de\u01e2"+
		"\u01f6\u0205\u0209";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}