package com.example.leafly_application_git.activities.search.payment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leafly_application_git.CombinedFunctionsClass
import com.example.leafly_application_git.R
import com.example.leafly_application_git.CombinedFunctionsClass.incrementProgress
import com.example.leafly_application_git.activities.authentication.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_confirmation_recycler.*
import kotlinx.android.synthetic.main.level_up_dialog.view.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
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
        val points: String = intent.getStringExtra(SplashScreenPaymentActivity.POINTS_KEY)
        dataPassClass(departure ,arrival ,departureTime, points)


        recycler_view_confirmation.layoutManager = LinearLayoutManager(this)

        if(verifyIfUserIsLoggedIn()){
            incrementBalance(points)
        }

    }

    private fun dataPassClass(departure: String,
                              arrival: String,
                              departureTime: String,
                              points: String){

        recycler_view_confirmation.adapter = ConfirmationAdapter(departure, arrival, departureTime, points)

        runOnUiThread{
            recycler_view_confirmation.adapter =
                ConfirmationAdapter(departure, arrival, departureTime, points)
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
            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var balance = user?.balance
                var totalEarned = user?.totalEarned
                val earnedHistory = "Kjøp av billett - fra ${intent.getStringExtra(SplashScreenPaymentActivity.FROM_KEY)} til ${intent.getStringExtra(SplashScreenPaymentActivity.TO_KEY)} \nMottok ${intent.getStringExtra(SplashScreenPaymentActivity.POINTS_KEY)} miljøpoeng"

                balance = balance?.plus(points.toInt())
                totalEarned = totalEarned?.plus(points.toInt())


                ref.child("/balance").setValue(balance)
                ref.child("/totalEarned").setValue(totalEarned)
                CombinedFunctionsClass.user = p0.getValue(User::class.java)
                var progress = CombinedFunctionsClass.user?.progress
                var level = CombinedFunctionsClass.user?.level
                val maxXp = 8200
                val progressAmount = intent.getStringExtra(SplashScreenPaymentActivity.POINTS_KEY).toDouble()

                incrementProgress(progressAmount)


                val convertedToPercent = progressAmount.div(maxXp) * 100.00

                if (level!! < 3) {
                    if (progress!! + convertedToPercent >= 100.00) {
                        val mDialogView = LayoutInflater.from(this@ConfirmationActivity)
                            .inflate(R.layout.level_up_dialog, null)
                        val mBuilder = AlertDialog.Builder(this@ConfirmationActivity)
                            .setView(mDialogView)

                        if (level == 1){
                            mDialogView.textView_level_up_congratulations.text = "Du har nådd et nytt nivå!\n Ditt nye nivå: Spire"
                        } else if (level == 2){
                            mDialogView.textView_level_up_congratulations.text = "Du har nådd et nytt nivå!\n Ditt nye nivå: Tre"
                        }


                        val mAlertDialog = mBuilder.show()

                        mDialogView.imageView_close_level_up.setOnClickListener {
                            mAlertDialog.dismiss()
                        }
                    }
                }







                val refEarnedHistory = ref.child("/earnedHistory")
                refEarnedHistory.push().setValue(earnedHistory)

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}