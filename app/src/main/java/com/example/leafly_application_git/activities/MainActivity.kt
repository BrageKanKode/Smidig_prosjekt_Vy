package com.example.leafly_application_git.activities

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.leafly_application_git.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_search,
            R.id.navigation_trips,
            R.id.navigation_miljopoints
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }


    //Does not run with readJson(), need to be fixed.

    /*
    //FileName
    val fileName = cacheDir.absolutePath+"/dummydata.json"

    private fun readJSONfromFile(f:String) {

        //Creating a new Gson object to read data
        var gson = Gson()
        //Read the PostJSON.json file
        val bufferedReader: BufferedReader = File(f).bufferedReader()
        // Read the text from buffferReader and store in String variable
        val inputString = bufferedReader.use { it.readText() }

        //Convert the Json File to Gson Object
        var post = gson.fromJson(inputString, Location::class.java)

        }
     */
}
