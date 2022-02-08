package com.neves.coin.data.remote

import com.neves.coin.domain.Either
import com.neves.coin.domain.model.Exchange


interface RemoteDataSource {
    suspend fun exchangeValue(coin:String): Either<Exchange, Exception>
}
