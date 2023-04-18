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

class DriverLoginViewModel(private val driverRepo: DriverRepo) : ViewModel() {
    private val _loginResponse = MutableLiveData<DriverModel>()
    val loginResponse: LiveData<DriverModel> = _loginResponse

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            val response = try {
                driverRepo.loginDriver(loginRequest)
            } catch (e: IOException) {
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                _loginResponse.postValue(response.body())
            } else {
                Log.e("Response Error", response.errorBody().toString())
            }

        }
    }
}