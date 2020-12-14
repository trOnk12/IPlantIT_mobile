package com.i_plant.ui.enrollment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.i_plant.R
import com.i_plant.ui.enrollment.viewmodel.EnrollmentViewModel
import com.i_plant.wifi.WifiScanResult
import com.i_plant.wifi.WifiScanner
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module

class EnrollmentActivity : AppCompatActivity() {

    private val enrollmentViewModel: EnrollmentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enrollment)

    }
}

val viewModelModule = module {
    viewModel { EnrollmentViewModel(get()) }
}