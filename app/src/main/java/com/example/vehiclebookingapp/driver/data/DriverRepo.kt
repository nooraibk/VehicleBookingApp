package com.example.vehiclebookingapp.driver.data

import com.example.vehiclebookingapp.driver.model.*
import com.example.vehiclebookingapp.driver.network.RetrofitInstanceDriver

class DriverRepo() {

    private val retrofit = RetrofitInstanceDriver.getInstance()


    suspend fun loginDriver(loginRequest: LoginRequest) = retrofit.login(loginRequest)

    suspend fun registerDriver(driver: Driver) = retrofit.registerDriver(driver)

    suspend fun getVehicles(driverID : DriverID) = retrofit.getVehicles(driverID)

    suspend fun registerVehicle(car : RegCarModel) = retrofit.registerCar(car)

    suspend fun carBookings(driverID : Int) = retrofit.driverBookings(driverID)

    suspend fun updateDriverProfile(profile: ProfilePhotoModel) = retrofit.updateProfile(profile)

}