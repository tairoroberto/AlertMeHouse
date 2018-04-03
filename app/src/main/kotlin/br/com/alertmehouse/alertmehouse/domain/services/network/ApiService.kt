package br.com.alertmehouse.alertmehouse.domain.services.network

import br.com.alertmehouse.alertmehouse.model.AlarmDevice
import br.com.alertmehouse.alertmehouse.model.AlarmDevicesResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("device/")
    fun setAlarmDevice(@Body alarmDevice: AlarmDevice): Single<AlarmDevice>

    @GET("device/")
    fun getAlarmDevices(): Single<AlarmDevicesResponse>
}