package com.example.leafly_application_git.activities.trip

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.data.Times

class ChooseTravelAdapter (val time: Times): RecyclerView.Adapter<Custom>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Custom {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return 3
    }

    override fun onBindViewHolder(holder: Custom, position: Int) {
        TODO("Not yet implemented")
    }


}

class Custom(val view: View, var time: Times? = null): RecyclerView.ViewHolder(view) {

}