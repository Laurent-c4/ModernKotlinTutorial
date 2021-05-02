package com.tutorials.countries.model

import com.tutorials.countries.di.DaggerApiComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CountriesService {

    @Inject
    lateinit var api:CountriesAPI

    init {
       DaggerApiComponent.create().inject(this)
    }

    fun getCountries() = api.getCountries()
}