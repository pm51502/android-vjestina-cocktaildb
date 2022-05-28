package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cocktaildb.data.CocktailRepository

class SearchViewModel(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {
    val cocktailSearchSharedFlow = cocktailRepository
        .searchCocktailByName(cocktailName = "")

    fun refreshSearchResults(cocktailName: String) {
        cocktailRepository.searchCocktailByName(cocktailName = cocktailName)
    }
}
