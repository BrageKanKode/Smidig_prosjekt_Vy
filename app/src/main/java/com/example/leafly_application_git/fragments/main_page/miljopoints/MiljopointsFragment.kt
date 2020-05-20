package com.example.leafly_application_git.fragments.main_page.miljopoints

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.MainActivity
import com.example.leafly_application_git.activities.authentication.SignUpActivity
import com.example.leafly_application_git.activities.miljopoints.MembershipBenefitsActivity
import com.example.leafly_application_git.activities.miljopoints.progression.ProgressionActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.example.leafly_application_git.storage.MyPreference
import com.google.firebase.auth.FirebaseAuth
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_miljopoints.*
import kotlinx.android.synthetic.main.fragment_miljopoints.view.*


class MiljopointsFragment : Fragment() {

    private lateinit var miljopointsViewModel: MiljopointsViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        miljopointsViewModel = ViewModelProviders.of(this).get(MiljopointsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_miljopoints, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()

        verifyIfUserIsLoggedIn()


        setHasOptionsMenu(true);


        //CONNECTED TO MiljopointsViewModel
//        val textView: TextView = root.findViewById(R.id.text_miljopoints)
//
//        miljopointsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        //Redirect to signup page

        val uid = FirebaseAuth.getInstance().uid
        if (uid !== null){
            root.button_redirect_to_signup.visibility = View.GONE
        }
        root.button_redirect_to_signup.setOnClickListener {
            requireActivity().startActivity(Intent(requireActivity(), SignUpActivity::class.java))
        }

        root.view_use_points.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), UsePointsActivity::class.java)
            )
        }
        root.view_member_benefits.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), MembershipBenefitsActivity::class.java)
            )
        }

        root.btnToProgression.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), ProgressionActivity::class.java)
            )
        }
        root.view_scan_code.setOnClickListener {
            scanFromFragment()
        }


        return root
    }

    private fun verifyIfUserIsLoggedIn(){
        val uid = FirebaseAuth.getInstance().uid
        if (uid == null){
            val intent = Intent(this.context, SignUpActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

            //requireActivity().startActivity(Intent(requireActivity(), SignUpActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.auth_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.sign_out_menu -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this.context, MainActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    //to update progress bar
    override fun onResume() {
        super.onResume()
        val mypreference = MyPreference(context!!.applicationContext)

        val progress = mypreference.getProgress()
        val currency = mypreference.getCurrency()

        progressbar_point_value.text = currency.toString()

        progressBar.setProgress(progress)
    }


    fun scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).initiateScan();
    }


}
