package com.ricardovalverde.movies.data.network.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesListResponse(
    val results: List<MovieResponse>
)


@Serializable
data class MovieResponse(
    val id: Int,
    val title: String,
    val overview: String,

    /*
     * Indica o nome do campo na resposta da API.
     * Nas outras propriedades não é necessário, pois os nomes são iguais aos da resposta.
     * A variável deve corresponder exatamente ao nome recebido,
     * mas em Kotlin não é boa prática usar "_" em nomes de variáveis.
     */
    @SerialName("poster_path")
    val posterPath: String,
    val genres: List<GenreResponse>? = null,

    @SerialName("release_date")
    val releaseDate: LocalDate,

    @SerialName("runtime")
    val runTime: Int? = null,

    @SerialName("vote_average")
    val voteAverage: Double
)

@Serializable
data class GenreResponse(
    val id: Int,
    val name: String

)




