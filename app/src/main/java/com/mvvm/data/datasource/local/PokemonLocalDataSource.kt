package com.mvvm.data.datasource.local

import com.mvvm.data.database.dao.PokemonDao
import com.mvvm.domain.models.Pokemon
import com.mvvm.domain.models.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface PokemonLocalDataSource {
    suspend fun selectPokemons(): List<Pokemon>
    suspend fun insertPokemons(pokemons : List<Pokemon>)
}

class PokemonLocalDataSourceImpl(
    private val pokemonDao: PokemonDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PokemonLocalDataSource {

    override suspend fun selectPokemons(): List<Pokemon> = withContext(ioDispatcher) {
        pokemonDao.getPokemons()
    }

    override suspend fun insertPokemons(pokemons : List<Pokemon>): Unit = withContext(ioDispatcher) {
        pokemonDao.insert(pokemons)
    }

}