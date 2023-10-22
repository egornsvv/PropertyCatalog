package com.example.realestatecatalog.ui

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.realestatecatalog.R
import com.example.realestatecatalog.app.MyApplication
import com.example.realestatecatalog.data.RealEstateProperty
import com.example.realestatecatalog.data.repository.RealEstateRepositoryInsert
import com.example.realestatecatalog.databinding.FragmentAddPropertyBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddPropertyFragment : Fragment() {
    @Inject
    lateinit var RealEstateRepositoryInsert: RealEstateRepositoryInsert
    lateinit var binding : FragmentAddPropertyBinding
    private val PICK_IMAGE_REQUEST = 1
    private var selectedImageUri: Uri? = null
    private val PERMISSION_REQUEST_CODE = 123

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPropertyBinding.inflate(inflater, container, false)
        (activity?.application as MyApplication).appComponent.inject(this)
        val adress = binding.editTextAdres
        val price = binding.editTextPrice
        val area = binding.editTextArea
        val floor = binding.editTextFloor
        val pricePerSquareMeter = binding.editTextPricePerSquareMeter
        val room = binding.editTextRoom

        binding.AddPhotoBut.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, PICK_IMAGE_REQUEST)
            }
        }
        binding.createPropertBut.setOnClickListener {
            if (price.text.isNotBlank() &&
                area.text.isNotBlank() &&
                adress.text.isNotBlank() &&
                room.text.isNotBlank() &&
                pricePerSquareMeter.text.isNotBlank() &&
                floor.text.isNotBlank()) {
                val property = RealEstateProperty(
                    price = price.text.toString().toDouble(),
                    area = area.text.toString().toDouble(),
                    address = adress.text.toString(),
                    rooms = room.text.toString().toInt(),
                    PricePerSquareMeter = pricePerSquareMeter.text.toString(),
                    floor = floor.text.toString().toInt(),
                    photo = selectedImageUri.toString(),
                )
                lifecycleScope.launch(Dispatchers.IO) {
                    RealEstateRepositoryInsert.insertProperty(property)
                }
                findNavController().navigate(R.id.listOfPropertyFragment)
            } else {
                Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            try {
                selectedImageUri = data?.data
                binding.imageView.setImageURI(selectedImageUri)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}