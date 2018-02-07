package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.openapi.Disposable
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.util.io.exists
import com.intellij.util.io.isDirectory
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import javax.swing.*


class SublimeSnippetsSupportSettingsPanel : JPanel(), Disposable {

    companion object {
        const val CARD_NO_SNIPPET: String = "CARD_NO_SNIPPET"
        const val CARD_TOO_MANY_SEARCH_FILES: String = "CARD_TOO_MANY_SEARCH_FILES"
        const val CARD_SNIPPETS_LIST: String = "CARD_SNIPPETS_LIST"
        const val CARD_INVALID_ROOT: String = "CARD_INVALID_ROOT"
        const val FILE_SEARCH_LIMIT: Int = 500
    }

    private val settings: SublimeSnippetsSupportSettings = SublimeSnippetsSupportSettings.getInstance()

    private val pnlCenter: JPanel = JPanel(CardLayout(5, 5))

    private val txtSublimeSnippetsRoot: JTextField = JTextField()
    private val btnSublimeSnippetsRootChooser: JButton = JButton("...")
    private val listModelSublimeSnippetsPreview: DefaultListModel<String> = DefaultListModel();
    private val listSublimeSnippetsPreview: JBList<String> = JBList(listModelSublimeSnippetsPreview)

    init {
        layout = BorderLayout(5, 5)

        val pnlNorth = JPanel()
        pnlNorth.layout = BoxLayout(pnlNorth, BoxLayout.Y_AXIS)
        add(pnlNorth, BorderLayout.NORTH)
        val boxSublimeSnippetsRoot = pnlNorth.add(Box.createHorizontalBox()) as Box
        boxSublimeSnippetsRoot.add(JLabel("Sublime Snippets Root:"))
        boxSublimeSnippetsRoot.add(txtSublimeSnippetsRoot)
        boxSublimeSnippetsRoot.add(btnSublimeSnippetsRootChooser)

        add(pnlCenter, BorderLayout.CENTER)
        pnlCenter.add(createNoSnippetCard(), CARD_NO_SNIPPET)
        pnlCenter.add(createTooManySearchFilesCard(), CARD_TOO_MANY_SEARCH_FILES)
        pnlCenter.add(createSnippetsListCard(), CARD_SNIPPETS_LIST)
        pnlCenter.add(createInvalidRootCard(), CARD_INVALID_ROOT)

        btnSublimeSnippetsRootChooser.addActionListener {
            val currentFile = LocalFileSystem.getInstance()
                    .findFileByPath(txtSublimeSnippetsRoot.text)

            val root = FileChooser.chooseFile(
                    FileChooserDescriptor(false, true, false, false, false, false),
                    this@SublimeSnippetsSupportSettingsPanel, null,
                    currentFile
            )

            if (root != null) {
                updateUIWithData(root.path)
            }
        }

        txtSublimeSnippetsRoot.addFocusListener(object : FocusAdapter() {
            override fun focusLost(e: FocusEvent?) {
                updateUIWithData(txtSublimeSnippetsRoot.text, skipTextField = true)
            }
        })
    }

    private fun createSnippetsListCard(): JPanel {
        val pnl = JPanel(BorderLayout(5, 5))
        pnl.add(JBLabel("Found the following snippets:"), BorderLayout.NORTH)
        pnl.add(JBScrollPane(listSublimeSnippetsPreview), BorderLayout.CENTER)
        return pnl
    }

    private fun createTooManySearchFilesCard(): JPanel {
        val pnl = JPanel(BorderLayout(5, 5))
        pnl.add(JBLabel("Cannot find any sublime snippets after searching $FILE_SEARCH_LIMIT files under this directory. It is suggested to narrow down the search range."), BorderLayout.NORTH)
        return pnl
    }

    private fun createNoSnippetCard(): JPanel {
        val pnl = JPanel(BorderLayout(5, 5))
        pnl.add(JBLabel("No sublime snippets found."), BorderLayout.NORTH)
        return pnl
    }

    private fun createInvalidRootCard(): JPanel {
        val pnl = JPanel(BorderLayout(5, 5))
        pnl.add(JBLabel("Please choose an existing directory."), BorderLayout.NORTH)
        return pnl
    }

    private fun updateUIWithData(newPath: String, skipTextField: Boolean = false) {

        fun checkCardToShow(path: Path): String {
            class Counter {
                var count: Int = 0
            }

            val counter = Counter()
            val found = Files.walk(path)
                    .filter { (!it.isDirectory() && it.toString().endsWith(".sublime-snippet")) || ++counter.count > FILE_SEARCH_LIMIT }
                    .findFirst().isPresent
            return if (!found) {
                CARD_NO_SNIPPET
            } else {
                if (counter.count > FILE_SEARCH_LIMIT) {
                    CARD_TOO_MANY_SEARCH_FILES
                } else {
                    CARD_SNIPPETS_LIST
                }
            }
        }

        if (!skipTextField) {
            txtSublimeSnippetsRoot.text = newPath
        }

        val newRootPath: Path = Paths.get(newPath)
        listModelSublimeSnippetsPreview.removeAllElements()

        if (newRootPath.exists() && newRootPath.isDirectory()) {
            val cardToShow = checkCardToShow(newRootPath)
            if (cardToShow == CARD_SNIPPETS_LIST) {
                Files.walk(newRootPath)
                        .filter { !it.isDirectory() && it.toString().endsWith(".sublime-snippet") }
                        .forEach { listModelSublimeSnippetsPreview.addElement(it.toString()) }
            }
            (pnlCenter.layout as CardLayout).show(pnlCenter, cardToShow)
        } else {
            (pnlCenter.layout as CardLayout).show(pnlCenter, CARD_INVALID_ROOT)
        }

    }


    @Throws(ConfigurationException::class)
    fun apply() {
        val sublimeSnippetsRoot = txtSublimeSnippetsRoot.text
        val path = Paths.get(sublimeSnippetsRoot)
        if (!Files.exists(path)) {
            throw ConfigurationException(sublimeSnippetsRoot + " does not exists.")
        }

        if (!Files.isDirectory(path)) {
            throw ConfigurationException(sublimeSnippetsRoot + " is not a directory")
        }

        val state = settings.state
        if (state != null) {
            state.sublimeSnippetsRoot = sublimeSnippetsRoot
            settings.reloadSublimeSnippetsAsLiveTemplates()
        }
    }


    fun reset() {
        val state = settings.state
        if (state != null) {
            updateUIWithData(state.sublimeSnippetsRoot)
        }
    }

    fun modified(): Boolean {
        val state = settings.state ?: return false

        val sublimeSnippetsRoot = state.sublimeSnippetsRoot
        return sublimeSnippetsRoot != txtSublimeSnippetsRoot.text
    }

    override fun dispose() {
        // Leave for future needs ...
    }
}