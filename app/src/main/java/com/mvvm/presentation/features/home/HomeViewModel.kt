package com.mvvm.presentation.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.mvvm.domain.models.Pokemon
import com.mvvm.domain.usecases.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val _pokemons = MediatorLiveData<List<Pokemon>>()
    val pokemon: LiveData<List<Pokemon>>
        get() =_pokemons

    init {
        getPokemons()
    }

    private fun getPokemons() = viewModelScope.launch {
        _pokemons.addSource(homeUseCase.getPokemons()){
            _pokemons.value = it
        }
    }

}