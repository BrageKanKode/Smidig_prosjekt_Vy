package com.example.leafly_application_git.activities.authentication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
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