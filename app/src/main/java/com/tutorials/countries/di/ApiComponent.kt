package com.tutorials.countries.di

import com.tutorials.countries.model.CountriesService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
}