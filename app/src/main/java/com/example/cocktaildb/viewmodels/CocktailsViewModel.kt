package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cocktaildb.data.CocktailRepository

class CocktailsViewModel(
    private val cocktailRepository: CocktailRepository,
    private val cocktailsType: String
): ViewModel() {

}
