package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.TemplateContextType
import org.jdom.Element
import java.util.*

enum class SublimeSnippetScope(private val scopeName: String) {

    JAVA_CODE("source.java") {
        override fun internalIsSupportedByAnyIntelliJTemplateContextType(): Boolean {
            return Arrays.stream(TemplateContextType.EP_NAME.extensions)
                    .anyMatch { contextType -> contextType.contextId == "JAVA_CODE" }
        }

        override fun createContextOption(): Element {
            return Element("option")
                    .setAttribute("name", "JAVA_CODE")
                    .setAttribute("value", "true")
        }
    };

    companion object {
        fun byScope(scopeName: String?): SublimeSnippetScope? {
            return Arrays.stream(values())
                    .filter { it.scopeName == scopeName }
                    .findFirst().orElse(null)
        }
    }

    private val supported: Lazy<Boolean>

    init {
        this.supported = lazy { this.internalIsSupportedByAnyIntelliJTemplateContextType() }
    }

    val isSupportedByAnyIntelliJTemplateContextType: Boolean
        get() = this.supported.value

    internal abstract fun internalIsSupportedByAnyIntelliJTemplateContextType(): Boolean
    abstract fun createContextOption(): Element
}