package com.example.leafly_application_git.fragments.main_page.trips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TripsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is trips Fragment"
    }
    val text: LiveData<String> = _text
}