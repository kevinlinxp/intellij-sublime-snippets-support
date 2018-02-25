parser grammar SublimeSnippetParser;

options  { tokenVocab=SublimeSnippetLexer; }

snippet
    : content EOF
    ;

content
    : (text | field)*
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
