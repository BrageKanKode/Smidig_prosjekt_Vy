package com.example.leafly_application_git.activities.search.trip

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.CombinedFunctionsClass.removeStuff
import com.example.leafly_application_git.CombinedFunctionsClass.verifyIfUserIsLoggedIn
import com.example.leafly_application_git.data.Json
import com.example.leafly_application_git.data.Times

import kotlinx.android.synthetic.main.list_choose_time_row.view.*

import kotlinx.android.synthetic.main.list_choose_time_row.view.textView_points

class ChooseTravelAdapter (private val json: Json, from: String, to: String, price: String, points: String): RecyclerView.Adapter<Custom>() {

    private val fromData = from
    private val toData = to
    private val priceData = price
    private val pointsData = points

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Custom {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_choose_time_row, parent, false)

        if(!verifyIfUserIsLoggedIn()){
            removeStuff(view, "SelectTime")
        }

        return Custom(view)
    }

    override fun getItemCount(): Int {
        return 3
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Custom, position: Int) {

        val data = json.times.get(position)
        holder.view.textView_From.text = fromData
        holder.view.textView_to.text = toData
        holder.view.textView_price_one.text = "$priceData Kr"
        holder.view.textView_points.text = "Du oppnår $pointsData miljøpoeng av denne reisen"

        holder.view.textView_time_arrival.text = data.arrival
        holder.view.textView_time_departure.text = data.departure

        holder.times = data
        holder.from = fromData
        holder.to = toData
        holder.price = priceData
        holder.points = pointsData
    }

}

class Custom(val view: View,
             var times: Times? = null,
             var to: String? = null,
             var from: String? = null,
             var price: String? = null,
             var points: String? = null): RecyclerView.ViewHolder(view) {

    companion object{
        const val DEPARTURE_KEY = "DEPARTURE"
        const val ARRIVAL_KEY = "ARRIVAL"
        const val DEPARTURE_TIME_KEY = "DEPARTURE_TIME"
        const val ARRIVAL_TIME_KEY = "ARRIVAL_TIME"
        const val DETAILS_PRICE_KEY = "DETAIL_PRICE"
        const val DETAILS_POINTS_KEY = "DETAIL_POINTS"
    }

    init {
        view.setOnClickListener{

            val intent = Intent(view.context, TravelDetailActivity::class.java)

            intent.putExtra(DEPARTURE_KEY,from)
            intent.putExtra(ARRIVAL_KEY, to)
            intent.putExtra(DEPARTURE_TIME_KEY, times?.departure)
            intent.putExtra(ARRIVAL_TIME_KEY, times?.arrival)
            intent.putExtra(DETAILS_PRICE_KEY, price)
            intent.putExtra(DETAILS_POINTS_KEY, points)

            view.context.startActivity(intent)
        }
    }

}