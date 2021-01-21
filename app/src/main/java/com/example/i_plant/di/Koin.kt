package com.example.i_plant.di

import android.content.Context
import android.net.wifi.WifiManager
import com.example.i_plant.data.repository.IPlantDeviceRepository
import com.example.i_plant.data.source.IWifiSource
import com.example.i_plant.data.source.WifiSource
import com.example.i_plant.wifi.WifiScanBroadCastReceiverHelper
import com.example.i_plant.wifi.WifiScanner
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val wifiModule = module {
    single { androidContext().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager }
    factory { WifiScanBroadCastReceiverHelper(get()) }
    factory { WifiScanner(get(), get()) }
    single { IPlantDeviceRepository(get())}
    single<IWifiSource> { WifiSource(get()) }
}
