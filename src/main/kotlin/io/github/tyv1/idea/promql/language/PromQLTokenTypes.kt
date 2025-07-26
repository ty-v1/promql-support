package io.github.tyv1.idea.promql.language

import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import io.github.tyv1.idea.promql.language.psi.PromQlTokenType

/**
 * Token types for PromQL language
 */
object PromQLTokenTypes {
    // Whitespace and comments
    @JvmField val WHITE_SPACE = TokenType.WHITE_SPACE
    @JvmField val COMMENT = PromQlTokenType("COMMENT")
    @JvmField val BAD_CHARACTER = TokenType.BAD_CHARACTER

    // Numbers and strings
    @JvmField val NUMBER = PromQlTokenType("NUMBER")
    @JvmField val STRING = PromQlTokenType("STRING")

    // Binary operators
    @JvmField val ADD = PromQlTokenType("ADD")
    @JvmField val SUB = PromQlTokenType("SUB")
    @JvmField val MULT = PromQlTokenType("MULT")
    @JvmField val DIV = PromQlTokenType("DIV")
    @JvmField val MOD = PromQlTokenType("MOD")
    @JvmField val POW = PromQlTokenType("POW")

    // Logical operators
    @JvmField val AND = PromQlTokenType("AND")
    @JvmField val OR = PromQlTokenType("OR")
    @JvmField val UNLESS = PromQlTokenType("UNLESS")

    // Comparison operators
    @JvmField val EQ = PromQlTokenType("EQ")
    @JvmField val DEQ = PromQlTokenType("DEQ")
    @JvmField val NE = PromQlTokenType("NE")
    @JvmField val GT = PromQlTokenType("GT")
    @JvmField val LT = PromQlTokenType("LT")
    @JvmField val GE = PromQlTokenType("GE")
    @JvmField val LE = PromQlTokenType("LE")
    @JvmField val RE = PromQlTokenType("RE")
    @JvmField val NRE = PromQlTokenType("NRE")

    // Aggregation modifiers
    @JvmField val BY = PromQlTokenType("BY")
    @JvmField val WITHOUT = PromQlTokenType("WITHOUT")

    // Join modifiers
    @JvmField val ON = PromQlTokenType("ON")
    @JvmField val IGNORING = PromQlTokenType("IGNORING")
    @JvmField val GROUP_LEFT = PromQlTokenType("GROUP_LEFT")
    @JvmField val GROUP_RIGHT = PromQlTokenType("GROUP_RIGHT")

    // Other keywords
    @JvmField val OFFSET = PromQlTokenType("OFFSET")
    @JvmField val BOOL = PromQlTokenType("BOOL")

    // Functions and aggregation operators
    @JvmField val FUNCTION = PromQlTokenType("FUNCTION")
    @JvmField val AGGREGATION_OPERATOR = PromQlTokenType("AGGREGATION_OPERATOR")

    // Brackets, braces, parentheses
    @JvmField val LEFT_BRACE = PromQlTokenType("LEFT_BRACE")
    @JvmField val RIGHT_BRACE = PromQlTokenType("RIGHT_BRACE")
    @JvmField val LEFT_PAREN = PromQlTokenType("LEFT_PAREN")
    @JvmField val RIGHT_PAREN = PromQlTokenType("RIGHT_PAREN")
    @JvmField val LEFT_BRACKET = PromQlTokenType("LEFT_BRACKET")
    @JvmField val RIGHT_BRACKET = PromQlTokenType("RIGHT_BRACKET")

    // Other punctuation
    @JvmField val COMMA = PromQlTokenType("COMMA")
    @JvmField val AT = PromQlTokenType("AT")

    // Time-related tokens
    @JvmField val TIME_RANGE = PromQlTokenType("TIME_RANGE")
    @JvmField val SUBQUERY_RANGE = PromQlTokenType("SUBQUERY_RANGE")
    @JvmField val DURATION = PromQlTokenType("DURATION")

    // Metric and label names
    @JvmField val METRIC_NAME = PromQlTokenType("METRIC_NAME")
    @JvmField val LABEL_NAME = PromQlTokenType("LABEL_NAME")
}
