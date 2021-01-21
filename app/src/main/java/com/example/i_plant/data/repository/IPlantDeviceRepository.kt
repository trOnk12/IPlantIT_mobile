package com.example.i_plant.data.repository

import com.example.i_plant.core.failure.Failure
import com.example.i_plant.core.functional.Either
import com.example.i_plant.core.functional.Either.Left
import com.example.i_plant.core.functional.Either.Right
import com.example.i_plant.data.source.IPlantAccessPoint
import com.example.i_plant.data.source.IWifiSource
import com.example.i_plant.domain.repository.IIPlantDeviceRepository
import com.example.i_plant.wifi.WifiScannerException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

@ExperimentalCoroutinesApi
class IPlantDeviceRepository(private val wifiSource: IWifiSource) : IIPlantDeviceRepository {

    override fun getNearbyAccessPointDevices(): Flow<Either<Failure, List<IPlantAccessPoint>>> = flow {
        wifiSource.scanNearbyAccessPointDevices()
            .catch { exception ->
                if (exception is WifiScannerException) {
                    emit(Left(IPlantDeviceRepositoryFailure.WifiScannerFailure))
                }
            }.collect { iPlantAccessPoint -> emit(Right(iPlantAccessPoint)) }
    }

}

sealed class IPlantDeviceRepositoryFailure {
    object WifiScannerFailure : Failure.FeatureFailure()
}