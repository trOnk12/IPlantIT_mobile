package com.example.i_plant.ui.enrollment.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.i_plant.R
import com.example.i_plant.core.base.BindingFragment
import com.example.i_plant.databinding.FragmentNearbByIplantAccessPointsBinding
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.compat.SharedViewModelCompat.sharedViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

@ExperimentalCoroutinesApi
class NearbyIPlantAccessPointsFragment :
    BindingFragment<FragmentNearbByIplantAccessPointsBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNearbByIplantAccessPointsBinding {
        return DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_nearb_by_iplant_access_points,
            container,
            false
        ) as FragmentNearbByIplantAccessPointsBinding
    }

}