package com.example.leafly_application_git.ui.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TripsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is trips Fragment"
    }
    val text: LiveData<String> = _text
}