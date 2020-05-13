package com.example.leafly_application_git.activities.miljopoints.usePoints

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R

class DuringTripActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_during_trip)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}