package br.com.alertmehouse.alertmehouse.di

import android.content.Context
import br.com.alertmehouse.alertmehouse.CustomApplication
import br.com.alertmehouse.alertmehouse.data.HomeRemoteDataStore
import br.com.alertmehouse.alertmehouse.domain.home.HomeRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
class AppModule {

    @Provides
    internal fun provideContext(application: CustomApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    internal fun provideHomeRemoteRepository(): HomeRemoteRepository {
        return HomeRemoteDataStore()
    }
}
