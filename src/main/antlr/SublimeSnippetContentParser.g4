parser grammar SublimeSnippetContentParser;

options  { tokenVocab=SublimeSnippetContentLexer; }

snippet
    : (field | text)*
    ;

text
    : TextCharacter+
    ;

field
    : fieldUnbracketed
    | fieldBracketed
    | fieldBracketedWithPlaceholder
    | fieldBracketedWithSubstitution
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
    : FieldBracketedWithPlaceholderStart (field | textInside)* FieldBracketedEnd
    ;

fieldBracketedWithSubstitution
    : FieldBracketedWithSubstitution
    ;
