package com.example.leafly_application_git.fragments.use_points

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.miljopoints.progression.HistoryActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.dplay_dialog.view.*
import kotlinx.android.synthetic.main.fragment_shop.view.*
import kotlinx.android.synthetic.main.narvesen_dialog.view.*
import kotlinx.android.synthetic.main.netflix_dialog.view.*
import kotlinx.android.synthetic.main.odeon_dialog.view.*
import kotlinx.android.synthetic.main.purchase_done_dialog.view.*
import kotlinx.android.synthetic.main.purchase_done_dialog.view.imageView_close_purchase_dialog
import kotlinx.android.synthetic.main.starbucks_dialog.view.*
import kotlinx.android.synthetic.main.voi_dialog.view.*

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




        //Buy Viaplay button logic
        root.view_shop_item1.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.dplay_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val dplayPurchasePrice = 500

            mDialogView.textView_price_dplay.text = "Pris: $dplayPurchasePrice"
            mDialogView.textView_dplay_dialog_dplay.text = "Leie av 1 film på Viaplay"

            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    var usedHistory =
                        "Du kjøpte 1 film på Viaplay"
                    mDialogView.textView_currency_dplay.text = "Saldo: " + balance.toString()


                    mDialogView.button_buy_dplay.setText("Kjøp for $dplayPurchasePrice poeng")
                    mDialogView.button_buy_dplay.setOnClickListener {


                        if (balance!! >= dplayPurchasePrice) {
                            balance = balance?.minus(dplayPurchasePrice)
                            mDialogView.textView_currency_dplay.text = balance.toString()
                            ref.child("/balance").setValue(balance)


                            var refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)


                            mAlertDialog.dismiss()



                            //Opens new alertdialog with confirmation of purchase
                            val mDialogView2 = LayoutInflater.from(activity as UsePointsActivity)
                                .inflate(R.layout.purchase_done_dialog, null)
                            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                                .setView(mDialogView2)

                            mDialogView2.textView_thanks_for_purchase_info.text = usedHistory

                            mDialogView2.button_purchase_view_cupon.setOnClickListener {
                                requireActivity().startActivity(Intent(requireActivity(), HistoryActivity::class.java))
                            }


                            val mAlertDialog = mBuilder.show()

                            mDialogView2.button_purchase_keep_shopping_shop.setOnClickListener {
                                mAlertDialog.dismiss()
                            }

                            mDialogView2.imageView_close_purchase_dialog.setOnClickListener {
                                mAlertDialog.dismiss()
                            }


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

            mDialogView.imageView_dplay_close.setOnClickListener {
                mAlertDialog.dismiss()
            }




        }




        //Buy Netflix logic
        root.view_shop_item2.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.netflix_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val netflixPurchasePrice = 3300



            mDialogView.textView_price_netflix.text = "Pris: $netflixPurchasePrice"
            mDialogView.textView_netflix_dialog.text = "3 måneder gratis Netflix"


            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    val usedHistory =
                        "Du kjøpte 3 måneder med Netflix"
                    mDialogView.textView_currency_netflix.text = "Saldo: " + balance.toString()


                    mDialogView.button_buy_netflix.setText("Kjøp for $netflixPurchasePrice poeng")
                    mDialogView.button_buy_netflix.setOnClickListener {

                        if (balance!! >= netflixPurchasePrice) {
                            balance = balance?.minus(netflixPurchasePrice)
                            mDialogView.textView_currency_netflix.text = balance.toString()
                            ref.child("/balance").setValue(balance)


                            var refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)


                            mAlertDialog.dismiss()

                            //Opens new alertdialog with confirmation of purchase
                            val mDialogView2 = LayoutInflater.from(activity as UsePointsActivity)
                                .inflate(R.layout.purchase_done_dialog, null)
                            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                                .setView(mDialogView2)

                            mDialogView2.textView_thanks_for_purchase_info.text = usedHistory

                            mDialogView2.button_purchase_view_cupon.setOnClickListener {
                                requireActivity().startActivity(Intent(requireActivity(), HistoryActivity::class.java))
                            }


                            val mAlertDialog = mBuilder.show()

                            mDialogView2.button_purchase_keep_shopping_shop.setOnClickListener {
                                mAlertDialog.dismiss()
                            }

                            mDialogView2.imageView_close_purchase_dialog.setOnClickListener {
                                mAlertDialog.dismiss()
                            }


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

            mDialogView.imageView_netflix_close.setOnClickListener {
                mAlertDialog.dismiss()
            }


        }



        //Logic to buy Odeon
        root.view_shop_item3.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.odeon_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val odeonPurchasePrice = 1350



            mDialogView.textView_price_odeon.text = "Pris: $odeonPurchasePrice"
            mDialogView.textView_odeon_dialog.text = "Få 2 billetter til prisen av 1"


            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    var usedHistory =
                        "Du kjøpte Odeon 2-for-1 kinobilett"
                    mDialogView.textView_currency_odeon.text = "Saldo: " + balance.toString()


                    mDialogView.button_buy_odeon.setText("Kjøp for $odeonPurchasePrice poeng")
                    mDialogView.button_buy_odeon.setOnClickListener {

                        if (balance!! >= odeonPurchasePrice) {
                            balance = balance?.minus(odeonPurchasePrice)
                            mDialogView.textView_currency_odeon.text = balance.toString()
                            ref.child("/balance").setValue(balance)


                            var refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)


                            mAlertDialog.dismiss()

                            //Opens new alertdialog with confirmation of purchase
                            val mDialogView2 = LayoutInflater.from(activity as UsePointsActivity)
                                .inflate(R.layout.purchase_done_dialog, null)
                            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                                .setView(mDialogView2)

                            mDialogView2.textView_thanks_for_purchase_info.text = usedHistory

                            mDialogView2.button_purchase_view_cupon.setOnClickListener {
                                requireActivity().startActivity(Intent(requireActivity(), HistoryActivity::class.java))
                            }


                            val mAlertDialog = mBuilder.show()

                            mDialogView2.button_purchase_keep_shopping_shop.setOnClickListener {
                                mAlertDialog.dismiss()
                            }

                            mDialogView2.imageView_close_purchase_dialog.setOnClickListener {
                                mAlertDialog.dismiss()
                            }


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


            mDialogView.imageView_odeon_close.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }




        //logic to buy narvesen product
        root.view_shop_item4.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.narvesen_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val narvesenPurchasePrice = 350



            mDialogView.textview_price_narvesen.text = "Pris: $narvesenPurchasePrice"
            mDialogView.textview_narvesen_dialog.text = "Få en gratis pølse fra en Narvesen"


            val ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    val usedHistory =
                        "Du fikk gratis pølse fra Narvesen"
                    mDialogView.textview_currency_narvesen.text = "Saldo: " + balance.toString()


                    mDialogView.button_buy_narvsesen.setText("Kjøp for $narvesenPurchasePrice poeng")
                    mDialogView.button_buy_narvsesen.setOnClickListener {

                        if (balance!! >= narvesenPurchasePrice) {
                            balance = balance?.minus(narvesenPurchasePrice)
                            mDialogView.textview_currency_narvesen.text = balance.toString()
                            ref.child("/balance").setValue(balance)


                            val refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)


                            mAlertDialog.dismiss()

                            //Opens new alertdialog with confirmation of purchase
                            val mDialogView2 = LayoutInflater.from(activity as UsePointsActivity)
                                .inflate(R.layout.purchase_done_dialog, null)
                            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                                .setView(mDialogView2)

                            mDialogView2.textView_thanks_for_purchase_info.text = usedHistory

                            mDialogView2.button_purchase_view_cupon.setOnClickListener {
                                requireActivity().startActivity(Intent(requireActivity(), HistoryActivity::class.java))
                            }


                            val mAlertDialog = mBuilder.show()

                            mDialogView2.button_purchase_keep_shopping_shop.setOnClickListener {
                                mAlertDialog.dismiss()
                            }

                            mDialogView2.imageView_close_purchase_dialog.setOnClickListener {
                                mAlertDialog.dismiss()
                            }


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


            mDialogView.imageView_narvesen_close.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }



        //Logic to purchase starbucks item
        root.view_shop_item5.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.starbucks_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val starbucksPurchasePrice = 200



            mDialogView.textview_price_starbucks.text = "Pris: $starbucksPurchasePrice"
            mDialogView.textview_starbucks_dialog.text = "Velg en valgfri kaffe fra Starbucks"


            val ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    val usedHistory =
                        "Du valgte en gratis, valgfri kaffe fra Starbucks"
                    mDialogView.textview_currency_starbucks.text = "Saldo: " + balance.toString()


                    mDialogView.button_buy_starbucks.setText("Kjøp for $starbucksPurchasePrice poeng")
                    mDialogView.button_buy_starbucks.setOnClickListener {

                        if (balance!! >= starbucksPurchasePrice) {
                            balance = balance?.minus(starbucksPurchasePrice)
                            mDialogView.textview_currency_starbucks.text = balance.toString()
                            ref.child("/balance").setValue(balance)

                            val refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)


                            mAlertDialog.dismiss()

                            //Opens new alertdialog with confirmation of purchase
                            val mDialogView2 = LayoutInflater.from(activity as UsePointsActivity)
                                .inflate(R.layout.purchase_done_dialog, null)
                            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                                .setView(mDialogView2)

                            mDialogView2.textView_thanks_for_purchase_info.text = usedHistory

                            mDialogView2.button_purchase_view_cupon.setOnClickListener {
                                requireActivity().startActivity(Intent(requireActivity(), HistoryActivity::class.java))
                            }


                            val mAlertDialog = mBuilder.show()

                            mDialogView2.button_purchase_keep_shopping_shop.setOnClickListener {
                                mAlertDialog.dismiss()
                            }

                            mDialogView2.imageView_close_purchase_dialog.setOnClickListener {
                                mAlertDialog.dismiss()
                            }


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


            mDialogView.imageView_close_starbucks.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }



        //Logic for purchasing voi item
        root.view_shop_item6.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.voi_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val voiPurchasePrice = 100



            mDialogView.textview_price_voi.text = "Pris: $voiPurchasePrice"
            mDialogView.textview_voi_dialog.text = "Ingen start-avgift for en Voi-tur"


            val ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    val usedHistory =
                        "Ingen start-avgift for en Voi tur"
                    mDialogView.textview_voi_currency.text = "Saldo: " + balance.toString()


                    mDialogView.button_buy_voi.setText("Kjøp for $voiPurchasePrice poeng")
                    mDialogView.button_buy_voi.setOnClickListener {

                        if (balance!! >= voiPurchasePrice) {
                            balance = balance?.minus(voiPurchasePrice)
                            mDialogView.textview_voi_currency.text = balance.toString()
                            ref.child("/balance").setValue(balance)


                            val refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)


                            mAlertDialog.dismiss()

                            //Opens new alertdialog with confirmation of purchase
                            val mDialogView2 = LayoutInflater.from(activity as UsePointsActivity)
                                .inflate(R.layout.purchase_done_dialog, null)
                            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                                .setView(mDialogView2)

                            mDialogView2.textView_thanks_for_purchase_info.text = usedHistory

                            mDialogView2.button_purchase_view_cupon.setOnClickListener {
                                requireActivity().startActivity(Intent(requireActivity(), HistoryActivity::class.java))
                            }


                            val mAlertDialog = mBuilder.show()

                            mDialogView2.button_purchase_keep_shopping_shop.setOnClickListener {
                                mAlertDialog.dismiss()
                            }

                            mDialogView2.imageView_close_purchase_dialog.setOnClickListener {
                                mAlertDialog.dismiss()
                            }


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


            mDialogView.imageView_close_voi.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }


    return root
}

}