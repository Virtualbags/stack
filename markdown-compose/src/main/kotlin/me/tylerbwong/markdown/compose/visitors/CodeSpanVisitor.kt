package me.tylerbwong.markdown.compose.visitors

import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import org.intellij.markdown.MarkdownElementTypes
import org.intellij.markdown.ast.ASTNode

internal object CodeSpanVisitor : Visitor {

    override val supportedTypes = listOf(MarkdownElementTypes.CODE_SPAN)

    override fun accept(
        node: ASTNode,
        builder: AnnotatedString.Builder,
        content: String,
        inlineTextContent: MutableMap<String, InlineTextContent>,
        linkPositions: MutableMap<IntRange, String>,
        continuation: Continuation
    ) {
        builder.withStyle(
            SpanStyle(
                fontFamily = FontFamily.Monospace,
                background = Color.LightGray
            )
        ) {
            node.children
                // Drop "`" token before and after
                .drop(1)
                .dropLast(1)
                .forEach { continuation(it, content) }
        }
    }
}
