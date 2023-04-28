package com.example.vehiclebookingapp.driver.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vehiclebookingapp.customer.adapters.UserBookingsAdapter
import com.example.vehiclebookingapp.customer.model.ResponseUserBooking
import com.example.vehiclebookingapp.databinding.ItemVehicleRecyclerBinding
import com.example.vehiclebookingapp.driver.model.DriverCars

class VehiclesAdapter(private val listener: ItemClickListener) :
    ListAdapter<DriverCars, VehiclesAdapter.ViewHolder>(VehiclesListDiffUtils()) {

    interface ItemClickListener{
        fun onItemClick(item: DriverCars)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val view = ItemVehicleRecyclerBinding.inflate(inflator, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car = getItem(position)
        holder.bin(car)

        holder.binding.root.setOnClickListener {
            listener.onItemClick(car)
        }

    }

    class ViewHolder(val binding: ItemVehicleRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bin(car : DriverCars){
//            val car = carsList[position]
//            Log.d("Adapter List", carsList.toString())
            binding.tvCarName.text = car.carName
            binding.tvCarModel.text = car.carModel

            if (car.images.isNotEmpty()) {

                for (image in car.images) {
                    val imageUri = "https://carapi.cricdigital.com/car-images/" + image.image

                    Log.d("images", imageUri)

                    Glide.with(binding.ivVehicle)
                        .load(imageUri)
                        .into(binding.ivVehicle)
                }
            }

//            holder.itemView
        }
    }

    class VehiclesListDiffUtils: DiffUtil.ItemCallback<DriverCars>(){
        override fun areItemsTheSame(oldItem: DriverCars, newItem: DriverCars): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DriverCars, newItem: DriverCars): Boolean {
            return oldItem == newItem
        }
    }
}