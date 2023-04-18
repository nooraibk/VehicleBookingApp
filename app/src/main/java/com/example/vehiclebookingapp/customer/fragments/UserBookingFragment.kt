package com.example.vehiclebookingapp.customer.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehiclebookingapp.customer.SharedPrefManagerUser
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.databinding.FragmentUserBookingBinding
import com.example.vehiclebookingapp.customer.adapters.VehiclesBookingAdapter
import com.example.vehiclebookingapp.customer.model.*
import com.example.vehiclebookingapp.driver.model.DriverCars
import kotlinx.coroutines.launch


class UserBookingFragment : Fragment() {

    private var _binding: FragmentUserBookingBinding? = null
    private val binding get() = _binding!!

    private lateinit var userCarsViewModel: CarsViewModel
    private val userRepo = UserRepo()

    private lateinit var bookingsViewModel: UserBookingViewModel

    private lateinit var sharedPrefManagerUser: SharedPrefManagerUser

    private lateinit var carsAdapter: VehiclesBookingAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBookingBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        val carViewModelFactory = CarsViewModelFactory(userRepo)
        userCarsViewModel = ViewModelProvider(this, carViewModelFactory)[CarsViewModel::class.java]


        val bookingViewModelFactory = UserBookingViewModelFactory(userRepo)
        bookingsViewModel = ViewModelProvider(this, bookingViewModelFactory)[UserBookingViewModel::class.java]

        sharedPrefManagerUser = SharedPrefManagerUser(requireContext())
        userCarsViewModel.getAllVehicles()
        binding.userBookingRecyclerView.layoutManager = LinearLayoutManager(requireContext())

         userCarsViewModel.carsList.observe(viewLifecycleOwner){
             carsAdapter = VehiclesBookingAdapter(it, object :VehiclesBookingAdapter.ItemClickListener{
                 override fun onItemClick(item: DriverCars) {

                     val bookingCar = item.driverId?.let { item.id?.let { it1 -> sharedPrefManagerUser.getUser().id?.let { it2 -> BookingBodyModel(driver_id = it.toInt(), car_id = it1, user_id = it2) } } }

                     lifecycleScope.launch {
                         if (bookingCar != null) {
                             bookingsViewModel.bookCar(bookingCar)
                         }

                         bookingsViewModel.bookingResponse.observe(viewLifecycleOwner){
                             Log.d("Booking Response", it.message)
                         }
                     }

                 }
             })
             binding.userBookingRecyclerView.adapter = carsAdapter

         }



        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}