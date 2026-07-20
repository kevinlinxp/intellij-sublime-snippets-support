# intellij-sublime-snippets-support
This IntelliJ IDEA plugin enables the IDE to support [Sublime Text Snippets](http://docs.sublimetext.info/en/latest/extensibility/snippets.html).

- JetBrains Marketplace URL: https://plugins.jetbrains.com/plugin/10446-sublime-snippets-support


## How it works
When your IDE starts up, this plugin will scan the folder containing your Sublime Text snippets:
- by default, it's the `Packages` folder under your Sublime Text installation path
- or you can config the folder, see [configuration](#configuration)

Each Sublime Text snippet found will be converted to [Live Templates](https://www.jetbrains.com/help/idea/live-templates.html) in the following way:
- _tabTrigger_ will be used as _template abbreviation_. Snippets without a tabTrigger will be omitted.
- _scope_ will be mapped to the [_corresponding contexts supported by your IDE_](https://github.com/kevinlinxp/intellij-sublime-snippets-support/blob/master/src/main/kotlin/com/kevinlinxp/sublimeSnippetsSupport/SublimeSnippetScope.kt). Snippets without any supported scope will be omitted.
- _Fields_ and _Mirrored Fields_ are supported.
- _Placeholders_ are supported, however, fields and mirrored fields inside placeholders are not supported.
- _Substitutions_ are not supported. They will be treated as _Mirrored Fields_ instead.

> While you can find the auto-generated live templates grouped as "SublimeSnippets" in **_Settings > Editor > Live Templates_**, it is recommended **NOT** to update them because they will be recreated every time you start your IDE. If you do wish to change them, edit the original sublime snippets instead.


## Install and Use

### Install
- Open **_Settings > Plugins > Browse Plugins_** and then type **_Sublime Snippets Support_** in the search box. You should be able to find this plugin as you typing.
- Install it and restart your IDE.

### Configuration
**_Settings > Editor > Live Templates > Sublime Snippets_**


## Build & Run

### Prerequisites
- Ensure environment variable `JAVA_HOME` exists and points to a **Java 17** distribution
- Ensure environment variable `PATH` correctly includes the $JAVA_HOME/bin

### Build

#### To build the plugin
```sh
./gradlew clean check buildPlugin
```

- `check` compiles and runs the test suite
- `buildPlugin` creates the installable ZIP under `build/distributions/`

#### To run the plugin within an IntelliJ IDEA sandbox
```sh
./gradlew runIde
```

### Publish
Gradle property `intellijPublishToken=...` is required for publishing and is intentionally separate from building. 

To publish, do either
- create an ignored `local.properties` file in the project root with `intellijPublishToken=...`
- or pass it with `-PintellijPublishToken=...`

### Known issue
- While [ANTLR](http://www.antlr.org/) is used to parse the snippet content, the [Gradle ANTLR plugin](https://docs.gradle.org/current/userguide/antlr_plugin.html) is not adopted in this project due to a [known issue](https://github.com/gradle/gradle/issues/2565). Instead, I prefer to generate the lexer/parser source files with [ANTLR v4 grammar plugin](https://plugins.jetbrains.com/plugin/7358-antlr-v4-grammar-plugin).
