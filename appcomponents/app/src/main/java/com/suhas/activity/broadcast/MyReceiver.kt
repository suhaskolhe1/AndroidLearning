package com.suhas.activity.broadcast

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(p0: Context?, p1: Intent?) {
        val isAirplaneModeEnabled = p1?.getBooleanExtra("EXTRA_AIRPLANE_MODE", false)

        if ((isAirplaneModeEnabled != null) && isAirplaneModeEnabled) {
           Toast.makeText(p0,"Airplane Mode ON",Toast.LENGTH_SHORT).show()

        } else {

            Toast.makeText(p0,"Airplane Mode Off",Toast.LENGTH_SHORT).show()

        }
    }
}