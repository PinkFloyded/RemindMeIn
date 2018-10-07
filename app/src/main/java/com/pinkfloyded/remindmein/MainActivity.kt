package com.pinkfloyded.remindmein

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        minsRemainingTextView.text = "19"
        nameTextView.text = "Water heater"
        controlButton.text = "Cancel"

        controlButton.setOnClickListener { v ->
            timerBackgroundView.isRunning = !timerBackgroundView.isRunning
        }
    }
}
