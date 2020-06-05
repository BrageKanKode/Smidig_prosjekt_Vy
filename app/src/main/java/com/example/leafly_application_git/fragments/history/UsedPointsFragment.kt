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
import com.example.leafly_application_git.activities.miljopoints.progression.HistoryActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.cupon_dialog.view.*
import kotlinx.android.synthetic.main.tree_cupon_dialog.view.*
import kotlinx.android.synthetic.main.used_history_row.view.*
import kotlinx.android.synthetic.main.used_history_row.view.textView_display_new_item
import kotlin.collections.ArrayList


class UsedPointsFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_used_points, container, false)
        //Shows actionbar
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()


        val usedHistoryList = ArrayList<String>()


        val ref = FirebaseDatabase.getInstance().getReference("/users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("/usedHistory/")
        val menuListener = object : ValueEventListener {
            @SuppressLint("SetTextI18n")
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
                            createPopup(mDialogView, "Netflix - Verdikuppong", "3 måneder Netflix abbonnement")

                        } else if (view.textView_display_new_item.text.toString().contains("Viaplay")){
                            createPopup(mDialogView, "Viaplay - Verdikuppong", "Leie av 1 film på Viaplay")

                        } else if(view.textView_display_new_item.text.toString().contains("Odeon")){
                            createPopup(mDialogView, "Odeon - Verdikuppong", "2 for 1 på Odeon kinobilletter")

                        } else if(view.textView_display_new_item.text.toString().contains("Narvesen")){
                            createPopup(mDialogView, "Narvesen - Verdikuppong", "1 gratis pølse hos en Narvesen forhandler")

                        } else if (view.textView_display_new_item.text.toString().contains("Starbucks")){
                            createPopup(mDialogView, "Starbucks - Verdikuppong", "En gratis valgfri kaffe hos en Starbucks forhandler")

                        } else if(view.textView_display_new_item.text.toString().contains("Voi")) {
                            createPopup(mDialogView, "Voi - Verdikuppong", "Ingen oppstarts-avgift på en Voi sparkesykkel")

                        } else if (view.textView_display_new_item.text.toString().contains("tre") || view.textView_display_new_item.text.toString().contains("trær")){
                            mBuilder.setView(mDialogView2)
                            mDialogView2.textView_tree_cupon_title.text = "Planting av trær"
                            mDialogView2.textView_cupon_desc.text = "Planting av trær som hjelper miljøet"
                            mDialogView2.textView_tree_info.text = activity?.applicationContext?.resources?.getString(R.string.tree_cupon_info_box)

                        } else if (view.textView_display_new_item.text.toString().contains("vannrensing")){
                            mBuilder.setView(mDialogView2)
                            mDialogView2.textView_tree_cupon_title.text = "Rydding av plast"
                            mDialogView2.textView_cupon_desc.text = "Rydding av plast i havet som hjelper miljøet"
                            mDialogView2.textView_tree_info.text = activity?.applicationContext?.resources?.getString(R.string.ocean_clean_info_box)

                        } else if (view.textView_display_new_item.text.toString().contains("kaffe på togturen")){
                            createPopup(mDialogView, "Kaffe - Verdikuppong", "Kaffe-kupong til en av våre maskiner")

                        }else if (view.textView_display_new_item.text.toString().contains("wrap")){
                            createPopup(mDialogView, "Wrap - Verdikuppong", "Wrap-kupong til en av våre maskiner eller togets cafe")

                        }else if (view.textView_display_new_item.text.toString().contains("Du kjøpte 1 te")){
                            createPopup(mDialogView, "Te - Verdikuppong", "Te-kupong til en av våre maskiner eller togets cafe")

                        }else if (view.textView_display_new_item.text.toString().contains("sandwich")){
                            createPopup(mDialogView, "Sandwich - Verdikuppong", "Sandwich-kupong til en av våre maskiner eller togets cafe")

                        }else if (view.textView_display_new_item.text.toString().contains("mineralvann")){
                            createPopup(mDialogView, "Mineralvann - Verdikuppong", "Mineralvann-kupong til en av våre maskiner eller togets cafe")

                        }else if (view.textView_display_new_item.text.toString().contains("smoothie")){
                            createPopup(mDialogView, "Smoothie - Verdikuppong", "Smoothie-kupong til togets cafe")

                        }else if (view.textView_display_new_item.text.toString().contains("falafel")){
                            createPopup(mDialogView, "Falafel - Verdikuppong", "Falafel-kupong til togets cafe")

                        }else if (view.textView_display_new_item.text.toString().contains("kjøttkake")){
                            createPopup(mDialogView, "Kjøttkake - Verdikuppong", "Kjøttkake-kupong til togets cafe")

                        }else if (view.textView_display_new_item.text.toString().contains("indisk curry")){
                            createPopup(mDialogView, "Indisk Curry - Verdikuppong", "Indisk Curry-kupong til togets cafe")

                        }else if (view.textView_display_new_item.text.toString().contains("Pulled Oumph")){
                            createPopup(mDialogView, "Pulled Oumph - Verdikuppong", "Pulled Oumph-kupong til togets cafe")

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

    fun createPopup(mDialogView: View, header: String, information: String) {
        mDialogView.textView_your_cupon_text.text = header
        mDialogView.textView_display_given_cupon.text = information
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

        fun setHistoryRowInformation(rootView: View, logo: Int, price: String) {
            rootView.imageView_used_history_image.setImageResource(logo)
            rootView.textView_display_used_history_price.text = price
        }


        val eatLogo = R.drawable.ic_restaurant_24px
        val warmDrinkLogo = R.drawable.ic_local_cafe_24px
        val coldDrinkLogo = R.drawable.ic_local_drink_24px

        //rendering out each row
        @SuppressLint("ViewHolder", "SetTextI18n")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val rootView: View = LayoutInflater.from(mContext).inflate(R.layout.used_history_row, parent, false)
            val usedPointsList = rootView.findViewById<TextView>(R.id.textView_display_new_item)


            Log.d("TAG", usedPointsList.toString())

            usedPointsList.text = mUsedHistoryList[position]


            //Checks what type of item that has been purchased. Displays the corresponding image to each picture
            if (rootView.textView_display_new_item.text.toString().contains("Netflix")){
                setHistoryRowInformation(rootView, R.drawable.ic_netflix_2015_logo, "-3300")

            } else if (rootView.textView_display_new_item.text.toString().contains("Viaplay")){
                setHistoryRowInformation(rootView, R.drawable.ic_viaplay, "-500")

            } else if (rootView.textView_display_new_item.text.toString().contains("Odeon")){
                setHistoryRowInformation(rootView, R.drawable.ic_odeon_logo, "-1350")

            } else if (rootView.textView_display_new_item.text.toString().contains("Narvesen")){
                setHistoryRowInformation(rootView, R.drawable.ic_narvesen_logo, "-350")

            } else if (rootView.textView_display_new_item.text.toString().contains("Starbucks")){
                setHistoryRowInformation(rootView, R.drawable.ic_starbucks_coffee, "-450")

            } else if (rootView.textView_display_new_item.text.toString().contains("Voi")){
                setHistoryRowInformation(rootView, R.drawable.ic_logotype_red, "-100")

            } else if (rootView.textView_display_new_item.text.toString().contains("tre") || rootView.textView_display_new_item.text.toString().contains("trær")){
                val treeDrawable = R.drawable.ic_trer
                rootView.imageView_used_history_image.setImageResource(treeDrawable)
                val value = usedPointsList.text.toString().replace("[^0-9]".toRegex(), "")
                val intvalue = value.substring(1)
                rootView.textView_display_used_history_price.text = "-$intvalue"
            } else if (rootView.textView_display_new_item.text.toString().contains("vannrensing")){
                val plasticDrawable = R.drawable.ic_plast
                rootView.imageView_used_history_image.setImageResource(plasticDrawable)
                val value = usedPointsList.text.toString().replace("[^0-9]".toRegex(), "")
                val intvalue = value.substring(1)
                rootView.textView_display_used_history_price.text = "-$intvalue"
            } else if (rootView.textView_display_new_item.text.toString().contains("kaffe på togturen")){
                setHistoryRowInformation(rootView, warmDrinkLogo, "-320")

            } else if (rootView.textView_display_new_item.text.toString().contains("wrap")){
                setHistoryRowInformation(rootView, eatLogo, "-890")

            } else if (rootView.textView_display_new_item.text.toString().contains("Du kjøpte 1 te")) {
                setHistoryRowInformation(rootView, warmDrinkLogo, "-420")

            }else if (rootView.textView_display_new_item.text.toString().contains("sandwich")) {
                setHistoryRowInformation(rootView, eatLogo, "-520")

            }else if (rootView.textView_display_new_item.text.toString().contains("mineralvann")) {
                setHistoryRowInformation(rootView, coldDrinkLogo, "-420")

            }else if (rootView.textView_display_new_item.text.toString().contains("smoothie")) {
                setHistoryRowInformation(rootView, coldDrinkLogo, "-520")

            }else if (rootView.textView_display_new_item.text.toString().contains("falafel")) {
                setHistoryRowInformation(rootView, eatLogo, "-1390")

            }else if (rootView.textView_display_new_item.text.toString().contains("kjøttkake")) {
                setHistoryRowInformation(rootView, eatLogo, "-1690")

            }else if (rootView.textView_display_new_item.text.toString().contains("Pulled Oumph")) {
                setHistoryRowInformation(rootView, eatLogo, "-1790")

            }else if (rootView.textView_display_new_item.text.toString().contains("indisk curry")) {
                setHistoryRowInformation(rootView, eatLogo, "-1790")

            }

            return rootView
        }
    }



}

