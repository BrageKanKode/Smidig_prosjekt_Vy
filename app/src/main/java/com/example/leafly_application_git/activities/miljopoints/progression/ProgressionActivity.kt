package com.example.leafly_application_git.activities.miljopoints.progression

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.storage.MyPreference
import kotlinx.android.synthetic.main.activity_progression.*

class ProgressionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progression)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        btnToGrowingTree.setOnClickListener {
            toGrowingTree()
        }
        btnToHistory.setOnClickListener {
            toHistory()
        }


    }

    fun toGrowingTree() {
        val intent = Intent(this, GrowingTreeActivity::class.java)
        startActivity(intent)
    }
    fun toHistory() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        val mypreference = MyPreference(this)

        val progress = mypreference.getProgress()
        val total = mypreference.getTotalCollected()
        var currency = mypreference.getCurrency()

        textView_total_collected.text = total.toString()
        textView_current_currency.text = currency.toString()

        progressBar2.incrementProgressBy(progress)
    }
}
