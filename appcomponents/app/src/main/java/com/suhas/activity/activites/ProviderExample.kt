package com.suhas.activity.activites

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.suhas.activity.R
import com.suhas.activity.providers.MyProvider
import java.lang.StringBuilder

class ProviderExample : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c_provider)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        return super.onTouchEvent(event)
    }

    fun onClickAddDetails(view: View?) {
        val values = ContentValues()
        values.put(
            MyProvider.name,
            (findViewById<View>(R.id.textName) as EditText).text.toString()
        )
        contentResolver.insert(MyProvider.CONTENT_URI, values)
        Toast.makeText(baseContext, "New Record Inserted", Toast.LENGTH_LONG).show()
    }


    @SuppressLint("Range")
    fun onClickShowDetails(view: View?) {
        val resultView = findViewById<View>(R.id.res) as TextView

        val cursor = contentResolver.query(
            Uri.parse("content://${MyProvider.PROVIDER_NAME}/users"),
            null,
            null,
            null,
            null
        )

        if(cursor!!.moveToFirst()){
            val strBuild=StringBuilder()
            while (!cursor.isAfterLast){
                strBuild.append("""${cursor.getString(cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}""".trimIndent())
                cursor.moveToNext()
            }
            resultView.text=strBuild
        }else{
            resultView.text="No Records Found"
        }
        cursor.close()
    }
}