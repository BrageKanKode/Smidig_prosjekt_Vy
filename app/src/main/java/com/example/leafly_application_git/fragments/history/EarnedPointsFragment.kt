package com.example.leafly_application_git.fragments.history

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.miljopoints.progression.HistoryActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EarnedPointsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_earned_points, container, false)

        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()


        val earnedHistoryList = ArrayList<String>()

        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("/earnedHistory/")
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                for (childSnapshot: DataSnapshot in p0.children){
                    earnedHistoryList.add(childSnapshot.value.toString())

                    Log.d("Tag", earnedHistoryList.toString())

                    val listView = root.findViewById(R.id.listView_earned_points) as ListView
                    val adapter = MyCustomAdapterForEarned(
                        activity as HistoryActivity,
                        earnedHistoryList
                    )
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()

                }


            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        ref.addListenerForSingleValueEvent(menuListener)


        return root
    }



    private class MyCustomAdapterForEarned(context: Context, earnedHistoryList: ArrayList<String>): BaseAdapter() {

        private val mContext: Context = context
        private var mEarnedHistoryList: ArrayList<String> = earnedHistoryList


        override fun getCount(): Int {
            return mEarnedHistoryList.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return mEarnedHistoryList[position]
        }

        //rendering out each row
        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rootView: View = LayoutInflater.from(mContext).inflate(R.layout.earned_history_row, parent, false)
            val usedPointsList = rootView.findViewById<TextView>(R.id.textView_display_earned_item)

            Log.d("TAG", usedPointsList.toString())

            usedPointsList.text = mEarnedHistoryList[position]

            return rootView
        }
    }

}