package com.pinkfloyded.remindmein

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_config_alarm.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var configAlarmDialog: MaterialDialog
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initConfigAlarmDialog()

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.alarmState.observe(this, Observer { status ->
            minsRemainingTextView.text = (status.millisRemaining / (1000 * 60)).toString()
            nameTextView.text = status.name
            controlButton.text = if (status.isRunning) "Cancel" else "Start"
        })


        nameTextView.setOnClickListener { configAlarmDialog.show() }
        timerBackgroundView.setOnClickListener { configAlarmDialog.show() }
    }


    private fun initConfigAlarmDialog() {
        val dialogCustomView = layoutInflater.inflate(R.layout.dialog_config_alarm, null)
        configAlarmDialog =
                MaterialDialog(this).customView(view = dialogCustomView)
                    .title(text = "New Reminder")
                    .positiveButton(text = "Ok") { dialog ->
                        val name = dialogCustomView.nameEditText.text.toString()
                        val minutesFromNow = dialogCustomView.timeAutocompleteTextView
                            .text.toString().toLong()

                        viewModel.scheduleAlarm(name, minutesFromNow * 60 * 1000)
                    }
    }
}
