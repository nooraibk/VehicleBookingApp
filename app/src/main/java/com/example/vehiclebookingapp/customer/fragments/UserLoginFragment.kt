package com.example.vehiclebookingapp.customer.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.vehiclebookingapp.R
import com.example.vehiclebookingapp.customer.SharedPrefManagerUser
import com.example.vehiclebookingapp.customer.activities.CustomerStartActivity
import com.example.vehiclebookingapp.customer.activities.MainActivityUser
import com.example.vehiclebookingapp.customer.data.UserRepo
import com.example.vehiclebookingapp.customer.model.LoginRequest
import com.example.vehiclebookingapp.customer.model.User
import com.example.vehiclebookingapp.customer.model.UserViewModel
import com.example.vehiclebookingapp.customer.model.UserViewModelFactory
import com.example.vehiclebookingapp.databinding.FragmentUserLoginBinding
import kotlinx.coroutines.launch


class UserLoginFragment : Fragment() {

    private var _binding: FragmentUserLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var email: String
    private lateinit var password: String

    private lateinit var viewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for z


        val viewModelFactory = UserViewModelFactory(UserRepo())
        viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[UserViewModel::class.java]

        binding.btnUserLogin.setOnClickListener {
            email = binding.etUserLoginEmail.text.toString()
            password = binding.etUserLoginPassword.text.toString()

            if (email.isEmpty()) {
                binding.etUserLoginEmail.error = getString(R.string.error_field_required)
            }
            if (password.isEmpty()) {
                binding.etUserLoginPassword.error = getString(R.string.error_field_required)
            } else {
                val userLoginRequest = LoginRequest(email, password)

                lifecycleScope.launch {
                    viewModel.loginUser(userLoginRequest)

                    viewModel.loginResponse.observe(viewLifecycleOwner) {
                        SharedPrefManagerUser(requireContext()).saveUser(
                            User(
                                id = it.user?.id, name = it.user?.name,
                                phoneNo = it.user?.phoneNo, email = it.user?.email
                            )
                        )
                        startActivity(Intent(activity, MainActivityUser::class.java))
                        Log.d("UserLoginData", it.toString())
                    }

                }

            }

        }






        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}