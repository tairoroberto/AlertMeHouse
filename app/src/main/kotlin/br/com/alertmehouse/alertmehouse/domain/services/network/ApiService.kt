package br.com.alertmehouse.alertmehouse.domain.services.network

import br.com.alertmehouse.alertmehouse.model.AlarmDevicesResponse
import br.com.alertmehouse.alertmehouse.model.AlarmStatus
import br.com.alertmehouse.alertmehouse.model.AlarmStatusResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("alarmeStatus/")
    fun alarmeStatus(@Body alermStatus: AlarmStatus): Single<AlarmStatusResponse>

    @GET("getAlarmDevices/")
    fun getAlarmDevices(): Single<AlarmDevicesResponse>
}