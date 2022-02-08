package com.neves.coin.data.di

import android.content.Context
import com.neves.coin.data.local.LocalDataSource
import com.neves.coin.data.local.LocalDataSourceImpl
import com.neves.coin.data.local.database.AppDatabase
import com.neves.coin.data.local.database.AppDatabase.Companion.getDatabase
import com.neves.coin.data.local.database.ExchangeDAO
import com.neves.coin.data.remote.RemoteDataSource
import com.neves.coin.data.repository.ExchangeRepositoryImpl
import com.neves.coin.domain.repository.ExchangeRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun bindExchangeRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): ExchangeRepository = ExchangeRepositoryImpl(localDataSource, remoteDataSource)

}