package com.example.leafly_application_git.activities.miljopoints.progression

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_progression.*

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

    //buttons to new activities
    private fun toGrowingTree() {
        val intent = Intent(this, GrowingTreeActivity::class.java)
        startActivity(intent)
    }
    private fun toHistory() {
        val intent = Intent(this, HistoryActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        displayCurrentBalance()
    }


    //Displays users balance and progress from firebase
    //Gets data from firebase
    private fun displayCurrentBalance(){
        val ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                val balance = user?.balance
                val level = user?.level

                var totalEarned = user?.totalEarned.toString()
                var nextLevelInt = 8200
                var remainingPointToLevel = nextLevelInt.minus(totalEarned.toInt())


                textView_current_currency.text = balance.toString()

                textView_progressbar_status.text = user?.progress?.toInt().toString()
                progressBar2.progress = user!!.progress.toInt()
                textView_total_collected.text = totalEarned


                if (level == 1){
                    textView_progression_show_level.text = "Frø"
                } else if (level == 2){
                    textView_progression_show_level.text = "Spire"
                    nextLevelInt = 16400
                    remainingPointToLevel = nextLevelInt.minus(totalEarned.toInt())
                } else {
                    textView_progression_show_level.text = "Tre"
                }

                textView_display_remaining_points_to_next_level.text = "Poeng til neste nivå: \n" + remainingPointToLevel.toString()

                if (level == 3){
                    textView_display_remaining_points_to_next_level.text = "Høyeste nivå!"
                }
            }

            override fun onCancelled(p0: DatabaseError) {
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}

