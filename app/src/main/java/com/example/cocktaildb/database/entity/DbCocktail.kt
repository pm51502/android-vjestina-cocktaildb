package com.example.cocktaildb.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail")
data class DbCocktail(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "cocktail_id")
    val id: Int,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "cocktail_name")
    val cocktailName: String
)
