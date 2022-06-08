package com.example.cocktaildb.di.modules

import androidx.room.Room
import com.example.cocktaildb.database.AppDatabase
import com.example.cocktaildb.network.*
import com.example.cocktaildb.network.CocktailApiImpl
import com.example.cocktaildb.repository.CocktailRepository
import com.example.cocktaildb.repository.CocktailRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "cocktail-db"
        ).build()
    }
    single {
        get<AppDatabase>().cocktailDao()
    }
    single<CocktailRepository> {
        CocktailRepositoryImpl(
            cocktailApi = get(),
            cocktailDao = get()
        )
    }
    single<CocktailApi> {
        CocktailApiImpl(
            httpClient = get(),
            context = androidContext()
        )
    }
    single {
        httpClient
    }
}
