package com.example.i_plant.core

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

fun AppCompatActivity.startWithFinish(intent: Intent){
    startActivity(intent)
    finish()
}