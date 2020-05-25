package com.example.leafly_application_git.activities.search.trip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.search.CustomViewHolder
import com.example.leafly_application_git.data.Json
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_confirmation_recycler.*
import java.io.InputStream

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SelectTimeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_confirmation_recycler)

        val from: String = intent.getStringExtra(CustomViewHolder.FROM_LOCATION_KEY)
        val to: String = intent.getStringExtra(CustomViewHolder.TO_LOCATION_KEY)
        val price: String = intent.getStringExtra(CustomViewHolder.PRICE_KEY)
        val points: String = intent.getStringExtra(CustomViewHolder.MILJO_POENG_KEY)
        fetchJson(from, to, price, points)

        recycler_view_confirmation.layoutManager = LinearLayoutManager(this)

    }


    private fun fetchJson(from: String, to: String, price: String, points: String) {

        val json: String?

        val inputStream : InputStream? = assets.open("dummydata.json")
        val gson = GsonBuilder().create()

        json = inputStream?.bufferedReader().use { it?.readText() }

        val time = gson.fromJson(json, Json::class.java)

        //RecyclerView_main.adapter = SelectTravelAdapter(location)
        recycler_view_confirmation.adapter = ChooseTravelAdapter(time, from,to,price,points)

        runOnUiThread {
            recycler_view_confirmation.adapter =
                ChooseTravelAdapter(
                    time, to, from, price, points
                )
        }
    }
}