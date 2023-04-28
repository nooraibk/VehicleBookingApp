package com.example.vehiclebookingapp.customer.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.databinding.ActivityMainUserBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivityUser : AppCompatActivity() {

    private lateinit var binding: ActivityMainUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolBar)


        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main_user)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
//                R.id.userHomeFragment, R.id.userDashboardFragment,
                R.id.userNotificationsFragment
            , R.id.userBookingFragment, R.id.userBookingsFragment
            )
        )


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}