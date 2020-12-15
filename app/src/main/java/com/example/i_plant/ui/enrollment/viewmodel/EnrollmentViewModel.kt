package com.example.i_plant.ui.enrollment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.i_plant.data.model.AccessPointDevice
import com.example.i_plant.data.repository.AccessPointDeviceRepository
import com.example.i_plant.data.repository.AccessPointResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class EnrollmentViewModel(
    private val accessPointDeviceRepository: AccessPointDeviceRepository
) : ViewModel() {

    private val _failure = MutableLiveData<EnrollmentFailure>()
    val failure: LiveData<EnrollmentFailure>
        get() = _failure

    private val _accessPointDevices = MutableLiveData<List<AccessPointDevice>>()
    val accessPointDevices: LiveData<List<AccessPointDevice>>
        get() = _accessPointDevices

    fun searchNearbyIPlantAccessPointDevices() {
        viewModelScope.launch {
            accessPointDeviceRepository.scanNearbyAccessPointDevices()
                .collect { accessPointResult ->
                    when (accessPointResult) {
                        is AccessPointResult.Success -> _accessPointDevices.value = accessPointResult.accessPointDevices
                        is AccessPointResult.Failure -> _failure.value = EnrollmentFailure.WifiScan
                    }
                }
        }
    }

}

sealed class EnrollmentFailure {
    object WifiScan : EnrollmentFailure()
}