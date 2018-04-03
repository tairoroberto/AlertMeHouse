package br.com.alertmehouse.alertmehouse.domain.home

import br.com.alertmehouse.alertmehouse.model.AlarmDevice
import br.com.alertmehouse.alertmehouse.model.AlarmDevicesResponse
import io.reactivex.Single

interface HomeRemoteRepository {
    fun setAlarmeStatus(alarmDevice: AlarmDevice): Single<AlarmDevice>
    fun getAlarmDevices(): Single<AlarmDevicesResponse>
}