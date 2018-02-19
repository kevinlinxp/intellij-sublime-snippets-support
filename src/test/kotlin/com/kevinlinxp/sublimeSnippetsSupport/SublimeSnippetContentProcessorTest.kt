package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.util.loadElement
import org.junit.Test


class SublimeSnippetContentProcessorTest {

    @Test
    fun testSublimeSnippetContentProcessor() {
        val resource = SublimeSnippetProcessor::class.java.getResourceAsStream("/java8.time.Date-to-LocalDate.sublime-snippet")
        val element = loadElement(resource)
        val content = element.getChild("content")

        val create = SublimeSnippetContentProcessor.create(content.text)
        println(create.liveTemplate)
    }

}