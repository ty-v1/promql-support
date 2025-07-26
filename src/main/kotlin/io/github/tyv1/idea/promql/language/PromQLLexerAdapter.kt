package io.github.tyv1.idea.promql.language

import com.intellij.lexer.FlexAdapter

class PromQLLexerAdapter : FlexAdapter(PromQLLexer(null))
