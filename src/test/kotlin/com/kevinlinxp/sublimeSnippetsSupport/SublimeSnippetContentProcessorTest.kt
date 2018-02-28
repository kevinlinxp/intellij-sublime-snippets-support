package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.util.loadElement
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
//import org.antlr.v4.runtime.ANTLRInputStream
//import org.antlr.v4.runtime.CommonTokenStream
//import org.antlr.v4.runtime.tree.ParseTreeVisitor
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.InputStream
import java.util.*


class SublimeSnippetContentProcessorTest {

    companion object {
        private val list = listOf(
                "/java8.time.Date-to-LocalDate",
                "/java8.time.duration-between",
                "/angular.\$interpolate",
                "/jsp.tag.sample"
        )

        private fun streamToString(inputStream: InputStream): String {
            val s = Scanner(inputStream).useDelimiter("\\A")
            return if (s.hasNext()) s.next() else ""
        }
    }

    @Test
    fun testSublimeSnippetContentProcessor() {

        list.forEach {
            val sublimeSnippetStream = SublimeSnippetProcessor::class.java.getResourceAsStream("$it.sublime-snippet")
            val element = loadElement(sublimeSnippetStream)
            val content = element.getChild("content")
            val processor = SublimeSnippetContentProcessor.create(content.text)

            val liveTemplateStream = SublimeSnippetContentProcessor::class.java.getResourceAsStream("$it.live-template")
            val liveTemplate = streamToString(liveTemplateStream)

            assertEquals("$it failed test.", liveTemplate, processor.liveTemplate)
        }

    }

}