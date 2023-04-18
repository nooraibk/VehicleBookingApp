package com.example.vehiclebookingapp.customer

import android.content.Context
import android.content.SharedPreferences
import com.example.vehiclebookingapp.customer.model.User

class SharedPrefManagerUser(var context: Context) {

    private val SHARED_PREF_NAME = "user"
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    fun saveUser(user: User) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        editor.putString("userName", user.name)
        editor.putString("userEmail", user.email)
        editor.putString("userPhoneNumber", user.phoneNo)
        user.id?.let { editor.putInt("userID", it) }
        editor.commit()
    }

    fun getUser(): User {
        var user: User
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        val userName = sharedPreferences.getString("userName", null).toString()
        val userEmail = sharedPreferences.getString("userEmail", null).toString()
        val userPhoneNumber = sharedPreferences.getString("userPhoneNumber", null).toString()
        val userID = sharedPreferences.getInt("userID", 0)

        return User(name = userName, email = userEmail, phoneNo = userPhoneNumber, id = userID)
    }

}