package com.example.leafly_application_git.activities.miljopoints

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.miljopoints.use.DonateActivity
import com.example.leafly_application_git.activities.miljopoints.use.DuringTripActivity
import com.example.leafly_application_git.activities.miljopoints.use.PointShopActivity
import kotlinx.android.synthetic.main.activity_use_points.*

class UsePointsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_points)

        btnToDuringTrip.setOnClickListener {
            toDuringTrip()
        }
        btnToDonate.setOnClickListener {
            toDonate()
        }
        btnToPointShop.setOnClickListener {
            toPointShop()
        }
    }

    fun toDuringTrip() {
        val intent = Intent(this, DuringTripActivity::class.java)
        startActivity(intent)
    }
    fun toDonate() {
        val intent = Intent(this, DonateActivity::class.java)
        startActivity(intent)
    }
    fun toPointShop() {
        val intent = Intent(this, PointShopActivity::class.java)
        startActivity(intent)
    }
}