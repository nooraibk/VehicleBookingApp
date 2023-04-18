package com.example.vehiclebookingapp.customer.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.driver.data.DriverRepo

class CarsViewModelFactory(private val userRepo: UserRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(CarsViewModel::class.java)) {
            return CarsViewModel(userRepo) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}