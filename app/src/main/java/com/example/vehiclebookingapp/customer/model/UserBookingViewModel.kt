package com.example.vehiclebookingapp.customer.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vehiclebookingapp.customer.data.UserRepo
import okio.IOException
import retrofit2.HttpException

class UserBookingViewModel(private val repo: UserRepo) : ViewModel() {

    val bookingResponse = MutableLiveData<BookingModel>()

    private var _userBookings = MutableLiveData<List<ResponseUserBooking>>()
    val userBookings : LiveData<List<ResponseUserBooking>> get() = _userBookings

    var bookedCar = ResponseUserBooking()

    suspend fun bookCar(bookingBodyModel: BookingBodyModel) {
        val response = try {
            repo.bookCar(bookingBodyModel)
        } catch (e: IOException) {
            Log.e("Response Exception", "Device might not be connected to the internet")
            return
        } catch (e: HttpException) {
            Log.e("Response Exception", "HTTPException, unexpected response")
            return
        }
        if (response.isSuccessful && response.body() != null) {
            bookingResponse.value = response.body()!!
            Log.d("Retrofit POST Response", "${response.code()} $bookingResponse")
        } else {
            Log.e("Response Error", response.errorBody().toString())
        }
    }


    suspend fun userBookings(userId: Int) {
        val response = try {
            repo.userBookings(userId)
        } catch (e: IOException) {
            Log.e("BookingList Exception", "Device might not be connected to the internet")
            return
        } catch (e: HttpException) {
            Log.e("BookingList Exception", "HTTPException, unexpected response")
            return
        }
        if (response.isSuccessful && response.body() != null) {
            _userBookings.postValue(response.body()!!)
            Log.d("BookingList POST Response", "${response.code()} ${response.body()!!}")
        } else {
            Log.e("BookingList Error", response.errorBody().toString())
        }

    }


}

