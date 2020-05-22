package com.example.leafly_application_git.activities.search.payment

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.MainActivity
import kotlinx.android.synthetic.main.activity_confirmation.view.*

class ConfirmationAdapter (
    private val departure: String,
    private val arrival: String,
    private val departureTime: String,
    private val price: String,
    private val points: String
): RecyclerView.Adapter<ViewConfirmation>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewConfirmation {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_confirmation, parent, false)

        return ViewConfirmation(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewConfirmation, position: Int) {

        holder.view.textView_confirmation_from.text = "Fra $departure til $arrival"
        holder.view.textView_confirmation_departure_time.text = "I dag, $departureTime"
        holder.view.textView_confirmation_price1.text = "$price,-"
        holder.view.textView_confirmation_price2.text = "$price ,-"
        holder.view.textView_confirmation_points.text = points
    }

}

class ViewConfirmation(val view: View): RecyclerView.ViewHolder(view){
    init {
        view.btnToHome.setOnClickListener{
            val intent = Intent(view.context, MainActivity::class.java)

            view.context.startActivity(intent)
        }
    }
}
