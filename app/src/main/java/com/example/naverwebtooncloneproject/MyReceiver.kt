package com.example.naverwebtooncloneproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent!!.action.equals(Intent.ACTION_BATTERY_CHANGED)) {
            Toast.makeText(context, "스크린 OFF", Toast.LENGTH_SHORT).show()
            Log.d("receiver", "Screen off...")
        }
    }
}