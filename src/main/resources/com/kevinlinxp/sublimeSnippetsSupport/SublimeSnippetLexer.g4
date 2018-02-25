lexer grammar SublimeSnippetLexer;

fragment VAR_NAME
    : ( NUMBER | (UPPERCASE_WORD ('_' | UPPERCASE_WORD | NUMBER)*) )
    ;

fragment DIGIT      : [0-9] ;
fragment NUMBER     : DIGIT+ ;
fragment LOWERCASE  : [a-z] ;
fragment LOWERCASE_WORD  : LOWERCASE+ ;
fragment UPPERCASE  : [A-Z] ;
fragment UPPERCASE_WORD : UPPERCASE+ ;

FieldUnbracketed
    : '$' VAR_NAME
    ;

FieldBracketed
    : '${' VAR_NAME '}'
    ;

FieldBracketedWithPlaceholderStart
    : '${' VAR_NAME ':'
    ;

FieldBracketedWithPlaceholderEnd
    : '}'
    ;

FieldBracketedWithSubstitutionStart
    : '${' VAR_NAME '/'
    ;

FieldBracketedWithSubstitutionEnd
    : '}'
    ;

TextCharacter
    : '\\\\' | '\\$' | '\\{' | '\\}' | ~[$]
    ;