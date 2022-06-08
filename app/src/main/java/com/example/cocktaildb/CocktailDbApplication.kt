package com.example.cocktaildb

import android.app.Application
import com.example.cocktaildb.di.modules.dataModule
import com.example.cocktaildb.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CocktailDbApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CocktailDbApplication)
            modules(
                viewModelModule,
                dataModule
            )
        }
    }
}
