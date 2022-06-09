package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktaildb.database.entity.DbCocktail
import com.example.cocktaildb.network.CocktailDetails
import com.example.cocktaildb.repository.CocktailRepository
import com.example.cocktaildb.utils.toCocktailDetailsViewState
import com.example.cocktaildb.ui.screens.CocktailDetailsViewState
import com.example.cocktaildb.utils.toCocktailViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val cocktailRepository: CocktailRepository,
    cocktailId: Int
) : ViewModel() {
    /*val cocktailDetailsStateFlow = combine(
        cocktailRepository.getCocktailDetails(cocktailId = cocktailId),
        cocktailRepository.getFavoriteCocktails()
    ) { cocktailDetails, favoriteList ->
        val favoriteListIds = favoriteList.map { cocktail -> cocktail.idDrink }
        cocktailDetails.copy(isFavorite = favoriteListIds.contains(cocktailDetails.idDrink))
        *//*cocktailDetails.map { cocktail ->
            cocktail.toCocktailViewState(isFavorite = favoriteListIds.contains(cocktail.idDrink))
        }*//*
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = CocktailDetailsViewState()
    )*/

    val cocktailDetailsStateFlow = combine(
        cocktailRepository.getCocktailDetails(cocktailId = cocktailId).map {
            toCocktailDetailsViewState(cocktailDetails = it)
        },
        cocktailRepository.getFavoriteCocktails()
    ) { cocktailDetails, favoriteList ->
        val favoriteListIds = favoriteList.map { cocktail -> cocktail.idDrink }
        cocktailDetails.copy(isFavorite = favoriteListIds.contains(cocktailDetails.id))
    }.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        initialValue = CocktailDetailsViewState()
    )


        /*cocktailRepository
        .getCocktailDetails(cocktailId = cocktailId)
        .map { toCocktailDetailsViewState(cocktailDetails = it) }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            CocktailDetailsViewState()
        )*/

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
