package com.example.i_plant

import android.app.Application
import com.example.i_plant.di.wifiModule
import com.example.i_plant.ui.enrollment.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class IPlantApplication : Application() {

    @ExperimentalCoroutinesApi
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@IPlantApplication)

            modules(
                wifiModule,
                viewModelModule
            )
        }
    }
}