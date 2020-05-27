package com.example.leafly_application_git.activities.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.TestClass.verifyIfUserIsLoggedIn
import com.example.leafly_application_git.activities.search.trip.SelectTimeActivity
import com.example.leafly_application_git.data.Features
import com.example.leafly_application_git.data.Json
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.list_choose_time_row.view.*
import kotlinx.android.synthetic.main.list_choose_trip_place_row.view.*

class SelectTravelAdapter (private val json: Json): RecyclerView.Adapter<CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val customView = layoutInflater.inflate(R.layout.list_choose_trip_place_row, parent, false)

        if(!verifyIfUserIsLoggedIn()){
            removeStuff(customView)
        }

        return CustomViewHolder(customView)
    }

    override fun getItemCount(): Int {
        return 3
    }

    private fun removeStuff(view: View) {
        view.textView20.visibility = View.GONE
        view.textView_choose_trip_point.visibility = View.GONE
        view.textView17.visibility = View.GONE
        view.imageView7.visibility = View.GONE
        view.cardView.visibility = View.GONE

    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val value = json.features[position]
        holder.customView.textView_choose_trip_from?.text = value.fronLocation
        holder.customView.textView_choose_trip_to?.text = value.toLocation
        holder.customView.textView_choose_trip_price?.text = value.price
        holder.customView.textView_choose_trip_point?.text = value.miljoPoeng

        holder.features = value
    }

}


//Sends information to ChooseTravelActivity
class CustomViewHolder(val customView: View, var features: Features? = null): RecyclerView.ViewHolder(customView) {

    companion object {
        const val FROM_LOCATION_KEY = "FROM_LOCATION_KEY"
        const val TO_LOCATION_KEY = "TO_LOCATION"
        const val PRICE_KEY = "PRICE"
        const val MILJO_POENG_KEY = "MILJOPOENG"
    }

    init {
        customView.setOnClickListener{

            val intent = Intent(customView.context, SelectTimeActivity::class.java)

            intent.putExtra(FROM_LOCATION_KEY, features?.fronLocation)
            intent.putExtra(TO_LOCATION_KEY, features?.toLocation)
            intent.putExtra(PRICE_KEY, features?.price)
            intent.putExtra(MILJO_POENG_KEY, features?.miljoPoeng)

            customView.context.startActivity(intent)
        }
    }

}