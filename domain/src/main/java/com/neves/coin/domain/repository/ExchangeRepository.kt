package com.neves.coin.domain.repository

import com.neves.coin.domain.Either
import com.neves.coin.domain.model.Exchange


interface ExchangeRepository {
    suspend fun exchangeValue(coin:String): Either<Exchange, Exception>
    suspend fun save(exchange: Exchange): Either<Unit, Exception>
    suspend fun getAll(): Either<List<Exchange>, Exception>
}