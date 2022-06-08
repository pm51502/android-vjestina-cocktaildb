package com.example.cocktaildb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktaildb.database.dao.CocktailDao
import com.example.cocktaildb.database.entity.DbCocktail

@Database(entities = [DbCocktail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}
