package com.example.foody

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.foody.databinding.FragmentAccountBinding
import com.google.firebase.auth.FirebaseAuth

class AccountFragment : Fragment() {
    private val binding:FragmentAccountBinding by lazy {
        FragmentAccountBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val Firbase=FirebaseAuth.getInstance().currentUser
        binding.profileImg.load( Firbase?.photoUrl.toString())
        binding.userName.text=Firbase?.displayName.toString()
        binding.userEmailId.text=Firbase?.email.toString()
        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent=Intent(requireActivity(),MainActivity::class.java)
            startActivity(intent)
            requireActivity().finishAffinity()
        }
        binding.myOrderLayout.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_order)
        }
        binding.aboutLayout.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_aboutUs)
        }
        return binding.root
    }

}