package com.example.vehiclebookingapp.customer.model

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginUserResponseModel(
    @SerializedName("token") var token: String? = null,
    @SerializedName("user") var user: User? = User(),
    @SerializedName("message") var message: String? = null
)