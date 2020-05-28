package com.example.leafly_application_git.activities.explanation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import kotlinx.android.synthetic.main.activity_explanation_what_are_points.*

class ExplanationActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation_what_are_points)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        cardView_explanation_how_many.setOnClickListener{
            val intent = Intent(this, ExplanationEarningActivity::class.java)
            startActivity(intent)
        }

        cardView_explanation_use_points.setOnClickListener{
            val intent = Intent(this, ExplanationUseActivity::class.java)
            startActivity(intent)
        }

        carView_explanation_spend_on.setOnClickListener{
            val intent = Intent(this, ExplanationQuestionActivity::class.java)
            startActivity(intent)
        }

        cardView_explanation_travel.setOnClickListener{
            val intent = Intent(this, ExplanationTravelActivity::class.java)
            startActivity(intent)
        }

    }
}