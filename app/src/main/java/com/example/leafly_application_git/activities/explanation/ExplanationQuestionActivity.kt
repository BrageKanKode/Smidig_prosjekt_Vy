package com.example.leafly_application_git.activities.explanation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R

class ExplanationQuestionActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation_questions)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}