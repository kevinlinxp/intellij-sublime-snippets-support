package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.ide.AppLifecycleListener

class SublimeSnippetsSupportAppLifecycleListener : AppLifecycleListener {
    override fun appFrameCreated(commandLineArgs: MutableList<String>) {
        SublimeSnippetsSupportSettingsService.getInstance().reloadSublimeSnippetsAsLiveTemplates()
    }
}