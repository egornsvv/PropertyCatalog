package com.example.realestatecatalog.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [RealEstateProperty::class], version = 1, exportSchema = false)
abstract class RealEstateDatabase : RoomDatabase() {
    abstract fun realEstatePropertyDao(): RealEstatePropertyDao
}