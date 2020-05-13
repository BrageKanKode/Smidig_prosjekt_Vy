package com.example.leafly_application_git.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import kotlinx.android.synthetic.main.activity_confirmation.*

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)


        btnToHome.setOnClickListener {
            openNewActivity()
        }
    }

    fun openNewActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}