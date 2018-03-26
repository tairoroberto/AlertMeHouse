package br.com.alertmehouse.alertmehouse.viewmodel.home

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.alertmehouse.alertmehouse.domain.home.HomeUseCase

class HomeViewModelFactory(private val homeUseCase: HomeUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}