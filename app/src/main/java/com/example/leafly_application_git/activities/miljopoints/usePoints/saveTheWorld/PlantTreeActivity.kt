package com.example.leafly_application_git.activities.miljopoints.usePoints.saveTheWorld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.storage.MyPreference
import kotlinx.android.synthetic.main.activity_plant_a_tree.*

class PlantTreeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_a_tree)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val mypreference = MyPreference(this)
        var currency = mypreference.getCurrency()

        textview_currency_show.text = currency.toString()

        btn_do_plant_tree.setOnClickListener {
            currency -= 50
            mypreference.setCurrency(currency)
        textview_currency_show.text = currency.toString()
        }
    }
}
