/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tutorials.countries.view.eventsscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutorials.countries.R
import com.tutorials.countries.viewmodel.EventsViewModel
import kotlinx.android.synthetic.main.fragment_countries.view.*


/**
 * Shows events fetched from API.
 */
class Events : Fragment() {

    lateinit var viewModel: EventsViewModel
    private val eventListAdapter =
        EventListAdapter(
            arrayListOf()
        )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_events, container, false)

        viewModel = ViewModelProviders.of(this).get(EventsViewModel::class.java)
        viewModel.refresh()

        view.recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = eventListAdapter
        }

        view.swipeRefreshLayout.setOnRefreshListener {
            view.swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel(view)

        return view
    }

    fun observeViewModel(view: View) {

        viewModel.events.observe(viewLifecycleOwner, Observer { events ->
            events?.let { eventListAdapter.swapDataSet(it) }
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let { view.list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                view.loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    view.list_error.visibility = View.GONE
                    view.recycler_view.visibility = View.GONE
                } else {
                    view.recycler_view.visibility = View.VISIBLE
                }
            }
        })
    }

}

