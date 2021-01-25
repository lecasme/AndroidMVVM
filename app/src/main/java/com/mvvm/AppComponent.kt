package com.mvvm

import com.google.gson.GsonBuilder
import com.mvvm.data.database.AppDataBase
import com.mvvm.data.datasource.local.PokemonLocalDataSource
import com.mvvm.data.datasource.local.PokemonLocalDataSourceImpl
import com.mvvm.data.datasource.remote.PokemonRemoteDataSource
import com.mvvm.data.datasource.remote.PokemonRemoteDataSourceImpl
import com.mvvm.data.repository.PokemonRepository
import com.mvvm.data.repository.PokemonRepositoryImpl
import com.mvvm.data.service.PokemonService
import com.mvvm.domain.usecases.HomeUseCase
import com.mvvm.domain.usecases.SplashUseCase
import com.mvvm.presentation.features.home.HomeViewModel
import com.mvvm.presentation.features.splash.SplashViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appComponent = listOf(
    createService("https://pokeapi.co/api/v2/"),
    createDataBase(),
    createRepositories(),
    createDataSources(),
    createUseCases(),
    createViewModels()
)

fun createViewModels() = module{
    viewModel { HomeViewModel(homeUseCase = get()) }
    viewModel { SplashViewModel(splashUseCase = get()) }
}

fun createUseCases() = module{
    factory { SplashUseCase(get()) }
    factory { HomeUseCase(get()) }
}

fun createRepositories() = module{
    factory { PokemonRepositoryImpl(get(), get()) as PokemonRepository }
}


fun createDataSources() = module{
    factory { PokemonRemoteDataSourceImpl(get()) as PokemonRemoteDataSource }
    factory { PokemonLocalDataSourceImpl(get()) as PokemonLocalDataSource }
}

fun createDataBase() = module{
    val database = "DATABASE"
    single(named(database)) { AppDataBase.buildDatabase(androidContext()) }
    factory { (get(named(database)) as AppDataBase).pokemonDao() }
}

fun createService(baseUrl: String) = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
    }

    factory { get<Retrofit>().create(PokemonService::class.java) }
}