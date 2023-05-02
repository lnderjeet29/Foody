package com.example.foody.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foody.model.CartModel
import com.example.foody.model.FavModel
import com.example.foody.model.OrderModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {
    @Query("Select * from cartItem")
    fun getCartData():Flow<List<CartModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setCartData(cart:CartModel)

    @Query("Delete from cartItem where id is :id")
    suspend fun deleteCart(id:Int)

    @Query("Select * from favItem")
    fun getFavData():Flow<List<FavModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setfavData(fav:FavModel)

    @Query("Delete from favItem where id is :id")
    suspend fun deleteFav(id:Int)

    @Query("Select * from OrderHistory")
    fun getOrderData():Flow<List<OrderModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setOrderData(fav:OrderModel)
}

