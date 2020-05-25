package com.example.leafly_application_git.activities.search.trip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafly_application_git.R
import kotlinx.android.synthetic.main.activity_confirmation_recycler.*
import kotlinx.android.synthetic.main.activity_select_time.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TravelDetailActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_confirmation_recycler)

        val departure: String = intent.getStringExtra(Custom.DEPARTURE_KEY)
        val arrival: String = intent.getStringExtra(Custom.ARRIVAL_KEY)
        val departureTime: String = intent.getStringExtra(Custom.DEPARTURE_TIME_KEY)
        val arrivalTime: String = intent.getStringExtra(Custom.ARRIVAL_TIME_KEY)
        val price: String = intent.getStringExtra(Custom.DETAILS_PRICE_KEY)
        val points: String = intent.getStringExtra(Custom.DETAILS_POINTS_KEY)
        dataPassClass(departure,arrival,departureTime,arrivalTime,price,points)

        recycler_view_confirmation.layoutManager = LinearLayoutManager(this)
    }

    private fun dataPassClass(departure: String,
                              arrival: String,
                              departureTime: String,
                              arrivalTime: String,
                              price: String,
                              points: String){

        recycler_view_confirmation.adapter = TravelDetailAdapter(departure, arrival, departureTime, arrivalTime, price, points)

        runOnUiThread{
            recycler_view_confirmation.adapter =
                TravelDetailAdapter(departure, arrival, departureTime, arrivalTime, price, points)
        }
    }

}