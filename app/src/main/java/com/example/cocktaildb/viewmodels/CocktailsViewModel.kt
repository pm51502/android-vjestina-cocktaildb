package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktaildb.data.Cocktail
import com.example.cocktaildb.data.CocktailRepository
import com.example.cocktaildb.data.toCocktailViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*

class CocktailsViewModel(
    private val cocktailRepository: CocktailRepository,
    cocktailsType: String
) : ViewModel() {
    val categoryCocktailsStateFlow = combine(
        cocktailRepository.getCategoryCocktails(category = cocktailsType),
        cocktailRepository.getFavoriteCocktails()
    ) { cocktailList, favoriteList ->
        val favoriteListIds = favoriteList.map { cocktail -> cocktail.idDrink }
        cocktailList.map { cocktail ->
            cocktail.toCocktailViewState(isFavorite = favoriteListIds.contains(cocktail.idDrink))
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun toggleFavorite(cocktail: Cocktail) {
        viewModelScope.launch(Dispatchers.Default) {
            cocktailRepository.toggleFavorite(cocktail = cocktail)
        }
    }
}
