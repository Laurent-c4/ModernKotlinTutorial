package com.tutorials.countries.di

import com.tutorials.countries.model.CountriesService
import com.tutorials.countries.viewmodel.EventsViewModel
import com.tutorials.countries.viewmodel.CountriesViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)

    fun inject(viewModel: CountriesViewModel)

    fun inject(viewModel: EventsViewModel)
}