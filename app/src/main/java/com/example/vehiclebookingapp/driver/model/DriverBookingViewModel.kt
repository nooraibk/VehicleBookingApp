package com.example.vehiclebookingapp.driver.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.customer.model.BookingBodyModel
import com.example.vehiclebookingapp.customer.model.BookingModel
import com.example.vehiclebookingapp.customer.model.UserBookingsModelItem
import com.example.vehiclebookingapp.driver.data.DriverRepo
import okio.IOException
import retrofit2.HttpException

class DriverBookingViewModel(private val repo: DriverRepo) : ViewModel() {


    val driverBookings = MutableLiveData<List<UserBookingsModelItem>>()


    suspend fun driverBookings(driverId: Int) {
        val response = try {
            repo.carBookings(driverId)
        } catch (e: IOException) {
            Log.e("Response Exception", "Device might not be connected to the internet")
            return
        } catch (e: HttpException) {
            Log.e("Response Exception", "HTTPException, unexpected response")
            return
        }
        if (response.isSuccessful && response.body() != null) {
            driverBookings.value = response.body()!!
            Log.d("Retrofit POST Response", "${response.code()} $driverBookings")
        } else {
            Log.e("Response Error", response.errorBody().toString())
        }

    }


}

