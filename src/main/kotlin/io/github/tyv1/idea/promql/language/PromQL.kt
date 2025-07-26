package io.github.tyv1.idea.promql.language

import com.intellij.lang.Language

class PromQL private constructor() : Language("PromQL") {
  companion object {
    val INSTANCE = PromQL()
  }
}
