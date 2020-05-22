package com.example.leafly_application_git.activities.search.payment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafly_application_git.R
import kotlinx.android.synthetic.main.activity_confirmation_recycler.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_recycler)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val departure: String = intent.getStringExtra(SplashScreenPaymentActivity.FROM_KEY)
        val arrival: String = intent.getStringExtra(SplashScreenPaymentActivity.TO_KEY)
        val departureTime: String = intent.getStringExtra(SplashScreenPaymentActivity.DEPARTURE_TIME_KEY)
        val price: String = intent.getStringExtra(SplashScreenPaymentActivity.PRICE_KEY)
        val points: String = intent.getStringExtra(SplashScreenPaymentActivity.POINTS_KEY)
        dataPassClass(departure ,arrival ,departureTime ,price ,points)

        recycler_view_confirmation.layoutManager = LinearLayoutManager(this)
    }

    private fun dataPassClass(departure: String,
                              arrival: String,
                              departureTime: String,
                              price: String,
                              points: String){

        recycler_view_confirmation.adapter = ConfirmationAdapter(departure, arrival, departureTime, price, points)

        runOnUiThread{
            recycler_view_confirmation.adapter =
                ConfirmationAdapter(departure, arrival, departureTime, price, points)
        }
    }
}