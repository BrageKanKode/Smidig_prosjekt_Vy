package com.example.leafly_application_git.fragments.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.search.TravelDetailsActivity
import kotlinx.android.synthetic.main.fragment_search.view.*

class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_search, container, false)

//        val textView: TextView = root.findViewById(R.id.)
//
//        searchViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })


        //Hides actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        root.btnVidere.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), TravelDetailsActivity::class.java)
            )
        }


        return root
    }
}
