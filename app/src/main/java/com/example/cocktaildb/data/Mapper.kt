package com.example.cocktaildb.data

import com.example.cocktaildb.ui.screens.CocktailDetailsViewState
import com.example.cocktaildb.utils.CocktailViewState
import com.google.gson.Gson
import org.json.JSONObject

fun CocktailViewState.toCocktail() = Cocktail(
    idDrink = id,
    strDrinkThumb = imageUrl,
    strDrink = cocktailName
)

fun Cocktail.toCocktailViewState(
    isFavorite: Boolean
) = CocktailViewState(
    id = idDrink,
    imageUrl = strDrinkThumb,
    cocktailName = strDrink,
    isFavorite = isFavorite
)

fun toCocktailDetailsViewState(
    cocktailDetails: CocktailDetails
): CocktailDetailsViewState {
    val gson = Gson()
    var jsonString = gson.toJson(cocktailDetails)
    val jsonObject = JSONObject(jsonString)

    val ingredients = mutableListOf<String>()
    val measures = mutableListOf<String>()

    for (i in 1..15) {
        if (jsonObject.has("strIngredient$i") && jsonObject.has("strMeasure$i")) {
            val ingredient = jsonObject.getString("strIngredient$i")
            val measure = jsonObject.getString("strMeasure$i")

            if (ingredient.isNotEmpty() && measure.isNotEmpty()) {
                ingredients.add(ingredient)
                measures.add(measure)
            }
        }
    }

    val tags = cocktailDetails.strTags?.split(",")?.toList()

    return CocktailDetailsViewState(
        id = cocktailDetails.idDrink,
        imageUrl = cocktailDetails.strDrinkThumb,
        name = cocktailDetails.strDrink,
        category = cocktailDetails.strCategory,
        alcoholic = cocktailDetails.strAlcoholic,
        ingredients = ingredients,
        measures = measures,
        instructions = cocktailDetails.strInstructions,
        tags = tags
    )
}

fun getRoute(category: String): String {
    val baseRoute = "${ApiConstants.BASE_URL}/${ApiConstants.API_KEY}"
    return when (category) {
        "Popular" -> "$baseRoute/${category.lowercase()}.php"
        "Latest" -> "$baseRoute/${category.lowercase()}.php"
        "Random" -> "$baseRoute/${category.lowercase()}selection.php"
        "Vodka" -> "$baseRoute/filter.php?i=${category.lowercase()}"
        "Gin" -> "$baseRoute/filter.php?i=${category.lowercase()}"
        "Rum" -> "$baseRoute/filter.php?i=${category.lowercase()}"
        "Tequila" -> "$baseRoute/filter.php?i=${category.lowercase()}"
        else -> "$baseRoute/randomselection.php"
    }
}
