package com.example.foody.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cartItem")
data class CartModel(
    @PrimaryKey
    val id:Int,
    val imageUrl:String,
    val foodName:String
):Parcelable
