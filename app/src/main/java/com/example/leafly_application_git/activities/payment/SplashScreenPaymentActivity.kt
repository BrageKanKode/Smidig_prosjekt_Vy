package com.example.leafly_application_git.activities.payment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SplashScreenPaymentActivity : AppCompatActivity() {

    companion object{
        const val FROM_KEY = "FROM"
        const val TO_KEY = "TO"
        const val DEPARTURE_TIME_KEY = "DEPARTURE"
        const val PRICE_KEY = "PRICE"
        const val POINTS_KEY = "POINTS"
    }

    // This is the loading time of the splash screen
    private val splashTimeOut:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen_payment)

        val departure: String = intent.getStringExtra(ViewOrderDetails.DEPARTURE_KEY)
        val arrival: String = intent.getStringExtra(ViewOrderDetails.ARRIVAL_KEY)
        val departureTime: String = intent.getStringExtra(ViewOrderDetails.DEPARTURE_TIME_KEY)
        val price: String = intent.getStringExtra(ViewOrderDetails.DETAILS_PRICE_KEY)
        val points: String = intent.getStringExtra(ViewOrderDetails.DETAILS_POINTS_KEY)


        val intent = Intent(this, ConfirmationActivity::class.java)

        intent.putExtra(FROM_KEY, departure)
        intent.putExtra(TO_KEY, arrival)
        intent.putExtra(DEPARTURE_TIME_KEY, departureTime)
        intent.putExtra(PRICE_KEY, price)
        intent.putExtra(POINTS_KEY, points)



        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(intent)

            // close this activity
            finish()
        }, splashTimeOut)
    }
}