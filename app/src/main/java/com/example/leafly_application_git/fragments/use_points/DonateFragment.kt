package com.example.leafly_application_git.fragments.use_points

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.saveTheWorld.CleanOceanActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.saveTheWorld.PlantTreeActivity
import kotlinx.android.synthetic.main.fragment_donate.view.*
import kotlinx.android.synthetic.main.fragment_miljopoints.view.*

class DonateFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_donate, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()



        root.btnToPlantTree.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), PlantTreeActivity::class.java)
            )
        }

        root.btnToCleanOcean.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), CleanOceanActivity::class.java)
            )
        }

        return root
    }
}