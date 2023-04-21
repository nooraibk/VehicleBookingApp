package com.example.vehiclebookingapp.customer.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vehiclebookingapp.customer.model.ResponseUserBooking
import com.example.vehiclebookingapp.databinding.UserBookingsItemListBinding

class UserBookingsAdapter(private val usersBookings: List<ResponseUserBooking>, private val listener: ItemClickListener) :
    RecyclerView.Adapter<UserBookingsAdapter.ViewHolder>() {

    interface ItemClickListener{
        fun onItemClick(item: ResponseUserBooking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = UserBookingsItemListBinding.inflate(inflator, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userBooking = usersBookings[position]
        Log.d("Booking List", userBooking.toString())
        holder.binding.tvBookingId.text = userBooking.user?.name
        holder.binding.tvUserId.text = userBooking.car?.carNo
        holder.binding.tvCarId.text = userBooking.id.toString()

        holder.binding.root.setOnClickListener {
            listener.onItemClick(userBooking)
        }
    }

    override fun getItemCount(): Int {
       return usersBookings.size
    }

    class ViewHolder( val binding: UserBookingsItemListBinding) : RecyclerView.ViewHolder(binding.root)
}