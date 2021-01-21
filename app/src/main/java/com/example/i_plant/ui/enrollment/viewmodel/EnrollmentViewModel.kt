package com.example.i_plant.ui.enrollment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.i_plant.core.extension.launchAndCollect
import com.example.i_plant.core.failure.Failure
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentFailure.WifiScanError
import com.example.i_plant.ui.enrollment.viewmodel.EnrollmentFailure.UnknownError
import com.example.i_plant.data.repository.IPlantDeviceRepository
import com.example.i_plant.data.repository.IPlantDeviceRepositoryFailure
import com.example.i_plant.data.source.IPlantAccessPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class EnrollmentViewModel(
    private val iPlantDeviceRepository: IPlantDeviceRepository
) : ViewModel() {

    private val _failure = MutableLiveData<EnrollmentFailure>()
    val failure: LiveData<EnrollmentFailure>
        get() = _failure

    private val _iPlantAccessPoints = MutableLiveData<List<IPlantAccessPoint>>()
    val iPlantAccessPoints: LiveData<List<IPlantAccessPoint>>
        get() = _iPlantAccessPoints

    fun searchNearbyIPlantAccessPointDevices() {
        launchAndCollect(iPlantDeviceRepository.getNearbyAccessPointDevices()) { result ->
            result.fold(::onIPlantAccessDeviceFailure, ::onIPlantAccessDeviceFound)
        }
    }

    private fun onIPlantAccessDeviceFound(iPlantAccessPoints: List<IPlantAccessPoint>) {
        _iPlantAccessPoints.value = iPlantAccessPoints
    }

    private fun onIPlantAccessDeviceFailure(failure: Failure) {
        when (failure) {
            is IPlantDeviceRepositoryFailure.WifiScannerFailure -> _failure.value = WifiScanError
            else -> _failure.value = UnknownError
        }
    }

}

sealed class EnrollmentFailure {
    object UnknownError : EnrollmentFailure()
    object WifiScanError : EnrollmentFailure()
}