lexer grammar SublimeSnippetLexer;

fragment VarName
    : ( [0-9]+ | ([A-Z]+ ('_' | [A-Z]+ | [0-9]+)*) )
    ;

TextCharacter
    : '\\\\' | '\\$' | '\\{' | '\\}' | ~[$]
    ;

FieldUnbracketed
    : '$' VarName
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

Inside_FieldBracketedStart
    : FieldBracketedStart -> type(FieldBracketedStart)
    ;


FieldBracketedEnd
    : '}' -> popMode
    ;

VarNameAndEnd
    : VarName FieldBracketedEnd
    ;

VarNameAndPlaceholderStart
    : VarName ':'
    ;

VarNameAndSubstitutionStart
    : VarName '/'
    ;
