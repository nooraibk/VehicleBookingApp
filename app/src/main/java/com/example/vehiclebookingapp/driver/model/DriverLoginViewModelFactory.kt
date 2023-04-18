package com.example.vehiclebookingapp.driver.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vehiclebookingapp.driver.data.DriverRepo

class DriverLoginViewModelFactory(private val repo : DriverRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DriverLoginViewModel::class.java)) {
            return DriverLoginViewModel(repo) as T
        }
         throw   IllegalStateException("DriverLogin ViewModel Not Found")
    }
}