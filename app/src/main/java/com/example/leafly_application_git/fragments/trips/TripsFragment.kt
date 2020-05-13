package com.example.leafly_application_git.fragments.trips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.R

class TripsFragment : Fragment() {

    private lateinit var tripsViewModel: TripsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tripsViewModel = ViewModelProviders.of(this).get(TripsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_trips, container, false)

//        val textView: TextView = root.findViewById(R.id.text_view_test)
//
//        tripsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        return root
    }
}
