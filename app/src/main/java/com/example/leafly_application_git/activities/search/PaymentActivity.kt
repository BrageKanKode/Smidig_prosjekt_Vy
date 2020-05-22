package com.example.leafly_application_git.activities.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.payment.SplashScreenPaymentActivity
import kotlinx.android.synthetic.main.activity_payment.*

class PaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnPay.setOnClickListener {
            openNewActivity()
        }
    }

    fun openNewActivity() {
        val intent = Intent(this, SplashScreenPaymentActivity::class.java)
        startActivity(intent)
    }
}