package com.example.vehiclebookingapp.driver.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vehiclebookingapp.customer.model.ResponseUserBooking
import com.example.vehiclebookingapp.driver.data.DriverRepo
import okio.IOException
import retrofit2.HttpException

class DriverBookingViewModel(private val repo: DriverRepo) : ViewModel() {

    private val _driverBookings = MutableLiveData<List<ResponseUserBooking>>()
    val driverBookings : LiveData<List<ResponseUserBooking>> get() = _driverBookings

    var bookingDetails = ResponseUserBooking()
    suspend fun driverBookings(driverId: Int) {
        val response = try {
            repo.carBookings(driverId)
        } catch (e: IOException) {
            Log.e("DriverBooking Exception", "Device might not be connected to the internet")
            return
        } catch (e: HttpException) {
            Log.e("DriverBooking Exception", "HTTPException, unexpected response")
            return
        }
        if (response.isSuccessful && response.body() != null) {
            val body = response.body()!!
            _driverBookings.postValue(body)
            Log.d("DriverBooking Response", "${response.code()} ${response.body()}")
        } else {
            Log.e("DriverBooking Error", response.errorBody().toString())
        }

    }

}

