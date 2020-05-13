package com.example.leafly_application_git.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import kotlinx.android.synthetic.main.activity_payment.*
import kotlinx.android.synthetic.main.activity_travel_details.*

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        btnPay.setOnClickListener {
            openNewActivity()
        }
    }

    fun openNewActivity() {
        val intent = Intent(this, SplashScreenPaymentActivity::class.java)
        startActivity(intent)
    }
}