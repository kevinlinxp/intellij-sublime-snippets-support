package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.openapi.components.ApplicationComponent

class PluginRegistration : ApplicationComponent {

    private val componentName = "Sublime Snippets Support Component Name"

    override fun getComponentName(): String {
        return componentName
    }

    override fun initComponent() {
        SublimeSnippetsSupportSettings.getInstance().reloadSublimeSnippetsAsLiveTemplates()
    }

    override fun disposeComponent() {
    }
}
