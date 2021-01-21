package com.example.i_plant.domain.repository

import com.example.i_plant.core.failure.Failure
import com.example.i_plant.core.functional.Either
import com.example.i_plant.data.model.AccessPointDevice
import com.example.i_plant.data.source.IPlantAccessPoint
import kotlinx.coroutines.flow.Flow

interface IIPlantDeviceRepository {

    fun getNearbyAccessPointDevices(): Flow<Either<Failure,List<IPlantAccessPoint>>>

}