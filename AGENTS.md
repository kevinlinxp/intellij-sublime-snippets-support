# AGENTS.md

## Project Overview

This repository is an IntelliJ Platform plugin named "Sublime Snippets Support". It scans Sublime Text `.sublime-snippet` files and converts supported snippets into IntelliJ Live Templates under the `SublimeSnippets` template group.

The plugin is written mostly in Kotlin, with generated ANTLR Java parser sources checked into `src/main/java`.

## Local Toolchain

- Use JDK 17. The plugin targets IntelliJ IDEA 2023.1, and the current Gradle wrapper is Gradle 7.6.1.
- Prefer the checked-in Gradle wrapper over a system Gradle install:

  ```sh
  ./gradlew clean check buildPlugin
  ```

- If your shell defaults to another JDK, set `JAVA_HOME` before running Gradle:

  ```sh
  export JAVA_HOME=/path/to/jdk-17
  export PATH="$JAVA_HOME/bin:$PATH"
  ```

- `buildPlugin` creates the installable plugin ZIP under `build/distributions/`.
- `runIde` starts an IntelliJ IDEA 2023.1 sandbox with the plugin installed.

## Secrets and Local Files

- Do not commit secrets.
- Publishing uses `intellijPublishToken` when present.
- The preferred project-local secret file is an ignored `local.properties` file in the repository root:

  ```properties
  intellijPublishToken=your-token-here
  ```

- `local.properties`, `.gradle-user-home/`, `build/`, `bin/`, `out/`, and IDE metadata are ignored and should stay untracked.
- `.gradle-user-home/` is only needed when a constrained environment cannot write to the normal Gradle user home at `~/.gradle`.

## Source Layout

- `src/main/resources/META-INF/plugin.xml` declares the plugin metadata, app lifecycle listener, settings configurable, and application service.
- `src/main/kotlin/com/kevinlinxp/sublimeSnippetsSupport/` contains plugin behavior:
  - `SublimeSnippetsSupportAppLifecycleListener.kt` reloads snippets when the app frame is created.
  - `SublimeSnippetsSupportSettingsService.kt` owns persisted settings and imports snippets into IntelliJ live templates.
  - `SublimeSnippetsSupportSettingsPanel.kt` implements the settings UI and snippet preview.
  - `SublimeSnippetProcessor.kt` parses each `.sublime-snippet` XML file and creates a `Template`.
  - `SublimeSnippetContentProcessor.kt` converts Sublime snippet placeholders into IntelliJ live template text and variables.
  - `SublimeSnippetScope.kt` maps Sublime scopes to IntelliJ live template context IDs.
- `src/main/antlr/` contains ANTLR grammars.
- `src/main/java/.../SublimeSnippetContentLexer.java` and `SublimeSnippetContentParser.java` are generated ANTLR sources and are currently committed intentionally.
- `src/test/kotlin/` contains parser/conversion tests backed by fixtures in `src/test/resources/`.

## Development Notes

- Keep edits compatible with IntelliJ Platform 2023.1 unless the target version is intentionally changed.
- Avoid adding `kotlin-stdlib` manually; the Kotlin Gradle plugin already contributes it.
- The Gradle ANTLR plugin is intentionally not used because this project relies on generated parser sources checked into `src/main/java`.
- When changing snippet parsing behavior, add or update `.sublime-snippet` and `.live-template` fixtures in `src/test/resources/`, then run `./gradlew check`.
- When changing plugin lifecycle, settings persistence, or template registration, prefer an IntelliJ sandbox check with `./gradlew runIde` in addition to unit tests.
- Treat generated or local output directories (`build/`, `bin/`, `.gradle/`, `.gradle-user-home/`) as disposable.

## Code Review Notes

- Be careful with `Files.walk(...)`; it returns a closeable stream. Existing code has calls that should be wrapped with `.use { ... }` when touched.
- `SublimeSnippetScope.kt` currently uses `Extensions.getRootArea()`, which is deprecated in newer IntelliJ Platform APIs. Prefer newer extension APIs when doing compatibility work.
- Existing tests focus on snippet content conversion. Broader coverage is still thin for directory scanning, settings UI behavior, lifecycle reloads, and scope-to-context registration.

## Agent Workflow

- Start by checking `git status --short` and preserve unrelated user changes.
- Use `rg` or `rg --files` for repo searches.
- Keep changes small and aligned with the existing Kotlin/Gradle style.
- Use `apply_patch` for manual edits.
- Verify build-impacting changes with at least:

  ```sh
  ./gradlew check
  ```

- For packaging or environment work, run:

  ```sh
  ./gradlew clean check buildPlugin
  ```

