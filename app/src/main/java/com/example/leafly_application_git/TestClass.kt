package com.example.leafly_application_git

import com.google.firebase.auth.FirebaseAuth

object TestClass {
    fun incrementProgress(progressAmount : Int): Int {


        return 1
    }


    //Checks with the Firebase Authentication if user is logged in or not
    fun verifyIfUserIsLoggedIn(): Boolean {
        val uid = FirebaseAuth.getInstance().uid
        //If user is not logged in, then return null
        return uid != null
    }
}