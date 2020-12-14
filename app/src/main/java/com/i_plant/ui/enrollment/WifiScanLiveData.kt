package com.i_plant.ui.enrollment

import androidx.lifecycle.LiveData
import com.i_plant.wifi.WifiScanResult
import com.i_plant.wifi.WifiScanner
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@KoinApiExtension
class WifiScanLiveData : LiveData<WifiScanResult>(), KoinComponent {

    private val wifiScanner : WifiScanner by inject()



}
