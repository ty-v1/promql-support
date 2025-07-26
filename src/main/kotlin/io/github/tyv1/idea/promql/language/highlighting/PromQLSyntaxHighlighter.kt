package io.github.tyv1.idea.promql.language.highlighting

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import io.github.tyv1.idea.promql.language.PromQLLexerAdapter
import io.github.tyv1.idea.promql.language.PromQLTokenTypes
import java.awt.Color
import java.awt.Font

class PromQLSyntaxHighlighter : SyntaxHighlighterBase() {

    companion object {
        // Define TextAttributesKey constants for different token types
        val KEYWORD = TextAttributesKey.createTextAttributesKey(
            "PROMQL_KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD
        )
        val OPERATOR = TextAttributesKey.createTextAttributesKey(
            "PROMQL_OPERATOR",
            DefaultLanguageHighlighterColors.OPERATION_SIGN
        )
        val NUMBER = TextAttributesKey.createTextAttributesKey("PROMQL_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val STRING = TextAttributesKey.createTextAttributesKey("PROMQL_STRING", DefaultLanguageHighlighterColors.STRING)
        val COMMENT =
            TextAttributesKey.createTextAttributesKey("PROMQL_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val FUNCTION =
            TextAttributesKey.createTextAttributesKey("PROMQL_FUNCTION", DefaultLanguageHighlighterColors.FUNCTION_CALL)
        val METRIC_NAME =
            TextAttributesKey.createTextAttributesKey("PROMQL_METRIC_NAME", DefaultLanguageHighlighterColors.IDENTIFIER)
        val LABEL_NAME = TextAttributesKey.createTextAttributesKey(
            "PROMQL_LABEL_NAME",
            DefaultLanguageHighlighterColors.INSTANCE_FIELD
        )
        val DURATION =
            TextAttributesKey.createTextAttributesKey("PROMQL_DURATION", DefaultLanguageHighlighterColors.NUMBER)
        val PARENTHESES = TextAttributesKey.createTextAttributesKey(
            "PROMQL_PARENTHESES",
            DefaultLanguageHighlighterColors.PARENTHESES
        )
        val BRACES = TextAttributesKey.createTextAttributesKey("PROMQL_BRACES", DefaultLanguageHighlighterColors.BRACES)
        val BRACKETS =
            TextAttributesKey.createTextAttributesKey("PROMQL_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val COMMA = TextAttributesKey.createTextAttributesKey("PROMQL_COMMA", DefaultLanguageHighlighterColors.COMMA)
        val BAD_CHARACTER =
            TextAttributesKey.createTextAttributesKey("PROMQL_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)
    }

    override fun getHighlightingLexer(): Lexer {
        return PromQLLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            // Keywords
            PromQLTokenTypes.AND, PromQLTokenTypes.OR, PromQLTokenTypes.UNLESS, PromQLTokenTypes.BY, PromQLTokenTypes.WITHOUT,
            PromQLTokenTypes.ON, PromQLTokenTypes.IGNORING, PromQLTokenTypes.GROUP_LEFT, PromQLTokenTypes.GROUP_RIGHT,
            PromQLTokenTypes.OFFSET, PromQLTokenTypes.BOOL -> pack(KEYWORD)

            // Operators
            PromQLTokenTypes.ADD, PromQLTokenTypes.SUB, PromQLTokenTypes.MULT, PromQLTokenTypes.DIV, PromQLTokenTypes.MOD, PromQLTokenTypes.POW,
            PromQLTokenTypes.EQ, PromQLTokenTypes.DEQ, PromQLTokenTypes.NE, PromQLTokenTypes.GT, PromQLTokenTypes.LT, PromQLTokenTypes.GE, PromQLTokenTypes.LE,
            PromQLTokenTypes.RE, PromQLTokenTypes.NRE, PromQLTokenTypes.AT -> pack(OPERATOR)

            // Numbers and strings
            PromQLTokenTypes.NUMBER -> pack(NUMBER)
            PromQLTokenTypes.STRING -> pack(STRING)

            // Comments
            PromQLTokenTypes.COMMENT -> pack(COMMENT)

            // Functions and aggregation operators
            PromQLTokenTypes.FUNCTION, PromQLTokenTypes.AGGREGATION_OPERATOR -> pack(FUNCTION)

            // Metric and label names
            PromQLTokenTypes.METRIC_NAME -> pack(METRIC_NAME)
            PromQLTokenTypes.LABEL_NAME -> pack(LABEL_NAME)

            // Time-related tokens
            PromQLTokenTypes.DURATION, PromQLTokenTypes.TIME_RANGE, PromQLTokenTypes.SUBQUERY_RANGE -> pack(DURATION)

            // Brackets, braces, parentheses
            PromQLTokenTypes.LEFT_PAREN, PromQLTokenTypes.RIGHT_PAREN -> pack(PARENTHESES)
            PromQLTokenTypes.LEFT_BRACE, PromQLTokenTypes.RIGHT_BRACE -> pack(BRACES)
            PromQLTokenTypes.LEFT_BRACKET, PromQLTokenTypes.RIGHT_BRACKET -> pack(BRACKETS)

            // Other punctuation
            PromQLTokenTypes.COMMA -> pack(COMMA)

            // Bad characters
            PromQLTokenTypes.BAD_CHARACTER -> pack(BAD_CHARACTER)

            // Default case
            else -> TextAttributesKey.EMPTY_ARRAY
        }
    }
}
