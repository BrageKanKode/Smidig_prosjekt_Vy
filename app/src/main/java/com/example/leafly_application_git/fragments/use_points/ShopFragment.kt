package com.example.leafly_application_git.fragments.use_points

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.miljopoints.usePoints.PointShopActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_clean_the_ocean.*
import kotlinx.android.synthetic.main.dplay_dialog.*
import kotlinx.android.synthetic.main.dplay_dialog.view.*
import kotlinx.android.synthetic.main.fragment_shop.*
import kotlinx.android.synthetic.main.fragment_shop.view.*
import kotlinx.android.synthetic.main.netflix_dialog.view.*
import kotlinx.android.synthetic.main.odeon_dialog.view.*

class ShopFragment : Fragment() {

    internal var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_shop, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()




        //Buy Dplay button logic
        root.view_shop_item1.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.dplay_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setTitle("Dplay Abbonement")
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val dplayPurchasePrice = 100



            mDialogView.textView_price_dplay.text = "Pris: $dplayPurchasePrice"
            mDialogView.textView_dplay_dialog_dplay.text = "1 måned gratis Dplay"


            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    var progress = user?.progress
                    var usedHistory =
                        "Du kjøpte Dplay abbonement \nFor $dplayPurchasePrice miljøpoeng"
                    mDialogView.textView_currency_dplay.text = "Saldo: " + balance.toString()


                    mDialogView.button_buy_dplay.setOnClickListener {

                        if (balance!! >= dplayPurchasePrice) {
                            balance = balance?.minus(dplayPurchasePrice)
                            mDialogView.textView_currency_dplay.text = balance.toString()
                            ref.child("/balance").setValue(balance)

                            progress = progress?.plus(1)
                            ref.child("/progress").setValue(progress)

                            var refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)

                            Toast.makeText(
                                activity as UsePointsActivity,
                                usedHistory + " Ny saldo: " + balance.toString(),
                                Toast.LENGTH_LONG
                            ).show()

                            mAlertDialog.dismiss()


                        } else {
                            Toast.makeText(
                                activity as UsePointsActivity,
                                "You need more money, fool!",
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
            ///


            mDialogView.button_cancel_dplay_payment.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }




        //Buy Netflix logic
        root.view_shop_item2.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.netflix_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setTitle("Netflix Abbonement")
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val netflixPurchasePrice = 100



            mDialogView.textView_price_netflix.text = "Pris: $netflixPurchasePrice"
            mDialogView.textView_netflix_dialog.text = "1 måned gratis Netflix"


            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    var progress = user?.progress
                    var usedHistory =
                        "Du kjøpte Netflix abbonement \nFor $netflixPurchasePrice miljøpoeng"
                    mDialogView.textView_currency_netflix.text = "Saldo: " + balance.toString()


                    mDialogView.button_buy_netflix.setOnClickListener {

                        if (balance!! >= netflixPurchasePrice) {
                            balance = balance?.minus(netflixPurchasePrice)
                            mDialogView.textView_currency_netflix.text = balance.toString()
                            ref.child("/balance").setValue(balance)

                            progress = progress?.plus(1)
                            ref.child("/progress").setValue(progress)

                            var refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)

                            Toast.makeText(
                                activity as UsePointsActivity,
                                usedHistory + " Ny saldo: " + balance.toString(),
                                Toast.LENGTH_LONG
                            ).show()

                            mAlertDialog.dismiss()


                        } else {
                            Toast.makeText(
                                activity as UsePointsActivity,
                                "You need more money, fool!",
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
            ///


            mDialogView.button_cancel_netflix_payment.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }



        root.view_shop_item3.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.odeon_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setTitle("Odeon - Kinobilett")
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val odeonPurchasePrice = 200



            mDialogView.textView_price_odeon.text = "Pris: $odeonPurchasePrice"
            mDialogView.textView_odeon_dialog.text = "1 Kinobilett"


            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    var progress = user?.progress
                    var usedHistory =
                        "Du kjøpte Odeon kinobilett \nFor $odeonPurchasePrice miljøpoeng"
                    mDialogView.textView_currency_odeon.text = "Saldo: " + balance.toString()


                    mDialogView.button_buy_odeon.setOnClickListener {

                        if (balance!! >= odeonPurchasePrice) {
                            balance = balance?.minus(odeonPurchasePrice)
                            mDialogView.textView_currency_odeon.text = balance.toString()
                            ref.child("/balance").setValue(balance)

                            progress = progress?.plus(1)
                            ref.child("/progress").setValue(progress)

                            var refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)

                            Toast.makeText(
                                activity as UsePointsActivity,
                                usedHistory + " Ny saldo: " + balance.toString(),
                                Toast.LENGTH_LONG
                            ).show()

                            mAlertDialog.dismiss()


                        } else {
                            Toast.makeText(
                                activity as UsePointsActivity,
                                "You need more money, fool!",
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
            ///


            mDialogView.button_cancel_odeon_purchase.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }





    return root
}

}