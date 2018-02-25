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
    : FieldBracketedStart VarNameAndEnd
    ;

textInside
    : Inside_TextCharacter+
    ;

fieldBracketedWithPlaceholder
    : FieldBracketedStart VarNameAndPlaceholderStart (field | textInside)* FieldBracketedEnd
    ;
