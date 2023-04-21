package com.example.vehiclebookingapp.customer.model

import com.google.gson.annotations.SerializedName


data class ResponseUserBooking(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("user_id") var userId: String? = null,
    @SerializedName("car_id") var carId: String? = null,
    @SerializedName("driver_id") var driverId: String? = null,
    @SerializedName("total_payment") var totalPayment: String? = null,
    @SerializedName("distance") var distance: String? = null,
    @SerializedName("start_position") var startPosition: String? = null,
    @SerializedName("end_position") var endPosition: String? = null,
    @SerializedName("booking_status") var bookingStatus: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("car") var car: Car? = Car()
)