package com.neves.coin.core

import android.app.Application
import com.neves.coin.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

}