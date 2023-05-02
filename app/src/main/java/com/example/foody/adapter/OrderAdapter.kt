package com.example.foody.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.databinding.AddToCartItemBinding
import com.example.foody.databinding.FragmentFavCartItemBinding
import com.example.foody.model.CartModel
import com.example.foody.model.OrderModel

class OrderAdapter :ListAdapter<OrderModel,OrderAdapter.ViewHolder>(homDiff){
    object homDiff: DiffUtil.ItemCallback<OrderModel>() {
        override fun areItemsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: OrderModel, newItem: OrderModel): Boolean {
            return oldItem==newItem
        }


    }

    inner class ViewHolder(val binding: FragmentFavCartItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentFavCartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food=getItem(position)
        holder.binding.apply {
            favImg.load(food.imageUrl)
            foodName.text=food.foodName
            cancelImg.visibility=View.GONE
        }
    }
}