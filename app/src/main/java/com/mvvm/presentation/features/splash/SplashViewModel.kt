package com.mvvm.presentation.features.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm.domain.usecases.SplashUseCase
import kotlinx.coroutines.launch

class SplashViewModel(private val splashUseCase: SplashUseCase) : ViewModel() {


    private val _status = MediatorLiveData<Boolean>()
    val status: LiveData<Boolean>
        get() =_status

    init {
        load()
    }

     private fun load() = viewModelScope.launch {
        _status.addSource(splashUseCase.fetchPokemons(LIMIT)){
            _status.value = it
        }
    }

    companion object{
        const val LIMIT = 151
    }

}