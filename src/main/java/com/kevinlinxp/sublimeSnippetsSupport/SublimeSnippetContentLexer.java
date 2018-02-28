// Generated from /Users/kelin/Work/kelin/IntelliJPlatform/intellij-sublime-snippets-support/src/main/antlr/SublimeSnippetContentLexer.g4 by ANTLR 4.7
package com.kevinlinxp.sublimeSnippetsSupport;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SublimeSnippetContentLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TextCharacter=1, FieldUnbracketed=2, FieldBracketed=3, FieldBracketedWithPlaceholderStart=4, 
		FieldBracketedWithSubstitution=5, Inside_TextCharacter=6, FieldBracketedEnd=7;
	public static final int
		Inside=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "Inside"
	};

	public static final String[] ruleNames = {
		"VarName", "TextCharacter", "FieldUnbracketed", "FieldBracketed", "FieldBracketedWithPlaceholderStart", 
		"FieldBracketedWithSubstitution", "Inside_TextCharacter", "Inside_FieldUnbracketed", 
		"Inside_FieldBracketed", "Inside_FieldBracketedWithPlaceholderStart", 
		"Inside_FieldBracketedWithSubstitution", "FieldBracketedEnd"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, null, "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "TextCharacter", "FieldUnbracketed", "FieldBracketed", "FieldBracketedWithPlaceholderStart", 
		"FieldBracketedWithSubstitution", "Inside_TextCharacter", "FieldBracketedEnd"
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


	public SublimeSnippetContentLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SublimeSnippetContentLexer.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\t\u0096\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\3\2\6\2\36\n\2\r\2\16\2\37\3\2\6\2#\n\2\r\2"+
		"\16\2$\3\2\3\2\6\2)\n\2\r\2\16\2*\3\2\6\2.\n\2\r\2\16\2/\7\2\62\n\2\f"+
		"\2\16\2\65\13\2\5\2\67\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3B\n"+
		"\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7]\n\7\f\7\16\7`\13\7\3\7\3\7\3\7\3"+
		"\7\7\7f\n\7\f\7\16\7i\13\7\3\7\3\7\5\7m\n\7\3\7\5\7p\n\7\3\7\5\7s\n\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0080\n\b\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\4^g\2\16\4\2\6\3\b\4\n\5\f\6\16\7\20\b\22\2\24\2\26\2\30\2\32"+
		"\t\4\2\3\b\3\2\62;\3\2C\\\3\2&&\3\2\61\61\5\2iikkoo\4\2&&\177\177\2\u00aa"+
		"\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\3\20\3\2"+
		"\2\2\3\22\3\2\2\2\3\24\3\2\2\2\3\26\3\2\2\2\3\30\3\2\2\2\3\32\3\2\2\2"+
		"\4\66\3\2\2\2\6A\3\2\2\2\bC\3\2\2\2\nF\3\2\2\2\fL\3\2\2\2\16T\3\2\2\2"+
		"\20\177\3\2\2\2\22\u0081\3\2\2\2\24\u0085\3\2\2\2\26\u0089\3\2\2\2\30"+
		"\u008e\3\2\2\2\32\u0092\3\2\2\2\34\36\t\2\2\2\35\34\3\2\2\2\36\37\3\2"+
		"\2\2\37\35\3\2\2\2\37 \3\2\2\2 \67\3\2\2\2!#\t\3\2\2\"!\3\2\2\2#$\3\2"+
		"\2\2$\"\3\2\2\2$%\3\2\2\2%\63\3\2\2\2&\62\7a\2\2\')\t\3\2\2(\'\3\2\2\2"+
		")*\3\2\2\2*(\3\2\2\2*+\3\2\2\2+\62\3\2\2\2,.\t\2\2\2-,\3\2\2\2./\3\2\2"+
		"\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61&\3\2\2\2\61(\3\2\2\2\61-\3\2"+
		"\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\67\3\2\2\2\65\63\3\2"+
		"\2\2\66\35\3\2\2\2\66\"\3\2\2\2\67\5\3\2\2\289\7^\2\29B\7^\2\2:;\7^\2"+
		"\2;B\7&\2\2<=\7^\2\2=B\7}\2\2>?\7^\2\2?B\7\177\2\2@B\n\4\2\2A8\3\2\2\2"+
		"A:\3\2\2\2A<\3\2\2\2A>\3\2\2\2A@\3\2\2\2B\7\3\2\2\2CD\7&\2\2DE\5\4\2\2"+
		"E\t\3\2\2\2FG\7&\2\2GH\7}\2\2HI\3\2\2\2IJ\5\4\2\2JK\7\177\2\2K\13\3\2"+
		"\2\2LM\7&\2\2MN\7}\2\2NO\3\2\2\2OP\5\4\2\2PQ\7<\2\2QR\3\2\2\2RS\b\6\2"+
		"\2S\r\3\2\2\2TU\7&\2\2UV\7}\2\2VW\3\2\2\2WX\5\4\2\2X^\7\61\2\2Y]\n\5\2"+
		"\2Z[\7^\2\2[]\7\61\2\2\\Y\3\2\2\2\\Z\3\2\2\2]`\3\2\2\2^_\3\2\2\2^\\\3"+
		"\2\2\2_a\3\2\2\2`^\3\2\2\2ag\7\61\2\2bf\n\5\2\2cd\7^\2\2df\7\61\2\2eb"+
		"\3\2\2\2ec\3\2\2\2fi\3\2\2\2gh\3\2\2\2ge\3\2\2\2hj\3\2\2\2ig\3\2\2\2j"+
		"l\7\61\2\2km\t\6\2\2lk\3\2\2\2lm\3\2\2\2mo\3\2\2\2np\t\6\2\2on\3\2\2\2"+
		"op\3\2\2\2pr\3\2\2\2qs\t\6\2\2rq\3\2\2\2rs\3\2\2\2st\3\2\2\2tu\7\177\2"+
		"\2u\17\3\2\2\2vw\7^\2\2w\u0080\7^\2\2xy\7^\2\2y\u0080\7&\2\2z{\7^\2\2"+
		"{\u0080\7}\2\2|}\7^\2\2}\u0080\7\177\2\2~\u0080\n\7\2\2\177v\3\2\2\2\177"+
		"x\3\2\2\2\177z\3\2\2\2\177|\3\2\2\2\177~\3\2\2\2\u0080\21\3\2\2\2\u0081"+
		"\u0082\5\b\4\2\u0082\u0083\3\2\2\2\u0083\u0084\b\t\3\2\u0084\23\3\2\2"+
		"\2\u0085\u0086\5\n\5\2\u0086\u0087\3\2\2\2\u0087\u0088\b\n\4\2\u0088\25"+
		"\3\2\2\2\u0089\u008a\5\f\6\2\u008a\u008b\3\2\2\2\u008b\u008c\b\13\2\2"+
		"\u008c\u008d\b\13\5\2\u008d\27\3\2\2\2\u008e\u008f\5\16\7\2\u008f\u0090"+
		"\3\2\2\2\u0090\u0091\b\f\6\2\u0091\31\3\2\2\2\u0092\u0093\7\177\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0095\b\r\7\2\u0095\33\3\2\2\2\24\2\3\37$*/\61\63"+
		"\66A\\^eglor\177\b\7\3\2\t\4\2\t\5\2\t\6\2\t\7\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}