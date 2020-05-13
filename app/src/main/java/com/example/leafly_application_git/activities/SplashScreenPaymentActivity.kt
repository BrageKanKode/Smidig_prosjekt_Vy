package com.example.leafly_application_git.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R

class SplashScreenPaymentActivity : AppCompatActivity() {

    // This is the loading time of the splash screen
    private val splashTimeOut:Long = 3000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen_payment)


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            startActivity(
                Intent(this,
                    ConfirmationActivity::class.java)
            )

            // close this activity
            finish()
        }, splashTimeOut)
    }
}