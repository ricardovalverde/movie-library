package com.ricardovalverde.movies

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getSystemLanguageTag(): String