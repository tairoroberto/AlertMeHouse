package br.com.alertmehouse.alertmehouse.data

import br.com.alertmehouse.alertmehouse.domain.home.HomeRemoteRepository
import br.com.alertmehouse.alertmehouse.domain.services.network.RemoteApiService
import br.com.alertmehouse.alertmehouse.model.AlarmDevice
import br.com.alertmehouse.alertmehouse.model.AlarmDevicesResponse
import io.reactivex.Single

/**
 * Created by tairo on 3/25/18 1:02 PM.
 */
class HomeRemoteDataStore : HomeRemoteRepository {
    override fun setAlarmeStatus(alarmDevice: AlarmDevice): Single<AlarmDevice> {
        return RemoteApiService.getInstance().getApiService().setAlarmDevice(alarmDevice)
    }

    override fun getAlarmDevices(): Single<AlarmDevicesResponse> {
        return RemoteApiService.getInstance().getApiService().getAlarmDevices()
    }
}