package com.example.leafly_application_git.activities.miljopoints.usePoints.saveTheWorld

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_clean_the_ocean.*

class CleanOceanActivity : AppCompatActivity() {

    internal var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clean_the_ocean)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        decrementBalance()


    }


    //Function to increment the users balance and progress when purchased from Firebase
    private fun decrementBalance(){
        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var balance = user?.balance
                var progress = user?.progress
                val cleanOceanPrice = 50
                var usedHistory = "Kjøpt 1L av vannrensing"
                textview_currency_show.text = balance.toString()

                btn_do_clean_ocean.setOnClickListener {
                    if(balance!! >= cleanOceanPrice) {
                    balance = balance?.minus(cleanOceanPrice)
                    textview_currency_show.text = balance.toString()
                    ref.child("/balance").setValue(balance)

                    progress = progress?.plus(1)
                    ref.child("/progress").setValue(progress)

                    var refUsedHistory = ref.child("/usedHistory")
                    refUsedHistory.push().setValue(usedHistory)
                    } else {
                        Toast.makeText(this@CleanOceanActivity, "You need more money, fool!", Toast.LENGTH_LONG).show()
                    }

                }


            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}