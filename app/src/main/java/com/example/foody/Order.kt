package com.example.foody

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.foody.adapter.CartAdapter
import com.example.foody.adapter.OrderAdapter
import com.example.foody.databinding.FragmentOrderBinding
import com.example.foody.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class Order:Fragment() {
    private val binding:FragmentOrderBinding by lazy {
        FragmentOrderBinding.inflate(layoutInflater)
    }
    private val homeviewModel:HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val adapter  by lazy {
       OrderAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeviewModel.initDB(requireContext())
        binding.rcHistory.adapter=adapter
        setData()
        return binding.root
    }

    private fun setData() {
        lifecycleScope.launch {
            homeviewModel.getOrderData().collectLatest {
                adapter.submitList(it)
            }
        }
    }
}