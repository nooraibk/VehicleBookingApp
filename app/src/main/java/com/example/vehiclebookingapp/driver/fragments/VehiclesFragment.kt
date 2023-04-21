package com.example.vehiclebookingapp.driver.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.customer.adapters.UserBookingsAdapter
import com.example.vehiclebookingapp.customer.model.ResponseUserBooking
import com.example.vehiclebookingapp.databinding.FragmentVehiclesBinding
import com.example.vehiclebookingapp.driver.SharedPrefManagerDriver
import com.example.vehiclebookingapp.driver.adapters.VehiclesAdapter
import com.example.vehiclebookingapp.driver.data.DriverRepo
import com.example.vehiclebookingapp.driver.model.*


class VehiclesFragment : Fragment() {

    private var _binding: FragmentVehiclesBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private lateinit var driverCarsViewModel: DriverCarsViewModel
    private val driverRepo = DriverRepo()

    private lateinit var sharedPrefManagerDriver: SharedPrefManagerDriver

    private lateinit var listOfCars: List<DriverCars>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVehiclesBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val carViewModelFactory = CarViewModelFactory(driverRepo)
        driverCarsViewModel =
            ViewModelProvider(this, carViewModelFactory)[DriverCarsViewModel::class.java]


        sharedPrefManagerDriver = SharedPrefManagerDriver(requireContext())
        navController = findNavController()

        Log.d("SharedPrefs Driver ID", sharedPrefManagerDriver.getDriver().id.toString())

//        sharedPrefManagerDriver.getDriver().id?.let { driverCarsViewModel.getVehicles(it) }

        val driverID = sharedPrefManagerDriver.getDriver().id?.let { DriverID(it) }

        if (driverID != null) {
            driverCarsViewModel.getVehicles(driverID)
        } else Toast.makeText(requireContext(), "No driver", Toast.LENGTH_SHORT).show()


//        listOfCars = driverCarsViewModel.carsList
        driverCarsViewModel.carsListLive.observe(viewLifecycleOwner) {
            passListToRecyclerView(it)

        }


//        carsAdapter = VehiclesAdapter(listOfCars)

        binding.btnAddVehicle.setOnClickListener {
            val action = R.id.action_vehiclesFragment_to_addVehicleActivity
            navController.navigate(action)
        }

        return binding.root
    }

    private fun passListToRecyclerView(carsList: List<DriverCars>){
        binding.recyclerViewVehicles.layoutManager = LinearLayoutManager(requireContext())
        val adapter = VehiclesAdapter(carsList, object: VehiclesAdapter.ItemClickListener{
            override fun onItemClick(item: DriverCars) {
                //Toast.makeText(requireContext(), "${ item.carNo }", Toast.LENGTH_SHORT).show()

            }
        })
        binding.recyclerViewVehicles.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}