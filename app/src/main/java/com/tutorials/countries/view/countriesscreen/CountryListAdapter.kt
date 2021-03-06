package com.tutorials.countries.view.countriesscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutorials.countries.R
import com.tutorials.countries.model.Country
import com.tutorials.countries.util.getProgressDrawable
import com.tutorials.countries.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    fun swapDataSet(countryList: ArrayList<Country>) {
        countries.clear()
        countries.addAll(countryList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_country, parent, false
            )
        )

    override fun getItemCount() = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val countryName = view.name
        private val flag = view.flag
        private val countryCapital = view.capital
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country: Country) {
            countryName.text = country.name
            countryCapital.text = country.capital
            flag.loadImage(country.flagPNG, progressDrawable)
        }
    }

}