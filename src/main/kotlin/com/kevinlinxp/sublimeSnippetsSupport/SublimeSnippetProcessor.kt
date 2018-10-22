package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.impl.TemplateImpl
import com.intellij.ide.plugins.PluginManager
import com.intellij.util.loadElement
import org.jdom.Element
import java.nio.file.Path

class SublimeSnippetProcessor private constructor(sublimeSnippetFile: Path) {

    companion object {
        fun create(sublimeSnippetFile: Path): SublimeSnippetProcessor {
            return SublimeSnippetProcessor(sublimeSnippetFile)
        }
    }

    private val sublimeSnippetDom: Element?

    init {
        var sublimeSnippetDom: Element? = null
        try {
            sublimeSnippetDom = loadElement(sublimeSnippetFile)
        } catch (e: Exception) {
            PluginManager.getLogger().error("Failed to parse $sublimeSnippetFile due to ${e.message}, skipped.", e)
        }
        this.sublimeSnippetDom = sublimeSnippetDom
    }

    fun getLiveTemplate(): Template? {
        if (sublimeSnippetDom == null) {
            return null
        }

        val contentElement = sublimeSnippetDom.getChild("content") ?: return null
        val content = contentElement.textTrim
        if (content == null || content == "") {
            return null
        }

        val tabTriggerElement = sublimeSnippetDom.getChild("tabTrigger") ?: return null
        val tabTrigger = tabTriggerElement.textTrim
        if (tabTrigger == null || tabTrigger == "") {
            return null
        }

        val scopeElement = sublimeSnippetDom.getChild("scope") ?: return null
        val contextElement = createContextElementWithSupportedScopes(scopeElement) ?: return null

        val contentProcessor = SublimeSnippetContentProcessor.create(content)

        val liveTemplate = contentProcessor.liveTemplate

        val template = TemplateImpl(tabTrigger, liveTemplate, SublimeSnippetsSupportSettings.LIVE_TEMPLATES_GROUP_NAME)
        template.isToReformat = true
        template.isToShortenLongNames = true
        template.isToIndent = true
        template.isDeactivated = false

        val description = sublimeSnippetDom.getChild("description")
        template.description = description?.textTrim

        contentProcessor.variableElements
                .forEach { (fieldIndex, defaultValue) ->
                    template.addVariable("VAR$fieldIndex", "", "\"$defaultValue\"", true)
                }
        template.templateContext.readTemplateContext(contextElement)

        return template
    }

    private fun createContextElementWithSupportedScopes(scopeElement: Element): Element? {
        val scopesStr = scopeElement.textTrim
        if (scopesStr == null || scopesStr == "") {
            return null
        }

        val optionList: List<Element> = scopesStr.split(" *, *".toRegex())
                .mapNotNull { SublimeSnippetScope.byScopeName(it) }
                .filter { it.supportedByIDE }
                .map { it.createContextOption() }
                .toList()

        if (optionList.isEmpty()) {
            return null
        }

        val contextElement = Element("context")

        optionList.forEach {
            contextElement.addContent(it)
        }

        return contextElement
    }


}
