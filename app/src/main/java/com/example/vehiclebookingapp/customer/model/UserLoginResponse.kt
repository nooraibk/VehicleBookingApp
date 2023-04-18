package com.example.vehiclebookingapp.customer.model

import com.google.gson.annotations.SerializedName


data class LoginRequest(
    val email: String,
    val password: String
)
data class User (

    @SerializedName("id"                ) var id              : Int?    = null,
    @SerializedName("name"              ) var name            : String? = null,
    @SerializedName("email"             ) var email           : String? = null,
    @SerializedName("phone_no"          ) var phoneNo         : String? = null,
    @SerializedName("distance"          ) var distance        : String? = null,
    @SerializedName("longitude"         ) var longitude       : String? = null,
    @SerializedName("latitude"          ) var latitude        : String? = null,
    @SerializedName("city"              ) var city            : String? = null,
    @SerializedName("state"             ) var state           : String? = null,
    @SerializedName("country"           ) var country         : String? = null,
    @SerializedName("email_verified_at" ) var emailVerifiedAt : String? = null,
    @SerializedName("created_at"        ) var createdAt       : String? = null,
    @SerializedName("updated_at"        ) var updatedAt       : String? = null

)

data class LoginUserResponseModel(
    @SerializedName("token"   ) var token   : String? = null,
    @SerializedName("user"    ) var user    : User?   = User(),
    @SerializedName("message" ) var message : String? = null
)