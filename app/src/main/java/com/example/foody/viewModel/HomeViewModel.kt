package com.example.foody.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foody.model.*
import com.example.foody.repo.CartRepo
import com.example.foody.repo.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    val AllSeaFood=MutableLiveData<SeaFoodData>()
    val searchFood=MutableLiveData<SearchData>()
    val foodById=MutableLiveData<SeaFoodData.Meal>()
    val cakeCategory=MutableLiveData<SeaFoodData>()
    private lateinit var repo:CartRepo
    fun getAllCategories(category:String){
        viewModelScope.launch {
            val food=Repository.getAllSeaCategoriesFood(category)
            AllSeaFood.postValue(food)
        }
    }
    fun getCakeCategory(category: String){
       viewModelScope.launch {
           val food =Repository.getAllSeaCategoriesFood(category)
           cakeCategory.postValue(food)
       }

    }
    fun getSearchFood(food_name:String){
        viewModelScope.launch {
            val search=Repository.getSearchData(food_name)
            searchFood.postValue(search)
        }
    }
    fun getFoodById(foodId:String){
        viewModelScope.launch {
            val food=Repository.getFoodById(foodId)
            foodById.postValue(food.meals.first())
        }
    }
    fun initDB(applicationContext: Context){
        repo= CartRepo(applicationContext)
    }
    fun getCart(): Flow<List<CartModel>>{
        return repo.getCartItem()
    }
    fun insertCart(cartData:CartModel){
        viewModelScope.launch {
            repo.insertCartItem(cartData)
        }
    }
    fun deleteCart(id:Int){
        viewModelScope.launch {
            repo.deleteCart(id)
        }
    }
    fun getFav(): Flow<List<FavModel>>{
        return repo.getFavItem()
    }
    fun insertFav(favData:FavModel){
        viewModelScope.launch {
            repo.insertFavItem(favData)
        }
    }
    fun deleteFav(id:Int){
        viewModelScope.launch {
            repo.deleteFav(id)
        }
    }
    fun getOrderData():Flow<List<OrderModel>>{
        return repo.getOrderHistory()
    }
    fun insertOrderData(orderData: OrderModel){
        viewModelScope.launch {
            repo.insertOrderData(orderData)
        }
    }
}