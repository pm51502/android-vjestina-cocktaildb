package com.example.cocktaildb.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

interface CocktailRepository {
    fun getCategoryCocktails(category: String): Flow<List<Cocktail>>
    fun getCocktailDetails(cocktailId: Int): Flow<CocktailDetails>
    fun getFavoriteCocktails(): Flow<List<Cocktail>>
    suspend fun toggleFavorite(cocktail: Cocktail)
    fun searchCocktailByName(cocktailName: String): Flow<List<Cocktail>>
}

class CocktailRepositoryImpl(
    private val cocktailApi: CocktailApi,
    private val cocktailDatabase: CocktailDatabase
): CocktailRepository {
    private val sharingScope = CoroutineScope(Dispatchers.Default)

    override fun getCategoryCocktails(category: String): Flow<List<Cocktail>> = flow {
        emit(cocktailApi.getCategoryCocktails(category = category).drinks)
    }.shareIn(
        sharingScope,
        SharingStarted.Lazily,
        replay = 1
    )

    override fun getCocktailDetails(cocktailId: Int): Flow<CocktailDetails> = flow {
        emit(cocktailApi.getCocktailDetails(cocktailId = cocktailId).drinks.first())
    }

    override fun getFavoriteCocktails(): Flow<List<Cocktail>> = favoriteCocktailsFlow

    override suspend fun toggleFavorite(cocktail: Cocktail) {
        cocktailDatabase.toggleFavorite(cocktail = cocktail)
        refreshFavoriteCocktailsPublisher.emit(RefreshEvent)
    }

    override fun searchCocktailByName(cocktailName: String): Flow<List<Cocktail>> = flow {
        emit(cocktailApi.searchCocktailByName(cocktailName = cocktailName).drinks)
    }.shareIn(
        sharingScope,
        SharingStarted.Lazily,
        replay = 1
    )

    object RefreshEvent
    private val refreshFavoriteCocktailsPublisher = MutableSharedFlow<RefreshEvent>(replay = 1)

    private val favoriteCocktailsFlow = refreshFavoriteCocktailsPublisher
        .onStart { refreshFavoriteCocktailsPublisher.emit(RefreshEvent) }
        .map { cocktailDatabase.getFavoriteCocktails() }
        .shareIn(
            sharingScope,
            SharingStarted.Lazily,
            replay = 1
        )
}
