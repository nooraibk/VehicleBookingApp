package com.example.vehiclebookingapp.customer.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vehiclebookingapp.databinding.ItemRecyclerBookingBinding
import com.example.vehiclebookingapp.driver.model.DriverCars

class VehiclesBookingAdapter(private val carsList: List<DriverCars>, private val listener : ItemClickListener) :
    RecyclerView.Adapter<VehiclesBookingAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(item: DriverCars)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = ItemRecyclerBookingBinding.inflate(inflator, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = carsList[position]
        Log.d("Car List", carsList.toString())
        holder.binding.tvCarName.text = car.carNo
        holder.binding.tvCarModel.text = car.carModel

        if (car.images.isNotEmpty()){

            val imageUri = "https://carapi.cricdigital.com/car-images/" + car.images[0].image

            Glide.with(holder.itemView.context)
                .load(imageUri)
                .into(holder.binding.ivVehicle)
        }

        holder.binding.btnBook.setOnClickListener {
            listener.onItemClick(car)
        }



    }

    override fun getItemCount(): Int {
       return carsList.size
    }

    class ViewHolder( val binding: ItemRecyclerBookingBinding) :
        RecyclerView.ViewHolder(binding.root)
}