package com.example.vehiclebookingapp.customer.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.driver.model.DriverCars
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class CarsViewModel(private val userRepo: UserRepo) : ViewModel() {


    var carsList = MutableLiveData<List<DriverCars>>()


    fun getAllVehicles() {

        viewModelScope.launch {
            val response = try {
                userRepo.getAllCars()
            } catch (e: IOException) {
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                carsList.value = response.body()!!
                Log.d("Retrofit POST Response", "${response.code()} $carsList")
            } else {
                Log.e("Response Error", response.errorBody().toString())
            }
        }
    }


}