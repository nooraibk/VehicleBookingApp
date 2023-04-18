package com.example.vehiclebookingapp.driver.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vehiclebookingapp.driver.data.DriverRepo

class CarViewModelFactory(private val driverRepo: DriverRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(DriverCarsViewModel::class.java)) {
            return DriverCarsViewModel(driverRepo) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}