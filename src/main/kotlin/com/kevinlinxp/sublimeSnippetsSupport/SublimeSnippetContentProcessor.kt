package com.kevinlinxp.sublimeSnippetsSupport

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.util.*
import java.util.regex.Pattern

class SublimeSnippetContentProcessor private constructor(content: String) {

    companion object {
        private val VARIABLE_PATTERN: Pattern = Pattern.compile("(?:(?<!\\\\)\\$(\\d+))|(?:(?<!\\\\)\\$\\{(\\d+)((?:(?:[^\\\\}])|(?:\\\\.))*)})")

        fun create(content: String): SublimeSnippetContentProcessor {
            return SublimeSnippetContentProcessor(content
                    .replace(Regex("^\n"), "")
                    .replace(Regex("\n$"), "")
            )
        }
    }

    private val textSegments = ArrayList<LiveTemplateSegment>()
    private val variableDefaultValueMap = TreeMap<Int, String?>()

    init {
        // analyseUsingRegexpStrategy(content)

        try {
            analyseUsingParser(content)
        } catch (e: StringIndexOutOfBoundsException) {
            println(content)
        }
    }

    private fun analyseUsingRegexpStrategy(content: String) {
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

    private fun analyseUsingParser(content: String) {
        val inputStream = CharStreams.fromString(content)
        val lexer = SublimeSnippetContentLexer(inputStream)
        val commonTokenStream = CommonTokenStream(lexer)
        val parser = SublimeSnippetContentParser(commonTokenStream)

        val snippet = parser.snippet()
        snippet.children
                .filterNotNull()
                .forEach {
                    when (it) {
                        is SublimeSnippetContentParser.TextContext -> {
                            val plainText = it.TextCharacter().joinToString(separator = "", transform = { it.text })
                            addPlainTextSegment(plainText)
                        }
                        is SublimeSnippetContentParser.FieldContext -> {
                            val child = it.getChild(0) ?: return@forEach

                            when (child) {
                                is SublimeSnippetContentParser.FieldUnbracketedContext -> {
                                    val varName = child.FieldUnbracketed().text?.substring(1)
                                            ?: return@forEach
                                    val fieldIndex = varName.toIntOrNull()
                                    if (fieldIndex != null) {
                                        addVariableSegment(fieldIndex)
                                    } else {
                                        thisMightBeASublimeSnippetEnvironmentVariableMayAddSupportLater(varName)
                                    }
                                }

                                is SublimeSnippetContentParser.FieldBracketedContext -> {
                                    var varName = child.FieldBracketed().text?.substring(2)
                                            ?: return@forEach
                                    varName = varName.substring(0, varName.length - 1)
                                    val fieldIndex = varName.toIntOrNull()
                                    if (fieldIndex != null) {
                                        addVariableSegment(fieldIndex)
                                    } else {
                                        thisMightBeASublimeSnippetEnvironmentVariableMayAddSupportLater(varName)
                                    }
                                }

                                is SublimeSnippetContentParser.FieldBracketedWithPlaceholderContext -> {
                                    var varName = child.FieldBracketedWithPlaceholderStart().text?.substring(2)
                                            ?: return@forEach
                                    varName = varName.substring(0, varName.length - 1)
                                    val placeholder = child.textInside().joinToString(separator = "", transform = { it.text })
                                    val fieldIndex = varName.toIntOrNull()
                                    if (fieldIndex != null) {
                                        addVariableSegment(fieldIndex, placeholder)
                                    }
                                }

                                is SublimeSnippetContentParser.FieldBracketedWithSubstitutionContext -> {
                                    var varName = child.FieldBracketedWithSubstitution().text
                                            ?: return@forEach
                                    varName = varName.substring(0, varName.length - 1)
                                    val fieldIndex = varName.toIntOrNull()
                                    if (fieldIndex != null) {
                                        addVariableSegment(fieldIndex)
                                    } else {
                                        thisMightBeASublimeSnippetEnvironmentVariableMayAddSupportLater(varName)
                                    }
                                }
                            }
                        }
                    }
                }
    }

    private fun addPlainTextSegment(plainText: String) {
        textSegments.add(PlainTextSegment(plainText))
    }

    private fun addVariableSegment(fieldIndex: Int, placeHolder: String? = null) {
        textSegments.add(VariableSegment(fieldIndex, placeHolder))
    }

    private fun thisMightBeASublimeSnippetEnvironmentVariableMayAddSupportLater(varName: String) {
    }

    private interface LiveTemplateSegment {
        fun text(): String
    }

    private class PlainTextSegment(text: String) : LiveTemplateSegment {

        private val text: String = text
                .replace("\\\\", "\\")
                .replace("\\\$", "$")

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

            return placeHolder ?: ""
        }

    }

    val liveTemplate: String
        get() {
            return textSegments.joinToString(separator = "", transform = { it.text() })
        }

    val variableElements: Set<Map.Entry<Int, String?>>
        get() {
            return variableDefaultValueMap.entries
        }
}
