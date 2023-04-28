package com.example.vehiclebookingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val intent = Intent(this@SplashActivity, AppStartActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}