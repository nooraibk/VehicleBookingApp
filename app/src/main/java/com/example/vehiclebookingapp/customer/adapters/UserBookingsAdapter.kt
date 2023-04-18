package com.example.vehiclebookingapp.customer.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vehiclebookingapp.customer.model.UserBookingsModel
import com.example.vehiclebookingapp.customer.model.UserBookingsModelItem
import com.example.vehiclebookingapp.databinding.ItemRecyclerBookingBinding
import com.example.vehiclebookingapp.databinding.UserBookingsItemListBinding
import com.example.vehiclebookingapp.driver.model.DriverCars

class UserBookingsAdapter(private val usersBookings: List<UserBookingsModelItem>) :
    RecyclerView.Adapter<UserBookingsAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = UserBookingsItemListBinding.inflate(inflator, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userBooking = usersBookings[position]
        Log.d("Booking List", userBooking.toString())
        holder.binding.tvBookingId.append(userBooking.id.toString())
        holder.binding.tvUserId.append(userBooking.user_id)
        holder.binding.tvCarId.append(userBooking.car_id)

    }

    override fun getItemCount(): Int {
       return usersBookings.size
    }

    class ViewHolder( val binding: UserBookingsItemListBinding) :
        RecyclerView.ViewHolder(binding.root)
}