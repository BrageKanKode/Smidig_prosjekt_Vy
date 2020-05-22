package com.example.leafly_application_git.activities.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafly_application_git.R
import com.example.leafly_application_git.data.Json
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_choose_trip.*
import java.io.InputStream

class SelectTravelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_trip)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        readJson()

        RecyclerView_main.layoutManager = LinearLayoutManager(this)

    }

    private fun readJson() {

        var json: String? = null

        val inputStream : InputStream? = assets.open("dummydata.json")
        val gson = GsonBuilder().create()

        json = inputStream?.bufferedReader().use { it?.readText() }

        val location = gson.fromJson(json, Json::class.java)


        RecyclerView_main.adapter = SelectTravelAdapter(location)

        runOnUiThread {
            RecyclerView_main.adapter =
                SelectTravelAdapter(
                    location
                )
        }

    }
}


