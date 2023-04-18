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

class DriverCarsViewModel(private val driverRepo: DriverRepo) : ViewModel() {

    private val _carsListLive = MutableLiveData<List<DriverCars>>()
    val carsListLive get() = _carsListLive

    var carsList = listOf<DriverCars>()

    private val _carResponse = MutableLiveData<CarModel>()
    val carResponse: LiveData<CarModel> = _carResponse

    fun getVehicles(driverID: DriverID) {

        viewModelScope.launch {
            val response = try {
                driverRepo.getVehicles(driverID)
            } catch (e: IOException) {
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                carsList = response.body()!!
                val result = response.body()!!
                _carsListLive.postValue(result)
                Log.d("Driver Cars List", "${response.code()} $result")
            } else {
                Log.e("Response Error", response.errorBody().toString())
            }

        }
    }

    fun registerCar(car: RegCarModel) {
        viewModelScope.launch {
            val response = try {
                driverRepo.registerVehicle(car)
            } catch (e: IOException) {
                Log.e("Response Exception", "Device might not be connected to the internet")
                return@launch
            } catch (e: HttpException) {
                Log.e("Response Exception", "HTTPException, unexpected response")
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                _carResponse.postValue(response.body()!!)
                Log.d("Retrofit POST Response", "${response.code()} $carsList")
            } else {
                Log.e("Response Error", response.errorBody().toString())
            }
        }
    }
}