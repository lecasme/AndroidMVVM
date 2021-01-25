package com.mvvm.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.mvvm.domain.models.Pokemon

@Dao
abstract class PokemonDao : BaseDao<Pokemon> {

    @Query("SELECT * FROM pokemon")
    abstract suspend fun getPokemons(): List<Pokemon>
}