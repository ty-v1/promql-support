package io.github.tyv1.idea.promql.language

import com.intellij.lang.Language

class PromQl private constructor() : Language("PromQL") {
  companion object {
    val INSTANCE = PromQl()
  }
}
