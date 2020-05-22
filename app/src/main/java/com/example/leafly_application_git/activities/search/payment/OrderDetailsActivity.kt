package com.example.leafly_application_git.activities.search.payment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.search.trip.ViewTravelDetails
import kotlinx.android.synthetic.main.activity_select_time.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class OrderDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_time)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val departure: String = intent.getStringExtra(ViewTravelDetails.DEPARTURE_KEY)
        val arrival: String = intent.getStringExtra(ViewTravelDetails.ARRIVAL_KEY)
        val departureTime: String = intent.getStringExtra(ViewTravelDetails.DEPARTURE_TIME_KEY)
        val price: String = intent.getStringExtra(ViewTravelDetails.DETAILS_PRICE_KEY)
        val points: String = intent.getStringExtra(ViewTravelDetails.DETAILS_POINTS_KEY)
        dataPassClass(departure ,arrival ,departureTime ,price ,points)

        recyclerView_time_select.layoutManager = LinearLayoutManager(this)
    }

    private fun dataPassClass(departure: String,
                              arrival: String,
                              departureTime: String,
                              price: String,
                              points: String){

        recyclerView_time_select.adapter = OrderDetailsAdapter(departure, arrival, departureTime, price, points)

        runOnUiThread{
            recyclerView_time_select.adapter =
                OrderDetailsAdapter(departure, arrival, departureTime, price, points)
        }
    }
}