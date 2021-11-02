package com.example.testkotlin

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testkotlin.models.Fighter
import com.example.testkotlin.sequences.FibonacciSequence
import com.example.testkotlin.sequences.NaturalSequence
import com.example.testkotlin.sequences.NumberSequence
import com.example.testkotlin.sequences.SyracuseSequence

class DetailActivity : AppCompatActivity() {
    private val tvFighterName: TextView by lazy { findViewById(R.id.tvFighterName) }
    private val tvAboutFighter: TextView by lazy { findViewById(R.id.tvAboutFighter) }
    private val ivFighter: ImageView by lazy { findViewById(R.id.ivFighter) }
    private val ivFighterType: ImageView by lazy { findViewById(R.id.ivFighterType) }

    private val tvNaturalNumber: TextView by lazy { findViewById(R.id.tvNaturalNumber) }
    private val tvFibonacciNumber: TextView by lazy { findViewById(R.id.tvFibonacciNumber) }
    private val tvColNumber: TextView by lazy { findViewById(R.id.tvColNumber) }
    private val btnNaturalInc: Button by lazy { findViewById(R.id.btnNaturalInc) }
    private val btnFibonacciInc: TextView by lazy { findViewById(R.id.btnFibonacciInc) }
    private val btnColInc: TextView by lazy { findViewById(R.id.btnColInc) }

    private val startNumberForSyracuseSequence = 650
    private val naturalSequence: NumberSequence = NaturalSequence()
    private val fibonacciSequence: NumberSequence = FibonacciSequence()
    private val syracuseSequence: NumberSequence = SyracuseSequence(startNumberForSyracuseSequence)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setFighterInfo()
        initCounters()
        setListeners()
    }

    private fun setFighterInfo() {
        val fighter = intent.getSerializableExtra("fighter") as Fighter
        tvFighterName.text = fighter.toString()
        tvAboutFighter.text = fighter.description
        tvAboutFighter.movementMethod = ScrollingMovementMethod()
        ivFighter.setImageResource(fighter.imageSource)
        ivFighterType.setImageResource(fighter.type.getIconSource())
    }

    private fun initCounters() {
        tvNaturalNumber.text = naturalSequence.getCurrent().toString()
        tvFibonacciNumber.text = fibonacciSequence.getCurrent().toString()
        tvColNumber.text = syracuseSequence.getCurrent().toString()
    }

    private fun setListeners() {
        btnNaturalInc.setOnClickListener {
            tvNaturalNumber.text = naturalSequence.getNext().toString()
        }
        btnFibonacciInc.setOnClickListener {
            tvFibonacciNumber.text = fibonacciSequence.getNext().toString()
        }
        btnColInc.setOnClickListener { tvColNumber.text = syracuseSequence.getNext().toString() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("naturalNumber", tvNaturalNumber.text.toString().toInt())
            putInt("fibonacciNumber", tvFibonacciNumber.text.toString().toInt())
            putInt("colNumber", tvColNumber.text.toString().toInt())
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val naturalNumber = savedInstanceState.getInt("naturalNumber")
        val fibonacciNumber = savedInstanceState.getInt("fibonacciNumber")
        val colNumber = savedInstanceState.getInt("colNumber")

        tvNaturalNumber.text = naturalNumber.toString()
        tvFibonacciNumber.text = fibonacciNumber.toString()
        tvColNumber.text = colNumber.toString()

        naturalSequence.initBy(naturalNumber)
        fibonacciSequence.initBy(fibonacciNumber)
        syracuseSequence.initBy(colNumber)
    }
}