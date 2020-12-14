package com.i_plant.di

import android.content.Context
import android.net.wifi.WifiManager
import com.i_plant.wifi.WifiScanBroadCastReceiverHelper
import com.i_plant.wifi.WifiScanner
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val wifiModule = module {
    single { androidContext().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager }
    factory { WifiScanBroadCastReceiverHelper(get()) }
    factory { WifiScanner(get(), get()) }
}
