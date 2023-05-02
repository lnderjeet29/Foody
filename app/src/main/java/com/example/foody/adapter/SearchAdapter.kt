package com.example.foody.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.databinding.FragmentFoodItemShowBinding
import com.example.foody.model.SearchData

class SearchAdapter(val FPosition:(String,Int)->Unit): ListAdapter<SearchData.Meal, SearchAdapter.ViewHolder>(HomDiff) {
    var i:Int=0
    inner class ViewHolder (val binding:FragmentFoodItemShowBinding):RecyclerView.ViewHolder(binding.root)

    object HomDiff:DiffUtil.ItemCallback<SearchData.Meal>() {
        override fun areItemsTheSame(oldItem: SearchData.Meal, newItem: SearchData.Meal): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(
            oldItem: SearchData.Meal,
            newItem: SearchData.Meal
        ): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentFoodItemShowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val search=getItem(position)
        holder.binding.apply {
            itemImg.load(search.strMealThumb)
            txtItem.text=search.strArea
            var count:Int=itemCount.text.toString().toInt()
            btnItemAdd.setOnClickListener {
                count++
                itemCount.text= count.toString()
            }
            itemImg.setOnClickListener {
                FPosition.invoke(search.idMeal,count)
            }

            i++
            if(i<10)
                costTxt.text= "$$i"
        }
    }
}