package com.example.foody.repo

import android.util.Log
import com.example.foody.model.SeaFoodData
import com.example.foody.model.SearchData

object Repository {

    private  val apiServic=Network.getApiServices()

    suspend fun getAllSeaCategoriesFood(cateory:String):SeaFoodData{
        return apiServic.getSeaFoodCategories(cateory)
    }

    suspend fun getSearchData(searchFood:String):SearchData{
        return apiServic.getSearchFood(searchFood)
    }

    suspend fun getFoodById(foodId:String):SeaFoodData{
        val response =  apiServic.getFoodById(foodId)
        Log.e("food","${response.body().toString()}")

        return response.body()!!
    }

}