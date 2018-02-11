package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.util.loadElement
import org.jdom.Element
import org.jdom.output.XMLOutputter
import java.util.*
import java.util.regex.Pattern

class SublimeSnippetProcessor(sublimeSnippetContent: String) {

    companion object {

        private val VARIABLE_PATTERN: Pattern = Pattern.compile("(?:(?<!\\\\)\\$(\\d+))|(?:(?<!\\\\)\\$\\{(\\d+)((?:(?:[^\\\\}])|(?:\\\\.))*)})")

        fun create(sublimeSnippetContent: String): SublimeSnippetProcessor {
            return SublimeSnippetProcessor(sublimeSnippetContent)
        }
    }

    private val variableDefaultValueMap = TreeMap<Int, String?>()
    private val textSegments = ArrayList<LiveTemplateSegment>()

    init {
        val m = VARIABLE_PATTERN.matcher(sublimeSnippetContent)
        var matchingStartedFrom = 0
        while (m.find()) {
            val start = m.start()
            val end = m.end()

            if (matchingStartedFrom != start) {
                textSegments.add(PlainTextSegment(sublimeSnippetContent.substring(matchingStartedFrom, start)))
            }

            val varName = m.group(1)
            val varNameInBrackets = m.group(2)
            val placeHolder = m.group(3)

            if (varName != null) {
                textSegments.add(VariableSegment(varName.toInt()))
            } else if (varNameInBrackets != null) {
                textSegments.add(VariableSegment(varNameInBrackets.toInt(), placeHolder))
            }

            matchingStartedFrom = end
        }

        if (matchingStartedFrom != sublimeSnippetContent.length) {
            textSegments.add(PlainTextSegment(sublimeSnippetContent.substring(matchingStartedFrom)))
        }
    }

    fun getLiveTemplate(): String {
        return textSegments
                .map { it.text() }
                .reduce { s1, s2 -> s1 + s2 }
    }

    fun getVariableElements(): List<Element> {
        return variableDefaultValueMap.entries
                .map { (fieldIndex, defaultValue) ->
                    Element("variable")
                            .setAttribute("name", "VAR$fieldIndex")
                            .setAttribute("defaultValue", defaultValue)
                            .setAttribute("alwaysStopAt", "true")
                }
    }

    internal interface LiveTemplateSegment {
        fun text(): String
    }

    internal class PlainTextSegment(private val text: String) : LiveTemplateSegment {

        override fun text(): String {
            return this.text
        }

    }

    internal inner class VariableSegment(private val fieldIndex: Int, private val placeHolder: String? = null) : LiveTemplateSegment {

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

    val resource = SublimeSnippetProcessor::class.java.getResourceAsStream("/java8.time.LocalDate-to-Date.sublime-snippet")
    val element = loadElement(resource)
    val content = element.getChild("content")

    val xmlOutputter = XMLOutputter()
    val create = SublimeSnippetProcessor.create(content.text)
    println(create.getLiveTemplate())
    create.getVariableElements()
            .forEach { println(xmlOutputter.outputString(it)) }
}

