package com.ricardovalverde.movies.ui.movies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ricardovalverde.movies.domain.model.MovieSection
import com.ricardovalverde.movies.domain.model.movie1
import com.ricardovalverde.movies.ui.components.MoviesSection
import com.ricardovalverde.movies.ui.theme.MoviesAppTheme
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.movies_list_popular_movies
import movies.composeapp.generated.resources.movies_list_top_rated_movies
import movies.composeapp.generated.resources.movies_list_upcoming_movies
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

/*
* Estrutura de duas fun sendo a Route chamando a Screen para facilitar o preview,
* pois na Screen vai ter viewModel e States oq complica muito o Preview, dessa forma
* o Routes fica responsavel apenas por chamar a Screen
* */

@Composable
fun MoviesListRoute(
    viewModel: MoviesListViewModel = koinViewModel(),
    navigateToMovieDetail: (idMovie: Int) -> Unit
) {
    val moviesListState by viewModel.moviesListState.collectAsStateWithLifecycle()

    MoviesListScreen(moviesListState = moviesListState, onMovieClick = navigateToMovieDetail)
}

@Composable
fun MoviesListScreen(
    moviesListState: MoviesListViewModel.MoviesListState,
    onMovieClick: (idMovie: Int) -> Unit
) {
    /*Scaffold importante pois ja cria o esqueleto necessário para uma tela
    * ele ja tem alguns componentes como topbar,bottombar, snackbar,
    * e respeita o edge to edge em Android 15+
    *
    * */
    Scaffold { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (moviesListState) {

                MoviesListViewModel.MoviesListState.Loading -> {
                    Column(
                        modifier = Modifier.padding(vertical = 32.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        repeat(5) {
                            MovieListScreenSkeleton()
                        }
                    }
                }

                is MoviesListViewModel.MoviesListState.Success -> {

                    LazyColumn(
                        modifier = Modifier,
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(32.dp)
                    ) {

                        items(moviesListState.movieSection) { movieSection ->

                            val title = when (movieSection.sectionType) {
                                MovieSection.SectionType.POPULAR -> stringResource(Res.string.movies_list_popular_movies)
                                MovieSection.SectionType.TOP_RATED -> stringResource(Res.string.movies_list_top_rated_movies)
                                MovieSection.SectionType.UPCOMING -> stringResource(Res.string.movies_list_upcoming_movies)
                            }

                            MoviesSection(
                                title = title,
                                movies = movieSection.movies,
                                onMoviePosterClick = onMovieClick
                            )

                        }
                    }
                }

                is MoviesListViewModel.MoviesListState.Error -> {
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = moviesListState.message,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MoviesListScreenPreview() {
    MoviesAppTheme {
        MoviesListScreen(
            moviesListState = MoviesListViewModel.MoviesListState.Success(
                movieSection = listOf(
                    MovieSection(
                        sectionType = MovieSection.SectionType.POPULAR,
                        movies = listOf(movie1)
                    ),
                    MovieSection(
                        sectionType = MovieSection.SectionType.TOP_RATED,
                        movies = listOf(movie1)
                    )
                )
            ),
            onMovieClick = {}
        )
    }
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
