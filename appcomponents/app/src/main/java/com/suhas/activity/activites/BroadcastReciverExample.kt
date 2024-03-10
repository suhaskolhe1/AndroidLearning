package com.suhas.activity.activites

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.suhas.activity.R
import com.suhas.activity.broadcast.MyReceiver

class BroadcastReciverExample : AppCompatActivity() {
    private val reciver: MyReceiver = MyReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reciver_example)
        val intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(reciver, intentFilter)
    }
}