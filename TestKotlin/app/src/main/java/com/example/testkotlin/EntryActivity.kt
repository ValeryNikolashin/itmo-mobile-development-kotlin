package com.example.testkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class EntryActivity : AppCompatActivity() {
    private val pointsPreferencesName = "points"
    private val pointsPreferences by lazy {
        getSharedPreferences(
            pointsPreferencesName,
            Context.MODE_PRIVATE
        )
    }
    private val drawerLayout: DrawerLayout by lazy { findViewById(R.id.drawerLayout) }
    private val navView: NavigationView by lazy { findViewById(R.id.nav_view) }
    private val btnPoints: Button by lazy { findViewById(R.id.btnPoints) }
    private val btnPlus: Button by lazy { findViewById(R.id.btnPlus) }
    private val btnMinus: Button by lazy { findViewById(R.id.btnMinus) }
    private lateinit var toggle: ActionBarDrawerToggle
    private var points: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        Log.i("EntryActivity", "EntryActivity was created.")
        initPointsFromPreferences()

        setListeners()
    }

    private fun initPointsFromPreferences() {
        points = pointsPreferences.getInt(pointsPreferencesName, 0)
        btnPoints.text = points.toString()
    }

    private fun setListeners() {
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_task1 -> {
                    val message = "Move to MainActivity."
                    Log.i("EntryActivity", message)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_task2 -> {
                    val message = "Move to Task2Activity."
                    Log.i("EntryActivity", message)
                    val intent = Intent(this, Task2Activity::class.java)
                    startActivity(intent)
                }
            }
            true
        }

        btnPlus.setOnClickListener { changePoints(1) }
        btnMinus.setOnClickListener { changePoints(-1) }
    }

    private fun changePoints(increment: Int) {
        points += increment
        if (points < 0) points = 0
        btnPoints.text = points.toString()
        savePointsIntoPreferences()
    }

    @SuppressLint("CommitPrefEdits")
    private fun savePointsIntoPreferences() {
        val editor = pointsPreferences.edit()
        editor.putInt(pointsPreferencesName, points)
        editor.apply()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }
}