package com.tutorials.countries.di

import com.google.firebase.firestore.FirebaseFirestore
import com.tutorials.countries.model.CountriesAPI
import com.tutorials.countries.model.CountriesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
class ApiModule {

    private val BASE_URL ="https://raw.githubusercontent.com"

    @Provides
    fun provideCountriesApi() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(CountriesAPI::class.java)

    @Provides
    fun provideCountriesService() =  CountriesService()

    @Provides
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    fun provideProductsCollectionReference(rootRef:FirebaseFirestore) = rootRef.collection("events")
}