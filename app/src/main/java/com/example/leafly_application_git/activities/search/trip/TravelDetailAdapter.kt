package com.example.leafly_application_git.activities.search.trip

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.search.payment.OrderDetailsActivity
import kotlinx.android.synthetic.main.activity_travel_detail.view.*

class TravelDetailAdapter(
    private val departure: String,
    private val arrival: String,
    private val departureTime: String,
    private val arrivalTime: String,
    private val price: String,
    private val points: String
) : RecyclerView.Adapter<ViewTravelDetails>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTravelDetails {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_travel_detail, parent, false)

        return ViewTravelDetails(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewTravelDetails, position: Int) {

        holder.view.textView_departure.text = departure
        holder.view.textView_arrival.text = arrival
        holder.view.textView_departure_time.text = departureTime
        holder.view.textView_arrival_time.text = arrivalTime
        holder.view.textView_details_price.text = price
        holder.view.textView_details_points.text = points

        holder.departure = departure
        holder.arrival = arrival
        holder.departureTime = departureTime
        holder.arrivalTime = arrivalTime
        holder.price = price
        holder.points = points
    }

}

class ViewTravelDetails(val view: View,
                        var departure: String? = null,
                        var arrival: String? = null,
                        var departureTime: String? = null,
                        var arrivalTime: String? = null,
                        var price: String? = null,
                        var points: String? = null ): RecyclerView.ViewHolder(view){
    companion object{

        const val DEPARTURE_KEY = "DEPARTURE"
        const val ARRIVAL_KEY = "ARRIVAL"
        const val DEPARTURE_TIME_KEY = "DEPARTURE_TIME"
        const val ARRIVAL_TIME_KEY = "ARRIVAL_TIME"
        const val DETAILS_PRICE_KEY = "DETAIL_PRICE"
        const val DETAILS_POINTS_KEY = "DETAIL_POINTS"


    }

    init {
        view.button_til_bestilling.setOnClickListener{


            val intent = Intent(view.context, OrderDetailsActivity:: class.java)

            intent.putExtra(DEPARTURE_KEY,departure)
            intent.putExtra(ARRIVAL_KEY, arrival)
            intent.putExtra(DEPARTURE_TIME_KEY, departureTime)
            intent.putExtra(ARRIVAL_TIME_KEY, arrivalTime)
            intent.putExtra(DETAILS_PRICE_KEY, price)
            intent.putExtra(DETAILS_POINTS_KEY, points)


            view.context.startActivity(intent)
        }
    }

}