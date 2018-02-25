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
		VarName=1, TextCharacter=2, FieldUnbracketed=3, FieldBracketedStart=4, 
		Inside_TextCharacter=5, FieldBracketedEnd=6, VarNameAndEnd=7, VarNameAndPlaceholderStart=8, 
		VarNameAndSubstitutionStart=9;
	public static final int
		Inside=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "Inside"
	};

	public static final String[] ruleNames = {
		"VarName", "TextCharacter", "FieldUnbracketed", "FieldBracketedStart", 
		"Inside_VarName", "Inside_TextCharacter", "Inside_FieldUnbracketed", "Inside_FieldBracketedStart", 
		"FieldBracketedEnd", "VarNameAndEnd", "VarNameAndPlaceholderStart", "VarNameAndSubstitutionStart"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'${'", null, "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "VarName", "TextCharacter", "FieldUnbracketed", "FieldBracketedStart", 
		"Inside_TextCharacter", "FieldBracketedEnd", "VarNameAndEnd", "VarNameAndPlaceholderStart", 
		"VarNameAndSubstitutionStart"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13o\b\1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\3\2\6\2\36\n\2\r\2\16\2\37\3\2\6\2#\n\2\r\2\16\2"+
		"$\3\2\3\2\6\2)\n\2\r\2\16\2*\3\2\6\2.\n\2\r\2\16\2/\7\2\62\n\2\f\2\16"+
		"\2\65\13\2\5\2\67\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3B\n\3\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\5\7Y\n\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\2\2\16\4\3\6\4\b\5\n\6\f\2\16\7"+
		"\20\2\22\2\24\b\26\t\30\n\32\13\4\2\3\6\3\2\62;\3\2C\\\3\2&&\4\2&&\177"+
		"\177\2}\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\3\f\3\2\2\2\3"+
		"\16\3\2\2\2\3\20\3\2\2\2\3\22\3\2\2\2\3\24\3\2\2\2\3\26\3\2\2\2\3\30\3"+
		"\2\2\2\3\32\3\2\2\2\4\66\3\2\2\2\6A\3\2\2\2\bC\3\2\2\2\nF\3\2\2\2\fK\3"+
		"\2\2\2\16X\3\2\2\2\20Z\3\2\2\2\22^\3\2\2\2\24b\3\2\2\2\26f\3\2\2\2\30"+
		"i\3\2\2\2\32l\3\2\2\2\34\36\t\2\2\2\35\34\3\2\2\2\36\37\3\2\2\2\37\35"+
		"\3\2\2\2\37 \3\2\2\2 \67\3\2\2\2!#\t\3\2\2\"!\3\2\2\2#$\3\2\2\2$\"\3\2"+
		"\2\2$%\3\2\2\2%\63\3\2\2\2&\62\7a\2\2\')\t\3\2\2(\'\3\2\2\2)*\3\2\2\2"+
		"*(\3\2\2\2*+\3\2\2\2+\62\3\2\2\2,.\t\2\2\2-,\3\2\2\2./\3\2\2\2/-\3\2\2"+
		"\2/\60\3\2\2\2\60\62\3\2\2\2\61&\3\2\2\2\61(\3\2\2\2\61-\3\2\2\2\62\65"+
		"\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\66\35"+
		"\3\2\2\2\66\"\3\2\2\2\67\5\3\2\2\289\7^\2\29B\7^\2\2:;\7^\2\2;B\7&\2\2"+
		"<=\7^\2\2=B\7}\2\2>?\7^\2\2?B\7\177\2\2@B\n\4\2\2A8\3\2\2\2A:\3\2\2\2"+
		"A<\3\2\2\2A>\3\2\2\2A@\3\2\2\2B\7\3\2\2\2CD\7&\2\2DE\5\4\2\2E\t\3\2\2"+
		"\2FG\7&\2\2GH\7}\2\2HI\3\2\2\2IJ\b\5\2\2J\13\3\2\2\2KL\5\4\2\2LM\3\2\2"+
		"\2MN\b\6\3\2N\r\3\2\2\2OP\7^\2\2PY\7^\2\2QR\7^\2\2RY\7&\2\2ST\7^\2\2T"+
		"Y\7}\2\2UV\7^\2\2VY\7\177\2\2WY\n\5\2\2XO\3\2\2\2XQ\3\2\2\2XS\3\2\2\2"+
		"XU\3\2\2\2XW\3\2\2\2Y\17\3\2\2\2Z[\5\b\4\2[\\\3\2\2\2\\]\b\b\4\2]\21\3"+
		"\2\2\2^_\5\n\5\2_`\3\2\2\2`a\b\t\5\2a\23\3\2\2\2bc\7\177\2\2cd\3\2\2\2"+
		"de\b\n\6\2e\25\3\2\2\2fg\5\f\6\2gh\5\24\n\2h\27\3\2\2\2ij\5\f\6\2jk\7"+
		"<\2\2k\31\3\2\2\2lm\5\f\6\2mn\7\61\2\2n\33\3\2\2\2\r\2\3\37$*/\61\63\66"+
		"AX\7\7\3\2\t\3\2\t\5\2\t\6\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}