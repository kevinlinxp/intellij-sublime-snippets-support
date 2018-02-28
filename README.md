## intellij-sublime-snippets-support
[This plugin](https://plugins.jetbrains.com/plugin/10446-sublime-snippets-support) enables you to use your [Sublime Text Snippets](http://docs.sublimetext.info/en/latest/extensibility/snippets.html) directly as IntelliJ [Live Templates](https://www.jetbrains.com/help/idea/live-templates.html).

### How to install and use:
In your IDE:
- Open **_Settings > Plugins > Browse Plugins_** and then type **_Sublime Snippets Support_** in the search box. You should be able to find this plugin as you typing.
- Install it and restart your IDE, then the snippets under your Sublime Text Packages folder will be ready to use.

You can config the directory containing your snippets in **_Settings > Editor > Live Templates > Sublime Snippets_**.

### How it works:
When your IDE starts up, this plugin will scan the snippets directory, which if not specified, is your Sublime Text Packages folder. All the snippets found will be converted to live templates in the following manner:
- _tabTrigger_ will be used as _template abbreviation_. Snippets without a tabTrigger will be omitted.
- _scope_ will be mapped to the [_corresponding contexts supported by your IDE_](https://github.com/kevinlinxp/intellij-sublime-snippets-support/blob/master/src/main/kotlin/com/kevinlinxp/sublimeSnippetsSupport/SublimeSnippetScope.kt). Snippets without any supported scope will be omitted.
- _Fields_ and _Mirrored Fields_ are supported.
- _Placeholders_ are supported, however, fields and mirrored fields inside placeholders are not supported.
- _Substitutions_ are not supported. They will be treated as _Mirrored Fields_ instead.

### Please be noted that:
While you can find the auto-generated live templates grouped as "SublimeSnippets" in **_Settings > Editor > Live Templates_**, it is recommended **NOT** to update them because they will be recreated every time you start your IDE. If you do wish to change them, edit the original sublime snippets instead.

### If you would like to setup a development environment for this project:
- Gradle is used to manage this project, please refer to the [official guide](https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html) if you have any questions.
- While [ANTLR](http://www.antlr.org/) is used to parse the snippet content, the [Gradle ANTLR plugin](https://docs.gradle.org/current/userguide/antlr_plugin.html) is not adopted in this project due to a [known issue](https://github.com/gradle/gradle/issues/2565). Instead, I prefer to generate the lexer/parser source files with the IntelliJ [ANTLR v4 grammar plugin](https://plugins.jetbrains.com/plugin/7358-antlr-v4-grammar-plugin).
