package com.example.cocktaildb.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktaildb.database.entity.DbCocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {
    @Query("SELECT * FROM cocktail")
    fun getAllCocktails(): Flow<List<DbCocktail>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCocktail(cocktail: DbCocktail)

    @Query("DELETE FROM cocktail WHERE cocktail_id = :cocktailId")
    suspend fun deleteCocktail(cocktailId: Int)
}
