package com.example.foody.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.databinding.AddToCartItemBinding
import com.example.foody.model.CartModel

class CartAdapter(val CPosition:(Int)->Unit):ListAdapter<CartModel,CartAdapter.ViewHolder>(homDiff) {
    object homDiff:DiffUtil.ItemCallback<CartModel>() {
        override fun areItemsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
            return oldItem==newItem
        }

    }

    inner class ViewHolder(val binding:AddToCartItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(AddToCartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food=getItem(position)
        holder.binding.apply {
            imgFood.load(food.imageUrl)
            foodName.text=food.foodName
            var i:Int=1
            var cost:Int=3
            itemCountTxt.text=i.toString()
            costTxt.text="$"+(cost*i).toString()
            itemAdd.setOnClickListener {
                i++
                itemCountTxt.text=i.toString()
                costTxt.text="$"+(i*cost).toString()
            }
            itemSubstract.setOnClickListener {
                if(i>0)
                    i--
                itemCountTxt.text=i.toString()
                costTxt.text="$"+(i*cost).toString()
            }
            cancelImg.setOnClickListener {
                CPosition.invoke(food.id)
            }
        }
    }
}