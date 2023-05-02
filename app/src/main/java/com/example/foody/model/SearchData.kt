package com.example.foody.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchData(
    val meals: List<Meal>
):Parcelable{
    @Parcelize
    data class Meal(
        val idMeal: String,
        val strArea: String,
        val strCategory: String,
        val strMeal: String,
        val strMealThumb: String,
    ):Parcelable
}