package com.i_plant.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager
import com.i_plant.core.SingletonHolder
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

class WifiScanBroadCastReceiverHelper(
    private val context: Context
) {

    private var wifiResultBroadCastReceiver: BroadcastReceiver? = null

    // catching IllegalStateException is work-around for the case when we try to register, already registered
    // BroadCastReceiver somewhere else in our code.
    fun startReceiving(onIntentResult: (Intent) -> Unit) {
        try {
            registerWifiScanBroadCastReceiver(onIntentResult)
        } catch (exception: IllegalStateException) {
            context.unregisterReceiver(wifiResultBroadCastReceiver)

            registerWifiScanBroadCastReceiver(onIntentResult)
        }
    }

    private fun registerWifiScanBroadCastReceiver(onReceive: (Intent) -> Unit) {
        wifiResultBroadCastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                onReceive(intent)
            }
        }

        val intentFilter = IntentFilter().apply {
            addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        }

        context.registerReceiver(wifiResultBroadCastReceiver, intentFilter)
    }

}

sealed class WifiScanResult {
    data class Success(val scanResult: List<String>) : WifiScanResult()
    object Failure : WifiScanResult()
}
