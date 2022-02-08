package com.neves.coin.core.di

import android.app.Application
import com.neves.coin.data.di.DataModule
import com.neves.coin.domain.di.DomainModule
import com.neves.coin.core.App
import com.neves.coin.data.di.LocalModule
import com.neves.coin.data.di.NetworkModule
import com.neves.coin.presentation.di.ActivityModuleBuilder
import com.neves.coin.presentation.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule


import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        ActivityModuleBuilder::class,
        ContextModule::class,
        NetworkModule::class,
        LocalModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}