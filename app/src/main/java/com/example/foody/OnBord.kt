package com.example.foody

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.foody.databinding.FragmentOnBordBinding
import com.google.firebase.auth.FirebaseAuth


class OnBord : Fragment() {

    private  val binding:FragmentOnBordBinding by lazy {
        FragmentOnBordBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.btnGetStart.setOnClickListener {
            findNavController().navigate(R.id.action_onBord_to_auth)
        }
        return binding.root
    }

}