package com.example.leafly_application_git.fragments.history

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class HistoryTabAdapter(fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm) {

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