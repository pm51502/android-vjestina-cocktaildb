package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cocktaildb.repository.CocktailRepository

class SearchViewModel(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {
    val cocktailSearchSharedFlow = cocktailRepository
        .searchCocktailByName(cocktailName = "")
}
