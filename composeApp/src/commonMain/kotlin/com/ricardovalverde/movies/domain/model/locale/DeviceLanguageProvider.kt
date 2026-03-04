package com.ricardovalverde.movies.domain.model.locale

import com.ricardovalverde.movies.getSystemLanguageTag


class DeviceLanguageProvider : LanguageProvider {
    override fun getLanguageTag(): String = getSystemLanguageTag()
}