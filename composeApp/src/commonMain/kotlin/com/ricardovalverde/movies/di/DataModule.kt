package com.ricardovalverde.movies.di

import com.ricardovalverde.movies.data.repository.MoviesRepository
import org.koin.dsl.module

val dataModule = module {
    //Factory pois para cada repositorio é melhor que cada vez que alguem precisar dele seja criada uma nova instancia;
    //Como o MoviesRepository precisa obrigatoriamente de um KtorClient, o passamos o get(), que significa que o koin vai buscar sozinho a instancia e passar para o MoviesRepository;
    factory { MoviesRepository(get()  ) }
}