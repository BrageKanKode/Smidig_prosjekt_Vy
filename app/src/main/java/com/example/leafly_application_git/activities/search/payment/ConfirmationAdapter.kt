package com.example.leafly_application_git.activities.search.payment

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.CombinedFunctionsClass
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.MainActivity
import kotlinx.android.synthetic.main.activity_confirmation_v_2.view.*
import java.util.*

class ConfirmationAdapter (
    private val departure: String,
    private val arrival: String,
    private val departureTime: String,
    private val points: String
): RecyclerView.Adapter<ViewConfirmation>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewConfirmation {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_confirmation_v_2, parent, false)

        if(!CombinedFunctionsClass.verifyIfUserIsLoggedIn()){
            CombinedFunctionsClass.removeStuff(view, "Confirmation")
        }

        return ViewConfirmation(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewConfirmation, position: Int) {

        val date = Calendar.getInstance()
        val month = date.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("NOR"))
        val dayInNumber = date.get(Calendar.DAY_OF_MONTH)
        val dayString = date.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale("NOR"))


        holder.view.textView_new_confirmation_from.text = "Fra $departure til"
        holder.view.textView_new_confirmation_to.text = arrival
        holder.view.textView_new_confirmation_departure_time.text = departureTime
        holder.view.textView_confirmation_points_new.text = "+ $points"
        holder.view.textView_confirmation_date.text = "$dayInNumber $month"
        holder.view.textView_new_confirmation_day.text = dayString
    }

}

class ViewConfirmation(val view: View): RecyclerView.ViewHolder(view){
    init {
        view.button_to_points_overview.setOnClickListener{
            val intent = Intent(view.context, MainActivity::class.java)

            view.context.startActivity(intent)
        }
    }
}
