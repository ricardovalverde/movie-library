package com.ricardovalverde.movies.domain.model

data class Genre(
    val id: Int,
    val name: String
)

//Fake Objects
val genre1 = Genre(
    id = 1,
    name = "Action"
)

val genre2 = Genre(
    id = 2,
    name = "Adventure"
)