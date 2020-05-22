package com.example.leafly_application_git.fragments.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsedPointsFragment : Fragment() {


    internal var user: User? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_used_points, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()


        displayPurchases()

        return root
    }



    private fun displayPurchases(){
        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)


//                val snapshotIterator: Iterable<DataSnapshot> = p0.getChildren()
//                val iterator = snapshotIterator.iterator()
//
//                while (iterator.hasNext()) {
//                    Log.i("TAG", "Value = " + iterator.next().child("/usedHistory").value)
//                }

//                var usedHistory = "Kjøpt 1L av vannrensing"
//
//                var refUsedHistory = ref.child("/usedHistory")
//                refUsedHistory.push().key(usedHistory)


            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }
        ref.addListenerForSingleValueEvent(menuListener)
    }
}