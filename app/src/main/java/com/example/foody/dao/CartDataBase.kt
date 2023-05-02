package com.example.foody.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foody.model.CartModel
import com.example.foody.model.FavModel
import com.example.foody.model.OrderModel

@Database(entities = arrayOf(FavModel::class,CartModel::class,OrderModel::class), version = 1, exportSchema = false)
abstract class CartDataBase:RoomDatabase() {
    abstract fun DaoCart() : CartDao
    companion object{
        private var INSTANCE: CartDataBase?=null
        fun getCartDatabse(ApplicationContext:Context): CartDataBase?{
            if(INSTANCE==null)
                    INSTANCE=Room.databaseBuilder(ApplicationContext,CartDataBase::class.java,"cartDB").build()
        return INSTANCE
        }
    }
}