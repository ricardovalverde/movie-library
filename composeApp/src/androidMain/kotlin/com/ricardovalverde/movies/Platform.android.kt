package com.ricardovalverde.movies

import android.os.Build
import androidx.compose.ui.text.intl.Locale

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getSystemLanguageTag(): String{
    return Locale.current.toLanguageTag()
}