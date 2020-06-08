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


        val listHeader = listOf("Frø", "Spire", "Tre")

        val froList = listOf("Samle poeng på dine reiser som du kan bruke i appen", "Nyhetsbrev med kampanjer for Miljøshopen", "Registreringsbonus på 300 miljøpoeng")
        val spireList = listOf("Eksklusive kampanjer for ditt nivå", "Gratis valgfri varmdrikke fra automat", "Gratis toalettbesøk på stasjoner")
        val treList = listOf("Dobbel hastighet på WiFi", "15% rabatt på setereservasjon", "Gratis parkering på utvalgte stasjoner")

        val listChild = HashMap<String, List<String>>()
        listChild.put(listHeader[0], froList)
        listChild.put(listHeader[1], spireList)
        listChild.put(listHeader[2], treList)


        val expandableListAdapter = ExpandableListAdapter(this, listHeader, listChild)

        expandable_list_view.setAdapter(expandableListAdapter)



    }

}