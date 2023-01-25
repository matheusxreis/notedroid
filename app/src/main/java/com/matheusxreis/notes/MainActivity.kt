package com.matheusxreis.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = findNavController(R.id.my_nav_host)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.notes_fragment
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}