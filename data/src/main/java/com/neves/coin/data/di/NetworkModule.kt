package com.neves.coin.data.di

import com.neves.coin.data.local.LocalDataSource
import com.neves.coin.data.local.LocalDataSourceImpl
import com.neves.coin.data.local.database.ExchangeDAO
import com.neves.coin.data.remote.RemoteDataSource
import com.neves.coin.data.remote.RemoteDataSourceImpl
import com.neves.coin.data.remote.service.ExchangeService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object{
        private const val BASE_URL = "https://economia.awesomeapi.com.br"
        private const val TIME_CONNECT = 5
        private const val TIME_READ = 5
    }

    @Provides
    fun provideOkHttpBuilder(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIME_CONNECT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_READ.toLong(), TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideExchangeService(retrofit: Retrofit) : ExchangeService {
        return retrofit.create(ExchangeService::class.java)
    }

    @Provides
    fun provideRemoteDataSource(exchangeService: ExchangeService): RemoteDataSource = RemoteDataSourceImpl(exchangeService)

}