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
import kotlinx.android.synthetic.main.activity_plant_a_tree.*

class PlantTreeActivity : AppCompatActivity() {

    internal var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant_a_tree)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        decrementBalance()

//
    }


    private fun decrementBalance(){
        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var balance = user?.balance
                var progress = user?.progress
                val treePrice = 50
                textview_currency_show.text = balance.toString()

                btn_do_plant_tree.setOnClickListener {

                    if(balance!! >= treePrice){
                    balance = balance?.minus(50)
                    textview_currency_show.text = balance.toString()
                    ref.child("/balance").setValue(balance)

                    progress = progress?.plus(1)
                    ref.child("/progress").setValue(progress)
                    } else {
                        Toast.makeText(this@PlantTreeActivity, "You need more more money fool", Toast.LENGTH_LONG).show()
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
