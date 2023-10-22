package com.example.realestatecatalog.data.repository

import com.example.realestatecatalog.data.RealEstateProperty
import com.example.realestatecatalog.data.RealEstatePropertyDao
import javax.inject.Inject

class RealEstateRepositoryGetAll@Inject constructor(private val propertyDao: RealEstatePropertyDao) {
    suspend fun getAllProperties(): List<RealEstateProperty> {
        return propertyDao.getAllProperties()
    }
}