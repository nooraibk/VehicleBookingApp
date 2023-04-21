package com.example.vehiclebookingapp.customer.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.driver.model.DriverModel
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

class UserViewModel(private val userRepo: UserRepo) : ViewModel() {

    private val _userData = MutableLiveData<UserRegisterResponseModel>()
    val userData: LiveData<UserRegisterResponseModel> = _userData

    private val _loginResponse = MutableLiveData<LoginUserResponseModel>()
    val loginResponse: LiveData<LoginUserResponseModel> = _loginResponse


     fun loginUser(loginRequest: LoginRequest) {

            viewModelScope.launch {
                val response = try {
                    userRepo.login(loginRequest)
                } catch (e: IOException) {
                    Log.e("Response Exception", "Device might not be connected to the internet")
                    return@launch
                } catch (e: HttpException) {
                    return@launch
                }
                if (response.isSuccessful && response.body() != null) {
                    _loginResponse.postValue(response.body())
                    Log.d("UserLoginData", response.body().toString())
                } else {
                    Log.e("Response Error", response.errorBody().toString())
                }

            }


    }

    suspend fun registerCustomer(user: UserRegister) {

        viewModelScope.launch {
            val response = try {
                userRepo.registerUser(user)
            } catch (e: IOException) {
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                val fetchedPost = response.body()!!
                _userData.postValue(fetchedPost)
                Log.d("Retrofit POST Response", "${response.code()} $fetchedPost")
            } else {
                Log.e("Response Error", response.errorBody().toString())
            }

        }
    }
}