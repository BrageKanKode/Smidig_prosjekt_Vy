package com.example.leafly_application_git.fragments.main_page.miljopoints

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.R
import com.example.leafly_application_git.CombinedFunctionsClass.verifyIfUserIsLoggedIn
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



    fun scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).initiateScan();
    }

    //Function to fetch the correct user from the Firebase Database, also to get the details that is saved within the chosen user
    private fun fetchUser(){
        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                //To display the username
                textView_welcome_title.text = user?.username
                progressbar_point_value.text = user?.balance.toString()

                progressBar.setProgress(user!!.progress.toInt())

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
