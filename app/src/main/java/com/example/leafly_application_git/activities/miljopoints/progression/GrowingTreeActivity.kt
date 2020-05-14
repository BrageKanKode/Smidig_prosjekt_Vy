package com.example.leafly_application_git.activities.miljopoints.progression

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R

class GrowingTreeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_growing_tree)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}