package com.mvvm.data.service

import com.mvvm.domain.models.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun fetchPokemons(@Query("limit") limit: Int): Response

}