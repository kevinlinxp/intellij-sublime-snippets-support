## intellij-sublime-snippets-support
[This IntelliJ plugin](https://plugins.jetbrains.com/plugin/10446-sublime-snippets-support) enables you to use your [Sublime Text Snippets](http://docs.sublimetext.info/en/latest/extensibility/snippets.html) directly as [Live Templates](https://www.jetbrains.com/help/idea/live-templates.html).

### How to install and use:
- Install this plugin from your IDE:
  - Open **_Settings > Plugins > Browse Plugins_** and then type **_Sublime Snippets Support_** in the search box. You should be able to find this plugin as you typing.
  - Install it and restart your IDE, then the snippets under your Sublime Text Packages folder will be ready to use.

- You can config the directory containing your snippets in **_Settings > Editor > Live Templates > Sublime Snippets_**.

### How it works:
- By default, the plugin will guess the location of your Sublime Text Packages directory and scan it, converting all the snippets found to live templates in the following manner:

  - "tabTrigger" will be used as template abbreviation. Snippets without a tabTrigger will be omitted.

  - "scope" will be mapped to the corresponding contexts supported by your IDE. Snippets without any supported scope will be omitted.

  - Fields, Mirrored Fields are supported.

  - Place Holders are supported, but fields and mirrored fields inside place holders are not.

  - Substitutions in Sublime Text snippets are not supported.

### Please be noted that:
- While you can find the auto-generated live templates grouped as "SublimeSnippets" in **_Settings > Editor > Live Templates_**, it is recommended **NOT** to change the settings for them because they will be recreated every time you start your IDE. Therefore, change your original snippets instead.
