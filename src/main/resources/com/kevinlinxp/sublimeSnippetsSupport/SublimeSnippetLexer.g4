lexer grammar SublimeSnippetLexer;

VarName
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

Inside_VarName
    : VarName -> type(VarName)
    ;

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
    : Inside_VarName FieldBracketedEnd
    ;

VarNameAndPlaceholderStart
    : Inside_VarName ':'
    ;

VarNameAndSubstitutionStart
    : Inside_VarName '/'
    ;
