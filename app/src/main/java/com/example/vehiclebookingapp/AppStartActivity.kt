package com.example.vehiclebookingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vehiclebookingapp.customer.activities.CustomerStartActivity
import com.example.vehiclebookingapp.databinding.ActivityAppStartBinding
import com.example.vehiclebookingapp.driver.activities.DriverStartActivity

class AppStartActivity : AppCompatActivity() {

    private var _binding : ActivityAppStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAppStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDriver.setOnClickListener {
            startActivity(Intent(this@AppStartActivity, DriverStartActivity::class.java))
        }

        binding.btnUser.setOnClickListener {
            startActivity(Intent(this@AppStartActivity, CustomerStartActivity::class.java))
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}