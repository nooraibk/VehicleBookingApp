package com.example.vehiclebookingapp.driver.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.databinding.FragmentDriverLoginBinding
import com.example.vehiclebookingapp.driver.SharedPrefManagerDriver
import com.example.vehiclebookingapp.driver.activities.DriverMainActivity
import com.example.vehiclebookingapp.driver.data.DriverRepo
import com.example.vehiclebookingapp.driver.model.DriverLoginViewModel
import com.example.vehiclebookingapp.driver.model.DriverLoginViewModelFactory
import com.example.vehiclebookingapp.driver.model.LoginRequest

class DriverLoginFragment : Fragment() {

    private var _binding: FragmentDriverLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DriverLoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriverLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val viewModelFactory = DriverLoginViewModelFactory(DriverRepo())
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[DriverLoginViewModel::class.java]

        binding.btnDriverLogin.setOnClickListener {
            loginDriver()
            viewModel.loginResponse.observe(viewLifecycleOwner) {
                it.driver?.let { it1 -> SharedPrefManagerDriver(requireContext()).saveDriver(it1) }
            //    if (it.message?.contains("Success") == true)
                startActivity(Intent(activity, DriverMainActivity::class.java))
                Log.d("Login User Data", it.driver.toString())
            }
        }

        binding.tvGoToRegister.setOnClickListener {
            val action = R.id.action_driverLoginFragment_to_registrationFragment2
            findNavController().navigate(action)
        }

        return binding.root
    }


    private fun loginDriver() {
        val email = binding.etDriverLoginEmail.text.toString()
        val password = binding.etDriverLoginPassword.text.toString()

        val loginRequest = LoginRequest(email, password)
        viewModel.login(loginRequest)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}