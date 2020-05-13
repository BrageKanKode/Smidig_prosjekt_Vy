package com.example.leafly_application_git.fragments.miljopoints

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.MembershipBenefitsActivity
import com.example.leafly_application_git.activities.UsePointsActivity
import kotlinx.android.synthetic.main.fragment_miljopoints.view.*

class MiljopointsFragment : Fragment() {

    private lateinit var miljopointsViewModel: MiljopointsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        miljopointsViewModel = ViewModelProviders.of(this).get(MiljopointsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_miljopoints, container, false)


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
        return root
    }
}
