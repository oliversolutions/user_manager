package com.oliversolutions.dev.usermanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        setupActionBarWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        return this.findNavController(R.id.nav_host_fragment_activity_main).navigateUp()
    }
}