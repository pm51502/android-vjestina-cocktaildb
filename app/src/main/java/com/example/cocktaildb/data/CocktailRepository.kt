package com.example.cocktaildb.data

interface CocktailRepository {

}

class CocktailRepositoryImpl(
    private val cocktailApi: CocktailApi,
    private val cocktailDatabase: CocktailDatabase
): CocktailRepository {

}
