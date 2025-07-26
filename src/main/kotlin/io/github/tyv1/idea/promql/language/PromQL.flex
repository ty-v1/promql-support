package io.github.tyv1.idea.promql.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import io.github.tyv1.idea.promql.language.PromQLTokenTypes;

%%

%class PromQLLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

// Define states
%state IN_COMMENT

// Define macros (similar to ANTLR fragments)
NUMERAL = [0-9]+ ("." [0-9]+)?
SCIENTIFIC_NUMBER = {NUMERAL} ("e" [-+]? {NUMERAL})?
DURATION_UNIT = "ms"|[smhdwy]
DURATION = [0-9]+ {DURATION_UNIT}+

// Whitespace and comments
WHITE_SPACE = [ \t\r\n]+
COMMENT = "#" [^\n]* \n?

// Case insensitive keywords (except for functions, label names, and metric names)
AND_KEYWORD = [aA][nN][dD]
OR_KEYWORD = [oO][rR]
UNLESS_KEYWORD = [uU][nN][lL][eE][sS][sS]
BY_KEYWORD = [bB][yY]
WITHOUT_KEYWORD = [wW][iI][tT][hH][oO][uU][tT]
ON_KEYWORD = [oO][nN]
IGNORING_KEYWORD = [iI][gG][nN][oO][rR][iI][nN][gG]
GROUP_LEFT_KEYWORD = [gG][rR][oO][uU][pP]_[lL][eE][fF][tT]
GROUP_RIGHT_KEYWORD = [gG][rR][oO][uU][pP]_[rR][iI][gG][hH][tT]
OFFSET_KEYWORD = [oO][fF][fF][sS][eE][tT]
BOOL_KEYWORD = [bB][oO][oO][lL]

// Aggregation operators (case insensitive)
AGGREGATION_OPERATOR_KEYWORD = [sS][uU][mM] | [mM][iI][nN] | [mM][aA][xX] | [aA][vV][gG] | [gG][rR][oO][uU][pP] | 
                              [sS][tT][dD][dD][eE][vV] | [sS][tT][dD][vV][aA][rR] | [cC][oO][uU][nN][tT] | 
                              [cC][oO][uU][nN][tT]_[vV][aA][lL][uU][eE][sS] | [bB][oO][tT][tT][oO][mM][kK] | 
                              [tT][oO][pP][kK] | [qQ][uU][aA][nN][tT][iI][lL][eE]

// Functions (case sensitive)
FUNCTION_KEYWORD = "abs" | "absent" | "absent_over_time" | "ceil" | "changes" | "clamp" | "clamp_max" | 
                  "clamp_min" | "day_of_month" | "day_of_week" | "day_of_year" | "days_in_month" | 
                  "delta" | "deriv" | "exp" | "floor" | "histogram_count" | "histogram_sum" | 
                  "histogram_fraction" | "histogram_quantile" | "holt_winters" | "hour" | "idelta" | 
                  "increase" | "irate" | "label_join" | "label_replace" | "ln" | "log2" | "log10" | 
                  "minute" | "month" | "predict_linear" | "rate" | "resets" | "round" | "scalar" | 
                  "sgn" | "sort" | "sort_desc" | "sqrt" | "time" | "timestamp" | "vector" | "year" | 
                  "avg_over_time" | "min_over_time" | "max_over_time" | "sum_over_time" | 
                  "count_over_time" | "quantile_over_time" | "stddev_over_time" | "stdvar_over_time" | 
                  "last_over_time" | "present_over_time" | "acos" | "acosh" | "asin" | "asinh" | 
                  "atan" | "atanh" | "cos" | "cosh" | "sin" | "sinh" | "tan" | "tanh" | "deg" | 
                  "pi" | "rad"

// Metric and label names
METRIC_NAME_PATTERN = [a-zA-Z_:][a-zA-Z0-9_:]*
LABEL_NAME_PATTERN = [a-zA-Z_][a-zA-Z0-9_]*

%%

// Rules section

