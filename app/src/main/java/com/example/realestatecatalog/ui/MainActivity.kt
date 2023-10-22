package com.example.realestatecatalog.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.realestatecatalog.R
import com.example.realestatecatalog.domain.PropertyViewModel

private lateinit var propertyViewModel: PropertyViewModel


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        propertyViewModel = ViewModelProvider(this).get(PropertyViewModel::class.java)
        val viewModel : PropertyViewModel by viewModels()
    }
}