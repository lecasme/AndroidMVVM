package com.mvvm.domain.usecases

import androidx.lifecycle.LiveData
import com.mvvm.data.repository.PokemonRepository

class SplashUseCase(
    private val pokemonRepository: PokemonRepository,
) {

    suspend fun fetchPokemons(limit: Int) : LiveData<Boolean>{
       return pokemonRepository.fetchPokemons(limit)
    }

}