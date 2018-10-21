package com.pinkfloyded.remindmein

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.pinkfloyded.remindmein.model.AlarmStatus

const val EXTRAS_KEY_NAME = "name"
const val EXTRAS_KEY_ALARM_LENGTH = "alarm-length"
const val ALARM_REQUEST_CODE = 666

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val alarmState = MutableLiveData<AlarmStatus>()

    init {
        alarmState.value = AlarmStatus(false,
                                       0,
                                       "Click to schedule alarm")
    }

    fun scheduleAlarm(name: String, millisFromNow: Long) {
        val alarmManager: AlarmManager = getApplication<Application>().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC_WAKEUP,
                         System.currentTimeMillis() + millisFromNow,
                         createAlarmIntent(name, millisFromNow))

        alarmState.value = AlarmStatus(isRunning = true,
                                       millisRemaining = millisFromNow,
                                       name = name)
    }

    private fun createAlarmIntent(name: String, millisFromNow: Long): PendingIntent {

        val intent = Intent(getApplication(), AlarmBroadcastReceiver::class.java)
        intent.putExtra(EXTRAS_KEY_NAME, name)
        intent.putExtra(EXTRAS_KEY_ALARM_LENGTH, millisFromNow)

        return PendingIntent.getBroadcast(getApplication(), ALARM_REQUEST_CODE, intent, 0)
    }
}
