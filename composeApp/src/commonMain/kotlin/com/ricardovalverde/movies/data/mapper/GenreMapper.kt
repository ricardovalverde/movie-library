package com.ricardovalverde.movies.data.mapper

import com.ricardovalverde.movies.data.network.model.GenreResponse
import com.ricardovalverde.movies.domain.model.Genre

fun GenreResponse.toModel() = Genre(
    id = this.id,
    name = this.name
)