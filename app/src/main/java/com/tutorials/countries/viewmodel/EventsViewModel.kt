package com.tutorials.countries.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.CollectionReference
import com.tutorials.countries.di.DaggerApiComponent
import com.tutorials.countries.model.CountriesService
import com.tutorials.countries.model.Country
import com.tutorials.countries.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class EventsViewModel : ViewModel() {

    @Inject
    lateinit var eventsRef:CollectionReference

    init {
        DaggerApiComponent.create().inject(this)
    }


    private val disposable = CompositeDisposable()

    val events = MutableLiveData<ArrayList<Event>>()
    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchEvents()
    }

    fun fetchEvents() {
        loading.value = true
        eventsRef.get()
            .addOnSuccessListener { documents ->
                val results = ArrayList<Event>()

                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    results.add(document.toObject(Event::class.java))
                }

                events.value = results
                error.value = false
                loading.value = false

            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
                loading.value = false
                error.value = true
            }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}