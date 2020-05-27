package com.example.leafly_application_git.activities.search.payment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.leafly_application_git.R
import com.example.leafly_application_git.TestClass.removeStuff
import com.example.leafly_application_git.TestClass.verifyIfUserIsLoggedIn
import com.example.leafly_application_git.activities.authentication.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.*
import kotlinx.android.synthetic.main.payment_with_points_popup.view.*
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.cardView_confirmation
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.imageView_travel_order_leaf
import kotlinx.android.synthetic.main.activity_travel_order_overview.view.textView_points

class OrderDetailsAdapter(
    private val departure: String,
    private val arrival: String,
    private val departureTime: String,
    private val price: String,
    private val points: String
) : RecyclerView.Adapter<ViewOrderDetails>() {

    internal var user: User? = null

    @SuppressLint("SetTextI18n", "InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewOrderDetails {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_travel_order_overview, parent, false)

        view.imageView_pay_with_points.setOnClickListener {
            val payment_points = LayoutInflater.from(parent.context)
                .inflate(R.layout.payment_with_points_popup, null)
            val diaBuilder =
                AlertDialog.Builder(parent.context).setView(payment_points)

            val testAlertDialog = diaBuilder.show()

            val ticketPurchasePrice = 18000

            payment_points.textView_price_in_points.text = ticketPurchasePrice.toString()
            payment_points.textView_from_to_values.text = "Fra $departure til $arrival"

            var ref = FirebaseDatabase.getInstance().getReference("/users")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
            val menuListener = object : ValueEventListener {
                @SuppressLint("SetTextI18n")
                override fun onDataChange(p0: DataSnapshot) {
                    user = p0.getValue(User::class.java)
                    var balance = user?.balance
                    var progress = user?.progress
                    var usedHistory =
                        "Du kjøpte Bilett \nFor $ticketPurchasePrice miljøpoeng"
                    payment_points.textView_current_saldo.text = balance.toString()

                    val newBalance = balance?.minus(ticketPurchasePrice)

                    if (newBalance!! < 0){
                        payment_points.textView_new_saldo.text = "Not Enough"
                    } else {
                        payment_points.textView_new_saldo.text = newBalance.toString()
                    }

                    payment_points.button_pay.setOnClickListener {

                        if (balance!! >= ticketPurchasePrice) {
                            balance = balance?.minus(ticketPurchasePrice)
                            payment_points.textView_current_saldo.text = balance.toString()
                            ref.child("/balance").setValue(balance)

                            progress = progress?.plus(1)
                            ref.child("/progress").setValue(progress)

                            var refUsedHistory = ref.child("/usedHistory")
                            refUsedHistory.push().setValue(usedHistory)

                            val intent = Intent(view.context, SplashScreenPaymentActivity::class.java)

                            intent.putExtra(ViewOrderDetails.DEPARTURE_KEY, departure)
                            intent.putExtra(ViewOrderDetails.ARRIVAL_KEY, arrival)
                            intent.putExtra(ViewOrderDetails.DEPARTURE_TIME_KEY, departureTime)
                            intent.putExtra(ViewOrderDetails.DETAILS_PRICE_KEY, price)
                            intent.putExtra(ViewOrderDetails.DETAILS_POINTS_KEY, points)


                            view.context.startActivity(intent)

                            testAlertDialog.dismiss()
                        } else {
                            Toast.makeText(
                                parent.context, "You do not have enough",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }
            }
            ref.addListenerForSingleValueEvent(menuListener)
        }

        if(!verifyIfUserIsLoggedIn()){
            removeStuff(view, "OrderDetails")
        }

        return ViewOrderDetails(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewOrderDetails, position: Int) {

        holder.view.textView_from.text = "Fra $departure til $arrival"
        holder.view.textView_departure_time.text = "I dag, $departureTime"
        holder.view.textView_price_one.text = "$price,-"
        holder.view.textView_price_two.text = "$price,-"
        holder.view.textView_points.text = "du oppnår $points miljøpoeng for denne reisen"

        holder.departure = departure
        holder.arrival = arrival
        holder.departureTime = departureTime
        holder.price = price
        holder.points = points

    }
}

class ViewOrderDetails(
    val view: View,
    var departure: String? = null,
    var arrival: String? = null,
    var departureTime: String? = null,
    var price: String? = null,
    var points: String? = null
): RecyclerView.ViewHolder(view) {
    companion object{
        const val DEPARTURE_KEY = "DEPARTURE"
        const val ARRIVAL_KEY = "ARRIVAL"
        const val DEPARTURE_TIME_KEY = "DEPARTURE_TIME"
        const val DETAILS_PRICE_KEY = "DETAIL_PRICE"
        const val DETAILS_POINTS_KEY = "DETAIL_POINTS"
    }
    init {
        view.btnToPayment.setOnClickListener {
            val intent = Intent(view.context, SplashScreenPaymentActivity::class.java)

            intent.putExtra(DEPARTURE_KEY, departure)
            intent.putExtra(ARRIVAL_KEY, arrival)
            intent.putExtra(DEPARTURE_TIME_KEY, departureTime)
            intent.putExtra(DETAILS_PRICE_KEY, price)
            intent.putExtra(DETAILS_POINTS_KEY, points)


            view.context.startActivity(intent)
        }
    }

}