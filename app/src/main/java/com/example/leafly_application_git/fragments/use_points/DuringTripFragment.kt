package com.example.leafly_application_git.fragments.use_points

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.saveTheWorld.PlantTreeActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.before_scan_dialog.*
import kotlinx.android.synthetic.main.before_scan_dialog.view.*
import kotlinx.android.synthetic.main.before_scan_dialog.view.imageView_dismiss_scan_dialog
import kotlinx.android.synthetic.main.cupon_dialog.view.*
import kotlinx.android.synthetic.main.fragment_donate.view.*
import kotlinx.android.synthetic.main.fragment_during_trip.view.*
import kotlinx.android.synthetic.main.fragment_miljopoints.view.*
import kotlinx.android.synthetic.main.fragment_shop.view.*
import kotlinx.android.synthetic.main.used_history_row.view.*

class DuringTripFragment : Fragment() {


    internal var user: User? = null
    internal val eatLogo = R.drawable.ic_restaurant_24px
    internal val warmDrinkLogo = R.drawable.ic_local_cafe_24px
    internal val coldDrinkLogo = R.drawable.ic_local_drink_24px

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_during_trip, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()











        root.cardview_coffe.setOnClickListener {

            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 3200
            mDialogView.imageView_scanable_image.setImageResource(warmDrinkLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_coffe)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_coffe_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            val ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }


        root.cardview_wrap.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 8900
            mDialogView.imageView_scanable_image.setImageResource(eatLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_wrap)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_wrap_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }


        root.cardview_tea.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 4200
            mDialogView.imageView_scanable_image.setImageResource(warmDrinkLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_tea)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_tea_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }


        root.cardview_sandwich.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 5200
            mDialogView.imageView_scanable_image.setImageResource(eatLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_sandwich)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_sandwich_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            val ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }

        root.cardview_soda.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 4200
            mDialogView.imageView_scanable_image.setImageResource(coldDrinkLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_soda)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_soda_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            val ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }

        root.cardview_smoothie.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 5200
            mDialogView.imageView_scanable_image.setImageResource(coldDrinkLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_smoothie)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_smoothie_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            val ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }

        root.cardview_falafel.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 13900
            mDialogView.imageView_scanable_image.setImageResource(eatLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_falafel)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_falafel_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            val ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }

        root.cardview_meatcakes.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 4200
            mDialogView.imageView_scanable_image.setImageResource(eatLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_meatcakes)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_meatcakes_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            val ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }


        root.cardview_oumph.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 17900
            mDialogView.imageView_scanable_image.setImageResource(eatLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_oumph)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_oumph_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }


        root.cardview_curry.setOnClickListener {
            val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
                .inflate(R.layout.before_scan_dialog, null)
            val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
                .setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            val getStringRes = activity?.applicationContext?.resources
            val price = 4200
            mDialogView.imageView_scanable_image.setImageResource(eatLogo)
            mDialogView.textView_scanable_title.text = getStringRes?.getString(R.string.during_curry)
            mDialogView.textView_item_desc_scan.text = getStringRes?.getString(R.string.during_curry_desc)
            mDialogView.textView_before_scan_price.text = price.toString()

            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    val balance = user?.balance?.toString()

                    mDialogView.textView_balance_current_before_scan.text = balance
                }

                override fun onCancelled(p0: DatabaseError) {

                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

            mDialogView.imageView_dismiss_scan_dialog.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }



        return root


    }






//    fun scanFromFragment() {
//        IntentIntegrator.forSupportFragment(this).initiateScan();
//    }





}