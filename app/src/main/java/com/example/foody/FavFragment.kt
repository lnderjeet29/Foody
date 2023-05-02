package com.example.foody

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.foody.adapter.FavAdapter
import com.example.foody.databinding.FragmentFavBinding
import com.example.foody.viewModel.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class FavFragment : Fragment() {


    private val binding:FragmentFavBinding by lazy {
        FragmentFavBinding.inflate(layoutInflater)
    }
    private val homeViewModel:HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    private val favAdapter by lazy {
        FavAdapter{
            id->homeViewModel.deleteFav(id)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeViewModel.initDB(requireContext())
        binding.rcFav.adapter=favAdapter
        setData()
        return binding.root
    }

    private fun setData() {
        lifecycleScope.launch {
            homeViewModel.getFav().collectLatest {
                favAdapter.submitList(it)
            }
        }
    }


}