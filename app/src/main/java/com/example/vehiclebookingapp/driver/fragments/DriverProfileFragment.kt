package com.example.vehiclebookingapp.driver.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.databinding.FragmentDriverProfileBinding
import com.example.vehiclebookingapp.driver.SharedPrefManagerDriver
import com.example.vehiclebookingapp.driver.data.DriverRepo
import com.example.vehiclebookingapp.driver.model.DriverViewModel
import com.example.vehiclebookingapp.driver.model.DriverViewModelFactory
import com.example.vehiclebookingapp.driver.model.ProfilePhotoModel


class DriverProfileFragment : Fragment() {

    private var _binding: FragmentDriverProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController
    private lateinit var viewModel: DriverViewModel

    private lateinit var sharedPref: SharedPrefManagerDriver


    private val SELECT_PROFILE_PHOTO_REQUEST_CODE = 0


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriverProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        navController = findNavController()

        sharedPref = SharedPrefManagerDriver(requireContext())

        getDriverDetails()

        val viewModelFactory = DriverViewModelFactory(DriverRepo())
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[DriverViewModel::class.java]


        binding.tvIDCardPhoto.setOnClickListener {
            val action = R.id.action_profileFragment_to_uploadDocumentFragment
            navController.navigate(action)
        }


        binding.ivProfilePic.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, SELECT_PROFILE_PHOTO_REQUEST_CODE)
        }

        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_PROFILE_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            val imageBase64 = selectedImageUri?.let { encodeImageToBase64(it) }

            val profileUpdate = ProfilePhotoModel(
                imageBase64,
                SharedPrefManagerDriver(requireContext()).getDriver().id
            )


            viewModel.updateDriverProfile(profileUpdate)

        }

    }

    @SuppressLint("Recycle")
    private fun encodeImageToBase64(imageUri: Uri): String {
        val inputStream = requireActivity().contentResolver.openInputStream(imageUri)
        val imageBytes = inputStream?.readBytes()
        return Base64.encodeToString(imageBytes, Base64.DEFAULT)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDriverDetails() {

        binding.tvDriverName.text = sharedPref.getDriver().name
        binding.tvDriverCNIC.text = sharedPref.getDriver().cnic
        binding.tvDriverEmail.text = sharedPref.getDriver().email
        binding.tvDriverPhoneNumber.text = sharedPref.getDriver().phoneNo

        Glide.with(this).load(sharedPref.getDriver().profilePhoto)
            .into(binding.ivProfilePic)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}