package com.example.leafly_application_git.activities.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import kotlinx.android.synthetic.main.activity_travel_order_details.*

class TravelDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_order_details)


        btnToPayment.setOnClickListener {
            openNewActivity()
        }
    }

    private fun openNewActivity() {
        val intent = Intent(this, PaymentActivity::class.java)
        startActivity(intent)
    }
}
