package com.example.testkotlin

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.testkotlin.datasources.FighterDataSource
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private var fighters = FighterDataSource().fetchData()
    private val coordinatorLayout: CoordinatorLayout by lazy { findViewById(R.id.coordinator) }
    private val lvFighters: ListView by lazy { findViewById(R.id.lvFighters) }
    private val btnHideList: Button by lazy { findViewById(R.id.btnHideList) }
    private val tvLabel: TextView by lazy { findViewById(R.id.tvLabel) }
    private val etLabelEditor: EditText by lazy { findViewById(R.id.etLabelEditor) }
    private val floatingBtn: FloatingActionButton by lazy { findViewById(R.id.floatingBtn) }
    private val colorSwitch: Switch by lazy { findViewById(R.id.colorSwitch) }
    private val btnToast: Button by lazy { findViewById(R.id.btnToast) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("MainActivity", "MainActivity was created.")
        setListeners()
    }

    private fun setListeners() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, fighters)
        lvFighters.adapter = adapter
        lvFighters.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            val fighter = fighters[position]
            intent.putExtra("fighter", fighter)
            startActivity(intent)
        }

        btnHideList.setOnClickListener {
            if (lvFighters.visibility == View.VISIBLE) {
                Log.i("MainActivity", "ListView was hidden.")
                lvFighters.visibility = View.INVISIBLE
            } else {
                Log.i("MainActivity", "ListView was displayed.")
                lvFighters.visibility = View.VISIBLE
            }
        }

        floatingBtn.setOnClickListener {
            val textEditorText = etLabelEditor.text
            val editMessage = "Text changed from '${tvLabel.text}' to '$textEditorText'."
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                Snackbar.make(
                    coordinatorLayout,
                    editMessage,
                    Snackbar.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(this, editMessage, Toast.LENGTH_LONG).show()
            }
            tvLabel.text = textEditorText
        }

        colorSwitch.setOnCheckedChangeListener { _, b ->
            tvLabel.setTextColor(if (b) Color.BLUE else Color.BLACK)
            Log.i("MainActivity", "LABEL color was changed.")
        }

        btnToast.setOnClickListener {
            val message = "Я тостик!"
            Log.i("MainActivity", "TOAST was displayed with message: $message.")
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("fightersVisibility", lvFighters.visibility)
            putBoolean("isColorSwitchChecked", colorSwitch.isChecked)
            putString("labelText", tvLabel.text.toString())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val fightersVisibility = savedInstanceState.getInt("fightersVisibility")
        val isColorSwitchChecked = savedInstanceState.getBoolean("isColorSwitchChecked")
        val labelText = savedInstanceState.getString("labelText")

        lvFighters.visibility = fightersVisibility
        colorSwitch.isChecked = isColorSwitchChecked
        tvLabel.text = labelText
    }
}