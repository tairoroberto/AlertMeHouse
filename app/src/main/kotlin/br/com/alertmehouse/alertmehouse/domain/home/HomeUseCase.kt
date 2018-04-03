package br.com.alertmehouse.alertmehouse.domain.home

import br.com.alertmehouse.alertmehouse.model.AlarmDevice
import br.com.alertmehouse.alertmehouse.model.AlarmDevicesResponse
import io.reactivex.Single

/**
 * Created by tairo on 12/11/17.
 */
class HomeUseCase(private val homeRemoteRepository: HomeRemoteRepository) {

    fun setAlarmeStatus(alarmDevice: AlarmDevice): Single<AlarmDevice> {
        return homeRemoteRepository.setAlarmeStatus(alarmDevice)
    }

    fun getAlarmDevices(): Single<AlarmDevicesResponse> {
        return homeRemoteRepository.getAlarmDevices()
    }
}
