package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cocktaildb.data.CocktailRepository

class FavoritesViewModel(
    private val cocktailRepository: CocktailRepository
): ViewModel() {

}
