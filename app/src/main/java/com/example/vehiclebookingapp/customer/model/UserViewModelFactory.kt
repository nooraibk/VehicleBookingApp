package com.example.vehiclebookingapp.customer.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.driver.data.DriverRepo

class UserViewModelFactory(private val userRepo: UserRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass : Class<T>) : T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepo) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}