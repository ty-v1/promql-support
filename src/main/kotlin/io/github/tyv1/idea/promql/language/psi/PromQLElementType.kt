package io.github.tyv1.idea.promql.language.psi

import com.intellij.psi.tree.IElementType
import io.github.tyv1.idea.promql.language.PromQL

class PromQLElementType(debugName: String) : IElementType(debugName, PromQL.INSTANCE)
