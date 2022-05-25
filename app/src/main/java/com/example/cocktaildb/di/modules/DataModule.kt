package com.example.cocktaildb.di.modules

import com.example.cocktaildb.data.*
import com.example.cocktaildb.data.CocktailApiImpl
import org.koin.dsl.module

val dataModule = module {
    single<CocktailRepository> {
        CocktailRepositoryImpl(
            cocktailApi = get(),
            cocktailDatabase = get()
        )
    }
    single<CocktailApi> {
        CocktailApiImpl()
    }
    single<CocktailDatabase> {
        CocktailDatabaseImpl()
    }
}
