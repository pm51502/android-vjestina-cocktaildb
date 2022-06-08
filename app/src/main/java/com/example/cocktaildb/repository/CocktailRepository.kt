package com.example.cocktaildb.repository

import com.example.cocktaildb.database.dao.CocktailDao
import com.example.cocktaildb.database.entity.DbCocktail
import com.example.cocktaildb.network.Cocktail
import com.example.cocktaildb.network.CocktailApi
import com.example.cocktaildb.network.CocktailDetails
import com.example.cocktaildb.utils.toCocktail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

interface CocktailRepository {
    fun getCategoryCocktails(category: String): Flow<List<Cocktail>>
    fun getCocktailDetails(cocktailId: Int): Flow<CocktailDetails>
    fun searchCocktailByName(cocktailName: String): Flow<List<Cocktail>>
    fun getFavoriteCocktails(): Flow<List<Cocktail>>
    suspend fun insertCocktail(cocktail: DbCocktail)
    suspend fun deleteCocktail(cocktailId: Int)
}

class CocktailRepositoryImpl(
    private val cocktailApi: CocktailApi,
    private val cocktailDao: CocktailDao
) : CocktailRepository {
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

    override fun searchCocktailByName(cocktailName: String): Flow<List<Cocktail>> = flow {
        emit(cocktailApi.searchCocktailByName(cocktailName = cocktailName).drinks)
    }.shareIn(
        sharingScope,
        SharingStarted.Lazily,
        replay = 1
    )

    override fun getFavoriteCocktails(): Flow<List<Cocktail>> =
        cocktailDao.getAllCocktails().map { cocktailList ->
            cocktailList.map { dbCocktail ->
                dbCocktail.toCocktail()
            }
        }

    override suspend fun insertCocktail(cocktail: DbCocktail) {
        cocktailDao.insertCocktail(cocktail = cocktail)
    }

    override suspend fun deleteCocktail(cocktailId: Int) {
        cocktailDao.deleteCocktail(cocktailId = cocktailId)
    }
}
