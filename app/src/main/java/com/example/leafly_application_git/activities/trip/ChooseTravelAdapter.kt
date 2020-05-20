package com.example.leafly_application_git.activities.trip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.search.CustomViewHolder
import com.example.leafly_application_git.data.Json
import com.example.leafly_application_git.data.Times
import kotlinx.android.synthetic.main.list_choose_time_row.view.*

class ChooseTravelAdapter (val json: Json): RecyclerView.Adapter<Custom>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Custom {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_choose_time_row, parent, false)

        return Custom(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: Custom, position: Int) {


        val row = json.times.get(position)
        holder.view.textView_From.text = CustomViewHolder.FROM_LOCATION_KEY
        holder.view.textView_to.text = CustomViewHolder.TO_LOCATION_KEY
        holder.view.textView_price.text = CustomViewHolder.PRICE_KEY
        holder.view.textView_points.text = CustomViewHolder.MILJO_POENG_KEY
        holder.view.textView_time_arrival.text = row.arrival
        holder.view.textView_time_departure.text = row.departure
    }

}

class Custom(val view: View, var time: Times? = null): RecyclerView.ViewHolder(view) {

}