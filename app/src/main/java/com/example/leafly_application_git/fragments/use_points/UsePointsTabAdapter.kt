package com.example.leafly_application_git.fragments.use_points

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class UsePointsTabAdapter(fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                DuringTripFragment()
            }
            1 -> {
                DonateFragment()
            }
            else -> ShopFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }
}