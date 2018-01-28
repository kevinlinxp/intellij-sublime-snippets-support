package com.kevinlinxp.sublime_snippets_support

import com.intellij.codeInsight.template.Template
import com.intellij.codeInsight.template.impl.TemplateImpl
import com.intellij.codeInsight.template.impl.TemplateSettings
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.components.ApplicationComponent
import com.intellij.openapi.ui.Messages
import com.intellij.util.io.isDirectory
import com.intellij.util.loadElement
import org.jdom.Element
import java.nio.file.Files
import java.nio.file.Paths

class PluginRegistration : ApplicationComponent {

    private val componentName = "Sublime Snippets Support Component Name"

    override fun getComponentName(): String {
        return componentName
    }

    override fun initComponent() {
        val am = ActionManager.getInstance();
        val action = SublimeSnippetsSupportSwitchAction()

        am.registerAction(componentName, action)

        // Gets an instance of the WindowMenu action group.
        val windowM = am.getAction("ToolsMenu") as DefaultActionGroup
        // Adds a separator and a new menu command to the WindowMenu group on the main menu.
        windowM.addSeparator()
        windowM.add(action)

        addSublimeSnippetsTemplates()
    }

    private fun addSublimeSnippetsTemplates() {
        val templateSettings = TemplateSettings.getInstance()
        val sublimeSnippetsRoot = ""
        Files.walk(Paths.get(sublimeSnippetsRoot)).forEach{
            if (it.isDirectory()) {
                return@forEach
            }

            if (it.endsWith(".sublime-snippet")) {
                return@forEach
            }

            val sublimeSnippetDom = loadElement(it)
            val template = createLiveTemplate(sublimeSnippetDom)
            templateSettings.addTemplate(template)
        }
    }

    private fun createLiveTemplate(sublimeSnippetDom: Element): Template {
        return TemplateImpl("key", "group")
    }

    override fun disposeComponent() {
    }
}


// If you register the action from Java code, this constructor is used to set the menu item name
// (optionally, you can specify the menu description and an icon to display next to the menu item).
// You can omit this constructor when registering the action in the plugin.xml file.
class SublimeSnippetsSupportSwitchAction : AnAction
// Set the menu item name.
("Enable Sublime Snippets Support")
// Set the menu item name, description and icon.
// super("Text _Boxes","Item description", IconLoader.getIcon("/Mypackage/icon.png"));
{

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        val txt = Messages.showInputDialog(project, "What is your name?", "Input your name",
                Messages.getQuestionIcon())
        Messages.showMessageDialog(
                project,
                "Hello, $txt!\n I am glad to see you.",
                "Information",
                Messages.getInformationIcon()
        )

    }
}
