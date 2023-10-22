package com.example.realestatecatalog.app

import android.app.Application
import com.example.realestatecatalog.di.AppComponent
import com.example.realestatecatalog.di.AppModule
import com.example.realestatecatalog.di.DaggerAppComponent
import com.example.realestatecatalog.di.DatabaseModule

class MyApplication : Application() {

    lateinit var  appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .databaseModule(DatabaseModule())
            .appModule(AppModule(this))
            .build()
    }
}