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
		TEXT=9, ASSERT=10, ATTRIBUTE=11, CHOICE=12, CONFATTR=13, CONTAINSATTR=14, 
		DESC=15, ELEMENT=16, IDATTR=17, INCLUDE=18, LET=19, MANDATTR=20, MAXOCCURSATTR=21, 
		MINOCCURSATTR=22, NAMEATTR=23, OPTATTR=24, PROHIBITED=25, REFATTR=26, 
		REPORT=27, STRENGTHATTR=28, TEMPLATE=29, TYPEATTR=30, VALUEATTR=31, CLOSE=32, 
		SPECIAL_CLOSE=33, SLASH_CLOSE=34, SLASH=35, EQUALS=36, AttrValue=37, Name=38, 
		S=39, PI=40;
	public static final int
		RULE_prolog = 0, RULE_template = 1, RULE_desc = 2, RULE_element = 3, RULE_attribute = 4, 
		RULE_choice = 5, RULE_include = 6, RULE_letter = 7, RULE_assertion = 8, 
		RULE_report = 9, RULE_content = 10, RULE_xmlelement = 11, RULE_reference = 12, 
		RULE_attr = 13, RULE_conformanceAttr = 14, RULE_containsAttr = 15, RULE_dataTypeAttr = 16, 
		RULE_idAttr = 17, RULE_isMandatoryAttr = 18, RULE_isOptionalAttr = 19, 
		RULE_maxOccursAttr = 20, RULE_minOccursAttr = 21, RULE_nameAttr = 22, 
		RULE_prohibitedAttr = 23, RULE_refAttr = 24, RULE_strengthAttr = 25, RULE_valueAttr = 26, 
		RULE_chardata = 27, RULE_misc = 28;
	public static final String[] ruleNames = {
		"prolog", "template", "desc", "element", "attribute", "choice", "include", 
		"letter", "assertion", "report", "content", "xmlelement", "reference", 
		"attr", "conformanceAttr", "containsAttr", "dataTypeAttr", "idAttr", "isMandatoryAttr", 
		"isOptionalAttr", "maxOccursAttr", "minOccursAttr", "nameAttr", "prohibitedAttr", 
		"refAttr", "strengthAttr", "valueAttr", "chardata", "misc"
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
			setState(58);
			match(XMLDeclOpen);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(59);
				attr();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			match(SPECIAL_CLOSE);
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEA_WS) {
				{
				setState(66);
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
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==XMLDeclOpen) {
				{
				setState(69);
				prolog();
				}
			}

			setState(72);
			match(OPEN);
			setState(73);
			match(TEMPLATE);
			setState(77);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(74);
					attr();
					}
					} 
				}
				setState(79);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(81);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDATTR) {
				{
				setState(80);
				idAttr();
				}
			}

			setState(86);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(83);
					attr();
					}
					} 
				}
				setState(88);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAMEATTR) {
				{
				setState(89);
				nameAttr();
				}
			}

			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(92);
				attr();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98);
			match(CLOSE);
			setState(99);
			content();
			setState(100);
			match(OPEN);
			setState(101);
			match(SLASH);
			setState(102);
			match(TEMPLATE);
			setState(103);
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
			setState(105);
			match(OPEN);
			setState(106);
			match(DESC);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(107);
				attr();
				}
				}
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(113);
			match(CLOSE);
			setState(114);
			content();
			setState(115);
			match(OPEN);
			setState(116);
			match(SLASH);
			setState(117);
			match(DESC);
			setState(118);
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
			setState(204);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(120);
				match(OPEN);
				setState(121);
				match(ELEMENT);
				setState(125);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(122);
					attr();
					}
					}
					setState(127);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(128);
					conformanceAttr();
					}
				}

				setState(132);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONTAINSATTR) {
					{
					setState(131);
					containsAttr();
					}
				}

				setState(135);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(134);
					dataTypeAttr();
					}
				}

				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(137);
					idAttr();
					}
				}

				setState(141);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(140);
					isMandatoryAttr();
					}
				}

				setState(144);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(143);
					maxOccursAttr();
					}
				}

				setState(147);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(146);
					minOccursAttr();
					}
				}

				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(149);
					nameAttr();
					}
				}

				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(152);
					valueAttr();
					}
				}

				setState(156);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRENGTHATTR) {
					{
					setState(155);
					strengthAttr();
					}
				}

				setState(158);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(OPEN);
				setState(160);
				match(ELEMENT);
				setState(164);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(161);
					attr();
					}
					}
					setState(166);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(168);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(167);
					conformanceAttr();
					}
				}

				setState(171);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONTAINSATTR) {
					{
					setState(170);
					containsAttr();
					}
				}

				setState(174);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(173);
					dataTypeAttr();
					}
				}

				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(176);
					idAttr();
					}
				}

				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(179);
					isMandatoryAttr();
					}
				}

				setState(183);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(182);
					maxOccursAttr();
					}
				}

				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(185);
					minOccursAttr();
					}
				}

				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(188);
					nameAttr();
					}
				}

				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(191);
					valueAttr();
					}
				}

				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==STRENGTHATTR) {
					{
					setState(194);
					strengthAttr();
					}
				}

				setState(197);
				match(CLOSE);
				setState(198);
				content();
				setState(199);
				match(OPEN);
				setState(200);
				match(SLASH);
				setState(201);
				match(ELEMENT);
				setState(202);
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
			setState(290);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(206);
				match(OPEN);
				setState(207);
				match(ATTRIBUTE);
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(208);
					attr();
					}
					}
					setState(213);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(214);
					conformanceAttr();
					}
				}

				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(217);
					dataTypeAttr();
					}
				}

				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(220);
					idAttr();
					}
				}

				setState(224);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(223);
					isMandatoryAttr();
					}
				}

				setState(227);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(226);
					isOptionalAttr();
					}
				}

				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(229);
					maxOccursAttr();
					}
				}

				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(232);
					minOccursAttr();
					}
				}

				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(235);
					nameAttr();
					}
				}

				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(238);
					prohibitedAttr();
					}
				}

				setState(242);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(241);
					valueAttr();
					}
				}

				setState(244);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				match(OPEN);
				setState(246);
				match(ATTRIBUTE);
				setState(250);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(247);
					attr();
					}
					}
					setState(252);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(253);
					conformanceAttr();
					}
				}

				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TYPEATTR) {
					{
					setState(256);
					dataTypeAttr();
					}
				}

				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(259);
					idAttr();
					}
				}

				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(262);
					isMandatoryAttr();
					}
				}

				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OPTATTR) {
					{
					setState(265);
					isOptionalAttr();
					}
				}

				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(268);
					maxOccursAttr();
					}
				}

				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(271);
					minOccursAttr();
					}
				}

				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(274);
					nameAttr();
					}
				}

				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==PROHIBITED) {
					{
					setState(277);
					prohibitedAttr();
					}
				}

				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(280);
					valueAttr();
					}
				}

				setState(283);
				match(CLOSE);
				setState(284);
				content();
				setState(285);
				match(OPEN);
				setState(286);
				match(SLASH);
				setState(287);
				match(ATTRIBUTE);
				setState(288);
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
			setState(292);
			match(OPEN);
			setState(293);
			match(CHOICE);
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Name) {
				{
				{
				setState(294);
				attr();
				}
				}
				setState(299);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MAXOCCURSATTR) {
				{
				setState(300);
				maxOccursAttr();
				}
			}

			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MINOCCURSATTR) {
				{
				setState(303);
				minOccursAttr();
				}
			}

			setState(306);
			match(CLOSE);
			setState(307);
			content();
			setState(308);
			match(OPEN);
			setState(309);
			match(SLASH);
			setState(310);
			match(CHOICE);
			setState(311);
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
			setState(385);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(313);
				match(OPEN);
				setState(314);
				match(INCLUDE);
				setState(318);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(315);
						attr();
						}
						} 
					}
					setState(320);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				}
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(321);
					conformanceAttr();
					}
				}

				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(324);
					idAttr();
					}
				}

				setState(328);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(327);
					isMandatoryAttr();
					}
				}

				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(330);
					maxOccursAttr();
					}
				}

				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(333);
					minOccursAttr();
					}
				}

				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(336);
					refAttr();
					}
				}

				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(339);
					attr();
					}
					}
					setState(344);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(345);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(346);
				match(OPEN);
				setState(347);
				match(INCLUDE);
				setState(351);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(348);
						attr();
						}
						} 
					}
					setState(353);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				}
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==CONFATTR) {
					{
					setState(354);
					conformanceAttr();
					}
				}

				setState(358);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==IDATTR) {
					{
					setState(357);
					idAttr();
					}
				}

				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MANDATTR) {
					{
					setState(360);
					isMandatoryAttr();
					}
				}

				setState(364);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MAXOCCURSATTR) {
					{
					setState(363);
					maxOccursAttr();
					}
				}

				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINOCCURSATTR) {
					{
					setState(366);
					minOccursAttr();
					}
				}

				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REFATTR) {
					{
					setState(369);
					refAttr();
					}
				}

				setState(375);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(372);
					attr();
					}
					}
					setState(377);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(378);
				match(CLOSE);
				setState(379);
				content();
				setState(380);
				match(OPEN);
				setState(381);
				match(SLASH);
				setState(382);
				match(INCLUDE);
				setState(383);
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
			setState(411);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,79,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				match(OPEN);
				setState(388);
				match(LET);
				setState(390);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(389);
					nameAttr();
					}
				}

				setState(393);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(392);
					valueAttr();
					}
				}

				setState(395);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(396);
				match(OPEN);
				setState(397);
				match(LET);
				setState(399);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NAMEATTR) {
					{
					setState(398);
					nameAttr();
					}
				}

				setState(402);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==VALUEATTR) {
					{
					setState(401);
					valueAttr();
					}
				}

				setState(404);
				match(CLOSE);
				setState(405);
				content();
				setState(406);
				match(OPEN);
				setState(407);
				match(SLASH);
				setState(408);
				match(LET);
				setState(409);
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
			setState(437);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(413);
				match(OPEN);
				setState(414);
				match(ASSERT);
				setState(418);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(415);
					attr();
					}
					}
					setState(420);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(421);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(422);
				match(OPEN);
				setState(423);
				match(ASSERT);
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
				match(CLOSE);
				setState(431);
				content();
				setState(432);
				match(OPEN);
				setState(433);
				match(SLASH);
				setState(434);
				match(ASSERT);
				setState(435);
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
			setState(463);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,85,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(439);
				match(OPEN);
				setState(440);
				match(REPORT);
				setState(444);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(441);
					attr();
					}
					}
					setState(446);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(447);
				match(SLASH_CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(448);
				match(OPEN);
				setState(449);
				match(REPORT);
				setState(453);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(450);
					attr();
					}
					}
					setState(455);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(456);
				match(CLOSE);
				setState(457);
				content();
				setState(458);
				match(OPEN);
				setState(459);
				match(SLASH);
				setState(460);
				match(REPORT);
				setState(461);
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
			setState(466);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SEA_WS || _la==TEXT) {
				{
				setState(465);
				chardata();
				}
			}

			setState(488);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(481);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
					case 1:
						{
						setState(468);
						xmlelement();
						}
						break;
					case 2:
						{
						setState(469);
						desc();
						}
						break;
					case 3:
						{
						setState(470);
						element();
						}
						break;
					case 4:
						{
						setState(471);
						include();
						}
						break;
					case 5:
						{
						setState(472);
						choice();
						}
						break;
					case 6:
						{
						setState(473);
						letter();
						}
						break;
					case 7:
						{
						setState(474);
						assertion();
						}
						break;
					case 8:
						{
						setState(475);
						report();
						}
						break;
					case 9:
						{
						setState(476);
						reference();
						}
						break;
					case 10:
						{
						setState(477);
						attribute();
						}
						break;
					case 11:
						{
						setState(478);
						match(CDATA);
						}
						break;
					case 12:
						{
						setState(479);
						match(PI);
						}
						break;
					case 13:
						{
						setState(480);
						match(COMMENT);
						}
						break;
					}
					setState(484);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SEA_WS || _la==TEXT) {
						{
						setState(483);
						chardata();
						}
					}

					}
					} 
				}
				setState(490);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
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
			setState(527);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,92,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(491);
				match(OPEN);
				setState(492);
				match(Name);
				setState(493);
				match(CLOSE);
				setState(494);
				content();
				setState(495);
				match(OPEN);
				setState(496);
				match(SLASH);
				setState(497);
				match(Name);
				setState(498);
				match(CLOSE);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(500);
				match(OPEN);
				setState(501);
				match(Name);
				setState(502);
				match(SLASH_CLOSE);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(503);
				match(OPEN);
				setState(504);
				match(Name);
				setState(508);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(505);
					attr();
					}
					}
					setState(510);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(511);
				match(CLOSE);
				setState(512);
				content();
				setState(513);
				match(OPEN);
				setState(514);
				match(SLASH);
				setState(515);
				match(Name);
				setState(516);
				match(CLOSE);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(518);
				match(OPEN);
				setState(519);
				match(Name);
				setState(523);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Name) {
					{
					{
					setState(520);
					attr();
					}
					}
					setState(525);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(526);
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
			setState(529);
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
			setState(531);
			match(Name);
			setState(532);
			match(EQUALS);
			setState(533);
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
		enterRule(_localctx, 28, RULE_conformanceAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			match(CONFATTR);
			setState(536);
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
		enterRule(_localctx, 30, RULE_containsAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(538);
			match(CONTAINSATTR);
			setState(539);
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
		enterRule(_localctx, 32, RULE_dataTypeAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
			match(TYPEATTR);
			setState(542);
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
		enterRule(_localctx, 34, RULE_idAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(544);
			match(IDATTR);
			setState(545);
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
		enterRule(_localctx, 36, RULE_isMandatoryAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(547);
			match(MANDATTR);
			setState(548);
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
		enterRule(_localctx, 38, RULE_isOptionalAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			match(OPTATTR);
			setState(551);
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
		enterRule(_localctx, 40, RULE_maxOccursAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(553);
			match(MAXOCCURSATTR);
			setState(554);
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
		enterRule(_localctx, 42, RULE_minOccursAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(556);
			match(MINOCCURSATTR);
			setState(557);
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
		enterRule(_localctx, 44, RULE_nameAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(559);
			match(NAMEATTR);
			setState(560);
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
		enterRule(_localctx, 46, RULE_prohibitedAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(562);
			match(PROHIBITED);
			setState(563);
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
		enterRule(_localctx, 48, RULE_refAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
			match(REFATTR);
			setState(566);
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
		enterRule(_localctx, 50, RULE_strengthAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(568);
			match(STRENGTHATTR);
			setState(569);
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
		enterRule(_localctx, 52, RULE_valueAttr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571);
			match(VALUEATTR);
			setState(572);
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
		enterRule(_localctx, 54, RULE_chardata);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(574);
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
		enterRule(_localctx, 56, RULE_misc);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(576);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u0245\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\7\2?\n\2\f"+
		"\2\16\2B\13\2\3\2\3\2\5\2F\n\2\3\3\5\3I\n\3\3\3\3\3\3\3\7\3N\n\3\f\3\16"+
		"\3Q\13\3\3\3\5\3T\n\3\3\3\7\3W\n\3\f\3\16\3Z\13\3\3\3\5\3]\n\3\3\3\7\3"+
		"`\n\3\f\3\16\3c\13\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\7\4o\n\4"+
		"\f\4\16\4r\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5~\n\5\f\5\16"+
		"\5\u0081\13\5\3\5\5\5\u0084\n\5\3\5\5\5\u0087\n\5\3\5\5\5\u008a\n\5\3"+
		"\5\5\5\u008d\n\5\3\5\5\5\u0090\n\5\3\5\5\5\u0093\n\5\3\5\5\5\u0096\n\5"+
		"\3\5\5\5\u0099\n\5\3\5\5\5\u009c\n\5\3\5\5\5\u009f\n\5\3\5\3\5\3\5\3\5"+
		"\7\5\u00a5\n\5\f\5\16\5\u00a8\13\5\3\5\5\5\u00ab\n\5\3\5\5\5\u00ae\n\5"+
		"\3\5\5\5\u00b1\n\5\3\5\5\5\u00b4\n\5\3\5\5\5\u00b7\n\5\3\5\5\5\u00ba\n"+
		"\5\3\5\5\5\u00bd\n\5\3\5\5\5\u00c0\n\5\3\5\5\5\u00c3\n\5\3\5\5\5\u00c6"+
		"\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00cf\n\5\3\6\3\6\3\6\7\6\u00d4\n"+
		"\6\f\6\16\6\u00d7\13\6\3\6\5\6\u00da\n\6\3\6\5\6\u00dd\n\6\3\6\5\6\u00e0"+
		"\n\6\3\6\5\6\u00e3\n\6\3\6\5\6\u00e6\n\6\3\6\5\6\u00e9\n\6\3\6\5\6\u00ec"+
		"\n\6\3\6\5\6\u00ef\n\6\3\6\5\6\u00f2\n\6\3\6\5\6\u00f5\n\6\3\6\3\6\3\6"+
		"\3\6\7\6\u00fb\n\6\f\6\16\6\u00fe\13\6\3\6\5\6\u0101\n\6\3\6\5\6\u0104"+
		"\n\6\3\6\5\6\u0107\n\6\3\6\5\6\u010a\n\6\3\6\5\6\u010d\n\6\3\6\5\6\u0110"+
		"\n\6\3\6\5\6\u0113\n\6\3\6\5\6\u0116\n\6\3\6\5\6\u0119\n\6\3\6\5\6\u011c"+
		"\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u0125\n\6\3\7\3\7\3\7\7\7\u012a\n"+
		"\7\f\7\16\7\u012d\13\7\3\7\5\7\u0130\n\7\3\7\5\7\u0133\n\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\b\3\b\3\b\7\b\u013f\n\b\f\b\16\b\u0142\13\b\3\b\5\b"+
		"\u0145\n\b\3\b\5\b\u0148\n\b\3\b\5\b\u014b\n\b\3\b\5\b\u014e\n\b\3\b\5"+
		"\b\u0151\n\b\3\b\5\b\u0154\n\b\3\b\7\b\u0157\n\b\f\b\16\b\u015a\13\b\3"+
		"\b\3\b\3\b\3\b\7\b\u0160\n\b\f\b\16\b\u0163\13\b\3\b\5\b\u0166\n\b\3\b"+
		"\5\b\u0169\n\b\3\b\5\b\u016c\n\b\3\b\5\b\u016f\n\b\3\b\5\b\u0172\n\b\3"+
		"\b\5\b\u0175\n\b\3\b\7\b\u0178\n\b\f\b\16\b\u017b\13\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\5\b\u0184\n\b\3\t\3\t\3\t\5\t\u0189\n\t\3\t\5\t\u018c\n\t"+
		"\3\t\3\t\3\t\3\t\5\t\u0192\n\t\3\t\5\t\u0195\n\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\5\t\u019e\n\t\3\n\3\n\3\n\7\n\u01a3\n\n\f\n\16\n\u01a6\13\n\3\n"+
		"\3\n\3\n\3\n\7\n\u01ac\n\n\f\n\16\n\u01af\13\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\5\n\u01b8\n\n\3\13\3\13\3\13\7\13\u01bd\n\13\f\13\16\13\u01c0\13"+
		"\13\3\13\3\13\3\13\3\13\7\13\u01c6\n\13\f\13\16\13\u01c9\13\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\5\13\u01d2\n\13\3\f\5\f\u01d5\n\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u01e4\n\f\3\f\5\f\u01e7"+
		"\n\f\7\f\u01e9\n\f\f\f\16\f\u01ec\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u01fd\n\r\f\r\16\r\u0200\13\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u020c\n\r\f\r\16\r\u020f\13\r\3\r"+
		"\5\r\u0212\n\r\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36\2\2\37\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\5\3\2\6\7\4\2"+
		"\b\b\13\13\5\2\3\3\b\b**\2\u0291\2<\3\2\2\2\4H\3\2\2\2\6k\3\2\2\2\b\u00ce"+
		"\3\2\2\2\n\u0124\3\2\2\2\f\u0126\3\2\2\2\16\u0183\3\2\2\2\20\u019d\3\2"+
		"\2\2\22\u01b7\3\2\2\2\24\u01d1\3\2\2\2\26\u01d4\3\2\2\2\30\u0211\3\2\2"+
		"\2\32\u0213\3\2\2\2\34\u0215\3\2\2\2\36\u0219\3\2\2\2 \u021c\3\2\2\2\""+
		"\u021f\3\2\2\2$\u0222\3\2\2\2&\u0225\3\2\2\2(\u0228\3\2\2\2*\u022b\3\2"+
		"\2\2,\u022e\3\2\2\2.\u0231\3\2\2\2\60\u0234\3\2\2\2\62\u0237\3\2\2\2\64"+
		"\u023a\3\2\2\2\66\u023d\3\2\2\28\u0240\3\2\2\2:\u0242\3\2\2\2<@\7\n\2"+
		"\2=?\5\34\17\2>=\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2"+
		"\2\2CE\7#\2\2DF\7\b\2\2ED\3\2\2\2EF\3\2\2\2F\3\3\2\2\2GI\5\2\2\2HG\3\2"+
		"\2\2HI\3\2\2\2IJ\3\2\2\2JK\7\t\2\2KO\7\37\2\2LN\5\34\17\2ML\3\2\2\2NQ"+
		"\3\2\2\2OM\3\2\2\2OP\3\2\2\2PS\3\2\2\2QO\3\2\2\2RT\5$\23\2SR\3\2\2\2S"+
		"T\3\2\2\2TX\3\2\2\2UW\5\34\17\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2"+
		"\2Y\\\3\2\2\2ZX\3\2\2\2[]\5.\30\2\\[\3\2\2\2\\]\3\2\2\2]a\3\2\2\2^`\5"+
		"\34\17\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2\2\2d"+
		"e\7\"\2\2ef\5\26\f\2fg\7\t\2\2gh\7%\2\2hi\7\37\2\2ij\7\"\2\2j\5\3\2\2"+
		"\2kl\7\t\2\2lp\7\21\2\2mo\5\34\17\2nm\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3"+
		"\2\2\2qs\3\2\2\2rp\3\2\2\2st\7\"\2\2tu\5\26\f\2uv\7\t\2\2vw\7%\2\2wx\7"+
		"\21\2\2xy\7\"\2\2y\7\3\2\2\2z{\7\t\2\2{\177\7\22\2\2|~\5\34\17\2}|\3\2"+
		"\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0083\3\2\2\2"+
		"\u0081\177\3\2\2\2\u0082\u0084\5\36\20\2\u0083\u0082\3\2\2\2\u0083\u0084"+
		"\3\2\2\2\u0084\u0086\3\2\2\2\u0085\u0087\5 \21\2\u0086\u0085\3\2\2\2\u0086"+
		"\u0087\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u008a\5\"\22\2\u0089\u0088\3"+
		"\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c\3\2\2\2\u008b\u008d\5$\23\2\u008c"+
		"\u008b\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008f\3\2\2\2\u008e\u0090\5&"+
		"\24\2\u008f\u008e\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091"+
		"\u0093\5*\26\2\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0095\3\2"+
		"\2\2\u0094\u0096\5,\27\2\u0095\u0094\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0098\3\2\2\2\u0097\u0099\5.\30\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2"+
		"\2\2\u0099\u009b\3\2\2\2\u009a\u009c\5\66\34\2\u009b\u009a\3\2\2\2\u009b"+
		"\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009f\5\64\33\2\u009e\u009d\3"+
		"\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00cf\7$\2\2\u00a1"+
		"\u00a2\7\t\2\2\u00a2\u00a6\7\22\2\2\u00a3\u00a5\5\34\17\2\u00a4\u00a3"+
		"\3\2\2\2\u00a5\u00a8\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7"+
		"\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a9\u00ab\5\36\20\2\u00aa\u00a9\3"+
		"\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ad\3\2\2\2\u00ac\u00ae\5 \21\2\u00ad"+
		"\u00ac\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af\u00b1\5\""+
		"\22\2\u00b0\u00af\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2"+
		"\u00b4\5$\23\2\u00b3\u00b2\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b6\3\2"+
		"\2\2\u00b5\u00b7\5&\24\2\u00b6\u00b5\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\u00b9\3\2\2\2\u00b8\u00ba\5*\26\2\u00b9\u00b8\3\2\2\2\u00b9\u00ba\3\2"+
		"\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00bd\5,\27\2\u00bc\u00bb\3\2\2\2\u00bc"+
		"\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00c0\5.\30\2\u00bf\u00be\3\2"+
		"\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00c3\5\66\34\2\u00c2"+
		"\u00c1\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c5\3\2\2\2\u00c4\u00c6\5\64"+
		"\33\2\u00c5\u00c4\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00c8\7\"\2\2\u00c8\u00c9\5\26\f\2\u00c9\u00ca\7\t\2\2\u00ca\u00cb\7"+
		"%\2\2\u00cb\u00cc\7\22\2\2\u00cc\u00cd\7\"\2\2\u00cd\u00cf\3\2\2\2\u00ce"+
		"z\3\2\2\2\u00ce\u00a1\3\2\2\2\u00cf\t\3\2\2\2\u00d0\u00d1\7\t\2\2\u00d1"+
		"\u00d5\7\r\2\2\u00d2\u00d4\5\34\17\2\u00d3\u00d2\3\2\2\2\u00d4\u00d7\3"+
		"\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d6\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7"+
		"\u00d5\3\2\2\2\u00d8\u00da\5\36\20\2\u00d9\u00d8\3\2\2\2\u00d9\u00da\3"+
		"\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00dd\5\"\22\2\u00dc\u00db\3\2\2\2\u00dc"+
		"\u00dd\3\2\2\2\u00dd\u00df\3\2\2\2\u00de\u00e0\5$\23\2\u00df\u00de\3\2"+
		"\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00e3\5&\24\2\u00e2"+
		"\u00e1\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e6\5("+
		"\25\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7"+
		"\u00e9\5*\26\2\u00e8\u00e7\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00eb\3\2"+
		"\2\2\u00ea\u00ec\5,\27\2\u00eb\u00ea\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"\u00ee\3\2\2\2\u00ed\u00ef\5.\30\2\u00ee\u00ed\3\2\2\2\u00ee\u00ef\3\2"+
		"\2\2\u00ef\u00f1\3\2\2\2\u00f0\u00f2\5\60\31\2\u00f1\u00f0\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f5\5\66\34\2\u00f4\u00f3\3"+
		"\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u0125\7$\2\2\u00f7"+
		"\u00f8\7\t\2\2\u00f8\u00fc\7\r\2\2\u00f9\u00fb\5\34\17\2\u00fa\u00f9\3"+
		"\2\2\2\u00fb\u00fe\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd"+
		"\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00ff\u0101\5\36\20\2\u0100\u00ff\3"+
		"\2\2\2\u0100\u0101\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u0104\5\"\22\2\u0103"+
		"\u0102\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u0107\5$"+
		"\23\2\u0106\u0105\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0109\3\2\2\2\u0108"+
		"\u010a\5&\24\2\u0109\u0108\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010c\3\2"+
		"\2\2\u010b\u010d\5(\25\2\u010c\u010b\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010f\3\2\2\2\u010e\u0110\5*\26\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2"+
		"\2\2\u0110\u0112\3\2\2\2\u0111\u0113\5,\27\2\u0112\u0111\3\2\2\2\u0112"+
		"\u0113\3\2\2\2\u0113\u0115\3\2\2\2\u0114\u0116\5.\30\2\u0115\u0114\3\2"+
		"\2\2\u0115\u0116\3\2\2\2\u0116\u0118\3\2\2\2\u0117\u0119\5\60\31\2\u0118"+
		"\u0117\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\3\2\2\2\u011a\u011c\5\66"+
		"\34\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\3\2\2\2\u011d"+
		"\u011e\7\"\2\2\u011e\u011f\5\26\f\2\u011f\u0120\7\t\2\2\u0120\u0121\7"+
		"%\2\2\u0121\u0122\7\r\2\2\u0122\u0123\7\"\2\2\u0123\u0125\3\2\2\2\u0124"+
		"\u00d0\3\2\2\2\u0124\u00f7\3\2\2\2\u0125\13\3\2\2\2\u0126\u0127\7\t\2"+
		"\2\u0127\u012b\7\16\2\2\u0128\u012a\5\34\17\2\u0129\u0128\3\2\2\2\u012a"+
		"\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012f\3\2"+
		"\2\2\u012d\u012b\3\2\2\2\u012e\u0130\5*\26\2\u012f\u012e\3\2\2\2\u012f"+
		"\u0130\3\2\2\2\u0130\u0132\3\2\2\2\u0131\u0133\5,\27\2\u0132\u0131\3\2"+
		"\2\2\u0132\u0133\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0135\7\"\2\2\u0135"+
		"\u0136\5\26\f\2\u0136\u0137\7\t\2\2\u0137\u0138\7%\2\2\u0138\u0139\7\16"+
		"\2\2\u0139\u013a\7\"\2\2\u013a\r\3\2\2\2\u013b\u013c\7\t\2\2\u013c\u0140"+
		"\7\24\2\2\u013d\u013f\5\34\17\2\u013e\u013d\3\2\2\2\u013f\u0142\3\2\2"+
		"\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140"+
		"\3\2\2\2\u0143\u0145\5\36\20\2\u0144\u0143\3\2\2\2\u0144\u0145\3\2\2\2"+
		"\u0145\u0147\3\2\2\2\u0146\u0148\5$\23\2\u0147\u0146\3\2\2\2\u0147\u0148"+
		"\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u014b\5&\24\2\u014a\u0149\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014d\3\2\2\2\u014c\u014e\5*\26\2\u014d\u014c\3\2"+
		"\2\2\u014d\u014e\3\2\2\2\u014e\u0150\3\2\2\2\u014f\u0151\5,\27\2\u0150"+
		"\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0153\3\2\2\2\u0152\u0154\5\62"+
		"\32\2\u0153\u0152\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0158\3\2\2\2\u0155"+
		"\u0157\5\34\17\2\u0156\u0155\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156\3"+
		"\2\2\2\u0158\u0159\3\2\2\2\u0159\u015b\3\2\2\2\u015a\u0158\3\2\2\2\u015b"+
		"\u0184\7$\2\2\u015c\u015d\7\t\2\2\u015d\u0161\7\24\2\2\u015e\u0160\5\34"+
		"\17\2\u015f\u015e\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0166\5\36"+
		"\20\2\u0165\u0164\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0168\3\2\2\2\u0167"+
		"\u0169\5$\23\2\u0168\u0167\3\2\2\2\u0168\u0169\3\2\2\2\u0169\u016b\3\2"+
		"\2\2\u016a\u016c\5&\24\2\u016b\u016a\3\2\2\2\u016b\u016c\3\2\2\2\u016c"+
		"\u016e\3\2\2\2\u016d\u016f\5*\26\2\u016e\u016d\3\2\2\2\u016e\u016f\3\2"+
		"\2\2\u016f\u0171\3\2\2\2\u0170\u0172\5,\27\2\u0171\u0170\3\2\2\2\u0171"+
		"\u0172\3\2\2\2\u0172\u0174\3\2\2\2\u0173\u0175\5\62\32\2\u0174\u0173\3"+
		"\2\2\2\u0174\u0175\3\2\2\2\u0175\u0179\3\2\2\2\u0176\u0178\5\34\17\2\u0177"+
		"\u0176\3\2\2\2\u0178\u017b\3\2\2\2\u0179\u0177\3\2\2\2\u0179\u017a\3\2"+
		"\2\2\u017a\u017c\3\2\2\2\u017b\u0179\3\2\2\2\u017c\u017d\7\"\2\2\u017d"+
		"\u017e\5\26\f\2\u017e\u017f\7\t\2\2\u017f\u0180\7%\2\2\u0180\u0181\7\24"+
		"\2\2\u0181\u0182\7\"\2\2\u0182\u0184\3\2\2\2\u0183\u013b\3\2\2\2\u0183"+
		"\u015c\3\2\2\2\u0184\17\3\2\2\2\u0185\u0186\7\t\2\2\u0186\u0188\7\25\2"+
		"\2\u0187\u0189\5.\30\2\u0188\u0187\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018b"+
		"\3\2\2\2\u018a\u018c\5\66\34\2\u018b\u018a\3\2\2\2\u018b\u018c\3\2\2\2"+
		"\u018c\u018d\3\2\2\2\u018d\u019e\7$\2\2\u018e\u018f\7\t\2\2\u018f\u0191"+
		"\7\25\2\2\u0190\u0192\5.\30\2\u0191\u0190\3\2\2\2\u0191\u0192\3\2\2\2"+
		"\u0192\u0194\3\2\2\2\u0193\u0195\5\66\34\2\u0194\u0193\3\2\2\2\u0194\u0195"+
		"\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0197\7\"\2\2\u0197\u0198\5\26\f\2"+
		"\u0198\u0199\7\t\2\2\u0199\u019a\7%\2\2\u019a\u019b\7\25\2\2\u019b\u019c"+
		"\7\"\2\2\u019c\u019e\3\2\2\2\u019d\u0185\3\2\2\2\u019d\u018e\3\2\2\2\u019e"+
		"\21\3\2\2\2\u019f\u01a0\7\t\2\2\u01a0\u01a4\7\f\2\2\u01a1\u01a3\5\34\17"+
		"\2\u01a2\u01a1\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4\u01a2\3\2\2\2\u01a4\u01a5"+
		"\3\2\2\2\u01a5\u01a7\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a7\u01b8\7$\2\2\u01a8"+
		"\u01a9\7\t\2\2\u01a9\u01ad\7\f\2\2\u01aa\u01ac\5\34\17\2\u01ab\u01aa\3"+
		"\2\2\2\u01ac\u01af\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae"+
		"\u01b0\3\2\2\2\u01af\u01ad\3\2\2\2\u01b0\u01b1\7\"\2\2\u01b1\u01b2\5\26"+
		"\f\2\u01b2\u01b3\7\t\2\2\u01b3\u01b4\7%\2\2\u01b4\u01b5\7\f\2\2\u01b5"+
		"\u01b6\7\"\2\2\u01b6\u01b8\3\2\2\2\u01b7\u019f\3\2\2\2\u01b7\u01a8\3\2"+
		"\2\2\u01b8\23\3\2\2\2\u01b9\u01ba\7\t\2\2\u01ba\u01be\7\35\2\2\u01bb\u01bd"+
		"\5\34\17\2\u01bc\u01bb\3\2\2\2\u01bd\u01c0\3\2\2\2\u01be\u01bc\3\2\2\2"+
		"\u01be\u01bf\3\2\2\2\u01bf\u01c1\3\2\2\2\u01c0\u01be\3\2\2\2\u01c1\u01d2"+
		"\7$\2\2\u01c2\u01c3\7\t\2\2\u01c3\u01c7\7\35\2\2\u01c4\u01c6\5\34\17\2"+
		"\u01c5\u01c4\3\2\2\2\u01c6\u01c9\3\2\2\2\u01c7\u01c5\3\2\2\2\u01c7\u01c8"+
		"\3\2\2\2\u01c8\u01ca\3\2\2\2\u01c9\u01c7\3\2\2\2\u01ca\u01cb\7\"\2\2\u01cb"+
		"\u01cc\5\26\f\2\u01cc\u01cd\7\t\2\2\u01cd\u01ce\7%\2\2\u01ce\u01cf\7\35"+
		"\2\2\u01cf\u01d0\7\"\2\2\u01d0\u01d2\3\2\2\2\u01d1\u01b9\3\2\2\2\u01d1"+
		"\u01c2\3\2\2\2\u01d2\25\3\2\2\2\u01d3\u01d5\58\35\2\u01d4\u01d3\3\2\2"+
		"\2\u01d4\u01d5\3\2\2\2\u01d5\u01ea\3\2\2\2\u01d6\u01e4\5\30\r\2\u01d7"+
		"\u01e4\5\6\4\2\u01d8\u01e4\5\b\5\2\u01d9\u01e4\5\16\b\2\u01da\u01e4\5"+
		"\f\7\2\u01db\u01e4\5\20\t\2\u01dc\u01e4\5\22\n\2\u01dd\u01e4\5\24\13\2"+
		"\u01de\u01e4\5\32\16\2\u01df\u01e4\5\n\6\2\u01e0\u01e4\7\4\2\2\u01e1\u01e4"+
		"\7*\2\2\u01e2\u01e4\7\3\2\2\u01e3\u01d6\3\2\2\2\u01e3\u01d7\3\2\2\2\u01e3"+
		"\u01d8\3\2\2\2\u01e3\u01d9\3\2\2\2\u01e3\u01da\3\2\2\2\u01e3\u01db\3\2"+
		"\2\2\u01e3\u01dc\3\2\2\2\u01e3\u01dd\3\2\2\2\u01e3\u01de\3\2\2\2\u01e3"+
		"\u01df\3\2\2\2\u01e3\u01e0\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e3\u01e2\3\2"+
		"\2\2\u01e4\u01e6\3\2\2\2\u01e5\u01e7\58\35\2\u01e6\u01e5\3\2\2\2\u01e6"+
		"\u01e7\3\2\2\2\u01e7\u01e9\3\2\2\2\u01e8\u01e3\3\2\2\2\u01e9\u01ec\3\2"+
		"\2\2\u01ea\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\27\3\2\2\2\u01ec\u01ea"+
		"\3\2\2\2\u01ed\u01ee\7\t\2\2\u01ee\u01ef\7(\2\2\u01ef\u01f0\7\"\2\2\u01f0"+
		"\u01f1\5\26\f\2\u01f1\u01f2\7\t\2\2\u01f2\u01f3\7%\2\2\u01f3\u01f4\7("+
		"\2\2\u01f4\u01f5\7\"\2\2\u01f5\u0212\3\2\2\2\u01f6\u01f7\7\t\2\2\u01f7"+
		"\u01f8\7(\2\2\u01f8\u0212\7$\2\2\u01f9\u01fa\7\t\2\2\u01fa\u01fe\7(\2"+
		"\2\u01fb\u01fd\5\34\17\2\u01fc\u01fb\3\2\2\2\u01fd\u0200\3\2\2\2\u01fe"+
		"\u01fc\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u0201\3\2\2\2\u0200\u01fe\3\2"+
		"\2\2\u0201\u0202\7\"\2\2\u0202\u0203\5\26\f\2\u0203\u0204\7\t\2\2\u0204"+
		"\u0205\7%\2\2\u0205\u0206\7(\2\2\u0206\u0207\7\"\2\2\u0207\u0212\3\2\2"+
		"\2\u0208\u0209\7\t\2\2\u0209\u020d\7(\2\2\u020a\u020c\5\34\17\2\u020b"+
		"\u020a\3\2\2\2\u020c\u020f\3\2\2\2\u020d\u020b\3\2\2\2\u020d\u020e\3\2"+
		"\2\2\u020e\u0210\3\2\2\2\u020f\u020d\3\2\2\2\u0210\u0212\7$\2\2\u0211"+
		"\u01ed\3\2\2\2\u0211\u01f6\3\2\2\2\u0211\u01f9\3\2\2\2\u0211\u0208\3\2"+
		"\2\2\u0212\31\3\2\2\2\u0213\u0214\t\2\2\2\u0214\33\3\2\2\2\u0215\u0216"+
		"\7(\2\2\u0216\u0217\7&\2\2\u0217\u0218\7\'\2\2\u0218\35\3\2\2\2\u0219"+
		"\u021a\7\17\2\2\u021a\u021b\7\'\2\2\u021b\37\3\2\2\2\u021c\u021d\7\20"+
		"\2\2\u021d\u021e\7\'\2\2\u021e!\3\2\2\2\u021f\u0220\7 \2\2\u0220\u0221"+
		"\7\'\2\2\u0221#\3\2\2\2\u0222\u0223\7\23\2\2\u0223\u0224\7\'\2\2\u0224"+
		"%\3\2\2\2\u0225\u0226\7\26\2\2\u0226\u0227\7\'\2\2\u0227\'\3\2\2\2\u0228"+
		"\u0229\7\32\2\2\u0229\u022a\7\'\2\2\u022a)\3\2\2\2\u022b\u022c\7\27\2"+
		"\2\u022c\u022d\7\'\2\2\u022d+\3\2\2\2\u022e\u022f\7\30\2\2\u022f\u0230"+
		"\7\'\2\2\u0230-\3\2\2\2\u0231\u0232\7\31\2\2\u0232\u0233\7\'\2\2\u0233"+
		"/\3\2\2\2\u0234\u0235\7\33\2\2\u0235\u0236\7\'\2\2\u0236\61\3\2\2\2\u0237"+
		"\u0238\7\34\2\2\u0238\u0239\7\'\2\2\u0239\63\3\2\2\2\u023a\u023b\7\36"+
		"\2\2\u023b\u023c\7\'\2\2\u023c\65\3\2\2\2\u023d\u023e\7!\2\2\u023e\u023f"+
		"\7\'\2\2\u023f\67\3\2\2\2\u0240\u0241\t\3\2\2\u02419\3\2\2\2\u0242\u0243"+
		"\t\4\2\2\u0243;\3\2\2\2_@EHOSX\\ap\177\u0083\u0086\u0089\u008c\u008f\u0092"+
		"\u0095\u0098\u009b\u009e\u00a6\u00aa\u00ad\u00b0\u00b3\u00b6\u00b9\u00bc"+
		"\u00bf\u00c2\u00c5\u00ce\u00d5\u00d9\u00dc\u00df\u00e2\u00e5\u00e8\u00eb"+
		"\u00ee\u00f1\u00f4\u00fc\u0100\u0103\u0106\u0109\u010c\u010f\u0112\u0115"+
		"\u0118\u011b\u0124\u012b\u012f\u0132\u0140\u0144\u0147\u014a\u014d\u0150"+
		"\u0153\u0158\u0161\u0165\u0168\u016b\u016e\u0171\u0174\u0179\u0183\u0188"+
		"\u018b\u0191\u0194\u019d\u01a4\u01ad\u01b7\u01be\u01c7\u01d1\u01d4\u01e3"+
		"\u01e6\u01ea\u01fe\u020d\u0211";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}