package com.suhas.activity.activites

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.suhas.activity.R

/*
 This Activity Demonstrate  Activity LifeCycle and State Changes
 */
class LifeCycleActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    //onCreate()
    //This is the first callback and called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_lifecycle_activity)
        Log.i(tag, ":-onCreate()")
        Show("onCreate()")


    }


    // onStart()
    // This callback is called when the activity becomes visible to the user.
    override fun onStart() {
        super.onStart()
        Log.i(tag, ":-onStart()")
        Show("onStart()")
    }


    //onPause()
    //The paused activity does not receive user input and cannot execute any code and called when the current activity is being paused and the previous activity is being resumed.
    override fun onPause() {
        super.onPause()

        Log.i(tag, ":-onPause()")
        Show("onPause()")
    }


    //onResume()
    //This is called when the user starts interacting with the application.
    override fun onResume() {
        super.onResume()

        Log.i(tag, ":-onResume()")
        Show("onResume()")
    }

    //onStop()
    //This callback is called when the activity is no longer visible.
    override fun onStop() {
        super.onStop()

        Log.i(tag, ":-onStop()")
        Show("onStop()")
    }

    //onDestroy()
    //This callback is called before the activity is destroyed by the system.
    override fun onDestroy() {
        super.onDestroy()
        Log.i(tag, ":-onDestroy()")
        Show("onDestroy()")
    }

    //onRestart()
    //This callback is called when the activity restarts after stopping it.
    override fun onRestart() {
        super.onRestart()
        Log.i(tag, ":-onRestart() ")
        Show("onRestart()")
    }

    fun Show(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

}