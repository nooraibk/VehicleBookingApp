package com.example.vehiclebookingapp.customer.model


data class Bookings(
    val car_id: String,
    val created_at: String,
    val driver_id: String,
    val id: Int,
    val updated_at: String,
    val user_id: String
)


data class BookingModel(
    val bookings: Bookings,
    val message: String
)


data class BookingBodyModel(
    val user_id: Int,
    val car_id: Int,
    val driver_id: Int,
    val distance : Int
)