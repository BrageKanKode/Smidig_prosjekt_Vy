package com.example.leafly_application_git.activities.search.payment

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_confirmation.*
import kotlinx.android.synthetic.main.activity_confirmation.view.*
import kotlinx.android.synthetic.main.activity_confirmation_v_2.view.*

class ConfirmationAdapter (
    private val departure: String,
    private val arrival: String,
    private val departureTime: String,
    private val price: String,
    private val points: String
): RecyclerView.Adapter<ViewConfirmation>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewConfirmation {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_confirmation_v_2, parent, false)



        /*
        if(!verifyIfUserIsLoggedIn()){
            removeStuff(view)
        }

         */
        return ViewConfirmation(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewConfirmation, position: Int) {

        holder.view.textView_new_confirmation_from.text = "Fra $departure til"
        holder.view.textView_new_confirmation_to.text = arrival
        holder.view.textView_new_confirmation_departure_time.text = departureTime
    }

}



/*
private fun removeStuff(view: View) {
    view.textView_points.visibility = View.GONE
    view.textView_confirmation_points.visibility = View.GONE
    view.imageView_travel_order_leaf.visibility = View.GONE
    view.cardView_confirmation.visibility = View.GONE

}

 */

class ViewConfirmation(val view: View): RecyclerView.ViewHolder(view){
    init {
        view.button_to_points_overview.setOnClickListener{
            val intent = Intent(view.context, MainActivity::class.java)

            view.context.startActivity(intent)
        }
    }
}
