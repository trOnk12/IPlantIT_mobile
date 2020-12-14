package com.i_plant.wifi

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiManager

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

    private fun registerWifiScanBroadCastReceiver(onIntentResult: (Intent) -> Unit) {
        wifiResultBroadCastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                onIntentResult(intent)
            }
        }

        val intentFilter = IntentFilter().apply {
            addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        }

        context.registerReceiver(wifiResultBroadCastReceiver, intentFilter)
    }

    fun unregisterWifiScanBroadCastReceiver() {
        context.unregisterReceiver(wifiResultBroadCastReceiver)
        wifiResultBroadCastReceiver = null
    }

}
