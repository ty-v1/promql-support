package io.github.tyv1.idea.promql.language

import com.intellij.psi.TokenType
import io.github.tyv1.idea.promql.language.psi.PromQLTokenType

/**
 * Token types for PromQL language
 */
object PromQLTokenTypes {
    // Whitespace and comments
    @JvmField val WHITE_SPACE = TokenType.WHITE_SPACE
    @JvmField val COMMENT = PromQLTokenType("COMMENT")
    @JvmField val BAD_CHARACTER = TokenType.BAD_CHARACTER

    // Numbers and strings
    @JvmField val NUMBER = PromQLTokenType("NUMBER")
    @JvmField val STRING = PromQLTokenType("STRING")

    // Binary operators
    @JvmField val ADD = PromQLTokenType("ADD")
    @JvmField val SUB = PromQLTokenType("SUB")
    @JvmField val MULT = PromQLTokenType("MULT")
    @JvmField val DIV = PromQLTokenType("DIV")
    @JvmField val MOD = PromQLTokenType("MOD")
    @JvmField val POW = PromQLTokenType("POW")

    // Logical operators
    @JvmField val AND = PromQLTokenType("AND")
    @JvmField val OR = PromQLTokenType("OR")
    @JvmField val UNLESS = PromQLTokenType("UNLESS")

    // Comparison operators
    @JvmField val EQ = PromQLTokenType("EQ")
    @JvmField val DEQ = PromQLTokenType("DEQ")
    @JvmField val NE = PromQLTokenType("NE")
    @JvmField val GT = PromQLTokenType("GT")
    @JvmField val LT = PromQLTokenType("LT")
    @JvmField val GE = PromQLTokenType("GE")
    @JvmField val LE = PromQLTokenType("LE")
    @JvmField val RE = PromQLTokenType("RE")
    @JvmField val NRE = PromQLTokenType("NRE")

    // Aggregation modifiers
    @JvmField val BY = PromQLTokenType("BY")
    @JvmField val WITHOUT = PromQLTokenType("WITHOUT")

    // Join modifiers
    @JvmField val ON = PromQLTokenType("ON")
    @JvmField val IGNORING = PromQLTokenType("IGNORING")
    @JvmField val GROUP_LEFT = PromQLTokenType("GROUP_LEFT")
    @JvmField val GROUP_RIGHT = PromQLTokenType("GROUP_RIGHT")

    // Other keywords
    @JvmField val OFFSET = PromQLTokenType("OFFSET")
    @JvmField val BOOL = PromQLTokenType("BOOL")

    // Functions and aggregation operators
    @JvmField val FUNCTION = PromQLTokenType("FUNCTION")
    @JvmField val AGGREGATION_OPERATOR = PromQLTokenType("AGGREGATION_OPERATOR")

    // Brackets, braces, parentheses
    @JvmField val LEFT_BRACE = PromQLTokenType("LEFT_BRACE")
    @JvmField val RIGHT_BRACE = PromQLTokenType("RIGHT_BRACE")
    @JvmField val LEFT_PAREN = PromQLTokenType("LEFT_PAREN")
    @JvmField val RIGHT_PAREN = PromQLTokenType("RIGHT_PAREN")
    @JvmField val LEFT_BRACKET = PromQLTokenType("LEFT_BRACKET")
    @JvmField val RIGHT_BRACKET = PromQLTokenType("RIGHT_BRACKET")

    // Other punctuation
    @JvmField val COMMA = PromQLTokenType("COMMA")
    @JvmField val AT = PromQLTokenType("AT")

    // Time-related tokens
    @JvmField val TIME_RANGE = PromQLTokenType("TIME_RANGE")
    @JvmField val SUBQUERY_RANGE = PromQLTokenType("SUBQUERY_RANGE")
    @JvmField val DURATION = PromQLTokenType("DURATION")

    // Metric and label names
    @JvmField val METRIC_NAME = PromQLTokenType("METRIC_NAME")
    @JvmField val LABEL_NAME = PromQLTokenType("LABEL_NAME")
}
