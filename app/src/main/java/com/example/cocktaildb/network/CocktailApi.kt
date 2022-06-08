package com.example.cocktaildb.network

import android.content.Context
import com.example.cocktaildb.BuildConfig
import com.example.cocktaildb.utils.ConnectionState
import com.example.cocktaildb.utils.currentConnectivityState
import com.example.cocktaildb.utils.getRoute
import io.ktor.client.*
import io.ktor.client.request.*

interface CocktailApi {
    suspend fun getCategoryCocktails(category: String): CocktailsResponse
    suspend fun getCocktailDetails(cocktailId: Int): CocktailDetailsResponse
    suspend fun searchCocktailByName(cocktailName: String): CocktailNameSearchResponse
}

internal class CocktailApiImpl(
    private val httpClient: HttpClient,
    context: Context
) : CocktailApi {
    private val isConnected = context.currentConnectivityState == ConnectionState.Available

    override suspend fun getCategoryCocktails(category: String): CocktailsResponse =
        if (isConnected) httpClient.get(urlString = getRoute(category = category))
        else CocktailsResponse(drinks = emptyList())

    override suspend fun getCocktailDetails(cocktailId: Int): CocktailDetailsResponse =
        if (isConnected) httpClient.get(urlString = "${ApiConstants.BASE_URL}/${ApiConstants.API_KEY}/lookup.php?i=$cocktailId")
        else CocktailDetailsResponse(drinks = emptyList())

    override suspend fun searchCocktailByName(cocktailName: String): CocktailNameSearchResponse =
        if (isConnected) httpClient.get(urlString = "${ApiConstants.BASE_URL}/${ApiConstants.API_KEY}/search.php?s=$cocktailName")
        else CocktailNameSearchResponse(drinks = emptyList())
}

object ApiConstants {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://www.thecocktaildb.com/api/json/v2"
}
