package com.example.leafly_application_git.activities.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.leafly_application_git.R
import com.example.leafly_application_git.activities.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.zip.Inflater

class SignUpActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)


        //Button to register a new user, calls on performRegister
        button_register.setOnClickListener {
            performRegister()
        }

        //Button to redirect to Login page
        textView_already_have_account.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    //Function to register new user to Firebase, and authenticate their details
    private fun performRegister(){
        Log.d("Register", "Register")

        val email = editText_email_signup.text.toString()
        val password = editText_password_signup.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter text and email", Toast.LENGTH_SHORT).show()
        }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                //Checks if registration is complete
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener


                //else successful
                Log.d("Signup", "Successfully logged in created user with uid: ${it.result?.user?.uid}")

            }

                //Checks if registration was success and the calls on a method to save the given user to the Firebase database
            .addOnSuccessListener {
                saveUserToFirebaseDatabase()
            }

                //Checks if registration fails
            .addOnFailureListener{
                Log.d("Signup", "Failed to create user: ${it.message}")
                Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_SHORT).show()
            }
    }

    //Function to save user to Firebase database
    private fun saveUserToFirebaseDatabase(){
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val ref = FirebaseDatabase.getInstance().getReference("/users/$uid")

        //Setting what should be saved under user table in database -- id, username, balance and progress
        val user = User(uid, editText_username_signup.text.toString(), 300, 0)

        ref.setValue(user)
            .addOnSuccessListener {
                Log.d("Signup", "Finally saved the user to db")

                val intent = Intent(this, MainActivity::class.java)
                //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
    }


    //Options menu that redirects you to home menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu, menu)
        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.redirect_home_btn -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}


//User class that connects user to firebase database
class User(val uid: String, val username: String, val balance: Int, val progress: Int){
    constructor() : this("", "", 0, 0)
}