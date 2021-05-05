package com.tutorials.countries.view.eventsscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tutorials.countries.R
import com.tutorials.countries.model.Country
import com.tutorials.countries.model.Event
import com.tutorials.countries.util.getProgressDrawable
import com.tutorials.countries.util.loadImage
import kotlinx.android.synthetic.main.item_country.view.*

class EventListAdapter(var events: ArrayList<Event>) :
    RecyclerView.Adapter<EventListAdapter.EventViewHolder>() {

    fun swapDataSet(eventsList: ArrayList<Event>) {
        events.clear()
        events.addAll(eventsList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_event, parent, false
            )
        )

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val countryName = view.name

        fun bind(event: Event) {
            countryName.text = event.name
        }
    }

}