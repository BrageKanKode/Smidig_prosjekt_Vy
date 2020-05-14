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

        //TODO: endre på verdiene til medlemsnivåer
        val listHeader = listOf("Frø", "Spire", "Tre")

        val froList = listOf("Gratis Internett", "Tilgang til automat-varer")
        val spireList = listOf("Gratis Internett", "Tilgang til underholdning", "1 Gratis kaffe i måneden")
        val treList = listOf("Raskere internett tilgang", "Game-zone", "Stille sone", "Gratis E-bøker", "2 gratis kaffe i måneden")

        val listChild = HashMap<String, List<String>>()
        listChild.put(listHeader[0], froList)
        listChild.put(listHeader[1], spireList)
        listChild.put(listHeader[2], treList)

        val expandableListAdapter = ExpandableListAdapter(this, listHeader, listChild)

        expandable_list_view.setAdapter(expandableListAdapter)

    }

}