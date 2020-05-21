package com.example.leafly_application_git.activities.search

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
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

        readJson()

        RecyclerView_main.layoutManager = LinearLayoutManager(this)

        dummyBtn.setOnClickListener {
            openNewActivity()
        }
    }

    fun readJson() {

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


    fun openNewActivity() {
        val intent = Intent(this, TravelDetailsActivity::class.java)
        startActivity(intent)
    }
}


