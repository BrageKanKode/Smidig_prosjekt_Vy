package com.example.leafly_application_git.activities.miljopoints

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.ExpandableListAdapter
import com.example.leafly_application_git.R
import kotlinx.android.synthetic.main.activity_membership_benefits.*

class MembershipBenefitsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membership_benefits)

        val listHeader = listOf("numbers", "fruits")

        val numbersList = listOf("One", "Two", "Three", "Four")
        val fruitList = listOf("apple", "orange", "banana")

        val listChild = HashMap<String, List<String>>()
        listChild.put(listHeader[0], numbersList)
        listChild.put(listHeader[1], fruitList)

        val expandableListAdapter = ExpandableListAdapter(this, listHeader, listChild)

        expandable_list_view.setAdapter(expandableListAdapter)


    }

}