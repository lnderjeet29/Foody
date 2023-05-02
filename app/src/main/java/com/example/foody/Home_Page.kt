package com.example.foody

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.foody.adapter.BannerAdapter
import com.example.foody.adapter.HomeAdapter
import com.example.foody.databinding.FragmentHomePageBinding
import com.example.foody.model.BannerData
import com.example.foody.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Home_Page : Fragment() {


    val binding: FragmentHomePageBinding by lazy {
        FragmentHomePageBinding.inflate(layoutInflater)
    }
    private val adapter: HomeAdapter by lazy {
        HomeAdapter { food,count->
            val action = Home_PageDirections.actionHomePageToItemDetailShow(food,count)
            findNavController().navigate(action)
        }
    }
    private val adapter2:HomeAdapter by lazy {
        HomeAdapter{food,count->
            val action=Home_PageDirections.actionHomePageToItemDetailShow(food,count)
            findNavController().navigate(action)
        }
    }

    private val homeViewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

    val imageList = listOf(
        BannerData(
            "https://cdn.dribbble.com/users/7855618/screenshots/17048233/media/01cbc31eb463942670e349fe6ccc894c.jpg?compress=1&resize=400x300&vertical=top",
            1
        ),
        BannerData(
            "https://img.freepik.com/free-vector/flat-design-food-facebook-ad_23-2149167379.jpg",
            2
        ),
        BannerData("https://img.lovepik.com/desgin_photo/45000/3303_list.jpg", 3),
    )


    val bannerAdapter = BannerAdapter()
    binding.vi.adapter = bannerAdapter

    bannerAdapter.submitList(imageList)
    scheduleNextPage()
    binding.search.setOnClickListener {
        findNavController().navigate(R.id.action_home_Page_to_exploreFragment2)
    }
    try {
        binding.rcCategory.adapter = adapter
        binding.rcSelling.adapter = adapter2
        homeViewModel.getAllCategories("SeaFood")
        homeViewModel.getCakeCategory("chicken")
        observe()
    } catch (e: Exception) {
        e.printStackTrace()
    }

        return binding.root
    }

    private fun observe() {
        homeViewModel.AllSeaFood.observe(viewLifecycleOwner) {
            adapter.submitList(it.meals)
        }
        homeViewModel.cakeCategory.observe(viewLifecycleOwner){
            Log.e("chicken","${it}")
            adapter2.submitList(it.meals)
        }
    }

   private  fun scheduleNextPage() {
        lifecycleScope.launch(Dispatchers.IO) {
            val viewPager = binding.vi
            while (true) {
                delay(4000)
                withContext(Dispatchers.Main) {
                    viewPager.currentItem = (viewPager.currentItem + 1) % 3
                }
            }
        }
    }


}