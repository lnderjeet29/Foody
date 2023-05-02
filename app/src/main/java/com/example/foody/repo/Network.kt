package com.example.foody.repo

import com.example.foody.Apiservice.ApiServices
import com.example.foody.model.SeaFoodData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object Network {
    private val BASE_URL="https://www.themealdb.com"
    fun getApiServices():ApiServices{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
}