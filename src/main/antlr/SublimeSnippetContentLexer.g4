lexer grammar SublimeSnippetContentLexer;

fragment VarName
    : ( [0-9]+ | ([A-Z]+ ('_' | [A-Z]+ | [0-9]+)*) )
    ;

TextCharacter
    : '\\\\' | '\\$' | '\\{' | '\\}' | ~[$]
    ;

FieldUnbracketed
    : '$' VarName
    ;

FieldBracketed
    : '${' VarName '}'
    ;

FieldBracketedStart
    : '${' -> pushMode(Inside)
    ;

mode Inside ;

Inside_TextCharacter
    : '\\\\' | '\\$' | '\\{' | '\\}' | ~[$}]
    ;

Inside_FieldUnbracketed
    : FieldUnbracketed -> type(FieldUnbracketed)
    ;

Inside_FieldBracketed
    : FieldBracketed -> type(FieldBracketed)
    ;

Inside_FieldBracketedStart
    : FieldBracketedStart -> pushMode(Inside), type(FieldBracketedStart)
    ;

FieldBracketedEnd
    : '}' -> popMode
    ;

VarNameAndPlaceholderStart
    : VarName ':'
    ;

VarNameAndSubstitutionStart
    : VarName '/' -> pushMode(InRegexpReplacement)
    ;

mode InRegexpReplacement ;

RegexpReplacementAndOptions
    : (~[/] | '\\/')*? '/' (~[/] | '\\/')*? '/' [igm]? [igm]? [igm]? -> popMode
    ;
