package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.Template
import com.intellij.testFramework.LoggedErrorProcessor
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import java.nio.file.Files

class SublimeSnippetProcessorTest : BasePlatformTestCase() {

    fun testMissingTabTriggerReturnsNull() {
        assertNull(
                liveTemplateFor(
                        """
                        <snippet>
                          <content><![CDATA[println("hello")]]></content>
                          <scope>source.java</scope>
                        </snippet>
                        """.trimIndent()
                )
        )
    }

    fun testBlankTabTriggerReturnsNull() {
        assertNull(
                liveTemplateFor(
                        """
                        <snippet>
                          <content><![CDATA[println("hello")]]></content>
                          <tabTrigger></tabTrigger>
                          <scope>source.java</scope>
                        </snippet>
                        """.trimIndent()
                )
        )
    }

    fun testEmptyContentReturnsNull() {
        assertNull(
                liveTemplateFor(
                        """
                        <snippet>
                          <content><![CDATA[]]></content>
                          <tabTrigger>empty-content</tabTrigger>
                          <scope>source.java</scope>
                        </snippet>
                        """.trimIndent()
                )
        )
    }

    fun testUnsupportedScopeReturnsNull() {
        assertNull(
                liveTemplateFor(
                        """
                        <snippet>
                          <content><![CDATA[println("hello")]]></content>
                          <tabTrigger>unsupported-scope</tabTrigger>
                          <scope>source.unsupported-language</scope>
                        </snippet>
                        """.trimIndent()
                )
        )
    }

    fun testMixedSupportedAndUnsupportedScopesReturnsTemplate() {
        val template = liveTemplateFor(
                """
                <snippet>
                  <content><![CDATA[println("hello")]]></content>
                  <tabTrigger>mixed-scope</tabTrigger>
                  <scope>source.unsupported-language, source.java-props</scope>
                </snippet>
                """.trimIndent()
        )

        assertNotNull(template)
        assertEquals("mixed-scope", template!!.key)
        assertEquals("""println("hello")""", template.string)
    }

    fun testMalformedSnippetXmlReturnsNull() {
        LoggedErrorProcessor.executeAndReturnLoggedError {
            assertNull(
                    liveTemplateFor(
                            """
                            <snippet>
                              <content><![CDATA[println("hello")]]></content>
                            """.trimIndent()
                    )
            )
        }
    }

    private fun liveTemplateFor(snippetXml: String): Template? {
        val snippetFile = Files.createTempFile("sublime-snippet-processor-test-", ".sublime-snippet")
        return try {
            Files.writeString(snippetFile, snippetXml)
            SublimeSnippetProcessor.create(snippetFile).getLiveTemplate()
        } finally {
            Files.deleteIfExists(snippetFile)
        }
    }
}
