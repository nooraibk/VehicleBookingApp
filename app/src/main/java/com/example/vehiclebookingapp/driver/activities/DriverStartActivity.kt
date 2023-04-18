package com.example.vehiclebookingapp.driver.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.databinding.ActivityDriverStartBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DriverStartActivity : AppCompatActivity() {

    var _binding: ActivityDriverStartBinding? = null
    val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDriverStartBinding.inflate(layoutInflater)
        setContentView(binding.root)



        navController = findNavController(R.id.nav_host_fragment_driver)



    }
}