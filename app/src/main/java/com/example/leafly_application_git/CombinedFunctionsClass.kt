package com.example.leafly_application_git


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.example.leafly_application_git.activities.MainActivity
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.miljopoints.progression.HistoryActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_confirmation_v_2.view.*
import kotlinx.android.synthetic.main.activity_travel_detail.view.*
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.*
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.textView_points
import kotlinx.android.synthetic.main.before_scan_dialog.view.*
import kotlinx.android.synthetic.main.list_choose_time_row.view.*
import kotlinx.android.synthetic.main.list_choose_trip_place_row.view.*
import kotlinx.android.synthetic.main.purchase_done_dialog.view.*


object CombinedFunctionsClass {

    internal var user: User? = null

   internal var leveledUp: Boolean = false



    fun incrementProgress(progressAmount : Double) {
    val ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var progress = user?.progress
                var level = user?.level
                val maxXp = 300
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

    fun createPopup(price: Int, logo: Int, header: String?, desc: String?, historyName: String, context: Context) {

        //Firebase reference
        val ref
                = FirebaseDatabase.getInstance().getReference("/users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)

        val btnText = "Betal"

        //Inflate popup
        var mDialogView = LayoutInflater.from(context)
            .inflate(R.layout.before_scan_dialog, null)
        var mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
        val mAlertDialog2 = mBuilder.show()

        val usedHistory =
            "Du kjøpte 1 $historyName på togturen"

        mDialogView.imageView_scanable_image.setImageResource(logo)
        mDialogView.textView_scanable_title.text = header
        mDialogView.textView_item_desc_scan.text = desc
        mDialogView.textView_before_scan_price.text = price.toString()
        mDialogView.button_scan_dialog.text = btnText




        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var balance = CombinedFunctionsClass.user?.balance

                //Set users balance on screen
                mDialogView.textView_balance_current_before_scan.text = balance?.toString()


                mDialogView.button_scan_dialog.setOnClickListener {
                    mAlertDialog2.dismiss()
                    if(balance!! >= price) {
                        mDialogView = LayoutInflater.from(context)
                            .inflate(R.layout.purchase_done_dialog, null)
                        mBuilder = AlertDialog.Builder(context)
                            .setView(mDialogView)
                        val mAlertDialog = mBuilder.show()

                        mDialogView.button_purchase_view_cupon.setOnClickListener {
                            val intent = Intent (context, HistoryActivity::class.java)
                            context.startActivity(intent)
                        }



                        mDialogView.button_purchase_keep_shopping_shop.setOnClickListener {
                            mAlertDialog.dismiss()
                        }

                        mDialogView.imageView_close_purchase_dialog.setOnClickListener {
                            mAlertDialog.dismiss()
                        }

                        var refUsedHistory = ref.child("/usedHistory")
                        refUsedHistory.push().setValue(usedHistory)

                        balance = balance?.minus(price)
                        ref.child("/balance").setValue(balance)
                    } else {
                        Toast.makeText(context, "Not enough points to do that", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }

    //Checks with the Firebase Authentication if user is logged in or not
    fun verifyIfUserIsLoggedIn(): Boolean {
        val uid = FirebaseAuth.getInstance().uid
        //If user is not logged in, then return null
        return uid != null
    }
}