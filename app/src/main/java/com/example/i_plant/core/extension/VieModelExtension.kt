package com.example.i_plant.core.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> ViewModel.launchAndCollect(
    flow: Flow<T>,
    collectBlock: (T) -> Unit,
) {
    viewModelScope.launch {
        flow.collect {
           collectBlock(it)
        }
    }
}