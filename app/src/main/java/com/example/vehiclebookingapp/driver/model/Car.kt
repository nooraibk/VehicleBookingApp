package com.example.vehiclebookingapp.driver.model

import com.google.gson.annotations.SerializedName

data class DriverCars(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("driver_id") var driverId: String? = null,
    @SerializedName("car_model") var carModel: String? = null,
    @SerializedName("car_no") var carNo: String? = null,
    @SerializedName("images") var images: ArrayList<Image> = arrayListOf(),
    @SerializedName("car_status") var carStatus: String? = null,
    @SerializedName("car_name") var carName: String? = null,
    @SerializedName("ip") var ip: String? = null,
    @SerializedName("longitude") var longitude: String? = null,
    @SerializedName("latitude") var latitude: String? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("state") var state: String? = null,
    @SerializedName("country") var country: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null

)

data class Image(
    val car_id: String,
    val created_at: String,
    val id: Int,
    val image: String,
    val updated_at: String
)


data class CarModel(
    @SerializedName("drivercars") var drivercars: ArrayList<DriverCars> = arrayListOf()
)

