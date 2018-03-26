package br.com.alertmehouse.alertmehouse.data

import br.com.alertmehouse.alertmehouse.domain.home.HomeRemoteRepository
import br.com.alertmehouse.alertmehouse.domain.services.network.RemoteApiService
import br.com.alertmehouse.alertmehouse.model.AlarmDevicesResponse
import br.com.alertmehouse.alertmehouse.model.AlarmStatus
import br.com.alertmehouse.alertmehouse.model.AlarmStatusResponse
import io.reactivex.Single

/**
 * Created by tairo on 3/25/18 1:02 PM.
 */
class HomeRemoteDataStore : HomeRemoteRepository {
    override fun setAlarmeStatus(alarmStatus: AlarmStatus): Single<AlarmStatusResponse> {
        return setAlarmeStatus(alarmStatus)
    }

    override fun getAlarmDevices(): Single<AlarmDevicesResponse> {
        return RemoteApiService.getInstance().getApiService().getAlarmDevices()
    }
}