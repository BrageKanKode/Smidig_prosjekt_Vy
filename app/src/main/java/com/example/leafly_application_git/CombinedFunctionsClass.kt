package com.example.leafly_application_git


import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_confirmation_v_2.view.*
import kotlinx.android.synthetic.main.activity_travel_detail.view.*
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.*
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.textView_points
import kotlinx.android.synthetic.main.list_choose_time_row.view.*
import kotlinx.android.synthetic.main.list_choose_trip_place_row.view.*


object CombinedFunctionsClass {

    internal var user: User? = null

   internal var leveledUp: Boolean = false



    fun incrementProgress(progressAmount : Double) {
    var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var progress = user?.progress
                var level = user?.level
                val maxXp = 8200
                leveledUp = false

                val convertedToPercent = progressAmount.div(maxXp) * 100.00

                if (level!! < 3) {
                    if (progress!! + convertedToPercent >= 100.00) {


                        progress = progress.plus(convertedToPercent)
                        progress = progress.minus(100)
                        level = level.plus(1)
                        leveledUp = true





                    } else {
                        progress = progress.plus(convertedToPercent)
                    }

                    ref.child("/level").setValue(level)
                    ref.child("/progress").setValue(progress)

                }

                if(level == 3) {
                    progress = 100.00
                    ref.child("/progress").setValue(progress)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        ref.addListenerForSingleValueEvent(menuListener)
    }

    fun removeStuff(view: View, activity: String) {
        if(activity == "SelectTime"){
            view.cardView2.visibility = View.GONE
            view.textView_points.visibility = View.GONE
            view.imageView_row_leaf.visibility = View.GONE

        } else if(activity == "OrderDetails") {
            view.cardView_confirmation.visibility = View.GONE
            view.textView_points.visibility = View.GONE
            view.imageView_travel_order_leaf.visibility = View.GONE
            view.imageView_pay_with_points.visibility = View.GONE
            view.imageView_travel_order_leaf_box.visibility = View.GONE
            view.textView_travel_order_point_text.visibility = View.GONE

        } else if(activity == "SelectTravel") {
            view.textView_choose_trip_point.visibility = View.GONE
            view.cardView.visibility = View.GONE
            view.imageView_choose_trip_leaf.visibility = View.GONE

        } else if(activity == "TravelDetails") {
            view.textView_details_points.visibility = View.GONE
            view.imageView_detail_leaf_green.visibility = View.GONE
            view.cardView5.visibility = View.GONE
        } else if (activity == "Confirmation") {
            view.imageView_confirmation_leaf.visibility = View.GONE
            view.textView_confirmation_points_new.visibility = View.GONE
            view.textView_points_text.visibility = View.GONE
        }


    }

    fun leveledUp(): Boolean{
        return leveledUp
    }

    //Checks with the Firebase Authentication if user is logged in or not
    fun verifyIfUserIsLoggedIn(): Boolean {
        val uid = FirebaseAuth.getInstance().uid
        //If user is not logged in, then return null
        return uid != null
    }
}