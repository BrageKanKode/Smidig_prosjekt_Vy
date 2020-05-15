package com.example.leafly_application_git.fragments.main_page.search

import android.R
import android.widget.ArrayAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.json.JSONArray
import java.io.File
import java.io.IOException
import java.io.InputStream

class SearchViewModel : ViewModel() {

    var arr = arrayListOf<String>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is home search Fragment"
    }
    val text: LiveData<String> = _text



}