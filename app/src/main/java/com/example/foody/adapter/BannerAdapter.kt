package com.example.foody.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.databinding.FragmentBannerImageBinding
import com.example.foody.model.BannerData

class BannerAdapter():ListAdapter<BannerData,BannerAdapter.ViewHolder>(homDiff) {

    object homDiff:DiffUtil.ItemCallback<BannerData>() {
        override fun areItemsTheSame(oldItem: BannerData, newItem: BannerData): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: BannerData, newItem: BannerData): Boolean {
            return oldItem==newItem
        }

    }

    inner class ViewHolder (val binding:FragmentBannerImageBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentBannerImageBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image=getItem(position)
        holder.binding.img.load(image.imageUrl)
    }

}