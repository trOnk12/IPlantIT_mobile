package com.example.i_plant.ui.enrollment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.i_plant.core.extension.observe
import com.example.i_plant.data.model.AccessPointDevice
import com.example.i_plant.databinding.ActivityEnrollmentBinding
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentFailure
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.dsl.module


@ExperimentalCoroutinesApi
class EnrollmentActivity : AppCompatActivity() {
    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, EnrollmentActivity::class.java)
        }
    }

    private val binding: ActivityEnrollmentBinding by lazy {
        ActivityEnrollmentBinding.inflate(layoutInflater)
    }

    private val enrollmentViewModel: EnrollmentViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observe(enrollmentViewModel.failure, ::onFailure)
        observe(enrollmentViewModel.accessPointDevices, ::onAccessPointDevicesChanged)
    }

    private fun onAccessPointDevicesChanged(accessPointDevices: List<AccessPointDevice>) {
        Log.d("TEST","accessPointDevices nearby  $accessPointDevices")
    }

    private fun onFailure(failure: EnrollmentFailure) {
        when (failure) {
            is EnrollmentFailure.WifiScan ->  Toast.makeText(this,"Something when wrong while scanning wifi network",Toast.LENGTH_LONG).show()
        }
    }

}

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { EnrollmentViewModel(get()) }
}