// Generated from ./SublimeSnippetLexer.g4 by ANTLR 4.7
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SublimeSnippetLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		FieldUnbracketed=1, FieldBracketed=2, FieldBracketedWithPlaceholderStart=3, 
		FieldBracketedWithPlaceholderEnd=4, FieldBracketedWithSubstitutionStart=5, 
		FieldBracketedWithSubstitutionEnd=6, TextCharacter=7;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"VAR_NAME", "DIGIT", "NUMBER", "LOWERCASE", "LOWERCASE_WORD", "UPPERCASE", 
		"UPPERCASE_WORD", "FieldUnbracketed", "FieldBracketed", "FieldBracketedWithPlaceholderStart", 
		"FieldBracketedWithPlaceholderEnd", "FieldBracketedWithSubstitutionStart", 
		"FieldBracketedWithSubstitutionEnd", "TextCharacter"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "FieldUnbracketed", "FieldBracketed", "FieldBracketedWithPlaceholderStart", 
		"FieldBracketedWithPlaceholderEnd", "FieldBracketedWithSubstitutionStart", 
		"FieldBracketedWithSubstitutionEnd", "TextCharacter"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public SublimeSnippetLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SublimeSnippetLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\td\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\7\2%\n\2\f"+
		"\2\16\2(\13\2\5\2*\n\2\3\3\3\3\3\4\6\4/\n\4\r\4\16\4\60\3\5\3\5\3\6\6"+
		"\6\66\n\6\r\6\16\6\67\3\7\3\7\3\b\6\b=\n\b\r\b\16\b>\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"c\n\17\2\2\20\3\2\5\2\7\2\t\2\13\2\r\2\17\2\21\3\23\4\25\5\27\6\31\7\33"+
		"\b\35\t\3\2\6\3\2\62;\3\2c|\3\2C\\\3\2&&\2g\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3)\3"+
		"\2\2\2\5+\3\2\2\2\7.\3\2\2\2\t\62\3\2\2\2\13\65\3\2\2\2\r9\3\2\2\2\17"+
		"<\3\2\2\2\21@\3\2\2\2\23C\3\2\2\2\25I\3\2\2\2\27O\3\2\2\2\31Q\3\2\2\2"+
		"\33W\3\2\2\2\35b\3\2\2\2\37*\5\7\4\2 &\5\17\b\2!%\7a\2\2\"%\5\17\b\2#"+
		"%\5\7\4\2$!\3\2\2\2$\"\3\2\2\2$#\3\2\2\2%(\3\2\2\2&$\3\2\2\2&\'\3\2\2"+
		"\2\'*\3\2\2\2(&\3\2\2\2)\37\3\2\2\2) \3\2\2\2*\4\3\2\2\2+,\t\2\2\2,\6"+
		"\3\2\2\2-/\5\5\3\2.-\3\2\2\2/\60\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61"+
		"\b\3\2\2\2\62\63\t\3\2\2\63\n\3\2\2\2\64\66\5\t\5\2\65\64\3\2\2\2\66\67"+
		"\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\f\3\2\2\29:\t\4\2\2:\16\3\2\2\2;="+
		"\5\r\7\2<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2\2?\20\3\2\2\2@A\7&\2\2"+
		"AB\5\3\2\2B\22\3\2\2\2CD\7&\2\2DE\7}\2\2EF\3\2\2\2FG\5\3\2\2GH\7\177\2"+
		"\2H\24\3\2\2\2IJ\7&\2\2JK\7}\2\2KL\3\2\2\2LM\5\3\2\2MN\7<\2\2N\26\3\2"+
		"\2\2OP\7\177\2\2P\30\3\2\2\2QR\7&\2\2RS\7}\2\2ST\3\2\2\2TU\5\3\2\2UV\7"+
		"\61\2\2V\32\3\2\2\2WX\7\177\2\2X\34\3\2\2\2YZ\7^\2\2Zc\7^\2\2[\\\7^\2"+
		"\2\\c\7&\2\2]^\7^\2\2^c\7}\2\2_`\7^\2\2`c\7\177\2\2ac\n\5\2\2bY\3\2\2"+
		"\2b[\3\2\2\2b]\3\2\2\2b_\3\2\2\2ba\3\2\2\2c\36\3\2\2\2\n\2$&)\60\67>b"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}