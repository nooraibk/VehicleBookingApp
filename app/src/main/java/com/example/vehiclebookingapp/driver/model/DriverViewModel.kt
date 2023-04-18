package com.example.vehiclebookingapp.driver.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehiclebookingapp.driver.data.DriverRepo
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class DriverViewModel(private val driverRepo: DriverRepo) : ViewModel() {

    private val _registerResponse = MutableLiveData<DriverModel>()
    val registerResponse: LiveData<DriverModel> = _registerResponse

    fun registerDriver(driver: Driver) {
        viewModelScope.launch {
            val response = try {
                driverRepo.registerDriver(driver)
            } catch (e: IOException) {
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val registeredDriver = response.body()!!
                _registerResponse.postValue(registeredDriver)
                Log.d("Retrofit POST Response", "${response.code()} $registeredDriver")
            } else {
                Log.e("Response Error", response.errorBody().toString())
            }
        }
    }


    fun updateDriverProfile(profile: ProfilePhotoModel) {

        viewModelScope.launch {
            val response = try {
                driverRepo.updateDriverProfile(profile)
            } catch (e: IOException) {
                Log.e("Driver Update Exception", e.message.toString())
                return@launch
            } catch (e: HttpException) {
                Log.e("HTTPS Driver Update Exception", e.message.toString())
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                val response = response.body()
                Log.d("Driver Update Response", response.toString())
            }
        }


    }
}