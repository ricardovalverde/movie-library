package com.ricardovalverde.movies

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ricardovalverde.movies.di.dataModule
import com.ricardovalverde.movies.di.networkModule
import com.ricardovalverde.movies.di.viewModelModule
import com.ricardovalverde.movies.navigation.AppRoutes
import com.ricardovalverde.movies.ui.movieDetail.MovieDetailRoute
import com.ricardovalverde.movies.ui.movies.MoviesListRoute
import com.ricardovalverde.movies.ui.theme.MoviesAppTheme
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(
        application = {
            //Aqui passa todos os modules da aplicacao
            modules(dataModule, networkModule, viewModelModule)
        }
    ) {
        MoviesAppTheme {
            val navController = rememberNavController()

            NavHost(navController, startDestination = AppRoutes.MoviesList) {

                composable<AppRoutes.MoviesList> {
                    MoviesListRoute(
                        navigateToMovieDetail = { idMovie ->
                            navController.navigate(AppRoutes.MovieDetail(idMovie))
                        }
                    )
                }
                composable<AppRoutes.MovieDetail> {
                    MovieDetailRoute(
                        onNavigationBackClick = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}