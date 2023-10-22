package com.example.realestatecatalog.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.realestatecatalog.R
import com.example.realestatecatalog.data.RealEstateProperty
import com.example.realestatecatalog.databinding.FragmentVewingPropertyBinding
import com.example.realestatecatalog.domain.PropertyViewModel
import com.squareup.picasso.Picasso

class VewingPropertyFragment : Fragment() {
    private var _binding: FragmentVewingPropertyBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVewingPropertyBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val args = arguments
        val property: RealEstateProperty? = args?.getParcelable("property")
        if (property != null) {
            binding?.priceTextView?.text = property.price.toString()
            binding?.squareMetersTextView?.text = property.area.toString()
            if (!property.photo.isNullOrEmpty()) {
                Picasso.get().load(property.photo).into(binding?.viewPager)
            } else {
                binding?.viewPager?.setImageResource(R.drawable._56cfd5afa30b7f2aa3ba77b7d44d2fdb52e9561)
            }
            binding?.textViewAddress?.text = property.address
            binding?.textViewNumberRoomsRes?.text = property.rooms.toString()
            binding?.textViewFloorRes?.text = property.floor.toString()
            binding?.textViewPriceSqmRes?.text = property.PricePerSquareMeter
        }
        binding?.buttonRed?.setOnClickListener {
            val bundle = bundleOf("property" to property)
            findNavController().navigate(R.id.editingPropertyFragment, bundle)
        }
        return root
    }
}