package com.example.leafly_application_git.activities.search.payment

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_confirmation.view.*
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.*
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.cardView_confirmation
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.imageView_travel_order_leaf
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.textView_points

class OrderDetailsAdapter(
    private val departure: String,
    private val arrival: String,
    private val departureTime: String,
    private val price: String,
    private val points: String
) : RecyclerView.Adapter<ViewOrderDetails>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewOrderDetails {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_travel_order_overview, parent, false)


        if(!verifyIfUserIsLoggedIn()){
            removeStuff(view)
        }
        return ViewOrderDetails(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewOrderDetails, position: Int) {

        holder.view.textView_from.text = "Fra $departure til $arrival"
        holder.view.textView_departure_time.text = "I dag, $departureTime"
        holder.view.textView_price_one.text = "$price,-"
        holder.view.textView_price_two.text = "$price,-"
        holder.view.textView_points.text = "du oppnår $points miljøpoeng for denne reisen"

        holder.departure = departure
        holder.arrival = arrival
        holder.departureTime = departureTime
        holder.price = price
        holder.points = points

    }
}

//Checks with the Firebase Authentication if user is logged in or not
private fun verifyIfUserIsLoggedIn(): Boolean {
    val uid = FirebaseAuth.getInstance().uid
    //If user is not logged in, then return null
    return uid != null
}

private fun removeStuff(view: View) {
    view.textView_points.visibility = View.GONE
    view.imageView_travel_order_leaf.visibility = View.GONE
    view.cardView_confirmation.visibility = View.GONE

}

class ViewOrderDetails(
    val view: View,
    var departure: String? = null,
    var arrival: String? = null,
    var departureTime: String? = null,
    var price: String? = null,
    var points: String? = null
): RecyclerView.ViewHolder(view) {
    companion object{
        const val DEPARTURE_KEY = "DEPARTURE"
        const val ARRIVAL_KEY = "ARRIVAL"
        const val DEPARTURE_TIME_KEY = "DEPARTURE_TIME"
        const val DETAILS_PRICE_KEY = "DETAIL_PRICE"
        const val DETAILS_POINTS_KEY = "DETAIL_POINTS"
    }
    init {
        view.btnToPayment.setOnClickListener {
            val intent = Intent(view.context, SplashScreenPaymentActivity::class.java)

            intent.putExtra(DEPARTURE_KEY, departure)
            intent.putExtra(ARRIVAL_KEY, arrival)
            intent.putExtra(DEPARTURE_TIME_KEY, departureTime)
            intent.putExtra(DETAILS_PRICE_KEY, price)
            intent.putExtra(DETAILS_POINTS_KEY, points)


            view.context.startActivity(intent)
        }
    }

}