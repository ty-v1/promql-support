package io.github.tyv1.idea.promql.language.psi

import com.intellij.psi.tree.IElementType
import io.github.tyv1.idea.promql.language.PromQl

class PromQlTokenType(debugName: String) : IElementType(debugName, PromQl.INSTANCE) {
  override fun toString(): String {
    return "PromQlTokenType." + super.toString()
  }
}
