package com.ricardovalverde.movies

import platform.Foundation.NSLocale
import platform.Foundation.preferredLanguages
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun getSystemLanguageTag(): String {
    val preferredLanguages = NSLocale.preferredLanguages
    val languageTag = if (preferredLanguages.isNotEmpty()) {
        preferredLanguages[0] as String
    } else {
        "en-US"
    }
    return languageTag
}