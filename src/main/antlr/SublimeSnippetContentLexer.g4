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

FieldBracketedWithPlaceholderStart
    : '${' VarName ':' -> pushMode(Inside)
    ;

FieldBracketedWithSubstitution
    : '${' VarName '/' (~[/] | '\\/')*? '/' (~[/] | '\\/')*? '/' [igm]? [igm]? [igm]? '}'
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

Inside_FieldBracketedWithPlaceholderStart
    : FieldBracketedWithPlaceholderStart -> pushMode(Inside), type(FieldBracketedWithPlaceholderStart)
    ;

Inside_FieldBracketedWithSubstitution
    : FieldBracketedWithSubstitution -> type(FieldBracketedWithSubstitution)
    ;

FieldBracketedEnd
    : '}' -> popMode
    ;

//VarNameAndSubstitutionStart
//    : VarName '/' -> pushMode(InRegexpReplacement)
//    ;
//
//mode InRegexpReplacement ;
//
//RegexpReplacementAndOptions
//    : (~[/] | '\\/')*? '/' (~[/] | '\\/')*? '/' [igm]? [igm]? [igm]? -> popMode
//    ;
