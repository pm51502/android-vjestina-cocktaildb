package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cocktaildb.repository.CocktailRepository
import com.example.cocktaildb.utils.toCocktailDetailsViewState
import com.example.cocktaildb.ui.screens.CocktailDetailsViewState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    cocktailRepository: CocktailRepository,
    cocktailId: Int
) : ViewModel() {
    val cocktailDetailsStateFlow = cocktailRepository
        .getCocktailDetails(cocktailId = cocktailId)
        .map { toCocktailDetailsViewState(cocktailDetails = it) }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            CocktailDetailsViewState()
        )
}
