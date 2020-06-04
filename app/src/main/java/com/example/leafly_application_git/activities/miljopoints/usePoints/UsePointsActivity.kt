package com.example.leafly_application_git.activities.miljopoints.usePoints

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.authentication.User
import com.example.leafly_application_git.fragments.use_points.UsePointsTabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_use_points.*

class UsePointsActivity : AppCompatActivity() {


    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    internal var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_points)


        //------------------Tab layout code-------------------------
        tabLayout = findViewById<TabLayout>(R.id.tabs_use_points)
        viewPager = findViewById<ViewPager>(R.id.view_pager_use_points)

        tabLayout.addTab(tabLayout.newTab().setText("Under reisen"))
        tabLayout.addTab(tabLayout.newTab().setText("Gi til miljøet"))
        tabLayout.addTab(tabLayout.newTab().setText("Shop"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter =
            UsePointsTabAdapter(
                supportFragmentManager,
                tabLayout.tabCount
            )
        viewPager.adapter = adapter


        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })

        //----------------End of tab code------------------


        val ref = FirebaseDatabase.getInstance().getReference("/users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                user = p0.getValue(User::class.java)

                textView_display_currency_header.text = user?.balance?.toString()
            }

            override fun onCancelled(p0: DatabaseError) {

            }

        }
        ref.addListenerForSingleValueEvent(menuListener)


    }









}