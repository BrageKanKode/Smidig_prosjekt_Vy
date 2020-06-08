@file:Suppress("DEPRECATION")

package com.example.leafly_application_git.fragments.main_page.miljopoints

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.CombinedFunctionsClass.createPopup
import com.example.leafly_application_git.R
import com.example.leafly_application_git.CombinedFunctionsClass.verifyIfUserIsLoggedIn
import com.example.leafly_application_git.activities.MainActivity
import com.example.leafly_application_git.activities.authentication.LoginActivity
import com.example.leafly_application_git.activities.authentication.SignUpActivity
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.explanation.ExplanationActivity
import com.example.leafly_application_git.activities.miljopoints.MembershipBenefitsActivity
import com.example.leafly_application_git.activities.miljopoints.progression.ProgressionActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_miljopoints.*
import kotlinx.android.synthetic.main.fragment_miljopoints.view.*
import kotlinx.android.synthetic.main.fragment_not_logged_in.view.*


@Suppress("DEPRECATION")
class MiljopointsFragment : Fragment() {

    private lateinit var miljopointsViewModel: MiljopointsViewModel

    internal var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        miljopointsViewModel = ViewModelProviders.of(this).get(MiljopointsViewModel::class.java)


        var root = inflater.inflate(R.layout.fragment_miljopoints, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()


        //Checks if user is logged in
        if (verifyIfUserIsLoggedIn()) {
            //--------------If logged in --------------
            fetchUser()

            //Define these buttons
            root.cardView_use_points.setOnClickListener {
                requireActivity().startActivity(
                    Intent(requireActivity(), UsePointsActivity::class.java)
                )
            }
            root.card_view_benefits.setOnClickListener {
                requireActivity().startActivity(
                    Intent(requireActivity(), MembershipBenefitsActivity::class.java)
                )
            }

            root.card_view_btnToProgression.setOnClickListener {
                requireActivity().startActivity(
                    Intent(requireActivity(), ProgressionActivity::class.java)
                )
            }
            root.card_view_scanner.setOnClickListener {
                scanFromFragment()
            }
            root.btn_do_logout.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                val ft: FragmentTransaction = this.requireFragmentManager().beginTransaction()
                ft.detach(this)
                ft.attach(this)
                ft.commit()
            }

            root.cardView_what_is_points.setOnClickListener {
                requireActivity().startActivity(
                    Intent(requireActivity(), ExplanationActivity::class.java)
                )
            }

        } else {
            //--------------If not logged in --------------
            //Change the fragment layout displayed
            root = inflater.inflate(R.layout.fragment_not_logged_in, container, false)

            //Define these buttons
            root.btn_to_sign_up.setOnClickListener {
                requireActivity().startActivity(
                    Intent(requireActivity(), SignUpActivity::class.java)
                )
            }
            root.btn_to_log_in.setOnClickListener {
                requireActivity().startActivity(
                    Intent(requireActivity(), LoginActivity::class.java)
                )
            }
            root.cardView_fragment_what_are_points.setOnClickListener {
                requireActivity().startActivity(
                    Intent(requireActivity(), ExplanationActivity::class.java)
                )
            }

            root.cardView_fragment_membership_benefits.setOnClickListener {
                requireActivity().startActivity(
                    Intent(requireActivity(), MembershipBenefitsActivity::class.java)
                )
            }
        }

        setHasOptionsMenu(true)

        return root
    }


    //to update progress bar and other user details
    override fun onResume() {
        super.onResume()

        val uid = FirebaseAuth.getInstance().uid
        if (uid != null) {
            fetchUser()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val eatLogo = R.drawable.ic_restaurant_24px
        val warmDrinkLogo = R.drawable.ic_local_cafe_24px
        val coldDrinkLogo = R.drawable.ic_local_drink_24px

        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        //If it doesn't recognise a QR code
        if (intentResult != null) {
            //If the content of the QR code has anything
            if (intentResult.contents != null) {
                //If the QR code has a text content of
                when (intentResult.contents) {
                    "Kaffe" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            320,
                            warmDrinkLogo,
                            getStringRes?.getString(R.string.during_coffe),
                            getStringRes?.getString(R.string.during_coffe_desc),
                            "kaffe",
                            activity as MainActivity
                        )
                    }

                    "Wrap" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            890,
                            eatLogo,
                            getStringRes?.getString(R.string.during_wrap),
                            getStringRes?.getString(R.string.during_wrap_desc),
                            "wrap",
                            activity as MainActivity
                        )
                    }

                    "Te" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            320,
                            warmDrinkLogo,
                            getStringRes?.getString(R.string.during_tea),
                            getStringRes?.getString(R.string.during_tea_desc),
                            "te",
                            activity as MainActivity
                        )
                    }
                    "Sandwich" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            890,
                            eatLogo,
                            getStringRes?.getString(R.string.during_sandwich),
                            getStringRes?.getString(R.string.during_sandwich_desc),
                            "sandwich",
                            activity as MainActivity
                        )
                    }
                    "Mineralvann" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            420,
                            coldDrinkLogo,
                            getStringRes?.getString(R.string.during_soda),
                            getStringRes?.getString(R.string.during_soda_desc),
                            "mineralvann",
                            activity as MainActivity
                        )
                    }
                    "Smoothie" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            520,
                            coldDrinkLogo,
                            getStringRes?.getString(R.string.during_smoothie),
                            getStringRes?.getString(R.string.during_smoothie_desc),
                            "smoothie",
                            activity as MainActivity
                        )
                    }
                    "Falafel" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            1390,
                            eatLogo,
                            getStringRes?.getString(R.string.during_falafel),
                            getStringRes?.getString(R.string.during_falafel_desc),
                            "falafel",
                            activity as MainActivity
                        )
                    }
                    "Kjøttkaker" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            1690,
                            eatLogo,
                            getStringRes?.getString(R.string.during_meatcakes),
                            getStringRes?.getString(R.string.during_meatcakes_desc),
                            "kjøttkaker",
                            activity as MainActivity
                        )
                    }
                    "Pulled Oumph" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            1790,
                            eatLogo,
                            getStringRes?.getString(R.string.during_oumph),
                            getStringRes?.getString(R.string.during_oumph_desc),
                            "Pulled Oumph",
                            activity as MainActivity
                        )
                    }
                    "Indisk Curry" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(
                            1790,
                            eatLogo,
                            getStringRes?.getString(R.string.during_curry),
                            getStringRes?.getString(R.string.during_curry_desc),
                            "indisk curry",
                            activity as MainActivity
                        )
                    }
                    else -> {
                        Toast.makeText(
                            activity as MainActivity,
                            "Ikke gjenkjent Vy kode",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    activity as MainActivity,
                    "Ikke gjenkjent QR kode",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }


    private fun scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).setBeepEnabled(false).initiateScan()
    }

    //Function to fetch the correct user from the Firebase Database, also to get the details that is saved within the chosen user
    private fun fetchUser() {
        val ref = FirebaseDatabase.getInstance().getReference("/users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                //To display the username
                textView_welcome_title.text = user?.username
                progressbar_point_value.text = user?.balance.toString()

                progressBar.progress = user!!.progress.toInt()

                //Siple if/else to level up user when certain amount of progress is achieved
                if (user?.level!! == 1) {
                    textView_level_points.text = "Frø"
                }

                if (user?.level!! == 2) {
                    textView_level_points.text = "Spire"
                }

                if (user?.level!! == 3) {
                    textView_level_points.text = "Tre"
                }

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }


}
