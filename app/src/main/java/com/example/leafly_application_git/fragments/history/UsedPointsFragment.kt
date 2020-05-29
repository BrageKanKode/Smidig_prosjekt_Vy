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
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.miljopoints.progression.HistoryActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.used_history_row.view.*
import java.util.*
import kotlin.collections.ArrayList


class UsedPointsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_used_points, container, false)
        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()




        val usedHistoryList = ArrayList<String>()


        var ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("/usedHistory/")
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                for (childSnapshot: DataSnapshot in p0.children){
                    usedHistoryList.add(childSnapshot.value.toString())

                    Log.d("Tag", usedHistoryList.toString())

                    val listView = root.findViewById(R.id.listView_used_points) as ListView
                    val adapter = MyCustomAdapter(activity as HistoryActivity, usedHistoryList)
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()

                }


            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        }

        ref.addListenerForSingleValueEvent(menuListener)



        Log.d("Tag", usedHistoryList.toString())


        return root
    }


    private class MyCustomAdapter(context: Context, usedHistoryList: ArrayList<String>): BaseAdapter() {

        private val mContext: Context = context
        private var mUsedHistoryList: ArrayList<String> = usedHistoryList


        override fun getCount(): Int {
            return mUsedHistoryList.size

        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return mUsedHistoryList[position]
        }

        //rendering out each row
        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rootView: View = LayoutInflater.from(mContext).inflate(R.layout.used_history_row, parent, false)
            val usedPointsList = rootView.findViewById<TextView>(R.id.textView_display_new_item)


            Log.d("TAG", usedPointsList.toString())

            usedPointsList.text = mUsedHistoryList[position]


            //Checks what type of item that has been purchased. Displays the corresponding image to each picture
            if (rootView.textView_display_new_item.text.toString().contains("Netflix")){
                val netflixDrawable = R.drawable.ic_netflix_2015_logo
                rootView.imageView_used_history_image.setImageResource(netflixDrawable)
            } else if (rootView.textView_display_new_item.text.toString().contains("Dplay")){
                val dplayDrawable = R.drawable.ic_dplay_logo_2019
                rootView.imageView_used_history_image.setImageResource(dplayDrawable)
            } else if (rootView.textView_display_new_item.text.toString().contains("Odeon")){
                val odeonDrawable = R.drawable.ic_odeon_logo
                rootView.imageView_used_history_image.setImageResource(odeonDrawable)
            } else if (rootView.textView_display_new_item.text.toString().contains("Narvesen")){
                val narvesenDrawable = R.drawable.ic_narvesen_logo
                rootView.imageView_used_history_image.setImageResource(narvesenDrawable)
            } else if (rootView.textView_display_new_item.text.toString().contains("Starbucks")){
                val starbucksDrawable = R.drawable.ic_starbucks_coffee
                rootView.imageView_used_history_image.setImageResource(starbucksDrawable)
            } else if (rootView.textView_display_new_item.text.toString().contains("Voi")){
                val voiDrawable = R.drawable.ic_logotype_red
                rootView.imageView_used_history_image.setImageResource(voiDrawable)
            } else if (rootView.textView_display_new_item.text.toString().contains("tre")){
                val treeDrawable = R.drawable.ic_trer
                rootView.imageView_used_history_image.setImageResource(treeDrawable)
            } else if (rootView.textView_display_new_item.text.toString().contains("vann")){
                val plasticDrawable = R.drawable.ic_plast
                rootView.imageView_used_history_image.setImageResource(plasticDrawable)
            } else {
                val standardImage = R.drawable.ic_eco_black_18dp
                rootView.imageView_used_history_image.setImageResource(standardImage)
            }

            return rootView
        }
    }



}

