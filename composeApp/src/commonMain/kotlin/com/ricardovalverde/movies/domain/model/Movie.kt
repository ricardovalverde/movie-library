package com.ricardovalverde.movies.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterUrl: String,
    val genres: List<Genre>?,
    val year: Int,
    val duration: String?,
    val rating: String,
    val castMembers: List<CastMember>?,
    val movieTrailerYoutubeKey: String?


)



//Fake Object
val movie1 = Movie(
    id = 1,
    title = "Movie 1",
    overview = "Overview 1",
    posterUrl = "https://examples.com/poster1.jpg",
    genres = listOf(genre1,genre2),
    year = 2022,
    duration = "2h 36min",
    rating = "8.5",
    castMembers = listOf(castMember1,castMember2),
    movieTrailerYoutubeKey = "dQw4w9WgXcQ"
)