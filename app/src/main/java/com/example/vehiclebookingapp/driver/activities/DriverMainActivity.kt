package com.example.vehiclebookingapp.driver.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.databinding.ActivityMainDriverBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class DriverMainActivity : AppCompatActivity() {

    private var _binding: ActivityMainDriverBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainDriverBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)

        val navView: BottomNavigationView = binding.bottomNavigation

        navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.driverFragmentHome, R.id.historyFragment, R.id.driverBookingsFragment
                , R.id.vehiclesFragment, R.id.profileFragment
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}