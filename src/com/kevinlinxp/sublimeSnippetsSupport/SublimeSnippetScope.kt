package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.TemplateContextType
import org.jdom.Element
import java.util.*

enum class SublimeSnippetScope(private val scopeName: String, private val contextId: String) {

    SOURCE_JAVA("source.java", "JAVA_CODE"),
    SOURCE_JS("source.js", "JAVA_SCRIPT"),
    TEXT_XML("text.xml", "XML_TEXT"),
    TEXT_XML_XSL("text.xml.xsl", "XSL_TEXT")
    ;

    companion object {

        init {
            val enumSize = values().size
            val uniqueScopesCount = values().map(SublimeSnippetScope::scopeName).toSet().size
            if (enumSize != uniqueScopesCount) {
                throw IllegalArgumentException("Found duplicated scope names in ${SublimeSnippetScope.javaClass.canonicalName}")
            }
        }

        fun byScopeName(scopeName: String?): SublimeSnippetScope? {
            return Arrays.stream(values())
                    .filter { it.scopeName == scopeName }
                    .findFirst().orElse(null)
        }
    }

    private val supported: Lazy<Boolean>

    init {
        this.supported = lazy { this.checkIfSupported() }
    }

    val supportedByIDE: Boolean
        get() = this.supported.value

    private fun checkIfSupported(): Boolean {
        return Arrays.stream(TemplateContextType.EP_NAME.extensions)
                .anyMatch { contextType -> contextType.contextId == this.contextId }
    }

    internal fun createContextOption(): Element {
        return Element("option")
                .setAttribute("name", contextId)
                .setAttribute("value", "true")
    }

}