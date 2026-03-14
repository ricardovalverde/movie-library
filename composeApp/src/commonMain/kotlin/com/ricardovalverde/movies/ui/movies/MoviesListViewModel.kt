package com.ricardovalverde.movies.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricardovalverde.movies.data.repository.MoviesRepository
import com.ricardovalverde.movies.domain.model.MovieSection
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class MoviesListViewModel(private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _moviesListState = MutableStateFlow<MoviesListState>(MoviesListState.Loading)
    val moviesListState = _moviesListState.asStateFlow()

    init {
        getMovieSections()
    }

    private fun getMovieSections() {
        viewModelScope.launch {
            try {
                val movieSections = moviesRepository.getMovieSections()

                delay(Random.nextLong(3000, 5000))

                _moviesListState.update {
                    MoviesListState.Success(movieSections)
                }

            } catch (e: Exception) {
                _moviesListState.update {
                    MoviesListState.Error(e.message ?: "UnknowError")
                }
            }
        }
    }


    //Representa os 3 estados que a tela pode ter
    sealed interface MoviesListState {
        data object Loading : MoviesListState
        data class Success(val movieSection: List<MovieSection>) : MoviesListState
        data class Error(val message: String) : MoviesListState
    }

}