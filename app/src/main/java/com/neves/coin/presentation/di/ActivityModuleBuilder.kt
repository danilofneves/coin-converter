package com.neves.coin.presentation.di

import com.neves.coin.presentation.ui.history.HistoryActivity
import com.neves.coin.presentation.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModuleBuilder {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHistoryActivity(): HistoryActivity

}