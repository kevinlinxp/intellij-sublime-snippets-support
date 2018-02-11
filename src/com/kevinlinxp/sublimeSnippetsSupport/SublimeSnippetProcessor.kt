package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.impl.TemplateImpl
import com.intellij.util.loadElement
import org.jdom.Element
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.regex.Pattern

class SublimeSnippetProcessor private constructor(sublimeSnippetFile: Path) {

    companion object {

        private val VARIABLE_PATTERN: Pattern = Pattern.compile("(?:(?<!\\\\)\\$(\\d+))|(?:(?<!\\\\)\\$\\{(\\d+)((?:(?:[^\\\\}])|(?:\\\\.))*)})")

        fun create(sublimeSnippetFile: Path): SublimeSnippetProcessor {
            return SublimeSnippetProcessor(sublimeSnippetFile)
        }
    }

    private val sublimeSnippetDom: Element = loadElement(sublimeSnippetFile)

    private val textSegments = ArrayList<LiveTemplateSegment>()
    private val variableDefaultValueMap = TreeMap<Int, String?>()

    fun getTemplate(): Template? {
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

        processContent(content)

        val liveTemplate = getLiveTemplate()

        val template = TemplateImpl(tabTrigger, liveTemplate, SublimeSnippetsSupportSettings.LIVE_TEMPLATES_GROUP_NAME)
        template.isToReformat = true
        template.isToShortenLongNames = true
        template.isToIndent = true
        template.isDeactivated = false

        getVariableElements()
                .forEach { (fieldIndex, defaultValue) ->
                    template.addVariable("VAR$fieldIndex", "", "\"$defaultValue\"", true)
                }
        template.templateContext.readTemplateContext(contextElement)

        return template
    }

    fun processContent(content: String) {
        val m = VARIABLE_PATTERN.matcher(content)
        var matchingStartedFrom = 0
        while (m.find()) {
            val start = m.start()
            val end = m.end()

            if (matchingStartedFrom != start) {
                addPlainTextSegment(content.substring(matchingStartedFrom, start))
            }

            val varName = m.group(1)
            val varNameInBrackets = m.group(2)
            val placeHolder = m.group(3)

            if (varName != null) {
                addVariableSegment(varName.toInt())
            } else if (varNameInBrackets != null) {
                addVariableSegment(varNameInBrackets.toInt(), placeHolder)
            }

            matchingStartedFrom = end
        }

        if (matchingStartedFrom != content.length) {
            addPlainTextSegment(content.substring(matchingStartedFrom))
        }
    }

    private fun addPlainTextSegment(plainText: String) {
        textSegments.add(PlainTextSegment(plainText))
    }

    private fun addVariableSegment(fieldIndex: Int, placeHolder: String? = null) {
        textSegments.add(VariableSegment(fieldIndex, placeHolder))
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

    fun getLiveTemplate(): String {
        return textSegments
                .map { it.text() }
                .reduce { s1, s2 -> s1 + s2 }
    }

    private fun getVariableElements(): Set<Map.Entry<Int, String?>> {
        return variableDefaultValueMap.entries
    }

    private interface LiveTemplateSegment {
        fun text(): String
    }

    private class PlainTextSegment(private val text: String) : LiveTemplateSegment {

        override fun text(): String {
            return this.text
        }

    }

    private inner class VariableSegment(private val fieldIndex: Int, private val placeHolder: String? = null) : LiveTemplateSegment {

        init {
            if (fieldIndex != 0) {
                val defaultVal = variableDefaultValueMap[fieldIndex]
                if (defaultVal == null || defaultVal == "") {
                    variableDefaultValueMap[fieldIndex] = getProcessedPlaceHolder()
                }
            }
        }

        override fun text(): String {
            return if (fieldIndex == 0) "\$END\$" else "\$VAR$fieldIndex\$"
        }

        private fun getProcessedPlaceHolder(): String {
            if (fieldIndex == 0) {
                return ""
            }

            return if (placeHolder == null) {
                ""
            } else if (placeHolder.startsWith(":") && placeHolder.length > 1) {
                placeHolder.substring(1)
            } else {
                ""
            }
        }
    }


}


fun main(args: Array<String>) {
    val resource = SublimeSnippetProcessor::class.java.getResource("/java8.time.LocalDate-to-Date.sublime-snippet").file
    val processor = SublimeSnippetProcessor.create(Paths.get(resource))
    processor.processContent(loadElement(Paths.get(resource)).getChild("content").textTrim)
    println(processor.getLiveTemplate())
}