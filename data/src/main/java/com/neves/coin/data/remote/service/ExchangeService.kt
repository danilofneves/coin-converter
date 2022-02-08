package com.neves.coin.data.remote.service

import com.neves.coin.data.remote.model.ExchangeResponseType
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeService {

    @GET("/json/last/{coins}")
    suspend fun exchangeValue(@Path("coins") coins: String): ExchangeResponseType
}