package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.impl.TemplateSettings
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.util.SystemInfo
import com.intellij.util.io.exists
import com.intellij.util.io.isDirectory
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Collectors


@State(
        name = "SublimeSnippetsSupportSettings",
        storages = [
            Storage("sublimeSnippetsSupport.xml")
        ]
)
class SublimeSnippetsSupportSettings : PersistentStateComponent<SublimeSnippetsSupportSettings.State> {

    companion object {
        const val LIVE_TEMPLATES_GROUP_NAME = "SublimeSnippets"
        fun getInstance(): SublimeSnippetsSupportSettings = ServiceManager.getService(SublimeSnippetsSupportSettings::class.java)
    }

    private var state: State = State()

    class State {
        var sublimeSnippetsRoot: String = guessSublimeLocation()

        private fun guessSublimeLocation(): String {
            return when {
                SystemInfo.isMac -> System.getProperty("user.home") + "/Library/Application Support/Sublime Text 3"
                SystemInfo.isWindows -> System.getProperty("user.home") + "/AppData/Roaming/Sublime Text 3"
                else -> ""
            }
        }
    }

    override fun getState(): SublimeSnippetsSupportSettings.State? {
        return state
    }


    override fun loadState(state: SublimeSnippetsSupportSettings.State) {
        this.state = state
        reloadSublimeSnippetsAsLiveTemplates() // In case the config file was changed externally/
    }


    fun reloadSublimeSnippetsAsLiveTemplates() {
        val templateSettings = TemplateSettings.getInstance() ?: return

        templateSettings.templatesAsList
                .filter { it.groupName == LIVE_TEMPLATES_GROUP_NAME }
                .forEach(templateSettings::removeTemplate)


        val rootDir = Paths.get(this.state.sublimeSnippetsRoot) ?: return
        if (!rootDir.exists() || !rootDir.isDirectory()) return

        Files.walk(rootDir)
                .filter { !it.isDirectory() && it.toString().endsWith(".sublime-snippet") }
                .map(this::toTemplate)
                .collect(Collectors.toList())
                .filterNotNull()
                .forEach(templateSettings::addTemplate)
    }

    private fun toTemplate(sublimeSnippetFile: Path): Template? {
        return SublimeSnippetProcessor.create(sublimeSnippetFile).getLiveTemplate()
    }
}
