package com.example.leafly_application_git.activities.search.trip

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.search.payment.OrderDetailsActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_travel_detail.view.*
import kotlinx.android.synthetic.main.activity_travel_detail.view.textView_departure_time
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.*

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

        if(!verifyIfUserIsLoggedIn()){
            removeStuff(view)
        }

        return ViewTravelDetails(view)
    }

    //Checks with the Firebase Authentication if user is logged in or not
    private fun verifyIfUserIsLoggedIn(): Boolean {
        val uid = FirebaseAuth.getInstance().uid
        //If user is not logged in, then return null
        return uid != null
    }

    private fun removeStuff(view: View) {
        view.textView_details_points.visibility = View.GONE
        view.imageView_detail_leaf_green.visibility = View.GONE
        view.cardView5.visibility = View.GONE

    }

    override fun getItemCount(): Int {
        return 1
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewTravelDetails, position: Int) {

        holder.view.textView_departure.text = departure
        holder.view.textView_arrival.text = arrival
        holder.view.textView_departure_time.text = departureTime
        holder.view.textView_arrival_time.text = arrivalTime
        holder.view.textView_details_price.text = "$price kr"
        holder.view.textView_details_points.text ="Du oppnår $points miljøpoeng av denne reisen"

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

    //Checks with the Firebase Authentication if user is logged in or not
    private fun verifyIfUserIsLoggedIn(): Boolean {
        val uid = FirebaseAuth.getInstance().uid
        //If user is not logged in, then return null
        return uid != null
    }

    private fun removeStuff(view: View) {
        //linearLayout.removeView(view.textView_points)
        view.textView_points.visibility = View.GONE
        view.imageView_travel_order_leaf.visibility = View.GONE
        view.cardView_confirmation.visibility = View.GONE

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