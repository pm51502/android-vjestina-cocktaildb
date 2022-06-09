package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktaildb.database.entity.DbCocktail
import com.example.cocktaildb.repository.CocktailRepository
import com.example.cocktaildb.utils.toCocktailViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val cocktailRepository: CocktailRepository
) : ViewModel() {
    val favoriteCocktailsStateFlow = cocktailRepository
        .getFavoriteCocktails()
        .map { cocktailsList ->
            cocktailsList.map { cocktail ->
                cocktail.toCocktailViewState(isFavorite = true)
            }
        }

    fun insertFavoriteCocktail(cocktail: DbCocktail) {
        viewModelScope.launch(Dispatchers.Default) {
            cocktailRepository.insertCocktail(cocktail = cocktail)
        }
    }

    fun deleteFavoriteCocktail(cocktailId: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            cocktailRepository.deleteCocktail(cocktailId = cocktailId)
        }
    }
}
