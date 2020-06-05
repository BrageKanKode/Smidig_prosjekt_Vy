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
import com.example.leafly_application_git.CombinedFunctionsClass
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.MainActivity
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_use_points.*
import kotlinx.android.synthetic.main.before_scan_dialog.view.*
import kotlinx.android.synthetic.main.before_scan_dialog.view.imageView_dismiss_scan_dialog
import kotlinx.android.synthetic.main.fragment_during_trip.view.*

class DuringTripFragment : Fragment() {


    internal var user: User? = null
    private val eatLogo = R.drawable.ic_restaurant_24px
    private val warmDrinkLogo = R.drawable.ic_local_cafe_24px
    private val coldDrinkLogo = R.drawable.ic_local_drink_24px


    private fun createPopup(price: Int, logo: Int, header: String?, desc: String?) {
        val mDialogView = LayoutInflater.from(activity as UsePointsActivity)
            .inflate(R.layout.before_scan_dialog, null)
        val mBuilder = AlertDialog.Builder(activity as UsePointsActivity)
            .setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        mDialogView.imageView_scanable_image.setImageResource(logo)
        mDialogView.textView_scanable_title.text = header
        mDialogView.textView_item_desc_scan.text = desc
        mDialogView.textView_before_scan_price.text = price.toString()




        mDialogView.button_scan_dialog.setOnClickListener {
            scanFromFragment()
            mAlertDialog.dismiss()
        }

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_during_trip, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()


        root.cardview_coffe.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(320, warmDrinkLogo, getStringRes?.getString(R.string.during_coffe), getStringRes?.getString(R.string.during_coffe_desc))
        }


        root.cardview_wrap.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(890, eatLogo, getStringRes?.getString(R.string.during_wrap), getStringRes?.getString(R.string.during_wrap_desc))
        }


        root.cardview_tea.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(420, warmDrinkLogo, getStringRes?.getString(R.string.during_tea), getStringRes?.getString(R.string.during_tea_desc))
        }


        root.cardview_sandwich.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(520, eatLogo, getStringRes?.getString(R.string.during_sandwich), getStringRes?.getString(R.string.during_sandwich_desc))
        }

        root.cardview_soda.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(420, coldDrinkLogo, getStringRes?.getString(R.string.during_soda), getStringRes?.getString(R.string.during_soda_desc))
        }

        root.cardview_smoothie.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(520, coldDrinkLogo, getStringRes?.getString(R.string.during_smoothie), getStringRes?.getString(R.string.during_smoothie_desc))
        }

        root.cardview_falafel.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(1390, eatLogo, getStringRes?.getString(R.string.during_falafel), getStringRes?.getString(R.string.during_falafel_desc))
        }

        root.cardview_meatcakes.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(1690, eatLogo, getStringRes?.getString(R.string.during_meatcakes), getStringRes?.getString(R.string.during_meatcakes_desc))
        }


        root.cardview_oumph.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(1790, eatLogo, getStringRes?.getString(R.string.during_oumph), getStringRes?.getString(R.string.during_oumph_desc))
        }


        root.cardview_curry.setOnClickListener {
            val getStringRes = activity?.applicationContext?.resources
            createPopup(1790, eatLogo, getStringRes?.getString(R.string.during_curry), getStringRes?.getString(R.string.during_curry_desc))
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val eatLogo = R.drawable.ic_restaurant_24px
        val warmDrinkLogo = R.drawable.ic_local_cafe_24px
        val coldDrinkLogo = R.drawable.ic_local_drink_24px

        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        //If it doesn't recognise a QR code
        if(intentResult != null) {
            //If the content of the QR code has anything
            if(intentResult.contents != null) {
                //If the QR code has a text content of
                when (intentResult.contents) {
                    "Kaffe" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            320,
                            warmDrinkLogo,
                            getStringRes?.getString(R.string.during_coffe),
                            getStringRes?.getString(R.string.during_coffe_desc),
                            "kaffe",
                            activity as UsePointsActivity
                        )
                    }

                    "Wrap" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            890,
                            eatLogo,
                            getStringRes?.getString(R.string.during_wrap),
                            getStringRes?.getString(R.string.during_wrap_desc),
                            "wrap",
                            activity as UsePointsActivity
                        )
                    }

                    "Te" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            320,
                            warmDrinkLogo,
                            getStringRes?.getString(R.string.during_tea),
                            getStringRes?.getString(R.string.during_tea_desc),
                            "te",
                            activity as UsePointsActivity
                        )
                    }
                    "Sandwich" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            890,
                            eatLogo,
                            getStringRes?.getString(R.string.during_sandwich),
                            getStringRes?.getString(R.string.during_sandwich_desc),
                            "sandwich",
                            activity as UsePointsActivity
                        )
                    }
                    "Mineralvann" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            420,
                            coldDrinkLogo,
                            getStringRes?.getString(R.string.during_soda),
                            getStringRes?.getString(R.string.during_soda_desc),
                            "mineralvann",
                            activity as UsePointsActivity
                        )
                    }
                    "Smoothie" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            520,
                            coldDrinkLogo,
                            getStringRes?.getString(R.string.during_smoothie),
                            getStringRes?.getString(R.string.during_smoothie_desc),
                            "smoothie",
                            activity as UsePointsActivity
                        )
                    }
                    "Falafel" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            1390,
                            eatLogo,
                            getStringRes?.getString(R.string.during_falafel),
                            getStringRes?.getString(R.string.during_falafel_desc),
                            "falafel",
                            activity as UsePointsActivity
                        )
                    }
                    "Kjøttkaker" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            1690,
                            eatLogo,
                            getStringRes?.getString(R.string.during_meatcakes),
                            getStringRes?.getString(R.string.during_meatcakes_desc),
                            "kjøttkaker",
                            activity as UsePointsActivity
                        )
                    }
                    "Pulled Oumph" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            1790,
                            eatLogo,
                            getStringRes?.getString(R.string.during_oumph),
                            getStringRes?.getString(R.string.during_oumph_desc),
                            "Pulled Oumph",
                            activity as UsePointsActivity
                        )
                    }
                    "Indisk Curry" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        CombinedFunctionsClass.createPopup(
                            1790,
                            eatLogo,
                            getStringRes?.getString(R.string.during_curry),
                            getStringRes?.getString(R.string.during_curry_desc),
                            "indisk curry",
                            activity as UsePointsActivity
                        )
                    } else -> {
                    Toast.makeText(activity as UsePointsActivity, "Ikke gjenkjent Vy kode", Toast.LENGTH_SHORT).show()
                }
                }
            } else {
                Toast.makeText(activity as UsePointsActivity, "Ikke gjenkjent QR kode", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    private fun scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).setBeepEnabled(false).initiateScan()
    }


}