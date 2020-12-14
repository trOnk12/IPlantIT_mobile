package com.example.i_plant.data.source

import com.example.i_plant.data.model.AccessPointDevice
import kotlinx.coroutines.flow.Flow


// Implementation of WiFiSource could change in the future, due to deprecated WifiManager.startScan() method and possible being removed from the API.
interface IWifiSource {
    fun scanNearbyAccessPointDevices(): Flow<WifiSourceResult>
}




