package com.example.i_plant.wifi

import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import java.lang.Exception

class WifiScanner(
    private val wifiScanBroadCastReceiverHelper: WifiScanBroadCastReceiverHelper,
    private val wifiManager: WifiManager,
) {

    fun startScan(onScanResult: (List<ScanResult>) -> Unit) {
        wifiScanBroadCastReceiverHelper.startReceiving { intent -> onScanResult(handleIntent(intent)) }

        wifiManager.startScan()
    }

    private fun handleIntent(intent: Intent): List<ScanResult> {
        return if (intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)) {
            wifiManager.scanResults
        } else {
            throw WifiScannerException()
        }
    }

    fun stopScan() {
        wifiScanBroadCastReceiverHelper.unregisterWifiScanBroadCastReceiver()
    }

}

class WifiScannerException : Exception()


