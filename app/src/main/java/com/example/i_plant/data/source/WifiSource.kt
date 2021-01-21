package com.example.i_plant.data.source

import android.net.wifi.ScanResult
import com.example.i_plant.wifi.WifiScanner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
class WifiSource(private val wifiScanner: WifiScanner) : IWifiSource {

    override fun scanNearbyAccessPointDevices(): Flow<List<IPlantAccessPoint>> = callbackFlow {
        try {
            wifiScanner.startScan { scanResult ->
                    sendBlocking(getIPlantAccessPoints(scanResult))
            }
        } catch (exception: Exception) {
            close(exception)
        }

        awaitClose {
            wifiScanner.stopScan()
        }

    }

    private fun getIPlantAccessPoints(scanResult: List<ScanResult>): List<IPlantAccessPoint> =
        scanResult
//            .filter { it.SSID.toLowerCase(Locale.ROOT).contains(IPlantDeviceConfig.SSID_PREFIX) }
            .map { it.mapToIPlantAccessPoint() }

}

fun ScanResult.mapToIPlantAccessPoint(): IPlantAccessPoint {
    return IPlantAccessPoint(SSID)
}

data class IPlantAccessPoint(val ssid: String)
