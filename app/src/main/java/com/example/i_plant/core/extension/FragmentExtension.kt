package com.example.i_plant.core.extension

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe

fun <T> Fragment.observe(data: LiveData<T>, onObserve: (T) -> Unit) {
    data.observe(this) {
        onObserve(it)
    }
}

fun Fragment.showToast(message:String, duration :Int = Toast.LENGTH_LONG){
    Toast.makeText(requireContext(),message,duration).show()
}