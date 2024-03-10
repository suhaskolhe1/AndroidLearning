package com.suhas.activity.activites

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.suhas.activity.R

class ContentResolverExample : AppCompatActivity() {
    val uri = Uri.parse("content://com.suhas.activity.provider/users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_reslover)
    }

    @SuppressLint("Range")
    fun onClickShowDetails() {
        val resultView = findViewById<View>(R.id.res) as TextView

        val cursor = contentResolver.query(
            uri, null, null, null, null
        )
        if (cursor!!.moveToFirst()) {
            val strBuild = StringBuilder()
            while (!cursor.isAfterLast) {
                strBuild.append(
                    """${cursor.getString(cursor.getColumnIndex("id"))}-${
                        cursor.getString(
                            cursor.getColumnIndex(
                                "name"
                            )
                        )
                    }
                """.trimIndent()
                )
                cursor.moveToNext()
            }
            resultView.text = strBuild

        } else {
            resultView.text = "No Data Found!"
        }
        cursor.close()
    }
}