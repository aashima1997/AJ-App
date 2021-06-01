package com.example.ajapp.Menu.ui.Fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import android.widget.ToggleButton
import com.example.ajapp.Menu.BroadcastReceiver.AlertReceiver
import com.example.ajapp.R
import java.util.*


class Alarm_Manager : Fragment(), View.OnClickListener {
    var alarmTimePicker: TimePicker? = null
    var pendingIntent: PendingIntent? = null
    var alarmManager: AlarmManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_alarm__manager, container, false)

        alarmTimePicker =view!!. findViewById<TimePicker>(R.id.timePicker)
        alarmManager = requireActivity().getSystemService(Context.ALARM_SERVICE) as AlarmManager?
val tog:ToggleButton=view!!.findViewById(R.id.toggleButton)
        tog.setOnClickListener(this)
        return view
    }

    private fun startAlarm(view: View) {
        if ((view as ToggleButton).isChecked) {
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker!!.currentHour)
            calendar.set(Calendar.MINUTE, alarmTimePicker!!.currentMinute)
            val intent = Intent(context, AlertReceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

            var time = calendar.timeInMillis - calendar.timeInMillis % 60000

            if (System.currentTimeMillis() > time) {
                if (Calendar.AM_PM == 0)
                    time += 1000 * 60 * 60 * 12
                else
                    time += time + 1000 * 60 * 60 * 24
            }
            /* For Repeating Alarm set time intervals as 10000 like below lines */
            // alarmManager!!.setRepeating(AlarmManager.RTC_WAKEUP, time, 10000, pendingIntent)

            alarmManager!!.set(AlarmManager.RTC, time, pendingIntent);
            Toast.makeText(context, "ALARM ON", Toast.LENGTH_SHORT).show()
        } else {
            alarmManager!!.cancel(pendingIntent)
            Toast.makeText(context, "ALARM OFF", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClick(view: View?) {
        view?.let { startAlarm(it) }
    }

}