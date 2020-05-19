package com.example.leafly_application_git.storage

import android.content.Context

class MyPreference(context: Context) {

    val PREFERENCE_NAME = "SharedPreferenceExample"
    val PREFERENCE_CURRENCY = "Currency"
    val PREFERENCE_PROGRESS = "Progress"
    val PREFERENCE_TOTAL_CURRENCY = "TotalCurrency"
    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getCurrency() : Int{
        return preference.getInt(PREFERENCE_CURRENCY, 0)
    }

    fun setCurrency(count : Int) {
        val editor = preference.edit()
        editor.putInt(PREFERENCE_CURRENCY, count)
        editor.apply()
    }

    fun getTotalCollected() : Int {
        return preference.getInt(PREFERENCE_TOTAL_CURRENCY, 0)
    }

    fun setTotalCurrency(count : Int) {
        val editor = preference.edit()
        editor.putInt(PREFERENCE_PROGRESS, count)
        editor.apply()
    }

    fun getProgress() : Int {
        return preference.getInt(PREFERENCE_PROGRESS, 0)
    }

    fun setProgress(count : Int) {
        val editor = preference.edit()
        editor.putInt(PREFERENCE_PROGRESS, count)
        editor.apply()
    }
}