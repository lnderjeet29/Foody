package com.example.foody

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.foody.databinding.FragmentItemDetailShowBinding
import com.example.foody.model.CartModel
import com.example.foody.model.FavModel
import com.example.foody.viewModel.HomeViewModel

class ItemDetailShow : Fragment() {

    private val args: ItemDetailShowArgs by navArgs()


    var flag: Boolean = true
    private val binding: FragmentItemDetailShowBinding by lazy {
        FragmentItemDetailShowBinding.inflate(layoutInflater)
    }
    private val homeView: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeView.getFoodById(args.FoodId)
        Log.e("foodId", "${args.FoodId}")

        setData()
        var i: Int

        binding.itemAdd.setOnClickListener {
            i = binding.itemCountTxt.text.toString().toInt()
            i++
            binding.itemCountTxt.text = i.toString()
        }
        binding.itemSubstract.setOnClickListener {

            i = binding.itemCountTxt.text.toString().toInt()
            if (i > 0)
                i--
            binding.itemCountTxt.text = i.toString()
        }
        homeView.initDB(requireContext())

        return binding.root
    }


    private fun setData() {
        var oneItem = CartModel(0, "", "")
        var favItem = FavModel(0, "", "")

        homeView.foodById.observe(viewLifecycleOwner) {
            Log.e("foodIdData", "${it}")

            binding.apply {
                itemImg.load(it.strMealThumb)
                itemNameTxt.text = it.strMeal
                disTxt.text = it.strInstructions
                itemCountTxt.text = args.count.toString()
                oneItem = CartModel(it.idMeal.toInt(), it.strMealThumb, it.strMeal)
                favItem= FavModel(it.idMeal.toInt(), it.strMealThumb, it.strMeal)
            }
        }
        binding.favBtn.setOnClickListener {
            if (flag) {
                binding.favBtn.setImageResource(R.drawable.like_ic)

                homeView.insertFav(favItem)
                flag = false
            } else {
                binding.favBtn.setImageResource(R.drawable.unlike_ic)
                flag = true
            }
        }

        binding.itemAddCard.setOnClickListener {
            Toast.makeText(requireContext(), "Add on backet", Toast.LENGTH_SHORT).show()
            Log.e("backet", "click")
            homeView.initDB(requireContext())
            homeView.getCart()
            homeView.insertCart(oneItem)
        }
    }
}