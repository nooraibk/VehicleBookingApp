package com.example.vehiclebookingapp.customer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.databinding.FragmentBookingDetailsBinding
import com.example.vehiclebookingapp.databinding.FragmentDriverBookingsBinding
import com.example.vehiclebookingapp.driver.data.DriverRepo
import com.example.vehiclebookingapp.driver.model.DriverBookingViewModel
import com.example.vehiclebookingapp.driver.model.DriverBookingViewModelFactory

class BookingDetailsFragment : Fragment() {
    private var _binding: FragmentBookingDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DriverBookingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookingDetailsBinding.inflate(layoutInflater, container, false)

        val bookingViewModelFactory = DriverBookingViewModelFactory(DriverRepo())
        viewModel = ViewModelProvider(this, bookingViewModelFactory)[DriverBookingViewModel::class.java]

        binding.tvName.text = viewModel.bookingDetails.user?.name
        binding.tvCity.text = viewModel.bookingDetails.user?.city
        binding.tvContact.text = viewModel.bookingDetails.user?.phoneNo
        binding.tvAddress.text = "${ viewModel.bookingDetails.user?.city }, ${viewModel.bookingDetails.user?.state}, ${viewModel.bookingDetails.user?.country}"
        binding.tvDestination.text = viewModel.bookingDetails.endPosition
        binding.tvDriverOrSelf.text = ""
        binding.tvCar.text = viewModel.bookingDetails.car?.carName
        binding.tvCarNum.text = viewModel.bookingDetails.car?.carNo
        binding.tvDriverOrSelf.text = ""

        binding.btnCall.setOnClickListener {

        }

        binding.btnMessage.setOnClickListener {

        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}