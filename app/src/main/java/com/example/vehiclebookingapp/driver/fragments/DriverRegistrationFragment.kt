package com.example.vehiclebookingapp.driver.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.databinding.FragmentDriverRegistrationBinding
import com.example.vehiclebookingapp.driver.SharedPrefManagerDriver
import com.example.vehiclebookingapp.driver.data.DriverRepo
import com.example.vehiclebookingapp.driver.model.Driver
import com.example.vehiclebookingapp.driver.model.DriverViewModel
import com.example.vehiclebookingapp.driver.model.DriverViewModelFactory

class DriverRegistrationFragment : Fragment() {

    var _binding: FragmentDriverRegistrationBinding? = null
    val binding get() = _binding!!


    lateinit var driverName: String
    lateinit var driverEmail: String
    lateinit var driverCNIC: String
    lateinit var driverPhoneNumber: String
    lateinit var driverPassword: String

    private lateinit var viewModel : DriverViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriverRegistrationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val viewModelFactory = DriverViewModelFactory(DriverRepo())
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[DriverViewModel::class.java]

        binding.btnDriverRegister.setOnClickListener {
            checkData()

        }

        binding.tvDriverLogin.setOnClickListener {
            val navController = findNavController()
            val action = R.id.action_registrationFragment2_to_driverLoginFragment
            navController.navigate(action)
        }


        return binding.root
    }

    private fun registerUser() {

        val driver = Driver(
            email = driverEmail,
            name = driverName,
            password = driverPassword,
            phoneNo = driverPhoneNumber,
            cnic = driverCNIC
        )

        val sharedPrefManagerDriver = SharedPrefManagerDriver(requireContext())

        sharedPrefManagerDriver.saveDriver(driver)
        viewModel.registerDriver(driver)

        viewModel.registerResponse.observe(viewLifecycleOwner){
            Log.d("RESPONSEDATA", it.toString())
        }



    }

    fun checkData() {

        driverName = binding.etDriverName.text.toString()
        driverEmail = binding.etDriverEmailAddress.text.toString()
        driverCNIC = binding.etDriverCNIC.text.toString()
        driverPhoneNumber = binding.etDriverPhoneNumber.text.toString()
        driverPassword = binding.etDriverPassword.text.toString()

        if (driverCNIC.isEmpty()) binding.etDriverCNIC.error = "CNIC is Empty"
        if (driverName.isEmpty()) binding.etDriverName.error = "Name is Empty"
        if (driverEmail.isEmpty()) binding.etDriverEmailAddress.error = "Email is Empty"
        if (driverPhoneNumber.isEmpty()) binding.etDriverPhoneNumber.error = "Phone Number is Empty"
        if (driverPassword.isEmpty()) binding.etDriverPassword.error = "Password is Empty"

        if (driverCNIC.isNotEmpty() && driverName.isNotEmpty() && driverEmail.isNotEmpty() && driverPhoneNumber.isNotEmpty() && driverPassword.isNotEmpty()) {
            registerUser()
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}