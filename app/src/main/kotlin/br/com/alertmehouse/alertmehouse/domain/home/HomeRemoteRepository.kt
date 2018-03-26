package br.com.alertmehouse.alertmehouse.domain.home

import br.com.alertmehouse.alertmehouse.model.AlarmDevicesResponse
import br.com.alertmehouse.alertmehouse.model.AlarmStatus
import br.com.alertmehouse.alertmehouse.model.AlarmStatusResponse
import io.reactivex.Single

interface HomeRemoteRepository {
    fun setAlarmeStatus(alarmStatus: AlarmStatus): Single<AlarmStatusResponse>
    fun getAlarmDevices(): Single<AlarmDevicesResponse>
}