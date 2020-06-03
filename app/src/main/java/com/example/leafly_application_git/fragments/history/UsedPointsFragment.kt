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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.activities.miljopoints.progression.HistoryActivity
import com.example.leafly_application_git.activities.miljopoints.usePoints.UsePointsActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.cupon_dialog.*
import kotlinx.android.synthetic.main.cupon_dialog.view.*
import kotlinx.android.synthetic.main.tree_cupon_dialog.view.*
import kotlinx.android.synthetic.main.used_history_row.*
import kotlinx.android.synthetic.main.used_history_row.view.*
import kotlinx.android.synthetic.main.used_history_row.view.textView_display_new_item
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
                    usedHistoryList.add(0, childSnapshot.value.toString())

                    Log.d("Tag", usedHistoryList.toString())

                    val listView = root.findViewById(R.id.listView_used_points) as ListView
                    val adapter = MyCustomAdapter(activity as HistoryActivity, usedHistoryList)
                    listView.adapter = adapter
                    adapter.notifyDataSetChanged()



                    listView.setOnItemClickListener { parent, view, position, id ->
                        val mDialogView = LayoutInflater.from(activity as HistoryActivity)
                        .inflate(R.layout.cupon_dialog, null)
                        val mBuilder = AlertDialog.Builder(activity as HistoryActivity)
                            .setView(mDialogView)


                        val mDialogView2 = LayoutInflater.from(activity as HistoryActivity).inflate(R.layout.tree_cupon_dialog, null)
                        fun getRandomString(length: Int) : String {
                            val allowedChars = ('A'..'Z') + ('a'..'z')
                            return (1..length)
                                .map { allowedChars.random() }
                                .joinToString("")
                        }

                        mDialogView.textView_cupon_code_example.text = getRandomString(8)

                        if (view.textView_display_new_item.text.toString().contains("Netflix")){
                            mDialogView.textView_your_cupon_text.text = "Netflix - Verdikuppong"
                            mDialogView.textView_display_given_cupon.text = "3 måneder Netflix abbonnement"
                        } else if (view.textView_display_new_item.text.toString().contains("Dplay")){
                            mDialogView.textView_your_cupon_text.text = "Dplay - Verdikuppong"
                            mDialogView.textView_display_given_cupon.text = "1 måned gratis Dplay abbonnement"
                        } else if(view.textView_display_new_item.text.toString().contains("Odeon")){
                            mDialogView.textView_your_cupon_text.text = "Odeon - Verdikuppong"
                            mDialogView.textView_display_given_cupon.text = "2 for 1 på Odeon kinobilletter"
                        } else if(view.textView_display_new_item.text.toString().contains("Narvesen")){
                            mDialogView.textView_your_cupon_text.text = "Narvesen - Verdikuppong"
                            mDialogView.textView_display_given_cupon.text = "1 gratis pølse hos en Narvesen forhandler"
                        } else if (view.textView_display_new_item.text.toString().contains("Starbucks")){
                            mDialogView.textView_your_cupon_text.text = "Starbucks - Verdikuppong"
                            mDialogView.textView_display_given_cupon.text = "En gratis valgfri kaffe hos en Starbucks forhandler"
                        } else if(view.textView_display_new_item.text.toString().contains("Voi")) {
                            mDialogView.textView_your_cupon_text.text = "Voi - Verdikuppong"
                            mDialogView.textView_display_given_cupon.text = "Ingen oppstarts-avgift på en Voi sparkesykkel"
                        } else if (view.textView_display_new_item.text.toString().contains("tre")){
                            mBuilder.setView(mDialogView2)
                            mDialogView2.textView_tree_cupon_title.text = "Planting av trær"
                            mDialogView2.textView_cupon_desc.text = "Planting av trær som hjelper miljøet"
                            mDialogView2.textView_tree_info.text = activity?.applicationContext?.resources?.getString(R.string.tree_cupon_info_box)
                        } else if (view.textView_display_new_item.text.toString().contains("vann")){
                            mBuilder.setView(mDialogView2)
                            mDialogView2.textView_tree_cupon_title.text = "Rydding av plast"
                            mDialogView2.textView_cupon_desc.text = "Rydding av plast i havet som hjelper miljøet"
                            mDialogView2.textView_tree_info.text = activity?.applicationContext?.resources?.getString(R.string.ocean_clean_info_box)
                        }

                        val mAlertDialog = mBuilder.show()
                        mDialogView.imageView_close_cupon_dialog.setOnClickListener {
                            mAlertDialog.dismiss()
                        }

                        mDialogView2.imageView_close_tree_ocean_cupon.setOnClickListener {
                            mAlertDialog.dismiss()
                        }

                    }


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
                rootView.textView_display_used_history_price.text = "-3300"
            } else if (rootView.textView_display_new_item.text.toString().contains("Dplay")){
                val dplayDrawable = R.drawable.ic_dplay_logo_2019
                rootView.imageView_used_history_image.setImageResource(dplayDrawable)
                rootView.textView_display_used_history_price.text = "-100"
            } else if (rootView.textView_display_new_item.text.toString().contains("Odeon")){
                val odeonDrawable = R.drawable.ic_odeon_logo
                rootView.imageView_used_history_image.setImageResource(odeonDrawable)
                rootView.textView_display_used_history_price.text = "-1350"
            } else if (rootView.textView_display_new_item.text.toString().contains("Narvesen")){
                val narvesenDrawable = R.drawable.ic_narvesen_logo
                rootView.imageView_used_history_image.setImageResource(narvesenDrawable)
                rootView.textView_display_used_history_price.text = "-350"
            } else if (rootView.textView_display_new_item.text.toString().contains("Starbucks")){
                val starbucksDrawable = R.drawable.ic_starbucks_coffee
                rootView.textView_display_used_history_price.text = "-450"
                rootView.imageView_used_history_image.setImageResource(starbucksDrawable)
            } else if (rootView.textView_display_new_item.text.toString().contains("Voi")){
                val voiDrawable = R.drawable.ic_logotype_red
                rootView.imageView_used_history_image.setImageResource(voiDrawable)
                rootView.textView_display_used_history_price.text = "-100"
            } else if (rootView.textView_display_new_item.text.toString().contains("tre")){
                val treeDrawable = R.drawable.ic_trer
                rootView.imageView_used_history_image.setImageResource(treeDrawable)
//                rootView.textView_display_used_history_price.text = "-100"
            } else if (rootView.textView_display_new_item.text.toString().contains("vann")){
                val plasticDrawable = R.drawable.ic_plast
                rootView.imageView_used_history_image.setImageResource(plasticDrawable)
                //rootView.textView_display_used_history_price.text = "-100"
            } else {
                val standardImage = R.drawable.ic_eco_black_18dp
                rootView.imageView_used_history_image.setImageResource(standardImage)
            }

            return rootView
        }
    }



}

