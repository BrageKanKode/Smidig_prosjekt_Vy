package com.example.leafly_application_git.fragments.used_points

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leafly_application_git.R

class UsedPointsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_used_points, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()



        return root
    }
}