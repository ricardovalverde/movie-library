package com.ricardovalverde.movies.ui.movieDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.ricardovalverde.movies.data.repository.MoviesRepository
import com.ricardovalverde.movies.domain.model.Movie
import com.ricardovalverde.movies.navigation.AppRoutes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository
) : ViewModel() {
    private val movieDetailRoute = savedStateHandle.toRoute<AppRoutes.MovieDetail>()
    private val _movieDetailState= MutableStateFlow<MovieDetailState>(MovieDetailState.Loading)

    val movieDetailState = _movieDetailState.asStateFlow()

    init {
        getMovieDetail()
    }

    private fun getMovieDetail(){
        viewModelScope.launch {
            moviesRepository.getMovieDetail(movieDetailRoute.id).fold(
                onSuccess = { movie ->
                    _movieDetailState.update { MovieDetailState.Success(movie) }
                    print("Duration: ${movie.duration}")


                },
                onFailure = { error ->
                    _movieDetailState.update { MovieDetailState.Error(error.message ?: "Unknow Error") }
                }
            )
        }
    }

    sealed interface MovieDetailState {
        data object Loading : MovieDetailState
        data class Success(val movie: Movie) : MovieDetailState
        data class Error(val message: String) : MovieDetailState
    }
}