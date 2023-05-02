package com.example.foody.Apiservice

import com.example.foody.model.SeaFoodData
import com.example.foody.model.SearchData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("/api/json/v1/1/filter.php")
    suspend fun getSeaFoodCategories(
        @Query("c")category:String
    ):SeaFoodData

    @GET("/api/json/v1/1/search.php")
    suspend fun getSearchFood(
        @Query("s")search:String
    ):SearchData

    @GET("/api/json/v1/1/lookup.php")
    suspend fun getFoodById (
        @Query("i")id:String
    ): Response<SeaFoodData>
}