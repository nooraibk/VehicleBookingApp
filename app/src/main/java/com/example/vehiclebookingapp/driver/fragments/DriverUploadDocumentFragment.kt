package com.example.vehiclebookingapp.driver.fragments

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.vehiclebookingapp.databinding.FragmentUploadDocumentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class DriverUploadDocumentFragment : Fragment() {

    private var _binding: FragmentUploadDocumentBinding? = null
    private val binding get() = _binding!!

    private lateinit var camera: Camera
    private lateinit var imageCapture: ImageCapture

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUploadDocumentBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        val cameraProviderFeature = ProcessCameraProvider.getInstance(requireContext())


        imageCapture = ImageCapture.Builder()
            .build()

        cameraProviderFeature.addListener({
            val cameraProvider = cameraProviderFeature.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(binding.previewViewForCamera.surfaceProvider)
                }

            val cameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()

            lifecycleScope.launch(Dispatchers.Main) {
                try {
                    cameraProvider.unbindAll()
                    camera = cameraProvider.bindToLifecycle(
                        this@DriverUploadDocumentFragment,
                        cameraSelector,
                        preview, imageCapture
                    )
                } catch (exception: Exception) {
                    Log.e("CameraException", exception.message, exception)
                }
            }


        }, ContextCompat.getMainExecutor(requireContext()))



        binding.btnUseCamera.setOnClickListener {

            captureImage(0)
        }

        return binding.root
    }

    private fun captureImage(id: Int) {

        binding.previewViewForCamera.visibility = View.VISIBLE
        val outputExecutor = ContextCompat.getMainExecutor(requireContext())
        val file =
            File(requireContext().getExternalFilesDir(null), "${System.currentTimeMillis()}.jpg")

        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()


        imageCapture.takePicture(
            outputOptions,
            outputExecutor,
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val savedUri = outputFileResults.savedUri ?: Uri.fromFile(file)
                    Log.d("TAG", "Photo capture succeeded: $savedUri")
                    if (id == 0) {
                        binding.ivCNICFront.setImageURI(savedUri)
                    } else {
                        binding.ivCNICBack.setImageURI(savedUri)
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    Log.e("TAG", "Photo capture failed: ${exception.message}", exception)
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}