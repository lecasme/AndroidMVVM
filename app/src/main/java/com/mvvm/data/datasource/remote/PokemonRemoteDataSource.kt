package com.mvvm.data.datasource.remote

import com.mvvm.data.service.PokemonService
import com.mvvm.domain.models.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PokemonRemoteDataSource {
    suspend fun fetchPokemons(limit: Int): Response
}

class PokemonRemoteDataSourceImpl(
    private val pokemonService: PokemonService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonRemoteDataSource {

    override suspend fun fetchPokemons(limit: Int) = withContext(ioDispatcher) {
        pokemonService.fetchPokemons(limit)
    }

}