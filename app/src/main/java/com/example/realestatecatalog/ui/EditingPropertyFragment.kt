package com.example.realestatecatalog.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.realestatecatalog.R
import com.example.realestatecatalog.app.MyApplication
import com.example.realestatecatalog.data.RealEstateProperty
import com.example.realestatecatalog.data.repository.RealEstateRepositoryDel
import com.example.realestatecatalog.data.repository.RealEstateRepositoryUpdate
import com.example.realestatecatalog.databinding.FragmentEditingPropertyBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


class EditingPropertyFragment : Fragment() {
    @Inject
    lateinit var RealEstateRepositoryUpdate: RealEstateRepositoryUpdate
    @Inject
    lateinit var RealEstateRepositoryDel: RealEstateRepositoryDel
    private var _binding: FragmentEditingPropertyBinding? = null
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditingPropertyBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        (activity?.application as MyApplication).appComponent.inject(this)
        val args = arguments
        val property: RealEstateProperty? = args?.getParcelable("property")

        if (property != null) {
            binding?.editTextAreaEdit?.setText(property.area.toString())
            binding?.editTextFloorEdit?.setText(property.floor.toString())
            binding?.editTextPriceEdit?.setText(property.price.toString())
            binding?.editTextRoomEdit?.setText(property.rooms.toString())
        }
        binding?.buttonRedact?.setOnClickListener {
            val updateProperty = RealEstateProperty(
                area = binding?.editTextAreaEdit?.text.toString().toDouble(),
                floor = binding?.editTextFloorEdit?.text.toString().toInt(),
                price = binding?.editTextPriceEdit?.text.toString().toDouble(),
                rooms = binding?.editTextRoomEdit?.text.toString().toInt(),
                photo = property?.photo!!,
                PricePerSquareMeter = property.PricePerSquareMeter,
                address = property.address,
                id = property.id
            )
            try {
                lifecycleScope.launch(Dispatchers.IO) {
                    RealEstateRepositoryUpdate.updateProperty(updateProperty)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            findNavController().navigate(R.id.listOfPropertyFragment)
        }
        binding?.buttonDel?.setOnClickListener {
            try {
                lifecycleScope.launch(Dispatchers.IO) {
                    RealEstateRepositoryDel.deleteProperty(property!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            findNavController().navigate(R.id.listOfPropertyFragment)
        }
        return root
    }
}