parser grammar SublimeSnippetParser;

options  { tokenVocab=SublimeSnippetLexer; }

snippet
    : content EOF
    ;

content
    : (field | text)*
    ;

field
    : fieldUnbracketed
    | fieldBracketed
    | fieldBracketedWithPlaceholder
    | fieldBracketedWithSubstitution
    ;

text
    : TextCharacter+
    ;

fieldUnbracketed
    : FieldUnbracketed
    ;

fieldBracketed
    : FieldBracketed
    ;

fieldBracketedWithPlaceholder
    : FieldBracketedWithPlaceholderStart (field | text)*? FieldBracketedWithPlaceholderEnd
    ;

fieldBracketedWithSubstitution
    : FieldBracketedWithSubstitutionStart .*? FieldBracketedWithSubstitutionEnd
    ;
