/** 
 * ******************************************************************************
 * HL7 ITS lexer derived from ANTLR v4 ref guide book example XML parser.
 * 
 * Parameters:
 * none
 * 
 * History:
 * 2019.04.16: Tony Schaller, medshare GmbH: First draft for PoC
 * 2019.07.23: Tony Schaller, medshare GmbH: Implementation of the ANTLR4 module for ART-DECOR to Java code generator.
 * 2019.07.25: Tony Schaller, medshare GmbH: Adding strength attribute to element
 * 2019.07.26: Tony Schaller, medshare GmbH: Adding flexibility attribute to element and new vocabulary element
 * 
 * ******************************************************************************
 */
 
lexer grammar Hl7ItsLexer;

@header {
package org.ehealth_connector.codegenerator.cda.antlr;
}

// Default "mode": Everything OUTSIDE of a tag
COMMENT     :   '<!--' .*? '-->' ;
CDATA       :   '<![CDATA[' .*? ']]>' ;

/** Scarf all DTD stuff, Entity Declarations like <!ENTITY ...>,
 *  and Notation Declarations <!NOTATION ...>
 */
DTD         :   '<!' .*? '>'            -> skip ;
EntityRef   :   '&' Name ';' ;
CharRef     :   '&#' DIGIT+ ';'
            |   '&#x' HEXDIGIT+ ';'
            ;
SEA_WS      :   (' '|'\t'|'\r'? '\n')+ ;

OPEN        :   '<'                     -> pushMode(INSIDE) ;
XMLDeclOpen :   '<?xml' S               -> pushMode(INSIDE) ;
SPECIAL_OPEN:   '<?' Name               -> more, pushMode(PROC_INSTR) ;

TEXT        :   ~[<&]+ ;        // match any 16 bit char other than < and &

// ----------------- Everything INSIDE of a tag ---------------------
mode INSIDE;

// handled keywords for HL7 ITS
ASSERT : 'assert';
ATTRIBUTE : 'attribute';
CHOICE : 'choice';
CODEATTR : 'code=';
CONFATTR : 'conformance=';
FLEXIBILITYATTR : 'flexibility=';
CONTAINSATTR : 'contains=';
DESC : 'desc';
ELEMENT : 'element';
IDATTR : 'id=';
INCLUDE : 'include';
LET : 'let';
MANDATTR : 'isMandatory=';
MAXOCCURSATTR : 'maximumMultiplicity=';
MINOCCURSATTR : 'minimumMultiplicity=';
NAMEATTR : 'name=';
OPTATTR : 'isOptional=';
PROHIBITED : 'prohibited=';
REFATTR : 'ref=';
REPORT : 'report';
STRENGTHATTR : 'strength=';
TEMPLATE : 'template';
TYPEATTR : 'datatype=';
VALUEATTR : 'value=';
VALUESETATTR: 'valueSet=';
VOCAB : 'vocabulary';

CLOSE       :   '>'                     -> popMode ;
SPECIAL_CLOSE:  '?>'                    -> popMode ; // close <?xml...?>
SLASH_CLOSE :   '/>'                    -> popMode ;
SLASH       :   '/' ;
EQUALS      :   '=' ;
AttrValue      :   '"' ~[<"]* '"'
            |   '\'' ~[<']* '\''
            ;
Name        :   NameStartChar NameChar* ;
S           :   [ \t\r\n]               -> skip ;

fragment
HEXDIGIT    :   [a-fA-F0-9] ;

fragment
DIGIT       :   [0-9] ;

fragment
NameChar    :   NameStartChar
            |   '-' | '_' | '.' | DIGIT
            |   '\u00B7'
            |   '\u0300'..'\u036F'
            |   '\u203F'..'\u2040'
            ;

fragment
NameStartChar
            :   [:a-zA-Z]
            |   '\u2070'..'\u218F'
            |   '\u2C00'..'\u2FEF'
            |   '\u3001'..'\uD7FF'
            |   '\uF900'..'\uFDCF'
            |   '\uFDF0'..'\uFFFD'
            ;


// ----------------- Handle <? ... ?> ---------------------
mode PROC_INSTR;

PI          :   '?>'                    -> popMode ; // close <?...?>
IGNORE      :   .                       -> more ;

