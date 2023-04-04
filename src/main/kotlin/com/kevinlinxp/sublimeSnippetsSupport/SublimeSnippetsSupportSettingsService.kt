package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.impl.TemplateSettings
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.diagnostic.Logger
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
class SublimeSnippetsSupportSettingsService : PersistentStateComponent<SublimeSnippetsSupportSettings> {

    companion object {
        const val LIVE_TEMPLATES_GROUP_NAME = "SublimeSnippets"
        fun getInstance(): SublimeSnippetsSupportSettingsService =
            ApplicationManager.getApplication().getService(SublimeSnippetsSupportSettingsService::class.java)
    }

    private val logger: Logger = Logger.getInstance(SublimeSnippetsSupportSettingsService::class.java)

    private var settings: SublimeSnippetsSupportSettings = SublimeSnippetsSupportSettings()

    override fun getState(): SublimeSnippetsSupportSettings? {
        return settings
    }

    override fun loadState(settings: SublimeSnippetsSupportSettings) {
        this.settings = settings
        reloadSublimeSnippetsAsLiveTemplates() // In case the config file was changed externally/
    }

    fun reloadSublimeSnippetsAsLiveTemplates() {
        val templateSettings = TemplateSettings.getInstance() ?: return

        templateSettings.templatesAsList
            .filter { it.groupName == LIVE_TEMPLATES_GROUP_NAME }
            .forEach(templateSettings::removeTemplate)


        val sublimeSnippetsRoot = this.settings.sublimeSnippetsRoot
        if (sublimeSnippetsRoot.isBlank()) return
        try {
            val rootDir = Paths.get(sublimeSnippetsRoot) ?: return
            if (!Files.exists(rootDir) || !rootDir.isDirectory()) {
                logger.error("Invalid root directory. ${suggestUpdatingRootDirectorySettings(sublimeSnippetsRoot)}")
                return
            }

            Files.walk(rootDir)
                .filter { !it.isDirectory() && it.toString().endsWith(".sublime-snippet") }
                .map(this::toTemplate)
                .collect(Collectors.toList())
                .filterNotNull()
                .forEach(templateSettings::addTemplate)
        } catch (e: Exception) {
            logger.error(
                "Exception (${e.message}) occurred while parsing snippets. ${
                    suggestUpdatingRootDirectorySettings(
                        sublimeSnippetsRoot
                    )
                }", e
            )
        }
    }

    private fun toTemplate(sublimeSnippetFile: Path): Template? {
        return try {
            val snippetProcessor = SublimeSnippetProcessor.create(sublimeSnippetFile)
            snippetProcessor.getLiveTemplate()
        } catch (e: Exception) {
            logger.error(
                "Exception (${e.message}) occurred while processing snippet file ${sublimeSnippetFile}, skipping it...",
                e
            )
            null
        }
    }

    private fun suggestUpdatingRootDirectorySettings(currentRoot: String) =
        "Please update the sublime snippets root directory (current value: $currentRoot) in settings and try again."

}
