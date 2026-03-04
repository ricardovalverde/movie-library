package com.ricardovalverde.movies.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ricardovalverde.movies.domain.model.Movie
import com.ricardovalverde.movies.domain.model.movie1
import com.ricardovalverde.movies.ui.theme.MoviesAppTheme

@Composable
fun MoviePoster(
    modifier: Modifier = Modifier,
    onMoviePosterClick: () -> Unit,
    movie: Movie
) {
    Column(
        modifier = modifier
            .width(140.dp)
            .clickable { onMoviePosterClick() }
    ) {
        println("PosterMovie: ${movie.posterUrl}")

        Card(
            modifier = modifier
                .width(140.dp)
                .height(210.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = movie.posterUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.padding(vertical = 5.dp),
            text = movie.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview
@Composable
fun MoviePosterPreview() {
    MoviesAppTheme {
        MoviePoster(movie = movie1, onMoviePosterClick = {})
    }
}