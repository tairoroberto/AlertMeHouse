package br.com.alertmehouse.alertmehouse.domain.home

import br.com.alertmehouse.alertmehouse.model.AlarmDevicesResponse
import br.com.alertmehouse.alertmehouse.model.AlarmStatus
import br.com.alertmehouse.alertmehouse.model.AlarmStatusResponse
import io.reactivex.Single

/**
 * Created by tairo on 12/11/17.
 */
class HomeUseCase(private val homeRemoteRepository: HomeRemoteRepository) {

    fun setAlarmeStatus(alarmStatus: AlarmStatus): Single<AlarmStatusResponse> {
        return homeRemoteRepository.setAlarmeStatus(alarmStatus)
    }

    fun getAlarmDevices(): Single<AlarmDevicesResponse> {
        return homeRemoteRepository.getAlarmDevices()
    }
}
