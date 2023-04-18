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
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.customer.model.UserRegister
import com.example.vehiclebookingapp.customer.model.UserViewModel
import com.example.vehiclebookingapp.customer.model.UserViewModelFactory
import com.example.vehiclebookingapp.databinding.FragmentCustomerRegistrationBinding
import kotlinx.coroutines.launch

class UserRegistrationFragment : Fragment() {

    private var _binding: FragmentCustomerRegistrationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: UserViewModel

    lateinit var name: String
    lateinit var email: String
    lateinit var phone: String
    lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCustomerRegistrationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val viewModelFactory = UserViewModelFactory(UserRepo())
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[UserViewModel::class.java]


        binding.tvCustomerLogin.setOnClickListener {
            val navController = findNavController()
            val action = R.id.action_userRegistrationFragment_to_userLoginFragment
            navController.navigate(action)
        }

        binding.btnCustomerRegister.setOnClickListener {
            checkData()
        }

        return binding.root
    }


    private fun checkData() {

        name = binding.etCustomerName.text.toString()
        email = binding.etCustomerEmailAddress.text.toString()
        phone = binding.etCustomerPhoneNumber.text.toString()
        password = binding.etCustomerPassword.text.toString()

        if (name.isEmpty()) {
            binding.etCustomerName.error = getString(R.string.empty)
        }
        if (email.isEmpty()) {
            binding.etCustomerEmailAddress.error = getString(R.string.empty)
        }
        if (phone.isEmpty()) {
            binding.etCustomerPhoneNumber.error = getString(R.string.empty)
        }
        if (password.isEmpty()) {
            binding.etCustomerPassword.error = getString(R.string.empty)
        }

        if (name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && password.isNotEmpty()) {

            val user = registerUser()

            lifecycleScope.launch {
                viewModel.registerCustomer(user)

                viewModel.userData.observe(viewLifecycleOwner) { user ->

                    Log.d("User Response", user.toString())
                    Toast.makeText(requireContext(), user.message.toString(), Toast.LENGTH_SHORT).show()

                }

            }
        }
    }


    private fun registerUser(): UserRegister {
        return UserRegister(
            email = email, password = password, name = name, phoneNo = phone
        )

        /* lifecycleScope.launch(Dispatchers.IO) {
             customerViewGroup.registerCustomer(user)
         }
 */
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
