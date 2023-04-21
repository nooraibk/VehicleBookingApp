package com.example.vehiclebookingapp.customer.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.driver.model.DriverCars
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class CarsViewModel(private val userRepo: UserRepo) : ViewModel() {


    private var _carsList = MutableLiveData<List<DriverCars>>()
    val carsList : LiveData<List<DriverCars>> get() = _carsList

    fun getAllVehicles() {

        viewModelScope.launch {
            val response = try {
                userRepo.getAllCars()
            } catch (e: IOException) {
                Log.e("CarsList Exception", "Device might not be connected to the internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("CarsList Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                _carsList.postValue(response.body()!!)
                Log.d("CarsList Response", "${response.code()} ${response.body()!!}")
            } else {
                Log.e("CarsList Error", response.errorBody().toString())
            }
        }
    }


}