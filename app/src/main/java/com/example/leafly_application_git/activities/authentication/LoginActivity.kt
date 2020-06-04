package com.example.leafly_application_git.activities.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        //Button to login user
        button_login.setOnClickListener {
            val email = editText_email_login.text.toString()
            val password = editText_password_login.text.toString()
            Log.d("Login", "Login")

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //Authenticating user with FireBase Authentication
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    //If not successful
                    if(!it.isSuccessful) return@addOnCompleteListener

                    //else successful
                    Log.d("Login", "Successfully logged in user with uid: ${it.result?.user?.uid}")
                    //Launching mainactivity if the authentication was succesful
                    val intent = Intent(this, MainActivity::class.java)
                    //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
                //If authentication fails (I.E username or password is wrong)
                .addOnFailureListener{
                    Log.d("Login", "Failed to login user: ${it.message}")
                    Toast.makeText(this, "Failed to login user: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

        //Button that redirects user back to registration
        textView_back_to_registration.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

}