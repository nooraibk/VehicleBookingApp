package com.example.vehiclebookingapp.customer.data

import com.example.vehiclebookingapp.customer.model.BookingBodyModel
import com.example.vehiclebookingapp.customer.model.LoginRequest
import com.example.vehiclebookingapp.customer.model.UserRegister
import com.example.vehiclebookingapp.customer.network.RetrofitInstanceUser

class UserRepo() {

    private val retrofit = RetrofitInstanceUser.getInstance()

    suspend fun registerUser(user : UserRegister) = retrofit.registerUser(user)

    suspend fun login(loginRequest: LoginRequest) = retrofit.login(loginRequest)

    suspend fun getAllCars() = retrofit.getAllCars()

    suspend fun bookCar(bookingBodyModel: BookingBodyModel)  = retrofit.bookCar(bookingBodyModel)

    suspend fun userBookings(id : Int) = retrofit.userBookings(id)
}
