package com.example.leafly_application_git.fragments.main_page.miljopoints

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.R
import com.example.leafly_application_git.CombinedFunctionsClass.verifyIfUserIsLoggedIn
import com.example.leafly_application_git.activities.MainActivity
import com.example.leafly_application_git.activities.authentication.LoginActivity
import com.example.leafly_application_git.activities.authentication.SignUpActivity
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.explanation.ExplanationActivity
import com.example.leafly_application_git.activities.miljopoints.MembershipBenefitsActivity
import com.example.leafly_application_git.activities.miljopoints.progression.HistoryActivity
import com.example.leafly_application_git.activities.miljopoints.progression.ProgressionActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.example.leafly_application_git.fragments.history.UsedPointsFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.before_scan_dialog.*
import kotlinx.android.synthetic.main.before_scan_dialog.view.*
import kotlinx.android.synthetic.main.before_scan_dialog.view.button_scan_dialog
import kotlinx.android.synthetic.main.fragment_miljopoints.*
import kotlinx.android.synthetic.main.fragment_miljopoints.view.*
import kotlinx.android.synthetic.main.fragment_not_logged_in.view.*
import kotlinx.android.synthetic.main.purchase_done_dialog.*
import kotlinx.android.synthetic.main.purchase_done_dialog.view.*
import kotlinx.android.synthetic.main.purchase_done_dialog.view.button_purchase_keep_shopping_shop


class MiljopointsFragment : Fragment() {

    private lateinit var miljopointsViewModel: MiljopointsViewModel

    internal var user: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        miljopointsViewModel = ViewModelProviders.of(this).get(MiljopointsViewModel::class.java)






        var root = inflater.inflate(R.layout.fragment_miljopoints, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()



        //Checks if user is logged in
        if(verifyIfUserIsLoggedIn()) {
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
                val ft : FragmentTransaction = this.fragmentManager!!.beginTransaction()
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
            root.cardView_fragment_what_are_points.setOnClickListener{
                requireActivity().startActivity(
                    Intent(requireActivity(), ExplanationActivity::class.java)
                )
            }
        }


        setHasOptionsMenu(true);



        //CONNECTED TO MiljopointsViewModel
//        val textView: TextView = root.findViewById(R.id.text_miljopoints)
//
//        miljopointsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })




        return root
    }


    //to update progress bar and other user details
    override fun onResume() {
        super.onResume()

        val uid = FirebaseAuth.getInstance().uid
        if (uid != null){
            fetchUser()
        }
    }

    fun createPopup(price: Int, logo: Int, header: String?, desc: String?, historyName: String) {

        //Firebase reference
        val ref
                = FirebaseDatabase.getInstance().getReference("/users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)

        val btnText = "Betal"

        //Inflate popup
        var mDialogView = LayoutInflater.from(activity as MainActivity)
            .inflate(R.layout.before_scan_dialog, null)
        var mBuilder = AlertDialog.Builder(activity as MainActivity)
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
                var balance = user?.balance

                //Set users balance on screen
                mDialogView.textView_balance_current_before_scan.text = balance?.toString()


                mDialogView.button_scan_dialog.setOnClickListener {
                    mAlertDialog2.dismiss()
                    if(balance!! >= price) {
                        mDialogView = LayoutInflater.from(activity as MainActivity)
                            .inflate(R.layout.purchase_done_dialog, null)
                        mBuilder = AlertDialog.Builder(activity as MainActivity)
                            .setView(mDialogView)
                        val mAlertDialog = mBuilder.show()

                        mDialogView.button_purchase_view_cupon.setOnClickListener {
                            requireActivity().startActivity(
                                Intent(requireActivity(), HistoryActivity::class.java)
                            )
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
                        Toast.makeText(activity as MainActivity, "Not enough points to do that", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        }
        ref.addListenerForSingleValueEvent(menuListener)

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
                val ref = FirebaseDatabase.getInstance().getReference("/users")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid)
                //If the QR code has a text content of
                when (intentResult.contents) {
                    "Kaffe" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(320, warmDrinkLogo, getStringRes?.getString(R.string.during_coffe), getStringRes?.getString(R.string.during_coffe_desc), "kaffe")
                    }

                    "Wrap" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(890, eatLogo, getStringRes?.getString(R.string.during_wrap), getStringRes?.getString(R.string.during_wrap_desc), "wrap")
                    }

                    "Te" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(320, warmDrinkLogo, getStringRes?.getString(R.string.during_tea), getStringRes?.getString(R.string.during_tea_desc), "te")
                    }
                    "Sandwich" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(890, eatLogo, getStringRes?.getString(R.string.during_sandwich), getStringRes?.getString(R.string.during_sandwich_desc), "sandwich")
                    }
                    "Mineralvann" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(420, coldDrinkLogo, getStringRes?.getString(R.string.during_soda), getStringRes?.getString(R.string.during_soda_desc), "mineralvann")
                    }
                    "Smoothie" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(520, coldDrinkLogo, getStringRes?.getString(R.string.during_smoothie), getStringRes?.getString(R.string.during_smoothie_desc), "smoothie")
                    }
                    "Falafel" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(1390, eatLogo, getStringRes?.getString(R.string.during_falafel), getStringRes?.getString(R.string.during_falafel_desc), "falafel")
                    }
                    "Kjøttkaker" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(1690, eatLogo, getStringRes?.getString(R.string.during_meatcakes), getStringRes?.getString(R.string.during_meatcakes_desc), "kjøttkaker")
                    }
                    "Pulled Oumph" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(1790, eatLogo, getStringRes?.getString(R.string.during_oumph), getStringRes?.getString(R.string.during_oumph_desc), "Pulled Oumph")
                    }
                    "Indisk Curry" -> {
                        val getStringRes = activity?.applicationContext?.resources
                        createPopup(1790, eatLogo, getStringRes?.getString(R.string.during_curry), getStringRes?.getString(R.string.during_curry_desc), "indisk curry")
                    } else -> {
                        Toast.makeText(activity as MainActivity, "Ikke gjenkjent Vy kode", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(activity as MainActivity, "Ikke gjenkjent QR kode", Toast.LENGTH_SHORT).show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }



    private fun scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).setBeepEnabled(false).initiateScan()
    }

    //Function to fetch the correct user from the Firebase Database, also to get the details that is saved within the chosen user
    private fun fetchUser(){
        val ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                //To display the username
                textView_welcome_title.text = user?.username
                progressbar_point_value.text = user?.balance.toString()

                progressBar.progress = user!!.progress.toInt()

                //Siple if/else to level up user when certain amount of progress is achieved
                if (user?.level!! == 1){
                    textView_level_points.text = "Frø"
                    val froDrawable = R.drawable.ic_fro_membership
                    imageView_display_level_icon.setImageResource(froDrawable)
                }

                if (user?.level!! == 2){
                    textView_level_points.text = "Spire"
                    val spireDrawable = R.drawable.ic_spire_membership
                    imageView_display_level_icon.setImageResource(spireDrawable)
                }

                if (user?.level!! == 3){
                    textView_level_points.text = "Tre"
                    val treDrawable = R.drawable.ic_tre_membership
                    imageView_display_level_icon.setImageResource(treDrawable)
                }


            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }


}
