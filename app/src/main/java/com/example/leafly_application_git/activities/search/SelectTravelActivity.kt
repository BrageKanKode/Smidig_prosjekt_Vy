package com.example.leafly_application_git.activities.search

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.example.leafly_application_git.R
import kotlinx.android.synthetic.main.activity_choose_trip.*
import org.json.JSONArray
import java.io.File
import java.io.IOException
import java.io.InputStream

class SelectTravelActivity : AppCompatActivity() {

    var arr = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_trip)

        readJson()


        dummyBtn.setOnClickListener {
            openNewActivity()
        }
    }

    fun readJson() {

        var json: String? = null

        //val fileName = File("dummydata.json")

        try {

            //val inputStream : InputStream? = context?.assets?.open("dummydata.json")
            val inputStream: InputStream = assets.open("dummydata.json")
            json = inputStream.bufferedReader().use { it.readText() }


            var jarr = JSONArray(json)
            for (i in 0 until jarr.length()) {

                var jobj = jarr.getJSONObject(i)
                arr.add(jobj.getString("fronLocation"))
            }
            var adapt = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
            json_List.adapter = adapt

        } catch (e: IOException) {
            println(e)
        }
    }

    fun openNewActivity() {
        val intent = Intent(this, TravelDetailsActivity::class.java)
        startActivity(intent)
    }
}
