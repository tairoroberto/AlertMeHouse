package br.com.alertmehouse.alertmehouse.di

import br.com.alertmehouse.alertmehouse.view.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Binds all sub-components within the app.
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [(HomeModule::class)])
    internal abstract fun bindHomeFragment(): HomeFragment

    // Add bindings for other sub-components here
}
