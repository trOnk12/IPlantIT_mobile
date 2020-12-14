package com.i_plant.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import java.lang.IllegalStateException

class WifiScanner(
    private val wifiScanBroadCastReceiverHelper: WifiScanBroadCastReceiverHelper,
    private val wifiManager: WifiManager,
) {

    fun startScan(onScanResult: (WifiScanResult) -> Unit) {
        wifiScanBroadCastReceiverHelper.startReceiving { intent -> onScanResult(handleIntent(intent)) }

        wifiManager.startScan()
    }

    private fun handleIntent(intent: Intent): WifiScanResult {

    }

}

sealed class WifiScanResult {
    data class Success(val scanResult: List<String>) : WifiScanResult()
    object Failure : WifiScanResult()
}
