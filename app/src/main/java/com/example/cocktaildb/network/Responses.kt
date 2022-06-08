package com.example.cocktaildb.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CocktailsResponse(
    @SerialName("drinks")
    val drinks: List<Cocktail>
)

@Serializable
data class Cocktail(
    @SerialName("idDrink")
    val idDrink: Int,
    @SerialName("strDrinkThumb")
    val strDrinkThumb: String?,
    @SerialName("strDrink")
    val strDrink: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cocktail

        if (idDrink != other.idDrink) return false
        if (strDrink != other.strDrink) return false

        return true
    }

    override fun hashCode(): Int {
        var result = idDrink
        result = 31 * result + strDrink.hashCode()
        return result
    }
}

@Serializable
data class CocktailDetailsResponse(
    @SerialName("drinks")
    val drinks: List<CocktailDetails>
)

@Serializable
data class CocktailDetails(
    @SerialName("idDrink")
    val idDrink: Int,
    @SerialName("strDrink")
    val strDrink: String,
    @SerialName("strDrinkThumb")
    val strDrinkThumb: String? = null,
    @SerialName("strCategory")
    val strCategory: String,
    @SerialName("strAlcoholic")
    val strAlcoholic: String,
    @SerialName("strIngredient1")
    val strIngredient1: String? = null,
    @SerialName("strIngredient2")
    val strIngredient2: String? = null,
    @SerialName("strIngredient3")
    val strIngredient3: String? = null,
    @SerialName("strIngredient4")
    val strIngredient4: String? = null,
    @SerialName("strIngredient5")
    val strIngredient5: String? = null,
    @SerialName("strIngredient6")
    val strIngredient6: String? = null,
    @SerialName("strIngredient7")
    val strIngredient7: String? = null,
    @SerialName("strIngredient8")
    val strIngredient8: String? = null,
    @SerialName("strIngredient9")
    val strIngredient9: String? = null,
    @SerialName("strIngredient10")
    val strIngredient10: String? = null,
    @SerialName("strIngredient11")
    val strIngredient11: String? = null,
    @SerialName("strIngredient12")
    val strIngredient12: String? = null,
    @SerialName("strIngredient13")
    val strIngredient13: String? = null,
    @SerialName("strIngredient14")
    val strIngredient14: String? = null,
    @SerialName("strIngredient15")
    val strIngredient15: String? = null,
    @SerialName("strMeasure1")
    val strMeasure1: String? = null,
    @SerialName("strMeasure2")
    val strMeasure2: String? = null,
    @SerialName("strMeasure3")
    val strMeasure3: String? = null,
    @SerialName("strMeasure4")
    val strMeasure4: String? = null,
    @SerialName("strMeasure5")
    val strMeasure5: String? = null,
    @SerialName("strMeasure6")
    val strMeasure6: String? = null,
    @SerialName("strMeasure7")
    val strMeasure7: String? = null,
    @SerialName("strMeasure8")
    val strMeasure8: String? = null,
    @SerialName("strMeasure9")
    val strMeasure9: String? = null,
    @SerialName("strMeasure10")
    val strMeasure10: String? = null,
    @SerialName("strMeasure11")
    val strMeasure11: String? = null,
    @SerialName("strMeasure12")
    val strMeasure12: String? = null,
    @SerialName("strMeasure13")
    val strMeasure13: String? = null,
    @SerialName("strMeasure14")
    val strMeasure14: String? = null,
    @SerialName("strMeasure15")
    val strMeasure15: String? = null,
    @SerialName("strInstructions")
    val strInstructions: String? = null,
    @SerialName("strTags")
    val strTags: String? = null
)

@Serializable
data class CocktailNameSearchResponse(
    @SerialName("drinks")
    val drinks: List<Cocktail>
)
