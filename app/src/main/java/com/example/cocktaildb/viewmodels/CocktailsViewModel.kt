package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktaildb.database.entity.DbCocktail
import com.example.cocktaildb.repository.CocktailRepository
import com.example.cocktaildb.utils.toCocktailViewState
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
