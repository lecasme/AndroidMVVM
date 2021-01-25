package com.mvvm.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.data.database.dao.PokemonDao
import com.mvvm.domain.models.Pokemon

@Database(
    entities = [
        Pokemon::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    // DAO
    abstract fun pokemonDao(): PokemonDao

    companion object {

        private const val databaseName = "Pokemon.db"
        fun buildDatabase(context: Context) = Room.databaseBuilder(context, AppDataBase::class.java, databaseName).build()
    }
}