package com.example.vehiclebookingapp.customer.model

import com.google.gson.annotations.SerializedName

data class UserRegister(
    @SerializedName("name") var name: String? = null,
    @SerializedName("email") var email: String? = null,
    @SerializedName("phone_no") var phoneNo: String? = null,
    @SerializedName("password") var password: String? = null
)


data class UserRegisterResponseModel(
    @SerializedName("user"    ) var user    : UserRegisterResponse?   = UserRegisterResponse(),
    @SerializedName("message" ) var message : String? = null
)


data class UserRegisterResponse(
    @SerializedName("name"       ) var name      : String? = null,
    @SerializedName("email"      ) var email     : String? = null,
    @SerializedName("phone_no"   ) var phoneNo   : String? = null,
    @SerializedName("updated_at" ) var updatedAt : String? = null,
    @SerializedName("created_at" ) var createdAt : String? = null,
    @SerializedName("id"         ) var id        : Int?    = null

)