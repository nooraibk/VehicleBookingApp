package com.example.vehiclebookingapp.customer.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.driver.data.DriverRepo

class UserBookingViewModelFactory(private val userRepo: UserRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(UserBookingViewModel::class.java)) {
            return UserBookingViewModel(userRepo) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}