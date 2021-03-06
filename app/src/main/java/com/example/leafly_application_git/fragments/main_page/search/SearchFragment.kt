package com.example.leafly_application_git.fragments.main_page.search

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.CombinedFunctionsClass.verifyIfUserIsLoggedIn
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.search.SelectTravelActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : Fragment() {

    private lateinit var searchViewModel: SearchViewModel

    internal var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_search, container, false)


        //If user is logged in, display username in search fragment
        val uid = FirebaseAuth.getInstance().uid
        if (uid != null){
            displayUsername()
        }

        //Hides actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        root.card_view_clickable_search.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), SelectTravelActivity::class.java)
            )
        }
        root.card_view_clickable_stations.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), SelectTravelActivity::class.java)
            )
        }
        root.card_view_cliclable_speedy.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), SelectTravelActivity::class.java)
            )
        }

        if(verifyIfUserIsLoggedIn()){
            val ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    var balance = user?.balance
                    var progress = user?.progress
                    var level = user?.level
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            ref.addListenerForSingleValueEvent(menuListener)

        }


        return root
    }


    private fun displayUsername(){
        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)
                textView_search_fragmen_headline_text?.text = "God dag, " + user?.username

            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }

}

