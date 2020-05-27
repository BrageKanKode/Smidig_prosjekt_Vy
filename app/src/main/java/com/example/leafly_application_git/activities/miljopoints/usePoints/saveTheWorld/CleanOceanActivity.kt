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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_clean_the_ocean.*
import kotlinx.android.synthetic.main.activity_clean_the_ocean.textview_currency_show
import kotlinx.android.synthetic.main.purchase_confirmation_clean_ocean_dialog.view.*
import kotlinx.android.synthetic.main.purchase_tree_confirmation.view.*

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
                val cleanOceanPrice = 50
                var cleanAmount = 1
                var totalCleanSum = cleanOceanPrice
                textview_currency_show.text = balance.toString()


                textView_clean_ocean_amount.text = cleanAmount.toString()
                textView_total_ocean_sum.text = totalCleanSum.toString()

                btn_clean_ocean_btn_minus.setOnClickListener {
                    if(cleanAmount > 1) {
                        cleanAmount--
                        textView_clean_ocean_amount.text = cleanAmount.toString()
                        totalCleanSum -= cleanOceanPrice
                        textView_total_ocean_sum.text = totalCleanSum.toString()
                    }
                }

                btn_clean_ocean_btn_plus.setOnClickListener {
                    if(balance!! >= totalCleanSum + cleanOceanPrice){
                        cleanAmount++
                        textView_clean_ocean_amount.text = cleanAmount.toString()
                        totalCleanSum += cleanOceanPrice
                        textView_total_ocean_sum.text = totalCleanSum.toString()
                    }
                }


                btn_do_clean_ocean.setOnClickListener {
                    if(balance!! >= cleanOceanPrice) {
                        balance = balance?.minus(totalCleanSum)
                        textview_currency_show.text = balance.toString()
                        ref.child("/balance").setValue(balance)

                        var usedHistory = "Kjøpt $cleanAmount L av vannrensing \nFor $cleanOceanPrice miljøpoeng"

                        val refUsedHistory = ref.child("/usedHistory")
                        refUsedHistory.push().setValue(usedHistory)


                        //cleanAmount = 1
                        totalCleanSum = cleanOceanPrice
                        textView_clean_ocean_amount.text = cleanAmount.toString()
                        textView_total_ocean_sum.text = totalCleanSum.toString()


                        //Opens new alertdialog with confirmation of purchase
                        val mDialogView2 = LayoutInflater.from(this@CleanOceanActivity)
                            .inflate(R.layout.purchase_confirmation_clean_ocean_dialog, null)
                        val mBuilder = AlertDialog.Builder(this@CleanOceanActivity)
                            .setView(mDialogView2)

                        mDialogView2.textView_confirmation_ocean_info.text = "Gratulerer! Du har ryddet $cleanAmount kg fra havet!"

                        val mAlertDialog = mBuilder.show()

                        mDialogView2.button_redirect_to_purchase_ocean.setOnClickListener {
                            val intent = Intent(this@CleanOceanActivity, HistoryActivity::class.java)
                            startActivity(intent)
                        }

                        mDialogView2.button_keep_shopping_ocean.setOnClickListener {
                            mAlertDialog.dismiss()
                        }

                        mDialogView2.imageView_ocean_confirmation_close.setOnClickListener {
                            mAlertDialog.dismiss()
                        }


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