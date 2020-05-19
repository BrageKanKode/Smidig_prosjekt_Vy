package com.example.leafly_application_git.fragments.main_page.miljopoints

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.miljopoints.MembershipBenefitsActivity
import com.example.leafly_application_git.activities.miljopoints.progression.ProgressionActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.example.leafly_application_git.storage.MyPreference
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


        //CONNECTED TO MiljopointsViewModel
//        val textView: TextView = root.findViewById(R.id.text_miljopoints)
//
//        miljopointsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

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


    //to update progress bar
    override fun onResume() {
        super.onResume()
        val mypreference = MyPreference(context!!.applicationContext)

        val progress = mypreference.getProgress()

        progressBar.incrementProgressBy(progress)

    }


    fun scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).initiateScan();
    }


}
