package com.example.i_plant.data.source

import android.net.wifi.ScanResult
import com.example.i_plant.config.IPlantDeviceConfig
import com.example.i_plant.data.model.AccessPointDevice
import com.example.i_plant.data.repository.AccessPointResult
import com.example.i_plant.wifi.WifiScanner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.*

@ExperimentalCoroutinesApi
class WifiSource(private val wifiScanner: WifiScanner) : IWifiSource {

    override fun scanNearbyAccessPointDevices(): Flow<AccessPointResult> = callbackFlow {
        try {
            wifiScanner.startScan { scanResult ->
                val accessPointDevices = getIPlantAccessPoints(scanResult)

                sendBlocking(AccessPointResult.Success(accessPointDevices))
            }
        } catch (exception: Exception) {
            sendBlocking(AccessPointResult.Failure)

            close()
        }

        awaitClose {
            wifiScanner.stopScan()
        }

    }

    private fun getIPlantAccessPoints(scanResult: List<ScanResult>): List<AccessPointDevice> =
        scanResult
//            .filter { it.SSID.toLowerCase(Locale.ROOT).contains(IPlantDeviceConfig.SSID_PREFIX) }
            .map { it.mapToAccessPointDevice() }

}

fun ScanResult.mapToAccessPointDevice(): AccessPointDevice {
    return AccessPointDevice(SSID)
}

