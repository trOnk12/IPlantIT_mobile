package com.example.i_plant.wifi

import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager
import java.lang.Exception

class WifiScanner(
    private val wifiScanBroadCastReceiverHelper: WifiScanBroadCastReceiverHelper,
    private val wifiManager: WifiManager,
) {

//    var counter = 0

    fun startScan(onScanResult: (List<ScanResult>) -> Unit) {
        wifiScanBroadCastReceiverHelper.startReceiving { intent -> onScanResult(handleIntent(intent)) }

        wifiManager.startScan()
    }

    private fun handleIntent(intent: Intent): List<ScanResult> {
//        counter++
//        if (counter == 3) {
//            return WifiScannerResult.Failure
//        }
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
//sealed class WifiScannerResult {
//    data class Success(val scanResult: List<ScanResult>) : WifiScannerResult()
//    object Failure : WifiScannerResult()
//}

