package com.example.leafly_application_git.activities.miljopoints.progression

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.storage.MyPreference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_clean_the_ocean.*
import kotlinx.android.synthetic.main.activity_progression.*
import kotlinx.android.synthetic.main.activity_progression.textView_total_collected
import kotlinx.android.synthetic.main.fragment_miljopoints.*

class ProgressionActivity : AppCompatActivity() {

    internal var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progression)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        displayCurrentBalance()


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

        displayCurrentBalance()

    }


    private fun displayCurrentBalance(){
        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var balance = user?.balance
                textView_current_currency.text = balance.toString()

                textView_progressbar_status.text = user?.progress.toString()
                progressBar2.setProgress(user!!.progress)
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}
