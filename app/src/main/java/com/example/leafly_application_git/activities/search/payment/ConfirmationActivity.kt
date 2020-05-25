package com.example.leafly_application_git.activities.search.payment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_clean_the_ocean.*
import kotlinx.android.synthetic.main.activity_confirmation.*
import kotlinx.android.synthetic.main.activity_confirmation_recycler.*

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ConfirmationActivity : AppCompatActivity() {

    internal var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_recycler)

        //Shows actionbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val departure: String = intent.getStringExtra(SplashScreenPaymentActivity.FROM_KEY)
        val arrival: String = intent.getStringExtra(SplashScreenPaymentActivity.TO_KEY)
        val departureTime: String = intent.getStringExtra(SplashScreenPaymentActivity.DEPARTURE_TIME_KEY)
        val price: String = intent.getStringExtra(SplashScreenPaymentActivity.PRICE_KEY)
        val points: String = intent.getStringExtra(SplashScreenPaymentActivity.POINTS_KEY)
        dataPassClass(departure ,arrival ,departureTime ,price ,points)

        recycler_view_confirmation.layoutManager = LinearLayoutManager(this)

        if(verifyIfUserIsLoggedIn()){
            incrementBalance(points)
        }
    }

    private fun dataPassClass(departure: String,
                              arrival: String,
                              departureTime: String,
                              price: String,
                              points: String){

        recycler_view_confirmation.adapter = ConfirmationAdapter(departure, arrival, departureTime, price, points)

        runOnUiThread{
            recycler_view_confirmation.adapter =
                ConfirmationAdapter(departure, arrival, departureTime, price, points)
        }
    }

    //Checks with the Firebase Authentication if user is logged in or not
    private fun verifyIfUserIsLoggedIn(): Boolean {
        val uid = FirebaseAuth.getInstance().uid
        //If user is not logged in, then return null
        return uid != null
    }


    //Function to increment the users balance and progress when purchased from Firebase
    private fun incrementBalance(points: String){
        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var balance = user?.balance
                var progress = user?.progress
                var earnedHistory = "Kjøp av billett"

                balance = balance?.plus(points.toInt())
                textView_confirmation_points.text = balance.toString()
                ref.child("/balance").setValue(balance)

                progress = progress?.plus(1)
                ref.child("/progress").setValue(progress)

                var refEarnedHistory = ref.child("/earnedHistory")
                refEarnedHistory.push().setValue(earnedHistory)




            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}