package com.example.leafly_application_git.activities.miljopoints.usePoints.saveTheWorld

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.miljopoints.progression.HistoryActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_plant_a_tree.*
import kotlinx.android.synthetic.main.purchase_done_dialog.view.*
import kotlinx.android.synthetic.main.purchase_tree_confirmation.view.*

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


    //Function to decrement balance and increment progress when purchased from Firebase
    private fun decrementBalance() {
        var ref = FirebaseDatabase.getInstance().getReference("/users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                var balance = user?.balance
                val treePrice = 50
                var treeAmount = 1
                var totalTreeSum = treePrice

                textview_currency_show.text = balance.toString()



                textView_plant_tree_amount.text = treeAmount.toString()
                textView_total_tree_sum.text = totalTreeSum.toString()

                btn_plant_tree_minus.setOnClickListener {
                    if(treeAmount > 1) {
                        treeAmount--
                        textView_plant_tree_amount.text = treeAmount.toString()
                        totalTreeSum -= treePrice
                        textView_total_tree_sum.text = totalTreeSum.toString()
                    }
                }

                btn_plant_tree_plus.setOnClickListener {
                    if(balance!! >= totalTreeSum + treePrice){
                        treeAmount++
                        textView_plant_tree_amount.text = treeAmount.toString()
                        totalTreeSum += treePrice
                        textView_total_tree_sum.text = totalTreeSum.toString()
                    }
                }



                btn_do_plant_tree.setOnClickListener {

                    if (balance!! >= totalTreeSum) {
                        balance = balance?.minus(totalTreeSum)
                        textview_currency_show.text = balance.toString()
                        ref.child("/balance").setValue(balance)



                        var usedHistory = "Du har reddet $treeAmount antall tre! \nFor $totalTreeSum miljøpoeng"

                        val refUsedHistory = ref.child("/usedHistory")
                        refUsedHistory.push().setValue(usedHistory)




                        //treeAmount = 1
                        totalTreeSum = treePrice
                        textView_plant_tree_amount.text = treeAmount.toString()
                        textView_total_tree_sum.text = totalTreeSum.toString()

                        //Opens new alertdialog with confirmation of purchase
                        val mDialogView2 = LayoutInflater.from(this@PlantTreeActivity)
                            .inflate(R.layout.purchase_tree_confirmation, null)
                        val mBuilder = AlertDialog.Builder(this@PlantTreeActivity)
                            .setView(mDialogView2)

                        mDialogView2.textView4_thanks_tree_info.text = "Gratulerer! Du har plantet $treeAmount antall trær!"

                        val mAlertDialog = mBuilder.show()

                        mDialogView2.button_see_my_purchases_tree.setOnClickListener {
                            val intent = Intent(this@PlantTreeActivity, HistoryActivity::class.java)
                            startActivity(intent)
                        }

                        mDialogView2.button2_keep_shopping.setOnClickListener {
                            mAlertDialog.dismiss()
                        }

                        mDialogView2.imageView_close_confirmation_tree.setOnClickListener {
                            mAlertDialog.dismiss()
                        }



                    } else {
                        Toast.makeText(
                            this@PlantTreeActivity,
                            "You need more more money, fool!",
                            Toast.LENGTH_LONG
                        ).show()
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
