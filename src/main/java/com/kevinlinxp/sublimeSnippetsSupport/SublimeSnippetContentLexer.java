// Generated from /Users/kelin/Work/kelin/SublimeSnippetsSupport/src/main/antlr/SublimeSnippetContentLexer.g4 by ANTLR 4.7
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
		TextCharacter=1, FieldUnbracketed=2, FieldBracketed=3, FieldBracketedStart=4, 
		Inside_TextCharacter=5, FieldBracketedEnd=6, VarNameAndPlaceholderStart=7, 
		VarNameAndSubstitutionStart=8, RegexpReplacementAndOptions=9;
	public static final int
		Inside=1, InRegexpReplacement=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "Inside", "InRegexpReplacement"
	};

	public static final String[] ruleNames = {
		"VarName", "TextCharacter", "FieldUnbracketed", "FieldBracketed", "FieldBracketedStart", 
		"Inside_TextCharacter", "Inside_FieldUnbracketed", "Inside_FieldBracketed", 
		"Inside_FieldBracketedStart", "FieldBracketedEnd", "VarNameAndPlaceholderStart", 
		"VarNameAndSubstitutionStart", "RegexpReplacementAndOptions"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'${'", null, "'}'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "TextCharacter", "FieldUnbracketed", "FieldBracketed", "FieldBracketedStart", 
		"Inside_TextCharacter", "FieldBracketedEnd", "VarNameAndPlaceholderStart", 
		"VarNameAndSubstitutionStart", "RegexpReplacementAndOptions"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\13\u0095\b\1\b\1"+
		"\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4"+
		"\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\6\2!\n\2\r\2\16\2\"\3\2"+
		"\6\2&\n\2\r\2\16\2\'\3\2\3\2\6\2,\n\2\r\2\16\2-\3\2\6\2\61\n\2\r\2\16"+
		"\2\62\7\2\65\n\2\f\2\16\28\13\2\5\2:\n\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\5\3E\n\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7^\n\7\3\b\3\b\3\b\3\b\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\7\16|\n\16\f\16\16\16\177\13\16\3\16\3\16"+
		"\3\16\3\16\7\16\u0085\n\16\f\16\16\16\u0088\13\16\3\16\3\16\5\16\u008c"+
		"\n\16\3\16\5\16\u008f\n\16\3\16\5\16\u0092\n\16\3\16\3\16\4}\u0086\2\17"+
		"\5\2\7\3\t\4\13\5\r\6\17\7\21\2\23\2\25\2\27\b\31\t\33\n\35\13\5\2\3\4"+
		"\b\3\2\62;\3\2C\\\3\2&&\4\2&&\177\177\3\2\61\61\5\2iikkoo\2\u00a8\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\3\17\3\2\2\2\3\21\3\2\2"+
		"\2\3\23\3\2\2\2\3\25\3\2\2\2\3\27\3\2\2\2\3\31\3\2\2\2\3\33\3\2\2\2\4"+
		"\35\3\2\2\2\59\3\2\2\2\7D\3\2\2\2\tF\3\2\2\2\13I\3\2\2\2\rO\3\2\2\2\17"+
		"]\3\2\2\2\21_\3\2\2\2\23c\3\2\2\2\25g\3\2\2\2\27l\3\2\2\2\31p\3\2\2\2"+
		"\33s\3\2\2\2\35}\3\2\2\2\37!\t\2\2\2 \37\3\2\2\2!\"\3\2\2\2\" \3\2\2\2"+
		"\"#\3\2\2\2#:\3\2\2\2$&\t\3\2\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2"+
		"\2\2(\66\3\2\2\2)\65\7a\2\2*,\t\3\2\2+*\3\2\2\2,-\3\2\2\2-+\3\2\2\2-."+
		"\3\2\2\2.\65\3\2\2\2/\61\t\2\2\2\60/\3\2\2\2\61\62\3\2\2\2\62\60\3\2\2"+
		"\2\62\63\3\2\2\2\63\65\3\2\2\2\64)\3\2\2\2\64+\3\2\2\2\64\60\3\2\2\2\65"+
		"8\3\2\2\2\66\64\3\2\2\2\66\67\3\2\2\2\67:\3\2\2\28\66\3\2\2\29 \3\2\2"+
		"\29%\3\2\2\2:\6\3\2\2\2;<\7^\2\2<E\7^\2\2=>\7^\2\2>E\7&\2\2?@\7^\2\2@"+
		"E\7}\2\2AB\7^\2\2BE\7\177\2\2CE\n\4\2\2D;\3\2\2\2D=\3\2\2\2D?\3\2\2\2"+
		"DA\3\2\2\2DC\3\2\2\2E\b\3\2\2\2FG\7&\2\2GH\5\5\2\2H\n\3\2\2\2IJ\7&\2\2"+
		"JK\7}\2\2KL\3\2\2\2LM\5\5\2\2MN\7\177\2\2N\f\3\2\2\2OP\7&\2\2PQ\7}\2\2"+
		"QR\3\2\2\2RS\b\6\2\2S\16\3\2\2\2TU\7^\2\2U^\7^\2\2VW\7^\2\2W^\7&\2\2X"+
		"Y\7^\2\2Y^\7}\2\2Z[\7^\2\2[^\7\177\2\2\\^\n\5\2\2]T\3\2\2\2]V\3\2\2\2"+
		"]X\3\2\2\2]Z\3\2\2\2]\\\3\2\2\2^\20\3\2\2\2_`\5\t\4\2`a\3\2\2\2ab\b\b"+
		"\3\2b\22\3\2\2\2cd\5\13\5\2de\3\2\2\2ef\b\t\4\2f\24\3\2\2\2gh\5\r\6\2"+
		"hi\3\2\2\2ij\b\n\2\2jk\b\n\5\2k\26\3\2\2\2lm\7\177\2\2mn\3\2\2\2no\b\13"+
		"\6\2o\30\3\2\2\2pq\5\5\2\2qr\7<\2\2r\32\3\2\2\2st\5\5\2\2tu\7\61\2\2u"+
		"v\3\2\2\2vw\b\r\7\2w\34\3\2\2\2x|\n\6\2\2yz\7^\2\2z|\7\61\2\2{x\3\2\2"+
		"\2{y\3\2\2\2|\177\3\2\2\2}~\3\2\2\2}{\3\2\2\2~\u0080\3\2\2\2\177}\3\2"+
		"\2\2\u0080\u0086\7\61\2\2\u0081\u0085\n\6\2\2\u0082\u0083\7^\2\2\u0083"+
		"\u0085\7\61\2\2\u0084\u0081\3\2\2\2\u0084\u0082\3\2\2\2\u0085\u0088\3"+
		"\2\2\2\u0086\u0087\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0089\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0089\u008b\7\61\2\2\u008a\u008c\t\7\2\2\u008b\u008a\3"+
		"\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\3\2\2\2\u008d\u008f\t\7\2\2\u008e"+
		"\u008d\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u0092\t\7"+
		"\2\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093"+
		"\u0094\b\16\6\2\u0094\36\3\2\2\2\25\2\3\4\"\'-\62\64\669D]{}\u0084\u0086"+
		"\u008b\u008e\u0091\b\7\3\2\t\4\2\t\5\2\t\6\2\6\2\2\7\4\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}