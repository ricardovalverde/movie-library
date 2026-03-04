package com.ricardovalverde.movies.data.network

import com.ricardovalverde.movies.data.network.model.CreditsListResponse
import com.ricardovalverde.movies.data.network.model.MovieResponse
import com.ricardovalverde.movies.data.network.model.MoviesListResponse
import com.ricardovalverde.movies.data.network.model.VideosListResponse
import com.ricardovalverde.movies.domain.model.locale.LanguageProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val BASE_URL = "https://api.themoviedb.org"
const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"


class KtorClient(val languageProvider: LanguageProvider) {

    private val client = HttpClient {

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys =
                        true // como nao serao mapeados todos os atributos se nao colocar true quebra
                }
            )
        }

        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzOWY2MzZlOThhOTEzNjljZGZiNWQ1NDMxMGMxNDUxYSIsIm5iZiI6MTc3MTc4NzAxMy4zOTMsInN1YiI6IjY5OWI1MzA1YzU5YWVjMjkxMjBlNTUwNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.3NyRkM-EBmsvYTOTNvv7WXAhFboZquJtWKIE2-786JA",
                        refreshToken = ""
                    )
                }
            }
        }

        install(Logging) {
            logger = Logger.SIMPLE //Esse SIMPLE é mostrado no logcat
            level = LogLevel.ALL //Mostra tanto o header como o body das requisicoes

            //Seguranca. Se o Header que estiver sendo passado for igual a um Header de Authorization, o valor do token é escondido, garantindo maior seguranca
            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }

    }

    suspend fun getMovies(category: String): MoviesListResponse {
        return client.get("$BASE_URL/3/movie/$category") {
            addLanguage()
        }.body()
    }

    suspend fun getMovieDetail(movieId:Int): MovieResponse {
        return client.get("$BASE_URL/3/movie/$movieId") {
            addLanguage()
        }.body()
    }

    suspend fun getCredits(movieId:Int): CreditsListResponse {
        return client.get("$BASE_URL/3/movie/$movieId/credits") {
            addLanguage()
        }.body()
    }

    suspend fun getVideos(movieId:Int): VideosListResponse{
        return client.get("$BASE_URL/3/movie/$movieId/videos"){
            addLanguage()
        }.body()
    }

    private fun HttpRequestBuilder.addLanguage() {
        parameter("language", languageProvider.getLanguageTag())
        println("Language: ${languageProvider.getLanguageTag()}")
    }


}