package com.example.leafly_application_git.fragments.main_page.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.search.SelectTravelActivity
import com.example.leafly_application_git.activities.search.TravelDetailsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_miljopoints.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.view.*
import org.json.JSONArray
import java.io.File
import java.io.IOException
import java.io.InputStream

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

//        val textView: TextView = root.findViewById(R.id.)
//
//        searchViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        //displayUsername()

        //Hides actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        root.btnVidere.setOnClickListener {
            requireActivity().startActivity(
                Intent(requireActivity(), SelectTravelActivity::class.java)
            )
        }

        return root
    }


//    private fun displayUsername(){
//        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
//        val menuListener = object : ValueEventListener {
//            override fun onDataChange(p0: DataSnapshot) {
//                user = p0.getValue(User::class.java)
//                textView_search_welcome.text = "God dag, " + user?.username
//
//            }
//
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        }
//        ref.addListenerForSingleValueEvent(menuListener)
//    }

}

