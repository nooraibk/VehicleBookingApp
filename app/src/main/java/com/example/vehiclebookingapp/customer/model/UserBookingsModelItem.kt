package com.example.vehiclebookingapp.customer.model

data class UserBookingsModelItem(
    val car_id: String,
    val created_at: String,
    val distance: String,
    val driver_id: String,
    val end_position: Any,
    val id: Int,
    val start_position: Any,
    val total_payment: Any,
    val updated_at: String,
    val user_id: String
)