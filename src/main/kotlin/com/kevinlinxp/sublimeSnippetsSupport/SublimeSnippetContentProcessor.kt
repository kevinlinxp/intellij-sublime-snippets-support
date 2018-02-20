package com.kevinlinxp.sublimeSnippetsSupport

import java.util.*
import java.util.regex.Pattern

class SublimeSnippetContentProcessor private constructor(content: String) {

    companion object {
        private val VARIABLE_PATTERN: Pattern = Pattern.compile("(?:(?<!\\\\)\\$(\\d+))|(?:(?<!\\\\)\\$\\{(\\d+)((?:(?:[^\\\\}])|(?:\\\\.))*)})")

        fun create(content: String): SublimeSnippetContentProcessor {
            return SublimeSnippetContentProcessor(content)
        }
    }

    private val textSegments = ArrayList<LiveTemplateSegment>()
    private val variableDefaultValueMap = TreeMap<Int, String?>()

    init {
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

    val liveTemplate: String
        get() {
            return textSegments.joinToString(separator = "", transform = { it.text() }).trim()
        }

    val variableElements: Set<Map.Entry<Int, String?>>
        get() {
            return variableDefaultValueMap.entries
        }
}
