package com.example.vehiclebookingapp.driver.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.vehiclebookingapp.databinding.ActivityAddVehicleBinding
import com.example.vehiclebookingapp.driver.SharedPrefManagerDriver
import com.example.vehiclebookingapp.driver.data.DriverRepo
import com.example.vehiclebookingapp.driver.model.*
import java.io.ByteArrayOutputStream

class AddVehicleActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityAddVehicleBinding
    val binding get() = _binding

    private lateinit var viewModel: DriverCarsViewModel

    private val PICK_IMAGES_REQUEST_CODE = 200
    private val MAX_IMAGES = 3

    private var encodedString: ArrayList<String> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddVehicleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = CarViewModelFactory(DriverRepo())
        viewModel =
            ViewModelProvider(
                this@AddVehicleActivity,
                viewModelFactory
            )[DriverCarsViewModel::class.java]

        binding.btnAddVehicle.setOnClickListener {

            val car = RegCarModel(
                carModel = binding.etCarModel.text.toString(),
                carNo = binding.etCarCompany.text.toString(),
                driverId = SharedPrefManagerDriver(this).getDriver().id,
                carName = binding.etCarName.text.toString(),
                images = encodedString
            )
            viewModel.registerCar(car)


            viewModel.carResponse.observe(this) {
                Log.d("Car Response", it.toString())
            }
        }

    }

    fun btnGetImagesOfVehicle(view: View) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.type = "image/*"
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            PICK_IMAGES_REQUEST_CODE
        )


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGES_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imagesList = mutableListOf<Bitmap>()
            if (data?.clipData != null) {
                // Multiple images selected
                val count = data.clipData!!.itemCount
                for (i in 0 until count) {
                    val imageUri = data.clipData!!.getItemAt(i).uri
                    val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)


                    imagesList.add(imageBitmap)
                }
            } else {
                // Single image selected
                val imageUri = data?.data
                val imageBitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                imagesList.add(imageBitmap)
            }
            if (imagesList.size > MAX_IMAGES) {
                Toast.makeText(this, "You can select 3 images only", Toast.LENGTH_SHORT).show()
            } else {
                // Do something with the selected images

                for ((i, image) in imagesList.withIndex()) {
                    if (i > 2) return
                    val imageView = binding.layoutImages.getChildAt(i)
                    val stream = ByteArrayOutputStream()
                    image.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    val byteArray = stream.toByteArray()
                    encodedString.add(Base64.encodeToString(byteArray, Base64.DEFAULT))

                    Glide.with(this)
                        .load(image)
                        .into(imageView as ImageView)
                }
            }
        }
    }

}