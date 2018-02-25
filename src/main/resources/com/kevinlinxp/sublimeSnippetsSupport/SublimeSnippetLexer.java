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
		TextCharacter=1, FieldUnbracketed=2, FieldBracketedStart=3, Inside_TextCharacter=4, 
		FieldBracketedEnd=5, VarNameAndEnd=6, VarNameAndPlaceholderStart=7, VarNameAndSubstitutionStart=8;
	public static final int
		Inside=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "Inside"
	};

	public static final String[] ruleNames = {
		"TextCharacter", "FieldUnbracketed", "FieldBracketedStart", "Inside_TextCharacter", 
		"Inside_FieldUnbracketed", "Inside_FieldBracketedStart", "FieldBracketedEnd", 
		"VarNameAndEnd", "VarNameAndPlaceholderStart", "VarNameAndSubstitutionStart", 
		"VarName"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, "'${'", null, "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "TextCharacter", "FieldUnbracketed", "FieldBracketedStart", "Inside_TextCharacter", 
		"FieldBracketedEnd", "VarNameAndEnd", "VarNameAndPlaceholderStart", "VarNameAndSubstitutionStart"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\ni\b\1\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2$\n\2\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\67\n\5\3"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\f\6\fO\n\f\r\f\16\fP\3\f\6\fT\n\f\r\f\16\fU\3\f\3\f"+
		"\6\fZ\n\f\r\f\16\f[\3\f\6\f_\n\f\r\f\16\f`\7\fc\n\f\f\f\16\ff\13\f\5\f"+
		"h\n\f\2\2\r\4\3\6\4\b\5\n\6\f\2\16\2\20\7\22\b\24\t\26\n\30\2\4\2\3\6"+
		"\3\2&&\4\2&&\177\177\3\2\62;\3\2C\\\2v\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2"+
		"\2\2\3\n\3\2\2\2\3\f\3\2\2\2\3\16\3\2\2\2\3\20\3\2\2\2\3\22\3\2\2\2\3"+
		"\24\3\2\2\2\3\26\3\2\2\2\4#\3\2\2\2\6%\3\2\2\2\b(\3\2\2\2\n\66\3\2\2\2"+
		"\f8\3\2\2\2\16<\3\2\2\2\20@\3\2\2\2\22D\3\2\2\2\24G\3\2\2\2\26J\3\2\2"+
		"\2\30g\3\2\2\2\32\33\7^\2\2\33$\7^\2\2\34\35\7^\2\2\35$\7&\2\2\36\37\7"+
		"^\2\2\37$\7}\2\2 !\7^\2\2!$\7\177\2\2\"$\n\2\2\2#\32\3\2\2\2#\34\3\2\2"+
		"\2#\36\3\2\2\2# \3\2\2\2#\"\3\2\2\2$\5\3\2\2\2%&\7&\2\2&\'\5\30\f\2\'"+
		"\7\3\2\2\2()\7&\2\2)*\7}\2\2*+\3\2\2\2+,\b\4\2\2,\t\3\2\2\2-.\7^\2\2."+
		"\67\7^\2\2/\60\7^\2\2\60\67\7&\2\2\61\62\7^\2\2\62\67\7}\2\2\63\64\7^"+
		"\2\2\64\67\7\177\2\2\65\67\n\3\2\2\66-\3\2\2\2\66/\3\2\2\2\66\61\3\2\2"+
		"\2\66\63\3\2\2\2\66\65\3\2\2\2\67\13\3\2\2\289\5\6\3\29:\3\2\2\2:;\b\6"+
		"\3\2;\r\3\2\2\2<=\5\b\4\2=>\3\2\2\2>?\b\7\4\2?\17\3\2\2\2@A\7\177\2\2"+
		"AB\3\2\2\2BC\b\b\5\2C\21\3\2\2\2DE\5\30\f\2EF\5\20\b\2F\23\3\2\2\2GH\5"+
		"\30\f\2HI\7<\2\2I\25\3\2\2\2JK\5\30\f\2KL\7\61\2\2L\27\3\2\2\2MO\t\4\2"+
		"\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Qh\3\2\2\2RT\t\5\2\2SR\3\2\2"+
		"\2TU\3\2\2\2US\3\2\2\2UV\3\2\2\2Vd\3\2\2\2Wc\7a\2\2XZ\t\5\2\2YX\3\2\2"+
		"\2Z[\3\2\2\2[Y\3\2\2\2[\\\3\2\2\2\\c\3\2\2\2]_\t\4\2\2^]\3\2\2\2_`\3\2"+
		"\2\2`^\3\2\2\2`a\3\2\2\2ac\3\2\2\2bW\3\2\2\2bY\3\2\2\2b^\3\2\2\2cf\3\2"+
		"\2\2db\3\2\2\2de\3\2\2\2eh\3\2\2\2fd\3\2\2\2gN\3\2\2\2gS\3\2\2\2h\31\3"+
		"\2\2\2\r\2\3#\66PU[`bdg\6\7\3\2\t\4\2\t\5\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}