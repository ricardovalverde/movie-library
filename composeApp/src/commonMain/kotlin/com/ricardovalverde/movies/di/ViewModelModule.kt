package com.ricardovalverde.movies.di

import com.ricardovalverde.movies.ui.movieDetail.MovieDetailViewModel
import com.ricardovalverde.movies.ui.movies.MoviesListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MoviesListViewModel(get()) }
    viewModel { MovieDetailViewModel(get(),get()) }
}