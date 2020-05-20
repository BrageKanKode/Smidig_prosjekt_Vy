package com.example.leafly_application_git.activities.trip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.search.SelectTravelAdapter
import com.example.leafly_application_git.data.Json
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_choose_trip.*
import kotlinx.android.synthetic.main.activity_select_time.*
import java.io.InputStream

class ChooseTravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_select_time)

        recyclerView_time_select.layoutManager = LinearLayoutManager(this)

    }


    fun fetchJson() {

        var json: String? = null

        val inputStream : InputStream? = assets.open("dummydata.json")
        val gson = GsonBuilder().create()

        json = inputStream?.bufferedReader().use { it?.readText() }

        val time = gson.fromJson(json, Json::class.java)


        //RecyclerView_main.adapter = SelectTravelAdapter(location)

        runOnUiThread {
            RecyclerView_main.adapter =
                SelectTravelAdapter(
                    time
                )
        }
    }
}