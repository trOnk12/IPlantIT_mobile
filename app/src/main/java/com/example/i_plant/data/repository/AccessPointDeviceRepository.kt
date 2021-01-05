package com.example.i_plant.data.repository

import com.example.i_plant.data.model.AccessPointDevice
import com.example.i_plant.data.source.IWifiSource
import com.example.i_plant.data.source.WifiSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class AccessPointDeviceRepository(
    private val wifiSource: IWifiSource
) {

    fun scanNearbyAccessPointDevices(): Flow<AccessPointResult> =
        wifiSource.scanNearbyAccessPointDevices()

}

sealed class AccessPointResult {
    data class Success(val accessPointDevices: List<AccessPointDevice>) : AccessPointResult()
    data class Failure(val exception : Exception) : AccessPointResult()
}
