package com.example.leafly_application_git.activities.miljopoints.progression

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.leafly_application_git.R
import com.example.leafly_application_git.fragments.MyPagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {


    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tabLayout = findViewById<TabLayout>(R.id.tabs)
        viewPager = findViewById<ViewPager>(R.id.view_pager)

        tabLayout.addTab(tabLayout.newTab().setText("Bruk"))
        tabLayout.addTab(tabLayout.newTab().setText("Opptjening"))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val adapter = MyPagerAdapter(supportFragmentManager, tabLayout.tabCount)
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

    }
}