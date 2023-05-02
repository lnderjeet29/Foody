package com.example.foody

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.foody.databinding.FragmentAuthBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import kotlin.math.log


class Auth : Fragment() {

    private val binding:FragmentAuthBinding by lazy {
        FragmentAuthBinding.inflate(layoutInflater)
    }

    private lateinit var signInLauncher : ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       signInLauncher = registerForActivityResult(FirebaseAuthUIActivityResultContract()){res->
           onSignInResult(res)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding.googleSignBtn.setOnClickListener {
             startAuthentication(AuthUI.IdpConfig.GoogleBuilder().build())
        }

        return binding.root
    }

    fun startAuthentication(build: AuthUI.IdpConfig) {
        AuthUI.getInstance().signOut(requireContext())
        val providers = arrayListOf(
            build
        )
        val signInIntent= AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setIsSmartLockEnabled(false)
            .build()
        signInLauncher.launch(signInIntent)
    }
    @SuppressLint("RestrictedApi")
    fun onSignInResult(res: FirebaseAuthUIAuthenticationResult?) {
        try {
        if(res?.idpResponse?.isSuccessful==true){
               val intent=Intent(requireActivity(),HomeActivity::class.java)
               startActivity(intent)

           // res?.idpResponse?.user?.photoUri.toString()
        }else{
            Toast.makeText(context," login failed", Toast.LENGTH_LONG).show()
        }}catch (e:java.lang.Exception){
            Log.e("signProblem","eorr occur")
            e.printStackTrace()
        }
    }


}