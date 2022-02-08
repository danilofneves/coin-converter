package com.neves.coin.domain.di

import com.neves.coin.domain.repository.ExchangeRepository
import com.neves.coin.domain.usecases.ExchangeValue
import com.neves.coin.domain.usecases.ListExchange
import com.neves.coin.domain.usecases.SaveExchange
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideSaveExchange(repository: ExchangeRepository): SaveExchange = SaveExchange(repository)

    @Provides
    fun provideListExchange(repository: ExchangeRepository): ListExchange = ListExchange(repository)

    @Provides
    fun provideExchangeValue(repository: ExchangeRepository): ExchangeValue = ExchangeValue(repository)

}