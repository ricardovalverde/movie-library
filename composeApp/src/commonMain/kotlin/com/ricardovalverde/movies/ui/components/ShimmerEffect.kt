package com.ricardovalverde.movies.ui.components

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import com.ricardovalverde.movies.ui.movies.MoviesListScreen
import com.ricardovalverde.movies.ui.movies.MoviesListViewModel
import com.ricardovalverde.movies.ui.theme.MoviesAppTheme

@Composable
fun shimmerEffect(): Brush {
    val infiniteTransition = rememberInfiniteTransition(label = "InfiniteTransition")

    val widthPx = LocalWindowInfo.current.containerSize.width
    val target = widthPx * 4f

    val scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = target,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Restart
        ), label = "shimmer"
    )



    return Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.5f),
            Color.White.copy(alpha = 0.20f),
            Color.White.copy(alpha = 0.5f),
        ),
        end = Offset(x = scale, y = scale)
    )

}

@Preview
@Composable
fun MoviesListScreenIsLoadingPreview() {
    MoviesAppTheme {
        MoviesListScreen(
            moviesListState = MoviesListViewModel.MoviesListState.Loading,
            onMovieClick = {}
        )
    }
}