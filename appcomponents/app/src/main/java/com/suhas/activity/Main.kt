package com.suhas.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.suhas.activity.activites.BroadcastReciverExample
import com.suhas.activity.activites.ContentResolverExample
import com.suhas.activity.activites.LifeCycleActivity
import com.suhas.activity.activites.ProviderExample
import com.suhas.activity.activites.ServiceExample

class Main : AppCompatActivity() {

    private lateinit var serviceExample: Button
    private lateinit var activityExample: Button
    private lateinit var reciverExample: Button
    private lateinit var providerExmple: Button
    private lateinit var resolverExmple: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        serviceExample = findViewById(R.id.service_example)
        activityExample = findViewById(R.id.activity_example)
        reciverExample = findViewById(R.id.broadcast_receivers_example)
        providerExmple = findViewById(R.id.content_providers_example)
        resolverExmple = findViewById(R.id.content_resolver_example)


        resolverExmple.setOnClickListener {
            startActivity(Intent(this@Main, ContentResolverExample::class.java))
        }

        serviceExample.setOnClickListener {
            startActivity(Intent(this@Main, ServiceExample::class.java))
        }
        activityExample.setOnClickListener {
            startActivity(Intent(this@Main, LifeCycleActivity::class.java))
        }
        reciverExample.setOnClickListener {
            startActivity(
                Intent(
                    this@Main,
                    BroadcastReciverExample::class.java
                )
            )
        }
        providerExmple.setOnClickListener {
            startActivity(Intent(this@Main, ProviderExample::class.java))
        }
    }
}