package io.github.tyv1.idea.promql.language.highlighting

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import io.github.tyv1.idea.promql.language.PromQlIcons
import javax.swing.Icon

class PromQLColorSettingsPage : ColorSettingsPage {

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName(): String = "PromQL"

    override fun getIcon(): Icon = PromQlIcons.FILE

    override fun getHighlighter(): SyntaxHighlighter = PromQLSyntaxHighlighter()

    override fun getDemoText(): String = """
        # This is a comment
        sum(rate(http_requests_total{job="api-server", method="POST"}[5m])) by (instance)
        
        # Arithmetic operators
        metric1 + metric2 * 2 - 3 / metric3 % 5 ^ 2
        
        # Comparison operators
        metric > 100
        metric == bool 100
        metric != 100
        metric =~ "regex"
        metric !~ "regex"
        
        # Logical operators
        metric1 and metric2
        metric1 or metric2
        metric1 unless metric2
        
        # Functions
        rate(http_requests_total[5m])
        sum_over_time(http_requests_total[1h:5m])
        
        # Aggregation operators with modifiers
        sum by (instance) (rate(http_requests_total[5m]))
        avg without (instance) (rate(http_requests_total[5m]))
        
        # Join operators
        metric1{foo="bar"} * on(instance) group_left(method) metric2{foo="baz"}
        
        # Offset modifier
        rate(http_requests_total[5m] offset 1h)
        
        # Subquery
        max_over_time(rate(http_requests_total[5m])[1h:1m])
    """.trimIndent()

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null
}

private val DESCRIPTORS = arrayOf(
    AttributesDescriptor("Keyword", PromQLSyntaxHighlighter.KEYWORD),
    AttributesDescriptor("Operator", PromQLSyntaxHighlighter.OPERATOR),
    AttributesDescriptor("Number", PromQLSyntaxHighlighter.NUMBER),
    AttributesDescriptor("String", PromQLSyntaxHighlighter.STRING),
    AttributesDescriptor("Comment", PromQLSyntaxHighlighter.COMMENT),
    AttributesDescriptor("Function", PromQLSyntaxHighlighter.FUNCTION),
    AttributesDescriptor("Metric Name", PromQLSyntaxHighlighter.METRIC_NAME),
    AttributesDescriptor("Label Name", PromQLSyntaxHighlighter.LABEL_NAME),
    AttributesDescriptor("Duration", PromQLSyntaxHighlighter.DURATION),
    AttributesDescriptor("Parentheses", PromQLSyntaxHighlighter.PARENTHESES),
    AttributesDescriptor("Braces", PromQLSyntaxHighlighter.BRACES),
    AttributesDescriptor("Brackets", PromQLSyntaxHighlighter.BRACKETS),
    AttributesDescriptor("Comma", PromQLSyntaxHighlighter.COMMA),
    AttributesDescriptor("Bad Character", PromQLSyntaxHighlighter.BAD_CHARACTER)
)