// Whitespace and comments
{WHITE_SPACE}         { return PromQLTokenTypes.WHITE_SPACE; }
{COMMENT}             { return PromQLTokenTypes.COMMENT; }

// Numbers and strings
{NUMERAL}|{SCIENTIFIC_NUMBER}  { return PromQLTokenTypes.NUMBER; }
\'[^\'\\]*(\\.[^\'\\]*)*\'     { return PromQLTokenTypes.STRING; }
\"[^\"\\]*(\\.[^\"\\]*)*\"     { return PromQLTokenTypes.STRING; }

// Binary operators
"+"                   { return PromQLTokenTypes.ADD; }
"-"                   { return PromQLTokenTypes.SUB; }
"*"                   { return PromQLTokenTypes.MULT; }
"/"                   { return PromQLTokenTypes.DIV; }
"%"                   { return PromQLTokenTypes.MOD; }
"^"                   { return PromQLTokenTypes.POW; }

// Logical operators
{AND_KEYWORD}         { return PromQLTokenTypes.AND; }
{OR_KEYWORD}          { return PromQLTokenTypes.OR; }
{UNLESS_KEYWORD}      { return PromQLTokenTypes.UNLESS; }

// Comparison operators
"="                   { return PromQLTokenTypes.EQ; }
"=="                  { return PromQLTokenTypes.DEQ; }
"!="                  { return PromQLTokenTypes.NE; }
">"                   { return PromQLTokenTypes.GT; }
"<"                   { return PromQLTokenTypes.LT; }
">="                  { return PromQLTokenTypes.GE; }
"<="                  { return PromQLTokenTypes.LE; }
"=~"                  { return PromQLTokenTypes.RE; }
"!~"                  { return PromQLTokenTypes.NRE; }

// Aggregation modifiers
{BY_KEYWORD}          { return PromQLTokenTypes.BY; }
{WITHOUT_KEYWORD}     { return PromQLTokenTypes.WITHOUT; }

// Join modifiers
{ON_KEYWORD}          { return PromQLTokenTypes.ON; }
{IGNORING_KEYWORD}    { return PromQLTokenTypes.IGNORING; }
{GROUP_LEFT_KEYWORD}  { return PromQLTokenTypes.GROUP_LEFT; }
{GROUP_RIGHT_KEYWORD} { return PromQLTokenTypes.GROUP_RIGHT; }

{OFFSET_KEYWORD}      { return PromQLTokenTypes.OFFSET; }
{BOOL_KEYWORD}        { return PromQLTokenTypes.BOOL; }

// Aggregation operators
{AGGREGATION_OPERATOR_KEYWORD}  { return PromQLTokenTypes.AGGREGATION_OPERATOR; }

// Functions
{FUNCTION_KEYWORD}    { return PromQLTokenTypes.FUNCTION; }

// Brackets, braces, parentheses
"{"                   { return PromQLTokenTypes.LEFT_BRACE; }
"}"                   { return PromQLTokenTypes.RIGHT_BRACE; }
"("                   { return PromQLTokenTypes.LEFT_PAREN; }
")"                   { return PromQLTokenTypes.RIGHT_PAREN; }
"["                   { return PromQLTokenTypes.LEFT_BRACKET; }
"]"                   { return PromQLTokenTypes.RIGHT_BRACKET; }

","                   { return PromQLTokenTypes.COMMA; }
"@"                   { return PromQLTokenTypes.AT; }

// Time-related tokens
"["{DURATION}"]"      { return PromQLTokenTypes.TIME_RANGE; }
"["{DURATION}":"{DURATION}?"]"  { return PromQLTokenTypes.SUBQUERY_RANGE; }
{DURATION}            { return PromQLTokenTypes.DURATION; }

// Metric and label names
{METRIC_NAME_PATTERN}  { return PromQLTokenTypes.METRIC_NAME; }
{LABEL_NAME_PATTERN}   { return PromQLTokenTypes.LABEL_NAME; }

// Catch-all rule for unrecognized tokens
[^]                   { return PromQLTokenTypes.BAD_CHARACTER; }