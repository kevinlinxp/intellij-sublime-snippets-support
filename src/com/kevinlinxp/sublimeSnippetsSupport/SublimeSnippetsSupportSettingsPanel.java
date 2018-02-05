package com.kevinlinxp.sublimeSnippetsSupport;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.ConfigurationException;
import javax.swing.JPanel;

/**
 * @author Ke Lin
 * @version $Id$
 */
public class SublimeSnippetsSupportSettingsPanel extends JPanel implements Disposable {

  @Override
  public void dispose() {
    // Leave for future needs ...
  }

  public void apply() throws ConfigurationException {

  }

  public void reset() {

  }
}
