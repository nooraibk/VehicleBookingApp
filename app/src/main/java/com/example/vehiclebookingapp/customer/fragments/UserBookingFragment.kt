package com.example.vehiclebookingapp.customer.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehiclebookingapp.R
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

    private var listOfCars = mutableListOf<DriverCars>()

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

        binding.searchVehicle.addTextChangedListener { str ->
            if (listOfCars.isNotEmpty()) {
                val searchedVehicles = listOfCars.filter {
                    it.carName?.contains(str.toString(), ignoreCase = true) == true ||
                            it.carNo?.contains(str.toString(), ignoreCase = true) == true ||
                            it.carModel?.contains(str.toString(), ignoreCase = true) == true
                }
//                Log.d("TextChangeListener", searchedVehicles.toString())
                passDataToRecyclerView(searchedVehicles)
            }
        }

        binding.userBookingRecyclerView.layoutManager = LinearLayoutManager(requireContext())

         userCarsViewModel.carsList.observe(viewLifecycleOwner){
             passDataToRecyclerView(it)
             listOfCars = it as MutableList<DriverCars>
         }

        return binding.root
    }

    private fun passDataToRecyclerView(cars : List<DriverCars>){
        carsAdapter = VehiclesBookingAdapter(cars, object :VehiclesBookingAdapter.ItemClickListener{
            override fun onItemClick(item: DriverCars) {

//                     driverCarsViewModel.car = item
//                     val action = R.id.action_vehiclesFragment_to_bookVehicleActivity
//                     navController.navigate(action)

                val bookCar = item.id?.let { it1 ->
                    sharedPrefManagerUser.getUser().id?.let { it2 ->
                        item.driverId?.let { it3 ->
                            BookingBodyModel(driver_id = it3.toInt(), car_id = it1, user_id = it2, distance = 0)
                        }
                    }
                }
                Log.d("Booking Response", bookCar.toString())

                lifecycleScope.launch {
                    if (bookCar != null) {
                        bookingsViewModel.bookCar(bookCar)
                    }

                    bookingsViewModel.bookingResponse.observe(viewLifecycleOwner){
                        Log.d("Booking Response", it.message)
                    }
                }

            }
        })
        binding.userBookingRecyclerView.adapter = carsAdapter
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}