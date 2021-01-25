package com.mvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mvvm.data.datasource.local.PokemonLocalDataSource
import com.mvvm.data.datasource.remote.PokemonRemoteDataSource
import com.mvvm.domain.models.Pokemon
import com.mvvm.domain.models.Response

interface PokemonRepository {
    suspend fun fetchPokemons(limit: Int): LiveData<Boolean>
    suspend fun getPokemons(): LiveData<List<Pokemon>>
}

class PokemonRepositoryImpl(private val pokemonRemoteDataSource: PokemonRemoteDataSource,
                            private val pokemonLocalDataSource: PokemonLocalDataSource): PokemonRepository {

    private val result = MutableLiveData<List<Pokemon>>()
    private val status = MutableLiveData<Boolean>()

    override suspend fun fetchPokemons(limit: Int): LiveData<Boolean> {
        val response = pokemonRemoteDataSource.fetchPokemons(limit)
        pokemonLocalDataSource.insertPokemons(response.results)
        status.postValue(true)
        return status
    }

    override suspend fun getPokemons(): LiveData<List<Pokemon>> {
        result.postValue(pokemonLocalDataSource.selectPokemons())
        return result
    }
}