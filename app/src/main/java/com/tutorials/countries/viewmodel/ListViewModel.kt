package com.tutorials.countries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tutorials.countries.model.Country

class ListViewModel : ViewModel() {

    val countries = MutableLiveData<ArrayList<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        val mockData: ArrayList<Country> = arrayListOf(
            Country("Country A"),
            Country("Country B"),
            Country("Country C"),
            Country("Country D"),
            Country("Country E"),
            Country("Country F"),
            Country("Country I"),
            Country("Country J"),
            Country("Country K")
        )

        countryLoadError.value = false
        loading.value = false
        countries.value = mockData
    }

}