package com.example.vehiclebookingapp.driver.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.customer.model.UserBookingViewModel
import com.example.vehiclebookingapp.customer.model.UserBookingViewModelFactory
import com.example.vehiclebookingapp.databinding.ActivityBookVehicleBinding
import com.example.vehiclebookingapp.driver.data.DriverRepo
import com.example.vehiclebookingapp.driver.model.CarViewModelFactory
import com.example.vehiclebookingapp.driver.model.DriverCarsViewModel

class BookVehicleActivity : AppCompatActivity() {

    lateinit var binding: ActivityBookVehicleBinding

    private lateinit var viewModel: UserBookingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val carViewModelFactory = UserBookingViewModelFactory(UserRepo())
        viewModel = ViewModelProvider(this, carViewModelFactory)[UserBookingViewModel::class.java]


//        if (viewModel.bookedCar.images.isNotEmpty()) {
//
//            for (image in viewModel.bookedCar.images) {
//                val imageUri = "https://carapi.cricdigital.com/car-images/" + image.image
//
//                Glide.with(binding.imgVehicle)
//                    .load(imageUri)
//                    .into(binding.imgVehicle)
//            }
//        }
//        binding.tvCarName.text = viewModel.bookedCar.carName
//        binding.tvBags.text = viewModel.bookedCar.carName
//        binding.tvSeats.text = viewModel.bookedCar.carName
//        binding.tvTransmission.text = viewModel.bookedCar.carName
//        binding.tvRentalPolicies.text = viewModel.bookedCar.carName
    }
}