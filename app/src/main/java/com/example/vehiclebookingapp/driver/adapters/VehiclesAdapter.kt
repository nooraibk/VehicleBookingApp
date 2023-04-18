package com.example.vehiclebookingapp.driver.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vehiclebookingapp.databinding.ItemVehicleRecyclerBinding
import com.example.vehiclebookingapp.driver.model.DriverCars

class VehiclesAdapter(private val carsList: List<DriverCars>) :
    RecyclerView.Adapter<VehiclesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = ItemVehicleRecyclerBinding.inflate(inflator, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = carsList[position]
        Log.d("Adapter List", carsList.toString())
        holder.binding.tvCarName.text = car.carName
        holder.binding.tvCarModel.text = car.carModel

        if (car.images.isNotEmpty()) {

            for (image in car.images) {
                val imageUri = "https://carapi.cricdigital.com/car-images/" + image.image

                Glide.with(holder.itemView.context)
                    .load(imageUri)
                    .into(holder.binding.ivVehicle)
            }
        }

        holder.itemView

    }

    override fun getItemCount(): Int {
        return carsList.size
    }

    class ViewHolder(val binding: ItemVehicleRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)
}