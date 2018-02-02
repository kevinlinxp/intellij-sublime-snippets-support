package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.impl.TemplateImpl
import com.intellij.util.io.isDirectory
import com.intellij.util.loadElement
import org.jdom.Element
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Collectors

class SublimeSnippetTemplatesProvider(private val sublimeSnippetsRoot: Path) {

    fun getTemplates(): List<Template> {
        return Files.walk(this.sublimeSnippetsRoot)
                .filter { !it.isDirectory() && it.toString().endsWith(".sublime-snippet") }
                .map { toTemplate(it) }
                .collect(Collectors.toList())
                .filterNotNull()
    }

    private fun toTemplate(sublimeSnippetFile: Path): Template? {
        val sublimeSnippetDom = loadElement(sublimeSnippetFile)
        val contentElement = sublimeSnippetDom.getChild("content") ?: return null
        val content = contentElement.textTrim
        if (content == "") {
            return null
        }

        val tabTriggerElement = sublimeSnippetDom.getChild("tabTrigger") ?: return null
        val tabTrigger = tabTriggerElement.textTrim
        if (tabTrigger == "") {
            return null;
        }

        val scopeElement = sublimeSnippetDom.getChild("scope")
        val contextElement = createContextElement(scopeElement)

        val template = TemplateImpl(tabTrigger, content, "SublimeSnippets")
        template.isToReformat = true
        template.isToShortenLongNames = true
        template.isToIndent = true
        template.isDeactivated = false

        template.templateContext.readTemplateContext(contextElement)

        return template
    }

    private fun createContextElement(scopeElement: Element?): Element {
        val result = Element("context")
        if (scopeElement == null) {
            result.addContent(Element("option")
                    .setAttribute("name", "OTHER")
                    .setAttribute("value", "false"))
            return result
        }

        result.addContent(Element("option")
                .setAttribute("name", "JAVA_CODE")
                .setAttribute("value", "true"))

        return result
    }
}