package br.com.alertmehouse.alertmehouse.di

import br.com.alertmehouse.alertmehouse.domain.home.HomeRemoteRepository
import br.com.alertmehouse.alertmehouse.domain.home.HomeUseCase
import br.com.alertmehouse.alertmehouse.viewmodel.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

/**
 * Define HomeFragment-specific dependencies here.
 */
@Module
class HomeModule {

    @Provides
    internal fun provideHomeViewModelFactory(homeUseCase: HomeUseCase): HomeViewModelFactory {
        return HomeViewModelFactory(homeUseCase)
    }

    @Provides
    internal fun provideHomeUseCase(remoteRepository: HomeRemoteRepository): HomeUseCase {
        return HomeUseCase(remoteRepository)
    }
}
