package com.example.leafly_application_git.storage

import android.content.Context

class MyPreference(context : Context) {

    val PREFERENCE_NAME = "SharedPreferenceExample"
    val PREFERENCE_CURRENCY = "Currency"
    val preference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    fun getCurrency() : Int{
        return preference.getInt(PREFERENCE_CURRENCY, 0)
    }

    fun setCurrency(count : Int) {
        val editor = preference.edit()
        editor.putInt(PREFERENCE_CURRENCY, count)
        editor.apply()
    }
}