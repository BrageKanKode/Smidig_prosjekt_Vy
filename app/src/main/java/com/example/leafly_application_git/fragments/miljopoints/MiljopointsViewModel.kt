package com.example.leafly_application_git.fragments.miljopoints

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MiljopointsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Miljopoints Fragment"
    }
    val text: LiveData<String> = _text
}