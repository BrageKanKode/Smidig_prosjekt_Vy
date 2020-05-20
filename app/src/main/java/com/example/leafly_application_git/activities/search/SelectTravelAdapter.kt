package com.example.leafly_application_git.activities.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.trip.ChooseTravelActivity
import com.example.leafly_application_git.data.Features
import com.example.leafly_application_git.data.Json
import kotlinx.android.synthetic.main.choose_trip_place_row.view.*

class SelectTravelAdapter (val json: Json): RecyclerView.Adapter<CustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val customView = layoutInflater.inflate(R.layout.choose_trip_place_row, parent, false)

        return CustomViewHolder(customView)
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val row = json.features.get(position)
        holder.customView.textView_choose_trip_from.text = row.fronLocation
        holder.customView.textView_choose_trip_to.text = row.toLocation
        holder.customView.textView_choose_trip_price.text = row.price
        holder.customView.textView_choose_trip_point.text = row.miljoPoeng

    }

}

class CustomViewHolder(val customView: View, var features: Features? = null): RecyclerView.ViewHolder(customView) {

    companion object {
        const val FROM_LOCATION_KEY = "FROM_LOCATION"
        const val TO_LOCATION_KEY = "To_LOCATION"
        const val PRICE_KEY = "PRICE"
        const val MILJO_POENG_KEY = "MILJOPOENG"
    }

    init {
        customView.setOnClickListener{

            val intent = Intent(customView.context, ChooseTravelActivity::class.java)

            intent.putExtra(FROM_LOCATION_KEY, features?.fronLocation)
            intent.putExtra(TO_LOCATION_KEY, features?.toLocation)
            intent.putExtra(PRICE_KEY, features?.price)
            intent.putExtra(MILJO_POENG_KEY, features?.miljoPoeng)

            customView.context.startActivity(intent)
        }
    }

}