package com.example.vehiclebookingapp.driver.data


import android.provider.ContactsContract.Profile
import com.example.vehiclebookingapp.customer.model.UserBookingsModelItem
import com.example.vehiclebookingapp.driver.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitServiceDriver {


    @POST("driver/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<DriverModel>


    @POST("driver/register")
    suspend fun registerDriver(@Body driver: Driver): Response<DriverModel>


    @POST("drivercars")
    suspend fun getVehicles(@Body id : DriverID) : Response<List<DriverCars>>

    @POST("storeCar")
    suspend fun registerCar(@Body car : RegCarModel) : Response<CarModel>

    @GET("driverbookings/{id}")
    suspend fun driverBookings(@Path("id") driverId : Int) : Response<List<UserBookingsModelItem>>


    @POST("driverupdate")
    suspend fun updateProfile(@Body profile_photo : ProfilePhotoModel) : Response<DriverUpdateResponse>

}