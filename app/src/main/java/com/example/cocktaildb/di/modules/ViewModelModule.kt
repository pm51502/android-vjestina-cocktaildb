package com.example.cocktaildb.di.modules

import com.example.cocktaildb.viewmodels.CocktailsViewModel
import com.example.cocktaildb.viewmodels.DetailsViewModel
import com.example.cocktaildb.viewmodels.FavoritesViewModel
import com.example.cocktaildb.viewmodels.SearchViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { params ->
        CocktailsViewModel(
            cocktailRepository = get(),
            cocktailsType = params.get()
        )
    }
    viewModel {
        FavoritesViewModel(cocktailRepository = get())
    }
    viewModel { params ->
        DetailsViewModel(
            cocktailRepository = get(),
            cocktailId = params.get()
        )
    }
    viewModel {
        SearchViewModel(
            cocktailRepository = get()
        )
    }
}
