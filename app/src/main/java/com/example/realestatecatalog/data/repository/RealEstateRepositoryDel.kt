package com.example.realestatecatalog.data.repository

import com.example.realestatecatalog.data.RealEstateProperty
import com.example.realestatecatalog.data.RealEstatePropertyDao
import javax.inject.Inject

class RealEstateRepositoryDel@Inject constructor(private val propertyDao: RealEstatePropertyDao) {
    suspend fun deleteProperty(property: RealEstateProperty) {
        propertyDao.deleteProperty(property)
    }
}