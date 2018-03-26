package br.com.alertmehouse.alertmehouse.viewmodel.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.alertmehouse.alertmehouse.domain.home.HomeUseCase
import br.com.alertmehouse.alertmehouse.model.AlarmDevice
import br.com.alertmehouse.alertmehouse.model.AlarmDevicesResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class HomeViewModel(private val homeUseCase: HomeUseCase) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val response: MutableLiveData<List<AlarmDevice>> = MutableLiveData()

    private val loadingStatus = MutableLiveData<Boolean>()

    private val errorStatus = MutableLiveData<String>()

    fun getLoadingStatus(): MutableLiveData<Boolean> {
        return loadingStatus
    }

    fun getErrorStatus(): MutableLiveData<String> {
        return errorStatus
    }

    fun getResponse(): MutableLiveData<List<AlarmDevice>> {
        return response
    }

    fun getDevices() {
        getAlarmDevices(homeUseCase.getAlarmDevices())
    }

    private fun getAlarmDevices(alarmDevicesResponse: Single<AlarmDevicesResponse>) {
        disposables.add(alarmDevicesResponse
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe({ loadingStatus.setValue(true) })
                .doAfterTerminate({ loadingStatus.setValue(false) })
                .doOnError { throwable -> errorStatus.value = throwable.message.toString() }
                .subscribe(
                        { alarmDevices ->
                            response.value = alarmDevices.devices
                        },
                        { throwable ->
                            errorStatus.value = throwable.message.toString()
                        }
                )
        )
    }
}