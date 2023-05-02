package com.example.foody

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.foody.databinding.FragmentSplashScreenBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay

class Splash_Screen : Fragment() {
   private val binding:FragmentSplashScreenBinding by lazy {
       FragmentSplashScreenBinding.inflate(layoutInflater)
   }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var flag:Boolean=false
        if(FirebaseAuth.getInstance().currentUser!=null){
            flag=true;
        }
        lifecycleScope.launchWhenStarted {
            delay(2000)
            if(flag){
                val intent=Intent(requireActivity(),HomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }else {
                findNavController().navigate(R.id.action_splash_Screen_to_onBord)
            }
        }
        return binding.root
    }

}