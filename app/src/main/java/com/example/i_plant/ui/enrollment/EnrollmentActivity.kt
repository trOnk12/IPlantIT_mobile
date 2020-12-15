package com.example.i_plant.ui.enrollment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.i_plant.R
import com.example.i_plant.data.repository.AccessPointDeviceRepository
import com.example.i_plant.databinding.ActivityEnrollmentBinding
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
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

    }


}

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { EnrollmentViewModel(get()) }
}