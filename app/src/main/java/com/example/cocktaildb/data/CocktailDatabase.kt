package com.example.cocktaildb.data

interface CocktailDatabase {
    fun getFavoriteCocktails(): List<Cocktail>
    fun toggleFavorite(cocktail: Cocktail)
}

internal class CocktailDatabaseImpl: CocktailDatabase {
    private val favoriteCocktails = mutableListOf<Cocktail>()

    override fun getFavoriteCocktails(): List<Cocktail> = favoriteCocktails

    override fun toggleFavorite(cocktail: Cocktail) {
        if (favoriteCocktails.contains(cocktail)) favoriteCocktails.remove(cocktail)
        else favoriteCocktails.add(cocktail)
    }
}
