package com.neves.coin.data.local

import com.neves.coin.data.local.model.ExchangeRoom
import com.neves.coin.domain.Either
import com.neves.coin.domain.model.Exchange


interface LocalDataSource {
    suspend fun save(exchangeRoom: ExchangeRoom): Either<Unit, Exception>
    suspend fun getAll(): Either<List<Exchange>, Exception>
}
