package com.example.foody

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.example.foody.adapter.CartAdapter
import com.example.foody.databinding.FragmentCartBinding
import com.example.foody.model.CartModel
import com.example.foody.model.OrderModel
import com.example.foody.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.immutableListOf

class CartFragment : Fragment() {



    private val binding:FragmentCartBinding by lazy {
        FragmentCartBinding.inflate(layoutInflater)
    }

    private val CAdapter by lazy {
        CartAdapter{id->
            deleteCart(id)
        }
    }

    private fun deleteCart(id:Int) {
        homeViewModel.deleteCart(id)
    }
    val list= mutableListOf<OrderModel>()

    private val homeViewModel:HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.btnOrderPlace.setOnClickListener {
            val dialog=inflater.inflate(R.layout.fragment_done_order,null)
            val animation=dialog.findViewById<LottieAnimationView>(R.id.animationView)

            val builder=AlertDialog.Builder(requireContext())
            builder.setView(dialog)
            val dia=builder.create()
            dia.show()
            animation.playAnimation()
            for (food in list)
            homeViewModel.insertOrderData(food)
        }
        binding.rcCart.adapter = CAdapter
        homeViewModel.initDB(requireContext())
        observe()

        return binding.root
    }

    private fun observe() {
       lifecycleScope.launch {
            homeViewModel.getCart().collectLatest {
                    CAdapter.submitList(it)
                for (food in it)
                    list.add(OrderModel(food.id,food.imageUrl,food.foodName))
            }
        }
    }
}