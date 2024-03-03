package com.suhas.activity.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast


class MyService : Service() {

    private val binder = MyServiceBinder()

    inner class MyServiceBinder : Binder() {
        fun getService(): MyService {
            return this@MyService
        }
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Service OnCreate.....", Toast.LENGTH_SHORT).show()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service Started.....", Toast.LENGTH_SHORT).show()
        return START_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "Service Destroy.....", Toast.LENGTH_SHORT).show()

        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder {

        Toast.makeText(this, "Service Bind.....", Toast.LENGTH_SHORT).show()


        return binder
    }

    override fun onRebind(intent: Intent?) {
        Toast.makeText(this, "Service ReBind.....", Toast.LENGTH_SHORT).show()

        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "Service UnBind.....", Toast.LENGTH_SHORT).show()

        return super.onUnbind(intent)
    }

}