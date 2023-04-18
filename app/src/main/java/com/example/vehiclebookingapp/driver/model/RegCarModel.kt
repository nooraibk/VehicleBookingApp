package com.example.vehiclebookingapp.driver.model

import com.google.gson.annotations.SerializedName


data class RegCarModel(
    @SerializedName("car_model") var carModel: String? = null,
    @SerializedName("car_no") var carNo: String? = null,
    @SerializedName("driver_id") var driverId: Int? = null,
    @SerializedName("car_name") var carName: String? = null,
    @SerializedName("image"     ) var images    : ArrayList<String> = arrayListOf()
)

