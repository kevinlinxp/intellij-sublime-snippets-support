package com.kevinlinxp.sublimeSnippetsSupport;

import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.intellij.openapi.util.Disposer;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author Ke Lin
 * @version $Id$
 */
public class SublimeSnippetsSupportConfigurable extends BaseConfigurable implements
    SearchableConfigurable, Configurable.NoScroll {

  public final static String ID = "com.kevinlinxp.sublimeSnippetsSupport";

  private SublimeSnippetsSupportSettingsPanel myPanel;

  @NotNull
  @Override
  public String getId() {
    return ID;
  }

  @Nls
  @Override
  public String getDisplayName() {
    return "Sublime Snippets Support";
  }

  @Nullable
  @Override
  public JComponent createComponent() {
    myPanel = new SublimeSnippetsSupportSettingsPanel();
    return myPanel;
  }

  @Override
  public void apply() throws ConfigurationException {
    myPanel.apply();
  }

  @Override
  public void reset() {
    myPanel.reset();
  }

  public void disposeUIResources() {
    if (myPanel != null) {
      Disposer.dispose(myPanel);
    }
    myPanel = null;
  }
}
