package com.example.vehiclebookingapp.customer.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.databinding.ActivityCustomerStartBinding

class CustomerStartActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityCustomerStartBinding
    private val binding get() = _binding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCustomerStartBinding.inflate(layoutInflater)
        setContentView(binding.root)


        navController = findNavController(R.id.nav_host_fragment_user)



        /*  val action = R.id.nav_host_fragment_user
          navController.navigate(action)*/


    }
}