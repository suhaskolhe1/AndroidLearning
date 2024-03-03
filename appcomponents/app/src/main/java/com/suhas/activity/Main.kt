package com.suhas.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.suhas.activity.activites.LifeCycleActivity
import com.suhas.activity.activites.ServiceExample
import com.suhas.activity.activites.ReciverExample

class Main : AppCompatActivity() {

    private lateinit var serviceExample: Button
    private lateinit var activityExample: Button
    private lateinit var reciverExample: Button
    private lateinit var providerExmple: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceExample = findViewById(R.id.service_example)
        activityExample = findViewById(R.id.activity_example)
        reciverExample=findViewById(R.id.broadcast_receivers_example)
        providerExmple=findViewById(R.id.content_providers_example)



        serviceExample.setOnClickListener {
            startActivity(Intent(this@Main, ServiceExample::class.java))
        }
        activityExample.setOnClickListener {
            startActivity(Intent(this@Main, LifeCycleActivity::class.java))
        }
        reciverExample.setOnClickListener { startActivity(Intent(this,ReciverExample::class.java)) }

    }
}