package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktaildb.data.Cocktail
import com.example.cocktaildb.data.CocktailRepository
import com.example.cocktaildb.data.toCocktailViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val cocktailRepository: CocktailRepository
): ViewModel() {

    val favoriteCocktailsStateFlow = cocktailRepository
        .getFavoriteCocktails()
        .map { cocktailsList ->
            cocktailsList.map { cocktail ->
                cocktail.toCocktailViewState(isFavorite = true)
            }
        }

    fun toggleFavorite(cocktail: Cocktail) {
        viewModelScope.launch(Dispatchers.Default) {
            cocktailRepository.toggleFavorite(cocktail = cocktail)
        }
    }
}
