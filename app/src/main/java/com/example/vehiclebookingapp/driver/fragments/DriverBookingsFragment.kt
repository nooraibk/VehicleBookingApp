package com.example.vehiclebookingapp.driver.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehiclebookingapp.customer.adapters.UserBookingsAdapter
import com.example.vehiclebookingapp.customer.model.ResponseUserBooking
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


        lifecycleScope.launch {
            SharedPrefManagerDriver(requireContext()).getDriver().id?.let {
                bookingsViewModel.driverBookings(it)
            }
        }

        bookingsViewModel.driverBookings.observe(viewLifecycleOwner) {
            passListToRecyclerView(it)
        }

        return binding.root
    }

    private fun passListToRecyclerView(userBookings: List<ResponseUserBooking>){
        binding.driverBookingsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        val adapter = UserBookingsAdapter(userBookings, object: UserBookingsAdapter.ItemClickListener{
            override fun onItemClick(item: ResponseUserBooking) {
//                Toast.makeText(requireContext(), "${ item.user?.name } ${item.car?.carNo}", Toast.LENGTH_SHORT).show()
                bookingsViewModel.bookingDetails = item

//                sendSMS(item.user?.phoneNo.toString(), "Hello From Driver Booking App")

            }
        })
        binding.driverBookingsRecyclerview.adapter = adapter
    }

    private fun sendSMS(number: String, text: String){


        val smsIntent = Intent(Intent.ACTION_VIEW).apply {
            type = "vnd.android-dir/mms-sms"
            // Add the phone number and message body as extras to the Intent
            putExtra("address", number)
            putExtra("sms_body", text)
        }

// Check if there is a default SMS app on the device
        if (smsIntent.resolveActivity(requireContext().packageManager) != null) {
            // Launch the SMS app with the Intent
            startActivity(smsIntent)
        } else {
            // No default SMS app found, handle the error
            Toast.makeText(requireContext(), "No SMS app found on the device", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}