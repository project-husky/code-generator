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
 * 2019.07.25: Tony Schaller, medshare GmbH: Adding strength attribute to element
 * 2019.07.26: Tony Schaller, medshare GmbH: Adding flexibility attribute to element and new vocabulary element
 * 2019.08.22: Tony Schaller, medshare GmbH: Adding codeSystem attribute to vocabulary element
 * 2019.09.05: Tony Schaller, medshare GmbH: Adding codeSystemName and displayName attributes to vocabulary element
 * 2022.04.15: Quentin Ligier: Adding ref attribute to template element.
 * 
 * ******************************************************************************
 */
 
parser grammar Hl7ItsParser;

@header {
package org.husky.codegenerator.cda.antlr;
}

options { tokenVocab=Hl7ItsLexer; }

prolog : XMLDeclOpen attr* SPECIAL_CLOSE SEA_WS?;

template : prolog? '<' TEMPLATE attr* displayNameAttr? attr* idAttr? attr* nameAttr? attr* refAttr? '>' content '<' '/' TEMPLATE '>' ;

desc : '<' DESC attr* '>' content '<' '/' DESC '>' ;

element : '<' ELEMENT attr* conformanceAttr? containsAttr? dataTypeAttr? flexibilityAttr? idAttr? isMandatoryAttr? maxOccursAttr? minOccursAttr? nameAttr? valueAttr? strengthAttr? '/>' |
          '<' ELEMENT attr* conformanceAttr? containsAttr? dataTypeAttr? flexibilityAttr? idAttr? isMandatoryAttr? maxOccursAttr? minOccursAttr? nameAttr? valueAttr? strengthAttr? '>' content '<' '/' ELEMENT '>' ;

attribute : '<' ATTRIBUTE attr* conformanceAttr? dataTypeAttr? idAttr? isMandatoryAttr? isOptionalAttr? maxOccursAttr? minOccursAttr? nameAttr? prohibitedAttr? valueAttr? '/>' |
            '<' ATTRIBUTE attr* conformanceAttr? dataTypeAttr? idAttr? isMandatoryAttr? isOptionalAttr? maxOccursAttr? minOccursAttr? nameAttr? prohibitedAttr? valueAttr? '>' content '<' '/' ATTRIBUTE '>' ;

choice : '<' CHOICE attr* maxOccursAttr? minOccursAttr? '>' content '<' '/' CHOICE '>' ;

include : '<' INCLUDE attr* conformanceAttr? flexibilityAttr? idAttr? isMandatoryAttr? maxOccursAttr? minOccursAttr? refAttr? attr* '/>' | 
          '<' INCLUDE attr* conformanceAttr? flexibilityAttr? idAttr? isMandatoryAttr? maxOccursAttr? minOccursAttr? refAttr? attr* '>' content '<' '/' INCLUDE '>';
          
vocab : '<' VOCAB attr* codeAttr? codeSystemAttr? codeSystemNameAttr? displayNameAttr? flexibilityAttr? valueSetAttr? attr* '/>' | 
        '<' VOCAB attr* codeAttr? codeSystemAttr? codeSystemNameAttr? displayNameAttr? flexibilityAttr? valueSetAttr? attr* '>' content '<' '/' VOCAB '>';


letter : '<' LET nameAttr? valueAttr? '/>' | 
				 '<' LET nameAttr? valueAttr? '>' content '<' '/' LET '>';

assertion : '<' ASSERT attr* '/>' | 
						'<' ASSERT attr* '>' content '<' '/' ASSERT '>';

report : '<' REPORT attr* '/>' | 
				 '<' REPORT attr* '>' content '<' '/' REPORT '>';

content : chardata?
          ((xmlelement | desc | element | include | choice | letter | assertion | report | reference | attribute | vocab | CDATA | PI | COMMENT) chardata?)* ;

xmlelement : '<' Name '>' content '<' '/' Name '>' |
             '<' Name '/>' |
             '<' Name attr* '>' content '<' '/' Name '>' |
             '<' Name attr* '/>';

reference : EntityRef | CharRef ;

// handled attributes for HL7 ITS
attr : Name '=' AttrValue ;
conformanceAttr : CONFATTR AttrValue ;
flexibilityAttr : FLEXIBILITYATTR AttrValue ;
codeAttr : CODEATTR AttrValue ;
codeSystemAttr : CODESYSTEMATTR AttrValue ;
codeSystemNameAttr : CODESYSTEMNAMEATTR AttrValue ;
containsAttr : CONTAINSATTR AttrValue ;
dataTypeAttr : DATATYPEATTR AttrValue ;
displayNameAttr : DISPLAYNAMEATTR AttrValue ;
idAttr : IDATTR AttrValue ;
isMandatoryAttr : MANDATTR AttrValue ;
isOptionalAttr : OPTATTR AttrValue ;
maxOccursAttr : MAXOCCURSATTR AttrValue ;
minOccursAttr : MINOCCURSATTR AttrValue ;
nameAttr : NAMEATTR AttrValue ;
prohibitedAttr : PROHIBITED AttrValue ;
refAttr : REFATTR AttrValue;
strengthAttr : STRENGTHATTR AttrValue;
valueAttr : VALUEATTR AttrValue ;
valueSetAttr : VALUESETATTR AttrValue;


/** 
 * All text that is not markup constitutes the character data of
 * the document.
 */
chardata    :   TEXT | SEA_WS ;

misc        :   COMMENT | PI | SEA_WS ;