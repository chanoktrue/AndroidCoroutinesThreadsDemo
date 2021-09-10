package com.trueddns.homenano.androidcoroutinesthreads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.coroutines.*

//https://youtu.be/05dHmC3POx8

class MainActivity : AppCompatActivity() {

    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)

    }

    fun run(view: View) {
        result.text = "Lading..."
        Log.e("Strat", "Name: ${Thread.currentThread().name }")

        /*
            Default = Cpu work hard
            IO = background (Internet, database)
            Main = UI
         */

        CoroutineScope(Dispatchers.IO).launch {
            download()
            withContext(Dispatchers.Main) {
                result.text = "Unpacking..."
            }
            withContext(Dispatchers.Default) {
                unpack()
            }
            withContext(Dispatchers.Main) {
                result.text = "Done"
                Log.e("Main", "Name: ${Thread.currentThread().name }")
            }
        }


    }

    private suspend fun download() {
        delay(5000)
        Log.e("Downlad", "Name: ${Thread.currentThread().name }")
    }

    private suspend fun unpack() {
        for (i in 1 until 10) {
            delay(100)
            Log.e("Unpack", "Name: ${Thread.currentThread().name }")
        }
    }

}