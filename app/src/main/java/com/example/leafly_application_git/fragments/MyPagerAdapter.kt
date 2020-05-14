package com.example.leafly_application_git.fragments

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.leafly_application_git.fragments.earnings.EarnedPointsFragment
import com.example.leafly_application_git.fragments.used_points.UsedPointsFragment

class MyPagerAdapter(fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                EarnedPointsFragment()
            }
            else -> UsedPointsFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }
}