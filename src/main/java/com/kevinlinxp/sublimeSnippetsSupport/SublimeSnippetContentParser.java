// Generated from /Users/kelin/Work/kelin/IntelliJPlatform/intellij-sublime-snippets-support/src/main/antlr/SublimeSnippetContentParser.g4 by ANTLR 4.7
package com.kevinlinxp.sublimeSnippetsSupport;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SublimeSnippetContentParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		TextCharacter=1, FieldUnbracketed=2, FieldBracketed=3, FieldBracketedWithPlaceholderStart=4, 
		FieldBracketedWithSubstitution=5, Inside_TextCharacter=6, FieldBracketedEnd=7;
	public static final int
		RULE_snippet = 0, RULE_text = 1, RULE_field = 2, RULE_fieldUnbracketed = 3, 
		RULE_fieldBracketed = 4, RULE_textInside = 5, RULE_fieldBracketedWithPlaceholder = 6, 
		RULE_fieldBracketedWithSubstitution = 7;
	public static final String[] ruleNames = {
		"snippet", "text", "field", "fieldUnbracketed", "fieldBracketed", "textInside", 
		"fieldBracketedWithPlaceholder", "fieldBracketedWithSubstitution"
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

	@Override
	public String getGrammarFileName() { return "SublimeSnippetContentParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SublimeSnippetContentParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class SnippetContext extends ParserRuleContext {
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<TextContext> text() {
			return getRuleContexts(TextContext.class);
		}
		public TextContext text(int i) {
			return getRuleContext(TextContext.class,i);
		}
		public SnippetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_snippet; }
	}

	public final SnippetContext snippet() throws RecognitionException {
		SnippetContext _localctx = new SnippetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_snippet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TextCharacter) | (1L << FieldUnbracketed) | (1L << FieldBracketed) | (1L << FieldBracketedWithPlaceholderStart) | (1L << FieldBracketedWithSubstitution))) != 0)) {
				{
				setState(18);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case FieldUnbracketed:
				case FieldBracketed:
				case FieldBracketedWithPlaceholderStart:
				case FieldBracketedWithSubstitution:
					{
					setState(16);
					field();
					}
					break;
				case TextCharacter:
					{
					setState(17);
					text();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(22);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextContext extends ParserRuleContext {
		public List<TerminalNode> TextCharacter() { return getTokens(SublimeSnippetContentParser.TextCharacter); }
		public TerminalNode TextCharacter(int i) {
			return getToken(SublimeSnippetContentParser.TextCharacter, i);
		}
		public TextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_text; }
	}

	public final TextContext text() throws RecognitionException {
		TextContext _localctx = new TextContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_text);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(24); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(23);
					match(TextCharacter);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(26); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldContext extends ParserRuleContext {
		public FieldUnbracketedContext fieldUnbracketed() {
			return getRuleContext(FieldUnbracketedContext.class,0);
		}
		public FieldBracketedContext fieldBracketed() {
			return getRuleContext(FieldBracketedContext.class,0);
		}
		public FieldBracketedWithPlaceholderContext fieldBracketedWithPlaceholder() {
			return getRuleContext(FieldBracketedWithPlaceholderContext.class,0);
		}
		public FieldBracketedWithSubstitutionContext fieldBracketedWithSubstitution() {
			return getRuleContext(FieldBracketedWithSubstitutionContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_field);
		try {
			setState(32);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FieldUnbracketed:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				fieldUnbracketed();
				}
				break;
			case FieldBracketed:
				enterOuterAlt(_localctx, 2);
				{
				setState(29);
				fieldBracketed();
				}
				break;
			case FieldBracketedWithPlaceholderStart:
				enterOuterAlt(_localctx, 3);
				{
				setState(30);
				fieldBracketedWithPlaceholder();
				}
				break;
			case FieldBracketedWithSubstitution:
				enterOuterAlt(_localctx, 4);
				{
				setState(31);
				fieldBracketedWithSubstitution();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldUnbracketedContext extends ParserRuleContext {
		public TerminalNode FieldUnbracketed() { return getToken(SublimeSnippetContentParser.FieldUnbracketed, 0); }
		public FieldUnbracketedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldUnbracketed; }
	}

	public final FieldUnbracketedContext fieldUnbracketed() throws RecognitionException {
		FieldUnbracketedContext _localctx = new FieldUnbracketedContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fieldUnbracketed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(FieldUnbracketed);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldBracketedContext extends ParserRuleContext {
		public TerminalNode FieldBracketed() { return getToken(SublimeSnippetContentParser.FieldBracketed, 0); }
		public FieldBracketedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldBracketed; }
	}

	public final FieldBracketedContext fieldBracketed() throws RecognitionException {
		FieldBracketedContext _localctx = new FieldBracketedContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fieldBracketed);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			match(FieldBracketed);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TextInsideContext extends ParserRuleContext {
		public List<TerminalNode> Inside_TextCharacter() { return getTokens(SublimeSnippetContentParser.Inside_TextCharacter); }
		public TerminalNode Inside_TextCharacter(int i) {
			return getToken(SublimeSnippetContentParser.Inside_TextCharacter, i);
		}
		public TextInsideContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_textInside; }
	}

	public final TextInsideContext textInside() throws RecognitionException {
		TextInsideContext _localctx = new TextInsideContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_textInside);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(39); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(38);
					match(Inside_TextCharacter);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(41); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldBracketedWithPlaceholderContext extends ParserRuleContext {
		public TerminalNode FieldBracketedWithPlaceholderStart() { return getToken(SublimeSnippetContentParser.FieldBracketedWithPlaceholderStart, 0); }
		public TerminalNode FieldBracketedEnd() { return getToken(SublimeSnippetContentParser.FieldBracketedEnd, 0); }
		public List<FieldContext> field() {
			return getRuleContexts(FieldContext.class);
		}
		public FieldContext field(int i) {
			return getRuleContext(FieldContext.class,i);
		}
		public List<TextInsideContext> textInside() {
			return getRuleContexts(TextInsideContext.class);
		}
		public TextInsideContext textInside(int i) {
			return getRuleContext(TextInsideContext.class,i);
		}
		public FieldBracketedWithPlaceholderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldBracketedWithPlaceholder; }
	}

	public final FieldBracketedWithPlaceholderContext fieldBracketedWithPlaceholder() throws RecognitionException {
		FieldBracketedWithPlaceholderContext _localctx = new FieldBracketedWithPlaceholderContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fieldBracketedWithPlaceholder);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(FieldBracketedWithPlaceholderStart);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FieldUnbracketed) | (1L << FieldBracketed) | (1L << FieldBracketedWithPlaceholderStart) | (1L << FieldBracketedWithSubstitution) | (1L << Inside_TextCharacter))) != 0)) {
				{
				setState(46);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case FieldUnbracketed:
				case FieldBracketed:
				case FieldBracketedWithPlaceholderStart:
				case FieldBracketedWithSubstitution:
					{
					setState(44);
					field();
					}
					break;
				case Inside_TextCharacter:
					{
					setState(45);
					textInside();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			match(FieldBracketedEnd);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldBracketedWithSubstitutionContext extends ParserRuleContext {
		public TerminalNode FieldBracketedWithSubstitution() { return getToken(SublimeSnippetContentParser.FieldBracketedWithSubstitution, 0); }
		public FieldBracketedWithSubstitutionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldBracketedWithSubstitution; }
	}

	public final FieldBracketedWithSubstitutionContext fieldBracketedWithSubstitution() throws RecognitionException {
		FieldBracketedWithSubstitutionContext _localctx = new FieldBracketedWithSubstitutionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fieldBracketedWithSubstitution);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(FieldBracketedWithSubstitution);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\t:\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\7\2\25\n\2"+
		"\f\2\16\2\30\13\2\3\3\6\3\33\n\3\r\3\16\3\34\3\4\3\4\3\4\3\4\5\4#\n\4"+
		"\3\5\3\5\3\6\3\6\3\7\6\7*\n\7\r\7\16\7+\3\b\3\b\3\b\7\b\61\n\b\f\b\16"+
		"\b\64\13\b\3\b\3\b\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\2:\2\26\3\2"+
		"\2\2\4\32\3\2\2\2\6\"\3\2\2\2\b$\3\2\2\2\n&\3\2\2\2\f)\3\2\2\2\16-\3\2"+
		"\2\2\20\67\3\2\2\2\22\25\5\6\4\2\23\25\5\4\3\2\24\22\3\2\2\2\24\23\3\2"+
		"\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\3\3\2\2\2\30\26\3\2"+
		"\2\2\31\33\7\3\2\2\32\31\3\2\2\2\33\34\3\2\2\2\34\32\3\2\2\2\34\35\3\2"+
		"\2\2\35\5\3\2\2\2\36#\5\b\5\2\37#\5\n\6\2 #\5\16\b\2!#\5\20\t\2\"\36\3"+
		"\2\2\2\"\37\3\2\2\2\" \3\2\2\2\"!\3\2\2\2#\7\3\2\2\2$%\7\4\2\2%\t\3\2"+
		"\2\2&\'\7\5\2\2\'\13\3\2\2\2(*\7\b\2\2)(\3\2\2\2*+\3\2\2\2+)\3\2\2\2+"+
		",\3\2\2\2,\r\3\2\2\2-\62\7\6\2\2.\61\5\6\4\2/\61\5\f\7\2\60.\3\2\2\2\60"+
		"/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62"+
		"\3\2\2\2\65\66\7\t\2\2\66\17\3\2\2\2\678\7\7\2\28\21\3\2\2\2\t\24\26\34"+
		"\"+\60\62";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}