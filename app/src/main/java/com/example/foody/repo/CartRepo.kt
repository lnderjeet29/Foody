package com.example.foody.repo

import android.content.Context
import com.example.foody.dao.CartDataBase
import com.example.foody.model.CartModel
import com.example.foody.model.FavModel
import com.example.foody.model.OrderModel
import kotlinx.coroutines.flow.Flow

class CartRepo(context: Context) {
    private val cartDao=CartDataBase.getCartDatabse(context)?.DaoCart()
    fun getCartItem():Flow<List<CartModel>>{
        return cartDao!!.getCartData()
    }

    suspend fun insertCartItem(CardItem:CartModel){
        cartDao?.setCartData(CardItem)
    }

    suspend fun deleteCart(id:Int){
        cartDao?.deleteCart(id)
    }
    fun getFavItem():Flow<List<FavModel>>{
        return cartDao!!.getFavData()
    }
    suspend fun insertFavItem(FavItem:FavModel){
        cartDao?.setfavData(FavItem)
    }
    suspend fun deleteFav(id:Int){
        cartDao?.deleteFav(id)
    }
    fun getOrderHistory():Flow<List<OrderModel>>{
        return cartDao!!.getOrderData()
    }
    suspend fun insertOrderData(item:OrderModel){
        cartDao?.setOrderData(item)
    }
}