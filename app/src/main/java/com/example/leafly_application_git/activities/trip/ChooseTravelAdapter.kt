package com.example.leafly_application_git.activities.trip

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.data.Json
import kotlinx.android.synthetic.main.list_choose_time_row.view.*

class ChooseTravelAdapter (val json: Json, val from: String, val to: String, val price: String, val points: String): RecyclerView.Adapter<Custom>() {

    private val fromData = from;
    private val toData = to;
    private val priceData = price;
    private val pointsData = price;

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
        holder.view.textView_From.text = fromData
        holder.view.textView_to.text = toData
        holder.view.textView_price.text = priceData
        holder.view.textView_points.text = pointsData

        holder.view.textView_time_arrival.text = row.arrival
        holder.view.textView_time_departure.text = row.departure
    }

}

class Custom(val view: View, var json: Json? = null): RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener{
            //val intent = Intent(view.context, )
            //Need to add new activity on click for redirect.
        }
    }

}