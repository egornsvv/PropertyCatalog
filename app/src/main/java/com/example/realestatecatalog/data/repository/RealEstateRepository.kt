package com.example.realestatecatalog.data.repository

import com.example.realestatecatalog.data.RealEstateProperty
import com.example.realestatecatalog.data.RealEstatePropertyDao
import javax.inject.Inject


class RealEstateRepositoryInsert @Inject constructor(private val propertyDao: RealEstatePropertyDao) {
    suspend fun insertProperty(property: RealEstateProperty) {
        propertyDao.insertProperty(property)
    }
}