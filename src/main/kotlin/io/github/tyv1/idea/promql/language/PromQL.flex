package io.github.tyv1.idea.promql.language;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import io.github.tyv1.idea.promql.language.psi.PromQlTokenType;

%%

%class PromQLLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

%{
  // Define token types as static fields
  public static final IElementType NUMBER = new PromQlTokenType("NUMBER");
  public static final IElementType STRING = new PromQlTokenType("STRING");

  // Binary operators
  public static final IElementType ADD = new PromQlTokenType("ADD");
  public static final IElementType SUB = new PromQlTokenType("SUB");
  public static final IElementType MULT = new PromQlTokenType("MULT");
  public static final IElementType DIV = new PromQlTokenType("DIV");
  public static final IElementType MOD = new PromQlTokenType("MOD");
  public static final IElementType POW = new PromQlTokenType("POW");

  // Logical operators
  public static final IElementType AND = new PromQlTokenType("AND");
  public static final IElementType OR = new PromQlTokenType("OR");
  public static final IElementType UNLESS = new PromQlTokenType("UNLESS");

  // Comparison operators
  public static final IElementType EQ = new PromQlTokenType("EQ");
  public static final IElementType DEQ = new PromQlTokenType("DEQ");
  public static final IElementType NE = new PromQlTokenType("NE");
  public static final IElementType GT = new PromQlTokenType("GT");
  public static final IElementType LT = new PromQlTokenType("LT");
  public static final IElementType GE = new PromQlTokenType("GE");
  public static final IElementType LE = new PromQlTokenType("LE");
  public static final IElementType RE = new PromQlTokenType("RE");
  public static final IElementType NRE = new PromQlTokenType("NRE");

  // Aggregation modifiers
  public static final IElementType BY = new PromQlTokenType("BY");
  public static final IElementType WITHOUT = new PromQlTokenType("WITHOUT");

  // Join modifiers
  public static final IElementType ON = new PromQlTokenType("ON");
  public static final IElementType IGNORING = new PromQlTokenType("IGNORING");
  public static final IElementType GROUP_LEFT = new PromQlTokenType("GROUP_LEFT");
  public static final IElementType GROUP_RIGHT = new PromQlTokenType("GROUP_RIGHT");

  public static final IElementType OFFSET = new PromQlTokenType("OFFSET");
  public static final IElementType BOOL = new PromQlTokenType("BOOL");

  public static final IElementType AGGREGATION_OPERATOR = new PromQlTokenType("AGGREGATION_OPERATOR");
  public static final IElementType FUNCTION = new PromQlTokenType("FUNCTION");

  // Brackets, braces, parentheses
  public static final IElementType LEFT_BRACE = new PromQlTokenType("LEFT_BRACE");
  public static final IElementType RIGHT_BRACE = new PromQlTokenType("RIGHT_BRACE");
  public static final IElementType LEFT_PAREN = new PromQlTokenType("LEFT_PAREN");
  public static final IElementType RIGHT_PAREN = new PromQlTokenType("RIGHT_PAREN");
  public static final IElementType LEFT_BRACKET = new PromQlTokenType("LEFT_BRACKET");
  public static final IElementType RIGHT_BRACKET = new PromQlTokenType("RIGHT_BRACKET");

  public static final IElementType COMMA = new PromQlTokenType("COMMA");
  public static final IElementType AT = new PromQlTokenType("AT");

  public static final IElementType SUBQUERY_RANGE = new PromQlTokenType("SUBQUERY_RANGE");
  public static final IElementType TIME_RANGE = new PromQlTokenType("TIME_RANGE");
  public static final IElementType DURATION = new PromQlTokenType("DURATION");

  public static final IElementType METRIC_NAME = new PromQlTokenType("METRIC_NAME");
  public static final IElementType LABEL_NAME = new PromQlTokenType("LABEL_NAME");

  public static final IElementType WHITESPACE = TokenType.WHITE_SPACE;
  public static final IElementType COMMENT = new PromQlTokenType("COMMENT");
%}

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
{WHITE_SPACE}         { return WHITESPACE; }
{COMMENT}             { return COMMENT; }

// Numbers and strings
{NUMERAL}|{SCIENTIFIC_NUMBER}  { return NUMBER; }
\'[^\'\\]*(\\.[^\'\\]*)*\'     { return STRING; }
\"[^\"\\]*(\\.[^\"\\]*)*\"     { return STRING; }

// Binary operators
"+"                   { return ADD; }
"-"                   { return SUB; }
"*"                   { return MULT; }
"/"                   { return DIV; }
"%"                   { return MOD; }
"^"                   { return POW; }

// Logical operators
{AND_KEYWORD}         { return AND; }
{OR_KEYWORD}          { return OR; }
{UNLESS_KEYWORD}      { return UNLESS; }

// Comparison operators
"="                   { return EQ; }
"=="                  { return DEQ; }
"!="                  { return NE; }
">"                   { return GT; }
"<"                   { return LT; }
">="                  { return GE; }
"<="                  { return LE; }
"=~"                  { return RE; }
"!~"                  { return NRE; }

// Aggregation modifiers
{BY_KEYWORD}          { return BY; }
{WITHOUT_KEYWORD}     { return WITHOUT; }

// Join modifiers
{ON_KEYWORD}          { return ON; }
{IGNORING_KEYWORD}    { return IGNORING; }
{GROUP_LEFT_KEYWORD}  { return GROUP_LEFT; }
{GROUP_RIGHT_KEYWORD} { return GROUP_RIGHT; }

{OFFSET_KEYWORD}      { return OFFSET; }
{BOOL_KEYWORD}        { return BOOL; }

// Aggregation operators
{AGGREGATION_OPERATOR_KEYWORD}  { return AGGREGATION_OPERATOR; }

// Functions
{FUNCTION_KEYWORD}    { return FUNCTION; }

// Brackets, braces, parentheses
"{"                   { return LEFT_BRACE; }
"}"                   { return RIGHT_BRACE; }
"("                   { return LEFT_PAREN; }
")"                   { return RIGHT_PAREN; }
"["                   { return LEFT_BRACKET; }
"]"                   { return RIGHT_BRACKET; }

","                   { return COMMA; }
"@"                   { return AT; }

// Time-related tokens
"["{DURATION}"]"      { return TIME_RANGE; }
"["{DURATION}":"{DURATION}?"]"  { return SUBQUERY_RANGE; }
{DURATION}            { return DURATION; }

// Metric and label names
{METRIC_NAME_PATTERN}  { return METRIC_NAME; }
{LABEL_NAME_PATTERN}   { return LABEL_NAME; }

// Catch-all rule for unrecognized tokens
[^]                   { return TokenType.BAD_CHARACTER; }