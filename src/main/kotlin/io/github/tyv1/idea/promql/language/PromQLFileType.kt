package io.github.tyv1.idea.promql.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon


class PromQLFileType : LanguageFileType(PromQl.INSTANCE) {

  override fun getName(): String {
    return "PromQL File"
  }

  override fun getDescription(): String {
    // TODO add description
    return "PromQL"
  }

  override fun getDefaultExtension(): String {
    return ".promql"
  }

  override fun getIcon(): Icon {
    return PromQlIcons.FILE
  }
}
