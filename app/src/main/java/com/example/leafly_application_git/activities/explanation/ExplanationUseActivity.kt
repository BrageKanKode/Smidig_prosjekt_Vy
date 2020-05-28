package com.example.leafly_application_git.activities.explanation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import kotlinx.android.synthetic.main.activity_explanation_what_are_points.*

class ExplanationUseActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation_use_points)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}