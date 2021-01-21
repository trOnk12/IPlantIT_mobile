package com.example.i_plant.core.extension

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe

fun AppCompatActivity.startAndFinishCurrentActivity(intent: Intent) {
    startActivity(intent)
    finish()
}

fun <T> LifecycleOwner.observe(data: LiveData<T>, onObserve: (T) -> Unit) {
    data.observe(this) {
        onObserve(it)
    }
}

fun AppCompatActivity.showToast(message:String, duration :Int = Toast.LENGTH_LONG){
    Toast.makeText(this,message,duration).show()
}

