package com.i_plant

import android.app.Application
import com.i_plant.di.wifiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IPlantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@IPlantApplication)

            modules(wifiModule)
        }
    }
}