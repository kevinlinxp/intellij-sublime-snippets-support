package com.kevinlinxp.sublimeSnippetsSupport;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBTextField;
import com.kevinlinxp.sublimeSnippetsSupport.SublimeSnippetsSupportSettings.State;
import java.awt.BorderLayout;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Ke Lin
 * @version $Id$
 */
public class SublimeSnippetsSupportSettingsPanel extends JPanel implements Disposable {

  private final SublimeSnippetsSupportSettings settings;

  private final JPanel pnlNorth;
  private final JBTextField txtSublimeSnippetsRoot;
  private final JButton btnSublimeSnippetsRootChooser;

  public SublimeSnippetsSupportSettingsPanel() {
    settings = SublimeSnippetsSupportSettings.Companion.getInstance();

    setLayout(new BorderLayout());
    add(pnlNorth = new JPanel(), BorderLayout.NORTH);
    pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.Y_AXIS));
    Box boxSublimeSnippetsRoot = (Box) pnlNorth.add(Box.createHorizontalBox());
    boxSublimeSnippetsRoot.add(new JLabel("Sublime Snippets Root:"));
    boxSublimeSnippetsRoot.add(txtSublimeSnippetsRoot = new JBTextField());
    boxSublimeSnippetsRoot.add(btnSublimeSnippetsRootChooser = new JButton("..."));

    btnSublimeSnippetsRootChooser.addActionListener(e -> {
      VirtualFile current = LocalFileSystem.getInstance()
          .findFileByPath(txtSublimeSnippetsRoot.getText());

      VirtualFile root = FileChooser.chooseFile(
          new FileChooserDescriptor(false, true, false, false, false, false),
          SublimeSnippetsSupportSettingsPanel.this,
          null,
          current
      );

      if (root != null) {
        txtSublimeSnippetsRoot.setText(root.getPath());
      }
    });
  }

  public void apply() throws ConfigurationException {
    String sublimeSnippetsRoot = txtSublimeSnippetsRoot.getText();
    Path path = Paths.get(sublimeSnippetsRoot);
    if (!Files.exists(path)) {
      throw new ConfigurationException(sublimeSnippetsRoot + " does not exists.");
    }

    if (!Files.isDirectory(path)) {
      throw new ConfigurationException(sublimeSnippetsRoot + " is not a directory");
    }

    State state = settings.getState();
    if (state != null) {
      state.setSublimeSnippetsRoot(sublimeSnippetsRoot);
      settings.reloadSublimeSnippetsAsLiveTemplates();
    }
  }


  public void reset() {
    State state = settings.getState();
    if (state != null) {
      txtSublimeSnippetsRoot.setText(state.getSublimeSnippetsRoot());
    }
  }

  public boolean modified() {
    State state = settings.getState();
    if (state == null) {
      return false;
    }

    String sublimeSnippetsRoot = state.getSublimeSnippetsRoot();
    return !sublimeSnippetsRoot.equals(txtSublimeSnippetsRoot.getText());
  }

  @Override
  public void dispose() {
    // Leave for future needs ...
  }
}
