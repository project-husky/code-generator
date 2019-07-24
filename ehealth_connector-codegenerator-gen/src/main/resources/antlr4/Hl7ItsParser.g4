/** 
 * ******************************************************************************
 * HL7 ITS parser derived from ANTLR v4 ref guide book example XML parser.
 * 
 * Parameters:
 * none
 * 
 * History:
 * 2019.04.16: Tony Schaller, medshare GmbH: First draft for PoC
 * 2019.07.23: Tony Schaller, medshare GmbH: Implementation of the ANTLR4 module for ART-DECOR to Java code generator.
 * 
 * ******************************************************************************
 */
 
parser grammar Hl7ItsParser;

@parser::header {
package org.ehealth_connector.codegenerator.cda.antlr;
}

options { tokenVocab=Hl7ItsLexer; }

prolog : XMLDeclOpen attr* SPECIAL_CLOSE SEA_WS?;

template : prolog? '<' TEMPLATE attr* idAttr? attr* nameAttr? attr* '>' content '<' '/' TEMPLATE '>' ;

desc : '<' DESC attr* '>' content '<' '/' DESC '>' ;

element : '<' ELEMENT attr* conformanceAttr? containsAttr? dataTypeAttr? idAttr? isMandatoryAttr? maxOccursAttr? minOccursAttr? nameAttr? valueAttr? '/>' |
          '<' ELEMENT attr* conformanceAttr? containsAttr? dataTypeAttr? idAttr? isMandatoryAttr? maxOccursAttr? minOccursAttr? nameAttr? valueAttr? '>' content '<' '/' ELEMENT '>' ;

attribute : '<' ATTRIBUTE attr* conformanceAttr? dataTypeAttr? idAttr? isMandatoryAttr? isOptionalAttr? maxOccursAttr? minOccursAttr? nameAttr? prohibitedAttr? valueAttr? '/>' |
            '<' ATTRIBUTE attr* conformanceAttr? dataTypeAttr? idAttr? isMandatoryAttr? isOptionalAttr? maxOccursAttr? minOccursAttr? nameAttr? prohibitedAttr? valueAttr? '>' content '<' '/' ATTRIBUTE '>' ;

choice : '<' CHOICE attr* maxOccursAttr? minOccursAttr? '>' content '<' '/' CHOICE '>' ;

include : '<' INCLUDE attr* conformanceAttr? idAttr? isMandatoryAttr? maxOccursAttr? minOccursAttr? refAttr? attr* '/>' | 
					'<' INCLUDE attr* conformanceAttr? idAttr? isMandatoryAttr? maxOccursAttr? minOccursAttr? refAttr? attr* '>' content '<' '/' INCLUDE '>';

letter : '<' LET nameAttr? valueAttr? '/>' | 
				 '<' LET nameAttr? valueAttr? '>' content '<' '/' LET '>';

assertion : '<' ASSERT attr* '/>' | 
						'<' ASSERT attr* '>' content '<' '/' ASSERT '>';

report : '<' REPORT attr* '/>' | 
				 '<' REPORT attr* '>' content '<' '/' REPORT '>';

content : chardata?
          ((xmlelement | desc | element | include | choice | letter | assertion | report | reference | attribute | CDATA | PI | COMMENT) chardata?)* ;

xmlelement : '<' Name '>' content '<' '/' Name '>' |
             '<' Name '/>' |
             '<' Name attr* '>' content '<' '/' Name '>' |
             '<' Name attr* '/>';

reference : EntityRef | CharRef ;

// handled attributes 
attr : Name '=' AttrValue ;
nameAttr : NAMEATTR AttrValue ;
dataTypeAttr : TYPEATTR AttrValue ;
minOccursAttr : MINOCCURSATTR AttrValue ;
maxOccursAttr : MAXOCCURSATTR AttrValue ;
conformanceAttr : CONFATTR AttrValue ;
isMandatoryAttr : MANDATTR AttrValue ;
isOptionalAttr : OPTATTR AttrValue ;
prohibitedAttr : PROHIBITED AttrValue ;
idAttr : IDATTR AttrValue ;
valueAttr : VALUEATTR AttrValue ;
containsAttr : CONTAINSATTR AttrValue ;
refAttr : REFATTR AttrValue;


/** 
 * All text that is not markup constitutes the character data of
 * the document.
 */
chardata    :   TEXT | SEA_WS ;

misc        :   COMMENT | PI | SEA_WS ;