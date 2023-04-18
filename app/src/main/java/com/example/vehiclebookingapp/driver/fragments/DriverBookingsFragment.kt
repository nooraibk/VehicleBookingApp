package com.example.vehiclebookingapp.driver.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehiclebookingapp.customer.adapters.UserBookingsAdapter
import com.example.vehiclebookingapp.databinding.FragmentDriverBookingsBinding
import com.example.vehiclebookingapp.driver.SharedPrefManagerDriver
import com.example.vehiclebookingapp.driver.data.DriverRepo
import com.example.vehiclebookingapp.driver.model.DriverBookingViewModel
import com.example.vehiclebookingapp.driver.model.DriverBookingViewModelFactory
import kotlinx.coroutines.launch


class DriverBookingsFragment : Fragment() {

    private var _binding: FragmentDriverBookingsBinding? = null
    private val binding get() = _binding!!

    private val driverRepo = DriverRepo()

    private lateinit var bookingsViewModel: DriverBookingViewModel

    private lateinit var bookingsAdapter: UserBookingsAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriverBookingsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        val bookingViewModelFactory = DriverBookingViewModelFactory(driverRepo)
        bookingsViewModel =
            ViewModelProvider(this, bookingViewModelFactory)[DriverBookingViewModel::class.java]


        binding.driverBookingsRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            SharedPrefManagerDriver(requireContext()).getDriver().id?.let {
                bookingsViewModel.driverBookings(it)
            }
            bookingsViewModel.driverBookings.observe(viewLifecycleOwner) {
                bookingsAdapter = UserBookingsAdapter(it)
                binding.driverBookingsRecyclerview.adapter = bookingsAdapter
            }
        }



        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}