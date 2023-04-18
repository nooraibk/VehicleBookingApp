package com.example.vehiclebookingapp.driver.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vehiclebookingapp.driver.data.DriverRepo

class DriverViewModelFactory(private val repo : DriverRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DriverViewModel::class.java)) {
            return DriverViewModel(repo) as T
        }
        throw IllegalStateException("Model class not Initialized")
    }
}