package com.i_plant.ui.enrollment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.i_plant.wifi.WifiScanResult
import com.i_plant.wifi.WifiScanner

class EnrollmentViewModel(private val wifiScanner: WifiScanner) : ViewModel() {

    private val _wifiScanResult: MutableLiveData<WifiScanResult> = MutableLiveData<WifiScanResult>()
    val wifiScanResult: LiveData<WifiScanResult>
        get() = _wifiScanResult

    init {
        wifiScanner.startScan { wifiScanResult ->
            _wifiScanResult.value = wifiScanResult
        }
    }

    override fun onCleared() {
        super.onCleared()

        wifiScanner.stopScan()
    }

}