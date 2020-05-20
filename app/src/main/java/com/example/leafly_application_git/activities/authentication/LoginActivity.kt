package com.example.leafly_application_git.activities.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_signup.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)




        button_login.setOnClickListener {
            val email = editText_email_login.text.toString()
            val password = editText_password_login.text.toString()
            Log.d("Login", "Login")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    if(!it.isSuccessful) return@addOnCompleteListener
                    //else successful
                    Log.d("Login", "Successfully logged in user with uid: ${it.result?.user?.uid}")

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                .addOnFailureListener{
                    Log.d("Login", "Failed to create user: ${it.message}")
                }
        }

        textView_back_to_registration.setOnClickListener {
            finish()
        }
    }
}