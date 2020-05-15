package com.example.leafly_application_git.activities.miljopoints.usePoints

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.leafly_application_git.R
import com.example.leafly_application_git.fragments.use_points.UsePointsTabAdapter
import com.google.android.material.tabs.TabLayout

class UsePointsActivity : AppCompatActivity() {


    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager


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

    }
}