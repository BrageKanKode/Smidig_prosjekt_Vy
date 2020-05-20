package com.example.leafly_application_git.activities.trip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.search.CustomViewHolder
import com.example.leafly_application_git.activities.search.SelectTravelAdapter
import com.example.leafly_application_git.data.Json
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_choose_trip.*
import kotlinx.android.synthetic.main.activity_select_time.*
import java.io.InputStream

class ChooseTravelActivity : AppCompatActivity() {

    var fromLocation: String? = null
    var toLocation: String? = null
    var priceLocation: String? = null
    var pointsLocation: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_select_time)

        recyclerView_time_select.layoutManager = LinearLayoutManager(this)

        fetchJson()

        fromLocation = intent.getStringExtra(CustomViewHolder.FROM_LOCATION_KEY)
        toLocation = intent.getStringExtra(CustomViewHolder.TO_LOCATION_KEY)
        priceLocation = intent.getStringExtra(CustomViewHolder.PRICE_KEY)
        pointsLocation = intent.getStringExtra(CustomViewHolder.MILJO_POENG_KEY)

    }


    fun fetchJson() {

        var json: String? = null

        val inputStream : InputStream? = assets.open("dummydata.json")
        val gson = GsonBuilder().create()

        json = inputStream?.bufferedReader().use { it?.readText() }

        val time = gson.fromJson(json, Json::class.java)


        //RecyclerView_main.adapter = SelectTravelAdapter(location)
        recyclerView_time_select.adapter = ChooseTravelAdapter(time)

        runOnUiThread {
            recyclerView_time_select.adapter =
                SelectTravelAdapter(
                    time
                )
        }
    }
}