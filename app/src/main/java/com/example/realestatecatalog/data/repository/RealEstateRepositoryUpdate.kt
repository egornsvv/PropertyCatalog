package com.example.realestatecatalog.data.repository

import com.example.realestatecatalog.data.RealEstateProperty
import com.example.realestatecatalog.data.RealEstatePropertyDao
import javax.inject.Inject

class RealEstateRepositoryUpdate@Inject constructor(private val propertyDao: RealEstatePropertyDao) {
    suspend fun updateProperty(property: RealEstateProperty) {
        propertyDao.updateProperty(property)
    }
}