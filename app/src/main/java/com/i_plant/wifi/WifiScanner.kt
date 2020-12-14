package com.i_plant.wifi

import android.content.Intent
import android.net.wifi.ScanResult
import android.net.wifi.WifiManager

class WifiScanner(
    private val wifiScanBroadCastReceiverHelper: WifiScanBroadCastReceiverHelper,
    private val wifiManager: WifiManager,
) {

    fun startScan(onScanResult: (WifiScanResult) -> Unit) {
        wifiScanBroadCastReceiverHelper.startReceiving { intent -> onScanResult(handleIntent(intent)) }

        wifiManager.startScan()
    }

    private fun handleIntent(intent: Intent): WifiScanResult {
        return if (intent.getBooleanExtra(WifiManager.EXTRA_RESULTS_UPDATED, false)) {
            WifiScanResult.Success(wifiManager.scanResults)
        } else {
            WifiScanResult.Failure
        }
    }

    fun stopScan(){
        wifiScanBroadCastReceiverHelper.unregisterWifiScanBroadCastReceiver()
    }

}

sealed class WifiScanResult {
    data class Success(val scanResult: List<ScanResult>) : WifiScanResult()
    object Failure : WifiScanResult()
}
