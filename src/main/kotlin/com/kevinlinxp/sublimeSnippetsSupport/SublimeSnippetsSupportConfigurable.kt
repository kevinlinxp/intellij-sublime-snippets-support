package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.openapi.options.BaseConfigurable
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.util.Disposer
import javax.swing.JComponent

private class SublimeSnippetsSupportConfigurable : BaseConfigurable(), SearchableConfigurable, Configurable.NoScroll {

    companion object {
        const val ID = "com.kevinlinxp.sublimeSnippetsSupport"
    }

    private var myPanel: SublimeSnippetsSupportSettingsPanel? = null

    override fun getId(): String {
        return ID
    }

    override fun getDisplayName(): String {
        return "Sublime Snippets Support"
    }

    override fun createComponent(): JComponent? {
        myPanel = SublimeSnippetsSupportSettingsPanel()
        return myPanel
    }

    @Throws(ConfigurationException::class)
    override fun apply() {
        myPanel?.apply()
    }

    override fun isModified(): Boolean {
        return myPanel?.modified() ?: false
    }

    override fun reset() {
        myPanel?.reset()
    }

    override fun disposeUIResources() {
        if (myPanel != null) {
            Disposer.dispose(myPanel!!)
        }
        myPanel = null
    }
}
