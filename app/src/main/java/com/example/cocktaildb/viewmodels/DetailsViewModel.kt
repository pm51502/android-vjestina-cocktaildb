package com.example.cocktaildb.viewmodels

import androidx.lifecycle.ViewModel
import com.example.cocktaildb.data.CocktailRepository

class DetailsViewModel(
    private val cocktailRepository: CocktailRepository,
    cocktailId: Int
): ViewModel() {

}
