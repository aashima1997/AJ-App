package com.example.ajapp.Menu.ui.Activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.ajapp.R
import com.google.android.material.navigation.NavigationView

class NavDrawer : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_drawer)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)


        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.loc,
            R.id.tts,
            R.id.imageEdit,
            R.id.tabss,
            R.id.PDFView,
            R.id.dt,
            R.id.attend1, R.id.weather1, R.id.home1, R.id.alarm, R.id.settings
        ), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
       toolbar.setTitleTextColor(Color.BLACK)
        navView.setupWithNavController(navController)

        toggle = ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu1, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    }
