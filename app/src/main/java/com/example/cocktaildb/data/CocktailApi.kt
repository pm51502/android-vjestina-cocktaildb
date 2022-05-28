package com.example.cocktaildb.data

import com.example.cocktaildb.BuildConfig
import io.ktor.client.*
import io.ktor.client.request.*

interface CocktailApi {
    suspend fun getCategoryCocktails(category: String): CocktailsResponse
    suspend fun getCocktailDetails(cocktailId: Int): CocktailDetailsResponse
    suspend fun searchCocktailByName(cocktailName: String): CocktailNameSearchResponse
}

internal class CocktailApiImpl(
    private val httpClient: HttpClient
) : CocktailApi {
    override suspend fun getCategoryCocktails(category: String): CocktailsResponse =
        httpClient.get(urlString = getRoute(category = category))

    override suspend fun getCocktailDetails(cocktailId: Int): CocktailDetailsResponse =
        httpClient.get(urlString = "${ApiConstants.BASE_URL}/${ApiConstants.API_KEY}/lookup.php?i=$cocktailId")

    override suspend fun searchCocktailByName(cocktailName: String): CocktailNameSearchResponse =
        httpClient.get(urlString = "${ApiConstants.BASE_URL}/${ApiConstants.API_KEY}/search.php?s=$cocktailName")
}

object ApiConstants {
    const val API_KEY = BuildConfig.API_KEY
    const val BASE_URL = "https://www.thecocktaildb.com/api/json/v2"
}
