grammar Chat;

/*
 * Parser Rules
 */
chat                : line+ EOF ;

line                : name command message;

message             : (emoticon | link | color | mention | WORD | ' ')+ ;

name                : WORD ' ';

command             : (SAYS | SHOUTS) ':' ' ';

emoticon            : ':' '-'? ')'
                    | ':' '-'? '('
                    ;

link                : '[' TEXT ']' '(' TEXT ')' ;

color               : '/' WORD '/' message '/';

mention             : '@' WORD ;


/*
 * Lexer (Tokenisers) Rules
 */

fragment A          : ('A'|'a') ;
fragment S          : ('S'|'s') ;
fragment Y          : ('Y'|'y') ;
fragment H          : ('H'|'h') ;
fragment O          : ('O'|'o') ;
fragment U          : ('U'|'u') ;
fragment T          : ('T'|'t') ;

fragment LOWERCASE  : [a-z] ;
fragment UPPERCASE  : [A-Z] ;

SAYS                : S A Y S ;

SHOUTS              : S H O U T S;

WORD                : (LOWERCASE | UPPERCASE | '_')+ ;

WHITESPACE          : (' ' | 't') -> skip;

NEWLINE             : ('r'? 'n' | 'r')+ ;
// NEWLINE             : ('n')+ ;

// TEXT                : ~[\])]+ ;
TEXT                : ('['|'(') ~[\])]+ (']'|')') ;
