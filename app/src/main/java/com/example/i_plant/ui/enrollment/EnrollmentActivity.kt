package com.example.i_plant.ui.enrollment

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.i_plant.R
import com.example.i_plant.data.repository.AccessPointDeviceRepository
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module


@ExperimentalCoroutinesApi
class EnrollmentActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context) : Intent {
            return Intent(context,EnrollmentActivity::class.java)
        }
    }

    private val enrollmentViewModel: EnrollmentViewModel by viewModel()

    private val accessPointDeviceRepository: AccessPointDeviceRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enrollment)

        Dexter.withContext(this)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) { /* ... */
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: List<PermissionRequest?>?,
                    token: PermissionToken?
                ) { /* ... */
                }
            }).check()

        lifecycleScope.launch {
            try {
                accessPointDeviceRepository.scanNearbyAccessPointDevices()
                    .collect { Log.d("TEST", "this is test $it") }
            } catch (exception: Exception) {
                Log.d("TEST", "TEST except")
            }
        }


    }
}

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { EnrollmentViewModel(get()) }
}