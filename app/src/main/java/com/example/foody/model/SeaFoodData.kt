package com.example.foody.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeaFoodData(
    val meals: List<Meal>
):Parcelable{
    @Parcelize
    data class Meal(
        val idMeal: String,
        val strMeal: String,
        val strMealThumb: String,
        val strInstructions:String
    ):Parcelable
}