package com.example.vehiclebookingapp.customer.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.customer.SharedPrefManagerUser
import com.example.vehiclebookingapp.customer.adapters.UserBookingsAdapter
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.customer.model.ResponseUserBooking
import com.example.vehiclebookingapp.customer.model.UserBookingViewModel
import com.example.vehiclebookingapp.customer.model.UserBookingViewModelFactory
import com.example.vehiclebookingapp.databinding.FragmentUserBookingsBinding
import kotlinx.coroutines.launch


class UserBookingsFragment : Fragment() {

    private var _binding: FragmentUserBookingsBinding? = null
    private val binding get() = _binding!!

    private val userRepo = UserRepo()

    private lateinit var bookingsViewModel: UserBookingViewModel

    private lateinit var bookingsAdapter: UserBookingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBookingsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        val bookingViewModelFactory = UserBookingViewModelFactory(userRepo)
        bookingsViewModel =
            ViewModelProvider(this, bookingViewModelFactory)[UserBookingViewModel::class.java]


        binding.userBookingsRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            SharedPrefManagerUser(requireContext()).getUser().id?.let {
                bookingsViewModel.userBookings(it)
                Log.d("UserID", it.toString())

//                bookingsViewModel.userBookings(3)
            }

//            bookingsViewModel.userBookings.observe(viewLifecycleOwner) {
//                Log.d("User Bookings List", it.toString())
//                bookingsAdapter = UserBookingsAdapter(it)
//                binding.userBookingsRecyclerview.adapter = bookingsAdapter
//            }
        }

        bookingsViewModel.userBookings.observe(viewLifecycleOwner){
            Log.d("BookingsList", it.toString())
            passListToRecyclerView(it)
        }




        return binding.root
    }

    private fun passListToRecyclerView(userBookings: List<ResponseUserBooking>){
        binding.userBookingsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        val adapter = UserBookingsAdapter(userBookings, object: UserBookingsAdapter.ItemClickListener{
            override fun onItemClick(item: ResponseUserBooking) {
//                Toast.makeText(requireContext(), "${ item.user?.name } ${item.car?.carNo}", Toast.LENGTH_SHORT).show()
                bookingsViewModel.bookedCar = item

                val action = R.id.action_userBookingsFragment_to_bookVehicleActivity
                findNavController().navigate(action)
//                sendSMS(item.user?.phoneNo.toString(), "Hello From Driver Booking App")

            }
        })
        binding.userBookingsRecyclerview.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}