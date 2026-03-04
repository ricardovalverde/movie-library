package com.ricardovalverde.movies.di

import com.ricardovalverde.movies.data.network.KtorClient
import com.ricardovalverde.movies.domain.model.locale.DeviceLanguageProvider
import com.ricardovalverde.movies.domain.model.locale.LanguageProvider
import org.koin.dsl.module

val networkModule = module {

    single<LanguageProvider> { DeviceLanguageProvider() }
    single { KtorClient(get()) }
}