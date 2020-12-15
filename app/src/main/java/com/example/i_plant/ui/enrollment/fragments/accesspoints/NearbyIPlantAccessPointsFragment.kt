package com.example.i_plant.ui.enrollment.fragments.accesspoints

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.i_plant.R
import com.example.i_plant.core.base.BindingFragment
import com.example.i_plant.core.extension.observe
import com.example.i_plant.data.model.AccessPointDevice
import com.example.i_plant.databinding.FragmentNearbByIplantAccessPointsBinding
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

@ExperimentalCoroutinesApi
class NearbyIPlantAccessPointsFragment :
    BindingFragment<FragmentNearbByIplantAccessPointsBinding>() {

    private val viewModel: EnrollmentViewModel by sharedViewModel()

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) =
        DataBindingUtil.inflate(
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

        with(viewLifecycleOwner) {
            observe(viewModel.accessPointDevices, ::onAccessPointDevicesChanged)
        }
    }

    private fun onAccessPointDevicesChanged(list: List<AccessPointDevice>) {
        Log.d("TEST", "list of dvices $list")
    }

}