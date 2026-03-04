package com.ricardovalverde.movies.data.mapper

import com.ricardovalverde.movies.data.network.IMAGE_BASE_URL
import com.ricardovalverde.movies.data.network.model.CastMemberResponse
import com.ricardovalverde.movies.data.network.model.MovieResponse
import com.ricardovalverde.movies.domain.model.ImageSize
import com.ricardovalverde.movies.domain.model.Movie
import com.ricardovalverde.movies.utils.formatRating

fun MovieResponse.toModel(
    castMemberResponse: List<CastMemberResponse>? = null,
    movieTrailerYoutubeKey: String? = null,
    imageSize: ImageSize = ImageSize.SMALL
) = Movie(
    id = this.id,
    title = this.title,
    overview = this.overview,
    posterUrl = "$IMAGE_BASE_URL/${imageSize.size}/${this.posterPath}",
    genres = this.genres?.map { it.toModel() },
    year = this.getYearFromReleaseDate(),
    duration = this.getDurationInHoursAndMinutes(),
    rating = this.voteAverage.formatRating(),
    castMembers = castMemberResponse
        ?.filter { it.department == "Acting" }
        ?.take(20)
        ?.map { it.toModel() },
    movieTrailerYoutubeKey = movieTrailerYoutubeKey
)

private fun MovieResponse.getYearFromReleaseDate(): Int {
    return this.releaseDate.year
}

private fun MovieResponse.getDurationInHoursAndMinutes(): String? {
    return this.runTime?.let { runtimeMinutes ->

        val hours = runtimeMinutes / 60
        val minutes = runtimeMinutes % 60

        buildString {
            if (hours > 0) {
                append("${hours}h ")
            }
            append("${minutes}min")
        }
    }
}