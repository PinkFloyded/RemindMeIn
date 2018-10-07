package com.pinkfloyded.remindmein

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var configAlarmDialog: MaterialDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initConfigAlarmDialog()

        minsRemainingTextView.text = "19"
        nameTextView.text = "Water heater"
        controlButton.text = "Cancel"

        nameTextView.setOnClickListener { configAlarmDialog.show() }
        timerBackgroundView.setOnClickListener { configAlarmDialog.show() }

        controlButton.setOnClickListener {
            timerBackgroundView.isRunning = !timerBackgroundView.isRunning
        }
    }


    private fun initConfigAlarmDialog() {
        val dialogCustomView = layoutInflater.inflate(R.layout.dialog_config_alarm, null)
        configAlarmDialog =
                MaterialDialog(this).customView(view = dialogCustomView)
                    .positiveButton(text = "Ok") {
                        Toast.makeText(this, "Positive Button", Toast.LENGTH_SHORT)
                            .show()
                    }
    }
}
