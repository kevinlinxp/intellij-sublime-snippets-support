package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.impl.TemplateSettings
import com.intellij.openapi.components.ApplicationComponent
import java.nio.file.Path
import java.nio.file.Paths

class PluginRegistration : ApplicationComponent {

    private val componentName = "Sublime Snippets Support Component Name"

    override fun getComponentName(): String {
        return componentName
    }

    override fun initComponent() {
        addSublimeSnippetsTemplates()
    }

    private fun addSublimeSnippetsTemplates() {
        val sublimeSnippetsRoot = findSublimeSnippetsRoot() ?: return
        SublimeSnippetTemplatesProvider(sublimeSnippetsRoot)
                .getTemplates()
                .forEach(TemplateSettings.getInstance()::addTemplate)
    }

    private fun findSublimeSnippetsRoot(): Path? = Paths.get("/Users/kelin/Library/Application Support/Sublime Text 3/Packages/User/sublimeSnippets")

    override fun disposeComponent() {
    }
}
