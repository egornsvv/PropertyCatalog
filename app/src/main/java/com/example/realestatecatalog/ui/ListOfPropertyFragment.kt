package com.example.realestatecatalog.ui

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.realestatecatalog.R
import com.example.realestatecatalog.app.MyApplication
import com.example.realestatecatalog.data.RealEstateProperty
import com.example.realestatecatalog.data.repository.RealEstateRepositoryGetAll
import com.example.realestatecatalog.databinding.FragmentListOfPropertyBinding
import com.example.realestatecatalog.ui.adapter.PropertyAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListOfPropertyFragment : Fragment() {
    private var _binding: FragmentListOfPropertyBinding? = null
    private val binding get() = _binding
    @Inject
    lateinit var RealEstateRepositoryGetAll: RealEstateRepositoryGetAll

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListOfPropertyBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        (activity?.application as MyApplication).appComponent.inject(this)

        binding?.signOut2?.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.authorizationFragment)
        }
        binding?.createBut?.setOnClickListener {
            findNavController().navigate(R.id.addPropertyFragment)
        }
        val recyclerView = binding?.RecyclerView
        val adapter = PropertyAdapter(object : PropertyAdapter.OnItemClickListener {
            override fun onItemClick(property: RealEstateProperty) {
                val bundle = bundleOf("property" to property)
                findNavController().navigate(R.id.vewingPropertyFragment, bundle)
            }
        })
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        try {
            lifecycleScope.launch {
                val properties = RealEstateRepositoryGetAll.getAllProperties()
                adapter.setProperties(properties)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return root
    }

}