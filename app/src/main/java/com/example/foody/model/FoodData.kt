package com.example.foody.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodData(
    val categories: List<Category>
):Parcelable{
    @Parcelize
    data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
):Parcelable
}