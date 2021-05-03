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

package com.tutorials.countries.view.listscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tutorials.countries.R
import com.tutorials.countries.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.fragment_countries.view.*


/**
 * Shows countries fetched from API.
 */
class Countries : Fragment() {

    lateinit var viewModel: ListViewModel
    private val countryListAdapter = CountryListAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_countries, container, false)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        view.countries_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countryListAdapter
        }

        view.swipeRefreshLayout.setOnRefreshListener {
            view.swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel(view)

        return view
    }

    fun observeViewModel(view: View) {

        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let { countryListAdapter.swapDataSet(it) }
        })

        viewModel.countryLoadError.observe(viewLifecycleOwner, Observer { isError ->
            isError?.let { view.list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                view.loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    view.list_error.visibility = View.GONE
                    view.countries_list.visibility = View.GONE
                } else {
                    view.countries_list.visibility = View.VISIBLE
                }
            }
        })
    }

}

