package com.mvvm.domain.usecases

import androidx.lifecycle.LiveData
import com.mvvm.data.repository.PokemonRepository
import com.mvvm.domain.models.Pokemon

class HomeUseCase(
    private val pokemonRepository: PokemonRepository,
) {

    suspend fun getPokemons(): LiveData<List<Pokemon>> {
        return pokemonRepository.getPokemons()
    }

}