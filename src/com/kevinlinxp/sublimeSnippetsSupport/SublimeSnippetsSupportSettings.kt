package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.impl.TemplateImpl
import com.intellij.codeInsight.template.impl.TemplateSettings
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.util.SystemInfo
import com.intellij.util.io.exists
import com.intellij.util.io.isDirectory
import com.intellij.util.loadElement
import org.jdom.Element
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
        val sublimeSnippetDom = loadElement(sublimeSnippetFile)
        val contentElement = sublimeSnippetDom.getChild("content") ?: return null
        val content = contentElement.textTrim
        if (content == "") {
            return null
        }

        val tabTriggerElement = sublimeSnippetDom.getChild("tabTrigger") ?: return null
        val tabTrigger = tabTriggerElement.textTrim
        if (tabTrigger == "") {
            return null
        }

        val scopeElement = sublimeSnippetDom.getChild("scope")
        val contextElement = createContextElementWithSupportedScopes(scopeElement) ?: return null

        val template = TemplateImpl(tabTrigger, content, LIVE_TEMPLATES_GROUP_NAME)
        template.isToReformat = true
        template.isToShortenLongNames = true
        template.isToIndent = true
        template.isDeactivated = false

        template.templateContext.readTemplateContext(contextElement)

        return template
    }

    private fun createContextElementWithSupportedScopes(scopeElement: Element?): Element? {
        if (scopeElement == null) {
            return null
        }

        val scopesStr = scopeElement.textTrim
        if (scopesStr == null || scopesStr == "") {
            return null
        }

        val optionList: List<Element> = scopesStr.split(" *, *".toRegex())
                .mapNotNull { SublimeSnippetScope.byScopeName(it) }
                .filter { it.supportedByIDE }
                .map { it.createContextOption() }
                .toList()

        if (optionList.isEmpty()) {
            return null
        }

        val contextElement = Element("context")

        optionList.forEach {
            contextElement.addContent(it)
        }

        return contextElement
    }
}
