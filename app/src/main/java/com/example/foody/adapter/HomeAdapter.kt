package com.example.foody.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.foody.databinding.FragmentFoodItemShowBinding
import com.example.foody.model.SeaFoodData

class HomeAdapter(val FPosition:(String,Int)->Unit):ListAdapter<SeaFoodData.Meal,HomeAdapter.ViewHolder>(HomeDiff) {
    object HomeDiff:DiffUtil.ItemCallback<SeaFoodData.Meal>() {
        override fun areItemsTheSame(
            oldItem: SeaFoodData.Meal,
            newItem: SeaFoodData.Meal
        ): Boolean {
            return oldItem.idMeal==newItem.idMeal
        }

        override fun areContentsTheSame(
            oldItem: SeaFoodData.Meal,
            newItem: SeaFoodData.Meal
        ): Boolean {
            return oldItem==newItem
        }

    }

    var i:Int=0


    inner class ViewHolder(val binding:FragmentFoodItemShowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentFoodItemShowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val food=getItem(position)
        holder.binding.apply {
            itemImg.load(food.strMealThumb)
            txtItem.text=food.strMeal
            var count:Int=itemCount.text.toString().toInt()
            btnItemAdd.setOnClickListener {
                count++
                itemCount.text= count.toString()
            }
            i++
            if(i<10)
                costTxt.text= "$$i"
            itemImg.setOnClickListener {
                FPosition.invoke(food.idMeal,count)
            }

        }
    }
}