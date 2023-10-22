package com.example.realestatecatalog.di

import android.content.Context
import androidx.room.Room
import com.example.realestatecatalog.data.RealEstateDatabase
import com.example.realestatecatalog.data.RealEstatePropertyDao
import com.example.realestatecatalog.data.repository.RealEstateRepositoryDel
import com.example.realestatecatalog.data.repository.RealEstateRepositoryGetAll
import com.example.realestatecatalog.data.repository.RealEstateRepositoryInsert
import com.example.realestatecatalog.data.repository.RealEstateRepositoryUpdate
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideRealEstateDatabase(context: Context): RealEstateDatabase {
        return Room.databaseBuilder(context, RealEstateDatabase::class.java, "real_estate_database").build()
    }

    @Provides
    @Singleton

    fun provideRealEstatePropertyDao(database: RealEstateDatabase): RealEstatePropertyDao {
        return database.realEstatePropertyDao()
    }

    @Provides
    @Singleton
    fun provideRealEstateRepositoryInsert(propertyDao: RealEstatePropertyDao): RealEstateRepositoryInsert {
        return RealEstateRepositoryInsert(propertyDao)
    }
    @Provides
    @Singleton
    fun provideRealEstateRepositoryGetAll(propertyDao: RealEstatePropertyDao): RealEstateRepositoryGetAll {
        return RealEstateRepositoryGetAll(propertyDao)
    }
    @Provides
    @Singleton
    fun provideRealEstateRepositoryUpdate(propertyDao: RealEstatePropertyDao): RealEstateRepositoryUpdate {
        return RealEstateRepositoryUpdate(propertyDao)
    }
    @Provides
    @Singleton
    fun provideRealEstateRepositoryDel(propertyDao: RealEstatePropertyDao): RealEstateRepositoryDel {
        return RealEstateRepositoryDel(propertyDao)
    }

}