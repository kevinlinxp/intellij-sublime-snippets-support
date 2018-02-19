package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.TemplateContextType
import org.jdom.Element
import java.util.*

enum class SublimeSnippetScope(private val scopeName: String, private val contextId: String) {

    SOURCE_ACTION_SCRIPT("source.actionscript.2", "ActionScript"),
    SOURCE_COFFEE("source.coffee", "CoffeeScript"),
    SOURCE_CSS("source.css", "CSS"),
    SOURCE_CSS_LESS("source.css.less", "LESS"),
    SOURCE_GROOVY("source.groovy", "GROOVY"),
    SOURCE_JAVA("source.java", "JAVA_CODE"),
    SOURCE_JAVA_PROPS("source.java-props", "OTHER"),
    SOURCE_JS("source.js", "JAVA_SCRIPT"),
    SOURCE_JSON("source.json", "JSON"),
    SOURCE_PERL("source.perl", "PERL"),
    SOURCE_PHP("source.php", "PHP"),
    SOURCE_PYTHON("source.python", "PYTHON"),
    SOURCE_RUBY("source.ruby", "RUBY"),
    SOURCE_SASS("source.sass", "SASS"),
    SOURCE_SCALA("source.scala", "SCALA"),
    SOURCE_SHELL("source.shell", "Bash"),
    SOURCE_SQL("source.sql", "SQL"),
    SOURCE_TS("source.ts", "TypeScript"),
    SOURCE_TSX("source.tsx", "TypeScript"),
    TEXT_HTML("text.html", "HTML_TEXT"),
    TEXT_HTML_BASIC("text.html.basic", "HTML_TEXT"),
    TEXT_HTML_JAVADOC("text.html.javadoc", "HTML_TEXT"),
    TEXT_HTML_JSP("text.html.jsp", "JSP"),
    TEXT_XML("text.xml", "XML_TEXT"),
    TEXT_XML_XSL("text.xml.xsl", "XSL_TEXT");

    companion object {

        init {
            val enumSize = values().size
            val uniqueScopesCount = values()
                    .map { it.scopeName.toLowerCase() }
                    .toSet().size
            if (enumSize != uniqueScopesCount) {
                throw IllegalArgumentException("Found duplicated scope names (case-insensitive) in ${SublimeSnippetScope.javaClass.canonicalName}")
            }
        }

        fun byScopeName(scopeName: String?): SublimeSnippetScope? {
            if (scopeName == null) {
                return null
            }

            return Arrays.stream(values())
                    .filter { it.scopeName.equals(scopeName, true) }
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
                .anyMatch { contextType -> contextType.contextId.equals(this.contextId, true) }
    }

    internal fun createContextOption(): Element {
        return Element("option")
                .setAttribute("name", contextId)
                .setAttribute("value", "true")
    }

}