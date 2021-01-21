package com.example.i_plant.ui.enrollment.fragments.accesspoints

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.i_plant.R
import com.example.i_plant.core.base.BindingFragment
import com.example.i_plant.core.extension.observe
import com.example.i_plant.core.extension.showToast
import com.example.i_plant.data.model.AccessPointDevice
import com.example.i_plant.data.source.IPlantAccessPoint
import com.example.i_plant.databinding.FragmentNearbByIplantAccessPointsBinding
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentFailure
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.sharedViewModel

@ExperimentalCoroutinesApi
class NearbyIPlantAccessPointsFragment :
    BindingFragment<FragmentNearbByIplantAccessPointsBinding>() {

    private val viewModel: EnrollmentViewModel by sharedViewModel()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_nearb_by_iplant_access_points,
        container,
        false
    ) as FragmentNearbByIplantAccessPointsBinding

    override fun onAttach(context: Context) {

        super.onAttach(context)
        viewModel.searchNearbyIPlantAccessPointDevices()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observe(viewModel.failure, ::onFailure)
        observe(viewModel.iPlantAccessPoints, ::onIPlantAccessDevicesChange)
    }

    private fun onIPlantAccessDevicesChange(list: List<IPlantAccessPoint>) {
        Log.d("TEST", "list of dvices $list")
    }

    private fun onFailure(failure: EnrollmentFailure) {
        when (failure) {
            is EnrollmentFailure.WifiScanError -> showToast("Something when wrong while scanning wifi network")
            else -> showToast("Something unexpected happend.")
        }
    }

}
