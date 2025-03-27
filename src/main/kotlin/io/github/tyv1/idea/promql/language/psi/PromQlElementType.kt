package io.github.tyv1.idea.promql.language.psi

import com.intellij.psi.tree.IElementType
import io.github.tyv1.idea.promql.language.PromQl

class PromQlElementType(debugName: String) : IElementType(debugName, PromQl.INSTANCE)
