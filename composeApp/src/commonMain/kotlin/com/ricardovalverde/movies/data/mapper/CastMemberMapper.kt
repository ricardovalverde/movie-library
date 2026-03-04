package com.ricardovalverde.movies.data.mapper

import com.ricardovalverde.movies.data.network.IMAGE_BASE_URL
import com.ricardovalverde.movies.data.network.model.CastMemberResponse
import com.ricardovalverde.movies.domain.model.CastMember
import com.ricardovalverde.movies.domain.model.ImageSize

fun CastMemberResponse.toModel() = CastMember(
    id = this.id,
    mainRole = this.department,
    name = this.name,
    character = this.character,
    profileUrl = "${IMAGE_BASE_URL}/${ImageSize.X_SMALL.size}/${this.profilePath}"
)