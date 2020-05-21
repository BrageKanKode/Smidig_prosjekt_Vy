package com.example.leafly_application_git.activities.miljopoints.usePoints.saveTheWorld

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
import kotlinx.android.synthetic.main.fragment_miljopoints.*

class CleanOceanActivity : AppCompatActivity() {

    internal var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clean_the_ocean)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        incrementBalance()


//        val mypreference = MyPreference(this)
//        var currency = mypreference.getCurrency()
//
//
//        var progress = mypreference.getProgress()
//
//        textview_currency_show.text = currency.toString()
//
//
//        btn_do_clean_ocean.setOnClickListener {
//            currency += 50
//            progress += 3
//            mypreference.setCurrency(currency)
//            textview_currency_show.text = currency.toString()
//            mypreference.setProgress(progress)
//        }
    }


    private fun incrementBalance(){
        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var balance = user?.balance
                textview_currency_show.text = balance.toString()

                btn_do_clean_ocean.setOnClickListener {
                    balance = balance?.plus(50)
                    textview_currency_show.text = balance.toString()
                    ref.child("/balance").setValue(balance)
                }


            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}