package com.kevinlinxp.sublimeSnippetsSupport

import com.intellij.openapi.util.SystemInfo

class SublimeSnippetsSupportSettings {
    var sublimeSnippetsRoot: String = guessSublimeLocation()

    private fun guessSublimeLocation(): String {
        return when {
            SystemInfo.isMac -> System.getProperty("user.home") + "/Library/Application Support/Sublime Text 3/Packages"
            SystemInfo.isWindows -> System.getProperty("user.home") + "/AppData/Roaming/Sublime Text 3/Packages"
            SystemInfo.isLinux -> System.getProperty("user.home") + "/.config/sublime-text-3/Packages"
            else -> ""
        }
    }
}