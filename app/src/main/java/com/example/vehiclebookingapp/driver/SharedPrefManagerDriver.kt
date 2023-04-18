package com.example.vehiclebookingapp.driver

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.vehiclebookingapp.driver.model.Driver

class SharedPrefManagerDriver(var context: Context) {

    private val SHARED_PREF_NAME = "driver"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor


    fun saveDriver(driver: Driver) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()

        val driverProfileURI = "https://carapi.cricdigital.com/driver-profile/"+driver.profilePhoto

        editor.putString("driverName", driver.name)
        editor.putString("driverEmail", driver.email)
        editor.putString("driverPhoneNumber", driver.phoneNo)
        editor.putString("driverPassword", driver.password)
        editor.putString("profilePhoto", driverProfileURI)
        driver.id?.let { editor.putInt("driverID", it) }
        editor.putString("createdAt", driver.createdAt)
        editor.putString("updatedAt", driver.updatedAt)
        editor.commit()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDriver(): Driver {

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        val createdAtString = sharedPreferences.getString("createdAt", null).toString()
        /*val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val createdTime = dateFormat.parse(createdAtString)
        val createdAt = Timestamp(createdTime.time).toString()
*/
        val updatedAtString = sharedPreferences.getString("updatedAt", null).toString()
        /* val updatedTime = dateFormat.parse(updatedAtString)
         val updatedAt = Timestamp(updatedTime.time).toString()*/


        val driverEmail = sharedPreferences.getString("driverEmail", null).toString()
        val driverPhoneNumber = sharedPreferences.getString("driverPhoneNumber", null).toString()
        val driverName = sharedPreferences.getString("driverName", null).toString()
        val driverPassword = sharedPreferences.getString("driverPassword", null).toString()
        val driverID = sharedPreferences.getInt("driverID", 0)
        val profilePhoto = sharedPreferences.getString("profilePhoto", null).toString()

        return Driver(
            createdAt = createdAtString,
            email = driverEmail,
            id = driverID,
            name = driverName,
            password = driverPassword,
            phoneNo = driverPhoneNumber,
            updatedAt = updatedAtString,
            profilePhoto = profilePhoto
        )
    }
}