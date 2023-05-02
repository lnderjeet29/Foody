package com.example.foody

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.adapter.HomeAdapter
import com.example.foody.adapter.SearchAdapter
import com.example.foody.databinding.FragmentExploreBinding
import com.example.foody.viewModel.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class ExploreFragment : Fragment() {
    private val binding: FragmentExploreBinding by lazy {
        FragmentExploreBinding.inflate(layoutInflater)
    }
    private val adapter: SearchAdapter by lazy {
        SearchAdapter{food,count->
            val action=ExploreFragmentDirections.actionExploreFragmentToItemDetailShow(food,count)
            findNavController().navigate(action)
        }
    }
    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bottomNavigationView = requireActivity().findViewById(R.id.bottom_nav)
        bottomNavigationView.visibility = View.GONE
        binding.rcSerachItem.adapter=adapter

        try {
            homeViewModel.getSearchFood("c")
            binding.searchFood.doAfterTextChanged {
                homeViewModel.getSearchFood(binding.searchFood.text.toString())
            }
            observe()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return binding.root
    }

    private fun observe() {
        homeViewModel.searchFood.observe(viewLifecycleOwner) {
            adapter.submitList(it.meals)
        }
    }

    override fun onPause() {
        super.onPause()
        bottomNavigationView.visibility = View.VISIBLE
    }


}