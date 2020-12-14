package com.example.i_plant.ui.enrollment.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.i_plant.data.repository.AccessPointDeviceRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class EnrollmentViewModel(
    private val accessPointDeviceRepository: AccessPointDeviceRepository
) : ViewModel() {

    init {
        Log.d("TEST","ThIS FACKING TeST")
//        viewModelScope.launch {
//            accessPointDeviceRepository.scanNearbyAccessPointDevices()
//                .catch { Log.d("TEST", "error thrown ${it.localizedMessage}") }
//                .collect { Log.d("TEST", "access point device test $it") }
//        }
    }


}