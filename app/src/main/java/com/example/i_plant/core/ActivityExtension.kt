package com.example.i_plant.core

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun AppCompatActivity.startWithFinish(intent: Intent) {
    startActivity(intent)
    finish()
}

fun <T> LifecycleOwner.observe(data: LiveData<T>, onObserve: (T) -> Unit) {
    data.observe(this) {
        onObserve(it)
    }
}

