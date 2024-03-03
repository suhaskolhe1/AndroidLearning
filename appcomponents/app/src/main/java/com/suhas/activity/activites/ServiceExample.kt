package com.suhas.activity.activites

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.suhas.activity.R
import com.suhas.activity.services.MyService

class ServiceExample : AppCompatActivity() {
    private var myBoundService: MyService? = null
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            myBoundService = (p1 as MyService.MyServiceBinder).getService()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            myBoundService = null
        }

    }

    private lateinit var start: Button
    private lateinit var stop: Button
    private lateinit var bind: Button
    private lateinit var unBind: Button
    private lateinit var reBind: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle_service)
        start = findViewById(R.id.start_service)
        stop = findViewById(R.id.stop_service)
        bind = findViewById(R.id.bind_service)
        unBind = findViewById(R.id.unbind_service)
        reBind = findViewById(R.id.rebind_service)

        val i = Intent(baseContext, MyService::class.java)

        start.setOnClickListener {
            startService(i)
        }
        stop.setOnClickListener {
            stopService(i)
        }
        bind.setOnClickListener {
            bindService(i, serviceConnection, Context.BIND_AUTO_CREATE)
        }
        unBind.setOnClickListener {
            unbindService(serviceConnection)
        }
        reBind.setOnClickListener {
            bindService(i, serviceConnection, BIND_AUTO_CREATE)
        }

    }
}