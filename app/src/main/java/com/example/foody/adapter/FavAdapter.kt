package com.example.foody.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.databinding.FragmentFavCartItemBinding
import com.example.foody.model.FavModel

class FavAdapter(val pos:(Int)->Unit):ListAdapter<FavModel,FavAdapter.viewHolder>(favDiff){
    object favDiff:DiffUtil.ItemCallback<FavModel>() {
        override fun areItemsTheSame(oldItem: FavModel, newItem: FavModel): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: FavModel, newItem: FavModel): Boolean {
            return oldItem==newItem
        }

    }

    inner class viewHolder(val binding:FragmentFavCartItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return viewHolder(FragmentFavCartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val fav=getItem(position)
        holder.binding.apply {
            favImg.load(fav.imageUrl)
            foodName.text=fav.foodName
            cancelImg.setOnClickListener {
                Log.e("cancelImg","${fav.id}")
                pos.invoke(fav.id)
            }
        }
    }
}