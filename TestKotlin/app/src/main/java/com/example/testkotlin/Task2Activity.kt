package com.example.testkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import java.util.concurrent.atomic.AtomicBoolean

class Task2Activity : AppCompatActivity() {
    private val startCountersValue = 0
    private val delayAddition = 100
    private var startThread1CounterDelay: Long = 400
    private var startThread2CounterDelay: Long = 600

    private lateinit var tvThread1Counter: TextView
    private lateinit var tvThread2Counter: TextView

    private lateinit var btnThreadRun: Button
    private lateinit var btnThreadStop: Button
    private lateinit var btnThreadReset: Button

    private lateinit var iBtnIncreaseThread1CounterSpeed: ImageButton
    private lateinit var iBtnDecreaseThread1CounterSpeed: ImageButton
    private lateinit var iBtnIncreaseThread2CounterSpeed: ImageButton
    private lateinit var iBtnDecreaseThread2CounterSpeed: ImageButton

    private var thread1Counter: Int = startCountersValue
    private var thread2Counter: Int = startCountersValue

    @Volatile
    private var thread1CounterDelay = startThread1CounterDelay
    @Volatile
    private var thread2CounterDelay = startThread2CounterDelay

    private var areThreadsRunning: AtomicBoolean = AtomicBoolean(false)
    private var isActivityDestroyed: AtomicBoolean = AtomicBoolean(false)

    private val thread1: Thread = Thread {
        while (true) {
            Thread.sleep(thread1CounterDelay)

            if (areThreadsRunning.get()) {
                runOnUiThread {
                    thread1Counter++
                    tvThread1Counter.text = "$thread1Counter"
                }
            }

            if (isActivityDestroyed.get()) {
                Log.d(TASK2_ACTIVITY_TAG, "Thread1 finished.")
                break
            }
            Log.d(TASK2_ACTIVITY_TAG, "Thread1 is working.")
        }
    }

    private val thread2: Thread = Thread {
        while (true) {
            Thread.sleep(thread2CounterDelay)

            if (areThreadsRunning.get()) {
                runOnUiThread {
                    thread2Counter++
                    tvThread2Counter.text = "$thread2Counter"
                }
            }

            if (isActivityDestroyed.get()) {
                Log.d(TASK2_ACTIVITY_TAG, "Thread2 finished.")
                break
            }
            Log.d(TASK2_ACTIVITY_TAG, "Thread2 is working.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)

        init()
        setListeners()
    }

    private fun init() {
        tvThread1Counter = findViewById(R.id.tvThread1Counter)
        tvThread2Counter = findViewById(R.id.tvThread2Counter)

        tvThread1Counter.text = "$thread1Counter"
        tvThread2Counter.text = "$thread2Counter"

        btnThreadRun = findViewById(R.id.btnRunThreads)
        btnThreadStop = findViewById(R.id.btnStopThreads)
        btnThreadReset = findViewById(R.id.btnResetThreads)

        iBtnIncreaseThread1CounterSpeed = findViewById(R.id.iBtnIncreaseThread1Counter)
        iBtnDecreaseThread1CounterSpeed = findViewById(R.id.iBtnDecreaseThread1Counter)
        iBtnIncreaseThread2CounterSpeed = findViewById(R.id.iBtnIncreaseThread2Counter)
        iBtnDecreaseThread2CounterSpeed = findViewById(R.id.iBtnDecreaseThread2Counter)
    }

    private fun setListeners() {
        btnThreadRun.setOnClickListener {
            if (!thread1.isAlive)
                thread1.start()
            if (!thread2.isAlive)
                thread2.start()

            areThreadsRunning.set(true)
        }

        btnThreadStop.setOnClickListener {
            areThreadsRunning.set(false)
        }

        btnThreadReset.setOnClickListener {
            areThreadsRunning.set(false)

            thread1Counter = startCountersValue
            thread2Counter = startCountersValue

            thread1CounterDelay = startThread1CounterDelay
            thread2CounterDelay = startThread2CounterDelay

            tvThread1Counter.text = "$thread1Counter"
            tvThread2Counter.text = "$thread2Counter"
        }

        iBtnIncreaseThread1CounterSpeed.setOnClickListener {
            if (areThreadsRunning.get() && thread1CounterDelay > delayAddition) {
                thread1CounterDelay -= delayAddition
            }
        }

        iBtnDecreaseThread1CounterSpeed.setOnClickListener {
            if (areThreadsRunning.get()) {
                thread1CounterDelay += delayAddition
            }
        }

        iBtnIncreaseThread2CounterSpeed.setOnClickListener {
            if (areThreadsRunning.get() && thread2CounterDelay > delayAddition) {
                thread2CounterDelay -= delayAddition
            }
        }

        iBtnDecreaseThread2CounterSpeed.setOnClickListener {
            if (areThreadsRunning.get()) {
                thread2CounterDelay += delayAddition
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        isActivityDestroyed.set(true)
    }
}