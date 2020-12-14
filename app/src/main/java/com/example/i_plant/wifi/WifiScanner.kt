package com.example.i_plant.wifi

import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import java.lang.Exception

class WifiScanner(
    private val wifiScanBroadCastReceiverHelper: WifiScanBroadCastReceiverHelper,
    private val wifiManager: WifiManager,
) {

    var counter = 0

    fun startScan(onScanResult: (WifiScannerResult) -> Unit) {
        wifiScanBroadCastReceiverHelper.startReceiving { intent -> onScanResult(handleIntent(intent)) }

        wifiManager.startScan()
    }

    private fun handleIntent(intent: Intent): WifiScannerResult {
        counter++
        if (counter == 3) {
            return WifiScannerResult.Failure
        }
        return if (intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)) {
            WifiScannerResult.Success(wifiManager.scanResults)
        } else {
            WifiScannerResult.Failure
        }
    }

    fun stopScan() {
        wifiScanBroadCastReceiverHelper.unregisterWifiScanBroadCastReceiver()
    }

}

sealed class WifiScannerResult {
    data class Success(val scanResult: List<ScanResult>) : WifiScannerResult()
    object Failure : WifiScannerResult()
}

