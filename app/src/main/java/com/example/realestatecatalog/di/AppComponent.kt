package com.example.realestatecatalog.di

import com.example.realestatecatalog.ui.AddPropertyFragment
import com.example.realestatecatalog.ui.EditingPropertyFragment
import com.example.realestatecatalog.ui.ListOfPropertyFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, AppModule::class])
interface AppComponent {
    fun inject(listOfPropertyFragment: ListOfPropertyFragment)
    fun inject(addPropertyFragment: AddPropertyFragment)
    fun inject(editingPropertyFragment: EditingPropertyFragment)
}