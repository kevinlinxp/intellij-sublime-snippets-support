package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.openapi.Disposable
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBList
import com.intellij.ui.components.JBScrollPane
import com.intellij.util.io.isDirectory
import java.awt.BorderLayout
import java.awt.CardLayout
import java.awt.event.FocusAdapter
import java.awt.event.FocusEvent
import java.nio.file.Files
import java.nio.file.InvalidPathException
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

    private val settings: SublimeSnippetsSupportSettingsService = SublimeSnippetsSupportSettingsService.getInstance()

    private val pnlCenter: JPanel = JPanel(CardLayout(5, 5))

    private val txtSublimeSnippetsRoot: JTextField = JTextField()
    private val btnSublimeSnippetsRootChooser: JButton = JButton("...")
    private val listModelSublimeSnippetsPreview: DefaultListModel<String> = DefaultListModel()
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
        pnl.add(JBLabel("Cannot find any sublime snippets after searching $FILE_SEARCH_LIMIT entries under this directory. It is suggested to narrow down the search range."), BorderLayout.NORTH)
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

        if (!skipTextField) {
            txtSublimeSnippetsRoot.text = newPath
        }

        listModelSublimeSnippetsPreview.removeAllElements()
        val newRootPath = toExistingDirectory(newPath)

        if (newRootPath == null) {
            (pnlCenter.layout as CardLayout).show(pnlCenter, CARD_INVALID_ROOT)
            return
        }

        val scanResult = scanSnippetsPreview(newRootPath)
        scanResult.snippets.forEach { listModelSublimeSnippetsPreview.addElement(it.toString()) }
        (pnlCenter.layout as CardLayout).show(pnlCenter, scanResult.cardToShow)
    }


    @Throws(ConfigurationException::class)
    fun apply() {
        val sublimeSnippetsRoot = txtSublimeSnippetsRoot.text
        val path = toExistingDirectory(sublimeSnippetsRoot)
        if (path == null) {
            throw ConfigurationException("$sublimeSnippetsRoot does not exists.")
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

    private fun toExistingDirectory(path: String): Path? {
        if (path.isBlank()) {
            return null
        }

        return try {
            Paths.get(path)
                .takeIf { Files.exists(it) && it.isDirectory() }
        } catch (e: InvalidPathException) {
            null
        }
    }

    private fun scanSnippetsPreview(rootPath: Path): SnippetPreviewScanResult {
        val snippets = ArrayList<Path>()
        var searchedEntries = 0

        Files.walk(rootPath).use { paths ->
            val iterator = paths.iterator()
            while (iterator.hasNext()) {
                val path = iterator.next()
                if (path == rootPath) {
                    continue
                }

                searchedEntries++
                if (!path.isDirectory() && path.toString().endsWith(".sublime-snippet")) {
                    snippets.add(path)
                }

                if (searchedEntries >= FILE_SEARCH_LIMIT) {
                    return if (snippets.isEmpty()) {
                        SnippetPreviewScanResult(CARD_TOO_MANY_SEARCH_FILES, emptyList())
                    } else {
                        SnippetPreviewScanResult(CARD_SNIPPETS_LIST, snippets)
                    }
                }
            }
        }

        return if (snippets.isEmpty()) {
            SnippetPreviewScanResult(CARD_NO_SNIPPET, snippets)
        } else {
            SnippetPreviewScanResult(CARD_SNIPPETS_LIST, snippets)
        }
    }

    private data class SnippetPreviewScanResult(
            val cardToShow: String,
            val snippets: List<Path>
    )
}
