package com.example.vehiclebookingapp.customer.data

import com.example.vehiclebookingapp.customer.model.*
import com.example.vehiclebookingapp.driver.model.DriverCars
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RetrofitServiceUser {

    @POST("user/register")
    suspend fun registerUser(@Body user: UserRegister): Response<UserRegisterResponseModel>


    @POST("user/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginUserResponseModel>

    @GET("allcars")
    suspend fun getAllCars(): Response<List<DriverCars>>

    @POST("bookCar")
    suspend fun bookCar(@Body booking: BookingBodyModel): Response<BookingModel>


    @GET("userbookings/{id}")
    suspend fun userBookings(@Path("id") userId: Int) : Response<List<ResponseUserBooking>>

}