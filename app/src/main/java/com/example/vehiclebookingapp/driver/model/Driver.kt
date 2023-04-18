package com.example.vehiclebookingapp.driver.model

import com.google.gson.annotations.SerializedName


data class DriverModel (
    @SerializedName("driver"  ) var driver  : Driver? = Driver(),
    @SerializedName("message" ) var message : String? = null
)


data class Driver(
    @SerializedName("id"             ) var id            : Int?    = null,
    @SerializedName("name"           ) var name          : String? = null,
    @SerializedName("email"          ) var email         : String? = null,
    @SerializedName("cnic"           ) var cnic          : String? = null,
    @SerializedName("phone_no"       ) var phoneNo       : String? = null,
    @SerializedName("cnic_photo"     ) var cnicPhoto     : String? = null,
    @SerializedName("profile_photo"  ) var profilePhoto  : String? = null,
    @SerializedName("password"       ) var password      : String? = null,
    @SerializedName("remember_token" ) var rememberToken : String? = null,
    @SerializedName("created_at"     ) var createdAt     : String? = null,
    @SerializedName("updated_at"     ) var updatedAt     : String? = null
)

data class DriverID(
    var id : Int
)

data class ProfilePhotoModel(
    @SerializedName("profile_photo"  ) var profilePhoto  : String? = null,
    @SerializedName("driver_id"             ) var id            : Int?    = null
)


data class DriverUpdateResponse(
    @SerializedName("message" ) var message : Boolean? = null,
    @SerializedName("data"    ) var data    : Boolean? = null
)