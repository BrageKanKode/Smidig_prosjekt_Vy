package com.example.leafly_application_git.activities.miljopoints.use

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R

class DonateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}