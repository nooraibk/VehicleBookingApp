package com.example.vehiclebookingapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.vehiclebookingapp.customer.SharedPrefManagerUser
import com.example.vehiclebookingapp.customer.activities.CustomerStartActivity
import com.example.vehiclebookingapp.customer.activities.MainActivityUser
import com.example.vehiclebookingapp.databinding.ActivityAppStartBinding
import com.example.vehiclebookingapp.driver.SharedPrefManagerDriver
import com.example.vehiclebookingapp.driver.activities.DriverMainActivity
import com.example.vehiclebookingapp.driver.activities.DriverStartActivity

class AppStartActivity : AppCompatActivity() {

    private var _binding : ActivityAppStartBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAppStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val driverPrefs = SharedPrefManagerDriver(applicationContext)
        val userPrefs = SharedPrefManagerUser(applicationContext)

        if (driverPrefs.getDriver().id == 0 && userPrefs.getUser().id == 0) {
            binding.btnDriver.setOnClickListener {
                startActivity(Intent(this@AppStartActivity, DriverStartActivity::class.java))
            }

            binding.btnUser.setOnClickListener {
                startActivity(Intent(this@AppStartActivity, CustomerStartActivity::class.java))
            }
        }
        if (driverPrefs.getDriver().id != 0) {
            startActivity(Intent(this@AppStartActivity, DriverMainActivity::class.java))
        }

        if(userPrefs.getUser().id != 0){
            startActivity(Intent(this@AppStartActivity, MainActivityUser::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}