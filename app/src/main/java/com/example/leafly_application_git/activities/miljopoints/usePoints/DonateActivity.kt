package com.example.leafly_application_git.activities.miljopoints.usePoints

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.miljopoints.usePoints.saveTheWorld.CleanOceanActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.saveTheWorld.PlantTreeActivity
import kotlinx.android.synthetic.main.activity_donate.*

class DonateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donate)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnToPlantATree.setOnClickListener {
            toPlantATree()
        }
        btnToCleanOcean.setOnClickListener {
            toCleanOcean()
        }
    }

    fun toPlantATree() {
        val intent = Intent(this, PlantTreeActivity::class.java)
        startActivity(intent)
    }
    fun toCleanOcean() {
        val intent = Intent(this, CleanOceanActivity::class.java)
        startActivity(intent)
    }
}