parser grammar SublimeSnippetParser;

@header {
package com.kevinlinxp.sublimeSnippetsSupport;
}

options  { tokenVocab=SublimeSnippetLexer; }

snippet
    : content EOF
    ;

content
    : (field | text)*
    ;

text
    : TextCharacter+
    ;

field
    : fieldUnbracketed
    | fieldBracketed
    | fieldBracketedWithPlaceholder
    ;

fieldUnbracketed
    : FieldUnbracketed
    ;

fieldBracketed
    : FieldBracketed
    ;

textInside
    : Inside_TextCharacter+
    ;

fieldBracketedWithPlaceholder
    : FieldBracketedStart VarNameAndPlaceholderStart (field | textInside)* FieldBracketedEnd
    ;
