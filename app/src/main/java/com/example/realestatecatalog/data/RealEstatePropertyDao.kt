package com.example.realestatecatalog.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RealEstatePropertyDao {
    @Insert
    suspend fun insertProperty(property: RealEstateProperty)

    @Update
    suspend fun updateProperty(property: RealEstateProperty)

    @Delete
    suspend fun deleteProperty(property: RealEstateProperty)

    @Query("SELECT * FROM realestateproperty")
    suspend fun getAllProperties(): List<RealEstateProperty>

}