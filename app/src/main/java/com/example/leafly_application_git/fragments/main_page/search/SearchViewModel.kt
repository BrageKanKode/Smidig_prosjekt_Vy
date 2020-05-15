package com.example.leafly_application_git.fragments.main_page.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home search Fragment"
    }
    val text: LiveData<String> = _text
}