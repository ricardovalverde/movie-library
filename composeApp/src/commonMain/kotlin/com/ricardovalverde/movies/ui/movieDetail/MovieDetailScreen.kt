package com.ricardovalverde.movies.ui.movieDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.composables.icons.fontawesome.FontAwesome
import com.composables.icons.fontawesome.brands.Youtube
import com.composables.icons.heroicons.Heroicons
import com.composables.icons.heroicons.outline.Clock
import com.composables.icons.heroicons.solid.ArrowLeft
import com.composables.icons.heroicons.solid.CalendarDays
import com.composables.icons.heroicons.solid.Star
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewNavigator
import com.multiplatform.webview.web.rememberWebViewState
import com.ricardovalverde.movies.domain.model.Movie
import com.ricardovalverde.movies.domain.model.movie1
import com.ricardovalverde.movies.ui.components.CastMemberItem
import com.ricardovalverde.movies.ui.components.MovieGenreChip
import com.ricardovalverde.movies.ui.components.MovieInfoItem
import com.ricardovalverde.movies.ui.theme.MoviesAppTheme
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.movie_detail
import movies.composeapp.generated.resources.movie_detail_watch_trailer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailRoute(
    viewModel: MovieDetailViewModel = koinViewModel(),
    onNavigationBackClick: () -> Unit
) {

    val movieDetailState by viewModel.movieDetailState.collectAsStateWithLifecycle()

    MovieDetailScreen(
        movieDetailState = movieDetailState,
        onNavigationIconClick = onNavigationBackClick
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieDetailState: MovieDetailViewModel.MovieDetailState,
    onNavigationIconClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(Res.string.movie_detail)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigationIconClick)
                    {
                        Icon(imageVector = Heroicons.Solid.ArrowLeft, contentDescription = null)
                    }
                }
            )
        }
    ) { paddingValues ->

        var youtubeVideoId by remember { mutableStateOf<String?>(null) }

        youtubeVideoId?.let { key ->

            ModalBottomSheet(
                modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                onDismissRequest = { youtubeVideoId = null },

                ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                    YouTubePlayer(
                        videoId = key
                    )
                }
            }
        }

        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when (movieDetailState) {
                MovieDetailViewModel.MovieDetailState.Loading -> {
                    CircularProgressIndicator()
                }

                is MovieDetailViewModel.MovieDetailState.Success -> {
                    MovieDetailContent(
                        movie = movieDetailState.movie,
                        onWatchTrailerClick = { key ->
                            youtubeVideoId = key
                        }
                    )
                }

                is MovieDetailViewModel.MovieDetailState.Error -> {
                    Text(
                        text = movieDetailState.message,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

    }

}

@Composable
fun MovieDetailContent(
    modifier: Modifier = Modifier,
    movie: Movie,
    onWatchTrailerClick: (key: String) -> Unit
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
                .weight(2f),
            shape = MaterialTheme.shapes.large
        ) {
            AsyncImage(
                modifier = Modifier.clip(MaterialTheme.shapes.large),
                model = movie.posterUrl,
                contentScale = ContentScale.Fit,
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(top = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold,
                text = movie.title,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(5.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                MovieInfoItem(
                    text = movie.rating,
                    icon = Heroicons.Solid.Star,
                    color = Color.Yellow,
                    size = 20.dp
                )

                Spacer(modifier = Modifier.width(16.dp))

                movie.duration?.let { duration ->
                    MovieInfoItem(
                        text = duration,
                        icon = Heroicons.Outline.Clock,
                        color = Color(0xFF69F0AE),
                        size = 20.dp
                    )
                }


                Spacer(modifier = Modifier.width(16.dp))

                MovieInfoItem(
                    text = "${movie.year}",
                    icon = Heroicons.Solid.CalendarDays,
                    color = Color(0XFF4FC3F7),
                    size = 20.dp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                movie.genres?.forEachIndexed { index, genre ->
                    MovieGenreChip(genre = genre.name)

                    if (index < movie.genres.size - 1) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            movie.movieTrailerYoutubeKey?.let { id ->
                ElevatedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = { onWatchTrailerClick(id) },
                ) {
                    Icon(imageVector = FontAwesome.Brands.Youtube, contentDescription = null)

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(Res.string.movie_detail_watch_trailer),
                        fontWeight = FontWeight.Medium,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White
                    )

                }
            }



            movie.castMembers?.let { castMembers ->

                Spacer(modifier = Modifier.height(5.dp))

                BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
                    val itemWidth = this.maxWidth * 0.55f

                    LazyRow(
                        contentPadding = PaddingValues(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(castMembers) { castMember ->
                            CastMemberItem(
                                modifier = Modifier.width(itemWidth),
                                profilePictureUrl = castMember.profileUrl,
                                name = castMember.name,
                                character = castMember.character

                            )
                        }
                    }
                }
            }


            Box(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun YouTubePlayer(
    videoId: String,
    modifier: Modifier = Modifier
) {
    val url = "https://www.youtube.com/watch?v=$videoId"
    val webViewState = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()

    WebView(
        state = webViewState,
        modifier = modifier.fillMaxWidth(),
        navigator = navigator,
        captureBackPresses = false
    )
}

@Preview
@Composable
fun MovieDetailPreview() {
    MoviesAppTheme {
        MovieDetailScreen(
            movieDetailState = MovieDetailViewModel.MovieDetailState.Success(
                movie1
            ),
            onNavigationIconClick = {}
        )
    }
}